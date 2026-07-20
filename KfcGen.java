package kartriderpack.generated;

import net.minecraft.scoreboard.ReadableScoreboardScore;
import net.minecraft.scoreboard.ScoreHolder;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ServerScoreboard;

/**
 * 자동 생성 코드(emit)가 사용하는 네이티브 스코어보드 헬퍼.
 *
 * <p>기존 손포팅용 kfcutil 과 분리한다(kfcutil 은 getScore/setScore 만 제공).
 * 여기서는 add/operation/matches 처럼 emit 이 필요로 하는 홀더-이름 기반 연산을 제공한다.
 * 모두 홀더 NAME(String) 으로 동작 — '@s' 는 executor.getNameForScoreboard(), '#foo' 는 리터럴.
 */
public final class KfcGen {

    static {
        // 명령 구문 예외(CommandSyntaxException)의 스택 캡처 비활성 — brigadier 공식 플래그.
        // 오류 '메시지'는 그대로 전달되고, 스택은 표준 명령 흐름에서 출력되지 않는다.
        // [실측] 동적(매크로 산출) 텍스트 리터럴이 packrat 1차 파서에 거부될 때 매 호출
        // fillInStackTrace 가 틱의 ~1.8%p 를 차지했다(파싱 폴백 자체는 정상 경로).
        com.mojang.brigadier.exceptions.CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES = false;
    }

    // ── 핫패스 캐시 ──────────────────────────────────────────────
    // [스레드 노트] 커맨드 실행은 서버 메인 스레드 전용이라 이 캐시들은 단일 스레드에서만
    // 접근된다 → ConcurrentHashMap 의 CAS/volatile 비용 없이 일반 HashMap 을 쓴다.
    // (비동기 접근 경로가 생기면 되돌릴 것 — 캐시 미스 재계산은 멱등이라 최악도 중복 계산뿐이나,
    //  HashMap 은 동시 변형 시 구조 손상 가능.)
    // 변환 출력은 상수 문자열(홀더명/사운드id/태그id/SNBT/텍스트 컴포넌트)을 매 틱 반복
    // 호출하므로, 문자열 → 파싱결과를 캐시해 매 호출 파싱/레지스트리 조회/객체 생성을 없앤다.
    /** 상한 있는 삽입순(FIFO) 맵 — 매크로 산출 '동적 문자열 키' 유입 시 무한 성장/해시 열화 방지.
     *  상수 키 워킹셋(수백~수천)보다 훨씬 큰 상한이라 정상 팩에서는 축출이 사실상 없다. */
    private static <K, V> java.util.LinkedHashMap<K, V> boundedMap(final int cap) {
        return new java.util.LinkedHashMap<K, V>(256, 0.75f, false) {
            @Override protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
                return size() > cap;
            }
        };
    }

    private static final java.util.Map<String, net.minecraft.scoreboard.ScoreHolder>
            HOLDER_CACHE = boundedMap(16384);
    private static final java.util.HashMap<String, net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent>>
            SOUND_CACHE = new java.util.HashMap<>();
    private static final java.util.HashMap<String, net.minecraft.sound.SoundCategory>
            SOUND_CAT_CACHE = new java.util.HashMap<>();
    private static final java.util.HashMap<String, net.minecraft.registry.tag.TagKey<net.minecraft.block.Block>>
            BLOCK_TAG_CACHE = new java.util.HashMap<>();
    private static final java.util.Map<String, net.minecraft.nbt.NbtElement>
            SNBT_CACHE = boundedMap(8192);
    // NbtPath 는 파싱 후 완전 불변(string/nodeEndIndices/nodes 전부 final, 적용 시 root 만 다룸)이라
    // 재사용 안전. data 경로는 전부 상수 리터럴(storagePutSnbt 895 등 매 틱 호출)이므로 문자열→
    // NbtPath 캐시로 NbtPathArgumentType 재파싱을 제거한다.
    private static final java.util.Map<String, net.minecraft.command.argument.NbtPathArgumentType.NbtPath>
            NBTPATH_CACHE = boundedMap(8192);
    // display transformation 행렬형 리터럴의 파싱+TRS 분해(matrix→translation/rotation/scale)는
    // setTransformation 이 매 호출 수행하는 핫 비용이다. AffineTransformation 은 불변이고 분해를
    // 인스턴스 내부에 1회 memoize(initialized 플래그)하므로, NbtElement 값 기준으로 캐시해 동일
    // 행렬의 재파싱·재분해를 제거한다(distinct 행렬당 1회). 캐시 인스턴스 공유는 불변이라 안전.
    private static final java.util.Map<net.minecraft.nbt.NbtElement, net.minecraft.util.math.AffineTransformation>
            TRANSFORM_CACHE = boundedMap(8192);
    // [identity 1차 캐시] 이 팩의 transformation 은 전부 상수 리터럴(동일 NbtElement 객체 재사용)이라
    // 값 기준 deep hashCode/equals 없이 객체 identity 로 즉시 히트시킨다. 미스 시 값 기준 TRANSFORM_CACHE
    // 로 폴백(서로 다른 객체·같은 값 dedup 유지 → 범용성 보존). 둘 다 불변 매핑이라 무효화 불필요.
    private static final java.util.IdentityHashMap<net.minecraft.nbt.NbtElement, net.minecraft.util.math.AffineTransformation>
            TRANSFORM_ID_CACHE = new java.util.IdentityHashMap<>();
    /** 파싱된 텍스트 + '정적(재해석 불필요)' 플래그. score/selector/nbt 컴포넌트가 없는 트리는
     *  Texts.parse 가 항등(불변 Text 복제)이므로 파싱된 인스턴스를 그대로 재사용해도 관측 동일. */
    private static final class CachedText {
        final net.minecraft.text.Text text; final boolean isStatic;
        // nbt+interpret(storage) 단일 컴포넌트 특수화 상태: 0=미분류 1=적용(자기검증 통과) 2=바닐라
        byte itpState = 0;
        net.minecraft.text.NbtTextContent itpNc;
        net.minecraft.text.Text itpSepStatic;     // 정적 separator 의 1회 해석본(없으면 기본)
        CachedText(net.minecraft.text.Text t, boolean s) { text = t; isStatic = s; }
    }

    // interpret 요소(문자열) → 디코드된 Text 캐시. 게이지처럼 같은 조각 문자열이 플레이어/틱에
    // 걸쳐 반복 등장하므로 문자열당 1회 디코드로 수렴한다. 실패도 캐시(반복 예외 생성 차단).
    private static final net.minecraft.text.Text INTERP_INVALID =
            net.minecraft.text.Text.literal("\u0000kfc-interp-invalid");
    private static final java.util.Map<String, net.minecraft.text.Text>
            INTERP_CACHE = boundedMap(8192);
    // 비문자열 interpret 요소용 캐시. 9차(프로파일 실측): kartactbar 게이지 조각은
    // `append value [{...},{...}]` 산출물이라 요소가 전부 NbtList/NbtCompound — 8차의 문자열
    // 캐시는 적중 0이었고 Decoder.parse 5.57%p(내부 selector 파싱 1.97%p + UUID IAE
    // fillInStackTrace 1.64%p 포함)가 그대로 남았다. NbtElement equals/hashCode 는 값 기반
    // (트리 재귀)이라 조회 비용은 코덱 디코드 대비 미미하다.
    // 키는 삽입 시 el.copy()(깊은 복사, 불변 스냅샷) — storage 원본 요소가 이후 data 명령으로
    // 제자리 변형되어도 캐시 키는 불변이므로 '변형된 키가 다른 조회와 오일치'하는 오염이 원천
    // 차단된다(잘못된 값 반환 불가; 최악은 미스 후 재디코드 = 멱등).
    private static final java.util.Map<net.minecraft.nbt.NbtElement, net.minecraft.text.Text>
            INTERP_CACHE_NBT = boundedMap(8192);
    private static final java.util.Map<String, CachedText>
            TEXT_CACHE = boundedMap(8192);

    /** 텍스트 트리에 런타임 해석(score/selector/nbt) 컴포넌트가 없으면 true.
     *  translatable 인자·siblings 까지 재귀 검사. 모르는 콘텐츠 타입은 false(fail-closed). */
    private static boolean isStaticText(net.minecraft.text.Text t) {
        net.minecraft.text.TextContent c = t.getContent();
        if (c instanceof net.minecraft.text.ScoreTextContent
                || c instanceof net.minecraft.text.SelectorTextContent
                || c instanceof net.minecraft.text.NbtTextContent) return false;
        if (c instanceof net.minecraft.text.TranslatableTextContent tr) {
            for (Object a : tr.getArgs()) {
                if (a instanceof net.minecraft.text.Text at && !isStaticText(at)) return false;
            }
        } else if (!(c instanceof net.minecraft.text.PlainTextContent
                || c instanceof net.minecraft.text.KeybindTextContent)) {
            return false;   // 미지의 콘텐츠 타입 — 보수적으로 동적 취급
        }
        for (net.minecraft.text.Text s : t.getSiblings()) if (!isStaticText(s)) return false;
        return true;
    }

    /** nbt+interpret(storage) 단일 컴포넌트의 resolve 특수화.
     *  바닐라 NbtTextContent.parse 파이프라인(바이트코드 확인)을 요소-캐시로 재현:
     *    dataSource.get(source) → NbtPath.get(root, 실패=빈 스트림) → 요소별
     *    TextCodecs.CODEC.parse(registryOps).getOrThrow()(실패=요소 스킵) → Texts.parse →
     *    separator 로 reduce-join(첫 요소에 append), 빈 결과 = Text.empty().
     *  차이(성능): 요소의 디코드 결과를 캐시 — 문자열 요소는 문자열 키(INTERP_CACHE),
     *  비문자열 요소는 el.copy() 스냅샷 키(INTERP_CACHE_NBT, 9차). 실패도 캐시(매 호출
     *  getOrThrow 예외 생성이 실측 ~2%p). 정적 요소는 Texts.parse 생략. 첫 사용 시 바닐라 경로와 equals 비교로
     *  자기검증 — 불일치면 그 텍스트는 영구 바닐라 경로(fail-closed).
     *  로그 편차(문서화): 디코드 실패 warn 이 요소 문자열당 1회로 줄어든다(스팸 억제). */
    private static net.minecraft.text.Text resolveInterpretStorage(
            net.minecraft.server.command.ServerCommandSource source,
            net.minecraft.entity.Entity sender, CachedText ct) {
        try {
            if (ct.itpState == 0) {   // 1회 분류
                ct.itpState = 2;
                net.minecraft.text.Text t = ct.text;
                if (!t.getSiblings().isEmpty() || !t.getStyle().isEmpty()) return null;
                if (!(t.getContent() instanceof net.minecraft.text.NbtTextContent nc)) return null;
                if (!nc.shouldInterpret()) return null;
                if (!(nc.getDataSource() instanceof net.minecraft.text.StorageNbtDataSource)) return null;
                java.util.Optional<net.minecraft.text.Text> sep = nc.getSeparator();
                if (sep.isPresent() && !isStaticText(sep.get())) return null;   // 동적 separator — 회피
                ct.itpNc = nc;
                ct.itpSepStatic = sep.isPresent() ? sep.get()
                        : net.minecraft.text.Texts.DEFAULT_SEPARATOR_TEXT;
                ct.itpState = 1;
                // 자기검증: fast 결과 == 바닐라 결과(같은 틱·같은 storage 상태 — 결정적)
                net.minecraft.text.Text fast = _itpResolve(source, sender, ct);
                net.minecraft.text.Text truth = net.minecraft.text.Texts.parse(source, ct.text, sender, 0);
                if (fast == null || !fast.equals(truth)) {
                    ct.itpState = 2;
                    return truth;   // 이번 호출 결과는 바닐라 값으로
                }
                return fast;
            }
            return _itpResolve(source, sender, ct);
        } catch (Exception ex) {
            ct.itpState = 2;   // 어떤 예외든 영구 바닐라 폴백
            return null;
        }
    }

    private static net.minecraft.text.Text _itpResolve(
            net.minecraft.server.command.ServerCommandSource source,
            net.minecraft.entity.Entity sender, CachedText ct) throws Exception {
        net.minecraft.text.NbtTextContent nc = ct.itpNc;
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(nc.getPath());
        if (p == null) return null;
        net.minecraft.text.MutableText out = null;
        java.util.Iterator<net.minecraft.nbt.NbtCompound> roots =
                nc.getDataSource().get(source).iterator();
        while (roots.hasNext()) {
            net.minecraft.nbt.NbtCompound root = roots.next();
            java.util.List<net.minecraft.nbt.NbtElement> els;
            try { els = p.get(root); }
            catch (Exception missing) { continue; }   // 바닐라: 경로 미매치 = 빈 스트림
            for (net.minecraft.nbt.NbtElement el : els) {
                net.minecraft.text.Text base;
                if (el instanceof net.minecraft.nbt.NbtString nstr) {
                    String key = nstr.asString().orElse(null);
                    if (key == null) continue;
                    base = INTERP_CACHE.get(key);
                    if (base == null) {
                        base = _itpDecode(source, el);
                        INTERP_CACHE.put(key, base == null ? INTERP_INVALID : base);
                    }
                    if (base == INTERP_INVALID || base == null) continue;
                } else {
                    // 비문자열 요소(NbtList/NbtCompound) — 실측상 게이지 조각의 전부.
                    base = INTERP_CACHE_NBT.get(el);
                    if (base == null) {
                        base = _itpDecode(source, el);
                        INTERP_CACHE_NBT.put(el.copy(),
                                base == null ? INTERP_INVALID : base);
                    }
                    if (base == INTERP_INVALID || base == null) continue;
                }
                net.minecraft.text.Text resolved =
                        isStaticText(base) ? base
                                : net.minecraft.text.Texts.parse(source, base, sender, 0);
                if (out == null) {
                    out = resolved.copy();   // 바닐라 reduce 의 첫 요소(변형 대상) — 캐시 보호 copy
                } else {
                    out.append(ct.itpSepStatic).append(resolved);
                }
            }
        }
        return out != null ? out : net.minecraft.text.Text.empty();
    }

    /** 바닐라 interpret 요소 디코드와 동일: TextCodecs.CODEC.parse(registryOps).getOrThrow().
     *  실패 = null(호출측이 요소 스킵 — 바닐라의 warn+스킵과 동작 동일, warn 은 최초 1회). */
    private static net.minecraft.text.Text _itpDecode(
            net.minecraft.server.command.ServerCommandSource source, net.minecraft.nbt.NbtElement el) {
        try {
            return net.minecraft.text.TextCodecs.CODEC
                    .parse(source.getRegistryManager().getOps(net.minecraft.nbt.NbtOps.INSTANCE), el)
                    .getOrThrow();
        } catch (Exception ex) {
            System.err.println("[KFC-TEXT] interpret decode failed (cached): " + el);
            return null;
        }
    }

    private static final net.minecraft.nbt.NbtElement SNBT_INVALID = new net.minecraft.nbt.NbtCompound();

    /** 셀렉터 음수태그/빈 태그 인자용 공유 빈 배열 — 호출마다 new String[0] 할당을 없앤다.
     *  내용이 비어 있고 읽기 전용으로만 쓰이므로 공유해도 안전하다. */
    public static final String[] NO_TAGS = new String[0];

    // ── 스코어보드 이름 캐시 ──
    // 비-플레이어 Entity.getNameForScoreboard() 는 호출마다 UUID 문자열을 새로 만든다(핫패스
    // 할당 폭증 — 핫코어 실측 3,574 호출부). 이름은 엔티티 수명 동안 불변(UUID 고정, 플레이어명
    // 세션 고정) → identity 기반 캐시. WeakHashMap(Entity 는 기본 identity equals) 이라 엔티티
    // 소멸 시 엔트리도 자연 회수된다(틱 청소 불필요, 누수 없음). 서버 스레드 전용.
    private static final java.util.WeakHashMap<net.minecraft.entity.Entity, String> NAME_CACHE =
            new java.util.WeakHashMap<>();
    public static String nameOf(net.minecraft.entity.Entity e) {
        if (e == null) return null;
        String n = NAME_CACHE.get(e);
        if (n == null) { n = e.getNameForScoreboard(); NAME_CACHE.put(e, n); }
        return n;
    }

    private static net.minecraft.scoreboard.ScoreHolder holderOf(String name) {
        return HOLDER_CACHE.computeIfAbsent(name, net.minecraft.scoreboard.ScoreHolder::fromName);
    }

    // Identifier.of 는 호출당 문자열 검증(namespace:path)+할당. Identifier 는 불변이고
    // reload 과 무관(레지스트리 '키'일 뿐 내용 아님) → 캐시 안전. null 입력은 그대로 null 반환.
    private static final java.util.HashMap<String, net.minecraft.util.Identifier>
            ID_CACHE = new java.util.HashMap<>();

    private static net.minecraft.util.Identifier idOf(String s) {
        if (s == null) return null;
        return ID_CACHE.computeIfAbsent(s, net.minecraft.util.Identifier::of);
    }

    private KfcGen() { throw new UnsupportedOperationException(); }

    // ── [tree_flatten] 구간 테이블 디코더 ──
    // 정적 int[] 초기화는 원소당 <clinit> 바이트코드를 만들어 수천 원소면 64KB 한계를
    // 위협한다. 대신 상수풀 문자열(5자/int, base-90, '"'/'\\' 회피 프린터블)로 실어
    // 클래스 로드 시 1회 디코드한다. 인코더는 tree_flatten._encode_ints (역함수 쌍).
    public static int[] decodeInts(String s) {
        int n = s.length() / 5;
        int[] out = new int[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            long v = 0;
            for (int k = 0; k < 5; k++) {
                int c = s.charAt(p++) - 35;
                if (c > 57) c--;             // 92('\\') 자리 건너뜀 보정
                v = v * 90 + c;
            }
            out[i] = (int) (v + Integer.MIN_VALUE);
        }
        return out;
    }

    // ──────────────── objective 핸들 캐시 (ObjRef) ────────────────
    // 모든 점수 헬퍼는 호출마다 sb.getNullableObjective(이름)(문자열 해시+맵 조회)을 탔다
    // (kartall 실측 점수 호출부 18만+). objective 는 사실상 load 때 만들고 이후 불변이므로,
    // 상수 objective 이름을 클래스 static final ObjRef 로 승격(merge_pass pass-4)하고
    // 세대(OBJ_GEN) 검사 한 번으로 재사용한다.
    // 무효화(OBJ_GEN++) 지점 — objective 집합이 바뀔 수 있는 모든 경로:
    //   · ensureObjective 실제 추가 / removeObjective 실제 제거 (우리 코드의 유일한 변경점)
    //   · 브릿지/디스패처(instantExecute*): 바닐라가 objectives add/remove 가능
    //   · 서버 틱 변경(getOrCreateContext에서 감지): 콘솔/플레이어 명령·바닐라 함수 등
    //     우리 코드 밖에서 벌어진 변경은 다음 우리-코드 진입 시점(틱 단위)에 재해소.
    //     (우리 코드 실행 중에는 외부 명령이 인터리브되지 않는다 — 단일 스레드 tick.)
    static long OBJ_GEN = 0;
    private static int OBJ_TICK = Integer.MIN_VALUE;
    public static final class ObjRef {
        final String name;
        net.minecraft.scoreboard.ScoreboardObjective obj;
        long gen = -1;
        ObjRef(String name) { this.name = name; }
    }
    /** merge_pass 승격 필드 초기화용. */
    public static ObjRef objRef(String name) { return new ObjRef(name); }
    private static ScoreboardObjective obj(ServerScoreboard sb, ObjRef r) {
        if (r.gen != OBJ_GEN) {
            r.obj = sb.getNullableObjective(r.name);
            r.gen = OBJ_GEN;
        }
        return r.obj;
    }

    // ──────────────── 실행 컨텍스트 (구 kfcutil 통합) ────────────────
    // 생성 코드가 ctx.world / ctx.scoreboard / ctx.allPlayers / ctx.server 를 쓴다.
    public static final class GameContext {
        public final net.minecraft.server.MinecraftServer server;
        public final net.minecraft.server.world.ServerWorld world;
        public final ServerScoreboard scoreboard;
        public final java.util.List<net.minecraft.server.network.ServerPlayerEntity> allPlayers;
        private GameContext(net.minecraft.server.MinecraftServer s) {
            this.server = s;
            this.world = s.getOverworld();
            this.scoreboard = s.getScoreboard();
            this.allPlayers = s.getPlayerManager().getPlayerList();
        }
    }

    // 서버당 1개 캐시 — 필드 전부 라이브 참조(world/scoreboard 불변, allPlayers 는 PlayerManager
    // 의 라이브 리스트)라 매 호출 재생성과 시맨틱 동일. 벽충돌 핫패스에서 executeReturn 마다
    // 컨텍스트를 새로 만들던 비용(getOverworld/getScoreboard/getPlayerList 게터 체인) 제거.
    private static GameContext CTX_CACHE;
    public static GameContext getOrCreateContext(net.minecraft.server.command.ServerCommandSource src) {
        // 25차: 외부 명령(커맨드블럭·채팅·/function·타 데이터팩) 실행이 감지되면, 다음 네이티브
        // 접근인 이 지점에서 캐시를 화해한다. KfcFuncCoherenceMixin 이 CommandManager.executeWithPrefix
        // 에서 EXTERNAL_DIRTY 를 세팅 → 명령 실행 자체엔 부하 0, 화해는 여기로 지연(coalesce).
        // 외부 명령 실행 중 네이티브는 재진입하지 않으므로, 여기 도달 시점엔 변이가 이미 반영돼 있다.
        if (EXTERNAL_DIRTY) { EXTERNAL_DIRTY = false; onExternalFunctionExecuted(); }
        GameContext c = CTX_CACHE;
        net.minecraft.server.MinecraftServer s = src.getServer();
        if (c == null || c.server != s) { c = new GameContext(s); CTX_CACHE = c; }
        int t = s.getTicks();
        if (t != OBJ_TICK) {
            OBJ_TICK = t; OBJ_GEN++;   // 외부(콘솔/바닐라) objective 변경 재해소(ObjRef 전용 — 저렴)
            // 25차[무결성·성능 양립]: 외부 변이 화해의 '즉시 경로'는 KfcFuncCoherenceMixin 이
            // 담당한다 — 커맨드블럭/타 데이터팩/수동이 CommandFunctionManager.execute 로 외부
            // 함수를 실행한 직후 onExternalFunctionExecuted() 가 캐시를 무효화(≤1틱). 그래서
            // 정상 네이티브 틱 경로는 캐시를 유지해 성능을 회복한다(24차의 매 틱 blanket 무효화 철회).
            // 여기(틱 경계)는 '안전망'만 남긴다: 함수를 거치지 않는 bare 커맨드블럭 명령(/scoreboard
            // /tag /data 직접)·콘솔/OP 개입은 믹스인 범위 밖이므로 100틱(5초) 주기로 수렴시킨다.
            // (믹스인이 미적용된 환경에서도 이 안전망이 최종 정합을 보장 — fail-safe.)
            // coherence 믹스인 발동이 확인돼(1회 활성 로그) 상시 dirty-flag 즉시 화해로 동작한다.
            // 여기는 함수를 거치지 않는 bare 커맨드블럭/콘솔 개입만 100틱(5초) 주기로 수렴시키는 안전망.
            if (HANDLE_RECON_TICK == Integer.MIN_VALUE || t - HANDLE_RECON_TICK >= 100) {
                HANDLE_RECON_TICK = t;
                invalidateScoreHandles();
                ENTITY_NBT_SNAP.clear();   // 무틱 엔티티(marker) NBT 스냅샷 외부 변이 화해
            }
            snapBarrierAll();   // 23차: 틱 경계 — 이전 틱의 미실체화 스냅샷을 콘솔 개입 전에 확정
        }
        return c;
    }

    // ── 틱 단위 엔티티 스냅샷 캐시 ──
    // @e/@a 스캔이 한 틱 안에서 여러 함수에 걸쳐 world.iterateEntities() 를 반복 호출하던 비용 제거.
    // 캐시는 '엔티티 참조 목록'만 보관하고, 태그/점수/위치/nbt 필터는 호출 시점에 그 참조에서
    // 라이브로 읽으므로 고증에 영향 없음(태그/점수 변동은 캐시를 무효화할 필요가 없다).
    // 무효화 조건: (a) 서버 틱이 바뀜(getTicks) → 게임이 스폰/디스폰한 변화 반영,
    //            (b) 우리 명령이 엔티티를 추가/제거(summon/lootSpawn/killEntity) → ENTITY_GEN 증가.
    static long ENTITY_GEN = 0;
    private static net.minecraft.server.MinecraftServer SNAP_SERVER;
    private static int  SNAP_TICK = Integer.MIN_VALUE;
    private static long SNAP_GEN  = -1;
    private static java.util.List<net.minecraft.entity.Entity> SNAP_ENTITIES;
    // 17차: 로드된 개체군의 순서무관 지문(엔티티별 identityHash 혼합값의 합). 스냅샷 재구축
    // 루프에서 공짜로 계산되고, snapAdd/snapRemove 가 ± 증분 유지한다. 훅 밖 유입/제거
    // (청크 로드/언로드, 바닐라 스폰/사망)는 이 지문으로만 드러난다 — tagBucket 드리프트 감지용.
    private static long SNAP_FP = 0;
    private static long _entFp(net.minecraft.entity.Entity e) {
        long h = System.identityHashCode(e);
        h *= 0x9E3779B97F4A7C15L;                 // 혼합(합 충돌 완화) — 순서무관 합산용
        return h ^ (h >>> 32) | 1L;               // 홀수화: 0 기여 방지(개수 변화도 반영)
    }
    public static java.util.List<net.minecraft.entity.Entity> entitiesSnapshot(GameContext ctx) {
        int t = ctx.server.getTicks();
        if (SNAP_ENTITIES == null || SNAP_SERVER != ctx.server || SNAP_TICK != t || SNAP_GEN != ENTITY_GEN) {
            java.util.ArrayList<net.minecraft.entity.Entity> list = new java.util.ArrayList<>();
            long fp = 0;
            for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;
                list.add(e);
                fp += _entFp(e);
            }
            SNAP_ENTITIES = list; SNAP_FP = fp;
            SNAP_SERVER = ctx.server; SNAP_TICK = t; SNAP_GEN = ENTITY_GEN;
        }
        return SNAP_ENTITIES;
    }

    // ── 틱 내 스폰/킬에 의한 스냅샷 무효화(thrashing) 제거 ──
    // TD 류 팩은 한 틱에 총알·이펙트를 대량 스폰/제거한다. 기존엔 그때마다 ENTITY_GEN++ 로
    // 스냅샷을 통째로 무효화 → 같은 틱 안에서 nearest/as @e 가 호출될 때마다 world 전체를
    // 재수집(O(전체엔티티)) 했다. 우리 명령의 스폰/킬은 '어떤 엔티티가' 바뀌었는지 알 수 있으므로
    // 스냅샷을 통째로 버리지 말고 증분 갱신한다. (바닐라/브릿지 스폰은 틱 경계에서 재수집되어
    // 정확성 유지 — 기존 per-tick 스냅샷 시맨틱 그대로.)
    static void snapAdd(net.minecraft.entity.Entity e) {
        long _prevGen = ENTITY_GEN;
        ENTITY_GEN++;                        // gen 은 단조 증가 유지(다른 캐시/판정과 일관)
        // [정합 가드] 직전까지 유효(TB_GEN==prev)했던 태그 버킷만 증분 갱신·재유효화한다.
        // 이미 무효(예: 직전 브릿지의 ENTITY_GEN++)면 '유효' 표시가 브릿지 변화를 놓친 버킷을
        // 부활시키므로 그대로 무효 상태로 둔다(다음 접근 때 재수집).
        if (TB_GEN == _prevGen && e != null) {
            tagBucketsOnAdd(e);
            for (net.minecraft.entity.Entity p : e.getPassengersDeep()) tagBucketsOnAdd(p);
            TB_GEN = ENTITY_GEN;
        }
        // 타입 인덱스도 동일 원칙의 증분 유지 — 종전엔 스폰/킬마다 gen 불일치로 다음 typeBucket
        // 접근 때 전체 재빌드(IdentityHashMap 재구축 + 전 엔티티 재분류)가 일어났다. 스폰이 잦은
        // 틱에서 이 재빌드가 스캔 사이마다 반복(핫코어 실측: 스폰/킬 1,145곳 × 스캔 9,279곳).
        if (TYPEIDX_GEN == _prevGen && e != null) {
            typeIndexAdd(e);
            for (net.minecraft.entity.Entity p : e.getPassengersDeep()) typeIndexAdd(p);
            TYPEIDX_GEN = ENTITY_GEN;
        }
        if (e == null || SNAP_ENTITIES == null) return;
        // [정합 가드] 스냅샷도 동일 원칙 — 직전까지 유효했던 경우에만 증분 갱신·재유효화.
        // (종전엔 무조건 SNAP_GEN=ENTITY_GEN 으로 표시해, '브릿지 무효화 → 접근 없이 → summon'
        //  순서에서 브릿지가 만든 변화가 빠진 스냅샷이 부활할 수 있었다.)
        if (SNAP_GEN != _prevGen) return;
        // 엔티티 + 모든 탑승자(재귀) 를 스냅샷에 반영한다.
        // spawnNewEntityAndPassengers 는 차량과 탑승자를 같은 틱에 함께 월드에 추가하므로,
        // parent 만 넣으면 탑승자(예: player_head item_display = loop-player-head)가 같은 틱
        // 셀렉터 / typeBucket 에서 누락된다.
        // [버그 이력] 과거 'if (e.hasPassengers()) return;' 폴백은 SNAP_GEN 을 갱신하지 않아
        // 다음 entitiesSnapshot 에서 전체 재수집을 의도했으나, 그 직후의 '탑승자 없는' 다른 단일
        // snapAdd 가 SNAP_GEN = ENTITY_GEN 으로 스냅샷을 다시 '유효' 표시 → 모델(block_display +
        // N item_display)이 빠진 스냅샷이 그 틱 내내 굳어 loop-player-head 가 0개로 관측됐다.
        SNAP_ENTITIES.add(e);
        SNAP_FP += _entFp(e);                                       // 17차: 지문 증분 동기
        for (net.minecraft.entity.Entity p : e.getPassengersDeep()) {
            SNAP_ENTITIES.add(p);
            SNAP_FP += _entFp(p);
        }
        SNAP_GEN = ENTITY_GEN;               // 엔티티+탑승자 전부 반영 → 현재 gen 으로 '유효' 표시
    }
    /** 탑승 깊이(= 위에 쌓인 차량 수). 차량 0, 그 위 탑승자 1, 또 위 2 … */
    private static int ridingDepth(net.minecraft.entity.Entity e) {
        int d = 0;
        net.minecraft.entity.Entity v = (e == null ? null : e.getVehicle());
        while (v != null && d < 64) { d++; v = v.getVehicle(); }
        return d;
    }

    /** 셀렉터 순회를 '탑승자 우선(riding-depth 내림차순)' 으로 안정 정렬해 새 리스트로 반환.
     *  [이유] 바닐라 `execute as @e at @s run`은 모든 `at @s` 소스 회전을 본문 실행 전에 한꺼번에
     *  스냅샷하지만, 생성 루프는 매 반복마다 라이브 yaw(e.getYaw())를 읽는다. 그래서 디스플레이
     *  리그(block_display 차량 + item_display 탑승자)를 `tp @s ~ ~ ~ ~rot`로 회전할 때 차량을 먼저
     *  처리하면, ① 차량 tp 가 탑승자 yaw 를 전파로 −90 만들고 ② 탑승자 반복이 라이브 −90 을 읽어
     *  또 −90 → 이중 회전(−180)이 된다.
     *  탑승자를 먼저 처리하면, 각 엔티티가 형제 tp 로 변경되기 전의 원래 yaw 를 읽고, 탑승자 자기 tp
     *  (full Entity.teleport)가 stopRiding 으로 하차해 이후 차량 전파 대상에서 빠지므로 각 엔티티가
     *  정확히 1회만 회전한다 — 바닐라가 이 패턴에서 내는 결과(−90, 탑승자 하차)와 동일하다.
     *  (탑승 스택이 없으면 정렬을 건너뛰어 기존 얕은 복사와 동일 비용; 무관한 엔티티는 안정 정렬로
     *   원래 상대 순서 유지 → 다른 명령 영향 최소.) */
    public static java.util.List<net.minecraft.entity.Entity> passengerFirst(
            java.util.Collection<? extends net.minecraft.entity.Entity> in) {
        java.util.ArrayList<net.minecraft.entity.Entity> list = new java.util.ArrayList<>(in);
        boolean anyRider = false;
        for (net.minecraft.entity.Entity e : list) {
            if (e != null && e.hasVehicle()) { anyRider = true; break; }
        }
        if (anyRider) {
            // 비교자 내 ridingDepth 재계산(O(n log n × 깊이)) 대신 깊이를 1회 사전계산해
            // (depth, 원래 인덱스) 키로 정렬한다. 인덱스 tie-break = 안정 정렬과 동일 순서
            // (기존 TimSort 안정성 재현) — 결과 순서 완전 동일, 비교 비용만 감소.
            final int n = list.size();
            long[] keys = new long[n];   // 상위 32비트: -depth(내림차순), 하위 32비트: 인덱스
            for (int i = 0; i < n; i++) {
                net.minecraft.entity.Entity e = list.get(i);
                int d = (e == null) ? 0 : ridingDepth(e);
                keys[i] = ((long) (Integer.MAX_VALUE - d) << 32) | (i & 0xFFFFFFFFL);
            }
            java.util.Arrays.sort(keys);
            java.util.ArrayList<net.minecraft.entity.Entity> sorted = new java.util.ArrayList<>(n);
            java.util.List<net.minecraft.entity.Entity> src0 = list;
            for (int i = 0; i < n; i++) sorted.add(src0.get((int) keys[i]));
            return sorted;
        }
        return list;
    }

    // ── passengerFirst(entitiesSnapshot) 틱 캐시 ──
    // as @e(untyped) 루프마다 스냅샷 복사 + 탑승자 정렬을 반복하던 것을
    // (tick, ENTITY_GEN, RIDE_MUT) 불변 시 재사용한다. 순서 = 멤버십(gen 축) × 탑승 깊이
    // (RIDE_MUT 축)만의 함수이고, 태그/점수 필터는 호출측이 라이브 재검사하므로 관측 동일.
    // RIDE_MUT: gen 을 올리지 않는 탑승 관계 변화 — ride dismount(KfcGen.dismount)·kill/damage
    // (사망 하차)·브릿지(gen++로 이미 커버)에서 증가. 리스트는 호출측이 읽기 전용 순회만 한다
    // (본문 스폰/킬은 버킷·스냅샷을 바꾸지 gen++로 다음 호출을 재계산시키며, 이 복사본은 격리).
    static long RIDE_MUT = 0;
    private static java.util.List<net.minecraft.entity.Entity> PF_CACHE;
    private static net.minecraft.server.MinecraftServer PF_SERVER;
    private static int  PF_TICK = Integer.MIN_VALUE;
    private static long PF_GEN = -1, PF_MUT = -1;

    public static java.util.List<net.minecraft.entity.Entity> passengerFirstSnap(GameContext ctx) {
        int tk = ctx.server.getTicks();
        if (PF_CACHE == null || PF_SERVER != ctx.server || PF_TICK != tk
                || PF_GEN != ENTITY_GEN || PF_MUT != RIDE_MUT) {
            PF_CACHE = passengerFirst(entitiesSnapshot(ctx));
            PF_SERVER = ctx.server; PF_TICK = tk; PF_GEN = ENTITY_GEN; PF_MUT = RIDE_MUT;
        }
        return PF_CACHE;
    }

    /** ride <target> dismount — stopRiding + 탑승 순서 캐시 무효화(emit 이 직접 필드 접근 대신 사용). */
    public static void dismount(net.minecraft.entity.Entity e) {
        if (e == null) return;
        RIDE_MUT++;
        e.stopRiding();
    }

    static void snapRemove(net.minecraft.entity.Entity e) {
        long _prevGen = ENTITY_GEN;
        ENTITY_GEN++;
        if (TB_GEN == _prevGen && e != null) {   // 태그 버킷 증분 제거(snapAdd 와 동일 정합 가드)
            tagBucketsOnRemove(e);
            TB_GEN = ENTITY_GEN;
        }
        if (TYPEIDX_GEN == _prevGen && e != null) {   // 타입 인덱스 증분 제거(동일 가드)
            typeIndexRemove(e);
            TYPEIDX_GEN = ENTITY_GEN;
        }
        if (e == null || SNAP_ENTITIES == null) return;
        if (SNAP_GEN != _prevGen) return;        // 이미 무효였던 스냅샷은 부활시키지 않는다
        // 단일 제거. discard 된 차량의 탑승자는 분리(dismount)되어 월드에 남으므로 스냅샷에서 빼지 않는다.
        // 탑승자도 함께 kill 되는 경우엔 각 엔티티가 자기 snapRemove 호출을 받는다.
        // (과거 'if (e.hasPassengers()) return;' 은 SNAP_GEN 을 stale 로 남겨 snapAdd 와 동일한 버그를 유발.)
        if (SNAP_ENTITIES.remove(e)) SNAP_FP -= _entFp(e);          // 17차: 지문 증분 동기
        SNAP_GEN = ENTITY_GEN;
    }

    // ── 틱 단위 타입→엔티티 버킷 (nearestEntity 등 타입 셀렉터 핫패스용) ──
    // 기존 nearestEntity 는 매 호출 world.getEntitiesByType(type, predicate) 로 '전 엔티티'를
    // 순회(EntityIndex.forEach + TypeFilter.downcast + 람다 + 리스트 생성)했다. TD 류는 타워마다
    // 매 틱 nearest 를 구해 O(타워×전체엔티티)로 폭증(spark 55%). 타입은 런타임 불변이므로,
    // 스냅샷을 타입별로 1회 버킷팅해두면 타워는 자기 타입 버킷만 순회하면 된다.
    // 고증 안전: 무효화는 스냅샷과 동일(틱 경계 + ENTITY_GEN=추가/제거)만 필요하고,
    // 태그/거리는 호출 시 버킷 원소에서 라이브로 읽어 필터하므로 태그 변경(브릿지 포함)도 정확.
    // 최악(틱 내 잦은 스폰으로 재빌드)에도 '매 호출 전수 스캔'인 기존보다 절대 나쁘지 않다.
    private static int  TYPEIDX_TICK = Integer.MIN_VALUE;
    private static long TYPEIDX_GEN  = -1;
    private static net.minecraft.server.MinecraftServer TYPEIDX_SERVER;
    private static java.util.Map<net.minecraft.entity.EntityType<?>, java.util.List<net.minecraft.entity.Entity>> TYPE_INDEX;
    static java.util.List<net.minecraft.entity.Entity> typeBucket(GameContext ctx, net.minecraft.entity.EntityType<?> t) {
        int tk = ctx.server.getTicks();
        if (TYPE_INDEX == null || TYPEIDX_SERVER != ctx.server || TYPEIDX_TICK != tk || TYPEIDX_GEN != ENTITY_GEN) {
            java.util.IdentityHashMap<net.minecraft.entity.EntityType<?>, java.util.List<net.minecraft.entity.Entity>> m =
                    new java.util.IdentityHashMap<>();
            for (net.minecraft.entity.Entity e : entitiesSnapshot(ctx))
                m.computeIfAbsent(e.getType(), k -> new java.util.ArrayList<>()).add(e);
            TYPE_INDEX = m; TYPEIDX_SERVER = ctx.server; TYPEIDX_TICK = tk; TYPEIDX_GEN = ENTITY_GEN;
        }
        java.util.List<net.minecraft.entity.Entity> b = TYPE_INDEX.get(t);
        return b == null ? java.util.Collections.emptyList() : b;
    }

    /** as @e[type=..](거리상한 없음) 루프용 — 타입버킷의 얕은 복사.
     *  world.getEntitiesByType(월드 인덱스 재순회 + 필터 + 리스트 생성) 대체: 집합·순서 동일
     *  (버킷 = 스냅샷(EntityIndex 순) 부분수열), isAlive 는 호출측 가드로 재현(untyped 경로와
     *  동일 방식). 본문 스폰/킬이 버킷을 증분 변형할 수 있어 복사본을 순회한다(격리). */
    public static java.util.List<net.minecraft.entity.Entity> typeBucketCopy(
            GameContext ctx, net.minecraft.entity.EntityType<?> t) {
        return new java.util.ArrayList<>(typeBucket(ctx, t));
    }

    /** as @e[type=..] 루프 본문이 '엔티티 집합을 바꾸지 않음'이 변환 시점에 증명된 경우의
     *  무복사 순회용(emit 이 본문 화이트리스트 검사 통과 시에만 이 판을 방출).
     *  스폰/킬/브릿지/함수호출이 본문에 없으면 버킷의 증분 변형이 일어나지 않아
     *  ConcurrentModificationException 및 '선택 시점 고정 집합' 위반이 없다 — 복사와 관측 동일. */
    public static java.util.List<net.minecraft.entity.Entity> typeBucketRO(
            GameContext ctx, net.minecraft.entity.EntityType<?> t) {
        return typeBucket(ctx, t);
    }

    /** 스폰/킬의 타입 인덱스 증분 반영(전체 재빌드 방지 — snapAdd/snapRemove 전용). */
    private static void typeIndexAdd(net.minecraft.entity.Entity e) {
        if (TYPE_INDEX == null || e == null) return;
        TYPE_INDEX.computeIfAbsent(e.getType(), k -> new java.util.ArrayList<>()).add(e);
    }
    private static void typeIndexRemove(net.minecraft.entity.Entity e) {
        if (TYPE_INDEX == null || e == null) return;
        java.util.List<net.minecraft.entity.Entity> b = TYPE_INDEX.get(e.getType());
        if (b != null) b.remove(e);
    }

    // ── 틱 내 무제한 존재검사(@e[type/tag] 존재여부) 메모 ──
    // 거리 필터가 없는 존재검사는 위치와 무관 — 같은 (타입, 태그) 시그니처의 반복 질의가
    // 한 틱에 수십 회 발생한다(조건부 tick 함수). 배열 신원 기반 키로 boolean 결과를 캐시.
    // 무효화 3축: 틱 경계 / ENTITY_GEN(스폰·킬·브릿지) / QUERY_MUT(태그 증분·생사 변화).
    static long QUERY_MUT = 0;
    private static final class QKey {
        final Object a, b, c;
        QKey(Object a, Object b, Object c) { this.a = a; this.b = b; this.c = c; }
        @Override public boolean equals(Object o) {
            return (o instanceof QKey k) && k.a == a && k.b == b && k.c == c;   // 신원 비교
        }
        @Override public int hashCode() {
            return System.identityHashCode(a) * 31 * 31
                 + System.identityHashCode(b) * 31 + System.identityHashCode(c);
        }
    }
    private static final java.util.HashMap<QKey, Boolean> ANY_MEMO = new java.util.HashMap<>();
    private static int  AM_TICK = Integer.MIN_VALUE;
    private static long AM_GEN = -1, AM_MUT = -1;
    private static net.minecraft.server.MinecraftServer AM_SERVER;

    private static Boolean anyMemoGet(GameContext ctx, Object type, String[] tagsPos, String[] tagsNeg) {
        int tk = ctx.server.getTicks();
        if (AM_SERVER != ctx.server || AM_TICK != tk || AM_GEN != ENTITY_GEN || AM_MUT != QUERY_MUT) {
            ANY_MEMO.clear();
            AM_SERVER = ctx.server; AM_TICK = tk; AM_GEN = ENTITY_GEN; AM_MUT = QUERY_MUT;
            return null;
        }
        return ANY_MEMO.get(new QKey(type, tagsPos, tagsNeg));
    }
    private static boolean anyMemoPut(Object type, String[] tagsPos, String[] tagsNeg, boolean v) {
        ANY_MEMO.put(new QKey(type, tagsPos, tagsNeg), v);
        return v;
    }

    // ── 틱 단위 태그→엔티티 버킷 (@e[tag=..] 무제한 스캔 핫패스용) ──
    // 무제한(박스 미적용) 태그 스캔이 매번 typeBucket/스냅샷 '전체'를 순회하던 것을 태그별
    // 소수 목록만 보게 한다. 타입버킷과 달리 멤버십(태그) 자체가 버킷 키라서 '사용 시 라이브
    // 재필터'만으론 누락(스캔 사이에 태그가 붙은 엔티티)을 못 잡는다 → 변형 훅으로 유지.
    // 14차: 버킷은 틱 간 증분 유지된다(매 틱 재구축 ~1.8%p 제거). 잔류(죽음/언로드/태그
    // 상실) 원소는 호출측 라이브 재검사(matchTagsAlive 등)가 걸러 무해하고, 누락은 아래
    // 훅 + 플레이어 입장 감지 + 100틱 주기 화해 + ENTITY_GEN(브릿지) 축이 막는다:
    //   · KfcGen.addTag/removeTag — 생성 코드의 tag add/remove 전부 이 경유(증분 갱신)
    //   · summon/kill(snapAdd/snapRemove) — 증분 갱신(스폰 NBT 태그 반영/제거)
    //   · 브릿지(instantExecute*: 바닐라 /tag 가능) — ENTITY_GEN++ → epoch 불일치로 전체 무효화
    //   · 엔티티 NBT 쓰기(readNbt 는 Tags 를 재로드할 수 있음) — readNbtTagAware 가 전후 비교로 무효화
    // 사용측이 양성/음성 태그·생존·타입·거리를 항상 라이브로 재검사하므로 버킷의 '초과' 원소는
    // 무해하고(걸러짐), 위 훅들이 '누락'을 막는다. 결과 집합/순서는 종전 경로와 동일하다
    // (버킷 = 스냅샷 순서의 부분수열; 타입 지정 헬퍼는 타입-우선 순회를 그대로 유지).
    private static final java.util.HashMap<String, java.util.ArrayList<net.minecraft.entity.Entity>> TAG_BUCKETS =
            new java.util.HashMap<>();
    private static net.minecraft.server.MinecraftServer TB_SERVER;
    private static int  TB_TICK = Integer.MIN_VALUE;
    private static long TB_GEN  = -1;
    // 14차: 틱 간 증분 유지용 외부-드리프트 감지 상태.
    private static int  TB_PLN = -1, TB_PLH = 0;            // 플레이어 목록 지문(수 + identity 합)
    private static int  TB_RECON_TICK = Integer.MIN_VALUE;  // 주기 화해 스탬프
    private static long TB_FP = Long.MIN_VALUE;             // 17차: 버킷이 마지막으로 본 개체군 지문
    // TAG_BUCKETS 가 clear 될 때마다(=틱/gen/서버 변화 rebuild, NBT 리로드 태그변경) 증가.
    // 정적 승격된 Tags 핸들(pass-4)이 (서버,틱,gen,epoch) 4-스탬프가 모두 일치할 때만
    // 캐시된 후보 리스트를 반환하고, 하나라도 어긋나면 String[] 경로로 위임(재해소)한다.
    private static long TB_EPOCH = 0;

    static java.util.List<net.minecraft.entity.Entity> tagBucket(GameContext ctx, String tag) {
        int tk = ctx.server.getTicks();
        // 14차: 틱 경계에서 버킷을 버리지 않는다(증분 훅이 진실 유지). 틱당 1회만
        // 외부 드리프트를 검사: (a) 플레이어 입퇴장(퇴장=잔류 무해, 입장=저장된 Tags 가
        // 훅 밖에서 리로드될 수 있음) → 즉시 전체 재구축, (b) 100틱 주기 화해(콘솔/OP 의
        // /tag·/summon 개입 수렴 — 문서화된 편차: 종전 1틱 → 최대 100틱).
        if (TB_TICK != tk) {
            TB_TICK = tk;
            java.util.List<net.minecraft.server.network.ServerPlayerEntity> pl = ctx.allPlayers;
            int pn = pl.size(), ph = 0;
            for (int i = 0; i < pn; i++) ph += System.identityHashCode(pl.get(i));
            boolean drift = (pn != TB_PLN || ph != TB_PLH);
            TB_PLN = pn; TB_PLH = ph;
            // 17차: 개체군 지문 대조 — 청크 로드/언로드·바닐라 스폰/사망처럼 snapAdd/snapRemove
            // 훅을 거치지 않는 개체군 변화를 그 틱에 감지해 재구축한다(바닐라 동등 ≤1틱 가시성).
            // [버그 이력] enter-room 의 forceload 로 로드된 track-main 등 태그 엔티티가 버킷에
            // 누락 → 트랙 선택룸 UI 가 다음 ENTITY_GEN 범프까지(1~2초) 무반응이었다.
            // 훅 경유 변화(경주 중 스폰/킬)는 지문이 동기 유지되어 재구축을 유발하지 않는다.
            entitiesSnapshot(ctx);                    // 이 틱 스냅샷/지문 확정(첫 접근 시 재구축)
            boolean popDrift = (SNAP_FP != TB_FP);
            TB_FP = SNAP_FP;
            // 25차[무결성·성능 양립]: 외부 태그 변이의 즉시 화해는 KfcFuncCoherenceMixin 이
            // 담당한다(외부 함수 실행 직후 onExternalFunctionExecuted()→ENTITY_GEN++ 가 아래
            // TB_GEN 검사로 버킷을 무효화). 여기는 종전 안전망(플레이어 드리프트 즉시 + 개체군
            // 지문 드리프트 즉시 + 100틱 주기)만 유지 — 함수 밖 bare /tag·콘솔 개입 수렴용.
            // (24차의 매 틱 무조건 clear 는 성능 회복 위해 철회.)
            if (drift || popDrift || TB_RECON_TICK == Integer.MIN_VALUE || tk - TB_RECON_TICK >= 100) {
                TB_RECON_TICK = tk;
                TAG_BUCKETS.clear(); TB_EPOCH++;
            }
        }
        if (TB_SERVER != ctx.server || TB_GEN != ENTITY_GEN) {
            TAG_BUCKETS.clear(); TB_EPOCH++; TB_SERVER = ctx.server; TB_GEN = ENTITY_GEN;
        }
        if (TAG_BUCKETS.isEmpty()) {
            // [실측 ~4.5%p] 종전 '태그별 지연 구축'은 질의 태그마다 스냅샷 전 엔티티 ×
            // getCommandTags().contains(해시 조회) 재스캔이었다. 스냅샷 1회 순회로 모든 태그
            // 버킷을 일괄 구축한다 — 비용이 (질의태그수×엔티티수)에서 Σ(엔티티 태그수)로 감소.
            // 시맨틱 동일: 버킷 = '스냅샷 순서의 태그 보유 엔티티 부분수열'(종전과 동일 정의),
            // 구축 후 부재 키 = 그 태그 보유 엔티티 없음 → 빈 버킷(EMPTY_BUCKET) — 종전의
            // '스캔 결과 0건' 과 동일. 증분 훅(snapAdd/tagBucketsOnAdd)은 computeIfAbsent 로
            // 부재 태그 버킷을 생성하므로 이후 스폰/태그 부여도 정확히 반영된다.
            for (net.minecraft.entity.Entity e : entitiesSnapshot(ctx)) {
                java.util.Set<String> tg0 = e.getCommandTags();
                if (tg0.isEmpty()) continue;   // 무태그(플레이어/바닐라 몹) — 이터레이터 할당 생략
                for (String tg : tg0) {
                    TAG_BUCKETS.computeIfAbsent(tg, k2 -> new java.util.ArrayList<>()).add(e);
                }
            }
        }
        // 부재 태그도 '빈 버킷'을 맵에 삽입해 반환 — 종전 lazy-cache 와 동일 계약.
        // (Tags 정적 핸들 등이 버킷 '참조'를 스탬프 기간 동안 보관하므로, 같은 틱 내 addTag 가
        //  '같은 리스트 객체'에 append 되어야 캐시된 참조가 최신 상태를 본다.)
        // 16차: 적중(대다수) 경로는 get 으로 처리 — computeIfAbsent 의 함수형 디스패치/노드
        // 처리 비용(스파크 0.41%p 중 질의분)을 미스 시에만 지불한다. 삽입 계약은 동일.
        java.util.ArrayList<net.minecraft.entity.Entity> hit = TAG_BUCKETS.get(tag);
        return hit != null ? hit : TAG_BUCKETS.computeIfAbsent(tag, k2 -> new java.util.ArrayList<>());
    }

    /** 거리상한(박스) 스캔용 후보: 양성 태그 버킷이 충분히 작을 때만 반환(그 외 null → 섹션 스캔 유지).
     *  거리·태그·생존·타입 필터는 호출측이 동일하게 재적용하므로 결과 '집합'은 박스 경로와 동일하다.
     *  관측 차이는 limit=1 다중매치 시 임의 픽 순서뿐(스냅샷 순서 — 섹션 순서보다 틱 간 안정적).
     *  버킷이 크면(수백+ 모델 태그 등) 박스 섹션 스캔이 더 적게 보므로 기존 경로를 유지한다.
     *  핫패스 근거: 충돌 계열 nearest/any 가 같은 소수-태그(@e[tag=carB..,distance=..N])를 틱당
     *  수십 회 박스 질의 — 버킷(카트 수) 순회 + 거리필터가 섹션 스캔 반복보다 훨씬 싸다. */
    /** 버킷 경로의 타입 배열 매치(1~2개 원소 — 선형이 가장 싸다). */
    private static boolean typeMatch(net.minecraft.entity.Entity e, net.minecraft.entity.EntityType<?>[] types) {
        for (net.minecraft.entity.EntityType<?> t : types) if (e.getType() == t) return true;
        return false;
    }

    private static final int TAG_BOUNDED_MAX = 128;
    static java.util.List<net.minecraft.entity.Entity> tagCandidatesBounded(GameContext ctx, String[] tagsPos) {
        java.util.List<net.minecraft.entity.Entity> b = tagCandidates(ctx, tagsPos);
        return (b != null && b.size() <= TAG_BOUNDED_MAX) ? b : null;
    }

    /** 무제한 스캔의 후보 축소: 양성 태그가 있으면 그 태그 버킷(대개 소수), 없으면 null(기존 소스 사용).
     *  호출측은 반드시 기존 필터(matchTagsAlive/타입/거리)를 그대로 재적용해야 한다 — 버킷은 1차 후보일 뿐. */
    static java.util.List<net.minecraft.entity.Entity> tagCandidates(GameContext ctx, String[] tagsPos) {
        if (tagsPos == null || tagsPos.length == 0) return null;
        // 양성 태그가 여럿이면 가장 작은 버킷을 순회 기반으로 선택(호출측이 전체 태그를
        // 라이브 재검사하므로 어느 버킷을 골라도 결과 집합 동일 — 순회량만 최소화).
        // 버킷은 태그별 틱-캐시라 추가 버킷 구축 비용은 태그당 틱당 1회로 상한된다.
        java.util.List<net.minecraft.entity.Entity> best = tagBucket(ctx, tagsPos[0]);
        for (int i = 1; i < tagsPos.length; i++) {
            if (best.isEmpty()) return best;
            java.util.List<net.minecraft.entity.Entity> b = tagBucket(ctx, tagsPos[i]);
            if (b.size() < best.size()) best = b;
        }
        return best;
    }

    /** 타입 미지정 무제한 스캔용: 양성 태그가 있으면 태그 버킷, 없으면 스냅샷(종전 소스). */
    static java.util.List<net.minecraft.entity.Entity> tagOrSnap(GameContext ctx, String[] tagsPos) {
        java.util.List<net.minecraft.entity.Entity> b = tagCandidates(ctx, tagsPos);
        return b != null ? b : entitiesSnapshot(ctx);
    }

    // ──────────────── Tags: 상수 태그셋의 후보버킷 해소 정적 승격 ────────────────
    // pass-4 가 firstEntity/nearestEntity 의 상수 tagsPos(KFC_SA_n) 를 static final
    // Tags(KFC_TAGS_n) 로 승격한다. tagCandidates(ctx, Tags) 는 (서버,틱,gen,epoch)
    // 4-스탬프가 모두 일치하면 지난 틱-내 해소 결과(TAG_BUCKETS.get 없이)를 그대로 반환하고,
    // 하나라도 어긋나면 String[] 경로로 위임해 재해소 후 재스탬프한다. 위임 경로가 진실의
    // 원천이므로 관측 결과는 String[] 판과 동일(캐시는 맵 조회만 절약). 다중 태그의 '최소
    // 버킷 선택'이 틱 내 크기변화로 달라질 수 있으나, 호출측이 전 태그를 라이브 재검사하므로
    // 어느 버킷(수퍼셋)을 잡아도 결과 집합은 동일 — 관측 동등.
    public static final class Tags {
        final String[] pos;
        java.util.List<net.minecraft.entity.Entity> b;   // 캐시된 후보 리스트(라이브 버킷 참조)
        net.minecraft.server.MinecraftServer srv;
        int  tick  = Integer.MIN_VALUE;
        long gen   = -1;
        long epoch = Long.MIN_VALUE;
        Tags(String[] pos) { this.pos = pos; }
    }
    public static Tags tags(String[] pos) { return new Tags(pos); }

    static java.util.List<net.minecraft.entity.Entity> tagCandidates(GameContext ctx, Tags t) {
        int tk = ctx.server.getTicks();
        if (t.b != null && t.srv == ctx.server && t.tick == tk && t.gen == ENTITY_GEN && t.epoch == TB_EPOCH) {
            return t.b;   // fast-path: TAG_BUCKETS 조회 없이 캐시 반환
        }
        java.util.List<net.minecraft.entity.Entity> r = tagCandidates(ctx, t.pos);  // 위임(진실의 원천)
        t.b = r; t.srv = ctx.server; t.tick = tk; t.gen = ENTITY_GEN; t.epoch = TB_EPOCH;
        return r;
    }

    static java.util.List<net.minecraft.entity.Entity> tagCandidatesBounded(GameContext ctx, Tags t) {
        java.util.List<net.minecraft.entity.Entity> b = tagCandidates(ctx, t);
        return (b != null && b.size() <= TAG_BOUNDED_MAX) ? b : null;
    }

    static java.util.List<net.minecraft.entity.Entity> tagOrSnap(GameContext ctx, Tags t) {
        java.util.List<net.minecraft.entity.Entity> b = tagCandidates(ctx, t);
        return b != null ? b : entitiesSnapshot(ctx);
    }

    /** 생성 코드의 `tag <sel> add` 는 전부 이 헬퍼를 경유한다(엔티티 직접 호출 금지 — 버킷 증분 유지). */
    public static void addTag(net.minecraft.entity.Entity e, String tag) {
        if (e == null) return;
        if (e.addCommandTag(tag)) {   // false(이미 있음/1024 초과) = 변화 없음 → 버킷 불변
            QUERY_MUT++;               // 태그 변화 → 존재검사 메모 무효화
            if (!TAG_BUCKETS.isEmpty()) {   // 전체-구축 모델: 부재 키 = 빈 버킷 → 생성해 반영
                java.util.ArrayList<net.minecraft.entity.Entity> b =
                        TAG_BUCKETS.computeIfAbsent(tag, k -> new java.util.ArrayList<>());
                if (!b.contains(e)) b.add(e);
            }
        }
    }

    /** 생성 코드의 `tag <sel> remove` 전용 (addTag 와 동일 원칙). */
    public static void removeTag(net.minecraft.entity.Entity e, String tag) {
        if (e == null) return;
        if (e.removeCommandTag(tag)) {
            QUERY_MUT++;               // 태그 변화 → 존재검사 메모 무효화
            java.util.ArrayList<net.minecraft.entity.Entity> b = TAG_BUCKETS.get(tag);
            if (b != null) b.remove(e);
        }
    }

    private static void tagBucketsOnAdd(net.minecraft.entity.Entity e) {
        if (TAG_BUCKETS.isEmpty() || e == null) return;
        for (String tg : e.getCommandTags()) {
            // 전체-구축 모델: 부재 키 = '빈 버킷' 의미이므로 새 태그는 버킷을 생성해 반영.
            java.util.ArrayList<net.minecraft.entity.Entity> b =
                    TAG_BUCKETS.computeIfAbsent(tg, k -> new java.util.ArrayList<>());
            if (!b.contains(e)) b.add(e);
        }
    }

    private static void tagBucketsOnRemove(net.minecraft.entity.Entity e) {
        if (TAG_BUCKETS.isEmpty() || e == null) return;
        for (java.util.ArrayList<net.minecraft.entity.Entity> b : TAG_BUCKETS.values()) b.remove(e);
    }

    /** e.readNbt(n) 대체 — readNbt 는 n.Tags 로 커맨드태그를 통째로 재로드할 수 있다(data merge/
     *  modify entity, store ... entity 등). 전후 태그가 실제로 달라졌을 때만 버킷을 무효화한다. */
    private static void readNbtTagAware(net.minecraft.entity.Entity e, net.minecraft.nbt.NbtCompound n) {
        QUERY_MUT++;   // NBT 재로드는 Tags/Health(생사)를 바꿀 수 있음 → 존재검사 메모 무효화
        if (TAG_BUCKETS.isEmpty()) { e.readNbt(n); return; }
        java.util.HashSet<String> before = new java.util.HashSet<>(e.getCommandTags());
        e.readNbt(n);
        if (!before.equals(e.getCommandTags())) { TAG_BUCKETS.clear(); TB_EPOCH++; }
    }

    // ── Sepals(Catheter) 호환 타입-박스 질의 ──
    // world.getEntitiesByType(TypeFilter, box, pred) 는 바닐라에선 섹션 타입 인덱스로 안전하지만,
    // Sepals-fabric 은 이를 Catheter 로 재구현하며 '박스 내 엔티티를 질의 타입으로 캐스팅' 한다.
    // 그래서 혼합 타입 박스 — 예: rectangle-hitbox/calc 가 area_effect_cloud 꼭짓점과 같은 2.6블록
    // 반경에 있는 text_display 카트를 함께 담는 박스 — 에서 TextDisplayEntity→AreaEffectCloudEntity
    // (class_8113$class_8123 → class_1295) ClassCastException 으로 크래시하거나 부분 결과를 반환한다.
    // getOtherEntities 는 Predicate<Entity> 기반이라 타입 캐스팅이 없어 Sepals 에서도 안전하며,
    // 타입 검사를 술어 안에서 수행하므로 결과 집합/의미는 getEntitiesByType(type, box, pred) 와 동일하다.
    public static java.util.List<net.minecraft.entity.Entity> entitiesByTypeBox(
            GameContext ctx, net.minecraft.entity.EntityType<?> t, net.minecraft.util.math.Box box,
            java.util.function.Predicate<net.minecraft.entity.Entity> pred) {
        // 21차(바닐라 초월): 섹션 스캔 대신 타입버킷 + 동일 판정식.
        // vanilla getOtherEntities 의 포함 조건 = 박스∩바운딩박스 + 술어 — 그대로 재현.
        // 타입 검사는 버킷 키가 보장(종전 람다의 getType()==t 와 동일). Sepals 의 Catheter
        // 캐스팅 이슈도 원천 회피(버킷 순회는 캐스팅이 없다).
        if (QUERY_IDX) {
            java.util.List<net.minecraft.entity.Entity> b = typeBucket(ctx, t);
            java.util.ArrayList<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
            for (int i = 0, n = b.size(); i < n; i++) {
                net.minecraft.entity.Entity e = b.get(i);
                if (e.isAlive() && box.intersects(e.getBoundingBox()) && pred.test(e)) out.add(e);
            }
            return out;
        }
        return ctx.world.getOtherEntities(null, box, e -> e.isAlive() && e.getType() == t && pred.test(e));
    }

    /** 네이티브화하지 못한 명령을 런타임에 1회 파싱·실행하는 브릿지 폴백. */
    /** caret(^) 로컬좌표 → 절대좌표. 마크 LocalCoordinates 공식 그대로.
     *  rot: Vec2f(x=pitch, y=yaw). (x,y,z) = (^left, ^up, ^forward). */
    public static net.minecraft.util.math.Vec3d localOffset(
            net.minecraft.util.math.Vec3d pos, net.minecraft.util.math.Vec2f rot,
            double x, double y, double z) {
        float f = net.minecraft.util.math.MathHelper.cos((rot.y + 90.0F) * 0.017453292F);
        float g = net.minecraft.util.math.MathHelper.sin((rot.y + 90.0F) * 0.017453292F);
        float h = net.minecraft.util.math.MathHelper.cos(-rot.x * 0.017453292F);
        float i = net.minecraft.util.math.MathHelper.sin(-rot.x * 0.017453292F);
        float j = net.minecraft.util.math.MathHelper.cos((-rot.x + 90.0F) * 0.017453292F);
        float k = net.minecraft.util.math.MathHelper.sin((-rot.x + 90.0F) * 0.017453292F);
        // fwd=(f*h,i,g*h), up=(f*j,k,g*j) 의 Vec3d 중간객체(fwd/up/cross/multiply)를 제거하고
        // 동일 double 연산으로 직접 계산한다(관측 비트 동일). 결과 Vec3d 1개만 할당 — 핫패스
        // (~6.6만 곳, 매 틱 ^ 좌표) 호출당 4개 할당 감소. trig 6회와 연산 순서는 그대로 보존.
        double fwdX = f * h, fwdY = i, fwdZ = g * h;
        double upX  = f * j, upY  = k, upZ  = g * j;
        double leftX = (fwdY * upZ - fwdZ * upY) * -1.0;
        double leftY = (fwdZ * upX - fwdX * upZ) * -1.0;
        double leftZ = (fwdX * upY - fwdY * upX) * -1.0;
        double dx = fwdX * z + upX * y + leftX * x;
        double dy = fwdY * z + upY * y + leftY * x;
        double dz = fwdZ * z + upZ * y + leftZ * x;
        return pos.add(dx, dy, dz);
    }

    /** if block <pos> — 블록 비교(단일 id 또는 태그 해소된 id 집합). */
    public static boolean blockMatches(net.minecraft.server.world.ServerWorld world,
                                       net.minecraft.util.math.Vec3d pos, String[] blockIds) {
        net.minecraft.util.math.BlockPos bp = net.minecraft.util.math.BlockPos.ofFloored(pos);
        // 바닐라 if block 은 청크를 로드해 실제 블록을 읽는다(getBlockState 가 청크 로드).
        // isChunkLoaded 로 false 단락하면 unless block 이 뒤집혀(미로딩=블록없음) 오작동한다.
        net.minecraft.util.Identifier id = net.minecraft.registry.Registries.BLOCK.getId(
                world.getBlockState(bp).getBlock());
        String s = id.toString();
        for (String b : blockIds) if (s.equals(b)) return true;
        return false;
    }

    /** if block <pos> <id>[state] — 블록 종류 + 상태 프로퍼티(예: "waterlogged=true,level=15") 일치.
     *  blockId 가 #tag 면 태그 멤버십 + 상태. 상태가 빈 문자열이면 종류만 검사. */
    public static boolean blockStateMatches(net.minecraft.server.world.ServerWorld world,
                                            net.minecraft.util.math.Vec3d pos,
                                            String blockId, String stateSpec) {
        net.minecraft.util.math.BlockPos bp = net.minecraft.util.math.BlockPos.ofFloored(pos);
        net.minecraft.block.BlockState st = world.getBlockState(bp);
        if (blockId != null && !blockId.isEmpty()) {
            if (blockId.startsWith("#")) {
                String tagId = blockId.substring(1);
                if (!tagId.contains(":")) tagId = "minecraft:" + tagId;
                net.minecraft.registry.tag.TagKey<net.minecraft.block.Block> key =
                        BLOCK_TAG_CACHE.computeIfAbsent(tagId, t ->
                                net.minecraft.registry.tag.TagKey.of(net.minecraft.registry.RegistryKeys.BLOCK, idOf(t)));
                if (!st.isIn(key)) return false;
            } else {
                String s = net.minecraft.registry.Registries.BLOCK.getId(st.getBlock()).toString();
                String want = blockId.contains(":") ? blockId : "minecraft:" + blockId;
                if (!s.equals(want)) return false;
            }
        }
        if (stateSpec != null && !stateSpec.isEmpty()) {
            for (String pair : stateSpec.split(",")) {
                int eq = pair.indexOf('=');
                if (eq < 0) continue;
                String pname = pair.substring(0, eq).trim();
                String pval = pair.substring(eq + 1).trim();
                net.minecraft.state.property.Property<?> prop = st.getBlock().getStateManager().getProperty(pname);
                if (prop == null) return false;
                java.util.Optional<?> parsed = prop.parse(pval);
                if (parsed.isEmpty()) return false;
                if (!st.get(prop).equals(parsed.get())) return false;
            }
        }
        return true;
    }

    /** @e[predicate=ns:id] / if predicate — 런타임 술어 평가(컴파일타임 JSON 부재 시).
     *  바닐라 SELECTOR 컨텍스트(THIS_ENTITY+ORIGIN)로 LootCondition.test 호출. */
    private static final java.util.HashMap<String, net.minecraft.loot.condition.LootCondition>
            PREDICATE_CACHE = new java.util.HashMap<>();

    public static boolean testPredicate(net.minecraft.server.command.ServerCommandSource src,
                                        net.minecraft.entity.Entity e, String predId) {
        if (e == null || src == null) return false;
        net.minecraft.loot.condition.LootCondition cond = PREDICATE_CACHE.get(predId);
        if (cond == null) {
            try {
                net.minecraft.util.Identifier id = idOf(predId);
                java.util.Optional<net.minecraft.registry.entry.RegistryEntry.Reference<net.minecraft.loot.condition.LootCondition>> opt =
                        src.getServer().getReloadableRegistries().createRegistryLookup()
                                .getOptionalEntry(net.minecraft.registry.RegistryKey.of(
                                        net.minecraft.registry.RegistryKeys.PREDICATE, id));
                if (opt.isEmpty()) return false;
                cond = opt.get().value();
                PREDICATE_CACHE.put(predId, cond);
            } catch (Exception ex) { return false; }
        }
        try {
            net.minecraft.server.world.ServerWorld world = (net.minecraft.server.world.ServerWorld) e.getWorld();
            net.minecraft.loot.context.LootWorldContext wc =
                    new net.minecraft.loot.context.LootWorldContext.Builder(world)
                            .add(net.minecraft.loot.context.LootContextParameters.THIS_ENTITY, e)
                            .add(net.minecraft.loot.context.LootContextParameters.ORIGIN, e.getPos())
                            .build(net.minecraft.loot.context.LootContextTypes.SELECTOR);
            net.minecraft.loot.context.LootContext lc =
                    new net.minecraft.loot.context.LootContext.Builder(wc).build(java.util.Optional.empty());
            return cond.test(lc);
        } catch (Exception ex) { return false; }
    }

    /** setblock <pos> <block> [replace|destroy|keep].
     *  바닐라 SetBlockCommand 흐름 그대로: BlockArgumentParser 로 상태+블록엔티티 NBT 를 파싱한 뒤
     *  BlockStateArgument.setBlockState(world, pos, flags) 로 적용한다(블록엔티티 NBT 포함).
     *  blockStr 은 'minecraft:gold_block' / 'oak_sign{...}' / 'stone[custom_data={...}]' 등 원문. */
    private static final java.util.HashMap<String, net.minecraft.command.argument.BlockStateArgument>
            BLOCK_ARG_CACHE = new java.util.HashMap<>();

    private static net.minecraft.command.argument.BlockStateArgument parseBlockArg(String blockStr) {
        net.minecraft.command.argument.BlockStateArgument a = BLOCK_ARG_CACHE.get(blockStr);
        if (a != null) return a;
        try {
            net.minecraft.command.argument.BlockArgumentParser.BlockResult r =
                    net.minecraft.command.argument.BlockArgumentParser.block(
                            net.minecraft.registry.Registries.BLOCK, blockStr, true);
            a = new net.minecraft.command.argument.BlockStateArgument(
                    r.blockState(), r.properties().keySet(), r.nbt());
            BLOCK_ARG_CACHE.put(blockStr, a);
            return a;
        } catch (com.mojang.brigadier.exceptions.CommandSyntaxException e) {
            return null;
        }
    }

    /** [고증 버그픽스] 바닐라 SetBlock/Fill/Clone 커맨드는 블록을 교체하기 전에
     *  Clearable.clear(블록엔티티)로 내용물을 비운다. 이게 없으면 컨테이너(상자 등)가
     *  onStateReplaced → ItemScatterer 로 내용물을 밖으로 뱉는다
     *  (실증: security-chest 상자↔통 전환에서 아이템 산란 — 바닐라에는 없던 현상). */
    private static void clearBeforeReplace(net.minecraft.server.world.ServerWorld world,
                                           net.minecraft.util.math.BlockPos pos) {
        // yarn 1.21.5 의 Clearable 은 인스턴스 clear() 만 제공(정적 헬퍼 없음 — 컴파일러 확인).
        net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof net.minecraft.util.Clearable c) c.clear();
    }

    public static void setBlock(net.minecraft.server.world.ServerWorld world,
                                net.minecraft.util.math.BlockPos pos, String blockStr, String mode) {
        if (world == null) return;
        net.minecraft.command.argument.BlockStateArgument arg = parseBlockArg(blockStr);
        if (arg == null) return;
        // keep: 기존 블록이 비어있을 때만 배치
        if ("keep".equals(mode) && !world.getBlockState(pos).isAir()) return;
        // destroy: 기존 블록을 부수고(드롭 발생) 배치
        if ("destroy".equals(mode)) world.breakBlock(pos, true, null, 512);
        clearBeforeReplace(world, pos);   // 바닐라 SetBlockCommand 동일(컨테이너 산란 방지)
        arg.setBlockState(world, pos, net.minecraft.block.Block.NOTIFY_ALL);
    }

    // ── 엔티티 타입 태그(#ns:path) 런타임 멤버십 — 컴파일타임 태그 JSON 부재 시 사용 ──
    private static final java.util.HashMap<String, net.minecraft.registry.tag.TagKey<net.minecraft.entity.EntityType<?>>>
            ENTITY_TYPE_TAG_CACHE = new java.util.HashMap<>();

    public static boolean entityInTypeTag(net.minecraft.entity.Entity e, String tagId) {
        if (e == null) return false;
        net.minecraft.registry.tag.TagKey<net.minecraft.entity.EntityType<?>> key =
                ENTITY_TYPE_TAG_CACHE.computeIfAbsent(tagId, t ->
                        net.minecraft.registry.tag.TagKey.of(net.minecraft.registry.RegistryKeys.ENTITY_TYPE,
                                idOf(t)));
        return e.getType().isIn(key);
    }

    /** type=<커스텀/모드 단일 타입> 런타임 비교 (컴파일타임 EntityType 상수 부재 시). */
    /** type=<커스텀/모드 단일 타입> 런타임 비교 (컴파일타임 EntityType 상수 부재 시). */
    public static boolean entityTypeIs(net.minecraft.entity.Entity e, String typeId) {
        if (e == null) return false;
        String want = typeId.contains(":") ? typeId : "minecraft:" + typeId;
        return net.minecraft.registry.Registries.ENTITY_TYPE.getId(e.getType()).toString().equals(want);
    }

    /** if entity <@e/@n[...]> 범용 존재검사 — 임의 가드 람다로 전 엔티티 1개라도 매칭하는지. */
    public static boolean anyEntityWhere(GameContext ctx,
            java.util.function.Predicate<net.minecraft.entity.Entity> pred) {
        for (net.minecraft.entity.Entity e : entitiesSnapshot(ctx)) {
            if (e.isAlive() && pred.test(e)) return true;   // @e/@n: 바닐라 기본 isAlive 술어
        }
        return false;
    }

    // ── team ── (바닐라 TeamCommand 흐름: Scoreboard 팀 API 직접 호출)
    public static void teamAdd(GameContext ctx, String name, String displayJson) {
        NAME_GEN++;   // 팀 생성/표시명 변화 — 표시명 틱-캐시 무효화(10차)
        net.minecraft.scoreboard.Team t = ctx.scoreboard.getTeam(name);
        if (t == null) t = ctx.scoreboard.addTeam(name);   // 멱등: 이미 있으면 재사용
        if (displayJson != null) t.setDisplayName(parseText(ctx.server, displayJson));
    }

    private static net.minecraft.scoreboard.AbstractTeam.VisibilityRule visRule(String v) {
        switch (v) {
            case "always": return net.minecraft.scoreboard.AbstractTeam.VisibilityRule.ALWAYS;
            case "never": return net.minecraft.scoreboard.AbstractTeam.VisibilityRule.NEVER;
            case "hideForOtherTeams": return net.minecraft.scoreboard.AbstractTeam.VisibilityRule.HIDE_FOR_OTHER_TEAMS;
            case "hideForOwnTeam": return net.minecraft.scoreboard.AbstractTeam.VisibilityRule.HIDE_FOR_OWN_TEAM;
            default: return null;
        }
    }
    private static net.minecraft.scoreboard.AbstractTeam.CollisionRule collRule(String v) {
        switch (v) {
            case "always": return net.minecraft.scoreboard.AbstractTeam.CollisionRule.ALWAYS;
            case "never": return net.minecraft.scoreboard.AbstractTeam.CollisionRule.NEVER;
            case "pushOtherTeams": return net.minecraft.scoreboard.AbstractTeam.CollisionRule.PUSH_OTHER_TEAMS;
            case "pushOwnTeam": return net.minecraft.scoreboard.AbstractTeam.CollisionRule.PUSH_OWN_TEAM;
            default: return null;
        }
    }

    public static void teamModify(GameContext ctx, String name, String option, String value) {
        NAME_GEN++;   // 팀 장식(색/prefix/suffix 등) 변화 — 표시명 틱-캐시 무효화(10차)
        net.minecraft.scoreboard.Team t = ctx.scoreboard.getTeam(name);
        if (t == null) return;
        switch (option) {
            case "friendlyFire": t.setFriendlyFireAllowed(Boolean.parseBoolean(value)); break;
            case "seeFriendlyInvisibles": t.setShowFriendlyInvisibles(Boolean.parseBoolean(value)); break;
            case "nametagVisibility": { var r = visRule(value); if (r != null) t.setNameTagVisibilityRule(r); break; }
            case "collisionRule": { var r = collRule(value); if (r != null) t.setCollisionRule(r); break; }
            case "color": { net.minecraft.util.Formatting f = net.minecraft.util.Formatting.byName(value); if (f != null) t.setColor(f); break; }
            case "displayName": t.setDisplayName(parseText(ctx.server, value)); break;
            case "prefix": t.setPrefix(parseText(ctx.server, value)); break;
            case "suffix": t.setSuffix(parseText(ctx.server, value)); break;
            default: break;
        }
    }

    public static void teamLeave(ServerScoreboard sb, String holder) {
        // 22차: 실제로 팀에서 빠진 경우에만 무효화(무소속 leave 는 표시명 무변화).
        if (holder != null && sb.clearTeam(holder)) NAME_GEN++;
    }

    /** team join <team> <members> — 멤버를 팀에 추가. */
    public static void teamJoin(ServerScoreboard sb, String teamName, String holder) {
        if (holder == null) return;
        net.minecraft.scoreboard.Team t = sb.getTeam(teamName);
        if (t == null) return;
        // 22차: 이미 같은 팀이면 멤버십/표시명 무변화 — 바닐라 재조인(제거 후 재추가)과 결과
        // 동등하므로 스킵(NAME_GEN 보존 + 팀 패킷 재전송 생략). 실변화시에만 무효화.
        if (sb.getScoreHolderTeam(holder) == t) return;
        NAME_GEN++;
        sb.addScoreHolderToTeam(holder, t);
    }

    // ── fill ── (바닐라 FillCommand 흐름: 박스 순회 + 모드/필터 판정 후 setBlockState)
    private static boolean fillFilterMatches(net.minecraft.server.world.ServerWorld world,
                                             net.minecraft.util.math.BlockPos pos, String filter) {
        if (filter == null) return true;                 // 필터 없음 = 전부
        net.minecraft.block.BlockState st = world.getBlockState(pos);
        if (filter.startsWith("#")) {
            net.minecraft.registry.tag.TagKey<net.minecraft.block.Block> key =
                    BLOCK_TAG_CACHE.computeIfAbsent(filter.substring(1), t ->
                            net.minecraft.registry.tag.TagKey.of(net.minecraft.registry.RegistryKeys.BLOCK,
                                    idOf(t)));
            return st.isIn(key);
        }
        String want = filter.indexOf(':') < 0 ? "minecraft:" + filter : filter;
        // 필터의 블록상태([..])는 무시하고 블록 id 만 비교(관측된 fill 필터는 전부 단순 id/#태그)
        int br = want.indexOf('[');
        if (br >= 0) want = want.substring(0, br);
        return net.minecraft.registry.Registries.BLOCK.getId(st.getBlock()).toString().equals(want);
    }

    public static void fill(net.minecraft.server.world.ServerWorld world,
                            int x1, int y1, int z1, int x2, int y2, int z2,
                            String blockStr, String mode, String filter) {
        if (world == null) return;
        net.minecraft.command.argument.BlockStateArgument arg = parseBlockArg(blockStr);
        if (arg == null) return;
        int minX = Math.min(x1, x2), minY = Math.min(y1, y2), minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2), maxY = Math.max(y1, y2), maxZ = Math.max(z1, z2);
        for (net.minecraft.util.math.BlockPos p : net.minecraft.util.math.BlockPos.iterate(minX, minY, minZ, maxX, maxY, maxZ)) {
            boolean onShell = p.getX() == minX || p.getX() == maxX || p.getY() == minY || p.getY() == maxY
                    || p.getZ() == minZ || p.getZ() == maxZ;
            if ("outline".equals(mode) && !onShell) continue;
            if ("hollow".equals(mode) && !onShell) {
                // hollow: 내부는 공기로 (교체 전 clear — 바닐라 FillCommand 동일)
                clearBeforeReplace(world, p);
                world.setBlockState(p, net.minecraft.block.Blocks.AIR.getDefaultState(), net.minecraft.block.Block.NOTIFY_ALL);
                continue;
            }
            if ("keep".equals(mode) && !world.getBlockState(p).isAir()) continue;
            if (("replace".equals(mode) || mode == null) && !fillFilterMatches(world, p, filter)) continue;
            if ("destroy".equals(mode)) world.breakBlock(p, true, null, 512);
            clearBeforeReplace(world, p);   // 바닐라 FillCommand 동일(컨테이너 산란 방지)
            arg.setBlockState(world, p.toImmutable(), net.minecraft.block.Block.NOTIFY_ALL);
        }
    }

    /** clone <begin> <end> <dest> (replace normal). 겹침 대비 전체 수집 후 배치.
     *  dest 는 영역의 최소 코너가 매핑되는 위치. */
    public static void clone(net.minecraft.server.world.ServerWorld world,
                             int x1, int y1, int z1, int x2, int y2, int z2, int dx, int dy, int dz) {
        if (world == null) return;
        int minX = Math.min(x1, x2), minY = Math.min(y1, y2), minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2), maxY = Math.max(y1, y2), maxZ = Math.max(z1, z2);
        int offX = dx - minX, offY = dy - minY, offZ = dz - minZ;
        net.minecraft.registry.RegistryWrapper.WrapperLookup lk = world.getRegistryManager();
        java.util.List<net.minecraft.util.math.BlockPos> dst = new java.util.ArrayList<>();
        java.util.List<net.minecraft.block.BlockState> sts = new java.util.ArrayList<>();
        java.util.List<net.minecraft.nbt.NbtCompound> nbts = new java.util.ArrayList<>();
        for (net.minecraft.util.math.BlockPos p : net.minecraft.util.math.BlockPos.iterate(minX, minY, minZ, maxX, maxY, maxZ)) {
            net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(p);
            dst.add(new net.minecraft.util.math.BlockPos(p.getX() + offX, p.getY() + offY, p.getZ() + offZ));
            sts.add(world.getBlockState(p));
            nbts.add(be != null ? be.createNbt(lk) : null);
        }
        for (int i = 0; i < dst.size(); i++) {
            net.minecraft.util.math.BlockPos pos = dst.get(i);
            clearBeforeReplace(world, pos);   // 바닐라 CloneCommand 동일(컨테이너 산란 방지)
            world.setBlockState(pos, sts.get(i), net.minecraft.block.Block.NOTIFY_ALL);
            if (nbts.get(i) != null) {
                net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
                if (be != null) be.read(nbts.get(i), lk);
            }
        }
    }

    /** clone 확장: maskMode(replace|masked|filtered)+filter, moveMode(normal|force|move). */
    @SuppressWarnings("deprecation")
    public static void cloneEx(net.minecraft.server.world.ServerWorld world,
                               int x1, int y1, int z1, int x2, int y2, int z2, int dx, int dy, int dz,
                               String maskMode, String filter, String moveMode) {
        if (world == null) return;
        int minX = Math.min(x1, x2), minY = Math.min(y1, y2), minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2), maxY = Math.max(y1, y2), maxZ = Math.max(z1, z2);
        int offX = dx - minX, offY = dy - minY, offZ = dz - minZ;
        net.minecraft.registry.RegistryWrapper.WrapperLookup lk = world.getRegistryManager();
        net.minecraft.block.Block filterBlock = null;
        if ("filtered".equals(maskMode) && filter != null) {
            net.minecraft.util.Identifier fid =
                    net.minecraft.util.Identifier.tryParse(filter.split("\\[")[0].split("\\{")[0]);
            if (fid != null) filterBlock = net.minecraft.registry.Registries.BLOCK.get(fid);
        }
        boolean masked = "masked".equals(maskMode);
        boolean move = "move".equals(moveMode);
        java.util.List<net.minecraft.util.math.BlockPos> src = new java.util.ArrayList<>();
        java.util.List<net.minecraft.util.math.BlockPos> dst = new java.util.ArrayList<>();
        java.util.List<net.minecraft.block.BlockState> sts = new java.util.ArrayList<>();
        java.util.List<net.minecraft.nbt.NbtCompound> nbts = new java.util.ArrayList<>();
        for (net.minecraft.util.math.BlockPos p : net.minecraft.util.math.BlockPos.iterate(minX, minY, minZ, maxX, maxY, maxZ)) {
            net.minecraft.block.BlockState st = world.getBlockState(p);
            if (masked && st.isAir()) continue;
            if (filterBlock != null && !st.isOf(filterBlock)) continue;
            net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(p);
            src.add(p.toImmutable());
            dst.add(new net.minecraft.util.math.BlockPos(p.getX() + offX, p.getY() + offY, p.getZ() + offZ));
            sts.add(st);
            nbts.add(be != null ? be.createNbt(lk) : null);
        }
        java.util.Set<net.minecraft.util.math.BlockPos> dstSet = new java.util.HashSet<>(dst);
        for (int i = 0; i < dst.size(); i++) {
            net.minecraft.util.math.BlockPos pos = dst.get(i);
            clearBeforeReplace(world, pos);   // 바닐라 CloneCommand 동일(컨테이너 산란 방지)
            world.setBlockState(pos, sts.get(i), net.minecraft.block.Block.NOTIFY_ALL);
            if (nbts.get(i) != null) {
                net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
                if (be != null) be.read(nbts.get(i), lk);
            }
        }
        if (move) {
            net.minecraft.block.BlockState air = net.minecraft.block.Blocks.AIR.getDefaultState();
            for (net.minecraft.util.math.BlockPos p : src)
                if (!dstSet.contains(p)) {
                    clearBeforeReplace(world, p);   // move: 원본 소거도 산란 없이(바닐라 동일)
                    world.setBlockState(p, air, net.minecraft.block.Block.NOTIFY_ALL);
                }
        }
    }

    /** item replace block <pos> <slot> with <item> [count] — 블록 컨테이너 슬롯 교체. */
    public static void itemReplaceBlockWith(net.minecraft.server.MinecraftServer server,
                                            net.minecraft.server.world.ServerWorld world,
                                            net.minecraft.util.math.BlockPos pos,
                                            String slotName, String itemId, int count) {
        if (world == null) return;
        net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof net.minecraft.inventory.Inventory inv)) return;
        int idx = slotIndexOf(slotName);
        if (idx < 0 || idx >= inv.size()) return;
        if (itemId == null) { inv.setStack(idx, net.minecraft.item.ItemStack.EMPTY); be.markDirty(); return; }
        net.minecraft.util.Identifier id = net.minecraft.util.Identifier.tryParse(itemId);
        if (id == null) return;
        net.minecraft.item.Item it = net.minecraft.registry.Registries.ITEM.get(id);
        net.minecraft.item.ItemStack stack = new net.minecraft.item.ItemStack(it, count <= 0 ? 1 : count);
        inv.setStack(idx, stack);
        be.markDirty();
    }
    /** container.N / N -> 슬롯 인덱스(블록 컨테이너는 container.* 만 유효). */
    private static int slotIndexOf(String slotName) {
        if (slotName == null) return -1;
        String s = slotName;
        int dot = s.lastIndexOf('.');
        if (dot >= 0) s = s.substring(dot + 1);
        try { return Integer.parseInt(s.trim()); } catch (NumberFormatException e) { return -1; }
    }

    /** advancement grant/revoke <targets> everything — 전체 어드밴스먼트 일괄. */
    public static void advancementAll(net.minecraft.server.MinecraftServer server,
                                      net.minecraft.entity.Entity e, boolean grant) {
        if (!(e instanceof net.minecraft.server.network.ServerPlayerEntity p)) return;
        for (net.minecraft.advancement.AdvancementEntry adv : server.getAdvancementLoader().getAdvancements()) {
            for (String c : adv.value().criteria().keySet()) {
                if (grant) p.getAdvancementTracker().grantCriterion(adv, c);
                else       p.getAdvancementTracker().revokeCriterion(adv, c);
            }
        }
    }

    /** advancement grant|revoke <t> from|until|through <id> — AdvancementCommand.Selection 동일:
     *  from=자신+자손 전체, until=루트→자신 경로(자신 포함), through=합집합.
     *  (부모 링크 대신 루트에서의 DFS 경로 탐색 — PlacedAdvancement.getChildren/getRoot 사용) */
    public static void advancementScope(net.minecraft.server.MinecraftServer server,
                                        net.minecraft.entity.Entity e,
                                        String advId, String scope, boolean grant) {
        if (!(e instanceof net.minecraft.server.network.ServerPlayerEntity p)) return;
        net.minecraft.advancement.AdvancementEntry target =
                server.getAdvancementLoader().get(net.minecraft.util.Identifier.of(advId));
        if (target == null) return;
        net.minecraft.advancement.PlacedAdvancement placed =
                server.getAdvancementLoader().getManager().get(target);
        if (placed == null) return;
        java.util.LinkedHashSet<net.minecraft.advancement.AdvancementEntry> sel = new java.util.LinkedHashSet<>();
        boolean up = !"from".equals(scope);      // until/through: 루트→자신 경로
        boolean down = !"until".equals(scope);   // from/through: 자신+자손
        if (up) {
            java.util.ArrayDeque<net.minecraft.advancement.PlacedAdvancement> path = new java.util.ArrayDeque<>();
            findPathTo(placed.getRoot(), target, path);
            for (net.minecraft.advancement.PlacedAdvancement pa : path) sel.add(pa.getAdvancementEntry());
        }
        if (down) collectDescendants(placed, sel);
        for (net.minecraft.advancement.AdvancementEntry adv : sel) {
            for (String c : adv.value().criteria().keySet()) {
                if (grant) p.getAdvancementTracker().grantCriterion(adv, c);
                else       p.getAdvancementTracker().revokeCriterion(adv, c);
            }
        }
    }
    private static boolean findPathTo(net.minecraft.advancement.PlacedAdvancement node,
                                      net.minecraft.advancement.AdvancementEntry target,
                                      java.util.ArrayDeque<net.minecraft.advancement.PlacedAdvancement> path) {
        path.addLast(node);
        if (node.getAdvancementEntry() == target) return true;
        for (net.minecraft.advancement.PlacedAdvancement ch : node.getChildren())
            if (findPathTo(ch, target, path)) return true;
        path.removeLast();
        return false;
    }
    private static void collectDescendants(net.minecraft.advancement.PlacedAdvancement node,
                                           java.util.Set<net.minecraft.advancement.AdvancementEntry> out) {
        out.add(node.getAdvancementEntry());
        for (net.minecraft.advancement.PlacedAdvancement ch : node.getChildren())
            collectDescendants(ch, out);
    }

    /** msg/tell/w — 바닐라 incoming/outgoing 중 수신측(incoming) 표기: 회색 이탤릭 귓속말. */
    public static void msgTo(net.minecraft.server.command.ServerCommandSource src,
                             net.minecraft.server.network.ServerPlayerEntity to, String text) {
        to.sendMessage(net.minecraft.text.Text.translatable("commands.message.display.incoming",
                src.getDisplayName(), net.minecraft.text.Text.literal(text))
                .formatted(net.minecraft.util.Formatting.GRAY, net.minecraft.util.Formatting.ITALIC));
    }

    /** teammsg — 실행자 팀 전원에게 chat.type.team.text 형식 전송(팀 없으면 no-op=바닐라 실패). */
    public static void teamMsg(GameContext ctx, net.minecraft.server.command.ServerCommandSource src, String text) {
        net.minecraft.entity.Entity e = src.getEntity();
        if (e == null) return;
        net.minecraft.scoreboard.Team t = ctx.scoreboard.getScoreHolderTeam(e.getNameForScoreboard());
        if (t == null) return;
        net.minecraft.text.Text msg = net.minecraft.text.Text.translatable("chat.type.team.text",
                t.getFormattedName(), src.getDisplayName(), net.minecraft.text.Text.literal(text));
        java.util.Collection<String> members = t.getPlayerList();
        for (net.minecraft.server.network.ServerPlayerEntity mp : ctx.allPlayers)
            if (members.contains(mp.getNameForScoreboard())) mp.sendMessage(msg);
    }

    /** kick <player> [<reason>] */
    public static void kickPlayer(net.minecraft.server.network.ServerPlayerEntity p, String reason) {
        p.networkHandler.disconnect(reason == null
                ? net.minecraft.text.Text.translatable("multiplayer.disconnect.kicked")
                : net.minecraft.text.Text.literal(reason));
    }

    /** forceload add <fromX> <fromZ> [toX] [toZ] — 블록좌표 사각형이 덮는 청크 전부 강제로드. */
    public static void forceloadAdd(net.minecraft.server.world.ServerWorld world,
                                    int fx, int fz, int tx, int tz) {
        if (world == null) return;
        int cx1 = Math.min(fx, tx) >> 4, cx2 = Math.max(fx, tx) >> 4;
        int cz1 = Math.min(fz, tz) >> 4, cz2 = Math.max(fz, tz) >> 4;
        for (int cx = cx1; cx <= cx2; cx++)
            for (int cz = cz1; cz <= cz2; cz++)
                world.setChunkForced(cx, cz, true);
    }

    // forceload remove <from> [<to>] — 컬럼 좌표 범위의 강제로드 해제(add 와 동일 루프, false).
    public static void forceloadRemove(net.minecraft.server.world.ServerWorld world,
                                       int fx, int fz, int tx, int tz) {
        if (world == null) return;
        int cx1 = Math.min(fx, tx) >> 4, cx2 = Math.max(fx, tx) >> 4;
        int cz1 = Math.min(fz, tz) >> 4, cz2 = Math.max(fz, tz) >> 4;
        for (int cx = cx1; cx <= cx2; cx++)
            for (int cz = cz1; cz <= cz2; cz++)
                world.setChunkForced(cx, cz, false);
    }

    // forceload remove all — 현재 강제로드된 모든 청크 해제. 순회 중 변경 방지 위해 배열 복사 후 해제.
    public static void forceloadRemoveAll(net.minecraft.server.world.ServerWorld world) {
        if (world == null) return;
        long[] forced = world.getForcedChunks().toLongArray();
        for (long key : forced) {
            world.setChunkForced(net.minecraft.util.math.ChunkPos.getPackedX(key),
                                 net.minecraft.util.math.ChunkPos.getPackedZ(key), false);
        }
    }

    /** title <targets> times <fadeIn> <stay> <fadeOut> */
    public static void titleTimes(net.minecraft.server.network.ServerPlayerEntity p, int in, int stay, int out) {
        p.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.TitleFadeS2CPacket(in, stay, out));
    }

    /** title <targets> clear|reset — reset=true 면 times 도 초기화. */
    public static void titleClear(net.minecraft.server.network.ServerPlayerEntity p, boolean reset) {
        p.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.ClearTitleS2CPacket(reset));
    }

    /** stopsound <targets> [category] [sound] */
    public static void stopSound(net.minecraft.server.network.ServerPlayerEntity p, String category, String soundId) {
        net.minecraft.util.Identifier id = (soundId == null ? null : idOf(soundId));
        net.minecraft.sound.SoundCategory cat = null;
        if (category != null) {
            for (net.minecraft.sound.SoundCategory c : net.minecraft.sound.SoundCategory.values()) {
                if (c.getName().equals(category)) { cat = c; break; }
            }
        }
        p.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.StopSoundS2CPacket(id, cat));
    }

    /** 타입 미지정 @e[...] — 전 엔티티 순회 필터. */
    public static java.util.List<net.minecraft.entity.Entity> allEntitiesAny(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
            if (!posInRange(origin, e.getPos(), minDist, maxDist)) continue;
            out.add(e);
        }
        return out;
    }

    /** forceload add <pos> — 청크 강제 로드. */
    public static void forceloadAdd(net.minecraft.server.world.ServerWorld world,
                                    net.minecraft.util.math.Vec3d pos, boolean forced) {
        net.minecraft.util.math.BlockPos bp = net.minecraft.util.math.BlockPos.ofFloored(pos);
        world.setChunkForced(bp.getX() >> 4, bp.getZ() >> 4, forced);
    }

    /** if block <pos> #tag — 런타임 태그 멤버십(데이터팩에 없는 바닐라 태그 등). */
    public static boolean blockInTag(net.minecraft.server.world.ServerWorld world,
                                     net.minecraft.util.math.Vec3d pos, String tagId) {
        net.minecraft.util.math.BlockPos bp = net.minecraft.util.math.BlockPos.ofFloored(pos);
        net.minecraft.registry.tag.TagKey<net.minecraft.block.Block> key =
                BLOCK_TAG_CACHE.computeIfAbsent(tagId, t ->
                        net.minecraft.registry.tag.TagKey.of(net.minecraft.registry.RegistryKeys.BLOCK,
                                idOf(t)));
        return world.getBlockState(bp).isIn(key);
    }

    /** if loaded <pos> — 청크 로드 여부. */
    public static boolean posLoaded(net.minecraft.server.world.ServerWorld world,
                                    net.minecraft.util.math.Vec3d pos) {
        net.minecraft.util.math.BlockPos bp = net.minecraft.util.math.BlockPos.ofFloored(pos);
        return world.isChunkLoaded(bp.getX() >> 4, bp.getZ() >> 4);
    }

    /** distance 상한이 있을 때 섹션 스캔을 한정하는 AABB.
     *  바닐라 셀렉터의 distance=..N 최적화와 동일 — 전 월드 엔티티 섹션 순회를 막는다(벽충돌 핫패스). */
    public static net.minecraft.util.math.Box rangeBox(net.minecraft.util.math.Vec3d o, double maxDist) {
        return new net.minecraft.util.math.Box(o.x - maxDist, o.y - maxDist, o.z - maxDist,
                                               o.x + maxDist, o.y + maxDist, o.z + maxDist);
    }

    // ── limit=1(arbitrary) 셀렉터의 조기종료 첫-매치 조회 ──
    // firstEntityAnyType* 는 기존에 allEntitiesAnyType 로 '범위 내 전체'를 리스트로 모은 뒤
    // 첫 매치를 골랐다(리스트 할당 + 전체 순회). 충돌 핫패스에서 같은 @e[carB,limit=1] 이
    // 한 틱에 수십 번 해소되며 매번 전체 리스트를 만들어 GC/CPU 를 크게 먹었다.
    // collectEntitiesByType(..., maxCount=1) 은 동일한 엔티티 인덱스를 같은 순서로 순회하되
    // '첫 매치에서 즉시 중단'한다(관측 동일: arbitrary limit=1 = 순회 첫 매치). getOtherEntities
    // 도 동일 인덱스(forEachIntersects)를 쓰므로 첫 요소가 일치한다.
    private static final net.minecraft.util.TypeFilter<net.minecraft.entity.Entity, net.minecraft.entity.Entity>
            ENTITY_ANY = net.minecraft.util.TypeFilter.instanceOf(net.minecraft.entity.Entity.class);

    private static net.minecraft.entity.Entity firstInBox(
            GameContext ctx, net.minecraft.util.math.Vec3d origin, double maxDist,
            java.util.function.Predicate<net.minecraft.entity.Entity> fullPred) {
        java.util.ArrayList<net.minecraft.entity.Entity> tmp = new java.util.ArrayList<>(1);
        ctx.world.collectEntitiesByType(ENTITY_ANY, rangeBox(origin, maxDist), fullPred, tmp, 1);
        return tmp.isEmpty() ? null : tmp.get(0);
    }

    /** 특정 타입 box 존재검사 — 첫 매치에서 수집을 멈춘다(limit=1). anyEntity 의 box 경로가
     *  entitiesByTypeBox(...).isEmpty() 로 '모든 매치를 리스트에 담은 뒤' 비었는지 보던 것을,
     *  존재 의미 동일하게 유지하며 리스트 성장/전량 순회 없이 조기종료로 대체한다.
     *  (getOtherEntities 를 쓰는 entitiesByTypeBox 와 동일한 술어 기반 — Sepals 캐스팅 안전.) */
    private static boolean anyInBoxTyped(
            GameContext ctx, net.minecraft.entity.EntityType<?> type, net.minecraft.util.math.Box box,
            java.util.function.Predicate<net.minecraft.entity.Entity> pred) {
        java.util.ArrayList<net.minecraft.entity.Entity> tmp = new java.util.ArrayList<>(1);
        ctx.world.collectEntitiesByType(ENTITY_ANY, box,
                e -> e.isAlive() && e.getType() == type && pred.test(e), tmp, 1);
        return !tmp.isEmpty();
    }

    /** 두 좌표 거리의 [lo,hi] 범위 검사 (음수=무시).
     *  거리 자체(sqrt) 대신 제곱거리로 비교한다. lo,hi>=0 이고 거리>=0 이므로
     *  d<lo ⇔ d²<lo², d>hi ⇔ d²>hi² 로 정적 동등이며 sqrt 1회를 제거한다
     *  (inRange(엔티티/Vec3d) 와 동일 패턴). */
    public static boolean posInRange(net.minecraft.util.math.Vec3d a, net.minecraft.util.math.Vec3d b,
                                     double lo, double hi) {
        double d2 = a.squaredDistanceTo(b);
        if (lo >= 0 && d2 < lo * lo) return false;
        if (hi >= 0 && d2 > hi * hi) return false;
        return true;
    }

    /** 타입 미지정 셀렉터(@e/@n[tag,distance]) 존재검사 — distance 상한 시 Box 한정. */
    public static boolean anyEntityAnyType(GameContext ctx, net.minecraft.entity.Entity origin,
                                           String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return true;
                }
                return false;
            }
            return firstInBox(ctx, origin.getPos(), maxDist,
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)) != null;
        }
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
            if (origin != null && !inRange(origin, e, minDist, maxDist)) continue;
            return true;
        }
        return false;
    }

    /** 타입 미지정 @n[tag,distance] — distance 상한 시 Box 한정 최근접. */
    public static net.minecraft.entity.Entity nearestEntityAnyType(
            GameContext ctx, net.minecraft.entity.Entity origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        net.minecraft.entity.Entity best = null;
        double bestD = Double.MAX_VALUE;
        net.minecraft.util.math.Vec3d o = origin != null ? origin.getPos() : null;
        if (o != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
                    if (!inRange(origin, e, minDist, maxDist)) continue;
                    double d = e.getPos().squaredDistanceTo(o);
                    if (d < bestD) { bestD = d; best = e; }
                }
                return best;
            }
            for (net.minecraft.entity.Entity e : ctx.world.getOtherEntities(null, rangeBox(o, maxDist),
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist))) {
                double d = e.getPos().squaredDistanceTo(o);
                if (d < bestD) { bestD = d; best = e; }
            }
            return best;
        }
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
            if (origin != null && !inRange(origin, e, minDist, maxDist)) continue;
            double d = (o == null) ? 0 : e.getPos().squaredDistanceTo(o);
            if (d < bestD) { bestD = d; best = e; }
        }
        return best;
    }

    /** kill <selector> — 셀렉터 대상 제거. (entities 는 호출부가 순회해 전달) */
    // ── team / name / level 셀렉터 필터 (검증된 yarn 1.21.5 API) ──
    // ── spawnpoint: 플레이어 스폰포인트 설정(Respawn 레코드) ──
    public static void setSpawnPoint(net.minecraft.server.command.ServerCommandSource src,
                                     net.minecraft.entity.Entity e,
                                     net.minecraft.util.math.BlockPos pos, float angle) {
        if (!(e instanceof net.minecraft.server.network.ServerPlayerEntity p)) return;
        net.minecraft.registry.RegistryKey<net.minecraft.world.World> dim = src.getWorld().getRegistryKey();
        p.setSpawnPoint(new net.minecraft.server.network.ServerPlayerEntity.Respawn(dim, pos, angle, false), false);
    }

    // ── enchant: 주손 아이템에 인챈트 부여(레지스트리 엔트리 직접 조회) ──
    public static void enchantHeld(net.minecraft.server.command.ServerCommandSource src,
                                   net.minecraft.entity.Entity e, String enchId, int level) {
        if (!(e instanceof net.minecraft.entity.LivingEntity le)) return;
        net.minecraft.util.Identifier id = net.minecraft.util.Identifier.tryParse(enchId);
        if (id == null) return;
        net.minecraft.registry.Registry<net.minecraft.enchantment.Enchantment> reg =
            src.getWorld().getRegistryManager().getOrThrow(net.minecraft.registry.RegistryKeys.ENCHANTMENT);
        java.util.Optional<? extends net.minecraft.registry.entry.RegistryEntry<net.minecraft.enchantment.Enchantment>> opt =
            reg.getEntry(id);
        if (opt.isEmpty()) return;
        net.minecraft.item.ItemStack stack = le.getMainHandStack();
        if (stack.isEmpty()) return;
        stack.addEnchantment(opt.get(), level);
    }

    // ── place feature: 구성된 피처를 월드에 생성 (registry -> ConfiguredFeature.generate) ──
    public static void placeFeature(net.minecraft.server.command.ServerCommandSource src,
                                    String featureId, net.minecraft.util.math.BlockPos pos) {
        net.minecraft.server.world.ServerWorld w = src.getWorld();
        net.minecraft.util.Identifier id = net.minecraft.util.Identifier.tryParse(featureId);
        if (id == null) return;
        net.minecraft.registry.Registry<net.minecraft.world.gen.feature.ConfiguredFeature<?, ?>> reg =
            w.getRegistryManager().getOrThrow(net.minecraft.registry.RegistryKeys.CONFIGURED_FEATURE);
        net.minecraft.world.gen.feature.ConfiguredFeature<?, ?> feature = reg.get(id);
        if (feature == null) return;
        net.minecraft.world.gen.chunk.ChunkGenerator gen =
            ((net.minecraft.server.world.ServerChunkManager) w.getChunkManager()).getChunkGenerator();
        feature.generate(w, gen, w.getRandom(), pos);
    }

    // ── spreadplayers: 중심 주변에 최소간격 만족 랜덤 산포 후 표면 텔레포트(teleportTo 재사용) ──
    //    RNG 산포라 바닐라와 비트동일은 아니나 관측 계약(범위/간격/표면/팀) 충족.
    public static void spreadPlayers(net.minecraft.server.command.ServerCommandSource src,
                                     double cx, double cz, double spread, double maxRange,
                                     boolean respectTeams, java.util.List<net.minecraft.entity.Entity> targets) {
        if (targets.isEmpty()) return;
        net.minecraft.server.world.ServerWorld w = src.getWorld();
        net.minecraft.util.math.random.Random rnd = w.getRandom();
        java.util.List<java.util.List<net.minecraft.entity.Entity>> piles = new java.util.ArrayList<>();
        if (respectTeams) {
            java.util.Map<String, java.util.List<net.minecraft.entity.Entity>> byTeam = new java.util.LinkedHashMap<>();
            for (net.minecraft.entity.Entity e : targets) {
                net.minecraft.scoreboard.Team t = e.getScoreboardTeam();
                String key = (t == null) ? ("\0solo:" + e.getUuidAsString()) : t.getName();
                byTeam.computeIfAbsent(key, kk -> new java.util.ArrayList<>()).add(e);
            }
            piles.addAll(byTeam.values());
        } else {
            for (net.minecraft.entity.Entity e : targets) {
                java.util.List<net.minecraft.entity.Entity> one = new java.util.ArrayList<>();
                one.add(e);
                piles.add(one);
            }
        }
        int n = piles.size();
        double[] px = new double[n], pz = new double[n];
        for (int i = 0; i < n; i++) {
            px[i] = cx + (rnd.nextDouble() * 2 - 1) * maxRange;
            pz[i] = cz + (rnd.nextDouble() * 2 - 1) * maxRange;
        }
        for (int iter = 0; iter < 200; iter++) {
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double dx = px[j] - px[i], dz = pz[j] - pz[i];
                    double d = Math.sqrt(dx * dx + dz * dz);
                    if (d < spread) {
                        ok = false;
                        double push = (spread - d) / 2.0 + 0.1;
                        double ux = (d < 1e-4) ? (rnd.nextDouble() - 0.5) : dx / d;
                        double uz = (d < 1e-4) ? (rnd.nextDouble() - 0.5) : dz / d;
                        px[i] -= ux * push; pz[i] -= uz * push;
                        px[j] += ux * push; pz[j] += uz * push;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                double dx = px[i] - cx, dz = pz[i] - cz;
                double d = Math.sqrt(dx * dx + dz * dz);
                if (d > maxRange && d > 1e-4) { px[i] = cx + dx / d * maxRange; pz[i] = cz + dz / d * maxRange; }
            }
            if (ok) break;
        }
        for (int i = 0; i < n; i++) {
            int bx = (int) Math.floor(px[i]), bz = (int) Math.floor(pz[i]);
            int ty = w.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING, bx, bz);
            for (net.minecraft.entity.Entity e : piles.get(i)) {
                teleportTo(e, bx + 0.5, ty, bz + 0.5);
            }
        }
    }

    // ── damage: Entity.damage(ServerWorld, DamageSource, amount). 기본 type=generic ──
    public static void applyDamage(net.minecraft.entity.Entity e, net.minecraft.server.world.ServerWorld w,
                                   float amount, String typeId) {
        net.minecraft.entity.damage.DamageSource ds;
        if (typeId == null) {
            ds = w.getDamageSources().generic();
        } else {
            net.minecraft.util.Identifier id = net.minecraft.util.Identifier.tryParse(typeId);
            ds = (id == null) ? w.getDamageSources().generic()
                 : w.getDamageSources().create(net.minecraft.registry.RegistryKey.of(
                       net.minecraft.registry.RegistryKeys.DAMAGE_TYPE, id));
        }
        QUERY_MUT++;   // 피해로 즉사 가능(isAlive 변화) → 존재검사 메모 무효화
        RIDE_MUT++;    // 즉사 시 하차 동반 가능 → 탑승 정렬 캐시 무효화
        e.damage(w, ds, amount);
    }

    /** damage ... by <attacker>: DamageSources.create(key, attacker) — DamageCommand 동일. */
    public static void applyDamageBy(net.minecraft.entity.Entity e, net.minecraft.server.world.ServerWorld w,
                                     float amount, String typeId, net.minecraft.entity.Entity attacker) {
        net.minecraft.util.Identifier id = (typeId == null ? null : net.minecraft.util.Identifier.tryParse(typeId));
        net.minecraft.entity.damage.DamageSource ds = (id == null)
                ? w.getDamageSources().generic()
                : w.getDamageSources().create(net.minecraft.registry.RegistryKey.of(
                        net.minecraft.registry.RegistryKeys.DAMAGE_TYPE, id), attacker);
        QUERY_MUT++;   // 피해로 즉사 가능 → 존재검사 메모 무효화
        RIDE_MUT++;    // 즉사 시 하차 동반 가능 → 탑승 정렬 캐시 무효화
        e.damage(w, ds, amount);
    }

    // ── trigger: @s 의 트리거 목표 점수. isLocked/lock 으로 enable->1회->lock 시맨틱 재현 ──
    public static void triggerScore(net.minecraft.server.command.ServerCommandSource src,
                                    String objName, int mode, int value) {
        if (!(src.getEntity() instanceof net.minecraft.server.network.ServerPlayerEntity p)) return;
        net.minecraft.scoreboard.ServerScoreboard sb = src.getServer().getScoreboard();
        net.minecraft.scoreboard.ScoreboardObjective obj = sb.getNullableObjective(objName);
        if (obj == null) return;
        net.minecraft.scoreboard.ScoreAccess acc = sb.getOrCreateScore(p, obj);
        if (acc.isLocked()) return;              // 비활성(enable 안 됨) -> 무시
        if (mode == 2) acc.setScore(value); else acc.incrementScore(value);
        acc.lock();                              // 트리거 후 잠금
    }

    // ── schedule: 바닐라와 동일한 월드 함수 타이머에 등록/제거 (interop 보장) ──
    private static net.minecraft.world.timer.Timer<net.minecraft.server.MinecraftServer> _fnTimer(
            net.minecraft.server.command.ServerCommandSource src) {
        return src.getServer().getSaveProperties().getMainWorldProperties().getScheduledEvents();
    }
    public static void scheduleFunction(net.minecraft.server.command.ServerCommandSource src,
                                        String fnId, long delayTicks, boolean append) {
        net.minecraft.util.Identifier id = net.minecraft.util.Identifier.tryParse(fnId);
        if (id == null) return;
        long trigger = src.getServer().getOverworld().getTime() + delayTicks;
        net.minecraft.world.timer.Timer<net.minecraft.server.MinecraftServer> timer = _fnTimer(src);
        if (!append) { timer.remove(fnId); schedNativeRemove(fnId); }   // replace 는 양쪽 큐 모두 대체
        timer.setEvent(fnId, trigger, new net.minecraft.world.timer.FunctionTimerCallback(id));
    }
    public static void scheduleClear(net.minecraft.server.command.ServerCommandSource src, String fnId) {
        _fnTimer(src).remove(fnId);
        schedNativeRemove(fnId);   // 네이티브 큐에 있어도 clear 시맨틱 유지
    }

    // ── 네이티브 스케줄러(#15): 변환된 함수 대상 schedule 을 바닐라 타이머(리플렉션 콜백 →
    //    mcfunction 인터프리트) 대신 자체 큐 + 네이티브 호출로 디스패치한다.
    //    발화 시점: 진입점 START_SERVER_TICK 의 tick 함수 디스패치 '직후'(= tick 함수 → 스케줄
    //    함수 순서 — 바닐라의 #tick 함수 → 월드 타이머 순서와 상대 순서 동일).
    //    소스: 바닐라 FunctionTimerCallback 과 동일(server.getCommandSource().withLevel(2).withSilent()).
    //    참고(계약 편차·문서화): 바닐라 타이머는 level.dat 에 영속돼 서버 재시작 후 복원되지만
    //    네이티브 큐는 메모리 상주라 재시작 시 소실된다(수 틱 단위 연쇄 스케줄 용도에선 무영향).
    private static final class SchedEntry {
        final long due, seq; final String key;
        final java.util.function.Consumer<net.minecraft.server.command.ServerCommandSource> fn;
        boolean cancelled;
        SchedEntry(long due, long seq, String key,
                   java.util.function.Consumer<net.minecraft.server.command.ServerCommandSource> fn) {
            this.due = due; this.seq = seq; this.key = key; this.fn = fn;
        }
    }
    private static final java.util.PriorityQueue<SchedEntry> SCHED_Q = new java.util.PriorityQueue<>(
            (a, b) -> a.due != b.due ? Long.compare(a.due, b.due) : Long.compare(a.seq, b.seq));
    private static final java.util.HashMap<String, java.util.ArrayList<SchedEntry>> SCHED_BY_KEY =
            new java.util.HashMap<>();
    private static long SCHED_NOW = 0, SCHED_SEQ = 0;

    private static void schedNativeRemove(String key) {
        java.util.ArrayList<SchedEntry> l = SCHED_BY_KEY.remove(key);
        if (l != null) for (SchedEntry e : l) e.cancelled = true;   // PQ 에선 지연 제거
    }

    /** schedule function <fnId>(변환된 네이티브 함수) — emit 이 대상 함수의 메서드 참조를 넘긴다. */
    public static void scheduleNative(net.minecraft.server.command.ServerCommandSource src,
                                      String fnId, long delayTicks, boolean append,
                                      java.util.function.Consumer<net.minecraft.server.command.ServerCommandSource> fn) {
        if (!append) { schedNativeRemove(fnId); _fnTimer(src).remove(fnId); }   // replace: 양쪽 큐 대체
        SchedEntry e = new SchedEntry(SCHED_NOW + Math.max(delayTicks, 0L), SCHED_SEQ++, fnId, fn);
        SCHED_Q.add(e);
        SCHED_BY_KEY.computeIfAbsent(fnId, k -> new java.util.ArrayList<>(2)).add(e);
    }

    /** 진입점이 매 틱(tick 함수 디스패치 직후) 호출. 같은 틱 재-스케줄은 다음 틱 발화(바닐라 동일). */
    public static void tickNativeSchedule(net.minecraft.server.MinecraftServer server) {
        SCHED_NOW++;
        if (SCHED_Q.isEmpty()) return;
        java.util.ArrayList<SchedEntry> due = null;
        while (!SCHED_Q.isEmpty() && SCHED_Q.peek().due <= SCHED_NOW) {
            SchedEntry e = SCHED_Q.poll();
            if (e.cancelled) continue;
            java.util.ArrayList<SchedEntry> l = SCHED_BY_KEY.get(e.key);
            if (l != null) { l.remove(e); if (l.isEmpty()) SCHED_BY_KEY.remove(e.key); }
            if (due == null) due = new java.util.ArrayList<>(4);
            due.add(e);
        }
        if (due == null) return;
        // 발화 목록을 먼저 확정한 뒤 실행 — 실행 중 재스케줄(0~1t 연쇄)은 다음 틱으로(바닐라 동일).
        net.minecraft.server.command.ServerCommandSource src =
                server.getCommandSource().withLevel(2).withSilent();   // 바닐라 getScheduledCommandSource 동일
        for (SchedEntry e : due) {
            try { e.fn.accept(src); }
            catch (Exception ex) { System.err.println("[KFC-SCHED] " + e.key + ": " + ex); }
        }
    }

    // ── give: 아이템 지급(스택 한도 초과 시 분할, 오버플로우는 giveItemStack 이 드롭) ──
    public static void giveItem(net.minecraft.server.network.ServerPlayerEntity p, String itemId, int count) {
        net.minecraft.item.Item item =
            net.minecraft.registry.Registries.ITEM.get(idOf(itemId));
        if (item == net.minecraft.item.Items.AIR) return;
        int max = new net.minecraft.item.ItemStack(item).getMaxCount();
        if (max < 1) max = 1;
        while (count > 0) {
            int n = Math.min(count, max);
            count -= n;
            p.giveItemStack(new net.minecraft.item.ItemStack(item, n));
        }
    }

    /** give <id>[components] — 컴포넌트/데이터 동반 아이템 지급. 기존 parseItemStack(캐시) 재사용.
     *  스택 한도 초과 시 분할, 파싱 실패 시 무동작(브릿지 폴백이 별도 처리). */
    public static void giveItemString(net.minecraft.server.command.ServerCommandSource source,
                                      net.minecraft.server.network.ServerPlayerEntity p,
                                      String itemString, int count) {
        if (p == null || itemString == null) return;
        try {
            net.minecraft.server.MinecraftServer server = source.getServer();
            net.minecraft.item.ItemStack base = parseItemStack(server, itemString, 1);
            if (base == null || base.isEmpty()) return;
            int max = base.getMaxCount();
            if (max < 1) max = 1;
            while (count > 0) {
                int n = Math.min(count, max);
                count -= n;
                net.minecraft.item.ItemStack s = parseItemStack(server, itemString, n);
                if (s != null && !s.isEmpty()) p.giveItemStack(s);
            }
        } catch (Exception ex) {
            // 무동작
        }
    }

    // ── gamerule: 전체 키를 런타임 수집(accept) 후 타입별 set. 하드코딩 없이 모든 룰 대응 ──
    private static java.util.Map<String, net.minecraft.world.GameRules.Key<?>> _GR_KEYS;
    private static void _ensureGameRuleKeys(net.minecraft.world.GameRules rules) {
        if (_GR_KEYS != null) return;
        java.util.Map<String, net.minecraft.world.GameRules.Key<?>> m = new java.util.HashMap<>();
        rules.accept(new net.minecraft.world.GameRules.Visitor() {
            @Override
            public <T extends net.minecraft.world.GameRules.Rule<T>> void visit(
                    net.minecraft.world.GameRules.Key<T> key, net.minecraft.world.GameRules.Type<T> type) {
                m.put(key.getName(), key);
            }
        });
        _GR_KEYS = m;
    }
    public static void setGameRule(net.minecraft.server.command.ServerCommandSource src,
                                   String rule, String value) {
        net.minecraft.server.MinecraftServer server = src.getServer();
        net.minecraft.world.GameRules rules = server.getGameRules();
        _ensureGameRuleKeys(rules);
        net.minecraft.world.GameRules.Key<?> key = _GR_KEYS.get(rule);
        if (key == null) return;
        net.minecraft.world.GameRules.Rule<?> r = rules.get(key);
        if (r instanceof net.minecraft.world.GameRules.BooleanRule b) {
            b.set(Boolean.parseBoolean(value), server);
        } else if (r instanceof net.minecraft.world.GameRules.IntRule i) {
            try { i.set(Integer.parseInt(value.trim()), server); } catch (NumberFormatException ignored) {}
        }
    }

    public static boolean teamIs(net.minecraft.entity.Entity e, String team, boolean inverted) {
        net.minecraft.scoreboard.Team t = e.getScoreboardTeam();
        String tn = (t == null) ? null : t.getName();
        boolean match = team.isEmpty() ? (tn == null) : team.equals(tn);
        return match != inverted;
    }
    public static boolean nameIs(net.minecraft.entity.Entity e, String name, boolean inverted) {
        boolean match = e.getName().getString().equals(name);
        return match != inverted;
    }
    // ── advancements 셀렉터: 발전과제 트래커 직접 API (NBT 아님) ──
    private static net.minecraft.advancement.AdvancementProgress _advProgress(
            net.minecraft.server.command.ServerCommandSource src,
            net.minecraft.entity.Entity e, String advId) {
        if (!(e instanceof net.minecraft.server.network.ServerPlayerEntity p)) return null;
        net.minecraft.util.Identifier id = net.minecraft.util.Identifier.tryParse(advId);
        if (id == null) return null;
        net.minecraft.advancement.AdvancementEntry entry = src.getServer().getAdvancementLoader().get(id);
        if (entry == null) return null;
        return p.getAdvancementTracker().getProgress(entry);
    }
    public static boolean advancementDone(net.minecraft.server.command.ServerCommandSource src,
            net.minecraft.entity.Entity e, String advId, boolean expected) {
        net.minecraft.advancement.AdvancementProgress prog = _advProgress(src, e, advId);
        if (prog == null) return false;
        return prog.isDone() == expected;
    }
    public static boolean advancementCriterion(net.minecraft.server.command.ServerCommandSource src,
            net.minecraft.entity.Entity e, String advId, String crit, boolean expected) {
        net.minecraft.advancement.AdvancementProgress prog = _advProgress(src, e, advId);
        if (prog == null) return false;
        net.minecraft.advancement.criterion.CriterionProgress cp = prog.getCriterionProgress(crit);
        boolean obtained = (cp != null && cp.isObtained());
        return obtained == expected;
    }

    // ── nbt 셀렉터: 바닐라 EntitySelectorOptions.nbt 와 동일한 NBT 부분매칭 ──
    //    (이 기능은 본질적으로 NBT 질의이므로 NbtPredicate/NbtHelper 가 충실한 구현)
    // 기대 NBT(리터럴 문자열)는 매 호출 동일하므로 파싱 결과를 캐시 — StringNbtReader 반복 파싱 제거.
    // 비교 입력으로만 읽기 전용 사용(NbtHelper.matches 는 expected 를 변형하지 않음)하므로 안전.
    private static final java.util.Map<String, net.minecraft.nbt.NbtCompound> NBT_EXPECT_CACHE =
            new java.util.HashMap<>();
    public static boolean nbtMatches(net.minecraft.entity.Entity e, String expectedNbt, boolean inverted) {
        boolean match;
        try {
            net.minecraft.nbt.NbtCompound expected = NBT_EXPECT_CACHE.get(expectedNbt);
            if (expected == null) {
                expected = net.minecraft.nbt.StringNbtReader.readCompound(expectedNbt);
                NBT_EXPECT_CACHE.put(expectedNbt, expected);
            }
            net.minecraft.nbt.NbtCompound actual =
                net.minecraft.predicate.NbtPredicate.entityToNbt(e);
            match = net.minecraft.nbt.NbtHelper.matches(expected, actual, true);
        } catch (Exception ex) {
            match = false;
        }
        return match != inverted;
    }

    public static boolean levelInRange(net.minecraft.entity.Entity e, int lo, int hi) {
        if (!(e instanceof net.minecraft.entity.player.PlayerEntity p)) return false;
        int lv = p.experienceLevel;
        return lv >= lo && lv <= hi;
    }

    /** x_rotation/y_rotation 술어 - 바닐라 EntitySelectorReader.getRotationPredicate 1:1 포팅.
     *  엔티티 각도와 경계 both wrapDegrees([-180,180)) 정규화 후 비교하고, 무한계 쪽은
     *  바닐라 기본값(min=0 / max=359)을 채운다. min>max(래핑)은 wrap 후 d>e 로 OR 판정.
     *  과거 rotation_conds 는 getYaw() 원값을 직접 비교해 누적 yaw(>180도)/단측 범위에서
     *  결과 집합이 어긋났다(예: y_rotation=45.. 를 반직선으로 처리). */
    public static boolean rotInRange(double angle, boolean hasMin, double min, boolean hasMax, double max) {
        double d = net.minecraft.util.math.MathHelper.wrapDegrees(hasMin ? min : 0.0);
        double e = net.minecraft.util.math.MathHelper.wrapDegrees(hasMax ? max : 359.0);
        double f = net.minecraft.util.math.MathHelper.wrapDegrees(angle);
        return d > e ? (f >= d || f <= e) : (f >= d && f <= e);
    }

    public static void killEntity(net.minecraft.entity.Entity e) {
        if (e == null) return;
        // 바닐라 KillCommand 는 대상 전원(플레이어 포함)에 entity.kill(world) 를 호출한다.
        //   - LivingEntity.kill : damage(world, genericKill(), MAX) → 실제 사망 처리
        //                         (드롭 / 사망 사운드·파티클 / advancement / "~가 죽었습니다" / death event)
        //   - Entity.kill(기본) : remove(RemovalReason.KILLED) + ENTITY_DIE 게임이벤트
        // 기존 discard() 는 단순 despawn(사망 이벤트·드롭 없음)이고 플레이어를 통째로 무시해
        // 바닐라와 시맨틱이 달랐다.
        if (!(e.getWorld() instanceof net.minecraft.server.world.ServerWorld sw)) return;
        QUERY_MUT++;   // 생사 변화(생물은 제거 지연이라 ENTITY_GEN 이 안 오름) → 메모 무효화
        RIDE_MUT++;    // 사망은 하차를 동반할 수 있음 → 탑승 정렬 캐시 무효화
        e.kill(sw);
        // 생물은 death 처리(실제 제거는 이후) → death 프레임 동안 셀렉터에 잡히는 바닐라 동작을
        // 위해 스냅샷을 건드리지 않고 틱 경계 재수집(entitiesSnapshot)에 맡긴다.
        if (e instanceof net.minecraft.entity.LivingEntity) return;
        // 비생물은 remove(KILLED) 로 즉시 제거되므로 스냅샷/버킷에서 즉시 뺀다.
        snapRemove(e);
    }

    // ── time / weather (월드 상태) ──
    public static void setTime(GameContext ctx, long value) {
        ctx.world.setTimeOfDay(value);
    }
    public static void addTime(GameContext ctx, long delta) {
        ctx.world.setTimeOfDay(ctx.world.getTimeOfDay() + delta);
    }
    public static void setWeather(GameContext ctx, int clearTicks, int rainTicks,
                                  boolean raining, boolean thundering) {
        ctx.world.setWeather(clearTicks, rainTicks, raining, thundering);
    }

    // ── difficulty / defaultgamemode (서버 전역) ──
    public static void setDifficulty(net.minecraft.server.MinecraftServer server, String name) {
        net.minecraft.world.Difficulty d = net.minecraft.world.Difficulty.byName(name);
        if (d == null) return;               // 잘못된 이름 - 바닐라처럼 무동작
        server.setDifficulty(d, true);       // forceUpdate=true
    }
    public static void setDefaultGameMode(net.minecraft.server.MinecraftServer server, String mode) {
        net.minecraft.world.GameMode gm;
        switch (mode) {
            case "creative":  gm = net.minecraft.world.GameMode.CREATIVE;  break;
            case "adventure": gm = net.minecraft.world.GameMode.ADVENTURE; break;
            case "spectator": gm = net.minecraft.world.GameMode.SPECTATOR; break;
            default:          gm = net.minecraft.world.GameMode.SURVIVAL;  break;
        }
        server.setDefaultGameMode(gm);
    }

    // ── random roll: 범위 내 난수를 굴려 결과를 브로드캐스트(바닐라 동작 근사) ──
    public static int randomRoll(GameContext ctx, net.minecraft.server.command.ServerCommandSource source,
                                 int min, int max) {
        int v = ctx.world.getRandom().nextBetween(min, max);
        net.minecraft.text.Text msg = net.minecraft.text.Text.literal(
                source.getName() + " rolled " + v + " (from " + min + " to " + max + ")");
        source.getServer().getPlayerManager().broadcast(msg, false);
        return v;
    }

    // ── recipe give/take: 레시피 잠금해제/잠금 (recipeId==null 이면 '*' 전체) ──
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void recipe(net.minecraft.server.MinecraftServer server,
                              net.minecraft.server.network.ServerPlayerEntity p,
                              String recipeId, boolean unlock) {
        net.minecraft.recipe.ServerRecipeManager rm = server.getRecipeManager();
        if (recipeId == null) {              // '*' : 전체 레시피
            java.util.Collection all = rm.values();
            if (unlock) p.unlockRecipes(all); else p.lockRecipes(all);
            return;
        }
        net.minecraft.util.Identifier id = net.minecraft.util.Identifier.tryParse(recipeId);
        if (id == null) return;
        net.minecraft.registry.RegistryKey key =
                net.minecraft.registry.RegistryKey.of(net.minecraft.registry.RegistryKeys.RECIPE, id);
        java.util.Optional e = rm.get(key);
        if (e.isEmpty()) return;
        java.util.Collection one = java.util.List.of(e.get());
        if (unlock) p.unlockRecipes(one); else p.lockRecipes(one);
    }

    // ── gamemode (플레이어 한정) ──
    // ── dx/dy/dz 박스 셀렉터 ──
    private static boolean kfcTypeIn(net.minecraft.entity.Entity e,
                                     net.minecraft.entity.EntityType<?>[] types) {
        if (types == null) return true;
        for (net.minecraft.entity.EntityType<?> t : types) if (e.getType() == t) return true;
        return false;
    }
    public static java.util.List<net.minecraft.entity.Entity> allEntitiesInBox(
            GameContext ctx, net.minecraft.util.math.Vec3d o,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double dx, double dy, double dz) {
        double x1 = Math.min(o.x, o.x + dx), x2 = Math.max(o.x, o.x + dx) + 1;
        double y1 = Math.min(o.y, o.y + dy), y2 = Math.max(o.y, o.y + dy) + 1;
        double z1 = Math.min(o.z, o.z + dz), z2 = Math.max(o.z, o.z + dz) + 1;
        net.minecraft.util.math.Box box = new net.minecraft.util.math.Box(x1, y1, z1, x2, y2, z2);
        // 21차(바닐라 초월): 후보 = 양성 태그 버킷(있으면) 아니면 틱 스냅샷 — 섹션 스캔 제거.
        // 판정식은 종전과 동일(박스∩바운딩박스 = getOtherEntities 포함 조건 + 동일 술어).
        if (QUERY_IDX) {
            java.util.List<net.minecraft.entity.Entity> cand = tagOrSnap(ctx, tagsPos);
            java.util.ArrayList<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
            for (int i = 0, n = cand.size(); i < n; i++) {
                net.minecraft.entity.Entity en = cand.get(i);
                if (box.intersects(en.getBoundingBox())
                        && matchTagsAlive(en, tagsPos, tagsNeg) && kfcTypeIn(en, types)) out.add(en);
            }
            return out;
        }
        return ctx.world.getOtherEntities(null, box,
                en -> matchTagsAlive(en, tagsPos, tagsNeg) && kfcTypeIn(en, types));
    }
    public static boolean posInBox(net.minecraft.util.math.Vec3d o, double dx, double dy, double dz,
                                   net.minecraft.util.math.Vec3d p) {
        double x1 = Math.min(o.x, o.x + dx), x2 = Math.max(o.x, o.x + dx) + 1;
        double y1 = Math.min(o.y, o.y + dy), y2 = Math.max(o.y, o.y + dy) + 1;
        double z1 = Math.min(o.z, o.z + dz), z2 = Math.max(o.z, o.z + dz) + 1;
        return p.x >= x1 && p.x < x2 && p.y >= y1 && p.y < y2 && p.z >= z1 && p.z < z2;
    }
    /** x,y,z,dx,dy,dz 볼륨 셀렉터 매칭. 바닐라는 발 좌표(점)가 아니라 **엔티티 히트박스가
     *  볼륨 박스와 교차**하는지로 판정한다(EntitySelectorReader: box.intersects(getBoundingBox)).
     *  점 기준이면 카트 탑승 등으로 발 y 가 박스 하한 아래로 살짝 내려갈 때 오탈락한다(방장 인식 버그).
     *  박스 지오메트리(min, max+1)는 anyEntityInBox/allEntitiesInBox(getOtherEntities)와 동일. */
    public static boolean posInBox(net.minecraft.util.math.Vec3d o, double dx, double dy, double dz,
                                   net.minecraft.entity.Entity e) {
        if (e == null) return false;
        double x1 = Math.min(o.x, o.x + dx), x2 = Math.max(o.x, o.x + dx) + 1;
        double y1 = Math.min(o.y, o.y + dy), y2 = Math.max(o.y, o.y + dy) + 1;
        double z1 = Math.min(o.z, o.z + dz), z2 = Math.max(o.z, o.z + dz) + 1;
        return new net.minecraft.util.math.Box(x1, y1, z1, x2, y2, z2)
                .intersects(e.getBoundingBox());
    }
    public static boolean anyEntityInBox(GameContext ctx, net.minecraft.util.math.Vec3d o,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double dx, double dy, double dz) {
        // 21차: 존재검사는 첫 매치에서 단락 — 결과 리스트 할당/전체 수집 제거(집합 판정 동일).
        if (QUERY_IDX) {
            double x1 = Math.min(o.x, o.x + dx), x2 = Math.max(o.x, o.x + dx) + 1;
            double y1 = Math.min(o.y, o.y + dy), y2 = Math.max(o.y, o.y + dy) + 1;
            double z1 = Math.min(o.z, o.z + dz), z2 = Math.max(o.z, o.z + dz) + 1;
            net.minecraft.util.math.Box box = new net.minecraft.util.math.Box(x1, y1, z1, x2, y2, z2);
            java.util.List<net.minecraft.entity.Entity> cand = tagOrSnap(ctx, tagsPos);
            for (int i = 0, n = cand.size(); i < n; i++) {
                net.minecraft.entity.Entity en = cand.get(i);
                if (box.intersects(en.getBoundingBox())
                        && matchTagsAlive(en, tagsPos, tagsNeg) && kfcTypeIn(en, types)) return true;
            }
            return false;
        }
        return !allEntitiesInBox(ctx, o, types, tagsPos, tagsNeg, dx, dy, dz).isEmpty();
    }

    public static void setGameMode(net.minecraft.entity.Entity e, String mode) {
        if (!(e instanceof net.minecraft.server.network.ServerPlayerEntity p)) return;
        net.minecraft.world.GameMode gm;
        switch (mode) {
            case "creative":  gm = net.minecraft.world.GameMode.CREATIVE;  break;
            case "adventure": gm = net.minecraft.world.GameMode.ADVENTURE; break;
            case "spectator": gm = net.minecraft.world.GameMode.SPECTATOR; break;
            default:          gm = net.minecraft.world.GameMode.SURVIVAL;  break;
        }
        p.changeGameMode(gm);
    }

    /** playsound <sound> <source> <targets> <pos> <vol> <pitch> — 플레이어별 재생. */
    // 좌표식이 이미 Vec3d 인 경우의 오버로드. emit 이 cond_pos_expr 결과(Vec3d/localOffset)를
    // .x/.y/.z 로 분해해 넘기면 호출당 좌표식이 3번 평가되어, new Vec3d 가 3개 할당되거나
    // localOffset(삼각함수)이 3번 돌았다. playSound 는 이 팩 최다 호출(~33만)이라 그 왕복이
    // 큰 GC/CPU 비용. Vec3d 를 1회만 받아 컴포넌트를 읽어 동일 패킷을 보낸다. 값/순서 동일.
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 String soundId, String category,
                                 net.minecraft.util.math.Vec3d pos, float vol, float pitch) {
        if (p == null || pos == null) return;
        playSound(p, soundId, category, pos.x, pos.y, pos.z, vol, pitch);
    }

    /** String 판 minVolume 오버로드 — 조회 캐시 후 완전 미러 코어로 위임. */
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 String soundId, String category,
                                 net.minecraft.util.math.Vec3d pos, float vol, float pitch, float minVol) {
        if (p == null || pos == null) return;
        playSound(p, soundEventCached(soundId), soundCatCached(category),
                  pos.x, pos.y, pos.z, vol, pitch, minVol, p.getWorld().getRandom().nextLong());
    }
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 String soundId, String category,
                                 net.minecraft.util.math.Vec3d pos, float vol, float pitch, float minVol, long seed) {
        if (p == null || pos == null) return;
        playSound(p, soundEventCached(soundId), soundCatCached(category),
                  pos.x, pos.y, pos.z, vol, pitch, minVol, seed);
    }

    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 String soundId, String category,
                                 double x, double y, double z, float vol, float pitch) {
        if (p == null) return;
        // minVolume 0 = 종전 시그니처 시맨틱(범위 밖 미재생은 클라 감쇠와 가청 동일, 패킷만 절약)
        playSound(p, soundEventCached(soundId), soundCatCached(category),
                  x, y, z, vol, pitch, 0.0f, p.getWorld().getRandom().nextLong());
    }

    private static net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent>
            soundEventCached(String soundId) {
        return SOUND_CACHE.computeIfAbsent(soundId, sid -> {
            net.minecraft.util.Identifier id = idOf(
                    sid.contains(":") ? sid : "minecraft:" + sid);
            net.minecraft.sound.SoundEvent e = net.minecraft.registry.Registries.SOUND_EVENT.get(id);
            // RegistryEntry 래핑까지 캐시한다 — RegistryEntry.of(ev) 를 매 호출(이 팩 ~33만회)
            // 새로 할당하던 비용 제거. RegistryEntry/SoundEvent 모두 불변이라 공유 안전.
            return net.minecraft.registry.entry.RegistryEntry.of(
                    e != null ? e : net.minecraft.sound.SoundEvent.of(id));  // 미등록도 id 그대로 재생(바닐라 허용)
        });
    }

    private static net.minecraft.sound.SoundCategory soundCatCached(String category) {
        return SOUND_CAT_CACHE.computeIfAbsent(category, c -> {
            // /playsound 의 source 인자명(record/block/player)은 SoundCategory enum 상수명과 다르다
            // — 바닐라처럼 getName()(=인자명)으로 매칭, 미매칭 = MASTER.
            for (net.minecraft.sound.SoundCategory sc : net.minecraft.sound.SoundCategory.values()) {
                if (sc.getName().equalsIgnoreCase(c)) return sc;
            }
            return net.minecraft.sound.SoundCategory.MASTER;
        });
    }

    // ════════════════════════════════════════════════════════════════
    //  [static-final 승격용] playSound 의 사운드/카테고리 리터럴 사전 파싱 + pre-parsed 오버로드.
    //  merge_pass pass-4 가 변환 시점 상수 soundId/category 를 클래스 static final 필드로 승격하고
    //  호출부를 아래 오버로드로 재작성 → 호출당 SOUND_CACHE/SOUND_CAT_CACHE 조회 + RegistryEntry.of
    //  재할당 제거(이 팩 ~34만 호출). RegistryEntry/SoundEvent/SoundCategory 모두 불변 → 공유 안전.
    //  파싱 시점: 버킷 클래스는 커맨드 실행(서버 시작 후)에 최초 로드 → SOUND_EVENT 레지스트리
    //  frozen 상태라 안전. 로직은 SOUND_CACHE/SOUND_CAT_CACHE 람다와 완전 동일.
    // ════════════════════════════════════════════════════════════════
    public static net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> soundEvent(String soundId) {
        net.minecraft.util.Identifier id = idOf(soundId.contains(":") ? soundId : "minecraft:" + soundId);
        net.minecraft.sound.SoundEvent e = net.minecraft.registry.Registries.SOUND_EVENT.get(id);
        // 미등록 id 도 그대로 재생(바닐라 허용) — RegistryEntry 래핑까지 1회.
        return net.minecraft.registry.entry.RegistryEntry.of(
                e != null ? e : net.minecraft.sound.SoundEvent.of(id));
    }

    public static net.minecraft.sound.SoundCategory soundCat(String category) {
        // /playsound source 인자명(record/block/player)을 getName 으로 매칭(바닐라 동일), 미매칭=MASTER.
        for (net.minecraft.sound.SoundCategory sc : net.minecraft.sound.SoundCategory.values()) {
            if (sc.getName().equalsIgnoreCase(category)) return sc;
        }
        return net.minecraft.sound.SoundCategory.MASTER;
    }

    /** pre-parsed 오버로드(Vec3d) — 승격된 사운드/카테고리 필드를 받아 String 판과 동일 동작. */
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> ev,
                                 net.minecraft.sound.SoundCategory cat,
                                 net.minecraft.util.math.Vec3d pos, float vol, float pitch) {
        if (p == null || pos == null) return;
        playSound(p, ev, cat, pos.x, pos.y, pos.z, vol, pitch);
    }

    /** pre-parsed 오버로드(x,y,z) — minVolume 없는 종전 시그니처(= minVolume 0). */
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> ev,
                                 net.minecraft.sound.SoundCategory cat,
                                 double x, double y, double z, float vol, float pitch) {
        if (p == null) return;
        playSound(p, ev, cat, x, y, z, vol, pitch, 0.0f, p.getWorld().getRandom().nextLong());
    }

    // ── /playsound 완전 미러 (PlaySoundCommand.execute 와 동일) ──
    // 가청 반경 = (vol > 1 ? vol*16 : 16). 범위 안: 지정 좌표·지정 볼륨으로 재생.
    // 범위 밖: minVolume ≤ 0 이면 미재생(패킷 자체를 안 보냄 — 바닐라 동일, 종전의
    // '무조건 전송'도 클라이언트 감쇠로 가청 결과는 같았으나 이제 패킷도 절약),
    // minVolume > 0 이면 '플레이어 위치 + 소리방향 단위벡터×2' 지점에서 minVolume 으로 재생.
    // seed: 바닐라는 명령 실행 1회당 난수 시드 1개를 전 대상에 공유(랜덤 변형 동일) —
    // 다중 대상 루프는 emit 이 시드를 루프 밖에서 1회 뽑아 전달한다.
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> ev,
                                 net.minecraft.sound.SoundCategory cat,
                                 net.minecraft.util.math.Vec3d pos, float vol, float pitch, float minVol) {
        if (p == null || pos == null) return;
        playSound(p, ev, cat, pos.x, pos.y, pos.z, vol, pitch, minVol, p.getWorld().getRandom().nextLong());
    }
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> ev,
                                 net.minecraft.sound.SoundCategory cat,
                                 net.minecraft.util.math.Vec3d pos, float vol, float pitch, float minVol, long seed) {
        if (p == null || pos == null) return;
        playSound(p, ev, cat, pos.x, pos.y, pos.z, vol, pitch, minVol, seed);
    }
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> ev,
                                 net.minecraft.sound.SoundCategory cat,
                                 double x, double y, double z, float vol, float pitch, float minVol) {
        if (p == null) return;
        playSound(p, ev, cat, x, y, z, vol, pitch, minVol, p.getWorld().getRandom().nextLong());
    }
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> ev,
                                 net.minecraft.sound.SoundCategory cat,
                                 double x, double y, double z, float vol, float pitch, float minVol, long seed) {
        if (p == null) return;
        double lim = vol > 1.0f ? (double) (vol * 16.0f) : 16.0;
        double dx = x - p.getX(), dy = y - p.getY(), dz = z - p.getZ();
        double d2 = dx * dx + dy * dy + dz * dz;
        double sx = x, sy = y, sz = z;
        float v = vol;
        if (d2 > lim * lim) {
            if (minVol <= 0.0f) return;             // 바닐라: 범위 밖 + minVolume 0 = 미재생
            double dist = Math.sqrt(d2);
            sx = p.getX() + dx / dist * 2.0;        // 플레이어 위치 + 소리방향×2 (바닐라 동일)
            sy = p.getY() + dy / dist * 2.0;
            sz = p.getZ() + dz / dist * 2.0;
            v = minVol;
        }
        p.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket(
                ev, cat, sx, sy, sz, v, pitch, seed));
    }

    /** on vehicle — source 의 엔티티를 그 탈것으로 재바인딩(없으면 null 소스 표식). */
    public static net.minecraft.server.command.ServerCommandSource onVehicle(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (e == null || e.getVehicle() == null) return null;
        // 바닐라 on 은 실행자(엔티티)만 교체하고 위치/회전/차원은 유지한다(위치 변경은 뒤의 at @s 가 담당).
        return src.withEntity(e.getVehicle());
    }

    // execute on owner — 실행자가 Ownable(투사체/길들인 동물 등)이면 소유자로 교체(ExecuteCommand 동일).
    public static net.minecraft.server.command.ServerCommandSource onOwner(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (!(e instanceof net.minecraft.entity.Ownable ow)) return null;
        net.minecraft.entity.Entity o = ow.getOwner();
        return o == null ? null : src.withEntity(o);
    }

    // execute on attacker — 실행자가 Attackable 이면 마지막 공격자(LivingEntity)로 교체, 없으면 null(미실행).
    public static net.minecraft.server.command.ServerCommandSource onAttacker(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (!(e instanceof net.minecraft.entity.Attackable a)) return null;
        net.minecraft.entity.LivingEntity la = a.getLastAttacker();
        return la == null ? null : src.withEntity(la);
    }

    // execute on target — 실행자가 Targeter 이면 현재 타깃(LivingEntity)으로 교체, 없으면 null(미실행).
    public static net.minecraft.server.command.ServerCommandSource onTarget(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (!(e instanceof net.minecraft.entity.Targeter t)) return null;
        net.minecraft.entity.LivingEntity tg = t.getTarget();
        return tg == null ? null : src.withEntity(tg);
    }

    // execute on origin — 실행자가 Ownable(투사체/소환물 등)이면 그 소유자(던진/소환한 주체)로 교체.
    //   예: ender_pearl 의 origin = 펄을 던진 플레이어. 없으면 null(미실행) — 바닐라와 동일.
    public static net.minecraft.server.command.ServerCommandSource onOrigin(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (!(e instanceof net.minecraft.entity.Ownable o)) return null;
        net.minecraft.entity.Entity owner = o.getOwner();
        return owner == null ? null : src.withEntity(owner);
    }

    // execute on controller — 탈것의 '조종하는 승객'(첫 승객)으로 교체, 없으면 null(미실행).
    public static net.minecraft.server.command.ServerCommandSource onController(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (e == null) return null;
        net.minecraft.entity.Entity c = e.getControllingPassenger();
        return c == null ? null : src.withEntity(c);
    }

    /** on passengers — source 엔티티의 승객 목록(엔티티만 교체, 위치/회전 유지). */
    // ── on passengers 순회 결과 캐시(단일 슬롯) ──
    // 데이터팩의 `execute on passengers run ...` 은 줄마다 독립이라, move 처럼 한 함수가 같은
    // source 로 on-passengers 를 6회 반복하면 매번 승객 리스트 + withEntity 래퍼를 새로 할당한다
    // (onPassengers 는 생성 코드 전체에서 612회 호출되는 초-hot 헬퍼).
    // 캐시 키 = (src 객체 신원, ENTITY_GEN). src 자체를 키로 삼는 이유: withEntity(p) 가 반환하는
    // 자식 소스는 부모 src 의 위치/회전/컨텍스트를 상속하므로, getEntity() 가 같아도 positioned/as
    // 로 재바인딩된 다른 src 면 결과가 달라야 한다(그 경우 캐시 미스 → 기존과 동일 재계산).
    // 탑승 관계 변경(startRiding/stopRiding/스폰·킬)은 전부 ENTITY_GEN++ 를 거쳐 자동 무효화.
    // 슬롯 1개라 다른 src 가 끼어들면 미스(정확성 무손실, 이득만 없음). 단일 틱 스레드 전제는
    // 이 클래스의 다른 per-tick 캐시(SNAP/TYPE_INDEX)와 동일.
    // src 는 틱마다 새로 만들어지는 임시 객체(identity 키 = 같은 틱 내에서만 유효).
    // 종전 단일-엔트리 캐시는 카트 A→B→A 처럼 src 가 교차되면 매번 미스였다(스파크 ~0.6%).
    // 틱/세대가 바뀌면 통째로 비운다(이전 틱 src 는 다시 안 오므로 누수 없음).
    private static final java.util.IdentityHashMap<net.minecraft.server.command.ServerCommandSource,
            java.util.List<net.minecraft.server.command.ServerCommandSource>> ONP_MAP =
            new java.util.IdentityHashMap<>();
    private static net.minecraft.server.MinecraftServer ONP_MAP_SERVER;
    private static int  ONP_MAP_TICK = Integer.MIN_VALUE;
    private static long ONP_MAP_GEN  = -1;
    // 22차: vehicle → {템플릿 11필드, 결과 리스트} — 동등 템플릿 src 간 공유(스탬프는 ONP_MAP 와 공용).
    private static final java.util.IdentityHashMap<net.minecraft.entity.Entity, Object[]> ONP_VEH =
            new java.util.IdentityHashMap<>();

    /** on passengers 의 '엔티티 선(先)열거' 판 — opt_post pass-2.78 이 필터 가드가 전부
     *  엔티티만 보는 루프를 이 판으로 재작성한다(가드 통과분만 withEntity 생성).
     *  vanilla getPassengerList 는 불변 복사 리스트라 본문 스폰/킬과 무관하게 안전 순회.
     *  [실측] withEntity 는 승객마다 getName().getString()+getDisplayName()(번역 렌더)을
     *  즉시 수행 — 수백 파츠 카트에서 매 미스마다 전 파츠 이름 렌더가 ~1.2%p 였다. */
    public static java.util.List<net.minecraft.entity.Entity> passengersOf(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (e == null) return java.util.List.of();
        return e.getPassengerList();
    }

    public static java.util.List<net.minecraft.server.command.ServerCommandSource> onPassengers(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (e == null) return java.util.List.of();
        net.minecraft.server.MinecraftServer sv = src.getServer();
        int tk = sv.getTicks();
        if (ONP_MAP_SERVER != sv || ONP_MAP_TICK != tk || ONP_MAP_GEN != ENTITY_GEN) {
            ONP_MAP.clear(); ONP_VEH.clear();
            ONP_MAP_SERVER = sv; ONP_MAP_TICK = tk; ONP_MAP_GEN = ENTITY_GEN;
        }
        java.util.List<net.minecraft.server.command.ServerCommandSource> hit = ONP_MAP.get(src);
        if (hit != null) return hit;   // 같은 src·같은 틱·같은 세대 — 재사용(할당 0)
        java.util.List<net.minecraft.entity.Entity> ps = e.getPassengerList();
        java.util.List<net.minecraft.server.command.ServerCommandSource> out;
        if (ps.isEmpty()) {
            out = java.util.List.of();   // 승객 없는 엔티티(마커 등) — 할당 0
        } else {
            // 12차: src 상속 필드 11개는 승객과 무관 — 루프 밖에서 1회만 읽는다.
            Object[] t = wefTemplate(src, ps.get(0));
            // 22차: 같은 vehicle 을 다른 src(다른 함수 컨텍스트)가 처리해도, 상속 11필드가
            // 값-동일하면 결과 소스 리스트는 값-동일 — (vehicle, 틱) 캐시로 공유한다.
            // 소스는 불변 객체라 공유 안전(객체 identity 는 어떤 경로도 관측하지 않음).
            // 위치/회전(Vec3d/Vec2f)은 값 equals, 나머지는 참조 동일성으로 비교 — 카트가
            // 함수 사이에 움직였으면 위치가 달라 자동 미스(정확성은 구조적으로 보존).
            Object[] veh = (t != null) ? ONP_VEH.get(e) : null;
            if (veh != null && _tplEq((Object[]) veh[0], t)) {
                @SuppressWarnings("unchecked")   // ONP_VEH 값은 이 메서드만 기록 — 타입 불변
                java.util.List<net.minecraft.server.command.ServerCommandSource> shared =
                        (java.util.List<net.minecraft.server.command.ServerCommandSource>) veh[1];
                out = shared;
            } else {
                java.util.ArrayList<net.minecraft.server.command.ServerCommandSource> tmp =
                        new java.util.ArrayList<>(ps.size());
                for (net.minecraft.entity.Entity p : ps)
                    tmp.add(t == null ? src.withEntity(p) : withEntitySrc(src, p, t));
                out = tmp;
                if (t != null) ONP_VEH.put(e, new Object[]{t, out});
            }
        }
        ONP_MAP.put(src, out);
        return out;
    }

    /** 22차: wefTemplate 11필드 동등성 — 위치/회전/레벨/silent 는 값, 그 외는 참조. */
    private static boolean _tplEq(Object[] a, Object[] b) {
        return a[0] == b[0]
                && java.util.Objects.equals(a[1], b[1])    // Vec3d position (값)
                && java.util.Objects.equals(a[2], b[2])    // Vec2f rotation (값)
                && a[3] == b[3] && java.util.Objects.equals(a[4], b[4]) && a[7] == b[7]
                && java.util.Objects.equals(a[9], b[9])
                && a[10] == b[10] && a[11] == b[11] && a[12] == b[12] && a[13] == b[13];
    }

    // ── withEntity 특수화(10차 도입, 11차 개정) ──
    // [실측 10차] vanilla withEntity 는 (승객 × src) 조합마다 entity.getName().getString()
    // (번역 렌더) + entity.getDisplayName()(팀 장식 렌더)을 반복한다 — 수백 파츠 카트의 지배 비용.
    // 이름/표시명은 엔티티 함수이지 src 함수가 아니므로 (틱, ENTITY_GEN, NAME_GEN) 스탬프의
    // 엔티티당 1회 렌더로 수렴시킨다. 무효화 훅은 팀 4종 + CustomName 을 만지는
    // entityPut/MergeSnbt(NAME_GEN++), 브릿지/디스패처 실행(ENTITY_GEN++).
    // [11차 개정] 10차의 '필드 14개 + 타입 유일성' 매칭은 프로덕션에서 비활성이었다(스파크:
    // withEntitySrc 하위 100% vanilla — 믹스인 필드 주입으로 전제 붕괴 추정). 개정판 전제 최소화:
    //   · 생성자: 바닐라 14-타입 시그니처를 getDeclaredConstructor 로 직접 조회(모호성 0,
    //     시그니처 자체가 위 바이트코드 재현의 검증 기준).
    //   · 필드 식별: 이름/타입/개수 무의존 — 공개 with* API 로 만든 센티널 변형과 원본의
    //     '달라진 필드' 관찰(프로브)로 역할별 필드를 찾는다. 믹스인 주입 필드는 프로브에서
    //     변하지 않으므로 자동 배제. 기대치(변화 개수/값) 위반 시 즉시 중단.
    //   · 어떤 실패든 [KFC-WEF] disabled: <사유> 1회 로그 후 영구 vanilla 폴백(fail-closed).
    //     최초 실사용 1회는 vanilla 결과와 식별 14필드 전수 비교 자기검증을 통과해야 활성화.
    private static long NAME_GEN = 0;
    private static final java.util.IdentityHashMap<net.minecraft.entity.Entity, Object[]> ND_MAP =
            new java.util.IdentityHashMap<>();
    private static net.minecraft.server.MinecraftServer ND_SERVER;
    private static int  ND_TICK = Integer.MIN_VALUE;          // 22차: 화해 주기 판정용(무효화 축 아님)
    private static long ND_NGEN = -1;
    private static int  ND_RECON_TICK = Integer.MIN_VALUE;    // 22차: 100틱 주기 화해 스탬프

    private static final class Wef {
        static byte state = 0;   // 0=미프로브 1=활성(자기검증 통과) 2=영구 vanilla 폴백
        static java.lang.reflect.Constructor<net.minecraft.server.command.ServerCommandSource> ctor;
        static java.lang.invoke.MethodHandle ctorMh;   // 12차: invokeExact 용(boxing/배열 없는 직접 호출)
        static java.lang.reflect.Field[] all;   // 전체 인스턴스 필드(프로브 관찰용)
        static java.lang.reflect.Field fOut, fPos, fRot, fWorld, fLevel, fName, fDisp, fServer,
                fEntity, fSilent, fRvc, fAnchor, fSigned, fQueue;

        static void disable(String why) {
            state = 2;
            System.err.println("[KFC-WEF] disabled: " + why);
        }

        /** a/b 의 인스턴스 필드 중 값이 다른 것들. 원시형은 박싱 equals — 관찰 목적상 충분. */
        private static java.util.ArrayList<java.lang.reflect.Field> diff(Object a, Object b) throws Exception {
            java.util.ArrayList<java.lang.reflect.Field> out = new java.util.ArrayList<>();
            for (java.lang.reflect.Field f : all)
                if (!java.util.Objects.equals(f.get(a), f.get(b))) out.add(f);
            return out;
        }
        private static java.lang.reflect.Field one(java.util.ArrayList<java.lang.reflect.Field> d,
                                                   String probe) {
            if (d.size() != 1) throw new IllegalStateException("probe " + probe + ": diff=" + d.size());
            return d.get(0);
        }

        /** 센티널 프로브로 역할별 필드 식별. 실패 시 false(사유 로그). */
        static boolean probe(net.minecraft.server.command.ServerCommandSource src,
                             net.minecraft.entity.Entity p) {
            try {
                Class<net.minecraft.server.command.ServerCommandSource> cls =
                        net.minecraft.server.command.ServerCommandSource.class;
                ctor = cls.getDeclaredConstructor(
                        net.minecraft.server.command.CommandOutput.class,
                        net.minecraft.util.math.Vec3d.class,
                        net.minecraft.util.math.Vec2f.class,
                        net.minecraft.server.world.ServerWorld.class,
                        int.class, String.class, net.minecraft.text.Text.class,
                        net.minecraft.server.MinecraftServer.class,
                        net.minecraft.entity.Entity.class, boolean.class,
                        net.minecraft.command.ReturnValueConsumer.class,
                        net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor.class,
                        net.minecraft.network.message.SignedCommandArguments.class,
                        net.minecraft.util.thread.FutureQueue.class);
                ctor.setAccessible(true);
                ctorMh = java.lang.invoke.MethodHandles.lookup().unreflectConstructor(ctor);
                java.util.ArrayList<java.lang.reflect.Field> fs = new java.util.ArrayList<>();
                for (java.lang.reflect.Field f : cls.getDeclaredFields())
                    if (!java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                        f.setAccessible(true); fs.add(f);
                    }
                all = fs.toArray(new java.lang.reflect.Field[0]);

                net.minecraft.server.world.ServerWorld w = src.getWorld();
                net.minecraft.server.MinecraftServer sv = src.getServer();
                net.minecraft.server.command.ServerCommandSource b0 =
                        new net.minecraft.server.command.ServerCommandSource(
                                net.minecraft.server.command.CommandOutput.DUMMY,
                                net.minecraft.util.math.Vec3d.ZERO, net.minecraft.util.math.Vec2f.ZERO,
                                w, 2, "kfc-wef-probe",
                                net.minecraft.text.Text.literal("kfc-wef-probe"), sv, null);

                fPos = one(diff(b0, b0.withPosition(
                        new net.minecraft.util.math.Vec3d(3.5, 7.25, 11.125))), "pos");
                fRot = one(diff(b0, b0.withRotation(
                        new net.minecraft.util.math.Vec2f(12.5f, 33.25f))), "rot");
                net.minecraft.server.command.CommandOutput sentOut =
                        new net.minecraft.server.command.CommandOutput() {
                            @Override public void sendMessage(net.minecraft.text.Text t) {}
                            @Override public boolean shouldReceiveFeedback() { return false; }
                            @Override public boolean shouldTrackOutput() { return false; }
                            @Override public boolean shouldBroadcastConsoleToOps() { return false; }
                        };
                fOut = one(diff(b0, b0.withOutput(sentOut)), "out");
                fLevel = one(diff(b0, b0.withLevel(3)), "level");
                fSilent = one(diff(b0, b0.withSilent()), "silent");
                net.minecraft.command.ReturnValueConsumer sentRvc = (ok, v) -> {};
                fRvc = one(diff(b0, b0.withReturnValueConsumer(sentRvc)), "rvc");
                fAnchor = one(diff(b0, b0.withEntityAnchor(
                        net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor.EYES)), "anchor");
                net.minecraft.network.message.SignedCommandArguments sentSigned = (s) -> null;
                net.minecraft.util.thread.FutureQueue sentQueue = new net.minecraft.util.thread.FutureQueue() {
                    @Override public <T> void append(java.util.concurrent.CompletableFuture<T> f,
                                                     java.util.function.Consumer<T> c) {}
                };
                net.minecraft.server.command.ServerCommandSource b8 =
                        b0.withSignedArguments(sentSigned, sentQueue);
                java.util.ArrayList<java.lang.reflect.Field> ds = diff(b0, b8);
                if (ds.size() != 2) throw new IllegalStateException("probe signed: diff=" + ds.size());
                for (java.lang.reflect.Field f : ds) {
                    Object v = f.get(b8);
                    if (v == sentSigned) fSigned = f;
                    else if (v == sentQueue) fQueue = f;
                }
                if (fSigned == null || fQueue == null) throw new IllegalStateException("probe signed: id");
                // entity 프로브 1회로 entity/name/displayName 3역할 동시 식별(값의 타입으로 구분)
                net.minecraft.server.command.ServerCommandSource b9 = b0.withEntity(p);
                for (java.lang.reflect.Field f : diff(b0, b9)) {
                    Object v = f.get(b9);
                    if (v instanceof net.minecraft.entity.Entity) fEntity = f;
                    else if (v instanceof String) fName = f;
                    else if (v instanceof net.minecraft.text.Text) fDisp = f;
                    else throw new IllegalStateException("probe entity: " + f.getType().getName());
                }
                if (fEntity == null || fName == null || fDisp == null)
                    throw new IllegalStateException("probe entity: id");
                // world/server — 남은 두 역할은 현재값 일치(유일)로 식별
                java.util.HashSet<java.lang.reflect.Field> used = new java.util.HashSet<>(java.util.List.of(
                        fPos, fRot, fOut, fLevel, fSilent, fRvc, fAnchor, fSigned, fQueue,
                        fEntity, fName, fDisp));
                for (java.lang.reflect.Field f : all) {
                    if (used.contains(f)) continue;
                    Object v = f.get(b0);
                    if (v == w) {
                        if (fWorld != null) throw new IllegalStateException("probe world: dup");
                        fWorld = f;
                    } else if (v == sv) {
                        if (fServer != null) throw new IllegalStateException("probe server: dup");
                        fServer = f;
                    }
                }
                if (fWorld == null || fServer == null) throw new IllegalStateException("probe world/server: id");
                return true;
            } catch (Throwable t) {
                disable(String.valueOf(t));
                return false;
            }
        }
    }

    /** onPassengers 미스당 1회: 프로브(최초) + src 상속 필드 11개 스냅샷.
     *  비활성/실패면 null(호출측 vanilla 폴백). 슬롯 5,6,8 은 승객별(name/disp/entity). */
    private static Object[] wefTemplate(net.minecraft.server.command.ServerCommandSource src,
                                        net.minecraft.entity.Entity firstPassenger) {
        if (Wef.state == 2) return null;
        try {
            if (Wef.state == 0 && !Wef.probe(src, firstPassenger)) return null;
            Object[] t = new Object[14];
            t[0] = Wef.fOut.get(src);    t[1] = Wef.fPos.get(src);    t[2] = Wef.fRot.get(src);
            t[3] = Wef.fWorld.get(src);  t[4] = Wef.fLevel.get(src);  t[7] = Wef.fServer.get(src);
            t[9] = Wef.fSilent.get(src); t[10] = Wef.fRvc.get(src);   t[11] = Wef.fAnchor.get(src);
            t[12] = Wef.fSigned.get(src); t[13] = Wef.fQueue.get(src);
            return t;
        } catch (Throwable th) {
            Wef.disable(String.valueOf(th));
            return null;
        }
    }

    /** src.withEntity(p) 와 관측 동등한 특수화 — 이름/표시명만 틱-캐시에서 공급하고 나머지는
     *  vanilla 바이트코드처럼 src 의 해당 필드 값(템플릿 t, src당 1회 채취)을 14-인자 생성자에
     *  전달한다. 생성은 MethodHandle.invokeExact(정확 시그니처) — Object[] boxing 경유하는
     *  Constructor.newInstance 의 호출당 오버헤드(스파크 0.6%p+)를 제거한다.
     *  표시명은 소스마다 copy() 로 격리(vanilla 도 호출마다 새 Text — 별칭 양상 동일).
     *  최초 실사용 시 vanilla 결과와 식별 14필드 전수 비교 자기검증 — 불일치/예외면 영구 폴백. */
    private static net.minecraft.server.command.ServerCommandSource withEntitySrc(
            net.minecraft.server.command.ServerCommandSource src, net.minecraft.entity.Entity p,
            Object[] t) {
        try {
            if (src.getEntity() == p) return src;   // vanilla 의 동일-엔티티 단락과 동일
            net.minecraft.server.MinecraftServer sv = src.getServer();
            int tk = sv.getTicks();
            // 22차: ND 캐시를 틱-간 유지 — 이름/표시명은 엔티티 수명 동안 사실상 불변이고,
            // 변경 경로는 전부 NAME_GEN 훅(팀 실변화/CustomName 쓰기/브릿지·디스패처)을 거친다.
            // 종전 '매 틱 클리어'는 수백 파츠의 번역 렌더를 매 틱 반복시켰다(~1.2%p).
            // 콘솔/OP 의 훅 밖 개입은 100틱(5초) 주기 화해로 수렴 — 13/14차와 동일한 편차 축.
            if (ND_TICK != tk) {
                ND_TICK = tk;
                if (ND_RECON_TICK == Integer.MIN_VALUE || tk - ND_RECON_TICK >= 100) {
                    ND_RECON_TICK = tk;
                    ND_MAP.clear();
                }
            }
            if (ND_SERVER != sv || ND_NGEN != NAME_GEN) {
                ND_MAP.clear(); ND_SERVER = sv; ND_NGEN = NAME_GEN;
            }
            Object[] nd = ND_MAP.get(p);
            if (nd == null) {
                nd = new Object[] { p.getName().getString(), p.getDisplayName() };
                ND_MAP.put(p, nd);
            }
            net.minecraft.server.command.ServerCommandSource fast =
                    (net.minecraft.server.command.ServerCommandSource) Wef.ctorMh.invokeExact(
                    (net.minecraft.server.command.CommandOutput) t[0],
                    (net.minecraft.util.math.Vec3d) t[1],
                    (net.minecraft.util.math.Vec2f) t[2],
                    (net.minecraft.server.world.ServerWorld) t[3],
                    (int) (Integer) t[4],
                    (String) nd[0],
                    (net.minecraft.text.Text) ((net.minecraft.text.Text) nd[1]).copy(),
                    (net.minecraft.server.MinecraftServer) t[7],
                    p,
                    (boolean) (Boolean) t[9],
                    (net.minecraft.command.ReturnValueConsumer) t[10],
                    (net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor) t[11],
                    (net.minecraft.network.message.SignedCommandArguments) t[12],
                    (net.minecraft.util.thread.FutureQueue) t[13]);
            if (Wef.state == 0) {
                net.minecraft.server.command.ServerCommandSource truth = src.withEntity(p);
                java.lang.reflect.Field[] idf = { Wef.fOut, Wef.fPos, Wef.fRot, Wef.fWorld,
                        Wef.fLevel, Wef.fName, Wef.fDisp, Wef.fServer, Wef.fEntity, Wef.fSilent,
                        Wef.fRvc, Wef.fAnchor, Wef.fSigned, Wef.fQueue };
                for (java.lang.reflect.Field f : idf) {
                    Object a = f.get(fast), b = f.get(truth);
                    boolean eq = (f.getType().isPrimitive() || f == Wef.fName || f == Wef.fDisp)
                            ? java.util.Objects.equals(a, b) : a == b;
                    if (!eq) { Wef.disable("verify: " + f.getName()); return truth; }
                }
                Wef.state = 1;
            }
            return fast;
        } catch (Throwable t2) {
            Wef.disable(String.valueOf(t2));
            return src.withEntity(p);
        }
    }

    /** 술어를 만족하는 플레이어가 하나라도 있는지 — predicate 동반 @a/@p 존재검사용. */
    public static boolean anyPlayerWhere(GameContext ctx,
            java.util.function.Predicate<net.minecraft.server.network.ServerPlayerEntity> pred) {
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (pred.test(p)) return true;
        }
        return false;
    }

    /** 조건에 맞는 플레이어 전체를 리스트로 — particle viewers(@a[tag,distance]) 등에 사용. */
    public static java.util.List<net.minecraft.server.network.ServerPlayerEntity> filterPlayers(
            GameContext ctx,
            java.util.function.Predicate<net.minecraft.server.network.ServerPlayerEntity> pred) {
        java.util.List<net.minecraft.server.network.ServerPlayerEntity> out = new java.util.ArrayList<>();
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (pred.test(p)) out.add(p);
        }
        return out;
    }

    /** scoreboard players reset <holder> [<obj>] — obj null 이면 전 목표에서 제거. */
    public static void resetScore(ServerScoreboard sb, String holder, String o) {
        if ("*".equals(holder)) { resetScoreWildcard(sb, o); return; }   // 전체 클리어는 wildcard 내부에서
        dropHandlesFor(holder);   // 13차: 해당 홀더만 선별 무효화(종전 전체 클리어 → 매 틱 재해소 churn)
        ScoreHolder sh = holderOf(holder);
        if (o == null) {
            sb.removeScores(sh);
        } else {
            ScoreboardObjective ob = obj(sb, o);
            if (ob != null) sb.removeScore(sh, ob);
        }
    }

    /** scoreboard players reset * [obj] — 와일드카드. 추적 중인 '모든' 홀더의 점수를 리셋.
     *  obj 가 null 이면 전 오브젝티브, 아니면 해당 오브젝티브만. (바닐라 '*' 시맨틱) */
    public static void resetScoreWildcard(ServerScoreboard sb, String o) {
        invalidateScoreHandles();
        java.util.List<ScoreHolder> holders =
                new java.util.ArrayList<>(sb.getKnownScoreHolders());
        if (o == null) {
            for (ScoreHolder sh : holders) sb.removeScores(sh);
        } else {
            ScoreboardObjective ob = obj(sb, o);
            if (ob == null) return;
            for (ScoreHolder sh : holders) sb.removeScore(sh, ob);
        }
    }

    /** particle <name> <pos> <delta> <speed> <count> — 단순 파티클(파라미터 없는 타입). */
    public static void spawnParticle(net.minecraft.server.world.ServerWorld world, String name,
                                     double x, double y, double z,
                                     double dx, double dy, double dz, double speed, int count) {
        spawnParticle(world, name, x, y, z, dx, dy, dz, speed, count, false, null);
    }

    public static void spawnParticle(net.minecraft.server.world.ServerWorld world, String name,
                                     double x, double y, double z,
                                     double dx, double dy, double dz, double speed, int count,
                                     boolean force,
                                     java.util.List<net.minecraft.server.network.ServerPlayerEntity> viewers) {
        net.minecraft.util.Identifier id = idOf(
                name.contains(":") ? name : "minecraft:" + name);
        net.minecraft.particle.ParticleType<?> pt = net.minecraft.registry.Registries.PARTICLE_TYPE.get(id);
        if (!(pt instanceof net.minecraft.particle.SimpleParticleType spt)) return;
        spawnParticleEffect(world, spt, x, y, z, dx, dy, dz, speed, count, force, viewers);
    }

    // ──────────────── effect ────────────────

    private static net.minecraft.registry.entry.RegistryEntry<net.minecraft.entity.effect.StatusEffect> effectEntry(String id) {
        return net.minecraft.registry.Registries.STATUS_EFFECT
                .getEntry(idOf(id)).orElse(null);
    }

    /** effect give — durationTicks<0 이면 무한. amplifier 는 명령 인자 그대로(0-base). */
    public static void effectGive(net.minecraft.entity.Entity e, String id, int durationTicks,
                                  int amplifier, boolean showParticles) {
        if (!(e instanceof net.minecraft.entity.LivingEntity le)) return;
        net.minecraft.registry.entry.RegistryEntry<net.minecraft.entity.effect.StatusEffect> entry = effectEntry(id);
        if (entry == null) return;
        int dur = durationTicks < 0 ? -1 : durationTicks;
        le.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                entry, dur, amplifier, false, showParticles, showParticles));
    }

    public static void effectClear(net.minecraft.entity.Entity e, String id) {
        if (!(e instanceof net.minecraft.entity.LivingEntity le)) return;
        net.minecraft.registry.entry.RegistryEntry<net.minecraft.entity.effect.StatusEffect> entry = effectEntry(id);
        if (entry != null) le.removeStatusEffect(entry);
    }

    public static void effectClearAll(net.minecraft.entity.Entity e) {
        if (e instanceof net.minecraft.entity.LivingEntity le) le.clearStatusEffects();
    }

    // ──────────────── scoreboard objectives ────────────────

    /** scoreboard objectives add — 멱등(이미 존재하면 무시). criterion 미해소면 dummy. 표시명=이름. */
    public static void ensureObjective(ServerScoreboard sb, String name, String criterion) {
        if (sb.getNullableObjective(name) != null) return;
        net.minecraft.scoreboard.ScoreboardCriterion crit =
                net.minecraft.scoreboard.ScoreboardCriterion.getOrCreateStatCriterion(criterion)
                        .orElse(net.minecraft.scoreboard.ScoreboardCriterion.DUMMY);
        sb.addObjective(name, crit, net.minecraft.text.Text.literal(name),
                crit.getDefaultRenderType(), false, null);
        OBJ_GEN++;   // 같은 이름의 'null 캐시' ObjRef 재해소
    }

    public static void removeObjective(ServerScoreboard sb, String name) {
        net.minecraft.scoreboard.ScoreboardObjective o = sb.getNullableObjective(name);
        if (o != null) { sb.removeObjective(o); OBJ_GEN++; invalidateScoreHandles(); }   // 13차: 해당 objective 의 전 엔트리 고아화
    }

    /** scoreboard objectives add/modify 의 displayname(Text) 설정. */
    public static void setObjectiveDisplay(ServerScoreboard sb, net.minecraft.server.MinecraftServer server,
                                           String name, String displayJson) {
        net.minecraft.scoreboard.ScoreboardObjective o = sb.getNullableObjective(name);
        if (o != null && displayJson != null) {
            net.minecraft.text.Text t = parseText(server, displayJson);
            if (t != null) o.setDisplayName(t);
        }
    }

    /** NbtComponent 내부 컴파운드 읽기 전용 참조 — copyNbt(딥카피) 회피용.
     *  deprecated 억제를 이 한 곳으로 고립한다(변형 금지 계약은 호출측 주석 참조). */
    @SuppressWarnings("deprecation")
    private static net.minecraft.nbt.NbtCompound kfc$nbtOf(net.minecraft.component.type.NbtComponent nc) {
        return nc.getNbt();
    }

    /** 텍스트 컴포넌트 JSON → Text. 실패 시 null. */
    public static net.minecraft.text.Text parseText(net.minecraft.server.MinecraftServer server, String json) {
        try {
            return net.minecraft.text.Text.Serialization.fromJson(json, server.getRegistryManager());
        } catch (Exception ex) {
            return null;
        }
    }

    // ──────────── 24차: 동적 텍스트(숫자 hole) 런타임 템플릿 ────────────
    // 생성 코드가 매크로 값을 concat 한 텍스트(예: {translate:"m:ss.cc",...})는 매 틱 새
    // 문자열이라 TEXT_CACHE 가 무력하고 packrat 풀 파싱이 반복된다. 숫자 런을 마스킹한
    // '형태 키'(digit-run → \u0001)당 센티널 2조 파싱으로 hole 잎(plain 문자열 / translate
    // 키)을 diff 로 찾아 골격을 만들고, 이후 호출은 hole 잎만 재조립한다.
    // 미지원(스타일 diff = 스타일 내 숫자, translatable 인자 diff, 비문자 leaf, 형제 수 불일치,
    // 센티널 모호)은 구성 단계에서 영구 풀-파싱. 최초 2회는 실파싱과 equals 자기검증.
    // 공유 서브트리는 불변 재사용 — TEXT_CACHE 인스턴스 재사용과 동일 계약(읽기 전용).
    private static final java.util.Map<String, TxtTmpl> TEXT_TMPL = boundedMap(2048);
    private static final class TxtLeaf {
        final int[] path; final byte kind;   // kind 0=plain 문자열, 1=translate 키
        final int[] holes; final String[] segs;
        TxtLeaf(int[] p, byte k, int[] h, String[] s) { path = p; kind = k; holes = h; segs = s; }
    }
    private static final class TxtTmpl {
        byte state = 0;              // 0=구성전 1=활성 2=영구 풀파싱
        int verifyLeft = 2;          // 활성 후 실파싱 equals 검증 횟수
        int k;                       // hole 수
        net.minecraft.text.Text skeleton;
        java.util.List<TxtLeaf> leafs;
        boolean isStatic;            // 형태 불변 — 인스턴스 공통
        String lastJson;             // 1-슬롯 메모(상수 문자열 지름길)
        CachedText lastCached;
    }

    /** json 의 숫자 런 [시작,끝) 목록. */
    private static java.util.ArrayList<int[]> _digitRuns(String s) {
        java.util.ArrayList<int[]> runs = new java.util.ArrayList<>();
        int i = 0, n = s.length();
        while (i < n) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int j = i + 1;
                while (j < n && s.charAt(j) >= '0' && s.charAt(j) <= '9') j++;
                runs.add(new int[]{i, j});
                i = j;
            } else i++;
        }
        return runs;
    }
    private static String _joinRuns(String s, java.util.ArrayList<int[]> runs, String[] fill) {
        StringBuilder sb = new StringBuilder(s.length() + 8);
        int prev = 0;
        for (int i = 0; i < runs.size(); i++) {
            int[] r = runs.get(i);
            sb.append(s, prev, r[0]).append(fill == null ? "\u0001" : fill[i]);
            prev = r[1];
        }
        sb.append(s, prev, s.length());
        return sb.toString();
    }
    /** packrat(명령 인자 파서) + 구 JSON 폴백 — parseTextResolved 의 파싱 체인과 동일. */
    private static net.minecraft.text.Text _txtParse(
            net.minecraft.server.command.ServerCommandSource source, String json) {
        try {
            net.minecraft.command.CommandRegistryAccess cra =
                    net.minecraft.command.CommandRegistryAccess.of(
                            source.getRegistryManager(), source.getEnabledFeatures());
            return net.minecraft.command.argument.TextArgumentType.text(cra)
                    .parse(new com.mojang.brigadier.StringReader(json));
        } catch (Exception primaryFail) {
            try {
                return net.minecraft.text.Text.Serialization.fromJson(json, source.getRegistryManager());
            } catch (Exception ex) { return null; }
        }
    }

    /** 두 파싱 트리의 diff → hole 잎 목록. 미지원 형태면 false. */
    private static boolean _txtDiff(net.minecraft.text.Text a, net.minecraft.text.Text b,
                                    java.util.ArrayDeque<Integer> path, String[] s1, String[] s2,
                                    boolean[] seen, java.util.List<TxtLeaf> out) {
        if (!a.getStyle().equals(b.getStyle())) return false;   // 스타일 내 숫자(hex 색 등) — 미지원
        net.minecraft.text.TextContent ca = a.getContent(), cb = b.getContent();
        if (ca instanceof net.minecraft.text.PlainTextContent pa
                && cb instanceof net.minecraft.text.PlainTextContent pb) {
            String va = pa.string(), vb = pb.string();
            if (!va.equals(vb) && !_txtLeaf(va, vb, path, (byte) 0, s1, s2, seen, out)) return false;
        } else if (ca instanceof net.minecraft.text.TranslatableTextContent ta
                && cb instanceof net.minecraft.text.TranslatableTextContent tb) {
            if (!java.util.Objects.equals(ta.getFallback(), tb.getFallback())) return false;
            Object[] aa = ta.getArgs(), ba = tb.getArgs();
            if (aa.length != ba.length) return false;
            for (int i = 0; i < aa.length; i++) if (!java.util.Objects.equals(aa[i], ba[i])) return false;
            String ka = ta.getKey(), kb = tb.getKey();
            if (!ka.equals(kb) && !_txtLeaf(ka, kb, path, (byte) 1, s1, s2, seen, out)) return false;
        } else if (!ca.equals(cb)) {
            return false;   // 그 외 콘텐츠에 hole — 미지원(score/selector/nbt/keybind 등)
        }
        java.util.List<net.minecraft.text.Text> sa = a.getSiblings(), sb2 = b.getSiblings();
        if (sa.size() != sb2.size()) return false;
        for (int i = 0; i < sa.size(); i++) {
            path.addLast(i);
            boolean ok = _txtDiff(sa.get(i), sb2.get(i), path, s1, s2, seen, out);
            path.removeLast();
            if (!ok) return false;
        }
        return true;
    }
    /** 문자열 leaf 의 센티널 분해(SnbtTmplN 과 동일 규칙). */
    private static boolean _txtLeaf(String va, String vb, java.util.ArrayDeque<Integer> path,
                                    byte kind, String[] s1, String[] s2,
                                    boolean[] seen, java.util.List<TxtLeaf> out) {
        if (va.length() != vb.length()) return false;
        int k = s1.length;
        java.util.ArrayList<int[]> occ = new java.util.ArrayList<>();
        for (int i = 0; i < k; i++) {
            int ia = va.indexOf(s1[i]);
            if (ia < 0) continue;
            if (ia != va.lastIndexOf(s1[i])) return false;
            if (!vb.startsWith(s2[i], ia) || vb.indexOf(s2[i]) != ia || vb.lastIndexOf(s2[i]) != ia) return false;
            if (seen[i]) return false;
            occ.add(new int[]{ia, i});
        }
        if (occ.isEmpty()) return false;
        occ.sort((x, y) -> Integer.compare(x[0], y[0]));
        int n = occ.size();
        int[] holes = new int[n];
        String[] segs = new String[n + 1];
        int prev = 0;
        for (int oi = 0; oi < n; oi++) {
            int pos = occ.get(oi)[0], hole = occ.get(oi)[1];
            if (pos < prev) return false;
            holes[oi] = hole;
            segs[oi] = va.substring(prev, pos);
            prev = pos + s1[hole].length();
        }
        segs[n] = va.substring(prev);
        if (!vb.endsWith(segs[n])) return false;
        for (int oi = 0; oi < n; oi++) seen[holes[oi]] = true;
        int[] pth = new int[path.size()];
        int pi = 0;
        for (Integer x : path) pth[pi++] = x;
        out.add(new TxtLeaf(pth, kind, holes, segs));
        return true;
    }
    /** 골격을 vals 로 재조립 — hole 경로만 새 노드, 나머지 서브트리는 공유. */
    private static net.minecraft.text.Text _txtRebuild(net.minecraft.text.Text n,
                                                       java.util.List<TxtLeaf> leafs, int depth,
                                                       String[] vals) {
        TxtLeaf here = null;
        boolean below = false;
        for (int i = 0; i < leafs.size(); i++) {
            TxtLeaf l = leafs.get(i);
            if (l.path.length == depth) here = l;
            else below = true;
        }
        if (here == null && !below) return n;
        net.minecraft.text.TextContent content = n.getContent();
        if (here != null) {
            StringBuilder sb = new StringBuilder(here.segs[0]);
            for (int oi = 0; oi < here.holes.length; oi++)
                sb.append(vals[here.holes[oi]]).append(here.segs[oi + 1]);
            String nv = sb.toString();
            if (here.kind == 0) {
                content = net.minecraft.text.PlainTextContent.of(nv);
            } else {
                net.minecraft.text.TranslatableTextContent tc =
                        (net.minecraft.text.TranslatableTextContent) content;
                content = new net.minecraft.text.TranslatableTextContent(nv, tc.getFallback(), tc.getArgs());
            }
        }
        net.minecraft.text.MutableText m = net.minecraft.text.MutableText.of(content);
        m.setStyle(n.getStyle());
        java.util.List<net.minecraft.text.Text> sibs = n.getSiblings();
        for (int i = 0; i < sibs.size(); i++) {
            java.util.ArrayList<TxtLeaf> sub = null;
            for (int j = 0; j < leafs.size(); j++) {
                TxtLeaf l = leafs.get(j);
                if (l.path.length > depth && l.path[depth] == i) {
                    if (sub == null) sub = new java.util.ArrayList<>(2);
                    sub.add(l);
                }
            }
            m.append(sub == null ? sibs.get(i) : _txtRebuild(sibs.get(i), sub, depth + 1, vals));
        }
        return m;
    }

    /** 동적(숫자 포함) 문자열의 템플릿 해석. 불가/미검증 실패면 null(호출측 풀 파싱). */
    private static CachedText txtTmplTry(net.minecraft.server.command.ServerCommandSource source,
                                         String json) {
        try {
            java.util.ArrayList<int[]> runs = _digitRuns(json);
            int k = runs.size();
            if (k == 0 || k > 20) return null;
            String masked = _joinRuns(json, runs, null);
            TxtTmpl t = TEXT_TMPL.get(masked);
            if (t == null) {
                t = new TxtTmpl();
                TEXT_TMPL.put(masked, t);
            }
            if (t.state == 2) return null;
            if (t.state == 0) {                     // 형태당 1회 구성
                t.state = 2;                        // 실패 기본값(fail-closed) — 성공 시에만 1
                if (k > 0) {
                    String[] s1 = new String[k], s2 = new String[k];
                    for (int i = 0; i < k; i++) { s1[i] = Integer.toString(101 + i); s2[i] = Integer.toString(201 + i); }
                    net.minecraft.text.Text a = _txtParse(source, _joinRuns(json, runs, s1));
                    net.minecraft.text.Text b = _txtParse(source, _joinRuns(json, runs, s2));
                    if (a != null && b != null) {
                        java.util.List<TxtLeaf> leafs = new java.util.ArrayList<>();
                        boolean[] seen = new boolean[k];
                        if (_txtDiff(a, b, new java.util.ArrayDeque<>(), s1, s2, seen, leafs)) {
                            boolean all = true;
                            for (int i = 0; i < k; i++) if (!seen[i]) { all = false; break; }
                            if (all) {
                                t.k = k; t.skeleton = a; t.leafs = leafs;
                                t.isStatic = isStaticText(a);
                                t.state = 1;
                            }
                        }
                    }
                }
                if (t.state != 1) return null;
            }
            if (t.k != k) return null;              // 형태 키 충돌 방어(이론상 불가)
            if (json.equals(t.lastJson)) return t.lastCached;   // 상수 문자열 지름길(1-슬롯)
            String[] vals = new String[k];
            for (int i = 0; i < k; i++) vals[i] = json.substring(runs.get(i)[0], runs.get(i)[1]);
            net.minecraft.text.Text patched = _txtRebuild(t.skeleton, t.leafs, 0, vals);
            if (t.verifyLeft > 0) {                 // 최초 2회 — 실파싱과 equals 자기검증
                net.minecraft.text.Text truth = _txtParse(source, json);
                if (truth == null || !patched.equals(truth)) { t.state = 2; return null; }
                t.verifyLeft--;
            }
            CachedText ct = new CachedText(patched, t.isStatic);
            t.lastJson = json; t.lastCached = ct;
            return ct;
        } catch (Exception ex) {
            return null;                            // 어떤 예외든 풀 파싱 폴백
        }
    }

    /** 텍스트 컴포넌트를 커맨드 소스로 해석(resolve). nbt/storage/score/selector/interpret 를
     *  런타임에 실제 값으로 치환한다 — actionbar 게이지처럼 storage 기반 컴포넌트에 필수. */
    public static net.minecraft.text.Text parseTextResolved(
            net.minecraft.server.command.ServerCommandSource source,
            net.minecraft.entity.Entity sender, String json) {
        // 바닐라 1.21.5 의 텍스트 명령 인자(title/tellraw)는 Gson JSON 이 아니라
        // packrat SNBT 파서 + TextCodecs.CODEC (TextArgumentType) 로 파싱된다.
        // 수용 문법(unquoted key, SNBT 타입 접미사 등)과 해석이 바이트 단위로 동일하도록
        // 같은 파서를 사용한다. (이전 fromJson 경로는 액션바 게이지의 storage/interpret/
        // separator 컴포넌트가 바닐라와 미묘하게 달라 외부 HUD 모드가 인식하지 못했음.)
        try {
            CachedText cached = TEXT_CACHE.get(json);
            if (cached == null) cached = txtTmplTry(source, json);   // 24차: 동적(숫자 hole) 문자열 — 형태 키 템플릿
            if (cached == null) {
                net.minecraft.text.Text parsed;
                try {
                    net.minecraft.command.CommandRegistryAccess cra =
                            net.minecraft.command.CommandRegistryAccess.of(
                                    source.getRegistryManager(), source.getEnabledFeatures());
                    parsed = net.minecraft.command.argument.TextArgumentType.text(cra)
                            .parse(new com.mojang.brigadier.StringReader(json));
                } catch (Exception primaryFail) {
                    // 폴백: 구 JSON 경로. [실측] packrat 이 거부하는 리터럴은 '매 호출' 예외 생성
                    // (fillInStackTrace) + 이중 파싱을 반복했다(성공만 캐시했기 때문 — 틱당 1.7%p).
                    // 폴백 성공/최종 실패 결과도 동일 키로 캐시해 문자열당 1회로 만든다.
                    parsed = net.minecraft.text.Text.Serialization.fromJson(
                            json, source.getRegistryManager());
                }
                // 컴포넌트 리터럴은 상수 — 파싱 1회면 충분(실패=null 도 캐시). 정적 여부 동봉.
                cached = new CachedText(parsed, parsed != null && isStaticText(parsed));
                TEXT_CACHE.put(json, cached);
            }
            if (cached.text == null) return null;   // 캐시된 파싱 실패 — 종전 null 반환과 동일
            // 정적 트리는 Texts.parse 가 항등(동등 복제)이므로 생략 — 파싱 인스턴스 재사용(불변).
            if (cached.isStatic) return cached.text;
            // nbt+interpret(storage) 단일 컴포넌트 — 요소 디코드 캐시판(최초 1회 바닐라와
            // 결과 equals 자기검증, 불일치 시 영구 바닐라 폴백). [실측 7.2%p: 게이지 액션바]
            if (cached.itpState != 2) {
                net.minecraft.text.Text fast = resolveInterpretStorage(source, sender, cached);
                if (fast != null) return fast;
            }
            return net.minecraft.text.Texts.parse(source, cached.text, sender, 0);
        } catch (Exception ex) {
            return null;   // resolve 실패(선택자 해석 등 런타임 예외) — 종전 최종 실패와 동일
        }
    }

    /** tellraw <players> <msg> — 채팅(컴포넌트를 명령 소스 기준으로 resolve). */
    public static void tellraw(net.minecraft.server.network.ServerPlayerEntity p,
                               net.minecraft.server.command.ServerCommandSource source, String json) {
        if (p == null) return;
        net.minecraft.text.Text t = parseTextResolved(source, source.getEntity(), json);
        if (t != null) p.sendMessage(t, false);
    }

    /** title <players> actionbar <msg> — 액션바(컴포넌트를 명령 소스 기준으로 resolve).
     *  바닐라 TitleCommand 와 동일하게 OverlayMessageS2CPacket 을 직접 보낸다.
     *  (sendMessage(t, true) 는 GameMessageS2CPacket(overlay) 라 화면 표시는 같지만
     *   패킷 타입이 달라, onOverlayMessage 를 후킹하는 외부 모드가 인식하지 못한다.) */
    public static void titleActionbar(net.minecraft.server.network.ServerPlayerEntity p,
                                      net.minecraft.server.command.ServerCommandSource source, String json) {
        if (p == null) return;
        net.minecraft.text.Text t = parseTextResolved(source, source.getEntity(), json);
        if (t != null) p.networkHandler.sendPacket(
                new net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket(t));
    }

    /** title <players> title|subtitle <msg> — 타이틀/서브타이틀(명령 소스 기준 resolve). */
    public static void titleText(net.minecraft.server.network.ServerPlayerEntity p,
                                 net.minecraft.server.command.ServerCommandSource source, String json, boolean sub) {
        if (p == null) return;
        net.minecraft.text.Text t = parseTextResolved(source, source.getEntity(), json);
        if (t == null) return;
        if (sub) p.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.SubtitleS2CPacket(t));
        else p.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.TitleS2CPacket(t));
    }

    /** facing <target> — src 위치에서 target 지점을 바라보는 회전으로 재바인딩. */
    public static net.minecraft.server.command.ServerCommandSource facing(
            net.minecraft.server.command.ServerCommandSource src, double tx, double ty, double tz) {
        return src.withLookingAt(new net.minecraft.util.math.Vec3d(tx, ty, tz));
    }

    // 좌표식이 이미 Vec3d 인 경우의 오버로드. emit 이 cond_pos_expr 결과(Vec3d)를 .x/.y/.z 로
    // 분해해 넘기면 facing 이 다시 new Vec3d 로 재조립했다(분해→재조립 + 좌표식 3중 평가).
    // Vec3d 를 그대로 받아 withLookingAt 에 넘겨 그 왕복 할당·중복 평가를 제거한다. 값 동일.
    public static net.minecraft.server.command.ServerCommandSource facing(
            net.minecraft.server.command.ServerCommandSource src, net.minecraft.util.math.Vec3d target) {
        return src.withLookingAt(target);
    }

    /** facing entity <e> <anchor> — eyes 면 눈높이, feet 면 발 위치를 향함.
     *  EntityAnchor.EYES/FEET.positionAt(e) 는 각각 getEyePos()/getPos() 와 동일. withLookingAt 위임. */
    public static net.minecraft.server.command.ServerCommandSource facingEntity(
            net.minecraft.server.command.ServerCommandSource src, net.minecraft.entity.Entity e, boolean eyes) {
        if (e == null) return src;
        net.minecraft.util.math.Vec3d t = eyes ? e.getEyePos() : e.getPos();
        return src.withLookingAt(t);
    }

    // ── at <entity> 위치+회전 리바인드(핫패스 SCS 체인 축약) ──
    // `at @s`/`at <루프엔티티>` 는 소스를 그 엔티티의 pos·rot 로 옮긴다. 기존 생성 코드는
    // src.withPosition(e.getPos()).withRotation(new Vec2f(e.getPitch(), e.getYaw())) 2-체인이라
    // 중간 SCS 1개 + Vec2f 1개를 매번 만들었다(충돌 rectangle 한 번에 수십 회). 임시 Vec2f 를
    // 이 메서드 스코프로 옮겨 JIT 이스케이프 분석이 스칼라 치환하기 쉬운 형태로 만들고,
    // withPosition/withRotation 의 값-동일 단락(this 반환)을 그대로 활용한다(관측 동일:
    // 두 with 의 합성과 정확히 같은 pos/rot 소스). null 은 바닐라 at 처럼 no-op(src 유지).
    // [D-10] KfcScsMixin(접근자+생성자 인보커) 적용 시 '단일 생성' 리바인드. 미적용이면
    // instanceof 가 거짓 → 기존 with* 체인 폴백(관측 동일). 첫 융합 시 1회 로그(적용 검증용).
    private static boolean SCS_FUSE_LOGGED = false;
    private static void scsFuseLog() {
        if (!SCS_FUSE_LOGGED) {
            SCS_FUSE_LOGGED = true;
            System.out.println("[KFC] SCS fused rebind active (KfcScsMixin single-construction)");
        }
    }

    public static net.minecraft.server.command.ServerCommandSource atEntity(
            net.minecraft.server.command.ServerCommandSource src, net.minecraft.entity.Entity e) {
        if (e == null) return src;
        if (src instanceof __KFC_GROUP__.mixin.KfcScsMixin acc) {
            scsFuseLog();
            // 기존 withPosition+withRotation 2-체인과 필드 단위로 동일(pos·rot 만 교체, world 불변).
            return __KFC_GROUP__.mixin.KfcScsMixin.kfc$create(
                    acc.kfc$output(), e.getPos(),
                    new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw()),
                    src.getWorld(), acc.kfc$level(), src.getName(), src.getDisplayName(),
                    src.getServer(), src.getEntity(), src.isSilent(), src.getReturnValueConsumer(),
                    src.getEntityAnchor(), src.getSignedArguments(), src.getMessageChainTaskQueue());
        }
        return src.withPosition(e.getPos())
                  .withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw()));
    }

    /** [D-10] `as <e> at @s` 융합: withEntity(e)+atEntity(…,e) 3-생성 체인 → 단일 생성.
     *  withEntity 의 name/displayName 갱신(getNameForScoreboard/getDisplayName — 바이트코드 확인)
     *  + at 의 pos/rot 교체를 한 번에 수행. 필드 단위로 기존 체인과 동일. 믹스인 미적용 폴백 동일. */
    public static net.minecraft.server.command.ServerCommandSource withEntityAt(
            net.minecraft.server.command.ServerCommandSource src, net.minecraft.entity.Entity e) {
        if (e == null) return src;
        if (src instanceof __KFC_GROUP__.mixin.KfcScsMixin acc) {
            scsFuseLog();
            return __KFC_GROUP__.mixin.KfcScsMixin.kfc$create(
                    acc.kfc$output(), e.getPos(),
                    new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw()),
                    src.getWorld(), acc.kfc$level(), e.getNameForScoreboard(), e.getDisplayName(),
                    src.getServer(), e, src.isSilent(), src.getReturnValueConsumer(),
                    src.getEntityAnchor(), src.getSignedArguments(), src.getMessageChainTaskQueue());
        }
        return atEntity(src.withEntity(e), e);
    }

    /** anchored eyes — caret(^) 원점을 실행자 눈 위치로(소스 위치 리바인드). */
    public static net.minecraft.server.command.ServerCommandSource anchorEyes(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = src.getEntity();
        return (e == null) ? src : src.withPosition(e.getEyePos());
    }
    /** anchored feet — 원점을 실행자 발 위치로. */
    public static net.minecraft.server.command.ServerCommandSource anchorFeet(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = src.getEntity();
        return (e == null) ? src : src.withPosition(e.getPos());
    }

    // ──────────────── attribute ────────────────
    private static net.minecraft.entity.attribute.EntityAttributeInstance attrInst(
            net.minecraft.entity.Entity e, String attrId) {
        if (!(e instanceof net.minecraft.entity.LivingEntity le)) return null;
        net.minecraft.util.Identifier id = idOf(
                attrId.contains(":") ? attrId : "minecraft:" + attrId);
        net.minecraft.registry.entry.RegistryEntry<net.minecraft.entity.attribute.EntityAttribute> entry =
                net.minecraft.registry.Registries.ATTRIBUTE.getEntry(id).orElse(null);
        if (entry == null) return null;
        return le.getAttributeInstance(entry);
    }

    /** attribute <e> <attr> modifier add <id> <value> <op> */
    public static void attrModifierAdd(net.minecraft.entity.Entity e, String attrId,
                                       String modId, double value, String op) {
        net.minecraft.entity.attribute.EntityAttributeInstance inst = attrInst(e, attrId);
        if (inst == null) return;
        net.minecraft.util.Identifier mid = idOf(
                modId.contains(":") ? modId : "minecraft:" + modId);
        net.minecraft.entity.attribute.EntityAttributeModifier.Operation o =
                switch (op) {
                    case "add_multiplied_base", "multiply_base" ->
                            net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE;
                    case "add_multiplied_total", "multiply" ->
                            net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL;
                    default -> net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_VALUE;
                };
        inst.removeModifier(mid);
        inst.addPersistentModifier(new net.minecraft.entity.attribute.EntityAttributeModifier(mid, value, o));
    }

    /** attribute <e> <attr> modifier value get <id> — 모디파이어 값(double). 없으면 0. */
    public static double attrModifierValue(net.minecraft.entity.Entity e, String attrId, String modId) {
        net.minecraft.entity.attribute.EntityAttributeInstance inst = attrInst(e, attrId);
        if (inst == null) return 0;
        net.minecraft.util.Identifier mid = idOf(
                modId.contains(":") ? modId : "minecraft:" + modId);
        net.minecraft.entity.attribute.EntityAttributeModifier m = inst.getModifier(mid);
        return m == null ? 0 : m.value();
    }

    /** attribute <e> <attr> modifier remove <id> */
    public static void attrModifierRemove(net.minecraft.entity.Entity e, String attrId, String modId) {
        net.minecraft.entity.attribute.EntityAttributeInstance inst = attrInst(e, attrId);
        if (inst == null) return;
        net.minecraft.util.Identifier mid = idOf(
                modId.contains(":") ? modId : "minecraft:" + modId);
        inst.removeModifier(mid);
    }

    /** attribute <e> <attr> base set <value> */
    public static void attrBaseSet(net.minecraft.entity.Entity e, String attrId, double value) {
        net.minecraft.entity.attribute.EntityAttributeInstance inst = attrInst(e, attrId);
        if (inst != null) inst.setBaseValue(value);
    }

    /** particle dust{color:[r,g,b],scale:s} — 색상 파티클. r,g,b 는 0~1. */
    public static void spawnDust(net.minecraft.server.world.ServerWorld world,
                                 double x, double y, double z,
                                 double dx, double dy, double dz, double speed, int count,
                                 float r, float g, float b, float scale) {
        spawnDust(world, x, y, z, dx, dy, dz, speed, count, r, g, b, scale, false, null);
    }

    /** force 모드 + viewers 지원. 바닐라 `particle ... force @a[...]` 는 클라 파티클 설정을
     *  무시하고(force=true) 지정된 플레이어들에게 강제 표시한다. force/viewers 를 빠뜨리면
     *  파티클이 클라 설정에 따라 샘플링돼 양이 줄고 불규칙해진다(드리프트 스키드 증상). */
    public static void spawnDust(net.minecraft.server.world.ServerWorld world,
                                 double x, double y, double z,
                                 double dx, double dy, double dz, double speed, int count,
                                 float r, float g, float b, float scale,
                                 boolean force,
                                 java.util.List<net.minecraft.server.network.ServerPlayerEntity> viewers) {
        int packed = (Math.round(r * 255) << 16) | (Math.round(g * 255) << 8) | Math.round(b * 255);
        net.minecraft.particle.DustParticleEffect eff =
                new net.minecraft.particle.DustParticleEffect(packed, scale);
        spawnParticleEffect(world, eff, x, y, z, dx, dy, dz, speed, count, force, viewers);
    }

    /** ParticleEffect 를 force/viewers 규칙에 맞게 스폰하는 공통 경로. */
    /** particle <복합타입> ... — block{block_state:..}/item{..}/dust_color_transition 등 파라미터 동반
     *  파티클을 ParticleEffectArgumentType.readParameters 로 파싱해 스폰(모든 vanilla 파티클 지원). */
    public static void spawnParticleParsed(net.minecraft.server.world.ServerWorld world, String particleStr,
                                           double x, double y, double z,
                                           double dx, double dy, double dz, double speed, int count,
                                           boolean force,
                                           java.util.List<net.minecraft.server.network.ServerPlayerEntity> viewers) {
        try {
            net.minecraft.particle.ParticleEffect eff = net.minecraft.command.argument.ParticleEffectArgumentType
                    .readParameters(new com.mojang.brigadier.StringReader(particleStr), world.getRegistryManager());
            spawnParticleEffect(world, eff, x, y, z, dx, dy, dz, speed, count, force, viewers);
        } catch (Exception ignored) {}
    }

    public static void spawnParticleEffect(net.minecraft.server.world.ServerWorld world,
                                           net.minecraft.particle.ParticleEffect eff,
                                           double x, double y, double z,
                                           double dx, double dy, double dz, double speed, int count,
                                           boolean force,
                                           java.util.List<net.minecraft.server.network.ServerPlayerEntity> viewers) {
        if (viewers != null) {
            // 지정된 플레이어에게만 표시(바닐라 viewers 시맨틱).
            for (net.minecraft.server.network.ServerPlayerEntity p : viewers) {
                world.spawnParticles(p, eff, force, false, x, y, z, count, dx, dy, dz, speed);
            }
        } else {
            world.spawnParticles(eff, force, false, x, y, z, count, dx, dy, dz, speed);
        }
    }

    /** 명령 좌표 리터럴 파싱(바닐라 WorldCoordinate.parseDouble 의 centerIntegers 규칙).
     *  center=true(x/z 축)이고 토큰이 정수 리터럴(소수점 '.' 없음)이면 블록 중심으로 +0.5.
     *  (매크로 변수 좌표처럼 확장 문자열을 런타임에 받는 경우에 사용; 리터럴은 코드젠 시점에 처리된다.) */
    /** 바닐라 매크로 인스턴스화 실패(치환 후 파싱 불가) 재현용 신호 예외.
     *  바닐라 Macro$VariableLine.instantiate 는 치환 라인의 CommandFunction.parse 실패 시
     *  MacroException 을 던지고, 함수 호출 전체가 실패한다 — 어떤 줄도 실행되지 않는다
     *  (jar 바이트코드 확인). 매크로 함수의 executeReturn 본문 전체가 이 예외를 잡아 즉시
     *  return 0 하는 것으로 재현한다(detect-exist-item 이 이 시맨틱으로 아이템 유효성을 판정).
     *  한계: 실패 줄 '이전' 줄의 부수효과는 이미 실행됨 — 바닐라는 사전 인스턴스화라 0줄 실행.
     *  이 팩에서 파싱 실패가 실제 발생하는 함수(detect-exist-item)는 실패 줄이 첫 줄이라 완전 동등. */
    public static final class MacroParseFail extends RuntimeException {
        MacroParseFail() { super(null, null, false, false); }   // 스택트레이스 억제(핫패스 안전)
    }
    public static final MacroParseFail MACRO_FAIL = new MacroParseFail();

    public static double coord(String tok, boolean center) {
        // 매크로 좌표 전용($(x) 등) — 비수치/누락이면 바닐라 매크로 인스턴스화 실패와 동일하게
        // 함수 전체 중단 신호를 던진다(줄 스킵이 아님).
        if (tok == null) throw MACRO_FAIL;
        String t = tok.trim();
        double v;
        try { v = Double.parseDouble(t); } catch (NumberFormatException e) { throw MACRO_FAIL; }
        if (center && t.indexOf('.') < 0) v += 0.5;   // 정수 리터럴만 센터링(소수점 있으면 정확값)
        return v;
    }

    /** 셀렉터 수치필드(박스 dx/dy/dz·원점 x/y/z, distance, level 등)용 '가드된' 매크로 double 파싱.
     *  비수치/null 이면 MACRO_FAIL(=바닐라 매크로 인스턴스화 실패 → 줄 스킵)을 던진다.
     *  조건식(posInBox/inRange 등) 안에서 평가돼도 함수의 catch(MacroParseFail)로 안전하게 잡힌다. */
    public static double macroD(String tok) { return coord(tok, false); }

    /** macroD 의 int 판(scores/limit/level 정수 바운드). 비수치/null → MACRO_FAIL. */
    public static int macroI(String tok) {
        if (tok == null) throw MACRO_FAIL;
        try { return Integer.parseInt(tok.trim()); } catch (NumberFormatException e) { throw MACRO_FAIL; }
    }

    /** macroD 의 float 판(playsound 볼륨/피치 등). 비수치/null → MACRO_FAIL. */
    public static float macroF(String tok) {
        if (tok == null) throw MACRO_FAIL;
        try { return Float.parseFloat(tok.trim()); } catch (NumberFormatException e) { throw MACRO_FAIL; }
    }

    /** summon <type> <pos> <nbt> — NBT 에 id 주입 후 위치 설정해 스폰. */
    public static void summon(net.minecraft.server.world.ServerWorld world, String type,
                              double x, double y, double z, String nbtSnbt) {
        try {
            net.minecraft.nbt.NbtCompound nbt;
            if (nbtSnbt != null && !nbtSnbt.isEmpty()) {
                nbt = net.minecraft.nbt.StringNbtReader.readCompound(nbtSnbt);
            } else {
                nbt = new net.minecraft.nbt.NbtCompound();
            }
            nbt.putString("id", type.contains(":") ? type : "minecraft:" + type);
            net.minecraft.entity.Entity e = net.minecraft.entity.EntityType.loadEntityWithPassengers(
                    nbt, world, net.minecraft.entity.SpawnReason.COMMAND, ent -> {
                        ent.refreshPositionAndAngles(x, y, z, ent.getYaw(), ent.getPitch());
                        return ent;
                    });
            if (e != null) {
                world.spawnNewEntityAndPassengers(e); snapAdd(e);
            }
        } catch (Exception ex) {
            // 소환 실패(SNBT 파싱/로드 등)는 곧 '엔티티 없음'이므로 무음 삼키지 않고 한 줄로 보고한다.
            System.err.println("[KFC] summon failed type=" + type + " : " + ex);
        }
    }

    /** tp <e> <pos> — 엔티티를 좌표로 이동(회전 유지). */
    /** 비-플레이어·무승객 leaf 엔티티 전용 경량 위치+회전 설정(풀 teleport() 관측 동등).
     *  바닐라 Entity.teleport(world,x,y,z,Set.of(),yaw,pitch,true) 는 same-dimension 에서
     *  setPos+setYaw+setHeadYaw+setPitch+resetPosition(updateLastPosition+updateLastAngles)
     *  +refreshPosition+setVelocity(ZERO) 만 수행한다(merged jar 바이트코드 확인).
     *  차원전환/승객 드래그/sendTeleportPacket 은 leaf 비-플레이어엔 불필요하며, 위치는 엔티티
     *  트래커가 매 틱 EntityPositionSyncS2CPacket 으로 동기화한다(movePosition 비-플레이어 경로와 동일).
     *  플레이어(카메라/하차 보정)·승객 동반(좌석 드래그)은 false → 호출부가 풀 teleport 로 폴백. */
    private static boolean lightTeleport(net.minecraft.entity.Entity e,
                                         double x, double y, double z, float yaw, float pitch) {
        if (e instanceof net.minecraft.server.network.ServerPlayerEntity) return false;
        if (e.hasPassengers()) return false;
        e.refreshPositionAndAngles(x, y, z, yaw, pitch);   // setPos+setYaw+setPitch+resetPosition+refreshPosition
        e.setHeadYaw(yaw);                                  // 풀 teleport 의 setHeadYaw 동등
        e.setVelocity(net.minecraft.util.math.Vec3d.ZERO);  // 풀 teleport 의 속도 0 동등(바닐라 /tp 정지)
        return true;
    }

    public static void teleportTo(net.minecraft.entity.Entity e, double x, double y, double z) {
        if (e == null) return;
        // 플레이어도 비-플레이어와 동일하게 바닐라 /tp 의 풀 teleport() 를 쓴다.
        // requestTeleport 는 dismount 직후 같은 틱 tp 시 하차 위치 보정에 덮어써져
        // (retire: stopRiding -> tp -17.5 가 카트 위치로 끌려감) 위치 복귀가 실패한다.
        if (lightTeleport(e, x, y, z, e.getYaw(), e.getPitch())) return;
        e.teleport((net.minecraft.server.world.ServerWorld) e.getWorld(), x, y, z,
                java.util.Set.of(), e.getYaw(), e.getPitch(), true);
    }

    /** tp <대상> <좌표> <회전> — 위치+회전 설정. */
    /** tp <targets> $(dest) — 매크로 목적지 런타임 파싱. 바닐라는 치환 후 재파싱하므로
     *  `x y z`(3토큰) 또는 `x y z yaw pitch`(5토큰) 를 지원한다(kartall 트랙 pos 는 5토큰:
     *  "-1000 31 1000 0 0"). 좌표는 절대/~상대, 절대 x/z 가 소수점 없는 정수면 바닐라 좌표
     *  파서의 블록 센터링(+0.5). 회전은 절대/~상대(source 회전 기준 — 바닐라 RotationArgument).
     *  파싱 불가면 그 줄만 스킵(바닐라 매크로 파싱실패 스킵 동등). */
    public static void tpMacroDest(net.minecraft.server.command.ServerCommandSource source,
                                   net.minecraft.entity.Entity t, String dest) {
        if (t == null || dest == null) return;   // 대상 없음은 파싱 실패가 아님(빈 셀렉터)
        String[] p = dest.trim().split("\\s+");
        if (p.length != 3 && p.length != 5) throw MACRO_FAIL;   // 좌표 형식 불일치 = 치환 라인 파싱 실패
        net.minecraft.util.math.Vec3d base = source.getPosition();
        double[] c = new double[3];
        for (int i = 0; i < 3; i++) {
            String s = p[i];
            double b = (i == 0 ? base.x : i == 1 ? base.y : base.z);
            try {
                if (s.equals("~")) c[i] = b;
                else if (s.startsWith("~")) c[i] = b + Double.parseDouble(s.substring(1));
                else {
                    double v = Double.parseDouble(s);
                    if (i != 1 && s.indexOf('.') < 0) v += 0.5;   // 정수 x/z 센터링
                    c[i] = v;
                }
            } catch (NumberFormatException ex) { throw MACRO_FAIL; }
        }
        float yaw = t.getYaw(), pitch = t.getPitch();
        if (p.length == 5) {
            net.minecraft.util.math.Vec2f rot = source.getRotation();   // ~회전은 source 기준(바닐라)
            try {
                yaw   = p[3].equals("~") ? rot.y
                        : p[3].startsWith("~") ? rot.y + Float.parseFloat(p[3].substring(1))
                        : Float.parseFloat(p[3]);
                pitch = p[4].equals("~") ? rot.x
                        : p[4].startsWith("~") ? rot.x + Float.parseFloat(p[4].substring(1))
                        : Float.parseFloat(p[4]);
            } catch (NumberFormatException ex) { throw MACRO_FAIL; }
        }
        teleportToWithRot(t, c[0], c[1], c[2], yaw, pitch);
    }

    /** store ← forceload add|remove <from> [<to>] 의 결과값: 상태가 실제 바뀐 청크 수.
     *  바닐라 ForceLoadCommand.executeChange: 블록좌표→섹션좌표(getSectionCoord=floor>>4) 범위 루프,
     *  setChunkForced 가 true 반환한 수 = result. 0 이면 'failed' 예외(store→0) — 반환 0 과 동일.
     *  범위 256청크 초과는 TOO_BIG 실패 → 0. */
    public static int forceloadChange(net.minecraft.server.command.ServerCommandSource source,
                                      double fx, double fz, double tx, double tz, boolean add) {
        if (!(source.getWorld() instanceof net.minecraft.server.world.ServerWorld sw)) return 0;
        int x1 = net.minecraft.util.math.ChunkSectionPos.getSectionCoord(
                net.minecraft.util.math.MathHelper.floor(Math.min(fx, tx)));
        int z1 = net.minecraft.util.math.ChunkSectionPos.getSectionCoord(
                net.minecraft.util.math.MathHelper.floor(Math.min(fz, tz)));
        int x2 = net.minecraft.util.math.ChunkSectionPos.getSectionCoord(
                net.minecraft.util.math.MathHelper.floor(Math.max(fx, tx)));
        int z2 = net.minecraft.util.math.ChunkSectionPos.getSectionCoord(
                net.minecraft.util.math.MathHelper.floor(Math.max(fz, tz)));
        long area = (long) (x2 - x1 + 1) * (long) (z2 - z1 + 1);
        if (area > 256L) return 0;
        int n = 0;
        for (int cx = x1; cx <= x2; cx++)
            for (int cz = z1; cz <= z2; cz++)
                if (sw.setChunkForced(cx, cz, add)) n++;
        return n;
    }

    public static void teleportToWithRot(net.minecraft.entity.Entity e, double x, double y, double z,
                                         float yaw, float pitch) {
        if (e == null) return;
        // 바닐라 TeleportCommand 와 동일: yaw/pitch 를 wrapDegrees 로 정규화 후 전체 teleport.
        // teleport 은 내부적으로 resetPosition→updateLastAngles 를 호출하므로 디스플레이 엔티티
        // 회전도 정상 동기화된다(소환 후 tp @s ~ ~ ~ ~rot 패턴).
        float _wy = net.minecraft.util.math.MathHelper.wrapDegrees(yaw);
        float _wp = net.minecraft.util.math.MathHelper.wrapDegrees(pitch);
        if (lightTeleport(e, x, y, z, _wy, _wp)) return;
        e.teleport((net.minecraft.server.world.ServerWorld) e.getWorld(), x, y, z,
                java.util.Set.of(), _wy, _wp, true);
    }

    /** tp <대상> <좌표> <회전> — 위치+회전 설정.
     *  relMask: 원본 명령에서 '~'(상대)였던 성분 비트마스크 (X=1,Y=2,Z=4,Y_ROT=8,X_ROT=16).
     *  바닐라 TeleportCommand.teleport 를 그대로 재현한다: 상대 성분은 (절대값 − 대상 현재값) 델타로
     *  바꾸고 movementFlags 를 Entity.teleport 에 전달한다.
     *  핵심: 차량/탑승자가 얽힌 리그는 반드시 full Entity.teleport 로 보낸다. 그래야
     *  ① 탑승자 직접 tp 시 stopRiding(하차) ② 차량 tp 시 getPassengerTeleportTarget 전파 가
     *  바닐라와 동일하게 동작해, passengerFirst 순회와 결합 시 각 엔티티가 정확히 1회만 회전한다
     *  (디스플레이 리그 이중 회전 −90→−180 방지). 자유 엔티티(차량·탑승자 없음)는 전파/하차가
     *  무의미하므로 lightTeleport 빠른 경로를 유지한다(절대 좌표/회전으로 동등). */
    public static void teleportToWithRot(net.minecraft.entity.Entity e, double x, double y, double z,
                                         float yaw, float pitch, int relMask) {
        if (e == null) return;
        // 자유 엔티티: 전파/하차 불필요 → 경량 경로(emit 이 넘긴 yaw/pitch 는 이미 절대 목표값).
        if (!e.hasVehicle() && !e.hasPassengers()
                && lightTeleport(e, x, y, z,
                        net.minecraft.util.math.MathHelper.wrapDegrees(yaw),
                        net.minecraft.util.math.MathHelper.wrapDegrees(pitch))) return;
        java.util.EnumSet<net.minecraft.network.packet.s2c.play.PositionFlag> flags =
                java.util.EnumSet.noneOf(net.minecraft.network.packet.s2c.play.PositionFlag.class);
        if ((relMask & 1)  != 0) flags.add(net.minecraft.network.packet.s2c.play.PositionFlag.X);
        if ((relMask & 2)  != 0) flags.add(net.minecraft.network.packet.s2c.play.PositionFlag.Y);
        if ((relMask & 4)  != 0) flags.add(net.minecraft.network.packet.s2c.play.PositionFlag.Z);
        if ((relMask & 8)  != 0) flags.add(net.minecraft.network.packet.s2c.play.PositionFlag.Y_ROT);
        if ((relMask & 16) != 0) flags.add(net.minecraft.network.packet.s2c.play.PositionFlag.X_ROT);
        double dx = flags.contains(net.minecraft.network.packet.s2c.play.PositionFlag.X) ? x - e.getX() : x;
        double dy = flags.contains(net.minecraft.network.packet.s2c.play.PositionFlag.Y) ? y - e.getY() : y;
        double dz = flags.contains(net.minecraft.network.packet.s2c.play.PositionFlag.Z) ? z - e.getZ() : z;
        float gy = flags.contains(net.minecraft.network.packet.s2c.play.PositionFlag.Y_ROT) ? yaw - e.getYaw() : yaw;
        float hp = flags.contains(net.minecraft.network.packet.s2c.play.PositionFlag.X_ROT) ? pitch - e.getPitch() : pitch;
        e.teleport((net.minecraft.server.world.ServerWorld) e.getWorld(), dx, dy, dz, flags,
                net.minecraft.util.math.MathHelper.wrapDegrees(gy),
                net.minecraft.util.math.MathHelper.wrapDegrees(hp), true);
    }

    /** tp <대상> <좌표> facing <좌표> — 위치 이동 후 좌표를 바라보게(바닐라 Entity.lookAt). */
    public static void teleportToFacing(net.minecraft.entity.Entity e, double x, double y, double z,
                                        double tx, double ty, double tz) {
        teleportTo(e, x, y, z);
        if (e != null) e.lookAt(net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor.FEET,
                new net.minecraft.util.math.Vec3d(tx, ty, tz));
    }

    /** tp <대상> <좌표> facing entity <엔티티> [feet|eyes] — 위치 이동 후 대상을 바라보게. */
    public static void teleportToFacingEntity(net.minecraft.entity.Entity e, double x, double y, double z,
                                              net.minecraft.entity.Entity target, boolean eyes) {
        teleportTo(e, x, y, z);
        if (e == null || target == null) return;
        net.minecraft.util.math.Vec3d tp = eyes ? target.getEyePos() : target.getPos();
        e.lookAt(net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor.FEET, tp);
    }

    /** rotate <e> <yaw> <pitch> — 엔티티 회전 설정. */
    public static void rotateTo(net.minecraft.entity.Entity e, float yaw, float pitch) {
        if (e == null) return;
        // 바닐라 RotateCommand.rotateToPos 와 동일: entity.rotate(yaw, pitch).
        // Entity.rotate = setYaw + setHeadYaw + setPitch + updateLastAngles().
        // 과거 구현은 updateLastAngles() 가 빠져, 디스플레이 엔티티(item_display 등)가
        // 클라이언트 보간 시 lastYaw 갱신 누락으로 회전이 시각적으로 반영되지 않았다
        // (소환 후 rotate @s ~ 가 안 먹히는 원인). tp 경로는 teleport→resetPosition→
        // updateLastAngles 로 이미 처리되므로 정상이었다.
        // ServerPlayerEntity.rotate 오버라이드는 PlayerRotationS2CPacket 을 전송하므로
        // 플레이어 카메라 락(라이딩 중 포함)은 그대로 유지된다(기존 playerRotateOnly 와 동일).
        e.rotate(yaw, pitch);
    }

    /** 플레이어 회전만 클라에 강제(위치는 상대=불변). 바닐라 /rotate <player> 와 동일.
     *  기존 절대좌표 requestTeleport 는 라이딩 중(@s 가 탈것 승객일 때) 탈것 위치
     *  동기화와 충돌해 회전 패킷이 클라 카메라에 안 먹혔다(루프 시점 고정 미작동).
     *  Entity.teleport 는 라이딩 플레이어를 하차시키므로 쓰지 않고, 위치 X/Y/Z 를
     *  상대(델타 0)로 둔 채 yaw/pitch 만 절대로 전송한다. */
    private static void playerRotateOnly(net.minecraft.server.network.ServerPlayerEntity p,
                                         float yaw, float pitch) {
        // 바닐라 /rotate 와 동일: ServerPlayerEntity.rotate(yaw,pitch) → PlayerRotationS2CPacket 전송.
        // requestTeleport 는 탑승 중인 플레이어의 시점을 못 돌리지만(서버 위치텔포는 라이딩 클라가 무시),
        // 이 회전 전용 패킷(1.21.2+)은 탑승 중에도 클라 시점을 강제 회전시킨다 = 카메라 락.
        p.rotate(yaw, pitch);
    }

    /** lookAt(FEET, target) 후 headYaw/플레이어 동기화 — rotate facing 공통 마무리. */
    private static void rotateFinish(net.minecraft.entity.Entity e) {
        e.setHeadYaw(e.getYaw());
        if (e instanceof net.minecraft.server.network.ServerPlayerEntity p) {
            playerRotateOnly(p, e.getYaw(), e.getPitch());
        }
    }

    /** rotate <e> facing <x y z> — 좌표를 바라보는 회전으로 설정. */
    public static void rotateToFacePos(net.minecraft.entity.Entity e, double x, double y, double z) {
        if (e == null) return;
        e.lookAt(net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor.FEET,
                new net.minecraft.util.math.Vec3d(x, y, z));
        rotateFinish(e);
    }

    /** rotate <e> facing entity <target> [eyes|feet] — 대상 엔티티를 바라보는 회전으로 설정. */
    public static void rotateToFaceEntity(net.minecraft.entity.Entity e, net.minecraft.entity.Entity target, boolean eyes) {
        if (e == null || target == null) return;
        net.minecraft.util.math.Vec3d tp = eyes ? target.getEyePos() : target.getPos();
        e.lookAt(net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor.FEET, tp);
        rotateFinish(e);
    }

    /** tp <who> <destination-entity> — 대상 엔티티 위치+회전으로 텔레포트. */
    public static void teleportToEntity(net.minecraft.entity.Entity who, net.minecraft.entity.Entity dest) {
        if (who == null || dest == null) return;
        double x = dest.getX(), y = dest.getY(), z = dest.getZ();
        float yaw = dest.getYaw(), pitch = dest.getPitch();
        if (lightTeleport(who, x, y, z, yaw, pitch)) return;
        who.teleport((net.minecraft.server.world.ServerWorld) who.getWorld(), x, y, z,
                java.util.Set.of(), yaw, pitch, true);
    }

    /** spectate <target> [player] — player 가 target 을 관전(카메라 부착). target=null 이면 본인 시점 복귀. */
    public static void spectate(net.minecraft.entity.Entity player, net.minecraft.entity.Entity target) {
        if (player instanceof net.minecraft.server.network.ServerPlayerEntity p) {
            p.setCameraEntity(target == null ? p : target);
        }
    }

    // ── item replace (바닐라 ItemCommand: SlotRanges.fromName -> getStackReference.set) ──
    private static int resolveSlot(String name) {
        try {
            net.minecraft.inventory.SlotRange r = net.minecraft.inventory.SlotRanges.fromName(name);
            if (r == null || r.getSlotCount() != 1) return -1;
            return r.getSlotIds().getInt(0);
        } catch (Exception e) { return -1; }
    }

    /** vanilla `data ... entity <slot>[.subpath]` 슬롯 접근자.
     *  weapon.offhand / equipment.offhand / armor.head / container.N 등은 raw NBT 경로가 아니라
     *  해당 슬롯의 ItemStack 을 NBT({id,count,components})로 직렬화해 접근하는 가상 경로다.
     *  (특히 플레이어 오프핸드는 Inventory[Slot:-106]에 저장돼 writeNbt 경로로는 안 잡힘 →
     *   반드시 슬롯으로 해소해야 `equipment.offhand.components.minecraft:custom_data` 등이 동작.)
     *  선행 1~2 세그먼트로 가장 긴 유효 단일 슬롯명을 찾고, 나머지는 아이템 NBT 내부 하위경로로 적용. */
    /** ItemStack 의 sub 경로 NbtElement 읽기.
     *  components.(minecraft:)custom_data[.X] 경로는 CUSTOM_DATA 컴포넌트를 직접 copyNbt 로 읽어
     *  전체 stack.toNbt()(모든 컴포넌트 인코딩) 직렬화를 회피한다 — vanilla 동등 값.
     *  카트 물리(speed/gauge/drift/boost 등)는 cod 오프핸드 custom_data 에 있어 매 틱 다수 읽혀
     *  이 fast-path 가 read 측 핵심 부하를 줄인다. 그 외/빈 경로는 기존대로 toNbt 후 getAtPath. */
    private static net.minecraft.nbt.NbtElement itemSubNbt(net.minecraft.entity.Entity e,
                                                           net.minecraft.item.ItemStack stack, String sub) {
        if (stack == null || stack.isEmpty()) return null;
        if (sub != null && !sub.isEmpty()) {
            String cdPrefix = null;
            if (sub.equals("components.minecraft:custom_data")
                    || sub.startsWith("components.minecraft:custom_data.")) cdPrefix = "components.minecraft:custom_data";
            else if (sub.equals("components.custom_data")
                    || sub.startsWith("components.custom_data."))           cdPrefix = "components.custom_data";
            if (cdPrefix != null) {
                net.minecraft.component.type.NbtComponent nc =
                        stack.get(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);
                if (nc == null) return null;                       // 컴포넌트 부재 → 경로 부재(vanilla 동일)
                net.minecraft.nbt.NbtCompound cd = nc.copyNbt();
                if (sub.length() == cdPrefix.length()) return cd;  // 정확히 custom_data → 내부 compound 전체
                return getAtPath(cd, sub.substring(cdPrefix.length() + 1));   // '.' 다음 잔여 경로
            }
        }
        net.minecraft.nbt.NbtElement itemNbt;
        try { itemNbt = stack.toNbt(e.getRegistryManager()); } catch (Exception ex) { return null; }
        if (sub == null || sub.isEmpty()) return itemNbt;
        return getAtPath(itemNbt, sub);
    }

    private static net.minecraft.nbt.NbtElement slotAccessorNbt(net.minecraft.entity.Entity e, String path) {
        if (e == null || path == null || path.isEmpty()) return null;
        String[] segs = path.split("\\.");
        // 플레이어 전용 가상 접근자 SelectedItem (현재 선택 핫바=메인핸드). writeNbt 에 없음 → 메인핸드로 해소.
        if (segs.length >= 1 && segs[0].equals("SelectedItem")) {
            int mh = resolveSlot("weapon.mainhand");
            if (mh < 0) return null;
            net.minecraft.inventory.StackReference ref = e.getStackReference(mh);
            if (ref == net.minecraft.inventory.StackReference.EMPTY) return null;
            net.minecraft.item.ItemStack stack = ref.get();
            if (stack == null || stack.isEmpty()) return null;
            StringBuilder sub = new StringBuilder();
            for (int i = 1; i < segs.length; i++) { if (sub.length() > 0) sub.append('.'); sub.append(segs[i]); }
            return itemSubNbt(e, stack, sub.toString());   // sub 비면 전체 itemNbt(=toNbt) 반환
        }
        int maxTake = Math.min(2, segs.length);
        for (int take = maxTake; take >= 1; take--) {
            StringBuilder sn = new StringBuilder();
            for (int i = 0; i < take; i++) { if (i > 0) sn.append('.'); sn.append(segs[i]); }
            int slotId = resolveSlot(sn.toString());
            if (slotId < 0) continue;
            net.minecraft.inventory.StackReference ref = e.getStackReference(slotId);
            if (ref == net.minecraft.inventory.StackReference.EMPTY) return null;
            net.minecraft.item.ItemStack stack = ref.get();
            if (stack == null || stack.isEmpty()) return null;  // 빈 슬롯 → 경로 부재(vanilla 동일)
            StringBuilder sub = new StringBuilder();
            for (int i = take; i < segs.length; i++) { if (sub.length() > 0) sub.append('.'); sub.append(segs[i]); }
            return itemSubNbt(e, stack, sub.toString());   // sub 비면 전체 itemNbt(=toNbt) 반환
        }
        return null;
    }

    private static final java.util.HashMap<String, java.util.Optional<net.minecraft.command.argument.ItemStackArgument>>
            ITEM_ARG_CACHE = new java.util.HashMap<>();

    private static net.minecraft.item.ItemStack parseItemStack(net.minecraft.server.MinecraftServer server,
                                                               String itemStr, int count) {
        if (itemStr == null) return net.minecraft.item.ItemStack.EMPTY;
        String head = itemStr;
        int b = head.indexOf('['); if (b >= 0) head = head.substring(0, b);
        int c = head.indexOf('{'); if (c >= 0) head = head.substring(0, c);
        head = head.trim();
        if (head.equals("air") || head.equals("minecraft:air")) return net.minecraft.item.ItemStack.EMPTY;
        // 아이템 문자열 파싱(ItemStringReader)은 비싸고 결과는 불변 → 1회 파싱 후 캐시.
        // 아이템 레지스트리는 비-reloadable 이라 세션 내 안정적. 사용 시점에만 createStack(=새 스택).
        java.util.Optional<net.minecraft.command.argument.ItemStackArgument> arg =
                ITEM_ARG_CACHE.computeIfAbsent(itemStr, s -> {
                    try {
                        net.minecraft.command.argument.ItemStringReader reader =
                                new net.minecraft.command.argument.ItemStringReader(server.getRegistryManager());
                        net.minecraft.command.argument.ItemStringReader.ItemResult r =
                                reader.consume(new com.mojang.brigadier.StringReader(s));
                        return java.util.Optional.of(new net.minecraft.command.argument.ItemStackArgument(r.item(), r.components()));
                    } catch (Exception e) { return java.util.Optional.empty(); }
                });
        if (arg.isEmpty()) return null;
        try {
            return arg.get().createStack(Math.max(1, count), false);
        } catch (Exception e) { return null; }
    }

    /** item replace entity <target> <slot> with <item> [count]. */
    public static void itemReplaceWith(net.minecraft.server.MinecraftServer server, net.minecraft.entity.Entity target,
                                       String slotName, String itemStr, int count) {
        if (target == null) return;
        int slotId = resolveSlot(slotName);
        if (slotId < 0) return;
        net.minecraft.item.ItemStack stack = parseItemStack(server, itemStr, count);
        if (stack == null) return;
        target.getStackReference(slotId).set(stack);
    }

    /** item replace entity <target> <slot> from entity <source> <sourceSlot>. */
    public static void itemReplaceFrom(net.minecraft.entity.Entity target, String tgtSlot,
                                       net.minecraft.entity.Entity source, String srcSlot) {
        if (target == null || source == null) return;
        int tid = resolveSlot(tgtSlot), sid = resolveSlot(srcSlot);
        if (tid < 0 || sid < 0) return;
        net.minecraft.item.ItemStack src = source.getStackReference(sid).get();
        target.getStackReference(tid).set(src == null ? net.minecraft.item.ItemStack.EMPTY : src.copy());
    }

    /** 블록 컨테이너 슬롯의 ItemStack 읽기(없으면 null). */
    private static net.minecraft.item.ItemStack blockSlotStack(net.minecraft.server.world.ServerWorld world,
                                                               net.minecraft.util.math.BlockPos pos, String slot) {
        if (world == null) return null;
        net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof net.minecraft.inventory.Inventory inv)) return null;
        int idx = slotIndexOf(slot);
        if (idx < 0 || idx >= inv.size()) return null;
        return inv.getStack(idx);
    }

    /** 블록 컨테이너 슬롯에 ItemStack 쓰기. */
    private static void setBlockSlotStack(net.minecraft.server.world.ServerWorld world,
                                          net.minecraft.util.math.BlockPos pos, String slot,
                                          net.minecraft.item.ItemStack stack) {
        if (world == null) return;
        net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof net.minecraft.inventory.Inventory inv)) return;
        int idx = slotIndexOf(slot);
        if (idx < 0 || idx >= inv.size()) return;
        inv.setStack(idx, stack);
        be.markDirty();
    }

    /** item replace entity <e> <slot> from block <pos> <srcSlot> — 엔티티 슬롯 ← 블록 컨테이너 슬롯. */
    public static void itemReplaceFromBlock(net.minecraft.entity.Entity target, String tgtSlot,
                                            net.minecraft.server.world.ServerWorld world,
                                            net.minecraft.util.math.BlockPos srcPos, String srcSlot) {
        if (target == null) return;
        int tid = resolveSlot(tgtSlot);
        if (tid < 0) return;
        net.minecraft.item.ItemStack src = blockSlotStack(world, srcPos, srcSlot);
        target.getStackReference(tid).set(src == null ? net.minecraft.item.ItemStack.EMPTY : src.copy());
    }

    /** item replace block <pos> <slot> from entity <e> <srcSlot> — 블록 컨테이너 슬롯 ← 엔티티 슬롯. */
    public static void itemReplaceBlockFromEntity(net.minecraft.server.world.ServerWorld world,
                                                  net.minecraft.util.math.BlockPos pos, String slot,
                                                  net.minecraft.entity.Entity source, String srcSlot) {
        if (source == null) return;
        int sid = resolveSlot(srcSlot);
        if (sid < 0) return;
        net.minecraft.item.ItemStack src = source.getStackReference(sid).get();
        setBlockSlotStack(world, pos, slot, src == null ? net.minecraft.item.ItemStack.EMPTY : src.copy());
    }

    /** item replace block <pos> <slot> from block <srcPos> <srcSlot> — 블록 ← 블록. */
    public static void itemReplaceBlockFromBlock(net.minecraft.server.world.ServerWorld world,
                                                 net.minecraft.util.math.BlockPos pos, String slot,
                                                 net.minecraft.util.math.BlockPos srcPos, String srcSlot) {
        net.minecraft.item.ItemStack src = blockSlotStack(world, srcPos, srcSlot);
        setBlockSlotStack(world, pos, slot, src == null ? net.minecraft.item.ItemStack.EMPTY : src.copy());
    }

    // ── loot (바닐라 LootCommand: source -> List<ItemStack> -> target) ──
    /** loot 의 slot 베이스 int(범위면 첫 슬롯). vanilla ItemSlotArgumentType 값과 동일. */
    private static int resolveSlotBase(String name) {
        try {
            net.minecraft.inventory.SlotRange r = net.minecraft.inventory.SlotRanges.fromName(name);
            if (r == null || r.getSlotIds().isEmpty()) return -1;
            return r.getSlotIds().getInt(0);
        } catch (Exception e) { return -1; }
    }

    /** source: loot <table> — CHEST 컨텍스트(ORIGIN + THIS_ENTITY optional). executeLoot 그대로. */
    public static java.util.List<net.minecraft.item.ItemStack> lootFromTable(
            net.minecraft.server.command.ServerCommandSource source, String tableId) {
        try {
            net.minecraft.loot.LootTable table;
            if (tableId != null && tableId.trim().startsWith("{")) {
                // 인라인 loot table(JSON/SNBT). 바닐라 RegistryEntryArgumentType 은 '{' 를 만나면
                // SNBT 로 읽어 ENTRY_CODEC 으로 디코드하는데, ENTRY_CODEC(RegistryElementCodec)의
                // 인라인 분기는 결국 elementCodec(=LootTable.CODEC).decode 그대로다(바이트코드 확인).
                // ENTRY_CODEC 을 직접 쓰면 선행 getEntryLookup(LOOT_TABLE) 검사에서 실패한다 —
                // server.getRegistryManager() 의 RegistryOps 에는 reloadable 전용인 loot_table
                // 레지스트리가 없기 때문(빈 loot 반환 버그). 그래서 인라인은 CODEC 을 직접 디코드한다.
                // 내부의 중첩 loot_table 참조는 TABLE_KEY(RegistryKey 코덱)라 lookup 이 필요 없고,
                // enchantment 등 dynamic 참조는 registryManager ops 로 해소된다 — 바닐라와 관측 동등.
                net.minecraft.nbt.NbtCompound nbt =
                        net.minecraft.nbt.StringNbtReader.readCompound(tableId);
                com.mojang.serialization.DynamicOps<net.minecraft.nbt.NbtElement> ops =
                        source.getServer().getRegistryManager()
                                .getOps(net.minecraft.nbt.NbtOps.INSTANCE);
                table = net.minecraft.loot.LootTable.CODEC
                        .parse(ops, nbt).result().orElse(null);
                if (table == null) return java.util.List.of();
            } else {
                java.util.Optional<net.minecraft.registry.entry.RegistryEntry.Reference<net.minecraft.loot.LootTable>> entry =
                        source.getServer().getReloadableRegistries().createRegistryLookup()
                                .getOptionalEntry(net.minecraft.registry.RegistryKey.of(
                                        net.minecraft.registry.RegistryKeys.LOOT_TABLE,
                                        idOf(tableId)));
                if (entry.isEmpty()) return java.util.List.of();
                table = entry.get().value();
            }
            net.minecraft.loot.context.LootWorldContext lc =
                    new net.minecraft.loot.context.LootWorldContext.Builder(source.getWorld())
                            .addOptional(net.minecraft.loot.context.LootContextParameters.THIS_ENTITY, source.getEntity())
                            .add(net.minecraft.loot.context.LootContextParameters.ORIGIN, source.getPosition())
                            .build(net.minecraft.loot.context.LootContextTypes.CHEST);
            return table.generateLoot(lc);
        } catch (Exception e) { return java.util.List.of(); }
    }

    /** source: mine <pos> <tool> — Block.getDroppedStacks(state, world, pos, be, entity, tool). executeMine 그대로. */
    public static java.util.List<net.minecraft.item.ItemStack> lootFromMine(
            net.minecraft.server.command.ServerCommandSource source,
            net.minecraft.util.math.BlockPos pos, net.minecraft.item.ItemStack tool) {
        try {
            net.minecraft.server.world.ServerWorld world = source.getWorld();
            net.minecraft.block.BlockState state = world.getBlockState(pos);
            net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
            if (state.getBlock().getLootTableKey().isEmpty()) return java.util.List.of();
            return net.minecraft.block.Block.getDroppedStacks(state, world, pos, be, source.getEntity(),
                    tool == null ? net.minecraft.item.ItemStack.EMPTY : tool);
        } catch (Exception e) { return java.util.List.of(); }
    }

    /** loot mine/fish <tool>: item literal | mainhand | offhand → ItemStack. */
    public static net.minecraft.item.ItemStack lootTool(net.minecraft.server.MinecraftServer server,
                                                        net.minecraft.server.command.ServerCommandSource source,
                                                        String toolStr) {
        if (toolStr == null) return net.minecraft.item.ItemStack.EMPTY;
        if (toolStr.equals("mainhand") || toolStr.equals("offhand")) {
            net.minecraft.entity.Entity e = source.getEntity();
            if (e instanceof net.minecraft.entity.LivingEntity le)
                return le.getStackInHand(toolStr.equals("mainhand")
                        ? net.minecraft.util.Hand.MAIN_HAND : net.minecraft.util.Hand.OFF_HAND);
            return net.minecraft.item.ItemStack.EMPTY;
        }
        net.minecraft.item.ItemStack s = parseItemStack(server, toolStr, 1);
        return s == null ? net.minecraft.item.ItemStack.EMPTY : s;
    }

    /** target: give <players> — insertStack(copy), 넘치면 드롭. executeGive 그대로. */
    public static void lootGive(java.util.List<net.minecraft.item.ItemStack> loot,
                                java.util.Collection<net.minecraft.server.network.ServerPlayerEntity> players) {
        if (loot == null) return;
        for (net.minecraft.server.network.ServerPlayerEntity p : players)
            for (net.minecraft.item.ItemStack st : loot) {
                net.minecraft.item.ItemStack c = st.copy();
                boolean inserted = p.getInventory().insertStack(c);
                if (!inserted || !c.isEmpty()) {
                    net.minecraft.entity.ItemEntity ie = p.dropItem(c, false);
                    if (ie != null) { ie.resetPickupDelay(); ie.setOwner(p.getUuid()); }
                }
            }
    }

    /** target: spawn <pos> — ItemEntity 스폰. executeSpawn 그대로. */
    public static void lootSpawn(net.minecraft.server.world.ServerWorld world,
                                 net.minecraft.util.math.Vec3d pos,
                                 java.util.List<net.minecraft.item.ItemStack> loot) {
        if (loot == null || world == null) return;
        for (net.minecraft.item.ItemStack st : loot) {
            net.minecraft.entity.ItemEntity ie = new net.minecraft.entity.ItemEntity(
                    world, pos.x, pos.y, pos.z, st.copy());
            ie.setToDefaultPickupDelay();
            world.spawnEntity(ie); snapAdd(ie);
        }
    }

    /** target: replace entity <e> <slot> [count] — slot 베이스부터 count 칸. count<0 이면 loot.size(). */
        /** loot insert <pos> <소스> — 소스 아이템들을 <pos> 컨테이너에 삽입(바닐라: 병합 후 빈 슬롯). */
    public static void lootInsert(net.minecraft.server.command.ServerCommandSource source,
                                  net.minecraft.util.math.BlockPos pos,
                                  java.util.List<net.minecraft.item.ItemStack> loot) {
        if (loot == null || loot.isEmpty()) return;
        net.minecraft.block.entity.BlockEntity be = source.getWorld().getBlockEntity(pos);
        if (!(be instanceof net.minecraft.inventory.Inventory inv)) return;
        boolean changed = false;
        for (net.minecraft.item.ItemStack st : loot) {
            if (st == null || st.isEmpty()) continue;
            net.minecraft.item.ItemStack rem = st.copy();
            // 1) 같은 아이템 스택에 병합
            for (int i = 0; i < inv.size() && !rem.isEmpty(); i++) {
                net.minecraft.item.ItemStack sl = inv.getStack(i);
                if (!sl.isEmpty() && net.minecraft.item.ItemStack.areItemsAndComponentsEqual(sl, rem)) {
                    int can = Math.min(rem.getCount(), sl.getMaxCount() - sl.getCount());
                    if (can > 0) { sl.increment(can); rem.decrement(can); changed = true; }
                }
            }
            // 2) 빈 슬롯에 배치
            for (int i = 0; i < inv.size() && !rem.isEmpty(); i++) {
                if (inv.getStack(i).isEmpty()) {
                    int put = Math.min(rem.getCount(), rem.getMaxCount());
                    net.minecraft.item.ItemStack place = rem.copy(); place.setCount(put);
                    inv.setStack(i, place); rem.decrement(put); changed = true;
                }
            }
        }
        if (changed) be.markDirty();
    }

    public static void lootReplaceEntity(net.minecraft.entity.Entity e, String slotName, int count,
                                         java.util.List<net.minecraft.item.ItemStack> loot) {
        if (e == null || loot == null) return;
        // item_display 의 표시 아이템은 getStackReference(0) 으로 접근한다(= setItemStack 백킹 →
        // DataTracker 갱신 + 클라 동기화). 인덱스 0 이 item ref(바이트코드로 확인), 그 외 EMPTY.
        if (e instanceof net.minecraft.entity.decoration.DisplayEntity.ItemDisplayEntity) {
            net.minecraft.item.ItemStack st = loot.isEmpty()
                    ? net.minecraft.item.ItemStack.EMPTY : loot.get(0).copy();
            net.minecraft.inventory.StackReference ref = e.getStackReference(0);
            if (ref != net.minecraft.inventory.StackReference.EMPTY) ref.set(st);
            return;
        }
        int base = resolveSlotBase(slotName);
        if (base < 0) return;
        int n = (count < 0) ? loot.size() : count;
        for (int i = 0; i < n; i++) {
            net.minecraft.item.ItemStack st = (i < loot.size()) ? loot.get(i).copy() : net.minecraft.item.ItemStack.EMPTY;
            net.minecraft.inventory.StackReference ref = e.getStackReference(base + i);
            if (ref != net.minecraft.inventory.StackReference.EMPTY) ref.set(st);
        }
        if (e instanceof net.minecraft.server.network.ServerPlayerEntity p) p.currentScreenHandler.sendContentUpdates();
    }

    /** target: replace block <pos> <slot> [count] — 컨테이너 슬롯 교체. executeBlock 그대로. */
    public static void lootReplaceBlock(net.minecraft.server.command.ServerCommandSource source,
                                        net.minecraft.util.math.BlockPos pos, String slotName, int count,
                                        java.util.List<net.minecraft.item.ItemStack> loot) {
        if (loot == null) return;
        net.minecraft.block.entity.BlockEntity be = source.getWorld().getBlockEntity(pos);
        if (!(be instanceof net.minecraft.inventory.Inventory inv)) return;
        int base = resolveSlotBase(slotName);
        if (base < 0) return;
        int n = (count < 0) ? loot.size() : count;
        for (int i = 0; i < n; i++) {
            int slot = base + i;
            if (slot < 0 || slot >= inv.size()) continue;
            net.minecraft.item.ItemStack st = (i < loot.size()) ? loot.get(i).copy() : net.minecraft.item.ItemStack.EMPTY;
            if (inv.isValid(slot, st)) inv.setStack(slot, st);
        }
        be.markDirty();
    }

    /** @x[nbt={active_effects:[{id:"ns:effect"}]}] — 상태효과 보유 검사. */
    public static boolean hasEffect(net.minecraft.entity.Entity e, String effectId) {
        if (!(e instanceof net.minecraft.entity.LivingEntity le)) return false;
        java.util.Optional<net.minecraft.registry.entry.RegistryEntry.Reference<net.minecraft.entity.effect.StatusEffect>> ref =
                net.minecraft.registry.Registries.STATUS_EFFECT.getEntry(idOf(effectId));
        return ref.isPresent() && le.hasStatusEffect(ref.get());
    }

    /** ride <who> mount <vehicle> / ride <who> dismount.
     *  바닐라 RideCommand 처럼 자기탑승·순환(누가 자기 탈것의 조상이 됨)을 차단한다.
     *  순환을 만들면 startRiding 후 addPassengersDeep 가 무한 재귀 → StackOverflow(서버 사망)이므로
     *  반드시 막아야 한다. 바닐라도 이 경우 명령을 실패(무탑승)시키므로 거부가 고증에 맞다. */
    public static void rideMount(net.minecraft.entity.Entity who, net.minecraft.entity.Entity vehicle) {
        if (who == null || vehicle == null) return;
        if (who == vehicle) return;                                  // 자기 자신엔 못 탐
        for (net.minecraft.entity.Entity v = vehicle; v != null; v = v.getVehicle())
            if (v == who) return;                                    // who 가 vehicle 체인에 있음 → 순환, 거부
        if (who.getVehicle() == vehicle) return;                     // 이미 그 탈것에 타고 있음
        who.stopRiding();
        who.startRiding(vehicle, true);   // force=true (커맨드 강제 탑승)
    }
    public static void rideDismount(net.minecraft.entity.Entity who) {
        if (who != null) who.stopRiding();
    }

    /** advancement grant|revoke <player> only <id> — 해당 어드밴스먼트의 모든 criteria 부여/회수. */
    public static void advancement(net.minecraft.server.MinecraftServer server,
                                   net.minecraft.entity.Entity player, String advId, boolean grant) {
        if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity p)) return;
        net.minecraft.advancement.AdvancementEntry entry =
                server.getAdvancementLoader().get(idOf(advId));
        if (entry == null) return;
        for (String crit : entry.value().criteria().keySet()) {
            if (grant) p.getAdvancementTracker().grantCriterion(entry, crit);
            else p.getAdvancementTracker().revokeCriterion(entry, crit);
        }
    }

    /** predicate ifpassenger — 승객 중 플레이어 존재. */
    public static boolean hasPlayerPassenger(net.minecraft.entity.Entity e) {
        if (e == null) return false;
        for (net.minecraft.entity.Entity p : e.getPassengerList()) {
            if (p instanceof net.minecraft.server.network.ServerPlayerEntity) return true;
        }
        return false;
    }

    /** if data entity @s <path> — NBT 경로 존재 검사(점 표기). */
    /** if data entity <e> {compound} — 엔티티 NBT 가 주어진 컴파운드를 부분 포함하는지(NbtHelper.matches).
     *  brightness 등 DataTracker 필드는 writeNbt 에 직렬화되므로 그걸로 검사한다. */
    public static boolean entityMatchesNbt(net.minecraft.entity.Entity e, String compoundSnbt) {
        if (e == null) return false;
        try {
            net.minecraft.nbt.NbtCompound expected =
                    net.minecraft.nbt.StringNbtReader.readCompound(compoundSnbt);
            return net.minecraft.nbt.NbtHelper.matches(expected, entitySnapshot(e), true);   // 캐시 스냅샷(읽기 전용)
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean entityHasPath(net.minecraft.entity.Entity e, String path) {
        if (e == null) return false;
        // 플레이어의 writeNbt 는 인벤토리 포함 전체 직렬화 — RootVehicle(탑승 여부)은 직접 검사.
        if (path.equals("RootVehicle")) return e.hasVehicle();
        // Entity.writeNbt 가 항상 기록하는 필드 — 존재 검사는 늘 true (writeNbt 라운드트립 불필요).
        switch (path.replace(" ", "")) {
            case "Pos": case "Pos[0]": case "Pos[1]": case "Pos[2]":
            case "Rotation": case "Rotation[0]": case "Rotation[1]":
            case "Motion": case "Motion[0]": case "Motion[1]": case "Motion[2]":
            case "UUID": case "FallDistance": case "Fire": case "Air":
            case "OnGround": case "Invulnerable":
                return true;
            case "CustomName":
                return e.getCustomName() != null;
            default: break;
        }
        if (path.startsWith("transformation")) {
            net.minecraft.nbt.NbtElement f = displayGetFast(e, path.replace(" ", ""));
            if (f != null) return true;
            if (e instanceof net.minecraft.entity.decoration.DisplayEntity) return false;
        }
        // 슬롯 접근자(weapon/equipment/armor/container.*) — writeNbt 경로로는 안 잡히므로 우선 검사.
        if (slotAccessorNbt(e, path.replace(" ", "")) != null) return true;
        try {
            net.minecraft.nbt.NbtCompound root = entitySnapshot(e);   // 캐시 스냅샷(읽기 전용 탐색)
            // 바닐라 NbtPath 로 검사 — 리스트 인덱스(temp3[0])·필터(foo[{id:1}])·와일드카드([])를
            // 정확히 처리한다. 단순 '.' split + contains(키) 는 "temp3[0]" 를 리터럴 키로 오인해
            // 인덱스 경로에서 항상 false 를 반환했다(async-summon 의 temp3[0] 루프가 1회 후 정지하는 버그).
            net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path.replace(" ", ""));
            if (p == null) return false;
            return p.count(root) > 0;
        } catch (Exception ex) {
            return false;
        }
    }

    /** mcfunction 함수 단위 폴백 — 원본 데이터팩 함수를 통째로 실행한다.
     *  부분 변환 함수의 줄 혼합(native+bridge)으로 인한 컨텍스트 단절을 피하기 위해,
     *  '완전 변환이 아니면 함수 전체를 원본으로' 원칙의 실행기. */
    /** 상대좌표 자기-미세이동(`tp ~ ~ ~`/`tp @s ^ ^ ^d` 류) 전용 위치 갱신.
     *  풀 Entity.teleport() 는 패킷 재동기화·승객 재배치·디스플레이 보간 리셋을 동반해
     *  틱당 수십 회 도는 미세이동에서 바닐라 명령 경로와 다른 잡음(간헐 검출 어긋남)을 만든다.
     *  검증된 레퍼런스 구현과 동일하게: 비플레이어는 updatePosition(raw), 플레이어는
     *  네트워크 텔레포트(클라이언트 동기화 필수)로 처리한다. 절대좌표/장거리 tp 는
     *  기존 teleportTo(풀 텔레포트)를 그대로 사용할 것. */
    public static void movePosition(net.minecraft.entity.Entity e, double x, double y, double z) {
        if (e == null) return;
        // 이동 변위(delta)를 먼저 계산 — 승객 동반 이동에 사용.
        double _dx = x - e.getX();
        double _dy = y - e.getY();
        double _dz = z - e.getZ();
        if (e instanceof net.minecraft.server.network.ServerPlayerEntity p) {
            p.networkHandler.requestTeleport(x, y, z, p.getYaw(), p.getPitch());
        } else {
            e.updatePosition(x, y, z);
        }
        // 바닐라 /tp <탈것> 은 승객 전체를 좌석 오프셋 유지한 채 함께 옮긴다.
        // updatePosition/requestTeleport 는 본체만 옮기고 승객을 데려가지 않아,
        // 루프처럼 매 틱 큰 거리를 연속 텔레포트하면 플레이어 서버 위치가 카트와 벌어져
        // @a[distance=..N] 속도계, playsound 가청, 체크포인트 위치판정이 모두 어긋났다
        // (정차 시 다음 틱 승객 동기화로 수렴 -> 소리/속도계 동시 복구 증상).
        // 좌석 오프셋은 (passengerPos - vehiclePos) 로 보존되므로 동일 delta 평행이동이 정확하다.
        if (e.hasPassengers() && (_dx != 0.0 || _dy != 0.0 || _dz != 0.0)) {
            _movePassengersByDelta(e, _dx, _dy, _dz);
        }
    }

    /** movePosition 보조: 탈것 이동 변위만큼 승객(및 중첩 승객)을 평행이동해 좌석을 유지한다.
     *  [실측 25차] 종전 승객당 hasPassengers() 재귀 가드가 가상호출 체인(hasPassengers →
     *  ImmutableList.isEmpty → AbstractCollection.isEmpty → size)으로 ~2.0%p 를 태웠다
     *  (수백 파츠 × 틱당 미세이동 수). 진입부 size 검사 + 인덱스 순회로 교체 — 가드 체인과
     *  이터레이터 할당이 사라진다. 이동 순서/결과는 종전과 완전 동일(동일 리스트, 동일 순번). */
    private static void _movePassengersByDelta(net.minecraft.entity.Entity vehicle,
                                               double dx, double dy, double dz) {
        java.util.List<net.minecraft.entity.Entity> lst = vehicle.getPassengerList();
        int n = lst.size();
        for (int i = 0; i < n; i++) {
            net.minecraft.entity.Entity ps = lst.get(i);
            if (ps instanceof net.minecraft.server.network.ServerPlayerEntity sp) {
                sp.networkHandler.requestTeleport(sp.getX() + dx, sp.getY() + dy, sp.getZ() + dz,
                        sp.getYaw(), sp.getPitch());
            } else {
                ps.updatePosition(ps.getX() + dx, ps.getY() + dy, ps.getZ() + dz);
            }
            _movePassengersByDelta(ps, dx, dy, dz);   // 빈 리스트는 진입부 n==0 으로 즉시 복귀
        }
    }

    /** --trace 디버깅: 지정 함수 진입 시 실행 컨텍스트 출력. */
    /** 디버그: 엔티티 해소 결과(이름/태그/점수) 1줄 출력. */
    public static net.minecraft.entity.Entity traceEntity(String label, net.minecraft.entity.Entity e,
                                                          ServerScoreboard sb, String obj) {
        if (e == null) { System.out.println("[KFC-DBG] " + label + " = null"); return null; }
        Integer v = read(sb, e.getNameForScoreboard(), obj);
        System.out.println("[KFC-DBG] " + label + " = " + e.getType().getUntranslatedName()
                + "@" + e.getPos() + " name=" + e.getNameForScoreboard()
                + " " + obj + "=" + v + " tags=" + e.getCommandTags());
        return e;
    }

    /** 디버그: 홀더가 보유한 모든 점수를 {obj=v,...} 로. */
    private static String holderScores(ServerScoreboard sb, net.minecraft.entity.Entity e) {
        StringBuilder s = new StringBuilder("{");
        boolean first = true;
        for (ScoreboardObjective o : sb.getObjectives()) {
            ReadableScoreboardScore sc = sb.getScore(e, o);
            if (sc == null) continue;
            if (!first) s.append(",");
            s.append(o.getName()).append("=").append(sc.getScore());
            first = false;
        }
        return s.append("}").toString();
    }

    public static void trace(String fid, net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = src.getEntity();
        net.minecraft.util.math.Vec2f r = src.getRotation();
        StringBuilder out = new StringBuilder("[KFC-TRACE] ").append(fid)
                .append(" pos=").append(src.getPosition())
                .append(" rot=(").append(r.x).append(",").append(r.y).append(")")
                .append(" exec=").append(e == null ? "null"
                        : (e.getType().getUntranslatedName() + "@" + e.getPos() + " tags=" + e.getCommandTags()));
        if (e != null) {
            GameContext ctx0 = getOrCreateContext(src);
            out.append(" scores=").append(holderScores(ctx0.scoreboard, e));
        }
        // 인근(8블럭) 엔티티 태그 요약 — 셀렉터 상태(carA/carB/kart-enemy-first 등) 실측용.
        if (e != null) {
            GameContext ctx = getOrCreateContext(src);
            int shown = 0;
            for (net.minecraft.entity.Entity n : ctx.world.getOtherEntities(e, e.getBoundingBox().expand(8),
                    en -> !en.getCommandTags().isEmpty())) {
                out.append("\n    [near] ").append(n.getType().getUntranslatedName())
                   .append("@").append(n.getPos()).append(" tags=").append(n.getCommandTags())
                   .append(" scores=").append(holderScores(ctx.scoreboard, n));
                if (++shown >= 6) break;
            }
        }
        System.out.println(out);
    }

    public static void instantExecuteFunction(net.minecraft.server.command.ServerCommandSource source,
                                              net.minecraft.util.Identifier functionId) {
        instantExecuteFunction(source, functionId, null);
    }

    // ── CommandFunction 핸들 캐시(#12) — 브릿지마다 manager.getFunction(id) Optional 조회 제거.
    // 무효화: 틱 경계(리로드는 명령 처리 틱에 일어나므로 다음 틱이면 항상 재해소) +
    //         runCommand 의 reload/datapack 계열 실행 직후(같은 틱 내 재조회 정합).
    private static final java.util.HashMap<net.minecraft.util.Identifier,
            net.minecraft.server.function.CommandFunction<net.minecraft.server.command.ServerCommandSource>>
            FN_CACHE = new java.util.HashMap<>();
    private static int FN_TICK = Integer.MIN_VALUE;
    private static net.minecraft.server.MinecraftServer FN_SERVER;

    private static net.minecraft.server.function.CommandFunction<net.minecraft.server.command.ServerCommandSource>
            functionHandle(net.minecraft.server.MinecraftServer server, net.minecraft.util.Identifier id) {
        int tk = server.getTicks();
        if (FN_SERVER != server || FN_TICK != tk) { FN_CACHE.clear(); FN_SERVER = server; FN_TICK = tk; }
        net.minecraft.server.function.CommandFunction<net.minecraft.server.command.ServerCommandSource> fn =
                FN_CACHE.get(id);
        if (fn == null) {
            fn = server.getCommandFunctionManager().getFunction(id).orElse(null);
            if (fn != null) FN_CACHE.put(id, fn);
        }
        return fn;
    }

    /** 브릿지 폴백에서 mcfunction 매크로에 넘길 NbtCompound 를 만든다.
     *  SnapMacroArgs(= `with entity/storage/block <path>` 로 만든 인자)면 바인드 시점 원본 타입 NBT 를
     *  그대로 넘겨, 바닐라 Macro.withMacroReplaced/Macro.toString 이 직접 포맷하게 한다(고증 완전 일치).
     *  그 외(인라인 {compound} 등에서 온 평문 Map)는 문자열 값을 NbtString 으로 넣는다 — 이 경우
     *  값이 이미 매크로-포맷 문자열이므로 Macro.toString(NbtString)=원문 그대로여서 동일하다.
     *  빈/없음이면 null(바닐라: 인자 없는 매크로 호출은 실행 안 함). */
    private static net.minecraft.nbt.NbtCompound bridgeMacroNbt(java.util.Map<String, String> macroArgs) {
        if (macroArgs == null || macroArgs.isEmpty()) return null;
        if (macroArgs instanceof SnapMacroArgs snap) {
            net.minecraft.nbt.NbtCompound raw = snap.rawArgsCopy();
            return (raw == null || raw.isEmpty()) ? null : raw;
        }
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        for (java.util.Map.Entry<String, String> e : macroArgs.entrySet()) {
            nbt.putString(e.getKey(), e.getValue());
        }
        return nbt;
    }

    /** 단일 바닐라 명령을 서버 디스패처로 그대로 실행(100% 바닐라 동작).
     *  깔끔한 네이티브 API 가 없거나(예: datapack enable/disable - 리소스 리로드 동반) 드물게만
     *  실행되는 관리 명령용. 함수의 나머지 줄은 네이티브로 유지하면서, 이 한 줄만 디스패처에
     *  위임한다(예전엔 이런 줄 하나가 함수 전체를 브릿지로 끌어내렸다 - 맵 uninstall 등).
     *  source 를 그대로 써서 같은 함수 내 다른 명령과 피드백/권한 동작을 일치시킨다. */
    public static void runCommand(net.minecraft.server.command.ServerCommandSource source, String command) {
        try {
            snapBarrierAll();   // 23차: 바닐라 실행이 storage 를 변이할 수 있음 — 바인드 스냅샷 실체화
            source.getServer().getCommandManager().executeWithPrefix(source, command);
            // 선별적 무효화(#11): 명령 문자열은 변환 시점 상수 — 첫 토큰으로 분류해
            // 엔티티 집합/태그/탑승/objective 에 영향을 줄 수 없는 명령은 GEN 스래싱을 생략.
            // 분류 불가/미지 명령은 종전대로 전체 무효화(fail-closed).
            int cls = classifyCommand(command);
            if ((cls & CMD_SAFE) == 0) {
                ENTITY_GEN++;   // 디스패처 실행이 summon/kill/tag/ride 했을 수 있으므로 무효화
                OBJ_GEN++;      // 바닐라가 objectives add/remove 했을 수 있음
                invalidateScoreHandles();   // 13차: 바닐라가 scoreboard reset/remove 했을 수 있음
                NAME_GEN++;                 // 22차: 바닐라가 /team·CustomName 을 바꿨을 수 있음
            }
            if ((cls & CMD_RELOADS) != 0) FN_CACHE.clear();   // reload/datapack — 함수 핸들 재해소
        } catch (Exception ex) {
            System.err.println("[KFC-CMD] '" + command + "' : " + ex);
        }
    }

    private static final int CMD_SAFE = 1, CMD_RELOADS = 2;
    private static final java.util.HashMap<String, Integer> CMD_CLASS = new java.util.HashMap<>();
    /** 엔티티 집합·태그·탑승·objective 어느 것도 바꿀 수 없는 명령 첫 토큰(화이트리스트).
     *  여기 없는 명령(execute/function/summon/kill/tag/ride/scoreboard/data/tp/give/loot/
     *  advancement/place/spreadplayers/reload/datapack …)은 전부 전체 무효화 유지. */
    private static final java.util.Set<String> CMD_SAFE_ROOTS = java.util.Set.of(
            "weather", "time", "difficulty", "gamerule", "playsound", "stopsound", "music",
            "particle", "title", "tellraw", "say", "tell", "msg", "w", "teammsg", "tm",
            "bossbar", "worldborder", "gamemode", "defaultgamemode", "xp", "experience",
            "clear", "item", "recipe", "fill", "setblock", "clone", "forceload",
            "effect", "enchant", "spawnpoint", "setworldspawn");

    private static int classifyCommand(String command) {
        Integer c = CMD_CLASS.get(command);
        if (c != null) return c;
        String s = command;
        int b = 0;
        while (b < s.length() && (s.charAt(b) == '/' || s.charAt(b) == ' ')) b++;
        int sp = s.indexOf(' ', b);
        String root = (sp < 0 ? s.substring(b) : s.substring(b, sp));
        int v = 0;
        if (CMD_SAFE_ROOTS.contains(root)) v |= CMD_SAFE;
        if (root.equals("reload") || root.equals("datapack")) v |= CMD_RELOADS;
        if (CMD_CLASS.size() < 4096) CMD_CLASS.put(command, v);   // 상수 명령만 유입 — 상한은 방어용
        return v;
    }

    /** 함수 폴백 + 반환값 캡처 — 폴백 함수의 executeReturn 이 mcfunction return 값을
     *  if function 분기에 전달할 수 있게 한다. 실패/미실행 시 0. */
    public static int instantExecuteFunctionReturn(net.minecraft.server.command.ServerCommandSource source,
                                                   net.minecraft.util.Identifier functionId,
                                                   java.util.Map<String, String> macroArgs) {
        final int[] ret = {0};
        try {
            net.minecraft.server.MinecraftServer server = source.getServer();
            net.minecraft.server.function.CommandFunctionManager manager = server.getCommandFunctionManager();
            net.minecraft.server.function.CommandFunction<net.minecraft.server.command.ServerCommandSource> fn =
                    functionHandle(server, functionId);
            if (fn == null) { System.err.println("[KFC-BRIDGE-ERROR] function not loaded: " + functionId); return 0; }
            net.minecraft.nbt.NbtCompound nbt = bridgeMacroNbt(macroArgs);
            net.minecraft.server.function.Procedure<net.minecraft.server.command.ServerCommandSource> procedure;
            try {
                procedure = fn.withMacroReplaced(nbt, manager.getDispatcher());
            } catch (Exception macroMissing) {
                // 매크로 인자 미해소(`with` 소스 부재) → 바닐라처럼 실행 안 함. 반환 0.
                return 0;
            }
            net.minecraft.command.ReturnValueConsumer consumer = new net.minecraft.command.ReturnValueConsumer() {
                public void onResult(boolean success, int value) { ret[0] = value; }
            };
            snapBarrierAll();   // 23차: 브릿지가 storage 를 변이할 수 있음 — 바인드 스냅샷 실체화
            net.minecraft.server.command.CommandManager.callWithContext(source, (context) -> {
                net.minecraft.command.CommandExecutionContext.enqueueProcedureCall(
                        context, procedure, source, consumer);
                context.run();
            });
            // 브릿지는 바닐라 엔진으로 실행되어 엔티티를 summon/kill 할 수 있으나 snapAdd/snapRemove 를
            // 거치지 않는다. 이후 스냅샷 기반 셀렉터(@e 루프/태그 제거 등)가 그 엔티티를 놓치면
            // (예: 브릿지로 소환된 모델의 임시태그 제거 실패 → 다음 소환이 이전 모델 간섭) 고증이 깨진다.
            // 따라서 브릿지 직후 스냅샷/타입버킷을 무효화해 다음 접근 때 live 월드에서 재빌드시킨다.
            ENTITY_GEN++;
            invalidateScoreHandles();   // 13차: 브릿지가 scoreboard reset/remove 했을 수 있음
            NAME_GEN++;                 // 22차: 브릿지가 /team·CustomName 을 바꿨을 수 있음
        } catch (Exception ex) {
            // 폴백 실행 실패는 조용히 삼키지 않는다 — 원본이라면 함수가 '실행'됐을 상황이므로
            // 실패는 변환기/환경 문제다. 진단 가능하도록 로그를 남긴다.
            System.err.println("[KFC-BRIDGE-ERROR] " + functionId + ": " + ex);
        }
        return ret[0];
    }

    public static int instantExecuteFunctionReturn(net.minecraft.server.command.ServerCommandSource source,
                                                   net.minecraft.util.Identifier functionId) {
        return instantExecuteFunctionReturn(source, functionId, null);
    }

    /** 매크로 인자 동반 함수 폴백. macroArgs(Map) → NbtCompound 로 변환해
     *  withMacroReplaced 에 전달 — 원본 `function X {a:1}` / `with ...` 시맨틱 보존. */
    public static void instantExecuteFunction(net.minecraft.server.command.ServerCommandSource source,
                                              net.minecraft.util.Identifier functionId,
                                              java.util.Map<String, String> macroArgs) {
        try {
            net.minecraft.server.MinecraftServer server = source.getServer();
            net.minecraft.server.function.CommandFunctionManager manager = server.getCommandFunctionManager();
            net.minecraft.server.function.CommandFunction<net.minecraft.server.command.ServerCommandSource> fn =
                    functionHandle(server, functionId);
            if (fn == null) { System.err.println("[KFC-BRIDGE-ERROR] function not loaded: " + functionId); return; }
            net.minecraft.nbt.NbtCompound nbt = bridgeMacroNbt(macroArgs);
            net.minecraft.server.function.Procedure<net.minecraft.server.command.ServerCommandSource> procedure;
            try {
                procedure = fn.withMacroReplaced(nbt, manager.getDispatcher());
            } catch (Exception macroMissing) {
                // 매크로 함수인데 인자가 없음 = `with <source>` 가 해소되지 않았음(또는 빈 인자).
                // 바닐라는 이 경우 함수를 실행하지 않으므로(인자 획득 실패) 조용히 스킵 — 에러 스팸 방지.
                return;
            }
            snapBarrierAll();   // 23차: 브릿지가 storage 를 변이할 수 있음 — 바인드 스냅샷 실체화
            net.minecraft.server.command.CommandManager.callWithContext(source, (context) -> {
                net.minecraft.command.CommandExecutionContext.enqueueProcedureCall(
                        context, procedure, source,
                        net.minecraft.command.ReturnValueConsumer.EMPTY);
                context.run();
            });
            ENTITY_GEN++;   // 브릿지가 summon/kill 했을 수 있으므로 스냅샷/타입버킷 무효화(위 설명 참조)
            OBJ_GEN++;      // 바닐라가 objectives add/remove 했을 수 있음
            invalidateScoreHandles();   // 13차: 브릿지가 scoreboard reset/remove 했을 수 있음
            NAME_GEN++;                 // 22차: 브릿지가 /team·CustomName 을 바꿨을 수 있음
        } catch (Exception ex) {
            System.err.println("[KFC-BRIDGE-ERROR] " + functionId + ": " + ex);
        }
    }

    private static ScoreboardObjective obj(ServerScoreboard sb, String o) {
        return sb.getNullableObjective(o);
    }

    /** 값 읽기 (없으면 null — mcfunction 시맨틱과 일치). */
    private static Integer read(ServerScoreboard sb, String holder, String o) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return null;
        ReadableScoreboardScore s = sb.getScore(holderOf(holder), ob);
        return s == null ? null : s.getScore();
    }

    private static int readOrZero(ServerScoreboard sb, String holder, String o) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return 0;
        // 읽기는 score 엔트리를 생성하지 않는다(getOrCreateScore 는 매 호출 엔트리 생성 +
        // dirty 마킹을 유발해 핫패스에서 매우 비쌌음). 미설정 = 0 시맨틱은 그대로.
        ReadableScoreboardScore s = sb.getScore(holderOf(holder), ob);
        return s == null ? 0 : s.getScore();
    }

    /** score read CSE(_svN) 용 nullable 읽기 — scoreMatches 의 '미설정=false' 시맨틱 원천.
     *  엔트리를 생성하지 않는다(read 와 동일). */
    public static Integer readScore(ServerScoreboard sb, String holder, String o) {
        return read(sb, holder, o);
    }

    /** 공개 스코어 읽기 (store←scoreboard get 등에서 사용). 없으면 0. */
    public static int getScore(ServerScoreboard sb, String holder, String o) {
        return readOrZero(sb, holder, o);
    }

    /** 엔티티의 스코어보드 이름으로 점수 조회 (null/미설정이면 0). */
    public static int getScoreOfEntity(ServerScoreboard sb, net.minecraft.entity.Entity e, String o) {
        if (e == null) return 0;
        return readOrZero(sb, nameOf(e), o);
    }

    public static void setScore(ServerScoreboard sb, String holder, String o, int v) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return;
        sb.getOrCreateScore(holderOf(holder), ob).setScore(v);
    }

    /** scoreboard players enable <holder> <obj> — trigger 잠금 해제. */
    public static void enableTrigger(ServerScoreboard sb, String holder, String o) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return;
        sb.getOrCreateScore(holderOf(holder), ob).unlock();
    }

    /** scoreboard players display numberformat <holder> <obj> fixed <text> —
     *  특정 홀더의 점수를 고정 텍스트(예: "3등")로 표시. 순위 표시의 핵심.
     *  텍스트는 SNBT/TextArgumentType 형식([{font:..,text:..}] 등 unquoted key)일 수 있어
     *  Gson(fromJson)이 아니라 명령 인자 파서(parseTextResolved)로 파싱해야 한다. */
    public static void displayNumberFormatFixed(net.minecraft.server.command.ServerCommandSource source,
                                                ServerScoreboard sb, String holder, String o, String textJson) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return;
        net.minecraft.text.Text t = parseTextResolved(source, source.getEntity(), textJson);
        if (t == null) return;
        sb.getOrCreateScore(holderOf(holder), ob)
                .setNumberFormat(new net.minecraft.scoreboard.number.FixedNumberFormat(t));
    }

    /** scoreboard players display numberformat <holder> <obj> blank — 점수 숨김. */
    public static void displayNumberFormatBlank(ServerScoreboard sb, String holder, String o) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return;
        sb.getOrCreateScore(holderOf(holder), ob)
                .setNumberFormat(net.minecraft.scoreboard.number.BlankNumberFormat.INSTANCE);
    }

    /** scoreboard players display numberformat <holder> <obj> (인자 없음) — 기본 포맷 복원. */
    public static void displayNumberFormatReset(ServerScoreboard sb, String holder, String o) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return;
        sb.getOrCreateScore(holderOf(holder), ob).setNumberFormat(null);
    }

    /** scoreboard players display name <holder> <obj> <text> — 점수 엔트리 표시명 설정.
     *  텍스트는 SNBT/TextArgumentType 형식이므로 parseTextResolved 로 파싱. */
    public static void displayScoreName(net.minecraft.server.command.ServerCommandSource source,
                                        ServerScoreboard sb, String holder, String o, String textJson) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return;
        net.minecraft.text.Text t = parseTextResolved(source, source.getEntity(), textJson);
        sb.getOrCreateScore(holderOf(holder), ob).setDisplayText(t);
    }

    /** scoreboard players display name <holder> <obj> (인자 없음) — 표시명 복원. */
    public static void displayScoreNameReset(ServerScoreboard sb, String holder, String o) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return;
        sb.getOrCreateScore(holderOf(holder), ob).setDisplayText(null);
    }

    /** scoreboard objectives setdisplay <slot> [<objective>] — 슬롯에 표시할 objective 지정(없으면 해제). */
    public static void setObjectiveDisplaySlot(ServerScoreboard sb, String slotName, String objName) {
        net.minecraft.scoreboard.ScoreboardDisplaySlot slot = null;
        for (net.minecraft.scoreboard.ScoreboardDisplaySlot s : net.minecraft.scoreboard.ScoreboardDisplaySlot.values()) {
            if (s.asString().equals(slotName)) { slot = s; break; }
        }
        if (slot == null) return;
        net.minecraft.scoreboard.ScoreboardObjective ob = (objName == null) ? null : obj(sb, objName);
        sb.setObjectiveSlot(slot, ob);
    }

    /** scoreboard objectives modify <obj> numberformat fixed <text> — objective 기본 숫자포맷 고정. */
    public static void objectiveNumberFormatFixed(net.minecraft.server.command.ServerCommandSource source,
                                                  ServerScoreboard sb, String objName, String textJson) {
        ScoreboardObjective ob = obj(sb, objName);
        if (ob == null) return;
        net.minecraft.text.Text t = parseTextResolved(source, source.getEntity(), textJson);
        if (t == null) return;
        ob.setNumberFormat(new net.minecraft.scoreboard.number.FixedNumberFormat(t));
    }

    /** scoreboard objectives modify <obj> numberformat blank — 숫자 숨김. */
    public static void objectiveNumberFormatBlank(ServerScoreboard sb, String objName) {
        ScoreboardObjective ob = obj(sb, objName);
        if (ob == null) return;
        ob.setNumberFormat(net.minecraft.scoreboard.number.BlankNumberFormat.INSTANCE);
    }

    /** scoreboard objectives modify <obj> numberformat (인자 없음) — 기본 복원. */
    public static void objectiveNumberFormatReset(ServerScoreboard sb, String objName) {
        ScoreboardObjective ob = obj(sb, objName);
        if (ob == null) return;
        ob.setNumberFormat(null);
    }

    public static void addScore(ServerScoreboard sb, String holder, String o, int n) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return;
        net.minecraft.scoreboard.ScoreAccess a = sb.getOrCreateScore(holderOf(holder), ob);
        a.setScore(a.getScore() + n);
    }

    public static boolean scoreMatches(ServerScoreboard sb, String holder, String o,
                                       Integer min, Integer max) {
        Integer v = read(sb, holder, o);
        if (v == null) return false;
        if (min != null && v < min) return false;
        if (max != null && v > max) return false;
        return true;
    }

    // 핫패스 점수 매칭(이 데이터팩 ~18.8만 곳): Integer 박싱 없는 primitive 오버로드.
    // 개구간은 Integer.MIN_VALUE/MAX_VALUE 센티넬(점수 정수범위와 수학적으로 동일).
    // read() 의 Integer 반환 박싱도 우회해 호출당 박싱 0.
    public static boolean scoreMatches(ServerScoreboard sb, String holder, String o,
                                       int min, int max) {
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return false;
        ReadableScoreboardScore sc = sb.getScore(holderOf(holder), ob);
        if (sc == null) return false;
        int v = sc.getScore();
        return v >= min && v <= max;
    }

    /** if score <엔티티> <obj> matches — 엔티티 ScoreHolder 직접(미설정=불일치, 바닐라 동일).
     *  (getScoreOfEntity 는 미설정을 0으로 돌려줘 matches 0 판정이 틀어진다 — 조건엔 이걸 쓸 것.) */
    public static boolean scoreMatchesEntity(ServerScoreboard sb, net.minecraft.entity.Entity e,
                                             String o, int min, int max) {
        if (e == null) return false;
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return false;
        ReadableScoreboardScore sc = sb.getScore(e, ob);
        if (sc == null) return false;
        int v = sc.getScore();
        return v >= min && v <= max;
    }

    /** [엔티티-홀더 점수 read CSE 용] 엔티티 홀더의 점수 nullable 읽기. 미설정/​null=null.
     *  sb.getScore(e, ob) 는 e.getNameForScoreboard() 를 키로 쓰므로
     *  scoreMatches(sb, nameOf(e), o, ..)/getScore(sb, nameOf(e), o) 의 홀더 경로
     *  (ScoreHolder.fromName(nameOf(e)))와 조회 키가 동일 → 값 동치. 조건용(미설정=미일치)이라
     *  readOrZero 가 아닌 nullable 을 돌려준다(scoreMatches/​_sv 시맨틱과 일치). */
    public static Integer readScoreEnt(ServerScoreboard sb, net.minecraft.entity.Entity e, String o) {
        if (e == null) return null;
        ScoreboardObjective ob = obj(sb, o);
        if (ob == null) return null;
        ReadableScoreboardScore sc = sb.getScore(e, ob);
        if (sc == null) return null;
        return sc.getScore();
    }

    // ──────────────── ScoreAccess 핸들 캐시 ────────────────
    // setScore/addScore/opScore 가 매 호출 getOrCreateScore(홀더 문자열 해시 조회 + ScoreAccess
    // 래퍼 할당)를 타던 것을 (ObjRef, 홀더)별 핸들 재사용으로 대체한다. 핸들은 스코어보드의
    // 라이브 엔트리에 바인딩되므로 '엔트리가 제거될 수 있는' 모든 지점에서 무효화해야 한다:
    //   · HANDLE_GEN 변화(objective remove / 브릿지·runCommand / 100틱 주기 화해) → 전체 클리어
    //     (13차: 종전 'OBJ_GEN=매 틱' 결합을 해제 — 매 틱 전체 재해소 churn ~2%p 제거.
    //      scoreboard players reset 은 dropHandlesFor 로 해당 홀더만 선별 무효화.)
    //   · 우리 resetScore/resetScoreWildcard(엔트리 제거) → 전체 클리어
    // 시맨틱: 캐시 미스 시 getOrCreateScore(엔트리 존재화 부수효과 포함) — 종전과 동일.
    // 히트 시에도 '이미 존재하는 엔트리'에 대한 재-getOrCreate 는 무변경이므로 관측 동일.
    private static long SH_GEN = Long.MIN_VALUE;
    // 13차: 핸들 캐시 세대 — 종전에는 OBJ_GEN(매 틱 증가)에 묶여 매 틱 전체 클리어됐다.
    // 이제 '엔트리가 고아가 될 수 있는' 경로에서만 증가한다. ScoreAccess/엔트리는 살아있는 한
    // 라이브 객체이므로(값 갱신과 무관) 틱을 넘겨 재사용해도 관측 동일하다.
    private static long HANDLE_GEN = 0;
    private static int HANDLE_RECON_TICK = Integer.MIN_VALUE;
    // 정적 승격 Sch/Rch 셀의 홀더별 레지스트리(선별 무효화용). 셀은 콜사이트당 1회 생성 — 유한.
    private static final java.util.HashMap<String, java.util.ArrayList<Object>> CELLS_BY_HOLDER =
            new java.util.HashMap<>();
    // 19차: 홀더-우선 키잉 — reset <holder> 선별 무효화가 외부 remove 1회(O(1))가 된다.
    private static final java.util.HashMap<String, java.util.HashMap<ObjRef, net.minecraft.scoreboard.ScoreAccess>>
            SCORE_HANDLES = new java.util.HashMap<>();
    // SCORE_HANDLES 가 (어떤 이유로든) 비워질 때마다 증가하는 세대 카운터.
    // 정적 승격된 Sch 셀(pass-4)이 이 값을 스탬프로 삼아, 값이 그대로면 이중 맵 조회를
    // 건너뛰고 캐시된 ScoreAccess 를 직접 쓴다. 맵이 비워지면(=OBJ_GEN 변화 / reset 엔트리 제거)
    // epoch 가 바뀌어 모든 Sch 가 재해소된다 → scoreHandle 매 호출과 정적 동등.
    private static long SCORE_EPOCH = 0;

    private static net.minecraft.scoreboard.ScoreAccess scoreHandle(ServerScoreboard sb, Object holderKey, ObjRef o) {
        if (SH_GEN != HANDLE_GEN) { SCORE_HANDLES.clear(); READ_HANDLES.clear(); SCORE_EPOCH++; SH_GEN = HANDLE_GEN; }
        String hn = (holderKey instanceof net.minecraft.entity.Entity e0)
                ? nameOf(e0) : (String) holderKey;
        java.util.HashMap<ObjRef, net.minecraft.scoreboard.ScoreAccess> m = SCORE_HANDLES.get(hn);
        if (m == null) { m = new java.util.HashMap<>(); SCORE_HANDLES.put(hn, m); }
        net.minecraft.scoreboard.ScoreAccess a = m.get(o);
        if (a == null) {
            ScoreboardObjective ob = obj(sb, o);
            if (ob == null) return null;
            ScoreHolder h = (holderKey instanceof net.minecraft.entity.Entity e)
                    ? e : holderOf(hn);
            a = sb.getOrCreateScore(h, ob);
            m.put(o, a);
        }
        return a;
    }

    private static void invalidateScoreHandles() {
        SCORE_HANDLES.clear(); READ_HANDLES.clear(); SCORE_EPOCH++;
        HANDLE_GEN++; SH_GEN = HANDLE_GEN;   // 지연 검사(scoreHandle/readHandle)와 즉시 동기
    }

    // ──────────────── 외부 명령 실행 후 캐시 화해 (25차) ────────────────
    // KfcFuncCoherenceMixin 이 net.minecraft.server.command.CommandManager.executeWithPrefix 의
    // HEAD 에서 markExternalCommand() 를 호출한다. 이 메서드는 '모든 명령 문자열 실행'의 단일
    // 관문이다 — 커맨드블럭(CommandBlockExecutor)·플레이어 채팅 명령·/function·타 데이터팩이 전부
    // 여기를 지난다. (종전 CommandFunctionManager.execute 는 현대 MC 의 스텝 실행 리팩터로 /function
    // 경로가 안 타서 감지 실패했다 — executeWithPrefix 가 올바른 관문. 우리 브릿지 runCommand 도
    // 이 메서드를 쓰므로 시그니처가 검증돼 있다.)
    // 화해 자체는 명령 실행 중이 아니라 '다음 네이티브 접근'(getOrCreateContext)으로 지연한다:
    //   · 명령 실행에 부하 0(플래그 세팅만), 틱 내 다중 외부 명령은 1회 화해로 coalesce.
    //   · 네이티브는 외부 명령 실행 중 재진입하지 않으므로, 소비 시점엔 변이가 반영돼 있다.
    // 우리 네이티브 틱(생성 executeReturn 직접호출)과 함수 브릿지(callWithContext)는 executeWithPrefix
    // 를 안 타므로 정상 경로 캐시는 유지된다(성능). 화해 규약은 브릿지 직후와 동일:
    //   ENTITY_GEN++ → 태그버킷/NBT스냅샷/타입인덱스/엔티티스냅샷 연쇄 무효화
    //   OBJ_GEN++    → objectives add/remove 반영,  invalidateScoreHandles() → detached 점수핸들 폐기,
    //   NAME_GEN++   → /team·CustomName 반영.
    // [스레드 노트 준수] 커맨드 실행·네이티브 틱 모두 서버 메인 스레드 전용(상단 스레드 노트)이라
    // 이 플래그도 단일 스레드에서만 쓰인다 — writer=markExternalCommand(executeWithPrefix HEAD),
    // reader=getOrCreateContext(네이티브 틱). 가드하는 캐시들이 전부 non-volatile HashMap 이므로
    // volatile 은 실질 안전 이득 없이 hot path(executeReturn 마다 읽음)에 메모리 배리어만 부과했다 → 제거.
    private static boolean EXTERNAL_DIRTY = false;
    // 믹스인 발동 여부는 SCS(SCS_FUSE_LOGGED)와 동일하게 '1회 활성 로그'로만 확인한다. 작동이
    // 검증돼 종전의 !MIXIN_PROVEN per-tick 폴백 분기는 제거했다 — 상시 dirty-flag 즉시 화해 +
    // 100틱 안전망 경로로만 동작한다(이유 없는 분기 제거).
    private static boolean COHERENCE_LOGGED = false;
    // 진단 토글: -Dkfc.debug.coherence=true 로 켜면 executeWithPrefix 훅이 '발동할 때마다' 명령
    // 문자열과 함께 로그 → 훅이 실제로 붙어 도는지 명령별로 확인(일회성 활성 로그의 한계 보완:
    // 활성 로그는 부팅 초기 load-tag 명령/내부 runCommand 가 이미 소비해 이후 안 보일 수 있음).
    // 미설정 시 per-call 비용은 static final boolean 분기 1회(무시 가능).
    private static final boolean DEBUG_COHERENCE = Boolean.getBoolean("kfc.debug.coherence");
    public static void markExternalCommand(String command) {
        if (!COHERENCE_LOGGED) {
            COHERENCE_LOGGED = true;
            System.out.println("[KFC] external-command coherence mixin active (CommandManager.executeWithPrefix)");
        }
        if (DEBUG_COHERENCE) System.out.println("[KFC] executeWithPrefix fired: /" + command);
        EXTERNAL_DIRTY = true;
    }
    public static void onExternalFunctionExecuted() {
        ENTITY_GEN++;
        OBJ_GEN++;
        invalidateScoreHandles();
        NAME_GEN++;
    }

    /** 13차 도입, 19차 개정: scoreboard players reset <holder> 의 선별 무효화.
     *  [실측 19차] 종전 (ObjRef→홀더) 키잉에선 리셋마다 전 맵 Entity-키 스캔(dropHolderKey)이
     *  필요해 그 자체가 2.6%p 핫스팟이 됐다. 홀더-우선 키잉으로 외부 remove 1회 = O(1).
     *  대상: (a) 정적 승격 Sch/Rch 셀(홀더별 레지스트리), (b) 두 핸들 맵의 홀더 서브트리.
     *  다른 홀더의 핸들은 그대로 유효(removeScore(s) 는 해당 홀더의 엔트리만 제거한다).
     *  objective 구분 없이 홀더 전체를 지우는 과잉 무효화는 안전(재해소는 멱등). */
    private static void dropHandlesFor(String holder) {
        java.util.ArrayList<Object> cells = CELLS_BY_HOLDER.get(holder);
        if (cells != null) {
            for (Object c : cells) {
                if (c instanceof Sch s) s.acc = null;
                else if (c instanceof Rch r) r.sc = null;
            }
        }
        SCORE_HANDLES.remove(holder);
        READ_HANDLES.remove(holder);
    }

    // ── 읽기 엔트리 핸들 캐시 ──
    // scoreMatchesEntity/readScoreEnt/readScore/getScore(ObjRef 판)가 매 호출 하던
    // 'holder 이름 해소 + 스코어보드 이중 맵 조회'를 (ObjRef, 홀더키)→엔트리 캐시로 대체.
    // 값은 항상 라이브 엔트리에서 읽으므로(getScore()) 캐시는 '엔트리 신원'만 보관 — 값 갱신과
    // 무관하게 정확하다. 존재하는 엔트리만 캐시(null 미캐시)라 관측 부수효과가 전혀 없다.
    private static final java.util.HashMap<String, java.util.HashMap<ObjRef, ReadableScoreboardScore>>
            READ_HANDLES = new java.util.HashMap<>();

    private static ReadableScoreboardScore readHandle(ServerScoreboard sb, Object holderKey, ObjRef o) {
        if (SH_GEN != HANDLE_GEN) { SCORE_HANDLES.clear(); READ_HANDLES.clear(); SCORE_EPOCH++; SH_GEN = HANDLE_GEN; }
        String hn = (holderKey instanceof net.minecraft.entity.Entity e0)
                ? nameOf(e0) : (String) holderKey;
        java.util.HashMap<ObjRef, ReadableScoreboardScore> m = READ_HANDLES.get(hn);
        if (m == null) { m = new java.util.HashMap<>(); READ_HANDLES.put(hn, m); }
        ReadableScoreboardScore sc = m.get(o);
        if (sc == null) {
            ScoreboardObjective ob = obj(sb, o);
            if (ob == null) return null;
            sc = sb.getScore(holderOf(hn), ob);
            if (sc != null) m.put(o, sc);          // 존재 엔트리만 캐시(null 미캐시 — 부수효과 0)
        }
        return sc;
    }

    // ──────────────── Sch: (상수 홀더, 상수 objective) 쓰기 핸들의 정적 승격 ────────────────
    // pass-4 가 setScore/addScore(sb, "리터럴홀더", KFC_OBJ_n, v) 를 static final Sch 필드로
    // 접어(collapse) sb·값만 남긴다. resolve 는 SCORE_EPOCH 스탬프가 일치하면 SCORE_HANDLES
    // 이중 조회(ObjRef→홀더)를 통째로 건너뛰고 캐시된 ScoreAccess 를 반환한다. epoch 불일치/
    // 최초 접근 시에만 기존 scoreHandle(맵 경로)로 폴백 — getOrCreateScore 부수효과·무효화가
    // 종전과 완전히 동일하므로 관측 동등(쓰기 전용; 읽기는 엔트리 생성 부수효과 때문에 미승격).
    public static final class Sch {
        final String holder; final ObjRef obj;
        net.minecraft.scoreboard.ScoreAccess acc;
        long gen = Long.MIN_VALUE;
        Sch(String holder, ObjRef obj) { this.holder = holder; this.obj = obj; }
    }
    public static Sch sch(String holder, ObjRef o) {
        Sch s = new Sch(holder, o);
        CELLS_BY_HOLDER.computeIfAbsent(holder, k -> new java.util.ArrayList<>()).add(s);   // 13차
        return s;
    }

    private static net.minecraft.scoreboard.ScoreAccess resolve(ServerScoreboard sb, Sch h) {
        if (h.acc == null || h.gen != SCORE_EPOCH) {
            h.acc = scoreHandle(sb, h.holder, h.obj);   // 미스: 맵 경로(엔트리 존재화 포함) — 종전 동일
            h.gen = SCORE_EPOCH;                          // scoreHandle 이 epoch 를 올렸다면 그 값으로 스탬프
        }
        return h.acc;
    }

    public static void setScore(ServerScoreboard sb, Sch h, int v) {
        net.minecraft.scoreboard.ScoreAccess a = resolve(sb, h);
        if (a != null) a.setScore(v);
    }
    public static void addScore(ServerScoreboard sb, Sch h, int n) {
        net.minecraft.scoreboard.ScoreAccess a = resolve(sb, h);
        if (a != null) a.setScore(a.getScore() + n);
    }

    // ──────────────── Rch: (상수 홀더, 상수 objective) '읽기' 핸들의 정적 승격 ────────────────
    // Sch(쓰기)와 대칭 — pass-4 가 scoreMatches/getScore/readScore/scoreCmp 의 리터럴 홀더 +
    // KFC_OBJ 인자쌍을 static final Rch 로 접는다. resolveR 은 SCORE_EPOCH 스탬프가 맞고 엔트리가
    // 캐시돼 있으면 READ_HANDLES 이중 조회를 통째로 건너뛴다. null(엔트리 부재)은 스탬프해도
    // 같은 epoch 안에서 '쓰기'(getOrCreateScore)가 엔트리를 만들 수 있으므로 캐시하지 않고
    // 매 호출 재해소(readHandle 의 null-미캐시 계약과 동일 — 관측 동등).
    public static final class Rch {
        final String holder; final ObjRef obj;
        ReadableScoreboardScore sc;
        long gen = Long.MIN_VALUE;
        Rch(String holder, ObjRef obj) { this.holder = holder; this.obj = obj; }
    }
    public static Rch rch(String holder, ObjRef o) {
        Rch r = new Rch(holder, o);
        CELLS_BY_HOLDER.computeIfAbsent(holder, k -> new java.util.ArrayList<>()).add(r);   // 13차
        return r;
    }

    private static ReadableScoreboardScore resolveR(ServerScoreboard sb, Rch h) {
        ReadableScoreboardScore sc = h.sc;
        if (sc != null && h.gen == SCORE_EPOCH) return sc;
        sc = readHandle(sb, h.holder, h.obj);
        h.sc = sc;
        h.gen = SCORE_EPOCH;
        return sc;
    }

    public static boolean scoreMatches(ServerScoreboard sb, Rch h, int min, int max) {
        ReadableScoreboardScore sc = resolveR(sb, h);
        if (sc == null) return false;
        int v = sc.getScore();
        return v >= min && v <= max;
    }
    public static boolean scoreMatches(ServerScoreboard sb, Rch h, Integer min, Integer max) {
        ReadableScoreboardScore sc = resolveR(sb, h);
        if (sc == null) return false;
        int v = sc.getScore();
        if (min != null && v < min) return false;
        if (max != null && v > max) return false;
        return true;
    }
    public static int getScore(ServerScoreboard sb, Rch h) {
        ReadableScoreboardScore sc = resolveR(sb, h);
        return sc == null ? 0 : sc.getScore();
    }
    public static Integer readScore(ServerScoreboard sb, Rch h) {
        ReadableScoreboardScore sc = resolveR(sb, h);
        return sc == null ? null : sc.getScore();
    }
    public static boolean scoreCmp(ServerScoreboard sb, Rch a, String op, Rch b) {
        ReadableScoreboardScore sa = resolveR(sb, a), sbb = resolveR(sb, b);
        return sa != null && sbb != null && cmpOp(sa.getScore(), op, sbb.getScore());
    }

    // ── Sch 확장: 상수 (홀더, objective) 의 opScoreN/opScore 도 정적 셀 경유 ──
    public static void opScoreN(ServerScoreboard sb, Sch h, String op, int n) {
        if (obj(sb, h.obj) == null) return;   // String 판과 동일: objective 부재 = 부수효과 없음
        net.minecraft.scoreboard.ScoreAccess a = resolve(sb, h);
        if (a != null) _opN(a, op, n);
    }
    public static void opScore(ServerScoreboard sb, Sch d, String op, Sch s) {
        // opScoreH 와 동일: 두 objective 해소 선검사(부재 시 어느 엔트리도 존재화하지 않음)
        if (obj(sb, d.obj) == null || obj(sb, s.obj) == null) return;
        net.minecraft.scoreboard.ScoreAccess da = resolve(sb, d);
        if (da == null) return;
        net.minecraft.scoreboard.ScoreAccess sa = resolve(sb, s);
        if (sa == null) return;
        int b = sa.getScore();
        int r;
        switch (op) {
            case "=":  r = b; break;
            case "+=": r = da.getScore() + b; break;
            case "-=": r = da.getScore() - b; break;
            case "*=": r = da.getScore() * b; break;
            case "/=": if (b == 0) return; r = Math.floorDiv(da.getScore(), b); break;
            case "%=": if (b == 0) return; r = Math.floorMod(da.getScore(), b); break;
            case "<":  { int a = da.getScore(); r = Math.min(a, b); break; }
            case ">":  { int a = da.getScore(); r = Math.max(a, b); break; }
            case "><": { int a = da.getScore(); da.setScore(b); sa.setScore(a); return; }
            default: return;
        }
        da.setScore(r);
    }

    // ──────────────── ObjRef 오버로드 (merge_pass pass-4 가 상수 objective 를 승격) ────────────────
    // String 판과 시맨틱 완전 동일 — objective 해소만 캐시(obj(sb, ref)) 경유.
    public static boolean scoreMatches(ServerScoreboard sb, String holder, ObjRef o,
                                       Integer min, Integer max) {
        Integer v = readScore(sb, holder, o);
        if (v == null) return false;
        if (min != null && v < min) return false;
        if (max != null && v > max) return false;
        return true;
    }
    public static boolean scoreMatches(ServerScoreboard sb, String holder, ObjRef o, int min, int max) {
        ReadableScoreboardScore sc = readHandle(sb, holder, o);
        if (sc == null) return false;
        int v = sc.getScore();
        return v >= min && v <= max;
    }
    public static boolean scoreMatchesEntity(ServerScoreboard sb, net.minecraft.entity.Entity e,
                                             ObjRef o, int min, int max) {
        if (e == null) return false;
        ReadableScoreboardScore sc = readHandle(sb, e, o);
        if (sc == null) return false;
        int v = sc.getScore();
        return v >= min && v <= max;
    }
    public static Integer readScoreEnt(ServerScoreboard sb, net.minecraft.entity.Entity e, ObjRef o) {
        if (e == null) return null;
        ReadableScoreboardScore sc = readHandle(sb, e, o);
        if (sc == null) return null;
        return sc.getScore();
    }
    public static Integer readScore(ServerScoreboard sb, String holder, ObjRef o) {
        ReadableScoreboardScore s = readHandle(sb, holder, o);
        return s == null ? null : s.getScore();
    }
    public static int getScore(ServerScoreboard sb, String holder, ObjRef o) {
        ReadableScoreboardScore s = readHandle(sb, holder, o);
        return s == null ? 0 : s.getScore();
    }
    public static int getScoreOfEntity(ServerScoreboard sb, net.minecraft.entity.Entity e, ObjRef o) {
        if (e == null) return 0;
        ReadableScoreboardScore s = readHandle(sb, e, o);
        return s == null ? 0 : s.getScore();
    }
    public static void setScore(ServerScoreboard sb, String holder, ObjRef o, int v) {
        net.minecraft.scoreboard.ScoreAccess a = scoreHandle(sb, holder, o);
        if (a != null) a.setScore(v);
    }
    public static void addScore(ServerScoreboard sb, String holder, ObjRef o, int n) {
        net.minecraft.scoreboard.ScoreAccess a = scoreHandle(sb, holder, o);
        if (a != null) a.setScore(a.getScore() + n);
    }
    // ── 엔티티 직접판(#2) — nameOf→holderOf 문자열 왕복 제거. 홀더 키 = 엔티티 신원. ──
    public static void setScore(ServerScoreboard sb, net.minecraft.entity.Entity e, ObjRef o, int v) {
        if (e == null) return;
        net.minecraft.scoreboard.ScoreAccess a = scoreHandle(sb, e, o);
        if (a != null) a.setScore(v);
    }
    public static void addScore(ServerScoreboard sb, net.minecraft.entity.Entity e, ObjRef o, int n) {
        if (e == null) return;
        net.minecraft.scoreboard.ScoreAccess a = scoreHandle(sb, e, o);
        if (a != null) a.setScore(a.getScore() + n);
    }
    public static void setScore(ServerScoreboard sb, net.minecraft.entity.Entity e, String o, int v) {
        if (e == null) return;
        setScore(sb, nameOf(e), o, v);
    }
    public static void addScore(ServerScoreboard sb, net.minecraft.entity.Entity e, String o, int n) {
        if (e == null) return;
        addScore(sb, nameOf(e), o, n);
    }
    public static void resetScore(ServerScoreboard sb, net.minecraft.entity.Entity e, String o) {
        if (e == null) return;
        resetScore(sb, nameOf(e), o);
    }
    public static int getScore(ServerScoreboard sb, net.minecraft.entity.Entity e, String o) {
        return getScoreOfEntity(sb, e, o);
    }
    public static int getScore(ServerScoreboard sb, net.minecraft.entity.Entity e, ObjRef o) {
        return getScoreOfEntity(sb, e, o);
    }
    public static Integer readScore(ServerScoreboard sb, net.minecraft.entity.Entity e, ObjRef o) {
        return readScoreEnt(sb, e, o);
    }
    public static Integer readScore(ServerScoreboard sb, net.minecraft.entity.Entity e, String o) {
        if (e == null) return null;
        return readScore(sb, nameOf(e), o);
    }
    public static void enableTrigger(ServerScoreboard sb, net.minecraft.entity.Entity e, String o) {
        if (e == null) return;
        enableTrigger(sb, nameOf(e), o);
    }
    // opScore 엔티티 홀더 조합(String objective 판 — pass-4 이전 레코드용 위임)
    public static void opScore(ServerScoreboard sb, net.minecraft.entity.Entity de, String dobj, String op,
                               String sh, String sobj) {
        if (de == null) return;
        opScore(sb, nameOf(de), dobj, op, sh, sobj);
    }
    public static void opScore(ServerScoreboard sb, String dh, String dobj, String op,
                               net.minecraft.entity.Entity se, String sobj) {
        if (se == null) return;
        opScore(sb, dh, dobj, op, nameOf(se), sobj);
    }
    public static void opScore(ServerScoreboard sb, net.minecraft.entity.Entity de, String dobj, String op,
                               net.minecraft.entity.Entity se, String sobj) {
        if (de == null || se == null) return;
        opScore(sb, nameOf(de), dobj, op, nameOf(se), sobj);
    }
    // opScore 엔티티 홀더 조합(ObjRef 판 — pass-4 승격 후, 핸들 캐시 직결)
    public static void opScore(ServerScoreboard sb, net.minecraft.entity.Entity de, ObjRef dobj, String op,
                               String sh, ObjRef sobj) {
        if (de == null) return;
        opScoreH(sb, de, dobj, op, sh, sobj);
    }
    public static void opScore(ServerScoreboard sb, String dh, ObjRef dobj, String op,
                               net.minecraft.entity.Entity se, ObjRef sobj) {
        if (se == null) return;
        opScoreH(sb, dh, dobj, op, se, sobj);
    }
    public static void opScore(ServerScoreboard sb, net.minecraft.entity.Entity de, ObjRef dobj, String op,
                               net.minecraft.entity.Entity se, ObjRef sobj) {
        if (de == null || se == null) return;
        opScoreH(sb, de, dobj, op, se, sobj);
    }
    public static void opScore(ServerScoreboard sb, String dh, ObjRef dobj, String op,
                               String sh, ObjRef sobj) {
        opScoreH(sb, dh, dobj, op, sh, sobj);
    }
    /** opScore 공통 본체 — 홀더 키(String|Entity) 불문 핸들 캐시 경유. String 판과 시맨틱 동일. */
    private static void opScoreH(ServerScoreboard sb, Object dh, ObjRef dobj, String op,
                                 Object sh, ObjRef sobj) {
        // String 판 opScore 와 동일: 두 objective 해소를 '먼저' 검사(어느 한쪽 부재 시 아무
        // 부수효과 없이 반환 — 원판과 엔트리 존재화 순서/조건 동일), 그 후 대상·소스
        // getOrCreateScore(존재화 부수효과 포함) — 핸들 캐시 경유.
        if (obj(sb, dobj) == null || obj(sb, sobj) == null) return;
        net.minecraft.scoreboard.ScoreAccess da = scoreHandle(sb, dh, dobj);
        if (da == null) return;
        net.minecraft.scoreboard.ScoreAccess sa = scoreHandle(sb, sh, sobj);
        if (sa == null) return;
        int b = sa.getScore();
        int r;
        switch (op) {
            case "=":  r = b; break;
            case "+=": r = da.getScore() + b; break;
            case "-=": r = da.getScore() - b; break;
            case "*=": r = da.getScore() * b; break;
            case "/=": if (b == 0) return; r = Math.floorDiv(da.getScore(), b); break;
            case "%=": if (b == 0) return; r = Math.floorMod(da.getScore(), b); break;
            case "<":  { int a = da.getScore(); r = Math.min(a, b); break; }
            case ">":  { int a = da.getScore(); r = Math.max(a, b); break; }
            case "><": { int a = da.getScore(); da.setScore(b); sa.setScore(a); return; }
            default: return;
        }
        da.setScore(r);
    }

    /** if score <a> OP <b> (비교형). 둘 중 하나라도 값 없으면 false(=mcfunction 시맨틱). */
    public static boolean scoreCmp(ServerScoreboard sb, String ha, String oa,
                                   String op, String hb, String ob) {
        Integer a = read(sb, ha, oa), b = read(sb, hb, ob);
        if (a == null || b == null) return false;
        switch (op) {
            case "<":  return a < b;
            case "<=": return a <= b;
            case "=":  return a.intValue() == b.intValue();
            case ">=": return a >= b;
            case ">":  return a > b;
            default:   return false;
        }
    }

    // 셀렉터 홀더(단일 엔티티) 비교형 — 인자로 1회 평가, null 이면 false.
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                   String op, String hb, String ob) {
        if (ea == null) return false;
        return scoreCmp(sb, nameOf(ea), oa, op, hb, ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, String ha, String oa,
                                   String op, net.minecraft.entity.Entity eb, String ob) {
        if (eb == null) return false;
        return scoreCmp(sb, ha, oa, op, nameOf(eb), ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                   String op, net.minecraft.entity.Entity eb, String ob) {
        if (ea == null || eb == null) return false;
        return scoreCmp(sb, nameOf(ea), oa, op, nameOf(eb), ob);
    }

    // neg(=unless) 인자판: 셀렉터 홀더가 비면 false(미실행), neg 는 비교 결과에만 적용.
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                   String op, String hb, String ob, boolean neg) {
        if (ea == null) return false;
        return neg ^ scoreCmp(sb, nameOf(ea), oa, op, hb, ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, String ha, String oa,
                                   String op, net.minecraft.entity.Entity eb, String ob, boolean neg) {
        if (eb == null) return false;
        return neg ^ scoreCmp(sb, ha, oa, op, nameOf(eb), ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                   String op, net.minecraft.entity.Entity eb, String ob, boolean neg) {
        if (ea == null || eb == null) return false;
        return neg ^ scoreCmp(sb, nameOf(ea), oa, op, nameOf(eb), ob);
    }

    // ──────────────── scoreCmp ObjRef 오버로드 ────────────────
    // pass-4 가 상수 objective 를 ObjRef 로 승격 — String 판과 시맨틱 동일하되 읽기가
    // readHandle(엔트리 핸들 캐시) 경유. '어느 한쪽 미설정 → false' 규칙 유지(null 미캐시).
    private static Integer readVal(ServerScoreboard sb, Object holderKey, ObjRef o) {
        ReadableScoreboardScore sc = readHandle(sb, holderKey, o);
        return sc == null ? null : sc.getScore();
    }
    private static boolean cmpOp(int a, String op, int b) {
        switch (op) {
            case "<":  return a < b;
            case "<=": return a <= b;
            case "=":  return a == b;
            case ">=": return a >= b;
            case ">":  return a > b;
            default:   return false;
        }
    }
    public static boolean scoreCmp(ServerScoreboard sb, String ha, ObjRef oa,
                                   String op, String hb, ObjRef ob) {
        Integer a = readVal(sb, ha, oa), b = readVal(sb, hb, ob);
        return a != null && b != null && cmpOp(a, op, b);
    }
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, ObjRef oa,
                                   String op, String hb, ObjRef ob) {
        if (ea == null) return false;
        Integer a = readVal(sb, ea, oa), b = readVal(sb, hb, ob);
        return a != null && b != null && cmpOp(a, op, b);
    }
    public static boolean scoreCmp(ServerScoreboard sb, String ha, ObjRef oa,
                                   String op, net.minecraft.entity.Entity eb, ObjRef ob) {
        if (eb == null) return false;
        Integer a = readVal(sb, ha, oa), b = readVal(sb, eb, ob);
        return a != null && b != null && cmpOp(a, op, b);
    }
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, ObjRef oa,
                                   String op, net.minecraft.entity.Entity eb, ObjRef ob) {
        if (ea == null || eb == null) return false;
        Integer a = readVal(sb, ea, oa), b = readVal(sb, eb, ob);
        return a != null && b != null && cmpOp(a, op, b);
    }
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, ObjRef oa,
                                   String op, String hb, ObjRef ob, boolean neg) {
        if (ea == null) return false;
        return neg ^ scoreCmp(sb, ea, oa, op, hb, ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, String ha, ObjRef oa,
                                   String op, net.minecraft.entity.Entity eb, ObjRef ob, boolean neg) {
        if (eb == null) return false;
        return neg ^ scoreCmp(sb, ha, oa, op, eb, ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, ObjRef oa,
                                   String op, net.minecraft.entity.Entity eb, ObjRef ob, boolean neg) {
        if (ea == null || eb == null) return false;
        return neg ^ scoreCmp(sb, ea, oa, op, eb, ob);
    }

    // ──────────────── 상수 소스 opScore (const_fold 확장) ────────────────
    // `scoreboard players operation D DO <op> #상수 O` 의 소스가 폴딩 상수 N 으로 증명된 경우.
    // 소스 getOrCreateScore 부수효과는 '이미 존재(load 시 set)+무변경'이라 관측 불변(const_fold 논증).
    public static void opScoreN(ServerScoreboard sb, String dh, ObjRef dobj, String op, int n) {
        net.minecraft.scoreboard.ScoreAccess da = scoreHandle(sb, dh, dobj);
        if (da != null) _opN(da, op, n);
    }
    public static void opScoreN(ServerScoreboard sb, net.minecraft.entity.Entity de, ObjRef dobj, String op, int n) {
        if (de == null) return;
        net.minecraft.scoreboard.ScoreAccess da = scoreHandle(sb, de, dobj);
        if (da != null) _opN(da, op, n);
    }
    public static void opScoreN(ServerScoreboard sb, String dh, String dobj, String op, int n) {
        ScoreboardObjective ob = obj(sb, dobj);
        if (ob == null) return;
        _opN(sb.getOrCreateScore(holderOf(dh), ob), op, n);
    }
    public static void opScoreN(ServerScoreboard sb, net.minecraft.entity.Entity de, String dobj, String op, int n) {
        if (de == null) return;
        opScoreN(sb, nameOf(de), dobj, op, n);
    }
    private static void _opN(net.minecraft.scoreboard.ScoreAccess da, String op, int b) {
        int r;
        switch (op) {
            case "=":  r = b; break;
            case "+=": r = da.getScore() + b; break;
            case "-=": r = da.getScore() - b; break;
            case "*=": r = da.getScore() * b; break;
            case "/=": if (b == 0) return; r = Math.floorDiv(da.getScore(), b); break;
            case "%=": if (b == 0) return; r = Math.floorMod(da.getScore(), b); break;
            case "<":  r = Math.min(da.getScore(), b); break;
            case ">":  r = Math.max(da.getScore(), b); break;
            default: return;
        }
        da.setScore(r);
    }

    /** 강등(score-demote) 지역 연산용 — mcfunction /= 시맨틱(0 나누기 = 무변경, floorDiv). */
    public static int sdiv(int a, int b) { return b == 0 ? a : Math.floorDiv(a, b); }
    /** 강등 지역 연산용 — mcfunction %= 시맨틱(0 나머지 = 무변경, floorMod). */
    public static int smod(int a, int b) { return b == 0 ? a : Math.floorMod(a, b); }

    // ──────────────── 상수 폴딩 비교 헬퍼 (const_fold 확장 #3①) ────────────────
    // scoreCmp 의 한쪽이 폴딩된 상수일 때: 상수 측은 '항상 설정'이 증명돼 있으므로
    // 나머지 측 읽기 + null 검사 + 순수 int 비교로 재작성된다. 시맨틱 동일
    // (양측 미설정 → false 규칙에서 상수 측은 설정 확정).
    private static boolean _cmpI(int a, String op, int b) {
        switch (op) {
            case "<":  return a < b;
            case "<=": return a <= b;
            case "=":  return a == b;
            case ">=": return a >= b;
            case ">":  return a > b;
            default:   return false;
        }
    }
    public static boolean scoreCmpL(ServerScoreboard sb, int a, String op, String hb, String ob) {
        Integer b = read(sb, hb, ob);
        return b != null && _cmpI(a, op, b);
    }
    public static boolean scoreCmpL(ServerScoreboard sb, int a, String op,
                                    net.minecraft.entity.Entity eb, String ob) {
        if (eb == null) return false;
        return scoreCmpL(sb, a, op, nameOf(eb), ob);
    }
    public static boolean scoreCmpL(ServerScoreboard sb, int a, String op,
                                    net.minecraft.entity.Entity eb, String ob, boolean neg) {
        if (eb == null) return false;
        return neg ^ scoreCmpL(sb, a, op, nameOf(eb), ob);
    }
    public static boolean scoreCmpR(ServerScoreboard sb, String ha, String oa, String op, int b) {
        Integer a = read(sb, ha, oa);
        return a != null && _cmpI(a, op, b);
    }
    public static boolean scoreCmpR(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                    String op, int b) {
        if (ea == null) return false;
        return scoreCmpR(sb, nameOf(ea), oa, op, b);
    }
    public static boolean scoreCmpR(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                    String op, int b, boolean neg) {
        if (ea == null) return false;
        return neg ^ scoreCmpR(sb, nameOf(ea), oa, op, b);
    }

    // ──────────────── if entity <selector> 존재 검사 ────────────────
    /** 거대 SNBT 문자열 연결 — 클래스파일 상수(CONSTANT_Utf8) 65535B 한계를 넘는 리터럴을
     *  emit 이 청크로 분할해 이 메서드로 연결한다("a"+"b"는 컴파일타임 폴딩되므로 불가).
     *  호출부는 pass-4 가 private static final 로 호이스팅해 클래스로드 시 1회만 연결된다. */
    public static String cat(String... parts) {
        int n = 0;
        for (String p : parts) n += p.length();
        StringBuilder sb = new StringBuilder(n);
        for (String p : parts) sb.append(p);
        return sb.toString();
    }

    private static boolean matchTags(net.minecraft.entity.Entity e, String[] pos, String[] neg) {
        if (pos.length == 0 && neg.length == 0) return true;   // 태그 필터 없음 — getCommandTags 호출조차 생략
        java.util.Set<String> tags = e.getCommandTags();        // 1회만 조회(매 태그마다 호출 안 함)
        for (String t : pos) if (!tags.contains(t)) return false;
        for (String t : neg) if (tags.contains(t)) return false;
        return true;
    }

    /** @e/@n 셀렉터 해소 전용 매치 — 바닐라 EntitySelectorReader 는 @e/@n 에만 기본 술어
     *  Entity::isAlive 를 추가한다(바이트코드 확인: readAtVariable 의 e/n 케이스만 플래그=1).
     *  LivingEntity.isAlive = !isRemoved && health>0 이라 /kill 직후(시체 애니메이션 20틱 동안)
     *  즉시 선택에서 제외 — 컷씬 spectate 가 죽은 주민과 새 주민 사이를 오가던 플리커의 원인 수정.
     *  (@a/@p/@r/@s 는 dead 포함이므로 플레이어 경로는 matchTags 를 그대로 사용해야 한다.) */
    private static boolean matchTagsAlive(net.minecraft.entity.Entity e, String[] pos, String[] neg) {
        return e.isAlive() && matchTags(e, pos, neg);
    }

    private static boolean inRange(net.minecraft.entity.Entity origin, net.minecraft.entity.Entity e,
                                   double minDist, double maxDist) {
        if (origin == null || (minDist < 0 && maxDist < 0)) return true;
        double d = origin.squaredDistanceTo(e);
        if (minDist >= 0 && d < minDist * minDist) return false;
        if (maxDist >= 0 && d > maxDist * maxDist) return false;
        return true;
    }

    // ── 소스 위치(Vec3d) 기준 셀렉터 평가 ──
    // 바닐라 셀렉터의 distance= 와 @n/@p 최근접 정렬은 '실행 소스의 위치' 기준이다
    // (executor 위치가 아님 — positioned/at 재바인딩 후엔 둘이 다르다).
    public static boolean inRange(net.minecraft.util.math.Vec3d origin, net.minecraft.entity.Entity e,
                                  double minDist, double maxDist) {
        if (origin == null || (minDist < 0 && maxDist < 0)) return true;
        double d = e.getPos().squaredDistanceTo(origin);
        if (minDist >= 0 && d < minDist * minDist) return false;
        if (maxDist >= 0 && d > maxDist * maxDist) return false;
        return true;
    }

    public static boolean anyEntity(GameContext ctx, net.minecraft.util.math.Vec3d origin,
                                    net.minecraft.entity.EntityType<?> type,
                                    String[] tagsPos, String[] tagsNeg,
                                    double minDist, double maxDist) {
        // predicate 를 조회 안으로 밀고, distance 상한이 있으면 Box 한정 섹션 스캔.
        if (QUERY_BOX && origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (e.getType() == type && matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return true;
                }
                return false;
            }
            return anyInBoxTyped(ctx, type, rangeBox(origin, maxDist),
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
        }
        // 거리 무제한: 전체 수집+isEmpty 대신 typeBucket 순회 + 첫 매치 early-return(존재 의미 동일).
        boolean _unb = (minDist < 0 && maxDist < 0);   // 거리 무제한 = 위치 무관 → 메모 가능
        if (_unb) {
            Boolean _m = anyMemoGet(ctx, type, tagsPos, tagsNeg);
            if (_m != null) return _m;
        }
        java.util.List<net.minecraft.entity.Entity> _tb = tagCandidates(ctx, tagsPos);
        for (net.minecraft.entity.Entity e : (_tb != null ? _tb : typeBucket(ctx, type))) {
            if (_tb != null && e.getType() != type) continue;   // 태그버킷 경로: 타입 필터
            if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist))
                return _unb ? anyMemoPut(type, tagsPos, tagsNeg, true) : true;
        }
        return _unb ? anyMemoPut(type, tagsPos, tagsNeg, false) : false;
    }

    public static boolean anyEntityAnyType(GameContext ctx, net.minecraft.util.math.Vec3d origin,
                                           String[] tagsPos, String[] tagsNeg,
                                           double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return true;
                }
                return false;
            }
            return firstInBox(ctx, origin, maxDist,
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)) != null;
        }
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
            if (!inRange(origin, e, minDist, maxDist)) continue;
            return true;
        }
        return false;
    }

    // 컴파일타임에 풀 수 없는 엔티티 '타입 태그'(#mod:tag)를 런타임 EntityType.isIn 으로 검사하는 존재검사.
    public static boolean anyEntityInTypeTag(GameContext ctx, net.minecraft.util.math.Vec3d origin,
                                             String tagId, String[] tagsPos, String[] tagsNeg,
                                             double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (entityInTypeTag(e, tagId) && matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return true;
                }
                return false;
            }
            return firstInBox(ctx, origin, maxDist,
                    en -> entityInTypeTag(en, tagId) && matchTagsAlive(en, tagsPos, tagsNeg)
                          && inRange(origin, en, minDist, maxDist)) != null;
        }
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!entityInTypeTag(e, tagId)) continue;
            if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
            if (origin != null && !inRange(origin, e, minDist, maxDist)) continue;
            return true;
        }
        return false;
    }
    public static net.minecraft.entity.Entity nearestEntity(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        net.minecraft.entity.Entity best = null;
        double bestD = Double.MAX_VALUE;
        // 거리 상한이 있으면 box 한정 섹션 스캔(allEntities/anyEntity 와 동일한 정확한 의미).
        // typeBucket 전체(월드의 해당 타입 전부) 순회를 피한다 — onkartcollision 등 핫패스에서
        // maxDist 가 작아(예: 4) box 스캔이 훨씬 적게 본다. 결과 집합/최근접 동일.
        if (QUERY_BOX && origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (!typeMatch(e, types)) continue;
                    if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
                    if (!inRange(origin, e, minDist, maxDist)) continue;
                    double d = e.getPos().squaredDistanceTo(origin);
                    if (d < bestD) { bestD = d; best = e; }
                }
                return best;
            }
            net.minecraft.util.math.Box _bx = rangeBox(origin, maxDist);
            for (net.minecraft.entity.EntityType<?> t : types) {
                for (net.minecraft.entity.Entity e : entitiesByTypeBox(ctx, t, _bx,
                        en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist))) {
                    double d = e.getPos().squaredDistanceTo(origin);
                    if (d < bestD) { bestD = d; best = e; }
                }
            }
            return best;
        }
        java.util.List<net.minecraft.entity.Entity> _tb = tagCandidates(ctx, tagsPos);
        for (net.minecraft.entity.EntityType<?> t : types) {
            for (net.minecraft.entity.Entity e : (_tb != null ? _tb : typeBucket(ctx, t))) {   // 거리 무제한
                if (_tb != null && e.getType() != t) continue;   // 태그버킷 경로: 타입 필터
                if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
                if (!inRange(origin, e, minDist, maxDist)) continue;
                double d = origin == null ? 0 : e.getPos().squaredDistanceTo(origin);
                if (d < bestD) { bestD = d; best = e; }
            }
        }
        return best;
    }

public static net.minecraft.entity.Entity nearestEntity(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            Tags tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        net.minecraft.entity.Entity best = null;
        double bestD = Double.MAX_VALUE;
        // 거리 상한이 있으면 box 한정 섹션 스캔(allEntities/anyEntity 와 동일한 정확한 의미).
        // typeBucket 전체(월드의 해당 타입 전부) 순회를 피한다 — onkartcollision 등 핫패스에서
        // maxDist 가 작아(예: 4) box 스캔이 훨씬 적게 본다. 결과 집합/최근접 동일.
        if (QUERY_BOX && origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (!typeMatch(e, types)) continue;
                    if (!matchTagsAlive(e, tagsPos.pos, tagsNeg)) continue;
                    if (!inRange(origin, e, minDist, maxDist)) continue;
                    double d = e.getPos().squaredDistanceTo(origin);
                    if (d < bestD) { bestD = d; best = e; }
                }
                return best;
            }
            net.minecraft.util.math.Box _bx = rangeBox(origin, maxDist);
            for (net.minecraft.entity.EntityType<?> t : types) {
                for (net.minecraft.entity.Entity e : entitiesByTypeBox(ctx, t, _bx,
                        en -> matchTagsAlive(en, tagsPos.pos, tagsNeg) && inRange(origin, en, minDist, maxDist))) {
                    double d = e.getPos().squaredDistanceTo(origin);
                    if (d < bestD) { bestD = d; best = e; }
                }
            }
            return best;
        }
        java.util.List<net.minecraft.entity.Entity> _tb = tagCandidates(ctx, tagsPos);
        for (net.minecraft.entity.EntityType<?> t : types) {
            for (net.minecraft.entity.Entity e : (_tb != null ? _tb : typeBucket(ctx, t))) {   // 거리 무제한
                if (_tb != null && e.getType() != t) continue;   // 태그버킷 경로: 타입 필터
                if (!matchTagsAlive(e, tagsPos.pos, tagsNeg)) continue;
                if (!inRange(origin, e, minDist, maxDist)) continue;
                double d = origin == null ? 0 : e.getPos().squaredDistanceTo(origin);
                if (d < bestD) { bestD = d; best = e; }
            }
        }
        return best;
    }

    public static net.minecraft.entity.Entity nearestEntityAnyType(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        net.minecraft.entity.Entity best = null;
        double bestD = Double.MAX_VALUE;
        if (origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
                    if (!inRange(origin, e, minDist, maxDist)) continue;
                    double d = e.getPos().squaredDistanceTo(origin);
                    if (d < bestD) { bestD = d; best = e; }
                }
                return best;
            }
            for (net.minecraft.entity.Entity e : ctx.world.getOtherEntities(null, rangeBox(origin, maxDist),
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist))) {
                double d = e.getPos().squaredDistanceTo(origin);
                if (d < bestD) { bestD = d; best = e; }
            }
            return best;
        }
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
            if (!inRange(origin, e, minDist, maxDist)) continue;
            double d = origin == null ? 0 : e.getPos().squaredDistanceTo(origin);
            if (d < bestD) { bestD = d; best = e; }
        }
        return best;
    }

    /** @a[...,limit=1] (sort 미지정=arbitrary) — 위치 무관, 매치하는 첫 플레이어.
     *  @p 와 달리 nearest 가 아니다. 정렬 기준점 고정이 필요한 알고리즘(순위 판정 등)에서 중요. */
    /** as @a[...,sort=nearest|furthest] — origin(보통 at 으로 rebind 된 위치) 기준 정렬된 플레이어 순회용.
     *  순위 부여 알고리즘(거리순으로 max 증가)에서 정렬 순서가 핵심이라 반드시 정렬해야 한다. */
    public static java.util.List<net.minecraft.server.network.ServerPlayerEntity> sortedPlayersByDist(
            GameContext ctx, net.minecraft.util.math.Vec3d origin, boolean furthest) {
        java.util.List<net.minecraft.server.network.ServerPlayerEntity> out =
                new java.util.ArrayList<>(ctx.allPlayers);
        if (origin != null) {
            java.util.Comparator<net.minecraft.entity.Entity> c =
                    java.util.Comparator.comparingDouble(p -> p.squaredDistanceTo(origin));
            if (furthest) c = c.reversed();
            out.sort(c);
        }
        return out;
    }

    public static net.minecraft.server.network.ServerPlayerEntity firstPlayer(
            GameContext ctx, String[] tagsPos, String[] tagsNeg, double minDist, double maxDist,
            net.minecraft.util.math.Vec3d origin) {
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (matchTags(p, tagsPos, tagsNeg) && inRange(origin, p, minDist, maxDist)) return p;
        }
        return null;
    }

    public static net.minecraft.server.network.ServerPlayerEntity firstPlayerWhere(
            GameContext ctx, java.util.function.Predicate<net.minecraft.server.network.ServerPlayerEntity> cond) {
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (cond.test(p)) return p;
        }
        return null;
    }

    public static net.minecraft.server.network.ServerPlayerEntity nearestPlayer(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        net.minecraft.server.network.ServerPlayerEntity best = null;
        double bestD = Double.MAX_VALUE;
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (!matchTags(p, tagsPos, tagsNeg)) continue;
            if (!inRange(origin, p, minDist, maxDist)) continue;
            double d = origin == null ? 0 : p.getPos().squaredDistanceTo(origin);
            if (d < bestD) { bestD = d; best = p; }
        }
        return best;
    }

    /** @r - 매칭 플레이어 중 균등 랜덤 1명(바닐라 EntitySelectorReader RANDOM 정렬 = 셔플 후 첫). */
    public static net.minecraft.server.network.ServerPlayerEntity randomPlayer(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        java.util.List<net.minecraft.server.network.ServerPlayerEntity> pool = null;
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (!matchTags(p, tagsPos, tagsNeg)) continue;
            if (!inRange(origin, p, minDist, maxDist)) continue;
            if (pool == null) pool = new java.util.ArrayList<>();
            pool.add(p);
        }
        if (pool == null) return null;
        return pool.size() == 1 ? pool.get(0) : pool.get(ctx.world.getRandom().nextInt(pool.size()));
    }

    /** @r + 추가 술어(gamemode/scores/predicate/rotation) - 매칭 중 균등 랜덤 1명. */
    public static net.minecraft.server.network.ServerPlayerEntity randomPlayerWhere(
            GameContext ctx, java.util.function.Predicate<net.minecraft.server.network.ServerPlayerEntity> cond) {
        java.util.List<net.minecraft.server.network.ServerPlayerEntity> pool = null;
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (!cond.test(p)) continue;
            if (pool == null) pool = new java.util.ArrayList<>();
            pool.add(p);
        }
        if (pool == null) return null;
        return pool.size() == 1 ? pool.get(0) : pool.get(ctx.world.getRandom().nextInt(pool.size()));
    }

    /** 무한거리(박스 없음) 단일 타입 조회. getEntitiesByType 의 제네릭 T 를 Entity 로 바인딩해
     *  List<? extends T> 캡처를 List<Entity> 로 안전하게 흡수한다(entitiesByTypeBox 의 무한거리 짝).
     *  EntityType<?> → TypeFilter<Entity,Entity> 캐스팅은 런타임 안전: 술어에서 타입이 이미 t 로
     *  좁혀지고, 필터 자체가 t 인스턴스만 통과시키므로 반환 원소는 모두 t(⊆ Entity) 다. */
    @SuppressWarnings("unchecked")
    public static java.util.List<net.minecraft.entity.Entity> entitiesByType(
            GameContext ctx, net.minecraft.entity.EntityType<?> t,
            java.util.function.Predicate<net.minecraft.entity.Entity> pred) {
        // 21차(바닐라 초월): vanilla getEntitiesByType(EntityLookup 전체 + TypeFilter downcast
        // + 콜백 체인, 스파크 1.57%p) 대신 타입버킷 직행. 결과 집합 동등 — 버킷 = 이 틱 로드
        // 개체군의 타입 부분집합, 호출측 술어(alive 포함)가 라이브 재검사(전 호출부 확인).
        if (QUERY_IDX) {
            java.util.List<net.minecraft.entity.Entity> b = typeBucket(ctx, t);
            java.util.ArrayList<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
            for (int i = 0, n = b.size(); i < n; i++) {
                net.minecraft.entity.Entity e = b.get(i);
                if (pred.test(e)) out.add(e);
            }
            return out;
        }
        net.minecraft.util.TypeFilter<net.minecraft.entity.Entity, net.minecraft.entity.Entity> tf =
                (net.minecraft.util.TypeFilter<net.minecraft.entity.Entity, net.minecraft.entity.Entity>)
                        (net.minecraft.util.TypeFilter<net.minecraft.entity.Entity, ?>) t;
        return (java.util.List<net.minecraft.entity.Entity>) ctx.world.getEntitiesByType(tf, pred);
    }

    public static java.util.List<net.minecraft.entity.Entity> allEntities(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        // 무제한 + 양성 태그: 태그 버킷 경로(결과 집합·타입-우선 순서 동일, 후보만 소수).
        java.util.List<net.minecraft.entity.Entity> _tb =
                (origin != null && maxDist >= 0) ? tagCandidatesBounded(ctx, tagsPos) : tagCandidates(ctx, tagsPos);
        if (_tb != null) {
            java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
            for (net.minecraft.entity.EntityType<?> t : types) {
                for (net.minecraft.entity.Entity e : _tb) {
                    if (e.getType() != t) continue;
                    if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) out.add(e);
                }
            }
            return out;
        }
        // 단일 타입(생성물의 226개 중 209개)은 조회 결과를 그대로 반환 — new ArrayList+addAll 로
        // 한 번 더 복사하던 중간 리스트 할당을 제거한다(내용·순서 동일, 복사만 생략).
        if (types.length == 1) {
            net.minecraft.entity.EntityType<?> t = types[0];
            if (origin != null && maxDist >= 0) {
                return entitiesByTypeBox(ctx, t, rangeBox(origin, maxDist),
                        en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
            }
            return entitiesByType(ctx, t,
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
        }
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.EntityType<?> t : types) {
            if (origin != null && maxDist >= 0) {
                out.addAll(entitiesByTypeBox(ctx, t, rangeBox(origin, maxDist),
                        en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)));
            } else {
                out.addAll(entitiesByType(ctx, t,
                        en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)));
            }
        }
        return out;
    }

    /** 소스 위치 기준 최근접 N개 (limit=N + sort=nearest / @n 다중). */
    public static java.util.List<net.minecraft.entity.Entity> nearestN(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist, int n) {
        // 기존 생성물 호환: sort 의도 미지정 → 현행(정렬) 유지.
        return nearestN(ctx, origin, types, tagsPos, tagsNeg, minDist, maxDist, n, true);
    }

    public static java.util.List<net.minecraft.entity.Entity> nearestN(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist, int n,
            boolean wantNearest) {
        java.util.List<net.minecraft.entity.Entity> all =
                allEntities(ctx, origin, types, tagsPos, tagsNeg, minDist, maxDist);
        // sort=nearest(wantNearest) 이거나 토글이 켜진 경우에만 거리순 정렬. 그 외(@e arbitrary)는
        // 반복 순서 그대로 첫 N개 — 바닐라 arbitrary 와 일치하고 정렬 비용을 없앤다.
        if ((wantNearest || LIMIT_SORT_NEAREST) && origin != null && all.size() > 1) {
            all.sort(java.util.Comparator.comparingDouble(e -> e.getPos().squaredDistanceTo(origin)));
        }
        return all.size() <= n ? all : all.subList(0, n);
    }

    /** limit 셀렉터에 scores/predicate 등 추가 술어가 붙은 경우. 바닐라는 모든 술어 매치를
     *  limit(과 sort) 보다 먼저 적용한다(@e[scores={..},limit=N]). extra 를 후보 수집 직후
     *  (정렬·자르기 전)에 적용해 그 순서를 정확히 재현한다. */
    public static java.util.List<net.minecraft.entity.Entity> nearestN(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist, int n,
            boolean wantNearest, java.util.function.Predicate<net.minecraft.entity.Entity> extra) {
        java.util.List<net.minecraft.entity.Entity> all =
                allEntities(ctx, origin, types, tagsPos, tagsNeg, minDist, maxDist);
        if (extra != null) all.removeIf(e -> !extra.test(e));
        if ((wantNearest || LIMIT_SORT_NEAREST) && origin != null && all.size() > 1) {
            all.sort(java.util.Comparator.comparingDouble(e -> e.getPos().squaredDistanceTo(origin)));
        }
        return all.size() <= n ? all : all.subList(0, n);
    }

    public static java.util.List<net.minecraft.entity.Entity> allEntitiesAnyType(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                java.util.List<net.minecraft.entity.Entity> _out = new java.util.ArrayList<>();
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) _out.add(e);
                }
                return _out;
            }
            return ctx.world.getOtherEntities(null, rangeBox(origin, maxDist),
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
        }
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
            if (!inRange(origin, e, minDist, maxDist)) continue;
            out.add(e);
        }
        return out;
    }

    /** 타입 미지정 @e[limit=N] (sort 미지정=nearest 기본) — origin 에서 가까운 순 N개. */
    public static java.util.List<net.minecraft.entity.Entity> nearestNAnyType(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist, int limit) {
        return nearestNAnyType(ctx, origin, tagsPos, tagsNeg, minDist, maxDist, limit, true);
    }

    public static java.util.List<net.minecraft.entity.Entity> nearestNAnyType(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist, int limit,
            boolean wantNearest) {
        java.util.List<net.minecraft.entity.Entity> all =
                allEntitiesAnyType(ctx, origin, tagsPos, tagsNeg, minDist, maxDist);
        if ((wantNearest || LIMIT_SORT_NEAREST) && origin != null && all.size() > 1) {
            all.sort(java.util.Comparator.comparingDouble(e -> e.squaredDistanceTo(origin)));
        }
        if (limit >= 0 && all.size() > limit) {
            // [할당 감소] subList 뷰 그대로 반환(복사 ArrayList 제거). all 은 이 메서드가 만든
            // 임시 리스트이고 호출부(emit 생성 코드)는 for 순회만 하며 add/remove 하지 않으므로
            // 뷰 반환이 안전하다. nearestN(타입 지정) 경로가 이미 동일하게 뷰를 반환 중.
            return all.subList(0, limit);
        }
        return all;
    }

    /** 타입 미지정 + 추가 술어(scores/predicate). 술어를 정렬·limit 전에 적용(바닐라 순서). */
    public static java.util.List<net.minecraft.entity.Entity> nearestNAnyType(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist, int limit,
            boolean wantNearest, java.util.function.Predicate<net.minecraft.entity.Entity> extra) {
        java.util.List<net.minecraft.entity.Entity> all =
                allEntitiesAnyType(ctx, origin, tagsPos, tagsNeg, minDist, maxDist);
        if (extra != null) all.removeIf(e -> !extra.test(e));
        if ((wantNearest || LIMIT_SORT_NEAREST) && origin != null && all.size() > 1) {
            all.sort(java.util.Comparator.comparingDouble(e -> e.squaredDistanceTo(origin)));
        }
        if (limit >= 0 && all.size() > limit) {
            return all.subList(0, limit);   // [할당 감소] 위와 동일 — 뷰 반환
        }
        return all;
    }

    /** if entity @e/@n[type,tag,distance] — 조건에 맞는 엔티티가 하나라도 있으면 true. */
    public static boolean anyEntity(GameContext ctx, net.minecraft.entity.Entity origin,
                                    net.minecraft.entity.EntityType<?> type,
                                    String[] tagsPos, String[] tagsNeg,
                                    double minDist, double maxDist) {
        if (QUERY_BOX && origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (e.getType() == type && matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return true;
                }
                return false;
            }
            return anyInBoxTyped(ctx, type, rangeBox(origin.getPos(), maxDist),
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
        }
        // 거리 무제한: 전체 수집+isEmpty 대신 typeBucket 순회 + 첫 매치 early-return(존재 의미 동일).
        java.util.List<net.minecraft.entity.Entity> _tb = tagCandidates(ctx, tagsPos);
        for (net.minecraft.entity.Entity e : (_tb != null ? _tb : typeBucket(ctx, type))) {
            if (_tb != null && e.getType() != type) continue;   // 태그버킷 경로: 타입 필터
            if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return true;
        }
        return false;
    }

    /** if entity @a/@p/@r[tag,distance] — 조건에 맞는 플레이어가 하나라도 있으면 true. */
    public static boolean anyPlayer(GameContext ctx, net.minecraft.entity.Entity origin,
                                    String[] tagsPos, String[] tagsNeg,
                                    double minDist, double maxDist) {
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (matchTags(p, tagsPos, tagsNeg) && inRange(origin, p, minDist, maxDist)) return true;
        }
        return false;
    }

    /** 술어 동반 최근접 플레이어 (gamemode/scores 등 복합 필터용). */
    public static net.minecraft.server.network.ServerPlayerEntity nearestPlayerWhere(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            java.util.function.Predicate<net.minecraft.server.network.ServerPlayerEntity> pred) {
        net.minecraft.server.network.ServerPlayerEntity best = null;
        double bestD = Double.MAX_VALUE;
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (!pred.test(p)) continue;
            double d = origin == null ? 0 : p.getPos().squaredDistanceTo(origin);
            if (d < bestD) { bestD = d; best = p; }
        }
        return best;
    }

    /** 술어 동반 최근접 엔티티 (scores 등 복합 필터용, Box 한정 재사용). */
    public static net.minecraft.entity.Entity nearestEntityWhere(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist,
            java.util.function.Predicate<net.minecraft.entity.Entity> pred) {
        net.minecraft.entity.Entity best = null;
        double bestD = Double.MAX_VALUE;
        for (net.minecraft.entity.Entity e : allEntities(ctx, origin, types, tagsPos, tagsNeg, minDist, maxDist)) {
            if (!pred.test(e)) continue;
            double d = origin == null ? 0 : e.getPos().squaredDistanceTo(origin);
            if (d < bestD) { bestD = d; best = e; }
        }
        return best;
    }

    /** 타입 미지정 + 술어 동반 최근접 엔티티 (커스텀 타입태그/모드 타입/type=!X 런타임 가드용). */
    // ── sort 미지정 limit=N 셀렉터(arbitrary)용 first-match 해소 ──
    // 바닐라 @e[limit=1](sort 미지정)은 sort=arbitrary: 정렬 없이 월드 순회 순서의 첫 매치라
    // '사실상 항상 같은 엔티티'가 뽑힌다(EntitySelectorReader 바이트코드: e/n 케이스 sorter 미설정
    // 시 ARBITRARY). 이를 nearest 로 해소하면 소스 위치에 따라 대상이 틱마다 스위칭돼
    // (예: logmain 모델 2대가 번갈아 전진 → 전진속도 절반/겹침) 관측이 어긋난다.
    // first* 는 스냅샷/타입인덱스(생성 순서 기반 — 틱 간 안정) 순회의 첫 매치를 반환한다.
    public static net.minecraft.entity.Entity firstEntity(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        java.util.List<net.minecraft.entity.Entity> _tb = tagCandidates(ctx, tagsPos);
        for (net.minecraft.entity.EntityType<?> t : types) {
            for (net.minecraft.entity.Entity e : (_tb != null ? _tb : typeBucket(ctx, t))) {
                if (_tb != null && e.getType() != t) continue;   // 태그버킷 경로: 타입-우선 순회 순서 보존
                if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return e;
            }
        }
        return null;
    }

public static net.minecraft.entity.Entity firstEntity(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            Tags tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        java.util.List<net.minecraft.entity.Entity> _tb = tagCandidates(ctx, tagsPos);
        for (net.minecraft.entity.EntityType<?> t : types) {
            for (net.minecraft.entity.Entity e : (_tb != null ? _tb : typeBucket(ctx, t))) {
                if (_tb != null && e.getType() != t) continue;   // 태그버킷 경로: 타입-우선 순회 순서 보존
                if (matchTagsAlive(e, tagsPos.pos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return e;
            }
        }
        return null;
    }

    public static net.minecraft.entity.Entity firstEntityAnyType(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {   // 범위 한정: 박스 조기종료(리스트 미생성)
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return e;
                }
                return null;
            }
            return firstInBox(ctx, origin, maxDist,
                    e -> matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist));
        }
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {   // 무한범위: 스냅샷 조기종료
            if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) return e;
        }
        return null;
    }

    public static net.minecraft.entity.Entity firstEntityWhere(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist,
            java.util.function.Predicate<net.minecraft.entity.Entity> pred) {
        java.util.List<net.minecraft.entity.Entity> _tb = tagCandidates(ctx, tagsPos);
        for (net.minecraft.entity.EntityType<?> t : types) {
            for (net.minecraft.entity.Entity e : (_tb != null ? _tb : typeBucket(ctx, t))) {
                if (_tb != null && e.getType() != t) continue;   // 태그버킷 경로: 타입-우선 순회 순서 보존
                if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)
                        && pred.test(e)) return e;
            }
        }
        return null;
    }

    public static net.minecraft.entity.Entity firstEntityAnyTypeWhere(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist,
            java.util.function.Predicate<net.minecraft.entity.Entity> pred) {
        if (origin != null && maxDist >= 0) {   // 범위 한정: 박스 조기종료(리스트 미생성)
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) { if (pred.test(e)) return e; }
                }
                return null;
            }
            return firstInBox(ctx, origin, maxDist,
                    e -> matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist) && pred.test(e));
        }
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {   // 무한범위: 스냅샷 조기종료
            if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist) && pred.test(e)) return e;
        }
        return null;
    }

    /** @a[limit=1](sort 미지정 = arbitrary)용 — 접속 순서(allPlayers) 첫 매치. */
    public static net.minecraft.entity.Entity firstPlayer(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (matchTags(p, tagsPos, tagsNeg) && inRange(origin, p, minDist, maxDist)) return p;
        }
        return null;
    }

    public static net.minecraft.entity.Entity nearestEntityAnyTypeWhere(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist,
            java.util.function.Predicate<net.minecraft.entity.Entity> pred) {
        net.minecraft.entity.Entity best = null;
        double bestD = Double.MAX_VALUE;
        for (net.minecraft.entity.Entity e : allEntitiesAnyType(ctx, origin, tagsPos, tagsNeg, minDist, maxDist)) {
            if (!pred.test(e)) continue;
            double d = origin == null ? 0 : e.getPos().squaredDistanceTo(origin);
            if (d < bestD) { bestD = d; best = e; }
        }
        return best;
    }

    /** gamemode= 셀렉터 필터 — 바닐라 GameMode 이름(survival/creative/adventure/spectator) 비교. */
    public static boolean gamemodeIs(net.minecraft.entity.Entity e, String name) {
        if (!(e instanceof net.minecraft.server.network.ServerPlayerEntity p)) return false;
        net.minecraft.world.GameMode gm = p.interactionManager.getGameMode();
        return gm != null && gm.asString().equals(name);
    }

    /** scores={obj=lo..hi} 1개 동반 @e/@n 존재검사 (Box 한정 재사용). */
    public static boolean anyEntityScored(GameContext ctx, net.minecraft.util.math.Vec3d origin,
                                          net.minecraft.entity.EntityType<?>[] types,
                                          String[] tagsPos, String[] tagsNeg,
                                          double minDist, double maxDist,
                                          ServerScoreboard sb, String obj, Integer lo, Integer hi) {
        for (net.minecraft.entity.Entity e : allEntities(ctx, origin, types, tagsPos, tagsNeg, minDist, maxDist)) {
            if (scoreMatches(sb, e.getNameForScoreboard(), obj, lo, hi)) return true;
        }
        return false;
    }

    /** 소스 위치(Vec3d) 기준 — 바닐라 distance= 시맨틱 (positioned/at 리바인드 뒤에도 정확). */
    public static boolean anyPlayer(GameContext ctx, net.minecraft.util.math.Vec3d origin,
                                    String[] tagsPos, String[] tagsNeg,
                                    double minDist, double maxDist) {
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (matchTags(p, tagsPos, tagsNeg) && inRange(origin, p, minDist, maxDist)) return true;
        }
        return false;
    }

    /** scoreboard players operation <dst> OP <src> (홀더 이름 기반). floor div/mod = MC 시맨틱. */
    public static void opScore(ServerScoreboard sb, String dh, String dobj, String op,
                               String sh, String sobj) {
        // 바닐라 ScoreboardCommand.executeOperation 은 대상·소스를 모두 getOrCreateScore 로
        // 가져온다 → 미존재 점수가 0 으로 '생성'되는 부수효과가 있다. kartriderpack 등은
        // 매 틱 operation(예: #kartspeed kartmove = @s kartmove)의 이 부수효과로 소환 직후
        // 점수를 존재화시키고, 이후 `if score A < B`(미존재면 실패)가 이에 의존한다.
        // readOrZero(생성 없음)로 읽으면 점수가 영영 미존재로 남아 비교 조건이 전부 실패한다.
        ScoreboardObjective od = obj(sb, dobj), os = obj(sb, sobj);
        if (od == null || os == null) return;
        net.minecraft.scoreboard.ScoreAccess da = sb.getOrCreateScore(holderOf(dh), od);
        net.minecraft.scoreboard.ScoreAccess sa = sb.getOrCreateScore(holderOf(sh), os);
        int b = sa.getScore();
        int r;
        // '=' 는 전체 operation 의 최다(≈45%)이고 대상 현재값(a)을 쓰지 않는다.
        // a 를 각 연산에서 필요할 때만 읽어(da.getScore()) '=' 경로의 불필요한 읽기를 없앤다.
        switch (op) {
            case "=":  r = b; break;
            case "+=": r = da.getScore() + b; break;
            case "-=": r = da.getScore() - b; break;
            case "*=": r = da.getScore() * b; break;
            case "/=": if (b == 0) return; r = Math.floorDiv(da.getScore(), b); break;   // 0나눗셈: 실패(생성은 유지)
            case "%=": if (b == 0) return; r = Math.floorMod(da.getScore(), b); break;
            case "<":  { int a = da.getScore(); r = Math.min(a, b); break; }
            case ">":  { int a = da.getScore(); r = Math.max(a, b); break; }
            case "><": { int a = da.getScore(); da.setScore(b); sa.setScore(a); return; }
            default: return;
        }
        da.setScore(r);
    }

    // ──────────────── 셀렉터 홀더 (단일 엔티티) ────────────────
    // if score @e[limit=1,...] / @n / @p[...] 처럼 "단일 엔티티"를 가리키는 스코어 홀더.
    // 조건에 맞는 가장 가까운(@n/sort=nearest 기본) 엔티티 1마리를 찾는다. 없으면 null.
    // mcfunction 의 @n/limit=1 시맨틱: origin 기준 최근접. distance 범위 옵션.

    public static net.minecraft.entity.Entity nearestEntity(
            GameContext ctx, net.minecraft.entity.Entity origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        net.minecraft.entity.Entity best = null;
        double bestD = Double.MAX_VALUE;
        if (QUERY_BOX && origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (!typeMatch(e, types)) continue;
                    if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
                    if (!inRange(origin, e, minDist, maxDist)) continue;
                    double d = origin.squaredDistanceTo(e);
                    if (d < bestD) { bestD = d; best = e; }
                }
                return best;
            }
            net.minecraft.util.math.Box _bx = rangeBox(origin.getPos(), maxDist);
            for (net.minecraft.entity.EntityType<?> t : types) {
                for (net.minecraft.entity.Entity e : entitiesByTypeBox(ctx, t, _bx,
                        en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist))) {
                    double d = origin.squaredDistanceTo(e);
                    if (d < bestD) { bestD = d; best = e; }
                }
            }
            return best;
        }
        java.util.List<net.minecraft.entity.Entity> _tb = tagCandidates(ctx, tagsPos);
        for (net.minecraft.entity.EntityType<?> t : types) {
            for (net.minecraft.entity.Entity e : (_tb != null ? _tb : typeBucket(ctx, t))) {   // 거리 무제한
                if (_tb != null && e.getType() != t) continue;   // 태그버킷 경로: 타입 필터
                if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
                if (origin != null && !inRange(origin, e, minDist, maxDist)) continue;
                double d = origin == null ? 0 : origin.squaredDistanceTo(e);
                if (d < bestD) { bestD = d; best = e; }
            }
        }
        return best;
    }

    /** 최근접 플레이어 1명(@p). origin 기준. tag 필터. 없으면 null. */
    public static net.minecraft.server.network.ServerPlayerEntity nearestPlayer(
            GameContext ctx, net.minecraft.entity.Entity origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        net.minecraft.server.network.ServerPlayerEntity best = null;
        double bestD = Double.MAX_VALUE;
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            if (!matchTags(p, tagsPos, tagsNeg)) continue;
            if (origin != null && !inRange(origin, p, minDist, maxDist)) continue;
            double d = origin == null ? 0 : origin.squaredDistanceTo(p);
            if (d < bestD) { bestD = d; best = p; }
        }
        return best;
    }

    /** 엔티티의 스코어가 [min,max] 범위인지. 엔티티 null 이면 false(=대상 없음). */
    // ──────────────── if items (아이템 술어) ────────────────
    /** `if items entity @s <slot> <item>[custom_data~{nbt}]` 의 네이티브 검사.
     *  slot: weapon | weapon.mainhand | weapon.offhand | armor.head/chest/legs/feet
     *  itemId: "minecraft:iron_nugget" 또는 "*"(아무 아이템 — 빈 슬롯 제외)
     *  customDataSnbt: null 이면 검사 안 함; "{kartmobil:1}" 형태 SNBT 서브셋 매치 */
    public static boolean itemsMatch(net.minecraft.entity.Entity e, String slot,
                                     String itemId, String customDataSnbt) {
        if (!(e instanceof net.minecraft.entity.LivingEntity le)) return false;
        net.minecraft.entity.EquipmentSlot es = switch (slot) {
            case "weapon", "weapon.mainhand" -> net.minecraft.entity.EquipmentSlot.MAINHAND;
            case "weapon.offhand" -> net.minecraft.entity.EquipmentSlot.OFFHAND;
            case "armor.head" -> net.minecraft.entity.EquipmentSlot.HEAD;
            case "armor.chest" -> net.minecraft.entity.EquipmentSlot.CHEST;
            case "armor.legs" -> net.minecraft.entity.EquipmentSlot.LEGS;
            case "armor.feet" -> net.minecraft.entity.EquipmentSlot.FEET;
            default -> null;
        };
        if (es == null) return false;
        net.minecraft.item.ItemStack stack = le.getEquippedStack(es);
        if (stack.isEmpty()) return false;
        if (!"*".equals(itemId)) {
            net.minecraft.util.Identifier id = idOf(
                    itemId.contains(":") ? itemId : "minecraft:" + itemId);
            if (!net.minecraft.registry.Registries.ITEM.getId(stack.getItem()).equals(id)) return false;
        }
        if (customDataSnbt != null) {
            net.minecraft.component.type.NbtComponent nc =
                    stack.get(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);
            if (nc == null) return false;
            try {
                net.minecraft.nbt.NbtCompound expected =
                        net.minecraft.nbt.StringNbtReader.readCompound(customDataSnbt);
                if (!net.minecraft.nbt.NbtHelper.matches(expected, nc.copyNbt(), true)) return false;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    /** ItemStack 이 item id(+custom_data) 술어에 일치하는지. clear/itemsMatch 공용. */
    private static final java.util.HashMap<String, net.minecraft.registry.tag.TagKey<net.minecraft.item.Item>>
            ITEM_TAG_CACHE = new java.util.HashMap<>();

    private static boolean stackMatches(net.minecraft.item.ItemStack stack, String itemId, String customDataSnbt) {
        if (stack == null || stack.isEmpty()) return false;
        if (itemId != null && itemId.startsWith("#")) {
            net.minecraft.registry.tag.TagKey<net.minecraft.item.Item> key =
                    ITEM_TAG_CACHE.computeIfAbsent(itemId.substring(1), t ->
                            net.minecraft.registry.tag.TagKey.of(net.minecraft.registry.RegistryKeys.ITEM,
                                    idOf(t)));
            if (!stack.isIn(key)) return false;
        } else if (itemId != null && !"*".equals(itemId)) {
            net.minecraft.util.Identifier id = idOf(
                    itemId.contains(":") ? itemId : "minecraft:" + itemId);
            if (!net.minecraft.registry.Registries.ITEM.getId(stack.getItem()).equals(id)) return false;
        }
        if (customDataSnbt != null) {
            net.minecraft.component.type.NbtComponent nc =
                    stack.get(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);
            if (nc == null) return false;
            try {
                // 기대 SNBT 는 변환 시점 상수 — 매 호출 packrat 재파싱(rkey/multiplay 핫패스 실측)
                // 대신 SNBT_CACHE 재사용(entityMergeSnbt 의 readCompound 캐시 규약과 동일 키).
                net.minecraft.nbt.NbtElement exp = SNBT_CACHE.computeIfAbsent(customDataSnbt, s -> {
                    try { return net.minecraft.nbt.StringNbtReader.readCompound(s); }
                    catch (Exception ex) { return SNBT_INVALID; }
                });
                if (!(exp instanceof net.minecraft.nbt.NbtCompound expected)) return false;
                // matches 는 읽기 전용 — copyNbt(전체 딥카피) 대신 내부 컴파운드 직접 참조.
                // (getNbt 의 deprecated 는 '변형 금지' 경고 — 여기선 읽기만 하므로 의도적 사용.)
                if (!net.minecraft.nbt.NbtHelper.matches(expected, kfc$nbtOf(nc), true)) return false;
            } catch (Exception ex) { return false; }
        }
        return true;
    }

    /** clear <player> [item] [maxCount] : 매칭 아이템 제거. 반환=제거(또는 maxCount=0 이면 일치) 개수.
     *  maxCount<0(생략)=전부 제거 / maxCount==0=개수만(제거 안 함) / >0=상한까지 제거. */
    public static int clearItems(net.minecraft.entity.Entity e, String itemId, String customDataSnbt, int maxCount) {
        if (!(e instanceof net.minecraft.entity.player.PlayerEntity p)) return 0;
        net.minecraft.entity.player.PlayerInventory inv = p.getInventory();
        int matched = 0;
        for (int i = 0; i < inv.size(); i++) {
            net.minecraft.item.ItemStack st = inv.getStack(i);
            if (!stackMatches(st, itemId, customDataSnbt)) continue;
            if (maxCount == 0) { matched += st.getCount(); continue; }   // 개수만
            if (maxCount < 0) { matched += st.getCount(); inv.setStack(i, net.minecraft.item.ItemStack.EMPTY); continue; }
            int take = Math.min(st.getCount(), maxCount - matched);
            if (take <= 0) break;
            st.decrement(take);
            if (st.isEmpty()) inv.setStack(i, net.minecraft.item.ItemStack.EMPTY);
            matched += take;
            if (matched >= maxCount) break;
        }
        return matched;
    }

    /** clear 의 아이템 술어가 매크로($clear @s $(item))로 오는 경우: 치환된 런타임 문자열을
     *  바닐라 ItemPredicateArgumentType 으로 파싱해 매칭한다(id/태그/컴포넌트/서브술어 전부 —
     *  바닐라가 치환 후 라인을 재파싱하는 것과 동일 코드 경로). 파싱 실패 = 바닐라에서
     *  해당 라인 인스턴스화 실패(함수 미실행)이므로 0 반환·무변경으로 안전 동작. */
    public static int clearItemsPred(net.minecraft.server.command.ServerCommandSource source,
                                     net.minecraft.entity.Entity e, String predStr, int maxCount) {
        if (!(e instanceof net.minecraft.entity.player.PlayerEntity p)) return 0;
        java.util.function.Predicate<net.minecraft.item.ItemStack> pred =
                parseItemPredicate(source.getServer(), predStr);
        if (pred == null) throw MACRO_FAIL;   // 바닐라: 치환 라인 파싱 실패 = 함수 전체 실패
        net.minecraft.entity.player.PlayerInventory inv = p.getInventory();
        int matched = 0;
        for (int i = 0; i < inv.size(); i++) {
            net.minecraft.item.ItemStack st = inv.getStack(i);
            if (st.isEmpty() || !pred.test(st)) continue;
            if (maxCount == 0) { matched += st.getCount(); continue; }
            if (maxCount < 0) { matched += st.getCount(); inv.setStack(i, net.minecraft.item.ItemStack.EMPTY); continue; }
            int take = Math.min(st.getCount(), maxCount - matched);
            if (take <= 0) break;
            st.decrement(take);
            if (st.isEmpty()) inv.setStack(i, net.minecraft.item.ItemStack.EMPTY);
            matched += take;
            if (matched >= maxCount) break;
        }
        return matched;
    }

    /** 바닐라 clear/if items 의 아이템 술어 파서(ItemPredicateArgumentType) 런타임 호출.
     *  술어는 reloadable 데이터(아이템 태그 등)에 바인딩될 수 있어 캐시하지 않는다. */
    private static java.util.function.Predicate<net.minecraft.item.ItemStack> parseItemPredicate(
            net.minecraft.server.MinecraftServer server, String predStr) {
        if (predStr == null) return null;
        try {
            net.minecraft.command.CommandRegistryAccess access =
                    net.minecraft.command.CommandRegistryAccess.of(
                            server.getRegistryManager(),
                            server.getSaveProperties().getEnabledFeatures());
            return new net.minecraft.command.argument.ItemPredicateArgumentType(access)
                    .parse(new com.mojang.brigadier.StringReader(predStr));
        } catch (Exception ex) { return null; }
    }

    /** if items entity <e> <slot|range> <pred> : 슬롯(범위 포함, container.* 등)에 일치 아이템 존재.
     *  SlotRanges + getStackReference 로 모든 슬롯 타입(weapon/container/contents/enderchest/armor) 처리. */
    public static boolean itemsMatchSlots(net.minecraft.entity.Entity e, String slotName,
                                          String itemId, String customDataSnbt) {
        if (e == null) return false;
        net.minecraft.inventory.SlotRange r;
        try { r = net.minecraft.inventory.SlotRanges.fromName(slotName); }
        catch (Exception ex) { return false; }
        if (r == null) return false;
        it.unimi.dsi.fastutil.ints.IntList ids = r.getSlotIds();
        for (int k = 0; k < ids.size(); k++) {
            net.minecraft.inventory.StackReference ref = e.getStackReference(ids.getInt(k));
            if (ref == net.minecraft.inventory.StackReference.EMPTY) continue;
            if (stackMatches(ref.get(), itemId, customDataSnbt)) return true;
        }
        return false;
    }

    // ── if items <slot> <복합 술어(컴포넌트/서브술어)> — 바닐라 파서 위임 경로 ──
    // 간이 파서(itemId+custom_data)가 못 푸는 임의 술어(minecraft:leather_helmet[
    // minecraft:dyed_color=255,enchantments={...}] 등)를 위 parseItemPredicate(clear 매크로와
    // 공용, 바닐라 ItemPredicateArgumentType)로 '런타임 파싱' 해 Predicate<ItemStack> 그대로 사용.
    // 바닐라 ExecuteCommand.countMatchingItems 와 동일: SlotRange 의 각 슬롯 스택에
    // predicate.test — 조건으로는 매치 존재 여부(count>0)만 필요.
    // 캐시하지 않음: 술어가 reloadable 데이터(아이템 태그, 1.21+ 데이터드리븐 인챈트 등)에
    // 바인딩될 수 있어 /reload 후 stale 참조 위험 — 기존 parseItemPredicate 정책과 동일.

    /** if items entity <e> <slot> <임의 술어> — 바닐라 술어 시맨틱 그대로(매치 존재 여부). */
    public static boolean itemsMatchSlotsPred(net.minecraft.server.MinecraftServer server,
                                              net.minecraft.entity.Entity e,
                                              String slotName, String predStr) {
        if (e == null) return false;
        java.util.function.Predicate<net.minecraft.item.ItemStack> pred = parseItemPredicate(server, predStr);
        if (pred == null) return false;   // 무효 술어: 바닐라도 파싱 실패로 조건 불성립(함수 내 무피드백)
        net.minecraft.inventory.SlotRange r;
        try { r = net.minecraft.inventory.SlotRanges.fromName(slotName); }
        catch (Exception ex) { return false; }
        if (r == null) return false;
        it.unimi.dsi.fastutil.ints.IntList ids = r.getSlotIds();
        for (int k = 0; k < ids.size(); k++) {
            net.minecraft.inventory.StackReference ref = e.getStackReference(ids.getInt(k));
            if (ref == net.minecraft.inventory.StackReference.EMPTY) continue;
            net.minecraft.item.ItemStack st = ref.get();
            if (st != null && pred.test(st)) return true;
        }
        return false;
    }

    // ── if/unless items 의 '조건' 시맨틱 헬퍼 ──
    // 바닐라(ExecuteCommand): items entity 는 EntityArgumentType.getEntities — 셀렉터가 아무도
    // 못 찾으면 ENTITY_NOT_FOUND_EXCEPTION 으로 '조건 명령 자체가 실패' → if 든 unless 든
    // 그 분기는 실행되지 않는다(바이트코드 확인). 따라서 negate(unless)를 조건식 바깥에서
    // !(...) 로 감싸면 '대상 없음' 이 unless 참으로 둔갑한다(팀모자 매틱 재장착 버그의 원인).
    // 이 헬퍼들은 negate 를 '아이템 매치' 에만 적용하고, 대상 없음은 무조건 false 를 돌려준다.

    /** 단일 대상 items 조건. e == null(셀렉터 매치 실패) → false (negate 무관, 바닐라 예외 동등). */
    public static boolean itemsCond(net.minecraft.entity.Entity e, String slotName,
                                    String itemId, String customDataSnbt, boolean negate) {
        if (e == null) return false;
        boolean m = itemsMatchSlots(e, slotName, itemId, customDataSnbt);
        return negate ? !m : m;
    }

    /** 단일 대상 + 복합 술어(바닐라 파서) items 조건. e == null → false (negate 무관). */
    public static boolean itemsCondPred(net.minecraft.server.MinecraftServer server,
                                        net.minecraft.entity.Entity e, String slotName,
                                        String predStr, boolean negate) {
        if (e == null) return false;
        boolean m = itemsMatchSlotsPred(server, e, slotName, predStr);
        return negate ? !m : m;
    }

    /** 멀티 플레이어(@a/@p/@r 태그필터) items 조건 — 바닐라: 매치 엔티티 0명이면 예외(미실행),
     *  1명 이상이면 '아이템 매치 합>0' 을 negate 적용해 판정. */
    public static boolean anyPlayerItemsCond(GameContext ctx, String[] tagsPos, String[] tagsNeg,
                                             String slot, String itemId, String customNbt, boolean negate) {
        boolean anyEntity = false, found = false;
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            boolean ok = true;
            for (String t : tagsPos) if (!p.getCommandTags().contains(t)) { ok = false; break; }
            if (ok) for (String t : tagsNeg) if (p.getCommandTags().contains(t)) { ok = false; break; }
            if (!ok) continue;
            anyEntity = true;
            if (!found && itemsMatchSlots(p, slot, itemId, customNbt)) found = true;
            if (found && !negate) return true;   // if: 조기 종료 가능
        }
        if (!anyEntity) return false;            // 대상 없음 = 바닐라 예외 = 미실행
        return negate ? !found : found;
    }

    /** 멀티 엔티티(@e/@n 태그필터) items 조건 — anyPlayerItemsCond 와 동일 시맨틱. */
    public static boolean anyEntityItemsCond(GameContext ctx, String[] tagsPos, String[] tagsNeg,
                                             String slot, String itemId, String customNbt, boolean negate) {
        boolean anyEntity = false, found = false;
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!e.isAlive()) continue;                      // @e/@n: 바닐라 기본 isAlive 술어
            boolean ok = true;
            for (String t : tagsPos) if (!e.getCommandTags().contains(t)) { ok = false; break; }
            if (ok) for (String t : tagsNeg) if (e.getCommandTags().contains(t)) { ok = false; break; }
            if (!ok) continue;
            anyEntity = true;
            if (!found && itemsMatchSlots(e, slot, itemId, customNbt)) found = true;
            if (found && !negate) return true;
        }
        if (!anyEntity) return false;
        return negate ? !found : found;
    }

    /** if items entity @a <slot> <pred> : 어떤 플레이어든 슬롯에 일치 아이템 보유.
     *  (@a/@p/@r 셀렉터의 태그 필터까지 반영; 단일 해소 불가한 멀티 타겟의 존재검사.) */
    public static boolean anyPlayerItemsMatch(GameContext ctx, String[] tagsPos, String[] tagsNeg,
                                              String slot, String itemId, String customNbt) {
        for (net.minecraft.server.network.ServerPlayerEntity p : ctx.allPlayers) {
            boolean ok = true;
            for (String t : tagsPos) if (!p.getCommandTags().contains(t)) { ok = false; break; }
            if (ok) for (String t : tagsNeg) if (p.getCommandTags().contains(t)) { ok = false; break; }
            if (ok && itemsMatchSlots(p, slot, itemId, customNbt)) return true;
        }
        return false;
    }

    /** if items entity @e/@n <slot> <pred> : 어떤 엔티티든 슬롯에 일치 아이템 보유(태그 필터 반영). */
    public static boolean anyEntityItemsMatch(GameContext ctx, String[] tagsPos, String[] tagsNeg,
                                              String slot, String itemId, String customNbt) {
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!e.isAlive()) continue;                      // @e/@n: 바닐라 기본 isAlive 술어
            boolean ok = true;
            for (String t : tagsPos) if (!e.getCommandTags().contains(t)) { ok = false; break; }
            if (ok) for (String t : tagsNeg) if (e.getCommandTags().contains(t)) { ok = false; break; }
            if (ok && itemsMatchSlots(e, slot, itemId, customNbt)) return true;
        }
        return false;
    }

    /** 엔티티가 요구 태그(tagsPos 전부)·배제 태그(tagsNeg 없음)를 만족하는지. */
    public static boolean entityHasTags(net.minecraft.entity.Entity e, String[] tagsPos, String[] tagsNeg) {
        if (e == null || !e.isAlive()) return false;   // @e 대상 해소 전용 — 바닐라 isAlive 술어
        for (String t : tagsPos) if (!e.getCommandTags().contains(t)) return false;
        for (String t : tagsNeg) if (e.getCommandTags().contains(t)) return false;
        return true;
    }

    public static boolean entityScoreMatches(ServerScoreboard sb, net.minecraft.entity.Entity e,
                                             String o, Integer min, Integer max) {
        if (e == null) return false;
        return scoreMatches(sb, nameOf(e), o, min, max);   // nameOf: 비-플레이어 UUID 문자열 캐시
    }
    // ObjRef 판(pass-4 승격) — 읽기 엔트리 핸들 캐시 경유, 시맨틱 동일.
    public static boolean entityScoreMatches(ServerScoreboard sb, net.minecraft.entity.Entity e,
                                             ObjRef o, Integer min, Integer max) {
        if (e == null) return false;
        Integer v = readVal(sb, e, o);
        if (v == null) return false;
        if (min != null && v < min) return false;
        if (max != null && v > max) return false;
        return true;
    }
    public static boolean entityScoreMatches(ServerScoreboard sb, net.minecraft.entity.Entity e,
                                             ObjRef o, Integer min, Integer max, boolean neg) {
        if (e == null) return false;
        return neg ^ entityScoreMatches(sb, e, o, min, max);
    }

    /** 셀렉터 홀더 score 조건(if/unless). 셀렉터가 비면(e==null) 바닐라에선 "No entity" 에러로
     *  명령 전체가 미실행 → if/unless 무관하게 false 반환. neg(=unless)는 점수 비교에만 적용. */
    public static boolean entityScoreMatches(ServerScoreboard sb, net.minecraft.entity.Entity e,
                                             String o, Integer min, Integer max, boolean neg) {
        if (e == null) return false;
        boolean m = scoreMatches(sb, nameOf(e), o, min, max);
        return neg ? !m : m;
    }

    /** 조건에 맞는 모든 엔티티(셀렉터 @e[...] 무제한). set/add/remove 를 전체에 적용할 때 사용. */
    public static java.util.List<net.minecraft.entity.Entity> allEntities(
            GameContext ctx, net.minecraft.entity.Entity origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        // 무제한 + 양성 태그: 태그 버킷 경로(결과 집합·타입-우선 순서 동일, 후보만 소수).
        java.util.List<net.minecraft.entity.Entity> _tb =
                (origin != null && maxDist >= 0) ? tagCandidatesBounded(ctx, tagsPos) : tagCandidates(ctx, tagsPos);
        if (_tb != null) {
            java.util.List<net.minecraft.entity.Entity> out2 = new java.util.ArrayList<>();
            for (net.minecraft.entity.EntityType<?> t : types) {
                for (net.minecraft.entity.Entity e : _tb) {
                    if (e.getType() != t) continue;
                    if (matchTagsAlive(e, tagsPos, tagsNeg)
                            && (origin == null || inRange(origin, e, minDist, maxDist))) out2.add(e);
                }
            }
            return out2;
        }
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.EntityType<?> t : types) {
            if (origin != null && maxDist >= 0) {
                out.addAll(entitiesByTypeBox(ctx, t, rangeBox(origin.getPos(), maxDist),
                        en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)));
            } else {
                out.addAll(entitiesByType(ctx, t,
                        en -> matchTagsAlive(en, tagsPos, tagsNeg)
                              && (origin == null || inRange(origin, en, minDist, maxDist))));
            }
        }
        return out;
    }

    /** 타입 무관 전 엔티티 순회 버전 (as @e[무타입]). */
    public static java.util.List<net.minecraft.entity.Entity> allEntitiesAnyType(
            GameContext ctx, net.minecraft.entity.Entity origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            java.util.List<net.minecraft.entity.Entity> _tbb = tagCandidatesBounded(ctx, tagsPos);
            if (_tbb != null) {
                java.util.List<net.minecraft.entity.Entity> _out = new java.util.ArrayList<>();
                for (net.minecraft.entity.Entity e : _tbb) {
                    if (matchTagsAlive(e, tagsPos, tagsNeg) && inRange(origin, e, minDist, maxDist)) _out.add(e);
                }
                return _out;
            }
            return ctx.world.getOtherEntities(null, rangeBox(origin.getPos(), maxDist),
                    en -> matchTagsAlive(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
        }
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.Entity e : tagOrSnap(ctx, tagsPos)) {
            if (!matchTagsAlive(e, tagsPos, tagsNeg)) continue;
            if (origin != null && !inRange(origin, e, minDist, maxDist)) continue;
            out.add(e);
        }
        return out;
    }

    // ──────────────── storage NBT (data ... storage <id> <path>) ────────────────
    // 경로는 점 표기('a.b.c')만 1차 지원. 배열 인덱스/필터는 호출부에서 거르며, 여기 도달하면
    // 단순 경로로 처리(없으면 0). storage 변경은 set 으로 되써야 영속화된다.

    private static net.minecraft.nbt.NbtCompound storageRoot(
            net.minecraft.server.MinecraftServer server, String id) {
        net.minecraft.util.Identifier sid = idOf(id.contains(":") ? id : "minecraft:" + id);
        return server.getDataCommandStorage().get(sid);
    }

    private static void storageSave(net.minecraft.server.MinecraftServer server, String id,
                                    net.minecraft.nbt.NbtCompound root) {
        net.minecraft.util.Identifier sid = idOf(id.contains(":") ? id : "minecraft:" + id);
        server.getDataCommandStorage().set(sid, root);
    }

    // ──────────────── NBT 경로 복사 (data modify ... set from) ────────────────
    // MC 의 NbtPathArgumentType 를 런타임 파싱해 임의 경로(점/[인덱스]/{필터})를 정확히 처리.

    private static net.minecraft.command.argument.NbtPathArgumentType.NbtPath nbtPath(String s) {
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = NBTPATH_CACHE.get(s);
        if (p != null) return p;
        try {
            p = net.minecraft.command.argument.NbtPathArgumentType.NbtPath.parse(s);
            NBTPATH_CACHE.put(s, p);   // 성공 파싱만 캐시(불변·재사용 안전). 실패 경로는 드물어 미캐시.
            return p;
        } catch (Exception e) { return null; }
    }

    /** 루트 NBT 에서 경로 첫 매치 요소(없으면 null). */
    private static net.minecraft.nbt.NbtElement getAtPath(net.minecraft.nbt.NbtElement root, String path) {
        if (root == null) return null;
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);
        if (p == null) return null;
        try {
            java.util.List<net.minecraft.nbt.NbtElement> ls = p.get(root);
            return ls.isEmpty() ? null : ls.get(0);
        } catch (Exception e) { return null; }
    }

    /** 루트 NBT 의 경로에 value 를 set(중간 생성). 성공 시 true. */
    private static boolean putAtPath(net.minecraft.nbt.NbtElement root, String path, net.minecraft.nbt.NbtElement value) {
        if (value == null) return false;
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);
        if (p == null) return false;
        try { p.put(root, value); return true; }
        catch (Exception e) { return false; }
    }

    /** putAtPath 와 동일하되 NbtPath.put 의 반환값(실제 변경된 노드 수)을 그대로 돌려준다.
     *  바닐라 NbtPath.put 은 MutableBoolean+NbtElement.equals 로 값이 실제 바뀐 터미널만 카운트하므로
     *  (동일 값 set → 0, 다른 값/신규 경로 → ≥1), 이는 /data modify 의 결과=수정 개수와 관측 동등.
     *  store success 는 이 값>0(=변경 있었음)을 성공(1)으로 쓴다(바닐라 시맨틱: 바뀐 게 있으면 성공). */
    private static int putAtPathCount(net.minecraft.nbt.NbtElement root, String path, net.minecraft.nbt.NbtElement value) {
        if (value == null) return 0;
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);
        if (p == null) return 0;
        try { return p.put(root, value); }
        catch (Exception e) { return 0; }
    }

    // ──────────────── 엔티티 NBT 읽기 스냅샷 캐시 ────────────────
    //  같은 틱 안에서 같은 엔티티를 여러 번 읽을 때 writeNbt(엔티티 전체 직렬화) 반복을 제거한다.
    //  불변식(원본과 관측 동등): 엔티티가 항상 진실의 원천 — 캐시는 '현재 틱·미변경' 상태에서만 재사용된다.
    //   · e.age (엔티티가 틱하면 +1) 가 바뀌면 무효 → 틱 경계에서 자동 갱신(엔티티 자체 틱 변화 반영).
    //   · ENTITY_GEN (소환/킬/브릿지 디스패치 시 증가) 이 바뀌면 무효 → 외부 변경 반영.
    //   · 우리 쓰기 경로(set/put/append/merge/remove)는 invalidateSnapshot 로 즉시 무효(write-through).
    //  명령 함수는 엔티티 틱 페이즈와 분리되어 동기 실행되므로, 한 실행 내 다중 읽기는 동일 age 로 안전하다.
    private static final boolean ENTITY_READ_CACHE = true;

    // 측정 결론(8주행+8관전, 동일 트랙/주행): typeBucket(false)이 box(true)보다 빠름.
    //   onStartTick 18.10% vs 19.17%, kartmain_loop 14.68% vs 15.53%,
    //   getEntitiesByType 0.52% vs 0.88%, nearestEntity+anyEntity 0.22% vs 0.87%.
    // box 한정 섹션 스캔은 순회량은 줄지만 매 호출 List 할당 + predicate lambda 비용이 더 컸다.
    // (워크로드가 크게 바뀌면 true 로 재측정 가능하도록 토글은 남겨둔다.)
    private static final boolean QUERY_BOX = false;
    // 21차: 박스/타입 질의를 자체 인덱스(타입버킷/스냅샷/태그버킷)로 순회. off = 종전 섹션 스캔.
    private static final boolean QUERY_IDX =
            !"off".equalsIgnoreCase(System.getProperty("kfc.queryidx", "on"));
    // @e[limit=N] sort 미지정은 바닐라에서 arbitrary(정렬 안 함)이다. 현재 변환은 거리 정렬
    // (nearestN)으로 처리하는데, 이는 (1) 고증 편차(arbitrary↔nearest)이고 (2) 불필요한 정렬
    // 비용이다. emit 이 sort=nearest 여부를 wantNearest 로 넘기므로, 이 토글이 false 면 명시적
    // sort=nearest 만 정렬하고 arbitrary 는 첫 N개(반복 순서=바닐라)로 처리해 정렬을 생략한다.
    // 기본 true = 현행 동작 유지(안전). false 로 두고 재빌드하면 최적화 활성(A/B 측정용).
    private static final boolean LIMIT_SORT_NEAREST = false;
    private static final class NbtSnap {
        final net.minecraft.nbt.NbtCompound nbt; final int age; final long gen;
        NbtSnap(net.minecraft.nbt.NbtCompound n, int a, long g) { nbt = n; age = a; gen = g; }
    }
    private static final java.util.Map<net.minecraft.entity.Entity, NbtSnap> ENTITY_NBT_SNAP =
            java.util.Collections.synchronizedMap(new java.util.WeakHashMap<net.minecraft.entity.Entity, NbtSnap>());

    /** 엔티티 writeNbt 스냅샷(현재 틱·미변경이면 재사용). 읽기 전용으로만 사용 — 변형 금지.
     *  [버그 이력·16차] interaction 엔티티는 캐시 제외. InteractionEntity.tick() 은 빈 메서드라
     *  (바이트코드: return 1줄) age 가 영원히 불변 → 'age 변화 = 틱 경계 무효화' 불변식이 깨져
     *  스냅샷이 무기한 유효했다. 그런데 클릭 데이터(interaction.attack/player)는 바닐라가 우리
     *  쓰기 훅 밖에서 기록하므로 캐시에 가려져, 온디맨드 소환 UI(트랙 선택룸)의 첫 클릭이
     *  ENTITY_GEN 이 우연히 오를 때까지(로비에서 1~2초) 무시됐다(상시 소환 사운드룸은 주변
     *  시스템의 잦은 무효화로 증상이 안 보였을 뿐 같은 결함). interaction 은 NBT 표면이 작고
     *  (width/height/response/attack/interaction) UI 폴링에서만 읽혀 fresh writeNbt 비용이 미미하다.
     *  (marker 등 다른 무틱 엔티티는 바닐라발 NBT 변이 경로가 없어 — 클릭류 기록은 interaction
     *  전용 메커니즘 — 종전 캐시 유지가 안전하다. 우리 쓰기는 전부 invalidateSnapshot 경유.) */
    private static net.minecraft.nbt.NbtCompound entitySnapshot(net.minecraft.entity.Entity e) {
        if (ENTITY_READ_CACHE && !(e instanceof net.minecraft.entity.decoration.InteractionEntity)) {
            NbtSnap s = ENTITY_NBT_SNAP.get(e);
            if (s != null && s.age == e.age && s.gen == ENTITY_GEN) return s.nbt;
            net.minecraft.nbt.NbtCompound n = new net.minecraft.nbt.NbtCompound();
            e.writeNbt(n);
            _addSelectedItem(e, n);
            ENTITY_NBT_SNAP.put(e, new NbtSnap(n, e.age, ENTITY_GEN));
            return n;
        }
        net.minecraft.nbt.NbtCompound n = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(n);
        _addSelectedItem(e, n);
        return n;
    }

    /** 바닐라 NbtPredicate.entityToNbt 동등: 플레이어는 writeNbt 후 선택 슬롯의 아이템을
     *  'SelectedItem' 키로 추가한다. data get / with entity @s SelectedItem 이 이 필드에
     *  의존하는데(writeNbt 에는 SelectedItemSlot 슬롯번호만 있고 아이템 자체는 없음),
     *  이게 없으면 매크로 인자 $(id) 등이 비어 폴백 함수(예: 커스텀 모델 건축의 $setblock $(id))가
     *  빈 블록을 놓아 실패한다. */
    private static void _addSelectedItem(net.minecraft.entity.Entity e, net.minecraft.nbt.NbtCompound n) {
        if (e instanceof net.minecraft.entity.player.PlayerEntity _p) {
            net.minecraft.item.ItemStack _sel = _p.getInventory().getSelectedStack();
            if (!_sel.isEmpty())
                n.put("SelectedItem", _sel.toNbt(e.getRegistryManager()));
        }
    }

    /** W1 쓰기 소스 컴파운드 — fresh writeNbt 동등 보장판.
     *  스냅샷 재사용은 display/marker/interaction(순수 데이터 홀더)로 한정하고, 커맨드 페이즈
     *  중 'invalidate 없이' 변할 수 있는 휘발 필드를 라이브 값으로 덮어쓴다:
     *    · Pos/Motion/Rotation/OnGround/fall_distance — 차량 teleport 의 승객 드래그/dismount 가
     *      스냅샷 무효화 없이 위치류를 바꾼다(정지 상태에선 동일값이라 무해 — 주행 중 변신 파츠
     *      롤백 버그의 원인이었음).
     *    · Tags — addTag/removeTag 는 버킷 증분 갱신만 하고 스냅샷을 invalidate 하지 않는다
     *      (읽기는 liveFieldNbt(Tags) 가 라이브라 종전엔 무해했음).
     *  덮어쓰기 인코딩은 writeNbt 원천 게터와 동일(liveFieldNbt 검증 결과 재사용 — 탑승 Pos 규칙 포함).
     *  그 외 엔티티(생물/플레이어 등 휘발 표면이 넓은 부류)는 종전 fresh writeNbt 유지. */
    private static net.minecraft.nbt.NbtCompound writeSourceCompound(net.minecraft.entity.Entity e) {
        if (!(e instanceof net.minecraft.entity.decoration.DisplayEntity
                || e instanceof net.minecraft.entity.decoration.InteractionEntity
                || e instanceof net.minecraft.entity.MarkerEntity)) {
            net.minecraft.nbt.NbtCompound fresh = new net.minecraft.nbt.NbtCompound();
            e.writeNbt(fresh);
            _addSelectedItem(e, fresh);
            return fresh;
        }
        net.minecraft.nbt.NbtCompound n = entitySnapshot(e).copy();
        n.put("Pos", liveFieldNbt(e, "Pos"));
        n.put("Motion", liveFieldNbt(e, "Motion"));
        n.put("Rotation", liveFieldNbt(e, "Rotation"));
        n.putBoolean("OnGround", e.isOnGround());
        n.putDouble("fall_distance", e.fallDistance);
        java.util.Set<String> tags = e.getCommandTags();
        if (tags.isEmpty()) {
            n.remove("Tags");                       // writeNbt: 비면 키 자체 생략
        } else {
            net.minecraft.nbt.NbtList tl = new net.minecraft.nbt.NbtList();
            for (String t : tags) tl.add(net.minecraft.nbt.NbtString.of(t));
            n.put("Tags", tl);
        }
        return n;
    }

    /** data-홀더(display/marker/interaction) 판별 — 재시드/W1 스냅샷 재사용 대상. */
    private static boolean isDataHolder(net.minecraft.entity.Entity e) {
        return e instanceof net.minecraft.entity.decoration.DisplayEntity
                || e instanceof net.minecraft.entity.decoration.InteractionEntity
                || e instanceof net.minecraft.entity.MarkerEntity;
    }

    /** data.* 쓰기 성공 직후 스냅샷 '재시드' — 무효화 대신 방금 적용한 컴파운드를 새 스냅샷으로.
     *  근거: 카트 루트처럼 승객 트리를 거느린 엔티티는 스냅샷 재구축(writeNbt)이 모델 전체
     *  직렬화(saveSelfNbt 재귀)라, data.pos 류 쓰기마다 무효화하면 틱당 수회 전량 재직렬화가
     *  발생한다(프로파일 실측 ~5%p). 재시드 정당성(fail-closed 조건):
     *    · 대상이 data-홀더 클래스이고 수정 경로 최상위가 "data" 뿐일 때만.
     *    · customData 는 readNbt 가 컴파운드를 '그대로' 보관·writeNbt 가 그대로 재방출(코덱
     *      정규화 없음 — 1.21.5 NbtComponent 필드 확인) → n 이 곧 다음 writeNbt 산출과 동일.
     *    · n 의 나머지 필드는 writeSourceCompound 가 라이브 동등화한 값 = fresh writeNbt 동일.
     *    · readNbt(n) 의 디스플레이 재적용은 동일 값 트래커 set(no-op) — 직렬화 결과 불변.
     *  별칭 안전: 스냅샷은 읽기 전용 계약이고 쓰기측은 항상 copy 후 변형(기존 계약 동일). */
    private static void reseedOrInvalidate(net.minecraft.entity.Entity e, String pt,
                                           net.minecraft.nbt.NbtCompound applied) {
        if (isDataHolder(e) && topSeg(pt).equals("data")) {
            ENTITY_NBT_SNAP.put(e, new NbtSnap(applied, e.age, ENTITY_GEN));
        } else {
            invalidateSnapshot(e);
        }
    }

    /** 엔티티 NBT 를 바꾸는 쓰기 직후 스냅샷 무효화(write-through). */
    private static void invalidateSnapshot(net.minecraft.entity.Entity e) {
        if (ENTITY_READ_CACHE) ENTITY_NBT_SNAP.remove(e);
    }

    // ── 구조적 no-op NBT 경로 자가검증/생략 (write/read 양쪽) ───────────────────────────────
    //  배경: display/marker 엔티티에 `data.X`(예: data.interpolation_duration, data.loop-data.*)를
    //   매 틱 쓰지만, 그 엔티티 클래스의 readNbt 는 스키마에 없는 최상위 키(`data`)를 버린다.
    //   따라서 writeNbt→putAtPath→readNbt 는 '순수 no-op'(엔티티 상태 불변)인데도 매 틱 전체
    //   엔티티를 직렬화/역직렬화한다(spark: entityPutNumberPath 핫). 읽기도 같은 경로를 snapshot
    //   (writeNbt)으로 조회하나 항상 부재→0 이다.
    //  최적화: 런타임 1회 자가검증으로 '(엔티티 클래스, 최상위 키)' 단위 droppable 여부를 판정·캐시한 뒤,
    //   이후 해당 경로의 쓰기는 writeNbt 자체를 생략하고, 읽기는 snapshot 없이 0 을 반환한다.
    //  정확성(원본 관측 동등): droppable 판정은 '쓰기 전·후 직렬화 모두에 최상위 키 부재'일 때만 한다.
    //   값이 아니라 엔티티 스키마(클래스)에 그 키가 없다는 구조적 사실이므로 값-독립적으로 안전하다.
    //   (실 필드 Pos/Rotation/Tags/attributes/Passengers/Health 등은 쓰기 전 직렬화에 키가 있어
    //    절대 droppable 로 분류되지 않고 기존과 동일하게 round-trip 한다.)
    //  보편성: 특정 데이터팩/경로 하드코딩 없음 — 어떤 엔티티·경로든 런타임 관측으로 판정한다.
    private static final java.util.Map<String, Boolean> NBT_DROP =
            new java.util.HashMap<>();

    private static String topSeg(String path) {
        int end = path.length();
        int dot = path.indexOf('.'); if (dot >= 0 && dot < end) end = dot;
        int br  = path.indexOf('['); if (br  >= 0 && br  < end) end = br;
        return path.substring(0, end);
    }
    private static String dropKey(net.minecraft.entity.Entity e, String topKey) {
        return e.getClass().getName() + '\u0000' + topKey;
    }
    /** 이 경로가 (엔티티 클래스 기준) 구조적으로 버려지는 no-op 경로로 이미 판정됐는가. */
    private static boolean nbtPathDroppable(net.minecraft.entity.Entity e, String path) {
        return NBT_DROP.get(dropKey(e, topSeg(path))) == Boolean.TRUE;
    }

    /** writeNbt→put→readNbt 엔티티 NBT 쓰기 공통 경로 + 구조적 no-op 자가검증/생략.
     *  원본(writeNbt;putAtPath;readNbt)과 관측 동등하되, droppable 로 판정된 경로는 직렬화를 생략한다. */
    private static void entityNbtRoundtrip(net.minecraft.entity.Entity e, String path,
                                           net.minecraft.nbt.NbtElement v) {
        if (e == null || v == null) return;
        String top = topSeg(path.replace(" ", ""));
        String k = dropKey(e, top);
        Boolean known = NBT_DROP.get(k);
        if (known != null) {
            if (known) return;                       // 구조적 no-op — writeNbt 생략(엔티티 불변)
            // effective — 작업 컴파운드를 읽기 스냅샷 캐시의 copy 로 조달(웜이면 직렬화 0회,
            // 콜드면 스냅샷으로 1회 직렬화 후 같은 틱 읽기/쓰기가 재사용). 스냅샷 신선도 계약은
            // 읽기와 동일(age/ENTITY_GEN/우리 쓰기 invalidate). 플레이어 스냅샷의 합성
            // SelectedItem 키는 readNbt 가 무시하는 여분 키라 관측 불변.
            net.minecraft.nbt.NbtCompound n = writeSourceCompound(e);
            // 무변경(동일 값) 이면 바닐라 /data modify 는 'Nothing changed' 로 명령이 실패해
            // setNbt(readNbt) 자체가 없다 — 종전 무조건 readNbt(부수효과 포함)보다 고증 정합.
            // invalidate 도 실제 적용 시에만(무변경이면 스냅샷 웜 유지).
            if (putAtPathCount(n, path, v) > 0) {
                invalidateSnapshot(e);
                readNbtTagAware(e, n);
                reseedOrInvalidate(e, top, n);   // data.* — 승객 트리 재직렬화 없이 스냅샷 갱신
            }
            return;
        }
        // 첫 관측: 자가검증(추가 writeNbt 1회 — (클래스,키)당 1회만, 이후 캐시 hit)
        invalidateSnapshot(e);
        net.minecraft.nbt.NbtCompound before = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(before);
        net.minecraft.nbt.NbtCompound mod = before.copy();
        if (!putAtPath(mod, path, v)) return;        // 경로 적용 실패 → 변화 없음(분류 보류)
        if (mod.equals(before)) return;              // put 이 NBT 를 안 바꿈(이미 동일값) → no-op(분류 보류)
        readNbtTagAware(e, mod);
        boolean beforeHas = before.contains(top);
        net.minecraft.nbt.NbtCompound after = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(after);
        boolean afterHas = after.contains(top);
        // 전·후 모두 최상위 키 부재 = readNbt 가 버림 = 구조적 droppable(값-독립).
        NBT_DROP.put(k, (!beforeHas && !afterHas) ? Boolean.TRUE : Boolean.FALSE);
    }

    // ── source 읽기 ──
    /** 엔티티 NBT 읽기 fast-path 체인(라이브 게터 → 디스플레이 → 슬롯 접근자).
     *  해당되면 전체 엔티티 writeNbt 스냅샷 없이 직접 구성한 NbtElement, 아니면 null.
     *  내부 숫자 읽기(entityGetDouble/Number)와 nbtGetEntity 가 공유해 동일 의미·일관성 보장. */
    private static net.minecraft.nbt.NbtElement entityNbtFast(net.minecraft.entity.Entity e, String pt) {
        net.minecraft.nbt.NbtElement lf = liveFieldNbt(e, pt);        // Pos/Rotation/Motion 라이브 게터
        if (lf != null) return lf;
        net.minecraft.nbt.NbtElement f = displayGetFast(e, pt);       // display transformation 등
        if (f != null) return f;
        return slotAccessorNbt(e, pt);                                // weapon/equipment/armor/container 슬롯
    }

    public static net.minecraft.nbt.NbtElement nbtGetEntity(net.minecraft.entity.Entity e, String path) {
        if (e == null) return null;
        String pt0 = path.replace(" ", "");
        net.minecraft.nbt.NbtElement fast = entityNbtFast(e, pt0);
        if (fast != null) return fast;
        if (nbtPathDroppable(e, pt0)) return null;   // 구조적 부재 경로 — snapshot writeNbt 회피
        // 캐시된 스냅샷(현재 상태)에서 읽되, 캐시 내부 객체가 호출부로 새지 않도록 copy 해 반환
        // (원본은 throwaway writeNbt 버퍼를 반환했으므로 copy 가 소유권 의미상 동일·더 안전).
        net.minecraft.nbt.NbtElement r = getAtPath(entitySnapshot(e), path);
        return r == null ? null : r.copy();
    }

    /** writeNbt 라운드트립 없이 라이브 게터로 동일한 NbtElement 를 구성하는 fast-path.
     *  entityGetDouble 이 이미 신뢰하는 게터(getPos/getYaw/getPitch/getVelocity)와 동일 의미.
     *  writeNbt 포맷: Pos=NbtList&lt;Double&gt;[x,y,z], Rotation=NbtList&lt;Float&gt;[yaw,pitch],
     *  Motion=NbtList&lt;Double&gt;[vx,vy,vz]. 모르는 경로는 null → 호출부가 폴백. */
    private static net.minecraft.nbt.NbtElement liveFieldNbt(net.minecraft.entity.Entity e, String pt) {
        switch (pt) {
            // Pos: 1.21.5 Entity.writeNbt 는 탑승 중이면 (vehicle.x, this.y, vehicle.z) 를 기록
            // (바이트코드 확인). data get 고증 정합을 위해 동일 규칙 적용.
            case "Pos[0]": return net.minecraft.nbt.NbtDouble.of(nbtPosX(e));
            case "Pos[1]": return net.minecraft.nbt.NbtDouble.of(e.getY());
            case "Pos[2]": return net.minecraft.nbt.NbtDouble.of(nbtPosZ(e));
            case "Rotation[0]": return net.minecraft.nbt.NbtFloat.of(e.getYaw());
            case "Rotation[1]": return net.minecraft.nbt.NbtFloat.of(e.getPitch());
            case "Motion[0]": return net.minecraft.nbt.NbtDouble.of(e.getVelocity().x);
            case "Motion[1]": return net.minecraft.nbt.NbtDouble.of(e.getVelocity().y);
            case "Motion[2]": return net.minecraft.nbt.NbtDouble.of(e.getVelocity().z);
            case "Pos": {
                net.minecraft.nbt.NbtList l = new net.minecraft.nbt.NbtList();
                l.add(net.minecraft.nbt.NbtDouble.of(nbtPosX(e)));
                l.add(net.minecraft.nbt.NbtDouble.of(e.getY()));
                l.add(net.minecraft.nbt.NbtDouble.of(nbtPosZ(e)));
                return l;
            }
            case "Rotation": {
                net.minecraft.nbt.NbtList l = new net.minecraft.nbt.NbtList();
                l.add(net.minecraft.nbt.NbtFloat.of(e.getYaw()));
                l.add(net.minecraft.nbt.NbtFloat.of(e.getPitch()));
                return l;
            }
            case "Motion": {
                net.minecraft.util.math.Vec3d v = e.getVelocity();
                net.minecraft.nbt.NbtList l = new net.minecraft.nbt.NbtList();
                l.add(net.minecraft.nbt.NbtDouble.of(v.x));
                l.add(net.minecraft.nbt.NbtDouble.of(v.y));
                l.add(net.minecraft.nbt.NbtDouble.of(v.z));
                return l;
            }
            // ── #8 확장: writeNbt 가 '무조건' 기록하는 스칼라 — 게터 = 직렬화 원천(바이트코드 확인)
            case "fall_distance": return net.minecraft.nbt.NbtDouble.of(e.fallDistance);
            case "Fire": return net.minecraft.nbt.NbtShort.of((short) e.getFireTicks());
            case "Air": return net.minecraft.nbt.NbtShort.of((short) e.getAir());
            case "OnGround": return net.minecraft.nbt.NbtByte.of(e.isOnGround());
            case "Invulnerable": return net.minecraft.nbt.NbtByte.of(e.isInvulnerable());
            case "PortalCooldown": return net.minecraft.nbt.NbtInt.of(e.getPortalCooldown());
            case "TicksFrozen": return net.minecraft.nbt.NbtInt.of(e.getFrozenTicks());
            // Health: LivingEntity.writeCustomData 가 putFloat(getHealth()) — 비생물은 키 부재라
            // fast-path 미적용(null 반환 → 스냅샷 폴백이 동일하게 '없음' 반환).
            case "Health":
                return (e instanceof net.minecraft.entity.LivingEntity le)
                        ? net.minecraft.nbt.NbtFloat.of(le.getHealth()) : null;
            // Tags: 비어 있으면 writeNbt 가 키 자체를 생략 — 그 경우 폴백(스냅샷도 '없음').
            case "Tags": {
                java.util.Set<String> tags = e.getCommandTags();
                if (tags.isEmpty()) return null;
                net.minecraft.nbt.NbtList l = new net.minecraft.nbt.NbtList();
                for (String t : tags) l.add(net.minecraft.nbt.NbtString.of(t));
                return l;
            }
            // Silent/NoGravity: true 일 때만 기록(조건부) — false 면 폴백(스냅샷도 '없음').
            case "Silent": return e.isSilent() ? net.minecraft.nbt.NbtByte.of(true) : null;
            case "NoGravity": return e.hasNoGravity() ? net.minecraft.nbt.NbtByte.of(true) : null;
            default: return null;
        }
    }

    /** writeNbt 의 Pos.x — 탑승 중이면 vehicle.getX() (1.21.5 바이트코드 동일). */
    private static double nbtPosX(net.minecraft.entity.Entity e) {
        net.minecraft.entity.Entity v = e.getVehicle();
        return v != null ? v.getX() : e.getX();
    }
    /** writeNbt 의 Pos.z — 탑승 중이면 vehicle.getZ(). */
    private static double nbtPosZ(net.minecraft.entity.Entity e) {
        net.minecraft.entity.Entity v = e.getVehicle();
        return v != null ? v.getZ() : e.getZ();
    }

    public static net.minecraft.nbt.NbtElement nbtGetStorage(net.minecraft.server.MinecraftServer server, String id, String path) {
        return getAtPath(storageRoot(server, id), path);
    }

    /** if data storage <id> <path> — 경로 존재 검사. NbtPath 라 리스트 인덱스(foo[0]) 지원. */
    public static boolean storageHasPath(net.minecraft.server.MinecraftServer server, String id, String path) {
        return nbtGetStorage(server, id, path) != null;
    }

    /** store result ... if data <entity|storage> <path> (run 없음) 의 값 = 경로 매칭 원소 수.
     *  바닐라는 if data 의 result 로 NbtPath.count(매칭 개수)를 쓴다. unless 면 0개일 때 1 else 0. */
    public static int entityPathCount(net.minecraft.entity.Entity e, String path) {
        if (e == null) return 0;
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);
        if (p == null) return 0;
        try { return p.count(entitySnapshot(e)); } catch (Exception ex) { return 0; }
    }
    public static int storagePathCount(net.minecraft.server.MinecraftServer server, String id, String path) {
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);
        if (p == null) return 0;
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        if (root == null) return 0;
        try { return p.count(root); } catch (Exception ex) { return 0; }
    }

    public static net.minecraft.nbt.NbtElement nbtGetBlock(net.minecraft.server.world.ServerWorld world,
                                                           net.minecraft.util.math.Vec3d pos, String path) {
        net.minecraft.util.math.BlockPos bp = net.minecraft.util.math.BlockPos.ofFloored(pos);
        net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(bp);
        if (be == null) return null;
        return getAtPath(be.createNbtWithIdentifyingData(world.getRegistryManager()), path);
    }

    // ── target 쓰기 ──
    // ── display 엔티티 fast-path: 엔티티 전체 NBT 라운드트립(writeNbt/readNbt) 없이 직접 setter ──
    //    run-anime/boost 가 매 틱 transformation·interpolation_duration 을 쓰므로 핵심 핫패스.
    /** item_display 의 표시 아이템(item 키) fast-path 공통.
     *  바닐라 ItemDisplayEntity write/readCustomDataToNbt 와 관측 동등:
     *   write: nbt.put("item", ItemStack.CODEC, regOps, getItemStack())  (빈 스택이면 키 생략)
     *   read : setItemStack(get("item", ItemStack.CODEC, regOps).orElse(EMPTY))
     *  여기선 전체 엔티티 writeNbt/readNbt(transformation 행렬 codec 등) 없이 아이템 스택만 round-trip.
     *  merge=true: 현재 표시 아이템 NBT 에 deep merge(/data merge 의미). false: itemNbt 로 대체(set).
     *  카트 모델의 매 틱 {item:{components:{...}}} 머지가 이 경로로 풀 직렬화를 회피한다. */
    private static boolean applyItemDisplayNbt(
            net.minecraft.entity.decoration.DisplayEntity.ItemDisplayEntity d,
            net.minecraft.nbt.NbtCompound itemNbtIn, boolean merge) {
        net.minecraft.registry.RegistryWrapper.WrapperLookup lookup = d.getRegistryManager();
        net.minecraft.nbt.NbtCompound itemNbt;
        if (merge) {
            net.minecraft.item.ItemStack cur = d.getItemStack();
            if (cur.isEmpty()) {
                // 바닐라 writeNbt 는 빈 스택이면 "item" 키를 생략 → 병합 대상은 patch 단독.
                itemNbt = new net.minecraft.nbt.NbtCompound();
            } else {
                net.minecraft.nbt.NbtElement enc = cur.toNbt(lookup);   // == ItemStack.CODEC encode
                if (!(enc instanceof net.minecraft.nbt.NbtCompound c)) return false;
                itemNbt = c;
            }
            itemNbt.copyFrom(itemNbtIn);   // deep merge (NbtCompound.copyFrom = /data merge 의미)
        } else {
            itemNbt = itemNbtIn;
        }
        net.minecraft.item.ItemStack ns =
                net.minecraft.item.ItemStack.fromNbt(lookup, itemNbt)
                        .orElse(net.minecraft.item.ItemStack.EMPTY);   // == readCustomDataFromNbt
        // [헤드 플래시 수정] ItemStack 은 equals 미구현(신원 비교)이라 DataTracker 가 '같은 값의 새
        // 객체'도 항상 dirty 처리한다 → 값이 안 변한 프레임 머지(컷씬/GHZ 바다 애니 타일)도 매번
        // 클라에 스택이 재전송되고, 클라는 수신 스택마다 profile 을 재해석해 준비될 때까지 기본
        // 머리(스티브)를 그린다. 바닐라 /data merge 는 병합 결과가 같으면 'Nothing changed' 로
        // 무부작용이므로, 값 동등(areEqual: 아이템·수량·컴포넌트) 시 재세팅을 생략해 바닐라와
        // 관측을 일치시킨다(무변경 타일 재전송 0 — 패킷·클라 텍스처 재바인딩 제거).
        if (net.minecraft.item.ItemStack.areEqual(d.getItemStack(), ns)) return true;
        d.setItemStack(ns);
        return true;
    }

    private static boolean displaySetFast(net.minecraft.entity.Entity e, String path,
                                          net.minecraft.nbt.NbtElement v) {
        if (!(e instanceof net.minecraft.entity.decoration.DisplayEntity d)) return false;
        switch (path) {
            case "text": {
                // TextDisplayEntity.readCustomDataFromNbt 의 text 적용부만 재현(바이트코드 확인):
                //   TextCodecs.CODEC.parse(registryOps(NbtOps), el) → getCommandSource(sw).withLevel(2)
                //   → Texts.parse(src, t, this, 0) → setText(resolved)
                // 나머지 필드 재적용은 동일 값의 DataTracker set(no-op)이라 관측 동등.
                // 파싱/해석 실패는 false 반환 → 원 roundtrip 폴백(바닐라의 warn+empty 동작 재현).
                if (!(e instanceof net.minecraft.entity.decoration.DisplayEntity.TextDisplayEntity td)) return false;
                if (!(e.getWorld() instanceof net.minecraft.server.world.ServerWorld sw)) return false;
                try {
                    net.minecraft.text.Text t = net.minecraft.text.TextCodecs.CODEC
                            .parse(td.getRegistryManager().getOps(net.minecraft.nbt.NbtOps.INSTANCE), v)
                            .getOrThrow();
                    net.minecraft.server.command.ServerCommandSource src =
                            td.getCommandSource(sw).withLevel(2);
                    td.setText(net.minecraft.text.Texts.parse(src, t, td, 0));
                    return true;
                } catch (Exception ex) {
                    return false;   // 폴백: 원 경로가 바닐라와 동일하게 warn + empty 처리
                }
            }
            case "transformation": {
                net.minecraft.util.math.AffineTransformation at = transformCached(v);
                if (at == null) return false;
                d.setTransformation(at);
                // 바닐라 /data modify 는 readNbt 를 거쳐 setStartInterpolation(force=true)을
                // 호출하므로 보간이 매번 재트리거된다. setTransformation 만으로는
                // START_INTERPOLATION 트래커가 갱신되지 않아(값 동일 시 dirty 스킵) 클라가
                // 보간을 시작하지 않는다. 현재 start_interpolation 값으로 강제 재설정해 트리거.
                d.setStartInterpolation(d.getStartInterpolation());
                return true;
            }
            case "interpolation_duration": d.setInterpolationDuration((int) nbtNum(v)); return true;
            case "start_interpolation":    d.setStartInterpolation((int) nbtNum(v)); return true;
            case "teleport_duration":      d.setTeleportDuration((int) nbtNum(v)); return true;
            case "brightness": {
                // {sky:N,block:M} → setBrightness 직접 호출(DataTracker 갱신+클라 동기화).
                // 일반 writeNbt/readNbt 폴백은 DisplayEntity brightness 트래커를 갱신하지 못해
                // 시각적으로 반영되지 않는다(라이트가 안 켜지거나 안 꺼지는 원인).
                if (v instanceof net.minecraft.nbt.NbtCompound bc) {
                    int sky = bc.contains("sky") ? (int) nbtNum(bc.get("sky")) : 0;
                    int block = bc.contains("block") ? (int) nbtNum(bc.get("block")) : 0;
                    d.setBrightness(new net.minecraft.entity.decoration.Brightness(block, sky));
                    return true;
                }
                return false;
            }
        }
        if (path.startsWith("transformation.") || path.startsWith("transformation[")) {
            // 부분 경로: 현재 transformation 만 인코딩 → 경로 수정 → 디코딩 (엔티티 readNbt 없음)
            java.util.Optional<net.minecraft.nbt.NbtElement> enc =
                    net.minecraft.util.math.AffineTransformation.ANY_CODEC
                            .encodeStart(net.minecraft.nbt.NbtOps.INSTANCE,
                                    net.minecraft.entity.decoration.DisplayEntity
                                            .getTransformation(d.getDataTracker()))
                            .result();
            if (enc.isEmpty()) return false;
            net.minecraft.nbt.NbtCompound root = new net.minecraft.nbt.NbtCompound();
            root.put("transformation", enc.get());
            if (!putAtPath(root, path, v)) return false;
            java.util.Optional<net.minecraft.util.math.AffineTransformation> dec =
                    net.minecraft.util.math.AffineTransformation.ANY_CODEC
                            .parse(net.minecraft.nbt.NbtOps.INSTANCE, root.get("transformation")).result();
            if (dec.isEmpty()) return false;
            d.setTransformation(dec.get());
            return true;
        }
        // item_display 표시 아이템: 전체 엔티티 writeNbt/readNbt 없이 아이템 스택만 codec round-trip.
        //   item            → set value(아이템 NBT 그대로 파싱·교체)
        //   item.<sub>/item[..] → 현재 아이템 인코딩 후 부분 경로 수정(엔티티 직렬화 회피)
        if (d instanceof net.minecraft.entity.decoration.DisplayEntity.ItemDisplayEntity _id) {
            if (path.equals("item")) {
                if (v instanceof net.minecraft.nbt.NbtCompound _ic) return applyItemDisplayNbt(_id, _ic, false);
                return false;
            }
            if (path.startsWith("item.") || path.startsWith("item[")) {
                net.minecraft.registry.RegistryWrapper.WrapperLookup _lk = _id.getRegistryManager();
                net.minecraft.nbt.NbtCompound _root = new net.minecraft.nbt.NbtCompound();
                net.minecraft.item.ItemStack _cur = _id.getItemStack();
                if (!_cur.isEmpty()) {
                    net.minecraft.nbt.NbtElement _enc = _cur.toNbt(_lk);
                    if (_enc instanceof net.minecraft.nbt.NbtCompound _ec) _root.put("item", _ec);
                }
                if (!putAtPath(_root, path, v)) return false;
                net.minecraft.nbt.NbtElement _m = _root.get("item");
                net.minecraft.item.ItemStack _ns = (_m instanceof net.minecraft.nbt.NbtCompound _mc)
                        ? net.minecraft.item.ItemStack.fromNbt(_lk, _mc).orElse(net.minecraft.item.ItemStack.EMPTY)
                        : net.minecraft.item.ItemStack.EMPTY;
                // [헤드 플래시 수정] 동등 스택 재세팅 생략 — applyItemDisplayNbt 의 게이트와 동일 근거.
                if (net.minecraft.item.ItemStack.areEqual(_id.getItemStack(), _ns)) return true;
                _id.setItemStack(_ns);
                return true;
            }
        }
        return false;
    }

    /** display 엔티티의 transformation(부분 경로 포함) 읽기 — 행렬 1개만 인코딩(엔티티 writeNbt 없음). */
    private static net.minecraft.nbt.NbtElement displayGetFast(net.minecraft.entity.Entity e, String path) {
        if (!(e instanceof net.minecraft.entity.decoration.DisplayEntity d)) return null;
        if (!path.equals("transformation") && !path.startsWith("transformation.")
                && !path.startsWith("transformation[")) return null;
        java.util.Optional<net.minecraft.nbt.NbtElement> enc =
                net.minecraft.util.math.AffineTransformation.ANY_CODEC
                        .encodeStart(net.minecraft.nbt.NbtOps.INSTANCE,
                                net.minecraft.entity.decoration.DisplayEntity
                                        .getTransformation(d.getDataTracker()))
                        .result();
        if (enc.isEmpty()) return null;
        if (path.equals("transformation")) return enc.get();
        net.minecraft.nbt.NbtCompound root = new net.minecraft.nbt.NbtCompound();
        root.put("transformation", enc.get());
        return getAtPath(root, path);
    }

    public static void nbtSetEntity(net.minecraft.entity.Entity e, String path, net.minecraft.nbt.NbtElement v) {
        if (e == null || v == null) return;
        String pt = path.replace(" ", "");
        if (displaySetFast(e, pt, v)) { invalidateSnapshot(e); return; }
        entityNbtRoundtrip(e, path, v);   // droppable(구조적 부재) 경로면 writeNbt·invalidate 생략
    }

    public static void nbtSetStorage(net.minecraft.server.MinecraftServer server, String id, String path,
                                     net.minecraft.nbt.NbtElement v) {
        if (v == null) return;
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        if (root == null) root = new net.minecraft.nbt.NbtCompound();
        if (putAtPath(root, path, v)) storageSave(server, id, root);
    }

    /** store result|success ... run data modify storage <id> <path> set from ... 의 값.
     *  바닐라 DataCommand.executeModify: i = NbtPath.put 이 실제 바꾼 종단 수(기존 값과 동일하면 0),
     *  i==0 → 'Nothing changed' 실패(store→0), 성공 시 command result = i.
     *  '소스 존재=1' 로 하면, 발판(점프대)의 표지판 줄 감지("빈 문자열 리셋 후 복사 — 변경 없으면
     *  빈 줄" 트릭)가 항상 존재하는 sign messages[i] 때문에 오판되어 바닐라와 달라진다. */
    public static int nbtSetStorageChanged(net.minecraft.server.MinecraftServer server, String id, String path,
                                           net.minecraft.nbt.NbtElement v) {
        if (v == null) return 0;
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        if (root == null) root = new net.minecraft.nbt.NbtCompound();
        int n = putAtPathCount(root, path, v);
        if (n > 0) storageSave(server, id, root);
        return n;
    }

    /** data modify storage <id> <path> set string <src> [<start> [<end>]] — 부분 문자열 복사.
     *  바닐라(바이트코드 확인): asString = NbtString.value | NbtPrimitive.toString(그 외 실패),
     *  인덱스 = i<0 ? len+i : i (getSubstringIndex), start<0|end>len|start>end 는 실패(쓰기 없음),
     *  결과 NbtString 을 put(값 불변이면 Nothing-changed — 저장 생략). end 센티널 MIN_VALUE = 끝까지.
     *  반환: store result 용 '변경 종단 수' — 실패(비문자열/범위 밖)나 값 불변이면 0
     *  (바닐라: 실패 시 예외 → store 0, Nothing-changed 도 예외 → 0 — put count 0 과 동일). */
    public static int nbtSetStorageString(net.minecraft.server.MinecraftServer server, String id, String path,
                                          net.minecraft.nbt.NbtElement src, int start, int end) {
        String s;
        if (src instanceof net.minecraft.nbt.NbtString ns) s = ns.value();
        else if (src instanceof net.minecraft.nbt.NbtPrimitive) s = src.toString();
        else return 0;
        int len = s.length();
        int a = start < 0 ? len + start : start;
        int b = (end == Integer.MIN_VALUE) ? len : (end < 0 ? len + end : end);
        if (a < 0 || b > len || a > b) return 0;
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        if (root == null) root = new net.minecraft.nbt.NbtCompound();
        int n = putAtPathCount(root, path, net.minecraft.nbt.NbtString.of(s.substring(a, b)));
        if (n > 0) storageSave(server, id, root);
        return n;
    }

    /** data modify ... append|prepend from ... : 경로의 리스트에 element 를 추가(없으면 리스트 생성).
     *  바닐라 DataCommand 의 getOrInit(NbtList::new) + add 와 동일. */
    private static boolean appendAtPath(net.minecraft.nbt.NbtElement root, String path,
                                        net.minecraft.nbt.NbtElement elem, boolean prepend) {
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);
        if (p == null) return false;
        try {
            boolean changed = false;
            for (net.minecraft.nbt.NbtElement el : p.getOrInit(root, net.minecraft.nbt.NbtList::new)) {
                if (el instanceof net.minecraft.nbt.NbtList list) {
                    list.add(prepend ? 0 : list.size(), elem.copy());
                    changed = true;
                }
            }
            return changed;
        } catch (Exception e) { return false; }
    }

    public static void nbtAppendStorage(net.minecraft.server.MinecraftServer server, String id, String path,
                                        net.minecraft.nbt.NbtElement elem, boolean prepend) {
        if (elem == null) return;
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        if (root == null) root = new net.minecraft.nbt.NbtCompound();
        if (appendAtPath(root, path, elem, prepend)) storageSave(server, id, root);
    }

    /** data modify <path> merge value <compound> : 경로의 컴파운드(없으면 생성)에 깊은 병합.
     *  바닐라 MERGE 연산: path.getOrInit(NbtCompound::new) 후 각 대상에 copyFrom(재귀 병합). */
    private static boolean mergeAtPath(net.minecraft.nbt.NbtElement root, String path,
                                       net.minecraft.nbt.NbtElement val) {
        if (!(val instanceof net.minecraft.nbt.NbtCompound vc)) return false;
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);
        if (p == null) return false;
        try {
            boolean changed = false;
            for (net.minecraft.nbt.NbtElement el : p.getOrInit(root, net.minecraft.nbt.NbtCompound::new)) {
                if (el instanceof net.minecraft.nbt.NbtCompound cc) {
                    cc.copyFrom((net.minecraft.nbt.NbtCompound) vc.copy());
                    changed = true;
                }
            }
            return changed;
        } catch (Exception e) { return false; }
    }

    public static void nbtAppendEntity(net.minecraft.entity.Entity e, String path,
                                       net.minecraft.nbt.NbtElement elem, boolean prepend) {
        if (e == null || elem == null) return;
        if (nbtPathDroppable(e, path.replace(" ", ""))) return;   // 구조적 부재 최상위 키 → readNbt 가 버림(no-op)
        net.minecraft.nbt.NbtCompound root = writeSourceCompound(e);     // W1(정정판): 휘발 필드 라이브 동등화
        if (appendAtPath(root, path, elem, prepend)) {
            invalidateSnapshot(e);
            readNbtTagAware(e, root);
            reseedOrInvalidate(e, path.replace(" ", ""), root);
        }
    }

    private static net.minecraft.nbt.NbtElement numberNbt(String type, double v) {
        switch (type) {
            case "byte":   return net.minecraft.nbt.NbtByte.of((byte) v);
            case "short":  return net.minecraft.nbt.NbtShort.of((short) v);
            case "long":   return net.minecraft.nbt.NbtLong.of((long) v);
            case "float":  return net.minecraft.nbt.NbtFloat.of((float) v);
            case "double": return net.minecraft.nbt.NbtDouble.of(v);
            default:        return net.minecraft.nbt.NbtInt.of((int) v);
        }
    }

    /** store result entity <sel> <path> <type> <scale> — 타입 숫자를 임의 NBT 경로에 기록. */
    public static void entityPutNumberPath(net.minecraft.entity.Entity e, String path, String type, double value) {
        if (e == null) return;
        // 회전(Rotation[0]=yaw, Rotation[1]=pitch)은 NBT 라운드트립 대신 직접 적용 —
        // 디스플레이 엔티티 등에서 writeNbt/readNbt 로는 라이브 회전이 확실히 갱신되지 않을 수 있다.
        if (path.equals("Rotation[0]")) {
            invalidateSnapshot(e);
            float y = (float) value;
            e.setYaw(y); e.setHeadYaw(y); e.setBodyYaw(y);
            return;
        }
        if (path.equals("Rotation[1]")) {
            invalidateSnapshot(e);
            e.setPitch((float) value);
            return;
        }
        net.minecraft.nbt.NbtElement nv = numberNbt(type, value);
        if (displaySetFast(e, path.replace(" ", ""), nv)) { invalidateSnapshot(e); return; }
        // 구조적 부재 경로(예: item_display 의 data.interpolation_duration, data.loop-data.*)는
        // readNbt 가 통째로 버리는 no-op — entityNbtRoundtrip 이 (클래스,키)당 1회 자가검증 후 writeNbt 생략.
        entityNbtRoundtrip(e, path, nv);
    }

    /** 점 표기 경로를 따라 마지막 직전 컴파운드까지 내려가며, 없으면 생성. 반환: (부모, 마지막키). */
    private static Object[] descend(net.minecraft.nbt.NbtCompound root, String path, boolean create) {
        String[] parts = path.split("\\.");
        net.minecraft.nbt.NbtCompound cur = root;
        for (int i = 0; i < parts.length - 1; i++) {
            net.minecraft.nbt.NbtCompound next = cur.getCompound(parts[i]).orElse(null);
            if (next == null) {
                if (!create) return null;
                next = new net.minecraft.nbt.NbtCompound();
                cur.put(parts[i], (net.minecraft.nbt.NbtElement) next);
            }
            cur = next;
        }
        return new Object[]{cur, parts[parts.length - 1]};
    }

    public static int storageGetNumber(net.minecraft.server.MinecraftServer server, String id, String path) {
        return (int) nbtNum(getAtPath(storageRoot(server, id), path));  // NbtPath: 인덱스/필터 경로 정확
    }

    /** NbtElement → double(숫자형 아니면 0). */
    private static double nbtNum(net.minecraft.nbt.NbtElement el) {
        if (el instanceof net.minecraft.nbt.AbstractNbtNumber n) return n.doubleValue();
        // 바닐라 data get <path> <scale>: 비숫자는 '크기'에 scale 적용(MathHelper.floor).
        //   리스트/배열(CollectionTag)=원소 수, 컴파운드=키 수, 문자열=길이. (DataCommand.getData)
        if (el instanceof net.minecraft.nbt.AbstractNbtList l) return l.size();
        if (el instanceof net.minecraft.nbt.NbtCompound c) return c.getSize();
        if (el instanceof net.minecraft.nbt.NbtString s) return s.value().length();
        return 0.0;
    }
    /** data get <entity|storage> <path> 의 값(double). getAtPath 라 리스트 인덱스(Rotation[0]) 지원. */
    public static double entityGetDouble(net.minecraft.entity.Entity e, String path) {
        if (e == null) return 0.0;
        // Rotation[0]/[1] 은 writeNbt 라운드트립 대신 라이브 yaw/pitch 를 직접 읽는다.
        // (display 엔티티 등에서 writeNbt 의 Rotation 이 라이브 회전과 어긋날 수 있어,
        //  바닐라 data get entity @s Rotation[n] 과 동일한 값을 보장하기 위함)
        String pt = path.replace(" ", "");
        if (pt.equals("Rotation[0]")) return e.getYaw();
        if (pt.equals("Rotation[1]")) return e.getPitch();
        // Pos/Motion 도 풀 writeNbt(엔티티 NBT 전체 직렬화) 없이 직접 읽기 — data get 핫패스.
        if (pt.equals("Pos[0]")) return e.getPos().x;
        if (pt.equals("Pos[1]")) return e.getPos().y;
        if (pt.equals("Pos[2]")) return e.getPos().z;
        if (pt.equals("Motion[0]")) return e.getVelocity().x;
        if (pt.equals("Motion[1]")) return e.getVelocity().y;
        if (pt.equals("Motion[2]")) return e.getVelocity().z;
        // 라이브 게터/디스플레이/슬롯(equipment custom_data) fast-path — 전체 writeNbt 회피.
        //   nbtGetEntity(정규 리더)와 동일 의미. kart 물리 custom_data 읽기가 여기서 직접 처리됨.
        net.minecraft.nbt.NbtElement fast = entityNbtFast(e, pt);
        if (fast != null) return nbtNum(fast);
        if (nbtPathDroppable(e, pt)) return 0.0;     // 구조적 부재(no-op) 경로 → 스냅샷 writeNbt 회피
        net.minecraft.nbt.NbtElement r = getAtPath(entitySnapshot(e), path);   // 캐시된 스냅샷 — data get 반복 시 writeNbt 회피
        return nbtNum(r);
    }
    public static double storageGetDouble(net.minecraft.server.MinecraftServer server, String id, String path) {
        return nbtNum(getAtPath(storageRoot(server, id), path));
    }

    /** /data get <path> <scale> 의 정수 결과 = floor(value*scale).
     *  바닐라는 MathHelper.floor(내림)을 쓴다(attribute get 의 0방향 절삭과 다름, MC-279197).
     *  공식은 위키 명시: n = value*scale; out = n < (int)n ? (int)n-1 : (int)n  (= 음수 내림). */
    public static int floorScale(double value, double scale) {
        double n = value * scale;
        return n < (int) n ? (int) n - 1 : (int) n;
    }

    public static void storagePutNumber(net.minecraft.server.MinecraftServer server, String id,
                                        String path, double value, String type) {
        snapBarrier(id);   // 23차: 이 storage 의 미실체화 바인드 스냅샷을 변이 전에 복사
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        if (root == null) root = new net.minecraft.nbt.NbtCompound();
        if (putAtPath(root, path, numberNbt(type, value))) storageSave(server, id, root);  // NbtPath
    }

    /** 임의 SNBT 값을 경로에 기록. mode: set | append | prepend | merge.
     *  값 파싱은 `{v:<snbt>}` 로 감싸 readCompound — 리스트/문자열/컴파운드/숫자 전부 합법. */
    public static void storagePutSnbt(net.minecraft.server.MinecraftServer server, String id,
                                      String path, String snbt, String mode) {
        snapBarrier(id);   // 23차: 이 storage 의 미실체화 바인드 스냅샷을 변이 전에 복사
        try {
            // SNBT 리터럴은 변환 시점 상수 — 매 호출 파싱 대신 캐시된 템플릿을 copy.
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try {
                    net.minecraft.nbt.NbtCompound w =
                            net.minecraft.nbt.StringNbtReader.readCompound("{v:" + s + "}");
                    net.minecraft.nbt.NbtElement v = w.get("v");
                    return v == null ? SNBT_INVALID : v;
                } catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID) return;
            net.minecraft.nbt.NbtElement val = tmpl.copy();
            net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
            if (root == null) root = new net.minecraft.nbt.NbtCompound();
            // 바닐라 NbtPath(인덱스 round[10]/필터 foo[{id:1}] 정확 처리) 사용 — descend(단순 . split)는
            // 인덱스를 키 이름으로 오해해 NBT 구조가 바닐라와 달라졌다(storage 경로의 86%가 인덱스 포함).
            boolean changed;
            switch (mode) {
                case "append"  -> changed = appendAtPath(root, path, val, false);
                case "prepend" -> changed = appendAtPath(root, path, val, true);
                case "merge"   -> changed = mergeAtPath(root, path, val);
                default        -> changed = putAtPath(root, path, val);   // set
            }
            if (changed) storageSave(server, id, root);
        } catch (Exception ignored) {}
    }

    /** data modify storage <id> <path> insert <index> value <snbt> — 리스트 index 앞 삽입.
     *  범위 밖(index > size)은 바닐라와 동일하게 실패(no-op). 음수 인덱스는 emit 단계에서 폴백. */
    public static void storageInsertSnbt(net.minecraft.server.MinecraftServer server, String id,
                                         String path, int index, String snbt) {
        snapBarrier(id);   // 23차: 이 storage 의 미실체화 바인드 스냅샷을 변이 전에 복사
        try {
            net.minecraft.nbt.NbtCompound w0 =
                    net.minecraft.nbt.StringNbtReader.readCompound("{v:" + snbt + "}");
            net.minecraft.nbt.NbtElement val = w0.get("v");
            if (val == null) return;
            net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
            if (root == null) return;
            net.minecraft.command.argument.NbtPathArgumentType.NbtPath np = nbtPath(path);
            if (np == null) return;
            boolean changed = false;
            for (net.minecraft.nbt.NbtElement el : np.get(root)) {
                if (el instanceof net.minecraft.nbt.AbstractNbtList lst
                        && index >= 0 && index <= lst.size()
                        && lst.addElement(index, val.copy())) {
                    changed = true;
                }
            }
            if (changed) storageSave(server, id, root);
        } catch (Exception ignored) {}
    }

    /** storagePutSnbt 와 동일한 쓰기지만 "값이 실제로 바뀌었는지"를 반환한다(store success 용).
     *  set 모드는 putAtPathCount(=NbtPath.put 변경 개수)>0 으로 판정 — putAtPath(경로 성공 여부)와
     *  달리, 이미 같은 값을 다시 set 하면 false(바닐라 store success 0)를 정확히 재현한다.
     *  append/prepend/merge 는 기존 changed 시맨틱과 동일. */
    public static boolean storagePutSnbtChanged(net.minecraft.server.MinecraftServer server, String id,
                                                String path, String snbt, String mode) {
        snapBarrier(id);   // 23차: 이 storage 의 미실체화 바인드 스냅샷을 변이 전에 복사
        try {
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try {
                    net.minecraft.nbt.NbtCompound w =
                            net.minecraft.nbt.StringNbtReader.readCompound("{v:" + s + "}");
                    net.minecraft.nbt.NbtElement v = w.get("v");
                    return v == null ? SNBT_INVALID : v;
                } catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID) return false;
            net.minecraft.nbt.NbtElement val = tmpl.copy();
            net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
            if (root == null) root = new net.minecraft.nbt.NbtCompound();
            boolean changed;
            switch (mode) {
                case "append"  -> changed = appendAtPath(root, path, val, false);
                case "prepend" -> changed = appendAtPath(root, path, val, true);
                case "merge"   -> changed = mergeAtPath(root, path, val);
                default        -> changed = putAtPathCount(root, path, val) > 0;   // set
            }
            if (changed) storageSave(server, id, root);
            return changed;
        } catch (Exception ignored) { return false; }
    }

    /** data merge storage <id> {snbt} — 컴파운드를 스토리지 루트에 깊은 병합. */
    /** data 블록 대상 접근 — 바닐라 BlockDataObject(getNbt/setNbt: markDirty+동기화 포함) 그대로 사용. */
    private static net.minecraft.command.BlockDataObject blockData(
            net.minecraft.server.world.ServerWorld w, net.minecraft.util.math.BlockPos pos) {
        net.minecraft.block.entity.BlockEntity be = (w == null ? null : w.getBlockEntity(pos));
        return be == null ? null : new net.minecraft.command.BlockDataObject(be, pos);
    }

    /** data modify block <pos> <path> set|append|prepend|merge value <snbt>. 블록엔티티 없으면 no-op(바닐라 실패). */
    public static void blockPutSnbt(net.minecraft.server.world.ServerWorld w,
                                    net.minecraft.util.math.BlockPos pos,
                                    String path, String snbt, String mode) {
        try {
            net.minecraft.command.BlockDataObject bd = blockData(w, pos);
            if (bd == null) return;
            net.minecraft.nbt.NbtCompound w0 =
                    net.minecraft.nbt.StringNbtReader.readCompound("{v:" + snbt + "}");
            net.minecraft.nbt.NbtElement val = w0.get("v");
            if (val == null) return;
            net.minecraft.nbt.NbtCompound root = bd.getNbt();
            boolean changed;
            switch (mode) {
                case "append"  -> changed = appendAtPath(root, path, val, false);
                case "prepend" -> changed = appendAtPath(root, path, val, true);
                case "merge"   -> changed = mergeAtPath(root, path, val);
                default        -> changed = putAtPath(root, path, val);   // set
            }
            if (changed) bd.setNbt(root);
        } catch (Exception ignored) {}
    }

    /** data modify block <pos> <path> set from … — 읽어온 NbtElement 를 블록 경로에 기록. */
    public static void blockSetElement(net.minecraft.server.world.ServerWorld w,
                                       net.minecraft.util.math.BlockPos pos,
                                       String path, net.minecraft.nbt.NbtElement v) {
        try {
            net.minecraft.command.BlockDataObject bd = blockData(w, pos);
            if (bd == null || v == null) return;
            net.minecraft.nbt.NbtCompound root = bd.getNbt();
            if (putAtPath(root, path, v.copy())) bd.setNbt(root);
        } catch (Exception ignored) {}
    }

    /** execute store result|success block <pos> <path> <type> <scale>. */
    public static void blockStoreNumber(net.minecraft.server.world.ServerWorld w,
                                        net.minecraft.util.math.BlockPos pos,
                                        String path, double value, String type) {
        try {
            net.minecraft.command.BlockDataObject bd = blockData(w, pos);
            if (bd == null) return;
            net.minecraft.nbt.NbtCompound root = bd.getNbt();
            if (putAtPath(root, path, numberNbt(type, value))) bd.setNbt(root);
        } catch (Exception ignored) {}
    }

    /** data merge storage 의 store success 판정판 — 바닐라 executeMerge 와 동일하게
     *  '병합 결과가 원본과 같으면 실패(0)'. */
    public static boolean storageMergeSnbtChanged(net.minecraft.server.MinecraftServer server,
                                                  String id, String snbt) {
        snapBarrier(id);   // 23차: 이 storage 의 미실체화 바인드 스냅샷을 변이 전에 복사
        try {
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try { return net.minecraft.nbt.StringNbtReader.readCompound(s); }
                catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID || !(tmpl instanceof net.minecraft.nbt.NbtCompound tc)) return false;
            net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
            net.minecraft.nbt.NbtCompound before = root.copy();
            root.copyFrom(tc.copy());
            boolean changed = !root.equals(before);
            if (changed) storageSave(server, id, root);
            return changed;
        } catch (Exception ignored) { return false; }
    }

    /** execute if blocks <start> <end> <dest> all|masked — ExecuteCommand testBlocksCondition 동일:
     *  상태 동일 + (블록엔티티 있으면) NBT 동일. masked 는 소스가 공기인 칸 건너뜀.
     *  바닐라 한계(32768블록) 초과는 커맨드 실패 = 조건 거짓과 동일 관측. */
    public static boolean blocksMatch(net.minecraft.server.world.ServerWorld w,
                                      int x1, int y1, int z1, int x2, int y2, int z2,
                                      int dx, int dy, int dz, boolean masked) {
        int sx = Math.min(x1, x2), ex = Math.max(x1, x2);
        int sy = Math.min(y1, y2), ey = Math.max(y1, y2);
        int sz = Math.min(z1, z2), ez = Math.max(z1, z2);
        long vol = (long)(ex-sx+1) * (ey-sy+1) * (ez-sz+1);
        if (vol > 32768L) return false;          // 바닐라 TOOBIG = 실행 실패
        int ox = dx - sx, oy = dy - sy, oz = dz - sz;
        net.minecraft.util.math.BlockPos.Mutable a = new net.minecraft.util.math.BlockPos.Mutable();
        net.minecraft.util.math.BlockPos.Mutable b = new net.minecraft.util.math.BlockPos.Mutable();
        for (int x = sx; x <= ex; x++) for (int y = sy; y <= ey; y++) for (int z = sz; z <= ez; z++) {
            a.set(x, y, z);
            net.minecraft.block.BlockState sa = w.getBlockState(a);
            if (masked && sa.isAir()) continue;
            b.set(x + ox, y + oy, z + oz);
            if (sa != w.getBlockState(b)) return false;
            net.minecraft.block.entity.BlockEntity ba = w.getBlockEntity(a);
            if (ba != null) {
                net.minecraft.block.entity.BlockEntity bb = w.getBlockEntity(b);
                if (bb == null) return false;
                net.minecraft.nbt.NbtCompound na = new net.minecraft.command.BlockDataObject(ba, a.toImmutable()).getNbt();
                net.minecraft.nbt.NbtCompound nb = new net.minecraft.command.BlockDataObject(bb, b.toImmutable()).getNbt();
                if (!na.equals(nb)) return false;
            }
        }
        return true;
    }

    public static void storageMergeSnbt(net.minecraft.server.MinecraftServer server, String id, String snbt) {
        snapBarrier(id);   // 23차: 이 storage 의 미실체화 바인드 스냅샷을 변이 전에 복사
        try {
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try { return net.minecraft.nbt.StringNbtReader.readCompound(s); }
                catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID || !(tmpl instanceof net.minecraft.nbt.NbtCompound)) return;
            net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
            root.copyFrom(((net.minecraft.nbt.NbtCompound) tmpl).copy());
            storageSave(server, id, root);
        } catch (Exception ignored) {}
    }

    public static void storageRemovePath(net.minecraft.server.MinecraftServer server, String id, String path) {
        snapBarrier(id);   // 23차: 이 storage 의 미실체화 바인드 스냅샷을 변이 전에 복사
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        if (root == null) return;
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);  // 인덱스/필터 경로 정확
        if (p == null) return;
        try { if (p.remove(root) > 0) storageSave(server, id, root); } catch (Exception ignored) {}
    }

    // ──────────────── entity NBT (data ... entity @s <path>) ────────────────
    // 엔티티 NBT 는 writeNbt 로 스냅샷 → 수정 → readNbt 로 반영. 비싸므로 gated.

    public static int entityGetNumber(net.minecraft.entity.Entity e, String path) {
        if (e == null) return 0;
        String pt = path.replace(" ", "");
        net.minecraft.nbt.NbtElement fast = entityNbtFast(e, pt);   // 라이브/디스플레이/슬롯 fast-path(writeNbt 회피)
        if (fast != null) return (int) nbtNum(fast);
        if (nbtPathDroppable(e, pt)) return 0;                      // 구조적 부재 no-op → 스냅샷 회피
        return (int) nbtNum(getAtPath(entitySnapshot(e), path));    // NbtPath: 인덱스/필터 경로 정확
    }

    /** null-safe 버전 — 셀렉터 대상이 없으면 0. */
    public static int entityGetNumberOf(net.minecraft.entity.Entity e, String path) {
        if (e == null) return 0;
        return entityGetNumber(e, path);
    }

    public static void entityPutNumber(net.minecraft.entity.Entity e, String path, double value) {
        if (e == null) return;
        entityNbtRoundtrip(e, path, net.minecraft.nbt.NbtInt.of((int) value));   // droppable no-op 자동 생략, NbtPath
    }

    /** data modify entity <e> <path> set value <snbt> — 임의 SNBT 값 기록. */
    /** data modify entity <e> <path> append|prepend value {snbt} — 리터럴 SNBT 를 리스트에 추가. */
    public static void entityAppendSnbt(net.minecraft.entity.Entity e, String path, String snbt, boolean prepend) {
        if (e == null) return;
        try {
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try {
                    net.minecraft.nbt.NbtCompound w =
                            net.minecraft.nbt.StringNbtReader.readCompound("{v:" + s + "}");
                    net.minecraft.nbt.NbtElement v = w.get("v");
                    return v == null ? SNBT_INVALID : v;
                } catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID) return;
            nbtAppendEntity(e, path, tmpl.copy(), prepend);
        } catch (Exception ignored) {}
    }

    public static void entityPutSnbt(net.minecraft.entity.Entity e, String path, String snbt) {
        if (e == null) return;
        if (path != null && path.contains("CustomName")) NAME_GEN++;   // 이름 틱-캐시 무효화(10차)
        try {
            // SNBT 리터럴은 변환 시점 상수 — 매 호출 파싱 대신 캐시된 템플릿을 copy.
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try {
                    net.minecraft.nbt.NbtCompound w =
                            net.minecraft.nbt.StringNbtReader.readCompound("{v:" + s + "}");
                    net.minecraft.nbt.NbtElement v = w.get("v");
                    return v == null ? SNBT_INVALID : v;
                } catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID) return;
            // fast 경로(displaySetFast)는 val 을 읽기 전용으로만 쓴다 → 캐시 템플릿 직접 전달(copy 제거).
            if (displaySetFast(e, path.replace(" ", ""), tmpl)) { invalidateSnapshot(e); return; }
            // 느린 경로만 copy — putAtPath 가 val 을 nbt 에 참조 삽입할 수 있고, readNbt 가 그 하위를
            // 엔티티에 보관할 수 있어 캐시 원본 보호가 필요하다.
            net.minecraft.nbt.NbtElement val = tmpl.copy();
            // W1: 스냅샷 캐시 copy(웜 시 직렬화 0회) — invalidate 는 copy 이후(단일 스레드라 동등).
            net.minecraft.nbt.NbtCompound nbt = writeSourceCompound(e);
            // W2: 무변경이면 바닐라 'Nothing changed'(명령 실패=미적용) — readNbt 부수효과도 없음.
            if (putAtPathCount(nbt, path, val) > 0) {
                invalidateSnapshot(e);
                readNbtTagAware(e, nbt);
                reseedOrInvalidate(e, path.replace(" ", ""), nbt);
            }
        } catch (Exception ignored) {}
    }

    /** entityPutSnbt 와 동일하되 "값이 실제로 바뀌었는지"를 반환(store success 용).
     *  바닐라 /data modify entity 처럼 writeNbt→put→readNbt 라운드트립을 하되, put 반환(변경 개수)>0
     *  으로 성공을 판정한다. display fast-path 는 건너뛰고 전체 라운드트립을 쓴다(변경 개수 필요·희소 경로).
     *  vanilla store success 는 readNbt 가 나중에 경로를 버려도 put 개수 기준이므로 이 판정이 관측 동등. */
    public static boolean entityPutSnbtChanged(net.minecraft.entity.Entity e, String path, String snbt) {
        if (e == null) return false;
        if (path != null && path.contains("CustomName")) NAME_GEN++;   // 이름 틱-캐시 무효화(10차)
        try {
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try {
                    net.minecraft.nbt.NbtCompound w =
                            net.minecraft.nbt.StringNbtReader.readCompound("{v:" + s + "}");
                    net.minecraft.nbt.NbtElement v = w.get("v");
                    return v == null ? SNBT_INVALID : v;
                } catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID) return false;
            net.minecraft.nbt.NbtElement val = tmpl.copy();
            net.minecraft.nbt.NbtCompound nbt = writeSourceCompound(e);     // W1(정정판): 휘발 필드 라이브 동등화
            int cnt = putAtPathCount(nbt, path, val);
            if (cnt > 0) {
                invalidateSnapshot(e);
                readNbtTagAware(e, nbt);
                reseedOrInvalidate(e, path.replace(" ", ""), nbt);
            }
            return cnt > 0;
        } catch (Exception ignored) { return false; }
    }

    /** data merge entity <e> {snbt} — 컴파운드를 엔티티 NBT 에 깊은 병합(/data merge 시맨틱). */
    /** display fast 머지가 가능한 키들 — 전부 직접 setter 가 있는 DataTracker 필드. */
    // transformation NbtElement → 파싱·TRS분해된 AffineTransformation(불변). identity 1차 + 값기준 2차.
    // 파싱 실패 시 null(호출부는 fast-path 포기). 관측·캐시 동작은 종전 인라인 로직과 동일.
    private static net.minecraft.util.math.AffineTransformation transformCached(net.minecraft.nbt.NbtElement v) {
        net.minecraft.util.math.AffineTransformation at = TRANSFORM_ID_CACHE.get(v);   // identity: deep hashCode 회피
        if (at != null) return at;
        at = TRANSFORM_CACHE.get(v);                                                    // 값 기준 dedup(범용성)
        if (at == null) {
            java.util.Optional<net.minecraft.util.math.AffineTransformation> r =
                    net.minecraft.util.math.AffineTransformation.ANY_CODEC
                            .parse(net.minecraft.nbt.NbtOps.INSTANCE, v).result();
            if (r.isEmpty()) return null;
            at = r.get();
            if (TRANSFORM_CACHE.size() < 8192) TRANSFORM_CACHE.put(v.copy(), at);       // 동적값 소프트 캡
        }
        if (TRANSFORM_ID_CACHE.size() < 8192) TRANSFORM_ID_CACHE.put(v, at);
        return at;
    }

    private static boolean displayMergeFast(net.minecraft.entity.Entity e,
                                            net.minecraft.nbt.NbtCompound patch) {
        if (!(e instanceof net.minecraft.entity.decoration.DisplayEntity d)) return false;
        // fast 처리 가능한 키만 있는지 먼저 검증(혼재 시 전체 폴백).
        for (String k : patch.getKeys()) {
            switch (k) {
                case "transformation": case "interpolation_duration":
                case "start_interpolation": case "teleport_duration": break;
                case "item":
                    if (!(e instanceof net.minecraft.entity.decoration.DisplayEntity.ItemDisplayEntity)) return false;
                    break;
                default: return false;
            }
        }
        // ── 바닐라 /data merge 시맨틱을 '정확히' 미러한다 ──
        // /data merge = writeNbt(현재) → 패치 병합 → readNbt(병합본). readNbt 는 고정 순서로
        //   transformation → interpolation_duration → start_interpolation → teleport_duration
        // 를 '각 1회' 호출한다(DisplayEntity.readCustomDataFromNbt 바이트코드 확인). 특히
        // setStartInterpolation 은 force-dirty 라 매번 보간을 재트리거하는데, 병합본의
        // start_interpolation 값은 '패치에 있으면 그 값, 없으면 현재값'이다.
        //
        // 기존 구현은 patch.getKeys()(해시 순서=비결정) 로 displaySetFast 에 위임했고,
        // displaySetFast 의 transformation 케이스가 자체적으로 setStartInterpolation 을
        // 재트리거하기 때문에 patch 에 start_interpolation 이 함께 있으면 '한 틱에 2회'
        // 호출되고 그 순서가 프레임마다 달라져, 일부 프레임에서 보간 시작 스냅샷이 어긋나
        // 롤백성 튐이 발생했다. 여기서는 각 setter 를 고정 순서로 정확히 1회만 호출한다.
        if (patch.contains("transformation")) {
            net.minecraft.nbt.NbtElement tv = patch.get("transformation");
            net.minecraft.util.math.AffineTransformation at = transformCached(tv);
            if (at == null) return false;
            d.setTransformation(at);
        }
        if (patch.contains("interpolation_duration"))
            d.setInterpolationDuration((int) nbtNum(patch.get("interpolation_duration")));
        // start_interpolation: 바닐라 readNbt 는 getInt("start_interpolation", 0) 을 '무조건'
        // setStartInterpolation 에 넘긴다(바이트코드 확인). 그리고 writeCustomDataToNbt 는
        // start_interpolation 을 '쓰지 않으므로', /data merge 병합본에는 패치가 준 경우에만
        // 이 키가 존재한다 → 패치에 있으면 그 값, 없으면 default 0. setStartInterpolation 은
        // 유일하게 force-dirty(항상 패킷)이라 이 '무조건 1회 호출'이 곧 보간 재트리거이며,
        // 매 data merge 마다 일어나는 바닐라 동작이다. (setTransformation/Interpolation/Teleport
        //  Duration 은 equality-check set 이라 패치에 없으면 현재값=무변경이므로 생략해도 동일.)
        d.setStartInterpolation(patch.contains("start_interpolation")
                ? (int) nbtNum(patch.get("start_interpolation")) : 0);
        if (patch.contains("teleport_duration"))
            d.setTeleportDuration(net.minecraft.util.math.MathHelper.clamp(
                    (int) nbtNum(patch.get("teleport_duration")), 0, 59));   // 바닐라 clamp[0,59]
        if (patch.contains("item")) {
            net.minecraft.nbt.NbtElement _iv = patch.get("item");
            if (!(_iv instanceof net.minecraft.nbt.NbtCompound _ic)) return false;
            if (!applyItemDisplayNbt(
                    (net.minecraft.entity.decoration.DisplayEntity.ItemDisplayEntity) e, _ic, true)) return false;
        }
        return true;
    }

    /** UUID 리터럴 대상 해소 — 바닐라 EntitySelector(uuid) 와 동일하게 서버의 '모든 월드'에서
     *  getEntity(uuid) 를 찾는다(EntitySelector.getEntities 바이트코드 확인). 축약 UUID
     *  ("7437-0-a-0-0" 등)는 UUID.fromString 이 그룹별 16진 파싱으로 허용한다(바닐라 동일). */
    public static net.minecraft.entity.Entity entityByUuid(GameContext ctx, String uuid) {
        java.util.UUID id;
        try { id = java.util.UUID.fromString(uuid); }
        catch (IllegalArgumentException e) { return null; }
        for (net.minecraft.server.world.ServerWorld w : ctx.server.getWorlds()) {
            net.minecraft.entity.Entity e = w.getEntity(id);
            if (e != null) return e;
        }
        return null;
    }

    // ── DisplayEntity 병합 경로 (기본 slow = 바닐라 정확) ──
    // [확정된 결론] display 병합의 fast 경로(displayMergeFast)는 바닐라(writeNbt→copyFrom→
    // readNbt)와 관측이 어긋나 애니 프레임에 롤백성 튐을 만든다(인게임 A/B: slow 에선 사라짐).
    // 원인 분해 시도 결과(모두 배제됨):
    //   · 위치/속도/회전 재적용(resetPosition/refreshPositionAndAngles/setVelocity): 인게임 3종
    //     실험 모두 튐 지속 → 델타 아님.
    //   · DisplayEntity 나머지 8개 setter(billboard/brightness/width/height/glow/shadow×2/
    //     viewRange): 전부 equality-check(2-arg) set 이라 현재값 재호출 시 no-op → 델타 아님.
    //   · 패신저/vehicle 재확립: 대상은 단독 display(패신저 없음) → 델타 아님.
    // 남은 유일 차이는 writeNbt→copyFrom→readNbt '라운드트립 구조 자체'로, 구성요소를 fast 에
    // 개별 추가하는 방식으론 재현 불가(라운드트립을 하면 그것이 곧 slow). 따라서 display 병합은
    // slow(바닐라 정확)를 확정 채택한다. 이 병합들은 컷씬/애니 및 1회성 모델 셋업 빈도라
    // writeNbt/readNbt 비용이 문제되지 않으며(매틱 racing 핫패스엔 display 병합 없음),
    // 스코어보드/스토리지/비-display 병합은 fast 를 그대로 유지한다.
    // (연구/프로파일용으로만 -Dkfc.displaymerge=fast 로 튐 있는 fast 경로를 켤 수 있다.)
    private static final boolean DISPLAY_MERGE_SLOW =
            !"fast".equalsIgnoreCase(System.getProperty("kfc.displaymerge", "slow"));

    public static void entityMergeSnbt(net.minecraft.entity.Entity e, String snbt) {
        if (e == null) return;
        if (snbt != null && snbt.contains("CustomName")) NAME_GEN++;   // 이름 틱-캐시 무효화(10차)
        try {
            // SNBT 리터럴은 변환 시점 상수 — 캐시된 템플릿을 copy (storagePutSnbt 와 동일 패턴).
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try { return net.minecraft.nbt.StringNbtReader.readCompound(s); }
                catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID || !(tmpl instanceof net.minecraft.nbt.NbtCompound tc)) return;
            // (invalidate 는 각 분기에서 실제 변형 직전/직후에 수행 — W1 스냅샷 copy 가 먼저)
            // fast 경로(이 팩 머지의 ~99%)는 patch 를 읽기 전용으로만 쓴다:
            // displayMergeFast/displaySetFast 는 getKeys/get 으로 읽기만 하고, transformation 은
            // v.copy() 로 캐시 저장, 부분경로는 새 root 객체에만 기록, applyItemDisplayNbt 는
            // 새 itemNbt 에 copyFrom(patch) 한다(patch 미변경). 따라서 캐시 템플릿을 copy 없이
            // 직접 넘겨 매 호출 NbtCompound 깊은 복사(~1.87만회/틱)를 제거한다. 관측 동일.
            if (!DISPLAY_MERGE_SLOW && displayMergeFast(e, tc)) return;
            // 느린 경로(non-display/혼합키, 드묾)만 방어적 copy — writeNbt/readNbt 가 nbt 하위를
            // 엔티티에 참조 보관할 수 있어 캐시 원본 보호가 필요하다.
            net.minecraft.nbt.NbtCompound patch = tc.copy();
            net.minecraft.nbt.NbtCompound before = writeSourceCompound(e);   // W1(정정판): fresh 동등 기준
            net.minecraft.nbt.NbtCompound nbt = before.copy();
            nbt.copyFrom(patch);
            // W2: 바닐라 /data merge 는 병합 결과가 원본과 같으면 'Nothing changed' 로 미적용.
            if (!nbt.equals(before)) {
                invalidateSnapshot(e);
                readNbtTagAware(e, nbt);
                boolean dataOnly = true;
                for (String k : patch.getKeys()) if (!"data".equals(k)) { dataOnly = false; break; }
                if (dataOnly) reseedOrInvalidate(e, "data", nbt);
            }
        } catch (Exception ignored) {}
    }

    // ════════════════════════════════════════════════════════════════
    //  [static-final 승격용] SNBT 리터럴 사전 파싱 + pre-parsed 오버로드
    //  merge_pass pass-4 가 변환 시점 상수 SNBT 를 클래스 static final 필드로 승격하고
    //  호출부를 아래 오버로드로 재작성한다 → 호출당 SNBT_CACHE 조회/문자열 해시/파싱 제거.
    //  (파싱은 StringNbtReader — 레지스트리 불요 → 클래스 로드 시 안전. 실패 시 null = 무동작.)
    // ════════════════════════════════════════════════════════════════
    /** SNBT 리터럴 → 불변 NbtCompound 템플릿(entityMergeSnbt 용). 파싱 실패/비compound 면 null. */
    public static net.minecraft.nbt.NbtCompound snbtCompound(String snbt) {
        try {
            net.minecraft.nbt.NbtElement t = net.minecraft.nbt.StringNbtReader.readCompound(snbt);
            return (t instanceof net.minecraft.nbt.NbtCompound c) ? c : null;
        } catch (Exception ex) { return null; }
    }

    /** SNBT 값 리터럴 → 불변 NbtElement(storagePutSnbt 용, {v:...} 래핑 파싱). 실패 시 null. */
    public static net.minecraft.nbt.NbtElement snbtValue(String snbt) {
        try {
            net.minecraft.nbt.NbtCompound w =
                    net.minecraft.nbt.StringNbtReader.readCompound("{v:" + snbt + "}");
            return w.get("v");
        } catch (Exception ex) { return null; }
    }

    /** pre-parsed 오버로드: 승격된 static final 템플릿을 받아 실행(String 판 파싱 경로만 생략,
     *  invalidateSnapshot/fast·slow 경로는 String 판과 완전 동일). tc==null(원본 SNBT_INVALID
     *  대응)이면 invalidateSnapshot 후 무동작 — String 판과 동일 순서. */
    public static void entityMergeSnbt(net.minecraft.entity.Entity e, net.minecraft.nbt.NbtCompound tc) {
        if (e == null || tc == null) return;
        if (tc.contains("CustomName")) NAME_GEN++;   // 이름 틱-캐시 무효화(10차)
        try {
            if (!DISPLAY_MERGE_SLOW && displayMergeFast(e, tc)) { invalidateSnapshot(e); return; }
            net.minecraft.nbt.NbtCompound patch = tc.copy();
            net.minecraft.nbt.NbtCompound before = writeSourceCompound(e);   // W1(정정판): fresh 동등 기준
            net.minecraft.nbt.NbtCompound nbt = before.copy();
            nbt.copyFrom(patch);
            // W2: 바닐라 /data merge 는 병합 결과가 원본과 같으면 'Nothing changed' 로 미적용.
            if (!nbt.equals(before)) {
                invalidateSnapshot(e);
                readNbtTagAware(e, nbt);
                boolean dataOnly = true;
                for (String k : patch.getKeys()) if (!"data".equals(k)) { dataOnly = false; break; }
                if (dataOnly) reseedOrInvalidate(e, "data", nbt);
            }
        } catch (Exception ignored) {}
    }

    /** pre-parsed 오버로드: 승격된 static final 값 템플릿을 받아 실행(String 판과 동일,
     *  파싱/조회만 생략). tmpl==null(원본 SNBT_INVALID 대응)이면 무동작 — String 판과 동일. */
    public static void storagePutSnbt(net.minecraft.server.MinecraftServer server, String id,
                                      String path, net.minecraft.nbt.NbtElement tmpl, String mode) {
        snapBarrier(id);   // 23차: 이 storage 의 미실체화 바인드 스냅샷을 변이 전에 복사
        if (tmpl == null) return;
        try {
            net.minecraft.nbt.NbtElement val = tmpl.copy();
            net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
            if (root == null) root = new net.minecraft.nbt.NbtCompound();
            boolean changed;
            switch (mode) {
                case "append"  -> changed = appendAtPath(root, path, val, false);
                case "prepend" -> changed = appendAtPath(root, path, val, true);
                case "merge"   -> changed = mergeAtPath(root, path, val);
                default        -> changed = putAtPath(root, path, val);   // set
            }
            if (changed) storageSave(server, id, root);
        } catch (Exception ignored) {}
    }

    // ════════════════════════════════════════════════════════════════
    //  [B] 매크로 SNBT 템플릿 패칭 (optimize-offer B-5)
    //  동적 매크로 SNBT `"PRE" + <수치인자> + "POST"` 는 연속값에서 매 호출 SNBT_CACHE 미스 →
    //  거대 SNBT 전체 재파싱이 든다. PRE/POST 가 상수이고 인자가 '완전한 수치 값 슬롯'을 차지하면,
    //  골격(pre+SENT+post)을 1회 파싱해 두고 호출 시 deep copy + 슬롯 노드만 set 하면 된다.
    //
    //  안전(3중 fail-closed):
    //   1) 클래스 로드 자기검증: 서로 다른 sentinel(111/222)을 실제 마크 파서로 파싱해 '정확히
    //      한 개의 수치 leaf'만 다른지 확인. 구조가 어긋나거나 leaf 가 비수치/복수면 비활성(ok=false).
    //   2) 클래스 로드 동치검증: 여러 probe 로 (skeleton+patch)==(전체 재파싱) 을 실제 파서로 assert.
    //      하나라도 어긋나면 비활성 — 구현 버그까지 여기서 걸러 concat 폴백만 남긴다.
    //   3) 매 호출: 인자가 엄격 수치 토큰(구조문자 불가 → 인젝션 불가)일 때만 fast-path, 아니면 concat.
    //  compound 키 경로만 탐색(리스트 슬롯은 leaf 불일치로 자동 비활성) — NbtList API 미사용.
    //  [15차] 문자열 hole 지원: 따옴표 리터럴 내부의 슬롯("...$v...")을 센티널 전후조각 일치로
    //  식별해 NbtString 제자리 set 으로 처리한다. 호출 가드는 따옴표/역슬래시 포함 값 거부
    //  (이스케이프·인용부호 상호작용 원천 차단 — 그런 값만 concat 폴백). bare 문자열 슬롯은
    //  probe("0:12.34"/"a b")가 재파싱 실패를 일으켜 구성 단계에서 자동 비활성(fail-closed).
    // ════════════════════════════════════════════════════════════════
    private static final boolean SNBT_TEMPLATE =
            !"off".equalsIgnoreCase(System.getProperty("kfc.snbttemplate", "on"));
    private static final java.util.regex.Pattern _NUM_TOKEN =
            java.util.regex.Pattern.compile("[+-]?(?:\\d+\\.?\\d*|\\.\\d+)(?:[eE][+-]?\\d+)?[bBsSlLfFdD]?");
    /** 15차: 문자열 hole 가드 — 따옴표/역슬래시 포함 값은 concat 파싱과 이스케이프 상호작용이
     *  생길 수 있어 fast-path 를 포기한다(concat 폴백 = 원본 동작 그대로). */
    private static boolean _strTokenOk(String v) {
        for (int i = 0; i < v.length(); i++) {
            char c = v.charAt(i);
            if (c == '"' || c == '\'' || c == '\\') return false;
        }
        return true;
    }

    public static final class SnbtTmpl {
        final String pre, post; final boolean wrap;   // wrap=true: storage 값({v:..}) / false: compound
        final boolean ok;
        final net.minecraft.nbt.NbtElement skeleton;   // pre+"0"+post 파싱 결과(unwrap 후)
        final String[] path;                            // hole 까지의 compound 키 경로(마지막=슬롯 키)
        // 15차: hole 종류 — 0=수치 슬롯(종전), 1=문자열 슬롯(따옴표 리터럴 내부, sPre+val+sSuf).
        final byte kind; final String sPre, sSuf;

        SnbtTmpl(String pre, String post, boolean wrap) {
            this.pre = pre; this.post = post; this.wrap = wrap;
            boolean okv = false; net.minecraft.nbt.NbtElement sk = null; String[] pth = null;
            byte kd = 0; String sp = null, ss = null;
            try {
                net.minecraft.nbt.NbtElement a = _parse(pre, "111", post, wrap);
                net.minecraft.nbt.NbtElement b = _parse(pre, "222", post, wrap);
                if (a != null && b != null) {
                    java.util.List<java.util.List<String>> diffs = new java.util.ArrayList<>();
                    _collectDiffs(a, b, new java.util.ArrayDeque<>(), diffs);
                    if (diffs.size() == 1) {
                        String[] p = diffs.get(0).toArray(new String[0]);
                        net.minecraft.nbt.NbtElement la = _leafAt(a, p), lb = _leafAt(b, p);
                        String[] probes = null;
                        if (la instanceof net.minecraft.nbt.AbstractNbtNumber
                                && lb instanceof net.minecraft.nbt.AbstractNbtNumber) {
                            probes = new String[]{"333", "-7", "40000", "5", "12.5"};   // 수치 슬롯(종전)
                        } else if (la instanceof net.minecraft.nbt.NbtString
                                && lb instanceof net.minecraft.nbt.NbtString) {
                            // 15차: 문자열 슬롯 — sentinel 이 양쪽 leaf 에 각 1회, 같은 위치,
                            // 전후 조각 동일일 때만 채택. 그 외는 ok=false(concat 폴백).
                            String va = ((net.minecraft.nbt.NbtString) la).asString().orElse(null);
                            String vb = ((net.minecraft.nbt.NbtString) lb).asString().orElse(null);
                            if (va != null && vb != null) {
                                int ia = va.indexOf("111"), ib = vb.indexOf("222");
                                if (ia >= 0 && ia == va.lastIndexOf("111")
                                        && ib == ia && ib == vb.lastIndexOf("222")
                                        && va.substring(0, ia).equals(vb.substring(0, ib))
                                        && va.substring(ia + 3).equals(vb.substring(ib + 3))) {
                                    kd = 1; sp = va.substring(0, ia); ss = va.substring(ia + 3);
                                    // "0:12.34"/"a b" 는 bare(비따옴표) 슬롯이면 재파싱이 실패해
                                    // 자동 비활성 — 따옴표 슬롯만 통과한다.
                                    probes = new String[]{"abc", "0:12.34", "12", "a b", ""};
                                }
                            }
                        }
                        if (probes != null) {
                            boolean good = true;
                            for (String probe : probes) {
                                net.minecraft.nbt.NbtElement patched = _patchCopy(a, p, probe, wrap, kd, sp, ss);
                                net.minecraft.nbt.NbtElement truth = _parse(pre, probe, post, wrap);
                                if (patched == null || truth == null || !patched.equals(truth)) { good = false; break; }
                            }
                            if (good) { okv = true; sk = a.copy(); pth = p; }
                        }
                    }
                }
            } catch (Exception ignored) {}
            this.ok = okv; this.skeleton = sk; this.path = pth;
            this.kind = kd; this.sPre = sp; this.sSuf = ss;
        }

        /** 인자를 슬롯에 꽂은 결과 element. 가드 불통과/파싱실패/비활성이면 null(→ 호출측 concat 폴백). */
        net.minecraft.nbt.NbtElement patch(String val) {
            if (!ok || val == null) return null;
            if (kind == 0 ? !_NUM_TOKEN.matcher(val).matches() : !_strTokenOk(val)) return null;
            return _patchCopy(skeleton, path, val, wrap, kind, sPre, sSuf);
        }

        private static net.minecraft.nbt.NbtElement _parse(String pre, String mid, String post, boolean wrap) {
            try {
                String body = pre + mid + post;
                net.minecraft.nbt.NbtCompound w =
                        net.minecraft.nbt.StringNbtReader.readCompound(wrap ? ("{v:" + body + "}") : body);
                return wrap ? w.get("v") : w;
            } catch (Exception ex) { return null; }
        }

        // compound 키만 재귀. compound 형태가 서로 같을 때만 하위로, 그 외엔 leaf 로 equals 비교.
        private static void _collectDiffs(net.minecraft.nbt.NbtElement a, net.minecraft.nbt.NbtElement b,
                                          java.util.ArrayDeque<String> path,
                                          java.util.List<java.util.List<String>> out) {
            if (a instanceof net.minecraft.nbt.NbtCompound ca && b instanceof net.minecraft.nbt.NbtCompound cb) {
                java.util.TreeSet<String> keys = new java.util.TreeSet<>();
                keys.addAll(ca.getKeys()); keys.addAll(cb.getKeys());
                for (String k : keys) {
                    net.minecraft.nbt.NbtElement av = ca.get(k), bv = cb.get(k);
                    if (av == null || bv == null) { out.add(new java.util.ArrayList<>(path)); continue; }
                    path.addLast(k); _collectDiffs(av, bv, path, out); path.removeLast();
                }
            } else if (!a.equals(b)) {
                out.add(new java.util.ArrayList<>(path));
            }
        }

        private static net.minecraft.nbt.NbtElement _leafAt(net.minecraft.nbt.NbtElement root, String[] p) {
            net.minecraft.nbt.NbtElement cur = root;
            for (String k : p) {
                if (!(cur instanceof net.minecraft.nbt.NbtCompound c)) return null;
                cur = c.get(k);
                if (cur == null) return null;
            }
            return cur;
        }

        // skeleton 을 deep copy 후 path 슬롯을 set. 수치 슬롯=parse(val), 문자열 슬롯=NbtString.
        private static net.minecraft.nbt.NbtElement _patchCopy(net.minecraft.nbt.NbtElement root, String[] p,
                                                               String val, boolean wrap,
                                                               byte kind, String sPre, String sSuf) {
            try {
                net.minecraft.nbt.NbtElement ve;
                if (kind == 0) {
                    net.minecraft.nbt.NbtCompound w =
                            net.minecraft.nbt.StringNbtReader.readCompound("{v:" + val + "}");
                    ve = w.get("v");
                    if (!(ve instanceof net.minecraft.nbt.AbstractNbtNumber)) return null;
                } else {
                    ve = net.minecraft.nbt.NbtString.of(sPre + val + sSuf);
                }
                if (p.length == 0) return ve;   // 루트 전체가 hole(값 전체 치환형 `set value $(v)`)
                net.minecraft.nbt.NbtElement copy = root.copy();
                net.minecraft.nbt.NbtElement cur = copy;
                for (int i = 0; i < p.length - 1; i++) {
                    if (!(cur instanceof net.minecraft.nbt.NbtCompound c)) return null;
                    cur = c.get(p[i]);
                    if (cur == null) return null;
                }
                if (!(cur instanceof net.minecraft.nbt.NbtCompound parent)) return null;
                parent.put(p[p.length - 1], ve);
                return copy;
            } catch (Exception ex) { return null; }
        }
    }

    /** 골격 템플릿 팩토리(compound 판 — entityMergeSnbt 용). */
    public static SnbtTmpl snbtTmplC(String pre, String post) { return new SnbtTmpl(pre, post, false); }
    /** 골격 템플릿 팩토리(값 판 — storagePutSnbt 용). */
    public static SnbtTmpl snbtTmplV(String pre, String post) { return new SnbtTmpl(pre, post, true); }

    /** 다중 수치 hole 골격 템플릿 — SnbtTmpl 의 k-hole 일반화(동일한 자기검증·fail-closed 원칙).
     *  [실측] kartvector 류 다인자 매크로 SNBT 가 매 호출 SNBT_CACHE 미스 → 전체 재파싱(~1%p).
     *  구성: 센티널 2조로 파싱해 diff 경로 k개를 얻고, 잎 값(=센티널)으로 hole↔경로를 대응시킨 뒤
     *  프로브 벡터로 patchN == 진짜 파싱 을 검증한다. 실패 시 ok=false → 호출측 concat 폴백. */
    public static final class SnbtTmplN {
        final String[] lits;                 // k+1 개 리터럴 조각(폴백 concat 용)
        final boolean wrap;
        final boolean ok;
        final net.minecraft.nbt.NbtElement skeleton;
        // 18차: leaf 단위 patch unit — 한 leaf 가 여러 hole 을 담을 수 있다(문자열 leaf).
        //   kinds[u]  : 0=수치 1-hole leaf / 1=문자열 leaf(다중 hole, 세그먼트 재조립)
        //   paths[u]  : leaf 까지의 compound 키 경로(길이 0 = 루트 전체)
        //   holeIdx[u]: 이 leaf 에 박힌 hole 번호들(등장 위치순)
        //   segs[u]   : 상수 세그먼트(holeIdx.length+1 개) — leaf 값 = seg0+val+seg1+val+...
        final String[][] paths;
        final byte[] kinds;
        final int[][] holeIdx;
        final String[][] segs;
        final byte[] holeKind;               // hole i → 0(수치)/1(문자열) — patchN 가드용

        SnbtTmplN(boolean wrap, String[] lits) {
            this.lits = lits; this.wrap = wrap;
            boolean okv = false; net.minecraft.nbt.NbtElement sk = null;
            String[][] pth = null; byte[] kdA = null; int[][] hiA = null; String[][] sgA = null;
            byte[] hkA = null;
            try {
                int k = lits.length - 1;
                String[] s1 = new String[k], s2 = new String[k];
                for (int i = 0; i < k; i++) { s1[i] = Integer.toString(101 + i); s2[i] = Integer.toString(201 + i); }
                net.minecraft.nbt.NbtElement a = _parseN(lits, s1, wrap);
                net.minecraft.nbt.NbtElement b = _parseN(lits, s2, wrap);
                if (a != null && b != null) {
                    java.util.List<java.util.List<String>> diffs = new java.util.ArrayList<>();
                    SnbtTmpl._collectDiffs(a, b, new java.util.ArrayDeque<>(), diffs);
                    int u = diffs.size();
                    if (u >= 1 && u <= k) {
                        String[][] ps = new String[u][];
                        byte[] kd = new byte[u];
                        int[][] hi = new int[u][];
                        String[][] sg = new String[u][];
                        byte[] hk = new byte[k];
                        boolean[] seen = new boolean[k];
                        boolean map = true;
                        int ui = 0;
                        for (java.util.List<String> dp : diffs) {
                            String[] pp = dp.toArray(new String[0]);
                            net.minecraft.nbt.NbtElement la = SnbtTmpl._leafAt(a, pp);
                            net.minecraft.nbt.NbtElement lb = SnbtTmpl._leafAt(b, pp);
                            if (la instanceof net.minecraft.nbt.AbstractNbtNumber num
                                    && lb instanceof net.minecraft.nbt.AbstractNbtNumber) {
                                int hole = (int) Math.round(num.doubleValue()) - 101;   // 센티널 → hole 번호
                                if (hole < 0 || hole >= k || seen[hole]) { map = false; break; }
                                seen[hole] = true;
                                ps[ui] = pp; kd[ui] = 0; hi[ui] = new int[]{hole};
                                sg[ui] = null; hk[hole] = 0;
                            } else if (la instanceof net.minecraft.nbt.NbtString
                                    && lb instanceof net.minecraft.nbt.NbtString) {
                                // 18차: 문자열 leaf — 등장하는 모든 sentinel 을 위치순으로 수집해
                                // (hole 목록, 상수 세그먼트) 로 분해한다. 각 sentinel 은 전체 템플릿에서
                                // 1회만, b-파싱과 같은 오프셋·같은 세그먼트여야 한다(불일치 = 비활성).
                                String va = ((net.minecraft.nbt.NbtString) la).asString().orElse(null);
                                String vb = ((net.minecraft.nbt.NbtString) lb).asString().orElse(null);
                                if (va == null || vb == null || va.length() != vb.length()) { map = false; break; }
                                java.util.ArrayList<int[]> occ = new java.util.ArrayList<>();   // {pos, hole}
                                for (int i = 0; i < k; i++) {
                                    int ia = va.indexOf(s1[i]);
                                    if (ia < 0) continue;
                                    if (ia != va.lastIndexOf(s1[i])) { map = false; break; }
                                    if (!vb.startsWith(s2[i], ia)) { map = false; break; }
                                    if (vb.indexOf(s2[i]) != ia || vb.lastIndexOf(s2[i]) != ia) { map = false; break; }
                                    if (seen[i]) { map = false; break; }
                                    occ.add(new int[]{ia, i});
                                }
                                if (!map) break;
                                if (occ.isEmpty()) { map = false; break; }
                                occ.sort((x, y) -> Integer.compare(x[0], y[0]));
                                int n2 = occ.size();
                                int[] holes = new int[n2];
                                String[] sgs = new String[n2 + 1];
                                int prev = 0;
                                boolean overlap = false;
                                for (int oi = 0; oi < n2; oi++) {
                                    int pos = occ.get(oi)[0], hole = occ.get(oi)[1];
                                    if (pos < prev) { overlap = true; break; }   // sentinel 겹침 — 비활성
                                    holes[oi] = hole;
                                    sgs[oi] = va.substring(prev, pos);
                                    prev = pos + s1[hole].length();
                                    // b-파싱 세그먼트 동일성 확인(위 startsWith 가 오프셋 일치를 보장,
                                    // 세그먼트는 sentinel 외 구간이므로 va/vb 동일해야 함)
                                    if (!vb.startsWith(sgs[oi], pos - sgs[oi].length())) { overlap = true; break; }
                                }
                                if (overlap) { map = false; break; }
                                sgs[n2] = va.substring(prev);
                                if (!vb.endsWith(sgs[n2])) { map = false; break; }
                                for (int oi = 0; oi < n2; oi++) { seen[holes[oi]] = true; hk[holes[oi]] = 1; }
                                ps[ui] = pp; kd[ui] = 1; hi[ui] = holes; sg[ui] = sgs;
                            } else {
                                map = false; break;
                            }
                            ui++;
                        }
                        if (map) {
                            for (int i = 0; i < k; i++) if (!seen[i]) { map = false; break; }   // 전 hole 피복
                        }
                        if (map) {
                            boolean good = true;
                            String[] numProbe = {"7", "-3", "40000", "12.5"};
                            String[] strProbe = {"abc", "0:12.34", "a b", ""};
                            for (int trial = 0; trial < 3 && good; trial++) {
                                String[] vals = new String[k];
                                for (int i = 0; i < k; i++) vals[i] = (hk[i] == 0)
                                        ? numProbe[(i + trial) % numProbe.length]
                                        : strProbe[(i + trial) % strProbe.length];
                                net.minecraft.nbt.NbtElement patched = _patchN(a, ps, kd, hi, sg, vals);
                                net.minecraft.nbt.NbtElement truth = _parseN(lits, vals, wrap);
                                if (patched == null || truth == null || !patched.equals(truth)) { good = false; break; }
                            }
                            if (good) {
                                okv = true; sk = a.copy();
                                pth = ps; kdA = kd; hiA = hi; sgA = sg; hkA = hk;
                            }
                        }
                    }
                }
            } catch (Exception ignored) {}
            this.ok = okv; this.skeleton = sk; this.paths = pth;
            this.kinds = kdA; this.holeIdx = hiA; this.segs = sgA; this.holeKind = hkA;
        }

        private static net.minecraft.nbt.NbtElement _parseN(String[] lits, String[] vals, boolean wrap) {
            StringBuilder sb = new StringBuilder(lits[0]);
            for (int i = 0; i < vals.length; i++) sb.append(vals[i]).append(lits[i + 1]);
            try {
                net.minecraft.nbt.NbtCompound w = net.minecraft.nbt.StringNbtReader
                        .readCompound(wrap ? ("{v:" + sb + "}") : sb.toString());
                return wrap ? w.get("v") : w;
            } catch (Exception ex) { return null; }
        }

        /** skeleton 1회 copy 후 각 unit leaf 를 제자리 set. 가드/타입 불일치 = null. */
        private static net.minecraft.nbt.NbtElement _patchN(net.minecraft.nbt.NbtElement root,
                                                            String[][] paths, byte[] kinds,
                                                            int[][] holeIdx, String[][] segs,
                                                            String[] vals) {
            try {
                net.minecraft.nbt.NbtElement copy = root.copy();
                for (int u = 0; u < paths.length; u++) {
                    net.minecraft.nbt.NbtElement ve;
                    if (kinds[u] == 0) {
                        net.minecraft.nbt.NbtCompound w =
                                net.minecraft.nbt.StringNbtReader.readCompound("{v:" + vals[holeIdx[u][0]] + "}");
                        ve = w.get("v");
                        if (!(ve instanceof net.minecraft.nbt.AbstractNbtNumber)) return null;
                    } else {
                        String[] sgs = segs[u];
                        int[] holes = holeIdx[u];
                        StringBuilder sb = new StringBuilder(sgs[0]);
                        for (int oi = 0; oi < holes.length; oi++) sb.append(vals[holes[oi]]).append(sgs[oi + 1]);
                        ve = net.minecraft.nbt.NbtString.of(sb.toString());
                    }
                    String[] p = paths[u];
                    if (p.length == 0) { copy = ve; continue; }   // 루트 hole(단일-diff 시에만 발생)
                    net.minecraft.nbt.NbtElement cur = copy;
                    for (int i = 0; i < p.length - 1; i++) {
                        if (!(cur instanceof net.minecraft.nbt.NbtCompound c)) return null;
                        cur = c.get(p[i]);
                        if (cur == null) return null;
                    }
                    if (!(cur instanceof net.minecraft.nbt.NbtCompound parent)) return null;
                    parent.put(p[p.length - 1], ve);
                }
                return copy;
            } catch (Exception ex) { return null; }
        }

        net.minecraft.nbt.NbtElement patchN(String[] vals) {
            if (!ok || vals == null || vals.length != holeKind.length) return null;
            for (int i = 0; i < vals.length; i++) {
                String v = vals[i];
                if (v == null) return null;
                if (holeKind[i] == 0 ? !_NUM_TOKEN.matcher(v).matches() : !_strTokenOk(v)) return null;
            }
            return _patchN(skeleton, paths, kinds, holeIdx, segs, vals);
        }

        String concat(String[] vals) {
            StringBuilder sb = new StringBuilder(lits[0]);
            for (int i = 0; i < vals.length; i++) sb.append(vals[i]).append(lits[i + 1]);
            return sb.toString();
        }
    }
    public static SnbtTmplN snbtTmplNC(String... lits) { return new SnbtTmplN(false, lits); }
    public static SnbtTmplN snbtTmplNV(String... lits) { return new SnbtTmplN(true, lits); }

    /** 다중 hole 판 entityMergeSnbt. fast 실패 시 기존 concat+파싱 경로로 폴백(동작 동일). */
    public static void entityMergeSnbtTN(net.minecraft.entity.Entity e, SnbtTmplN t, String... vals) {
        if (SNBT_TEMPLATE && t != null && t.ok) {
            net.minecraft.nbt.NbtElement patched = t.patchN(vals);
            if (patched instanceof net.minecraft.nbt.NbtCompound tc) { entityMergeSnbt(e, tc); return; }
        }
        entityMergeSnbt(e, t == null ? "" : t.concat(vals));
    }

    /** 다중 hole 판 storagePutSnbt. fast 실패 시 기존 concat+파싱 경로로 폴백(동작 동일). */
    public static void storagePutSnbtTN(net.minecraft.server.MinecraftServer server, String id,
                                        String path, SnbtTmplN t, String mode, String... vals) {
        if (SNBT_TEMPLATE && t != null && t.ok) {
            net.minecraft.nbt.NbtElement patched = t.patchN(vals);
            if (patched != null) { storagePutSnbt(server, id, path, patched, mode); return; }
        }
        storagePutSnbt(server, id, path, t == null ? "" : t.concat(vals), mode);
    }

    /** entityMergeSnbt 템플릿 오버로드. fast-path 실패 시 정확히 기존 concat+파싱 경로로 폴백. */
    public static void entityMergeSnbtT(net.minecraft.entity.Entity e, SnbtTmpl t, String val) {
        if (SNBT_TEMPLATE && t != null && t.ok) {
            net.minecraft.nbt.NbtElement patched = t.patch(val);
            if (patched instanceof net.minecraft.nbt.NbtCompound tc) { entityMergeSnbt(e, tc); return; }
        }
        entityMergeSnbt(e, (t == null ? val : t.pre + val + t.post));   // 폴백(원본 동작)
    }

    /** storagePutSnbt 템플릿 오버로드. fast-path 실패 시 기존 concat+파싱 경로로 폴백. */
    public static void storagePutSnbtT(net.minecraft.server.MinecraftServer server, String id,
                                       String path, SnbtTmpl t, String val, String mode) {
        if (SNBT_TEMPLATE && t != null && t.ok) {
            net.minecraft.nbt.NbtElement patched = t.patch(val);
            if (patched != null) { storagePutSnbt(server, id, path, patched, mode); return; }
        }
        storagePutSnbt(server, id, path, (t == null ? val : t.pre + val + t.post), mode);   // 폴백
    }

    public static void entityRemovePath(net.minecraft.entity.Entity e, String path) {
        invalidateSnapshot(e);
        // DisplayEntity 의 brightness 는 DataTracker 필드 — writeNbt/readNbt 폴백으로는
        // 트래커가 갱신되지 않아 라이트가 꺼지지 않는다. setBrightness(null) 로 직접 제거(기본값 복귀).
        if ("brightness".equals(path.replace(" ", ""))
                && e instanceof net.minecraft.entity.decoration.DisplayEntity d) {
            d.setBrightness(null);
            return;
        }
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(nbt);
        net.minecraft.command.argument.NbtPathArgumentType.NbtPath p = nbtPath(path);   // NbtPath
        if (p == null) return;
        try { if (p.remove(nbt) > 0) readNbtTagAware(e, nbt); } catch (Exception ignored) {}
    }

    // ──────────────── 매크로 인자 (function X with .../{...}) ────────────────
    // mcfunction 매크로: $(var) 는 NBT 값의 문자열 표현으로 치환된다.
    //  - NbtString  → 따옴표 없는 원문(value())
    //  - 그 외(숫자/컴파운드/리스트) → SNBT(toString())
    // 호출부에서 만든 Map<String,String> 을 피호출 함수의 execute(source, macroArgs) 로 넘긴다.

    /** 가변 인자 (k1,v1,k2,v2,...) → Map. 인라인 {compound} 호출에서 사용. */
    /** 바닐라 매크로 인스턴스화: 함수가 쓰는 $(var) 중 하나라도 인자에 없으면 그 함수 호출은
     *  '전체 실패'(어떤 줄도 미실행)한다. 이 함수가 그 사전검사 — keys 전부 있으면 true. */
    public static boolean macroHasAll(java.util.Map<String, String> m, String... keys) {
        if (m == null) return keys.length == 0;
        for (String k : keys) if (!m.containsKey(k)) return false;
        return true;
    }

    public static java.util.Map<String, String> macroArgs(String... kv) {
        java.util.Map<String, String> m = new java.util.HashMap<>();
        for (int i = 0; i + 1 < kv.length; i += 2) m.put(kv[i], kv[i + 1]);
        return m;
    }

    /** 매크로 인자가 비었는지(null 또는 빈 문자열). 바닐라에서 {key:$(var)} 의 $(var) 가
     *  비면 NBT 파싱 실패로 매크로 줄이 스킵되는 것과 동일 판정에 쓴다. */
    public static boolean macroEmpty(String v) {
        return v == null || v.isEmpty();
    }

    /** MC 의 Macro.toString 과 동일한 DecimalFormat: "#", 소수 15자리, Locale.US. */
    private static final java.text.DecimalFormat MACRO_DECIMAL_FORMAT = makeMacroFormat();

    private static java.text.DecimalFormat makeMacroFormat() {
        java.text.DecimalFormat f = new java.text.DecimalFormat("#");
        f.setMaximumFractionDigits(15);
        f.setDecimalFormatSymbols(java.text.DecimalFormatSymbols.getInstance(java.util.Locale.US));
        return f;
    }

    /** NBT → 매크로 인자 문자열. MC 의 net.minecraft.server.function.Macro.toString 과 동일 규칙:
     *   float/double → DecimalFormat(접미사 없음), 정수형 → 10진수, 문자열 → raw, 그 외 → SNBT.
     *   (이전엔 e.toString() 으로 "0.45f" 처럼 접미사가 붙어 playsound 피치 파싱이 실패했음.) */
    private static String nbtToMacroString(net.minecraft.nbt.NbtElement e) {
        if (e == null) return "";
        if (e instanceof net.minecraft.nbt.NbtString s) return s.value();
        if (e instanceof net.minecraft.nbt.NbtFloat || e instanceof net.minecraft.nbt.NbtDouble) {
            return MACRO_DECIMAL_FORMAT.format(((net.minecraft.nbt.AbstractNbtNumber) e).doubleValue());
        }
        if (e instanceof net.minecraft.nbt.AbstractNbtNumber n) {
            return String.valueOf(n.longValue());
        }
        return e.toString();
    }

    /** 인라인 매크로 인자 값(`function X {k:v,...}` 의 v)을 바닐라와 동일하게 정규화한다.
     *  바닐라는 라인 치환 후 compound 전체를 SNBT 로 재파싱해 각 값을 NBT 타입으로 만들고,
     *  매크로 치환 시 Macro.toString(NbtElement) 로 문자열화한다. 이를 그대로 재현:
     *  바닐라 파서(StringNbtReader)로 파싱 → nbtToMacroString(=Macro.toString 과 관측 동등 증명).
     *  예: 1.5f→"1.5" · 3s→"3" · 2.0d→"2" · 'red'→red · {'a': 1}→{a:1} (공백/인용 정규화).
     *  파싱 실패(잘못된 SNBT — 바닐라는 그 라인 인스턴스화 실패로 함수 미실행) → null 반환,
     *  호출부는 null 이면 함수를 호출하지 않는다(관측상 함수 미실행으로 동일).
     *  값은 변환 시점 상수+유한한 매크로 체인이므로 캐시(distinct 값당 1회 파싱). */
    private static final java.util.HashMap<String, String> MACRO_NORM_CACHE =
            new java.util.HashMap<>();
    private static final String MACRO_NORM_INVALID = new String("\u0000invalid");
    public static String macroNormalize(String raw) {
        if (raw == null) return null;
        String r = MACRO_NORM_CACHE.computeIfAbsent(raw, s -> {
            try {
                net.minecraft.nbt.NbtCompound w =
                        net.minecraft.nbt.StringNbtReader.readCompound("{v:" + s + "}");
                net.minecraft.nbt.NbtElement v = w.get("v");
                return v == null ? MACRO_NORM_INVALID : nbtToMacroString(v);
            } catch (Exception ex) { return MACRO_NORM_INVALID; }
        });
        return (r == MACRO_NORM_INVALID) ? null : r;
    }

    /** 매크로 인자 Map — 원본(즉시 전 키 문자열화)과 관측 동등하되, 비싼 값(컴파운드/리스트의 SNBT
     *  toString, 스파크상 nbtToMacroString 22.5k ms)을 실제 참조될 때까지 미룬다.
     *  바인드 시점 스냅샷 보존: 스칼라는 즉시 문자열화, 비스칼라는 그 자리에서 copy(깊은 복사)해 둔다.
     *  → 소스가 호출 도중 제자리 변형돼도 바인드 시점 값으로 해소(바닐라 `with` 스냅샷 시맨틱과 동일).
     *  참조되지 않은 비스칼라 키는 toString 을 영영 수행하지 않아 그만큼 절약된다. */
    private static final class SnapMacroArgs extends java.util.AbstractMap<String, String> {
        private final java.util.HashMap<String, String> resolved = new java.util.HashMap<>();
        private final java.util.HashMap<String, net.minecraft.nbt.NbtElement> pending = new java.util.HashMap<>();
        // 바인드 시점 원본 타입 NBT 스냅샷 — 키별 요소로 보관(10차: 종전 c.copy() 전체 깊은복사가
        // 스파크 1.07%p — 비스칼라는 pending 에 이미 copy 가 있고 스칼라 NBT 는 불변이라 참조로
        // 충분해 이중 복사였다). 브릿지 폴백은 rawArgsCopy() 재조립본을 바닐라 매크로 엔진
        // (Macro.withMacroReplaced)에 그대로 넘겨, 우리 문자열화(nbtToMacroString)에 의존하지 않고
        // 바닐라 자체 Macro.toString 으로 치환한다 → 폴백이 바닐라와 완전 동일(미래 MC 포맷 변화에도 자동 추종).
        private final java.util.HashMap<String, net.minecraft.nbt.NbtElement> bindEls = new java.util.HashMap<>();
        // 23차: copy-on-write — 비스칼라도 '참조'로 보관하고, 소스 storage 가 변이되기 직전에만
        // snapBarrier 가 materialize()(그때 1회 복사)를 호출한다. 변이가 없으면 복사 0.
        private boolean matz;   // true = 실체화 완료(또는 불필요) — 이후 참조가 안전
        SnapMacroArgs(net.minecraft.nbt.NbtCompound c, String srcStorageId) {
            boolean hasComposite = false;
            for (String k : c.getKeys()) {
                net.minecraft.nbt.NbtElement el = c.get(k);
                if (el == null) continue;
                if (el instanceof net.minecraft.nbt.NbtString
                        || el instanceof net.minecraft.nbt.AbstractNbtNumber) {
                    resolved.put(k, nbtToMacroString(el));     // 스칼라: 즉시 스냅샷(원본과 동일)
                    bindEls.put(k, el);                        // NBT 스칼라는 불변 — 참조 스냅샷으로 충분
                } else {
                    pending.put(k, el);                        // 비스칼라: 참조 보관(23차 — 복사는 장벽에서)
                    bindEls.put(k, el);
                    hasComposite = true;
                }
            }
            // entity/block 소스(srcStorageId=null)는 원본이 제자리 변이되지 않아 실체화 불필요.
            this.matz = !hasComposite || srcStorageId == null;
            if (!this.matz) {
                SNAP_PENDING.computeIfAbsent(srcStorageId, k2 -> new java.util.ArrayList<>()).add(this);
            }
        }
        /** 소스 storage 변이 직전 1회 — 비스칼라 참조를 바인드 시점 값의 복사본으로 교체. */
        void materialize() {
            if (matz) return;
            matz = true;
            for (java.util.Map.Entry<String, net.minecraft.nbt.NbtElement> en : bindEls.entrySet()) {
                net.minecraft.nbt.NbtElement v = en.getValue();
                if (v instanceof net.minecraft.nbt.NbtString
                        || v instanceof net.minecraft.nbt.AbstractNbtNumber) continue;
                net.minecraft.nbt.NbtElement cp = v.copy();
                en.setValue(cp);
                if (pending.get(en.getKey()) == v) pending.put(en.getKey(), cp);
            }
        }
        /** 브릿지용 — 바인드 시점 원본 타입 NBT 의 복사본(키별 스냅샷 재조립, 종전 bindSnapshot.copy() 와 값 동일). */
        net.minecraft.nbt.NbtCompound rawArgsCopy() {
            net.minecraft.nbt.NbtCompound out = new net.minecraft.nbt.NbtCompound();
            for (java.util.Map.Entry<String, net.minecraft.nbt.NbtElement> e : bindEls.entrySet())
                out.put(e.getKey(), e.getValue().copy());
            return out;
        }
        @Override public boolean containsKey(Object k) {
            return resolved.containsKey(k) || pending.containsKey(k);
        }
        @Override public String get(Object k) {
            if (resolved.containsKey(k)) return resolved.get(k);
            net.minecraft.nbt.NbtElement el = pending.get(k);
            if (el == null) return null;
            String v = nbtToMacroString(el);                   // 최초 참조 시 1회만 직렬화
            resolved.put((String) k, v); pending.remove(k);
            return v;
        }
        @Override public String put(String k, String v) { pending.remove(k); return resolved.put(k, v); }
        @Override public java.util.Set<Entry<String, String>> entrySet() {
            for (String k : new java.util.ArrayList<>(pending.keySet())) get(k);   // 남은 비스칼라 해소
            return resolved.entrySet();
        }
    }

    // ── 23차: 미실체화 바인드 스냅샷 레지스트리(소스 storage id 별) ──
    // 변이 헬퍼가 해당 id 의 스냅샷만 실체화한다(타 storage 쓰기는 무관 — 낭비 복사 없음).
    // 브릿지/디스패처(바닐라 /data 가능)와 틱 경계(콘솔 개입 수렴)는 전체 실체화.
    private static final java.util.HashMap<String, java.util.ArrayList<SnapMacroArgs>> SNAP_PENDING =
            new java.util.HashMap<>();
    private static void snapBarrier(String id) {
        if (SNAP_PENDING.isEmpty()) return;
        java.util.ArrayList<SnapMacroArgs> l = SNAP_PENDING.remove(id);
        if (l != null) for (int i = 0; i < l.size(); i++) l.get(i).materialize();
    }
    private static void snapBarrierAll() {
        if (SNAP_PENDING.isEmpty()) return;
        for (java.util.ArrayList<SnapMacroArgs> l : SNAP_PENDING.values())
            for (int i = 0; i < l.size(); i++) l.get(i).materialize();
        SNAP_PENDING.clear();
    }

    private static java.util.Map<String, String> compoundToMacroArgs(net.minecraft.nbt.NbtCompound c,
                                                                     String srcStorageId) {
        if (c == null) return new java.util.HashMap<>();
        return new SnapMacroArgs(c, srcStorageId);
    }

    /** function X with storage <id> — 스토리지 루트 컴파운드의 각 필드 → 매크로 인자. */
    public static java.util.Map<String, String> storageMacroArgs(
            net.minecraft.server.MinecraftServer server, String id) {
        return compoundToMacroArgs(storageRoot(server, id), id);
    }

    /** function X with storage <id> <path> — 경로 하위 컴파운드 → 매크로 인자. */
    public static java.util.Map<String, String> storageMacroArgs(
            net.minecraft.server.MinecraftServer server, String id, String path) {
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        return compoundToMacroArgs(compoundAt(root, path), id);
    }

    /** function X with entity @s <path> — 엔티티 NBT 경로 하위 컴파운드 → 매크로 인자. */
    public static java.util.Map<String, String> entityMacroArgs(
            net.minecraft.entity.Entity e, String path) {
        if (e == null) return new java.util.HashMap<>();
        // [버그수정] equipment.offhand / weapon.mainhand / container.N / SelectedItem 등은
        //   '가상 슬롯 경로'다 — 플레이어 오프핸드는 Inventory[Slot:-106] 에 저장돼 raw writeNbt
        //   경로(compoundAt)로는 안 잡힌다. data entity 읽기와 동일하게 슬롯 접근자로 해소해야
        //   아이템 NBT({id,count,components})가 나온다. 이걸 안 하면 `function ... with entity @s
        //   equipment.offhand` 매크로 인자가 빈 맵이 돼(savehelditem 류) 아이템 저장이 실패하고
        //   교체 시 슬롯이 사라진다. 슬롯경로가 아니거나 raw NBT 경로(Inventory[{Slot:9b}] 등)면
        //   slotAccessorNbt 가 null → 아래 raw 경로(compoundAt)로 폴백한다.
        if (path != null && !path.isEmpty()) {
            net.minecraft.nbt.NbtElement se = slotAccessorNbt(e, path);
            if (se instanceof net.minecraft.nbt.NbtCompound sc) return compoundToMacroArgs(sc, null);
        }
        net.minecraft.nbt.NbtCompound nbt = entitySnapshot(e);
        if (path == null || path.isEmpty()) return compoundToMacroArgs(nbt, null);
        return compoundToMacroArgs(compoundAt(nbt, path), null);
    }

    /** function X with block <pos> [<path>] — 블록 엔티티 NBT → 매크로 인자. */
    public static java.util.Map<String, String> blockMacroArgs(
            net.minecraft.server.world.ServerWorld world,
            net.minecraft.util.math.BlockPos pos, String path) {
        net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
        if (be == null) return new java.util.HashMap<>();
        net.minecraft.nbt.NbtCompound nbt = be.createNbt(world.getRegistryManager());
        if (path == null || path.isEmpty()) return compoundToMacroArgs(nbt, null);
        return compoundToMacroArgs(compoundAt(nbt, path), null);
    }

    /** 점 표기 경로의 컴파운드를 반환(없으면 null). 마지막 키가 가리키는 값이 컴파운드여야 함. */
    private static net.minecraft.nbt.NbtCompound compoundAt(net.minecraft.nbt.NbtCompound root, String path) {
        if (root == null) return null;
        // descend 는 점-분리 컴파운드만 처리해 리스트 인덱스(round[0]) 를 해소 못한다.
        // getAtPath 는 NbtPath.parse 라 foo.bar[0].baz 같은 전체 경로를 지원 →
        // `function X with storage <id> <path>` 의 매크로 인자를 정확히 읽는다.
        net.minecraft.nbt.NbtElement leaf = getAtPath(root, path);
        return (leaf instanceof net.minecraft.nbt.NbtCompound lc) ? lc : null;
    }

    // ───────────────────────── 보스바 (/bossbar add|set) ─────────────────────────
    /** "ns:path" → Identifier. 네임스페이스 생략 시 minecraft. */
    public static net.minecraft.util.Identifier bbId(String s) {
        int c = s.indexOf(':');
        return c >= 0 ? net.minecraft.util.Identifier.of(s.substring(0, c), s.substring(c + 1))
                      : net.minecraft.util.Identifier.of("minecraft", s);
    }

    public static net.minecraft.entity.boss.CommandBossBar bossbarGet(
            net.minecraft.server.command.ServerCommandSource src, String idStr) {
        return src.getServer().getBossBarManager().get(bbId(idStr));
    }

    /** 텍스트 컴포넌트(SNBT풍 lenient JSON) → Text. 실패 시 literal 폴백.
     *  바닐라 BossBarCommand 와 동일하게, 파싱 후 Texts.parse 로 selector/score/nbt
     *  컴포넌트를 '명령 실행 시점'에 해소한다. 해소하지 않으면 미해소 SelectorTextContent
     *  가 그대로 저장되어, 클라이언트가 "@a[tag=bangjang]" 같은 셀렉터 패턴을 리터럴로
     *  렌더한다(보고된 "방장 @a[tag=bangjang]" 증상의 원인). */
    private static net.minecraft.text.Text bbText(
            net.minecraft.server.command.ServerCommandSource src, String json, String fallback) {
        try {
            net.minecraft.text.Text t = net.minecraft.text.Text.Serialization
                    .fromLenientJson(json, src.getRegistryManager());
            if (t != null) {
                try {
                    return net.minecraft.text.Texts.parse(src, t, (net.minecraft.entity.Entity) null, 0);
                } catch (Exception parseFail) {
                    return t;   // 해소 실패 시 파싱본 그대로(최소한 add 는 성공)
                }
            }
        } catch (Exception ignored) {}
        return net.minecraft.text.Text.literal(fallback);
    }

    /** bossbar add: 바닐라 /bossbar add 는 같은 id 가 이미 있으면 ALREADY_EXISTS 로 실패하고
     *  아무것도 하지 않는다 — 기존 bar 의 이름/값/색/플레이어를 절대 건드리지 않는다.
     *  (과거 구현은 '이미 있으면 이름을 add 값으로 갱신'했는데, 이는 바닐라 의미 위반이며
     *   회귀를 만든다: 게임 중 `bossbar set name` 으로 갱신된 동적 이름(예: "02:00.000 내로 완주")이,
     *   잔존 bar 가 있는 채로 startset 이 `bossbar add master "Master License"` 를 재실행하면
     *   정적 add 이름("Master License")으로 되돌아간다. 이후 load-settings 의 set name 이 조건
     *   매칭/매크로 타이밍으로 스킵되면 그 흰 "Master License" 가 완주 텍스트를 덮어 보인다.)
     *  → 바닐라처럼 이미 있으면 no-op(기존 bar 그대로 반환). 신규일 때만 add. */
    public static net.minecraft.entity.boss.CommandBossBar bossbarAdd(
            net.minecraft.server.command.ServerCommandSource src, String idStr, String nameJson) {
        net.minecraft.util.Identifier id = bbId(idStr);
        net.minecraft.entity.boss.BossBarManager mgr = src.getServer().getBossBarManager();
        net.minecraft.entity.boss.CommandBossBar bar = mgr.get(id);
        if (bar != null) return bar;
        return mgr.add(id, bbText(src, nameJson, idStr));
    }

    public static void bossbarSetValue(net.minecraft.server.command.ServerCommandSource src, String idStr, int v) {
        net.minecraft.entity.boss.CommandBossBar b = bossbarGet(src, idStr);
        if (b != null) b.setValue(v);
    }
    public static void bossbarSetMaxValue(net.minecraft.server.command.ServerCommandSource src, String idStr, int v) {
        net.minecraft.entity.boss.CommandBossBar b = bossbarGet(src, idStr);
        if (b != null) b.setMaxValue(v);
    }
    public static void bossbarSetColor(net.minecraft.server.command.ServerCommandSource src, String idStr, String color) {
        net.minecraft.entity.boss.CommandBossBar b = bossbarGet(src, idStr);
        if (b == null) return;
        try { b.setColor(net.minecraft.entity.boss.BossBar.Color.valueOf(
                color.toUpperCase(java.util.Locale.ROOT))); } catch (Exception ignored) {}
    }
    public static void bossbarSetStyle(net.minecraft.server.command.ServerCommandSource src, String idStr, String style) {
        net.minecraft.entity.boss.CommandBossBar b = bossbarGet(src, idStr);
        if (b == null) return;
        try { b.setStyle(net.minecraft.entity.boss.BossBar.Style.valueOf(
                style.toUpperCase(java.util.Locale.ROOT))); } catch (Exception ignored) {}
    }
    public static void bossbarRemove(net.minecraft.server.command.ServerCommandSource src, String idStr) {
        net.minecraft.entity.boss.BossBarManager mgr = src.getServer().getBossBarManager();
        net.minecraft.entity.boss.CommandBossBar b = mgr.get(bbId(idStr));
        if (b == null) return;
        // 이미 보고 있던 클라이언트에 'hide' 패킷을 확실히 보낸다.
        // mgr.remove() 만으로는 뷰어에게 제거 패킷이 가지 않아(버전/타이밍에 따라)
        // 데이터만 지워지고 화면엔 고스트 보스바가 남을 수 있다. 정상 동작하는
        // 감속 보스바(`bossbar set players` = clearPlayers 경로)와 동일하게,
        // 제거 전에 명시적으로 플레이어를 모두 떼어내고 숨긴 뒤 매니저에서 제거한다.
        b.clearPlayers();
        b.setVisible(false);
        mgr.remove(b);
    }
    public static void bossbarSetName(net.minecraft.server.command.ServerCommandSource src, String idStr, String nameJson) {
        net.minecraft.entity.boss.CommandBossBar b = bossbarGet(src, idStr);
        if (b != null) b.setName(bbText(src, nameJson, idStr));
    }
    /** execute in <dim>: withWorld + x/z 좌표 스케일(y 불변) — ExecuteCommand 와 동일. */
    public static net.minecraft.server.command.ServerCommandSource inDimension(
            net.minecraft.server.command.ServerCommandSource src, String dimId) {
        net.minecraft.registry.RegistryKey<net.minecraft.world.World> key =
                net.minecraft.registry.RegistryKey.of(net.minecraft.registry.RegistryKeys.WORLD,
                        net.minecraft.util.Identifier.of(dimId));
        net.minecraft.server.world.ServerWorld w = src.getServer().getWorld(key);
        if (w == null) return null;                       // 미존재 차원 — fork 사망
        double f = net.minecraft.world.dimension.DimensionType.getCoordinateScaleFactor(
                src.getWorld().getDimension(), w.getDimension());
        net.minecraft.util.math.Vec3d p = src.getPosition();
        return src.withWorld(w).withPosition(new net.minecraft.util.math.Vec3d(p.x * f, p.y, p.z * f));
    }

    /** team empty <team>: 전 구성원 제거(TeamCommand executeEmpty 와 동일). */
    public static void teamEmpty(GameContext ctx, String name) {
        net.minecraft.scoreboard.Team t = ctx.scoreboard.getTeam(name);
        if (t == null) return;
        for (String m : new java.util.ArrayList<>(t.getPlayerList()))
            ctx.scoreboard.removeScoreHolderFromTeam(m, t);
    }

    /** team remove <team>. */
    public static void teamRemove(GameContext ctx, String name) {
        net.minecraft.scoreboard.Team t = ctx.scoreboard.getTeam(name);
        if (t != null) ctx.scoreboard.removeTeam(t);
    }

    /** scoreboard objectives modify <obj> rendertype hearts|integer. */
    public static void objectiveRenderType(ServerScoreboard sb, String objName, String type) {
        net.minecraft.scoreboard.ScoreboardObjective o = sb.getNullableObjective(objName);
        if (o != null) o.setRenderType("hearts".equals(type)
                ? net.minecraft.scoreboard.ScoreboardCriterion.RenderType.HEARTS
                : net.minecraft.scoreboard.ScoreboardCriterion.RenderType.INTEGER);
        OBJ_GEN++;
    }

    /** scoreboard objectives modify <obj> displayautoupdate <bool>. */
    public static void objectiveDisplayAutoUpdate(ServerScoreboard sb, String objName, boolean v) {
        net.minecraft.scoreboard.ScoreboardObjective o = sb.getNullableObjective(objName);
        if (o != null) o.setDisplayAutoUpdate(v);
        OBJ_GEN++;
    }

    public static void bossbarSetVisible(net.minecraft.server.command.ServerCommandSource src, String idStr, boolean vis) {
        net.minecraft.entity.boss.CommandBossBar b = bossbarGet(src, idStr);
        if (b != null) b.setVisible(vis);
    }
    /** bossbar set <id> players <sel>: 기존 플레이어를 모두 치우고 주어진 집합으로 교체. */
    public static void bossbarSetPlayers(net.minecraft.server.command.ServerCommandSource src, String idStr,
            java.util.Collection<net.minecraft.server.network.ServerPlayerEntity> players) {
        net.minecraft.entity.boss.CommandBossBar b = bossbarGet(src, idStr);
        if (b == null) return;
        b.clearPlayers();
        b.addPlayers(players);
    }
}