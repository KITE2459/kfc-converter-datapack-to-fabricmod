package tdpack.buckets;

import tdpack.generated.KfcGen;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/** Auto-bucketed: 97 datapack functions. */
public final class Bucket5 {
    private Bucket5() { throw new UnsupportedOperationException(); }

    public static void _m_d74fd68f_enemy_ability_timer_rabbit_main_execute(ServerCommandSource source) {
        _m_d74fd68f_enemy_ability_timer_rabbit_main_executeReturn(source);
    }

    public static int _m_d74fd68f_enemy_ability_timer_rabbit_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy temp set from entity @s data
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data"); if (_v != null) KfcGen.nbtSetStorage(server, "enemy", "temp", _v); }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run playsound minecraft:entity.rabbit.jump record @a ~ ~ ~ 1.0 1.0
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.rabbit.jump", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] positioned ~ -59.5 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc87 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -59.5, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc87, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-1}] positioned ~ -59.1 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc88 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -59.1, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -1, -1))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc88, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-2}] positioned ~ -58.8 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc89 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.8, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -2, -2))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc89, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-3}] positioned ~ -58.6 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc90 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.6, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -3, -3))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc90, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-4}] positioned ~ -58.5 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc91 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.5, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -4, -4))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc91, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-5}] positioned ~ -58.6 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc92 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.6, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -5, -5))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc92, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-6}] positioned ~ -58.8 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc93 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.8, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -6, -6))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc93, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-7}] positioned ~ -59.1 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc94 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -59.1, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -7, -7))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc94, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-8}] positioned ~ -59.5 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc95 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -59.5, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -8, -8))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc95, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-9}] positioned ~ -60.0 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc96 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -9, -9))) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc96, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 1
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -20, -20))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 1);
        }
        return 0;
    }

    public static void _m_f8f76f99_enemy_ability_timer_raid_lord_call_horn_execute(ServerCommandSource source) {
        _m_f8f76f99_enemy_ability_timer_raid_lord_call_horn_executeReturn(source);
    }

    public static int _m_f8f76f99_enemy_ability_timer_raid_lord_call_horn_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players operation #temp enemy.skill-trigger.timer-cooldown = @s enemy.skill-trigger.timer-cooldown
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.skill-trigger.timer-cooldown", "=", executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown");

        // playsound item.goat_horn.sound.6 weather @a ~ ~ ~
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "item.goat_horn.sound.6", "weather", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
        }

        // execute as @n[tag=map.spawn_point,tag=game] at @s run function enemy:ability/timer/raid-lord/call-horn-spawn_point
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.spawn_point", "game"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc100 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:ability/timer/raid-lord/call-horn-spawn_point
            tdpack.buckets.Bucket5._m_934cb6c1_enemy_ability_timer_raid_lord_call_horn_spawn_point_execute(kfcSrc100);
        } }

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-101}] remove enemy.skill-loop.2
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -101))) executor.removeCommandTag("enemy.skill-loop.2");

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-101}] add enemy.skill-loop.3
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -101))) executor.addCommandTag("enemy.skill-loop.3");

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -101 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -101, -101)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        }
        return 0;
    }

    public static void _m_934cb6c1_enemy_ability_timer_raid_lord_call_horn_spawn_point_execute(ServerCommandSource source) {
        _m_934cb6c1_enemy_ability_timer_raid_lord_call_horn_spawn_point_executeReturn(source);
    }

    public static int _m_934cb6c1_enemy_ability_timer_raid_lord_call_horn_spawn_point_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #5 enemy.skill-trigger.timer-cooldown 5
        KfcGen.setScore(sb, "#5", "enemy.skill-trigger.timer-cooldown", 5);

        // scoreboard players operation #temp2 enemy.skill-trigger.timer-cooldown = #temp enemy.skill-trigger.timer-cooldown
        KfcGen.opScore(sb, "#temp2", "enemy.skill-trigger.timer-cooldown", "=", "#temp", "enemy.skill-trigger.timer-cooldown");

        // scoreboard players operation #temp2 enemy.skill-trigger.timer-cooldown %= #5 enemy.skill-trigger.timer-cooldown
        KfcGen.opScore(sb, "#temp2", "enemy.skill-trigger.timer-cooldown", "%=", "#5", "enemy.skill-trigger.timer-cooldown");

        // execute if score #temp enemy.skill-trigger.timer-cooldown matches -45..-20 if score #temp2 enemy.skill-trigger.timer-cooldown matches 0 run function api:enemy/spawn {model:vindicator,difficulty:hard,map:forest_temple}
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.skill-trigger.timer-cooldown", -45, -20)) {
            if (KfcGen.scoreMatches(sb, "#temp2", "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                // -> api:enemy/spawn
                tdpack.buckets.Bucket0._m_dcd1441b_api_enemy_spawn_execute(source, KfcGen.macroArgs("model", "vindicator", "difficulty", "hard", "map", "forest_temple"));
            }
        }

        // execute if score #temp enemy.skill-trigger.timer-cooldown matches -80..-61 if score #temp2 enemy.skill-trigger.timer-cooldown matches 0 run function api:enemy/spawn {model:pillager,difficulty:hard,map:forest_temple}
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.skill-trigger.timer-cooldown", -80, -61)) {
            if (KfcGen.scoreMatches(sb, "#temp2", "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                // -> api:enemy/spawn
                tdpack.buckets.Bucket0._m_dcd1441b_api_enemy_spawn_execute(source, KfcGen.macroArgs("model", "pillager", "difficulty", "hard", "map", "forest_temple"));
            }
        }

        // execute if score #temp enemy.skill-trigger.timer-cooldown matches -40 run function api:enemy/spawn {model:illusioner,difficulty:hard,map:forest_temple}
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            // -> api:enemy/spawn
            tdpack.buckets.Bucket0._m_dcd1441b_api_enemy_spawn_execute(source, KfcGen.macroArgs("model", "illusioner", "difficulty", "hard", "map", "forest_temple"));
        }

        // execute if score #temp enemy.skill-trigger.timer-cooldown matches -50 run function api:enemy/spawn {model:ravager,difficulty:hard,map:forest_temple}
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.skill-trigger.timer-cooldown", -50, -50)) {
            // -> api:enemy/spawn
            tdpack.buckets.Bucket0._m_dcd1441b_api_enemy_spawn_execute(source, KfcGen.macroArgs("model", "ravager", "difficulty", "hard", "map", "forest_temple"));
        }

        // execute if score #temp enemy.skill-trigger.timer-cooldown matches -60 run function api:enemy/spawn {model:evoker,difficulty:hard,map:forest_temple}
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.skill-trigger.timer-cooldown", -60, -60)) {
            // -> api:enemy/spawn
            tdpack.buckets.Bucket0._m_dcd1441b_api_enemy_spawn_execute(source, KfcGen.macroArgs("model", "evoker", "difficulty", "hard", "map", "forest_temple"));
        }
        return 0;
    }

    public static void _m_f6cf5363_enemy_ability_timer_raid_lord_healing_crystal_execute(ServerCommandSource source) {
        _m_f6cf5363_enemy_ability_timer_raid_lord_healing_crystal_executeReturn(source);
    }

    public static int _m_f6cf5363_enemy_ability_timer_raid_lord_healing_crystal_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~ ~ ~-2 run function enemy:spawn/summon/main {model:healing-crystal}
        {
            ServerCommandSource kfcSrc101 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, (source.getPosition().z + -2.0)));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                // -> enemy:spawn/summon/main
                tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(kfcSrc101, KfcGen.macroArgs("model", "healing-crystal"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~2 ~ ~ run function enemy:spawn/summon/main {model:healing-crystal}
        {
            ServerCommandSource kfcSrc102 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + 2.0), source.getPosition().y, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                // -> enemy:spawn/summon/main
                tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(kfcSrc102, KfcGen.macroArgs("model", "healing-crystal"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~-2 ~ ~ run function enemy:spawn/summon/main {model:healing-crystal}
        {
            ServerCommandSource kfcSrc103 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -2.0), source.getPosition().y, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                // -> enemy:spawn/summon/main
                tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(kfcSrc103, KfcGen.macroArgs("model", "healing-crystal"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~ ~ ~2 run function enemy:spawn/summon/main {model:healing-crystal}
        {
            ServerCommandSource kfcSrc104 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, (source.getPosition().z + 2.0)));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                // -> enemy:spawn/summon/main
                tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(kfcSrc104, KfcGen.macroArgs("model", "healing-crystal"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~ ~ ~-2 run tag @n[tag=enemy.data,nbt={data:{id:"healing-crystal"}},distance=..1,sort=nearest,limit=1] add enemy.skill.raid-lord.healing-crystal
        {
            ServerCommandSource kfcSrc105 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, (source.getPosition().z + -2.0)));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc105.getPosition(), new String[]{"enemy.data"}, new String[0], -1, 1, _ee -> (KfcGen.nbtMatches(_ee, "{data:{id:\"healing-crystal\"}}", false))); if (_t != null) _t.addCommandTag("enemy.skill.raid-lord.healing-crystal"); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~2 ~ ~ run tag @n[tag=enemy.data,nbt={data:{id:"healing-crystal"}},distance=..1,sort=nearest,limit=1] add enemy.skill.raid-lord.healing-crystal
        {
            ServerCommandSource kfcSrc106 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + 2.0), source.getPosition().y, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc106.getPosition(), new String[]{"enemy.data"}, new String[0], -1, 1, _ee -> (KfcGen.nbtMatches(_ee, "{data:{id:\"healing-crystal\"}}", false))); if (_t != null) _t.addCommandTag("enemy.skill.raid-lord.healing-crystal"); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~-2 ~ ~ run tag @n[tag=enemy.data,nbt={data:{id:"healing-crystal"}},distance=..1,sort=nearest,limit=1] add enemy.skill.raid-lord.healing-crystal
        {
            ServerCommandSource kfcSrc107 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -2.0), source.getPosition().y, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc107.getPosition(), new String[]{"enemy.data"}, new String[0], -1, 1, _ee -> (KfcGen.nbtMatches(_ee, "{data:{id:\"healing-crystal\"}}", false))); if (_t != null) _t.addCommandTag("enemy.skill.raid-lord.healing-crystal"); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~ ~ ~2 run tag @n[tag=enemy.data,nbt={data:{id:"healing-crystal"}},distance=..1,sort=nearest,limit=1] add enemy.skill.raid-lord.healing-crystal
        {
            ServerCommandSource kfcSrc108 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, (source.getPosition().z + 2.0)));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc108.getPosition(), new String[]{"enemy.data"}, new String[0], -1, 1, _ee -> (KfcGen.nbtMatches(_ee, "{data:{id:\"healing-crystal\"}}", false))); if (_t != null) _t.addCommandTag("enemy.skill.raid-lord.healing-crystal"); }
            }
        }

        // execute if entity @e[tag=enemy.skill.raid-lord.healing-crystal] run scoreboard players set @s enemy.attribute.healing 750
        if (KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy.skill.raid-lord.healing-crystal"}, new String[0], -1, -1)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.attribute.healing", 750);
        }

        // execute if entity @e[tag=enemy.skill.raid-lord.healing-crystal] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 0
        if (KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy.skill.raid-lord.healing-crystal"}, new String[0], -1, -1)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0);
        }

        // execute unless entity @e[tag=enemy.skill.raid-lord.healing-crystal] run scoreboard players set @s enemy.attribute.healing 0
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy.skill.raid-lord.healing-crystal"}, new String[0], -1, -1))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.attribute.healing", 0);
        }

        // execute unless entity @e[tag=enemy.skill.raid-lord.healing-crystal] run tag @s remove enemy.skill-loop.3
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy.skill.raid-lord.healing-crystal"}, new String[0], -1, -1))) {
            if (executor != null) executor.removeCommandTag("enemy.skill-loop.3");
        }

        // execute unless entity @e[tag=enemy.skill.raid-lord.healing-crystal] run tag @s add enemy.skill-loop.1
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy.skill.raid-lord.healing-crystal"}, new String[0], -1, -1))) {
            if (executor != null) executor.addCommandTag("enemy.skill-loop.1");
        }

        // execute unless entity @e[tag=enemy.skill.raid-lord.healing-crystal] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy.skill.raid-lord.healing-crystal"}, new String[0], -1, -1))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        }
        return 0;
    }

    public static void _m_910b2235_enemy_ability_timer_raid_lord_stun_tower_execute(ServerCommandSource source) {
        _m_910b2235_enemy_ability_timer_raid_lord_stun_tower_executeReturn(source);
    }

    public static int _m_910b2235_enemy_ability_timer_raid_lord_stun_tower_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..10] tower.state.stun 55
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -20, -20))) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, 10)) {
                if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 55);
            }
        }

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] remove enemy.skill-loop.1
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -40))) executor.removeCommandTag("enemy.skill-loop.1");

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] add enemy.skill-loop.2
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -40))) executor.addCommandTag("enemy.skill-loop.2");

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        }
        return 0;
    }

    public static void _m_7a5270ad_enemy_ability_timer_sculk_titan_ground_break_execute(ServerCommandSource source) {
        _m_7a5270ad_enemy_ability_timer_sculk_titan_ground_break_executeReturn(source);
    }

    public static int _m_7a5270ad_enemy_ability_timer_sculk_titan_ground_break_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] at @n[tag=tower.core] run particle minecraft:block{block_state:"minecraft:blackstone"} ~ ~0.1 ~ 2 0.2 2 0 500 force
        {
            net.minecraft.entity.Entity _atE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
            ServerCommandSource kfcSrc111 = (_atE1 != null ? source.withPosition(_atE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_atE1.getPitch(), _atE1.getYaw())) : null);
            if (kfcSrc111 != null) {
                if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -20, -20))) {
                    { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc111.getPosition().x, (kfcSrc111.getPosition().y + 0.1), kfcSrc111.getPosition().z); KfcGen.spawnParticleParsed(ctx.world, "minecraft:block{block_state:\"minecraft:blackstone\"}", _pp.x, _pp.y, _pp.z, 2, 0.2, 2, 0, (int)(500), true, null); }
                }
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] at @n[tag=tower.core] run scoreboard players set @e[tag=tower.data,distance=..5] tower.state.stun 120
        {
            net.minecraft.entity.Entity _atE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
            ServerCommandSource kfcSrc112 = (_atE1 != null ? source.withPosition(_atE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_atE1.getPitch(), _atE1.getYaw())) : null);
            if (kfcSrc112 != null) {
                if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -20, -20))) {
                    for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc112.getPosition(), new String[]{"tower.data"}, new String[0], -1, 5)) {
                        KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                    }
                }
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] at @n[tag=tower.core] run playsound minecraft:block.anvil.place record @a ~ ~ ~ 0.5 0.5
        {
            net.minecraft.entity.Entity _atE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
            ServerCommandSource kfcSrc113 = (_atE1 != null ? source.withPosition(_atE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_atE1.getPitch(), _atE1.getYaw())) : null);
            if (kfcSrc113 != null) {
                if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -20, -20))) {
                    for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                        KfcGen.playSound(_ps, "minecraft:block.anvil.place", "record", new net.minecraft.util.math.Vec3d(kfcSrc113.getPosition().x, kfcSrc113.getPosition().y, kfcSrc113.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc113.getPosition().x, kfcSrc113.getPosition().y, kfcSrc113.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc113.getPosition().x, kfcSrc113.getPosition().y, kfcSrc113.getPosition().z).z, 0.5f, 0.5f);
                    }
                }
            }
        }

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] remove enemy.skill-loop.2
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -40))) executor.removeCommandTag("enemy.skill-loop.2");

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] add enemy.skill-loop.1
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -40))) executor.addCommandTag("enemy.skill-loop.1");

        // scoreboard players set @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] enemy.skill-trigger.timer-cooldown 150
        if (executor != null && ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -40)))) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        return 0;
    }

    public static void _m_a5751313_enemy_ability_timer_sculk_titan_sculk_sonicwave_execute(ServerCommandSource source) {
        _m_a5751313_enemy_ability_timer_sculk_titan_sculk_sonicwave_executeReturn(source);
    }

    public static int _m_a5751313_enemy_ability_timer_sculk_titan_sculk_sonicwave_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run data modify entity @s data.last_rotation set from entity @s Rotation
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "data.last_rotation", _v); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run rotate @s facing entity @e[scores={tower.y=-60},tag=tower.data,sort=furthest,limit=1] eyes
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.entity.Entity _rt = executor, _fe = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (KfcGen.scoreMatches(sb, _ee.getNameForScoreboard(), "tower.y", -60, -60))); if (_rt != null && _fe != null) KfcGen.rotateToFaceEntity(_rt, _fe, true); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound entity.warden.sonic_charge record @a ~ ~ ~ 1.0 1.0 0.5
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "entity.warden.sonic_charge", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -90..-40 run playsound entity.warden.sonic_boom record @a ~ ~ ~ 1.0 1.0 0.5
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -90, -40)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "entity.warden.sonic_boom", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -90..-40 run summon marker ~ ~2 ~ {Tags:[sculk-sonicwave.blast]}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -90, -40)) {
            { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 2.0), source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[sculk-sonicwave.blast]}"); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -91..-40 run data modify entity @n[tag=sculk-sonicwave.blast] Rotation set from entity @s Rotation
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -91, -40)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"sculk-sonicwave.blast"}, new String[0], -1, -1, _ee -> (true)), "Rotation", _v); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -90..-40 run scoreboard players set @e[tag=sculk-sonicwave.blast,distance=..2] enemy.hp 50
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -90, -40)) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"sculk-sonicwave.blast"}, new String[0], -1, 2)) {
                KfcGen.setScore(sb, _t.getNameForScoreboard(), "enemy.hp", 50);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -50..-40 run rotate @s ~3 0
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -50, -40)) {
            if (executor != null) KfcGen.rotateTo(executor, (source.getRotation().y + 3.0f), 0.0f);
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -70..-50 run rotate @s ~-3 0
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -70, -50)) {
            if (executor != null) KfcGen.rotateTo(executor, (source.getRotation().y + -3.0f), 0.0f);
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -90..-70 run rotate @s ~3 0
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -90, -70)) {
            if (executor != null) KfcGen.rotateTo(executor, (source.getRotation().y + 3.0f), 0.0f);
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -92 run data modify entity @s Rotation set from entity @s data.last_rotation
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -92, -92)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data.last_rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "Rotation", _v); }
        }

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-100}] remove enemy.skill-loop.2
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -100))) executor.removeCommandTag("enemy.skill-loop.2");

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-100}] add enemy.skill-loop.1
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -100))) executor.addCommandTag("enemy.skill-loop.1");

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 300
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -100, -100)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 300);
        }
        return 0;
    }

    public static void _m_b412dbb8_enemy_ability_timer_sculk_titan_sculk_sonicwave_blast_execute(ServerCommandSource source) {
        _m_b412dbb8_enemy_ability_timer_sculk_titan_sculk_sonicwave_blast_executeReturn(source);
    }

    public static int _m_b412dbb8_enemy_ability_timer_sculk_titan_sculk_sonicwave_blast_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 85
        for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
            KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 85);
        }

        // particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }

        // tp @s ^ ^ ^1
        {
            net.minecraft.util.math.Vec3d _tpPos = KfcGen.localOffset(source.getPosition(), source.getRotation(), 0.0, 0.0, 1.0);
        if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
        }

        // scoreboard players remove @s enemy.hp 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.hp", -(1));

        // execute if score @s enemy.hp matches ..0 run kill @s
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", Integer.MIN_VALUE, 0)) {
            if (executor != null) KfcGen.killEntity(executor);
        }
        return 0;
    }

    public static void _m_a1b182e5_enemy_ability_timer_sculk_titan_stun_tower_execute(ServerCommandSource source) {
        _m_a1b182e5_enemy_ability_timer_sculk_titan_stun_tower_executeReturn(source);
    }

    public static int _m_a1b182e5_enemy_ability_timer_sculk_titan_stun_tower_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @n[tag=tower.core,distance=..5] run function enemy:ability/timer/sculk-titan/ground-break
        if (KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, 5)) {
            // -> enemy:ability/timer/sculk-titan/ground-break
            tdpack.buckets.Bucket5._m_7a5270ad_enemy_ability_timer_sculk_titan_ground_break_execute(source);
        }

        // execute unless entity @n[tag=tower.core,distance=..5] if entity @n[tag=tower.core] run function enemy:ability/timer/sculk-titan/sculk-sonicwave
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, 5))) {
            if (KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1)) {
                // -> enemy:ability/timer/sculk-titan/sculk-sonicwave
                tdpack.buckets.Bucket5._m_a5751313_enemy_ability_timer_sculk_titan_sculk_sonicwave_execute(source);
            }
        }

        // execute unless entity @n[tag=tower.core] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 10);
        }
        return 0;
    }

    public static void _m_73c6526e_enemy_ability_timer_sculk_titan_summon_zombie_execute(ServerCommandSource source) {
        _m_73c6526e_enemy_ability_timer_sculk_titan_summon_zombie_executeReturn(source);
    }

    public static int _m_73c6526e_enemy_ability_timer_sculk_titan_summon_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run summon marker ~ ~ ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:5,model:skulk-zombie}}
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:5,model:skulk-zombie}}"); }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)), "Rotation", _v); }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
              if (_vE1 != null) {
                ServerCommandSource _vS1 = source.withEntity(_vE1);
                int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.tick", (int)(_stv));
            } }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
              if (_vE1 != null) {
                ServerCommandSource _vS1 = source.withEntity(_vE1);
                int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.count", (int)(_stv));
            } }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
              if (_vE1 != null) {
                ServerCommandSource _vS1 = source.withEntity(_vE1);
                int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_count"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.number", (int)(_stv));
            } }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "enemy.progress", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress")); }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] as @n[tag=mob-gen,tag=not-allocated] run tag @s remove not-allocated
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                if (e != null) e.removeCommandTag("not-allocated");
            } }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run tag @s add map.spawn_point
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            if (executor != null) executor.addCommandTag("map.spawn_point");
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run function enemy:spawn/summon/main {model:catalyst-zombie}
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "catalyst-zombie"));
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run tag @s remove map.spawn_point
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            if (executor != null) executor.removeCommandTag("map.spawn_point");
        }

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-50}] remove enemy.skill-loop.1
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -50))) executor.removeCommandTag("enemy.skill-loop.1");

        // tag @s[scores={enemy.skill-trigger.timer-cooldown=..-50}] add enemy.skill-loop.2
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -50))) executor.addCommandTag("enemy.skill-loop.2");

        // scoreboard players set @s[scores={enemy.skill-trigger.timer-cooldown=..-50}] enemy.skill-trigger.timer-cooldown 300
        if (executor != null && ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, -50)))) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 300);
        return 0;
    }

    public static void _m_d51aab57_enemy_ability_timer_skeleton_main_execute(ServerCommandSource source) {
        _m_d51aab57_enemy_ability_timer_skeleton_main_executeReturn(source);
    }

    public static int _m_d51aab57_enemy_ability_timer_skeleton_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] unless entity @n[tag=tower.data,scores={tower.y=-60}] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
            if (!(KfcGen.anyEntityWhere(ctx, _se -> (_se.getCommandTags().contains("tower.data") && KfcGen.scoreMatches(sb, _se.getNameForScoreboard(), "tower.y", -60, -60))))) {
                if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 10);
            }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] run scoreboard players set @n[tag=tower.data,scores={tower.y=-60}] tower.state.stun 55
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -20, -20))) {
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (KfcGen.scoreMatches(sb, _ee.getNameForScoreboard(), "tower.y", -60, -60))); if (_t != null) KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 55); }
        }

        // execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-40}] run scoreboard players operation @s enemy.skill-trigger.timer-cooldown = @s enemy.skill-trigger.timer
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -40, -40))) {
            if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", "=", executor.getNameForScoreboard(), "enemy.skill-trigger.timer");
        }
        return 0;
    }

    public static void _m_523872e0_enemy_animation_check_buff_execute(ServerCommandSource source) {
        _m_523872e0_enemy_animation_check_buff_executeReturn(source);
    }

    public static int _m_523872e0_enemy_animation_check_buff_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.attribute.healing matches 1.. run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.attribute.healing", 1, Integer.MAX_VALUE)) {
            return 1;
        }

        // execute on vehicle if entity @s[tag=enemy.attribute.heavy] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.attribute.heavy"))) {
                return 1;
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.attribute.speed] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.attribute.speed"))) {
                return 1;
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.attribute.taunt] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.attribute.taunt"))) {
                return 1;
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.attribute.dark] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.attribute.dark"))) {
                return 1;
            }
          } }

        // return 0
        return 0;
    }

    public static void _m_c10081dd_enemy_animation_check_debuff_execute(ServerCommandSource source) {
        _m_c10081dd_enemy_animation_check_debuff_executeReturn(source);
    }

    public static int _m_c10081dd_enemy_animation_check_debuff_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.state.freeze matches 1.. run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.freeze", 1, Integer.MAX_VALUE)) {
            return 1;
        }

        // execute if score @s enemy.state.poison matches 1.. run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison", 1, Integer.MAX_VALUE)) {
            return 1;
        }

        // execute if score @s enemy.state.flame matches 1.. run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 1, Integer.MAX_VALUE)) {
            return 1;
        }

        // execute if score @s enemy.state.bleed matches 1.. run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.bleed", 1, Integer.MAX_VALUE)) {
            return 1;
        }

        // execute if score @s enemy.state.stun matches 1.. run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun", 1, Integer.MAX_VALUE)) {
            return 1;
        }

        // execute if score @s enemy.state.weakness matches 1.. run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 1, Integer.MAX_VALUE)) {
            return 1;
        }

        // execute if score @s enemy.state.corruption matches 1..99 run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.corruption", 1, 99)) {
            return 1;
        }

        // return 0
        return 0;
    }

    public static void _m_f9a1e0f0_enemy_animation_check_immune_execute(ServerCommandSource source) {
        _m_f9a1e0f0_enemy_animation_check_immune_executeReturn(source);
    }

    public static int _m_f9a1e0f0_enemy_animation_check_immune_executeReturn(ServerCommandSource source) {
        
        // execute on vehicle if entity @s[tag=enemy.immune.bleed] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.bleed"))) {
                return 1;
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.flame] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.flame"))) {
                return 1;
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.poison] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.poison"))) {
                return 1;
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.freeze] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.freeze"))) {
                return 1;
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.stun] run return 1
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.stun"))) {
                return 1;
            }
          } }

        // return 0
        return 0;
    }

    public static void _m_0c0142e3_enemy_animation_poison_text_execute(ServerCommandSource source) {
        _m_0c0142e3_enemy_animation_poison_text_executeReturn(source);
    }

    public static int _m_0c0142e3_enemy_animation_poison_text_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set #10 enemy.state.poison 10
        KfcGen.setScore(sb, "#10", "enemy.state.poison", 10);

        // scoreboard players set #100 enemy.state.poison 100
        KfcGen.setScore(sb, "#100", "enemy.state.poison", 100);

        // scoreboard players set #1000 enemy.state.poison 1000
        KfcGen.setScore(sb, "#1000", "enemy.state.poison", 1000);

        // scoreboard players operation #temp-degit-1 enemy.state.poison = @s enemy.state.poison
        if (executor != null) KfcGen.opScore(sb, "#temp-degit-1", "enemy.state.poison", "=", executor.getNameForScoreboard(), "enemy.state.poison");

        // scoreboard players operation #temp-degit-1 enemy.state.poison %= #10 enemy.state.poison
        KfcGen.opScore(sb, "#temp-degit-1", "enemy.state.poison", "%=", "#10", "enemy.state.poison");

        // scoreboard players operation #temp-degit-10 enemy.state.poison = @s enemy.state.poison
        if (executor != null) KfcGen.opScore(sb, "#temp-degit-10", "enemy.state.poison", "=", executor.getNameForScoreboard(), "enemy.state.poison");

        // scoreboard players operation #temp-degit-10 enemy.state.poison %= #100 enemy.state.poison
        KfcGen.opScore(sb, "#temp-degit-10", "enemy.state.poison", "%=", "#100", "enemy.state.poison");

        // scoreboard players operation #temp-degit-10 enemy.state.poison /= #10 enemy.state.poison
        KfcGen.opScore(sb, "#temp-degit-10", "enemy.state.poison", "/=", "#10", "enemy.state.poison");

        // scoreboard players operation #temp-degit-100 enemy.state.poison = @s enemy.state.poison
        if (executor != null) KfcGen.opScore(sb, "#temp-degit-100", "enemy.state.poison", "=", executor.getNameForScoreboard(), "enemy.state.poison");

        // scoreboard players operation #temp-degit-100 enemy.state.poison /= #100 enemy.state.poison
        KfcGen.opScore(sb, "#temp-degit-100", "enemy.state.poison", "/=", "#100", "enemy.state.poison");

        // execute if score @s enemy.state.poison matches 100.. run data modify storage temp text append value [{text:"☠",color:green},{"score":{"name":"#temp-degit-100","objective":"enemy.state.poison"},color:white},{"score":{"name":"#temp-degit-10","objective":"enemy.state.poison"},color:white},{text:".",color:white},{"score":{"name":"#temp-degit-1","objective":"enemy.state.poison"},color:white},{text:"%",color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison", 100, Integer.MAX_VALUE)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"☠\",color:green},{\"score\":{\"name\":\"#temp-degit-100\",\"objective\":\"enemy.state.poison\"},color:white},{\"score\":{\"name\":\"#temp-degit-10\",\"objective\":\"enemy.state.poison\"},color:white},{text:\".\",color:white},{\"score\":{\"name\":\"#temp-degit-1\",\"objective\":\"enemy.state.poison\"},color:white},{text:\"%\",color:white}]", "append");
        }

        // execute if score @s enemy.state.poison matches 10..99 run data modify storage temp text append value [{text:"☠",color:green},{"score":{"name":"#temp-degit-10","objective":"enemy.state.poison"},color:white},{text:".",color:white},{"score":{"name":"#temp-degit-1","objective":"enemy.state.poison"},color:white},{text:"%",color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison", 10, 99)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"☠\",color:green},{\"score\":{\"name\":\"#temp-degit-10\",\"objective\":\"enemy.state.poison\"},color:white},{text:\".\",color:white},{\"score\":{\"name\":\"#temp-degit-1\",\"objective\":\"enemy.state.poison\"},color:white},{text:\"%\",color:white}]", "append");
        }

        // execute if score @s enemy.state.poison matches 1..9 run data modify storage temp text append value [{text:"☠",color:green},{text:"0.",color:white},{"score":{"name":"#temp-degit-1","objective":"enemy.state.poison"},color:white},{text:"%",color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison", 1, 9)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"☠\",color:green},{text:\"0.\",color:white},{\"score\":{\"name\":\"#temp-degit-1\",\"objective\":\"enemy.state.poison\"},color:white},{text:\"%\",color:white}]", "append");
        }
        return 0;
    }

    public static void _m_99732e02_enemy_animation_reload_text_execute(ServerCommandSource source) {
        _m_99732e02_enemy_animation_reload_text_executeReturn(source);
    }

    public static int _m_99732e02_enemy_animation_reload_text_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score @s enemy.hp on vehicle run scoreboard players get @s enemy.hp
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.hp"));
          } }

        // execute store result score @s enemy.defence on vehicle run scoreboard players get @s enemy.defence
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.defence"));
          } }

        // execute store result score @s enemy.state.freeze on vehicle run scoreboard players get @s enemy.state.freeze
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.freeze", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.state.freeze"));
          } }

        // execute store result score @s enemy.state.bleed on vehicle run scoreboard players get @s enemy.state.bleed
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.bleed", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.state.bleed"));
          } }

        // execute store result score @s enemy.state.poison on vehicle run scoreboard players get @s enemy.state.poison
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.state.poison"));
          } }

        // execute store result score @s enemy.state.flame on vehicle run scoreboard players get @s enemy.state.flame
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.state.flame"));
          } }

        // execute store result score @s enemy.state.stun on vehicle run scoreboard players get @s enemy.state.stun
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.state.stun"));
          } }

        // execute store result score @s enemy.state.weakness on vehicle run scoreboard players get @s enemy.state.weakness
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.state.weakness"));
          } }

        // execute store result score @s enemy.state.corruption on vehicle run scoreboard players get @s enemy.state.corruption
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.corruption", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.state.corruption"));
          } }

        // execute store result score @s enemy.attribute.healing on vehicle run scoreboard players get @s enemy.attribute.healing
        { net.minecraft.server.command.ServerCommandSource _stSrc = KfcGen.onVehicle(source);
          if (_stSrc != null) {
            net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.attribute.healing", KfcGen.getScore(sb, (_stSelf == null ? "<no-_stSelf>" : _stSelf.getNameForScoreboard()), "enemy.attribute.healing"));
          } }

        // execute on vehicle run data modify storage temp text set value [{text:""}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"\"}]", "set");
          } }

        // execute on vehicle run data modify storage temp text append from entity @s data.name
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(_onEnt1, "data.name"); if (_v != null) KfcGen.nbtAppendStorage(server, "temp", "text", _v, false); }
          } }

        // data modify storage temp text append value [{text:"Hp",color:red,bold:false},{text:":",color:white,bold:false},{score:{name:"@s",objective:"enemy.hp"},color:yellow,bold:false}]
        KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"Hp\",color:red,bold:false},{text:\":\",color:white,bold:false},{score:{name:\"@s\",objective:\"enemy.hp\"},color:yellow,bold:false}]", "append");

        // execute unless score @s enemy.defence matches 0 run data modify storage temp text append value [{text:" 🛡",color:gray},{"score":{"name":"@s","objective":"enemy.defence"},color:gray}]
        if (!(KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence", 0, 0))) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\" 🛡\",color:gray},{\"score\":{\"name\":\"@s\",\"objective\":\"enemy.defence\"},color:gray}]", "append");
        }

        // execute if function enemy:animation/check-buff run data modify storage temp text append value {text:"\n",color:aqua}
        if ((tdpack.buckets.Bucket5._m_523872e0_enemy_animation_check_buff_executeReturn(source) != 0)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "{text:\"\\n\",color:aqua}", "append");
        }

        // execute if score @s enemy.attribute.healing matches 1.. run data modify storage temp text append value [{text:"+",color:green,bold:true},{"score":{"name":"@s","objective":"enemy.attribute.healing"},color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.attribute.healing", 1, Integer.MAX_VALUE)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"+\",color:green,bold:true},{\"score\":{\"name\":\"@s\",\"objective\":\"enemy.attribute.healing\"},color:white}]", "append");
        }

        // execute on vehicle if entity @s[tag=enemy.attribute.heavy] run data modify storage temp text append value [{text:" [육중]",color:gold}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.attribute.heavy"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\" [육중]\",color:gold}]", "append");
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.attribute.speed] run data modify storage temp text append value [{text:" [신속]",color:aqua}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.attribute.speed"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\" [신속]\",color:aqua}]", "append");
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.attribute.taunt] run data modify storage temp text append value [{text:" [도발]",color:red}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.attribute.taunt"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\" [도발]\",color:red}]", "append");
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.attribute.dark] run data modify storage temp text append value [{text:" [☯어둠]",color:black}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.attribute.dark"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\" [☯어둠]\",color:black}]", "append");
            }
          } }

        // execute if function enemy:animation/check-immune run data modify storage temp text append value [{text:"\n",color:aqua},{text:"면역 ",color:light_purple}]
        if ((tdpack.buckets.Bucket5._m_f9a1e0f0_enemy_animation_check_immune_executeReturn(source) != 0)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"\\n\",color:aqua},{text:\"면역 \",color:light_purple}]", "append");
        }

        // execute on vehicle if entity @s[tag=enemy.immune.freeze] run data modify storage temp text append value [{text:"❄",color:aqua}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.freeze"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"❄\",color:aqua}]", "append");
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.bleed] run data modify storage temp text append value [{text:"🩸",color:red}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.bleed"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"🩸\",color:red}]", "append");
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.poison] run data modify storage temp text append value [{text:"☠",color:green}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.poison"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"☠\",color:green}]", "append");
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.flame] run data modify storage temp text append value [{text:"🔥",color:gold}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.flame"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"🔥\",color:gold}]", "append");
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.stun] run data modify storage temp text append value [{text:"💫",color:yellow}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.stun"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"💫\",color:yellow}]", "append");
            }
          } }

        // execute on vehicle if entity @s[tag=enemy.immune.weakness] run data modify storage temp text append value [{text:"☢",color:dark_green}]
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.immune.weakness"))) {
                KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"☢\",color:dark_green}]", "append");
            }
          } }

        // execute if function enemy:animation/check-debuff run data modify storage temp text append value {text:"\n",color:aqua}
        if ((tdpack.buckets.Bucket5._m_c10081dd_enemy_animation_check_debuff_executeReturn(source) != 0)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "{text:\"\\n\",color:aqua}", "append");
        }

        // execute if score @s enemy.state.freeze matches 1.. run data modify storage temp text append value [{text:"❄",color:aqua},{"score":{"name":"@s","objective":"enemy.state.freeze"},color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.freeze", 1, Integer.MAX_VALUE)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"❄\",color:aqua},{\"score\":{\"name\":\"@s\",\"objective\":\"enemy.state.freeze\"},color:white}]", "append");
        }

        // execute if score @s enemy.state.bleed matches 1.. run data modify storage temp text append value [{text:"🩸",color:red},{"score":{"name":"@s","objective":"enemy.state.bleed"},color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.bleed", 1, Integer.MAX_VALUE)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"🩸\",color:red},{\"score\":{\"name\":\"@s\",\"objective\":\"enemy.state.bleed\"},color:white}]", "append");
        }

        // execute if score @s enemy.state.poison matches 1.. run function enemy:animation/poison-text
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison", 1, Integer.MAX_VALUE)) {
            // -> enemy:animation/poison-text
            tdpack.buckets.Bucket5._m_0c0142e3_enemy_animation_poison_text_execute(source);
        }

        // execute if score @s enemy.state.flame matches 1.. run data modify storage temp text append value [{text:"🔥",color:gold},{"score":{"name":"@s","objective":"enemy.state.flame"},color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 1, Integer.MAX_VALUE)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"🔥\",color:gold},{\"score\":{\"name\":\"@s\",\"objective\":\"enemy.state.flame\"},color:white}]", "append");
        }

        // execute if score @s enemy.state.stun matches 1.. run data modify storage temp text append value [{text:"💫",color:yellow},{"score":{"name":"@s","objective":"enemy.state.stun"},color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun", 1, Integer.MAX_VALUE)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"💫\",color:yellow},{\"score\":{\"name\":\"@s\",\"objective\":\"enemy.state.stun\"},color:white}]", "append");
        }

        // execute if score @s enemy.state.weakness matches 1.. run data modify storage temp text append value [{text:"☢",color:dark_green},{"score":{"name":"@s","objective":"enemy.state.weakness"},color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 1, Integer.MAX_VALUE)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"☢\",color:dark_green},{\"score\":{\"name\":\"@s\",\"objective\":\"enemy.state.weakness\"},color:white}]", "append");
        }

        // execute if score @s enemy.state.corruption matches 1..99 run data modify storage temp text append value [{text:"☯",color:black},{"score":{"name":"@s","objective":"enemy.state.corruption"},color:white}]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.corruption", 1, 99)) {
            KfcGen.storagePutSnbt(server, "temp", "text", "[{text:\"☯\",color:black},{\"score\":{\"name\":\"@s\",\"objective\":\"enemy.state.corruption\"},color:white}]", "append");
        }

        // data modify entity @s text set from storage temp text
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetStorage(server, "temp", "text"); if (_v != null) KfcGen.nbtSetEntity(executor, "text", _v); }
        return 0;
    }

    public static void _m_167f25b7_enemy_animation_warped_zombie_f10_execute(ServerCommandSource source) {
        _m_167f25b7_enemy_animation_warped_zombie_f10_executeReturn(source);
    }

    public static int _m_167f25b7_enemy_animation_warped_zombie_f10_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute if entity @s[tag=warped-zombie1,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.000025f,-0.00358325f,-0.00297494f,0.99998915f],right_rotation:[0f,0f,0f,1f],translation:[-0.375f,1f,0.8125f],scale:[0.60002621f,2.00003482f,0.60001541f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie1"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.000025f,-0.00358325f,-0.00297494f,0.99998915f],right_rotation:[0f,0f,0f,1f],translation:[-0.375f,1f,0.8125f],scale:[0.60002621f,2.00003482f,0.60001541f]}}");
        }

        // execute if entity @s[tag=warped-zombie2,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.25577892f,-0.03511781f,-0.00328244f,0.96609171f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1f,0.1875f],scale:[0.7000397f,2.00003708f,0.70002444f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie2"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.25577892f,-0.03511781f,-0.00328244f,0.96609171f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1f,0.1875f],scale:[0.7000397f,2.00003708f,0.70002444f]}}");
        }

        // execute if entity @s[tag=warped-zombie3,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.17338559f,0.0056633f,0.00572451f,0.98482109f],right_rotation:[0f,0f,0f,1f],translation:[0f,2.0625f,0.75f],scale:[1.19995577f,1.00000054f,0.70002718f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie3"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.17338559f,0.0056633f,0.00572451f,0.98482109f],right_rotation:[0f,0f,0f,1f],translation:[0f,2.0625f,0.75f],scale:[1.19995577f,1.00000054f,0.70002718f]}}");
        }

        // execute if entity @s[tag=warped-zombie4,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.02893156f,-0.21302245f,0.97659431f,-0.00692651f],right_rotation:[0f,0f,0f,1f],translation:[0f,3.0625f,1.125f],scale:[1.39997917f,1.49997701f,0.89998628f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie4"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.02893156f,-0.21302245f,0.97659431f,-0.00692651f],right_rotation:[0f,0f,0f,1f],translation:[0f,3.0625f,1.125f],scale:[1.39997917f,1.49997701f,0.89998628f]}}");
        }

        // execute if entity @s[tag=warped-zombie5,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[-0.12136445f,-0.03613325f,-0.12864245f,0.98357316f],right_rotation:[0f,0f,0f,1f],translation:[-0.9375f,2.6875f,1.5625f],scale:[0.6000329f,2.00002199f,0.60003441f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie5"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[-0.12136445f,-0.03613325f,-0.12864245f,0.98357316f],right_rotation:[0f,0f,0f,1f],translation:[-0.9375f,2.6875f,1.5625f],scale:[0.6000329f,2.00002199f,0.60003441f]}}");
        }

        // execute if entity @s[tag=warped-zombie6,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.00158609f,-0.01058836f,0.09328034f,0.99558231f],right_rotation:[0f,0f,0f,1f],translation:[0.9375f,2.625f,1.375f],scale:[0.5999718f,2.00002572f,0.60003822f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie6"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.00158609f,-0.01058836f,0.09328034f,0.99558231f],right_rotation:[0f,0f,0f,1f],translation:[0.9375f,2.625f,1.375f],scale:[0.5999718f,2.00002572f,0.60003822f]}}");
        }

        // execute if entity @s[tag=warped-zombie7,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[-0.02862185f,0.92702624f,0.35895742f,-0.10465294f],right_rotation:[0f,0f,0f,1f],translation:[0f,4.1875f,2.0625f],scale:[1.50000981f,1.5000233f,1.50001015f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie7"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[-0.02862185f,0.92702624f,0.35895742f,-0.10465294f],right_rotation:[0f,0f,0f,1f],translation:[0f,4.1875f,2.0625f],scale:[1.50000981f,1.5000233f,1.50001015f]}}");
        }
        return 0;
    }

    public static void _m_70ab8a4f_enemy_animation_warped_zombie_f15_execute(ServerCommandSource source) {
        _m_70ab8a4f_enemy_animation_warped_zombie_f15_executeReturn(source);
    }

    public static int _m_70ab8a4f_enemy_animation_warped_zombie_f15_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute if entity @s[tag=warped-zombie2,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[-0.13066381f,0.00979448f,-0.01695355f,0.99123339f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1.3125f,0.8125f],scale:[0.59996012f,1.99996002f,0.59999657f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie2"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[-0.13066381f,0.00979448f,-0.01695355f,0.99123339f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1.3125f,0.8125f],scale:[0.59996012f,1.99996002f,0.59999657f]}}");
        }

        // execute if entity @s[tag=warped-zombie1,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.1295971f,-0.01386846f,0.00672353f,0.99144691f],right_rotation:[0f,0f,0f,1f],translation:[-0.375f,1f,0f],scale:[0.70003327f,1.99996923f,0.69997889f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie1"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.1295971f,-0.01386846f,0.00672353f,0.99144691f],right_rotation:[0f,0f,0f,1f],translation:[-0.375f,1f,0f],scale:[0.70003327f,1.99996923f,0.69997889f]}}");
        }

        // execute if entity @s[tag=warped-zombie3,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.04362821f,0f,0f,0.99904784f],right_rotation:[0f,0f,0f,1f],translation:[0f,2.3125f,0.375f],scale:[1.2f,1.00000914f,0.69996306f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie3"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.04362821f,0f,0f,0.99904784f],right_rotation:[0f,0f,0f,1f],translation:[0f,2.3125f,0.375f],scale:[1.2f,1.00000914f,0.69996306f]}}");
        }

        // execute if entity @s[tag=warped-zombie4,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0f,-0.08716644f,0.99619376f,-0f],right_rotation:[0f,0f,0f,1f],translation:[0f,3.5f,0.5625f],scale:[1.4f,1.49999336f,0.89997632f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie4"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0f,-0.08716644f,0.99619376f,-0f],right_rotation:[0f,0f,0f,1f],translation:[0f,3.5f,0.5625f],scale:[1.4f,1.49999336f,0.89997632f]}}");
        }

        // execute if entity @s[tag=warped-zombie6,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.12803793f,-0.01423616f,0.06848001f,0.98929994f],right_rotation:[0f,0f,0f,1f],translation:[0.9375f,3.3125f,0.3125f],scale:[0.59996865f,2.00001617f,0.60002267f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie6"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.12803793f,-0.01423616f,0.06848001f,0.98929994f],right_rotation:[0f,0f,0f,1f],translation:[0.9375f,3.3125f,0.3125f],scale:[0.59996865f,2.00001617f,0.60002267f]}}");
        }

        // execute if entity @s[tag=warped-zombie5,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[-0.12196784f,-0.0465214f,-0.07315561f,0.98874066f],right_rotation:[0f,0f,0f,1f],translation:[-0.9375f,2.9375f,0.8125f],scale:[0.60001984f,2.00001556f,0.60004821f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie5"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[-0.12196784f,-0.0465214f,-0.07315561f,0.98874066f],right_rotation:[0f,0f,0f,1f],translation:[-0.9375f,2.9375f,0.8125f],scale:[0.60001984f,2.00001556f,0.60004821f]}}");
        }

        // execute if entity @s[tag=warped-zombie7,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0f,0.99144619f,0.13051611f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,4.8125f,1.0625f],scale:[1.5f,1.50000348f,1.50000348f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie7"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0f,0.99144619f,0.13051611f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,4.8125f,1.0625f],scale:[1.5f,1.50000348f,1.50000348f]}}");
        }
        return 0;
    }

    public static void _m_a95eebe0_enemy_animation_warped_zombie_f20_execute(ServerCommandSource source) {
        _m_a95eebe0_enemy_animation_warped_zombie_f20_executeReturn(source);
    }

    public static int _m_a95eebe0_enemy_animation_warped_zombie_f20_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute if entity @s[tag=warped-zombie2,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.000025f,-0.00358325f,-0.00297494f,0.99998915f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1f,0.8125f],scale:[0.60002621f,2.00003482f,0.60001541f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie2"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.000025f,-0.00358325f,-0.00297494f,0.99998915f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1f,0.8125f],scale:[0.60002621f,2.00003482f,0.60001541f]}}");
        }

        // execute if entity @s[tag=warped-zombie1,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.25577892f,-0.03511781f,-0.00328244f,0.96609171f],right_rotation:[0f,0f,0f,1f],translation:[-0.375f,1f,0.1875f],scale:[0.7000397f,2.00003708f,0.70002444f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie1"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.25577892f,-0.03511781f,-0.00328244f,0.96609171f],right_rotation:[0f,0f,0f,1f],translation:[-0.375f,1f,0.1875f],scale:[0.7000397f,2.00003708f,0.70002444f]}}");
        }

        // execute if entity @s[tag=warped-zombie3,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.17338559f,0.0056633f,0.00572451f,0.98482109f],right_rotation:[0f,0f,0f,1f],translation:[0f,2.0625f,0.75f],scale:[1.19995577f,1.00000054f,0.70002718f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie3"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.17338559f,0.0056633f,0.00572451f,0.98482109f],right_rotation:[0f,0f,0f,1f],translation:[0f,2.0625f,0.75f],scale:[1.19995577f,1.00000054f,0.70002718f]}}");
        }

        // execute if entity @s[tag=warped-zombie4,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.02893156f,-0.21302245f,0.97659431f,-0.00692651f],right_rotation:[0f,0f,0f,1f],translation:[0f,3.0625f,1.125f],scale:[1.39997917f,1.49997701f,0.89998628f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie4"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.02893156f,-0.21302245f,0.97659431f,-0.00692651f],right_rotation:[0f,0f,0f,1f],translation:[0f,3.0625f,1.125f],scale:[1.39997917f,1.49997701f,0.89998628f]}}");
        }

        // execute if entity @s[tag=warped-zombie6,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[-0.12336175f,-0.00368622f,0.13194483f,0.98354379f],right_rotation:[0f,0f,0f,1f],translation:[0.9375f,2.6875f,1.5625f],scale:[0.60001462f,1.99999684f,0.59997592f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie6"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[-0.12336175f,-0.00368622f,0.13194483f,0.98354379f],right_rotation:[0f,0f,0f,1f],translation:[0.9375f,2.6875f,1.5625f],scale:[0.60001462f,1.99999684f,0.59997592f]}}");
        }

        // execute if entity @s[tag=warped-zombie5,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.00350375f,-0.01013069f,-0.09584701f,0.99533834f],right_rotation:[0f,0f,0f,1f],translation:[-0.9375f,2.625f,1.375f],scale:[0.59994381f,2.00000422f,0.60003772f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie5"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.00350375f,-0.01013069f,-0.09584701f,0.99533834f],right_rotation:[0f,0f,0f,1f],translation:[-0.9375f,2.625f,1.375f],scale:[0.59994381f,2.00000422f,0.60003772f]}}");
        }

        // execute if entity @s[tag=warped-zombie7,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[-0.02862185f,0.92702624f,0.35895742f,-0.10465294f],right_rotation:[0f,0f,0f,1f],translation:[0f,4.1875f,2.0625f],scale:[1.50000981f,1.5000233f,1.50001015f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie7"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[-0.02862185f,0.92702624f,0.35895742f,-0.10465294f],right_rotation:[0f,0f,0f,1f],translation:[0f,4.1875f,2.0625f],scale:[1.50000981f,1.5000233f,1.50001015f]}}");
        }
        return 0;
    }

    public static void _m_fef9c646_enemy_animation_warped_zombie_f5_execute(ServerCommandSource source) {
        _m_fef9c646_enemy_animation_warped_zombie_f5_executeReturn(source);
    }

    public static int _m_fef9c646_enemy_animation_warped_zombie_f5_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute if entity @s[tag=warped-zombie1,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[-0.12903088f,0.00987647f,-0.01689533f,0.9914474f],right_rotation:[0f,0f,0f,1f],translation:[-0.375f,1.3125f,0.8125f],scale:[0.59995802f,1.99994865f,0.59999355f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie1"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[-0.12903088f,0.00987647f,-0.01689533f,0.9914474f],right_rotation:[0f,0f,0f,1f],translation:[-0.375f,1.3125f,0.8125f],scale:[0.59995802f,1.99994865f,0.59999355f]}}");
        }

        // execute if entity @s[tag=warped-zombie2,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.1295971f,-0.01386846f,0.00672353f,0.99144691f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1f,0f],scale:[0.70003327f,1.99996923f,0.69997889f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie2"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.1295971f,-0.01386846f,0.00672353f,0.99144691f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1f,0f],scale:[0.70003327f,1.99996923f,0.69997889f]}}");
        }

        // execute if entity @s[tag=warped-zombie3,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.04362821f,0f,0f,0.99904784f],right_rotation:[0f,0f,0f,1f],translation:[0f,2.3125f,0.375f],scale:[1.2f,1.00000914f,0.69996306f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie3"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.04362821f,0f,0f,0.99904784f],right_rotation:[0f,0f,0f,1f],translation:[0f,2.3125f,0.375f],scale:[1.2f,1.00000914f,0.69996306f]}}");
        }

        // execute if entity @s[tag=warped-zombie4,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0f,-0.08716644f,0.99619376f,-0f],right_rotation:[0f,0f,0f,1f],translation:[0f,3.5f,0.5625f],scale:[1.4f,1.49999336f,0.89997632f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie4"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0f,-0.08716644f,0.99619376f,-0f],right_rotation:[0f,0f,0f,1f],translation:[0f,3.5f,0.5625f],scale:[1.4f,1.49999336f,0.89997632f]}}");
        }

        // execute if entity @s[tag=warped-zombie5,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0.12880966f,0.00320063f,-0.06561584f,0.98949125f],right_rotation:[0f,0f,0f,1f],translation:[-0.9375f,3.3125f,0.3125f],scale:[0.59997584f,1.99999998f,0.60002162f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie5"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0.12880966f,0.00320063f,-0.06561584f,0.98949125f],right_rotation:[0f,0f,0f,1f],translation:[-0.9375f,3.3125f,0.3125f],scale:[0.59997584f,1.99999998f,0.60002162f]}}");
        }

        // execute if entity @s[tag=warped-zombie6,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[-0.1278284f,-0.02636435f,0.08594337f,0.98771415f],right_rotation:[0f,0f,0f,1f],translation:[0.9375f,2.9375f,0.8125f],scale:[0.59999709f,2.00001151f,0.6000408f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie6"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[-0.1278284f,-0.02636435f,0.08594337f,0.98771415f],right_rotation:[0f,0f,0f,1f],translation:[0.9375f,2.9375f,0.8125f],scale:[0.59999709f,2.00001151f,0.6000408f]}}");
        }

        // execute if entity @s[tag=warped-zombie7,type=item_display] run data merge entity @s {interpolation_duration: 5, transformation:{left_rotation:[0f,0.99144619f,0.13051611f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,4.8125f,1.0625f],scale:[1.5f,1.50000348f,1.50000348f]}}
        if ((executor != null && executor.getType() == EntityType.ITEM_DISPLAY && executor.getCommandTags().contains("warped-zombie7"))) {
            if (executor != null) KfcGen.entityMergeSnbt(executor, "{interpolation_duration: 5, transformation:{left_rotation:[0f,0.99144619f,0.13051611f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,4.8125f,1.0625f],scale:[1.5f,1.50000348f,1.50000348f]}}");
        }
        return 0;
    }

    public static void _m_5e22a6ff_enemy_animation_warped_zombie_frame_execute(ServerCommandSource source) {
        _m_5e22a6ff_enemy_animation_warped_zombie_frame_executeReturn(source);
    }

    public static int _m_5e22a6ff_enemy_animation_warped_zombie_frame_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s tower.animation matches 20 run function enemy:animation/warped-zombie/f20
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.animation", 20, 20)) {
            // -> enemy:animation/warped-zombie/f20
            tdpack.buckets.Bucket5._m_a95eebe0_enemy_animation_warped_zombie_f20_execute(source);
        }

        // execute if score @s tower.animation matches 5 run function enemy:animation/warped-zombie/f5
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.animation", 5, 5)) {
            // -> enemy:animation/warped-zombie/f5
            tdpack.buckets.Bucket5._m_fef9c646_enemy_animation_warped_zombie_f5_execute(source);
        }

        // execute if score @s tower.animation matches 10 run function enemy:animation/warped-zombie/f10
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.animation", 10, 10)) {
            // -> enemy:animation/warped-zombie/f10
            tdpack.buckets.Bucket5._m_167f25b7_enemy_animation_warped_zombie_f10_execute(source);
        }

        // execute if score @s tower.animation matches 15 run function enemy:animation/warped-zombie/f15
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.animation", 15, 15)) {
            // -> enemy:animation/warped-zombie/f15
            tdpack.buckets.Bucket5._m_70ab8a4f_enemy_animation_warped_zombie_f15_execute(source);
        }
        return 0;
    }

    public static void _m_59122ae4_enemy_attribute_main_execute(ServerCommandSource source) {
        _m_59122ae4_enemy_attribute_main_executeReturn(source);
    }

    public static int _m_59122ae4_enemy_attribute_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.attribute.healing matches 1.. run function enemy:attribute/buff/healing
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.attribute.healing", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/buff/healing
            tdpack.buckets.Bucket5._m_243bcf2b_enemy_attribute_buff_healing_execute(source);
        }

        // execute if score @s enemy.attribute.dealing matches 1.. run function enemy:attribute/buff/dealing
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.attribute.dealing", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/buff/dealing
            tdpack.buckets.Bucket5._m_ebabcb0c_enemy_attribute_buff_dealing_execute(source);
        }

        // function enemy:attribute/dark/main
        // -> enemy:attribute/dark/main
        tdpack.buckets.Bucket5._m_18044e38_enemy_attribute_dark_main_execute(source);

        // execute if score @s enemy.state.bleed matches 1.. run function enemy:attribute/debuff/bleed
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.bleed", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/debuff/bleed
            tdpack.buckets.Bucket5._m_553cbdfe_enemy_attribute_debuff_bleed_execute(source);
        }

        // execute if score @s enemy.state.flame matches 1.. run function enemy:attribute/debuff/flame
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/debuff/flame
            tdpack.buckets.Bucket5._m_17faaf32_enemy_attribute_debuff_flame_execute(source);
        }

        // execute if score @s enemy.state.poison matches 1.. run function enemy:attribute/debuff/poison
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/debuff/poison
            tdpack.buckets.Bucket5._m_1bc8c77b_enemy_attribute_debuff_poison_execute(source);
        }

        // execute if score @s enemy.state.freeze matches 1.. run function enemy:attribute/debuff/freeze
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.freeze", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/debuff/freeze
            tdpack.buckets.Bucket5._m_52fca30f_enemy_attribute_debuff_freeze_execute(source);
        }

        // execute if score @s enemy.state.stun matches 1.. run function enemy:attribute/debuff/stun
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/debuff/stun
            tdpack.buckets.Bucket5._m_eaab00d1_enemy_attribute_debuff_stun_execute(source);
        }

        // execute if score @s enemy.state.weakness matches 1.. run function enemy:attribute/debuff/weakness
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/debuff/weakness
            tdpack.buckets.Bucket5._m_d89b51bd_enemy_attribute_debuff_weakness_execute(source);
        }

        // execute if score @s enemy.state.stun-immune matches 1.. run function enemy:attribute/debuff/stun-immune
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun-immune", 1, Integer.MAX_VALUE)) {
            // -> enemy:attribute/debuff/stun-immune
            tdpack.buckets.Bucket5._m_1c952a44_enemy_attribute_debuff_stun_immune_execute(source);
        }

        // execute unless score @s enemy.hp matches 1.. run function enemy:hit/death with entity @s data
        if (!(KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1, Integer.MAX_VALUE))) {
            // -> enemy:hit/death
            tdpack.buckets.Bucket5._m_2db09a02_enemy_hit_death_execute(source, KfcGen.entityMacroArgs(executor, "data"));
        }

        // function enemy:ability/main
        // -> enemy:ability/main
        tdpack.buckets.Bucket4._m_cb0aea95_enemy_ability_main_execute(source);

        // scoreboard players add @s enemy.attribute.timer 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.attribute.timer", 1);
        return 0;
    }

    public static void _m_ebabcb0c_enemy_attribute_buff_dealing_execute(ServerCommandSource source) {
        _m_ebabcb0c_enemy_attribute_buff_dealing_executeReturn(source);
    }

    public static int _m_ebabcb0c_enemy_attribute_buff_dealing_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #temp enemy.attribute.timer 10
        KfcGen.setScore(sb, "#temp", "enemy.attribute.timer", 10);

        // scoreboard players operation #temp enemy.attribute.dealing = @s enemy.attribute.timer
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.attribute.dealing", "=", executor.getNameForScoreboard(), "enemy.attribute.timer");

        // scoreboard players operation #temp enemy.attribute.dealing %= #temp enemy.attribute.timer
        KfcGen.opScore(sb, "#temp", "enemy.attribute.dealing", "%=", "#temp", "enemy.attribute.timer");

        // execute if score #temp enemy.attribute.dealing matches 0 run scoreboard players operation @s enemy.hp -= @s enemy.attribute.dealing
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.dealing", 0, 0)) {
            if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "-=", executor.getNameForScoreboard(), "enemy.attribute.dealing");
        }

        // execute if score @s enemy.hp > @s enemy.max_hp run scoreboard players operation @s enemy.hp = @s enemy.max_hp
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", ">", (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.max_hp")) {
            if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "=", executor.getNameForScoreboard(), "enemy.max_hp");
        }
        return 0;
    }

    public static void _m_243bcf2b_enemy_attribute_buff_healing_execute(ServerCommandSource source) {
        _m_243bcf2b_enemy_attribute_buff_healing_executeReturn(source);
    }

    public static int _m_243bcf2b_enemy_attribute_buff_healing_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #temp enemy.attribute.timer 10
        KfcGen.setScore(sb, "#temp", "enemy.attribute.timer", 10);

        // scoreboard players operation #temp enemy.attribute.healing = @s enemy.attribute.timer
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.attribute.healing", "=", executor.getNameForScoreboard(), "enemy.attribute.timer");

        // scoreboard players operation #temp enemy.attribute.healing %= #temp enemy.attribute.timer
        KfcGen.opScore(sb, "#temp", "enemy.attribute.healing", "%=", "#temp", "enemy.attribute.timer");

        // execute if score #temp enemy.attribute.healing matches 0 run scoreboard players operation @s enemy.hp += @s enemy.attribute.healing
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.healing", 0, 0)) {
            if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "+=", executor.getNameForScoreboard(), "enemy.attribute.healing");
        }

        // execute if score @s enemy.hp > @s enemy.max_hp run scoreboard players operation @s enemy.hp = @s enemy.max_hp
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", ">", (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.max_hp")) {
            if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "=", executor.getNameForScoreboard(), "enemy.max_hp");
        }
        return 0;
    }

    public static void _m_18044e38_enemy_attribute_dark_main_execute(ServerCommandSource source) {
        _m_18044e38_enemy_attribute_dark_main_executeReturn(source);
    }

    public static int _m_18044e38_enemy_attribute_dark_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @e[type=marker,tag=enemy.dark-field,tag=!enemy.dark-field.large,distance=..1.5] run tag @s add enemy.dark-field.inside
        if (KfcGen.anyEntity(ctx, source.getPosition(), EntityType.MARKER, new String[]{"enemy.dark-field"}, new String[]{"enemy.dark-field.large"}, -1, 1.5)) {
            if (executor != null) executor.addCommandTag("enemy.dark-field.inside");
        }

        // execute if entity @e[type=marker,tag=enemy.dark-field,tag=enemy.dark-field.large,distance=..5] run tag @s add enemy.dark-field.inside
        if (KfcGen.anyEntity(ctx, source.getPosition(), EntityType.MARKER, new String[]{"enemy.dark-field", "enemy.dark-field.large"}, new String[0], -1, 5)) {
            if (executor != null) executor.addCommandTag("enemy.dark-field.inside");
        }

        // execute unless entity @e[type=marker,tag=enemy.dark-field,tag=!enemy.dark-field.large,distance=..3] unless entity @e[type=marker,tag=enemy.dark-field,tag=enemy.dark-field.large,distance=..5] run tag @s remove enemy.dark-field.inside
        if (!(KfcGen.anyEntity(ctx, source.getPosition(), EntityType.MARKER, new String[]{"enemy.dark-field"}, new String[]{"enemy.dark-field.large"}, -1, 3))) {
            if (!(KfcGen.anyEntity(ctx, source.getPosition(), EntityType.MARKER, new String[]{"enemy.dark-field", "enemy.dark-field.large"}, new String[0], -1, 5))) {
                if (executor != null) executor.removeCommandTag("enemy.dark-field.inside");
            }
        }

        // execute if entity @s[tag=enemy.attribute.dark,tag=enemy.dark-field.inside] run tag @s add enemy.dark-field.immune
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark") && executor.getCommandTags().contains("enemy.dark-field.inside"))) {
            if (executor != null) executor.addCommandTag("enemy.dark-field.immune");
        }

        // execute unless entity @s[tag=enemy.attribute.dark,tag=enemy.dark-field.inside] run tag @s remove enemy.dark-field.immune
        if (!((executor != null && executor.getCommandTags().contains("enemy.attribute.dark") && executor.getCommandTags().contains("enemy.dark-field.inside")))) {
            if (executor != null) executor.removeCommandTag("enemy.dark-field.immune");
        }

        // execute if entity @s[tag=!enemy.attribute.dark,tag=enemy.dark-field.inside] run scoreboard players add @s enemy.state.corruption 1
        if ((executor != null && executor.getCommandTags().contains("enemy.dark-field.inside") && !executor.getCommandTags().contains("enemy.attribute.dark"))) {
            if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.state.corruption", 1);
        }

        // execute if score @s enemy.state.corruption matches 100.. run scoreboard players set @s enemy.state.corruption 100
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.corruption", 100, Integer.MAX_VALUE)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.corruption", 100);
        }

        // execute if entity @s[tag=!enemy.attribute.dark,scores={enemy.state.corruption=100..}] run tag @s add enemy.attribute.dark.new
        if ((executor != null && !executor.getCommandTags().contains("enemy.attribute.dark") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.state.corruption", 100, Integer.MAX_VALUE))) {
            if (executor != null) executor.addCommandTag("enemy.attribute.dark.new");
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run tag @s add enemy.attribute.dark
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) executor.addCommandTag("enemy.attribute.dark");
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.freeze 0
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.freeze", 0);
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.stun 0
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.stun", 0);
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.poison 0
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.poison", 0);
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.flame 0
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.flame", 0);
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.bleed 0
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.bleed", 0);
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.weakness 0
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.weakness", 0);
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.stun-immune 0
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.stun-immune", 0);
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.corruption 0
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.corruption", 0);
        }

        // execute if entity @s[tag=enemy.attribute.dark.new] run tag @s remove enemy.attribute.dark.new
        if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark.new"))) {
            if (executor != null) executor.removeCommandTag("enemy.attribute.dark.new");
        }
        return 0;
    }

    public static void _m_553cbdfe_enemy_attribute_debuff_bleed_execute(ServerCommandSource source) {
        _m_553cbdfe_enemy_attribute_debuff_bleed_executeReturn(source);
    }

    public static int _m_553cbdfe_enemy_attribute_debuff_bleed_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set #20 enemy.attribute.timer 20
        KfcGen.setScore(sb, "#20", "enemy.attribute.timer", 20);

        // scoreboard players operation #temp enemy.attribute.timer = @s enemy.attribute.timer
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.attribute.timer", "=", executor.getNameForScoreboard(), "enemy.attribute.timer");

        // scoreboard players operation #temp enemy.attribute.timer %= #20 enemy.attribute.timer
        KfcGen.opScore(sb, "#temp", "enemy.attribute.timer", "%=", "#20", "enemy.attribute.timer");

        // execute if score @s enemy.hp matches 1000000.. run scoreboard players set #1000 enemy.state.bleed 200000
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1000000, Integer.MAX_VALUE)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 200000);
        }

        // execute if score @s enemy.hp matches 750000..999999 run scoreboard players set #1000 enemy.state.bleed 172000
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 750000, 999999)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 172000);
        }

        // execute if score @s enemy.hp matches 562500..749999 run scoreboard players set #1000 enemy.state.bleed 147920
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 562500, 749999)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 147920);
        }

        // execute if score @s enemy.hp matches 421875..562499 run scoreboard players set #1000 enemy.state.bleed 127211
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 421875, 562499)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 127211);
        }

        // execute if score @s enemy.hp matches 316406..421874 run scoreboard players set #1000 enemy.state.bleed 109401
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 316406, 421874)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 109401);
        }

        // execute if score @s enemy.hp matches 237304..316405 run scoreboard players set #1000 enemy.state.bleed 94084
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 237304, 316405)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 94084);
        }

        // execute if score @s enemy.hp matches 177978..237303 run scoreboard players set #1000 enemy.state.bleed 80912
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 177978, 237303)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 80912);
        }

        // execute if score @s enemy.hp matches 133483..177977 run scoreboard players set #1000 enemy.state.bleed 69584
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 133483, 177977)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 69584);
        }

        // execute if score @s enemy.hp matches 100112..133482 run scoreboard players set #1000 enemy.state.bleed 59842
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 100112, 133482)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 59842);
        }

        // execute if score @s enemy.hp matches 75084..100111 run scoreboard players set #1000 enemy.state.bleed 51464
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 75084, 100111)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 51464);
        }

        // execute if score @s enemy.hp matches 56313..75083 run scoreboard players set #1000 enemy.state.bleed 44259
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 56313, 75083)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 44259);
        }

        // execute if score @s enemy.hp matches 42234..56312 run scoreboard players set #1000 enemy.state.bleed 38062
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 42234, 56312)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 38062);
        }

        // execute if score @s enemy.hp matches 31675..42233 run scoreboard players set #1000 enemy.state.bleed 32733
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 31675, 42233)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 32733);
        }

        // execute if score @s enemy.hp matches 23756..31674 run scoreboard players set #1000 enemy.state.bleed 28150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 23756, 31674)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 28150);
        }

        // execute if score @s enemy.hp matches 17817..23755 run scoreboard players set #1000 enemy.state.bleed 24209
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 17817, 23755)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 24209);
        }

        // execute if score @s enemy.hp matches 13362..17816 run scoreboard players set #1000 enemy.state.bleed 20819
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 13362, 17816)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 20819);
        }

        // execute if score @s enemy.hp matches 10021..13361 run scoreboard players set #1000 enemy.state.bleed 17904
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 10021, 13361)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 17904);
        }

        // execute if score @s enemy.hp matches 7515..10020 run scoreboard players set #1000 enemy.state.bleed 15397
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 7515, 10020)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 15397);
        }

        // execute if score @s enemy.hp matches 5636..7514 run scoreboard players set #1000 enemy.state.bleed 13241
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 5636, 7514)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 13241);
        }

        // execute if score @s enemy.hp matches 4227..5635 run scoreboard players set #1000 enemy.state.bleed 11387
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 4227, 5635)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 11387);
        }

        // execute if score @s enemy.hp matches 3170..4226 run scoreboard players set #1000 enemy.state.bleed 9792
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 3170, 4226)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 9792);
        }

        // execute if score @s enemy.hp matches 2377..3169 run scoreboard players set #1000 enemy.state.bleed 8421
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 2377, 3169)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 8421);
        }

        // execute if score @s enemy.hp matches 1782..2376 run scoreboard players set #1000 enemy.state.bleed 7242
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1782, 2376)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 7242);
        }

        // execute if score @s enemy.hp matches 1336..1781 run scoreboard players set #1000 enemy.state.bleed 6228
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1336, 1781)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 6228);
        }

        // execute if score @s enemy.hp matches 1002..1335 run scoreboard players set #1000 enemy.state.bleed 5356
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1002, 1335)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 5356);
        }

        // execute if score @s enemy.hp matches 751..1001 run scoreboard players set #1000 enemy.state.bleed 4606
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 751, 1001)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 4606);
        }

        // execute if score @s enemy.hp matches 563..750 run scoreboard players set #1000 enemy.state.bleed 3961
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 563, 750)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 3961);
        }

        // execute if score @s enemy.hp matches 422..562 run scoreboard players set #1000 enemy.state.bleed 3406
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 422, 562)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 3406);
        }

        // execute if score @s enemy.hp matches 316..421 run scoreboard players set #1000 enemy.state.bleed 2929
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 316, 421)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 2929);
        }

        // execute if score @s enemy.hp matches 237..315 run scoreboard players set #1000 enemy.state.bleed 2518
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 237, 315)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 2518);
        }

        // execute if score @s enemy.hp matches 177..236 run scoreboard players set #1000 enemy.state.bleed 2165
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 177, 236)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 2165);
        }

        // execute if score @s enemy.hp matches 132..176 run scoreboard players set #1000 enemy.state.bleed 1861
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 132, 176)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 1861);
        }

        // execute if score @s enemy.hp matches 99..131 run scoreboard players set #1000 enemy.state.bleed 1600
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 99, 131)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 1600);
        }

        // execute if score @s enemy.hp matches 74..98 run scoreboard players set #1000 enemy.state.bleed 1376
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 74, 98)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 1376);
        }

        // execute if score @s enemy.hp matches 55..73 run scoreboard players set #1000 enemy.state.bleed 1183
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 55, 73)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 1183);
        }

        // execute if score @s enemy.hp matches 41..54 run scoreboard players set #1000 enemy.state.bleed 1017
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 41, 54)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 1017);
        }

        // execute if score @s enemy.hp matches 30..40 run scoreboard players set #1000 enemy.state.bleed 874
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 30, 40)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 874);
        }

        // execute if score @s enemy.hp matches 22..29 run scoreboard players set #1000 enemy.state.bleed 751
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 22, 29)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 751);
        }

        // execute if score @s enemy.hp matches 16..21 run scoreboard players set #1000 enemy.state.bleed 645
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 16, 21)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 645);
        }

        // execute if score @s enemy.hp matches 12..15 run scoreboard players set #1000 enemy.state.bleed 554
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 12, 15)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 554);
        }

        // execute if score @s enemy.hp matches 9..11 run scoreboard players set #1000 enemy.state.bleed 476
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 9, 11)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 476);
        }

        // execute if score @s enemy.hp matches 6..8 run scoreboard players set #1000 enemy.state.bleed 409
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 6, 8)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 409);
        }

        // execute if score @s enemy.hp matches 4..5 run scoreboard players set #1000 enemy.state.bleed 351
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 4, 5)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 351);
        }

        // execute if score @s enemy.hp matches 3..3 run scoreboard players set #1000 enemy.state.bleed 301
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 3, 3)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 301);
        }

        // execute if score @s enemy.hp matches 2..2 run scoreboard players set #1000 enemy.state.bleed 258
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 2, 2)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 258);
        }

        // execute if score @s enemy.hp matches 1..1 run scoreboard players set #1000 enemy.state.bleed 221
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1, 1)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 221);
        }

        // execute if score @s enemy.hp matches 0..0 run scoreboard players set #1000 enemy.state.bleed 190
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 0, 0)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.bleed", 190);
        }

        // execute if score #temp enemy.attribute.timer matches 0 store result score #temp enemy.hp run scoreboard players get @s enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.setScore(sb, "#temp", "enemy.hp", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp"));
        }

        // execute if score #temp enemy.attribute.timer matches 0 store result score #temp enemy.state.bleed run scoreboard players get @s enemy.state.bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.setScore(sb, "#temp", "enemy.state.bleed", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.bleed"));
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_q enemy.hp = #temp enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp_q", "enemy.hp", "=", "#temp", "enemy.hp");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_q enemy.hp /= #1000 enemy.state.bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp_q", "enemy.hp", "/=", "#1000", "enemy.state.bleed");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_q enemy.hp *= #temp enemy.state.bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp_q", "enemy.hp", "*=", "#temp", "enemy.state.bleed");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_r enemy.hp = #temp enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp_r", "enemy.hp", "=", "#temp", "enemy.hp");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_r enemy.hp %= #1000 enemy.state.bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp_r", "enemy.hp", "%=", "#1000", "enemy.state.bleed");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_r enemy.hp *= #temp enemy.state.bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp_r", "enemy.hp", "*=", "#temp", "enemy.state.bleed");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_r enemy.hp /= #1000 enemy.state.bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp_r", "enemy.hp", "/=", "#1000", "enemy.state.bleed");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_q enemy.hp += #temp_r enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp_q", "enemy.hp", "+=", "#temp_r", "enemy.hp");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation @s enemy.hp -= #temp_q enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "-=", "#temp_q", "enemy.hp");
        }

        // execute if score #temp enemy.attribute.timer matches 0 store result storage temp bleed int 0.75 run scoreboard players get @s enemy.state.bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.storagePutNumber(server, "temp", "bleed", (KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.bleed")) * 0.75, "int");
        }

        // execute if score #temp enemy.attribute.timer matches 0 store result score @s enemy.state.bleed run data get storage temp bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.bleed", (int)(KfcGen.storageGetDouble(server, "temp", "bleed")));
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation bleed statistics += #temp_q enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "bleed", "statistics", "+=", "#temp_q", "enemy.hp");
        }
        return 0;
    }

    public static void _m_17faaf32_enemy_attribute_debuff_flame_execute(ServerCommandSource source) {
        _m_17faaf32_enemy_attribute_debuff_flame_executeReturn(source);
    }

    public static int _m_17faaf32_enemy_attribute_debuff_flame_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set #temp enemy.attribute.timer 4
        KfcGen.setScore(sb, "#temp", "enemy.attribute.timer", 4);

        // scoreboard players operation #temp enemy.state.flame = @s enemy.attribute.timer
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.state.flame", "=", executor.getNameForScoreboard(), "enemy.attribute.timer");

        // scoreboard players operation #temp enemy.state.flame %= #temp enemy.attribute.timer
        KfcGen.opScore(sb, "#temp", "enemy.state.flame", "%=", "#temp", "enemy.attribute.timer");

        // execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 5.. run scoreboard players set #flame enemy.state.flame 1
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 5, Integer.MAX_VALUE)) {
                KfcGen.setScore(sb, "#flame", "enemy.state.flame", 1);
            }
        }

        // execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 10.. run scoreboard players add #flame enemy.state.flame 1
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 10, Integer.MAX_VALUE)) {
                KfcGen.addScore(sb, "#flame", "enemy.state.flame", 1);
            }
        }

        // execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 20.. run scoreboard players add #flame enemy.state.flame 1
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 20, Integer.MAX_VALUE)) {
                KfcGen.addScore(sb, "#flame", "enemy.state.flame", 1);
            }
        }

        // execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 40.. run scoreboard players add #flame enemy.state.flame 1
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 40, Integer.MAX_VALUE)) {
                KfcGen.addScore(sb, "#flame", "enemy.state.flame", 1);
            }
        }

        // execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 80.. run scoreboard players add #flame enemy.state.flame 1
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 80, Integer.MAX_VALUE)) {
                KfcGen.addScore(sb, "#flame", "enemy.state.flame", 1);
            }
        }

        // execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 160.. run scoreboard players add #flame enemy.state.flame 1
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", 160, Integer.MAX_VALUE)) {
                KfcGen.addScore(sb, "#flame", "enemy.state.flame", 1);
            }
        }

        // execute if score #temp enemy.state.flame matches 0 run scoreboard players operation @s enemy.hp -= #flame enemy.state.flame
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "-=", "#flame", "enemy.state.flame");
        }

        // execute if score #temp enemy.state.flame matches 0 store result storage temp flame int 0.9 run scoreboard players get @s enemy.state.flame
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            KfcGen.storagePutNumber(server, "temp", "flame", (KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame")) * 0.9, "int");
        }

        // execute if score #temp enemy.state.flame matches 0 store result score @s enemy.state.flame run data get storage temp flame
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.flame", (int)(KfcGen.storageGetDouble(server, "temp", "flame")));
        }

        // execute if score #temp enemy.state.flame matches 0 run scoreboard players operation flame statistics += #flame enemy.state.flame
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 0, 0)) {
            KfcGen.opScore(sb, "flame", "statistics", "+=", "#flame", "enemy.state.flame");
        }
        return 0;
    }

    public static void _m_52fca30f_enemy_attribute_debuff_freeze_execute(ServerCommandSource source) {
        _m_52fca30f_enemy_attribute_debuff_freeze_executeReturn(source);
    }

    public static int _m_52fca30f_enemy_attribute_debuff_freeze_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #temp enemy.attribute.timer 10
        KfcGen.setScore(sb, "#temp", "enemy.attribute.timer", 10);

        // scoreboard players operation #temp enemy.state.freeze = @s enemy.attribute.timer
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.state.freeze", "=", executor.getNameForScoreboard(), "enemy.attribute.timer");

        // scoreboard players operation #temp enemy.state.freeze %= #temp enemy.attribute.timer
        KfcGen.opScore(sb, "#temp", "enemy.state.freeze", "%=", "#temp", "enemy.attribute.timer");

        // execute if score #temp enemy.state.freeze matches 0 run scoreboard players remove @s enemy.state.freeze 1
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.freeze", 0, 0)) {
            if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.state.freeze", -(1));
        }

        // particle minecraft:snowflake ~ ~1 ~ 0.3 0.6 0.3 0 1 force
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:snowflake", _pp.x, _pp.y, _pp.z, 0.3, 0.6, 0.3, 0, (int)(1), true, null); }
        return 0;
    }

    public static void _m_1bc8c77b_enemy_attribute_debuff_poison_execute(ServerCommandSource source) {
        _m_1bc8c77b_enemy_attribute_debuff_poison_executeReturn(source);
    }

    public static int _m_1bc8c77b_enemy_attribute_debuff_poison_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set #7 enemy.attribute.timer 2
        KfcGen.setScore(sb, "#7", "enemy.attribute.timer", 2);

        // scoreboard players operation #temp enemy.attribute.timer = @s enemy.attribute.timer
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.attribute.timer", "=", executor.getNameForScoreboard(), "enemy.attribute.timer");

        // scoreboard players operation #temp enemy.attribute.timer %= #7 enemy.attribute.timer
        KfcGen.opScore(sb, "#temp", "enemy.attribute.timer", "%=", "#7", "enemy.attribute.timer");

        // execute if score @s enemy.hp matches 1000000.. run scoreboard players set #1000 enemy.state.poison 25000
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1000000, Integer.MAX_VALUE)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 25000);
        }

        // execute if score @s enemy.hp matches 750000..999999 run scoreboard players set #1000 enemy.state.poison 21125
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 750000, 999999)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 21125);
        }

        // execute if score @s enemy.hp matches 562500..749999 run scoreboard players set #1000 enemy.state.poison 17850
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 562500, 749999)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 17850);
        }

        // execute if score @s enemy.hp matches 421875..562499 run scoreboard players set #1000 enemy.state.poison 15083
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 421875, 562499)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 15083);
        }

        // execute if score @s enemy.hp matches 316406..421874 run scoreboard players set #1000 enemy.state.poison 12745
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 316406, 421874)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 12745);
        }

        // execute if score @s enemy.hp matches 237304..316405 run scoreboard players set #1000 enemy.state.poison 10769
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 237304, 316405)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 10769);
        }

        // execute if score @s enemy.hp matches 177978..237303 run scoreboard players set #1000 enemy.state.poison 9099
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 177978, 237303)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 9099);
        }

        // execute if score @s enemy.hp matches 133483..177977 run scoreboard players set #1000 enemy.state.poison 7688
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 133483, 177977)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 7688);
        }

        // execute if score @s enemy.hp matches 100112..133482 run scoreboard players set #1000 enemy.state.poison 6496
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 100112, 133482)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 6496);
        }

        // execute if score @s enemy.hp matches 75084..100111 run scoreboard players set #1000 enemy.state.poison 5489
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 75084, 100111)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 5489);
        }

        // execute if score @s enemy.hp matches 56313..75083 run scoreboard players set #1000 enemy.state.poison 4638
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 56313, 75083)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 4638);
        }

        // execute if score @s enemy.hp matches 42234..56312 run scoreboard players set #1000 enemy.state.poison 3919
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 42234, 56312)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 3919);
        }

        // execute if score @s enemy.hp matches 31675..42233 run scoreboard players set #1000 enemy.state.poison 3311
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 31675, 42233)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 3311);
        }

        // execute if score @s enemy.hp matches 23756..31674 run scoreboard players set #1000 enemy.state.poison 2797
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 23756, 31674)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 2797);
        }

        // execute if score @s enemy.hp matches 17817..23755 run scoreboard players set #1000 enemy.state.poison 2363
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 17817, 23755)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 2363);
        }

        // execute if score @s enemy.hp matches 13362..17816 run scoreboard players set #1000 enemy.state.poison 1996
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 13362, 17816)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 1996);
        }

        // execute if score @s enemy.hp matches 10021..13361 run scoreboard players set #1000 enemy.state.poison 1686
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 10021, 13361)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 1686);
        }

        // execute if score @s enemy.hp matches 7515..10020 run scoreboard players set #1000 enemy.state.poison 1424
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 7515, 10020)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 1424);
        }

        // execute if score @s enemy.hp matches 5636..7514 run scoreboard players set #1000 enemy.state.poison 1203
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 5636, 7514)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 1203);
        }

        // execute if score @s enemy.hp matches 4227..5635 run scoreboard players set #1000 enemy.state.poison 1016
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 4227, 5635)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 1016);
        }

        // execute if score @s enemy.hp matches 3170..4226 run scoreboard players set #1000 enemy.state.poison 858
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 3170, 4226)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 858);
        }

        // execute if score @s enemy.hp matches 2377..3169 run scoreboard players set #1000 enemy.state.poison 725
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 2377, 3169)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 725);
        }

        // execute if score @s enemy.hp matches 1782..2376 run scoreboard players set #1000 enemy.state.poison 612
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1782, 2376)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 612);
        }

        // execute if score @s enemy.hp matches 1336..1781 run scoreboard players set #1000 enemy.state.poison 517
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1336, 1781)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 517);
        }

        // execute if score @s enemy.hp matches 1002..1335 run scoreboard players set #1000 enemy.state.poison 436
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1002, 1335)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 436);
        }

        // execute if score @s enemy.hp matches 751..1001 run scoreboard players set #1000 enemy.state.poison 368
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 751, 1001)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 368);
        }

        // execute if score @s enemy.hp matches 563..750 run scoreboard players set #1000 enemy.state.poison 310
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 563, 750)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 310);
        }

        // execute if score @s enemy.hp matches 422..562 run scoreboard players set #1000 enemy.state.poison 261
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 422, 562)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 261);
        }

        // execute if score @s enemy.hp matches 316..421 run scoreboard players set #1000 enemy.state.poison 220
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 316, 421)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 220);
        }

        // execute if score @s enemy.hp matches 237..315 run scoreboard players set #1000 enemy.state.poison 185
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 237, 315)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 185);
        }

        // execute if score @s enemy.hp matches 177..236 run scoreboard players set #1000 enemy.state.poison 156
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 177, 236)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 156);
        }

        // execute if score @s enemy.hp matches 132..176 run scoreboard players set #1000 enemy.state.poison 131
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 132, 176)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 131);
        }

        // execute if score @s enemy.hp matches 99..131 run scoreboard players set #1000 enemy.state.poison 110
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 99, 131)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 110);
        }

        // execute if score @s enemy.hp matches 74..98 run scoreboard players set #1000 enemy.state.poison 92
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 74, 98)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 92);
        }

        // execute if score @s enemy.hp matches 55..73 run scoreboard players set #1000 enemy.state.poison 77
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 55, 73)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 77);
        }

        // execute if score @s enemy.hp matches 41..54 run scoreboard players set #1000 enemy.state.poison 65
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 41, 54)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 65);
        }

        // execute if score @s enemy.hp matches 30..40 run scoreboard players set #1000 enemy.state.poison 54
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 30, 40)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 54);
        }

        // execute if score @s enemy.hp matches 22..29 run scoreboard players set #1000 enemy.state.poison 45
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 22, 29)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 45);
        }

        // execute if score @s enemy.hp matches 16..21 run scoreboard players set #1000 enemy.state.poison 38
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 16, 21)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 38);
        }

        // execute if score @s enemy.hp matches 12..15 run scoreboard players set #1000 enemy.state.poison 32
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 12, 15)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 32);
        }

        // execute if score @s enemy.hp matches 9..11 run scoreboard players set #1000 enemy.state.poison 27
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 9, 11)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 27);
        }

        // execute if score @s enemy.hp matches 6..8 run scoreboard players set #1000 enemy.state.poison 22
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 6, 8)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 22);
        }

        // execute if score @s enemy.hp matches 4..5 run scoreboard players set #1000 enemy.state.poison 18
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 4, 5)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 18);
        }

        // execute if score @s enemy.hp matches 3..3 run scoreboard players set #1000 enemy.state.poison 15
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 3, 3)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 15);
        }

        // execute if score @s enemy.hp matches 2..2 run scoreboard players set #1000 enemy.state.poison 12
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 2, 2)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 12);
        }

        // execute if score @s enemy.hp matches 1..1 run scoreboard players set #1000 enemy.state.poison 10
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1, 1)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 10);
        }

        // execute if score @s enemy.hp matches 0..0 run scoreboard players set #1000 enemy.state.poison 8
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 0, 0)) {
            KfcGen.setScore(sb, "#1000", "enemy.state.poison", 8);
        }

        // execute store result storage temp temp int 10 run scoreboard players get #1000 enemy.state.poison
        KfcGen.storagePutNumber(server, "temp", "temp", (KfcGen.getScore(sb, "#1000", "enemy.state.poison")) * 10, "int");

        // execute store result score #1000 enemy.state.poison run data get storage temp temp
        KfcGen.setScore(sb, "#1000", "enemy.state.poison", (int)(KfcGen.storageGetDouble(server, "temp", "temp")));

        // scoreboard players set #1000 enemy.hp 1000
        KfcGen.setScore(sb, "#1000", "enemy.hp", 1000);

        // scoreboard players operation #temp enemy.hp = @s enemy.max_hp
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.hp", "=", executor.getNameForScoreboard(), "enemy.max_hp");

        // scoreboard players operation #temp_r enemy.hp = @s enemy.max_hp
        if (executor != null) KfcGen.opScore(sb, "#temp_r", "enemy.hp", "=", executor.getNameForScoreboard(), "enemy.max_hp");

        // scoreboard players operation #temp enemy.hp /= #1000 enemy.hp
        KfcGen.opScore(sb, "#temp", "enemy.hp", "/=", "#1000", "enemy.hp");

        // scoreboard players operation #temp enemy.hp *= @s enemy.state.poison
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.hp", "*=", executor.getNameForScoreboard(), "enemy.state.poison");

        // scoreboard players operation #temp_r enemy.hp %= #1000 enemy.hp
        KfcGen.opScore(sb, "#temp_r", "enemy.hp", "%=", "#1000", "enemy.hp");

        // scoreboard players operation #temp_r enemy.hp *= @s enemy.state.poison
        if (executor != null) KfcGen.opScore(sb, "#temp_r", "enemy.hp", "*=", executor.getNameForScoreboard(), "enemy.state.poison");

        // scoreboard players operation #temp_r enemy.hp /= #1000 enemy.hp
        KfcGen.opScore(sb, "#temp_r", "enemy.hp", "/=", "#1000", "enemy.hp");

        // scoreboard players operation #temp enemy.hp += #temp_r enemy.hp
        KfcGen.opScore(sb, "#temp", "enemy.hp", "+=", "#temp_r", "enemy.hp");

        // execute if score @s enemy.hp <= #temp enemy.hp run particle dust{color:[0.0f,0.5f,0.0f],scale:2.0f} ~ ~1 ~ 0.3 0.6 0.3 0 20 force @a
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", "<=", "#temp", "enemy.hp")) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.3, 0.6, 0.3, 0, (int)(20), 0.0f, 0.5f, 0.0f, 2.0f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.hp <= #temp enemy.hp run particle dust{color:[0.5f,0.5f,0.5f],scale:2.0f} ~ ~1 ~ 0.3 0.6 0.3 0 20 force @a
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", "<=", "#temp", "enemy.hp")) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.3, 0.6, 0.3, 0, (int)(20), 0.5f, 0.5f, 0.5f, 2.0f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.hp <= #temp enemy.hp run particle dust{color:[0.1f,0.1f,0.1f],scale:2.0f} ~ ~1 ~ 0.3 0.6 0.3 0 20 force @a
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", "<=", "#temp", "enemy.hp")) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.3, 0.6, 0.3, 0, (int)(20), 0.1f, 0.1f, 0.1f, 2.0f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.hp <= #temp enemy.hp run function enemy:hit/death with entity @s data
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", "<=", "#temp", "enemy.hp")) {
            // -> enemy:hit/death
            tdpack.buckets.Bucket5._m_2db09a02_enemy_hit_death_execute(source, KfcGen.entityMacroArgs(executor, "data"));
        }

        // execute if score @s enemy.hp <= #temp enemy.hp run scoreboard players operation poison statistics += #temp enemy.hp
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", "<=", "#temp", "enemy.hp")) {
            KfcGen.opScore(sb, "poison", "statistics", "+=", "#temp", "enemy.hp");
        }

        // execute if score #temp enemy.attribute.timer matches 0 store result score #temp enemy.hp run scoreboard players get @s enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.setScore(sb, "#temp", "enemy.hp", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp"));
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp enemy.hp /= #1000 enemy.state.poison
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "#temp", "enemy.hp", "/=", "#1000", "enemy.state.poison");
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation @s enemy.hp -= #temp enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "-=", "#temp", "enemy.hp");
        }

        // execute if score #temp enemy.attribute.timer matches 0 store result storage temp poison int 0.97 run scoreboard players get @s enemy.state.poison
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.storagePutNumber(server, "temp", "poison", (KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison")) * 0.97, "int");
        }

        // execute if score #temp enemy.attribute.timer matches 0 store result score @s enemy.state.poison run data get storage temp poison
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.poison", (int)(KfcGen.storageGetDouble(server, "temp", "poison")));
        }

        // execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation poison statistics += #temp enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0)) {
            KfcGen.opScore(sb, "poison", "statistics", "+=", "#temp", "enemy.hp");
        }
        return 0;
    }

    public static void _m_eaab00d1_enemy_attribute_debuff_stun_execute(ServerCommandSource source) {
        _m_eaab00d1_enemy_attribute_debuff_stun_executeReturn(source);
    }

    public static int _m_eaab00d1_enemy_attribute_debuff_stun_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players remove @s enemy.state.stun 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.state.stun", -(1));

        // execute if score @s enemy.state.stun matches 0.. run scoreboard players set @s enemy.state.stun-immune 50
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun", 0, Integer.MAX_VALUE)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.stun-immune", 50);
        }
        return 0;
    }

    public static void _m_1c952a44_enemy_attribute_debuff_stun_immune_execute(ServerCommandSource source) {
        _m_1c952a44_enemy_attribute_debuff_stun_immune_executeReturn(source);
    }

    public static int _m_1c952a44_enemy_attribute_debuff_stun_immune_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players remove @s enemy.state.stun-immune 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.state.stun-immune", -(1));
        return 0;
    }

    public static void _m_d89b51bd_enemy_attribute_debuff_weakness_execute(ServerCommandSource source) {
        _m_d89b51bd_enemy_attribute_debuff_weakness_executeReturn(source);
    }

    public static int _m_d89b51bd_enemy_attribute_debuff_weakness_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.state.weakness matches 25.. if score @s enemy.defence matches 1.. run execute store result storage temp weakness.defence int 0.075 run scoreboard players get @s enemy.defence
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 25, Integer.MAX_VALUE)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence", 1, Integer.MAX_VALUE)) {
                KfcGen.storagePutNumber(server, "temp", "weakness.defence", (KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence")) * 0.075, "int");
            }
        }

        // execute if score @s enemy.state.weakness matches 25.. if score @s enemy.defence matches 1.. run execute store result score weakness-apply enemy.defence run data get storage temp weakness.defence
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 25, Integer.MAX_VALUE)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence", 1, Integer.MAX_VALUE)) {
                KfcGen.setScore(sb, "weakness-apply", "enemy.defence", (int)(KfcGen.storageGetDouble(server, "temp", "weakness.defence")));
            }
        }

        // execute if score @s enemy.state.weakness matches 25.. if score @s enemy.defence matches ..0 run scoreboard players set weakness-apply enemy.defence 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 25, Integer.MAX_VALUE)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence", Integer.MIN_VALUE, 0)) {
                KfcGen.setScore(sb, "weakness-apply", "enemy.defence", 1);
            }
        }

        // execute if score @s enemy.state.weakness matches 25.. unless score weakness-apply enemy.defence matches 1.. run scoreboard players set weakness-apply enemy.defence 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 25, Integer.MAX_VALUE)) {
            if (!(KfcGen.scoreMatches(sb, "weakness-apply", "enemy.defence", 1, Integer.MAX_VALUE))) {
                KfcGen.setScore(sb, "weakness-apply", "enemy.defence", 1);
            }
        }

        // execute if score @s enemy.state.weakness matches 25.. run scoreboard players operation @s enemy.defence -= weakness-apply enemy.defence
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 25, Integer.MAX_VALUE)) {
            if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.defence", "-=", "weakness-apply", "enemy.defence");
        }

        // execute if score @s enemy.state.weakness matches 25.. run scoreboard players operation weakness statistics += weakness-apply enemy.defence
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 25, Integer.MAX_VALUE)) {
            KfcGen.opScore(sb, "weakness", "statistics", "+=", "weakness-apply", "enemy.defence");
        }

        // execute if score @s enemy.state.weakness matches 25.. run scoreboard players remove @s enemy.state.weakness 25
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.weakness", 25, Integer.MAX_VALUE)) {
            if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.state.weakness", -(25));
        }
        return 0;
    }

    public static void _m_792e32cd_enemy_hit_calculate_defence_execute(ServerCommandSource source) {
        _m_792e32cd_enemy_hit_calculate_defence_executeReturn(source);
    }

    public static int _m_792e32cd_enemy_hit_calculate_defence_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.defence matches 1.. run function enemy:hit/calculate_defence_plus
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence", 1, Integer.MAX_VALUE)) {
            // -> enemy:hit/calculate_defence_plus
            tdpack.buckets.Bucket5._m_efcfd84d_enemy_hit_calculate_defence_plus_execute(source);
        }

        // execute if score @s enemy.defence matches ..-1 run function enemy:hit/calculate_defence_minus
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence", Integer.MIN_VALUE, -1)) {
            // -> enemy:hit/calculate_defence_minus
            tdpack.buckets.Bucket5._m_08689671_enemy_hit_calculate_defence_minus_execute(source);
        }
        return 0;
    }

    public static void _m_08689671_enemy_hit_calculate_defence_minus_execute(ServerCommandSource source) {
        _m_08689671_enemy_hit_calculate_defence_minus_executeReturn(source);
    }

    public static int _m_08689671_enemy_hit_calculate_defence_minus_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #100 enemy.defence 100
        KfcGen.setScore(sb, "#100", "enemy.defence", 100);

        // scoreboard players set #buffer enemy.defence 10000
        KfcGen.setScore(sb, "#buffer", "enemy.defence", 10000);

        // scoreboard players set #buffer3 enemy.defence -1
        KfcGen.setScore(sb, "#buffer3", "enemy.defence", -1);

        // scoreboard players operation #buffer3 enemy.defence *= @s enemy.defence
        if (executor != null) KfcGen.opScore(sb, "#buffer3", "enemy.defence", "*=", executor.getNameForScoreboard(), "enemy.defence");

        // scoreboard players operation #buffer enemy.defence *= #buffer3 enemy.defence
        KfcGen.opScore(sb, "#buffer", "enemy.defence", "*=", "#buffer3", "enemy.defence");

        // scoreboard players set #buffer2 enemy.defence 100
        KfcGen.setScore(sb, "#buffer2", "enemy.defence", 100);

        // scoreboard players operation #buffer2 enemy.defence += #buffer3 enemy.defence
        KfcGen.opScore(sb, "#buffer2", "enemy.defence", "+=", "#buffer3", "enemy.defence");

        // scoreboard players operation #buffer enemy.defence /= #buffer2 enemy.defence
        KfcGen.opScore(sb, "#buffer", "enemy.defence", "/=", "#buffer2", "enemy.defence");

        // scoreboard players operation #buffer enemy.defence *= #damage enemy.hp
        KfcGen.opScore(sb, "#buffer", "enemy.defence", "*=", "#damage", "enemy.hp");

        // scoreboard players set 10000 enemy.defence 10000
        KfcGen.setScore(sb, "10000", "enemy.defence", 10000);

        // scoreboard players operation #buffer enemy.defence /= 10000 enemy.defence
        KfcGen.opScore(sb, "#buffer", "enemy.defence", "/=", "10000", "enemy.defence");

        // scoreboard players operation #damage enemy.hp += #buffer enemy.defence
        KfcGen.opScore(sb, "#damage", "enemy.hp", "+=", "#buffer", "enemy.defence");
        return 0;
    }

    public static void _m_efcfd84d_enemy_hit_calculate_defence_plus_execute(ServerCommandSource source) {
        _m_efcfd84d_enemy_hit_calculate_defence_plus_executeReturn(source);
    }

    public static int _m_efcfd84d_enemy_hit_calculate_defence_plus_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #100 enemy.defence 100
        KfcGen.setScore(sb, "#100", "enemy.defence", 100);

        // scoreboard players set #buffer enemy.defence 10000
        KfcGen.setScore(sb, "#buffer", "enemy.defence", 10000);

        // scoreboard players operation #buffer enemy.defence *= @s enemy.defence
        if (executor != null) KfcGen.opScore(sb, "#buffer", "enemy.defence", "*=", executor.getNameForScoreboard(), "enemy.defence");

        // scoreboard players set #buffer2 enemy.defence 100
        KfcGen.setScore(sb, "#buffer2", "enemy.defence", 100);

        // scoreboard players operation #buffer2 enemy.defence += @s enemy.defence
        if (executor != null) KfcGen.opScore(sb, "#buffer2", "enemy.defence", "+=", executor.getNameForScoreboard(), "enemy.defence");

        // scoreboard players operation #buffer enemy.defence /= #buffer2 enemy.defence
        KfcGen.opScore(sb, "#buffer", "enemy.defence", "/=", "#buffer2", "enemy.defence");

        // scoreboard players operation #buffer enemy.defence *= #damage enemy.hp
        KfcGen.opScore(sb, "#buffer", "enemy.defence", "*=", "#damage", "enemy.hp");

        // scoreboard players set 10000 enemy.defence 10000
        KfcGen.setScore(sb, "10000", "enemy.defence", 10000);

        // scoreboard players operation #buffer enemy.defence /= 10000 enemy.defence
        KfcGen.opScore(sb, "#buffer", "enemy.defence", "/=", "10000", "enemy.defence");

        // scoreboard players operation #damage enemy.hp -= #buffer enemy.defence
        KfcGen.opScore(sb, "#damage", "enemy.hp", "-=", "#buffer", "enemy.defence");
        return 0;
    }

    public static void _m_2db09a02_enemy_hit_death_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_2db09a02_enemy_hit_death_executeReturn(source, macroArgs);
    }

    public static int _m_2db09a02_enemy_hit_death_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $scoreboard players add @a money $(money)
        for (net.minecraft.entity.Entity _t : ctx.allPlayers) {
            try { KfcGen.addScore(sb, _t.getNameForScoreboard(), "money", Integer.parseInt(macroArgs.get("money"))); } catch (NumberFormatException _nfe) {}
        }

        // scoreboard players add killcount info 1
        KfcGen.addScore(sb, "killcount", "info", 1);

        // scoreboard players add @e[tag=catalyst-zombie, distance=..3] enemy.hp 150
        for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"catalyst-zombie"}, new String[0], -1, 3)) {
            KfcGen.addScore(sb, _t.getNameForScoreboard(), "enemy.hp", 150);
        }

        // execute if entity @s[tag=enemy.attribute.dark] at @s run function enemy:ability/death/dark-attribute
        {
            ServerCommandSource kfcSrc1 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("enemy.attribute.dark"))) {
                // -> enemy:ability/death/dark-attribute
                tdpack.buckets.Bucket4._m_b68ac934_enemy_ability_death_dark_attribute_execute(kfcSrc1);
            }
        }

        // $execute if entity @s[tag=enemy.skill-trigger.death] run function enemy:ability/death/$(id)
        if ((executor != null && executor.getCommandTags().contains("enemy.skill-trigger.death"))) {
            // -> enemy:ability/death/macroArgs.get("id")
            switch (macroArgs.get("id")) {
                case "charged-creeper": tdpack.buckets.Bucket4._m_add5a37e_enemy_ability_death_charged_creeper_execute(source); break;
                case "creeper": tdpack.buckets.Bucket4._m_8f6e0b88_enemy_ability_death_creeper_execute(source); break;
                case "dark-attribute": tdpack.buckets.Bucket4._m_b68ac934_enemy_ability_death_dark_attribute_execute(source); break;
                case "dark-headbomb": tdpack.buckets.Bucket4._m_8f0342be_enemy_ability_death_dark_headbomb_execute(source); break;
                case "endermite": tdpack.buckets.Bucket4._m_8ebf2e22_enemy_ability_death_endermite_execute(source); break;
                case "endermite-egg": tdpack.buckets.Bucket4._m_b79fe92e_enemy_ability_death_endermite_egg_execute(source); break;
                case "indura-zombie": tdpack.buckets.Bucket4._m_d63ad9bb_enemy_ability_death_indura_zombie_execute(source); break;
                case "revive-zombie": tdpack.buckets.Bucket4._m_f4312e18_enemy_ability_death_revive_zombie_execute(source); break;
                case "silverfish": tdpack.buckets.Bucket4._m_07241b4c_enemy_ability_death_silverfish_execute(source); break;
                case "silverfish-egg": tdpack.buckets.Bucket4._m_1ce5b19e_enemy_ability_death_silverfish_egg_execute(source); break;
                case "split-zombie": tdpack.buckets.Bucket4._m_a89b1407_enemy_ability_death_split_zombie_execute(source); break;
                default: break;
            }
        }

        // execute if entity @s[type=!marker] on passengers run kill @s
        if ((executor != null && !(executor.getType() == EntityType.MARKER))) {
            for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
                net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
                if (_onEnt1 != null) KfcGen.killEntity(_onEnt1);
            }
        }

        // execute if entity @s[type=marker] on vehicle on passengers run kill @s
        if ((executor != null && executor.getType() == EntityType.MARKER)) {
            { ServerCommandSource _on1 = KfcGen.onVehicle(source);
              if (_on1 != null) {
                net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
                for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                    net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                    if (_onEnt2 != null) KfcGen.killEntity(_onEnt2);
                }
              } }
        }

        // kill @s
        if (executor != null) KfcGen.killEntity(executor);
        return 0;
    }

    public static void _m_8b8e0228_enemy_hit_main_execute(ServerCommandSource source) {
        _m_8b8e0228_enemy_hit_main_executeReturn(source);
    }

    public static int _m_8b8e0228_enemy_hit_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // function enemy:hit/calculate_defence
        // -> enemy:hit/calculate_defence
        tdpack.buckets.Bucket5._m_792e32cd_enemy_hit_calculate_defence_execute(source);

        // execute if entity @s[tag=!enemy.dark-field.immune] if score #temp enemy.state.stun matches 1.. unless score @s[tag=!enemy.immune.stun] enemy.state.stun-immune matches 1.. if score @s enemy.state.stun <= #temp enemy.state.stun run scoreboard players operation @s enemy.state.stun = #temp enemy.state.stun
        if ((executor != null && !executor.getCommandTags().contains("enemy.dark-field.immune"))) {
            if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.stun", 1, Integer.MAX_VALUE)) {
                if (KfcGen.entityScoreMatches(sb, ((!executor.getCommandTags().contains("enemy.immune.stun")) ? executor : null), "enemy.state.stun-immune", 1, Integer.MAX_VALUE, true)) {
                    if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun", "<=", "#temp", "enemy.state.stun")) {
                        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.state.stun", "=", "#temp", "enemy.state.stun");
                    }
                }
            }
        }

        // execute if score #temp enemy.state.freeze matches 1.. if score @s[tag=!enemy.immune.freeze,tag=!enemy.dark-field.immune] enemy.state.freeze <= #temp enemy.state.freeze run scoreboard players operation @s enemy.state.freeze = #temp enemy.state.freeze
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.freeze", 1, Integer.MAX_VALUE)) {
            if (KfcGen.scoreCmp(sb, ((!executor.getCommandTags().contains("enemy.immune.freeze") && !executor.getCommandTags().contains("enemy.dark-field.immune")) ? executor : null), "enemy.state.freeze", "<=", "#temp", "enemy.state.freeze", false)) {
                if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.state.freeze", "=", "#temp", "enemy.state.freeze");
            }
        }

        // execute if score #temp enemy.state.poison matches 1.. run scoreboard players operation @s[tag=!enemy.immune.poison,tag=!enemy.dark-field.immune] enemy.state.poison += #temp enemy.state.poison
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.poison", 1, Integer.MAX_VALUE)) {
            if (executor != null && ((executor != null && !executor.getCommandTags().contains("enemy.immune.poison") && !executor.getCommandTags().contains("enemy.dark-field.immune")))) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.state.poison", "+=", "#temp", "enemy.state.poison");
        }

        // execute if score #temp enemy.state.flame matches 1.. run scoreboard players operation @s[tag=!enemy.immune.flame,tag=!enemy.dark-field.immune] enemy.state.flame += #temp enemy.state.flame
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.flame", 1, Integer.MAX_VALUE)) {
            if (executor != null && ((executor != null && !executor.getCommandTags().contains("enemy.immune.flame") && !executor.getCommandTags().contains("enemy.dark-field.immune")))) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.state.flame", "+=", "#temp", "enemy.state.flame");
        }

        // execute if score #temp enemy.state.bleed matches 1.. run scoreboard players operation @s[tag=!enemy.immune.bleed,tag=!enemy.dark-field.immune] enemy.state.bleed += #temp enemy.state.bleed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.bleed", 1, Integer.MAX_VALUE)) {
            if (executor != null && ((executor != null && !executor.getCommandTags().contains("enemy.immune.bleed") && !executor.getCommandTags().contains("enemy.dark-field.immune")))) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.state.bleed", "+=", "#temp", "enemy.state.bleed");
        }

        // execute if score #temp enemy.state.weakness matches 1.. run scoreboard players operation @s[tag=!enemy.immune.weakness,tag=!enemy.dark-field.immune] enemy.state.weakness += #temp enemy.state.weakness
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.state.weakness", 1, Integer.MAX_VALUE)) {
            if (executor != null && ((executor != null && !executor.getCommandTags().contains("enemy.immune.weakness") && !executor.getCommandTags().contains("enemy.dark-field.immune")))) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.state.weakness", "+=", "#temp", "enemy.state.weakness");
        }

        // scoreboard players operation @s enemy.hp -= #damage enemy.hp
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "-=", "#damage", "enemy.hp");

        // execute if entity @s[type=!end_crystal,type=!enderman] if score #damage enemy.hp matches 1.. run damage @s 0.001 out_of_world
        if ((executor != null && !(executor.getType() == EntityType.ENDERMAN))) {
            if (KfcGen.scoreMatches(sb, "#damage", "enemy.hp", 1, Integer.MAX_VALUE)) {
                if (executor != null) KfcGen.applyDamage(executor, source.getWorld(), 0.001f, "out_of_world");
            }
        }

        // tag @s remove enemy.target.hit
        if (executor != null) executor.removeCommandTag("enemy.target.hit");

        // execute unless score @s enemy.hp matches 1.. run function enemy:hit/death with entity @s data
        if (!(KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", 1, Integer.MAX_VALUE))) {
            // -> enemy:hit/death
            tdpack.buckets.Bucket5._m_2db09a02_enemy_hit_death_execute(source, KfcGen.entityMacroArgs(executor, "data"));
        }
        return 0;
    }

    public static void _m_f4daf345_enemy_move_calculate_freeze_execute(ServerCommandSource source) {
        _m_f4daf345_enemy_move_calculate_freeze_executeReturn(source);
    }

    public static int _m_f4daf345_enemy_move_calculate_freeze_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score #temp enemy.speed run data get storage enemy temp.speed 10000
        KfcGen.setScore(sb, "#temp", "enemy.speed", (int)((KfcGen.storageGetDouble(server, "enemy", "temp.speed")) * 10000));

        // scoreboard players set #temp enemy.state.freeze 100
        KfcGen.setScore(sb, "#temp", "enemy.state.freeze", 100);

        // scoreboard players operation #temp enemy.state.freeze -= @s enemy.state.freeze
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.state.freeze", "-=", executor.getNameForScoreboard(), "enemy.state.freeze");

        // scoreboard players operation #temp enemy.state.freeze *= #temp enemy.speed
        KfcGen.opScore(sb, "#temp", "enemy.state.freeze", "*=", "#temp", "enemy.speed");

        // execute store result storage enemy temp.speed float 0.000001 run scoreboard players get #temp enemy.state.freeze
        KfcGen.storagePutNumber(server, "enemy", "temp.speed", (KfcGen.getScore(sb, "#temp", "enemy.state.freeze")) * 0.000001, "float");
        return 0;
    }

    public static void _m_6c3b8400_enemy_move_main_execute(ServerCommandSource source) {
        _m_6c3b8400_enemy_move_main_executeReturn(source);
    }

    public static int _m_6c3b8400_enemy_move_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if entity @s[tag=enemy.data] run data modify storage enemy temp set from entity @s data
        if ((executor != null && executor.getCommandTags().contains("enemy.data"))) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data"); if (_v != null) KfcGen.nbtSetStorage(server, "enemy", "temp", _v); }
        }

        // execute unless entity @s[tag=enemy.data] on passengers if entity @s[tag=enemy.data] run data modify storage enemy temp set from entity @s data
        if (!((executor != null && executor.getCommandTags().contains("enemy.data")))) {
            for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
                net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
                if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.data"))) {
                    { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(_onEnt1, "data"); if (_v != null) KfcGen.nbtSetStorage(server, "enemy", "temp", _v); }
                }
            }
        }

        // execute if entity @s[tag=enemy.data] if score @s enemy.state.freeze matches 1.. run function enemy:move/calculate_freeze
        if ((executor != null && executor.getCommandTags().contains("enemy.data"))) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.freeze", 1, Integer.MAX_VALUE)) {
                // -> enemy:move/calculate_freeze
                tdpack.buckets.Bucket5._m_f4daf345_enemy_move_calculate_freeze_execute(source);
            }
        }

        // execute unless entity @s[tag=enemy.data] on passengers if entity @s[tag=enemy.data] if score @s enemy.state.freeze matches 1.. run function enemy:move/calculate_freeze
        if (!((executor != null && executor.getCommandTags().contains("enemy.data")))) {
            for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
                net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
                if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.data"))) {
                    if (KfcGen.scoreMatches(sb, (_onEnt1 == null ? "<no-_onEnt1>" : _onEnt1.getNameForScoreboard()), "enemy.state.freeze", 1, Integer.MAX_VALUE)) {
                        // -> enemy:move/calculate_freeze
                        tdpack.buckets.Bucket5._m_f4daf345_enemy_move_calculate_freeze_execute(_on1);
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.data] if score @s enemy.state.stun matches 1.. run data modify storage enemy temp.speed set value 0
        if ((executor != null && executor.getCommandTags().contains("enemy.data"))) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun", 1, Integer.MAX_VALUE)) {
                KfcGen.storagePutNumber(server, "enemy", "temp.speed", 0, "int");
            }
        }

        // execute unless entity @s[tag=enemy.data] on passengers if entity @s[tag=enemy.data] if score @s enemy.state.stun matches 1.. run data modify storage enemy temp.speed set value 0
        if (!((executor != null && executor.getCommandTags().contains("enemy.data")))) {
            for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
                net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
                if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.data"))) {
                    if (KfcGen.scoreMatches(sb, (_onEnt1 == null ? "<no-_onEnt1>" : _onEnt1.getNameForScoreboard()), "enemy.state.stun", 1, Integer.MAX_VALUE)) {
                        KfcGen.storagePutNumber(server, "enemy", "temp.speed", 0, "int");
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.data] if score @s enemy.skill-trigger.timer-cooldown matches ..0 run data modify storage enemy temp.speed set value 0
        if ((executor != null && executor.getCommandTags().contains("enemy.data"))) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0)) {
                KfcGen.storagePutNumber(server, "enemy", "temp.speed", 0, "int");
            }
        }

        // execute unless entity @s[tag=enemy.data] on passengers if entity @s[tag=enemy.data] if score @s enemy.skill-trigger.timer-cooldown matches ..0 run data modify storage enemy temp.speed set value 0
        if (!((executor != null && executor.getCommandTags().contains("enemy.data")))) {
            for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
                net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
                if ((_onEnt1 != null && _onEnt1.getCommandTags().contains("enemy.data"))) {
                    if (KfcGen.scoreMatches(sb, (_onEnt1 == null ? "<no-_onEnt1>" : _onEnt1.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0)) {
                        KfcGen.storagePutNumber(server, "enemy", "temp.speed", 0, "int");
                    }
                }
            }
        }

        // function enemy:move/teleport with storage enemy temp
        // -> enemy:move/teleport
        tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(source, KfcGen.storageMacroArgs(server, "enemy", "temp"));
        return 0;
    }

    public static void _m_3ad1db46_enemy_move_sync_execute(ServerCommandSource source) {
        _m_3ad1db46_enemy_move_sync_executeReturn(source);
    }

    public static int _m_3ad1db46_enemy_move_sync_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #2 enemy.onGround 2
        KfcGen.setScore(sb, "#2", "enemy.onGround", 2);

        // scoreboard players operation #temp enemy.onGround = @s enemy.onGround
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.onGround", "=", executor.getNameForScoreboard(), "enemy.onGround");

        // scoreboard players operation #temp enemy.onGround %= #2 enemy.onGround
        KfcGen.opScore(sb, "#temp", "enemy.onGround", "%=", "#2", "enemy.onGround");

        // execute if score #temp enemy.onGround matches 1 run data modify entity @s OnGround set value true
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.onGround", 1, 1)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "OnGround", "true");
        }

        // execute unless score #temp enemy.onGround matches 1 run data modify entity @s OnGround set value false
        if (!(KfcGen.scoreMatches(sb, "#temp", "enemy.onGround", 1, 1))) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "OnGround", "false");
        }

        // scoreboard players remove @s enemy.onGround 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.onGround", -(1));
        return 0;
    }

    public static void _m_9284ec00_enemy_move_teleport_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_9284ec00_enemy_move_teleport_executeReturn(source, macroArgs);
    }

    public static int _m_9284ec00_enemy_move_teleport_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score #temp game.return run function enemy:move/checkpoint/main with storage enemy temp
        KfcGen.setScore(sb, "#temp", "game.return", tdpack.buckets.Bucket5._m_0eac30ae_enemy_move_checkpoint_main_executeReturn(source));

        // execute store result score #temp enemy.progress run data get storage enemy temp.speed 10000
        KfcGen.setScore(sb, "#temp", "enemy.progress", (int)((KfcGen.storageGetDouble(server, "enemy", "temp.speed")) * 10000));

        // scoreboard players operation @s enemy.progress += #temp enemy.progress
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.progress", "+=", "#temp", "enemy.progress");

        // execute if score #temp game.return matches 1 run return 0
        if (KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1)) {
            return 0;
        }

        // $execute positioned ^ ^ ^$(speed) run teleport ~ ~ ~
        {
            ServerCommandSource kfcSrc8 = null; try { kfcSrc8 = source.withPosition(KfcGen.localOffset(source.getPosition(), source.getRotation(), 0.0, 0.0, Double.parseDouble(macroArgs.get("speed")))); } catch (NumberFormatException _nfe) {}
            {
                net.minecraft.util.math.Vec3d _tpPos = new net.minecraft.util.math.Vec3d(kfcSrc8.getPosition().x, kfcSrc8.getPosition().y, kfcSrc8.getPosition().z);
            if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
            }
        }

        // execute if score @s enemy.onGround matches 1.. run function enemy:move/sync
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.onGround", 1, Integer.MAX_VALUE)) {
            // -> enemy:move/sync
            tdpack.buckets.Bucket5._m_3ad1db46_enemy_move_sync_execute(source);
        }

        // return 1
        return 1;
    }

    public static void _m_bf4fe44b_enemy_move_checkpoint_checkpoint_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_bf4fe44b_enemy_move_checkpoint_checkpoint_executeReturn(source, macroArgs);
    }

    public static int _m_bf4fe44b_enemy_move_checkpoint_checkpoint_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $execute positioned ~ -60 ~ unless entity @n[tag=map.check_point,distance=..$(speed)] run return 0
        {
            ServerCommandSource kfcSrc2 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            boolean _mcond3 = false; try { _mcond3 = (!(KfcGen.anyEntityAnyType(ctx, kfcSrc2.getPosition(), new String[]{"map.check_point"}, new String[0], -1, Double.parseDouble(macroArgs.get("speed"))))); } catch (NumberFormatException _nfe) {}
            if (_mcond3) {
                return 0;
            }
        }

        // scoreboard players set #360 enemy.rotation 360
        KfcGen.setScore(sb, "#360", "enemy.rotation", 360);

        // execute store result score #temp enemy.rotation run data get entity @n[tag=map.check_point] Rotation[0]
        KfcGen.setScore(sb, "#temp", "enemy.rotation", (int)(KfcGen.entityGetDouble(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.check_point"}, new String[0], -1, -1, _ee -> (true)), "Rotation[0]")));

        // execute store result score #temp2 enemy.rotation run data get entity @s Rotation[0]
        KfcGen.setScore(sb, "#temp2", "enemy.rotation", (int)(KfcGen.entityGetDouble(executor, "Rotation[0]")));

        // scoreboard players operation #temp2 enemy.rotation %= #360 enemy.rotation
        KfcGen.opScore(sb, "#temp2", "enemy.rotation", "%=", "#360", "enemy.rotation");

        // execute if score #temp enemy.rotation = #temp2 enemy.rotation run return 0
        if (KfcGen.scoreCmp(sb, "#temp", "enemy.rotation", "=", "#temp2", "enemy.rotation")) {
            return 0;
        }

        // $execute at @n[tag=map.check_point] run tp @s ~ $(y) ~ ~ ~
        {
            net.minecraft.entity.Entity _atE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.check_point"}, new String[0], -1, -1, _ee -> (true));
            ServerCommandSource kfcSrc4 = (_atE1 != null ? source.withPosition(_atE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_atE1.getPitch(), _atE1.getYaw())) : null);
            if (kfcSrc4 != null) {
                {
                    net.minecraft.util.math.Vec3d _tpPos = null; try { _tpPos = new net.minecraft.util.math.Vec3d(kfcSrc4.getPosition().x, Double.parseDouble(macroArgs.get("y")), kfcSrc4.getPosition().z); } catch (NumberFormatException _nfe) {}
                if (executor != null) KfcGen.teleportToWithRot(executor, _tpPos.x, _tpPos.y, _tpPos.z, kfcSrc4.getRotation().y, kfcSrc4.getRotation().x);
                }
            }
        }

        // execute on passengers at @s run rotate @s ~ ~
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            {
                ServerCommandSource kfcSrc5 = (_onEnt1 != null ? _on1.withPosition(_onEnt1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_onEnt1.getPitch(), _onEnt1.getYaw())) : _on1);
                if (_onEnt1 != null) KfcGen.rotateTo(_onEnt1, kfcSrc5.getRotation().y, kfcSrc5.getRotation().x);
            }
        }

        // scoreboard players set @s enemy.onGround 50
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.onGround", 50);

        // return 1
        return 1;
    }

    public static void _m_aa338f28_enemy_move_checkpoint_dest_point_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_aa338f28_enemy_move_checkpoint_dest_point_executeReturn(source, macroArgs);
    }

    public static int _m_aa338f28_enemy_move_checkpoint_dest_point_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $execute positioned ~ -60 ~ unless entity @n[tag=map.dest_point,distance=..$(speed)] run return 0
        {
            ServerCommandSource kfcSrc6 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            boolean _mcond7 = false; try { _mcond7 = (!(KfcGen.anyEntityAnyType(ctx, kfcSrc6.getPosition(), new String[]{"map.dest_point"}, new String[0], -1, Double.parseDouble(macroArgs.get("speed"))))); } catch (NumberFormatException _nfe) {}
            if (_mcond7) {
                return 0;
            }
        }

        // scoreboard players operation game game.base_health -= @s enemy.hp
        if (executor != null) KfcGen.opScore(sb, "game", "game.base_health", "-=", executor.getNameForScoreboard(), "enemy.hp");

        // execute on passengers run kill @s
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (_onEnt1 != null) KfcGen.killEntity(_onEnt1);
        }

        // scoreboard players set #temp game.return 1
        KfcGen.setScore(sb, "#temp", "game.return", 1);

        // kill @s
        if (executor != null) KfcGen.killEntity(executor);
        return 0;
    }

    public static void _m_0eac30ae_enemy_move_checkpoint_main_execute(ServerCommandSource source) {
        _m_0eac30ae_enemy_move_checkpoint_main_executeReturn(source);
    }

    public static int _m_0eac30ae_enemy_move_checkpoint_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result storage enemy temp.y double 1 run data get entity @s Pos[1] 1.0
        KfcGen.storagePutNumber(server, "enemy", "temp.y", (int)(KfcGen.entityGetDouble(executor, "Pos[1]")), "double");

        // execute store result score #temp game.return run function enemy:move/checkpoint/checkpoint with storage enemy temp
        KfcGen.setScore(sb, "#temp", "game.return", tdpack.buckets.Bucket5._m_bf4fe44b_enemy_move_checkpoint_checkpoint_executeReturn(source, KfcGen.storageMacroArgs(server, "enemy", "temp")));

        // execute if score #temp game.return matches 1 run return 1
        if (KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1)) {
            return 1;
        }

        // function enemy:move/checkpoint/dest_point with storage enemy temp
        // -> enemy:move/checkpoint/dest_point
        tdpack.buckets.Bucket5._m_aa338f28_enemy_move_checkpoint_dest_point_execute(source, KfcGen.storageMacroArgs(server, "enemy", "temp"));
        return 0;
    }

    public static void _m_426f2211_enemy_spawn_model_armord_zombie_execute(ServerCommandSource source) {
        _m_426f2211_enemy_spawn_model_armord_zombie_executeReturn(source);
    }

    public static int _m_426f2211_enemy_spawn_model_armord_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:225, speed:0.07, money:200, defence:25, id:armord-zombie,name:{color:white,"text":"아머드 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:225, speed:0.07, money:200, defence:25, id:armord-zombie,name:{color:white,\"text\":\"아머드 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},legs:{id:netherite_leggings},feet:{id:netherite_boots}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"아머드 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},legs:{id:netherite_leggings},feet:{id:netherite_boots}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"아머드 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7dce365e_enemy_spawn_model_bogged_execute(ServerCommandSource source) {
        _m_7dce365e_enemy_spawn_model_bogged_executeReturn(source);
    }

    public static int _m_7dce365e_enemy_spawn_model_bogged_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:350, speed:0.16, money:250,id:bogged,name:{color:white,"text":"보그드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:350, speed:0.16, money:250,id:bogged,name:{color:white,\"text\":\"보그드\\n\",\"bold\":true}}", "set");

        // summon bogged ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "bogged", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_09a7e34f_enemy_spawn_model_breeze_execute(ServerCommandSource source) {
        _m_09a7e34f_enemy_spawn_model_breeze_executeReturn(source);
    }

    public static int _m_09a7e34f_enemy_spawn_model_breeze_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:400, speed:0.22, defence:25,money:400,id:breeze,name:{color:white,"text":"브리즈\n","bold":true}, skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:400, speed:0.22, defence:25,money:400,id:breeze,name:{color:white,\"text\":\"브리즈\\n\",\"bold\":true}, skills:{timer:100}}", "set");

        // summon breeze ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_breeze,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_breeze,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "breeze", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_breeze,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_breeze,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_19d190eb_enemy_spawn_model_bright_zombie_execute(ServerCommandSource source) {
        _m_19d190eb_enemy_spawn_model_bright_zombie_executeReturn(source);
    }

    public static int _m_19d190eb_enemy_spawn_model_bright_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:350, speed:0.15, defence:100, money:350,id:bright-zombie,name:{color:white,"text":"발광 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:350, speed:0.15, defence:100, money:350,id:bright-zombie,name:{color:white,\"text\":\"발광 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {active_effects:[{id:"minecraft:glowing",amplifier:0,duration:-1}],Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.taunt],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{active_effects:[{id:\"minecraft:glowing\",amplifier:0,duration:-1}],Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.taunt],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_2c1f1a87_enemy_spawn_model_carsinops_execute(ServerCommandSource source) {
        _m_2c1f1a87_enemy_spawn_model_carsinops_executeReturn(source);
    }

    public static int _m_2c1f1a87_enemy_spawn_model_carsinops_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:80000, speed:0.07, money:50000,id:carsinops,name:{color:white,"text":"카르시놉스\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:80000, speed:0.07, money:50000,id:carsinops,name:{color:white,\"text\":\"카르시놉스\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_ddd56cca_enemy_spawn_model_catalyst_zombie_execute(ServerCommandSource source) {
        _m_ddd56cca_enemy_spawn_model_catalyst_zombie_executeReturn(source);
    }

    public static int _m_ddd56cca_enemy_spawn_model_catalyst_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1600, speed:0.1, money:1435,id:catalyst-zombie,name:{color:white,"text":"촉매 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1600, speed:0.1, money:1435,id:catalyst-zombie,name:{color:white,\"text\":\"촉매 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,catalyst-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,catalyst-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_176bef65_enemy_spawn_model_cave_spider_execute(ServerCommandSource source) {
        _m_176bef65_enemy_spawn_model_cave_spider_executeReturn(source);
    }

    public static int _m_176bef65_enemy_spawn_model_cave_spider_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:80, speed:0.16, money:50,id:cave-spider,name:{color:white,"text":"동굴 거미\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:80, speed:0.16, money:50,id:cave-spider,name:{color:white,\"text\":\"동굴 거미\\n\",\"bold\":true}}", "set");

        // summon cave_spider ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.text],text:[{"color":"dark_green","text":"동굴 거미\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "cave_spider", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"동굴 거미\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_367c76db_enemy_spawn_model_charged_creeper_execute(ServerCommandSource source) {
        _m_367c76db_enemy_spawn_model_charged_creeper_executeReturn(source);
    }

    public static int _m_367c76db_enemy_spawn_model_charged_creeper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1500, speed:0.13, money:1500,id:charged-creeper,name:{color:white,"text":"충전된 크리퍼\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1500, speed:0.13, money:1500,id:charged-creeper,name:{color:white,\"text\":\"충전된 크리퍼\\n\",\"bold\":true}}", "set");

        // summon creeper ~ ~ ~ {powered:1b, Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{"color":"dark_green","text":"크리퍼\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "creeper", _sp.x, _sp.y, _sp.z, "{powered:1b, Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"크리퍼\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_dbe2de6f_enemy_spawn_model_corrupted_drowned_execute(ServerCommandSource source) {
        _m_dbe2de6f_enemy_spawn_model_corrupted_drowned_executeReturn(source);
    }

    public static int _m_dbe2de6f_enemy_spawn_model_corrupted_drowned_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:50, speed:0.11, money:40,id:corrupted-drowned,name:{color:white,"text":"잠식된 드라운드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:50, speed:0.11, money:40,id:corrupted-drowned,name:{color:white,\"text\":\"잠식된 드라운드\\n\",\"bold\":true}}", "set");

        // summon drowned ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "drowned", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_c94e1d4a_enemy_spawn_model_corrupted_husk_execute(ServerCommandSource source) {
        _m_c94e1d4a_enemy_spawn_model_corrupted_husk_executeReturn(source);
    }

    public static int _m_c94e1d4a_enemy_spawn_model_corrupted_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15, speed:0.12, money:15,id:corrupted-husk,name:{color:white,"text":"잠식된 허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15, speed:0.12, money:15,id:corrupted-husk,name:{color:white,\"text\":\"잠식된 허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_37e87b30_enemy_spawn_model_corrupted_zombie_execute(ServerCommandSource source) {
        _m_37e87b30_enemy_spawn_model_corrupted_zombie_executeReturn(source);
    }

    public static int _m_37e87b30_enemy_spawn_model_corrupted_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15, speed:0.1, money:15,id:corrupted-zombie,name:{color:white,"text":"잠식된 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15, speed:0.1, money:15,id:corrupted-zombie,name:{color:white,\"text\":\"잠식된 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_ca771912_enemy_spawn_model_crawler_execute(ServerCommandSource source) {
        _m_ca771912_enemy_spawn_model_crawler_executeReturn(source);
    }

    public static int _m_ca771912_enemy_spawn_model_crawler_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15000, speed:0.8, money:10000,id:crawler,name:{color:white,"text":"크롤러\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15000, speed:0.8, money:10000,id:crawler,name:{color:white,\"text\":\"크롤러\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_cec2ac3c_enemy_spawn_model_creeper_execute(ServerCommandSource source) {
        _m_cec2ac3c_enemy_spawn_model_creeper_executeReturn(source);
    }

    public static int _m_cec2ac3c_enemy_spawn_model_creeper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:800, speed:0.13, money:800,id:creeper,name:{color:white,"text":"크리퍼\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:800, speed:0.13, money:800,id:creeper,name:{color:white,\"text\":\"크리퍼\\n\",\"bold\":true}}", "set");

        // summon creeper ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{"color":"dark_green","text":"크리퍼\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "creeper", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"크리퍼\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_239b2b65_enemy_spawn_model_crimson_zombie_execute(ServerCommandSource source) {
        _m_239b2b65_enemy_spawn_model_crimson_zombie_executeReturn(source);
    }

    public static int _m_239b2b65_enemy_spawn_model_crimson_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:4000, speed:0.17, defence:75, money:4000,id:crimson-zombie,name:{color:dark_purple,"text":"진홍빛 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:4000, speed:0.17, defence:75, money:4000,id:crimson-zombie,name:{color:dark_purple,\"text\":\"진홍빛 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.text],text:[{"color":"dark_green","text":"진홍빛 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:2}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"진홍빛 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:2}]}"); }
        return 0;
    }

    public static void _m_ccd0d764_enemy_spawn_model_dark_execute(ServerCommandSource source) {
        _m_ccd0d764_enemy_spawn_model_dark_executeReturn(source);
    }

    public static int _m_ccd0d764_enemy_spawn_model_dark_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:600, speed:0.24, money:500,id:dark,name:{color:white,"text":"다크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:600, speed:0.24, money:500,id:dark,name:{color:white,\"text\":\"다크\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_2488a1de_enemy_spawn_model_dark_dash_zombie_execute(ServerCommandSource source) {
        _m_2488a1de_enemy_spawn_model_dark_dash_zombie_executeReturn(source);
    }

    public static int _m_2488a1de_enemy_spawn_model_dark_dash_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:250, speed:0, money:300,id:dark-dash-zombie,name:{color:white,"text":"다크 돌진 좀비\n","bold":true},skills:{timer:20}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:250, speed:0, money:300,id:dark-dash-zombie,name:{color:white,\"text\":\"다크 돌진 좀비\\n\",\"bold\":true},skills:{timer:20}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_3cfe6080_enemy_spawn_model_dark_guardian_execute(ServerCommandSource source) {
        _m_3cfe6080_enemy_spawn_model_dark_guardian_executeReturn(source);
    }

    public static int _m_3cfe6080_enemy_spawn_model_dark_guardian_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:50000, speed:0.15, money:25000,id:dark-guardian,name:{color:white,"text":"다크 가디언\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:50000, speed:0.15, money:25000,id:dark-guardian,name:{color:white,\"text\":\"다크 가디언\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_f3c7ab23_enemy_spawn_model_dark_headbomb_execute(ServerCommandSource source) {
        _m_f3c7ab23_enemy_spawn_model_dark_headbomb_executeReturn(source);
    }

    public static int _m_f3c7ab23_enemy_spawn_model_dark_headbomb_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:2500, speed:0.12, money:2500,id:dark-headbomb,name:{color:white,"text":"다크 헤드밤\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:2500, speed:0.12, money:2500,id:dark-headbomb,name:{color:white,\"text\":\"다크 헤드밤\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_2e483042_enemy_spawn_model_dark_knight_execute(ServerCommandSource source) {
        _m_2e483042_enemy_spawn_model_dark_knight_executeReturn(source);
    }

    public static int _m_2e483042_enemy_spawn_model_dark_knight_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15000, speed:0.1, money:12500,id:dark-knight,name:{color:white,"text":"다크 나이트\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15000, speed:0.1, money:12500,id:dark-knight,name:{color:white,\"text\":\"다크 나이트\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_d1c0ab69_enemy_spawn_model_dark_mist_execute(ServerCommandSource source) {
        _m_d1c0ab69_enemy_spawn_model_dark_mist_executeReturn(source);
    }

    public static int _m_d1c0ab69_enemy_spawn_model_dark_mist_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1000, speed:0.20, money:1000,id:dark-mist,name:{color:white,"text":"다크 미스트\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1000, speed:0.20, money:1000,id:dark-mist,name:{color:white,\"text\":\"다크 미스트\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_b88c18e8_enemy_spawn_model_dark_walker_execute(ServerCommandSource source) {
        _m_b88c18e8_enemy_spawn_model_dark_walker_executeReturn(source);
    }

    public static int _m_b88c18e8_enemy_spawn_model_dark_walker_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:300, speed:0.15, money:250,id:dark-walker,name:{color:white,"text":"다크 워커\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:300, speed:0.15, money:250,id:dark-walker,name:{color:white,\"text\":\"다크 워커\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_98bce5ba_enemy_spawn_model_darkness_soron_execute(ServerCommandSource source) {
        _m_98bce5ba_enemy_spawn_model_darkness_soron_executeReturn(source);
    }

    public static int _m_98bce5ba_enemy_spawn_model_darkness_soron_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:250000, speed:0.08, money:125000,id:darkness-soron,name:{color:white,"text":"다크니스 소론\n","bold":true},skills:{timer:150,low-hp:100000}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:250000, speed:0.08, money:125000,id:darkness-soron,name:{color:white,\"text\":\"다크니스 소론\\n\",\"bold\":true},skills:{timer:150,low-hp:100000}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-loop.1,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-loop.1,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_b1e0c83a_enemy_spawn_model_dash_zombie_execute(ServerCommandSource source) {
        _m_b1e0c83a_enemy_spawn_model_dash_zombie_executeReturn(source);
    }

    public static int _m_b1e0c83a_enemy_spawn_model_dash_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:125, speed:0, money:125,id:dash-zombie,name:{color:white,"text":"돌진 좀비\n","bold":true},skills:{timer:20}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:125, speed:0, money:125,id:dash-zombie,name:{color:white,\"text\":\"돌진 좀비\\n\",\"bold\":true},skills:{timer:20}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_af1616e9_enemy_spawn_model_drowned_execute(ServerCommandSource source) {
        _m_af1616e9_enemy_spawn_model_drowned_executeReturn(source);
    }

    public static int _m_af1616e9_enemy_spawn_model_drowned_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:40, speed:0.11, defence:25, money:30,id:drowned,name:{color:white,"text":"드라운드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:40, speed:0.11, defence:25, money:30,id:drowned,name:{color:white,\"text\":\"드라운드\\n\",\"bold\":true}}", "set");

        // summon drowned ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"드라운드\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "drowned", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"드라운드\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_f2b42990_enemy_spawn_model_elite_drowned_execute(ServerCommandSource source) {
        _m_f2b42990_enemy_spawn_model_elite_drowned_executeReturn(source);
    }

    public static int _m_f2b42990_enemy_spawn_model_elite_drowned_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1500, defence:100, speed:0.13, money:2000,id:elite-drowned,name:{color:white,"text":"엘리트 드라운드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1500, defence:100, speed:0.13, money:2000,id:elite-drowned,name:{color:white,\"text\":\"엘리트 드라운드\\n\",\"bold\":true}}", "set");

        // summon drowned ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"드라운드\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "drowned", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"드라운드\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_be3d2943_enemy_spawn_model_enderman_execute(ServerCommandSource source) {
        _m_be3d2943_enemy_spawn_model_enderman_executeReturn(source);
    }

    public static int _m_be3d2943_enemy_spawn_model_enderman_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:5000, speed:0.17, money:3500,id:enderman,name:{color:white,"text":"엔더맨\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:5000, speed:0.17, money:3500,id:enderman,name:{color:white,\"text\":\"엔더맨\\n\",\"bold\":true}}", "set");

        // summon enderman ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "enderman", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_06732c5b_enemy_spawn_model_endermite_execute(ServerCommandSource source) {
        _m_06732c5b_enemy_spawn_model_endermite_executeReturn(source);
    }

    public static int _m_06732c5b_enemy_spawn_model_endermite_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:200, speed:0.15, money:400,id:endermite,name:{color:white,"text":"엔더마이트\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:200, speed:0.15, money:400,id:endermite,name:{color:white,\"text\":\"엔더마이트\\n\",\"bold\":true}}", "set");

        // summon endermite ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"엔더마이트\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "endermite", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"엔더마이트\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_8e1b2d5c_enemy_spawn_model_endermite_baby_execute(ServerCommandSource source) {
        _m_8e1b2d5c_enemy_spawn_model_endermite_baby_executeReturn(source);
    }

    public static int _m_8e1b2d5c_enemy_spawn_model_endermite_baby_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:250, speed:0.02, money:0,id:endermite-baby,name:{color:white,"text":"새끼 엔더마이트\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:250, speed:0.02, money:0,id:endermite-baby,name:{color:white,\"text\":\"새끼 엔더마이트\\n\",\"bold\":true}}", "set");

        // summon endermite ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"엔더마이트\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "endermite", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"엔더마이트\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_f013c4fd_enemy_spawn_model_endermite_egg_execute(ServerCommandSource source) {
        _m_f013c4fd_enemy_spawn_model_endermite_egg_executeReturn(source);
    }

    public static int _m_f013c4fd_enemy_spawn_model_endermite_egg_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:100, speed:0, money:0,id:endermite-egg,name:{color:white,"text":"단단한 엔더마이트 알집\n","bold":true},attribute:{dealing:1}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:100, speed:0, money:0,id:endermite-egg,name:{color:white,\"text\":\"단단한 엔더마이트 알집\\n\",\"bold\":true},attribute:{dealing:1}}", "set");

        // summon block_display ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.flame,enemy.immune.bleed],Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"엔더마이트\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.5f,0f],scale:[1f,1f,1f]}},{Tags:[enemy,enemy.hitbox_type_endermite],id:"minecraft:item_display",item:{id:"minecraft:player_head",count:1,components:{"minecraft:profile":{id:[I;1976325190,919718279,780001242,1210155425],properties:[{name:"textures",value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBmNDIxYWMwYzUwODYxNzAwYzc0MDIxMjg4NTI3ZWMwNjA3YzM0NjE3M2M2M2M5NTkxOGQ2ZDk3NTc2YTI4NSJ9fX0="}]}}},item_display:"none",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.5f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.flame,enemy.immune.bleed],Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"엔더마이트\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.5f,0f],scale:[1f,1f,1f]}},{Tags:[enemy,enemy.hitbox_type_endermite],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",count:1,components:{\"minecraft:profile\":{id:[I;1976325190,919718279,780001242,1210155425],properties:[{name:\"textures\",value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBmNDIxYWMwYzUwODYxNzAwYzc0MDIxMjg4NTI3ZWMwNjA3YzM0NjE3M2M2M2M5NTkxOGQ2ZDk3NTc2YTI4NSJ9fX0=\"}]}}},item_display:\"none\",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.5f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_d1307819_enemy_spawn_model_evoker_execute(ServerCommandSource source) {
        _m_d1307819_enemy_spawn_model_evoker_executeReturn(source);
    }

    public static int _m_d1307819_enemy_spawn_model_evoker_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1750, defence:75, speed:0.13, money:3000,id:evoker,name:{color:white,"text":"소환사\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1750, defence:75, speed:0.13, money:3000,id:evoker,name:{color:white,\"text\":\"소환사\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon evoker ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-loop.1],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"소환사\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "evoker", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-loop.1],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"소환사\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_35a1d023_enemy_spawn_model_giant_execute(ServerCommandSource source) {
        _m_35a1d023_enemy_spawn_model_giant_executeReturn(source);
    }

    public static int _m_35a1d023_enemy_spawn_model_giant_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:2000, speed:0.06, money:1750,id:giant,name:{color:dark_purple,"text":"자이언트 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:2000, speed:0.06, money:1750,id:giant,name:{color:dark_purple,\"text\":\"자이언트 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.text],text:[{"color":"dark_green","text":"자이언트 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:1.5}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"자이언트 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:1.5}]}"); }
        return 0;
    }

    public static void _m_b5b4cb42_enemy_spawn_model_healing_crystal_execute(ServerCommandSource source) {
        _m_b5b4cb42_enemy_spawn_model_healing_crystal_executeReturn(source);
    }

    public static int _m_b5b4cb42_enemy_spawn_model_healing_crystal_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:5000, speed:0, money:0,id:healing-crystal,name:{color:yellow,"text":"힐링 크리스탈\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:5000, speed:0, money:0,id:healing-crystal,name:{color:yellow,\"text\":\"힐링 크리스탈\\n\",\"bold\":true}}", "set");

        // summon end_crystal ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_breeze,enemy.target,enemy.core,enemy.data,enemy.attribute.taunt],Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_breeze,enemy.text],text:[{"color":"dark_green","text":"힐링 크리스탈\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "end_crystal", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_breeze,enemy.target,enemy.core,enemy.data,enemy.attribute.taunt],Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_breeze,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"힐링 크리스탈\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_6f58d4bb_enemy_spawn_model_healing_zombie_execute(ServerCommandSource source) {
        _m_6f58d4bb_enemy_spawn_model_healing_zombie_executeReturn(source);
    }

    public static int _m_6f58d4bb_enemy_spawn_model_healing_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:200, speed:0.07, defence:15, money:300,id:healing-zombie,name:{color:white,"text":"힐링 좀비\n","bold":true},attribute:{healing:1}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:200, speed:0.07, defence:15, money:300,id:healing-zombie,name:{color:white,\"text\":\"힐링 좀비\\n\",\"bold\":true},attribute:{healing:1}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:leather_helmet,components:{dyed_color:655104}},chest:{id:leather_chestplate,components:{dyed_color:655104}}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:leather_helmet,components:{dyed_color:655104}},chest:{id:leather_chestplate,components:{dyed_color:655104}}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7de1b50a_enemy_spawn_model_heavy_dark_execute(ServerCommandSource source) {
        _m_7de1b50a_enemy_spawn_model_heavy_dark_executeReturn(source);
    }

    public static int _m_7de1b50a_enemy_spawn_model_heavy_dark_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1250, speed:0.12, money:1000,id:heavy-dark,name:{color:white,"text":"헤비 다크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1250, speed:0.12, money:1000,id:heavy-dark,name:{color:white,\"text\":\"헤비 다크\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1.1f,1.1f,1.1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1.1f,1.1f,1.1f]}}]}"); }
        return 0;
    }

    public static void _m_ff6a6601_enemy_spawn_model_heavy_husk_execute(ServerCommandSource source) {
        _m_ff6a6601_enemy_spawn_model_heavy_husk_executeReturn(source);
    }

    public static int _m_ff6a6601_enemy_spawn_model_heavy_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:80, speed:0.08, money:75, defence:25,id:heavy-husk,name:{color:white,"text":"헤비 허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:80, speed:0.08, money:75, defence:25,id:heavy-husk,name:{color:white,\"text\":\"헤비 허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_515f242c_enemy_spawn_model_heavy_zombie_execute(ServerCommandSource source) {
        _m_515f242c_enemy_spawn_model_heavy_zombie_executeReturn(source);
    }

    public static int _m_515f242c_enemy_spawn_model_heavy_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:60, speed:0.07, money:45,id:heavy-zombie,name:{color:white,"text":"헤비 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:60, speed:0.07, money:45,id:heavy-zombie,name:{color:white,\"text\":\"헤비 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.text],text:[{"color":"dark_green","text":"헤비 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:1.1}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"헤비 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:1.1}]}"); }
        return 0;
    }

    public static void _m_774e1877_enemy_spawn_model_hidden_zombie_execute(ServerCommandSource source) {
        _m_774e1877_enemy_spawn_model_hidden_zombie_executeReturn(source);
    }

    public static int _m_774e1877_enemy_spawn_model_hidden_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:800, speed:0.2, money:700,id:hidden-zombie,name:{color:white,"text":"은신 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:800, speed:0.2, money:700,id:hidden-zombie,name:{color:white,\"text\":\"은신 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_22dade0f_enemy_spawn_model_horse_skeleton_execute(ServerCommandSource source) {
        _m_22dade0f_enemy_spawn_model_horse_skeleton_executeReturn(source);
    }

    public static int _m_22dade0f_enemy_spawn_model_horse_skeleton_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1000, speed:0.25, money:1250,id:horse-skeleton,name:{color:white,"text":"기마 스켈레톤\n","bold":true},skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1000, speed:0.25, money:1250,id:horse-skeleton,name:{color:white,\"text\":\"기마 스켈레톤\\n\",\"bold\":true},skills:{timer:100}}", "set");

        // summon skeleton_horse ~ ~ ~ {equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{"color":"dark_green","text":"기마 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:"skeleton",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "skeleton_horse", _sp.x, _sp.y, _sp.z, "{equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"기마 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:\"skeleton\",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}"); }
        return 0;
    }

    public static void _m_e41902a5_enemy_spawn_model_horse_zombie_execute(ServerCommandSource source) {
        _m_e41902a5_enemy_spawn_model_horse_zombie_executeReturn(source);
    }

    public static int _m_e41902a5_enemy_spawn_model_horse_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1000, speed:0.25, defence:25, money:1000,id:horse-zombie,name:{color:dark_purple,"text":"기마 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1000, speed:0.25, defence:25, money:1000,id:horse-zombie,name:{color:dark_purple,\"text\":\"기마 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie_horse ~ ~ ~ {equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{"color":"dark_green","text":"기마 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:"zombie",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie_horse", _sp.x, _sp.y, _sp.z, "{equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"기마 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:\"zombie\",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}"); }
        return 0;
    }

    public static void _m_ae24a1f3_enemy_spawn_model_hp_zombie_execute(ServerCommandSource source) {
        _m_ae24a1f3_enemy_spawn_model_hp_zombie_executeReturn(source);
    }

    public static int _m_ae24a1f3_enemy_spawn_model_hp_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:999999, speed:0.06, money:8,id:zombie,name:{color:white,"text":"좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:999999, speed:0.06, money:8,id:zombie,name:{color:white,\"text\":\"좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_f99dcf5a_enemy_spawn_model_husk_execute(ServerCommandSource source) {
        _m_f99dcf5a_enemy_spawn_model_husk_executeReturn(source);
    }

    public static int _m_f99dcf5a_enemy_spawn_model_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10, speed:0.1, money:10,id:husk,name:{color:white,"text":"허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10, speed:0.1, money:10,id:husk,name:{color:white,\"text\":\"허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_fb3e3599_enemy_spawn_model_illusioner_execute(ServerCommandSource source) {
        _m_fb3e3599_enemy_spawn_model_illusioner_executeReturn(source);
    }

    public static int _m_fb3e3599_enemy_spawn_model_illusioner_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:3500, speed:0.13, defence:75, money:3000,id:illusioner,name:{color:white,"text":"환술사\n","bold":true}, skills:{low_hp:2499}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:3500, speed:0.13, defence:75, money:3000,id:illusioner,name:{color:white,\"text\":\"환술사\\n\",\"bold\":true}, skills:{low_hp:2499}}", "set");

        // summon illusioner ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "illusioner", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_33e97b94_enemy_spawn_model_illusioner_illusion_execute(ServerCommandSource source) {
        _m_33e97b94_enemy_spawn_model_illusioner_illusion_executeReturn(source);
    }

    public static int _m_33e97b94_enemy_spawn_model_illusioner_illusion_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:567, speed:0.17, money:25,id:illusioner-illusion,name:{color:white,"text":"환술사 환영\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:567, speed:0.17, money:25,id:illusioner-illusion,name:{color:white,\"text\":\"환술사 환영\\n\",\"bold\":true}}", "set");

        // summon illusioner ~ ~ ~ {active_effects:[{id:"minecraft:glowing",amplifier:0,duration:-1}],Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.taunt],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "illusioner", _sp.x, _sp.y, _sp.z, "{active_effects:[{id:\"minecraft:glowing\",amplifier:0,duration:-1}],Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.taunt],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_78606879_enemy_spawn_model_indura_stone_execute(ServerCommandSource source) {
        _m_78606879_enemy_spawn_model_indura_stone_executeReturn(source);
    }

    public static int _m_78606879_enemy_spawn_model_indura_stone_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:100000, speed:0.0, defence:10000, money:0,id:indura-stone,name:{color:white,"text":"인듀라 스톤\n","bold":true},skills:{timer:40}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:100000, speed:0.0, defence:10000, money:0,id:indura-stone,name:{color:white,\"text\":\"인듀라 스톤\\n\",\"bold\":true},skills:{timer:40}}", "set");

        // summon item_display ~ ~1 ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],item:{id:black_concrete}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.summon(ctx.world, "item_display", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],item:{id:black_concrete}}"); }
        return 0;
    }

    public static void _m_bc379fc5_enemy_spawn_model_indura_zombie_execute(ServerCommandSource source) {
        _m_bc379fc5_enemy_spawn_model_indura_zombie_executeReturn(source);
    }

    public static int _m_bc379fc5_enemy_spawn_model_indura_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:325, speed:0.2, defence:50, money:300,id:indura-zombie,name:{color:white,"text":"인듀라 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:325, speed:0.2, defence:50, money:300,id:indura-zombie,name:{color:white,\"text\":\"인듀라 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:black_shulker_box},chest:{id:netherite_chestplate,components:{trim:{material:quartz,pattern:eye}},count:1b}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:black_shulker_box},chest:{id:netherite_chestplate,components:{trim:{material:quartz,pattern:eye}},count:1b}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_b32fece6_enemy_spawn_model_necter_execute(ServerCommandSource source) {
        _m_b32fece6_enemy_spawn_model_necter_executeReturn(source);
    }

    public static int _m_b32fece6_enemy_spawn_model_necter_executeReturn(ServerCommandSource source) {
        // [용량 한계 64KB 초과] 원본 mcfunction 호출로 유지(반환값 전파)
        return KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("enemy", "spawn/model/necter"));
    }

    public static void _m_99dbe4d4_enemy_spawn_model_phantom_execute(ServerCommandSource source) {
        _m_99dbe4d4_enemy_spawn_model_phantom_executeReturn(source);
    }

    public static int _m_99dbe4d4_enemy_spawn_model_phantom_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:135, speed:0.23, money:125,id:phantom,name:{color:white,"text":"팬텀\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:135, speed:0.23, money:125,id:phantom,name:{color:white,\"text\":\"팬텀\\n\",\"bold\":true}}", "set");

        // summon phantom ~ ~1 ~ {Tags:[enemy,enemy.hitbox_type_phantom,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_phantom,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.summon(ctx.world, "phantom", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_phantom,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_phantom,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_834560f8_enemy_spawn_model_pillager_execute(ServerCommandSource source) {
        _m_834560f8_enemy_spawn_model_pillager_executeReturn(source);
    }

    public static int _m_834560f8_enemy_spawn_model_pillager_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:500, speed:0.1, money:450,id:pillager,name:{color:white,"text":"약탈자\n","bold":true},skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:500, speed:0.1, money:450,id:pillager,name:{color:white,\"text\":\"약탈자\\n\",\"bold\":true},skills:{timer:100}}", "set");

        // summon pillager ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "pillager", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_2cd47ab7_enemy_spawn_model_quick_husk_execute(ServerCommandSource source) {
        _m_2cd47ab7_enemy_spawn_model_quick_husk_executeReturn(source);
    }

    public static int _m_2cd47ab7_enemy_spawn_model_quick_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:18, speed:0.2, money:18,id:quick-husk,name:{color:white,"text":"퀵 허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:18, speed:0.2, money:18,id:quick-husk,name:{color:white,\"text\":\"퀵 허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_ed8d7ff7_enemy_spawn_model_quick_zombie_execute(ServerCommandSource source) {
        _m_ed8d7ff7_enemy_spawn_model_quick_zombie_executeReturn(source);
    }

    public static int _m_ed8d7ff7_enemy_spawn_model_quick_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15, speed:0.2, money:15,id:quick-zombie,name:{color:white,"text":"퀵 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15, speed:0.2, money:15,id:quick-zombie,name:{color:white,\"text\":\"퀵 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:diamond_helmet}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"퀵 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:diamond_helmet}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"퀵 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_93f3e7cc_enemy_spawn_model_rabbit_execute(ServerCommandSource source) {
        _m_93f3e7cc_enemy_spawn_model_rabbit_executeReturn(source);
    }

    public static int _m_93f3e7cc_enemy_spawn_model_rabbit_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10, speed:0.52, money:8,id:rabbit,name:{color:white,"text":"좀비 토끼\n","bold":true},skills:{timer:0}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10, speed:0.52, money:8,id:rabbit,name:{color:white,\"text\":\"좀비 토끼\\n\",\"bold\":true},skills:{timer:0}}", "set");

        // summon rabbit ~ ~ ~ {RabbitType:99, Tags:[enemy,enemy.hitbox_type_rabbit,enemy.target,enemy.core,enemy.data,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_rabbit,enemy.text],text:[{"color":"dark_green","text":"좀비 토끼\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "rabbit", _sp.x, _sp.y, _sp.z, "{RabbitType:99, Tags:[enemy,enemy.hitbox_type_rabbit,enemy.target,enemy.core,enemy.data,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_rabbit,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비 토끼\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_5a04187b_enemy_spawn_model_raid_guardian_execute(ServerCommandSource source) {
        _m_5a04187b_enemy_spawn_model_raid_guardian_executeReturn(source);
    }

    public static int _m_5a04187b_enemy_spawn_model_raid_guardian_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:12500, defence:175, speed:0.15, money:15000,id:raid-guardian,name:{color:white,"text":"레이드 가디언\n","bold":true}, skills:{low_hp:10000}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:12500, defence:175, speed:0.15, money:15000,id:raid-guardian,name:{color:white,\"text\":\"레이드 가디언\\n\",\"bold\":true}, skills:{low_hp:10000}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze,enemy.immune.stun,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze,enemy.immune.stun,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_9d9a76db_enemy_spawn_model_raid_lord_execute(ServerCommandSource source) {
        _m_9d9a76db_enemy_spawn_model_raid_lord_executeReturn(source);
    }

    public static int _m_9d9a76db_enemy_spawn_model_raid_lord_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:45000, defence:250, speed:0.10, money:112500,id:raid-lord,name:{color:white,"text":"레이드 로드\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:45000, defence:250, speed:0.10, money:112500,id:raid-lord,name:{color:white,\"text\":\"레이드 로드\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.flame,enemy.immune.freeze,enemy.immune.stun,enemy.immune.poison,enemy.skill-loop.1],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.flame,enemy.immune.freeze,enemy.immune.stun,enemy.immune.poison,enemy.skill-loop.1],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }
}
