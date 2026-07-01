package datapackconvert.parsedumper;

import com.google.gson.GsonBuilder;
import com.google.gson.Strictness;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.context.ParsedArgument;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.tree.CommandNode;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.minecraft.command.EntitySelector;
import net.minecraft.server.command.ServerCommandSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * ParseDumper (ModInitializer · 스트리밍 버전)
 *
 * <p>Fabric Loader 가 서버를 정상 부팅하게 두고, 부팅 직후(SERVER_STARTED) 서버가
 * 만들어 둔 dispatcher 로 파싱한다. 레지스트리(블록/아이템/엔티티/태그)는 완전히
 * 채워져 있고 클래스 검증도 통과한 상태다 → IllegalAccessError/VerifyError 없음.
 *
 * <p><b>대용량(100MB+ zip) 데이터팩 대응:</b> 모든 줄의 파스트리를 메모리에 모았다가
 * 한 번에 직렬화하면 거대 문자열에서 OutOfMemoryError 가 난다. 이 버전은 입력을
 * {@link JsonReader} 로 증분 파싱(전체 문자열 적재 없음)하고, 출력을 {@link JsonWriter}
 * 에 줄 단위로 직접 기록한다. 줄마다 중간 {@code Map} 을 만들지 않고 writer 에 필드를
 * 직접 쓰므로 줄당 객체 할당과 Gson 리플렉션 직렬화 비용이 모두 사라진다.
 *
 * <p>안전장치: 시스템 프로퍼티 parsedumper.in 이 있을 때만 동작. 파싱 후 서버 종료.
 *
 * 실행:
 *   ./gradlew runServer -Dparsedumper.in=build/convert/lines.json -Dparsedumper.out=build/convert/trees.json
 */
public final class ParseDumper implements ModInitializer {

    private static final int FLUSH_EVERY = 20_000;   // 2만 줄마다 flush

    @Override
    public void onInitialize() {
        System.out.println("[ParseDumper] mod loaded (entrypoint active)");
        String inProp = prop("parsedumper.in");
        if (inProp == null) {
            System.out.println("[ParseDumper] parsedumper.in 미지정 → 파싱 비활성(일반 서버로 동작)");
            return;   // 파싱 요청 없음 → 비활성
        }
        String outProp = prop("parsedumper.out");
        Path inPath = Path.of(inProp);
        Path outPath = Path.of(outProp != null ? outProp : "build/convert/trees.json");
        System.out.println("[ParseDumper] in=" + inPath + " out=" + outPath);

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            try {
                CommandDispatcher<ServerCommandSource> dispatcher =
                        server.getCommandManager().getDispatcher();
                ServerCommandSource src = server.getCommandSource();

                if (outPath.getParent() != null) Files.createDirectories(outPath.getParent());

                long lineCount = 0;
                int threads = parseThreads();
                // 출력 형식: JSONL (줄당 객체 1개). 입력 lines.json 이 {fid:[lines]} 맵이라
                // 함수별로 순서 처리되어, 같은 함수 객체가 연속으로 나온다 → generate 가
                // 함수 경계에서 변환 후 메모리를 비울 수 있어 700MB+ trees 도 메모리 일정.
                try (JsonReader jr = new JsonReader(Files.newBufferedReader(inPath));
                     java.io.BufferedWriter out = Files.newBufferedWriter(outPath)) {

                    jr.setStrictness(Strictness.LENIENT);

                    if (threads > 1) {
                        // 병렬 파싱: dispatcher/src 는 파싱 중 읽기 전용이라 줄 단위로 병렬화 가능.
                        // 배치 스트리밍이라 메모리는 배치 크기로 한정. -Dkfc.parse.threads=1 로 비활성(순차).
                        lineCount = dumpBatchedParallel(dispatcher, src, jr, out, threads);
                        System.out.println("[ParseDumper] parallel parse with " + threads + " threads");
                    } else {
                    switch (jr.peek()) {
                        case BEGIN_ARRAY -> {                // 구버전: 줄 배열
                            jr.beginArray();
                            while (jr.hasNext()) {
                                out.write(dumpLineToString(dispatcher, src, jr.nextString(), null));
                                out.write('\n');
                                if (++lineCount % FLUSH_EVERY == 0) out.flush();
                            }
                            jr.endArray();
                        }
                        default -> {                         // 신버전: 함수ID → 줄 배열 맵
                            jr.beginObject();
                            while (jr.hasNext()) {
                                String fid = jr.nextName();
                                jr.beginArray();
                                while (jr.hasNext()) {
                                    out.write(dumpLineToString(dispatcher, src, jr.nextString(), fid));
                                    out.write('\n');
                                    if (++lineCount % FLUSH_EVERY == 0) out.flush();
                                }
                                jr.endArray();
                            }
                            jr.endObject();
                        }
                    }
                    out.flush();
                    }
                }
                System.out.println("[ParseDumper] " + lineCount + " lines -> " + outPath
                        + " (unique cached: " + BODY_CACHE.size()
                        + ", reuse: " + (lineCount > 0 ? (100 * (lineCount - BODY_CACHE.size()) / lineCount) : 0) + "%)");
            } catch (Throwable t) {
                System.err.println("[ParseDumper] FAILED: " + t);
                t.printStackTrace();
            } finally {
                server.stop(false);
            }
        });
    }

    private static String prop(String key) {
        String v = System.getProperty(key);
        if (v == null) v = System.getenv(key.replace('.', '_').toUpperCase());
        return (v == null || v.isBlank()) ? null : v;
    }

    /** 파싱 스레드 수: -Dkfc.parse.threads / KFC_PARSE_THREADS, 기본 = 가용 코어. 1 이면 순차(안전 폴백). */
    private static int parseThreads() {
        String v = prop("kfc.parse.threads");
        if (v != null) {
            try { int n = Integer.parseInt(v.trim()); if (n > 0) return n; } catch (NumberFormatException ignored) {}
        }
        return Math.max(1, Runtime.getRuntime().availableProcessors());
    }

    // ── 배치 스트리밍 병렬 파싱 ──
    // 입력을 BATCH 단위로 모아, 배치 내 '캐시에 없는 고유 줄'을 스레드풀로 병렬 파싱한 뒤
    // 원래 순서대로 기록한다. dispatcher/src 는 파싱 중 읽기 전용이라 줄 단위 병렬이 안전.
    // 메모리는 BATCH(기본 2만) 분량으로 한정 → 700MB+ trees 도 메모리 일정.
    private static final int PARSE_BATCH = 20_000;

    private static long dumpBatchedParallel(CommandDispatcher<ServerCommandSource> dispatcher,
                                            ServerCommandSource src, JsonReader jr,
                                            java.io.BufferedWriter out, int threads) throws IOException {
        java.util.concurrent.ExecutorService pool =
                java.util.concurrent.Executors.newFixedThreadPool(threads);
        long count = 0;
        try {
            java.util.List<String[]> batch = new java.util.ArrayList<>(PARSE_BATCH);
            switch (jr.peek()) {
                case BEGIN_ARRAY -> {
                    jr.beginArray();
                    while (jr.hasNext()) {
                        batch.add(new String[]{null, jr.nextString()});
                        if (batch.size() >= PARSE_BATCH) { count += flushBatch(pool, dispatcher, src, batch, out, threads); batch.clear(); }
                    }
                    jr.endArray();
                }
                default -> {
                    jr.beginObject();
                    while (jr.hasNext()) {
                        String fid = jr.nextName();
                        jr.beginArray();
                        while (jr.hasNext()) {
                            batch.add(new String[]{fid, jr.nextString()});
                            if (batch.size() >= PARSE_BATCH) { count += flushBatch(pool, dispatcher, src, batch, out, threads); batch.clear(); }
                        }
                        jr.endArray();
                    }
                    jr.endObject();
                }
            }
            if (!batch.isEmpty()) count += flushBatch(pool, dispatcher, src, batch, out, threads);
            out.flush();
        } finally {
            pool.shutdown();
        }
        return count;
    }

    private static long flushBatch(java.util.concurrent.ExecutorService pool,
                                   CommandDispatcher<ServerCommandSource> dispatcher,
                                   ServerCommandSource src, java.util.List<String[]> batch,
                                   java.io.BufferedWriter out, int threads) throws IOException {
        // 1) 이 배치에서 아직 캐시에 없는 고유 줄 수집
        java.util.LinkedHashSet<String> todoSet = new java.util.LinkedHashSet<>();
        for (String[] b : batch) if (!BODY_CACHE.containsKey(b[1])) todoSet.add(b[1]);
        // 2) 병렬 파싱 → local 에 본문 적재(캐시 가득이어도 이 배치 출력은 재파싱 없이 처리)
        final java.util.concurrent.ConcurrentHashMap<String, String> local =
                new java.util.concurrent.ConcurrentHashMap<>();
        if (!todoSet.isEmpty()) {
            final java.util.List<String> list = new java.util.ArrayList<>(todoSet);
            int n = list.size();
            int per = (n + threads - 1) / threads;
            java.util.List<java.util.concurrent.Future<?>> fs = new java.util.ArrayList<>();
            for (int t = 0; t < n; t += per) {
                final int s0 = t, e0 = Math.min(n, t + per);
                fs.add(pool.submit(() -> {
                    for (int i = s0; i < e0; i++) {
                        String ln = list.get(i);
                        try { local.put(ln, bodyOf(dispatcher, src, ln)); }
                        catch (Throwable ex) { /* 한 줄 파싱 예외는 격리: 순차 경로에서 보정 */ }
                    }
                    return null;
                }));
            }
            for (java.util.concurrent.Future<?> f : fs) {
                try { f.get(); } catch (Exception ex) { throw new IOException("parallel parse failed", ex); }
            }
        }
        // 3) 원래 순서대로 기록 (local → 캐시 → 최후 순차 파싱)
        for (String[] b : batch) {
            String body = local.get(b[1]);
            if (body == null) body = bodyOf(dispatcher, src, b[1]);
            out.write(injectFid(body, b[0]));
            out.write('\n');
        }
        return batch.size();
    }

    // ────────────────────────────────────────────────────────────────────
    //  한 줄 → JsonWriter 에 객체 직접 기록 (중간 Map 없음)
    //  스키마(필드 순서 보존): line, macro, [매크로면 macroVars/reparsed/...],
    //                          ok, [error|command+chain|nodes], function?
    // ────────────────────────────────────────────────────────────────────
    // ── 줄 단위 파싱 결과 캐시 ──
    // 카트팩 등 데이터팩은 동일 명령 줄이 수천 번 반복된다(측정상 30%+ 중복).
    // 같은 rawLine 은 파싱 결과가 동일하므로, function 필드를 뺀 '본문 객체 JSON'을
    // 캐싱해 재사용한다. 출력 시 function 만 '}' 앞에 끼워 넣는다.
    // 메모리 보호: 고유 줄 수가 매우 많을 수 있으므로 상한을 두고 넘으면 캐시를 비활성.
    private static final int CACHE_MAX = 200_000;
    private static final java.util.concurrent.ConcurrentHashMap<String, String> BODY_CACHE =
            new java.util.concurrent.ConcurrentHashMap<>();
    private static com.google.gson.Gson GSON_COMPACT;

    /** rawLine → 본문 객체 JSON(function 미포함). 캐시 활용. 스레드 안전(ConcurrentHashMap). */
    private static String bodyOf(CommandDispatcher<ServerCommandSource> dispatcher,
                                 ServerCommandSource src, String rawLine) throws IOException {
        String body = BODY_CACHE.get(rawLine);
        if (body == null) {
            java.io.StringWriter sw = new java.io.StringWriter(128);
            JsonWriter bw = new JsonWriter(sw);
            bw.setStrictness(Strictness.LENIENT);
            bw.setIndent("");
            buildBody(dispatcher, src, rawLine, bw);   // dispatcher/src 는 파싱 중 읽기 전용 → 병렬 안전
            bw.flush();
            body = sw.toString();
            if (BODY_CACHE.size() < CACHE_MAX) BODY_CACHE.put(rawLine, body);
        }
        return body;
    }

    /** 본문 JSON 의 '}' 앞에 function 필드를 끼워 넣는다(JSONL 한 줄 완성). */
    private static String injectFid(String body, String fid) {
        if (fid == null) return body;
        int close = body.lastIndexOf('}');
        String fjson = gsonCompact().toJson(fid);
        boolean empty = body.substring(0, close).trim().endsWith("{");
        return body.substring(0, close) + (empty ? "" : ",") + "\"function\":" + fjson + "}";
    }

    /** 한 줄 → 완성된 객체 JSON 문자열(function 포함). JSONL 출력용. 캐시 활용. */
    private static String dumpLineToString(CommandDispatcher<ServerCommandSource> dispatcher,
                                           ServerCommandSource src, String rawLine,
                                           String fid) throws IOException {
        return injectFid(bodyOf(dispatcher, src, rawLine), fid);
    }

    private static com.google.gson.Gson gsonCompact() {
        if (GSON_COMPACT == null) GSON_COMPACT = new GsonBuilder().disableHtmlEscaping().create();
        return GSON_COMPACT;
    }

    /** function 필드를 제외한 본문 객체({line,macro,ok,...})를 bw 에 기록. */
    private static void buildBody(CommandDispatcher<ServerCommandSource> dispatcher,
                                  ServerCommandSource src, String rawLine,
                                  JsonWriter jw) throws IOException {
        boolean macro = rawLine.startsWith("$");
        String line = macro ? rawLine.substring(1) : rawLine;

        jw.beginObject();
        jw.name("line").value(rawLine);
        jw.name("macro").value(macro);

        if (macro && line.contains("$(")) {
            dumpMacroLine(dispatcher, src, line, jw);
            jw.endObject();
            return;
        }

        ParseResults<ServerCommandSource> pr;
        try {
            pr = dispatcher.parse(line, src);
        } catch (Throwable t) {
            if (recover(dispatcher, src, line, jw)) { jw.endObject(); return; }
            jw.name("ok").value(false);
            jw.name("error").value(String.valueOf(t.getMessage()));
            jw.endObject();
            return;
        }

        if (!pr.getExceptions().isEmpty()) {
            if (recover(dispatcher, src, line, jw)) { jw.endObject(); return; }
            jw.name("ok").value(false);
            jw.name("error").value(firstException(pr));
            jw.name("nodes");
            writeNodeNames(jw, pr);
            jw.endObject();
            return;
        }

        jw.name("ok").value(true);
        List<ParsedCommandNode<ServerCommandSource>> nodes = pr.getContext().getNodes();
        jw.name("command");
        if (nodes.isEmpty()) jw.nullValue(); else jw.value(nodes.get(0).getNode().getName());
        jw.name("chain");
        writeChain(jw, pr.getContext(), line);
        jw.endObject();
    }

    private static final String MV = "MACROVAR_";

    private static void dumpMacroLine(CommandDispatcher<ServerCommandSource> dispatcher,
                                      ServerCommandSource src, String line,
                                      JsonWriter jw) throws IOException {
        List<String> varNames = new ArrayList<>();
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("\\$\\(([A-Za-z0-9_]+)\\)").matcher(line);
        while (m.find()) varNames.add(m.group(1));

        String[] strategies = buildStrategies(line, varNames);
        ParseResults<ServerCommandSource> best = null;
        String bestSub = null;
        String firstErr = null;
        for (String sub : strategies) {
            try {
                ParseResults<ServerCommandSource> pr = dispatcher.parse(sub, src);
                if (pr.getExceptions().isEmpty()
                        && pr.getReader().getRemainingLength() == 0) {
                    best = pr; bestSub = sub; break;
                }
                if (firstErr == null) firstErr = firstException(pr);
            } catch (Throwable t) {
                if (firstErr == null) firstErr = String.valueOf(t.getMessage());
            }
        }

        if (best == null) {
            List<String> distinct = new ArrayList<>(new java.util.LinkedHashSet<>(varNames));
            String sub = tryBacktrack(dispatcher, src, line, distinct);
            if (sub != null) {
                try {
                    ParseResults<ServerCommandSource> pr = dispatcher.parse(sub, src);
                    if (pr.getExceptions().isEmpty()
                            && pr.getReader().getRemainingLength() == 0) {
                        best = pr; bestSub = sub;
                    }
                } catch (Throwable ignored) {}
            }
        }

        jw.name("macroVars");
        jw.beginArray();
        for (String v : varNames) jw.value(v);
        jw.endArray();

        if (best == null) {
            jw.name("ok").value(false);
            jw.name("error").value("macro reparse failed: " + firstErr);
            jw.name("reparsed").value(false);
            return;
        }
        jw.name("ok").value(true);
        jw.name("reparsed").value(true);
        jw.name("reparsedLine").value(bestSub);
        List<ParsedCommandNode<ServerCommandSource>> nodes = best.getContext().getNodes();
        jw.name("command");
        if (nodes.isEmpty()) jw.nullValue(); else jw.value(nodes.get(0).getNode().getName());
        jw.name("chain");
        writeChain(jw, best.getContext(), bestSub);
    }

    private static String[] buildStrategies(String line, List<String> varNames) {
        return new String[] {
                replaceVarsByIndex(line, varNames),     // 식별자 MACROVAR_<i>
                replaceVarsAll(line, "0"),              // 정수
                replaceVarsAll(line, "0.0001"),         // 실수
                replaceVarsAll(line, "\"MACROVAR\""),   // 문자열
                replaceVarsAll(line, "minecraft:stone"),// 블록/아이템 id 자리(setblock/fill/give 등)
        };
    }

    private static String tryBacktrack(
            CommandDispatcher<ServerCommandSource> dispatcher, ServerCommandSource src,
            String line, List<String> distinctVars) {
        String[] cands = {"0", "0.0001", "\"MACROVAR\"", "MACROVARID", "minecraft:stone"};
        int n = distinctVars.size();
        if (n == 0 || n > 4) return null;
        int total = 1;
        for (int i = 0; i < n; i++) total *= cands.length;
        for (int combo = 0; combo < total; combo++) {
            int c = combo;
            String sub = line;
            for (int i = 0; i < n; i++) {
                int ci = c % cands.length; c /= cands.length;
                sub = sub.replace("$(" + distinctVars.get(i) + ")", cands[ci]);
            }
            try {
                ParseResults<ServerCommandSource> pr = dispatcher.parse(sub, src);
                if (pr.getExceptions().isEmpty() && pr.getReader().getRemainingLength() == 0)
                    return sub;
            } catch (Throwable ignored) {}
        }
        return null;
    }

    private static String replaceVarsByIndex(String line, List<String> varNames) {
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("\\$\\(([A-Za-z0-9_]+)\\)").matcher(line);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (m.find()) {
            m.appendReplacement(sb, java.util.regex.Matcher.quoteReplacement(MV + (i++)));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String replaceVarsAll(String line, String dummy) {
        return line.replaceAll("\\$\\([A-Za-z0-9_]+\\)",
                java.util.regex.Matcher.quoteReplacement(dummy));
    }

    private static void writeChain(JsonWriter jw,
                                   CommandContextBuilder<ServerCommandSource> ctx,
                                   String fullLine) throws IOException {
        jw.beginArray();
        while (ctx != null) {
            for (ParsedCommandNode<ServerCommandSource> pcn : ctx.getNodes()) {
                CommandNode<ServerCommandSource> node = pcn.getNode();
                jw.beginObject();
                jw.name("node").value(node.getName());
                jw.name("type").value(node.getClass().getSimpleName());
                jw.name("range").value(
                        safeSub(fullLine, pcn.getRange().getStart(), pcn.getRange().getEnd()));
                jw.endObject();
            }
            for (var e : ctx.getArguments().entrySet()) {
                String raw = e.getValue().getRange().get(fullLine);
                Object result = e.getValue().getResult();
                jw.beginObject();
                jw.name("arg").value(e.getKey());
                jw.name("value");
                writeArg(jw, result, raw);
                jw.endObject();
            }
            ctx = ctx.getChild();
        }
        jw.endArray();
    }

    private static void writeArg(JsonWriter jw, Object result, String raw) throws IOException {
        jw.beginObject();
        if (result instanceof EntitySelector) {
            jw.name("kind").value("EntitySelector");
        } else {
            jw.name("kind").value(result == null ? "null" : result.getClass().getSimpleName());
        }
        jw.name("raw").value(raw);
        jw.endObject();
    }

    private static void writeNodeNames(JsonWriter jw,
                                       ParseResults<ServerCommandSource> pr) throws IOException {
        jw.beginArray();
        for (ParsedCommandNode<ServerCommandSource> n : pr.getContext().getNodes()) {
            jw.value(n.getNode().getName());
        }
        jw.endArray();
    }

    private static String safeSub(String s, int a, int b) {
        a = Math.max(0, Math.min(a, s.length()));
        b = Math.max(a, Math.min(b, s.length()));
        return s.substring(a, b);
    }

    // ────────────────────────────────────────────────────────────────────
    //  회복(recovery): 모드 태그/predicate 미등록으로만 실패한 줄을, 유효 placeholder 로
    //  치환해 재파싱한 뒤 chain 의 해당 토큰을 원래 식별자로 복원한다. 파싱 서버에 모드를
    //  올리거나 stub 데이터팩을 넣지 않아도, '모르는 모드 태그/predicate'를 일반 태그처럼
    //  통과시킨다. 런타임엔 생성 코드의 blockInTag/entityInTypeTag/testPredicate 가 실제
    //  레지스트리로 평가하므로 placeholder 는 파싱 통과용일 뿐 결과에 영향 없다.
    //  실패하면 false → 기존 ok:false 경로로 무해하게 폴백(스레딩/리로드 없음).
    // ────────────────────────────────────────────────────────────────────
    private static final String[] BLOCK_TAG_POOL = {
            "#minecraft:dirt", "#minecraft:logs", "#minecraft:planks", "#minecraft:wool",
            "#minecraft:sand", "#minecraft:leaves", "#minecraft:flowers", "#minecraft:ice",
            "#minecraft:rails", "#minecraft:slabs", "#minecraft:doors", "#minecraft:walls",
            "#minecraft:stairs", "#minecraft:fences", "#minecraft:crops", "#minecraft:beds"
    };
    private static final String[] ENTITY_TAG_POOL = {
            "#minecraft:skeletons", "#minecraft:raiders", "#minecraft:arrows",
            "#minecraft:zombies", "#minecraft:undead", "#minecraft:illager"
    };
    // 유효한 바닐라 '아이템' 태그 풀(파싱 통과용 placeholder). 런타임 stackMatches 가
    // stack.isIn(TagKey) 로 실제 태그를 평가하므로 placeholder 는 파싱에만 쓰인다.
    private static final String[] ITEM_TAG_POOL = {
            "#minecraft:anvil", "#minecraft:arrows", "#minecraft:axes", "#minecraft:banners",
            "#minecraft:beds", "#minecraft:boats", "#minecraft:buttons", "#minecraft:candles",
            "#minecraft:chest_boats", "#minecraft:coals", "#minecraft:compasses", "#minecraft:bundles",
            "#minecraft:beacon_payment_items", "#minecraft:bookshelf_books", "#minecraft:bamboo_blocks",
            "#minecraft:brewing_fuel"
    };
    // 유효한 LootCondition inline 풀. predicate 인자(ResourceOrId)는 inline JSON 을 허용한다.
    // 한 줄에 미등록 predicate 가 여러 개면(예: if predicate A if predicate B) 서로 다른
    // placeholder 가 필요하므로 chance 값을 달리한 distinct inline 들을 둔다.
    private static final String[] PREDICATE_INLINE_POOL = {
            "{\"condition\":\"minecraft:random_chance\",\"chance\":1.0}",
            "{\"condition\":\"minecraft:random_chance\",\"chance\":0.9}",
            "{\"condition\":\"minecraft:random_chance\",\"chance\":0.8}",
            "{\"condition\":\"minecraft:random_chance\",\"chance\":0.7}",
            "{\"condition\":\"minecraft:random_chance\",\"chance\":0.6}",
            "{\"condition\":\"minecraft:random_chance\",\"chance\":0.5}",
            "{\"condition\":\"minecraft:random_chance\",\"chance\":0.4}",
            "{\"condition\":\"minecraft:random_chance\",\"chance\":0.3}"
    };

    /** 회복 시도. 성공 시 jw 에 ok/command/chain 을 쓰고 true(호출자가 endObject). 실패 시 false. */
    private static boolean recover(CommandDispatcher<ServerCommandSource> dispatcher,
                                   ServerCommandSource src, String line, JsonWriter jw) throws IOException {
        java.util.LinkedHashMap<String, String> restore = new java.util.LinkedHashMap<>(); // placeholder -> 원본
        String work = line;
        for (int guard = 0; guard < 12; guard++) {
            ParseResults<ServerCommandSource> pr;
            String err;
            try {
                pr = dispatcher.parse(work, src);
                err = pr.getExceptions().isEmpty() ? null : firstException(pr);
                if (err == null && pr.getReader().getRemainingLength() == 0) {
                    if (restore.isEmpty()) return false;   // 회복할 게 없었음 → 정상 경로에 맡김
                    java.util.List<ParsedCommandNode<ServerCommandSource>> nodes = pr.getContext().getNodes();
                    jw.name("ok").value(true);
                    jw.name("recovered").value(true);
                    jw.name("command");
                    if (nodes.isEmpty()) jw.nullValue(); else jw.value(nodes.get(0).getNode().getName());
                    jw.name("chain");
                    writeChainRestored(jw, pr.getContext(), work, restore);
                    return true;
                }
            } catch (Throwable t) {
                err = String.valueOf(t.getMessage());
            }
            String[] plan = planSub(err, work, restore.keySet());
            if (plan == null) return false;
            int at = work.indexOf(plan[0]);
            if (at < 0) return false;
            restore.put(plan[1], plan[0]);
            work = work.substring(0, at) + plan[1] + work.substring(at + plan[0].length());
        }
        return false;
    }

    /** 에러 메시지로부터 (원본토큰, placeholder) 치환 계획. 회복 불가면 null. */
    private static String[] planSub(String err, String work, java.util.Set<String> used) {
        if (err == null) return null;
        java.util.regex.Matcher m;
        m = java.util.regex.Pattern.compile("Unknown block tag '([^']+)'").matcher(err);
        if (m.find()) {
            String ph = pickPool(BLOCK_TAG_POOL, work, used);
            return ph == null ? null : new String[]{"#" + m.group(1), ph};
        }
        m = java.util.regex.Pattern.compile("Unknown entity type tag '([^']+)'").matcher(err);
        if (m.find()) {
            String ph = pickPool(ENTITY_TAG_POOL, work, used);
            return ph == null ? null : new String[]{"#" + m.group(1), ph};
        }
        m = java.util.regex.Pattern.compile("Unknown item tag '([^']+)'").matcher(err);
        if (m.find()) {
            String ph = pickPool(ITEM_TAG_POOL, work, used);
            return ph == null ? null : new String[]{"#" + m.group(1), ph};
        }
        // predicate 미등록: ResourceKey[minecraft:predicate / ns:path]
        m = java.util.regex.Pattern.compile("ResourceKey\\[minecraft:predicate / ([^\\]]+)\\]").matcher(err);
        if (m.find()) {
            String token = m.group(1).trim();
            if (work.indexOf(token) < 0) return null;
            String ph = pickPool(PREDICATE_INLINE_POOL, work, used);   // 다중 predicate → 서로 다른 inline
            return ph == null ? null : new String[]{token, ph};
        }
        return null;
    }

    /** work 에 없고 아직 안 쓴 placeholder 선택(원본 토큰과 충돌하지 않도록). */
    private static String pickPool(String[] pool, String work, java.util.Set<String> used) {
        for (String c : pool) if (!used.contains(c) && work.indexOf(c) < 0) return c;
        return null;
    }

    /** writeChain + restore: 추출 문자열에서 placeholder 를 원본으로 되돌린다.
     *  placeholder 는 원본 줄에 없던 토큰이라(=collision-free) 단순 치환이 안전하다. */
    private static void writeChainRestored(JsonWriter jw,
                                           CommandContextBuilder<ServerCommandSource> ctx,
                                           String fullLine,
                                           java.util.Map<String, String> restore) throws IOException {
        jw.beginArray();
        while (ctx != null) {
            for (ParsedCommandNode<ServerCommandSource> pcn : ctx.getNodes()) {
                CommandNode<ServerCommandSource> node = pcn.getNode();
                jw.beginObject();
                jw.name("node").value(node.getName());
                jw.name("type").value(node.getClass().getSimpleName());
                jw.name("range").value(restoreStr(
                        safeSub(fullLine, pcn.getRange().getStart(), pcn.getRange().getEnd()), restore));
                jw.endObject();
            }
            for (var e : ctx.getArguments().entrySet()) {
                String raw = restoreStr(e.getValue().getRange().get(fullLine), restore);
                jw.beginObject();
                jw.name("arg").value(e.getKey());
                jw.name("value");
                writeArg(jw, e.getValue().getResult(), raw);
                jw.endObject();
            }
            ctx = ctx.getChild();
        }
        jw.endArray();
    }

    private static String restoreStr(String s, java.util.Map<String, String> restore) {
        if (s == null || restore.isEmpty()) return s;
        for (var e : restore.entrySet())
            if (s.contains(e.getKey())) s = s.replace(e.getKey(), e.getValue());
        return s;
    }

    private static String firstException(ParseResults<ServerCommandSource> pr) {
        return pr.getExceptions().values().stream().findFirst()
                .map(Throwable::getMessage).orElse("parse incomplete");
    }
}