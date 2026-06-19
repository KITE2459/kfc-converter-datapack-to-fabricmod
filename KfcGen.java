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
    public static final boolean DBG_SCORECMP = Boolean.getBoolean("kfc.dbg.scorecmp");

    // ── 핫패스 캐시 ──────────────────────────────────────────────
    // 변환 출력은 상수 문자열(홀더명/사운드id/태그id/SNBT/텍스트 컴포넌트)을 매 틱 반복
    // 호출하므로, 문자열 → 파싱결과를 캐시해 매 호출 파싱/레지스트리 조회/객체 생성을 없앤다.
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.scoreboard.ScoreHolder>
            HOLDER_CACHE = new java.util.concurrent.ConcurrentHashMap<>();
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.sound.SoundEvent>
            SOUND_CACHE = new java.util.concurrent.ConcurrentHashMap<>();
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.sound.SoundCategory>
            SOUND_CAT_CACHE = new java.util.concurrent.ConcurrentHashMap<>();
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.registry.tag.TagKey<net.minecraft.block.Block>>
            BLOCK_TAG_CACHE = new java.util.concurrent.ConcurrentHashMap<>();
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.nbt.NbtElement>
            SNBT_CACHE = new java.util.concurrent.ConcurrentHashMap<>();
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.text.Text>
            TEXT_CACHE = new java.util.concurrent.ConcurrentHashMap<>();
    private static final net.minecraft.nbt.NbtElement SNBT_INVALID = new net.minecraft.nbt.NbtCompound();

    private static net.minecraft.scoreboard.ScoreHolder holderOf(String name) {
        return HOLDER_CACHE.computeIfAbsent(name, net.minecraft.scoreboard.ScoreHolder::fromName);
    }

    // Identifier.of 는 호출당 문자열 검증(namespace:path)+할당. Identifier 는 불변이고
    // reload 과 무관(레지스트리 '키'일 뿐 내용 아님) → 캐시 안전. null 입력은 그대로 null 반환.
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.util.Identifier>
            ID_CACHE = new java.util.concurrent.ConcurrentHashMap<>();

    private static net.minecraft.util.Identifier idOf(String s) {
        if (s == null) return null;
        return ID_CACHE.computeIfAbsent(s, net.minecraft.util.Identifier::of);
    }

    private KfcGen() { throw new UnsupportedOperationException(); }

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
        GameContext c = CTX_CACHE;
        net.minecraft.server.MinecraftServer s = src.getServer();
        if (c == null || c.server != s) { c = new GameContext(s); CTX_CACHE = c; }
        return c;
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
        net.minecraft.util.math.Vec3d fwd = new net.minecraft.util.math.Vec3d(f * h, i, g * h);
        net.minecraft.util.math.Vec3d up  = new net.minecraft.util.math.Vec3d(f * j, k, g * j);
        net.minecraft.util.math.Vec3d left = fwd.crossProduct(up).multiply(-1.0);
        double dx = fwd.x * z + up.x * y + left.x * x;
        double dy = fwd.y * z + up.y * y + left.y * x;
        double dz = fwd.z * z + up.z * y + left.z * x;
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
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.loot.condition.LootCondition>
            PREDICATE_CACHE = new java.util.concurrent.ConcurrentHashMap<>();

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
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.command.argument.BlockStateArgument>
            BLOCK_ARG_CACHE = new java.util.concurrent.ConcurrentHashMap<>();

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

    public static void setBlock(net.minecraft.server.world.ServerWorld world,
                                net.minecraft.util.math.BlockPos pos, String blockStr, String mode) {
        if (world == null) return;
        net.minecraft.command.argument.BlockStateArgument arg = parseBlockArg(blockStr);
        if (arg == null) return;
        // keep: 기존 블록이 비어있을 때만 배치
        if ("keep".equals(mode) && !world.getBlockState(pos).isAir()) return;
        // destroy: 기존 블록을 부수고(드롭 발생) 배치
        if ("destroy".equals(mode)) world.breakBlock(pos, true, null, 512);
        arg.setBlockState(world, pos, net.minecraft.block.Block.NOTIFY_ALL);
    }

    // ── 엔티티 타입 태그(#ns:path) 런타임 멤버십 — 컴파일타임 태그 JSON 부재 시 사용 ──
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.registry.tag.TagKey<net.minecraft.entity.EntityType<?>>>
            ENTITY_TYPE_TAG_CACHE = new java.util.concurrent.ConcurrentHashMap<>();

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
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (pred.test(e)) return true;
        }
        return false;
    }

    // ── team ── (바닐라 TeamCommand 흐름: Scoreboard 팀 API 직접 호출)
    public static void teamAdd(GameContext ctx, String name, String displayJson) {
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
        if (holder != null) sb.clearTeam(holder);   // 현재 소속 팀에서 제거
    }

    /** team join <team> <members> — 멤버를 팀에 추가. */
    public static void teamJoin(ServerScoreboard sb, String teamName, String holder) {
        if (holder == null) return;
        net.minecraft.scoreboard.Team t = sb.getTeam(teamName);
        if (t != null) sb.addScoreHolderToTeam(holder, t);
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
                // hollow: 내부는 공기로
                world.setBlockState(p, net.minecraft.block.Blocks.AIR.getDefaultState(), net.minecraft.block.Block.NOTIFY_ALL);
                continue;
            }
            if ("keep".equals(mode) && !world.getBlockState(p).isAir()) continue;
            if (("replace".equals(mode) || mode == null) && !fillFilterMatches(world, p, filter)) continue;
            if ("destroy".equals(mode)) world.breakBlock(p, true, null, 512);
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
            world.setBlockState(pos, sts.get(i), net.minecraft.block.Block.NOTIFY_ALL);
            if (nbts.get(i) != null) {
                net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
                if (be != null) be.read(nbts.get(i), lk);
            }
        }
        if (move) {
            net.minecraft.block.BlockState air = net.minecraft.block.Blocks.AIR.getDefaultState();
            for (net.minecraft.util.math.BlockPos p : src)
                if (!dstSet.contains(p)) world.setBlockState(p, air, net.minecraft.block.Block.NOTIFY_ALL);
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
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (!matchTags(e, tagsPos, tagsNeg)) continue;
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
    private static net.minecraft.util.math.Box rangeBox(net.minecraft.util.math.Vec3d o, double maxDist) {
        return new net.minecraft.util.math.Box(o.x - maxDist, o.y - maxDist, o.z - maxDist,
                                               o.x + maxDist, o.y + maxDist, o.z + maxDist);
    }

    /** 두 좌표 거리의 [lo,hi] 범위 검사 (음수=무시). */
    public static boolean posInRange(net.minecraft.util.math.Vec3d a, net.minecraft.util.math.Vec3d b,
                                     double lo, double hi) {
        double d = a.distanceTo(b);
        if (lo >= 0 && d < lo) return false;
        if (hi >= 0 && d > hi) return false;
        return true;
    }

    /** 타입 미지정 셀렉터(@e/@n[tag,distance]) 존재검사 — distance 상한 시 Box 한정. */
    public static boolean anyEntityAnyType(GameContext ctx, net.minecraft.entity.Entity origin,
                                           String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            return !ctx.world.getOtherEntities(null, rangeBox(origin.getPos(), maxDist),
                    en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)).isEmpty();
        }
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (!matchTags(e, tagsPos, tagsNeg)) continue;
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
            for (net.minecraft.entity.Entity e : ctx.world.getOtherEntities(null, rangeBox(o, maxDist),
                    en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist))) {
                double d = e.getPos().squaredDistanceTo(o);
                if (d < bestD) { bestD = d; best = e; }
            }
            return best;
        }
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (!matchTags(e, tagsPos, tagsNeg)) continue;
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
        if (!append) timer.remove(fnId);
        timer.setEvent(fnId, trigger, new net.minecraft.world.timer.FunctionTimerCallback(id));
    }
    public static void scheduleClear(net.minecraft.server.command.ServerCommandSource src, String fnId) {
        _fnTimer(src).remove(fnId);
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
    public static boolean nbtMatches(net.minecraft.entity.Entity e, String expectedNbt, boolean inverted) {
        boolean match;
        try {
            net.minecraft.nbt.NbtCompound expected =
                net.minecraft.nbt.StringNbtReader.readCompound(expectedNbt);
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

    public static void killEntity(net.minecraft.entity.Entity e) {
        if (e != null && !(e instanceof net.minecraft.server.network.ServerPlayerEntity)) {
            e.discard();
        }
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
        return ctx.world.getOtherEntities(null, box,
                en -> matchTags(en, tagsPos, tagsNeg) && kfcTypeIn(en, types));
    }
    public static boolean posInBox(net.minecraft.util.math.Vec3d o, double dx, double dy, double dz,
                                   net.minecraft.util.math.Vec3d p) {
        double x1 = Math.min(o.x, o.x + dx), x2 = Math.max(o.x, o.x + dx) + 1;
        double y1 = Math.min(o.y, o.y + dy), y2 = Math.max(o.y, o.y + dy) + 1;
        double z1 = Math.min(o.z, o.z + dz), z2 = Math.max(o.z, o.z + dz) + 1;
        return p.x >= x1 && p.x < x2 && p.y >= y1 && p.y < y2 && p.z >= z1 && p.z < z2;
    }
    public static boolean anyEntityInBox(GameContext ctx, net.minecraft.util.math.Vec3d o,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double dx, double dy, double dz) {
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
    public static void playSound(net.minecraft.server.network.ServerPlayerEntity p,
                                 String soundId, String category,
                                 double x, double y, double z, float vol, float pitch) {
        if (p == null) return;
        net.minecraft.sound.SoundEvent ev = SOUND_CACHE.computeIfAbsent(soundId, sid -> {
            net.minecraft.util.Identifier id = idOf(
                    sid.contains(":") ? sid : "minecraft:" + sid);
            net.minecraft.sound.SoundEvent e = net.minecraft.registry.Registries.SOUND_EVENT.get(id);
            return e != null ? e : net.minecraft.sound.SoundEvent.of(id);  // 미등록도 id 그대로 재생(바닐라 허용)
        });
        net.minecraft.sound.SoundCategory cat = SOUND_CAT_CACHE.computeIfAbsent(category, c -> {
            try { return net.minecraft.sound.SoundCategory.valueOf(c.toUpperCase(java.util.Locale.ROOT)); }
            catch (Exception ex) { return net.minecraft.sound.SoundCategory.MASTER; }
        });
        p.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket(
                net.minecraft.registry.entry.RegistryEntry.of(ev), cat,
                x, y, z, vol, pitch, p.getWorld().getRandom().nextLong()));
    }

    /** on vehicle — source 의 엔티티를 그 탈것으로 재바인딩(없으면 null 소스 표식). */
    public static net.minecraft.server.command.ServerCommandSource onVehicle(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (e == null || e.getVehicle() == null) return null;
        // 바닐라 on 은 실행자(엔티티)만 교체하고 위치/회전/차원은 유지한다(위치 변경은 뒤의 at @s 가 담당).
        return src.withEntity(e.getVehicle());
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

    /** on passengers — source 엔티티의 승객 목록(엔티티만 교체, 위치/회전 유지). */
    public static java.util.List<net.minecraft.server.command.ServerCommandSource> onPassengers(
            net.minecraft.server.command.ServerCommandSource src) {
        net.minecraft.entity.Entity e = (src == null ? null : src.getEntity());
        if (e == null) return java.util.List.of();
        java.util.List<net.minecraft.entity.Entity> ps = e.getPassengerList();
        if (ps.isEmpty()) return java.util.List.of();   // 승객 없는 엔티티(마커 등) — 할당 0
        java.util.List<net.minecraft.server.command.ServerCommandSource> out =
                new java.util.ArrayList<>(ps.size());
        for (net.minecraft.entity.Entity p : ps) {
            out.add(src.withEntity(p));
        }
        return out;
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
        if ("*".equals(holder)) { resetScoreWildcard(sb, o); return; }
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
    }

    public static void removeObjective(ServerScoreboard sb, String name) {
        net.minecraft.scoreboard.ScoreboardObjective o = sb.getNullableObjective(name);
        if (o != null) sb.removeObjective(o);
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

    /** 텍스트 컴포넌트 JSON → Text. 실패 시 null. */
    public static net.minecraft.text.Text parseText(net.minecraft.server.MinecraftServer server, String json) {
        try {
            return net.minecraft.text.Text.Serialization.fromJson(json, server.getRegistryManager());
        } catch (Exception ex) {
            return null;
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
            net.minecraft.text.Text cached = TEXT_CACHE.get(json);
            if (cached == null) {
                net.minecraft.command.CommandRegistryAccess cra =
                        net.minecraft.command.CommandRegistryAccess.of(
                                source.getRegistryManager(), source.getEnabledFeatures());
                cached = net.minecraft.command.argument.TextArgumentType.text(cra)
                        .parse(new com.mojang.brigadier.StringReader(json));
                TEXT_CACHE.put(json, cached);  // 컴포넌트 리터럴은 상수 — 파싱 1회면 충분 (resolve 는 매 호출)
            }
            return net.minecraft.text.Texts.parse(source, cached, sender, 0);
        } catch (Exception ex) {
            try {  // 폴백: 구 JSON 경로
                net.minecraft.text.Text raw = net.minecraft.text.Text.Serialization.fromJson(
                        json, source.getRegistryManager());
                if (raw == null) return null;
                return net.minecraft.text.Texts.parse(source, raw, sender, 0);
            } catch (Exception e2) {
                return null;
            }
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
        net.minecraft.util.math.Vec3d o = src.getPosition();
        double dx = tx - o.x, dy = ty - o.y, dz = tz - o.z;
        double horiz = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float) (net.minecraft.util.math.MathHelper.atan2(dz, dx) * 57.2957795 - 90.0);
        float pitch = (float) (-(net.minecraft.util.math.MathHelper.atan2(dy, horiz) * 57.2957795));
        return src.withRotation(new net.minecraft.util.math.Vec2f(pitch, yaw));
    }

    /** facing entity <e> <anchor> — eyes 면 눈높이, feet 면 발 위치를 향함. */
    public static net.minecraft.server.command.ServerCommandSource facingEntity(
            net.minecraft.server.command.ServerCommandSource src, net.minecraft.entity.Entity e, boolean eyes) {
        if (e == null) return src;
        net.minecraft.util.math.Vec3d t = eyes
                ? e.getEyePos()
                : e.getPos();
        return facing(src, t.x, t.y, t.z);
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
            if (e != null) world.spawnNewEntityAndPassengers(e);
        } catch (Exception ignored) {}
    }

    /** tp <e> <pos> — 엔티티를 좌표로 이동(회전 유지). */
    public static void teleportTo(net.minecraft.entity.Entity e, double x, double y, double z) {
        if (e == null) return;
        // 플레이어도 비-플레이어와 동일하게 바닐라 /tp 의 풀 teleport() 를 쓴다.
        // requestTeleport 는 dismount 직후 같은 틱 tp 시 하차 위치 보정에 덮어써져
        // (retire: stopRiding -> tp -17.5 가 카트 위치로 끌려감) 위치 복귀가 실패한다.
        e.teleport((net.minecraft.server.world.ServerWorld) e.getWorld(), x, y, z,
                java.util.Set.of(), e.getYaw(), e.getPitch(), true);
    }

    /** tp <대상> <좌표> <회전> — 위치+회전 설정. */
    public static void teleportToWithRot(net.minecraft.entity.Entity e, double x, double y, double z,
                                         float yaw, float pitch) {
        if (e == null) return;
        e.teleport((net.minecraft.server.world.ServerWorld) e.getWorld(), x, y, z,
                java.util.Set.of(), yaw, pitch, true);
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
        e.setYaw(yaw);
        e.setPitch(pitch);
        e.setHeadYaw(yaw);
        if (e instanceof net.minecraft.server.network.ServerPlayerEntity p) {
            p.networkHandler.requestTeleport(e.getX(), e.getY(), e.getZ(), yaw, pitch);
        }
    }

    /** tp <who> <destination-entity> — 대상 엔티티 위치+회전으로 텔레포트. */
    public static void teleportToEntity(net.minecraft.entity.Entity who, net.minecraft.entity.Entity dest) {
        if (who == null || dest == null) return;
        double x = dest.getX(), y = dest.getY(), z = dest.getZ();
        float yaw = dest.getYaw(), pitch = dest.getPitch();
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

    private static final java.util.concurrent.ConcurrentHashMap<String, java.util.Optional<net.minecraft.command.argument.ItemStackArgument>>
            ITEM_ARG_CACHE = new java.util.concurrent.ConcurrentHashMap<>();

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
            java.util.Optional<net.minecraft.registry.entry.RegistryEntry.Reference<net.minecraft.loot.LootTable>> entry =
                    source.getServer().getReloadableRegistries().createRegistryLookup()
                            .getOptionalEntry(net.minecraft.registry.RegistryKey.of(
                                    net.minecraft.registry.RegistryKeys.LOOT_TABLE,
                                    idOf(tableId)));
            if (entry.isEmpty()) return java.util.List.of();
            net.minecraft.loot.context.LootWorldContext lc =
                    new net.minecraft.loot.context.LootWorldContext.Builder(source.getWorld())
                            .addOptional(net.minecraft.loot.context.LootContextParameters.THIS_ENTITY, source.getEntity())
                            .add(net.minecraft.loot.context.LootContextParameters.ORIGIN, source.getPosition())
                            .build(net.minecraft.loot.context.LootContextTypes.CHEST);
            return entry.get().value().generateLoot(lc);
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
            world.spawnEntity(ie);
        }
    }

    /** target: replace entity <e> <slot> [count] — slot 베이스부터 count 칸. count<0 이면 loot.size(). */
    public static void lootReplaceEntity(net.minecraft.entity.Entity e, String slotName, int count,
                                         java.util.List<net.minecraft.item.ItemStack> loot) {
        if (e == null || loot == null) return;
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

    /** ride <who> mount <vehicle> / ride <who> dismount. */
    public static void rideMount(net.minecraft.entity.Entity who, net.minecraft.entity.Entity vehicle) {
        if (who == null || vehicle == null) return;
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
            net.minecraft.nbt.NbtCompound actual = new net.minecraft.nbt.NbtCompound();
            e.writeNbt(actual);
            return net.minecraft.nbt.NbtHelper.matches(expected, actual, true);
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
        try {
            net.minecraft.nbt.NbtCompound root = new net.minecraft.nbt.NbtCompound();
            e.writeNbt(root);
            String[] parts = path.split("\\.");
            net.minecraft.nbt.NbtCompound cur = root;
            for (int i = 0; i < parts.length - 1; i++) {
                cur = cur.getCompound(parts[i]).orElse(null);
                if (cur == null) return false;
            }
            return cur.contains(parts[parts.length - 1]);
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
        if (e instanceof net.minecraft.server.network.ServerPlayerEntity p) {
            p.networkHandler.requestTeleport(x, y, z, p.getYaw(), p.getPitch());
        } else {
            e.updatePosition(x, y, z);
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
                    manager.getFunction(functionId).orElse(null);
            if (fn == null) { System.err.println("[KFC-BRIDGE-ERROR] function not loaded: " + functionId); return 0; }
            net.minecraft.nbt.NbtCompound nbt = null;
            if (macroArgs != null && !macroArgs.isEmpty()) {
                nbt = new net.minecraft.nbt.NbtCompound();
                for (java.util.Map.Entry<String, String> e : macroArgs.entrySet())
                    nbt.putString(e.getKey(), e.getValue());
            }
            net.minecraft.server.function.Procedure<net.minecraft.server.command.ServerCommandSource> procedure =
                    fn.withMacroReplaced(nbt, manager.getDispatcher());
            net.minecraft.command.ReturnValueConsumer consumer = new net.minecraft.command.ReturnValueConsumer() {
                public void onResult(boolean success, int value) { ret[0] = value; }
            };
            net.minecraft.server.command.CommandManager.callWithContext(source, (context) -> {
                net.minecraft.command.CommandExecutionContext.enqueueProcedureCall(
                        context, procedure, source, consumer);
                context.run();
            });
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
                    manager.getFunction(functionId).orElse(null);
            if (fn == null) { System.err.println("[KFC-BRIDGE-ERROR] function not loaded: " + functionId); return; }
            net.minecraft.nbt.NbtCompound nbt = null;
            if (macroArgs != null && !macroArgs.isEmpty()) {
                nbt = new net.minecraft.nbt.NbtCompound();
                for (java.util.Map.Entry<String, String> e : macroArgs.entrySet()) {
                    nbt.putString(e.getKey(), e.getValue());
                }
            }
            net.minecraft.server.function.Procedure<net.minecraft.server.command.ServerCommandSource> procedure =
                    fn.withMacroReplaced(nbt, manager.getDispatcher());
            net.minecraft.server.command.CommandManager.callWithContext(source, (context) -> {
                net.minecraft.command.CommandExecutionContext.enqueueProcedureCall(
                        context, procedure, source,
                        net.minecraft.command.ReturnValueConsumer.EMPTY);
                context.run();
            });
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

    /** 공개 스코어 읽기 (store←scoreboard get 등에서 사용). 없으면 0. */
    public static int getScore(ServerScoreboard sb, String holder, String o) {
        return readOrZero(sb, holder, o);
    }

    /** 엔티티의 스코어보드 이름으로 점수 조회 (null/미설정이면 0). */
    public static int getScoreOfEntity(ServerScoreboard sb, net.minecraft.entity.Entity e, String o) {
        if (e == null) return 0;
        return readOrZero(sb, e.getNameForScoreboard(), o);
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

    /** if score <a> OP <b> (비교형). 둘 중 하나라도 값 없으면 false(=mcfunction 시맨틱). */
    public static boolean scoreCmp(ServerScoreboard sb, String ha, String oa,
                                   String op, String hb, String ob) {
        Integer a = read(sb, ha, oa), b = read(sb, hb, ob);
        if (DBG_SCORECMP) System.out.println("[KFC-CMP] " + ha + "." + oa + "(" + a + ") "
                + op + " " + hb + "." + ob + "(" + b + ")");
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
        return scoreCmp(sb, ea.getNameForScoreboard(), oa, op, hb, ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, String ha, String oa,
                                   String op, net.minecraft.entity.Entity eb, String ob) {
        if (eb == null) return false;
        return scoreCmp(sb, ha, oa, op, eb.getNameForScoreboard(), ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                   String op, net.minecraft.entity.Entity eb, String ob) {
        if (ea == null || eb == null) return false;
        return scoreCmp(sb, ea.getNameForScoreboard(), oa, op, eb.getNameForScoreboard(), ob);
    }

    // neg(=unless) 인자판: 셀렉터 홀더가 비면 false(미실행), neg 는 비교 결과에만 적용.
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                   String op, String hb, String ob, boolean neg) {
        if (ea == null) return false;
        return neg ^ scoreCmp(sb, ea.getNameForScoreboard(), oa, op, hb, ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, String ha, String oa,
                                   String op, net.minecraft.entity.Entity eb, String ob, boolean neg) {
        if (eb == null) return false;
        return neg ^ scoreCmp(sb, ha, oa, op, eb.getNameForScoreboard(), ob);
    }
    public static boolean scoreCmp(ServerScoreboard sb, net.minecraft.entity.Entity ea, String oa,
                                   String op, net.minecraft.entity.Entity eb, String ob, boolean neg) {
        if (DBG_SCORECMP) System.out.println("[KFC-CMP-E] ea=" + (ea==null?"null":ea.getNameForScoreboard()+"@"+ea.getPos())
                + " eb=" + (eb==null?"null":eb.getNameForScoreboard()+"@"+eb.getPos()));
        if (ea == null || eb == null) return false;
        return neg ^ scoreCmp(sb, ea.getNameForScoreboard(), oa, op, eb.getNameForScoreboard(), ob);
    }

    // ──────────────── if entity <selector> 존재 검사 ────────────────
    private static boolean matchTags(net.minecraft.entity.Entity e, String[] pos, String[] neg) {
        for (String t : pos) if (!e.getCommandTags().contains(t)) return false;
        for (String t : neg) if (e.getCommandTags().contains(t)) return false;
        return true;
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
        if (origin != null && maxDist >= 0) {
            return !ctx.world.getEntitiesByType(type, rangeBox(origin, maxDist),
                    en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)).isEmpty();
        }
        return !ctx.world.getEntitiesByType(type,
                en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)).isEmpty();
    }

    public static boolean anyEntityAnyType(GameContext ctx, net.minecraft.util.math.Vec3d origin,
                                           String[] tagsPos, String[] tagsNeg,
                                           double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            return !ctx.world.getOtherEntities(null, rangeBox(origin, maxDist),
                    en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)).isEmpty();
        }
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (!matchTags(e, tagsPos, tagsNeg)) continue;
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
            return !ctx.world.getOtherEntities(null, rangeBox(origin, maxDist),
                    en -> entityInTypeTag(en, tagId) && matchTags(en, tagsPos, tagsNeg)
                          && inRange(origin, en, minDist, maxDist)).isEmpty();
        }
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (!entityInTypeTag(e, tagId)) continue;
            if (!matchTags(e, tagsPos, tagsNeg)) continue;
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
        for (net.minecraft.entity.EntityType<?> t : types) {
            java.util.List<? extends net.minecraft.entity.Entity> found =
                    (origin != null && maxDist >= 0)
                    ? ctx.world.getEntitiesByType(t, rangeBox(origin, maxDist),
                            en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist))
                    : ctx.world.getEntitiesByType(t,
                            en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
            for (net.minecraft.entity.Entity e : found) {
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
            for (net.minecraft.entity.Entity e : ctx.world.getOtherEntities(null, rangeBox(origin, maxDist),
                    en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist))) {
                double d = e.getPos().squaredDistanceTo(origin);
                if (d < bestD) { bestD = d; best = e; }
            }
            return best;
        }
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (!matchTags(e, tagsPos, tagsNeg)) continue;
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

    public static java.util.List<net.minecraft.entity.Entity> allEntities(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.EntityType<?> t : types) {
            if (origin != null && maxDist >= 0) {
                out.addAll(ctx.world.getEntitiesByType(t, rangeBox(origin, maxDist),
                        en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)));
            } else {
                out.addAll(ctx.world.getEntitiesByType(t,
                        en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)));
            }
        }
        return out;
    }

    /** 소스 위치 기준 최근접 N개 (limit=N + sort=nearest / @n 다중). */
    public static java.util.List<net.minecraft.entity.Entity> nearestN(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist, int n) {
        java.util.List<net.minecraft.entity.Entity> all =
                allEntities(ctx, origin, types, tagsPos, tagsNeg, minDist, maxDist);
        if (origin != null) {
            all.sort(java.util.Comparator.comparingDouble(e -> e.getPos().squaredDistanceTo(origin)));
        }
        return all.size() <= n ? all : all.subList(0, n);
    }

    public static java.util.List<net.minecraft.entity.Entity> allEntitiesAnyType(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            return ctx.world.getOtherEntities(null, rangeBox(origin, maxDist),
                    en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
        }
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (!matchTags(e, tagsPos, tagsNeg)) continue;
            if (!inRange(origin, e, minDist, maxDist)) continue;
            out.add(e);
        }
        return out;
    }

    /** 타입 미지정 @e[limit=N] (sort 미지정=nearest 기본) — origin 에서 가까운 순 N개. */
    public static java.util.List<net.minecraft.entity.Entity> nearestNAnyType(
            GameContext ctx, net.minecraft.util.math.Vec3d origin,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist, int limit) {
        java.util.List<net.minecraft.entity.Entity> all =
                allEntitiesAnyType(ctx, origin, tagsPos, tagsNeg, minDist, maxDist);
        if (origin != null && all.size() > 1) {
            all.sort(java.util.Comparator.comparingDouble(e -> e.squaredDistanceTo(origin)));
        }
        if (limit >= 0 && all.size() > limit) {
            return new java.util.ArrayList<>(all.subList(0, limit));
        }
        return all;
    }

    /** if entity @e/@n[type,tag,distance] — 조건에 맞는 엔티티가 하나라도 있으면 true. */
    public static boolean anyEntity(GameContext ctx, net.minecraft.entity.Entity origin,
                                    net.minecraft.entity.EntityType<?> type,
                                    String[] tagsPos, String[] tagsNeg,
                                    double minDist, double maxDist) {
        if (origin != null && maxDist >= 0) {
            return !ctx.world.getEntitiesByType(type, rangeBox(origin.getPos(), maxDist),
                    en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)).isEmpty();
        }
        return !ctx.world.getEntitiesByType(type,
                en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)).isEmpty();
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
        int a = da.getScore(), b = sa.getScore();
        int r;
        switch (op) {
            case "=":  r = b; break;
            case "+=": r = a + b; break;
            case "-=": r = a - b; break;
            case "*=": r = a * b; break;
            case "/=": if (b == 0) return; r = Math.floorDiv(a, b); break;   // 0나눗셈: 실패(생성은 유지)
            case "%=": if (b == 0) return; r = Math.floorMod(a, b); break;
            case "<":  r = Math.min(a, b); break;
            case ">":  r = Math.max(a, b); break;
            case "><": da.setScore(b); sa.setScore(a); return;
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
        for (net.minecraft.entity.EntityType<?> t : types) {
            java.util.List<? extends net.minecraft.entity.Entity> found =
                    (origin != null && maxDist >= 0)
                    ? ctx.world.getEntitiesByType(t, rangeBox(origin.getPos(), maxDist),
                            en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist))
                    : ctx.world.getEntitiesByType(t,
                            en -> matchTags(en, tagsPos, tagsNeg)
                                  && (origin == null || inRange(origin, en, minDist, maxDist)));
            for (net.minecraft.entity.Entity e : found) {
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
    private static final java.util.concurrent.ConcurrentHashMap<String, net.minecraft.registry.tag.TagKey<net.minecraft.item.Item>>
            ITEM_TAG_CACHE = new java.util.concurrent.ConcurrentHashMap<>();

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
                net.minecraft.nbt.NbtCompound expected =
                        net.minecraft.nbt.StringNbtReader.readCompound(customDataSnbt);
                if (!net.minecraft.nbt.NbtHelper.matches(expected, nc.copyNbt(), true)) return false;
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

    public static boolean entityScoreMatches(ServerScoreboard sb, net.minecraft.entity.Entity e,
                                             String o, Integer min, Integer max) {
        if (e == null) return false;
        return scoreMatches(sb, e.getNameForScoreboard(), o, min, max);
    }

    /** 셀렉터 홀더 score 조건(if/unless). 셀렉터가 비면(e==null) 바닐라에선 "No entity" 에러로
     *  명령 전체가 미실행 → if/unless 무관하게 false 반환. neg(=unless)는 점수 비교에만 적용. */
    public static boolean entityScoreMatches(ServerScoreboard sb, net.minecraft.entity.Entity e,
                                             String o, Integer min, Integer max, boolean neg) {
        if (e == null) return false;
        boolean m = scoreMatches(sb, e.getNameForScoreboard(), o, min, max);
        return neg ? !m : m;
    }

    /** 조건에 맞는 모든 엔티티(셀렉터 @e[...] 무제한). set/add/remove 를 전체에 적용할 때 사용. */
    public static java.util.List<net.minecraft.entity.Entity> allEntities(
            GameContext ctx, net.minecraft.entity.Entity origin,
            net.minecraft.entity.EntityType<?>[] types,
            String[] tagsPos, String[] tagsNeg, double minDist, double maxDist) {
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.EntityType<?> t : types) {
            if (origin != null && maxDist >= 0) {
                out.addAll(ctx.world.getEntitiesByType(t, rangeBox(origin.getPos(), maxDist),
                        en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist)));
            } else {
                out.addAll(ctx.world.getEntitiesByType(t,
                        en -> matchTags(en, tagsPos, tagsNeg)
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
            return ctx.world.getOtherEntities(null, rangeBox(origin.getPos(), maxDist),
                    en -> matchTags(en, tagsPos, tagsNeg) && inRange(origin, en, minDist, maxDist));
        }
        java.util.List<net.minecraft.entity.Entity> out = new java.util.ArrayList<>();
        for (net.minecraft.entity.Entity e : ctx.world.iterateEntities()) {
            if (!matchTags(e, tagsPos, tagsNeg)) continue;
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
        try { return net.minecraft.command.argument.NbtPathArgumentType.NbtPath.parse(s); }
        catch (Exception e) { return null; }
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

    // ── source 읽기 ──
    public static net.minecraft.nbt.NbtElement nbtGetEntity(net.minecraft.entity.Entity e, String path) {
        if (e == null) return null;
        String pt0 = path.replace(" ", "");
        net.minecraft.nbt.NbtElement lf = liveFieldNbt(e, pt0);   // Pos/Rotation/Motion 라이브 게터 fast-path
        if (lf != null) return lf;
        net.minecraft.nbt.NbtElement f = displayGetFast(e, pt0);
        if (f != null) return f;
        net.minecraft.nbt.NbtCompound n = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(n);
        return getAtPath(n, path);
    }

    /** writeNbt 라운드트립 없이 라이브 게터로 동일한 NbtElement 를 구성하는 fast-path.
     *  entityGetDouble 이 이미 신뢰하는 게터(getPos/getYaw/getPitch/getVelocity)와 동일 의미.
     *  writeNbt 포맷: Pos=NbtList&lt;Double&gt;[x,y,z], Rotation=NbtList&lt;Float&gt;[yaw,pitch],
     *  Motion=NbtList&lt;Double&gt;[vx,vy,vz]. 모르는 경로는 null → 호출부가 폴백. */
    private static net.minecraft.nbt.NbtElement liveFieldNbt(net.minecraft.entity.Entity e, String pt) {
        switch (pt) {
            case "Pos[0]": return net.minecraft.nbt.NbtDouble.of(e.getPos().x);
            case "Pos[1]": return net.minecraft.nbt.NbtDouble.of(e.getPos().y);
            case "Pos[2]": return net.minecraft.nbt.NbtDouble.of(e.getPos().z);
            case "Rotation[0]": return net.minecraft.nbt.NbtFloat.of(e.getYaw());
            case "Rotation[1]": return net.minecraft.nbt.NbtFloat.of(e.getPitch());
            case "Motion[0]": return net.minecraft.nbt.NbtDouble.of(e.getVelocity().x);
            case "Motion[1]": return net.minecraft.nbt.NbtDouble.of(e.getVelocity().y);
            case "Motion[2]": return net.minecraft.nbt.NbtDouble.of(e.getVelocity().z);
            default: break;
        }
        if (pt.equals("Pos")) {
            net.minecraft.util.math.Vec3d p = e.getPos();
            net.minecraft.nbt.NbtList l = new net.minecraft.nbt.NbtList();
            l.add(net.minecraft.nbt.NbtDouble.of(p.x));
            l.add(net.minecraft.nbt.NbtDouble.of(p.y));
            l.add(net.minecraft.nbt.NbtDouble.of(p.z));
            return l;
        }
        if (pt.equals("Rotation")) {
            net.minecraft.nbt.NbtList l = new net.minecraft.nbt.NbtList();
            l.add(net.minecraft.nbt.NbtFloat.of(e.getYaw()));
            l.add(net.minecraft.nbt.NbtFloat.of(e.getPitch()));
            return l;
        }
        if (pt.equals("Motion")) {
            net.minecraft.util.math.Vec3d v = e.getVelocity();
            net.minecraft.nbt.NbtList l = new net.minecraft.nbt.NbtList();
            l.add(net.minecraft.nbt.NbtDouble.of(v.x));
            l.add(net.minecraft.nbt.NbtDouble.of(v.y));
            l.add(net.minecraft.nbt.NbtDouble.of(v.z));
            return l;
        }
        return null;
    }

    public static net.minecraft.nbt.NbtElement nbtGetStorage(net.minecraft.server.MinecraftServer server, String id, String path) {
        return getAtPath(storageRoot(server, id), path);
    }

    /** if data storage <id> <path> — 경로 존재 검사. NbtPath 라 리스트 인덱스(foo[0]) 지원. */
    public static boolean storageHasPath(net.minecraft.server.MinecraftServer server, String id, String path) {
        return nbtGetStorage(server, id, path) != null;
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
    private static boolean displaySetFast(net.minecraft.entity.Entity e, String path,
                                          net.minecraft.nbt.NbtElement v) {
        if (!(e instanceof net.minecraft.entity.decoration.DisplayEntity d)) return false;
        switch (path) {
            case "transformation": {
                java.util.Optional<net.minecraft.util.math.AffineTransformation> r =
                        net.minecraft.util.math.AffineTransformation.ANY_CODEC
                                .parse(net.minecraft.nbt.NbtOps.INSTANCE, v).result();
                if (r.isPresent()) {
                    d.setTransformation(r.get());
                    // 바닐라 /data modify 는 readNbt 를 거쳐 setStartInterpolation(force=true)을
                    // 호출하므로 보간이 매번 재트리거된다. setTransformation 만으로는
                    // START_INTERPOLATION 트래커가 갱신되지 않아(값 동일 시 dirty 스킵) 클라가
                    // 보간을 시작하지 않는다. 현재 start_interpolation 값으로 강제 재설정해 트리거.
                    d.setStartInterpolation(d.getStartInterpolation());
                    return true;
                }
                return false;
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
        if (displaySetFast(e, pt, v)) return;
        net.minecraft.nbt.NbtCompound n = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(n);
        if (putAtPath(n, path, v)) e.readNbt(n);
    }

    public static void nbtSetStorage(net.minecraft.server.MinecraftServer server, String id, String path,
                                     net.minecraft.nbt.NbtElement v) {
        if (v == null) return;
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        if (root == null) root = new net.minecraft.nbt.NbtCompound();
        if (putAtPath(root, path, v)) storageSave(server, id, root);
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

    public static void nbtAppendEntity(net.minecraft.entity.Entity e, String path,
                                       net.minecraft.nbt.NbtElement elem, boolean prepend) {
        if (e == null || elem == null) return;
        net.minecraft.nbt.NbtCompound root = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(root);
        if (appendAtPath(root, path, elem, prepend)) e.readNbt(root);
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
            float y = (float) value;
            e.setYaw(y); e.setHeadYaw(y); e.setBodyYaw(y);
            return;
        }
        if (path.equals("Rotation[1]")) {
            e.setPitch((float) value);
            return;
        }
        if (displaySetFast(e, path.replace(" ", ""), numberNbt(type, value))) return;
        net.minecraft.nbt.NbtCompound n = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(n);
        if (putAtPath(n, path, numberNbt(type, value))) e.readNbt(n);
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
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        Object[] d = descend(root, path, false);
        if (d == null) return 0;
        net.minecraft.nbt.NbtCompound parent = (net.minecraft.nbt.NbtCompound) d[0];
        return parent.getInt((String) d[1], 0);
    }

    /** NbtElement → double(숫자형 아니면 0). */
    private static double nbtNum(net.minecraft.nbt.NbtElement el) {
        return (el instanceof net.minecraft.nbt.AbstractNbtNumber n) ? n.doubleValue() : 0.0;
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
        if (pt.startsWith("transformation")) {
            net.minecraft.nbt.NbtElement f = displayGetFast(e, pt);
            if (f != null) return nbtNum(f);
        }
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(nbt);
        return nbtNum(getAtPath(nbt, path));
    }
    public static double storageGetDouble(net.minecraft.server.MinecraftServer server, String id, String path) {
        return nbtNum(getAtPath(storageRoot(server, id), path));
    }

    public static void storagePutNumber(net.minecraft.server.MinecraftServer server, String id,
                                        String path, double value, String type) {
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        Object[] d = descend(root, path, true);
        net.minecraft.nbt.NbtCompound parent = (net.minecraft.nbt.NbtCompound) d[0];
        String key = (String) d[1];
        switch (type) {
            case "float":  parent.putFloat(key, (float) value); break;
            case "double": parent.putDouble(key, value); break;
            case "byte":   parent.putByte(key, (byte) value); break;
            case "short":  parent.putShort(key, (short) value); break;
            case "long":   parent.putLong(key, (long) value); break;
            default:       parent.putInt(key, (int) value); break;
        }
        storageSave(server, id, root);
    }

    /** 임의 SNBT 값을 경로에 기록. mode: set | append | prepend | merge.
     *  값 파싱은 `{v:<snbt>}` 로 감싸 readCompound — 리스트/문자열/컴파운드/숫자 전부 합법. */
    public static void storagePutSnbt(net.minecraft.server.MinecraftServer server, String id,
                                      String path, String snbt, String mode) {
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
            Object[] d = descend(root, path, true);
            net.minecraft.nbt.NbtCompound parent = (net.minecraft.nbt.NbtCompound) d[0];
            String key = (String) d[1];
            switch (mode) {
                case "append", "prepend" -> {
                    net.minecraft.nbt.NbtElement cur = parent.get(key);
                    net.minecraft.nbt.NbtList list =
                            (cur instanceof net.minecraft.nbt.NbtList l) ? l : new net.minecraft.nbt.NbtList();
                    if ("prepend".equals(mode)) list.add(0, val); else list.add(val);
                    parent.put(key, list);
                }
                case "merge" -> {
                    net.minecraft.nbt.NbtElement cur = parent.get(key);
                    if (cur instanceof net.minecraft.nbt.NbtCompound cc
                            && val instanceof net.minecraft.nbt.NbtCompound vc) {
                        cc.copyFrom(vc);
                    } else {
                        parent.put(key, val);
                    }
                }
                default -> parent.put(key, val);   // set
            }
            storageSave(server, id, root);
        } catch (Exception ignored) {}
    }

    /** data merge storage <id> {snbt} — 컴파운드를 스토리지 루트에 깊은 병합. */
    public static void storageMergeSnbt(net.minecraft.server.MinecraftServer server, String id, String snbt) {
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
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        Object[] d = descend(root, path, false);
        if (d == null) return;
        ((net.minecraft.nbt.NbtCompound) d[0]).remove((String) d[1]);
        storageSave(server, id, root);
    }

    // ──────────────── entity NBT (data ... entity @s <path>) ────────────────
    // 엔티티 NBT 는 writeNbt 로 스냅샷 → 수정 → readNbt 로 반영. 비싸므로 gated.

    public static int entityGetNumber(net.minecraft.entity.Entity e, String path) {
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(nbt);
        Object[] d = descend(nbt, path, false);
        if (d == null) return 0;
        return ((net.minecraft.nbt.NbtCompound) d[0]).getInt((String) d[1], 0);
    }

    /** null-safe 버전 — 셀렉터 대상이 없으면 0. */
    public static int entityGetNumberOf(net.minecraft.entity.Entity e, String path) {
        if (e == null) return 0;
        return entityGetNumber(e, path);
    }

    public static void entityPutNumber(net.minecraft.entity.Entity e, String path, double value) {
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(nbt);
        Object[] d = descend(nbt, path, true);
        ((net.minecraft.nbt.NbtCompound) d[0]).putInt((String) d[1], (int) value);
        e.readNbt(nbt);
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
            if (displaySetFast(e, path.replace(" ", ""), val)) return;
            net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
            e.writeNbt(nbt);
            Object[] d = descend(nbt, path, true);
            ((net.minecraft.nbt.NbtCompound) d[0]).put((String) d[1], val);
            e.readNbt(nbt);
        } catch (Exception ignored) {}
    }

    /** data merge entity <e> {snbt} — 컴파운드를 엔티티 NBT 에 깊은 병합(/data merge 시맨틱). */
    /** display fast 머지가 가능한 키들 — 전부 직접 setter 가 있는 DataTracker 필드. */
    private static boolean displayMergeFast(net.minecraft.entity.Entity e,
                                            net.minecraft.nbt.NbtCompound patch) {
        if (!(e instanceof net.minecraft.entity.decoration.DisplayEntity)) return false;
        for (String k : patch.getKeys()) {
            switch (k) {
                case "transformation": case "interpolation_duration":
                case "start_interpolation": case "teleport_duration": break;
                default: return false;   // fast 불가 키 혼재 → 전체 폴백
            }
        }
        for (String k : patch.getKeys()) {
            if (!displaySetFast(e, k, patch.get(k))) return false;
        }
        return true;
    }

    public static void entityMergeSnbt(net.minecraft.entity.Entity e, String snbt) {
        if (e == null) return;
        try {
            // SNBT 리터럴은 변환 시점 상수 — 캐시된 템플릿을 copy (storagePutSnbt 와 동일 패턴).
            net.minecraft.nbt.NbtElement tmpl = SNBT_CACHE.computeIfAbsent(snbt, s -> {
                try { return net.minecraft.nbt.StringNbtReader.readCompound(s); }
                catch (Exception ex) { return SNBT_INVALID; }
            });
            if (tmpl == SNBT_INVALID || !(tmpl instanceof net.minecraft.nbt.NbtCompound)) return;
            net.minecraft.nbt.NbtCompound patch = ((net.minecraft.nbt.NbtCompound) tmpl).copy();
            // run-anime/boost 의 매 틱 보간 머지({start_interpolation,interpolation_duration} 등)는
            // 엔티티 전체 writeNbt/readNbt 없이 직접 setter 로 — 핵심 핫패스.
            if (displayMergeFast(e, patch)) return;
            net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
            e.writeNbt(nbt);
            nbt.copyFrom(patch);
            e.readNbt(nbt);
        } catch (Exception ignored) {}
    }

    public static void entityRemovePath(net.minecraft.entity.Entity e, String path) {
        // DisplayEntity 의 brightness 는 DataTracker 필드 — writeNbt/readNbt 폴백으로는
        // 트래커가 갱신되지 않아 라이트가 꺼지지 않는다. setBrightness(null) 로 직접 제거(기본값 복귀).
        if ("brightness".equals(path.replace(" ", ""))
                && e instanceof net.minecraft.entity.decoration.DisplayEntity d) {
            d.setBrightness(null);
            return;
        }
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(nbt);
        Object[] d = descend(nbt, path, false);
        if (d == null) return;
        ((net.minecraft.nbt.NbtCompound) d[0]).remove((String) d[1]);
        e.readNbt(nbt);
    }

    // ──────────────── 매크로 인자 (function X with .../{...}) ────────────────
    // mcfunction 매크로: $(var) 는 NBT 값의 문자열 표현으로 치환된다.
    //  - NbtString  → 따옴표 없는 원문(value())
    //  - 그 외(숫자/컴파운드/리스트) → SNBT(toString())
    // 호출부에서 만든 Map<String,String> 을 피호출 함수의 execute(source, macroArgs) 로 넘긴다.

    /** 가변 인자 (k1,v1,k2,v2,...) → Map. 인라인 {compound} 호출에서 사용. */
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

    private static java.util.Map<String, String> compoundToMacroArgs(net.minecraft.nbt.NbtCompound c) {
        java.util.Map<String, String> m = new java.util.HashMap<>();
        if (c == null) return m;
        for (String k : c.getKeys()) m.put(k, nbtToMacroString(c.get(k)));
        return m;
    }

    /** function X with storage <id> — 스토리지 루트 컴파운드의 각 필드 → 매크로 인자. */
    public static java.util.Map<String, String> storageMacroArgs(
            net.minecraft.server.MinecraftServer server, String id) {
        return compoundToMacroArgs(storageRoot(server, id));
    }

    /** function X with storage <id> <path> — 경로 하위 컴파운드 → 매크로 인자. */
    public static java.util.Map<String, String> storageMacroArgs(
            net.minecraft.server.MinecraftServer server, String id, String path) {
        net.minecraft.nbt.NbtCompound root = storageRoot(server, id);
        return compoundToMacroArgs(compoundAt(root, path));
    }

    /** function X with entity @s <path> — 엔티티 NBT 경로 하위 컴파운드 → 매크로 인자. */
    public static java.util.Map<String, String> entityMacroArgs(
            net.minecraft.entity.Entity e, String path) {
        if (e == null) return new java.util.HashMap<>();
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        e.writeNbt(nbt);
        if (path == null || path.isEmpty()) return compoundToMacroArgs(nbt);
        return compoundToMacroArgs(compoundAt(nbt, path));
    }

    /** function X with block <pos> [<path>] — 블록 엔티티 NBT → 매크로 인자. */
    public static java.util.Map<String, String> blockMacroArgs(
            net.minecraft.server.world.ServerWorld world,
            net.minecraft.util.math.BlockPos pos, String path) {
        net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
        if (be == null) return new java.util.HashMap<>();
        net.minecraft.nbt.NbtCompound nbt = be.createNbt(world.getRegistryManager());
        if (path == null || path.isEmpty()) return compoundToMacroArgs(nbt);
        return compoundToMacroArgs(compoundAt(nbt, path));
    }

    /** 점 표기 경로의 컴파운드를 반환(없으면 null). 마지막 키가 가리키는 값이 컴파운드여야 함. */
    private static net.minecraft.nbt.NbtCompound compoundAt(net.minecraft.nbt.NbtCompound root, String path) {
        if (root == null) return null;
        Object[] d = descend(root, path, false);
        if (d == null) return null;
        net.minecraft.nbt.NbtElement leaf = ((net.minecraft.nbt.NbtCompound) d[0]).get((String) d[1]);
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

    /** 텍스트 컴포넌트(SNBT풍 lenient JSON) → Text. 실패 시 literal 폴백. */
    private static net.minecraft.text.Text bbText(
            net.minecraft.server.command.ServerCommandSource src, String json, String fallback) {
        try {
            net.minecraft.text.Text t = net.minecraft.text.Text.Serialization
                    .fromLenientJson(json, src.getRegistryManager());
            if (t != null) return t;
        } catch (Exception ignored) {}
        return net.minecraft.text.Text.literal(fallback);
    }

    /** bossbar add: 이미 있으면 그대로 반환(중복 add 무시 — load 1회 안전). */
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
        if (b != null) mgr.remove(b);
    }
    public static void bossbarSetName(net.minecraft.server.command.ServerCommandSource src, String idStr, String nameJson) {
        net.minecraft.entity.boss.CommandBossBar b = bossbarGet(src, idStr);
        if (b != null) b.setName(bbText(src, nameJson, idStr));
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