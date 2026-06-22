package tdpack.buckets;

import tdpack.generated.KfcGen;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/** Auto-bucketed: 77 datapack functions. */
public final class Bucket0 {
    private Bucket0() { throw new UnsupportedOperationException(); }

    public static void _m_b092a33d_api_load_execute(ServerCommandSource source) {
        _m_b092a33d_api_load_executeReturn(source);
    }

    public static int _m_b092a33d_api_load_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard objectives add player.rotation dummy
        KfcGen.ensureObjective(sb, "player.rotation", "dummy");

        // function api:map/data/load
        // -> api:map/data/load
        tdpack.buckets.Bucket0._m_33a3dfe6_api_map_data_load_execute(source);
        return 0;
    }

    public static void _m_3121f975_api_tick_execute(ServerCommandSource source) {
        _m_3121f975_api_tick_executeReturn(source);
    }

    public static int _m_3121f975_api_tick_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute as @a[tag=player.map_modify] at @s run function api:map/checkpoint/main with entity @s equipment.offhand.components.minecraft:custom_data
        for (ServerPlayerEntity e : ctx.allPlayers) {
            if (!(e.getCommandTags().contains("player.map_modify"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc1 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> api:map/checkpoint/main
            tdpack.buckets.Bucket0._m_7e2df4f8_api_map_checkpoint_main_execute(kfcSrc1);
        }

        // execute as @a[tag=player.map_modify] at @s run function api:map/checkpoint/path/main with storage player temp.components.minecraft:custom_data
        for (ServerPlayerEntity e : ctx.allPlayers) {
            if (!(e.getCommandTags().contains("player.map_modify"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc2 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> api:map/checkpoint/path/main
            tdpack.buckets.Bucket0._m_513c89ef_api_map_checkpoint_path_main_execute(kfcSrc2);
        }
        return 0;
    }

    public static void _m_dcd1441b_api_enemy_spawn_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_dcd1441b_api_enemy_spawn_executeReturn(source, macroArgs);
    }

    public static int _m_dcd1441b_api_enemy_spawn_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/2줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("api", "enemy/spawn"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function enemy:spawn/model/$(map)/$(difficulty)/$(model)
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // execute as @e[tag=enemy.data] unless entity @s[scores={enemy.number=0..}] run function enemy:spawn/summon/allocate
                // for (Entity e : ctx.world.iterateEntities()) {
                //     if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                //     Entity en = e; if (!(en.getCommandTags().contains("enemy.data"))) continue;
                //     ServerCommandSource es = source.withEntity(e);
                //     if (!(!((e != null && KfcGen.scoreMatches(sb, e.getNameForScoreboard(), "enemy.number", 0, Integer.MAX_VALUE))))) continue;
                //     // -> enemy:spawn/summon/allocate
                //     tdpack.buckets.Bucket6._m_3309be71_enemy_spawn_summon_allocate_execute(es);
                // }
        return 0;
    }

    public static void _m_7e2df4f8_api_map_checkpoint_main_execute(ServerCommandSource source) {
        _m_7e2df4f8_api_map_checkpoint_main_executeReturn(source);
    }

    public static int _m_7e2df4f8_api_map_checkpoint_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage player temp set from entity @s equipment.offhand
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "equipment.offhand"); if (_v != null) KfcGen.nbtSetStorage(server, "player", "temp", _v); }

        // execute if data storage player temp.components.minecraft:custom_data.map run function api:map/checkpoint/use_checkpoint/main with storage player temp.components.minecraft:custom_data
        if (KfcGen.storageHasPath(source.getServer(), "player", "temp.components.minecraft:custom_data.map")) {
            // -> api:map/checkpoint/use_checkpoint/main
            tdpack.buckets.Bucket0._m_61434502_api_map_checkpoint_use_checkpoint_main_execute(source, KfcGen.storageMacroArgs(server, "player", "temp.components.minecraft:custom_data"));
        }
        return 0;
    }

    public static void _m_b2d69a20_api_map_checkpoint_active_active_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_b2d69a20_api_map_checkpoint_active_active_executeReturn(source, macroArgs);
    }

    public static int _m_b2d69a20_api_map_checkpoint_active_active_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        // $tag @s add player.map_modify.$(map)
        if (executor != null) executor.addCommandTag("player.map_modify." + macroArgs.get("map"));

        // tag @s add player.map_modify
        if (executor != null) executor.addCommandTag("player.map_modify");

        // $function api:map/checkpoint/active/get_item {map:$(map)}
        // -> api:map/checkpoint/active/get_item
        if (!KfcGen.macroEmpty(macroArgs.get("map"))) tdpack.buckets.Bucket0._m_7de7bc58_api_map_checkpoint_active_get_item_execute(source, KfcGen.macroArgs("map", macroArgs.get("map")));
        return 0;
    }

    public static void _m_7de7bc58_api_map_checkpoint_active_get_item_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_7de7bc58_api_map_checkpoint_active_get_item_executeReturn(source, macroArgs);
    }

    public static int _m_7de7bc58_api_map_checkpoint_active_get_item_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // $give @s emerald_block[item_name=[{text:"[ 적 스폰 지점 ]",color:green,bold:true},{text:" 맵: ",color:gray,bold:false},{text:"$(map)",color:yellow,bold:false}],custom_data={map:"$(map)",type:"spawn"}]
        if (executor != null) { if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "emerald_block[item_name=[{text:\"[ 적 스폰 지점 ]\",color:green,bold:true},{text:\" 맵: \",color:gray,bold:false},{text:\"" + macroArgs.get("map") + "\",color:yellow,bold:false}],custom_data={map:\"" + macroArgs.get("map") + "\",type:\"spawn\"}]", 1); }

        // $give @s diamond_block[item_name=[{text:"[ 적 체크포인트 ]",color:aqua,bold:true},{text:" 맵: ",color:gray,bold:false},{text:"$(map)",color:yellow,bold:false}],custom_data={map:"$(map)",type:"check"}]
        if (executor != null) { if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "diamond_block[item_name=[{text:\"[ 적 체크포인트 ]\",color:aqua,bold:true},{text:\" 맵: \",color:gray,bold:false},{text:\"" + macroArgs.get("map") + "\",color:yellow,bold:false}],custom_data={map:\"" + macroArgs.get("map") + "\",type:\"check\"}]", 1); }

        // $give @s black_concrete[item_name=[{text:"[ 적 도착 지점 ]",color:gray,bold:true},{text:" 맵: ",color:gray,bold:false},{text:"$(map)",color:yellow,bold:false}],custom_data={map:"$(map)",type:"dest"}]
        if (executor != null) { if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "black_concrete[item_name=[{text:\"[ 적 도착 지점 ]\",color:gray,bold:true},{text:\" 맵: \",color:gray,bold:false},{text:\"" + macroArgs.get("map") + "\",color:yellow,bold:false}],custom_data={map:\"" + macroArgs.get("map") + "\",type:\"dest\"}]", 1); }

        // $give @s barrier[item_name=[{text:"[ 포인트 삭제 ]",color:red,bold:true},{text:" 맵: ",color:gray,bold:false},{text:"$(map)",color:yellow,bold:false}],custom_data={map:"$(map)",type:"delete"}]
        if (executor != null) { if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "barrier[item_name=[{text:\"[ 포인트 삭제 ]\",color:red,bold:true},{text:\" 맵: \",color:gray,bold:false},{text:\"" + macroArgs.get("map") + "\",color:yellow,bold:false}],custom_data={map:\"" + macroArgs.get("map") + "\",type:\"delete\"}]", 1); }
        return 0;
    }

    public static void _m_513c89ef_api_map_checkpoint_path_main_execute(ServerCommandSource source) {
        _m_513c89ef_api_map_checkpoint_path_main_executeReturn(source);
    }

    public static int _m_513c89ef_api_map_checkpoint_path_main_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute as @e[tag=map.point,tag=map.spawn_point] at @s run particle minecraft:dust{color:[0.000,1.000,0.000],scale:2} ~ ~ ~ 0.1 0.1 0.1 0 1 force @a[tag=player.map_modify]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("map.point") && en.getCommandTags().contains("map.spawn_point"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc1 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc1.getPosition().x, kfcSrc1.getPosition().y, kfcSrc1.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.1, 0.1, 0.1, 0, (int)(1), 0.000f, 1.000f, 0.000f, 2f, true, KfcGen.filterPlayers(ctx, _pv -> (_pv.getCommandTags().contains("player.map_modify")))); }
        }

        // execute as @e[tag=map.point,tag=map.spawn_point] at @s run particle minecraft:dust{color:[1.000,0.000,0.000],scale:1} ^ ^ ^0.5 0 0 0 0 1 force @a[tag=player.map_modify]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("map.point") && en.getCommandTags().contains("map.spawn_point"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc2 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.util.math.Vec3d _pp = KfcGen.localOffset(kfcSrc2.getPosition(), kfcSrc2.getRotation(), 0.0, 0.0, 0.5); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 1.000f, 0.000f, 0.000f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (_pv.getCommandTags().contains("player.map_modify")))); }
        }

        // execute as @e[tag=map.point,tag=map.check_point] at @s run particle minecraft:dust{color:[0.000,0.000,1.000],scale:2} ~ ~ ~ 0.1 0.1 0.1 0 1 force @a[tag=player.map_modify]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("map.point") && en.getCommandTags().contains("map.check_point"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc3 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc3.getPosition().x, kfcSrc3.getPosition().y, kfcSrc3.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.1, 0.1, 0.1, 0, (int)(1), 0.000f, 0.000f, 1.000f, 2f, true, KfcGen.filterPlayers(ctx, _pv -> (_pv.getCommandTags().contains("player.map_modify")))); }
        }

        // execute as @e[tag=map.point,tag=map.check_point] at @s run particle minecraft:dust{color:[1.000,0.000,0.000],scale:1} ^ ^ ^0.5 0 0 0 0 1 force @a[tag=player.map_modify]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("map.point") && en.getCommandTags().contains("map.check_point"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc4 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.util.math.Vec3d _pp = KfcGen.localOffset(kfcSrc4.getPosition(), kfcSrc4.getRotation(), 0.0, 0.0, 0.5); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 1.000f, 0.000f, 0.000f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (_pv.getCommandTags().contains("player.map_modify")))); }
        }

        // execute as @e[tag=map.point,tag=map.dest_point] at @s run particle minecraft:dust{color:[1.000,0.000,0.000],scale:2} ~ ~ ~ 0.1 0.1 0.1 0 1 force @a[tag=player.map_modify]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("map.point") && en.getCommandTags().contains("map.dest_point"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc5 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc5.getPosition().x, kfcSrc5.getPosition().y, kfcSrc5.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.1, 0.1, 0.1, 0, (int)(1), 1.000f, 0.000f, 0.000f, 2f, true, KfcGen.filterPlayers(ctx, _pv -> (_pv.getCommandTags().contains("player.map_modify")))); }
        }
        return 0;
    }

    public static void _m_82ac4994_api_map_checkpoint_use_checkpoint_check_point_execute(ServerCommandSource source) {
        _m_82ac4994_api_map_checkpoint_use_checkpoint_check_point_executeReturn(source);
    }

    public static int _m_82ac4994_api_map_checkpoint_use_checkpoint_check_point_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // tellraw @s [{text:"적 체크포인트 지점이 설정되었습니다!",color:"green"}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"적 체크포인트 지점이 설정되었습니다!\",color:\"green\"}]");
        return 0;
    }

    public static void _m_99b83eec_api_map_checkpoint_use_checkpoint_delete_point_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_99b83eec_api_map_checkpoint_use_checkpoint_delete_point_executeReturn(source, macroArgs);
    }

    public static int _m_99b83eec_api_map_checkpoint_use_checkpoint_delete_point_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // $kill @e[tag=map.$(map)]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"map." + macroArgs.get("map")}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // tellraw @s [{text:"포인트가 삭제되었습니다!",color:"red"}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"포인트가 삭제되었습니다!\",color:\"red\"}]");
        return 0;
    }

    public static void _m_5f43ca54_api_map_checkpoint_use_checkpoint_dest_point_execute(ServerCommandSource source) {
        _m_5f43ca54_api_map_checkpoint_use_checkpoint_dest_point_executeReturn(source);
    }

    public static int _m_5f43ca54_api_map_checkpoint_use_checkpoint_dest_point_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // tellraw @s [{text:"적 도착 지점이 설정되었습니다!",color:"green"}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"적 도착 지점이 설정되었습니다!\",color:\"green\"}]");
        return 0;
    }

    public static void _m_61434502_api_map_checkpoint_use_checkpoint_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_61434502_api_map_checkpoint_use_checkpoint_main_executeReturn(source, macroArgs);
    }

    public static int _m_61434502_api_map_checkpoint_use_checkpoint_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/9줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("api", "map/checkpoint/use_checkpoint/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // execute store result score @s player.rotation run data get entity @s Rotation[0] 10.0
                // KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "player.rotation", (int)((KfcGen.entityGetDouble(executor, "Rotation[0]")) * 10.0));
                // 
                // // $execute if score @s player.rotation matches -450..449 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[0f,0f]}
                // if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "player.rotation", -450, 449)) {
                //     { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[map.point,map." + macroArgs.get("type") + "_point,map." + macroArgs.get("map") + "],Rotation:[0f,0f]}"); }
                // }
                // 
                // // $execute if score @s player.rotation matches 450..1349 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[90f,0f]}
                // if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "player.rotation", 450, 1349)) {
                //     { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[map.point,map." + macroArgs.get("type") + "_point,map." + macroArgs.get("map") + "],Rotation:[90f,0f]}"); }
                // }
                // 
                // // $execute if score @s player.rotation matches 1350..1800 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[180f,0f]}
                // if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "player.rotation", 1350, 1800)) {
                //     { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[map.point,map." + macroArgs.get("type") + "_point,map." + macroArgs.get("map") + "],Rotation:[180f,0f]}"); }
                // }
                // 
                // // $execute if score @s player.rotation matches -1800..-1351 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[180f,0f]}
                // if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "player.rotation", -1800, -1351)) {
                //     { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[map.point,map." + macroArgs.get("type") + "_point,map." + macroArgs.get("map") + "],Rotation:[180f,0f]}"); }
                // }
                // 
                // // $execute if score @s player.rotation matches -1350..-451 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[270f,0f]}
                // if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "player.rotation", -1350, -451)) {
                //     { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[map.point,map." + macroArgs.get("type") + "_point,map." + macroArgs.get("map") + "],Rotation:[270f,0f]}"); }
                // }
                // 
                // // $function api:map/checkpoint/use_checkpoint/$(type)_point with storage player temp.components.minecraft:custom_data
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // item replace entity @s weapon.mainhand from entity @s weapon.offhand
                // if (executor != null) { net.minecraft.entity.Entity _isrc = executor; if (_isrc != null) KfcGen.itemReplaceFrom(executor, "weapon.mainhand", _isrc, "weapon.offhand"); }
                // 
                // // item replace entity @s weapon.offhand with air
                // if (executor != null) KfcGen.itemReplaceWith(server, executor, "weapon.offhand", "air", 1);
        return 0;
    }

    public static void _m_44a222ee_api_map_checkpoint_use_checkpoint_spawn_point_execute(ServerCommandSource source) {
        _m_44a222ee_api_map_checkpoint_use_checkpoint_spawn_point_executeReturn(source);
    }

    public static int _m_44a222ee_api_map_checkpoint_use_checkpoint_spawn_point_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // tellraw @s [{text:"적 스폰 지점이 설정되었습니다!",color:"green"}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"적 스폰 지점이 설정되었습니다!\",color:\"green\"}]");
        return 0;
    }

    public static void _m_33a3dfe6_api_map_data_load_execute(ServerCommandSource source) {
        _m_33a3dfe6_api_map_data_load_executeReturn(source);
    }

    public static int _m_33a3dfe6_api_map_data_load_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data remove storage map test
        KfcGen.storageRemovePath(server, "map", "test");

        // function api:map/data/test/easy/main
        // -> api:map/data/test/easy/main
        tdpack.buckets.Bucket2._m_09e676e0_api_map_data_test_easy_main_execute(source);

        // function api:map/data/test/normal/main
        // -> api:map/data/test/normal/main
        tdpack.buckets.Bucket3._m_d6262504_api_map_data_test_normal_main_execute(source);

        // function api:map/data/test/hard/main
        // -> api:map/data/test/hard/main
        tdpack.buckets.Bucket3._m_a2c49988_api_map_data_test_hard_main_execute(source);

        // function api:map/data/test/dark/main
        // -> api:map/data/test/dark/main
        tdpack.buckets.Bucket2._m_f47dad90_api_map_data_test_dark_main_execute(source);

        // function api:map/data/test/infinity/main
        // -> api:map/data/test/infinity/main
        tdpack.buckets.Bucket3._m_6fe61adc_api_map_data_test_infinity_main_execute(source);

        // data remove storage map forest_temple
        KfcGen.storageRemovePath(server, "map", "forest_temple");

        // function api:map/data/forest_temple/easy/main
        // -> api:map/data/forest_temple/easy/main
        tdpack.buckets.Bucket1._m_977ddc86_api_map_data_forest_temple_easy_main_execute(source);

        // function api:map/data/forest_temple/normal/main
        // -> api:map/data/forest_temple/normal/main
        tdpack.buckets.Bucket2._m_eb8af428_api_map_data_forest_temple_normal_main_execute(source);

        // function api:map/data/forest_temple/hard/main
        // -> api:map/data/forest_temple/hard/main
        tdpack.buckets.Bucket1._m_6217b003_api_map_data_forest_temple_hard_main_execute(source);

        // function api:map/data/forest_temple/dark/main
        // -> api:map/data/forest_temple/dark/main
        tdpack.buckets.Bucket0._m_338ea7e4_api_map_data_forest_temple_dark_main_execute(source);

        // function api:map/data/forest_temple/infinity/main
        // -> api:map/data/forest_temple/infinity/main
        tdpack.buckets.Bucket1._m_dd9444c6_api_map_data_forest_temple_infinity_main_execute(source);
        return 0;
    }

    public static void _m_7731d29b_api_map_data_forest_temple_dark_1_execute(ServerCommandSource source) {
        _m_7731d29b_api_map_data_forest_temple_dark_1_executeReturn(source);
    }

    public static int _m_7731d29b_api_map_data_forest_temple_dark_1_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[0].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[0].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[0].tick_30 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[0].tick_30", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[0].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[0].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[0].end set value 60
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[0].end", 60, "int");
        return 0;
    }

    public static void _m_952bce45_api_map_data_forest_temple_dark_10_execute(ServerCommandSource source) {
        _m_952bce45_api_map_data_forest_temple_dark_10_executeReturn(source);
    }

    public static int _m_952bce45_api_map_data_forest_temple_dark_10_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[9].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[9].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[9].tick_20 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[9].tick_20", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[9].tick_40 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[9].tick_40", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[9].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[9].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[9].tick_80 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[9].tick_80", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[9].tick_100 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[9].tick_100", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[9].tick_120 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[9].tick_120", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[9].tick_140 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[9].tick_140", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[9].end set value 140
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[9].end", 140, "int");
        return 0;
    }

    public static void _m_3f47ba87_api_map_data_forest_temple_dark_11_execute(ServerCommandSource source) {
        _m_3f47ba87_api_map_data_forest_temple_dark_11_executeReturn(source);
    }

    public static int _m_3f47ba87_api_map_data_forest_temple_dark_11_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[10].tick_0 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[10].tick_0", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[10].tick_19 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[10].tick_19", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[10].tick_39 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[10].tick_39", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[10].tick_59 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[10].tick_59", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[10].tick_79 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[10].tick_79", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[10].tick_99 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[10].tick_99", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[10].tick_199 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[10].tick_199", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[10].end set value 199
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[10].end", 199, "int");
        return 0;
    }

    public static void _m_26437b04_api_map_data_forest_temple_dark_12_execute(ServerCommandSource source) {
        _m_26437b04_api_map_data_forest_temple_dark_12_executeReturn(source);
    }

    public static int _m_26437b04_api_map_data_forest_temple_dark_12_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[11].tick_19 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[11].tick_19", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[11].tick_99 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[11].tick_99", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[11].tick_119 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[11].tick_119", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[11].tick_149 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[11].tick_149", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[11].tick_199 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[11].tick_199", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[11].end set value 199
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[11].end", 199, "int");
        return 0;
    }

    public static void _m_d5ba3a93_api_map_data_forest_temple_dark_13_execute(ServerCommandSource source) {
        _m_d5ba3a93_api_map_data_forest_temple_dark_13_executeReturn(source);
    }

    public static int _m_d5ba3a93_api_map_data_forest_temple_dark_13_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[12].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_20 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_20", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_40 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_40", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_49 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_49", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_80 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_80", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_100 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_100", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_120 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_120", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_140 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_140", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_160 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_160", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_180 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_180", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_199 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_199", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[12].tick_200 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[12].tick_200", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[12].end set value 200
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[12].end", 200, "int");
        return 0;
    }

    public static void _m_3d9b8ba1_api_map_data_forest_temple_dark_14_execute(ServerCommandSource source) {
        _m_3d9b8ba1_api_map_data_forest_temple_dark_14_executeReturn(source);
    }

    public static int _m_3d9b8ba1_api_map_data_forest_temple_dark_14_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[13].tick_24 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[13].tick_24", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[13].tick_29 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[13].tick_29", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[13].tick_49 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[13].tick_49", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[13].tick_99 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[13].tick_99", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[13].tick_124 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[13].tick_124", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[13].tick_149 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[13].tick_149", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[13].tick_169 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[13].tick_169", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[13].end set value 169
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[13].end", 169, "int");
        return 0;
    }

    public static void _m_1623304c_api_map_data_forest_temple_dark_15_execute(ServerCommandSource source) {
        _m_1623304c_api_map_data_forest_temple_dark_15_executeReturn(source);
    }

    public static int _m_1623304c_api_map_data_forest_temple_dark_15_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[14].tick_19 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[14].tick_19", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[14].tick_119 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[14].tick_119", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[14].tick_219 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[14].tick_219", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[14].end set value 219
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[14].end", 219, "int");
        return 0;
    }

    public static void _m_a5ecab88_api_map_data_forest_temple_dark_16_execute(ServerCommandSource source) {
        _m_a5ecab88_api_map_data_forest_temple_dark_16_executeReturn(source);
    }

    public static int _m_a5ecab88_api_map_data_forest_temple_dark_16_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[15].tick_0 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_0", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_1 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_1", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_9 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_9", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_49 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_49", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_54 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_54", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_99 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_99", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_100 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_100", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_104 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_104", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_199 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_199", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_299 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_299", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_339 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_339", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[15].tick_379 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[15].tick_379", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[15].end set value 379
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[15].end", 379, "int");
        return 0;
    }

    public static void _m_96a290f8_api_map_data_forest_temple_dark_17_execute(ServerCommandSource source) {
        _m_96a290f8_api_map_data_forest_temple_dark_17_executeReturn(source);
    }

    public static int _m_96a290f8_api_map_data_forest_temple_dark_17_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[16].tick_0 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[16].tick_0", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[16].tick_29 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[16].tick_29", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[16].tick_34 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[16].tick_34", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[16].tick_69 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[16].tick_69", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[16].tick_104 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[16].tick_104", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[16].end set value 104
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[16].end", 104, "int");
        return 0;
    }

    public static void _m_7c776534_api_map_data_forest_temple_dark_18_execute(ServerCommandSource source) {
        _m_7c776534_api_map_data_forest_temple_dark_18_executeReturn(source);
    }

    public static int _m_7c776534_api_map_data_forest_temple_dark_18_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[17].tick_0 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[17].tick_0", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[17].tick_1 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[17].tick_1", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[17].tick_59 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[17].tick_59", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[17].tick_119 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[17].tick_119", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[17].tick_179 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[17].tick_179", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[17].tick_199 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[17].tick_199", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[17].tick_239 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[17].tick_239", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[17].end set value 239
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[17].end", 239, "int");
        return 0;
    }

    public static void _m_416cf142_api_map_data_forest_temple_dark_19_execute(ServerCommandSource source) {
        _m_416cf142_api_map_data_forest_temple_dark_19_executeReturn(source);
    }

    public static int _m_416cf142_api_map_data_forest_temple_dark_19_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[18].tick_0 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_0", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_1 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_1", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_9 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_9", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_69 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_69", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_79 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_79", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_139 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_139", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_149 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_149", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_199 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_199", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_209 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_209", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[18].tick_219 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[18].tick_219", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[18].end set value 219
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[18].end", 219, "int");
        return 0;
    }

    public static void _m_efeb9260_api_map_data_forest_temple_dark_2_execute(ServerCommandSource source) {
        _m_efeb9260_api_map_data_forest_temple_dark_2_executeReturn(source);
    }

    public static int _m_efeb9260_api_map_data_forest_temple_dark_2_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[1].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[1].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[1].tick_30 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[1].tick_30", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[1].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[1].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[1].tick_90 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[1].tick_90", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[1].tick_120 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[1].tick_120", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[1].end set value 120
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[1].end", 120, "int");
        return 0;
    }

    public static void _m_8965ae59_api_map_data_forest_temple_dark_20_execute(ServerCommandSource source) {
        _m_8965ae59_api_map_data_forest_temple_dark_20_executeReturn(source);
    }

    public static int _m_8965ae59_api_map_data_forest_temple_dark_20_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[19].tick_0 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_0", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[19].tick_1 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_1", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[19].tick_19 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_19", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[19].tick_39 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_39", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[19].tick_69 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_69", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[19].tick_139 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_139", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[19].tick_174 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_174", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[19].tick_209 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_209", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[19].tick_299 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[19].tick_299", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[19].end set value 299
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[19].end", 299, "int");
        return 0;
    }

    public static void _m_fe319035_api_map_data_forest_temple_dark_21_execute(ServerCommandSource source) {
        _m_fe319035_api_map_data_forest_temple_dark_21_executeReturn(source);
    }

    public static int _m_fe319035_api_map_data_forest_temple_dark_21_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[20].tick_0 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_0", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_1 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_1", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_39 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_39", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_49 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_49", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_79 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_79", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_80 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_80", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_99 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_99", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_149 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_149", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_150 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_150", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_199 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_199", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_249 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_249", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_299 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_299", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_300 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_300", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_349 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_349", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[20].tick_350 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[20].tick_350", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[20].end set value 350
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[20].end", 350, "int");
        return 0;
    }

    public static void _m_2e491aea_api_map_data_forest_temple_dark_22_execute(ServerCommandSource source) {
        _m_2e491aea_api_map_data_forest_temple_dark_22_executeReturn(source);
    }

    public static int _m_2e491aea_api_map_data_forest_temple_dark_22_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[21].tick_0 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_0", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_9", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_29 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_29", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_69 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_69", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_74 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_74", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_99 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_99", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_100 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_100", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_109 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_109", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_119 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_119", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_139 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_139", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_149 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_149", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_150 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_150", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_159 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_159", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_179 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_179", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_189 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_189", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_224 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_224", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_225 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_225", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_239 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_239", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_249 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_249", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_289 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_289", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_299 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_299", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_339 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_339", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[21].tick_399 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[21].tick_399", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[21].end set value 399
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[21].end", 399, "int");
        return 0;
    }

    public static void _m_90b0c4ea_api_map_data_forest_temple_dark_23_execute(ServerCommandSource source) {
        _m_90b0c4ea_api_map_data_forest_temple_dark_23_executeReturn(source);
    }

    public static int _m_90b0c4ea_api_map_data_forest_temple_dark_23_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[22].tick_0 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_0", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_9", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_10 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_10", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_19 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_19", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_39 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_39", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_59 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_59", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_60 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_60", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_79 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_79", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_99 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_99", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_119 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_119", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_120 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_120", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_139 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_139", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_149 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_149", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_159 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_159", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].tick_179 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[22].tick_179", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[22].end set value 179
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[22].end", 179, "int");
        return 0;
    }

    public static void _m_3defb63c_api_map_data_forest_temple_dark_24_execute(ServerCommandSource source) {
        _m_3defb63c_api_map_data_forest_temple_dark_24_executeReturn(source);
    }

    public static int _m_3defb63c_api_map_data_forest_temple_dark_24_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[23].tick_0 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_0", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_1 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_1", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_2 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_2", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_9 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_9", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_19 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_19", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_20 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_20", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_39 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_39", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_40 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_40", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_49 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_49", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_59 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_59", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_60 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_60", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_61 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_61", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_79 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_79", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_99 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_99", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_100 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_100", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_119 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_119", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_120 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_120", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_139 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_139", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_159 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_159", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].tick_179 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[23].tick_179", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[23].end set value 179
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[23].end", 179, "int");
        return 0;
    }

    public static void _m_9e7368b0_api_map_data_forest_temple_dark_25_execute(ServerCommandSource source) {
        _m_9e7368b0_api_map_data_forest_temple_dark_25_executeReturn(source);
    }

    public static int _m_9e7368b0_api_map_data_forest_temple_dark_25_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[24].tick_0 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_0", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_9", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_10 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_10", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_11 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_11", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_20 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_20", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_40 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_40", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_49 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_49", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_50 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_50", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_59 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_59", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_60 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_60", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_80 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_80", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_89 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_89", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_99 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_99", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_100 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_100", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_119 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_119", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_120 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_120", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_129 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_129", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_140 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_140", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_169 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_169", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[24].tick_209 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[24].tick_209", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[24].end set value 209
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[24].end", 209, "int");
        return 0;
    }

    public static void _m_4bbb28ec_api_map_data_forest_temple_dark_26_execute(ServerCommandSource source) {
        _m_4bbb28ec_api_map_data_forest_temple_dark_26_executeReturn(source);
    }

    public static int _m_4bbb28ec_api_map_data_forest_temple_dark_26_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[25].tick_0 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_0", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_1 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_1", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_2 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_2", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_9", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_10 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_10", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_19 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_19", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_20 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_20", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_21 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_21", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_39 set value {difficulty:dark,map:forest_temple,model:stray}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_39", "{difficulty:dark,map:forest_temple,model:stray}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_40 set value {difficulty:dark,map:forest_temple,model:bogged}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_40", "{difficulty:dark,map:forest_temple,model:bogged}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_41 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_41", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_49 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_49", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_69 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_69", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_89 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_89", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_99 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_99", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_119 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_119", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_120 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_120", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_129 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_129", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_159 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_159", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_189 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_189", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_209 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_209", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_219 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_219", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[25].tick_239 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[25].tick_239", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[25].end set value 239
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[25].end", 239, "int");
        return 0;
    }

    public static void _m_87828504_api_map_data_forest_temple_dark_27_execute(ServerCommandSource source) {
        _m_87828504_api_map_data_forest_temple_dark_27_executeReturn(source);
    }

    public static int _m_87828504_api_map_data_forest_temple_dark_27_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[26].tick_0 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_0", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_1 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_1", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_9", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_19 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_19", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_29 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_29", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_39 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_39", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_59 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_59", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_69 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_69", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_70 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_70", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_89 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_89", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_99 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_99", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_119 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_119", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_129 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_129", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_149 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_149", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_159 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_159", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_179 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_179", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_189 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_189", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_199 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_199", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_209 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_209", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_210 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_210", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_239 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_239", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_240 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_240", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_269 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_269", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_299 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_299", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_329 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_329", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].tick_359 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[26].tick_359", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[26].end set value 359
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[26].end", 359, "int");
        return 0;
    }

    public static void _m_44ae66d7_api_map_data_forest_temple_dark_28_execute(ServerCommandSource source) {
        _m_44ae66d7_api_map_data_forest_temple_dark_28_executeReturn(source);
    }

    public static int _m_44ae66d7_api_map_data_forest_temple_dark_28_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[27].tick_0 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_0", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_4 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_4", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_9 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_9", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_10 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_10", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_14 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_14", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_19 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_19", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_20 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_20", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_21 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_21", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_24 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_24", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_29 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_29", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_30 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_30", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_34 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_34", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_39 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_39", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_40 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_40", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_44 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_44", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_49 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_49", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_59 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_59", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_69 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_69", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_79 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_79", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_89 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_89", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_99 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_99", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_100 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_100", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_109 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_109", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_119 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_119", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_129 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_129", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_139 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_139", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_149 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_149", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_159 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_159", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_169 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_169", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_179 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_179", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].tick_189 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[27].tick_189", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[27].end set value 189
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[27].end", 189, "int");
        return 0;
    }

    public static void _m_0d0b4c23_api_map_data_forest_temple_dark_29_execute(ServerCommandSource source) {
        _m_0d0b4c23_api_map_data_forest_temple_dark_29_executeReturn(source);
    }

    public static int _m_0d0b4c23_api_map_data_forest_temple_dark_29_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[28].tick_0 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_0", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_1 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_1", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_19 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_19", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_24 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_24", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_49 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_49", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_74 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_74", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_99 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_99", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_124 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_124", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_149 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_149", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_174 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_174", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_199 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_199", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_224 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_224", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_249 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_249", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[28].tick_299 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[28].tick_299", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[28].end set value 299
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[28].end", 299, "int");
        return 0;
    }

    public static void _m_49e1883f_api_map_data_forest_temple_dark_3_execute(ServerCommandSource source) {
        _m_49e1883f_api_map_data_forest_temple_dark_3_executeReturn(source);
    }

    public static int _m_49e1883f_api_map_data_forest_temple_dark_3_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[2].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[2].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[2].tick_1 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[2].tick_1", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[2].tick_30 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[2].tick_30", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[2].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[2].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[2].tick_90 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[2].tick_90", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[2].tick_100 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[2].tick_100", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[2].end set value 100
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[2].end", 100, "int");
        return 0;
    }

    public static void _m_a1b8d80d_api_map_data_forest_temple_dark_30_execute(ServerCommandSource source) {
        _m_a1b8d80d_api_map_data_forest_temple_dark_30_executeReturn(source);
    }

    public static int _m_a1b8d80d_api_map_data_forest_temple_dark_30_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[29].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_9", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_10 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_10", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_29 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_29", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_30 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_30", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_59 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_59", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_60 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_60", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_79 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_79", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_80 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_80", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_99 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_99", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_109 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_109", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_110 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_110", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_119 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_119", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_120 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_120", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_139 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_139", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_140 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_140", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_169 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_169", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_170 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_170", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_199 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_199", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_200 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_200", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[29].tick_299 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[29].tick_299", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[29].end set value 299
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[29].end", 299, "int");
        return 0;
    }

    public static void _m_f01f899e_api_map_data_forest_temple_dark_31_execute(ServerCommandSource source) {
        _m_f01f899e_api_map_data_forest_temple_dark_31_executeReturn(source);
    }

    public static int _m_f01f899e_api_map_data_forest_temple_dark_31_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[30].tick_0 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_0", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_1 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_1", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_2 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_2", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_3 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_3", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_9", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_19 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_19", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_29 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_29", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_30 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_30", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_31 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_31", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_39 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_39", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_49 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_49", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_50 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_50", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_60 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_60", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_69 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_69", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_89 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_89", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_99 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_99", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_100 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_100", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_109 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_109", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_119 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_119", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_129 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_129", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_139 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_139", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_199 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_199", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_209 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_209", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_219 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_219", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].tick_229 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[30].tick_229", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[30].end set value 229
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[30].end", 229, "int");
        return 0;
    }

    public static void _m_25d21ddb_api_map_data_forest_temple_dark_32_execute(ServerCommandSource source) {
        _m_25d21ddb_api_map_data_forest_temple_dark_32_executeReturn(source);
    }

    public static int _m_25d21ddb_api_map_data_forest_temple_dark_32_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[31].tick_0 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_0", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_1 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_1", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_9 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_9", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_10 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_10", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_11 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_11", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_29 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_29", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_30 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_30", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_31 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_31", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_49 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_49", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_50 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_50", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_60 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_60", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_69 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_69", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_79 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_79", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_89 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_89", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_109 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_109", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_129 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_129", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_149 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_149", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_169 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_169", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_189 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_189", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_209 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_209", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_229 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_229", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_249 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_249", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].tick_269 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[31].tick_269", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[31].end set value 269
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[31].end", 269, "int");
        return 0;
    }

    public static void _m_f462559d_api_map_data_forest_temple_dark_33_execute(ServerCommandSource source) {
        _m_f462559d_api_map_data_forest_temple_dark_33_executeReturn(source);
    }

    public static int _m_f462559d_api_map_data_forest_temple_dark_33_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[32].tick_0 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[32].tick_0", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[32].tick_49 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[32].tick_49", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[32].tick_79 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[32].tick_79", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[32].tick_80 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[32].tick_80", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[32].tick_159 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[32].tick_159", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[32].tick_239 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[32].tick_239", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[32].tick_299 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[32].tick_299", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[32].end set value 299
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[32].end", 299, "int");
        return 0;
    }

    public static void _m_624b611a_api_map_data_forest_temple_dark_34_execute(ServerCommandSource source) {
        _m_624b611a_api_map_data_forest_temple_dark_34_executeReturn(source);
    }

    public static int _m_624b611a_api_map_data_forest_temple_dark_34_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[33].tick_0 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_0", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_1 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_1", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_2 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_2", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_3 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_3", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_49 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_49", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_79 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_79", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_104 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_104", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_114 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_114", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_124 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_124", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_125 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_125", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_134 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_134", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_144 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_144", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_149 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_149", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_154 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_154", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_159 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_159", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_164 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_164", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_174 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_174", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_184 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_184", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_194 set value {difficulty:dark,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_194", "{difficulty:dark,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_239 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_239", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_299 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_299", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[33].tick_300 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[33].tick_300", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[33].end set value 300
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[33].end", 300, "int");
        return 0;
    }

    public static void _m_800affca_api_map_data_forest_temple_dark_35_execute(ServerCommandSource source) {
        _m_800affca_api_map_data_forest_temple_dark_35_executeReturn(source);
    }

    public static int _m_800affca_api_map_data_forest_temple_dark_35_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[34].tick_0 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_0", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_1 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_1", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_19 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_19", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_29 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_29", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_39 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_39", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_49 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_49", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_59 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_59", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_60 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_60", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_79 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_79", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_80 set value {difficulty:dark,map:forest_temple,model:carsinops}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_80", "{difficulty:dark,map:forest_temple,model:carsinops}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_99 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_99", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_119 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_119", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_139 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_139", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_149 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_149", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_159 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_159", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_179 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_179", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_180 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_180", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_199 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_199", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[34].tick_229 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[34].tick_229", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[34].end set value 229
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[34].end", 229, "int");
        return 0;
    }

    public static void _m_9e253dc9_api_map_data_forest_temple_dark_36_execute(ServerCommandSource source) {
        _m_9e253dc9_api_map_data_forest_temple_dark_36_executeReturn(source);
    }

    public static int _m_9e253dc9_api_map_data_forest_temple_dark_36_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[35].tick_0 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_0", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_1 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_1", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_2 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_2", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_19 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_19", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_20 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_20", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_24 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_24", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_29 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_29", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_39 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_39", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_40 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_40", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_59 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_59", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_60 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_60", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_61 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_61", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_79 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_79", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_80 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_80", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_99 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_99", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_100 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_100", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_119 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_119", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_139 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_139", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_149 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_149", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_159 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_159", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_179 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_179", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_180 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_180", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_199 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_199", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_209 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_209", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_219 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_219", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_239 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_239", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[35].tick_259 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[35].tick_259", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[35].end set value 259
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[35].end", 259, "int");
        return 0;
    }

    public static void _m_25f9d85a_api_map_data_forest_temple_dark_37_execute(ServerCommandSource source) {
        _m_25f9d85a_api_map_data_forest_temple_dark_37_executeReturn(source);
    }

    public static int _m_25f9d85a_api_map_data_forest_temple_dark_37_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[36].tick_0 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_0", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_1 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_1", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_2 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_2", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_4 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_4", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_19 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_19", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_20 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_20", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_34 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_34", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_39 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_39", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_40 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_40", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_59 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_59", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_60 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_60", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_64 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_64", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_69 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_69", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_70 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_70", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_79 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_79", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_80 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_80", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_81 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_81", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_94 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_94", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_99 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_99", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_119 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_119", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_124 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_124", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_139 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_139", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_140 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_140", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_154 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_154", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_159 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_159", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_184 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_184", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_199 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_199", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_209 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_209", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_214 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_214", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_219 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_219", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_239 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_239", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_244 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_244", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_259 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_259", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_274 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_274", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_279 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_279", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[36].tick_304 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[36].tick_304", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[36].end set value 304
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[36].end", 304, "int");
        return 0;
    }

    public static void _m_e110a66d_api_map_data_forest_temple_dark_38_execute(ServerCommandSource source) {
        _m_e110a66d_api_map_data_forest_temple_dark_38_executeReturn(source);
    }

    public static int _m_e110a66d_api_map_data_forest_temple_dark_38_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[37].tick_0 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_0", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_1 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_1", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_9", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_19 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_19", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_24 set value {difficulty:dark,map:forest_temple,model:dark-guardian}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_24", "{difficulty:dark,map:forest_temple,model:dark-guardian}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_29 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_29", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_40 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_40", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_59 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_59", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_60 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_60", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_69 set value {difficulty:dark,map:forest_temple,model:tank-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_69", "{difficulty:dark,map:forest_temple,model:tank-zombie}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_80 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_80", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_89 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_89", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_99 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_99", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_100 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_100", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_119 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_119", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_120 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_120", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_139 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_139", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_149 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_149", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_159 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_159", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_179 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_179", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_180 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_180", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_209 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_209", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_239 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_239", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_269 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_269", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_299 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_299", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_329 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_329", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].tick_359 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[37].tick_359", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[37].end set value 359
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[37].end", 359, "int");
        return 0;
    }

    public static void _m_0716ca5f_api_map_data_forest_temple_dark_39_execute(ServerCommandSource source) {
        _m_0716ca5f_api_map_data_forest_temple_dark_39_executeReturn(source);
    }

    public static int _m_0716ca5f_api_map_data_forest_temple_dark_39_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[38].tick_0 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_0", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_20 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_20", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_40 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_40", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_49 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_49", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_59 set value {difficulty:dark,map:forest_temple,model:dark-guardian}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_59", "{difficulty:dark,map:forest_temple,model:dark-guardian}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_60 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_60", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_80 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_80", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_99 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_99", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_100 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_100", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_120 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_120", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_140 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_140", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_149 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_149", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_199 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_199", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_220 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_220", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_240 set value {difficulty:dark,map:forest_temple,model:enderman}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_240", "{difficulty:dark,map:forest_temple,model:enderman}", "set");

        // data modify storage map forest_temple.dark.round[38].tick_249 set value {difficulty:dark,map:forest_temple,model:scream-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[38].tick_249", "{difficulty:dark,map:forest_temple,model:scream-zombie}", "set");

        // data modify storage map forest_temple.dark.round[38].end set value 249
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[38].end", 249, "int");
        return 0;
    }

    public static void _m_69b31992_api_map_data_forest_temple_dark_4_execute(ServerCommandSource source) {
        _m_69b31992_api_map_data_forest_temple_dark_4_executeReturn(source);
    }

    public static int _m_69b31992_api_map_data_forest_temple_dark_4_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[3].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[3].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[3].tick_1 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[3].tick_1", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[3].tick_20 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[3].tick_20", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[3].tick_40 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[3].tick_40", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[3].tick_50 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[3].tick_50", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[3].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[3].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[3].tick_80 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[3].tick_80", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[3].tick_100 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[3].tick_100", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[3].end set value 100
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[3].end", 100, "int");
        return 0;
    }

    public static void _m_161aa93d_api_map_data_forest_temple_dark_40_execute(ServerCommandSource source) {
        _m_161aa93d_api_map_data_forest_temple_dark_40_executeReturn(source);
    }

    public static int _m_161aa93d_api_map_data_forest_temple_dark_40_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[39].tick_0 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_0", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_1 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_1", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_9 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_9", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_10 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_10", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_11 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_11", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_19 set value {difficulty:dark,map:forest_temple,model:carsinops}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_19", "{difficulty:dark,map:forest_temple,model:carsinops}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_20 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_20", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_29 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_29", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_40 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_40", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_49 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_49", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_59 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_59", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_60 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_60", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_80 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_80", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_89 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_89", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_90 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_90", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_99 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_99", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_100 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_100", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_100 set value {difficulty:dark,map:forest_temple,model:darkness-soron}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_100", "{difficulty:dark,map:forest_temple,model:darkness-soron}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_119 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_119", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_120 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_120", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_129 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_129", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_140 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_140", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_149 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_149", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_160 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_160", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_169 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_169", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_179 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_179", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_180 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_180", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_199 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_199", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_200 set value {difficulty:dark,map:forest_temple,model:dark-guardian}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_200", "{difficulty:dark,map:forest_temple,model:dark-guardian}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_201 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_201", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_209 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_209", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_210 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_210", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_220 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_220", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_239 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_239", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_240 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_240", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_249 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_249", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_260 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_260", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_269 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_269", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_280 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_280", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_289 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_289", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_299 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_299", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_300 set value {difficulty:dark,map:forest_temple,model:dark-mist}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_300", "{difficulty:dark,map:forest_temple,model:dark-mist}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_301 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_301", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_320 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_320", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_329 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_329", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_340 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_340", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_360 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_360", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_369 set value {difficulty:dark,map:forest_temple,model:heavy-dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_369", "{difficulty:dark,map:forest_temple,model:heavy-dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_380 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_380", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_399 set value {difficulty:dark,map:forest_temple,model:dark-headbomb}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_399", "{difficulty:dark,map:forest_temple,model:dark-headbomb}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_400 set value {difficulty:dark,map:forest_temple,model:dark-knight}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_400", "{difficulty:dark,map:forest_temple,model:dark-knight}", "set");

        // data modify storage map forest_temple.dark.round[39].tick_401 set value {difficulty:dark,map:forest_temple,model:dark}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[39].tick_401", "{difficulty:dark,map:forest_temple,model:dark}", "set");

        // data modify storage map forest_temple.dark.round[39].end set value 402
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[39].end", 402, "int");
        return 0;
    }

    public static void _m_e2a2e819_api_map_data_forest_temple_dark_5_execute(ServerCommandSource source) {
        _m_e2a2e819_api_map_data_forest_temple_dark_5_executeReturn(source);
    }

    public static int _m_e2a2e819_api_map_data_forest_temple_dark_5_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[4].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[4].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[4].tick_1 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[4].tick_1", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[4].tick_20 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[4].tick_20", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[4].tick_40 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[4].tick_40", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[4].tick_41 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[4].tick_41", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[4].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[4].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[4].tick_80 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[4].tick_80", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[4].tick_81 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[4].tick_81", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[4].end set value 81
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[4].end", 81, "int");
        return 0;
    }

    public static void _m_aa5a1ef8_api_map_data_forest_temple_dark_6_execute(ServerCommandSource source) {
        _m_aa5a1ef8_api_map_data_forest_temple_dark_6_executeReturn(source);
    }

    public static int _m_aa5a1ef8_api_map_data_forest_temple_dark_6_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[5].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_1 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_1", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_2 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_2", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_20 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_20", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_21 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_21", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_40 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_40", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_41 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_41", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_80 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_80", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_81 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_81", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_82 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_82", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_100 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_100", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[5].tick_101 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[5].tick_101", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[5].end set value 101
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[5].end", 101, "int");
        return 0;
    }

    public static void _m_f1f76996_api_map_data_forest_temple_dark_7_execute(ServerCommandSource source) {
        _m_f1f76996_api_map_data_forest_temple_dark_7_executeReturn(source);
    }

    public static int _m_f1f76996_api_map_data_forest_temple_dark_7_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[6].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_10 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_10", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_20 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_20", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_30 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_30", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_31 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_31", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_40 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_40", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_50 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_50", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_59 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_59", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_60 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_60", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_61 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_61", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_79 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_79", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_90 set value {difficulty:dark,map:forest_temple,model:corrupted-husk}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_90", "{difficulty:dark,map:forest_temple,model:corrupted-husk}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_99 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_99", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_119 set value {difficulty:dark,map:forest_temple,model:corrupted-drowned}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_119", "{difficulty:dark,map:forest_temple,model:corrupted-drowned}", "set");

        // data modify storage map forest_temple.dark.round[6].tick_199 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[6].tick_199", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[6].end set value 199
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[6].end", 199, "int");
        return 0;
    }

    public static void _m_d31ca840_api_map_data_forest_temple_dark_8_execute(ServerCommandSource source) {
        _m_d31ca840_api_map_data_forest_temple_dark_8_executeReturn(source);
    }

    public static int _m_d31ca840_api_map_data_forest_temple_dark_8_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[7].tick_0 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_0", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_1 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_1", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_14 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_14", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_29 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_29", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_44 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_44", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_59 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_59", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_74 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_74", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_89 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_89", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_114 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_114", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_129 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_129", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_144 set value {difficulty:dark,map:forest_temple,model:corrupted-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_144", "{difficulty:dark,map:forest_temple,model:corrupted-zombie}", "set");

        // data modify storage map forest_temple.dark.round[7].tick_149 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[7].tick_149", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[7].end set value 149
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[7].end", 149, "int");
        return 0;
    }

    public static void _m_d6eb53f7_api_map_data_forest_temple_dark_9_execute(ServerCommandSource source) {
        _m_d6eb53f7_api_map_data_forest_temple_dark_9_executeReturn(source);
    }

    public static int _m_d6eb53f7_api_map_data_forest_temple_dark_9_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round", "{}", "append");

        // data modify storage map forest_temple.dark.round[8].tick_0 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[8].tick_0", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[8].tick_49 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[8].tick_49", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[8].tick_99 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[8].tick_99", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[8].tick_149 set value {difficulty:dark,map:forest_temple,model:zoglin}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark.round[8].tick_149", "{difficulty:dark,map:forest_temple,model:zoglin}", "set");

        // data modify storage map forest_temple.dark.round[8].end set value 149
        KfcGen.storagePutNumber(server, "map", "forest_temple.dark.round[8].end", 149, "int");
        return 0;
    }

    public static void _m_338ea7e4_api_map_data_forest_temple_dark_main_execute(ServerCommandSource source) {
        _m_338ea7e4_api_map_data_forest_temple_dark_main_executeReturn(source);
    }

    public static int _m_338ea7e4_api_map_data_forest_temple_dark_main_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.dark set value {setup:{money:900,map:forest_temple,map_name:"저지선",difficulty:dark,each_money:325},round:[]}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.dark", "{setup:{money:900,map:forest_temple,map_name:\"저지선\",difficulty:dark,each_money:325},round:[]}", "set");

        // function api:map/data/forest_temple/dark/1
        // -> api:map/data/forest_temple/dark/1
        tdpack.buckets.Bucket0._m_7731d29b_api_map_data_forest_temple_dark_1_execute(source);

        // function api:map/data/forest_temple/dark/2
        // -> api:map/data/forest_temple/dark/2
        tdpack.buckets.Bucket0._m_efeb9260_api_map_data_forest_temple_dark_2_execute(source);

        // function api:map/data/forest_temple/dark/3
        // -> api:map/data/forest_temple/dark/3
        tdpack.buckets.Bucket0._m_49e1883f_api_map_data_forest_temple_dark_3_execute(source);

        // function api:map/data/forest_temple/dark/4
        // -> api:map/data/forest_temple/dark/4
        tdpack.buckets.Bucket0._m_69b31992_api_map_data_forest_temple_dark_4_execute(source);

        // function api:map/data/forest_temple/dark/5
        // -> api:map/data/forest_temple/dark/5
        tdpack.buckets.Bucket0._m_e2a2e819_api_map_data_forest_temple_dark_5_execute(source);

        // function api:map/data/forest_temple/dark/6
        // -> api:map/data/forest_temple/dark/6
        tdpack.buckets.Bucket0._m_aa5a1ef8_api_map_data_forest_temple_dark_6_execute(source);

        // function api:map/data/forest_temple/dark/7
        // -> api:map/data/forest_temple/dark/7
        tdpack.buckets.Bucket0._m_f1f76996_api_map_data_forest_temple_dark_7_execute(source);

        // function api:map/data/forest_temple/dark/8
        // -> api:map/data/forest_temple/dark/8
        tdpack.buckets.Bucket0._m_d31ca840_api_map_data_forest_temple_dark_8_execute(source);

        // function api:map/data/forest_temple/dark/9
        // -> api:map/data/forest_temple/dark/9
        tdpack.buckets.Bucket0._m_d6eb53f7_api_map_data_forest_temple_dark_9_execute(source);

        // function api:map/data/forest_temple/dark/10
        // -> api:map/data/forest_temple/dark/10
        tdpack.buckets.Bucket0._m_952bce45_api_map_data_forest_temple_dark_10_execute(source);

        // function api:map/data/forest_temple/dark/11
        // -> api:map/data/forest_temple/dark/11
        tdpack.buckets.Bucket0._m_3f47ba87_api_map_data_forest_temple_dark_11_execute(source);

        // function api:map/data/forest_temple/dark/12
        // -> api:map/data/forest_temple/dark/12
        tdpack.buckets.Bucket0._m_26437b04_api_map_data_forest_temple_dark_12_execute(source);

        // function api:map/data/forest_temple/dark/13
        // -> api:map/data/forest_temple/dark/13
        tdpack.buckets.Bucket0._m_d5ba3a93_api_map_data_forest_temple_dark_13_execute(source);

        // function api:map/data/forest_temple/dark/14
        // -> api:map/data/forest_temple/dark/14
        tdpack.buckets.Bucket0._m_3d9b8ba1_api_map_data_forest_temple_dark_14_execute(source);

        // function api:map/data/forest_temple/dark/15
        // -> api:map/data/forest_temple/dark/15
        tdpack.buckets.Bucket0._m_1623304c_api_map_data_forest_temple_dark_15_execute(source);

        // function api:map/data/forest_temple/dark/16
        // -> api:map/data/forest_temple/dark/16
        tdpack.buckets.Bucket0._m_a5ecab88_api_map_data_forest_temple_dark_16_execute(source);

        // function api:map/data/forest_temple/dark/17
        // -> api:map/data/forest_temple/dark/17
        tdpack.buckets.Bucket0._m_96a290f8_api_map_data_forest_temple_dark_17_execute(source);

        // function api:map/data/forest_temple/dark/18
        // -> api:map/data/forest_temple/dark/18
        tdpack.buckets.Bucket0._m_7c776534_api_map_data_forest_temple_dark_18_execute(source);

        // function api:map/data/forest_temple/dark/19
        // -> api:map/data/forest_temple/dark/19
        tdpack.buckets.Bucket0._m_416cf142_api_map_data_forest_temple_dark_19_execute(source);

        // function api:map/data/forest_temple/dark/20
        // -> api:map/data/forest_temple/dark/20
        tdpack.buckets.Bucket0._m_8965ae59_api_map_data_forest_temple_dark_20_execute(source);

        // function api:map/data/forest_temple/dark/21
        // -> api:map/data/forest_temple/dark/21
        tdpack.buckets.Bucket0._m_fe319035_api_map_data_forest_temple_dark_21_execute(source);

        // function api:map/data/forest_temple/dark/22
        // -> api:map/data/forest_temple/dark/22
        tdpack.buckets.Bucket0._m_2e491aea_api_map_data_forest_temple_dark_22_execute(source);

        // function api:map/data/forest_temple/dark/23
        // -> api:map/data/forest_temple/dark/23
        tdpack.buckets.Bucket0._m_90b0c4ea_api_map_data_forest_temple_dark_23_execute(source);

        // function api:map/data/forest_temple/dark/24
        // -> api:map/data/forest_temple/dark/24
        tdpack.buckets.Bucket0._m_3defb63c_api_map_data_forest_temple_dark_24_execute(source);

        // function api:map/data/forest_temple/dark/25
        // -> api:map/data/forest_temple/dark/25
        tdpack.buckets.Bucket0._m_9e7368b0_api_map_data_forest_temple_dark_25_execute(source);

        // function api:map/data/forest_temple/dark/26
        // -> api:map/data/forest_temple/dark/26
        tdpack.buckets.Bucket0._m_4bbb28ec_api_map_data_forest_temple_dark_26_execute(source);

        // function api:map/data/forest_temple/dark/27
        // -> api:map/data/forest_temple/dark/27
        tdpack.buckets.Bucket0._m_87828504_api_map_data_forest_temple_dark_27_execute(source);

        // function api:map/data/forest_temple/dark/28
        // -> api:map/data/forest_temple/dark/28
        tdpack.buckets.Bucket0._m_44ae66d7_api_map_data_forest_temple_dark_28_execute(source);

        // function api:map/data/forest_temple/dark/29
        // -> api:map/data/forest_temple/dark/29
        tdpack.buckets.Bucket0._m_0d0b4c23_api_map_data_forest_temple_dark_29_execute(source);

        // function api:map/data/forest_temple/dark/30
        // -> api:map/data/forest_temple/dark/30
        tdpack.buckets.Bucket0._m_a1b8d80d_api_map_data_forest_temple_dark_30_execute(source);

        // function api:map/data/forest_temple/dark/31
        // -> api:map/data/forest_temple/dark/31
        tdpack.buckets.Bucket0._m_f01f899e_api_map_data_forest_temple_dark_31_execute(source);

        // function api:map/data/forest_temple/dark/32
        // -> api:map/data/forest_temple/dark/32
        tdpack.buckets.Bucket0._m_25d21ddb_api_map_data_forest_temple_dark_32_execute(source);

        // function api:map/data/forest_temple/dark/33
        // -> api:map/data/forest_temple/dark/33
        tdpack.buckets.Bucket0._m_f462559d_api_map_data_forest_temple_dark_33_execute(source);

        // function api:map/data/forest_temple/dark/34
        // -> api:map/data/forest_temple/dark/34
        tdpack.buckets.Bucket0._m_624b611a_api_map_data_forest_temple_dark_34_execute(source);

        // function api:map/data/forest_temple/dark/35
        // -> api:map/data/forest_temple/dark/35
        tdpack.buckets.Bucket0._m_800affca_api_map_data_forest_temple_dark_35_execute(source);

        // function api:map/data/forest_temple/dark/36
        // -> api:map/data/forest_temple/dark/36
        tdpack.buckets.Bucket0._m_9e253dc9_api_map_data_forest_temple_dark_36_execute(source);

        // function api:map/data/forest_temple/dark/37
        // -> api:map/data/forest_temple/dark/37
        tdpack.buckets.Bucket0._m_25f9d85a_api_map_data_forest_temple_dark_37_execute(source);

        // function api:map/data/forest_temple/dark/38
        // -> api:map/data/forest_temple/dark/38
        tdpack.buckets.Bucket0._m_e110a66d_api_map_data_forest_temple_dark_38_execute(source);

        // function api:map/data/forest_temple/dark/39
        // -> api:map/data/forest_temple/dark/39
        tdpack.buckets.Bucket0._m_0716ca5f_api_map_data_forest_temple_dark_39_execute(source);

        // function api:map/data/forest_temple/dark/40
        // -> api:map/data/forest_temple/dark/40
        tdpack.buckets.Bucket0._m_161aa93d_api_map_data_forest_temple_dark_40_execute(source);
        return 0;
    }

    public static void _m_d0936a3b_api_map_data_forest_temple_easy_1_execute(ServerCommandSource source) {
        _m_d0936a3b_api_map_data_forest_temple_easy_1_executeReturn(source);
    }

    public static int _m_d0936a3b_api_map_data_forest_temple_easy_1_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[0].tick_0 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[0].tick_0", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[0].tick_20 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[0].tick_20", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[0].tick_40 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[0].tick_40", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[0].end set value 40
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[0].end", 40, "int");
        return 0;
    }

    public static void _m_260658c5_api_map_data_forest_temple_easy_10_execute(ServerCommandSource source) {
        _m_260658c5_api_map_data_forest_temple_easy_10_executeReturn(source);
    }

    public static int _m_260658c5_api_map_data_forest_temple_easy_10_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[9].tick_0 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_0", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_0 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_0", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_9 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_9", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_19 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_19", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_29 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_29", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_39 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_39", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_49 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_49", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_54 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_54", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_59 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_59", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_64 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_64", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_74 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_74", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_99 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_99", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].tick_119 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[9].tick_119", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[9].end set value 119
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[9].end", 119, "int");
        return 0;
    }

    public static void _m_df2c4f3c_api_map_data_forest_temple_easy_11_execute(ServerCommandSource source) {
        _m_df2c4f3c_api_map_data_forest_temple_easy_11_executeReturn(source);
    }

    public static int _m_df2c4f3c_api_map_data_forest_temple_easy_11_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[10].tick_9 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[10].tick_9", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[10].tick_49 set value {difficulty:easy,map:forest_temple,model:armord-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[10].tick_49", "{difficulty:easy,map:forest_temple,model:armord-zombie}", "set");

        // data modify storage map forest_temple.easy.round[10].tick_99 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[10].tick_99", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[10].tick_119 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[10].tick_119", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[10].tick_134 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[10].tick_134", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[10].tick_139 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[10].tick_139", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[10].tick_159 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[10].tick_159", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[10].tick_179 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[10].tick_179", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[10].end set value 179
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[10].end", 179, "int");
        return 0;
    }

    public static void _m_2066a91c_api_map_data_forest_temple_easy_12_execute(ServerCommandSource source) {
        _m_2066a91c_api_map_data_forest_temple_easy_12_executeReturn(source);
    }

    public static int _m_2066a91c_api_map_data_forest_temple_easy_12_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[11].tick_0 set value {difficulty:easy,map:forest_temple,model:armord-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[11].tick_0", "{difficulty:easy,map:forest_temple,model:armord-zombie}", "set");

        // data modify storage map forest_temple.easy.round[11].tick_4 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[11].tick_4", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[11].tick_9 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[11].tick_9", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[11].tick_14 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[11].tick_14", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[11].tick_99 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[11].tick_99", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[11].tick_149 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[11].tick_149", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[11].tick_229 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[11].tick_229", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[11].end set value 229
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[11].end", 229, "int");
        return 0;
    }

    public static void _m_518eca34_api_map_data_forest_temple_easy_13_execute(ServerCommandSource source) {
        _m_518eca34_api_map_data_forest_temple_easy_13_executeReturn(source);
    }

    public static int _m_518eca34_api_map_data_forest_temple_easy_13_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[12].tick_0 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_0", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_9 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_9", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_14 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_14", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_19 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_19", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_29 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_29", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_39 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_39", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_44 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_44", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_49 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_49", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_50 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_50", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_59 set value {difficulty:easy,map:forest_temple,model:armord-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_59", "{difficulty:easy,map:forest_temple,model:armord-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_74 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_74", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_79 set value {difficulty:easy,map:forest_temple,model:armord-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_79", "{difficulty:easy,map:forest_temple,model:armord-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_99 set value {difficulty:easy,map:forest_temple,model:armord-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_99", "{difficulty:easy,map:forest_temple,model:armord-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_100 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_100", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].tick_150 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[12].tick_150", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[12].end set value 150
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[12].end", 150, "int");
        return 0;
    }

    public static void _m_e77e2b86_api_map_data_forest_temple_easy_14_execute(ServerCommandSource source) {
        _m_e77e2b86_api_map_data_forest_temple_easy_14_executeReturn(source);
    }

    public static int _m_e77e2b86_api_map_data_forest_temple_easy_14_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[13].tick_0 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_0", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_0 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_0", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_19 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_19", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_39 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_39", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_59 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_59", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_79 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_79", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_99 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_99", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_99 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_99", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_99 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_99", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_149 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_149", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[13].tick_199 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[13].tick_199", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[13].end set value 199
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[13].end", 199, "int");
        return 0;
    }

    public static void _m_1336b9a9_api_map_data_forest_temple_easy_15_execute(ServerCommandSource source) {
        _m_1336b9a9_api_map_data_forest_temple_easy_15_executeReturn(source);
    }

    public static int _m_1336b9a9_api_map_data_forest_temple_easy_15_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[14].tick_0 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_0", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_9 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_9", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_29 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_29", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_59 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_59", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_79 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_79", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_89 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_89", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_119 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_119", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_149 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_149", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_149 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_149", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_179 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_179", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_209 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_209", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_219 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_219", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_239 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_239", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_269 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_269", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_289 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_289", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_299 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_299", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].tick_329 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[14].tick_329", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[14].end set value 329
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[14].end", 329, "int");
        return 0;
    }

    public static void _m_4248e867_api_map_data_forest_temple_easy_16_execute(ServerCommandSource source) {
        _m_4248e867_api_map_data_forest_temple_easy_16_executeReturn(source);
    }

    public static int _m_4248e867_api_map_data_forest_temple_easy_16_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[15].tick_0 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[15].tick_0", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[15].tick_24 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[15].tick_24", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[15].tick_49 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[15].tick_49", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[15].tick_99 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[15].tick_99", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[15].tick_149 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[15].tick_149", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[15].tick_199 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[15].tick_199", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[15].end set value 199
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[15].end", 199, "int");
        return 0;
    }

    public static void _m_d3d60e47_api_map_data_forest_temple_easy_17_execute(ServerCommandSource source) {
        _m_d3d60e47_api_map_data_forest_temple_easy_17_executeReturn(source);
    }

    public static int _m_d3d60e47_api_map_data_forest_temple_easy_17_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[16].tick_0 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_0", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_0 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_0", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_0 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_0", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_19 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_19", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_19 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_19", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_39 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_39", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_39 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_39", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_59 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_59", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_59 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_59", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_79 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_79", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_79 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_79", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_99 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_99", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_99 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_99", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_119 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_119", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_119 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_119", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_139 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_139", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[16].tick_139 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[16].tick_139", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[16].end set value 139
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[16].end", 139, "int");
        return 0;
    }

    public static void _m_42135eb0_api_map_data_forest_temple_easy_18_execute(ServerCommandSource source) {
        _m_42135eb0_api_map_data_forest_temple_easy_18_executeReturn(source);
    }

    public static int _m_42135eb0_api_map_data_forest_temple_easy_18_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[17].tick_0 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_0", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_30 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_30", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_40 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_40", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_49 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_49", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_50 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_50", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_99 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_99", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_99 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_99", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_109 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_109", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_119 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_119", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_129 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_129", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_139 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_139", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_149 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_149", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_159 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_159", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].tick_169 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[17].tick_169", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[17].end set value 169
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[17].end", 169, "int");
        return 0;
    }

    public static void _m_14d7072b_api_map_data_forest_temple_easy_19_execute(ServerCommandSource source) {
        _m_14d7072b_api_map_data_forest_temple_easy_19_executeReturn(source);
    }

    public static int _m_14d7072b_api_map_data_forest_temple_easy_19_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[18].tick_0 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_0", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_0 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_0", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_0 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_0", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_19 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_19", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_19 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_19", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_39 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_39", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_39 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_39", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_40 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_40", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_59 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_59", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_59 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_59", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_79 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_79", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_79 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_79", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_80 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_80", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_99 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_99", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_120 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_120", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_149 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_149", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_160 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_160", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_174 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_174", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[18].tick_199 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[18].tick_199", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[18].end set value 199
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[18].end", 199, "int");
        return 0;
    }

    public static void _m_4383aeb5_api_map_data_forest_temple_easy_2_execute(ServerCommandSource source) {
        _m_4383aeb5_api_map_data_forest_temple_easy_2_executeReturn(source);
    }

    public static int _m_4383aeb5_api_map_data_forest_temple_easy_2_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[1].tick_0 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[1].tick_0", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[1].tick_30 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[1].tick_30", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[1].tick_60 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[1].tick_60", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[1].tick_90 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[1].tick_90", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[1].tick_120 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[1].tick_120", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[1].tick_150 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[1].tick_150", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[1].end set value 150
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[1].end", 150, "int");
        return 0;
    }

    public static void _m_feb47ec5_api_map_data_forest_temple_easy_20_execute(ServerCommandSource source) {
        _m_feb47ec5_api_map_data_forest_temple_easy_20_executeReturn(source);
    }

    public static int _m_feb47ec5_api_map_data_forest_temple_easy_20_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[19].tick_0 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_0", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_0 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_0", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_20 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_20", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_29 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_29", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_40 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_40", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_49 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_49", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_54 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_54", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_60 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_60", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_69 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_69", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_74 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_74", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_89 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_89", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_94 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_94", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_99 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_99", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_100 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_100", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_120 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_120", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_140 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_140", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[19].tick_180 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[19].tick_180", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[19].end set value 180
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[19].end", 180, "int");
        return 0;
    }

    public static void _m_e110e29d_api_map_data_forest_temple_easy_21_execute(ServerCommandSource source) {
        _m_e110e29d_api_map_data_forest_temple_easy_21_executeReturn(source);
    }

    public static int _m_e110e29d_api_map_data_forest_temple_easy_21_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[20].tick_0 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_0", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_19 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_19", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_19 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_19", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_39 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_39", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_39 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_39", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_49 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_49", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_50 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_50", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_59 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_59", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_59 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_59", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_79 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_79", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_79 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_79", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_99 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_99", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_99 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_99", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_100 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_100", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[20].tick_149 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[20].tick_149", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[20].end set value 149
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[20].end", 149, "int");
        return 0;
    }

    public static void _m_7e25396e_api_map_data_forest_temple_easy_22_execute(ServerCommandSource source) {
        _m_7e25396e_api_map_data_forest_temple_easy_22_executeReturn(source);
    }

    public static int _m_7e25396e_api_map_data_forest_temple_easy_22_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[21].tick_0 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_0", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_0 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_0", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_9 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_9", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_19 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_19", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_20 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_20", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_39 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_39", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_40 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_40", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_59 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_59", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_100 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_100", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[21].tick_200 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[21].tick_200", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[21].end set value 200
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[21].end", 200, "int");
        return 0;
    }

    public static void _m_d1a1f81a_api_map_data_forest_temple_easy_23_execute(ServerCommandSource source) {
        _m_d1a1f81a_api_map_data_forest_temple_easy_23_executeReturn(source);
    }

    public static int _m_d1a1f81a_api_map_data_forest_temple_easy_23_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[22].tick_0 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_0", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_4 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_4", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_19 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_19", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_24 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_24", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_39 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_39", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_44 set value {difficulty:easy,map:forest_temple,model:silverfish}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_44", "{difficulty:easy,map:forest_temple,model:silverfish}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_149 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_149", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_199 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_199", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_199 set value {difficulty:easy,map:forest_temple,model:endermite}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_199", "{difficulty:easy,map:forest_temple,model:endermite}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_199 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_199", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_199 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_199", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_209 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_209", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_209 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_209", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_219 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_219", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_229 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_229", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_239 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_239", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_249 set value {difficulty:easy,map:forest_temple,model:endermite}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_249", "{difficulty:easy,map:forest_temple,model:endermite}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_249 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_249", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_259 set value {difficulty:easy,map:forest_temple,model:spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_259", "{difficulty:easy,map:forest_temple,model:spider}", "set");

        // data modify storage map forest_temple.easy.round[22].tick_269 set value {difficulty:easy,map:forest_temple,model:cave-spider}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[22].tick_269", "{difficulty:easy,map:forest_temple,model:cave-spider}", "set");

        // data modify storage map forest_temple.easy.round[22].end set value 269
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[22].end", 269, "int");
        return 0;
    }

    public static void _m_4e5a0164_api_map_data_forest_temple_easy_24_execute(ServerCommandSource source) {
        _m_4e5a0164_api_map_data_forest_temple_easy_24_executeReturn(source);
    }

    public static int _m_4e5a0164_api_map_data_forest_temple_easy_24_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[23].tick_0 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_0", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_39 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_39", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_49 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_49", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_69 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_69", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_79 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_79", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_89 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_89", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_109 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_109", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_109 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_109", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_119 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_119", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_124 set value {difficulty:easy,map:forest_temple,model:endermite}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_124", "{difficulty:easy,map:forest_temple,model:endermite}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_129 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_129", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_129 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_129", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_149 set value {difficulty:easy,map:forest_temple,model:endermite}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_149", "{difficulty:easy,map:forest_temple,model:endermite}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_149 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_149", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_169 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_169", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_174 set value {difficulty:easy,map:forest_temple,model:endermite}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_174", "{difficulty:easy,map:forest_temple,model:endermite}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_189 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_189", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_209 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_209", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_229 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_229", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_249 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_249", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_269 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_269", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].tick_289 set value {difficulty:easy,map:forest_temple,model:shiled-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[23].tick_289", "{difficulty:easy,map:forest_temple,model:shiled-zombie}", "set");

        // data modify storage map forest_temple.easy.round[23].end set value 289
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[23].end", 289, "int");
        return 0;
    }

    public static void _m_3434fe8d_api_map_data_forest_temple_easy_25_execute(ServerCommandSource source) {
        _m_3434fe8d_api_map_data_forest_temple_easy_25_executeReturn(source);
    }

    public static int _m_3434fe8d_api_map_data_forest_temple_easy_25_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[24].tick_0 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_0", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_24 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_24", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_49 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_49", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_59 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_59", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_69 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_69", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_79 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_79", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_89 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_89", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_99 set value {difficulty:easy,map:forest_temple,model:endermite}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_99", "{difficulty:easy,map:forest_temple,model:endermite}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_99 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_99", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_99 set value {difficulty:easy,map:forest_temple,model:giant}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_99", "{difficulty:easy,map:forest_temple,model:giant}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_119 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_119", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_124 set value {difficulty:easy,map:forest_temple,model:endermite}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_124", "{difficulty:easy,map:forest_temple,model:endermite}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_139 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_139", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_149 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_149", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_149 set value {difficulty:easy,map:forest_temple,model:endermite}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_149", "{difficulty:easy,map:forest_temple,model:endermite}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_159 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_159", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_179 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_179", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_249 set value {difficulty:easy,map:forest_temple,model:warden}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_249", "{difficulty:easy,map:forest_temple,model:warden}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_259 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_259", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_274 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_274", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_279 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_279", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_299 set value {difficulty:easy,map:forest_temple,model:horse-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_299", "{difficulty:easy,map:forest_temple,model:horse-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_309 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_309", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_319 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_319", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_329 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_329", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].tick_339 set value {difficulty:easy,map:forest_temple,model:split-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[24].tick_339", "{difficulty:easy,map:forest_temple,model:split-zombie}", "set");

        // data modify storage map forest_temple.easy.round[24].end set value 340
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[24].end", 340, "int");
        return 0;
    }

    public static void _m_56b55c63_api_map_data_forest_temple_easy_3_execute(ServerCommandSource source) {
        _m_56b55c63_api_map_data_forest_temple_easy_3_executeReturn(source);
    }

    public static int _m_56b55c63_api_map_data_forest_temple_easy_3_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[2].tick_0 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[2].tick_0", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[2].tick_9 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[2].tick_9", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[2].tick_24 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[2].tick_24", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[2].tick_39 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[2].tick_39", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[2].tick_40 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[2].tick_40", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[2].tick_54 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[2].tick_54", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[2].tick_69 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[2].tick_69", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[2].end set value 69
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[2].end", 69, "int");
        return 0;
    }

    public static void _m_70a15202_api_map_data_forest_temple_easy_4_execute(ServerCommandSource source) {
        _m_70a15202_api_map_data_forest_temple_easy_4_executeReturn(source);
    }

    public static int _m_70a15202_api_map_data_forest_temple_easy_4_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[3].tick_0 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_0", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_9 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_9", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_24 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_24", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_25 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_25", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_39 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_39", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_49 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_49", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_54 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_54", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_69 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_69", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_74 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_74", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_84 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_84", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].tick_99 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[3].tick_99", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[3].end set value 99
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[3].end", 99, "int");
        return 0;
    }

    public static void _m_7d789636_api_map_data_forest_temple_easy_5_execute(ServerCommandSource source) {
        _m_7d789636_api_map_data_forest_temple_easy_5_executeReturn(source);
    }

    public static int _m_7d789636_api_map_data_forest_temple_easy_5_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[4].tick_0 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[4].tick_0", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[4].tick_9 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[4].tick_9", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[4].tick_39 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[4].tick_39", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[4].tick_74 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[4].tick_74", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[4].tick_89 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[4].tick_89", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[4].end set value 89
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[4].end", 89, "int");
        return 0;
    }

    public static void _m_7d896e5a_api_map_data_forest_temple_easy_6_execute(ServerCommandSource source) {
        _m_7d896e5a_api_map_data_forest_temple_easy_6_executeReturn(source);
    }

    public static int _m_7d896e5a_api_map_data_forest_temple_easy_6_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[5].tick_0 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_0", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].tick_1 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_1", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].tick_2 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_2", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].tick_24 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_24", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].tick_29 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_29", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].tick_30 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_30", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].tick_49 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_49", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].tick_60 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_60", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].tick_60 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[5].tick_60", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[5].end set value 60
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[5].end", 60, "int");
        return 0;
    }

    public static void _m_e3e7bf7a_api_map_data_forest_temple_easy_7_execute(ServerCommandSource source) {
        _m_e3e7bf7a_api_map_data_forest_temple_easy_7_executeReturn(source);
    }

    public static int _m_e3e7bf7a_api_map_data_forest_temple_easy_7_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage map forest_temple.easy.round append value {}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round", "{}", "append");

        // data modify storage map forest_temple.easy.round[6].tick_0 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_0", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_0 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_0", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_20 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_20", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_40 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_40", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_60 set value {difficulty:easy,map:forest_temple,model:heavy-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_60", "{difficulty:easy,map:forest_temple,model:heavy-zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_119 set value {difficulty:easy,map:forest_temple,model:quick-zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_119", "{difficulty:easy,map:forest_temple,model:quick-zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_139 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_139", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_159 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_159", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_179 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_179", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].tick_199 set value {difficulty:easy,map:forest_temple,model:zombie}
        KfcGen.storagePutSnbt(server, "map", "forest_temple.easy.round[6].tick_199", "{difficulty:easy,map:forest_temple,model:zombie}", "set");

        // data modify storage map forest_temple.easy.round[6].end set value 199
        KfcGen.storagePutNumber(server, "map", "forest_temple.easy.round[6].end", 199, "int");
        return 0;
    }
}
