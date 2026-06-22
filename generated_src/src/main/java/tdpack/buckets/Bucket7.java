package tdpack.buckets;

import tdpack.generated.KfcGen;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/** Auto-bucketed: 92 datapack functions. */
public final class Bucket7 {
    private Bucket7() { throw new UnsupportedOperationException(); }

    public static void _m_a4ac5906_game_game_round_ready_execute(ServerCommandSource source) {
        _m_a4ac5906_game_game_round_ready_executeReturn(source);
    }

    public static int _m_a4ac5906_game_game_round_ready_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score timer gameState matches 0 run title @a title {text:"5",color:yellow,bold:true}
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"5\",color:yellow,bold:true}", false);
            }
        }

        // execute if score timer gameState matches 0 run title @a subtitle {text:"",color:white}
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"\",color:white}", true);
            }
        }

        // execute if score timer gameState matches 0 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 0, 0)) {
            for (ServerPlayerEntity e : ctx.allPlayers) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc9 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "ui.button.click", "weather", new net.minecraft.util.math.Vec3d(kfcSrc9.getPosition().x, kfcSrc9.getPosition().y, kfcSrc9.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc9.getPosition().x, kfcSrc9.getPosition().y, kfcSrc9.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc9.getPosition().x, kfcSrc9.getPosition().y, kfcSrc9.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score timer gameState matches 0 as @a at @s run clear @a yellow_dye
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 0, 0)) {
            for (ServerPlayerEntity e : ctx.allPlayers) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc10 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                for (net.minecraft.entity.Entity _clE : ctx.allPlayers) {
                    KfcGen.clearItems(_clE, "yellow_dye", null, -1);
                }
            }
        }

        // execute if score timer gameState matches 20 run title @a title {text:"4",color:yellow,bold:true}
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 20, 20)) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"4\",color:yellow,bold:true}", false);
            }
        }

        // execute if score timer gameState matches 20 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 20, 20)) {
            for (ServerPlayerEntity e : ctx.allPlayers) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc11 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "ui.button.click", "weather", new net.minecraft.util.math.Vec3d(kfcSrc11.getPosition().x, kfcSrc11.getPosition().y, kfcSrc11.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc11.getPosition().x, kfcSrc11.getPosition().y, kfcSrc11.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc11.getPosition().x, kfcSrc11.getPosition().y, kfcSrc11.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score timer gameState matches 40 run title @a title {text:"3",color:yellow,bold:true}
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 40, 40)) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"3\",color:yellow,bold:true}", false);
            }
        }

        // execute if score timer gameState matches 40 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 40, 40)) {
            for (ServerPlayerEntity e : ctx.allPlayers) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc12 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "ui.button.click", "weather", new net.minecraft.util.math.Vec3d(kfcSrc12.getPosition().x, kfcSrc12.getPosition().y, kfcSrc12.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc12.getPosition().x, kfcSrc12.getPosition().y, kfcSrc12.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc12.getPosition().x, kfcSrc12.getPosition().y, kfcSrc12.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score timer gameState matches 60 run title @a title {text:"2",color:yellow,bold:true}
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 60, 60)) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"2\",color:yellow,bold:true}", false);
            }
        }

        // execute if score timer gameState matches 60 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 60, 60)) {
            for (ServerPlayerEntity e : ctx.allPlayers) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc13 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "ui.button.click", "weather", new net.minecraft.util.math.Vec3d(kfcSrc13.getPosition().x, kfcSrc13.getPosition().y, kfcSrc13.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc13.getPosition().x, kfcSrc13.getPosition().y, kfcSrc13.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc13.getPosition().x, kfcSrc13.getPosition().y, kfcSrc13.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score timer gameState matches 80 run title @a title {text:"1",color:yellow,bold:true}
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 80, 80)) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"1\",color:yellow,bold:true}", false);
            }
        }

        // execute if score timer gameState matches 80 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 80, 80)) {
            for (ServerPlayerEntity e : ctx.allPlayers) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc14 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "ui.button.click", "weather", new net.minecraft.util.math.Vec3d(kfcSrc14.getPosition().x, kfcSrc14.getPosition().y, kfcSrc14.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc14.getPosition().x, kfcSrc14.getPosition().y, kfcSrc14.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc14.getPosition().x, kfcSrc14.getPosition().y, kfcSrc14.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score timer gameState matches 100 store result score #round money run data get storage game setup.each_money
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            KfcGen.setScore(sb, "#round", "money", (int)(KfcGen.storageGetDouble(server, "game", "setup.each_money")));
        }

        // execute if score timer gameState matches 100 run scoreboard players operation #round money *= game wave
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            KfcGen.opScore(sb, "#round", "money", "*=", "game", "wave");
        }

        // execute if score timer gameState matches 100 unless score game wave matches ..1 run scoreboard players operation @a money += #round money
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            if (!(KfcGen.scoreMatches(sb, "game", "wave", Integer.MIN_VALUE, 1))) {
                { 
                for (net.minecraft.server.network.ServerPlayerEntity _od : ctx.allPlayers) {
                    KfcGen.opScore(sb, _od.getNameForScoreboard(), "money", "+=", "#round", "money");
                }
                }
            }
        }

        // execute if score timer gameState matches 100 run title @a title {text:"시작!",color:green,bold:true}
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"시작!\",color:green,bold:true}", false);
            }
        }

        // execute if score timer gameState matches 100 run title @a subtitle [{text:"라운드 보너스: ",color:white,bold:true},{score:{name:"#round",objective:"money"},color:yellow,bold:false}]
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "[{text:\"라운드 보너스: \",color:white,bold:true},{score:{name:\"#round\",objective:\"money\"},color:yellow,bold:false}]", true);
            }
        }

        // execute if score timer gameState matches 100 as @a at @s run playsound minecraft:item.goat_horn.sound.0 weather @s ~ ~ ~
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            for (ServerPlayerEntity e : ctx.allPlayers) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc15 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:item.goat_horn.sound.0", "weather", new net.minecraft.util.math.Vec3d(kfcSrc15.getPosition().x, kfcSrc15.getPosition().y, kfcSrc15.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc15.getPosition().x, kfcSrc15.getPosition().y, kfcSrc15.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc15.getPosition().x, kfcSrc15.getPosition().y, kfcSrc15.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score timer gameState matches 100 as @e[tag=mine,tag=tower.data,scores={tower.level=0..}] run function game:game/mine_income
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = e; if (!(en.getCommandTags().contains("mine") && en.getCommandTags().contains("tower.data") && KfcGen.scoreMatches(sb, en.getNameForScoreboard(), "tower.level", 0, Integer.MAX_VALUE))) continue;
                ServerCommandSource es = source.withEntity(e);
                // -> game:game/mine_income
                tdpack.buckets.Bucket6._m_ec7d49b3_game_game_mine_income_execute(es);
            }
        }

        // execute if score timer gameState matches 100 run scoreboard players set stage gameState 2
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            KfcGen.setScore(sb, "stage", "gameState", 2);
        }

        // execute if score timer gameState matches 100 run scoreboard players set timer gameState 0
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 100, 100)) {
            KfcGen.setScore(sb, "timer", "gameState", 0);
        }
        return 0;
    }

    public static void _m_cc493260_game_game_round_wait_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_cc493260_game_game_round_wait_executeReturn(source, macroArgs);
    }

    public static int _m_cc493260_game_game_round_wait_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score game game.difficulty matches 5 run function game:infinity/round_wait with storage game setup
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 5, 5)) {
            // -> game:infinity/round_wait
            tdpack.buckets.Bucket7._m_f3b8da53_game_infinity_round_wait_execute(source);
        }

        // execute if score game game.difficulty matches 5 run return 0
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 5, 5)) {
            return 0;
        }

        // $execute store result score #total_rounds temp run data get storage map $(map).$(difficulty).round
        KfcGen.setScore(sb, "#total_rounds", "temp", (int)(KfcGen.storageGetDouble(server, "map", macroArgs.get("map") + "." + macroArgs.get("difficulty") + ".round")));

        // execute if score timer gameState >= #temp time if score game wave >= #total_rounds temp store result score #enemy_count temp if entity @e[tag=enemy]
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
        if (KfcGen.scoreCmp(sb, "game", "wave", ">=", "#total_rounds", "temp")) {
            int scnt_16 = 0;
            for (net.minecraft.entity.Entity _ce : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1)) {
                scnt_16++;
            }
            KfcGen.setScore(sb, "#enemy_count", "temp", scnt_16);
        }
        }

        // execute if score timer gameState >= #temp time if score game wave >= #total_rounds temp if score #enemy_count temp matches 0 run function game:game/victory
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            if (KfcGen.scoreCmp(sb, "game", "wave", ">=", "#total_rounds", "temp")) {
                if (KfcGen.scoreMatches(sb, "#enemy_count", "temp", 0, 0)) {
                    // -> game:game/victory
                    tdpack.buckets.Bucket7._m_f82e58df_game_game_victory_execute(source);
                }
            }
        }

        // execute if score game wave >= #total_rounds temp run return 0
        if (KfcGen.scoreCmp(sb, "game", "wave", ">=", "#total_rounds", "temp")) {
            return 0;
        }

        // execute if items entity @a weapon.offhand yellow_dye run scoreboard players set stage gameState 1
        if (KfcGen.anyPlayerItemsMatch(ctx, new String[0], new String[0], "weapon.offhand", "yellow_dye", null)) {
            KfcGen.setScore(sb, "stage", "gameState", 1);
        }

        // execute if items entity @a weapon.offhand yellow_dye run scoreboard players add game wave 1
        if (KfcGen.anyPlayerItemsMatch(ctx, new String[0], new String[0], "weapon.offhand", "yellow_dye", null)) {
            KfcGen.addScore(sb, "game", "wave", 1);
        }

        // execute if items entity @a weapon.offhand yellow_dye run scoreboard players set timer gameState -1
        if (KfcGen.anyPlayerItemsMatch(ctx, new String[0], new String[0], "weapon.offhand", "yellow_dye", null)) {
            KfcGen.setScore(sb, "timer", "gameState", -1);
        }

        // execute if score timer gameState >= #temp time run scoreboard players set stage gameState 1
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            KfcGen.setScore(sb, "stage", "gameState", 1);
        }

        // execute if score timer gameState >= #temp time run scoreboard players add game wave 1
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            KfcGen.addScore(sb, "game", "wave", 1);
        }

        // execute if score timer gameState >= #temp time run scoreboard players set timer gameState -1
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            KfcGen.setScore(sb, "timer", "gameState", -1);
        }

        // execute unless entity @n[tag=enemy] run scoreboard players set stage gameState 1
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1))) {
            KfcGen.setScore(sb, "stage", "gameState", 1);
        }

        // execute unless entity @n[tag=enemy] run scoreboard players add game wave 1
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1))) {
            KfcGen.addScore(sb, "game", "wave", 1);
        }

        // execute unless entity @n[tag=enemy] run scoreboard players set timer gameState -1
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1))) {
            KfcGen.setScore(sb, "timer", "gameState", -1);
        }
        return 0;
    }

    public static void _m_04d71bda_game_game_show_stats_execute(ServerCommandSource source) {
        _m_04d71bda_game_game_show_stats_executeReturn(source);
    }

    public static int _m_04d71bda_game_game_show_stats_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score game game.difficulty matches 1 run scoreboard players display name difficulty_value sidebar-text {text:"쉬움",color:green,bold:true}
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 1, 1)) {
            KfcGen.displayScoreName(source, sb, "difficulty_value", "sidebar-text", "{text:\"쉬움\",color:green,bold:true}");
        }

        // execute if score game game.difficulty matches 2 run scoreboard players display name difficulty_value sidebar-text {text:"보통",color:yellow,bold:true}
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 2, 2)) {
            KfcGen.displayScoreName(source, sb, "difficulty_value", "sidebar-text", "{text:\"보통\",color:yellow,bold:true}");
        }

        // execute if score game game.difficulty matches 3 run scoreboard players display name difficulty_value sidebar-text {text:"어려움",color:red,bold:true}
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 3, 3)) {
            KfcGen.displayScoreName(source, sb, "difficulty_value", "sidebar-text", "{text:\"어려움\",color:red,bold:true}");
        }

        // execute if score game game.difficulty matches 4 run scoreboard players display name difficulty_value sidebar-text {text:"다크",color:dark_purple,bold:true}
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 4, 4)) {
            KfcGen.displayScoreName(source, sb, "difficulty_value", "sidebar-text", "{text:\"다크\",color:dark_purple,bold:true}");
        }

        // execute if score game game.difficulty matches 5 run scoreboard players display name difficulty_value sidebar-text {text:"무한",color:aqua,bold:true}
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 5, 5)) {
            KfcGen.displayScoreName(source, sb, "difficulty_value", "sidebar-text", "{text:\"무한\",color:aqua,bold:true}");
        }

        // scoreboard players set enemy_count statistics 0
        KfcGen.setScore(sb, "enemy_count", "statistics", 0);

        // execute as @e[tag=enemy.core] run scoreboard players add enemy_count statistics 1
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("enemy.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            KfcGen.addScore(sb, "enemy_count", "statistics", 1);
        }

        // scoreboard players display name enemy_value sidebar-text [{text:"남은 몹 수: ",color:white},{score:{name:"enemy_count",objective:"statistics"},color:red}]
        KfcGen.displayScoreName(source, sb, "enemy_value", "sidebar-text", "[{text:\"남은 몹 수: \",color:white},{score:{name:\"enemy_count\",objective:\"statistics\"},color:red}]");

        // scoreboard players set #remain_ticks time 0
        KfcGen.setScore(sb, "#remain_ticks", "time", 0);

        // execute if score game game.difficulty matches 5 run function game:game/show_stats_infinity
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 5, 5)) {
            // -> game:game/show_stats_infinity
            tdpack.buckets.Bucket7._m_4adcac84_game_game_show_stats_infinity_execute(source);
        }

        // execute unless score game game.difficulty matches 5 run function game:game/show_stats_normal with storage game setup
        if (!(KfcGen.scoreMatches(sb, "game", "game.difficulty", 5, 5))) {
            // -> game:game/show_stats_normal
            tdpack.buckets.Bucket7._m_2a9b4bc3_game_game_show_stats_normal_execute(source, KfcGen.storageMacroArgs(server, "game", "setup"));
        }

        // scoreboard players operation #remain_seconds time = #remain_ticks time
        KfcGen.opScore(sb, "#remain_seconds", "time", "=", "#remain_ticks", "time");

        // scoreboard players set #div time 20
        KfcGen.setScore(sb, "#div", "time", 20);

        // scoreboard players operation #remain_seconds time /= #div time
        KfcGen.opScore(sb, "#remain_seconds", "time", "/=", "#div", "time");

        // scoreboard players operation #minutes time = #remain_seconds time
        KfcGen.opScore(sb, "#minutes", "time", "=", "#remain_seconds", "time");

        // scoreboard players set #div time 60
        KfcGen.setScore(sb, "#div", "time", 60);

        // scoreboard players operation #minutes time /= #div time
        KfcGen.opScore(sb, "#minutes", "time", "/=", "#div", "time");

        // scoreboard players operation #seconds time = #remain_seconds time
        KfcGen.opScore(sb, "#seconds", "time", "=", "#remain_seconds", "time");

        // scoreboard players set #div time 60
        KfcGen.setScore(sb, "#div", "time", 60);

        // scoreboard players operation #seconds time %= #div time
        KfcGen.opScore(sb, "#seconds", "time", "%=", "#div", "time");

        // scoreboard players operation #sec_tens time = #seconds time
        KfcGen.opScore(sb, "#sec_tens", "time", "=", "#seconds", "time");

        // scoreboard players set #div time 10
        KfcGen.setScore(sb, "#div", "time", 10);

        // scoreboard players operation #sec_tens time /= #div time
        KfcGen.opScore(sb, "#sec_tens", "time", "/=", "#div", "time");

        // scoreboard players operation #sec_ones time = #seconds time
        KfcGen.opScore(sb, "#sec_ones", "time", "=", "#seconds", "time");

        // scoreboard players set #div time 10
        KfcGen.setScore(sb, "#div", "time", 10);

        // scoreboard players operation #sec_ones time %= #div time
        KfcGen.opScore(sb, "#sec_ones", "time", "%=", "#div", "time");

        // scoreboard players display name time_value sidebar-text [{score:{name:"#minutes",objective:"time"},color:aqua,bold:true},{text:":",color:aqua,bold:true},{score:{name:"#sec_tens",objective:"time"},color:aqua,bold:true},{score:{name:"#sec_ones",objective:"time"},color:aqua,bold:true}]
        KfcGen.displayScoreName(source, sb, "time_value", "sidebar-text", "[{score:{name:\"#minutes\",objective:\"time\"},color:aqua,bold:true},{text:\":\",color:aqua,bold:true},{score:{name:\"#sec_tens\",objective:\"time\"},color:aqua,bold:true},{score:{name:\"#sec_ones\",objective:\"time\"},color:aqua,bold:true}]");
        return 0;
    }

    public static void _m_4adcac84_game_game_show_stats_infinity_execute(ServerCommandSource source) {
        _m_4adcac84_game_game_show_stats_infinity_executeReturn(source);
    }

    public static int _m_4adcac84_game_game_show_stats_infinity_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players display name round_value sidebar-text [{text:"라운드 ",color:white},{score:{name:"game",objective:"wave"},color:yellow,bold:true}]
        KfcGen.displayScoreName(source, sb, "round_value", "sidebar-text", "[{text:\"라운드 \",color:white},{score:{name:\"game\",objective:\"wave\"},color:yellow,bold:true}]");

        // execute if score stage gameState matches 2 run scoreboard players operation #remain_ticks time = #infinity_end time
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 2, 2)) {
            KfcGen.opScore(sb, "#remain_ticks", "time", "=", "#infinity_end", "time");
        }

        // execute if score stage gameState matches 2 run scoreboard players add #remain_ticks time 300
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 2, 2)) {
            KfcGen.addScore(sb, "#remain_ticks", "time", 300);
        }

        // execute if score stage gameState matches 2 run scoreboard players operation #remain_ticks time -= timer gameState
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 2, 2)) {
            KfcGen.opScore(sb, "#remain_ticks", "time", "-=", "timer", "gameState");
        }

        // execute if score stage gameState matches 3 run scoreboard players operation #remain_ticks time = #temp time
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 3, 3)) {
            KfcGen.opScore(sb, "#remain_ticks", "time", "=", "#temp", "time");
        }

        // execute if score stage gameState matches 3 run scoreboard players operation #remain_ticks time -= timer gameState
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 3, 3)) {
            KfcGen.opScore(sb, "#remain_ticks", "time", "-=", "timer", "gameState");
        }

        // execute if score #remain_ticks time matches ..-1 run scoreboard players set #remain_ticks time 0
        if (KfcGen.scoreMatches(sb, "#remain_ticks", "time", Integer.MIN_VALUE, -1)) {
            KfcGen.setScore(sb, "#remain_ticks", "time", 0);
        }
        return 0;
    }

    public static void _m_2a9b4bc3_game_game_show_stats_normal_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_2a9b4bc3_game_game_show_stats_normal_executeReturn(source, macroArgs);
    }

    public static int _m_2a9b4bc3_game_game_show_stats_normal_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // $execute store result score #total_rounds temp run data get storage map $(map).$(difficulty).round
        KfcGen.setScore(sb, "#total_rounds", "temp", (int)(KfcGen.storageGetDouble(server, "map", macroArgs.get("map") + "." + macroArgs.get("difficulty") + ".round")));

        // scoreboard players display name round_value sidebar-text [{text:"라운드 ",color:white},{score:{name:"game",objective:"wave"},color:yellow,bold:true},{text:"/",color:gray},{score:{name:"#total_rounds",objective:"temp"},color:yellow,bold:true}]
        KfcGen.displayScoreName(source, sb, "round_value", "sidebar-text", "[{text:\"라운드 \",color:white},{score:{name:\"game\",objective:\"wave\"},color:yellow,bold:true},{text:\"/\",color:gray},{score:{name:\"#total_rounds\",objective:\"temp\"},color:yellow,bold:true}]");

        // $execute if score stage gameState matches 2 store result score #round_end time run data get storage map $(map).$(difficulty).round[$(round)].end
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 2, 2)) {
            KfcGen.setScore(sb, "#round_end", "time", (int)(KfcGen.storageGetDouble(server, "map", macroArgs.get("map") + "." + macroArgs.get("difficulty") + ".round[" + macroArgs.get("round") + "].end")));
        }

        // execute if score stage gameState matches 2 run scoreboard players operation #remain_ticks time = #round_end time
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 2, 2)) {
            KfcGen.opScore(sb, "#remain_ticks", "time", "=", "#round_end", "time");
        }

        // execute if score stage gameState matches 2 run scoreboard players add #remain_ticks time 300
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 2, 2)) {
            KfcGen.addScore(sb, "#remain_ticks", "time", 300);
        }

        // execute if score stage gameState matches 2 run scoreboard players operation #remain_ticks time -= timer gameState
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 2, 2)) {
            KfcGen.opScore(sb, "#remain_ticks", "time", "-=", "timer", "gameState");
        }

        // execute if score stage gameState matches 3 run scoreboard players operation #remain_ticks time = #temp time
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 3, 3)) {
            KfcGen.opScore(sb, "#remain_ticks", "time", "=", "#temp", "time");
        }

        // execute if score stage gameState matches 3 run scoreboard players operation #remain_ticks time -= timer gameState
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 3, 3)) {
            KfcGen.opScore(sb, "#remain_ticks", "time", "-=", "timer", "gameState");
        }

        // execute if score #remain_ticks time matches ..-1 run scoreboard players set #remain_ticks time 0
        if (KfcGen.scoreMatches(sb, "#remain_ticks", "time", Integer.MIN_VALUE, -1)) {
            KfcGen.setScore(sb, "#remain_ticks", "time", 0);
        }
        return 0;
    }

    public static void _m_f82e58df_game_game_victory_execute(ServerCommandSource source) {
        _m_f82e58df_game_game_victory_executeReturn(source);
    }

    public static int _m_f82e58df_game_game_victory_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set timer gameState 0
        KfcGen.setScore(sb, "timer", "gameState", 0);

        // scoreboard players set stage gameState 0
        KfcGen.setScore(sb, "stage", "gameState", 0);

        // title @a title {"text":"Victory","color":"gold","bold":true}
        for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
            KfcGen.titleText(_tp, source, "{\"text\":\"Victory\",\"color\":\"gold\",\"bold\":true}", false);
        }

        // execute store result score #temp info run data get storage game setup.each_money 0.4
        KfcGen.setScore(sb, "#temp", "info", (int)((KfcGen.storageGetDouble(server, "game", "setup.each_money")) * 0.4));

        // scoreboard players operation #temp info *= game wave
        KfcGen.opScore(sb, "#temp", "info", "*=", "game", "wave");

        // scoreboard players operation coin info += #temp info
        KfcGen.opScore(sb, "coin", "info", "+=", "#temp", "info");

        // title @a subtitle [{text:"승리 보상: ",color:green},{score:{name:"#temp",objective:info},color:yellow}]
        for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
            KfcGen.titleText(_tp, source, "[{text:\"승리 보상: \",color:green},{score:{name:\"#temp\",objective:info},color:yellow}]", true);
        }

        // playsound minecraft:item.goat_horn.sound.1 master @a ~ ~ ~ 1 1 1
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "minecraft:item.goat_horn.sound.1", "master", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
        }

        // scoreboard players set game gameState 0
        KfcGen.setScore(sb, "game", "gameState", 0);

        // scoreboard objectives setdisplay sidebar info
        KfcGen.setObjectiveDisplaySlot(sb, "sidebar", "info");
        return 0;
    }

    public static void _m_bcb76b95_game_infinity_apply_enemy_bonus_execute(ServerCommandSource source) {
        _m_bcb76b95_game_infinity_apply_enemy_bonus_executeReturn(source);
    }

    public static int _m_bcb76b95_game_infinity_apply_enemy_bonus_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players operation #bonus_round wave = game wave
        KfcGen.opScore(sb, "#bonus_round", "wave", "=", "game", "wave");

        // scoreboard players set #hp_mul wave 1000
        KfcGen.setScore(sb, "#hp_mul", "wave", 1000);

        // scoreboard players set #speed_mul wave 1000
        KfcGen.setScore(sb, "#speed_mul", "wave", 1000);

        // function game:infinity/apply_enemy_bonus_loop
        // -> game:infinity/apply_enemy_bonus_loop
        tdpack.buckets.Bucket7._m_f299e125_game_infinity_apply_enemy_bonus_loop_execute(source);

        // scoreboard players operation @s enemy.hp *= #hp_mul wave
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "*=", "#hp_mul", "wave");

        // scoreboard players set #base wave 1000
        KfcGen.setScore(sb, "#base", "wave", 1000);

        // scoreboard players operation @s enemy.hp /= #base wave
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "/=", "#base", "wave");

        // scoreboard players operation @s enemy.max_hp *= #hp_mul wave
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.max_hp", "*=", "#hp_mul", "wave");

        // scoreboard players operation @s enemy.max_hp /= #base wave
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.max_hp", "/=", "#base", "wave");

        // scoreboard players operation #def_add wave = game wave
        KfcGen.opScore(sb, "#def_add", "wave", "=", "game", "wave");

        // scoreboard players set #five wave 5
        KfcGen.setScore(sb, "#five", "wave", 5);

        // scoreboard players operation #def_add wave /= #five wave
        KfcGen.opScore(sb, "#def_add", "wave", "/=", "#five", "wave");

        // scoreboard players set #three wave 1
        KfcGen.setScore(sb, "#three", "wave", 1);

        // scoreboard players operation #def_add wave *= #three wave
        KfcGen.opScore(sb, "#def_add", "wave", "*=", "#three", "wave");

        // scoreboard players operation @s enemy.defence += #def_add wave
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.defence", "+=", "#def_add", "wave");

        // execute store result score #enemy_speed enemy.speed run data get entity @s data.speed 100000
        KfcGen.setScore(sb, "#enemy_speed", "enemy.speed", (int)((KfcGen.entityGetDouble(executor, "data.speed")) * 100000));

        // scoreboard players operation #enemy_speed enemy.speed *= #speed_mul wave
        KfcGen.opScore(sb, "#enemy_speed", "enemy.speed", "*=", "#speed_mul", "wave");

        // scoreboard players operation #enemy_speed enemy.speed /= #base wave
        KfcGen.opScore(sb, "#enemy_speed", "enemy.speed", "/=", "#base", "wave");

        // execute store result entity @s data.speed float 0.00001 run scoreboard players get #enemy_speed enemy.speed
        { net.minecraft.entity.Entity _se = executor; if (_se != null) KfcGen.entityPutNumberPath(_se, "data.speed", "float", (KfcGen.getScore(sb, "#enemy_speed", "enemy.speed")) * 0.00001); }
        return 0;
    }

    public static void _m_f299e125_game_infinity_apply_enemy_bonus_loop_execute(ServerCommandSource source) {
        _m_f299e125_game_infinity_apply_enemy_bonus_loop_executeReturn(source);
    }

    public static int _m_f299e125_game_infinity_apply_enemy_bonus_loop_executeReturn(ServerCommandSource source) {
        
        // 자기재귀 → 브릿지(스택오버플로 방지) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("game", "infinity/apply_enemy_bonus_loop"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // execute if score #bonus_round wave matches ..0 run return 0
                // if (KfcGen.scoreMatches(sb, "#bonus_round", "wave", Integer.MIN_VALUE, 0)) {
                //     return 0;
                // }
                // 
                // // scoreboard players set #hp_step wave 1025
                // KfcGen.setScore(sb, "#hp_step", "wave", 1025);
                // 
                // // scoreboard players operation #hp_mul wave *= #hp_step wave
                // KfcGen.opScore(sb, "#hp_mul", "wave", "*=", "#hp_step", "wave");
                // 
                // // scoreboard players set #base wave 1000
                // KfcGen.setScore(sb, "#base", "wave", 1000);
                // 
                // // scoreboard players operation #hp_mul wave /= #base wave
                // KfcGen.opScore(sb, "#hp_mul", "wave", "/=", "#base", "wave");
                // 
                // // scoreboard players set #speed_step wave 1005
                // KfcGen.setScore(sb, "#speed_step", "wave", 1005);
                // 
                // // scoreboard players operation #speed_mul wave *= #speed_step wave
                // KfcGen.opScore(sb, "#speed_mul", "wave", "*=", "#speed_step", "wave");
                // 
                // // scoreboard players operation #speed_mul wave /= #base wave
                // KfcGen.opScore(sb, "#speed_mul", "wave", "/=", "#base", "wave");
                // 
                // // scoreboard players remove #bonus_round wave 1
                // KfcGen.addScore(sb, "#bonus_round", "wave", -(1));
                // 
                // // function game:infinity/apply_enemy_bonus_loop
                // // -> game:infinity/apply_enemy_bonus_loop
                // tdpack.buckets.Bucket7._m_f299e125_game_infinity_apply_enemy_bonus_loop_execute(source);
                return kfcBridgeRet;
    }

    public static void _m_63b40ad8_game_infinity_process_boss_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_63b40ad8_game_infinity_process_boss_executeReturn(source, macroArgs);
    }

    public static int _m_63b40ad8_game_infinity_process_boss_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #count wave 1
        KfcGen.setScore(sb, "#count", "wave", 1);

        // scoreboard players operation #inc wave = #delta wave
        KfcGen.opScore(sb, "#inc", "wave", "=", "#delta", "wave");

        // execute if score #inc wave matches 26.. run scoreboard players set #inc wave 25
        if (KfcGen.scoreMatches(sb, "#inc", "wave", 26, Integer.MAX_VALUE)) {
            KfcGen.setScore(sb, "#inc", "wave", 25);
        }

        // scoreboard players set #div wave 15
        KfcGen.setScore(sb, "#div", "wave", 15);

        // scoreboard players operation #inc wave /= #div wave
        KfcGen.opScore(sb, "#inc", "wave", "/=", "#div", "wave");

        // scoreboard players operation #count wave += #inc wave
        KfcGen.opScore(sb, "#count", "wave", "+=", "#inc", "wave");

        // execute if score #count wave matches 5.. run scoreboard players set #count wave 5
        if (KfcGen.scoreMatches(sb, "#count", "wave", 5, Integer.MAX_VALUE)) {
            KfcGen.setScore(sb, "#count", "wave", 5);
        }

        // execute if score #count wave matches ..4 run scoreboard players operation #mod wave = game wave
        if (KfcGen.scoreMatches(sb, "#count", "wave", Integer.MIN_VALUE, 4)) {
            KfcGen.opScore(sb, "#mod", "wave", "=", "game", "wave");
        }

        // $execute if score #count wave matches ..4 run scoreboard players remove #mod wave $(unlock)
        if (KfcGen.scoreMatches(sb, "#count", "wave", Integer.MIN_VALUE, 4)) {
            try { KfcGen.addScore(sb, "#mod", "wave", -(Integer.parseInt(macroArgs.get("unlock")))); } catch (NumberFormatException _nfe) {}
        }

        // execute if score #count wave matches ..4 run scoreboard players set #intv wave 5
        if (KfcGen.scoreMatches(sb, "#count", "wave", Integer.MIN_VALUE, 4)) {
            KfcGen.setScore(sb, "#intv", "wave", 5);
        }

        // execute if score #count wave matches ..4 run scoreboard players operation #mod wave %= #intv wave
        if (KfcGen.scoreMatches(sb, "#count", "wave", Integer.MIN_VALUE, 4)) {
            KfcGen.opScore(sb, "#mod", "wave", "%=", "#intv", "wave");
        }

        // execute if score #count wave matches ..4 unless score #mod wave matches 0 run return 0
        if (KfcGen.scoreMatches(sb, "#count", "wave", Integer.MIN_VALUE, 4)) {
            if (!(KfcGen.scoreMatches(sb, "#mod", "wave", 0, 0))) {
                return 0;
            }
        }

        // execute unless score timer gameState matches 0 run return 0
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 0, 0))) {
            return 0;
        }

        // scoreboard players operation #loop wave = #count wave
        KfcGen.opScore(sb, "#loop", "wave", "=", "#count", "wave");

        // function game:infinity/spawn_loop
        // -> game:infinity/spawn_loop
        tdpack.buckets.Bucket7._m_17d6ec70_game_infinity_spawn_loop_execute(source);
        return 0;
    }

    public static void _m_8f22b3a8_game_infinity_process_elite_execute(ServerCommandSource source) {
        _m_8f22b3a8_game_infinity_process_elite_executeReturn(source);
    }

    public static int _m_8f22b3a8_game_infinity_process_elite_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score #delta wave matches 16.. run return 0
        if (KfcGen.scoreMatches(sb, "#delta", "wave", 16, Integer.MAX_VALUE)) {
            return 0;
        }

        // scoreboard players operation #count wave = #delta wave
        KfcGen.opScore(sb, "#count", "wave", "=", "#delta", "wave");

        // scoreboard players set #div wave 2
        KfcGen.setScore(sb, "#div", "wave", 2);

        // scoreboard players operation #count wave /= #div wave
        KfcGen.opScore(sb, "#count", "wave", "/=", "#div", "wave");

        // scoreboard players add #count wave 2
        KfcGen.addScore(sb, "#count", "wave", 2);

        // scoreboard players operation #end wave = #count wave
        KfcGen.opScore(sb, "#end", "wave", "=", "#count", "wave");

        // scoreboard players remove #end wave 1
        KfcGen.addScore(sb, "#end", "wave", -(1));

        // scoreboard players set #intv wave 50
        KfcGen.setScore(sb, "#intv", "wave", 50);

        // scoreboard players operation #end wave *= #intv wave
        KfcGen.opScore(sb, "#end", "wave", "*=", "#intv", "wave");

        // scoreboard players operation #infinity_end time > #end wave
        KfcGen.opScore(sb, "#infinity_end", "time", ">", "#end", "wave");

        // scoreboard players operation #mod wave = timer gameState
        KfcGen.opScore(sb, "#mod", "wave", "=", "timer", "gameState");

        // scoreboard players set #intv wave 50
        KfcGen.setScore(sb, "#intv", "wave", 50);

        // scoreboard players operation #mod wave %= #intv wave
        KfcGen.opScore(sb, "#mod", "wave", "%=", "#intv", "wave");

        // execute unless score #mod wave matches 0 run return 0
        if (!(KfcGen.scoreMatches(sb, "#mod", "wave", 0, 0))) {
            return 0;
        }

        // scoreboard players operation #step wave = timer gameState
        KfcGen.opScore(sb, "#step", "wave", "=", "timer", "gameState");

        // scoreboard players operation #step wave /= #intv wave
        KfcGen.opScore(sb, "#step", "wave", "/=", "#intv", "wave");

        // scoreboard players operation #cmp wave = #step wave
        KfcGen.opScore(sb, "#cmp", "wave", "=", "#step", "wave");

        // scoreboard players operation #cmp wave -= #count wave
        KfcGen.opScore(sb, "#cmp", "wave", "-=", "#count", "wave");

        // execute if score #cmp wave matches ..-1 run function game:infinity/spawn_one
        if (KfcGen.scoreMatches(sb, "#cmp", "wave", Integer.MIN_VALUE, -1)) {
            // -> game:infinity/spawn_one
            tdpack.buckets.Bucket7._m_55c39d23_game_infinity_spawn_one_execute(source);
        }
        return 0;
    }

    public static void _m_396c560e_game_infinity_process_entry_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_396c560e_game_infinity_process_entry_executeReturn(source, macroArgs);
    }

    public static int _m_396c560e_game_infinity_process_entry_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // $execute unless data storage map $(map).infinity.round[$(index)] run return 0
        if (!(KfcGen.storageHasPath(source.getServer(), "map", macroArgs.get("map") + ".infinity.round[" + macroArgs.get("index") + "]"))) {
            return 0;
        }

        // $data modify storage game infinity.mob set from storage map $(map).infinity.round[$(index)]
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetStorage(server, "map", macroArgs.get("map") + ".infinity.round[" + macroArgs.get("index") + "]"); if (_v != null) KfcGen.nbtSetStorage(server, "game", "infinity.mob", _v); }

        // scoreboard players operation #delta wave = game wave
        KfcGen.opScore(sb, "#delta", "wave", "=", "game", "wave");

        // $scoreboard players remove #delta wave $(unlock)
        try { KfcGen.addScore(sb, "#delta", "wave", -(Integer.parseInt(macroArgs.get("unlock")))); } catch (NumberFormatException _nfe) {}

        // execute if score #delta wave matches ..-1 run return 0
        if (KfcGen.scoreMatches(sb, "#delta", "wave", Integer.MIN_VALUE, -1)) {
            return 0;
        }

        // execute store result score #mob_type wave run data get storage game infinity.mob.kind 1
        KfcGen.setScore(sb, "#mob_type", "wave", (int)(KfcGen.storageGetDouble(server, "game", "infinity.mob.kind")));

        // execute if score #mob_type wave matches 1 run function game:infinity/process_normal
        if (KfcGen.scoreMatches(sb, "#mob_type", "wave", 1, 1)) {
            // -> game:infinity/process_normal
            tdpack.buckets.Bucket7._m_8db13c5c_game_infinity_process_normal_execute(source);
        }

        // execute if score #mob_type wave matches 2 run function game:infinity/process_elite
        if (KfcGen.scoreMatches(sb, "#mob_type", "wave", 2, 2)) {
            // -> game:infinity/process_elite
            tdpack.buckets.Bucket7._m_8f22b3a8_game_infinity_process_elite_execute(source);
        }

        // execute if score #mob_type wave matches 3 run function game:infinity/process_boss with storage game infinity.ctx
        if (KfcGen.scoreMatches(sb, "#mob_type", "wave", 3, 3)) {
            // -> game:infinity/process_boss
            tdpack.buckets.Bucket7._m_63b40ad8_game_infinity_process_boss_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));
        }
        return 0;
    }

    public static void _m_8db13c5c_game_infinity_process_normal_execute(ServerCommandSource source) {
        _m_8db13c5c_game_infinity_process_normal_executeReturn(source);
    }

    public static int _m_8db13c5c_game_infinity_process_normal_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score #delta wave matches 8.. run return 0
        if (KfcGen.scoreMatches(sb, "#delta", "wave", 8, Integer.MAX_VALUE)) {
            return 0;
        }

        // scoreboard players operation #count wave = #delta wave
        KfcGen.opScore(sb, "#count", "wave", "=", "#delta", "wave");

        // scoreboard players add #count wave 5
        KfcGen.addScore(sb, "#count", "wave", 5);

        // scoreboard players operation #end wave = #count wave
        KfcGen.opScore(sb, "#end", "wave", "=", "#count", "wave");

        // scoreboard players remove #end wave 1
        KfcGen.addScore(sb, "#end", "wave", -(1));

        // scoreboard players set #intv wave 20
        KfcGen.setScore(sb, "#intv", "wave", 20);

        // scoreboard players operation #end wave *= #intv wave
        KfcGen.opScore(sb, "#end", "wave", "*=", "#intv", "wave");

        // scoreboard players operation #infinity_end time > #end wave
        KfcGen.opScore(sb, "#infinity_end", "time", ">", "#end", "wave");

        // scoreboard players operation #mod wave = timer gameState
        KfcGen.opScore(sb, "#mod", "wave", "=", "timer", "gameState");

        // scoreboard players set #intv wave 20
        KfcGen.setScore(sb, "#intv", "wave", 20);

        // scoreboard players operation #mod wave %= #intv wave
        KfcGen.opScore(sb, "#mod", "wave", "%=", "#intv", "wave");

        // execute unless score #mod wave matches 0 run return 0
        if (!(KfcGen.scoreMatches(sb, "#mod", "wave", 0, 0))) {
            return 0;
        }

        // scoreboard players operation #step wave = timer gameState
        KfcGen.opScore(sb, "#step", "wave", "=", "timer", "gameState");

        // scoreboard players operation #step wave /= #intv wave
        KfcGen.opScore(sb, "#step", "wave", "/=", "#intv", "wave");

        // scoreboard players operation #cmp wave = #step wave
        KfcGen.opScore(sb, "#cmp", "wave", "=", "#step", "wave");

        // scoreboard players operation #cmp wave -= #count wave
        KfcGen.opScore(sb, "#cmp", "wave", "-=", "#count", "wave");

        // execute if score #cmp wave matches ..-1 run function game:infinity/spawn_one
        if (KfcGen.scoreMatches(sb, "#cmp", "wave", Integer.MIN_VALUE, -1)) {
            // -> game:infinity/spawn_one
            tdpack.buckets.Bucket7._m_55c39d23_game_infinity_spawn_one_execute(source);
        }
        return 0;
    }

    public static void _m_aa5af606_game_infinity_process_wave_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_aa5af606_game_infinity_process_wave_executeReturn(source, macroArgs);
    }

    public static int _m_aa5af606_game_infinity_process_wave_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // $data modify storage game infinity.ctx set value {map:"$(map)",index:0,unlock:1}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:0,unlock:1}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:1,unlock:3}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:1,unlock:3}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:2,unlock:6}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:2,unlock:6}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:3,unlock:9}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:3,unlock:9}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:4,unlock:12}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:4,unlock:12}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:5,unlock:15}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:5,unlock:15}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:6,unlock:18}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:6,unlock:18}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:7,unlock:21}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:7,unlock:21}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:8,unlock:24}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:8,unlock:24}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:9,unlock:27}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:9,unlock:27}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:10,unlock:30}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:10,unlock:30}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:11,unlock:33}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:11,unlock:33}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:12,unlock:36}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:12,unlock:36}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:13,unlock:39}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:13,unlock:39}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:14,unlock:42}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:14,unlock:42}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:15,unlock:45}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:15,unlock:45}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:16,unlock:48}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:16,unlock:48}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:17,unlock:51}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:17,unlock:51}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:18,unlock:54}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:18,unlock:54}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:19,unlock:57}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:19,unlock:57}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:20,unlock:60}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:20,unlock:60}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:21,unlock:63}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:21,unlock:63}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:22,unlock:66}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:22,unlock:66}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:23,unlock:69}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:23,unlock:69}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:24,unlock:72}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:24,unlock:72}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:25,unlock:75}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:25,unlock:75}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:26,unlock:78}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:26,unlock:78}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:27,unlock:81}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:27,unlock:81}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:28,unlock:84}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:28,unlock:84}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:29,unlock:87}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:29,unlock:87}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:30,unlock:90}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:30,unlock:90}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:31,unlock:93}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:31,unlock:93}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:32,unlock:96}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:32,unlock:96}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:33,unlock:99}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:33,unlock:99}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:34,unlock:102}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:34,unlock:102}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:35,unlock:105}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:35,unlock:105}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:36,unlock:108}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:36,unlock:108}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));

        // $data modify storage game infinity.ctx set value {map:"$(map)",index:37,unlock:111}
        KfcGen.storagePutSnbt(server, "game", "infinity.ctx", "{map:\"" + macroArgs.get("map") + "\",index:37,unlock:111}", "set");

        // function game:infinity/process_entry with storage game infinity.ctx
        // -> game:infinity/process_entry
        tdpack.buckets.Bucket7._m_396c560e_game_infinity_process_entry_execute(source, KfcGen.storageMacroArgs(server, "game", "infinity.ctx"));
        return 0;
    }

    public static void _m_dd4c88c1_game_infinity_round_progress_execute(ServerCommandSource source) {
        _m_dd4c88c1_game_infinity_round_progress_executeReturn(source);
    }

    public static int _m_dd4c88c1_game_infinity_round_progress_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set #infinity_end time 0
        KfcGen.setScore(sb, "#infinity_end", "time", 0);

        // function game:infinity/process_wave with storage game setup
        // -> game:infinity/process_wave
        tdpack.buckets.Bucket7._m_aa5af606_game_infinity_process_wave_execute(source, KfcGen.storageMacroArgs(server, "game", "setup"));

        // execute if score timer gameState >= #infinity_end time run scoreboard players set stage gameState 3
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#infinity_end", "time")) {
            KfcGen.setScore(sb, "stage", "gameState", 3);
        }

        // execute if score timer gameState >= #infinity_end time run give @a yellow_dye[item_name=[{text:"라운드 스킵",bold:true}]] 1
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#infinity_end", "time")) {
            for (net.minecraft.entity.Entity _gE : ctx.allPlayers) {
                if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "yellow_dye[item_name=[{text:\"라운드 스킵\",bold:true}]]", 1);
            }
        }

        // execute if score timer gameState >= #infinity_end time run scoreboard players operation #temp time = #infinity_end time
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#infinity_end", "time")) {
            KfcGen.opScore(sb, "#temp", "time", "=", "#infinity_end", "time");
        }

        // execute if score timer gameState >= #infinity_end time run scoreboard players add #temp time 300
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#infinity_end", "time")) {
            KfcGen.addScore(sb, "#temp", "time", 300);
        }
        return 0;
    }

    public static void _m_f3b8da53_game_infinity_round_wait_execute(ServerCommandSource source) {
        _m_f3b8da53_game_infinity_round_wait_executeReturn(source);
    }

    public static int _m_f3b8da53_game_infinity_round_wait_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if items entity @a weapon.offhand yellow_dye run scoreboard players set stage gameState 1
        if (KfcGen.anyPlayerItemsMatch(ctx, new String[0], new String[0], "weapon.offhand", "yellow_dye", null)) {
            KfcGen.setScore(sb, "stage", "gameState", 1);
        }

        // execute if items entity @a weapon.offhand yellow_dye run scoreboard players add game wave 1
        if (KfcGen.anyPlayerItemsMatch(ctx, new String[0], new String[0], "weapon.offhand", "yellow_dye", null)) {
            KfcGen.addScore(sb, "game", "wave", 1);
        }

        // execute if items entity @a weapon.offhand yellow_dye run scoreboard players set timer gameState -1
        if (KfcGen.anyPlayerItemsMatch(ctx, new String[0], new String[0], "weapon.offhand", "yellow_dye", null)) {
            KfcGen.setScore(sb, "timer", "gameState", -1);
        }

        // execute if score timer gameState >= #temp time run scoreboard players set stage gameState 1
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            KfcGen.setScore(sb, "stage", "gameState", 1);
        }

        // execute if score timer gameState >= #temp time run scoreboard players add game wave 1
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            KfcGen.addScore(sb, "game", "wave", 1);
        }

        // execute if score timer gameState >= #temp time run scoreboard players set timer gameState -1
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            KfcGen.setScore(sb, "timer", "gameState", -1);
        }

        // execute unless entity @n[tag=enemy] run scoreboard players set stage gameState 1
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1))) {
            KfcGen.setScore(sb, "stage", "gameState", 1);
        }

        // execute unless entity @n[tag=enemy] run scoreboard players add game wave 1
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1))) {
            KfcGen.addScore(sb, "game", "wave", 1);
        }

        // execute unless entity @n[tag=enemy] run scoreboard players set timer gameState -1
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1))) {
            KfcGen.setScore(sb, "timer", "gameState", -1);
        }
        return 0;
    }

    public static void _m_17d6ec70_game_infinity_spawn_loop_execute(ServerCommandSource source) {
        _m_17d6ec70_game_infinity_spawn_loop_executeReturn(source);
    }

    public static int _m_17d6ec70_game_infinity_spawn_loop_executeReturn(ServerCommandSource source) {
        
        // 자기재귀 → 브릿지(스택오버플로 방지) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("game", "infinity/spawn_loop"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // execute if score #loop wave matches ..0 run return 0
                // if (KfcGen.scoreMatches(sb, "#loop", "wave", Integer.MIN_VALUE, 0)) {
                //     return 0;
                // }
                // 
                // // function game:infinity/spawn_one
                // // -> game:infinity/spawn_one
                // tdpack.buckets.Bucket7._m_55c39d23_game_infinity_spawn_one_execute(source);
                // 
                // // scoreboard players remove #loop wave 1
                // KfcGen.addScore(sb, "#loop", "wave", -(1));
                // 
                // // function game:infinity/spawn_loop
                // // -> game:infinity/spawn_loop
                // tdpack.buckets.Bucket7._m_17d6ec70_game_infinity_spawn_loop_execute(source);
                return kfcBridgeRet;
    }

    public static void _m_55c39d23_game_infinity_spawn_one_execute(ServerCommandSource source) {
        _m_55c39d23_game_infinity_spawn_one_executeReturn(source);
    }

    public static int _m_55c39d23_game_infinity_spawn_one_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute as @n[tag=map.spawn_point,tag=game] at @s run function api:enemy/spawn with storage game infinity.mob
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.spawn_point", "game"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc17 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> api:enemy/spawn
            tdpack.buckets.Bucket0._m_dcd1441b_api_enemy_spawn_execute(kfcSrc17, KfcGen.storageMacroArgs(server, "game", "infinity.mob"));
        } }
        return 0;
    }

    public static void _m_6e8e204b_game_player_init_execute(ServerCommandSource source) {
        _m_6e8e204b_game_player_init_executeReturn(source);
    }

    public static int _m_6e8e204b_game_player_init_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players add global player.id 1
        KfcGen.addScore(sb, "global", "player.id", 1);

        // scoreboard players operation @s player.id = global player.id
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "player.id", "=", "global", "player.id");
        return 0;
    }

    public static void _m_e87e78dd_game_story_api_stage_toggle_hidden_apply_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_e87e78dd_game_story_api_stage_toggle_hidden_apply_executeReturn(source, macroArgs);
    }

    public static int _m_e87e78dd_game_story_api_stage_toggle_hidden_apply_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/1줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("game", "story/api/stage/toggle_hidden_apply"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_c42e17e9_game_story_api_wave_round_delete_apply_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_c42e17e9_game_story_api_wave_round_delete_apply_executeReturn(source, macroArgs);
    }

    public static int _m_c42e17e9_game_story_api_wave_round_delete_apply_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/1줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("game", "story/api/wave/round_delete_apply"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_15223bbc_game_story_runtime_clear_resolve_apply_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_15223bbc_game_story_runtime_clear_resolve_apply_executeReturn(source, macroArgs);
    }

    public static int _m_15223bbc_game_story_runtime_clear_resolve_apply_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/1줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("game", "story/runtime/clear_resolve_apply"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_64694d18_game_story_runtime_complete_current_apply_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_64694d18_game_story_runtime_complete_current_apply_executeReturn(source, macroArgs);
    }

    public static int _m_64694d18_game_story_runtime_complete_current_apply_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/1줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("game", "story/runtime/complete_current_apply"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_0322d66a_game_story_ui_editor_prepare_round_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_0322d66a_game_story_ui_editor_prepare_round_executeReturn(source, macroArgs);
    }

    public static int _m_0322d66a_game_story_ui_editor_prepare_round_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/1줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("game", "story/ui/editor_prepare_round"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_faf99388_game_ui_actionbar_execute(ServerCommandSource source) {
        _m_faf99388_game_ui_actionbar_executeReturn(source);
    }

    public static int _m_faf99388_game_ui_actionbar_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute unless score game wave matches 0 run title @s actionbar [{text:"소지금: ", bold:true, color:green},{score:{name:"@s", objective:money},bold:false, color:yellow},{text:" | ", bold:false, color:white},{text:"체력: ", bold:true, color:red},{score:{name:"game", objective:game.base_health},bold:false, color:yellow},{text:" | ", bold:false, color:white},{text:"웨이브: ", bold:true, color:aqua},{score:{name:"game", objective:wave},bold:false, color:yellow},{text:" | ", bold:false, color:white}]
        if (!(KfcGen.scoreMatches(sb, "game", "wave", 0, 0))) {
            if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.titleActionbar(_tp, source, "[{text:\"소지금: \", bold:true, color:green},{score:{name:\"@s\", objective:money},bold:false, color:yellow},{text:\" | \", bold:false, color:white},{text:\"체력: \", bold:true, color:red},{score:{name:\"game\", objective:game.base_health},bold:false, color:yellow},{text:\" | \", bold:false, color:white},{text:\"웨이브: \", bold:true, color:aqua},{score:{name:\"game\", objective:wave},bold:false, color:yellow},{text:\" | \", bold:false, color:white}]");
        }

        // execute if score game wave matches 0 run title @s actionbar [{text:"소지금: ", bold:true, color:green},{score:{name:"@s", objective:money},bold:false, color:yellow},{text:" | ", bold:false, color:white},{text:"체력: ", bold:true, color:red},{score:{name:"game", objective:game.base_health},bold:false, color:yellow},{text:" | ", bold:false, color:white},{text:"웨이브: ", bold:true, color:aqua},{text:"준비",bold:true, color:green},{text:" | ", bold:false, color:white}]
        if (KfcGen.scoreMatches(sb, "game", "wave", 0, 0)) {
            if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.titleActionbar(_tp, source, "[{text:\"소지금: \", bold:true, color:green},{score:{name:\"@s\", objective:money},bold:false, color:yellow},{text:\" | \", bold:false, color:white},{text:\"체력: \", bold:true, color:red},{score:{name:\"game\", objective:game.base_health},bold:false, color:yellow},{text:\" | \", bold:false, color:white},{text:\"웨이브: \", bold:true, color:aqua},{text:\"준비\",bold:true, color:green},{text:\" | \", bold:false, color:white}]");
        }
        return 0;
    }

    public static void _m_c80f526c_game_ui_main_execute(ServerCommandSource source) {
        _m_c80f526c_game_ui_main_executeReturn(source);
    }

    public static int _m_c80f526c_game_ui_main_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute as @a run function game:ui/actionbar
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            // -> game:ui/actionbar
            tdpack.buckets.Bucket7._m_faf99388_game_ui_actionbar_execute(es);
        }
        return 0;
    }

    public static void _m_615eb1f6_minecraft_load_execute(ServerCommandSource source) {
        _m_615eb1f6_minecraft_load_executeReturn(source);
    }

    public static int _m_615eb1f6_minecraft_load_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // function api:load
        // -> api:load
        tdpack.buckets.Bucket0._m_b092a33d_api_load_execute(source);

        // function game:load
        // -> game:load
        tdpack.buckets.Bucket6._m_a2c1fafb_game_load_execute(source);

        // function tower:load
        // -> tower:load
        tdpack.buckets.Bucket7._m_53755743_tower_load_execute(source);

        // function enemy:load
        // -> enemy:load
        tdpack.buckets.Bucket4._m_54968aca_enemy_load_execute(source);

        // function map:load
        // -> map:load
        KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("map", "load"));

        // tellraw @a {text:"게임 로드 완료!",color:green}
        for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
            KfcGen.tellraw(_tp, source, "{text:\"게임 로드 완료!\",color:green}");
        }
        return 0;
    }

    public static void _m_1628f07b_minecraft_tick_execute(ServerCommandSource source) {
        _m_1628f07b_minecraft_tick_executeReturn(source);
    }

    public static int _m_1628f07b_minecraft_tick_executeReturn(ServerCommandSource source) {
        
        // function game:tick
        // -> game:tick
        tdpack.buckets.Bucket6._m_a5c02ce3_game_tick_execute(source);

        // function map:tick
        // -> map:tick
        KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("map", "tick"));

        // function tower:tick
        // -> tower:tick
        tdpack.buckets.Bucket7._m_7989f5ae_tower_tick_execute(source);

        // function enemy:tick
        // -> enemy:tick
        tdpack.buckets.Bucket4._m_28da3f46_enemy_tick_execute(source);

        // function api:tick
        // -> api:tick
        tdpack.buckets.Bucket0._m_3121f975_api_tick_execute(source);
        return 0;
    }

    public static void _m_53755743_tower_load_execute(ServerCommandSource source) {
        _m_53755743_tower_load_executeReturn(source);
    }

    public static int _m_53755743_tower_load_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard objectives add blueprint_id dummy
        KfcGen.ensureObjective(sb, "blueprint_id", "dummy");

        // scoreboard objectives add blueprint_seq dummy
        KfcGen.ensureObjective(sb, "blueprint_seq", "dummy");

        // scoreboard objectives add tower.id dummy
        KfcGen.ensureObjective(sb, "tower.id", "dummy");

        // scoreboard objectives add tower.animation dummy
        KfcGen.ensureObjective(sb, "tower.animation", "dummy");

        // scoreboard objectives add tower.number dummy
        KfcGen.ensureObjective(sb, "tower.number", "dummy");

        // scoreboard objectives add tower.attack_countdown dummy
        KfcGen.ensureObjective(sb, "tower.attack_countdown", "dummy");

        // scoreboard objectives add tower.target_mode dummy
        KfcGen.ensureObjective(sb, "tower.target_mode", "dummy");

        // scoreboard objectives add tower.attack dummy
        KfcGen.ensureObjective(sb, "tower.attack", "dummy");

        // scoreboard objectives add tower.attack_speed dummy
        KfcGen.ensureObjective(sb, "tower.attack_speed", "dummy");

        // scoreboard objectives add tower.level dummy
        KfcGen.ensureObjective(sb, "tower.level", "dummy");

        // scoreboard objectives add tower.y dummy
        KfcGen.ensureObjective(sb, "tower.y", "dummy");

        // scoreboard objectives add tower.limit dummy
        KfcGen.ensureObjective(sb, "tower.limit", "dummy");

        // scoreboard objectives add tower.model.y_sync dummy
        KfcGen.ensureObjective(sb, "tower.model.y_sync", "dummy");

        // scoreboard objectives add tower.attribute.freeze dummy
        KfcGen.ensureObjective(sb, "tower.attribute.freeze", "dummy");

        // scoreboard objectives add tower.attribute.freeze_duration dummy
        KfcGen.ensureObjective(sb, "tower.attribute.freeze_duration", "dummy");

        // scoreboard objectives add tower.attribute.poison dummy
        KfcGen.ensureObjective(sb, "tower.attribute.poison", "dummy");

        // scoreboard objectives add tower.attribute.flame dummy
        KfcGen.ensureObjective(sb, "tower.attribute.flame", "dummy");

        // scoreboard objectives add tower.attribute.bleed dummy
        KfcGen.ensureObjective(sb, "tower.attribute.bleed", "dummy");

        // scoreboard objectives add tower.attribute.stun dummy
        KfcGen.ensureObjective(sb, "tower.attribute.stun", "dummy");

        // scoreboard objectives add tower.state.stun dummy
        KfcGen.ensureObjective(sb, "tower.state.stun", "dummy");

        // scoreboard objectives add tower dummy
        KfcGen.ensureObjective(sb, "tower", "dummy");

        // scoreboard players set #2 tower 2
        KfcGen.setScore(sb, "#2", "tower", 2);

        // function tower:bullet/load
        // -> tower:bullet/load
        tdpack.buckets.Bucket8._m_fe9c6bd9_tower_bullet_load_execute(source);

        // function tower:armory/load
        // -> tower:armory/load
        tdpack.buckets.Bucket7._m_00810e73_tower_armory_load_execute(source);
        return 0;
    }

    public static void _m_a0b99693_tower_main_execute(ServerCommandSource source) {
        _m_a0b99693_tower_main_executeReturn(source);
    }

    public static int _m_a0b99693_tower_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @s[tag=tower.data] run function tower:state/main
        if ((executor != null && executor.getCommandTags().contains("tower.data"))) {
            // -> tower:state/main
            tdpack.buckets.Bucket10._m_13b33e70_tower_state_main_execute(source);
        }

        // execute if entity @s[tag=tower.muzzle] at @s run function tower:attack/main with entity @s data
        {
            ServerCommandSource kfcSrc103 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("tower.muzzle"))) {
                // -> tower:attack/main
                tdpack.buckets.Bucket7._m_4f0d1a71_tower_attack_main_execute(kfcSrc103, KfcGen.entityMacroArgs(executor, "data"));
            }
        }

        // execute if entity @s[tag=tower.animation] at @s run function tower:animation/main
        {
            ServerCommandSource kfcSrc104 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("tower.animation"))) {
                // -> tower:animation/main
                tdpack.buckets.Bucket7._m_48fb7720_tower_animation_main_execute(kfcSrc104);
            }
        }

        // execute if entity @s[tag=tower.hitbox] at @s run function tower:upgrade/main
        {
            ServerCommandSource kfcSrc105 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("tower.hitbox"))) {
                // -> tower:upgrade/main
                tdpack.buckets.Bucket10._m_a04def05_tower_upgrade_main_execute(kfcSrc105);
            }
        }

        // execute if entity @s[tag=tower.upgrade_range] at @s if score @n[tag=tower.core] player.id matches 1.. run function tower:upgrade/show_range with entity @n[tag=tower.data] data
        {
            ServerCommandSource kfcSrc106 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("tower.upgrade_range"))) {
                if (KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true)), "player.id", 1, Integer.MAX_VALUE, false)) {
                    // -> tower:upgrade/show_range
                    tdpack.buckets.Bucket10._m_d85b1472_tower_upgrade_show_range_execute(kfcSrc106, KfcGen.entityMacroArgs(KfcGen.nearestEntityAnyType(ctx, kfcSrc106.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1), "data"));
                }
            }
        }

        // function tower:armory/main
        // -> tower:armory/main
        tdpack.buckets.Bucket7._m_fc585490_tower_armory_main_execute(source);
        return 0;
    }

    public static void _m_7989f5ae_tower_tick_execute(ServerCommandSource source) {
        _m_7989f5ae_tower_tick_executeReturn(source);
    }

    public static int _m_7989f5ae_tower_tick_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute as @a at @s run function tower:spawn/main
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc100 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:spawn/main
            tdpack.buckets.Bucket8._m_d202a715_tower_spawn_main_execute(kfcSrc100);
        }

        // execute as @e[tag=tower.data,type=marker] run function tower:state/main
        for (Entity e : ctx.world.getEntitiesByType(EntityType.MARKER,
                en -> en.getCommandTags().contains("tower.data"))) {
            ServerCommandSource es = source.withEntity(e);
            // -> tower:state/main
            tdpack.buckets.Bucket10._m_13b33e70_tower_state_main_execute(es);
        }

        // execute as @e[tag=tower.muzzle,type=marker] at @s run function tower:attack/main with entity @s data
        for (Entity e : ctx.world.getEntitiesByType(EntityType.MARKER,
                en -> en.getCommandTags().contains("tower.muzzle"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc101 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:attack/main
            tdpack.buckets.Bucket7._m_4f0d1a71_tower_attack_main_execute(kfcSrc101, KfcGen.entityMacroArgs(e, "data"));
        }

        // execute as @e[tag=tower.data,type=marker] at @s run function tower:animation/main
        for (Entity e : ctx.world.getEntitiesByType(EntityType.MARKER,
                en -> en.getCommandTags().contains("tower.data"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc102 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:animation/main
            tdpack.buckets.Bucket7._m_48fb7720_tower_animation_main_execute(kfcSrc102);
        }

        // function tower:bullet/main
        // -> tower:bullet/main
        tdpack.buckets.Bucket8._m_1b810019_tower_bullet_main_execute(source);

        // execute as @e[tag=tower.hitbox,type=interaction] at @s run function tower:upgrade/main
        for (Entity e : ctx.world.getEntitiesByType(EntityType.INTERACTION,
                en -> en.getCommandTags().contains("tower.hitbox"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc103 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:upgrade/main
            tdpack.buckets.Bucket10._m_a04def05_tower_upgrade_main_execute(kfcSrc103);
        }

        // execute as @e[tag=tower.upgrade_range] at @s if score @n[tag=tower.core] player.id matches 1.. run function tower:upgrade/show_range with entity @n[tag=tower.data] data
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.upgrade_range"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc104 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, es.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true)), "player.id", 1, Integer.MAX_VALUE, false))) continue;
            // -> tower:upgrade/show_range
            tdpack.buckets.Bucket10._m_d85b1472_tower_upgrade_show_range_execute(kfcSrc104, KfcGen.entityMacroArgs(KfcGen.nearestEntityAnyType(ctx, kfcSrc104.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1), "data"));
        }

        // function tower:armory/main
        // -> tower:armory/main
        tdpack.buckets.Bucket7._m_fc585490_tower_armory_main_execute(source);
        return 0;
    }

    public static void _m_48fb7720_tower_animation_main_execute(ServerCommandSource source) {
        _m_48fb7720_tower_animation_main_executeReturn(source);
    }

    public static int _m_48fb7720_tower_animation_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if entity @s[scores={tower.state.stun=1..}] run return 0
        if ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "tower.state.stun", 1, Integer.MAX_VALUE))) {
            return 0;
        }

        // execute store result storage tower temp.level int 1 run scoreboard players get @s tower.level
        KfcGen.storagePutNumber(server, "tower", "temp.level", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.level"), "int");

        // function tower:animation/main-sub
        // -> tower:animation/main-sub
        tdpack.buckets.Bucket7._m_3ba86465_tower_animation_main_sub_execute(source);

        // execute on vehicle on passengers if entity @s[tag=tower.animation] run function tower:animation/main-sub
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if ((_onEnt2 != null && _onEnt2.getCommandTags().contains("tower.animation"))) {
                    // -> tower:animation/main-sub
                    tdpack.buckets.Bucket7._m_3ba86465_tower_animation_main_sub_execute(_on2);
                }
            }
          } }
        return 0;
    }

    public static void _m_3ba86465_tower_animation_main_sub_execute(ServerCommandSource source) {
        _m_3ba86465_tower_animation_main_sub_executeReturn(source);
    }

    public static int _m_3ba86465_tower_animation_main_sub_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if entity @s[tag=crossbow] run function tower:animation/crossbow/main with storage tower temp
        if ((executor != null && executor.getCommandTags().contains("crossbow"))) {
            // -> tower:animation/crossbow/main
            tdpack.buckets.Bucket7._m_496d8bf9_tower_animation_crossbow_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute if entity @s[tag=sniper] run function tower:animation/sniper/main with storage tower temp
        if ((executor != null && executor.getCommandTags().contains("sniper"))) {
            // -> tower:animation/sniper/main
            tdpack.buckets.Bucket7._m_c73be216_tower_animation_sniper_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute if entity @s[tag=freeze-tower] run function tower:animation/freeze-tower/main with storage tower temp
        if ((executor != null && executor.getCommandTags().contains("freeze-tower"))) {
            // -> tower:animation/freeze-tower/main
            tdpack.buckets.Bucket7._m_631e71ce_tower_animation_freeze_tower_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute if entity @s[tag=gatling] run function tower:animation/gatling/main with storage tower temp
        if ((executor != null && executor.getCommandTags().contains("gatling"))) {
            // -> tower:animation/gatling/main
            tdpack.buckets.Bucket7._m_ab32d368_tower_animation_gatling_main_execute(source);
        }

        // execute if entity @s[tag=coilgun] run function tower:animation/coilgun/main with storage tower temp
        if ((executor != null && executor.getCommandTags().contains("coilgun"))) {
            // -> tower:animation/coilgun/main
            tdpack.buckets.Bucket7._m_6f81bf34_tower_animation_coilgun_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute if entity @s[tag=machine-gun] run function tower:animation/machine-gun/main with storage tower temp
        if ((executor != null && executor.getCommandTags().contains("machine-gun"))) {
            // -> tower:animation/machine-gun/main
            tdpack.buckets.Bucket7._m_7896d2ca_tower_animation_machine_gun_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute if entity @s[tag=railgun] run function tower:animation/railgun/main with storage tower temp
        if ((executor != null && executor.getCommandTags().contains("railgun"))) {
            // -> tower:animation/railgun/main
            tdpack.buckets.Bucket7._m_5ea517ac_tower_animation_railgun_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute if entity @s[tag=stun-tower] run function tower:animation/stun-tower/main with storage tower temp
        if ((executor != null && executor.getCommandTags().contains("stun-tower"))) {
            // -> tower:animation/stun-tower/main
            tdpack.buckets.Bucket7._m_1c690409_tower_animation_stun_tower_main_execute(source);
        }
        return 0;
    }

    public static void _m_6f81bf34_tower_animation_coilgun_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_6f81bf34_tower_animation_coilgun_main_executeReturn(source, macroArgs);
    }

    public static int _m_6f81bf34_tower_animation_coilgun_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/4줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "animation/coilgun/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function tower:animation/coilgun/lv$(level)/main
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.trial_spawner.ominous_activate record @a ~ ~ ~ 0.5 1.0
                // if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                //     for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                //         KfcGen.playSound(_ps, "minecraft:block.trial_spawner.ominous_activate", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 1.0f);
                //     }
                // }
                // 
                // // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:item.ominous_bottle.dispose record @a ~ ~ ~ 1 1.5
                // if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                //     for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                //         KfcGen.playSound(_ps, "minecraft:item.ominous_bottle.dispose", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.5f);
                //     }
                // }
                // 
                // // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.beacon.activate record @a ~ ~ ~ 1 1.5
                // if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                //     for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                //         KfcGen.playSound(_ps, "minecraft:block.beacon.activate", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.5f);
                //     }
                // }
        return 0;
    }

    public static void _m_c89560e3_tower_animation_coilgun_lv0_main_execute(ServerCommandSource source) {
        _m_c89560e3_tower_animation_coilgun_lv0_main_executeReturn(source);
    }

    public static int _m_c89560e3_tower_animation_coilgun_lv0_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 50 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.2
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 50, 50, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.2f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 50 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 50, 50, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_28d70137_tower_animation_coilgun_lv1_main_execute(ServerCommandSource source) {
        _m_28d70137_tower_animation_coilgun_lv1_main_executeReturn(source);
    }

    public static int _m_28d70137_tower_animation_coilgun_lv1_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 45 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.4
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 45, 45, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.4f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 45 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 45, 45, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_ea170950_tower_animation_coilgun_lv2_main_execute(ServerCommandSource source) {
        _m_ea170950_tower_animation_coilgun_lv2_main_executeReturn(source);
    }

    public static int _m_ea170950_tower_animation_coilgun_lv2_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 40 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.6
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 40, 40, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.6f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 40 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 40, 40, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_a285505a_tower_animation_coilgun_lv3_main_execute(ServerCommandSource source) {
        _m_a285505a_tower_animation_coilgun_lv3_main_executeReturn(source);
    }

    public static int _m_a285505a_tower_animation_coilgun_lv3_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 35 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.8
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 35, 35, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.8f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 35 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 35, 35, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_0079dda7_tower_animation_coilgun_lv4_main_execute(ServerCommandSource source) {
        _m_0079dda7_tower_animation_coilgun_lv4_main_executeReturn(source);
    }

    public static int _m_0079dda7_tower_animation_coilgun_lv4_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 2.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 2.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_ce1a8964_tower_animation_coilgun_lv5_main_execute(ServerCommandSource source) {
        _m_ce1a8964_tower_animation_coilgun_lv5_main_executeReturn(source);
    }

    public static int _m_ce1a8964_tower_animation_coilgun_lv5_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 20 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 2.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 20, 20, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 2.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 20 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 20, 20, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_496d8bf9_tower_animation_crossbow_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_496d8bf9_tower_animation_crossbow_main_executeReturn(source, macroArgs);
    }

    public static int _m_496d8bf9_tower_animation_crossbow_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/1줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "animation/crossbow/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function tower:animation/crossbow/lv$(level)/main
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_a1e56fe8_tower_animation_crossbow_lv0_main_execute(ServerCommandSource source) {
        _m_a1e56fe8_tower_animation_crossbow_lv0_main_executeReturn(source);
    }

    public static int _m_a1e56fe8_tower_animation_crossbow_lv0_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.animation0] tower.animation matches 20 run data modify entity @s item.components.minecraft:charged_projectiles set value [{id:"minecraft:arrow",count:1b}]
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 20, 20, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[{id:\"minecraft:arrow\",count:1b}]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 20 run playsound item.crossbow.quick_charge_1 record @a ~ ~ ~ 0.7 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 20, 20, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.quick_charge_1", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.7f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run data modify entity @s item.components.minecraft:charged_projectiles set value []
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run playsound item.crossbow.shoot record @a ~ ~ ~ 0.5 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_cff55192_tower_animation_crossbow_lv1_main_execute(ServerCommandSource source) {
        _m_cff55192_tower_animation_crossbow_lv1_main_executeReturn(source);
    }

    public static int _m_cff55192_tower_animation_crossbow_lv1_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.animation0] tower.animation matches 15 run data modify entity @s item.components.minecraft:charged_projectiles set value [{id:"minecraft:arrow",count:1b}]
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 15, 15, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[{id:\"minecraft:arrow\",count:1b}]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 15 run playsound item.crossbow.quick_charge_1 record @a ~ ~ ~ 0.7 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 15, 15, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.quick_charge_1", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.7f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run data modify entity @s item.components.minecraft:charged_projectiles set value []
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run playsound item.crossbow.shoot record @a ~ ~ ~ 0.5 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_e4a471c5_tower_animation_crossbow_lv2_main_execute(ServerCommandSource source) {
        _m_e4a471c5_tower_animation_crossbow_lv2_main_executeReturn(source);
    }

    public static int _m_e4a471c5_tower_animation_crossbow_lv2_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.animation0] tower.animation matches 10 run data modify entity @s item.components.minecraft:charged_projectiles set value [{id:"minecraft:arrow",count:1b}]
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 10, 10, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[{id:\"minecraft:arrow\",count:1b}]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 10 run playsound item.crossbow.quick_charge_1 record @a ~ ~ ~ 0.7 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 10, 10, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.quick_charge_1", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.7f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run data modify entity @s item.components.minecraft:charged_projectiles set value []
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run playsound item.crossbow.shoot record @a ~ ~ ~ 0.5 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_710fb5c9_tower_animation_crossbow_lv3_main_execute(ServerCommandSource source) {
        _m_710fb5c9_tower_animation_crossbow_lv3_main_executeReturn(source);
    }

    public static int _m_710fb5c9_tower_animation_crossbow_lv3_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.animation0] tower.animation matches 7 run data modify entity @s item.components.minecraft:charged_projectiles set value [{id:"minecraft:arrow",count:1b}]
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 7, 7, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[{id:\"minecraft:arrow\",count:1b}]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 7 run playsound item.crossbow.quick_charge_1 record @a ~ ~ ~ 0.7 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 7, 7, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.quick_charge_1", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.7f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run data modify entity @s item.components.minecraft:charged_projectiles set value []
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run playsound item.crossbow.shoot record @a ~ ~ ~ 0.5 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_e452650a_tower_animation_crossbow_lv4_main_execute(ServerCommandSource source) {
        _m_e452650a_tower_animation_crossbow_lv4_main_executeReturn(source);
    }

    public static int _m_e452650a_tower_animation_crossbow_lv4_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.animation0] tower.animation matches 5 run data modify entity @s item.components.minecraft:charged_projectiles set value [{id:"minecraft:arrow",count:1b}]
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 5, 5, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[{id:\"minecraft:arrow\",count:1b}]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 5 run playsound item.crossbow.quick_charge_1 record @a ~ ~ ~ 0.7 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 5, 5, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.quick_charge_1", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.7f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run data modify entity @s item.components.minecraft:charged_projectiles set value []
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "item.components.minecraft:charged_projectiles", "[]");
        }

        // execute if score @s[tag=tower.animation0] tower.animation matches 1 run playsound item.crossbow.shoot record @a ~ ~ ~ 0.5 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.animation0")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.crossbow.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_631e71ce_tower_animation_freeze_tower_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_631e71ce_tower_animation_freeze_tower_main_executeReturn(source, macroArgs);
    }

    public static int _m_631e71ce_tower_animation_freeze_tower_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/2줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "animation/freeze-tower/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function tower:animation/freeze-tower/lv$(level)/main
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.trial_spawner.spawn_item record @a ~ ~ ~ 1 1
                // if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                //     for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                //         KfcGen.playSound(_ps, "minecraft:block.trial_spawner.spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
                //     }
                // }
        return 0;
    }

    public static void _m_ab32d368_tower_animation_gatling_main_execute(ServerCommandSource source) {
        _m_ab32d368_tower_animation_gatling_main_executeReturn(source);
    }

    public static int _m_ab32d368_tower_animation_gatling_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.blast record @a ~ ~ ~ 0.6 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.6f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 at @s run particle flame ^ ^ ^2 0 0 0 0.1 1 force @a
        {
            ServerCommandSource kfcSrc1 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                { net.minecraft.util.math.Vec3d _pp = KfcGen.localOffset(kfcSrc1.getPosition(), kfcSrc1.getRotation(), 0.0, 0.0, 2.0); KfcGen.spawnParticle(ctx.world, "flame", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0.1, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }
        return 0;
    }

    public static void _m_7896d2ca_tower_animation_machine_gun_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_7896d2ca_tower_animation_machine_gun_main_executeReturn(source, macroArgs);
    }

    public static int _m_7896d2ca_tower_animation_machine_gun_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/1줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "animation/machine-gun/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function tower:animation/machine-gun/lv$(level)/main
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_b46295b9_tower_animation_machine_gun_lv0_main_execute(ServerCommandSource source) {
        _m_b46295b9_tower_animation_machine_gun_lv0_main_executeReturn(source);
    }

    public static int _m_b46295b9_tower_animation_machine_gun_lv0_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.large_blast record @a ~ ~ ~ 0.4 1.1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.large_blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 1.1f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.chain.fall record @a ~ ~ ~ 1.0 1.1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.chain.fall", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.1f);
            }
        }
        return 0;
    }

    public static void _m_acf66db8_tower_animation_machine_gun_lv1_main_execute(ServerCommandSource source) {
        _m_acf66db8_tower_animation_machine_gun_lv1_main_executeReturn(source);
    }

    public static int _m_acf66db8_tower_animation_machine_gun_lv1_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.large_blast record @a ~ ~ ~ 0.4 1.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.large_blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.chain.fall record @a ~ ~ ~ 1.0 1.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.chain.fall", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_2bd4dbf4_tower_animation_machine_gun_lv2_main_execute(ServerCommandSource source) {
        _m_2bd4dbf4_tower_animation_machine_gun_lv2_main_executeReturn(source);
    }

    public static int _m_2bd4dbf4_tower_animation_machine_gun_lv2_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.large_blast record @a ~ ~ ~ 0.4 0.9
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.large_blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.9f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.chain.fall record @a ~ ~ ~ 1.0 0.9
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.chain.fall", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.9f);
            }
        }
        return 0;
    }

    public static void _m_e04d300c_tower_animation_machine_gun_lv3_main_execute(ServerCommandSource source) {
        _m_e04d300c_tower_animation_machine_gun_lv3_main_executeReturn(source);
    }

    public static int _m_e04d300c_tower_animation_machine_gun_lv3_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.large_blast record @a ~ ~ ~ 0.4 0.8
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.large_blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.8f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.chain.fall record @a ~ ~ ~ 1.0 0.8
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.chain.fall", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.8f);
            }
        }
        return 0;
    }

    public static void _m_a2715955_tower_animation_machine_gun_lv4_main_execute(ServerCommandSource source) {
        _m_a2715955_tower_animation_machine_gun_lv4_main_executeReturn(source);
    }

    public static int _m_a2715955_tower_animation_machine_gun_lv4_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.large_blast record @a ~ ~ ~ 0.4 0.7
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.large_blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.7f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.chain.fall record @a ~ ~ ~ 1.0 0.7
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.chain.fall", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.7f);
            }
        }
        return 0;
    }

    public static void _m_b04e5978_tower_animation_machine_gun_lv5_main_execute(ServerCommandSource source) {
        _m_b04e5978_tower_animation_machine_gun_lv5_main_executeReturn(source);
    }

    public static int _m_b04e5978_tower_animation_machine_gun_lv5_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.large_blast record @a ~ ~ ~ 0.4 0.6
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.large_blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.6f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.chain.fall record @a ~ ~ ~ 1.0 0.6
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.chain.fall", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.6f);
            }
        }
        return 0;
    }

    public static void _m_3f261bd9_tower_animation_machine_gun_lv6_main_execute(ServerCommandSource source) {
        _m_3f261bd9_tower_animation_machine_gun_lv6_main_executeReturn(source);
    }

    public static int _m_3f261bd9_tower_animation_machine_gun_lv6_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.large_blast record @a ~ ~ ~ 0.4 0.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.large_blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.chain.fall record @a ~ ~ ~ 1.0 0.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.chain.fall", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.5f);
            }
        }
        return 0;
    }

    public static void _m_5ea517ac_tower_animation_railgun_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_5ea517ac_tower_animation_railgun_main_executeReturn(source, macroArgs);
    }

    public static int _m_5ea517ac_tower_animation_railgun_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/3줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "animation/railgun/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function tower:animation/railgun/lv$(level)/main
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound entity.firework_rocket.large_blast_far record @a ~ ~ ~ 1 0.8
                // if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                //     for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                //         KfcGen.playSound(_ps, "entity.firework_rocket.large_blast_far", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.8f);
                //     }
                // }
                // 
                // // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound block.beacon.deactivate record @a ~ ~ ~ 1 0.8
                // if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                //     for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                //         KfcGen.playSound(_ps, "block.beacon.deactivate", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.8f);
                //     }
                // }
        return 0;
    }

    public static void _m_d9c57808_tower_animation_railgun_lv0_main_execute(ServerCommandSource source) {
        _m_d9c57808_tower_animation_railgun_lv0_main_executeReturn(source);
    }

    public static int _m_d9c57808_tower_animation_railgun_lv0_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 86 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.3
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 86, 86, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.3f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 72 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.4
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 72, 72, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.4f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 60 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 60, 60, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 50 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.6
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 50, 50, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.6f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 42 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.7
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 42, 42, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.7f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 36 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.8
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 36, 36, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.8f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 32 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.9
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 32, 32, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.9f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 2.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 2.0f);
            }
        }
        return 0;
    }

    public static void _m_e95c57fd_tower_animation_railgun_lv1_main_execute(ServerCommandSource source) {
        _m_e95c57fd_tower_animation_railgun_lv1_main_executeReturn(source);
    }

    public static int _m_e95c57fd_tower_animation_railgun_lv1_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 30..110 run playsound minecraft:block.note_block.didgeridoo record @a ~ ~ ~ 0.05 0.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 110, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.note_block.didgeridoo", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.05f, 0.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 120 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 120, 120, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.1f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 102 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.2
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 102, 102, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.2f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 86 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.3
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 86, 86, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.3f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 72 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.4
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 72, 72, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.4f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 60 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 60, 60, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 50 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.6
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 50, 50, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.6f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 42 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.7
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 42, 42, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.7f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 36 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.8
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 36, 36, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.8f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 32 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.9
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 32, 32, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.9f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 2.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 2.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:block.trial_spawner.ambient_ominous record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.ambient_ominous", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:event.mob_effect.bad_omen record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:event.mob_effect.bad_omen", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_95cbbf35_tower_animation_railgun_lv2_main_execute(ServerCommandSource source) {
        _m_95cbbf35_tower_animation_railgun_lv2_main_executeReturn(source);
    }

    public static int _m_95cbbf35_tower_animation_railgun_lv2_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 139 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 139, 139, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 139 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 139, 139, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 120 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 120, 120, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.1f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 120 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 120, 120, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.1f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 102 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.2
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 102, 102, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.2f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 102 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.2
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 102, 102, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.2f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 86 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.3
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 86, 86, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.3f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 86 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.3
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 86, 86, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.3f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 72 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.4
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 72, 72, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.4f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 72 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.4
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 72, 72, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.4f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 60 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 60, 60, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 60 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 60, 60, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 50 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.6
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 50, 50, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.6f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 50 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.6
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 50, 50, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.6f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 42 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.7
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 42, 42, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.7f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 42 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.7
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 42, 42, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.7f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 36 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.8
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 36, 36, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.8f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 36 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.8
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 36, 36, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.8f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 32 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 1.9
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 32, 32, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.9f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 32 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 1.9
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 32, 32, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.9f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:block.trial_spawner.about_to_spawn_item record @a ~ ~ ~ 1 2.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.about_to_spawn_item", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 2.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches ..30 run playsound minecraft:entity.ender_dragon.shoot record @a ~ ~ ~ 0.2 2.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", Integer.MIN_VALUE, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 2.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:block.trial_spawner.ambient_ominous record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:block.trial_spawner.ambient_ominous", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:event.mob_effect.bad_omen record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:event.mob_effect.bad_omen", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:entity.warden.sonic_charge record @a ~ ~ ~ 1 0.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.warden.sonic_charge", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:event.mob_effect.raid_omen record @a ~ ~ ~ 1 1.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:event.mob_effect.raid_omen", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.ender_dragon.hurt record @a ~ ~ ~ 1 0.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.hurt", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.warden.sonic_boom record @a ~ ~ ~ 1 2.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.warden.sonic_boom", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 2.0f);
            }
        }
        return 0;
    }

    public static void _m_c73be216_tower_animation_sniper_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_c73be216_tower_animation_sniper_main_executeReturn(source, macroArgs);
    }

    public static int _m_c73be216_tower_animation_sniper_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/3줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "animation/sniper/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function tower:animation/sniper/lv$(level)/main
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.blast record @a ~ ~ ~ 0.4 0.5
                // if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                //     for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                //         KfcGen.playSound(_ps, "minecraft:entity.firework_rocket.blast", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.5f);
                //     }
                // }
                // 
                // // execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:item.ominous_bottle.dispose record @a ~ ~ ~ 1 1.5
                // if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 1, false)) {
                //     for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                //         KfcGen.playSound(_ps, "minecraft:item.ominous_bottle.dispose", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.5f);
                //     }
                // }
        return 0;
    }

    public static void _m_ad6cf8b7_tower_animation_sniper_lv0_main_execute(ServerCommandSource source) {
        _m_ad6cf8b7_tower_animation_sniper_lv0_main_executeReturn(source);
    }

    public static int _m_ad6cf8b7_tower_animation_sniper_lv0_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 50 run playsound item.spyglass.use record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 50, 50, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.spyglass.use", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 50 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 50, 50, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_8c5da7ce_tower_animation_sniper_lv1_main_execute(ServerCommandSource source) {
        _m_8c5da7ce_tower_animation_sniper_lv1_main_executeReturn(source);
    }

    public static int _m_8c5da7ce_tower_animation_sniper_lv1_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 45 run playsound item.spyglass.use record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 45, 45, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.spyglass.use", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 45 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 45, 45, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_078e1168_tower_animation_sniper_lv2_main_execute(ServerCommandSource source) {
        _m_078e1168_tower_animation_sniper_lv2_main_executeReturn(source);
    }

    public static int _m_078e1168_tower_animation_sniper_lv2_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 40 run playsound item.spyglass.use record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 40, 40, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.spyglass.use", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 40 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 40, 40, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_c6bc44a2_tower_animation_sniper_lv3_main_execute(ServerCommandSource source) {
        _m_c6bc44a2_tower_animation_sniper_lv3_main_executeReturn(source);
    }

    public static int _m_c6bc44a2_tower_animation_sniper_lv3_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 35 run playsound item.spyglass.use record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 35, 35, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.spyglass.use", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 35 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 35, 35, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_527f2d8b_tower_animation_sniper_lv4_main_execute(ServerCommandSource source) {
        _m_527f2d8b_tower_animation_sniper_lv4_main_executeReturn(source);
    }

    public static int _m_527f2d8b_tower_animation_sniper_lv4_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound item.spyglass.use record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.spyglass.use", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 30 run playsound minecraft:item.armor.equip_chain record @a ~ ~ ~ 1 1
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 30, 30, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:item.armor.equip_chain", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_1c690409_tower_animation_stun_tower_main_execute(ServerCommandSource source) {
        _m_1c690409_tower_animation_stun_tower_main_executeReturn(source);
    }

    public static int _m_1c690409_tower_animation_stun_tower_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s[tag=tower.sound] tower.animation matches 1..3 run playsound entity.silverfish.ambient weather @a ~ ~ ~ 0.5 1.5
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 3, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "entity.silverfish.ambient", "weather", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 1.5f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1..3 run playsound entity.silverfish.ambient weather @a ~ ~ ~ 0.5 1.2
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 3, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "entity.silverfish.ambient", "weather", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 1.2f);
            }
        }

        // execute if score @s[tag=tower.sound] tower.animation matches 1..3 run playsound entity.silverfish.ambient weather @a ~ ~ ~ 0.5 2.0
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.sound")) ? executor : null), "tower.animation", 1, 3, false)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "entity.silverfish.ambient", "weather", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.5f, 2.0f);
            }
        }
        return 0;
    }

    public static void _m_3916b9ae_tower_armory_create_execute(ServerCommandSource source) {
        _m_3916b9ae_tower_armory_create_executeReturn(source);
    }

    public static int _m_3916b9ae_tower_armory_create_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute unless data entity @s SelectedItem.components.minecraft:custom_data.Tower_Status run tellraw @s {"text":"주 손에 타워 아이템을 들어야 합니다.","color":"red"}
        if (!(KfcGen.entityHasPath(executor, "SelectedItem.components.minecraft:custom_data.Tower_Status"))) {
            if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{\"text\":\"주 손에 타워 아이템을 들어야 합니다.\",\"color\":\"red\"}");
        }

        // execute unless data entity @s SelectedItem.components.minecraft:custom_data.Tower_Status run return fail
        if (!(KfcGen.entityHasPath(executor, "SelectedItem.components.minecraft:custom_data.Tower_Status"))) {
            return 0;
        }

        // execute anchored eyes positioned ^ ^ ^3 rotated as @s positioned ~ ~-1.62 ~ rotated ~ 0 run function tower:armory/create_place
        {
            ServerCommandSource kfcSrc2 = KfcGen.anchorEyes(source);
            ServerCommandSource kfcSrc3 = kfcSrc2.withPosition(KfcGen.localOffset(kfcSrc2.getPosition(), kfcSrc2.getRotation(), 0.0, 0.0, 3.0));
            ServerCommandSource kfcSrc4 = (executor != null ? kfcSrc3.withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : null);
            ServerCommandSource kfcSrc5 = (kfcSrc4 == null ? null : kfcSrc4.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc4.getPosition().x, (kfcSrc4.getPosition().y + -1.62), kfcSrc4.getPosition().z)));
            ServerCommandSource kfcSrc6 = (kfcSrc5 == null ? null : kfcSrc5.withRotation(new net.minecraft.util.math.Vec2f(0.0f, kfcSrc5.getRotation().y)));
            if (kfcSrc6 != null) {
                // -> tower:armory/create_place
                tdpack.buckets.Bucket7._m_7b777796_tower_armory_create_place_execute(kfcSrc6);
            }
        }

        // tellraw @s {"text":"무기고를 소환했습니다.","color":"green"}
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{\"text\":\"무기고를 소환했습니다.\",\"color\":\"green\"}");
        return 0;
    }

    public static void _m_7b777796_tower_armory_create_place_execute(ServerCommandSource source) {
        _m_7b777796_tower_armory_create_place_executeReturn(source);
    }

    public static int _m_7b777796_tower_armory_create_place_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // kill @e[type=item_display,tag=armory-tower,distance=..0.1]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"armory-tower"}, new String[0], -1, 0.1)) {
            KfcGen.killEntity(_k);
        }

        // setblock ~ ~ ~ minecraft:barrel[facing=up] destroy
        KfcGen.setBlock(source.getWorld(), net.minecraft.util.math.BlockPos.ofFloored(new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z)), "minecraft:barrel[facing=up]", "destroy");

        // item replace block ~ ~ ~ container.0 from entity @s weapon.mainhand
        { net.minecraft.entity.Entity _isrc = executor; if (_isrc != null) KfcGen.itemReplaceBlockFromEntity(source.getWorld(), net.minecraft.util.math.BlockPos.ofFloored(new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z)), "container.0", _isrc, "weapon.mainhand"); }

        // summon item_display ~ ~3 ~ {Tags:["armory-tower"],billboard:"fixed",item_display:"ground",view_range:0.8f}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 3.0), source.getPosition().z); KfcGen.summon(ctx.world, "item_display", _sp.x, _sp.y, _sp.z, "{Tags:[\"armory-tower\"],billboard:\"fixed\",item_display:\"ground\",view_range:0.8f}"); }

        // data modify entity @n[type=item_display,tag=armory-tower,distance=..0.1,sort=nearest,limit=1] item set from entity @s SelectedItem
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "SelectedItem"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"armory-tower"}, new String[0], -1, 0.1), "item", _v); }
        return 0;
    }

    public static void _m_00810e73_tower_armory_load_execute(ServerCommandSource source) {
        _m_00810e73_tower_armory_load_executeReturn(source);
    }

    public static int _m_00810e73_tower_armory_load_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard objectives add amory-time dummy
        KfcGen.ensureObjective(sb, "amory-time", "dummy");
        return 0;
    }

    public static void _m_fc585490_tower_armory_main_execute(ServerCommandSource source) {
        _m_fc585490_tower_armory_main_executeReturn(source);
    }

    public static int _m_fc585490_tower_armory_main_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute at @a run tag @e[distance=..7,tag=armory-tower,type=item_display] add armory-show
        for (net.minecraft.entity.Entity _atE1 : ctx.allPlayers) {
            ServerCommandSource _atSrc1 = source.withPosition(_atE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_atE1.getPitch(), _atE1.getYaw()));
            for (net.minecraft.entity.Entity _t : KfcGen.allEntities(ctx, _atSrc1.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"armory-tower"}, new String[0], -1, 7)) {
                _t.addCommandTag("armory-show");
            }
        }

        // execute as @e[tag=armory-show,type=item_display] at @s run function tower:armory/summon/main
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("armory-show"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc8 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:armory/summon/main
            tdpack.buckets.Bucket7._m_8d823dd4_tower_armory_summon_main_execute(kfcSrc8);
        }

        // execute as @e[tag=armory-show,type=item_display] at @s unless entity @a[distance=..7] run function tower:armory/summon/remove
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("armory-show"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc9 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(!(KfcGen.anyPlayer(ctx, kfcSrc9.getPosition(), new String[0], new String[0], -1, 7)))) continue;
            // -> tower:armory/summon/remove
            tdpack.buckets.Bucket7._m_29e94599_tower_armory_summon_remove_execute(kfcSrc9);
        }
        return 0;
    }

    public static void _m_91cf3014_tower_armory_interact_get_item_macro_execute(ServerCommandSource source) {
        _m_91cf3014_tower_armory_interact_get_item_macro_executeReturn(source);
    }

    public static int _m_91cf3014_tower_armory_interact_get_item_macro_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute at @n[tag=armory-tower] run item replace entity @s weapon.mainhand from block ~ ~-3 ~ container.0
        {
            net.minecraft.entity.Entity _atE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"armory-tower"}, new String[0], -1, -1, _ee -> (true));
            ServerCommandSource kfcSrc7 = (_atE1 != null ? source.withPosition(_atE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_atE1.getPitch(), _atE1.getYaw())) : null);
            if (kfcSrc7 != null) {
                if (executor != null) KfcGen.itemReplaceFromBlock(executor, "weapon.mainhand", kfcSrc7.getWorld(), net.minecraft.util.math.BlockPos.ofFloored(new net.minecraft.util.math.Vec3d(kfcSrc7.getPosition().x, (kfcSrc7.getPosition().y + -3.0), kfcSrc7.getPosition().z)), "container.0");
            }
        }
        return 0;
    }

    public static void _m_0bd11738_tower_armory_interact_is_bought_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_0bd11738_tower_armory_interact_is_bought_executeReturn(source, macroArgs);
    }

    public static int _m_0bd11738_tower_armory_interact_is_bought_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        // $execute if entity @s[tag=isbought.$(model)] run return 1
        if ((executor != null && executor.getCommandTags().contains("isbought." + macroArgs.get("model")))) {
            return 1;
        }

        // return 0
        return 0;
    }

    public static void _m_eeff5c91_tower_armory_summon_interaction_execute(ServerCommandSource source) {
        _m_eeff5c91_tower_armory_summon_interaction_executeReturn(source);
    }

    public static int _m_eeff5c91_tower_armory_summon_interaction_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute on attacker run function tower:armory/interact/get-item-macro with entity @n[tag=armory-tower] item
        { ServerCommandSource _on1 = KfcGen.onAttacker(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            // -> tower:armory/interact/get-item-macro
            tdpack.buckets.Bucket7._m_91cf3014_tower_armory_interact_get_item_macro_execute(_on1);
          } }

        // data remove entity @s interaction
        if (executor != null) KfcGen.entityRemovePath(executor, "interaction");

        // data remove entity @s attack
        if (executor != null) KfcGen.entityRemovePath(executor, "attack");
        return 0;
    }

    public static void _m_8d823dd4_tower_armory_summon_main_execute(ServerCommandSource source) {
        _m_8d823dd4_tower_armory_summon_main_executeReturn(source);
    }

    public static int _m_8d823dd4_tower_armory_summon_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players add @s amory-time 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "amory-time", 1);

        // execute if entity @a[distance=..4] if score @s amory-time matches 10.. run tag @s add show-transform
        if (KfcGen.anyPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, 4)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 10, Integer.MAX_VALUE)) {
                if (executor != null) executor.addCommandTag("show-transform");
            }
        }

        // execute if score @s amory-time matches 10.. run scoreboard players remove @s[tag=!show-transform] amory-time 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 10, Integer.MAX_VALUE)) {
            if (executor != null && ((executor != null && !executor.getCommandTags().contains("show-transform")))) KfcGen.addScore(sb, executor.getNameForScoreboard(), "amory-time", -(1));
        }

        // execute store result score #temp game.return as @p run function tower:armory/interact/is-bought with entity @n[type=minecraft:item_display] item.components.minecraft:custom_data.Tower_Status
        { net.minecraft.entity.Entity _se = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "game.return", tdpack.buckets.Bucket7._m_0bd11738_tower_armory_interact_is_bought_executeReturn(source, KfcGen.entityMacroArgs(KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType[]{EntityType.ITEM_DISPLAY}, new String[0], new String[0], -1, -1), "item.components.minecraft:custom_data.Tower_Status"))); }

        // execute if score @s amory-time matches 1 run data modify entity @s item set from block ~ ~-3 ~ Items[0]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 1, 1)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetBlock(ctx.world, new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + -3.0), source.getPosition().z), "Items[0]"); if (_v != null) KfcGen.nbtSetEntity(executor, "item", _v); }
        }

        // execute if score #temp game.return matches 1 if score @s amory-time matches 1 run data merge entity @s {start_interpolation:0,interpolation_duration:5,billboard:"center",transformation:{scale:[0f,0f,0f],right_rotation:[0f,1f,0f,0f],translation:[0f,0f,0f]}}
        if (KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 1, 1)) {
                if (executor != null) KfcGen.entityMergeSnbt(executor, "{start_interpolation:0,interpolation_duration:5,billboard:\"center\",transformation:{scale:[0f,0f,0f],right_rotation:[0f,1f,0f,0f],translation:[0f,0f,0f]}}");
            }
        }

        // execute if score #temp game.return matches 1 if score @s amory-time matches 4 positioned ~ ~-0.5 ~ run kill @e[tag=armory-text,distance=..0.01]
        {
            ServerCommandSource kfcSrc10 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + -0.5), source.getPosition().z));
            if (KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1)) {
                if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 4, 4)) {
                    for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, kfcSrc10.getPosition(), new String[]{"armory-text"}, new String[0], -1, 0.01)) {
                        KfcGen.killEntity(_k);
                    }
                }
            }
        }

        // execute if score #temp game.return matches 1 if score @s amory-time matches 4 run function tower:armory/summon/model with entity @s item.components.minecraft:custom_data
        if (KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1)) {
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 4, 4)) {
                // -> tower:armory/summon/model
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "armory/summon/model"), KfcGen.entityMacroArgs(executor, "item.components.minecraft:custom_data"));
            }
        }

        // execute if score @s amory-time matches 1 run summon interaction ~ ~-0.5 ~ {width:2.5f,height:1.5f,Tags:["armory-interaction"]}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 1, 1)) {
            { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + -0.5), source.getPosition().z); KfcGen.summon(ctx.world, "interaction", _sp.x, _sp.y, _sp.z, "{width:2.5f,height:1.5f,Tags:[\"armory-interaction\"]}"); }
        }

        // execute if score @s amory-time matches 1 positioned ~ ~-0.5 ~ run summon text_display ~ ~ ~ {view_range:0.2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[1f,0.1f,1.5f],scale:[0.5f,0.5f,0.5f]},text:{"bold":true,"color":"yellow",translate:"클릭해서 획득"}}
        {
            ServerCommandSource kfcSrc11 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + -0.5), source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 1, 1)) {
                { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(kfcSrc11.getPosition().x, kfcSrc11.getPosition().y, kfcSrc11.getPosition().z); KfcGen.summon(ctx.world, "text_display", _sp.x, _sp.y, _sp.z, "{view_range:0.2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[1f,0.1f,1.5f],scale:[0.5f,0.5f,0.5f]},text:{\"bold\":true,\"color\":\"yellow\",translate:\"클릭해서 획득\"}}"); }
            }
        }

        // execute as @e[tag=armory-interaction,type=interaction] at @s run function tower:armory/summon/interaction
        for (Entity e : ctx.world.getEntitiesByType(EntityType.INTERACTION,
                en -> en.getCommandTags().contains("armory-interaction"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc12 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:armory/summon/interaction
            tdpack.buckets.Bucket7._m_eeff5c91_tower_armory_summon_interaction_execute(kfcSrc12);
        }

        // execute if score @s amory-time matches 4 positioned ~ ~-0.5 ~ run summon text_display ~ ~ ~ {Tags:["armory-text"],view_range:0.2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[1f,0.25f,1.5f],scale:[0.75f,0.75f,0.75f]},background:-16777216}
        {
            ServerCommandSource kfcSrc13 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + -0.5), source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 4, 4)) {
                { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(kfcSrc13.getPosition().x, kfcSrc13.getPosition().y, kfcSrc13.getPosition().z); KfcGen.summon(ctx.world, "text_display", _sp.x, _sp.y, _sp.z, "{Tags:[\"armory-text\"],view_range:0.2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[1f,0.25f,1.5f],scale:[0.75f,0.75f,0.75f]},background:-16777216}"); }
            }
        }

        // execute if score @s amory-time matches 4 run data modify entity @n[tag=armory-text] text set from entity @s item.components.minecraft:item_name
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 4, 4)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "item.components.minecraft:item_name"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"armory-text"}, new String[0], -1, -1, _ee -> (true)), "text", _v); }
        }

        // execute if score @s amory-time matches 4 positioned ~ ~-0.5 ~ rotated ~ ~ as @e[distance=..0.001,type=text_display] run rotate @s ~ ~
        {
            ServerCommandSource kfcSrcPre16 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + -0.5), source.getPosition().z));
            ServerCommandSource kfcSrcPre17 = kfcSrcPre16.withRotation(new net.minecraft.util.math.Vec2f(kfcSrcPre16.getRotation().x, kfcSrcPre16.getRotation().y));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "amory-time", 4, 4)) {
                for (Entity e : ctx.world.getEntitiesByType(EntityType.TEXT_DISPLAY,
                        en -> KfcGen.inRange(kfcSrcPre17.getPosition(), en, -1, 0.001))) {
                    ServerCommandSource es = kfcSrcPre17.withEntity(e);
                    if (e != null) KfcGen.rotateTo(e, es.getRotation().y, es.getRotation().x);
                }
            }
        }
        return 0;
    }

    public static void _m_29e94599_tower_armory_summon_remove_execute(ServerCommandSource source) {
        _m_29e94599_tower_armory_summon_remove_executeReturn(source);
    }

    public static int _m_29e94599_tower_armory_summon_remove_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players reset @s amory-time
        if (executor != null) KfcGen.resetScore(sb, executor.getNameForScoreboard(), "amory-time");

        // data merge entity @s {start_interpolation:0,interpolation_duration:5,transformation:{scale:[1.25f,1.25f,1.25f],translation:[0f,0.25f,0f]}}
        if (executor != null) KfcGen.entityMergeSnbt(executor, "{start_interpolation:0,interpolation_duration:5,transformation:{scale:[1.25f,1.25f,1.25f],translation:[0f,0.25f,0f]}}");

        // execute positioned ~ ~-0.5 ~ run kill @e[distance=..0.0001,tag=!armory-show,tag=!armory-text,type=!player]
        {
            ServerCommandSource kfcSrc18 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + -0.5), source.getPosition().z));
            for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, kfcSrc18.getPosition(), new String[0], new String[]{"armory-show", "armory-text"}, -1, 0.0001)) {
                if (_k.getType() == EntityType.PLAYER) continue;
                KfcGen.killEntity(_k);
            }
        }

        // tag @s remove armory-show
        if (executor != null) executor.removeCommandTag("armory-show");

        // tag @s remove armory-show-unsecurity
        if (executor != null) executor.removeCommandTag("armory-show-unsecurity");

        // tag @s remove show-transform
        if (executor != null) executor.removeCommandTag("show-transform");
        return 0;
    }

    public static void _m_169af078_tower_attack_decrese_countdown_execute(ServerCommandSource source) {
        _m_169af078_tower_attack_decrese_countdown_executeReturn(source);
    }

    public static int _m_169af078_tower_attack_decrese_countdown_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players remove @s tower.attack_countdown 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "tower.attack_countdown", -(1));

        // scoreboard players operation #temp tower.attack_countdown = @s tower.attack_countdown
        if (executor != null) KfcGen.opScore(sb, "#temp", "tower.attack_countdown", "=", executor.getNameForScoreboard(), "tower.attack_countdown");

        // execute on vehicle on passengers run scoreboard players operation @s tower.animation = #temp tower.attack_countdown
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if (_onEnt2 != null) KfcGen.opScore(sb, _onEnt2.getNameForScoreboard(), "tower.animation", "=", "#temp", "tower.attack_countdown");
            }
          } }
        return 0;
    }

    public static void _m_4f0d1a71_tower_attack_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_4f0d1a71_tower_attack_main_executeReturn(source, macroArgs);
    }

    public static int _m_4f0d1a71_tower_attack_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute as @n[tag=tower.data] if score @s tower.state.stun matches 1.. run return 0
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            if ((KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "tower.state.stun", 1, Integer.MAX_VALUE))) {
                return 0;
            }
        } }

        // data modify storage tower temp set from entity @n[tag=tower.data] data
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "data"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "temp", _v); }

        // execute store result storage tower temp.number int 1 run scoreboard players get @n[tag=tower.data] tower.number
        KfcGen.storagePutNumber(server, "tower", "temp.number", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "tower.number"), "int");

        // $execute positioned ~ -60 ~ if entity @n[tag=enemy.target,distance=..$(range)] run function tower:attack/targeting/main with storage tower temp
        {
            ServerCommandSource kfcSrc44 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            boolean _mcond45 = false; try { _mcond45 = (KfcGen.anyEntityAnyType(ctx, kfcSrc44.getPosition(), new String[]{"enemy.target"}, new String[0], -1, Double.parseDouble(macroArgs.get("range")))); } catch (NumberFormatException _nfe) {}
            if (_mcond45) {
                // -> tower:attack/targeting/main
                tdpack.buckets.Bucket8._m_259d798c_tower_attack_targeting_main_execute(kfcSrc44, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // $execute positioned ~ -60 ~ unless entity @n[tag=enemy.target,distance=..$(range)] unless score @s tower.attack_countdown = @s tower.attack_speed run function tower:attack/decrese_countdown
        {
            ServerCommandSource kfcSrc46 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            boolean _mcond47 = false; try { _mcond47 = (!(KfcGen.anyEntityAnyType(ctx, kfcSrc46.getPosition(), new String[]{"enemy.target"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))))); } catch (NumberFormatException _nfe) {}
            if (_mcond47) {
                if (!(KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.attack_countdown", "=", (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.attack_speed"))) {
                    // -> tower:attack/decrese_countdown
                    tdpack.buckets.Bucket7._m_169af078_tower_attack_decrese_countdown_execute(kfcSrc46);
                }
            }
        }

        // execute if score @s tower.attack_countdown matches ..0 run function tower:attack/fire/main with storage tower temp.Bullet
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.attack_countdown", Integer.MIN_VALUE, 0)) {
            // -> tower:attack/fire/main
            tdpack.buckets.Bucket7._m_ec60bc70_tower_attack_fire_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp.Bullet"));
        }
        return 0;
    }

    public static void _m_ec60bc70_tower_attack_fire_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_ec60bc70_tower_attack_fire_main_executeReturn(source, macroArgs);
    }

    public static int _m_ec60bc70_tower_attack_fire_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $execute if score @s[tag=tower.muzzle] tower.attack_countdown matches ..0 run function tower:attack/fire/$(type)/main with storage tower temp
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.muzzle")) ? executor : null), "tower.attack_countdown", Integer.MIN_VALUE, 0, false)) {
            // -> tower:attack/fire/macroArgs.get("type")/main
            switch (macroArgs.get("type")) {
                case "farm": tdpack.buckets.Bucket7._m_b59f3eba_tower_attack_fire_farm_main_execute(source); break;
                case "hitscan": tdpack.buckets.Bucket7._m_fb5f4e19_tower_attack_fire_hitscan_main_execute(source); break;
                case "projectile": tdpack.buckets.Bucket8._m_59cc4b80_tower_attack_fire_projectile_main_execute(source); break;
                case "sword": tdpack.buckets.Bucket8._m_1d8a2ab3_tower_attack_fire_sword_main_execute(source); break;
                default: break;
            }
        }

        // execute if score @s[tag=tower.muzzle] tower.attack_countdown matches ..0 run scoreboard players operation @s tower.attack_countdown = @s tower.attack_speed
        if (KfcGen.entityScoreMatches(sb, ((executor.getCommandTags().contains("tower.muzzle")) ? executor : null), "tower.attack_countdown", Integer.MIN_VALUE, 0, false)) {
            if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "tower.attack_countdown", "=", executor.getNameForScoreboard(), "tower.attack_speed");
        }

        // execute if score @s[tag=tower.muzzle,tag=farm] tower.attack_countdown = @s tower.attack_speed run scoreboard players remove @s tower.attack_countdown 1
        if (KfcGen.scoreCmp(sb, ((executor.getCommandTags().contains("tower.muzzle") && executor.getCommandTags().contains("farm")) ? executor : null), "tower.attack_countdown", "=", (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.attack_speed", false)) {
            if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "tower.attack_countdown", -(1));
        }

        // scoreboard players operation #temp tower.attack_countdown = @s tower.attack_countdown
        if (executor != null) KfcGen.opScore(sb, "#temp", "tower.attack_countdown", "=", executor.getNameForScoreboard(), "tower.attack_countdown");

        // execute on vehicle on passengers run scoreboard players operation @s tower.animation = #temp tower.attack_countdown
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if (_onEnt2 != null) KfcGen.opScore(sb, _onEnt2.getNameForScoreboard(), "tower.animation", "=", "#temp", "tower.attack_countdown");
            }
          } }
        return 0;
    }

    public static void _m_b59f3eba_tower_attack_fire_farm_main_execute(ServerCommandSource source) {
        _m_b59f3eba_tower_attack_fire_farm_main_executeReturn(source);
    }

    public static int _m_b59f3eba_tower_attack_fire_farm_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score #temp money run data get storage tower temp.attack
        KfcGen.setScore(sb, "#temp", "money", (int)(KfcGen.storageGetDouble(server, "tower", "temp.attack")));

        // scoreboard players operation @a money += #temp money
        { 
        for (net.minecraft.server.network.ServerPlayerEntity _od : ctx.allPlayers) {
            KfcGen.opScore(sb, _od.getNameForScoreboard(), "money", "+=", "#temp", "money");
        }
        }

        // execute at @s run playsound minecraft:entity.villager.work_farmer master @a ~ ~ ~ 1 1
        {
            ServerCommandSource kfcSrc19 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.villager.work_farmer", "master", new net.minecraft.util.math.Vec3d(kfcSrc19.getPosition().x, kfcSrc19.getPosition().y, kfcSrc19.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc19.getPosition().x, kfcSrc19.getPosition().y, kfcSrc19.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc19.getPosition().x, kfcSrc19.getPosition().y, kfcSrc19.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_fb5f4e19_tower_attack_fire_hitscan_main_execute(ServerCommandSource source) {
        _m_fb5f4e19_tower_attack_fire_hitscan_main_executeReturn(source);
    }

    public static int _m_fb5f4e19_tower_attack_fire_hitscan_main_executeReturn(ServerCommandSource source) {
        
        // function tower:attack/fire/hitscan/hit-marker/spawn/main with storage tower temp.Bullet
        // -> tower:attack/fire/hitscan/hit-marker/spawn/main
        tdpack.buckets.Bucket8._m_19a7cd59_tower_attack_fire_hitscan_hit_marker_spawn_main_execute(source);
        return 0;
    }

    public static void _m_b37341ac_tower_attack_fire_hitscan_hit_marker_animation_coilgun_execute(ServerCommandSource source) {
        _m_b37341ac_tower_attack_fire_hitscan_hit_marker_animation_coilgun_executeReturn(source);
    }

    public static int _m_b37341ac_tower_attack_fire_hitscan_hit_marker_animation_coilgun_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // particle dust{color:[0.53f,0.81f,0.92f],scale:3.5} ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 0.53f, 0.81f, 0.92f, 3.5f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        return 0;
    }

    public static void _m_a4bd5984_tower_attack_fire_hitscan_hit_marker_animation_flame_tower_execute(ServerCommandSource source) {
        _m_a4bd5984_tower_attack_fire_hitscan_hit_marker_animation_flame_tower_executeReturn(source);
    }

    public static int _m_a4bd5984_tower_attack_fire_hitscan_hit_marker_animation_flame_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // particle dust{color:[1.0f,0.5f,0.0f],scale:1} ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 1.0f, 0.5f, 0.0f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        return 0;
    }

    public static void _m_6b440c61_tower_attack_fire_hitscan_hit_marker_animation_freeze_tower_execute(ServerCommandSource source) {
        _m_6b440c61_tower_attack_fire_hitscan_hit_marker_animation_freeze_tower_executeReturn(source);
    }

    public static int _m_6b440c61_tower_attack_fire_hitscan_hit_marker_animation_freeze_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // particle dust{color:[0.5f,1.0f,1.0f],scale:1} ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 0.5f, 1.0f, 1.0f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }

        // particle snowflake ~ ~ ~ 0.2 0.2 0.2 0 5 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "snowflake", _pp.x, _pp.y, _pp.z, 0.2, 0.2, 0.2, 0, (int)(5), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        return 0;
    }

    public static void _m_2f845e44_tower_attack_fire_hitscan_hit_marker_animation_gatling_execute(ServerCommandSource source) {
        _m_2f845e44_tower_attack_fire_hitscan_hit_marker_animation_gatling_executeReturn(source);
    }

    public static int _m_2f845e44_tower_attack_fire_hitscan_hit_marker_animation_gatling_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // particle dust{color:[0.8f,0.8f,0.8f],scale:1} ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 0.8f, 0.8f, 0.8f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        return 0;
    }

    public static void _m_91698d79_tower_attack_fire_hitscan_hit_marker_animation_machine_gun_execute(ServerCommandSource source) {
        _m_91698d79_tower_attack_fire_hitscan_hit_marker_animation_machine_gun_executeReturn(source);
    }

    public static int _m_91698d79_tower_attack_fire_hitscan_hit_marker_animation_machine_gun_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // particle dust{color:[0.2f,0.2f,0.2f],scale:1} ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 0.2f, 0.2f, 0.2f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        return 0;
    }

    public static void _m_8689bbb7_tower_attack_fire_hitscan_hit_marker_animation_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_8689bbb7_tower_attack_fire_hitscan_hit_marker_animation_main_executeReturn(source, macroArgs);
    }

    public static int _m_8689bbb7_tower_attack_fire_hitscan_hit_marker_animation_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // $function tower:attack/fire/hitscan/hit-marker/animation/$(model)
        // -> tower:attack/fire/hitscan/hit-marker/animation/macroArgs.get("model")
        switch (macroArgs.get("model")) {
            case "coilgun": tdpack.buckets.Bucket7._m_b37341ac_tower_attack_fire_hitscan_hit_marker_animation_coilgun_execute(source); break;
            case "flame-tower": tdpack.buckets.Bucket7._m_a4bd5984_tower_attack_fire_hitscan_hit_marker_animation_flame_tower_execute(source); break;
            case "freeze-tower": tdpack.buckets.Bucket7._m_6b440c61_tower_attack_fire_hitscan_hit_marker_animation_freeze_tower_execute(source); break;
            case "gatling": tdpack.buckets.Bucket7._m_2f845e44_tower_attack_fire_hitscan_hit_marker_animation_gatling_execute(source); break;
            case "machine-gun": tdpack.buckets.Bucket7._m_91698d79_tower_attack_fire_hitscan_hit_marker_animation_machine_gun_execute(source); break;
            case "railgun": tdpack.buckets.Bucket7._m_099925f7_tower_attack_fire_hitscan_hit_marker_animation_railgun_execute(source); break;
            case "sniper": tdpack.buckets.Bucket7._m_d4ea1a5b_tower_attack_fire_hitscan_hit_marker_animation_sniper_execute(source); break;
            case "stun-tower": tdpack.buckets.Bucket7._m_0f2eddcf_tower_attack_fire_hitscan_hit_marker_animation_stun_tower_execute(source); break;
            default: break;
        }
        return 0;
    }

    public static void _m_099925f7_tower_attack_fire_hitscan_hit_marker_animation_railgun_execute(ServerCommandSource source) {
        _m_099925f7_tower_attack_fire_hitscan_hit_marker_animation_railgun_executeReturn(source);
    }

    public static int _m_099925f7_tower_attack_fire_hitscan_hit_marker_animation_railgun_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // particle dust{color:[0.1f,0.1f,1.0f],scale:1} ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 0.1f, 0.1f, 1.0f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        return 0;
    }

    public static void _m_d4ea1a5b_tower_attack_fire_hitscan_hit_marker_animation_sniper_execute(ServerCommandSource source) {
        _m_d4ea1a5b_tower_attack_fire_hitscan_hit_marker_animation_sniper_executeReturn(source);
    }

    public static int _m_d4ea1a5b_tower_attack_fire_hitscan_hit_marker_animation_sniper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // particle dust{color:[0.5f,0.5f,0.5f],scale:1} ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 0.5f, 0.5f, 0.5f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        return 0;
    }

    public static void _m_0f2eddcf_tower_attack_fire_hitscan_hit_marker_animation_stun_tower_execute(ServerCommandSource source) {
        _m_0f2eddcf_tower_attack_fire_hitscan_hit_marker_animation_stun_tower_executeReturn(source);
    }

    public static int _m_0f2eddcf_tower_attack_fire_hitscan_hit_marker_animation_stun_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // particle electric_spark ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "electric_spark", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        return 0;
    }

    public static void _m_ff7b6ff4_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_ff7b6ff4_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_executeReturn(source, macroArgs);
    }

    public static int _m_ff7b6ff4_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $execute as @n[tag=target_$(number),distance=..5] at @s if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-macro run scoreboard players set #temp game.return 41
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number")}, new String[0], -1, 5, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc156 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (((tdpack.buckets.Bucket8._m_f6f2a84c_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_macr_executeReturn(kfcSrc156) != 0))) {
                KfcGen.setScore(sb, "#temp", "game.return", 41);
            }
        } }
        return 0;
    }

    public static void _m_03ddee11_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_execute(ServerCommandSource source) {
        _m_03ddee11_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source);
    }

    public static int _m_03ddee11_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.77,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.77,dz=0] run return 1
        {
            ServerCommandSource kfcSrc20 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc21 = kfcSrc20.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc20.getPosition().x + -0.4), kfcSrc20.getPosition().y, (kfcSrc20.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_breeze"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc20.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.77, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc21.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.77, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.35 ~ ~-0.35 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] positioned ~-0.3 ~-0.5 ~-0.3 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc22 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.35), source.getPosition().y, (source.getPosition().z + -0.35)));
            ServerCommandSource kfcSrc23 = kfcSrc22.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc22.getPosition().x + -0.3), (kfcSrc22.getPosition().y + -0.5), (kfcSrc22.getPosition().z + -0.3)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_cave_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc22.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc23.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.7,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.7,dz=0] run return 1
        {
            ServerCommandSource kfcSrc24 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc25 = kfcSrc24.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc24.getPosition().x + -0.4), kfcSrc24.getPosition().y, (kfcSrc24.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_creeper"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc24.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.7, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc25.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.7, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.95,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.95,dz=0] run return 1
        {
            ServerCommandSource kfcSrc26 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc27 = kfcSrc26.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc26.getPosition().x + -0.4), kfcSrc26.getPosition().y, (kfcSrc26.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_drowned"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc26.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.95, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc27.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.95, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.9,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.9,dz=0] run return 1
        {
            ServerCommandSource kfcSrc28 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc29 = kfcSrc28.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc28.getPosition().x + -0.4), kfcSrc28.getPosition().y, (kfcSrc28.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_enderman"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc28.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.9, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc29.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.9, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.2 ~ ~-0.2 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] positioned ~-0.6 ~-0.7 ~-0.6 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc30 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.2), source.getPosition().y, (source.getPosition().z + -0.2)));
            ServerCommandSource kfcSrc31 = kfcSrc30.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc30.getPosition().x + -0.6), (kfcSrc30.getPosition().y + -0.7), (kfcSrc30.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_endermite"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc30.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc31.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.45 ~ ~-0.45 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] positioned ~-0.1 ~-0.5 ~-0.1 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc32 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), source.getPosition().y, (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc33 = kfcSrc32.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc32.getPosition().x + -0.1), (kfcSrc32.getPosition().y + -0.5), (kfcSrc32.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_phantom"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc32.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc33.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.2 ~ ~-0.2 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] positioned ~-0.6 ~-0.5 ~-0.6 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc34 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.2), source.getPosition().y, (source.getPosition().z + -0.2)));
            ServerCommandSource kfcSrc35 = kfcSrc34.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc34.getPosition().x + -0.6), (kfcSrc34.getPosition().y + -0.5), (kfcSrc34.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_rabbit"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc34.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc35.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-0.975 ~ ~-0.975 if entity @n[type=marker,tag=hitscan-marker,dx=0.950,dy=1.2,dz=0.950] run return 1
        {
            ServerCommandSource kfcSrc36 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.975), source.getPosition().y, (source.getPosition().z + -0.975)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_ravager"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc36.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.950, 1.2, 0.950)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.99,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.99,dz=0] run return 1
        {
            ServerCommandSource kfcSrc37 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc38 = kfcSrc37.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc37.getPosition().x + -0.4), kfcSrc37.getPosition().y, (kfcSrc37.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc37.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.99, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc38.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.99, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-0.69824 ~ ~-0.69824 if entity @n[type=marker,tag=hitscan-marker,dx=0.19824,dy=0.6,dz=0.19824] run return 1
        {
            ServerCommandSource kfcSrc39 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.69824), source.getPosition().y, (source.getPosition().z + -0.69824)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton_horse"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc39.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.19824, 0.6, 0.19824)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-0.7 ~-0.3 ~-0.7 if entity @n[type=marker,tag=hitscan-marker,dx=0.4,dy=0,dz=0.4] run return 1
        {
            ServerCommandSource kfcSrc40 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.3), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc40.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.4, 0, 0.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.2 ~ ~-0.2 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] positioned ~-0.6 ~-0.2 ~-0.6 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc41 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.2), source.getPosition().y, (source.getPosition().z + -0.2)));
            ServerCommandSource kfcSrc42 = kfcSrc41.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc41.getPosition().x + -0.6), (kfcSrc41.getPosition().y + -0.2), (kfcSrc41.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_vex"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc41.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc42.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.45 ~ ~-0.45 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.9,dz=0] positioned ~-0.1 ~ ~-0.1 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.9,dz=0] run return 1
        {
            ServerCommandSource kfcSrc43 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), source.getPosition().y, (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc44 = kfcSrc43.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc43.getPosition().x + -0.1), kfcSrc43.getPosition().y, (kfcSrc43.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_warden"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc43.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.9, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc44.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.9, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.35 ~ ~-0.35 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.4,dz=0] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.4,dz=0] run return 1
        {
            ServerCommandSource kfcSrc45 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.35), source.getPosition().y, (source.getPosition().z + -0.35)));
            ServerCommandSource kfcSrc46 = kfcSrc45.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc45.getPosition().x + -0.3), kfcSrc45.getPosition().y, (kfcSrc45.getPosition().z + -0.3)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_wither_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc45.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.4, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc46.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.4, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.15 ~ ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] positioned ~-0.7 ~-0.025 ~-0.7 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc47 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.15), source.getPosition().y, (source.getPosition().z + -0.15)));
            ServerCommandSource kfcSrc48 = kfcSrc47.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc47.getPosition().x + -0.7), (kfcSrc47.getPosition().y + -0.025), (kfcSrc47.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_baby"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc47.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc48.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.33 ~ ~-0.33 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.145,dz=0] positioned ~-0.34 ~ ~-0.34 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.145,dz=0] run return 1
        {
            ServerCommandSource kfcSrc49 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.33), source.getPosition().y, (source.getPosition().z + -0.33)));
            ServerCommandSource kfcSrc50 = kfcSrc49.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc49.getPosition().x + -0.34), kfcSrc49.getPosition().y, (kfcSrc49.getPosition().z + -0.34)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_1"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc49.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.145, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc50.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.145, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.45 ~ ~-0.45 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.925,dz=0] positioned ~-0.1 ~ ~-0.1 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.925,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc51 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), source.getPosition().y, (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc52 = kfcSrc51.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc51.getPosition().x + -0.1), kfcSrc51.getPosition().y, (kfcSrc51.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_5"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc51.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.925, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc52.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.925, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc52) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-0.54 ~ ~-0.54 if entity @n[type=marker,tag=hitscan-marker,dx=0.08,dy=2.51,dz=0.08] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc53 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.54), source.getPosition().y, (source.getPosition().z + -0.54)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_8"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc53.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.08, 2.51, 0.08)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc53) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~-0.6 ~ ~-0.6 if entity @n[type=marker,tag=hitscan-marker,dx=0.2,dy=2.9,dz=0.2] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc54 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.6), source.getPosition().y, (source.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_2"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc54.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.2, 2.9, 0.2)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc54) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-0.7 ~ ~-0.7 if entity @n[type=marker,tag=hitscan-marker,dx=0.4,dy=0.4,dz=0.4] run return 1
        {
            ServerCommandSource kfcSrc55 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), source.getPosition().y, (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zoglin"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc55.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.4, 0.4, 0.4)) {
                    return 1;
                }
            }
        }

        // return 0
        return 0;
    }

    public static void _m_91dff13c_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_execute(ServerCommandSource source) {
        _m_91dff13c_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source);
    }

    public static int _m_91dff13c_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.02,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.77,dz=0] run return 1
        {
            ServerCommandSource kfcSrc56 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.425), (source.getPosition().y + 0.0), (source.getPosition().z + -0.425)));
            ServerCommandSource kfcSrc57 = kfcSrc56.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc56.getPosition().x + -0.15), (kfcSrc56.getPosition().y + 0.0), (kfcSrc56.getPosition().z + -0.15)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_breeze"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc56.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.02, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc57.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.77, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.475 ~-0.5 ~-0.475 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.05 ~0 ~-0.05 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc58 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.475), (source.getPosition().y + -0.5), (source.getPosition().z + -0.475)));
            ServerCommandSource kfcSrc59 = kfcSrc58.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc58.getPosition().x + -0.05), (kfcSrc58.getPosition().y + 0.0), (kfcSrc58.getPosition().z + -0.05)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_cave_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc58.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.25, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc59.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.95,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.7,dz=0] run return 1
        {
            ServerCommandSource kfcSrc60 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.425), (source.getPosition().y + 0.0), (source.getPosition().z + -0.425)));
            ServerCommandSource kfcSrc61 = kfcSrc60.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc60.getPosition().x + -0.15), (kfcSrc60.getPosition().y + 0.0), (kfcSrc60.getPosition().z + -0.15)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_creeper"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc60.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.95, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc61.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.7, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.2,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.95,dz=0] run return 1
        {
            ServerCommandSource kfcSrc62 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.425), (source.getPosition().y + 0.0), (source.getPosition().z + -0.425)));
            ServerCommandSource kfcSrc63 = kfcSrc62.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc62.getPosition().x + -0.15), (kfcSrc62.getPosition().y + 0.0), (kfcSrc62.getPosition().z + -0.15)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_drowned"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc62.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.2, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc63.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.95, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=2.15,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.9,dz=0] run return 1
        {
            ServerCommandSource kfcSrc64 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.425), (source.getPosition().y + 0.0), (source.getPosition().z + -0.425)));
            ServerCommandSource kfcSrc65 = kfcSrc64.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc64.getPosition().x + -0.15), (kfcSrc64.getPosition().y + 0.0), (kfcSrc64.getPosition().z + -0.15)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_enderman"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc64.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 2.15, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc65.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.9, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.325 ~-0.7 ~-0.325 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.35 ~0 ~-0.35 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc66 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.325), (source.getPosition().y + -0.7), (source.getPosition().z + -0.325)));
            ServerCommandSource kfcSrc67 = kfcSrc66.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc66.getPosition().x + -0.35), (kfcSrc66.getPosition().y + 0.0), (kfcSrc66.getPosition().z + -0.35)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_endermite"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc66.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.25, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc67.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.575 ~-0.5 ~-0.575 if entity @n[type=marker,tag=hitscan-marker,dx=1.15,dy=0.75,dz=1.15] run return 1
        {
            ServerCommandSource kfcSrc68 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.575), (source.getPosition().y + -0.5), (source.getPosition().z + -0.575)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_phantom"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc68.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.15, 0.75, 1.15)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.325 ~-0.5 ~-0.325 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.35 ~0 ~-0.35 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc69 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.325), (source.getPosition().y + -0.5), (source.getPosition().z + -0.325)));
            ServerCommandSource kfcSrc70 = kfcSrc69.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc69.getPosition().x + -0.35), (kfcSrc69.getPosition().y + 0.0), (kfcSrc69.getPosition().z + -0.35)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_rabbit"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc69.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.25, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc70.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-1.1 ~0 ~-1.1 if entity @n[type=marker,tag=hitscan-marker,dx=2.2,dy=2.45,dz=2.2] run return 1
        {
            ServerCommandSource kfcSrc71 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.1), (source.getPosition().y + 0.0), (source.getPosition().z + -1.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_ravager"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc71.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.2, 2.45, 2.2)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.24,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.99,dz=0] run return 1
        {
            ServerCommandSource kfcSrc72 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.425), (source.getPosition().y + 0.0), (source.getPosition().z + -0.425)));
            ServerCommandSource kfcSrc73 = kfcSrc72.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc72.getPosition().x + -0.15), (kfcSrc72.getPosition().y + 0.0), (kfcSrc72.getPosition().z + -0.15)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc72.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.24, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc73.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.99, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-0.82324 ~0 ~-0.82324 if entity @n[type=marker,tag=hitscan-marker,dx=1.64648,dy=1.85,dz=1.64648] run return 1
        {
            ServerCommandSource kfcSrc74 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.82324), (source.getPosition().y + 0.0), (source.getPosition().z + -0.82324)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton_horse"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc74.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.64648, 1.85, 1.64648)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-0.825 ~-0.1 ~-0.825 if entity @n[type=marker,tag=hitscan-marker,dx=1.65,dy=1.15,dz=1.65] run return 1
        {
            ServerCommandSource kfcSrc75 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.825), (source.getPosition().y + -0.1), (source.getPosition().z + -0.825)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc75.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.65, 1.15, 1.65)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.325 ~-0.2 ~-0.325 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.35 ~0 ~-0.35 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc76 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.325), (source.getPosition().y + -0.2), (source.getPosition().z + -0.325)));
            ServerCommandSource kfcSrc77 = kfcSrc76.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc76.getPosition().x + -0.35), (kfcSrc76.getPosition().y + 0.0), (kfcSrc76.getPosition().z + -0.35)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_vex"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc76.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.25, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc77.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.575 ~0 ~-0.575 if entity @n[type=marker,tag=hitscan-marker,dx=1.15,dy=3.15,dz=1.15] run return 1
        {
            ServerCommandSource kfcSrc78 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.575), (source.getPosition().y + 0.0), (source.getPosition().z + -0.575)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_warden"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc78.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.15, 3.15, 1.15)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.475 ~0 ~-0.475 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.65,dz=0] positioned ~-0.05 ~0 ~-0.05 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.4,dz=0] run return 1
        {
            ServerCommandSource kfcSrc79 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.475), (source.getPosition().y + 0.0), (source.getPosition().z + -0.475)));
            ServerCommandSource kfcSrc80 = kfcSrc79.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc79.getPosition().x + -0.05), (kfcSrc79.getPosition().y + 0.0), (kfcSrc79.getPosition().z + -0.05)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_wither_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc79.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.65, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc80.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.4, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.275 ~-0.025 ~-0.275 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.45 ~0 ~-0.45 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc81 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.275), (source.getPosition().y + -0.025), (source.getPosition().z + -0.275)));
            ServerCommandSource kfcSrc82 = kfcSrc81.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc81.getPosition().x + -0.45), (kfcSrc81.getPosition().y + 0.0), (kfcSrc81.getPosition().z + -0.45)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_baby"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc81.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.25, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc82.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.455 ~0 ~-0.455 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.395,dz=0] positioned ~-0.09 ~0 ~-0.09 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.145,dz=0] run return 1
        {
            ServerCommandSource kfcSrc83 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.455), (source.getPosition().y + 0.0), (source.getPosition().z + -0.455)));
            ServerCommandSource kfcSrc84 = kfcSrc83.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc83.getPosition().x + -0.09), (kfcSrc83.getPosition().y + 0.0), (kfcSrc83.getPosition().z + -0.09)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_1"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc83.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.395, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc84.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 1.145, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.525 ~0 ~-0.525 if entity @n[type=marker,tag=hitscan-marker,dx=0.025,dy=2.175,dz=0.025] run return 1
        {
            ServerCommandSource kfcSrc85 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.525), (source.getPosition().y + 0.0), (source.getPosition().z + -0.525)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_5"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc85.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.025, 2.175, 0.025)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-0.615 ~0 ~-0.615 if entity @n[type=marker,tag=hitscan-marker,dx=0.115,dy=2.55,dz=0.115] run return 1
        {
            ServerCommandSource kfcSrc86 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.615), (source.getPosition().y + 0.0), (source.getPosition().z + -0.615)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_8"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc86.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.115, 2.55, 0.115)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~-0.675 ~0 ~-0.675 if entity @n[type=marker,tag=hitscan-marker,dx=0.2,dy=2.9,dz=0.2] run return 1
        {
            ServerCommandSource kfcSrc87 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.675), (source.getPosition().y + 0.0), (source.getPosition().z + -0.675)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_2"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc87.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.2, 2.9, 0.2)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-0.825 ~ ~-0.825 if entity @n[type=marker,tag=hitscan-marker,dx=0.65,dy=0.65,dz=0.65] run return 1
        {
            ServerCommandSource kfcSrc88 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.825), source.getPosition().y, (source.getPosition().z + -0.825)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zoglin"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc88.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.65, 0.65, 0.65)) {
                    return 1;
                }
            }
        }

        // return 0
        return 0;
    }
}
