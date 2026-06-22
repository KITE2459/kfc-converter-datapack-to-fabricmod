package tdpack.buckets;

import tdpack.generated.KfcGen;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/** Auto-bucketed: 136 datapack functions. */
public final class Bucket6 {
    private Bucket6() { throw new UnsupportedOperationException(); }

    public static void _m_b4ea114c_enemy_spawn_model_ravager_execute(ServerCommandSource source) {
        _m_b4ea114c_enemy_spawn_model_ravager_executeReturn(source);
    }

    public static int _m_b4ea114c_enemy_spawn_model_ravager_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:6000, speed:0.23, defence:125, money:8000,id:ravager,name:{color:white,"text":"파괴수\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:6000, speed:0.23, defence:125, money:8000,id:ravager,name:{color:white,\"text\":\"파괴수\\n\",\"bold\":true}}", "set");

        // summon ravager ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_ravager,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_ravager,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "ravager", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_ravager,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_ravager,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_ea7946db_enemy_spawn_model_revive_zombie_execute(ServerCommandSource source) {
        _m_ea7946db_enemy_spawn_model_revive_zombie_executeReturn(source);
    }

    public static int _m_ea7946db_enemy_spawn_model_revive_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:225, speed:0.09, defence:25, money:300,id:revive-zombie,name:{color:white,"text":"부활 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:225, speed:0.09, defence:25, money:300,id:revive-zombie,name:{color:white,\"text\":\"부활 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_a1340cea_enemy_spawn_model_revived_zombie_execute(ServerCommandSource source) {
        _m_a1340cea_enemy_spawn_model_revived_zombie_executeReturn(source);
    }

    public static int _m_a1340cea_enemy_spawn_model_revived_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:250, speed:0.27, money:300,id:revived-zombie,name:{color:white,"text":"부활 좀비\n","bold":true},skills:{timer:1},attribute:{healing:2}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:250, speed:0.27, money:300,id:revived-zombie,name:{color:white,\"text\":\"부활 좀비\\n\",\"bold\":true},skills:{timer:1},attribute:{healing:2}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_22ce2a09_enemy_spawn_model_scream_zombie_execute(ServerCommandSource source) {
        _m_22ce2a09_enemy_spawn_model_scream_zombie_executeReturn(source);
    }

    public static int _m_22ce2a09_enemy_spawn_model_scream_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:3000, speed:0.11, money:3000,id:scream-zombie,name:{color:white,"text":"비명체 좀비\n","bold":true},skills:{timer:320}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:3000, speed:0.11, money:3000,id:scream-zombie,name:{color:white,\"text\":\"비명체 좀비\\n\",\"bold\":true},skills:{timer:320}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_2e3281cc_enemy_spawn_model_sculk_titan_execute(ServerCommandSource source) {
        _m_2e3281cc_enemy_spawn_model_sculk_titan_executeReturn(source);
    }

    public static int _m_2e3281cc_enemy_spawn_model_sculk_titan_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:55000, speed:0.06, defence:100, money:39000,id:sculk-titan,name:{color:red,"text":"스컬크 타이탄\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:55000, speed:0.06, defence:100, money:39000,id:sculk-titan,name:{color:red,\"text\":\"스컬크 타이탄\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.type.sculk-titan,enemy.skill-loop.1,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:scale,base:2.0}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.type.sculk-titan,enemy.skill-loop.1,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:scale,base:2.0}]}"); }
        return 0;
    }

    public static void _m_0f5c2b3d_enemy_spawn_model_shiled_zombie_execute(ServerCommandSource source) {
        _m_0f5c2b3d_enemy_spawn_model_shiled_zombie_executeReturn(source);
    }

    public static int _m_0f5c2b3d_enemy_spawn_model_shiled_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:125, speed:0.12, money:90, defence:50, id:shiled-zombie,name:{color:white,"text":"쉴드 좀비\n","bold":true}, skills:{low_hp:70}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:125, speed:0.12, money:90, defence:50, id:shiled-zombie,name:{color:white,\"text\":\"쉴드 좀비\\n\",\"bold\":true}, skills:{low_hp:70}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate},feet:{id:iron_boots},legs:{id:iron_leggings},mainhand:{id:shield}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp,enemy.immune.bleed,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"쉴드 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate},feet:{id:iron_boots},legs:{id:iron_leggings},mainhand:{id:shield}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp,enemy.immune.bleed,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"쉴드 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_dd0645c2_enemy_spawn_model_silverfish_execute(ServerCommandSource source) {
        _m_dd0645c2_enemy_spawn_model_silverfish_executeReturn(source);
    }

    public static int _m_dd0645c2_enemy_spawn_model_silverfish_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:50, speed:0.2, money:50,id:silverfish,name:{color:white,"text":"좀벌레\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:50, speed:0.2, money:50,id:silverfish,name:{color:white,\"text\":\"좀벌레\\n\",\"bold\":true}}", "set");

        // summon silverfish ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"좀벌레\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "silverfish", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀벌레\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_234ab0d4_enemy_spawn_model_silverfish_baby_execute(ServerCommandSource source) {
        _m_234ab0d4_enemy_spawn_model_silverfish_baby_executeReturn(source);
    }

    public static int _m_234ab0d4_enemy_spawn_model_silverfish_baby_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10, speed:0.17, money:0,id:silverfish-baby,name:{color:white,"text":"새끼 좀벌레\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10, speed:0.17, money:0,id:silverfish-baby,name:{color:white,\"text\":\"새끼 좀벌레\\n\",\"bold\":true}}", "set");

        // summon silverfish ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"좀벌레\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "silverfish", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀벌레\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_e966b1d9_enemy_spawn_model_silverfish_egg_execute(ServerCommandSource source) {
        _m_e966b1d9_enemy_spawn_model_silverfish_egg_executeReturn(source);
    }

    public static int _m_e966b1d9_enemy_spawn_model_silverfish_egg_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:100, speed:0, money:0,id:silverfish-egg,name:{color:white,"text":"단단한 좀벌레 알집\n","bold":true},attribute:{dealing:1}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:100, speed:0, money:0,id:silverfish-egg,name:{color:white,\"text\":\"단단한 좀벌레 알집\\n\",\"bold\":true},attribute:{dealing:1}}", "set");

        // summon block_display ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.flame,enemy.immune.bleed],Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"좀벌레\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.5f,0f],scale:[1f,1f,1f]}},{Tags:[enemy,enemy.hitbox_type_endermite],id:"minecraft:item_display",item:{id:"minecraft:player_head",count:1,components:{"minecraft:profile":{id:[I;1976325190,919718279,780001242,1210155425],properties:[{name:"textures",value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDA2MzEwYTg5NTJiMjY1YzZlNmJlZDQzNDgyMzlkZGVhOGU1NDgyYzhjNjhiZTZmZmY5ODFiYTgwNTZiZjJlIn19fQ=="}]}}},item_display:"none",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.5f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.flame,enemy.immune.bleed],Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀벌레\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.5f,0f],scale:[1f,1f,1f]}},{Tags:[enemy,enemy.hitbox_type_endermite],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",count:1,components:{\"minecraft:profile\":{id:[I;1976325190,919718279,780001242,1210155425],properties:[{name:\"textures\",value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDA2MzEwYTg5NTJiMjY1YzZlNmJlZDQzNDgyMzlkZGVhOGU1NDgyYzhjNjhiZTZmZmY5ODFiYTgwNTZiZjJlIn19fQ==\"}]}}},item_display:\"none\",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.5f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_b72a8a25_enemy_spawn_model_skeleton_execute(ServerCommandSource source) {
        _m_b72a8a25_enemy_spawn_model_skeleton_executeReturn(source);
    }

    public static int _m_b72a8a25_enemy_spawn_model_skeleton_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:175, speed:0.11, money:125,id:skeleton,name:{color:white,"text":"스켈레톤\n","bold":true},skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:175, speed:0.11, money:125,id:skeleton,name:{color:white,\"text\":\"스켈레톤\\n\",\"bold\":true},skills:{timer:100}}", "set");

        // summon skeleton ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_skeleton,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton,enemy.text],text:[{"color":"dark_green","text":"스켈레톤\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "skeleton", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_skeleton,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"스켈레톤\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_80358231_enemy_spawn_model_skulk_zombie_execute(ServerCommandSource source) {
        _m_80358231_enemy_spawn_model_skulk_zombie_executeReturn(source);
    }

    public static int _m_80358231_enemy_spawn_model_skulk_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:600, speed:0.15, money:425,id:skulk-zombie,name:{color:white,"text":"스컬크 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:600, speed:0.15, money:425,id:skulk-zombie,name:{color:white,\"text\":\"스컬크 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,skulk-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,skulk-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_916ba7f4_enemy_spawn_model_spider_execute(ServerCommandSource source) {
        _m_916ba7f4_enemy_spawn_model_spider_executeReturn(source);
    }

    public static int _m_916ba7f4_enemy_spawn_model_spider_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:200, speed:0.12, money:150,id:spider,name:{color:white,"text":"거미\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:200, speed:0.12, money:150,id:spider,name:{color:white,\"text\":\"거미\\n\",\"bold\":true}}", "set");

        // summon spider ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_spider,enemy.text],text:[{"color":"dark_green","text":"거미\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "spider", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_spider,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"거미\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_ada594d4_enemy_spawn_model_split_zombie_execute(ServerCommandSource source) {
        _m_ada594d4_enemy_spawn_model_split_zombie_executeReturn(source);
    }

    public static int _m_ada594d4_enemy_spawn_model_split_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:200, speed:0.13, money:135, id:split-zombie,name:{color:dark_purple,"text":"분열 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:200, speed:0.13, money:135, id:split-zombie,name:{color:dark_purple,\"text\":\"분열 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:slime_block}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"분열 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:slime_block}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"분열 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_f90319db_enemy_spawn_model_split_zombie_splits_execute(ServerCommandSource source) {
        _m_f90319db_enemy_spawn_model_split_zombie_splits_executeReturn(source);
    }

    public static int _m_f90319db_enemy_spawn_model_split_zombie_splits_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:60, speed:0.2, money:0, id:split-zombie,name:{color:white,"text":"좀비 분열체\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:60, speed:0.2, money:0, id:split-zombie,name:{color:white,\"text\":\"좀비 분열체\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_baby,enemy.target,enemy.core,enemy.data,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_baby,enemy.text],text:[{"color":"dark_green","text":"좀비 분열체\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],IsBaby:1b,equipment:{head:{id:slime_block}}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_baby,enemy.target,enemy.core,enemy.data,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_baby,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비 분열체\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],IsBaby:1b,equipment:{head:{id:slime_block}}}"); }
        return 0;
    }

    public static void _m_0607c3b9_enemy_spawn_model_stray_execute(ServerCommandSource source) {
        _m_0607c3b9_enemy_spawn_model_stray_executeReturn(source);
    }

    public static int _m_0607c3b9_enemy_spawn_model_stray_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:225, speed:0.14, money:175,id:stray,name:{color:white,"text":"스트레이\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:225, speed:0.14, money:175,id:stray,name:{color:white,\"text\":\"스트레이\\n\",\"bold\":true}}", "set");

        // summon stray ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "stray", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_b244d026_enemy_spawn_model_tank_zombie_execute(ServerCommandSource source) {
        _m_b244d026_enemy_spawn_model_tank_zombie_executeReturn(source);
    }

    public static int _m_b244d026_enemy_spawn_model_tank_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10000, speed:0.22, money:6500,id:tank-zombie,name:{color:white,"text":"탱크 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10000, speed:0.22, money:6500,id:tank-zombie,name:{color:white,\"text\":\"탱크 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_eaaf1c45_enemy_spawn_model_vex_execute(ServerCommandSource source) {
        _m_eaaf1c45_enemy_spawn_model_vex_executeReturn(source);
    }

    public static int _m_eaaf1c45_enemy_spawn_model_vex_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:235, speed:0.23, money:0,id:vex,name:{color:white,"text":"벡스\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:235, speed:0.23, money:0,id:vex,name:{color:white,\"text\":\"벡스\\n\",\"bold\":true}}", "set");

        // summon vex ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_vex,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_vex,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "vex", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_vex,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_vex,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_afa59a4a_enemy_spawn_model_vindicator_execute(ServerCommandSource source) {
        _m_afa59a4a_enemy_spawn_model_vindicator_executeReturn(source);
    }

    public static int _m_afa59a4a_enemy_spawn_model_vindicator_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:350, speed:0.23, money:200,id:vindicator,name:{color:white,"text":"변명자\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:350, speed:0.23, money:200,id:vindicator,name:{color:white,\"text\":\"변명자\\n\",\"bold\":true}}", "set");

        // summon vindicator ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "vindicator", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_90aac8d1_enemy_spawn_model_warden_execute(ServerCommandSource source) {
        _m_90aac8d1_enemy_spawn_model_warden_executeReturn(source);
    }

    public static int _m_90aac8d1_enemy_spawn_model_warden_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10000, speed:0.07, money:18000, defence:25, id:warden,name:{color:red,"text":"워든\n","bold":true}, skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10000, speed:0.07, money:18000, defence:25, id:warden,name:{color:red,\"text\":\"워든\\n\",\"bold\":true}, skills:{timer:300}}", "set");

        // summon warden ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_warden,enemy.target,enemy.core,enemy.data,enemy.immune.stun,enemy.immune.freeze,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_warden,enemy.text],text:[{"color":"dark_green","text":"워든\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"movement_speed",base:0.0}],Brain:{memories:{"minecraft:dig_cooldown":{value:{},ttl:999999L}}}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "warden", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_warden,enemy.target,enemy.core,enemy.data,enemy.immune.stun,enemy.immune.freeze,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_warden,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"워든\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"movement_speed\",base:0.0}],Brain:{memories:{\"minecraft:dig_cooldown\":{value:{},ttl:999999L}}}}"); }
        return 0;
    }

    public static void _m_40f9583b_enemy_spawn_model_warped_zombie_execute(ServerCommandSource source) {
        _m_40f9583b_enemy_spawn_model_warped_zombie_executeReturn(source);
    }

    public static int _m_40f9583b_enemy_spawn_model_warped_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:4000, speed:0.08, defence:75, money:3000,id:warped-zombie,name:{color:dark_purple,"text":"뒤틀린 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:4000, speed:0.08, defence:75, money:3000,id:warped-zombie,name:{color:dark_purple,\"text\":\"뒤틀린 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_1_8,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.text],text:[{"color":"dark_green","text":"뒤틀린 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:1.8}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_1_8,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"뒤틀린 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:1.8}]}"); }
        return 0;
    }

    public static void _m_e923c29b_enemy_spawn_model_witch_execute(ServerCommandSource source) {
        _m_e923c29b_enemy_spawn_model_witch_executeReturn(source);
    }

    public static int _m_e923c29b_enemy_spawn_model_witch_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:500, speed:0.09, defence:40, money:550,id:witch,name:{color:dark_purple,"text":"마녀\n","bold":true}, skills:{low_hp:499}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:500, speed:0.09, defence:40, money:550,id:witch,name:{color:dark_purple,\"text\":\"마녀\\n\",\"bold\":true}, skills:{low_hp:499}}", "set");

        // summon witch ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"마녀\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "witch", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"마녀\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_4b611dce_enemy_spawn_model_wither_skeleton_execute(ServerCommandSource source) {
        _m_4b611dce_enemy_spawn_model_wither_skeleton_executeReturn(source);
    }

    public static int _m_4b611dce_enemy_spawn_model_wither_skeleton_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:450, speed:0.17, money:300,id:wither-skeleton,name:{color:white,"text":"위더 스켈레톤\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:450, speed:0.17, money:300,id:wither-skeleton,name:{color:white,\"text\":\"위더 스켈레톤\\n\",\"bold\":true}}", "set");

        // summon wither_skeleton ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.text],text:[{"color":"dark_green","text":"wither_skeleton\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "wither_skeleton", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"wither_skeleton\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_82f7929e_enemy_spawn_model_zoglin_execute(ServerCommandSource source) {
        _m_82f7929e_enemy_spawn_model_zoglin_executeReturn(source);
    }

    public static int _m_82f7929e_enemy_spawn_model_zoglin_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:150, speed:0.13, money:125,id:zoglin,name:{color:white,"text":"조글린\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:150, speed:0.13, money:125,id:zoglin,name:{color:white,\"text\":\"조글린\\n\",\"bold\":true}}", "set");

        // summon zoglin ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zoglin,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zoglin", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zoglin,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_2795ff4f_enemy_spawn_model_zombie_execute(ServerCommandSource source) {
        _m_2795ff4f_enemy_spawn_model_zombie_executeReturn(source);
    }

    public static int _m_2795ff4f_enemy_spawn_model_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:8, speed:0.1, money:8,id:zombie,name:{color:white,"text":"좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:8, speed:0.1, money:8,id:zombie,name:{color:white,\"text\":\"좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_97bf4c8a_enemy_spawn_model_zombie_villager_execute(ServerCommandSource source) {
        _m_97bf4c8a_enemy_spawn_model_zombie_villager_executeReturn(source);
    }

    public static int _m_97bf4c8a_enemy_spawn_model_zombie_villager_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10, speed:0.1, defence:25, money:13,id:zombie-villager,name:{color:white,"text":"좀비 주민\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10, speed:0.1, defence:25, money:13,id:zombie-villager,name:{color:white,\"text\":\"좀비 주민\\n\",\"bold\":true}}", "set");

        // summon zombie_villager ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie_villager", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_61fd029e_enemy_spawn_model_forest_temple_dark_bogged_execute(ServerCommandSource source) {
        _m_61fd029e_enemy_spawn_model_forest_temple_dark_bogged_executeReturn(source);
    }

    public static int _m_61fd029e_enemy_spawn_model_forest_temple_dark_bogged_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:350, speed:0.16, money:250,id:bogged,name:{color:white,"text":"보그드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:350, speed:0.16, money:250,id:bogged,name:{color:white,\"text\":\"보그드\\n\",\"bold\":true}}", "set");

        // summon bogged ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "bogged", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7db7d6e9_enemy_spawn_model_forest_temple_dark_carsinops_execute(ServerCommandSource source) {
        _m_7db7d6e9_enemy_spawn_model_forest_temple_dark_carsinops_executeReturn(source);
    }

    public static int _m_7db7d6e9_enemy_spawn_model_forest_temple_dark_carsinops_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:80000, speed:0.07, money:50000,id:carsinops,name:{color:white,"text":"카르시놉스\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:80000, speed:0.07, money:50000,id:carsinops,name:{color:white,\"text\":\"카르시놉스\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_a63f5a50_enemy_spawn_model_forest_temple_dark_corrupted_drowned_execute(ServerCommandSource source) {
        _m_a63f5a50_enemy_spawn_model_forest_temple_dark_corrupted_drowned_executeReturn(source);
    }

    public static int _m_a63f5a50_enemy_spawn_model_forest_temple_dark_corrupted_drowned_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:50, speed:0.11, money:40,id:corrupted-drowned,name:{color:white,"text":"잠식된 드라운드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:50, speed:0.11, money:40,id:corrupted-drowned,name:{color:white,\"text\":\"잠식된 드라운드\\n\",\"bold\":true}}", "set");

        // summon drowned ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "drowned", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_51fd2874_enemy_spawn_model_forest_temple_dark_corrupted_husk_execute(ServerCommandSource source) {
        _m_51fd2874_enemy_spawn_model_forest_temple_dark_corrupted_husk_executeReturn(source);
    }

    public static int _m_51fd2874_enemy_spawn_model_forest_temple_dark_corrupted_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15, speed:0.12, money:15,id:corrupted-husk,name:{color:white,"text":"잠식된 허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15, speed:0.12, money:15,id:corrupted-husk,name:{color:white,\"text\":\"잠식된 허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_8d9c7cf5_enemy_spawn_model_forest_temple_dark_corrupted_zombie_execute(ServerCommandSource source) {
        _m_8d9c7cf5_enemy_spawn_model_forest_temple_dark_corrupted_zombie_executeReturn(source);
    }

    public static int _m_8d9c7cf5_enemy_spawn_model_forest_temple_dark_corrupted_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15, speed:0.1, money:15,id:corrupted-zombie,name:{color:white,"text":"잠식된 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15, speed:0.1, money:15,id:corrupted-zombie,name:{color:white,\"text\":\"잠식된 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_736595fb_enemy_spawn_model_forest_temple_dark_crawler_execute(ServerCommandSource source) {
        _m_736595fb_enemy_spawn_model_forest_temple_dark_crawler_executeReturn(source);
    }

    public static int _m_736595fb_enemy_spawn_model_forest_temple_dark_crawler_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15000, speed:0.08, money:10000,id:crawler,name:{color:white,"text":"크롤러\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15000, speed:0.08, money:10000,id:crawler,name:{color:white,\"text\":\"크롤러\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_63d6e303_enemy_spawn_model_forest_temple_dark_dark_execute(ServerCommandSource source) {
        _m_63d6e303_enemy_spawn_model_forest_temple_dark_dark_executeReturn(source);
    }

    public static int _m_63d6e303_enemy_spawn_model_forest_temple_dark_dark_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:600, speed:0.24, money:500,id:dark,name:{color:white,"text":"다크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:600, speed:0.24, money:500,id:dark,name:{color:white,\"text\":\"다크\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_15317608_enemy_spawn_model_forest_temple_dark_dark_dash_zombie_execute(ServerCommandSource source) {
        _m_15317608_enemy_spawn_model_forest_temple_dark_dark_dash_zombie_executeReturn(source);
    }

    public static int _m_15317608_enemy_spawn_model_forest_temple_dark_dark_dash_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:250, speed:0, money:300,id:dark-dash-zombie,name:{color:white,"text":"다크 돌진 좀비\n","bold":true},skills:{timer:20}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:250, speed:0, money:300,id:dark-dash-zombie,name:{color:white,\"text\":\"다크 돌진 좀비\\n\",\"bold\":true},skills:{timer:20}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun,enemy.attribute.dark],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun,enemy.attribute.dark],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7c6a410c_enemy_spawn_model_forest_temple_dark_dark_guardian_execute(ServerCommandSource source) {
        _m_7c6a410c_enemy_spawn_model_forest_temple_dark_dark_guardian_executeReturn(source);
    }

    public static int _m_7c6a410c_enemy_spawn_model_forest_temple_dark_dark_guardian_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:50000, speed:0.15, money:25000,id:dark-guardian,name:{color:white,"text":"다크 가디언\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:50000, speed:0.15, money:25000,id:dark-guardian,name:{color:white,\"text\":\"다크 가디언\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.immune.freeze,enemy.immune.bleed],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.immune.freeze,enemy.immune.bleed],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_656ecd60_enemy_spawn_model_forest_temple_dark_dark_headbomb_execute(ServerCommandSource source) {
        _m_656ecd60_enemy_spawn_model_forest_temple_dark_dark_headbomb_executeReturn(source);
    }

    public static int _m_656ecd60_enemy_spawn_model_forest_temple_dark_dark_headbomb_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:2500, speed:0.12, money:2500,id:dark-headbomb,name:{color:white,"text":"다크 헤드밤\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:2500, speed:0.12, money:2500,id:dark-headbomb,name:{color:white,\"text\":\"다크 헤드밤\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_90e1d2e4_enemy_spawn_model_forest_temple_dark_dark_knight_execute(ServerCommandSource source) {
        _m_90e1d2e4_enemy_spawn_model_forest_temple_dark_dark_knight_executeReturn(source);
    }

    public static int _m_90e1d2e4_enemy_spawn_model_forest_temple_dark_dark_knight_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15000, speed:0.1, money:12500,id:dark-knight,name:{color:white,"text":"다크 나이트\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15000, speed:0.1, money:12500,id:dark-knight,name:{color:white,\"text\":\"다크 나이트\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.immune.freeze,enemy.immune.bleed],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.immune.freeze,enemy.immune.bleed],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_961699d4_enemy_spawn_model_forest_temple_dark_dark_mist_execute(ServerCommandSource source) {
        _m_961699d4_enemy_spawn_model_forest_temple_dark_dark_mist_executeReturn(source);
    }

    public static int _m_961699d4_enemy_spawn_model_forest_temple_dark_dark_mist_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1000, speed:0.20, money:1000,id:dark-mist,name:{color:black,"text":"다크 미스트\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1000, speed:0.20, money:1000,id:dark-mist,name:{color:black,\"text\":\"다크 미스트\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"black","text":"다크 미스트\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"black\",\"text\":\"다크 미스트\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_d7fab8ca_enemy_spawn_model_forest_temple_dark_dark_walker_execute(ServerCommandSource source) {
        _m_d7fab8ca_enemy_spawn_model_forest_temple_dark_dark_walker_executeReturn(source);
    }

    public static int _m_d7fab8ca_enemy_spawn_model_forest_temple_dark_dark_walker_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:300, speed:0.15, money:250,id:dark-walker,name:{color:white,"text":"다크 워커\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:300, speed:0.15, money:250,id:dark-walker,name:{color:white,\"text\":\"다크 워커\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_73b6054c_enemy_spawn_model_forest_temple_dark_darkness_soron_execute(ServerCommandSource source) {
        _m_73b6054c_enemy_spawn_model_forest_temple_dark_darkness_soron_executeReturn(source);
    }

    public static int _m_73b6054c_enemy_spawn_model_forest_temple_dark_darkness_soron_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:250000, speed:0.08, money:125000,id:darkness-soron,name:{color:white,"text":"다크니스 소론\n","bold":true},skills:{timer:150,low-hp:100000}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:250000, speed:0.08, money:125000,id:darkness-soron,name:{color:white,\"text\":\"다크니스 소론\\n\",\"bold\":true},skills:{timer:150,low-hp:100000}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.skill-loop.1,enemy.skill-trigger.low-hp,enemy.immune.flame,enemy.immune.freeze,enemy.immune.poison,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.attribute.dark,enemy.skill-loop.1,enemy.skill-trigger.low-hp,enemy.immune.flame,enemy.immune.freeze,enemy.immune.poison,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_db1fc092_enemy_spawn_model_forest_temple_dark_enderman_execute(ServerCommandSource source) {
        _m_db1fc092_enemy_spawn_model_forest_temple_dark_enderman_executeReturn(source);
    }

    public static int _m_db1fc092_enemy_spawn_model_forest_temple_dark_enderman_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:5000, speed:0.17, money:3500,id:enderman,name:{color:white,"text":"엔더맨\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:5000, speed:0.17, money:3500,id:enderman,name:{color:white,\"text\":\"엔더맨\\n\",\"bold\":true}}", "set");

        // summon enderman ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_enderman,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_enderman,enemy.text],text:[{"color":"dark_green","text":"엔더맨\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "enderman", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_enderman,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_enderman,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"엔더맨\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_d9bc40e7_enemy_spawn_model_forest_temple_dark_heavy_dark_execute(ServerCommandSource source) {
        _m_d9bc40e7_enemy_spawn_model_forest_temple_dark_heavy_dark_executeReturn(source);
    }

    public static int _m_d9bc40e7_enemy_spawn_model_forest_temple_dark_heavy_dark_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1250, speed:0.12, money:1000,id:heavy-dark,name:{color:white,"text":"헤비 다크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1250, speed:0.12, money:1000,id:heavy-dark,name:{color:white,\"text\":\"헤비 다크\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.target,enemy.core,enemy.data,enemy.attribute.dark],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1.1f,1.1f,1.1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.target,enemy.core,enemy.data,enemy.attribute.dark],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1.1f,1.1f,1.1f]}}]}"); }
        return 0;
    }

    public static void _m_aa32fee2_enemy_spawn_model_forest_temple_dark_scream_zombie_execute(ServerCommandSource source) {
        _m_aa32fee2_enemy_spawn_model_forest_temple_dark_scream_zombie_executeReturn(source);
    }

    public static int _m_aa32fee2_enemy_spawn_model_forest_temple_dark_scream_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:3000, speed:0.11, money:3000,id:scream-zombie,name:{color:white,"text":"비명체 좀비\n","bold":true},skills:{timer:320}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:3000, speed:0.11, money:3000,id:scream-zombie,name:{color:white,\"text\":\"비명체 좀비\\n\",\"bold\":true},skills:{timer:320}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_883b0a73_enemy_spawn_model_forest_temple_dark_stray_execute(ServerCommandSource source) {
        _m_883b0a73_enemy_spawn_model_forest_temple_dark_stray_executeReturn(source);
    }

    public static int _m_883b0a73_enemy_spawn_model_forest_temple_dark_stray_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:225, speed:0.14, money:175,id:stray,name:{color:white,"text":"스트레이\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:225, speed:0.14, money:175,id:stray,name:{color:white,\"text\":\"스트레이\\n\",\"bold\":true}}", "set");

        // summon stray ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "stray", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_9b9f0694_enemy_spawn_model_forest_temple_dark_tank_zombie_execute(ServerCommandSource source) {
        _m_9b9f0694_enemy_spawn_model_forest_temple_dark_tank_zombie_executeReturn(source);
    }

    public static int _m_9b9f0694_enemy_spawn_model_forest_temple_dark_tank_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10000, speed:0.22, money:6500,id:tank-zombie,name:{color:white,"text":"탱크 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10000, speed:0.22, money:6500,id:tank-zombie,name:{color:white,\"text\":\"탱크 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_547700e6_enemy_spawn_model_forest_temple_dark_zoglin_execute(ServerCommandSource source) {
        _m_547700e6_enemy_spawn_model_forest_temple_dark_zoglin_executeReturn(source);
    }

    public static int _m_547700e6_enemy_spawn_model_forest_temple_dark_zoglin_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:150, speed:0.13, money:125,id:zoglin,name:{color:white,"text":"조글린\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:150, speed:0.13, money:125,id:zoglin,name:{color:white,\"text\":\"조글린\\n\",\"bold\":true}}", "set");

        // summon zoglin ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zoglin,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zoglin", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zoglin,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_04bfcfcf_enemy_spawn_model_forest_temple_dark_zombie_execute(ServerCommandSource source) {
        _m_04bfcfcf_enemy_spawn_model_forest_temple_dark_zombie_executeReturn(source);
    }

    public static int _m_04bfcfcf_enemy_spawn_model_forest_temple_dark_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:8, speed:0.1, money:8,id:zombie,name:{color:white,"text":"좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:8, speed:0.1, money:8,id:zombie,name:{color:white,\"text\":\"좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_394f7538_enemy_spawn_model_forest_temple_easy_armord_zombie_execute(ServerCommandSource source) {
        _m_394f7538_enemy_spawn_model_forest_temple_easy_armord_zombie_executeReturn(source);
    }

    public static int _m_394f7538_enemy_spawn_model_forest_temple_easy_armord_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:225, speed:0.07, money:200, defence:25, id:armord-zombie,name:{color:white,"text":"아머드 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:225, speed:0.07, money:200, defence:25, id:armord-zombie,name:{color:white,\"text\":\"아머드 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},legs:{id:netherite_leggings},feet:{id:netherite_boots}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"아머드 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},legs:{id:netherite_leggings},feet:{id:netherite_boots}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"아머드 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_1f63522a_enemy_spawn_model_forest_temple_easy_cave_spider_execute(ServerCommandSource source) {
        _m_1f63522a_enemy_spawn_model_forest_temple_easy_cave_spider_executeReturn(source);
    }

    public static int _m_1f63522a_enemy_spawn_model_forest_temple_easy_cave_spider_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:80, speed:0.16, money:50,id:cave-spider,name:{color:white,"text":"동굴 거미\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:80, speed:0.16, money:50,id:cave-spider,name:{color:white,\"text\":\"동굴 거미\\n\",\"bold\":true}}", "set");

        // summon cave_spider ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.text],text:[{"color":"dark_green","text":"동굴 거미\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "cave_spider", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"동굴 거미\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_ae5dafbb_enemy_spawn_model_forest_temple_easy_endermite_execute(ServerCommandSource source) {
        _m_ae5dafbb_enemy_spawn_model_forest_temple_easy_endermite_executeReturn(source);
    }

    public static int _m_ae5dafbb_enemy_spawn_model_forest_temple_easy_endermite_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:200, speed:0.15, money:400,id:endermite,name:{color:white,"text":"엔더마이트\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:200, speed:0.15, money:400,id:endermite,name:{color:white,\"text\":\"엔더마이트\\n\",\"bold\":true}}", "set");

        // summon endermite ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"엔더마이트\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "endermite", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"엔더마이트\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7a445452_enemy_spawn_model_forest_temple_easy_giant_execute(ServerCommandSource source) {
        _m_7a445452_enemy_spawn_model_forest_temple_easy_giant_executeReturn(source);
    }

    public static int _m_7a445452_enemy_spawn_model_forest_temple_easy_giant_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:2000, speed:0.06, money:1750,id:giant,name:{color:dark_purple,"text":"자이언트 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:2000, speed:0.06, money:1750,id:giant,name:{color:dark_purple,\"text\":\"자이언트 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.text],text:[{"color":"dark_green","text":"자이언트 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:1.5}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"자이언트 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:1.5}]}"); }
        return 0;
    }

    public static void _m_e0c60dec_enemy_spawn_model_forest_temple_easy_heavy_zombie_execute(ServerCommandSource source) {
        _m_e0c60dec_enemy_spawn_model_forest_temple_easy_heavy_zombie_executeReturn(source);
    }

    public static int _m_e0c60dec_enemy_spawn_model_forest_temple_easy_heavy_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:60, speed:0.07, money:45,id:heavy-zombie,name:{color:white,"text":"헤비 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:60, speed:0.07, money:45,id:heavy-zombie,name:{color:white,\"text\":\"헤비 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.text],text:[{"color":"dark_green","text":"헤비 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:1.1}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_1,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"헤비 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:1.1}]}"); }
        return 0;
    }

    public static void _m_a8eca323_enemy_spawn_model_forest_temple_easy_horse_zombie_execute(ServerCommandSource source) {
        _m_a8eca323_enemy_spawn_model_forest_temple_easy_horse_zombie_executeReturn(source);
    }

    public static int _m_a8eca323_enemy_spawn_model_forest_temple_easy_horse_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1000, speed:0.25, defence:25, money:1000,id:horse-zombie,name:{color:dark_purple,"text":"기마 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1000, speed:0.25, defence:25, money:1000,id:horse-zombie,name:{color:dark_purple,\"text\":\"기마 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie_horse ~ ~ ~ {equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{"color":"dark_green","text":"기마 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:"zombie",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie_horse", _sp.x, _sp.y, _sp.z, "{equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"기마 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:\"zombie\",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}"); }
        return 0;
    }

    public static void _m_2820e0ae_enemy_spawn_model_forest_temple_easy_quick_zombie_execute(ServerCommandSource source) {
        _m_2820e0ae_enemy_spawn_model_forest_temple_easy_quick_zombie_executeReturn(source);
    }

    public static int _m_2820e0ae_enemy_spawn_model_forest_temple_easy_quick_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15, speed:0.2, money:15,id:quick-zombie,name:{color:white,"text":"퀵 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15, speed:0.2, money:15,id:quick-zombie,name:{color:white,\"text\":\"퀵 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:diamond_helmet}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"퀵 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:diamond_helmet}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"퀵 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_152e7623_enemy_spawn_model_forest_temple_easy_shiled_zombie_execute(ServerCommandSource source) {
        _m_152e7623_enemy_spawn_model_forest_temple_easy_shiled_zombie_executeReturn(source);
    }

    public static int _m_152e7623_enemy_spawn_model_forest_temple_easy_shiled_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:125, speed:0.12, money:90, defence:50, id:shiled-zombie,name:{color:white,"text":"쉴드 좀비\n","bold":true}, skills:{low_hp:70}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:125, speed:0.12, money:90, defence:50, id:shiled-zombie,name:{color:white,\"text\":\"쉴드 좀비\\n\",\"bold\":true}, skills:{low_hp:70}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate},feet:{id:iron_boots},legs:{id:iron_leggings},mainhand:{id:shield}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp,enemy.immune.bleed,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"쉴드 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate},feet:{id:iron_boots},legs:{id:iron_leggings},mainhand:{id:shield}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp,enemy.immune.bleed,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"쉴드 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_b49e1334_enemy_spawn_model_forest_temple_easy_silverfish_execute(ServerCommandSource source) {
        _m_b49e1334_enemy_spawn_model_forest_temple_easy_silverfish_executeReturn(source);
    }

    public static int _m_b49e1334_enemy_spawn_model_forest_temple_easy_silverfish_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:50, speed:0.2, money:50,id:silverfish,name:{color:white,"text":"좀벌레\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:50, speed:0.2, money:50,id:silverfish,name:{color:white,\"text\":\"좀벌레\\n\",\"bold\":true}}", "set");

        // summon silverfish ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"좀벌레\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "silverfish", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀벌레\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_dd374c60_enemy_spawn_model_forest_temple_easy_spider_execute(ServerCommandSource source) {
        _m_dd374c60_enemy_spawn_model_forest_temple_easy_spider_executeReturn(source);
    }

    public static int _m_dd374c60_enemy_spawn_model_forest_temple_easy_spider_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:200, speed:0.12, money:150,id:spider,name:{color:white,"text":"거미\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:200, speed:0.12, money:150,id:spider,name:{color:white,\"text\":\"거미\\n\",\"bold\":true}}", "set");

        // summon spider ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_spider,enemy.text],text:[{"color":"dark_green","text":"거미\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "spider", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_spider,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"거미\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_760e1884_enemy_spawn_model_forest_temple_easy_split_zombie_execute(ServerCommandSource source) {
        _m_760e1884_enemy_spawn_model_forest_temple_easy_split_zombie_executeReturn(source);
    }

    public static int _m_760e1884_enemy_spawn_model_forest_temple_easy_split_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:200, speed:0.13, money:135, id:split-zombie,name:{color:dark_purple,"text":"분열 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:200, speed:0.13, money:135, id:split-zombie,name:{color:dark_purple,\"text\":\"분열 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:slime_block}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"분열 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:slime_block}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"분열 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_45894301_enemy_spawn_model_forest_temple_easy_warden_execute(ServerCommandSource source) {
        _m_45894301_enemy_spawn_model_forest_temple_easy_warden_executeReturn(source);
    }

    public static int _m_45894301_enemy_spawn_model_forest_temple_easy_warden_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10000, speed:0.07, money:18000, defence:25, id:warden,name:{color:red,"text":"워든\n","bold":true}, skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10000, speed:0.07, money:18000, defence:25, id:warden,name:{color:red,\"text\":\"워든\\n\",\"bold\":true}, skills:{timer:300}}", "set");

        // summon warden ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_warden,enemy.target,enemy.core,enemy.data,enemy.immune.stun,enemy.immune.freeze,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_warden,enemy.text],text:[{"color":"dark_green","text":"워든\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"movement_speed",base:0.0}],Brain:{memories:{"minecraft:dig_cooldown":{value:{},ttl:999999L}}}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "warden", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_warden,enemy.target,enemy.core,enemy.data,enemy.immune.stun,enemy.immune.freeze,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_warden,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"워든\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"movement_speed\",base:0.0}],Brain:{memories:{\"minecraft:dig_cooldown\":{value:{},ttl:999999L}}}}"); }
        return 0;
    }

    public static void _m_1cf3a095_enemy_spawn_model_forest_temple_easy_zombie_execute(ServerCommandSource source) {
        _m_1cf3a095_enemy_spawn_model_forest_temple_easy_zombie_executeReturn(source);
    }

    public static int _m_1cf3a095_enemy_spawn_model_forest_temple_easy_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:8, speed:0.1, money:8,id:zombie,name:{color:white,"text":"좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:8, speed:0.1, money:8,id:zombie,name:{color:white,\"text\":\"좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_9b2c613d_enemy_spawn_model_forest_temple_hard_charged_creeper_execute(ServerCommandSource source) {
        _m_9b2c613d_enemy_spawn_model_forest_temple_hard_charged_creeper_executeReturn(source);
    }

    public static int _m_9b2c613d_enemy_spawn_model_forest_temple_hard_charged_creeper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1500, speed:0.13, money:1500,id:charged-creeper,name:{color:white,"text":"충전된 크리퍼\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1500, speed:0.13, money:1500,id:charged-creeper,name:{color:white,\"text\":\"충전된 크리퍼\\n\",\"bold\":true}}", "set");

        // summon creeper ~ ~ ~ {powered:1b, Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{"color":"dark_green","text":"크리퍼\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "creeper", _sp.x, _sp.y, _sp.z, "{powered:1b, Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"크리퍼\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_fc0ba291_enemy_spawn_model_forest_temple_hard_creeper_execute(ServerCommandSource source) {
        _m_fc0ba291_enemy_spawn_model_forest_temple_hard_creeper_executeReturn(source);
    }

    public static int _m_fc0ba291_enemy_spawn_model_forest_temple_hard_creeper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:800, speed:0.13, money:800,id:creeper,name:{color:white,"text":"크리퍼\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:800, speed:0.13, money:800,id:creeper,name:{color:white,\"text\":\"크리퍼\\n\",\"bold\":true}}", "set");

        // summon creeper ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{"color":"dark_green","text":"크리퍼\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "creeper", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"크리퍼\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_d427955e_enemy_spawn_model_forest_temple_hard_dash_zombie_execute(ServerCommandSource source) {
        _m_d427955e_enemy_spawn_model_forest_temple_hard_dash_zombie_executeReturn(source);
    }

    public static int _m_d427955e_enemy_spawn_model_forest_temple_hard_dash_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:125, speed:0, money:125,id:dash-zombie,name:{color:white,"text":"돌진 좀비\n","bold":true},skills:{timer:20}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:125, speed:0, money:125,id:dash-zombie,name:{color:white,\"text\":\"돌진 좀비\\n\",\"bold\":true},skills:{timer:20}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7b7ac911_enemy_spawn_model_forest_temple_hard_drowned_execute(ServerCommandSource source) {
        _m_7b7ac911_enemy_spawn_model_forest_temple_hard_drowned_executeReturn(source);
    }

    public static int _m_7b7ac911_enemy_spawn_model_forest_temple_hard_drowned_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:40, speed:0.11, defence:25, money:30,id:drowned,name:{color:white,"text":"드라운드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:40, speed:0.11, defence:25, money:30,id:drowned,name:{color:white,\"text\":\"드라운드\\n\",\"bold\":true}}", "set");

        // summon drowned ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"드라운드\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "drowned", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"드라운드\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_e3e31f09_enemy_spawn_model_forest_temple_hard_elite_drowned_execute(ServerCommandSource source) {
        _m_e3e31f09_enemy_spawn_model_forest_temple_hard_elite_drowned_executeReturn(source);
    }

    public static int _m_e3e31f09_enemy_spawn_model_forest_temple_hard_elite_drowned_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1500, defence:100, speed:0.13, money:2000,id:elite-drowned,name:{color:white,"text":"엘리트 드라운드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1500, defence:100, speed:0.13, money:2000,id:elite-drowned,name:{color:white,\"text\":\"엘리트 드라운드\\n\",\"bold\":true}}", "set");

        // summon drowned ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"드라운드\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "drowned", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"드라운드\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_6cf966ed_enemy_spawn_model_forest_temple_hard_evoker_execute(ServerCommandSource source) {
        _m_6cf966ed_enemy_spawn_model_forest_temple_hard_evoker_executeReturn(source);
    }

    public static int _m_6cf966ed_enemy_spawn_model_forest_temple_hard_evoker_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1750, defence:75, speed:0.13, money:3000,id:evoker,name:{color:white,"text":"소환사\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1750, defence:75, speed:0.13, money:3000,id:evoker,name:{color:white,\"text\":\"소환사\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon evoker ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-loop.1],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"소환사\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "evoker", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-loop.1],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"소환사\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_98328adb_enemy_spawn_model_forest_temple_hard_horse_skeleton_execute(ServerCommandSource source) {
        _m_98328adb_enemy_spawn_model_forest_temple_hard_horse_skeleton_executeReturn(source);
    }

    public static int _m_98328adb_enemy_spawn_model_forest_temple_hard_horse_skeleton_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1000, speed:0.25, money:1250,id:horse-skeleton,name:{color:white,"text":"기마 스켈레톤\n","bold":true},skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1000, speed:0.25, money:1250,id:horse-skeleton,name:{color:white,\"text\":\"기마 스켈레톤\\n\",\"bold\":true},skills:{timer:100}}", "set");

        // summon skeleton_horse ~ ~ ~ {equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{"color":"dark_green","text":"기마 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:"skeleton",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "skeleton_horse", _sp.x, _sp.y, _sp.z, "{equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"기마 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:\"skeleton\",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}"); }
        return 0;
    }

    public static void _m_a438e090_enemy_spawn_model_forest_temple_hard_husk_execute(ServerCommandSource source) {
        _m_a438e090_enemy_spawn_model_forest_temple_hard_husk_executeReturn(source);
    }

    public static int _m_a438e090_enemy_spawn_model_forest_temple_hard_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10, speed:0.1, money:10,id:husk,name:{color:white,"text":"허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10, speed:0.1, money:10,id:husk,name:{color:white,\"text\":\"허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_48055efc_enemy_spawn_model_forest_temple_hard_illusioner_execute(ServerCommandSource source) {
        _m_48055efc_enemy_spawn_model_forest_temple_hard_illusioner_executeReturn(source);
    }

    public static int _m_48055efc_enemy_spawn_model_forest_temple_hard_illusioner_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:3500, speed:0.13, defence:75, money:3000,id:illusioner,name:{color:white,"text":"환술사\n","bold":true}, skills:{low_hp:2499}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:3500, speed:0.13, defence:75, money:3000,id:illusioner,name:{color:white,\"text\":\"환술사\\n\",\"bold\":true}, skills:{low_hp:2499}}", "set");

        // summon illusioner ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "illusioner", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_c7f08e61_enemy_spawn_model_forest_temple_hard_pillager_execute(ServerCommandSource source) {
        _m_c7f08e61_enemy_spawn_model_forest_temple_hard_pillager_executeReturn(source);
    }

    public static int _m_c7f08e61_enemy_spawn_model_forest_temple_hard_pillager_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:500, speed:0.1, money:450,id:pillager,name:{color:white,"text":"약탈자\n","bold":true},skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:500, speed:0.1, money:450,id:pillager,name:{color:white,\"text\":\"약탈자\\n\",\"bold\":true},skills:{timer:100}}", "set");

        // summon pillager ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "pillager", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_fbbe9386_enemy_spawn_model_forest_temple_hard_rabbit_execute(ServerCommandSource source) {
        _m_fbbe9386_enemy_spawn_model_forest_temple_hard_rabbit_executeReturn(source);
    }

    public static int _m_fbbe9386_enemy_spawn_model_forest_temple_hard_rabbit_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10, speed:0.52, money:8,id:rabbit,name:{color:white,"text":"좀비 토끼\n","bold":true},skills:{timer:0}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10, speed:0.52, money:8,id:rabbit,name:{color:white,\"text\":\"좀비 토끼\\n\",\"bold\":true},skills:{timer:0}}", "set");

        // summon rabbit ~ ~ ~ {RabbitType:99, Tags:[enemy,enemy.hitbox_type_rabbit,enemy.target,enemy.core,enemy.data,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_rabbit,enemy.text],text:[{"color":"dark_green","text":"좀비 토끼\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "rabbit", _sp.x, _sp.y, _sp.z, "{RabbitType:99, Tags:[enemy,enemy.hitbox_type_rabbit,enemy.target,enemy.core,enemy.data,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_rabbit,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비 토끼\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_bda396f9_enemy_spawn_model_forest_temple_hard_raid_guardian_execute(ServerCommandSource source) {
        _m_bda396f9_enemy_spawn_model_forest_temple_hard_raid_guardian_executeReturn(source);
    }

    public static int _m_bda396f9_enemy_spawn_model_forest_temple_hard_raid_guardian_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:12500, defence:175, speed:0.15, money:15000,id:raid-guardian,name:{color:white,"text":"레이드 가디언\n","bold":true}, skills:{low_hp:10000}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:12500, defence:175, speed:0.15, money:15000,id:raid-guardian,name:{color:white,\"text\":\"레이드 가디언\\n\",\"bold\":true}, skills:{low_hp:10000}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze,enemy.immune.stun,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze,enemy.immune.stun,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_0d6cf30b_enemy_spawn_model_forest_temple_hard_raid_lord_execute(ServerCommandSource source) {
        _m_0d6cf30b_enemy_spawn_model_forest_temple_hard_raid_lord_executeReturn(source);
    }

    public static int _m_0d6cf30b_enemy_spawn_model_forest_temple_hard_raid_lord_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:75000, defence:250, speed:0.10, money:112500,id:raid-lord,name:{color:white,"text":"레이드 로드\n","bold":true},skills:{timer:75,low-hp:25000}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:75000, defence:250, speed:0.10, money:112500,id:raid-lord,name:{color:white,\"text\":\"레이드 로드\\n\",\"bold\":true},skills:{timer:75,low-hp:25000}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.flame,enemy.immune.freeze,enemy.immune.stun,enemy.immune.poison,enemy.skill-loop.1,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.flame,enemy.immune.freeze,enemy.immune.stun,enemy.immune.poison,enemy.skill-loop.1,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_b4289d5b_enemy_spawn_model_forest_temple_hard_ravager_execute(ServerCommandSource source) {
        _m_b4289d5b_enemy_spawn_model_forest_temple_hard_ravager_executeReturn(source);
    }

    public static int _m_b4289d5b_enemy_spawn_model_forest_temple_hard_ravager_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:6000, speed:0.23, defence:125, money:8000,id:ravager,name:{color:white,"text":"파괴수\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:6000, speed:0.23, defence:125, money:8000,id:ravager,name:{color:white,\"text\":\"파괴수\\n\",\"bold\":true}}", "set");

        // summon ravager ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_ravager,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_ravager,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "ravager", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_ravager,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_ravager,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_727fb141_enemy_spawn_model_forest_temple_hard_revive_zombie_execute(ServerCommandSource source) {
        _m_727fb141_enemy_spawn_model_forest_temple_hard_revive_zombie_executeReturn(source);
    }

    public static int _m_727fb141_enemy_spawn_model_forest_temple_hard_revive_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:225, speed:0.09, defence:25, money:300,id:revive-zombie,name:{color:white,"text":"부활 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:225, speed:0.09, defence:25, money:300,id:revive-zombie,name:{color:white,\"text\":\"부활 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_38450b64_enemy_spawn_model_forest_temple_hard_skeleton_execute(ServerCommandSource source) {
        _m_38450b64_enemy_spawn_model_forest_temple_hard_skeleton_executeReturn(source);
    }

    public static int _m_38450b64_enemy_spawn_model_forest_temple_hard_skeleton_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:175, speed:0.11, money:125,id:skeleton,name:{color:white,"text":"스켈레톤\n","bold":true},skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:175, speed:0.11, money:125,id:skeleton,name:{color:white,\"text\":\"스켈레톤\\n\",\"bold\":true},skills:{timer:100}}", "set");

        // summon skeleton ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_skeleton,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton,enemy.text],text:[{"color":"dark_green","text":"스켈레톤\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "skeleton", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_skeleton,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"스켈레톤\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_e0858043_enemy_spawn_model_forest_temple_hard_vindicator_execute(ServerCommandSource source) {
        _m_e0858043_enemy_spawn_model_forest_temple_hard_vindicator_executeReturn(source);
    }

    public static int _m_e0858043_enemy_spawn_model_forest_temple_hard_vindicator_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:350, speed:0.23, money:200,id:vindicator,name:{color:white,"text":"변명자\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:350, speed:0.23, money:200,id:vindicator,name:{color:white,\"text\":\"변명자\\n\",\"bold\":true}}", "set");

        // summon vindicator ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "vindicator", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_e4c9049d_enemy_spawn_model_forest_temple_hard_wither_skeleton_execute(ServerCommandSource source) {
        _m_e4c9049d_enemy_spawn_model_forest_temple_hard_wither_skeleton_executeReturn(source);
    }

    public static int _m_e4c9049d_enemy_spawn_model_forest_temple_hard_wither_skeleton_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:450, speed:0.17, money:300,id:wither-skeleton,name:{color:white,"text":"위더 스켈레톤\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:450, speed:0.17, money:300,id:wither-skeleton,name:{color:white,\"text\":\"위더 스켈레톤\\n\",\"bold\":true}}", "set");

        // summon wither_skeleton ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.text],text:[{"color":"dark_green","text":"wither_skeleton\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "wither_skeleton", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"wither_skeleton\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_1fd66f50_enemy_spawn_model_forest_temple_hard_zombie_villager_execute(ServerCommandSource source) {
        _m_1fd66f50_enemy_spawn_model_forest_temple_hard_zombie_villager_executeReturn(source);
    }

    public static int _m_1fd66f50_enemy_spawn_model_forest_temple_hard_zombie_villager_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10, speed:0.1, defence:25, money:13,id:zombie-villager,name:{color:white,"text":"좀비 주민\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10, speed:0.1, defence:25, money:13,id:zombie-villager,name:{color:white,\"text\":\"좀비 주민\\n\",\"bold\":true}}", "set");

        // summon zombie_villager ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie_villager", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_63b416d7_enemy_spawn_model_forest_temple_infinity_breeze_execute(ServerCommandSource source) {
        _m_63b416d7_enemy_spawn_model_forest_temple_infinity_breeze_executeReturn(source);
    }

    public static int _m_63b416d7_enemy_spawn_model_forest_temple_infinity_breeze_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:750, speed:0.22, defence:25,money:400,id:breeze,name:{color:white,"text":"브리즈\n","bold":true}, skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:750, speed:0.22, defence:25,money:400,id:breeze,name:{color:white,\"text\":\"브리즈\\n\",\"bold\":true}, skills:{timer:100}}", "set");

        // summon breeze ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_breeze,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_breeze,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "breeze", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_breeze,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_breeze,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_9d666c33_enemy_spawn_model_forest_temple_infinity_carsinops_execute(ServerCommandSource source) {
        _m_9d666c33_enemy_spawn_model_forest_temple_infinity_carsinops_executeReturn(source);
    }

    public static int _m_9d666c33_enemy_spawn_model_forest_temple_infinity_carsinops_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:80000, speed:0.07, money:50000,id:carsinops,name:{color:white,"text":"카르시놉스\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:80000, speed:0.07, money:50000,id:carsinops,name:{color:white,\"text\":\"카르시놉스\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_311a2ddc_enemy_spawn_model_forest_temple_infinity_catalyst_zombie_execute(ServerCommandSource source) {
        _m_311a2ddc_enemy_spawn_model_forest_temple_infinity_catalyst_zombie_executeReturn(source);
    }

    public static int _m_311a2ddc_enemy_spawn_model_forest_temple_infinity_catalyst_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1600, speed:0.1, money:1435,id:catalyst-zombie,name:{color:white,"text":"촉매 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1600, speed:0.1, money:1435,id:catalyst-zombie,name:{color:white,\"text\":\"촉매 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,catalyst-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,catalyst-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_35a58167_enemy_spawn_model_forest_temple_infinity_cave_spider_execute(ServerCommandSource source) {
        _m_35a58167_enemy_spawn_model_forest_temple_infinity_cave_spider_executeReturn(source);
    }

    public static int _m_35a58167_enemy_spawn_model_forest_temple_infinity_cave_spider_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:225, speed:0.16, money:50,id:cave-spider,name:{color:white,"text":"동굴 거미\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:225, speed:0.16, money:50,id:cave-spider,name:{color:white,\"text\":\"동굴 거미\\n\",\"bold\":true}}", "set");

        // summon cave_spider ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.text],text:[{"color":"dark_green","text":"동굴 거미\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "cave_spider", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_cave_spider,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"동굴 거미\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_719b0564_enemy_spawn_model_forest_temple_infinity_charged_creeper_execute(ServerCommandSource source) {
        _m_719b0564_enemy_spawn_model_forest_temple_infinity_charged_creeper_executeReturn(source);
    }

    public static int _m_719b0564_enemy_spawn_model_forest_temple_infinity_charged_creeper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1500, speed:0.13, money:1500,id:charged-creeper,name:{color:white,"text":"충전된 크리퍼\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1500, speed:0.13, money:1500,id:charged-creeper,name:{color:white,\"text\":\"충전된 크리퍼\\n\",\"bold\":true}}", "set");

        // summon creeper ~ ~ ~ {powered:1b, Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{"color":"dark_green","text":"크리퍼\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "creeper", _sp.x, _sp.y, _sp.z, "{powered:1b, Tags:[enemy,enemy.hitbox_type_creeper,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_creeper,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"크리퍼\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_6c017377_enemy_spawn_model_forest_temple_infinity_corrupted_husk_execute(ServerCommandSource source) {
        _m_6c017377_enemy_spawn_model_forest_temple_infinity_corrupted_husk_executeReturn(source);
    }

    public static int _m_6c017377_enemy_spawn_model_forest_temple_infinity_corrupted_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:60, speed:0.12, money:15,id:corrupted-husk,name:{color:white,"text":"잠식된 허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:60, speed:0.12, money:15,id:corrupted-husk,name:{color:white,\"text\":\"잠식된 허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_20b608a2_enemy_spawn_model_forest_temple_infinity_crimson_zombie_execute(ServerCommandSource source) {
        _m_20b608a2_enemy_spawn_model_forest_temple_infinity_crimson_zombie_executeReturn(source);
    }

    public static int _m_20b608a2_enemy_spawn_model_forest_temple_infinity_crimson_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:4000, speed:0.17, defence:75, money:4000,id:crimson-zombie,name:{color:dark_purple,"text":"진홍빛 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:4000, speed:0.17, defence:75, money:4000,id:crimson-zombie,name:{color:dark_purple,\"text\":\"진홍빛 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.text],text:[{"color":"dark_green","text":"진홍빛 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:2}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"진홍빛 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:2}]}"); }
        return 0;
    }

    public static void _m_cb91c064_enemy_spawn_model_forest_temple_infinity_dark_execute(ServerCommandSource source) {
        _m_cb91c064_enemy_spawn_model_forest_temple_infinity_dark_executeReturn(source);
    }

    public static int _m_cb91c064_enemy_spawn_model_forest_temple_infinity_dark_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1250, speed:0.24, money:500,id:dark,name:{color:white,"text":"다크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1250, speed:0.24, money:500,id:dark,name:{color:white,\"text\":\"다크\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_982be3fd_enemy_spawn_model_forest_temple_infinity_dark_guardian_execute(ServerCommandSource source) {
        _m_982be3fd_enemy_spawn_model_forest_temple_infinity_dark_guardian_executeReturn(source);
    }

    public static int _m_982be3fd_enemy_spawn_model_forest_temple_infinity_dark_guardian_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:50000, speed:0.15, money:25000,id:dark-guardian,name:{color:white,"text":"다크 가디언\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:50000, speed:0.15, money:25000,id:dark-guardian,name:{color:white,\"text\":\"다크 가디언\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_773ed56b_enemy_spawn_model_forest_temple_infinity_dark_knight_execute(ServerCommandSource source) {
        _m_773ed56b_enemy_spawn_model_forest_temple_infinity_dark_knight_executeReturn(source);
    }

    public static int _m_773ed56b_enemy_spawn_model_forest_temple_infinity_dark_knight_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15000, speed:0.1, money:12500,id:dark-knight,name:{color:white,"text":"다크 나이트\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15000, speed:0.1, money:12500,id:dark-knight,name:{color:white,\"text\":\"다크 나이트\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_f2213988_enemy_spawn_model_forest_temple_infinity_darkness_soron_execute(ServerCommandSource source) {
        _m_f2213988_enemy_spawn_model_forest_temple_infinity_darkness_soron_executeReturn(source);
    }

    public static int _m_f2213988_enemy_spawn_model_forest_temple_infinity_darkness_soron_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:75000, speed:0.08, money:125000,defence:250,id:darkness-soron,name:{color:white,"text":"다크니스 소론\n","bold":true},skills:{timer:150,low-hp:100000}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:75000, speed:0.08, money:125000,defence:250,id:darkness-soron,name:{color:white,\"text\":\"다크니스 소론\\n\",\"bold\":true},skills:{timer:150,low-hp:100000}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-loop.1,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-loop.1,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7d3d48a7_enemy_spawn_model_forest_temple_infinity_dash_zombie_execute(ServerCommandSource source) {
        _m_7d3d48a7_enemy_spawn_model_forest_temple_infinity_dash_zombie_executeReturn(source);
    }

    public static int _m_7d3d48a7_enemy_spawn_model_forest_temple_infinity_dash_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:75, speed:0, money:125,id:dash-zombie,name:{color:white,"text":"돌진 좀비\n","bold":true},skills:{timer:25}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:75, speed:0, money:125,id:dash-zombie,name:{color:white,\"text\":\"돌진 좀비\\n\",\"bold\":true},skills:{timer:25}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7166a940_enemy_spawn_model_forest_temple_infinity_elite_drowned_execute(ServerCommandSource source) {
        _m_7166a940_enemy_spawn_model_forest_temple_infinity_elite_drowned_executeReturn(source);
    }

    public static int _m_7166a940_enemy_spawn_model_forest_temple_infinity_elite_drowned_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:2500, defence:100, speed:0.13, money:2000,id:elite-drowned,name:{color:white,"text":"엘리트 드라운드\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:2500, defence:100, speed:0.13, money:2000,id:elite-drowned,name:{color:white,\"text\":\"엘리트 드라운드\\n\",\"bold\":true}}", "set");

        // summon drowned ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"드라운드\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "drowned", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"드라운드\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_0a3c8015_enemy_spawn_model_forest_temple_infinity_giant_execute(ServerCommandSource source) {
        _m_0a3c8015_enemy_spawn_model_forest_temple_infinity_giant_executeReturn(source);
    }

    public static int _m_0a3c8015_enemy_spawn_model_forest_temple_infinity_giant_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:2000, speed:0.06, money:1750,id:giant,name:{color:dark_purple,"text":"자이언트 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:2000, speed:0.06, money:1750,id:giant,name:{color:dark_purple,\"text\":\"자이언트 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.text],text:[{"color":"dark_green","text":"자이언트 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:1.5}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_scale_1_5,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"자이언트 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:1.5}]}"); }
        return 0;
    }

    public static void _m_d07dce05_enemy_spawn_model_forest_temple_infinity_heavy_husk_execute(ServerCommandSource source) {
        _m_d07dce05_enemy_spawn_model_forest_temple_infinity_heavy_husk_executeReturn(source);
    }

    public static int _m_d07dce05_enemy_spawn_model_forest_temple_infinity_heavy_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:600, speed:0.08, money:75, defence:25,id:heavy-husk,name:{color:white,"text":"헤비 허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:600, speed:0.08, money:75, defence:25,id:heavy-husk,name:{color:white,\"text\":\"헤비 허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_1534a5e6_enemy_spawn_model_forest_temple_infinity_horse_zombie_execute(ServerCommandSource source) {
        _m_1534a5e6_enemy_spawn_model_forest_temple_infinity_horse_zombie_executeReturn(source);
    }

    public static int _m_1534a5e6_enemy_spawn_model_forest_temple_infinity_horse_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:2500, speed:0.25, defence:25, money:1000,id:horse-zombie,name:{color:dark_purple,"text":"기마 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:2500, speed:0.25, defence:25, money:1000,id:horse-zombie,name:{color:dark_purple,\"text\":\"기마 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie_horse ~ ~ ~ {equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{"color":"dark_green","text":"기마 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:"zombie",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie_horse", _sp.x, _sp.y, _sp.z, "{equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"기마 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:\"zombie\",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}"); }
        return 0;
    }

    public static void _m_0631551d_enemy_spawn_model_forest_temple_infinity_indura_zombie_execute(ServerCommandSource source) {
        _m_0631551d_enemy_spawn_model_forest_temple_infinity_indura_zombie_executeReturn(source);
    }

    public static int _m_0631551d_enemy_spawn_model_forest_temple_infinity_indura_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:750, speed:0.2, defence:50, money:300,id:indura-zombie,name:{color:white,"text":"인듀라 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:750, speed:0.2, defence:50, money:300,id:indura-zombie,name:{color:white,\"text\":\"인듀라 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:black_shulker_box},chest:{id:netherite_chestplate,components:{trim:{material:quartz,pattern:eye}},count:1b}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:black_shulker_box},chest:{id:netherite_chestplate,components:{trim:{material:quartz,pattern:eye}},count:1b}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_6c44ce0e_enemy_spawn_model_forest_temple_infinity_pillager_execute(ServerCommandSource source) {
        _m_6c44ce0e_enemy_spawn_model_forest_temple_infinity_pillager_executeReturn(source);
    }

    public static int _m_6c44ce0e_enemy_spawn_model_forest_temple_infinity_pillager_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:500, speed:0.1, money:450,id:pillager,name:{color:white,"text":"약탈자\n","bold":true},skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:500, speed:0.1, money:450,id:pillager,name:{color:white,\"text\":\"약탈자\\n\",\"bold\":true},skills:{timer:100}}", "set");

        // summon pillager ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "pillager", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_68dce989_enemy_spawn_model_forest_temple_infinity_rabbit_execute(ServerCommandSource source) {
        _m_68dce989_enemy_spawn_model_forest_temple_infinity_rabbit_executeReturn(source);
    }

    public static int _m_68dce989_enemy_spawn_model_forest_temple_infinity_rabbit_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:15, speed:0.52, money:8,id:rabbit,name:{color:white,"text":"좀비 토끼\n","bold":true},skills:{timer:0}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:15, speed:0.52, money:8,id:rabbit,name:{color:white,\"text\":\"좀비 토끼\\n\",\"bold\":true},skills:{timer:0}}", "set");

        // summon rabbit ~ ~ ~ {RabbitType:99, Tags:[enemy,enemy.hitbox_type_rabbit,enemy.target,enemy.core,enemy.data,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_rabbit,enemy.text],text:[{"color":"dark_green","text":"좀비 토끼\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "rabbit", _sp.x, _sp.y, _sp.z, "{RabbitType:99, Tags:[enemy,enemy.hitbox_type_rabbit,enemy.target,enemy.core,enemy.data,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_rabbit,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비 토끼\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_36399a2b_enemy_spawn_model_forest_temple_infinity_raid_guardian_execute(ServerCommandSource source) {
        _m_36399a2b_enemy_spawn_model_forest_temple_infinity_raid_guardian_executeReturn(source);
    }

    public static int _m_36399a2b_enemy_spawn_model_forest_temple_infinity_raid_guardian_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:12500, defence:175, speed:0.15, money:15000,id:raid-guardian,name:{color:white,"text":"레이드 가디언\n","bold":true}, skills:{low_hp:10000}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:12500, defence:175, speed:0.15, money:15000,id:raid-guardian,name:{color:white,\"text\":\"레이드 가디언\\n\",\"bold\":true}, skills:{low_hp:10000}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze,enemy.immune.stun,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze,enemy.immune.stun,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_4aea2010_enemy_spawn_model_forest_temple_infinity_raid_lord_execute(ServerCommandSource source) {
        _m_4aea2010_enemy_spawn_model_forest_temple_infinity_raid_lord_executeReturn(source);
    }

    public static int _m_4aea2010_enemy_spawn_model_forest_temple_infinity_raid_lord_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:45000, defence:250, speed:0.10, money:112500,id:raid-lord,name:{color:white,"text":"레이드 로드\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:45000, defence:250, speed:0.10, money:112500,id:raid-lord,name:{color:white,\"text\":\"레이드 로드\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.flame,enemy.immune.freeze,enemy.immune.stun,enemy.immune.poison,enemy.skill-loop.1],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.flame,enemy.immune.freeze,enemy.immune.stun,enemy.immune.poison,enemy.skill-loop.1],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_8441f152_enemy_spawn_model_forest_temple_infinity_ravager_execute(ServerCommandSource source) {
        _m_8441f152_enemy_spawn_model_forest_temple_infinity_ravager_executeReturn(source);
    }

    public static int _m_8441f152_enemy_spawn_model_forest_temple_infinity_ravager_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:4000, speed:0.23, defence:125, money:8000,id:ravager,name:{color:white,"text":"파괴수\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:4000, speed:0.23, defence:125, money:8000,id:ravager,name:{color:white,\"text\":\"파괴수\\n\",\"bold\":true}}", "set");

        // summon ravager ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_ravager,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_ravager,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "ravager", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_ravager,enemy.target,enemy.core,enemy.data,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_ravager,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_02fd7e2f_enemy_spawn_model_forest_temple_infinity_sculk_titan_execute(ServerCommandSource source) {
        _m_02fd7e2f_enemy_spawn_model_forest_temple_infinity_sculk_titan_executeReturn(source);
    }

    public static int _m_02fd7e2f_enemy_spawn_model_forest_temple_infinity_sculk_titan_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:55000, speed:0.06, defence:100, money:39000,id:sculk-titan,name:{color:red,"text":"스컬크 타이탄\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:55000, speed:0.06, defence:100, money:39000,id:sculk-titan,name:{color:red,\"text\":\"스컬크 타이탄\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.type.sculk-titan,enemy.skill-loop.1,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:scale,base:2.0}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.type.sculk-titan,enemy.skill-loop.1,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:scale,base:2.0}]}"); }
        return 0;
    }

    public static void _m_eac8dd6b_enemy_spawn_model_forest_temple_infinity_shiled_zombie_execute(ServerCommandSource source) {
        _m_eac8dd6b_enemy_spawn_model_forest_temple_infinity_shiled_zombie_executeReturn(source);
    }

    public static int _m_eac8dd6b_enemy_spawn_model_forest_temple_infinity_shiled_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:300, speed:0.12, money:90, defence:75, id:shiled-zombie,name:{color:white,"text":"쉴드 좀비\n","bold":true}, skills:{low_hp:75}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:300, speed:0.12, money:90, defence:75, id:shiled-zombie,name:{color:white,\"text\":\"쉴드 좀비\\n\",\"bold\":true}, skills:{low_hp:75}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate},feet:{id:iron_boots},legs:{id:iron_leggings},mainhand:{id:shield}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp,enemy.immune.bleed,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"쉴드 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate},feet:{id:iron_boots},legs:{id:iron_leggings},mainhand:{id:shield}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp,enemy.immune.bleed,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"쉴드 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_84968ac5_enemy_spawn_model_forest_temple_infinity_silverfish_execute(ServerCommandSource source) {
        _m_84968ac5_enemy_spawn_model_forest_temple_infinity_silverfish_executeReturn(source);
    }

    public static int _m_84968ac5_enemy_spawn_model_forest_temple_infinity_silverfish_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:75, speed:0.2, money:50,id:silverfish,name:{color:white,"text":"좀벌레\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:75, speed:0.2, money:50,id:silverfish,name:{color:white,\"text\":\"좀벌레\\n\",\"bold\":true}}", "set");

        // summon silverfish ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{"color":"dark_green","text":"좀벌레\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "silverfish", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_endermite,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_endermite,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀벌레\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_7627b268_enemy_spawn_model_forest_temple_infinity_skeleton_execute(ServerCommandSource source) {
        _m_7627b268_enemy_spawn_model_forest_temple_infinity_skeleton_executeReturn(source);
    }

    public static int _m_7627b268_enemy_spawn_model_forest_temple_infinity_skeleton_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:250, speed:0.11, money:125,id:skeleton,name:{color:white,"text":"스켈레톤\n","bold":true},skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:250, speed:0.11, money:125,id:skeleton,name:{color:white,\"text\":\"스켈레톤\\n\",\"bold\":true},skills:{timer:100}}", "set");

        // summon skeleton ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_skeleton,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton,enemy.text],text:[{"color":"dark_green","text":"스켈레톤\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "skeleton", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_skeleton,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"스켈레톤\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_4d607dd4_enemy_spawn_model_forest_temple_infinity_split_zombie_execute(ServerCommandSource source) {
        _m_4d607dd4_enemy_spawn_model_forest_temple_infinity_split_zombie_executeReturn(source);
    }

    public static int _m_4d607dd4_enemy_spawn_model_forest_temple_infinity_split_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:2000, speed:0.13, money:135, id:split-zombie,name:{color:dark_purple,"text":"분열 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:2000, speed:0.13, money:135, id:split-zombie,name:{color:dark_purple,\"text\":\"분열 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:slime_block}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"분열 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:slime_block}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"분열 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_deead11c_enemy_spawn_model_forest_temple_infinity_tank_zombie_execute(ServerCommandSource source) {
        _m_deead11c_enemy_spawn_model_forest_temple_infinity_tank_zombie_executeReturn(source);
    }

    public static int _m_deead11c_enemy_spawn_model_forest_temple_infinity_tank_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10000, speed:0.22, money:6500,id:tank-zombie,name:{color:white,"text":"탱크 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10000, speed:0.22, money:6500,id:tank-zombie,name:{color:white,\"text\":\"탱크 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_f1912da5_enemy_spawn_model_forest_temple_infinity_vindicator_execute(ServerCommandSource source) {
        _m_f1912da5_enemy_spawn_model_forest_temple_infinity_vindicator_executeReturn(source);
    }

    public static int _m_f1912da5_enemy_spawn_model_forest_temple_infinity_vindicator_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:550, speed:0.23, money:200,id:vindicator,name:{color:white,"text":"변명자\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:550, speed:0.23, money:200,id:vindicator,name:{color:white,\"text\":\"변명자\\n\",\"bold\":true}}", "set");

        // summon vindicator ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "vindicator", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_866a69de_enemy_spawn_model_forest_temple_infinity_warden_execute(ServerCommandSource source) {
        _m_866a69de_enemy_spawn_model_forest_temple_infinity_warden_executeReturn(source);
    }

    public static int _m_866a69de_enemy_spawn_model_forest_temple_infinity_warden_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:25000, speed:0.07, money:18000, defence:25, id:warden,name:{color:red,"text":"워든\n","bold":true}, skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:25000, speed:0.07, money:18000, defence:25, id:warden,name:{color:red,\"text\":\"워든\\n\",\"bold\":true}, skills:{timer:300}}", "set");

        // summon warden ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_warden,enemy.target,enemy.core,enemy.data,enemy.immune.stun,enemy.immune.freeze,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_warden,enemy.text],text:[{"color":"dark_green","text":"워든\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"movement_speed",base:0.0}],Brain:{memories:{"minecraft:dig_cooldown":{value:{},ttl:999999L}}}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "warden", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_warden,enemy.target,enemy.core,enemy.data,enemy.immune.stun,enemy.immune.freeze,enemy.immune.poison],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_warden,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"워든\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"movement_speed\",base:0.0}],Brain:{memories:{\"minecraft:dig_cooldown\":{value:{},ttl:999999L}}}}"); }
        return 0;
    }

    public static void _m_024479f1_enemy_spawn_model_forest_temple_infinity_witch_execute(ServerCommandSource source) {
        _m_024479f1_enemy_spawn_model_forest_temple_infinity_witch_executeReturn(source);
    }

    public static int _m_024479f1_enemy_spawn_model_forest_temple_infinity_witch_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:500, speed:0.09, defence:40, money:550,id:witch,name:{color:dark_purple,"text":"마녀\n","bold":true}, skills:{low_hp:499}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:500, speed:0.09, defence:40, money:550,id:witch,name:{color:dark_purple,\"text\":\"마녀\\n\",\"bold\":true}, skills:{low_hp:499}}", "set");

        // summon witch ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"마녀\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "witch", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"마녀\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_a180db34_enemy_spawn_model_forest_temple_infinity_wither_skeleton_execute(ServerCommandSource source) {
        _m_a180db34_enemy_spawn_model_forest_temple_infinity_wither_skeleton_executeReturn(source);
    }

    public static int _m_a180db34_enemy_spawn_model_forest_temple_infinity_wither_skeleton_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:4500, speed:0.17, money:300,id:wither-skeleton,name:{color:white,"text":"위더 스켈레톤\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:4500, speed:0.17, money:300,id:wither-skeleton,name:{color:white,\"text\":\"위더 스켈레톤\\n\",\"bold\":true}}", "set");

        // summon wither_skeleton ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.text],text:[{"color":"dark_green","text":"wither_skeleton\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "wither_skeleton", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_wither_skeleton,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"wither_skeleton\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_212d15d0_enemy_spawn_model_forest_temple_infinity_zoglin_execute(ServerCommandSource source) {
        _m_212d15d0_enemy_spawn_model_forest_temple_infinity_zoglin_executeReturn(source);
    }

    public static int _m_212d15d0_enemy_spawn_model_forest_temple_infinity_zoglin_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:350, speed:0.13, money:125,id:zoglin,name:{color:white,"text":"조글린\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:350, speed:0.13, money:125,id:zoglin,name:{color:white,\"text\":\"조글린\\n\",\"bold\":true}}", "set");

        // summon zoglin ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zoglin,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zoglin", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zoglin,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_95e04cd1_enemy_spawn_model_forest_temple_infinity_zombie_execute(ServerCommandSource source) {
        _m_95e04cd1_enemy_spawn_model_forest_temple_infinity_zombie_executeReturn(source);
    }

    public static int _m_95e04cd1_enemy_spawn_model_forest_temple_infinity_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:8, speed:0.1, money:8,id:zombie,name:{color:white,"text":"좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:8, speed:0.1, money:8,id:zombie,name:{color:white,\"text\":\"좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_135e496b_enemy_spawn_model_forest_temple_normal_breeze_execute(ServerCommandSource source) {
        _m_135e496b_enemy_spawn_model_forest_temple_normal_breeze_executeReturn(source);
    }

    public static int _m_135e496b_enemy_spawn_model_forest_temple_normal_breeze_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:400, speed:0.22, defence:25,money:400,id:breeze,name:{color:white,"text":"브리즈\n","bold":true}, skills:{timer:100}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:400, speed:0.22, defence:25,money:400,id:breeze,name:{color:white,\"text\":\"브리즈\\n\",\"bold\":true}, skills:{timer:100}}", "set");

        // summon breeze ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_breeze,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_breeze,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "breeze", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_breeze,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_breeze,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_d7ccd94f_enemy_spawn_model_forest_temple_normal_catalyst_zombie_execute(ServerCommandSource source) {
        _m_d7ccd94f_enemy_spawn_model_forest_temple_normal_catalyst_zombie_executeReturn(source);
    }

    public static int _m_d7ccd94f_enemy_spawn_model_forest_temple_normal_catalyst_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1600, speed:0.1, money:1435,id:catalyst-zombie,name:{color:white,"text":"촉매 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1600, speed:0.1, money:1435,id:catalyst-zombie,name:{color:white,\"text\":\"촉매 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,catalyst-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,catalyst-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_76d49e46_enemy_spawn_model_forest_temple_normal_crimson_zombie_execute(ServerCommandSource source) {
        _m_76d49e46_enemy_spawn_model_forest_temple_normal_crimson_zombie_executeReturn(source);
    }

    public static int _m_76d49e46_enemy_spawn_model_forest_temple_normal_crimson_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:4000, speed:0.17, defence:75, money:4000,id:crimson-zombie,name:{color:dark_purple,"text":"진홍빛 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:4000, speed:0.17, defence:75, money:4000,id:crimson-zombie,name:{color:dark_purple,\"text\":\"진홍빛 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.text],text:[{"color":"dark_green","text":"진홍빛 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:2}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"진홍빛 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:2}]}"); }
        return 0;
    }

    public static void _m_cc2ce476_enemy_spawn_model_forest_temple_normal_healing_zombie_execute(ServerCommandSource source) {
        _m_cc2ce476_enemy_spawn_model_forest_temple_normal_healing_zombie_executeReturn(source);
    }

    public static int _m_cc2ce476_enemy_spawn_model_forest_temple_normal_healing_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:200, speed:0.07, defence:15, money:300,id:healing-zombie,name:{color:white,"text":"힐링 좀비\n","bold":true},attribute:{healing:1}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:200, speed:0.07, defence:15, money:300,id:healing-zombie,name:{color:white,\"text\":\"힐링 좀비\\n\",\"bold\":true},attribute:{healing:1}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:leather_helmet,components:{dyed_color:655104}},chest:{id:leather_chestplate,components:{dyed_color:655104}}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:leather_helmet,components:{dyed_color:655104}},chest:{id:leather_chestplate,components:{dyed_color:655104}}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_3bf260e4_enemy_spawn_model_forest_temple_normal_heavy_husk_execute(ServerCommandSource source) {
        _m_3bf260e4_enemy_spawn_model_forest_temple_normal_heavy_husk_executeReturn(source);
    }

    public static int _m_3bf260e4_enemy_spawn_model_forest_temple_normal_heavy_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:80, speed:0.08, money:75, defence:25,id:heavy-husk,name:{color:white,"text":"헤비 허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:80, speed:0.08, money:75, defence:25,id:heavy-husk,name:{color:white,\"text\":\"헤비 허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:iron_helmet},chest:{id:iron_chestplate}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_9989fd30_enemy_spawn_model_forest_temple_normal_horse_zombie_execute(ServerCommandSource source) {
        _m_9989fd30_enemy_spawn_model_forest_temple_normal_horse_zombie_executeReturn(source);
    }

    public static int _m_9989fd30_enemy_spawn_model_forest_temple_normal_horse_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:1000, speed:0.25, defence:25, money:1000,id:horse-zombie,name:{color:dark_purple,"text":"기마 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:1000, speed:0.25, defence:25, money:1000,id:horse-zombie,name:{color:dark_purple,\"text\":\"기마 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie_horse ~ ~ ~ {equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{"color":"dark_green","text":"기마 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:"zombie",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie_horse", _sp.x, _sp.y, _sp.z, "{equipment:{chest:{id:iron_horse_armor}},Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_skeleton_horse,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"기마 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.25f,0f],scale:[1f,1f,1f]}},{id:\"zombie\",Tags:[enemy,enemy.hitbox_type_skeleton_horse],NoAI:1b,Invulnerable:1b,equipment:{head:{id:netherite_helmet},chest:{id:netherite_chestplate},mainhand:{id:netherite_sword},feet:{id:netherite_boots},legs:{id:netherite_leggings}}}]}"); }
        return 0;
    }

    public static void _m_238269f5_enemy_spawn_model_forest_temple_normal_husk_execute(ServerCommandSource source) {
        _m_238269f5_enemy_spawn_model_forest_temple_normal_husk_executeReturn(source);
    }

    public static int _m_238269f5_enemy_spawn_model_forest_temple_normal_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:10, speed:0.1, money:10,id:husk,name:{color:white,"text":"허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:10, speed:0.1, money:10,id:husk,name:{color:white,\"text\":\"허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_c3722221_enemy_spawn_model_forest_temple_normal_indura_zombie_execute(ServerCommandSource source) {
        _m_c3722221_enemy_spawn_model_forest_temple_normal_indura_zombie_executeReturn(source);
    }

    public static int _m_c3722221_enemy_spawn_model_forest_temple_normal_indura_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:325, speed:0.2, defence:50, money:300,id:indura-zombie,name:{color:white,"text":"인듀라 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:325, speed:0.2, defence:50, money:300,id:indura-zombie,name:{color:white,\"text\":\"인듀라 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {equipment:{head:{id:black_shulker_box},chest:{id:netherite_chestplate,components:{trim:{material:quartz,pattern:eye}},count:1b}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{equipment:{head:{id:black_shulker_box},chest:{id:netherite_chestplate,components:{trim:{material:quartz,pattern:eye}},count:1b}},Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.death,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_4386711a_enemy_spawn_model_forest_temple_normal_phantom_execute(ServerCommandSource source) {
        _m_4386711a_enemy_spawn_model_forest_temple_normal_phantom_executeReturn(source);
    }

    public static int _m_4386711a_enemy_spawn_model_forest_temple_normal_phantom_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:135, speed:0.23, money:125,id:phantom,name:{color:white,"text":"팬텀\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:135, speed:0.23, money:125,id:phantom,name:{color:white,\"text\":\"팬텀\\n\",\"bold\":true}}", "set");

        // summon phantom ~ ~1 ~ {Tags:[enemy,enemy.hitbox_type_phantom,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_phantom,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.summon(ctx.world, "phantom", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_phantom,enemy.target,enemy.core,enemy.data,enemy.immune.bleed,enemy.immune.freeze],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_phantom,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_6c2c75b3_enemy_spawn_model_forest_temple_normal_quick_husk_execute(ServerCommandSource source) {
        _m_6c2c75b3_enemy_spawn_model_forest_temple_normal_quick_husk_executeReturn(source);
    }

    public static int _m_6c2c75b3_enemy_spawn_model_forest_temple_normal_quick_husk_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:18, speed:0.2, money:18,id:quick-husk,name:{color:white,"text":"퀵 허스크\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:18, speed:0.2, money:18,id:quick-husk,name:{color:white,\"text\":\"퀵 허스크\\n\",\"bold\":true}}", "set");

        // summon husk ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "husk", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_a87049de_enemy_spawn_model_forest_temple_normal_sculk_titan_execute(ServerCommandSource source) {
        _m_a87049de_enemy_spawn_model_forest_temple_normal_sculk_titan_executeReturn(source);
    }

    public static int _m_a87049de_enemy_spawn_model_forest_temple_normal_sculk_titan_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:55000, speed:0.06, defence:100, money:39000,id:sculk-titan,name:{color:red,"text":"스컬크 타이탄\n","bold":true},skills:{timer:300}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:55000, speed:0.06, defence:100, money:39000,id:sculk-titan,name:{color:red,\"text\":\"스컬크 타이탄\\n\",\"bold\":true},skills:{timer:300}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.type.sculk-titan,enemy.skill-loop.1,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:scale,base:2.0}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_2,enemy.target,enemy.core,enemy.data,enemy.type.sculk-titan,enemy.skill-loop.1,enemy.immune.poison,enemy.immune.freeze,enemy.immune.stun],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:scale,base:2.0}]}"); }
        return 0;
    }

    public static void _m_6eba75f2_enemy_spawn_model_forest_temple_normal_skulk_zombie_execute(ServerCommandSource source) {
        _m_6eba75f2_enemy_spawn_model_forest_temple_normal_skulk_zombie_executeReturn(source);
    }

    public static int _m_6eba75f2_enemy_spawn_model_forest_temple_normal_skulk_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:600, speed:0.15, money:425,id:skulk-zombie,name:{color:white,"text":"스컬크 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:600, speed:0.15, money:425,id:skulk-zombie,name:{color:white,\"text\":\"스컬크 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,skulk-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,skulk-zombie],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_081ab4a7_enemy_spawn_model_forest_temple_normal_warped_zombie_execute(ServerCommandSource source) {
        _m_081ab4a7_enemy_spawn_model_forest_temple_normal_warped_zombie_executeReturn(source);
    }

    public static int _m_081ab4a7_enemy_spawn_model_forest_temple_normal_warped_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:4000, speed:0.08, defence:75, money:3000,id:warped-zombie,name:{color:dark_purple,"text":"뒤틀린 좀비\n","bold":true}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:4000, speed:0.08, defence:75, money:3000,id:warped-zombie,name:{color:dark_purple,\"text\":\"뒤틀린 좀비\\n\",\"bold\":true}}", "set");

        // summon zombie ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_zombie_scale_1_8,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.text],text:[{"color":"dark_green","text":"뒤틀린 좀비\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:"minecraft:scale",base:1.8}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "zombie", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_zombie_scale_1_8,enemy.target,enemy.core,enemy.data,enemy.immune.flame],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"뒤틀린 좀비\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}],attributes:[{id:\"minecraft:scale\",base:1.8}]}"); }
        return 0;
    }

    public static void _m_519c3832_enemy_spawn_model_forest_temple_normal_witch_execute(ServerCommandSource source) {
        _m_519c3832_enemy_spawn_model_forest_temple_normal_witch_executeReturn(source);
    }

    public static int _m_519c3832_enemy_spawn_model_forest_temple_normal_witch_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy data set value {health:500, speed:0.09, defence:40, money:550,id:witch,name:{color:dark_purple,"text":"마녀\n","bold":true}, skills:{low_hp:499}}
        KfcGen.storagePutSnbt(server, "enemy", "data", "{health:500, speed:0.09, defence:40, money:550,id:witch,name:{color:dark_purple,\"text\":\"마녀\\n\",\"bold\":true}, skills:{low_hp:499}}", "set");

        // summon witch ~ ~ ~ {Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:"minecraft:text_display",billboard:"vertical",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{"color":"dark_green","text":"마녀\n","bold":true},{"color":"red","text":"Hp"},{"color":"white","text":":"},{"color":"yellow",text:"None"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "witch", _sp.x, _sp.y, _sp.z, "{Tags:[enemy,enemy.hitbox_type_drowned,enemy.target,enemy.core,enemy.data,enemy.skill-trigger.low-hp],NoAI:1b,Invulnerable:1b,Passengers:[{id:\"minecraft:text_display\",billboard:\"vertical\",Tags:[enemy,enemy.hitbox_type_drowned,enemy.text],text:[{\"color\":\"dark_green\",\"text\":\"마녀\\n\",\"bold\":true},{\"color\":\"red\",\"text\":\"Hp\"},{\"color\":\"white\",\"text\":\":\"},{\"color\":\"yellow\",text:\"None\"}],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1f,1f,1f]}}]}"); }
        return 0;
    }

    public static void _m_3309be71_enemy_spawn_summon_allocate_execute(ServerCommandSource source) {
        _m_3309be71_enemy_spawn_summon_allocate_executeReturn(source);
    }

    public static int _m_3309be71_enemy_spawn_summon_allocate_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify entity @s data set from storage enemy data
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetStorage(server, "enemy", "data"); if (_v != null) KfcGen.nbtSetEntity(executor, "data", _v); }

        // execute store result score @s enemy.hp run data get storage enemy data.health 1.0
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", (int)(KfcGen.storageGetDouble(server, "enemy", "data.health")));

        // scoreboard players operation @s enemy.max_hp = @s enemy.hp
        if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.max_hp", "=", executor.getNameForScoreboard(), "enemy.hp");

        // execute store result score @s enemy.defence run data get storage enemy data.defence 1.0
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.defence", (int)(KfcGen.storageGetDouble(server, "enemy", "data.defence")));

        // execute store result score @s enemy.attribute.healing run data get storage enemy data.attribute.healing 1.0
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.attribute.healing", (int)(KfcGen.storageGetDouble(server, "enemy", "data.attribute.healing")));

        // execute store result score @s enemy.attribute.dealing run data get storage enemy data.attribute.dealing 1.0
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.attribute.dealing", (int)(KfcGen.storageGetDouble(server, "enemy", "data.attribute.dealing")));

        // data modify entity @s PersistenceRequired set value 1b
        if (executor != null) KfcGen.entityPutSnbt(executor, "PersistenceRequired", "1b");

        // effect give @s instant_health infinite 0 true
        if (executor != null) KfcGen.effectGive(executor, "instant_health", -1, 0, false);

        // effect give @s resistance infinite 4 true
        if (executor != null) KfcGen.effectGive(executor, "resistance", -1, 4, false);

        // scoreboard players set @s enemy.attribute.timer 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.attribute.timer", 0);

        // scoreboard players set @s enemy.progress 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.progress", 0);

        // team join noColison @s
        if (executor != null) KfcGen.teamJoin(sb, "noColison", executor.getNameForScoreboard());

        // scoreboard players set @s enemy.state.freeze 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.freeze", 0);

        // scoreboard players set @s enemy.state.stun 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.stun", 0);

        // scoreboard players set @s enemy.state.poison 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.poison", 0);

        // scoreboard players set @s enemy.state.flame 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.flame", 0);

        // scoreboard players set @s enemy.state.bleed 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.bleed", 0);

        // scoreboard players set @s enemy.state.corruption 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.state.corruption", 0);

        // execute if data storage enemy data.skills.low_hp store result score @s enemy.skill-trigger.hp run data get storage enemy data.skills.low_hp 1.0
        if (KfcGen.storageHasPath(source.getServer(), "enemy", "data.skills.low_hp")) {
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.hp", (int)(KfcGen.storageGetDouble(server, "enemy", "data.skills.low_hp")));
        }

        // execute if data storage enemy data.skills.timer store result score @s enemy.skill-trigger.timer run data get storage enemy data.skills.timer 1.0
        if (KfcGen.storageHasPath(source.getServer(), "enemy", "data.skills.timer")) {
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer", (int)(KfcGen.storageGetDouble(server, "enemy", "data.skills.timer")));
        }

        // execute if data storage enemy data.skills.timer run scoreboard players operation @s enemy.skill-trigger.timer-cooldown = @s enemy.skill-trigger.timer
        if (KfcGen.storageHasPath(source.getServer(), "enemy", "data.skills.timer")) {
            if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", "=", executor.getNameForScoreboard(), "enemy.skill-trigger.timer");
        }

        // execute if score game game.difficulty matches 5 run function game:infinity/apply_enemy_bonus
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 5, 5)) {
            // -> game:infinity/apply_enemy_bonus
            tdpack.buckets.Bucket7._m_bcb76b95_game_infinity_apply_enemy_bonus_execute(source);
        }

        // data modify entity @s Rotation set from entity @n[tag=map.spawn_point] Rotation
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.spawn_point"}, new String[0], -1, -1, _ee -> (true)), "Rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "Rotation", _v); }

        // execute on passengers at @s run data modify entity @s Rotation set from entity @n[tag=map.spawn_point] Rotation
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            {
                ServerCommandSource kfcSrc1 = (_onEnt1 != null ? _on1.withPosition(_onEnt1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_onEnt1.getPitch(), _onEnt1.getYaw())) : _on1);
                { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc1.getPosition(), new String[]{"map.spawn_point"}, new String[0], -1, -1, _ee -> (true)), "Rotation"); if (_v != null) KfcGen.nbtSetEntity(_onEnt1, "Rotation", _v); }
            }
        }

        // scoreboard players set @s enemy.onGround 50
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.onGround", 50);

        // execute if score @n[tag=map.spawn_point] enemy.progress matches 1.. run scoreboard players operation @s enemy.progress = @n[tag=map.spawn_point] enemy.progress
        if (KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.spawn_point"}, new String[0], -1, -1, _ee -> (true)), "enemy.progress", 1, Integer.MAX_VALUE, false)) {
            { net.minecraft.entity.Entity _s = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.spawn_point"}, new String[0], -1, -1, _ee -> (true)); if (executor != null && _s != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.progress", "=", _s.getNameForScoreboard(), "enemy.progress"); }
        }

        // scoreboard players add global enemy.number 1
        KfcGen.addScore(sb, "global", "enemy.number", 1);

        // scoreboard players operation @s enemy.number = global enemy.number
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.number", "=", "global", "enemy.number");
        return 0;
    }

    public static void _m_5a993352_enemy_spawn_summon_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_5a993352_enemy_spawn_summon_main_executeReturn(source, macroArgs);
    }

    public static int _m_5a993352_enemy_spawn_summon_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $function enemy:spawn/model/$(model)
        // -> enemy:spawn/model/macroArgs.get("model")
        switch (macroArgs.get("model")) {
            case "armord-zombie": tdpack.buckets.Bucket5._m_426f2211_enemy_spawn_model_armord_zombie_execute(source); break;
            case "bogged": tdpack.buckets.Bucket5._m_7dce365e_enemy_spawn_model_bogged_execute(source); break;
            case "breeze": tdpack.buckets.Bucket5._m_09a7e34f_enemy_spawn_model_breeze_execute(source); break;
            case "bright-zombie": tdpack.buckets.Bucket5._m_19d190eb_enemy_spawn_model_bright_zombie_execute(source); break;
            case "carsinops": tdpack.buckets.Bucket5._m_2c1f1a87_enemy_spawn_model_carsinops_execute(source); break;
            case "catalyst-zombie": tdpack.buckets.Bucket5._m_ddd56cca_enemy_spawn_model_catalyst_zombie_execute(source); break;
            case "cave-spider": tdpack.buckets.Bucket5._m_176bef65_enemy_spawn_model_cave_spider_execute(source); break;
            case "charged-creeper": tdpack.buckets.Bucket5._m_367c76db_enemy_spawn_model_charged_creeper_execute(source); break;
            case "corrupted-drowned": tdpack.buckets.Bucket5._m_dbe2de6f_enemy_spawn_model_corrupted_drowned_execute(source); break;
            case "corrupted-husk": tdpack.buckets.Bucket5._m_c94e1d4a_enemy_spawn_model_corrupted_husk_execute(source); break;
            case "corrupted-zombie": tdpack.buckets.Bucket5._m_37e87b30_enemy_spawn_model_corrupted_zombie_execute(source); break;
            case "crawler": tdpack.buckets.Bucket5._m_ca771912_enemy_spawn_model_crawler_execute(source); break;
            case "creeper": tdpack.buckets.Bucket5._m_cec2ac3c_enemy_spawn_model_creeper_execute(source); break;
            case "crimson-zombie": tdpack.buckets.Bucket5._m_239b2b65_enemy_spawn_model_crimson_zombie_execute(source); break;
            case "dark": tdpack.buckets.Bucket5._m_ccd0d764_enemy_spawn_model_dark_execute(source); break;
            case "dark-dash-zombie": tdpack.buckets.Bucket5._m_2488a1de_enemy_spawn_model_dark_dash_zombie_execute(source); break;
            case "dark-guardian": tdpack.buckets.Bucket5._m_3cfe6080_enemy_spawn_model_dark_guardian_execute(source); break;
            case "dark-headbomb": tdpack.buckets.Bucket5._m_f3c7ab23_enemy_spawn_model_dark_headbomb_execute(source); break;
            case "dark-knight": tdpack.buckets.Bucket5._m_2e483042_enemy_spawn_model_dark_knight_execute(source); break;
            case "dark-mist": tdpack.buckets.Bucket5._m_d1c0ab69_enemy_spawn_model_dark_mist_execute(source); break;
            case "dark-walker": tdpack.buckets.Bucket5._m_b88c18e8_enemy_spawn_model_dark_walker_execute(source); break;
            case "darkness-soron": tdpack.buckets.Bucket5._m_98bce5ba_enemy_spawn_model_darkness_soron_execute(source); break;
            case "dash-zombie": tdpack.buckets.Bucket5._m_b1e0c83a_enemy_spawn_model_dash_zombie_execute(source); break;
            case "drowned": tdpack.buckets.Bucket5._m_af1616e9_enemy_spawn_model_drowned_execute(source); break;
            case "elite-drowned": tdpack.buckets.Bucket5._m_f2b42990_enemy_spawn_model_elite_drowned_execute(source); break;
            case "enderman": tdpack.buckets.Bucket5._m_be3d2943_enemy_spawn_model_enderman_execute(source); break;
            case "endermite": tdpack.buckets.Bucket5._m_06732c5b_enemy_spawn_model_endermite_execute(source); break;
            case "endermite-baby": tdpack.buckets.Bucket5._m_8e1b2d5c_enemy_spawn_model_endermite_baby_execute(source); break;
            case "endermite-egg": tdpack.buckets.Bucket5._m_f013c4fd_enemy_spawn_model_endermite_egg_execute(source); break;
            case "evoker": tdpack.buckets.Bucket5._m_d1307819_enemy_spawn_model_evoker_execute(source); break;
            case "giant": tdpack.buckets.Bucket5._m_35a1d023_enemy_spawn_model_giant_execute(source); break;
            case "healing-crystal": tdpack.buckets.Bucket5._m_b5b4cb42_enemy_spawn_model_healing_crystal_execute(source); break;
            case "healing-zombie": tdpack.buckets.Bucket5._m_6f58d4bb_enemy_spawn_model_healing_zombie_execute(source); break;
            case "heavy-dark": tdpack.buckets.Bucket5._m_7de1b50a_enemy_spawn_model_heavy_dark_execute(source); break;
            case "heavy-husk": tdpack.buckets.Bucket5._m_ff6a6601_enemy_spawn_model_heavy_husk_execute(source); break;
            case "heavy-zombie": tdpack.buckets.Bucket5._m_515f242c_enemy_spawn_model_heavy_zombie_execute(source); break;
            case "hidden-zombie": tdpack.buckets.Bucket5._m_774e1877_enemy_spawn_model_hidden_zombie_execute(source); break;
            case "horse-skeleton": tdpack.buckets.Bucket5._m_22dade0f_enemy_spawn_model_horse_skeleton_execute(source); break;
            case "horse-zombie": tdpack.buckets.Bucket5._m_e41902a5_enemy_spawn_model_horse_zombie_execute(source); break;
            case "hp-zombie": tdpack.buckets.Bucket5._m_ae24a1f3_enemy_spawn_model_hp_zombie_execute(source); break;
            case "husk": tdpack.buckets.Bucket5._m_f99dcf5a_enemy_spawn_model_husk_execute(source); break;
            case "illusioner": tdpack.buckets.Bucket5._m_fb3e3599_enemy_spawn_model_illusioner_execute(source); break;
            case "illusioner-illusion": tdpack.buckets.Bucket5._m_33e97b94_enemy_spawn_model_illusioner_illusion_execute(source); break;
            case "indura-stone": tdpack.buckets.Bucket5._m_78606879_enemy_spawn_model_indura_stone_execute(source); break;
            case "indura-zombie": tdpack.buckets.Bucket5._m_bc379fc5_enemy_spawn_model_indura_zombie_execute(source); break;
            case "necter": tdpack.buckets.Bucket5._m_b32fece6_enemy_spawn_model_necter_execute(source); break;
            case "phantom": tdpack.buckets.Bucket5._m_99dbe4d4_enemy_spawn_model_phantom_execute(source); break;
            case "pillager": tdpack.buckets.Bucket5._m_834560f8_enemy_spawn_model_pillager_execute(source); break;
            case "quick-husk": tdpack.buckets.Bucket5._m_2cd47ab7_enemy_spawn_model_quick_husk_execute(source); break;
            case "quick-zombie": tdpack.buckets.Bucket5._m_ed8d7ff7_enemy_spawn_model_quick_zombie_execute(source); break;
            case "rabbit": tdpack.buckets.Bucket5._m_93f3e7cc_enemy_spawn_model_rabbit_execute(source); break;
            case "raid-guardian": tdpack.buckets.Bucket5._m_5a04187b_enemy_spawn_model_raid_guardian_execute(source); break;
            case "raid-lord": tdpack.buckets.Bucket5._m_9d9a76db_enemy_spawn_model_raid_lord_execute(source); break;
            case "ravager": tdpack.buckets.Bucket6._m_b4ea114c_enemy_spawn_model_ravager_execute(source); break;
            case "revive-zombie": tdpack.buckets.Bucket6._m_ea7946db_enemy_spawn_model_revive_zombie_execute(source); break;
            case "revived-zombie": tdpack.buckets.Bucket6._m_a1340cea_enemy_spawn_model_revived_zombie_execute(source); break;
            case "scream-zombie": tdpack.buckets.Bucket6._m_22ce2a09_enemy_spawn_model_scream_zombie_execute(source); break;
            case "sculk-titan": tdpack.buckets.Bucket6._m_2e3281cc_enemy_spawn_model_sculk_titan_execute(source); break;
            case "shiled-zombie": tdpack.buckets.Bucket6._m_0f5c2b3d_enemy_spawn_model_shiled_zombie_execute(source); break;
            case "silverfish": tdpack.buckets.Bucket6._m_dd0645c2_enemy_spawn_model_silverfish_execute(source); break;
            case "silverfish-baby": tdpack.buckets.Bucket6._m_234ab0d4_enemy_spawn_model_silverfish_baby_execute(source); break;
            case "silverfish-egg": tdpack.buckets.Bucket6._m_e966b1d9_enemy_spawn_model_silverfish_egg_execute(source); break;
            case "skeleton": tdpack.buckets.Bucket6._m_b72a8a25_enemy_spawn_model_skeleton_execute(source); break;
            case "skulk-zombie": tdpack.buckets.Bucket6._m_80358231_enemy_spawn_model_skulk_zombie_execute(source); break;
            case "spider": tdpack.buckets.Bucket6._m_916ba7f4_enemy_spawn_model_spider_execute(source); break;
            case "split-zombie": tdpack.buckets.Bucket6._m_ada594d4_enemy_spawn_model_split_zombie_execute(source); break;
            case "split-zombie-splits": tdpack.buckets.Bucket6._m_f90319db_enemy_spawn_model_split_zombie_splits_execute(source); break;
            case "stray": tdpack.buckets.Bucket6._m_0607c3b9_enemy_spawn_model_stray_execute(source); break;
            case "tank-zombie": tdpack.buckets.Bucket6._m_b244d026_enemy_spawn_model_tank_zombie_execute(source); break;
            case "vex": tdpack.buckets.Bucket6._m_eaaf1c45_enemy_spawn_model_vex_execute(source); break;
            case "vindicator": tdpack.buckets.Bucket6._m_afa59a4a_enemy_spawn_model_vindicator_execute(source); break;
            case "warden": tdpack.buckets.Bucket6._m_90aac8d1_enemy_spawn_model_warden_execute(source); break;
            case "warped-zombie": tdpack.buckets.Bucket6._m_40f9583b_enemy_spawn_model_warped_zombie_execute(source); break;
            case "witch": tdpack.buckets.Bucket6._m_e923c29b_enemy_spawn_model_witch_execute(source); break;
            case "wither-skeleton": tdpack.buckets.Bucket6._m_4b611dce_enemy_spawn_model_wither_skeleton_execute(source); break;
            case "zoglin": tdpack.buckets.Bucket6._m_82f7929e_enemy_spawn_model_zoglin_execute(source); break;
            case "zombie": tdpack.buckets.Bucket6._m_2795ff4f_enemy_spawn_model_zombie_execute(source); break;
            case "zombie-villager": tdpack.buckets.Bucket6._m_97bf4c8a_enemy_spawn_model_zombie_villager_execute(source); break;
            default: break;
        }

        // execute as @e[tag=enemy.data] unless entity @s[scores={enemy.number=0..}] run function enemy:spawn/summon/allocate
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("enemy.data"))) continue;
            ServerCommandSource es = source.withEntity(e);
            if (!(!((e != null && KfcGen.scoreMatches(sb, e.getNameForScoreboard(), "enemy.number", 0, Integer.MAX_VALUE))))) continue;
            // -> enemy:spawn/summon/allocate
            tdpack.buckets.Bucket6._m_3309be71_enemy_spawn_summon_allocate_execute(es);
        }
        return 0;
    }

    public static void _m_a2c1fafb_game_load_execute(ServerCommandSource source) {
        _m_a2c1fafb_game_load_executeReturn(source);
    }

    public static int _m_a2c1fafb_game_load_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard objectives add money dummy
        KfcGen.ensureObjective(sb, "money", "dummy");

        // scoreboard objectives add coins dummy
        KfcGen.ensureObjective(sb, "coins", "dummy");

        // scoreboard objectives add wave dummy
        KfcGen.ensureObjective(sb, "wave", "dummy");

        // scoreboard objectives add killCount dummy
        KfcGen.ensureObjective(sb, "killCount", "dummy");

        // scoreboard objectives add game.base_health dummy
        KfcGen.ensureObjective(sb, "game.base_health", "dummy");

        // scoreboard objectives add time dummy
        KfcGen.ensureObjective(sb, "time", "dummy");

        // scoreboard objectives add gameState dummy
        KfcGen.ensureObjective(sb, "gameState", "dummy");

        // scoreboard objectives add game.return dummy
        KfcGen.ensureObjective(sb, "game.return", "dummy");

        // scoreboard objectives add player.id dummy
        KfcGen.ensureObjective(sb, "player.id", "dummy");

        // scoreboard objectives add player.last_upgrade_id dummy
        KfcGen.ensureObjective(sb, "player.last_upgrade_id", "dummy");

        // scoreboard objectives add game.difficulty dummy
        KfcGen.ensureObjective(sb, "game.difficulty", "dummy");

        // scoreboard objectives add game.enemy_hp_multiply dummy
        KfcGen.ensureObjective(sb, "game.enemy_hp_multiply", "dummy");

        // scoreboard objectives add game.enemy_speed_multiply dummy
        KfcGen.ensureObjective(sb, "game.enemy_speed_multiply", "dummy");

        // scoreboard objectives add game.enemy_def_multiply dummy
        KfcGen.ensureObjective(sb, "game.enemy_def_multiply", "dummy");

        // scoreboard objectives add statistics dummy
        KfcGen.ensureObjective(sb, "statistics", "dummy");

        // scoreboard objectives modify statistics displayname {text:"통계"}
        KfcGen.setObjectiveDisplay(sb, server, "statistics", "{text:\"통계\"}");

        // scoreboard objectives add info dummy
        KfcGen.ensureObjective(sb, "info", "dummy");

        // scoreboard objectives modify info displayname {text:"소론",color:"dark_purple",shadow_color:-99999999}
        KfcGen.setObjectiveDisplay(sb, server, "info", "{text:\"소론\",color:\"dark_purple\",shadow_color:-99999999}");

        // execute unless score coin info matches 0.. run scoreboard players set coin info 0
        if (!(KfcGen.scoreMatches(sb, "coin", "info", 0, Integer.MAX_VALUE))) {
            KfcGen.setScore(sb, "coin", "info", 0);
        }

        // scoreboard players display name coin info {text:"코인",color:yellow,bold:true}
        KfcGen.displayScoreName(source, sb, "coin", "info", "{text:\"코인\",color:yellow,bold:true}");

        // execute unless score killcount info matches 0.. run scoreboard players set killcount info 0
        if (!(KfcGen.scoreMatches(sb, "killcount", "info", 0, Integer.MAX_VALUE))) {
            KfcGen.setScore(sb, "killcount", "info", 0);
        }

        // scoreboard players display name killcount info {text:"킬카운트",color:red,bold:true}
        KfcGen.displayScoreName(source, sb, "killcount", "info", "{text:\"킬카운트\",color:red,bold:true}");

        // execute as @a unless score @s player.id matches 0.. run scoreboard players add global player.id 1
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", 0, Integer.MAX_VALUE)))) continue;
            KfcGen.addScore(sb, "global", "player.id", 1);
        }

        // execute as @a unless score @s player.id matches 0.. run scoreboard players operation @s player.id = global player.id
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", 0, Integer.MAX_VALUE)))) continue;
            if (e != null) KfcGen.opScore(sb, e.getNameForScoreboard(), "player.id", "=", "global", "player.id");
        }
        return 0;
    }

    public static void _m_a5c02ce3_game_tick_execute(ServerCommandSource source) {
        _m_a5c02ce3_game_tick_executeReturn(source);
    }

    public static int _m_a5c02ce3_game_tick_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score game gameState matches 1 run function game:ui/main
        if (KfcGen.scoreMatches(sb, "game", "gameState", 1, 1)) {
            // -> game:ui/main
            tdpack.buckets.Bucket7._m_c80f526c_game_ui_main_execute(source);
        }

        // execute if score game gameState matches 1 run function game:game/main
        if (KfcGen.scoreMatches(sb, "game", "gameState", 1, 1)) {
            // -> game:game/main
            tdpack.buckets.Bucket6._m_382f715f_game_game_main_execute(source);
        }

        // execute as @a unless score @s player.id matches 0.. run function game:player/init
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", 0, Integer.MAX_VALUE)))) continue;
            // -> game:player/init
            tdpack.buckets.Bucket7._m_6e8e204b_game_player_init_execute(es);
        }
        return 0;
    }

    public static void _m_79b0c32a_game_game_defeat_execute(ServerCommandSource source) {
        _m_79b0c32a_game_game_defeat_executeReturn(source);
    }

    public static int _m_79b0c32a_game_game_defeat_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set timer gameState 0
        KfcGen.setScore(sb, "timer", "gameState", 0);

        // scoreboard players set stage gameState 0
        KfcGen.setScore(sb, "stage", "gameState", 0);

        // title @a title {"text":"Defeat","color":"red","bold":true}
        for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
            KfcGen.titleText(_tp, source, "{\"text\":\"Defeat\",\"color\":\"red\",\"bold\":true}", false);
        }

        // execute store result score #temp info run data get storage game setup.each_money 0.1
        KfcGen.setScore(sb, "#temp", "info", (int)((KfcGen.storageGetDouble(server, "game", "setup.each_money")) * 0.1));

        // scoreboard players operation #temp info *= game wave
        KfcGen.opScore(sb, "#temp", "info", "*=", "game", "wave");

        // scoreboard players operation coin info += #temp info
        KfcGen.opScore(sb, "coin", "info", "+=", "#temp", "info");

        // title @a subtitle [{text:"패배 보상: ",color:green},{score:{name:"#temp",objective:info},color:yellow}]
        for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
            KfcGen.titleText(_tp, source, "[{text:\"패배 보상: \",color:green},{score:{name:\"#temp\",objective:info},color:yellow}]", true);
        }

        // playsound minecraft:item.goat_horn.sound.5 master @a ~ ~ ~ 1 1 1
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "minecraft:item.goat_horn.sound.5", "master", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
        }

        // scoreboard players set game gameState 0
        KfcGen.setScore(sb, "game", "gameState", 0);

        // kill @e[tag=enemy]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // kill @e[tag=tower]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // kill @e[tag=bullet]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"bullet"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // scoreboard objectives setdisplay sidebar info
        KfcGen.setObjectiveDisplaySlot(sb, "sidebar", "info");
        return 0;
    }

    public static void _m_15656f3b_game_game_init_execute(ServerCommandSource source) {
        _m_15656f3b_game_game_init_executeReturn(source);
    }

    public static int _m_15656f3b_game_game_init_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set timer gameState 0
        KfcGen.setScore(sb, "timer", "gameState", 0);

        // scoreboard players set stage gameState 0
        KfcGen.setScore(sb, "stage", "gameState", 0);
        return 0;
    }

    public static void _m_382f715f_game_game_main_execute(ServerCommandSource source) {
        _m_382f715f_game_game_main_executeReturn(source);
    }

    public static int _m_382f715f_game_game_main_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute unless score stage gameState matches 1.. run function game:game/ready with storage game setup
        if (!(KfcGen.scoreMatches(sb, "stage", "gameState", 1, Integer.MAX_VALUE))) {
            // -> game:game/ready
            tdpack.buckets.Bucket6._m_33ed0ea3_game_game_ready_execute(source, KfcGen.storageMacroArgs(server, "game", "setup"));
        }

        // execute if score stage gameState matches 1 run function game:game/round_ready
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 1, 1)) {
            // -> game:game/round_ready
            tdpack.buckets.Bucket7._m_a4ac5906_game_game_round_ready_execute(source);
        }

        // execute store result storage game setup.time int 1.0 run scoreboard players get timer gameState
        KfcGen.storagePutNumber(server, "game", "setup.time", KfcGen.getScore(sb, "timer", "gameState"), "int");

        // execute store result score #temp wave run scoreboard players get game wave
        KfcGen.setScore(sb, "#temp", "wave", KfcGen.getScore(sb, "game", "wave"));

        // scoreboard players remove #temp wave 1
        KfcGen.addScore(sb, "#temp", "wave", -(1));

        // execute store result storage game setup.round int 1.0 run scoreboard players get #temp wave
        KfcGen.storagePutNumber(server, "game", "setup.round", KfcGen.getScore(sb, "#temp", "wave"), "int");

        // execute if score stage gameState matches 2 run function game:game/round_progress with storage game setup
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 2, 2)) {
            // -> game:game/round_progress
            tdpack.buckets.Bucket6._m_10e94ee4_game_game_round_progress_execute(source, KfcGen.storageMacroArgs(server, "game", "setup"));
        }

        // execute if score game game.base_health matches ..0 run function game:game/defeat
        if (KfcGen.scoreMatches(sb, "game", "game.base_health", Integer.MIN_VALUE, 0)) {
            // -> game:game/defeat
            tdpack.buckets.Bucket6._m_79b0c32a_game_game_defeat_execute(source);
        }

        // execute if score stage gameState matches 3 run function game:game/round_wait with storage game setup
        if (KfcGen.scoreMatches(sb, "stage", "gameState", 3, 3)) {
            // -> game:game/round_wait
            tdpack.buckets.Bucket7._m_cc493260_game_game_round_wait_execute(source, KfcGen.storageMacroArgs(server, "game", "setup"));
        }

        // function game:game/show_stats with storage game setup
        // -> game:game/show_stats
        tdpack.buckets.Bucket7._m_04d71bda_game_game_show_stats_execute(source);

        // scoreboard players add timer gameState 1
        KfcGen.addScore(sb, "timer", "gameState", 1);
        return 0;
    }

    public static void _m_ec7d49b3_game_game_mine_income_execute(ServerCommandSource source) {
        _m_ec7d49b3_game_game_mine_income_executeReturn(source);
    }

    public static int _m_ec7d49b3_game_game_mine_income_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score @s tower.attack run data get entity @s data.attack
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.attack", (int)(KfcGen.entityGetDouble(executor, "data.attack")));

        // scoreboard players operation @a money += @s tower.attack
        { 
        if (executor != null) {
            for (net.minecraft.server.network.ServerPlayerEntity _od : ctx.allPlayers) {
                KfcGen.opScore(sb, _od.getNameForScoreboard(), "money", "+=", executor.getNameForScoreboard(), "tower.attack");
            }
        }
        }

        // execute at @s run playsound block.chain.break record @a ~ ~ ~ 1.0 1.0
        {
            ServerCommandSource kfcSrc6 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "block.chain.break", "record", new net.minecraft.util.math.Vec3d(kfcSrc6.getPosition().x, kfcSrc6.getPosition().y, kfcSrc6.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc6.getPosition().x, kfcSrc6.getPosition().y, kfcSrc6.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc6.getPosition().x, kfcSrc6.getPosition().y, kfcSrc6.getPosition().z).z, 1.0f, 1.0f);
            }
        }
        return 0;
    }

    public static void _m_33ed0ea3_game_game_ready_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_33ed0ea3_game_game_ready_executeReturn(source, macroArgs);
    }

    public static int _m_33ed0ea3_game_game_ready_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute unless score timer gameState matches 1.. run scoreboard players set game wave 0
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            KfcGen.setScore(sb, "game", "wave", 0);
        }

        // execute unless score timer gameState matches 1.. store result score @a money run data get storage game setup.money 1.0
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            { int _stv = (int)(KfcGen.storageGetDouble(server, "game", "setup.money")); for (net.minecraft.server.network.ServerPlayerEntity _se : ctx.allPlayers) if (KfcGen.entityHasTags(_se, new String[0], new String[0])) KfcGen.setScore(sb, _se.getNameForScoreboard(), "money", _stv); }
        }

        // execute unless score timer gameState matches 1.. run tag @e[tag=map.point] remove game
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAny(ctx, source.getPosition(), new String[]{"map.point"}, new String[0], -1, -1)) {
                _t.removeCommandTag("game");
            }
        }

        // execute unless score timer gameState matches 1.. run scoreboard players set game game.base_health 200
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            KfcGen.setScore(sb, "game", "game.base_health", 200);
        }

        // $execute unless score timer gameState matches 1.. run tag @e[tag=map.point,tag=map.$(map)] add game
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAny(ctx, source.getPosition(), new String[]{"map.point", "map." + macroArgs.get("map")}, new String[0], -1, -1)) {
                _t.addCommandTag("game");
            }
        }

        // execute unless score timer gameState matches 1.. run kill @e[tag=enemy]
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"enemy"}, new String[0], -1, -1)) {
                KfcGen.killEntity(_k);
            }
        }

        // execute unless score timer gameState matches 1.. run kill @e[tag=tower]
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower"}, new String[0], -1, -1)) {
                KfcGen.killEntity(_k);
            }
        }

        // execute unless score timer gameState matches 1.. run kill @e[tag=bullet]
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"bullet"}, new String[0], -1, -1)) {
                KfcGen.killEntity(_k);
            }
        }

        // execute unless score timer gameState matches 1.. run title @a title {text:"게임 준비",color:green,bold:true}
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"게임 준비\",color:green,bold:true}", false);
            }
        }

        // execute unless score timer gameState matches 1.. run title @a subtitle {text:"곧 라운드가 시작됩니다.",color:white}
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {
                KfcGen.titleText(_tp, source, "{text:\"곧 라운드가 시작됩니다.\",color:white}", true);
            }
        }

        // execute unless score timer gameState matches 1.. as @a at @s run playsound ui.button.click weather @s ~ ~ ~
        if (!(KfcGen.scoreMatches(sb, "timer", "gameState", 1, Integer.MAX_VALUE))) {
            for (ServerPlayerEntity e : ctx.allPlayers) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc7 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "ui.button.click", "weather", new net.minecraft.util.math.Vec3d(kfcSrc7.getPosition().x, kfcSrc7.getPosition().y, kfcSrc7.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc7.getPosition().x, kfcSrc7.getPosition().y, kfcSrc7.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc7.getPosition().x, kfcSrc7.getPosition().y, kfcSrc7.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // scoreboard objectives remove statistics
        KfcGen.removeObjective(sb, "statistics");

        // scoreboard objectives add statistics dummy
        KfcGen.ensureObjective(sb, "statistics", "dummy");

        // scoreboard objectives remove sidebar-text
        KfcGen.removeObjective(sb, "sidebar-text");

        // scoreboard objectives add sidebar-text dummy
        KfcGen.ensureObjective(sb, "sidebar-text", "dummy");

        // scoreboard objectives setdisplay sidebar sidebar-text
        KfcGen.setObjectiveDisplaySlot(sb, "sidebar", "sidebar-text");

        // scoreboard objectives modify sidebar-text numberformat blank
        KfcGen.objectiveNumberFormatBlank(sb, "sidebar-text");

        // scoreboard objectives modify sidebar-text displayname {text:"소론",bold:true,color:dark_purple,shadow_color:-99999999}
        KfcGen.setObjectiveDisplay(sb, server, "sidebar-text", "{text:\"소론\",bold:true,color:dark_purple,shadow_color:-99999999}");

        // scoreboard players set map_header sidebar-text 80
        KfcGen.setScore(sb, "map_header", "sidebar-text", 80);

        // scoreboard players set map_name sidebar-text 79
        KfcGen.setScore(sb, "map_name", "sidebar-text", 79);

        // scoreboard players set difficulty_value sidebar-text 78
        KfcGen.setScore(sb, "difficulty_value", "sidebar-text", 78);

        // scoreboard players set blank_1 sidebar-text 77
        KfcGen.setScore(sb, "blank_1", "sidebar-text", 77);

        // scoreboard players set round_header sidebar-text 76
        KfcGen.setScore(sb, "round_header", "sidebar-text", 76);

        // scoreboard players set round_value sidebar-text 75
        KfcGen.setScore(sb, "round_value", "sidebar-text", 75);

        // scoreboard players set enemy_value sidebar-text 74
        KfcGen.setScore(sb, "enemy_value", "sidebar-text", 74);

        // scoreboard players set time_value sidebar-text 73
        KfcGen.setScore(sb, "time_value", "sidebar-text", 73);

        // scoreboard players set enemy_count statistics 0
        KfcGen.setScore(sb, "enemy_count", "statistics", 0);

        // scoreboard players display name map_header sidebar-text {text:"맵 정보",color:gold,bold:true}
        KfcGen.displayScoreName(source, sb, "map_header", "sidebar-text", "{text:\"맵 정보\",color:gold,bold:true}");

        // $scoreboard players display name map_name sidebar-text {text:"$(map_name)",color:green,bold:true}
        KfcGen.displayScoreName(source, sb, "map_name", "sidebar-text", "{text:\"" + macroArgs.get("map_name") + "\",color:green,bold:true}");

        // scoreboard players display name difficulty_value sidebar-text {text:"-",color:white}
        KfcGen.displayScoreName(source, sb, "difficulty_value", "sidebar-text", "{text:\"-\",color:white}");

        // scoreboard players display name blank_1 sidebar-text {text:" "}
        KfcGen.displayScoreName(source, sb, "blank_1", "sidebar-text", "{text:\" \"}");

        // scoreboard players display name round_header sidebar-text {text:"라운드 정보",color:gold,bold:true}
        KfcGen.displayScoreName(source, sb, "round_header", "sidebar-text", "{text:\"라운드 정보\",color:gold,bold:true}");

        // scoreboard players display name round_value sidebar-text {text:"라운드 0/0",color:white}
        KfcGen.displayScoreName(source, sb, "round_value", "sidebar-text", "{text:\"라운드 0/0\",color:white}");

        // scoreboard players display name enemy_value sidebar-text [{text:"남은 몹 수: ",color:white},{score:{name:"enemy_count",objective:"statistics"},color:red}]
        KfcGen.displayScoreName(source, sb, "enemy_value", "sidebar-text", "[{text:\"남은 몹 수: \",color:white},{score:{name:\"enemy_count\",objective:\"statistics\"},color:red}]");

        // scoreboard players display name time_value sidebar-text {text:"0:00",color:aqua,bold:true}
        KfcGen.displayScoreName(source, sb, "time_value", "sidebar-text", "{text:\"0:00\",color:aqua,bold:true}");

        // execute if score timer gameState matches 50 run scoreboard players set stage gameState 1
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 50, 50)) {
            KfcGen.setScore(sb, "stage", "gameState", 1);
        }

        // execute if score timer gameState matches 50 run scoreboard players set game wave 1
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 50, 50)) {
            KfcGen.setScore(sb, "game", "wave", 1);
        }

        // execute if score timer gameState matches 50 run scoreboard players set timer gameState 0
        if (KfcGen.scoreMatches(sb, "timer", "gameState", 50, 50)) {
            KfcGen.setScore(sb, "timer", "gameState", 0);
        }
        return 0;
    }

    public static void _m_10e94ee4_game_game_round_progress_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_10e94ee4_game_game_round_progress_executeReturn(source, macroArgs);
    }

    public static int _m_10e94ee4_game_game_round_progress_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score game game.difficulty matches 5 run function game:infinity/round_progress with storage game setup
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 5, 5)) {
            // -> game:infinity/round_progress
            tdpack.buckets.Bucket7._m_dd4c88c1_game_infinity_round_progress_execute(source);
        }

        // execute if score game game.difficulty matches 5 run return 0
        if (KfcGen.scoreMatches(sb, "game", "game.difficulty", 5, 5)) {
            return 0;
        }

        // $execute as @n[tag=map.spawn_point,tag=game] at @s run function api:enemy/spawn with storage map $(map).$(difficulty).round[$(round)].tick_$(time)
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.spawn_point", "game"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc8 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> api:enemy/spawn
            tdpack.buckets.Bucket0._m_dcd1441b_api_enemy_spawn_execute(kfcSrc8, KfcGen.storageMacroArgs(server, "map", macroArgs.get("map") + "." + macroArgs.get("difficulty") + ".round[" + macroArgs.get("round") + "].tick_" + macroArgs.get("time")));
        } }

        // $execute store result score #temp time run data get storage map $(map).$(difficulty).round[$(round)].end
        KfcGen.setScore(sb, "#temp", "time", (int)(KfcGen.storageGetDouble(server, "map", macroArgs.get("map") + "." + macroArgs.get("difficulty") + ".round[" + macroArgs.get("round") + "].end")));

        // execute if score timer gameState >= #temp time run scoreboard players set stage gameState 3
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            KfcGen.setScore(sb, "stage", "gameState", 3);
        }

        // execute if score timer gameState >= #temp time run give @a yellow_dye[item_name=[{text:"라운드 스킵",bold:true}]] 1
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            for (net.minecraft.entity.Entity _gE : ctx.allPlayers) {
                if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "yellow_dye[item_name=[{text:\"라운드 스킵\",bold:true}]]", 1);
            }
        }

        // execute if score timer gameState >= #temp time run scoreboard players add #temp time 300
        if (KfcGen.scoreCmp(sb, "timer", "gameState", ">=", "#temp", "time")) {
            KfcGen.addScore(sb, "#temp", "time", 300);
        }
        return 0;
    }
}
