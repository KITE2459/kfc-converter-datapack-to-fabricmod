# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A compiler that turns a **Minecraft datapack** (`.mcfunction` command scripts) into a **native Fabric mod Java source tree**. One datapack function → one generated Java class; commands become direct Minecraft API calls instead of being interpreted by the vanilla command engine. The goal is vanilla-identical observable behavior at a fraction of the tick cost (see [CHANGELOG-optimization.md](CHANGELOG-optimization.md) for measured mspt deltas).

Source comments, docstrings and reports are written in **Korean** — match that when editing.

## Toolchain

**JDK 21 exactly** (MC 1.21.5 + Loom needs it; Java 24/25 fails with `Unsupported class file major version`). [gradle_project/gradle.properties](gradle_project/gradle.properties) pins `org.gradle.java.home=../openjdk-21-x86` (a JDK is vendored at [openjdk-21-x86/](openjdk-21-x86/), untracked). Python 3.10+; PyPy is auto-detected and used for the Python stages if `pypy3` is on PATH.

## Commands

Full conversion (datapack → Java source), run from [gradle_project/](gradle_project/):

```bash
cd gradle_project
./gradlew convert -Pdatapack=../mypack.zip -Pout=../mysrc      # see parsebuild.sh
#   -Pjobs=8    generate-stage worker processes (default: CPU count)
#   -Pheap=6G   parse-stage JVM heap (default 4G)
#   -Ppython=python3
```

Build the produced mod: `cd ../mysrc && ./gradlew build` → `build/libs/*.jar`.

**Fast iteration loop** — after changing `emit.py`/`assemble.py`/any pass, do *not* re-run the whole pipeline. The parse stage is the slow part and its output is cached at `gradle_project/build/kfc/trees.json`; re-run only `generate` ([build.sh](build.sh)):

```bash
python convert.py generate gradle_project/build/kfc/trees.json mypack.zip mysrc
#   --group <pkg>   override package root (default: sanitized datapack filename)
#   --bridge <fid-prefix>   force those functions to bridge (bisecting a miscompile)
#   --trace  <fid-prefix>   log execution context on entry (server console)
#   --no-merge / --no-clean
```

Individual stages: `python convert.py extract <datapack> <lines.json>`. (`convert.py build` wants a `HeadlessParser.java` that is **not** in this repo — the Gradle `convert` task is the working path.)

Checks / debug entry points (no test framework; `fixtures/trees_sample.json` referenced by the `__main__` blocks is absent, so pass a real trees.json):

```bash
python emit.py <trees.json> [datapack_root]        # per-line native/gated/bridge dump
python assemble.py <trees.json>                    # one function → Java class dump
python validate_types.py KfcGen.java mysrc/src/main/java [--strict]   # KfcGen call-type gate, pre-javac
python ebb_sim_verify.py                           # randomized equivalence sim for opt_post pass-2.9
```

Commit via [gitpush.sh](gitpush.sh) (timestamp commit messages, `git add .` + push).

## Pipeline

`extract` (Python) → `parse` (Java, on a real Fabric server) → `generate` (Python, many passes).

1. **extract** — [convert.py](convert.py) reads the datapack (zip or dir, transparently via [datapack_io.py](datapack_io.py) — zips are read in memory, never unpacked) and emits `lines.json`: every function id → its command lines, plus tick/load tags.
2. **parse** — [ParseDumper.java](ParseDumper.java) runs as a Fabric mod under Loom's `runServer` and dumps brigadier parse trees to `trees.json` (JSONL, streamed). A real server boot is required: the MC command parser only works correctly under Fabric Loader's class transformation. The server boots a flat world, parses at `SERVER_STARTED`, and stops itself — no game loop. `build.gradle` stages the datapack into `run/world/datapacks/` **with `function/` and `tags/function/` stripped**, so registry tags/predicates resolve while avoiding double-parsing hundreds of thousands of functions. A free loopback port ≥20000 is picked to avoid 25565 conflicts.
3. **generate** — [emit.py](emit.py) turns each parse tree into Java statements; [assemble.py](assemble.py) wraps a function's statements into a class; then the post-passes below rewrite the generated *text* before it is written out.

### Generate passes (search `pass-` in convert.py to find each call site)

| pass | module | what |
|---|---|---|
| 1 / 1.5 | convert.py | raw JSONL scan for macro functions + fids; interprocedural tag summaries |
| 2 | convert.py (multiprocessing) | per-function emit+assemble |
| 2.7 | [tree_flatten.py](tree_flatten.py) | collapse score-dispatch binary-search function trees into one interval table + worker (can be >50% of all functions in a large pack) |
| 2.75 / 2.78 / 2.9 | [opt_post.py](opt_post.py) | entity-direct score holders; deferred `on passengers` sources; demote `#fakeplayer` score chains to Java locals within straight-line regions |
| 2.8 | [const_fold.py](const_fold.py) | fold provably-constant `#fakeplayer` scores into int literals |
| 3 | [merge_pass.py](merge_pass.py) | absorb single-caller leaf classes into callers; bucket many functions into shared classes (class-count / 64KB-method / constant-pool limits) |
| 4 | convert.py | hoist emitted constant arrays to static finals |

Every optimizing pass is **fail-closed**: its module docstring carries the vanilla-equivalence argument and the exact conditions under which the rewrite is allowed. Preserve that discipline — when a condition cannot be proven, leave the original form. Passes have kill switches (module-level flags like `FOLD_CONST_SCORES`, `REWRITE_ENTITY_HOLDERS`).

### native / gated / bridge

`emit_line()` classifies each command line (`Emitted.kind`):
- **native** — compiled to direct API calls.
- **gated** — native but guarded by a runtime condition.
- **bridge** — not safely compilable; falls back to running the original command string through `KfcGen.runCommand()`. `Emitted.reason` records why, and it lands in `CONVERSION_REPORT.md` in the output dir. Bridging is the safety valve: prefer a bridge over a semantically risky native emission.

Because bridges re-enter the vanilla command engine, the generated mod **ships the original datapack inside itself** (resources are copied to `src/main/resources/data/`). Users must remove the original datapack from `datapacks/` — see [usage.md](usage.md).

## Runtime support library (Java, at repo root)

These are templates, not compiled here — `convert.py` copies them into the output tree with the package rewritten to `<group>`:

- **[KfcGen.java](KfcGen.java)** (~600KB) — everything generated code calls: scoreboard ops, selectors, NBT, caches (holder/sound/tag/SNBT/NbtPath), the bridge `runCommand`, and cache-coherence machinery. Declared `package kartriderpack.generated`; `convert.py` substitutes the real group. Single-threaded by design (server main thread only) — plain `HashMap`s, no concurrency.
- **`Kfc*Mixin.java`** — collected by **glob**, so adding a new `Kfc<Name>Mixin.java` next to `convert.py` is enough; it is copied into `<group>/mixin/` and registered in `<mod_id>.mixins.json` automatically.

**KfcGen and the mixins must stay in sync.** Reusing an old `generated_src` while updating only `KfcGen.java` silently disables optimizations (a past incident: tag-bucket cost went 0.08 → 1.59 mspt). Regenerating fixes it; if you hand-patch, sync `<group>/mixin/` *and* the mixins.json list together.

Runtime `-D` toggles (revert individual optimizations in-game without rebuilding): `kfc.sectionidx`, `kfc.taghook`, `kfc.entcells`, `kfc.reconphase`, `kfc.extsel`, `kfc.displaymerge`, `kfc.queryidx`, `kfc.snbttemplate`, `kfc.tagfp`, `kfc.itpid`, plus `kfc.debug.tagbucket` / `kfc.debug.coherence`.

Python env vars: `KFC_JOBS` (worker count), `KFC_SLOW_STRIP=1` (use the reference state-machine comment stripper instead of the regex fast path — for bisecting regressions), `KFC_SAFE_CMDS`, `KFC_NONVANILLA_SAFE`, `KFC_RES_MANIFEST`.

## Editing notes

- Edit **[ParseDumper.java](ParseDumper.java) at the repo root**; `gradle_project`'s `setupParserSource` task copies it into `src/main/java/datapackconvert/parsedumper/` on every build. The copy under `gradle_project/` is tracked but generated.
- MC/Fabric versions live in two places that must agree: the 4 lines in [gradle_project/gradle.properties](gradle_project/gradle.properties) and the `BuildProfile` in [build_config.py](build_config.py). Core logic (emit/assemble/convert) is meant to stay version-agnostic; a minor MC bump should require nothing else.
- Generated output layout: `<group>/ModEntry.java` (tick tag → server tick event, registers the `kfc-converted` detection command, `KfcGen.resetAll` on server stop), `<group>/generated/` (KfcGen + flattened trees), `<group>/mixin/`, `<group>/buckets/`, `<group>/<namespace>/...` per-function classes.
- `.gitignore` excludes `*.json`, `test*`, `kart*`, `datapack-all-in-one/` — sample packs and generated source trees in this working dir are deliberately untracked.
