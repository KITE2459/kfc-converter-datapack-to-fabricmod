package tdpack.buckets;

import tdpack.generated.KfcGen;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/** Auto-bucketed: 43 datapack functions. */
public final class Bucket10 {
    private Bucket10() { throw new UnsupportedOperationException(); }

    public static void _m_afe1a3fd_tower_spawn_model_sniper_lv2_execute(ServerCommandSource source) {
        _m_afe1a3fd_tower_spawn_model_sniper_lv2_executeReturn(source);
    }

    public static int _m_afe1a3fd_tower_spawn_model_sniper_lv2_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv2
        // -> tower:spawn/model/supporter/lv2
        tdpack.buckets.Bucket10._m_083f103b_tower_spawn_model_supporter_lv2_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc30 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc30.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }

        // kill @e[distance=..1,tag=tower.head,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.head"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // summon block_display ~ ~ ~ {Passengers:[{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-466573512,1337152165,-2086722608,-914359342],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.375f,0f,-0.143500297f,0f,0f,-0.036057f,0.2823754681f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1568099898,-199167318,916626597,-1114099738],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.3740449674f,-0.071323357f,-0.161937797f,0f,-0.0216109772f,-0.8059422231f,0.0788604681f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1832314079,1981075951,-65270442,167122440],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.375f,0f,-0.178969047f,0f,0f,-0.026563f,-0.1177107819f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1824462011,-581191478,1151837384,-981945533],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.59375f,0f,0.0643359375f,0f,0f,-2.5f,-0.2521679687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1710024128,1586570932,-710239455,170247202],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.2359991049f,-0.288249952f,-0.1798828125f,0f,-0.180108823f,-0.3776979359f,-0.8712304687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1716892642,1823600934,-1235481270,1082513721],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.1185949309f,-0.4220320617f,-0.0414453125f,0f,-0.1838118736f,-0.2722939614f,-0.9456054687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;129541518,-223560209,255070408,-588570749],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.375f,0f,0f,0.0017349493f,0f,0.6875f,0f,-0.0342578125f,0f,0f,-0.28125f,-2.1584179687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1595121233,-386076129,-1767113242,-422314638],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.484375f,0f,-0.1276953125f,0f,0f,-1.0065f,-1.1271679687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-721448355,811177886,848797974,1657824321],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1436310649f,0.1436310649f,0f,0.0376724493f,0.1436310649f,0.1436310649f,0f,0.0299609375f,0f,0f,-0.046875f,1.6036132813f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-699356915,-402059878,1472648820,333943095],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1436310649f,0.1436310649f,0f,0.0376724493f,0.1436310649f,0.1436310649f,0f,0.0143359375f,0f,0f,-0.109625f,1.6033007813f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1883991238,-1049446,466583526,1417091230],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.0738826878f,0.1867721824f,0.0171484375f,0f,0.0721242743f,-0.1913257497f,0.4212695313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-644326563,1595896825,102345203,332707525],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.0929796731f,-0.0727762648f,-0.1676953125f,0f,-0.0119934517f,-0.5642006566f,0.5062695313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-735095248,82034834,299884481,1927458299],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.390625f,0f,-0.0020703125f,0f,0f,-2.5f,0.9978320313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-774641154,-525685758,-2040423921,-241862521],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.2082432626f,0.1762910785f,-0.0866015625f,0f,0.0669798931f,-0.5480962672f,-1.2334179687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;360250494,487357407,1474369183,-115189276],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.65625f,0f,-0.0420703125f,0f,0f,-1.46875f,-1.7365429687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-708392246,-800134956,-1111097926,1203321428],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1325825215f,0.1325825215f,0f,0.035f,0.1325825215f,0.1325825215f,0f,0.01125f,0f,0f,-3.6615f,1.073125f,0f,0f,0f,1f]}],Tags:[not-allocated]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-466573512,1337152165,-2086722608,-914359342],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.375f,0f,-0.143500297f,0f,0f,-0.036057f,0.2823754681f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1568099898,-199167318,916626597,-1114099738],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.3740449674f,-0.071323357f,-0.161937797f,0f,-0.0216109772f,-0.8059422231f,0.0788604681f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1832314079,1981075951,-65270442,167122440],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.375f,0f,-0.178969047f,0f,0f,-0.026563f,-0.1177107819f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1824462011,-581191478,1151837384,-981945533],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.59375f,0f,0.0643359375f,0f,0f,-2.5f,-0.2521679687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1710024128,1586570932,-710239455,170247202],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.2359991049f,-0.288249952f,-0.1798828125f,0f,-0.180108823f,-0.3776979359f,-0.8712304687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1716892642,1823600934,-1235481270,1082513721],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.1185949309f,-0.4220320617f,-0.0414453125f,0f,-0.1838118736f,-0.2722939614f,-0.9456054687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;129541518,-223560209,255070408,-588570749],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.375f,0f,0f,0.0017349493f,0f,0.6875f,0f,-0.0342578125f,0f,0f,-0.28125f,-2.1584179687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1595121233,-386076129,-1767113242,-422314638],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.484375f,0f,-0.1276953125f,0f,0f,-1.0065f,-1.1271679687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-721448355,811177886,848797974,1657824321],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1436310649f,0.1436310649f,0f,0.0376724493f,0.1436310649f,0.1436310649f,0f,0.0299609375f,0f,0f,-0.046875f,1.6036132813f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-699356915,-402059878,1472648820,333943095],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1436310649f,0.1436310649f,0f,0.0376724493f,0.1436310649f,0.1436310649f,0f,0.0143359375f,0f,0f,-0.109625f,1.6033007813f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1883991238,-1049446,466583526,1417091230],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.0738826878f,0.1867721824f,0.0171484375f,0f,0.0721242743f,-0.1913257497f,0.4212695313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-644326563,1595896825,102345203,332707525],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.0929796731f,-0.0727762648f,-0.1676953125f,0f,-0.0119934517f,-0.5642006566f,0.5062695313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-735095248,82034834,299884481,1927458299],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.390625f,0f,-0.0020703125f,0f,0f,-2.5f,0.9978320313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-774641154,-525685758,-2040423921,-241862521],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.2082432626f,0.1762910785f,-0.0866015625f,0f,0.0669798931f,-0.5480962672f,-1.2334179687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;360250494,487357407,1474369183,-115189276],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.65625f,0f,-0.0420703125f,0f,0f,-1.46875f,-1.7365429687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-708392246,-800134956,-1111097926,1203321428],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1325825215f,0.1325825215f,0f,0.035f,0.1325825215f,0.1325825215f,0f,0.01125f,0f,0f,-3.6615f,1.073125f,0f,0f,0f,1f]}],Tags:[not-allocated]}"); }

        // execute as @n[tag=not-allocated] run kill @s
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.killEntity(e);
        } }

        // execute as @e[tag=tower.head,type=item_display] at @s run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.head"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc31 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc31.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }
        return 0;
    }

    public static void _m_95f4687e_tower_spawn_model_sniper_lv3_execute(ServerCommandSource source) {
        _m_95f4687e_tower_spawn_model_sniper_lv3_executeReturn(source);
    }

    public static int _m_95f4687e_tower_spawn_model_sniper_lv3_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv3
        // -> tower:spawn/model/supporter/lv3
        tdpack.buckets.Bucket10._m_d95fbe34_tower_spawn_model_supporter_lv3_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc32 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc32.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }

        // kill @e[distance=..1,tag=tower.head,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.head"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // summon block_display ~ ~ ~ {Passengers:[{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-640042752,1688427112,2015624392,-1912508824],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.375f,0f,-0.143500297f,0f,0f,-0.036057f,0.2823754681f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;697197892,796125148,406224027,-1877742255],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.3740449674f,-0.071323357f,-0.161937797f,0f,-0.0216109772f,-0.8059422231f,0.0788604681f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;463213505,878898306,1311639986,1580050103],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.375f,0f,-0.178969047f,0f,0f,-0.026563f,-0.1177107819f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1632416450,-361081813,1210440441,461487751],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.59375f,0f,0.0643359375f,0f,0f,-2.5f,-0.2521679687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1019117593,644261526,-1187151252,-1978008850],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.2359991049f,-0.288249952f,-0.1798828125f,0f,-0.180108823f,-0.3776979359f,-0.8712304687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;837605978,-465736129,117674469,-1222755337],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.1185949309f,-0.4220320617f,-0.0414453125f,0f,-0.1838118736f,-0.2722939614f,-0.9456054687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-135008646,-276586041,-1415116201,-1325419035],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.375f,0f,0f,0.0017349493f,0f,0.6875f,0f,-0.0342578125f,0f,0f,-0.28125f,-2.1584179687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1735000166,442127102,1169194611,-1439872514],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.484375f,0f,-0.1276953125f,0f,0f,-1.0065f,-1.1271679687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;309736684,1072177135,821815544,851852920],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1436310649f,0.1436310649f,0f,0.0376724493f,0.1436310649f,0.1436310649f,0f,0.0299609375f,0f,0f,-0.046875f,1.6036132813f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1637435995,2124607939,-74387140,-870786991],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1436310649f,0.1436310649f,0f,0.0376724493f,0.1436310649f,0.1436310649f,0f,0.0143359375f,0f,0f,-0.109625f,1.6033007813f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-2029906063,-661578372,-2116481121,1470173592],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.0738826878f,0.1867721824f,0.0171484375f,0f,0.0721242743f,-0.1913257497f,0.4212695313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1530606760,-1005321135,-1351249185,-843999942],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.0929796731f,-0.0727762648f,-0.1676953125f,0f,-0.0119934517f,-0.5642006566f,0.5062695313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;2021157202,1115279916,-1801749932,-2107550625],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.390625f,0f,-0.0020703125f,0f,0f,-2.5f,0.9978320313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1078537359,-124691338,1542571446,178980882],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.2082432626f,0.1762910785f,-0.0866015625f,0f,0.0669798931f,-0.5480962672f,-1.2334179687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1219488820,-1809174804,543571046,-1052292714],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.65625f,0f,-0.0420703125f,0f,0f,-1.46875f,-1.7365429687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;226314047,-1662767203,822943297,932772541],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1325825215f,0.1325825215f,0f,0.035f,0.1325825215f,0.1325825215f,0f,0.01125f,0f,0f,-3.6615f,1.073125f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1032831770,273873269,-785899262,-1825445733],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE5MTE5MCwKICAicHJvZmlsZUlkIiA6ICI2YTQ3NjRhZWEwNWY0MTE1OTc0NzFlZjNjYWU4ZTdmZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJiYWRlZW5kamVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkOTI2YjczZWM1M2Y1NDdhMWZkNzY0MjFkNjE1Mjc0MDU2OTJiMTdjYWRiOTIxZDczMDczMzc1M2QwNDRhZWMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.154644253f,0.1657458295f,0f,0.043125f,0.154644253f,0.1657458295f,0f,0.019375f,0f,0f,-0.5f,2.030625f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;753701065,1911337095,-374875202,-677827905],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE5MTE5MCwKICAicHJvZmlsZUlkIiA6ICI2YTQ3NjRhZWEwNWY0MTE1OTc0NzFlZjNjYWU4ZTdmZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJiYWRlZW5kamVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkOTI2YjczZWM1M2Y1NDdhMWZkNzY0MjFkNjE1Mjc0MDU2OTJiMTdjYWRiOTIxZDczMDczMzc1M2QwNDRhZWMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1878075611f,0.1878075611f,0f,0.04875f,0.1878075611f,0.1878075611f,0f,0.025f,0f,0f,-0.25f,1.98375f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1799427656,-1046817463,-1807763670,-1027876128],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE5MTE5MCwKICAicHJvZmlsZUlkIiA6ICI2YTQ3NjRhZWEwNWY0MTE1OTc0NzFlZjNjYWU4ZTdmZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJiYWRlZW5kamVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkOTI2YjczZWM1M2Y1NDdhMWZkNzY0MjFkNjE1Mjc0MDU2OTJiMTdjYWRiOTIxZDczMDczMzc1M2QwNDRhZWMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1878075611f,0.1878075611f,0f,0.04875f,0.1878075611f,0.1878075611f,0f,0.025f,0f,0f,-2f,2.5775f,0f,0f,0f,1f]}],Tags:["not-allocated"]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-640042752,1688427112,2015624392,-1912508824],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.375f,0f,-0.143500297f,0f,0f,-0.036057f,0.2823754681f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;697197892,796125148,406224027,-1877742255],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.3740449674f,-0.071323357f,-0.161937797f,0f,-0.0216109772f,-0.8059422231f,0.0788604681f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;463213505,878898306,1311639986,1580050103],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.21875f,0f,0f,0.0013787831f,0f,0.375f,0f,-0.178969047f,0f,0f,-0.026563f,-0.1177107819f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1632416450,-361081813,1210440441,461487751],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.59375f,0f,0.0643359375f,0f,0f,-2.5f,-0.2521679687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1019117593,644261526,-1187151252,-1978008850],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.2359991049f,-0.288249952f,-0.1798828125f,0f,-0.180108823f,-0.3776979359f,-0.8712304687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;837605978,-465736129,117674469,-1222755337],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.1185949309f,-0.4220320617f,-0.0414453125f,0f,-0.1838118736f,-0.2722939614f,-0.9456054687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-135008646,-276586041,-1415116201,-1325419035],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.375f,0f,0f,0.0017349493f,0f,0.6875f,0f,-0.0342578125f,0f,0f,-0.28125f,-2.1584179687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1735000166,442127102,1169194611,-1439872514],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.484375f,0f,-0.1276953125f,0f,0f,-1.0065f,-1.1271679687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;309736684,1072177135,821815544,851852920],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1436310649f,0.1436310649f,0f,0.0376724493f,0.1436310649f,0.1436310649f,0f,0.0299609375f,0f,0f,-0.046875f,1.6036132813f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1637435995,2124607939,-74387140,-870786991],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1436310649f,0.1436310649f,0f,0.0376724493f,0.1436310649f,0.1436310649f,0f,0.0143359375f,0f,0f,-0.109625f,1.6033007813f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-2029906063,-661578372,-2116481121,1470173592],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.0738826878f,0.1867721824f,0.0171484375f,0f,0.0721242743f,-0.1913257497f,0.4212695313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1530606760,-1005321135,-1351249185,-843999942],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.0929796731f,-0.0727762648f,-0.1676953125f,0f,-0.0119934517f,-0.5642006566f,0.5062695313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;2021157202,1115279916,-1801749932,-2107550625],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.3125f,0f,0f,0.0017349493f,0f,0.390625f,0f,-0.0020703125f,0f,0f,-2.5f,0.9978320313f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1078537359,-124691338,1542571446,178980882],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.2082432626f,0.1762910785f,-0.0866015625f,0f,0.0669798931f,-0.5480962672f,-1.2334179687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1219488820,-1809174804,543571046,-1052292714],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE1NzY1MiwKICAicHJvZmlsZUlkIiA6ICI4NzA1MzkxMDBlOGQ0MzU2YjJiYjlmZGI1NjFhOGU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3R1c3pmYWxsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2NzgxNzM2MWJjYjRhYzNmMzdiYWIxNzg2ZWU3Y2ZjYmRjZTBmY2ZjMWUyMmIzZjRlNTQ0MDhhNGIwYTgwOTgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.328125f,0f,0f,0.0017349493f,0f,0.65625f,0f,-0.0420703125f,0f,0f,-1.46875f,-1.7365429687f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;226314047,-1662767203,822943297,932772541],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE0ODE4NiwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkM2MwMjU5MmM1MTVhM2RhMjBjYmFiM2Y5OGNkYzQ5OGNmNTlhNzhhZmY4ZjI0ZTM2MGI2Mjg0OTg3NDZjZTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1325825215f,0.1325825215f,0f,0.035f,0.1325825215f,0.1325825215f,0f,0.01125f,0f,0f,-3.6615f,1.073125f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1032831770,273873269,-785899262,-1825445733],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE5MTE5MCwKICAicHJvZmlsZUlkIiA6ICI2YTQ3NjRhZWEwNWY0MTE1OTc0NzFlZjNjYWU4ZTdmZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJiYWRlZW5kamVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkOTI2YjczZWM1M2Y1NDdhMWZkNzY0MjFkNjE1Mjc0MDU2OTJiMTdjYWRiOTIxZDczMDczMzc1M2QwNDRhZWMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.154644253f,0.1657458295f,0f,0.043125f,0.154644253f,0.1657458295f,0f,0.019375f,0f,0f,-0.5f,2.030625f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;753701065,1911337095,-374875202,-677827905],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE5MTE5MCwKICAicHJvZmlsZUlkIiA6ICI2YTQ3NjRhZWEwNWY0MTE1OTc0NzFlZjNjYWU4ZTdmZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJiYWRlZW5kamVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkOTI2YjczZWM1M2Y1NDdhMWZkNzY0MjFkNjE1Mjc0MDU2OTJiMTdjYWRiOTIxZDczMDczMzc1M2QwNDRhZWMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1878075611f,0.1878075611f,0f,0.04875f,0.1878075611f,0.1878075611f,0f,0.025f,0f,0f,-0.25f,1.98375f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1799427656,-1046817463,-1807763670,-1027876128],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3Mzk5NTE5MTE5MCwKICAicHJvZmlsZUlkIiA6ICI2YTQ3NjRhZWEwNWY0MTE1OTc0NzFlZjNjYWU4ZTdmZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJiYWRlZW5kamVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkOTI2YjczZWM1M2Y1NDdhMWZkNzY0MjFkNjE1Mjc0MDU2OTJiMTdjYWRiOTIxZDczMDczMzc1M2QwNDRhZWMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1878075611f,0.1878075611f,0f,0.04875f,0.1878075611f,0.1878075611f,0f,0.025f,0f,0f,-2f,2.5775f,0f,0f,0f,1f]}],Tags:[\"not-allocated\"]}"); }

        // execute as @n[tag=not-allocated] run kill @s
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.killEntity(e);
        } }

        // execute as @e[tag=tower.head,type=item_display] at @s run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.head"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc33 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc33.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }
        return 0;
    }

    public static void _m_ef607da0_tower_spawn_model_sniper_lv4_execute(ServerCommandSource source) {
        _m_ef607da0_tower_spawn_model_sniper_lv4_executeReturn(source);
    }

    public static int _m_ef607da0_tower_spawn_model_sniper_lv4_executeReturn(ServerCommandSource source) {
        // [용량 한계 64KB 초과] 원본 mcfunction 호출로 유지(반환값 전파)
        return KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "spawn/model/sniper/lv4"));
    }

    public static void _m_f693e7be_tower_spawn_model_stun_tower_lv0_execute(ServerCommandSource source) {
        _m_f693e7be_tower_spawn_model_stun_tower_lv0_executeReturn(source);
    }

    public static int _m_f693e7be_tower_spawn_model_stun_tower_lv0_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // summon block_display ~ ~1.5 ~ {Passengers:[{teleport_duration:3,Tags:[tower,tower.upgrade-1,tower.sound],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;2075979092,-465580444,-253115299,-1407973443],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgyNjA0MiwKICAicHJvZmlsZUlkIiA6ICI4ODYyYjU4ZWI4YjE0MTg5OGY3MWE0YjI2MDA3ZWVkNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYWJyaWV3RiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jNWFhNmMzNjBlMWU2ZDFlNDBiNGEyZTRiMmE0YmRkMGRhYjM3ZWQzZjVjMDUwMjEyNzUyNWFlMzA2ZWM5MiIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.24f,0f,0f,-0.0002123926f,0f,3f,0f,-0.0489623926f,0f,0f,0.56f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1169235586,-1471837068,-1082181690,289173779],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzMTk4NCwKICAicHJvZmlsZUlkIiA6ICJiMTM1MDRmMjMxOGI0OWNjYWFkZDcyYWVhYmMyNTQ1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUeXBrZW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWJkNzc5NzgxMjNkM2U0MTRiYTMyNTA4NjE4ODkzYWVmMDQ0ZTQ3YWIwMWVlMDI3MmI2N2EwMmE0Y2Y2NzZiZSIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,3f,0f,-0.0489623926f,-0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;241208331,1248848221,-579026237,-1069634725],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzNzQ3NywKICAicHJvZmlsZUlkIiA6ICIyNDY1ODI2NWVjMjg0NTY4YTg3MDJkOTVlYzdlYTc4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcmdvc1oxMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOThkODI2NWM4YjllMjUwOTM0ODYxNzBhNTJhOGVjODUyNjc2ZTFkMjViNmQwMmI2ODI3MTU5NjY0ZDE4MGY1IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,3f,0f,-0.0489623926f,-0.1697056275f,0f,-0.3959797975f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;292815763,-855887100,-1880130265,710240304],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzNzQ3NywKICAicHJvZmlsZUlkIiA6ICIyNDY1ODI2NWVjMjg0NTY4YTg3MDJkOTVlYzdlYTc4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcmdvc1oxMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOThkODI2NWM4YjllMjUwOTM0ODYxNzBhNTJhOGVjODUyNjc2ZTFkMjViNmQwMmI2ODI3MTU5NjY0ZDE4MGY1IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0f,0f,0.56f,0f,0f,3f,0f,-0.04875f,-0.24f,0f,0f,-0.000625f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1536756248,487387945,-686804151,363511221],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg0MzE3NywKICAicHJvZmlsZUlkIiA6ICJhZTQyMTU2N2QzODg0YWUxOTJhNmNiNTY4ZmZkNGZhMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aW1vdGh5ZGVvZG9yYW50IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I0OGExMmNjNzI1ZDgxNDFhODg5YjJiMThlNGNiODk4OGFjZmExYTY2NTVjMjM1ZWEzOTJkYTRlZDQ4ZjEwZWIiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[1.2f,0f,0f,0f,0f,0.4f,0f,0.0999279059f,0f,0f,0.4f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1940821755,1686011784,1471557632,1210012214],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg0ODM2MCwKICAicHJvZmlsZUlkIiA6ICJjNWVmOGQ1NDIwOWY0OTdlYWYzYzA1NjA3MjZhYTMwNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaXNoX0RheCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZTNmZTJjMzQ1NjEwYzZmNGQ1NDhjMTkyYTJlMDlkMmIwMmIwNjcxN2M1MDM1OTAxMjUyZGJlZDQ5NjU5NGRlIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0.4f,0f,0f,0f,0f,0.4f,0f,0.0999279059f,0f,0f,1.2f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1898245952,-896393415,-10758523,-1032351716],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg1NDI0MywKICAicHJvZmlsZUlkIiA6ICI4MWU4ZWU1MTE1ZmE0NzU5OTU1YTAzMThkNTNhNjViYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJDb25zaWRlclRoYXQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY1NmM3ZjQxYTllMjUxNzRhNTIzNjBlZTY1ZmM1ZDc5MmUwYjM2ZDU5MGYwNDgxMmM1NTU5NzEyYzNkNTQ4ZSIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.4f,0f,0f,0f,0f,1.2f,0f,0.2999279059f,0f,0f,0.4f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-725105525,1037109223,-381604352,-757649707],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg1OTg2MCwKICAicHJvZmlsZUlkIiA6ICJmZDJlZDMzMzI0Mjk0ZThmOWVlZGNiN2RhMWJiNzlkZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJkcmFtYWdnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzljMDViODdhZjc4MWQxMGY0YzY1ZGM2OGQxOGEwYmRiMTExNTk3MDBlM2JjMzU5NjU1ZWYzMTM0MGY5ZmE5NDAiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.8004448763f,0f,0.4016366517f,0f,0f,0.4f,0f,0.0999279059f,-0.8004448763f,0f,0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-474808496,48462855,-1616220161,1882091736],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg2NTg3OSwKICAicHJvZmlsZUlkIiA6ICJjYmNkNDQzZGE1NTI0OGU3ODM3NWNmZjYwMmQzZWI0NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJPX1JlaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82OWNmYzk2ZTkwZjE1NTVmNjg2Y2E0ZDY3Mzc4MDhhMTJjYjlhM2ViYTU2NDFjMTUwYmViYTY5MGI4YzQxZTc3IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.8004448763f,0f,0.4016366517f,0f,0f,0.4f,0f,0.0999279059f,-0.8004448763f,0f,-0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1445286665,1573974903,545516087,1321392743],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg3MTY1NCwKICAicHJvZmlsZUlkIiA6ICJlY2ExZDJjMWU4ODg0ZjA3OGQxZTBlZGU2ODE4MGRhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MDRDb3NtaWNhbCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81NDY1NzdlMDRkMjhhZmYzNjBhMWRjYTg1MTQyYjUyY2EyMGE0YjI1OWQ1NTYyNmQwNmUyYmVkMmY1NmRhOGRkIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0f,0.4f,0f,0.1f,-0.8004448763f,0f,-0.4016366517f,-0.0000720941f,-0.8004448763f,0f,0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;698869580,-1392963771,1866872040,1979800923],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg3NzE1OCwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEwMTI0Y2Q4NTMxNGIwNjdmNTQ2ZDY4MmVhZjg2OTFhOWQyMTVkY2JiMjQwMzBkZWUyZTFiYmYzODRiYjZmMDkiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0f,0.4f,0f,0.1f,0.8004448763f,0f,-0.4016366517f,-0.0000720941f,-0.8004448763f,0f,-0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-623537458,-175395338,1991914552,921351547],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg4MjYxOCwKICAicHJvZmlsZUlkIiA6ICI1ZjU5NmViY2JlOTQ0NmQxYmI0M2JlNGYzZjRiOGJlNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWlsMHNzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRiMDRkMGM5OTMxOGE3M2NjYmU3YTM2ZTM3M2I5NTFkOTU5MjYzMTc4NWZjZmQyZTVkM2JiMmY1N2EzOTNkNGYiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.8004448763f,0f,0.4016366517f,0f,0.8004448763f,0f,-0.4016366517f,-0.0000720941f,0f,0.4f,0f,0.1f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;464292607,-337188806,2923167,-1733280780],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg4ODM4MSwKICAicHJvZmlsZUlkIiA6ICI3ZjU3MzM2M2M1YTM0N2JiOGQwYzJjNDYyOTVmN2JlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJNb25leWVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzczOTZkZGRiZTRmMjdhODRjZGJmMmRmNjRjNTIzYjIyZjZmZDYwNDU3YjJhYTFhNDNhYWVhOGE4YWZhNTg3Y2MiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[-0.8004448763f,0f,0.4016366517f,0f,0.8004448763f,0f,0.4016366517f,-0.0000720941f,0f,0.4f,0f,0.1f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1746556088,522632260,393571875,827031917],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk0MjE4NSwKICAicHJvZmlsZUlkIiA6ICJlZGUyYzdhMGFjNjM0MTNiYjA5ZDNmMGJlZTllYzhlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aGVEZXZKYWRlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU5YjY1NmJkNjcyMzEwNWIyM2NmYjU4MGNmYmZhNzVkNzRkN2U2ZjFkOWU2OTI2ODMyYWE2M2U0NDFkNTI3MWIiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[-0.22627417f,0f,0.5374011537f,0f,0f,1f,0f,-1f,-0.22627417f,0f,-0.5374011537f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-683610057,415526949,1749051061,2103705412],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk0ODY4MSwKICAicHJvZmlsZUlkIiA6ICJjMjVlMWMxZTE1YTQ0N2IwOTQ2Zjg2YzYzYzhjYjZkOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJiaWdpYm9zMzIxIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y4ZGQ4YjU1OWUwM2JiN2Q4NzQ4YjNlNjczM2JhYzcyOGYwNjJmMjU5ODM4NDg5ZTg0ZDcxMWQxNDEwZjVkOWQiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.22627417f,0f,0.5374011537f,0f,0f,1f,0f,-1f,-0.22627417f,0f,0.5374011537f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;967217615,-247363283,1431292361,-1350763108],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk1NDEzOCwKICAicHJvZmlsZUlkIiA6ICJlNzM4MTYzZTYwM2M0MTFkOTg4MzNiYzkyZTI4Y2IyYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJYZW5va3JhdGVzUml0dmEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI0MjQyZTY0ZGIwYmY4ZDI0ODkzOWYxOTNhMTgyZTA1OWJkNTI5NjA2NzFkNTYxZmMzN2JhOTM1NDAzZDM1NiIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0f,0f,0.76f,0f,0f,1f,0f,-1f,-0.32f,0f,0f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1784288949,-874205784,696380262,1558258941],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk1OTgzMCwKICAicHJvZmlsZUlkIiA6ICIxNTUyNmU1OGZhOWE0NjBmODhhNmZhNjk1M2RlNjgzNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQaWVkcml0YTE3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg1M2I4MjMzMTJkOTE0ZjFiNDk5YTZiNzYzYjBhYWZjYzA3N2FlYThjMzJlZmExN2JhMDBmNjFkMzA5MDY2NmQiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.32f,0f,0f,0f,0f,1f,0f,-1f,0f,0f,0.76f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1558872693,-548693706,1548902068,-1609249222],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk2NTQwOCwKICAicHJvZmlsZUlkIiA6ICIwMTIxYzFjZDUxOTM0M2NmYWM4YzgyZThiNjVjYTFjZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfVmV4X1RWIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU4YmIwN2RiZGYzNDNjN2I4NWJlNjcxOWQwZWU5ZjM5NzQzOTcyNjhkOTVhOWE3YmNmOGEzODZjYzJjYTdlODAiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.4f,0f,0f,0f,0f,0.8485281374f,-0.8485281374f,-1.288125f,0f,0.8485281374f,0.8485281374f,0.211875f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1612658659,-1062237677,-463382023,1707256956],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk3MTM2MCwKICAicHJvZmlsZUlkIiA6ICJkMjM0Y2IxYWQ4MzU0MjdiYTAzZDJkYTJlZThmYmY5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJtY21ha2VzbXlwcHNvZnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjE1ODkzYTAyOTYzMThkMTVhNjJkMzY0ZWFhN2Y3NzkwNjJkZTMxOTBlMjBlMTllYzVlYWRmYmJhMzk1MWM0YyIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0f,0.8485281374f,0.8485281374f,0.211875f,0f,0.8485281374f,-0.8485281374f,-1.288125f,-0.4f,0f,0f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1134480147,-656185200,1777988794,-1808162791],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg5NDU0OSwKICAicHJvZmlsZUlkIiA6ICJhNjJmNGM0MWY5Y2M0ZDRhOGEyZjFkY2NjZjZkZmE5NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCMDBLWSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hYWJhMWE0Mjk0ZGFmNDQyNTkyY2I0ZTY5YmJmYTEzMTkyNWIwNTg4MjQ1YTk5NzM5MWI4NmFjNDI2ZTMxMjhmIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0f,0f,0.16f,-0.3280002166f,0f,0.4f,0f,-0.29375f,-0.608f,0f,0f,2.166e-7f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-492729636,805992759,917683949,-1199585635],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwMDU4NywKICAicHJvZmlsZUlkIiA6ICJmODY0ZjY3ZGJlN2Y0OTBlYTZlODQzMjg2M2NkZWMxOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3lmcmllbmQ1MDY1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzYWNiNzk2NzZkNmFlNjVlMTI1MzUxNWI3YmZkZmY0ZDFmOGI4MWM3MWY0YWY4ZTZmNDZmMjc4YjU0YzQxNTgiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0f,0f,0.16f,0.3279997834f,0f,0.4f,0f,-0.29375f,-0.608f,0f,0f,2.166e-7f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1945061364,-182964449,1019901863,-535560241],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwNjI0OCwKICAicHJvZmlsZUlkIiA6ICJkOWYxZDI2ZWFkYjg0MjQ4OTI3NjU1NzYzNWNiMzQyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb2JPbGRmbHkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI4MzZmY2Q1YTE3MGRkOWI1ZDE5N2YyYzE2ZTU0YmI4NjliM2NkZWU5NzQ1YTg0NDYyNTQ0ZDAzZWU3ZDY2ZiIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[-0.429920923f,0f,0.113137085f,-0.2320002166f,0f,0.4f,0f,-0.29375f,-0.429920923f,0f,-0.113137085f,0.2320002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;410252229,-1649969535,909420705,-2122077371],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxMTk4OSwKICAicHJvZmlsZUlkIiA6ICIxMzEzZGFmMDc2OGQ0YmQ5Yjc1ODJkMGI1NWUwZGQxNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJMZW50aWNjaGllIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZjZDFiYzRkYTIzZjYzZmYxZjU4YmI4MzNhOWJjNzg1Y2E1Yjg4ODY0YjQ4OGEyZTU4ZDc2MWRmNzI1NWU4MDEiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[-0.429920923f,0f,0.113137085f,0.2319997834f,0f,0.4f,0f,-0.29375f,-0.429920923f,0f,-0.113137085f,-0.2319997834f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-973381518,415169902,2043675950,-2095548252],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxODE2NywKICAicHJvZmlsZUlkIiA6ICJjM2ZmNTY5OWZlNWI0OTY2YTYzYzdhMTEzNTBjZGIyNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWNobzkwMDAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRiYmU0NWM0NWIwNGE0NjcyNmQwNjRiNDFiZWRjZWNkOGQ3OGE4MjVlYjBlYWVhM2U3YmY4YmZhMTIzNDM5YSIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[-0.608f,0f,0f,-2.166e-7f,0f,0.4f,0f,-0.29375f,0f,0f,-0.16f,0.3280002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-829679559,-746434364,-1450667448,983584616],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkyMzc4OSwKICAicHJvZmlsZUlkIiA6ICI0ZWEwN2YwODlmN2U0MWZhYmMwNjRhMjZlNWM1OWU2ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzcGlmZnRvcGlhMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MjA1Y2Q2NjJmYTljZTY2YTA3ZDQ3OGUwZjVlNjUwMzExZWQ4NmQ1NDJlNDgxMTExODQ4Mzk3ZjU1MzIwMmRkIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.608f,0f,0f,-2.166e-7f,0f,0.4f,0f,-0.29375f,0f,0f,-0.16f,-0.3279997834f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1054849420,587322709,895092581,-1747377678],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MjMyMjc1NywKICAicHJvZmlsZUlkIiA6ICJmODJmNTQ1MDIzZDA0MTFkYmVlYzU4YWI4Y2JlMTNjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXNwb25kZW50cyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MWYwOWY5YWFhM2ZlMDhjYWRjZWQ5M2EyMTg3Yjk4YTM3MjQ0NzZiNDU3NDBlZmE5Mzg4Y2ZjODVmNTc1MjEzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[-0.429920923f,0f,-0.113137085f,0.2319997834f,0f,0.4f,0f,-0.29375f,0.429920923f,0f,-0.113137085f,0.2320002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-43300164,1945999801,-2106905896,680258356],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkzNjA3MiwKICAicHJvZmlsZUlkIiA6ICI2M2VkZjY1ZDcxNmE0NGZmYmUzOWU5MWVkMmNiMjM3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ2aWxlX3NsYXZlbWFzdGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RkZmNiMzc3MDU2NWMxMDE0ZWFlMDQ0ZDkxM2FmMTZkNjM5OWVlYjA5YWFjNmVlYTJiZWZhMjZhNmQxYzQ1IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.429920923f,0f,-0.113137085f,-0.2320002166f,0f,0.4f,0f,-0.29375f,0.429920923f,0f,-0.113137085f,-0.2319997834f,0f,0f,0f,1f]},{id:"minecraft:interaction",width:2f,height:-1.5f,response:1b,Tags:[tower,tower.hitbox]},{id:"minecraft:marker",Tags:[tower,stun-tower,tower.head,tower.muzzle,tower.animation,tower.sound]}],Tags:[tower,stun-tower,tower.core]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.5), source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{teleport_duration:3,Tags:[tower,tower.upgrade-1,tower.sound],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;2075979092,-465580444,-253115299,-1407973443],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgyNjA0MiwKICAicHJvZmlsZUlkIiA6ICI4ODYyYjU4ZWI4YjE0MTg5OGY3MWE0YjI2MDA3ZWVkNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYWJyaWV3RiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jNWFhNmMzNjBlMWU2ZDFlNDBiNGEyZTRiMmE0YmRkMGRhYjM3ZWQzZjVjMDUwMjEyNzUyNWFlMzA2ZWM5MiIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.24f,0f,0f,-0.0002123926f,0f,3f,0f,-0.0489623926f,0f,0f,0.56f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1169235586,-1471837068,-1082181690,289173779],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzMTk4NCwKICAicHJvZmlsZUlkIiA6ICJiMTM1MDRmMjMxOGI0OWNjYWFkZDcyYWVhYmMyNTQ1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUeXBrZW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWJkNzc5NzgxMjNkM2U0MTRiYTMyNTA4NjE4ODkzYWVmMDQ0ZTQ3YWIwMWVlMDI3MmI2N2EwMmE0Y2Y2NzZiZSIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,3f,0f,-0.0489623926f,-0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;241208331,1248848221,-579026237,-1069634725],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzNzQ3NywKICAicHJvZmlsZUlkIiA6ICIyNDY1ODI2NWVjMjg0NTY4YTg3MDJkOTVlYzdlYTc4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcmdvc1oxMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOThkODI2NWM4YjllMjUwOTM0ODYxNzBhNTJhOGVjODUyNjc2ZTFkMjViNmQwMmI2ODI3MTU5NjY0ZDE4MGY1IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,3f,0f,-0.0489623926f,-0.1697056275f,0f,-0.3959797975f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;292815763,-855887100,-1880130265,710240304],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzNzQ3NywKICAicHJvZmlsZUlkIiA6ICIyNDY1ODI2NWVjMjg0NTY4YTg3MDJkOTVlYzdlYTc4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcmdvc1oxMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOThkODI2NWM4YjllMjUwOTM0ODYxNzBhNTJhOGVjODUyNjc2ZTFkMjViNmQwMmI2ODI3MTU5NjY0ZDE4MGY1IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0f,0f,0.56f,0f,0f,3f,0f,-0.04875f,-0.24f,0f,0f,-0.000625f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1536756248,487387945,-686804151,363511221],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg0MzE3NywKICAicHJvZmlsZUlkIiA6ICJhZTQyMTU2N2QzODg0YWUxOTJhNmNiNTY4ZmZkNGZhMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aW1vdGh5ZGVvZG9yYW50IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I0OGExMmNjNzI1ZDgxNDFhODg5YjJiMThlNGNiODk4OGFjZmExYTY2NTVjMjM1ZWEzOTJkYTRlZDQ4ZjEwZWIiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[1.2f,0f,0f,0f,0f,0.4f,0f,0.0999279059f,0f,0f,0.4f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1940821755,1686011784,1471557632,1210012214],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg0ODM2MCwKICAicHJvZmlsZUlkIiA6ICJjNWVmOGQ1NDIwOWY0OTdlYWYzYzA1NjA3MjZhYTMwNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaXNoX0RheCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZTNmZTJjMzQ1NjEwYzZmNGQ1NDhjMTkyYTJlMDlkMmIwMmIwNjcxN2M1MDM1OTAxMjUyZGJlZDQ5NjU5NGRlIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0.4f,0f,0f,0f,0f,0.4f,0f,0.0999279059f,0f,0f,1.2f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1898245952,-896393415,-10758523,-1032351716],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg1NDI0MywKICAicHJvZmlsZUlkIiA6ICI4MWU4ZWU1MTE1ZmE0NzU5OTU1YTAzMThkNTNhNjViYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJDb25zaWRlclRoYXQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY1NmM3ZjQxYTllMjUxNzRhNTIzNjBlZTY1ZmM1ZDc5MmUwYjM2ZDU5MGYwNDgxMmM1NTU5NzEyYzNkNTQ4ZSIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.4f,0f,0f,0f,0f,1.2f,0f,0.2999279059f,0f,0f,0.4f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-725105525,1037109223,-381604352,-757649707],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg1OTg2MCwKICAicHJvZmlsZUlkIiA6ICJmZDJlZDMzMzI0Mjk0ZThmOWVlZGNiN2RhMWJiNzlkZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJkcmFtYWdnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzljMDViODdhZjc4MWQxMGY0YzY1ZGM2OGQxOGEwYmRiMTExNTk3MDBlM2JjMzU5NjU1ZWYzMTM0MGY5ZmE5NDAiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.8004448763f,0f,0.4016366517f,0f,0f,0.4f,0f,0.0999279059f,-0.8004448763f,0f,0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-474808496,48462855,-1616220161,1882091736],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg2NTg3OSwKICAicHJvZmlsZUlkIiA6ICJjYmNkNDQzZGE1NTI0OGU3ODM3NWNmZjYwMmQzZWI0NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJPX1JlaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82OWNmYzk2ZTkwZjE1NTVmNjg2Y2E0ZDY3Mzc4MDhhMTJjYjlhM2ViYTU2NDFjMTUwYmViYTY5MGI4YzQxZTc3IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.8004448763f,0f,0.4016366517f,0f,0f,0.4f,0f,0.0999279059f,-0.8004448763f,0f,-0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1445286665,1573974903,545516087,1321392743],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg3MTY1NCwKICAicHJvZmlsZUlkIiA6ICJlY2ExZDJjMWU4ODg0ZjA3OGQxZTBlZGU2ODE4MGRhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MDRDb3NtaWNhbCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81NDY1NzdlMDRkMjhhZmYzNjBhMWRjYTg1MTQyYjUyY2EyMGE0YjI1OWQ1NTYyNmQwNmUyYmVkMmY1NmRhOGRkIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0f,0.4f,0f,0.1f,-0.8004448763f,0f,-0.4016366517f,-0.0000720941f,-0.8004448763f,0f,0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;698869580,-1392963771,1866872040,1979800923],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg3NzE1OCwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEwMTI0Y2Q4NTMxNGIwNjdmNTQ2ZDY4MmVhZjg2OTFhOWQyMTVkY2JiMjQwMzBkZWUyZTFiYmYzODRiYjZmMDkiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0f,0.4f,0f,0.1f,0.8004448763f,0f,-0.4016366517f,-0.0000720941f,-0.8004448763f,0f,-0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-623537458,-175395338,1991914552,921351547],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg4MjYxOCwKICAicHJvZmlsZUlkIiA6ICI1ZjU5NmViY2JlOTQ0NmQxYmI0M2JlNGYzZjRiOGJlNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWlsMHNzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRiMDRkMGM5OTMxOGE3M2NjYmU3YTM2ZTM3M2I5NTFkOTU5MjYzMTc4NWZjZmQyZTVkM2JiMmY1N2EzOTNkNGYiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.8004448763f,0f,0.4016366517f,0f,0.8004448763f,0f,-0.4016366517f,-0.0000720941f,0f,0.4f,0f,0.1f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;464292607,-337188806,2923167,-1733280780],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg4ODM4MSwKICAicHJvZmlsZUlkIiA6ICI3ZjU3MzM2M2M1YTM0N2JiOGQwYzJjNDYyOTVmN2JlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJNb25leWVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzczOTZkZGRiZTRmMjdhODRjZGJmMmRmNjRjNTIzYjIyZjZmZDYwNDU3YjJhYTFhNDNhYWVhOGE4YWZhNTg3Y2MiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[-0.8004448763f,0f,0.4016366517f,0f,0.8004448763f,0f,0.4016366517f,-0.0000720941f,0f,0.4f,0f,0.1f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1746556088,522632260,393571875,827031917],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk0MjE4NSwKICAicHJvZmlsZUlkIiA6ICJlZGUyYzdhMGFjNjM0MTNiYjA5ZDNmMGJlZTllYzhlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aGVEZXZKYWRlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU5YjY1NmJkNjcyMzEwNWIyM2NmYjU4MGNmYmZhNzVkNzRkN2U2ZjFkOWU2OTI2ODMyYWE2M2U0NDFkNTI3MWIiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[-0.22627417f,0f,0.5374011537f,0f,0f,1f,0f,-1f,-0.22627417f,0f,-0.5374011537f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-683610057,415526949,1749051061,2103705412],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk0ODY4MSwKICAicHJvZmlsZUlkIiA6ICJjMjVlMWMxZTE1YTQ0N2IwOTQ2Zjg2YzYzYzhjYjZkOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJiaWdpYm9zMzIxIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y4ZGQ4YjU1OWUwM2JiN2Q4NzQ4YjNlNjczM2JhYzcyOGYwNjJmMjU5ODM4NDg5ZTg0ZDcxMWQxNDEwZjVkOWQiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.22627417f,0f,0.5374011537f,0f,0f,1f,0f,-1f,-0.22627417f,0f,0.5374011537f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;967217615,-247363283,1431292361,-1350763108],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk1NDEzOCwKICAicHJvZmlsZUlkIiA6ICJlNzM4MTYzZTYwM2M0MTFkOTg4MzNiYzkyZTI4Y2IyYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJYZW5va3JhdGVzUml0dmEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI0MjQyZTY0ZGIwYmY4ZDI0ODkzOWYxOTNhMTgyZTA1OWJkNTI5NjA2NzFkNTYxZmMzN2JhOTM1NDAzZDM1NiIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0f,0f,0.76f,0f,0f,1f,0f,-1f,-0.32f,0f,0f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1784288949,-874205784,696380262,1558258941],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk1OTgzMCwKICAicHJvZmlsZUlkIiA6ICIxNTUyNmU1OGZhOWE0NjBmODhhNmZhNjk1M2RlNjgzNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQaWVkcml0YTE3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg1M2I4MjMzMTJkOTE0ZjFiNDk5YTZiNzYzYjBhYWZjYzA3N2FlYThjMzJlZmExN2JhMDBmNjFkMzA5MDY2NmQiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.32f,0f,0f,0f,0f,1f,0f,-1f,0f,0f,0.76f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1558872693,-548693706,1548902068,-1609249222],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk2NTQwOCwKICAicHJvZmlsZUlkIiA6ICIwMTIxYzFjZDUxOTM0M2NmYWM4YzgyZThiNjVjYTFjZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfVmV4X1RWIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU4YmIwN2RiZGYzNDNjN2I4NWJlNjcxOWQwZWU5ZjM5NzQzOTcyNjhkOTVhOWE3YmNmOGEzODZjYzJjYTdlODAiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.4f,0f,0f,0f,0f,0.8485281374f,-0.8485281374f,-1.288125f,0f,0.8485281374f,0.8485281374f,0.211875f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1612658659,-1062237677,-463382023,1707256956],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk3MTM2MCwKICAicHJvZmlsZUlkIiA6ICJkMjM0Y2IxYWQ4MzU0MjdiYTAzZDJkYTJlZThmYmY5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJtY21ha2VzbXlwcHNvZnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjE1ODkzYTAyOTYzMThkMTVhNjJkMzY0ZWFhN2Y3NzkwNjJkZTMxOTBlMjBlMTllYzVlYWRmYmJhMzk1MWM0YyIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0f,0.8485281374f,0.8485281374f,0.211875f,0f,0.8485281374f,-0.8485281374f,-1.288125f,-0.4f,0f,0f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1134480147,-656185200,1777988794,-1808162791],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg5NDU0OSwKICAicHJvZmlsZUlkIiA6ICJhNjJmNGM0MWY5Y2M0ZDRhOGEyZjFkY2NjZjZkZmE5NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCMDBLWSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hYWJhMWE0Mjk0ZGFmNDQyNTkyY2I0ZTY5YmJmYTEzMTkyNWIwNTg4MjQ1YTk5NzM5MWI4NmFjNDI2ZTMxMjhmIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0f,0f,0.16f,-0.3280002166f,0f,0.4f,0f,-0.29375f,-0.608f,0f,0f,2.166e-7f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-492729636,805992759,917683949,-1199585635],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwMDU4NywKICAicHJvZmlsZUlkIiA6ICJmODY0ZjY3ZGJlN2Y0OTBlYTZlODQzMjg2M2NkZWMxOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3lmcmllbmQ1MDY1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzYWNiNzk2NzZkNmFlNjVlMTI1MzUxNWI3YmZkZmY0ZDFmOGI4MWM3MWY0YWY4ZTZmNDZmMjc4YjU0YzQxNTgiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0f,0f,0.16f,0.3279997834f,0f,0.4f,0f,-0.29375f,-0.608f,0f,0f,2.166e-7f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1945061364,-182964449,1019901863,-535560241],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwNjI0OCwKICAicHJvZmlsZUlkIiA6ICJkOWYxZDI2ZWFkYjg0MjQ4OTI3NjU1NzYzNWNiMzQyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb2JPbGRmbHkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI4MzZmY2Q1YTE3MGRkOWI1ZDE5N2YyYzE2ZTU0YmI4NjliM2NkZWU5NzQ1YTg0NDYyNTQ0ZDAzZWU3ZDY2ZiIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[-0.429920923f,0f,0.113137085f,-0.2320002166f,0f,0.4f,0f,-0.29375f,-0.429920923f,0f,-0.113137085f,0.2320002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;410252229,-1649969535,909420705,-2122077371],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxMTk4OSwKICAicHJvZmlsZUlkIiA6ICIxMzEzZGFmMDc2OGQ0YmQ5Yjc1ODJkMGI1NWUwZGQxNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJMZW50aWNjaGllIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZjZDFiYzRkYTIzZjYzZmYxZjU4YmI4MzNhOWJjNzg1Y2E1Yjg4ODY0YjQ4OGEyZTU4ZDc2MWRmNzI1NWU4MDEiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[-0.429920923f,0f,0.113137085f,0.2319997834f,0f,0.4f,0f,-0.29375f,-0.429920923f,0f,-0.113137085f,-0.2319997834f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-973381518,415169902,2043675950,-2095548252],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxODE2NywKICAicHJvZmlsZUlkIiA6ICJjM2ZmNTY5OWZlNWI0OTY2YTYzYzdhMTEzNTBjZGIyNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWNobzkwMDAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRiYmU0NWM0NWIwNGE0NjcyNmQwNjRiNDFiZWRjZWNkOGQ3OGE4MjVlYjBlYWVhM2U3YmY4YmZhMTIzNDM5YSIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[-0.608f,0f,0f,-2.166e-7f,0f,0.4f,0f,-0.29375f,0f,0f,-0.16f,0.3280002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-829679559,-746434364,-1450667448,983584616],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkyMzc4OSwKICAicHJvZmlsZUlkIiA6ICI0ZWEwN2YwODlmN2U0MWZhYmMwNjRhMjZlNWM1OWU2ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzcGlmZnRvcGlhMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MjA1Y2Q2NjJmYTljZTY2YTA3ZDQ3OGUwZjVlNjUwMzExZWQ4NmQ1NDJlNDgxMTExODQ4Mzk3ZjU1MzIwMmRkIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.608f,0f,0f,-2.166e-7f,0f,0.4f,0f,-0.29375f,0f,0f,-0.16f,-0.3279997834f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1054849420,587322709,895092581,-1747377678],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MjMyMjc1NywKICAicHJvZmlsZUlkIiA6ICJmODJmNTQ1MDIzZDA0MTFkYmVlYzU4YWI4Y2JlMTNjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXNwb25kZW50cyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MWYwOWY5YWFhM2ZlMDhjYWRjZWQ5M2EyMTg3Yjk4YTM3MjQ0NzZiNDU3NDBlZmE5Mzg4Y2ZjODVmNTc1MjEzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[-0.429920923f,0f,-0.113137085f,0.2319997834f,0f,0.4f,0f,-0.29375f,0.429920923f,0f,-0.113137085f,0.2320002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-43300164,1945999801,-2106905896,680258356],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkzNjA3MiwKICAicHJvZmlsZUlkIiA6ICI2M2VkZjY1ZDcxNmE0NGZmYmUzOWU5MWVkMmNiMjM3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ2aWxlX3NsYXZlbWFzdGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RkZmNiMzc3MDU2NWMxMDE0ZWFlMDQ0ZDkxM2FmMTZkNjM5OWVlYjA5YWFjNmVlYTJiZWZhMjZhNmQxYzQ1IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.429920923f,0f,-0.113137085f,-0.2320002166f,0f,0.4f,0f,-0.29375f,0.429920923f,0f,-0.113137085f,-0.2319997834f,0f,0f,0f,1f]},{id:\"minecraft:interaction\",width:2f,height:-1.5f,response:1b,Tags:[tower,tower.hitbox]},{id:\"minecraft:marker\",Tags:[tower,stun-tower,tower.head,tower.muzzle,tower.animation,tower.sound]}],Tags:[tower,stun-tower,tower.core]}"); }
        return 0;
    }

    public static void _m_613a397c_tower_spawn_model_stun_tower_lv1_execute(ServerCommandSource source) {
        _m_613a397c_tower_spawn_model_stun_tower_lv1_executeReturn(source);
    }

    public static int _m_613a397c_tower_spawn_model_stun_tower_lv1_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // kill @e[distance=..1,tag=tower.upgrade-1,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.upgrade-1"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // summon block_display ~ ~ ~ {Tags:[not-allocated],Passengers:[{teleport_duration:3,Tags:[tower,tower.upgrade-1,tower.sound],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1711634156,-461169337,-1059787917,1478195191],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgyNjA0MiwKICAicHJvZmlsZUlkIiA6ICI4ODYyYjU4ZWI4YjE0MTg5OGY3MWE0YjI2MDA3ZWVkNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYWJyaWV3RiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jNWFhNmMzNjBlMWU2ZDFlNDBiNGEyZTRiMmE0YmRkMGRhYjM3ZWQzZjVjMDUwMjEyNzUyNWFlMzA2ZWM5MiIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.24f,0f,0f,-0.0002123926f,0f,3f,0f,-0.0489623926f,0f,0f,0.56f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-801783750,1624966960,-77099099,-132646985],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzMTk4NCwKICAicHJvZmlsZUlkIiA6ICJiMTM1MDRmMjMxOGI0OWNjYWFkZDcyYWVhYmMyNTQ1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUeXBrZW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWJkNzc5NzgxMjNkM2U0MTRiYTMyNTA4NjE4ODkzYWVmMDQ0ZTQ3YWIwMWVlMDI3MmI2N2EwMmE0Y2Y2NzZiZSIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,3f,0f,-0.0489623926f,-0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1059571874,1248150361,1671379286,-1860005390],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzNzQ3NywKICAicHJvZmlsZUlkIiA6ICIyNDY1ODI2NWVjMjg0NTY4YTg3MDJkOTVlYzdlYTc4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcmdvc1oxMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOThkODI2NWM4YjllMjUwOTM0ODYxNzBhNTJhOGVjODUyNjc2ZTFkMjViNmQwMmI2ODI3MTU5NjY0ZDE4MGY1IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,3f,0f,-0.0489623926f,-0.1697056275f,0f,-0.3959797975f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1664772716,-115906095,-1557260402,-1893587790],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzNzQ3NywKICAicHJvZmlsZUlkIiA6ICIyNDY1ODI2NWVjMjg0NTY4YTg3MDJkOTVlYzdlYTc4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcmdvc1oxMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOThkODI2NWM4YjllMjUwOTM0ODYxNzBhNTJhOGVjODUyNjc2ZTFkMjViNmQwMmI2ODI3MTU5NjY0ZDE4MGY1IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0f,0f,0.56f,0f,0f,3f,0f,-0.04875f,-0.24f,0f,0f,-0.000625f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1192206450,1609041248,242449329,-880979707],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg0MzE3NywKICAicHJvZmlsZUlkIiA6ICJhZTQyMTU2N2QzODg0YWUxOTJhNmNiNTY4ZmZkNGZhMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aW1vdGh5ZGVvZG9yYW50IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I0OGExMmNjNzI1ZDgxNDFhODg5YjJiMThlNGNiODk4OGFjZmExYTY2NTVjMjM1ZWEzOTJkYTRlZDQ4ZjEwZWIiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[1.2f,0f,0f,0f,0f,0.4f,0f,0.0999279059f,0f,0f,0.4f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1393963923,827509835,998518926,167117054],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg0ODM2MCwKICAicHJvZmlsZUlkIiA6ICJjNWVmOGQ1NDIwOWY0OTdlYWYzYzA1NjA3MjZhYTMwNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaXNoX0RheCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZTNmZTJjMzQ1NjEwYzZmNGQ1NDhjMTkyYTJlMDlkMmIwMmIwNjcxN2M1MDM1OTAxMjUyZGJlZDQ5NjU5NGRlIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0.4f,0f,0f,0f,0f,0.4f,0f,0.0999279059f,0f,0f,1.2f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-722343846,-887699982,42192667,1897286292],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg1NDI0MywKICAicHJvZmlsZUlkIiA6ICI4MWU4ZWU1MTE1ZmE0NzU5OTU1YTAzMThkNTNhNjViYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJDb25zaWRlclRoYXQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY1NmM3ZjQxYTllMjUxNzRhNTIzNjBlZTY1ZmM1ZDc5MmUwYjM2ZDU5MGYwNDgxMmM1NTU5NzEyYzNkNTQ4ZSIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.4f,0f,0f,0f,0f,1.2f,0f,0.2999279059f,0f,0f,0.4f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;261110091,-1914535677,657344909,-1854540656],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg1OTg2MCwKICAicHJvZmlsZUlkIiA6ICJmZDJlZDMzMzI0Mjk0ZThmOWVlZGNiN2RhMWJiNzlkZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJkcmFtYWdnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzljMDViODdhZjc4MWQxMGY0YzY1ZGM2OGQxOGEwYmRiMTExNTk3MDBlM2JjMzU5NjU1ZWYzMTM0MGY5ZmE5NDAiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.8004448763f,0f,0.4016366517f,0f,0f,0.4f,0f,0.0999279059f,-0.8004448763f,0f,0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1388425148,1175819394,-1478272807,811489122],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg2NTg3OSwKICAicHJvZmlsZUlkIiA6ICJjYmNkNDQzZGE1NTI0OGU3ODM3NWNmZjYwMmQzZWI0NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJPX1JlaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82OWNmYzk2ZTkwZjE1NTVmNjg2Y2E0ZDY3Mzc4MDhhMTJjYjlhM2ViYTU2NDFjMTUwYmViYTY5MGI4YzQxZTc3IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.8004448763f,0f,0.4016366517f,0f,0f,0.4f,0f,0.0999279059f,-0.8004448763f,0f,-0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1923067438,-1143504310,-2062165181,-1356314876],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg3MTY1NCwKICAicHJvZmlsZUlkIiA6ICJlY2ExZDJjMWU4ODg0ZjA3OGQxZTBlZGU2ODE4MGRhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MDRDb3NtaWNhbCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81NDY1NzdlMDRkMjhhZmYzNjBhMWRjYTg1MTQyYjUyY2EyMGE0YjI1OWQ1NTYyNmQwNmUyYmVkMmY1NmRhOGRkIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0f,0.4f,0f,0.1f,-0.8004448763f,0f,-0.4016366517f,-0.0000720941f,-0.8004448763f,0f,0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1004764580,-905661497,726124213,78278456],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg3NzE1OCwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEwMTI0Y2Q4NTMxNGIwNjdmNTQ2ZDY4MmVhZjg2OTFhOWQyMTVkY2JiMjQwMzBkZWUyZTFiYmYzODRiYjZmMDkiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0f,0.4f,0f,0.1f,0.8004448763f,0f,-0.4016366517f,-0.0000720941f,-0.8004448763f,0f,-0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1403240163,882833578,-1107557583,-1997024008],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg4MjYxOCwKICAicHJvZmlsZUlkIiA6ICI1ZjU5NmViY2JlOTQ0NmQxYmI0M2JlNGYzZjRiOGJlNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWlsMHNzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRiMDRkMGM5OTMxOGE3M2NjYmU3YTM2ZTM3M2I5NTFkOTU5MjYzMTc4NWZjZmQyZTVkM2JiMmY1N2EzOTNkNGYiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.8004448763f,0f,0.4016366517f,0f,0.8004448763f,0f,-0.4016366517f,-0.0000720941f,0f,0.4f,0f,0.1f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1693589665,-1668552063,-782397728,1169886498],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg4ODM4MSwKICAicHJvZmlsZUlkIiA6ICI3ZjU3MzM2M2M1YTM0N2JiOGQwYzJjNDYyOTVmN2JlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJNb25leWVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzczOTZkZGRiZTRmMjdhODRjZGJmMmRmNjRjNTIzYjIyZjZmZDYwNDU3YjJhYTFhNDNhYWVhOGE4YWZhNTg3Y2MiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[-0.8004448763f,0f,0.4016366517f,0f,0.8004448763f,0f,0.4016366517f,-0.0000720941f,0f,0.4f,0f,0.1f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;311196984,114919632,2146675669,519682996],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk0MjE4NSwKICAicHJvZmlsZUlkIiA6ICJlZGUyYzdhMGFjNjM0MTNiYjA5ZDNmMGJlZTllYzhlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aGVEZXZKYWRlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU5YjY1NmJkNjcyMzEwNWIyM2NmYjU4MGNmYmZhNzVkNzRkN2U2ZjFkOWU2OTI2ODMyYWE2M2U0NDFkNTI3MWIiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[-0.22627417f,0f,0.5374011537f,0f,0f,1f,0f,-1f,-0.22627417f,0f,-0.5374011537f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-59056228,838179571,991108868,-1739558756],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk0ODY4MSwKICAicHJvZmlsZUlkIiA6ICJjMjVlMWMxZTE1YTQ0N2IwOTQ2Zjg2YzYzYzhjYjZkOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJiaWdpYm9zMzIxIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y4ZGQ4YjU1OWUwM2JiN2Q4NzQ4YjNlNjczM2JhYzcyOGYwNjJmMjU5ODM4NDg5ZTg0ZDcxMWQxNDEwZjVkOWQiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.22627417f,0f,0.5374011537f,0f,0f,1f,0f,-1f,-0.22627417f,0f,0.5374011537f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1188583408,1121922715,-79833060,-649429314],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk1NDEzOCwKICAicHJvZmlsZUlkIiA6ICJlNzM4MTYzZTYwM2M0MTFkOTg4MzNiYzkyZTI4Y2IyYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJYZW5va3JhdGVzUml0dmEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI0MjQyZTY0ZGIwYmY4ZDI0ODkzOWYxOTNhMTgyZTA1OWJkNTI5NjA2NzFkNTYxZmMzN2JhOTM1NDAzZDM1NiIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0f,0f,0.76f,0f,0f,1f,0f,-1f,-0.32f,0f,0f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;734465170,-1851806133,-340683718,-1158242367],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk1OTgzMCwKICAicHJvZmlsZUlkIiA6ICIxNTUyNmU1OGZhOWE0NjBmODhhNmZhNjk1M2RlNjgzNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQaWVkcml0YTE3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg1M2I4MjMzMTJkOTE0ZjFiNDk5YTZiNzYzYjBhYWZjYzA3N2FlYThjMzJlZmExN2JhMDBmNjFkMzA5MDY2NmQiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.32f,0f,0f,0f,0f,1f,0f,-1f,0f,0f,0.76f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-287421572,46676062,1791947854,88515642],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk2NTQwOCwKICAicHJvZmlsZUlkIiA6ICIwMTIxYzFjZDUxOTM0M2NmYWM4YzgyZThiNjVjYTFjZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfVmV4X1RWIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU4YmIwN2RiZGYzNDNjN2I4NWJlNjcxOWQwZWU5ZjM5NzQzOTcyNjhkOTVhOWE3YmNmOGEzODZjYzJjYTdlODAiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.4f,0f,0f,0f,0f,0.8485281374f,-0.8485281374f,-1.288125f,0f,0.8485281374f,0.8485281374f,0.211875f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;254975406,-1967469638,-742736850,34932316],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk3MTM2MCwKICAicHJvZmlsZUlkIiA6ICJkMjM0Y2IxYWQ4MzU0MjdiYTAzZDJkYTJlZThmYmY5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJtY21ha2VzbXlwcHNvZnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjE1ODkzYTAyOTYzMThkMTVhNjJkMzY0ZWFhN2Y3NzkwNjJkZTMxOTBlMjBlMTllYzVlYWRmYmJhMzk1MWM0YyIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0f,0.8485281374f,0.8485281374f,0.211875f,0f,0.8485281374f,-0.8485281374f,-1.288125f,-0.4f,0f,0f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-981640646,-1251251971,1752872049,1026481675],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg5NDU0OSwKICAicHJvZmlsZUlkIiA6ICJhNjJmNGM0MWY5Y2M0ZDRhOGEyZjFkY2NjZjZkZmE5NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCMDBLWSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hYWJhMWE0Mjk0ZGFmNDQyNTkyY2I0ZTY5YmJmYTEzMTkyNWIwNTg4MjQ1YTk5NzM5MWI4NmFjNDI2ZTMxMjhmIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.3199233495f,0f,-0.2032534972f,0.4169778745f,0f,0.4f,0f,-0.79375f,0.7723632892f,0f,-0.0841903551f,0.1725744867f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-2021033125,-1905504091,-1039145598,102354754],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwMDU4NywKICAicHJvZmlsZUlkIiA6ICJmODY0ZjY3ZGJlN2Y0OTBlYTZlODQzMjg2M2NkZWMxOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3lmcmllbmQ1MDY1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzYWNiNzk2NzZkNmFlNjVlMTI1MzUxNWI3YmZkZmY0ZDFmOGI4MWM3MWY0YWY4ZTZmNDZmMjc4YjU0YzQxNTgiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[-0.3199233495f,0f,-0.2032534972f,-0.4163614638f,0f,0.4f,0f,-0.79375f,0.7723632892f,0f,-0.0841903551f,-0.1726059693f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1576389504,1868204836,1146654161,-1575405487],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwNjI0OCwKICAicHJvZmlsZUlkIiA6ICJkOWYxZDI2ZWFkYjg0MjQ4OTI3NjU1NzYzNWNiMzQyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb2JPbGRmbHkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI4MzZmY2Q1YTE3MGRkOWI1ZDE5N2YyYzE2ZTU0YmI4NjliM2NkZWU5NzQ1YTg0NDYyNTQ0ZDAzZWU3ZDY2ZiIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.3199233495f,0f,-0.2032534972f,0.4171017911f,0f,0.4f,0f,-0.79375f,0.7723632892f,0f,0.0841903551f,-0.1726572972f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1908632846,-509133382,1195005207,1823605970],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxMTk4OSwKICAicHJvZmlsZUlkIiA6ICIxMzEzZGFmMDc2OGQ0YmQ5Yjc1ODJkMGI1NWUwZGQxNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJMZW50aWNjaGllIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZjZDFiYzRkYTIzZjYzZmYxZjU4YmI4MzNhOWJjNzg1Y2E1Yjg4ODY0YjQ4OGEyZTU4ZDc2MWRmNzI1NWU4MDEiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0.3199233495f,0f,-0.2032534972f,-0.4164853805f,0f,0.4f,0f,-0.79375f,0.7723632892f,0f,0.0841903551f,0.1726258147f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-211941412,1086914097,-606396955,369484261],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxODE2NywKICAicHJvZmlsZUlkIiA6ICJjM2ZmNTY5OWZlNWI0OTY2YTYzYzdhMTEzNTBjZGIyNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWNobzkwMDAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRiYmU0NWM0NWIwNGE0NjcyNmQwNjRiNDFiZWRjZWNkOGQ3OGE4MjVlYjBlYWVhM2U3YmY4YmZhMTIzNDM5YSIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.7723632892f,0f,-0.0841903551f,0.1728984333f,0f,0.4f,0f,-0.79375f,0.3199233495f,0f,0.2032534972f,-0.4166854104f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-97991607,-1773645565,1178118916,897755638],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkyMzc4OSwKICAicHJvZmlsZUlkIiA6ICI0ZWEwN2YwODlmN2U0MWZhYmMwNjRhMjZlNWM1OWU2ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzcGlmZnRvcGlhMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MjA1Y2Q2NjJmYTljZTY2YTA3ZDQ3OGUwZjVlNjUwMzExZWQ4NmQ1NDJlNDgxMTExODQ4Mzk3ZjU1MzIwMmRkIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0.7723632892f,0f,-0.0841903551f,-0.1722820227f,0f,0.4f,0f,-0.79375f,0.3199233495f,0f,0.2032534972f,0.4166539279f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-348250520,1604843838,-55187472,444383770],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MjMyMjc1NywKICAicHJvZmlsZUlkIiA6ICJmODJmNTQ1MDIzZDA0MTFkYmVlYzU4YWI4Y2JlMTNjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXNwb25kZW50cyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MWYwOWY5YWFhM2ZlMDhjYWRjZWQ5M2EyMTg3Yjk4YTM3MjQ0NzZiNDU3NDBlZmE5Mzg4Y2ZjODVmNTc1MjEzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[0.7723632892f,0f,0.0841903551f,-0.1723333506f,0f,0.4f,0f,-0.79375f,-0.3199233495f,0f,0.2032534972f,-0.4168093271f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1001732057,-1063258822,-1594060362,2143985981],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkzNjA3MiwKICAicHJvZmlsZUlkIiA6ICI2M2VkZjY1ZDcxNmE0NGZmYmUzOWU5MWVkMmNiMjM3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ2aWxlX3NsYXZlbWFzdGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RkZmNiMzc3MDU2NWMxMDE0ZWFlMDQ0ZDkxM2FmMTZkNjM5OWVlYjA5YWFjNmVlYTJiZWZhMjZhNmQxYzQ1IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0.7723632892f,0f,0.0841903551f,0.1729497613f,0f,0.4f,0f,-0.79375f,-0.3199233495f,0f,0.2032534972f,0.4167778445f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-45783725,304056845,-1676212242,-1426709311],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg5NDU0OSwKICAicHJvZmlsZUlkIiA6ICJhNjJmNGM0MWY5Y2M0ZDRhOGEyZjFkY2NjZjZkZmE5NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCMDBLWSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hYWJhMWE0Mjk0ZGFmNDQyNTkyY2I0ZTY5YmJmYTEzMTkyNWIwNTg4MjQ1YTk5NzM5MWI4NmFjNDI2ZTMxMjhmIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[0f,0f,0.16f,-0.3280002166f,0f,0.4f,0f,-0.29375f,-0.608f,0f,0f,2.166e-7f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1857028805,-2138544783,-1248569402,-1178259956],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwMDU4NywKICAicHJvZmlsZUlkIiA6ICJmODY0ZjY3ZGJlN2Y0OTBlYTZlODQzMjg2M2NkZWMxOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3lmcmllbmQ1MDY1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzYWNiNzk2NzZkNmFlNjVlMTI1MzUxNWI3YmZkZmY0ZDFmOGI4MWM3MWY0YWY4ZTZmNDZmMjc4YjU0YzQxNTgiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[0f,0f,0.16f,0.3279997834f,0f,0.4f,0f,-0.29375f,-0.608f,0f,0f,2.166e-7f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1424301621,-119194595,1334663530,-2004117918],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwNjI0OCwKICAicHJvZmlsZUlkIiA6ICJkOWYxZDI2ZWFkYjg0MjQ4OTI3NjU1NzYzNWNiMzQyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb2JPbGRmbHkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI4MzZmY2Q1YTE3MGRkOWI1ZDE5N2YyYzE2ZTU0YmI4NjliM2NkZWU5NzQ1YTg0NDYyNTQ0ZDAzZWU3ZDY2ZiIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[-0.429920923f,0f,0.113137085f,-0.2320002166f,0f,0.4f,0f,-0.29375f,-0.429920923f,0f,-0.113137085f,0.2320002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1468294576,-13021715,-752102373,-1187420102],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxMTk4OSwKICAicHJvZmlsZUlkIiA6ICIxMzEzZGFmMDc2OGQ0YmQ5Yjc1ODJkMGI1NWUwZGQxNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJMZW50aWNjaGllIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZjZDFiYzRkYTIzZjYzZmYxZjU4YmI4MzNhOWJjNzg1Y2E1Yjg4ODY0YjQ4OGEyZTU4ZDc2MWRmNzI1NWU4MDEiCiAgICB9CiAgfQp9"}]}}},item_display:"none",transformation:[-0.429920923f,0f,0.113137085f,0.2319997834f,0f,0.4f,0f,-0.29375f,-0.429920923f,0f,-0.113137085f,-0.2319997834f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;740302962,1228429086,-908198443,1376788068],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxODE2NywKICAicHJvZmlsZUlkIiA6ICJjM2ZmNTY5OWZlNWI0OTY2YTYzYzdhMTEzNTBjZGIyNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWNobzkwMDAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRiYmU0NWM0NWIwNGE0NjcyNmQwNjRiNDFiZWRjZWNkOGQ3OGE4MjVlYjBlYWVhM2U3YmY4YmZhMTIzNDM5YSIKICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[-0.608f,0f,0f,-2.166e-7f,0f,0.4f,0f,-0.29375f,0f,0f,-0.16f,0.3280002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1581318742,-1055328070,931889104,1815137108],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkyMzc4OSwKICAicHJvZmlsZUlkIiA6ICI0ZWEwN2YwODlmN2U0MWZhYmMwNjRhMjZlNWM1OWU2ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzcGlmZnRvcGlhMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MjA1Y2Q2NjJmYTljZTY2YTA3ZDQ3OGUwZjVlNjUwMzExZWQ4NmQ1NDJlNDgxMTExODQ4Mzk3ZjU1MzIwMmRkIgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.608f,0f,0f,-2.166e-7f,0f,0.4f,0f,-0.29375f,0f,0f,-0.16f,-0.3279997834f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;308934360,887871749,-337909748,-983906158],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MjMyMjc1NywKICAicHJvZmlsZUlkIiA6ICJmODJmNTQ1MDIzZDA0MTFkYmVlYzU4YWI4Y2JlMTNjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXNwb25kZW50cyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MWYwOWY5YWFhM2ZlMDhjYWRjZWQ5M2EyMTg3Yjk4YTM3MjQ0NzZiNDU3NDBlZmE5Mzg4Y2ZjODVmNTc1MjEzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0="}]}}},item_display:"none",transformation:[-0.429920923f,0f,-0.113137085f,0.2319997834f,0f,0.4f,0f,-0.29375f,0.429920923f,0f,-0.113137085f,0.2320002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;-1683880806,-1942112327,473393514,-2020664574],properties:[{name:"textures",value:"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkzNjA3MiwKICAicHJvZmlsZUlkIiA6ICI2M2VkZjY1ZDcxNmE0NGZmYmUzOWU5MWVkMmNiMjM3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ2aWxlX3NsYXZlbWFzdGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RkZmNiMzc3MDU2NWMxMDE0ZWFlMDQ0ZDkxM2FmMTZkNjM5OWVlYjA5YWFjNmVlYTJiZWZhMjZhNmQxYzQ1IgogICAgfQogIH0KfQ=="}]}}},item_display:"none",transformation:[-0.429920923f,0f,-0.113137085f,-0.2320002166f,0f,0.4f,0f,-0.29375f,0.429920923f,0f,-0.113137085f,-0.2319997834f,0f,0f,0f,1f]}]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Tags:[not-allocated],Passengers:[{teleport_duration:3,Tags:[tower,tower.upgrade-1,tower.sound],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1711634156,-461169337,-1059787917,1478195191],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgyNjA0MiwKICAicHJvZmlsZUlkIiA6ICI4ODYyYjU4ZWI4YjE0MTg5OGY3MWE0YjI2MDA3ZWVkNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYWJyaWV3RiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jNWFhNmMzNjBlMWU2ZDFlNDBiNGEyZTRiMmE0YmRkMGRhYjM3ZWQzZjVjMDUwMjEyNzUyNWFlMzA2ZWM5MiIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.24f,0f,0f,-0.0002123926f,0f,3f,0f,-0.0489623926f,0f,0f,0.56f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-801783750,1624966960,-77099099,-132646985],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzMTk4NCwKICAicHJvZmlsZUlkIiA6ICJiMTM1MDRmMjMxOGI0OWNjYWFkZDcyYWVhYmMyNTQ1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUeXBrZW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWJkNzc5NzgxMjNkM2U0MTRiYTMyNTA4NjE4ODkzYWVmMDQ0ZTQ3YWIwMWVlMDI3MmI2N2EwMmE0Y2Y2NzZiZSIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,3f,0f,-0.0489623926f,-0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1059571874,1248150361,1671379286,-1860005390],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzNzQ3NywKICAicHJvZmlsZUlkIiA6ICIyNDY1ODI2NWVjMjg0NTY4YTg3MDJkOTVlYzdlYTc4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcmdvc1oxMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOThkODI2NWM4YjllMjUwOTM0ODYxNzBhNTJhOGVjODUyNjc2ZTFkMjViNmQwMmI2ODI3MTU5NjY0ZDE4MGY1IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.1697056275f,0f,0.3959797975f,-0.0002123926f,0f,3f,0f,-0.0489623926f,-0.1697056275f,0f,-0.3959797975f,-0.0002123926f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1664772716,-115906095,-1557260402,-1893587790],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTgzNzQ3NywKICAicHJvZmlsZUlkIiA6ICIyNDY1ODI2NWVjMjg0NTY4YTg3MDJkOTVlYzdlYTc4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcmdvc1oxMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOThkODI2NWM4YjllMjUwOTM0ODYxNzBhNTJhOGVjODUyNjc2ZTFkMjViNmQwMmI2ODI3MTU5NjY0ZDE4MGY1IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0f,0f,0.56f,0f,0f,3f,0f,-0.04875f,-0.24f,0f,0f,-0.000625f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1192206450,1609041248,242449329,-880979707],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg0MzE3NywKICAicHJvZmlsZUlkIiA6ICJhZTQyMTU2N2QzODg0YWUxOTJhNmNiNTY4ZmZkNGZhMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aW1vdGh5ZGVvZG9yYW50IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I0OGExMmNjNzI1ZDgxNDFhODg5YjJiMThlNGNiODk4OGFjZmExYTY2NTVjMjM1ZWEzOTJkYTRlZDQ4ZjEwZWIiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[1.2f,0f,0f,0f,0f,0.4f,0f,0.0999279059f,0f,0f,0.4f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1393963923,827509835,998518926,167117054],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg0ODM2MCwKICAicHJvZmlsZUlkIiA6ICJjNWVmOGQ1NDIwOWY0OTdlYWYzYzA1NjA3MjZhYTMwNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaXNoX0RheCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZTNmZTJjMzQ1NjEwYzZmNGQ1NDhjMTkyYTJlMDlkMmIwMmIwNjcxN2M1MDM1OTAxMjUyZGJlZDQ5NjU5NGRlIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0.4f,0f,0f,0f,0f,0.4f,0f,0.0999279059f,0f,0f,1.2f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-722343846,-887699982,42192667,1897286292],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg1NDI0MywKICAicHJvZmlsZUlkIiA6ICI4MWU4ZWU1MTE1ZmE0NzU5OTU1YTAzMThkNTNhNjViYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJDb25zaWRlclRoYXQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY1NmM3ZjQxYTllMjUxNzRhNTIzNjBlZTY1ZmM1ZDc5MmUwYjM2ZDU5MGYwNDgxMmM1NTU5NzEyYzNkNTQ4ZSIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.4f,0f,0f,0f,0f,1.2f,0f,0.2999279059f,0f,0f,0.4f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;261110091,-1914535677,657344909,-1854540656],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg1OTg2MCwKICAicHJvZmlsZUlkIiA6ICJmZDJlZDMzMzI0Mjk0ZThmOWVlZGNiN2RhMWJiNzlkZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJkcmFtYWdnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzljMDViODdhZjc4MWQxMGY0YzY1ZGM2OGQxOGEwYmRiMTExNTk3MDBlM2JjMzU5NjU1ZWYzMTM0MGY5ZmE5NDAiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.8004448763f,0f,0.4016366517f,0f,0f,0.4f,0f,0.0999279059f,-0.8004448763f,0f,0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1388425148,1175819394,-1478272807,811489122],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg2NTg3OSwKICAicHJvZmlsZUlkIiA6ICJjYmNkNDQzZGE1NTI0OGU3ODM3NWNmZjYwMmQzZWI0NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJPX1JlaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82OWNmYzk2ZTkwZjE1NTVmNjg2Y2E0ZDY3Mzc4MDhhMTJjYjlhM2ViYTU2NDFjMTUwYmViYTY5MGI4YzQxZTc3IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.8004448763f,0f,0.4016366517f,0f,0f,0.4f,0f,0.0999279059f,-0.8004448763f,0f,-0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1923067438,-1143504310,-2062165181,-1356314876],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg3MTY1NCwKICAicHJvZmlsZUlkIiA6ICJlY2ExZDJjMWU4ODg0ZjA3OGQxZTBlZGU2ODE4MGRhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MDRDb3NtaWNhbCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81NDY1NzdlMDRkMjhhZmYzNjBhMWRjYTg1MTQyYjUyY2EyMGE0YjI1OWQ1NTYyNmQwNmUyYmVkMmY1NmRhOGRkIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0f,0.4f,0f,0.1f,-0.8004448763f,0f,-0.4016366517f,-0.0000720941f,-0.8004448763f,0f,0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1004764580,-905661497,726124213,78278456],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg3NzE1OCwKICAicHJvZmlsZUlkIiA6ICI3YjA5ZDg5NWQyYjc0NTU3YmM0YTkzNWYyNjU0NWNjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBaXJwbGFuZUdvQnJyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEwMTI0Y2Q4NTMxNGIwNjdmNTQ2ZDY4MmVhZjg2OTFhOWQyMTVkY2JiMjQwMzBkZWUyZTFiYmYzODRiYjZmMDkiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0f,0.4f,0f,0.1f,0.8004448763f,0f,-0.4016366517f,-0.0000720941f,-0.8004448763f,0f,-0.4016366517f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1403240163,882833578,-1107557583,-1997024008],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg4MjYxOCwKICAicHJvZmlsZUlkIiA6ICI1ZjU5NmViY2JlOTQ0NmQxYmI0M2JlNGYzZjRiOGJlNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWlsMHNzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRiMDRkMGM5OTMxOGE3M2NjYmU3YTM2ZTM3M2I5NTFkOTU5MjYzMTc4NWZjZmQyZTVkM2JiMmY1N2EzOTNkNGYiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.8004448763f,0f,0.4016366517f,0f,0.8004448763f,0f,-0.4016366517f,-0.0000720941f,0f,0.4f,0f,0.1f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1693589665,-1668552063,-782397728,1169886498],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg4ODM4MSwKICAicHJvZmlsZUlkIiA6ICI3ZjU3MzM2M2M1YTM0N2JiOGQwYzJjNDYyOTVmN2JlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJNb25leWVoIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzczOTZkZGRiZTRmMjdhODRjZGJmMmRmNjRjNTIzYjIyZjZmZDYwNDU3YjJhYTFhNDNhYWVhOGE4YWZhNTg3Y2MiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[-0.8004448763f,0f,0.4016366517f,0f,0.8004448763f,0f,0.4016366517f,-0.0000720941f,0f,0.4f,0f,0.1f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;311196984,114919632,2146675669,519682996],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk0MjE4NSwKICAicHJvZmlsZUlkIiA6ICJlZGUyYzdhMGFjNjM0MTNiYjA5ZDNmMGJlZTllYzhlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aGVEZXZKYWRlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU5YjY1NmJkNjcyMzEwNWIyM2NmYjU4MGNmYmZhNzVkNzRkN2U2ZjFkOWU2OTI2ODMyYWE2M2U0NDFkNTI3MWIiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[-0.22627417f,0f,0.5374011537f,0f,0f,1f,0f,-1f,-0.22627417f,0f,-0.5374011537f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-59056228,838179571,991108868,-1739558756],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk0ODY4MSwKICAicHJvZmlsZUlkIiA6ICJjMjVlMWMxZTE1YTQ0N2IwOTQ2Zjg2YzYzYzhjYjZkOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJiaWdpYm9zMzIxIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y4ZGQ4YjU1OWUwM2JiN2Q4NzQ4YjNlNjczM2JhYzcyOGYwNjJmMjU5ODM4NDg5ZTg0ZDcxMWQxNDEwZjVkOWQiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.22627417f,0f,0.5374011537f,0f,0f,1f,0f,-1f,-0.22627417f,0f,0.5374011537f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1188583408,1121922715,-79833060,-649429314],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk1NDEzOCwKICAicHJvZmlsZUlkIiA6ICJlNzM4MTYzZTYwM2M0MTFkOTg4MzNiYzkyZTI4Y2IyYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJYZW5va3JhdGVzUml0dmEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI0MjQyZTY0ZGIwYmY4ZDI0ODkzOWYxOTNhMTgyZTA1OWJkNTI5NjA2NzFkNTYxZmMzN2JhOTM1NDAzZDM1NiIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0f,0f,0.76f,0f,0f,1f,0f,-1f,-0.32f,0f,0f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;734465170,-1851806133,-340683718,-1158242367],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk1OTgzMCwKICAicHJvZmlsZUlkIiA6ICIxNTUyNmU1OGZhOWE0NjBmODhhNmZhNjk1M2RlNjgzNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQaWVkcml0YTE3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg1M2I4MjMzMTJkOTE0ZjFiNDk5YTZiNzYzYjBhYWZjYzA3N2FlYThjMzJlZmExN2JhMDBmNjFkMzA5MDY2NmQiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.32f,0f,0f,0f,0f,1f,0f,-1f,0f,0f,0.76f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-287421572,46676062,1791947854,88515642],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk2NTQwOCwKICAicHJvZmlsZUlkIiA6ICIwMTIxYzFjZDUxOTM0M2NmYWM4YzgyZThiNjVjYTFjZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfVmV4X1RWIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU4YmIwN2RiZGYzNDNjN2I4NWJlNjcxOWQwZWU5ZjM5NzQzOTcyNjhkOTVhOWE3YmNmOGEzODZjYzJjYTdlODAiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.4f,0f,0f,0f,0f,0.8485281374f,-0.8485281374f,-1.288125f,0f,0.8485281374f,0.8485281374f,0.211875f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;254975406,-1967469638,-742736850,34932316],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTk3MTM2MCwKICAicHJvZmlsZUlkIiA6ICJkMjM0Y2IxYWQ4MzU0MjdiYTAzZDJkYTJlZThmYmY5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJtY21ha2VzbXlwcHNvZnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjE1ODkzYTAyOTYzMThkMTVhNjJkMzY0ZWFhN2Y3NzkwNjJkZTMxOTBlMjBlMTllYzVlYWRmYmJhMzk1MWM0YyIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0f,0.8485281374f,0.8485281374f,0.211875f,0f,0.8485281374f,-0.8485281374f,-1.288125f,-0.4f,0f,0f,0f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-981640646,-1251251971,1752872049,1026481675],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg5NDU0OSwKICAicHJvZmlsZUlkIiA6ICJhNjJmNGM0MWY5Y2M0ZDRhOGEyZjFkY2NjZjZkZmE5NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCMDBLWSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hYWJhMWE0Mjk0ZGFmNDQyNTkyY2I0ZTY5YmJmYTEzMTkyNWIwNTg4MjQ1YTk5NzM5MWI4NmFjNDI2ZTMxMjhmIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.3199233495f,0f,-0.2032534972f,0.4169778745f,0f,0.4f,0f,-0.79375f,0.7723632892f,0f,-0.0841903551f,0.1725744867f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-2021033125,-1905504091,-1039145598,102354754],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwMDU4NywKICAicHJvZmlsZUlkIiA6ICJmODY0ZjY3ZGJlN2Y0OTBlYTZlODQzMjg2M2NkZWMxOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3lmcmllbmQ1MDY1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzYWNiNzk2NzZkNmFlNjVlMTI1MzUxNWI3YmZkZmY0ZDFmOGI4MWM3MWY0YWY4ZTZmNDZmMjc4YjU0YzQxNTgiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[-0.3199233495f,0f,-0.2032534972f,-0.4163614638f,0f,0.4f,0f,-0.79375f,0.7723632892f,0f,-0.0841903551f,-0.1726059693f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1576389504,1868204836,1146654161,-1575405487],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwNjI0OCwKICAicHJvZmlsZUlkIiA6ICJkOWYxZDI2ZWFkYjg0MjQ4OTI3NjU1NzYzNWNiMzQyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb2JPbGRmbHkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI4MzZmY2Q1YTE3MGRkOWI1ZDE5N2YyYzE2ZTU0YmI4NjliM2NkZWU5NzQ1YTg0NDYyNTQ0ZDAzZWU3ZDY2ZiIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.3199233495f,0f,-0.2032534972f,0.4171017911f,0f,0.4f,0f,-0.79375f,0.7723632892f,0f,0.0841903551f,-0.1726572972f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1908632846,-509133382,1195005207,1823605970],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxMTk4OSwKICAicHJvZmlsZUlkIiA6ICIxMzEzZGFmMDc2OGQ0YmQ5Yjc1ODJkMGI1NWUwZGQxNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJMZW50aWNjaGllIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZjZDFiYzRkYTIzZjYzZmYxZjU4YmI4MzNhOWJjNzg1Y2E1Yjg4ODY0YjQ4OGEyZTU4ZDc2MWRmNzI1NWU4MDEiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0.3199233495f,0f,-0.2032534972f,-0.4164853805f,0f,0.4f,0f,-0.79375f,0.7723632892f,0f,0.0841903551f,0.1726258147f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-211941412,1086914097,-606396955,369484261],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxODE2NywKICAicHJvZmlsZUlkIiA6ICJjM2ZmNTY5OWZlNWI0OTY2YTYzYzdhMTEzNTBjZGIyNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWNobzkwMDAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRiYmU0NWM0NWIwNGE0NjcyNmQwNjRiNDFiZWRjZWNkOGQ3OGE4MjVlYjBlYWVhM2U3YmY4YmZhMTIzNDM5YSIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.7723632892f,0f,-0.0841903551f,0.1728984333f,0f,0.4f,0f,-0.79375f,0.3199233495f,0f,0.2032534972f,-0.4166854104f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-97991607,-1773645565,1178118916,897755638],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkyMzc4OSwKICAicHJvZmlsZUlkIiA6ICI0ZWEwN2YwODlmN2U0MWZhYmMwNjRhMjZlNWM1OWU2ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzcGlmZnRvcGlhMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MjA1Y2Q2NjJmYTljZTY2YTA3ZDQ3OGUwZjVlNjUwMzExZWQ4NmQ1NDJlNDgxMTExODQ4Mzk3ZjU1MzIwMmRkIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0.7723632892f,0f,-0.0841903551f,-0.1722820227f,0f,0.4f,0f,-0.79375f,0.3199233495f,0f,0.2032534972f,0.4166539279f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-348250520,1604843838,-55187472,444383770],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MjMyMjc1NywKICAicHJvZmlsZUlkIiA6ICJmODJmNTQ1MDIzZDA0MTFkYmVlYzU4YWI4Y2JlMTNjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXNwb25kZW50cyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MWYwOWY5YWFhM2ZlMDhjYWRjZWQ5M2EyMTg3Yjk4YTM3MjQ0NzZiNDU3NDBlZmE5Mzg4Y2ZjODVmNTc1MjEzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[0.7723632892f,0f,0.0841903551f,-0.1723333506f,0f,0.4f,0f,-0.79375f,-0.3199233495f,0f,0.2032534972f,-0.4168093271f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1001732057,-1063258822,-1594060362,2143985981],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkzNjA3MiwKICAicHJvZmlsZUlkIiA6ICI2M2VkZjY1ZDcxNmE0NGZmYmUzOWU5MWVkMmNiMjM3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ2aWxlX3NsYXZlbWFzdGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RkZmNiMzc3MDU2NWMxMDE0ZWFlMDQ0ZDkxM2FmMTZkNjM5OWVlYjA5YWFjNmVlYTJiZWZhMjZhNmQxYzQ1IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0.7723632892f,0f,0.0841903551f,0.1729497613f,0f,0.4f,0f,-0.79375f,-0.3199233495f,0f,0.2032534972f,0.4167778445f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-45783725,304056845,-1676212242,-1426709311],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTg5NDU0OSwKICAicHJvZmlsZUlkIiA6ICJhNjJmNGM0MWY5Y2M0ZDRhOGEyZjFkY2NjZjZkZmE5NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCMDBLWSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hYWJhMWE0Mjk0ZGFmNDQyNTkyY2I0ZTY5YmJmYTEzMTkyNWIwNTg4MjQ1YTk5NzM5MWI4NmFjNDI2ZTMxMjhmIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[0f,0f,0.16f,-0.3280002166f,0f,0.4f,0f,-0.29375f,-0.608f,0f,0f,2.166e-7f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1857028805,-2138544783,-1248569402,-1178259956],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwMDU4NywKICAicHJvZmlsZUlkIiA6ICJmODY0ZjY3ZGJlN2Y0OTBlYTZlODQzMjg2M2NkZWMxOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3lmcmllbmQ1MDY1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzYWNiNzk2NzZkNmFlNjVlMTI1MzUxNWI3YmZkZmY0ZDFmOGI4MWM3MWY0YWY4ZTZmNDZmMjc4YjU0YzQxNTgiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[0f,0f,0.16f,0.3279997834f,0f,0.4f,0f,-0.29375f,-0.608f,0f,0f,2.166e-7f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1424301621,-119194595,1334663530,-2004117918],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkwNjI0OCwKICAicHJvZmlsZUlkIiA6ICJkOWYxZDI2ZWFkYjg0MjQ4OTI3NjU1NzYzNWNiMzQyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb2JPbGRmbHkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI4MzZmY2Q1YTE3MGRkOWI1ZDE5N2YyYzE2ZTU0YmI4NjliM2NkZWU5NzQ1YTg0NDYyNTQ0ZDAzZWU3ZDY2ZiIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[-0.429920923f,0f,0.113137085f,-0.2320002166f,0f,0.4f,0f,-0.29375f,-0.429920923f,0f,-0.113137085f,0.2320002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1468294576,-13021715,-752102373,-1187420102],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxMTk4OSwKICAicHJvZmlsZUlkIiA6ICIxMzEzZGFmMDc2OGQ0YmQ5Yjc1ODJkMGI1NWUwZGQxNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJMZW50aWNjaGllIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZjZDFiYzRkYTIzZjYzZmYxZjU4YmI4MzNhOWJjNzg1Y2E1Yjg4ODY0YjQ4OGEyZTU4ZDc2MWRmNzI1NWU4MDEiCiAgICB9CiAgfQp9\"}]}}},item_display:\"none\",transformation:[-0.429920923f,0f,0.113137085f,0.2319997834f,0f,0.4f,0f,-0.29375f,-0.429920923f,0f,-0.113137085f,-0.2319997834f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;740302962,1228429086,-908198443,1376788068],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkxODE2NywKICAicHJvZmlsZUlkIiA6ICJjM2ZmNTY5OWZlNWI0OTY2YTYzYzdhMTEzNTBjZGIyNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZWNobzkwMDAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRiYmU0NWM0NWIwNGE0NjcyNmQwNjRiNDFiZWRjZWNkOGQ3OGE4MjVlYjBlYWVhM2U3YmY4YmZhMTIzNDM5YSIKICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[-0.608f,0f,0f,-2.166e-7f,0f,0.4f,0f,-0.29375f,0f,0f,-0.16f,0.3280002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1581318742,-1055328070,931889104,1815137108],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkyMzc4OSwKICAicHJvZmlsZUlkIiA6ICI0ZWEwN2YwODlmN2U0MWZhYmMwNjRhMjZlNWM1OWU2ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzcGlmZnRvcGlhMiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MjA1Y2Q2NjJmYTljZTY2YTA3ZDQ3OGUwZjVlNjUwMzExZWQ4NmQ1NDJlNDgxMTExODQ4Mzk3ZjU1MzIwMmRkIgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.608f,0f,0f,-2.166e-7f,0f,0.4f,0f,-0.29375f,0f,0f,-0.16f,-0.3279997834f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;308934360,887871749,-337909748,-983906158],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MjMyMjc1NywKICAicHJvZmlsZUlkIiA6ICJmODJmNTQ1MDIzZDA0MTFkYmVlYzU4YWI4Y2JlMTNjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXNwb25kZW50cyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MWYwOWY5YWFhM2ZlMDhjYWRjZWQ5M2EyMTg3Yjk4YTM3MjQ0NzZiNDU3NDBlZmE5Mzg4Y2ZjODVmNTc1MjEzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=\"}]}}},item_display:\"none\",transformation:[-0.429920923f,0f,-0.113137085f,0.2319997834f,0f,0.4f,0f,-0.29375f,0.429920923f,0f,-0.113137085f,0.2320002166f,0f,0f,0f,1f]},{teleport_duration:3,Tags:[tower,tower.upgrade-1],id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;-1683880806,-1942112327,473393514,-2020664574],properties:[{name:\"textures\",value:\"ewogICJ0aW1lc3RhbXAiIDogMTc3NDA4MTkzNjA3MiwKICAicHJvZmlsZUlkIiA6ICI2M2VkZjY1ZDcxNmE0NGZmYmUzOWU5MWVkMmNiMjM3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ2aWxlX3NsYXZlbWFzdGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RkZmNiMzc3MDU2NWMxMDE0ZWFlMDQ0ZDkxM2FmMTZkNjM5OWVlYjA5YWFjNmVlYTJiZWZhMjZhNmQxYzQ1IgogICAgfQogIH0KfQ==\"}]}}},item_display:\"none\",transformation:[-0.429920923f,0f,-0.113137085f,-0.2320002166f,0f,0.4f,0f,-0.29375f,0.429920923f,0f,-0.113137085f,-0.2319997834f,0f,0f,0f,1f]}]}"); }

        // execute as @n[tag=not-allocated] run kill @s
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.killEntity(e);
        } }

        // execute as @e[tag=tower.upgrade-1,type=item_display,distance=..0.5] at @s run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.upgrade-1") && KfcGen.inRange(source.getPosition(), en, -1, 0.5))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc36 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc36.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }
        return 0;
    }

    public static void _m_2f95f9a0_tower_spawn_model_stun_tower_lv2_execute(ServerCommandSource source) {
        _m_2f95f9a0_tower_spawn_model_stun_tower_lv2_executeReturn(source);
    }

    public static int _m_2f95f9a0_tower_spawn_model_stun_tower_lv2_executeReturn(ServerCommandSource source) {
        // [용량 한계 64KB 초과] 원본 mcfunction 호출로 유지(반환값 전파)
        return KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "spawn/model/stun-tower/lv2"));
    }

    public static void _m_f1ca21b5_tower_spawn_model_stun_tower_lv3_execute(ServerCommandSource source) {
        _m_f1ca21b5_tower_spawn_model_stun_tower_lv3_executeReturn(source);
    }

    public static int _m_f1ca21b5_tower_spawn_model_stun_tower_lv3_executeReturn(ServerCommandSource source) {
        // [용량 한계 64KB 초과] 원본 mcfunction 호출로 유지(반환값 전파)
        return KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "spawn/model/stun-tower/lv3"));
    }

    public static void _m_43f54ca5_tower_spawn_model_stun_tower_lv4_execute(ServerCommandSource source) {
        _m_43f54ca5_tower_spawn_model_stun_tower_lv4_executeReturn(source);
    }

    public static int _m_43f54ca5_tower_spawn_model_stun_tower_lv4_executeReturn(ServerCommandSource source) {
        // [용량 한계 64KB 초과] 원본 mcfunction 호출로 유지(반환값 전파)
        return KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "spawn/model/stun-tower/lv4"));
    }

    public static void _m_726df0bc_tower_spawn_model_stun_tower_lv5_execute(ServerCommandSource source) {
        _m_726df0bc_tower_spawn_model_stun_tower_lv5_executeReturn(source);
    }

    public static int _m_726df0bc_tower_spawn_model_stun_tower_lv5_executeReturn(ServerCommandSource source) {
        // [용량 한계 64KB 초과] 원본 mcfunction 호출로 유지(반환값 전파)
        return KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "spawn/model/stun-tower/lv5"));
    }

    public static void _m_ac8c6cbc_tower_spawn_model_supporter_lv0_execute(ServerCommandSource source) {
        _m_ac8c6cbc_tower_spawn_model_supporter_lv0_executeReturn(source);
    }

    public static int _m_ac8c6cbc_tower_spawn_model_supporter_lv0_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:smooth_stone",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:smooth_stone\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}"); }
        return 0;
    }

    public static void _m_c2f15088_tower_spawn_model_supporter_lv1_execute(ServerCommandSource source) {
        _m_c2f15088_tower_spawn_model_supporter_lv1_executeReturn(source);
    }

    public static int _m_c2f15088_tower_spawn_model_supporter_lv1_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:iron_block",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:infested_chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.8f,0.125f,0.8f],scale:[0.5f,0.25f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:infested_chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.8f,0.125f,0.8f],scale:[0.5f,0.25f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:infested_chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.8f,0.125f,-0.8f],scale:[0.5f,0.25f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:infested_chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.8f,0.125f,-0.8f],scale:[0.5f,0.25f,0.5f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:iron_block\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:infested_chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.8f,0.125f,0.8f],scale:[0.5f,0.25f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:infested_chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.8f,0.125f,0.8f],scale:[0.5f,0.25f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:infested_chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.8f,0.125f,-0.8f],scale:[0.5f,0.25f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:infested_chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.8f,0.125f,-0.8f],scale:[0.5f,0.25f,0.5f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}"); }
        return 0;
    }

    public static void _m_083f103b_tower_spawn_model_supporter_lv2_execute(ServerCommandSource source) {
        _m_083f103b_tower_spawn_model_supporter_lv2_executeReturn(source);
    }

    public static int _m_083f103b_tower_spawn_model_supporter_lv2_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:chiseled_polished_blackstone",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.05f,0f],scale:[2f,0.1f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:chiseled_polished_blackstone",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[1.8f,0.1f,1.8f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:black_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[1.801f,0.099f,1.801f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:black_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.05f,0f],scale:[2.001f,0.099f,2.001f]},Tags:[tower.supporter,not-allocated,tower],Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gold_block",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.15f,0f],scale:[1.7f,0.1f,1.7f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:chiseled_polished_blackstone\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.05f,0f],scale:[2f,0.1f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:chiseled_polished_blackstone\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[1.8f,0.1f,1.8f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:black_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[1.801f,0.099f,1.801f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:black_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.05f,0f],scale:[2.001f,0.099f,2.001f]},Tags:[tower.supporter,not-allocated,tower],Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gold_block\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.15f,0f],scale:[1.7f,0.1f,1.7f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}"); }
        return 0;
    }

    public static void _m_d95fbe34_tower_spawn_model_supporter_lv3_execute(ServerCommandSource source) {
        _m_d95fbe34_tower_spawn_model_supporter_lv3_executeReturn(source);
    }

    public static int _m_d95fbe34_tower_spawn_model_supporter_lv3_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:netherite_block",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:diamond_block",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[2.001f,0.4f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:diamond_block",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[0.5f,0.4f,2.001f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:iron_block",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.25f,0f],scale:[0.6f,0.4f,0.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:infested_chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.8f,0.15f,0.8f],scale:[0.7f,0.3f,0.7f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:infested_chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.8f,0.15f,0.8f],scale:[0.7f,0.3f,0.7f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:infested_chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.8f,0.15f,-0.8f],scale:[0.7f,0.3f,0.7f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:infested_chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.8f,0.15f,-0.8f],scale:[0.7f,0.3f,0.7f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:netherite_block\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:diamond_block\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[2.001f,0.4f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:diamond_block\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[0.5f,0.4f,2.001f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:iron_block\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.25f,0f],scale:[0.6f,0.4f,0.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:infested_chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.8f,0.15f,0.8f],scale:[0.7f,0.3f,0.7f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:infested_chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.8f,0.15f,0.8f],scale:[0.7f,0.3f,0.7f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:infested_chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.8f,0.15f,-0.8f],scale:[0.7f,0.3f,0.7f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:infested_chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.8f,0.15f,-0.8f],scale:[0.7f,0.3f,0.7f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}"); }
        return 0;
    }

    public static void _m_b0cdf714_tower_spawn_model_supporter_lv4_execute(ServerCommandSource source) {
        _m_b0cdf714_tower_spawn_model_supporter_lv4_executeReturn(source);
    }

    public static int _m_b0cdf714_tower_spawn_model_supporter_lv4_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:diamond_block",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.05f,0f],scale:[2f,0.1f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gray_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.90125f,0.1f,0f],scale:[0.2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gray_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.90125f,0.1f,0f],scale:[0.2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gray_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0.90125f],scale:[2f,0.2f,0.2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gray_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,-0.90125f],scale:[2f,0.2f,0.2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gray_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.72075f,0.48f,-0.00025f],scale:[0.16f,0.16f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gray_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.72125f,0.48f,-0.00025f],scale:[0.16f,0.16f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gray_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.00025f,0.48f,0.72075f],scale:[1.6f,0.16f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:gray_concrete",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.00025f,0.48f,-0.72125f],scale:[1.6f,0.16f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:light_blue_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.720625f,0.25f,0f],scale:[0.16f,0.05f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:light_blue_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.72125f,0.25f,0f],scale:[0.16f,0.05f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:light_blue_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.25f,0.720625f],scale:[1.6f,0.05f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:light_blue_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.25f,-0.72125f],scale:[1.6f,0.05f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:light_blue_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.72075f,0.35f,-0.00025f],scale:[0.16f,0.05f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:light_blue_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.72125f,0.35f,-0.00025f],scale:[0.16f,0.05f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:light_blue_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.00025f,0.35f,0.72075f],scale:[1.6f,0.05f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:light_blue_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.00025f,0.35f,-0.72125f],scale:[1.6f,0.05f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:chiseled_stone_bricks",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1.5f,0.2f,1.5f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:diamond_block\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.05f,0f],scale:[2f,0.1f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gray_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.90125f,0.1f,0f],scale:[0.2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gray_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.90125f,0.1f,0f],scale:[0.2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gray_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0.90125f],scale:[2f,0.2f,0.2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gray_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,-0.90125f],scale:[2f,0.2f,0.2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gray_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.72075f,0.48f,-0.00025f],scale:[0.16f,0.16f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gray_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.72125f,0.48f,-0.00025f],scale:[0.16f,0.16f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gray_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.00025f,0.48f,0.72075f],scale:[1.6f,0.16f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:gray_concrete\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.00025f,0.48f,-0.72125f],scale:[1.6f,0.16f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_blue_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.720625f,0.25f,0f],scale:[0.16f,0.05f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_blue_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.72125f,0.25f,0f],scale:[0.16f,0.05f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_blue_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.25f,0.720625f],scale:[1.6f,0.05f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_blue_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.25f,-0.72125f],scale:[1.6f,0.05f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_blue_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0.72075f,0.35f,-0.00025f],scale:[0.16f,0.05f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_blue_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.72125f,0.35f,-0.00025f],scale:[0.16f,0.05f,1.6f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_blue_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.00025f,0.35f,0.72075f],scale:[1.6f,0.05f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_blue_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[-0.00025f,0.35f,-0.72125f],scale:[1.6f,0.05f,0.16f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:chiseled_stone_bricks\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[1.5f,0.2f,1.5f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}"); }
        return 0;
    }

    public static void _m_c0a85629_tower_spawn_model_supporter_lv5_execute(ServerCommandSource source) {
        _m_c0a85629_tower_spawn_model_supporter_lv5_executeReturn(source);
    }

    public static int _m_c0a85629_tower_spawn_model_supporter_lv5_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:beacon",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:beacon\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}"); }
        return 0;
    }

    public static void _m_de429101_tower_spawn_model_supporter_lv6_execute(ServerCommandSource source) {
        _m_de429101_tower_spawn_model_supporter_lv6_executeReturn(source);
    }

    public static int _m_de429101_tower_spawn_model_supporter_lv6_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:netherite_block",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:chiseled_polished_blackstone",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[2.001f,0.4f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:chiseled_polished_blackstone",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[0.5f,0.4f,2.001f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:chiseled_polished_blackstone",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.3f,0f],scale:[1f,0.4f,1f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:purple_stained_glass",count:1},item_display:"none",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.30125f,0f],scale:[1.001f,0.4f,1.001f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:purple_stained_glass_pane",count:1},item_display:"none",transformation:{left_rotation:[0.70710678f,0f,0f,0.70710678f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.6f,0f],scale:[1f,1f,1f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:purple_stained_glass_pane",count:1},item_display:"none",transformation:{left_rotation:[0.70710678f,0f,0f,0.70710678f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.7f,0f],scale:[1.2f,1.2f,1f]},Tags:[tower.supporter,not-allocated,tower]},{id:"minecraft:item_display",item:{id:"minecraft:purple_stained_glass_pane",count:1},item_display:"none",transformation:{left_rotation:[0.70710678f,0f,0f,0.70710678f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.8f,0f],scale:[1.4f,1.4f,1f]},Tags:[tower.supporter,not-allocated,tower],Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:netherite_block\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0f],scale:[2f,0.2f,2f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:chiseled_polished_blackstone\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[2.001f,0.4f,0.5f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:chiseled_polished_blackstone\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.2f,0f],scale:[0.5f,0.4f,2.001f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:chiseled_polished_blackstone\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.3f,0f],scale:[1f,0.4f,1f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:purple_stained_glass\",count:1},item_display:\"none\",transformation:{left_rotation:[0f,1f,0f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.30125f,0f],scale:[1.001f,0.4f,1.001f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:purple_stained_glass_pane\",count:1},item_display:\"none\",transformation:{left_rotation:[0.70710678f,0f,0f,0.70710678f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.6f,0f],scale:[1f,1f,1f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:purple_stained_glass_pane\",count:1},item_display:\"none\",transformation:{left_rotation:[0.70710678f,0f,0f,0.70710678f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.7f,0f],scale:[1.2f,1.2f,1f]},Tags:[tower.supporter,not-allocated,tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:purple_stained_glass_pane\",count:1},item_display:\"none\",transformation:{left_rotation:[0.70710678f,0f,0f,0.70710678f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.8f,0f],scale:[1.4f,1.4f,1f]},Tags:[tower.supporter,not-allocated,tower],Tags:[tower.supporter,not-allocated,tower]}],Tags:[tower.supporter,not-allocated,tower]}"); }
        return 0;
    }

    public static void _m_f961b5f9_tower_spawn_model_weakness_tower_lv0_execute(ServerCommandSource source) {
        _m_f961b5f9_tower_spawn_model_weakness_tower_lv0_executeReturn(source);
    }

    public static int _m_f961b5f9_tower_spawn_model_weakness_tower_lv0_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon block_display ~ ~1.5 ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:oak_wood"},item_display:"none",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.7125f,0f],scale:[0.2f,1.2f,0.2f]},Tags:[tower,weakness-tower]},{id:"minecraft:item_display",item:{id:"minecraft:glass"},item_display:"none",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.025f,-0.875f],scale:[0.5f,0.5f,1f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:item_display",item:{id:"minecraft:magenta_concrete"},item_display:"none",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.025f,-0.875f],scale:[0.4f,0.4f,0.9f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:item_display",item:{id:"minecraft:red_concrete"},item_display:"none",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.025f,0f],scale:[0.6f,0.6f,1f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:item_display",item:{id:"minecraft:red_concrete"},item_display:"none",transformation:{left_rotation:[-0.25881905f,0f,0f,0.96592583f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.2125f,0f],scale:[0.599f,0.3f,0.98f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:item_display",item:{id:"minecraft:magenta_concrete"},item_display:"none",transformation:{left_rotation:[-0.70710678f,0f,0f,0.70710678f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1625f,0.8125f],scale:[0.5f,1f,0.5f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:item_display",item:{id:"minecraft:iron_block"},item_display:"none",transformation:{left_rotation:[-0.79863551f,0f,0f,0.60181502f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0.166875f],scale:[0.499f,0.5f,0.5f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:item_display",item:{id:"minecraft:light_gray_concrete"},item_display:"none",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[-0.125f,-0.2125f,0.8125f],scale:[0.1f,0.1f,1f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:item_display",item:{id:"minecraft:light_gray_concrete"},item_display:"none",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0.125f,-0.2125f,0.8125f],scale:[0.1f,0.1f,1f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:item_display",item:{id:"minecraft:player_head",components:{"minecraft:profile":{id:[I;1961346295,-1283909184,-1955977319,-1738626496],properties:[{name:"textures",value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2IyZjJiNzY0MmI1NDQ5MGI5ZmZhMWUwZTFiMjk2YWFkMmI3YTRlZGI5MzYxZTdlOGU5OTAzYjY0ZGJiODkzOSJ9fX0="}]}}},item_display:"none",transformation:{left_rotation:[0f,-0.70710678f,0.70710678f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.0875f,1.0625f],scale:[0.8f,1f,0.8f]},Tags:[tower,weakness-tower,tower.head,"tower.animation0",tower.animation],teleport_duration:1},{id:"minecraft:interaction",width:2f,height:-1.5f,response:1b,Tags:[tower,tower.hitbox]},{id:"minecraft:marker",Tags:[tower,weakness-tower,tower.head,tower.muzzle,tower.animation,tower.sound]}],Tags:[tower,weakness-tower,tower.core]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.5), source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:oak_wood\"},item_display:\"none\",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.7125f,0f],scale:[0.2f,1.2f,0.2f]},Tags:[tower,weakness-tower]},{id:\"minecraft:item_display\",item:{id:\"minecraft:glass\"},item_display:\"none\",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.025f,-0.875f],scale:[0.5f,0.5f,1f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:item_display\",item:{id:\"minecraft:magenta_concrete\"},item_display:\"none\",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.025f,-0.875f],scale:[0.4f,0.4f,0.9f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:item_display\",item:{id:\"minecraft:red_concrete\"},item_display:\"none\",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.025f,0f],scale:[0.6f,0.6f,1f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:item_display\",item:{id:\"minecraft:red_concrete\"},item_display:\"none\",transformation:{left_rotation:[-0.25881905f,0f,0f,0.96592583f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.2125f,0f],scale:[0.599f,0.3f,0.98f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:item_display\",item:{id:\"minecraft:magenta_concrete\"},item_display:\"none\",transformation:{left_rotation:[-0.70710678f,0f,0f,0.70710678f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1625f,0.8125f],scale:[0.5f,1f,0.5f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:item_display\",item:{id:\"minecraft:iron_block\"},item_display:\"none\",transformation:{left_rotation:[-0.79863551f,0f,0f,0.60181502f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.1f,0.166875f],scale:[0.499f,0.5f,0.5f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_gray_concrete\"},item_display:\"none\",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[-0.125f,-0.2125f,0.8125f],scale:[0.1f,0.1f,1f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:item_display\",item:{id:\"minecraft:light_gray_concrete\"},item_display:\"none\",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0.125f,-0.2125f,0.8125f],scale:[0.1f,0.1f,1f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:item_display\",item:{id:\"minecraft:player_head\",components:{\"minecraft:profile\":{id:[I;1961346295,-1283909184,-1955977319,-1738626496],properties:[{name:\"textures\",value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2IyZjJiNzY0MmI1NDQ5MGI5ZmZhMWUwZTFiMjk2YWFkMmI3YTRlZGI5MzYxZTdlOGU5OTAzYjY0ZGJiODkzOSJ9fX0=\"}]}}},item_display:\"none\",transformation:{left_rotation:[0f,-0.70710678f,0.70710678f,0f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.0875f,1.0625f],scale:[0.8f,1f,0.8f]},Tags:[tower,weakness-tower,tower.head,\"tower.animation0\",tower.animation],teleport_duration:1},{id:\"minecraft:interaction\",width:2f,height:-1.5f,response:1b,Tags:[tower,tower.hitbox]},{id:\"minecraft:marker\",Tags:[tower,weakness-tower,tower.head,tower.muzzle,tower.animation,tower.sound]}],Tags:[tower,weakness-tower,tower.core]}"); }

        // function tower:spawn/model/supporter/lv0
        // -> tower:spawn/model/supporter/lv0
        tdpack.buckets.Bucket10._m_ac8c6cbc_tower_spawn_model_supporter_lv0_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc45 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc45.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }
        return 0;
    }

    public static void _m_aff25874_tower_spawn_model_weakness_tower_lv1_execute(ServerCommandSource source) {
        _m_aff25874_tower_spawn_model_weakness_tower_lv1_executeReturn(source);
    }

    public static int _m_aff25874_tower_spawn_model_weakness_tower_lv1_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv1
        // -> tower:spawn/model/supporter/lv1
        tdpack.buckets.Bucket10._m_c2f15088_tower_spawn_model_supporter_lv1_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc46 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc46.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }
        return 0;
    }

    public static void _m_fd939ad7_tower_spawn_model_weakness_tower_lv2_execute(ServerCommandSource source) {
        _m_fd939ad7_tower_spawn_model_weakness_tower_lv2_executeReturn(source);
    }

    public static int _m_fd939ad7_tower_spawn_model_weakness_tower_lv2_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv2
        // -> tower:spawn/model/supporter/lv2
        tdpack.buckets.Bucket10._m_083f103b_tower_spawn_model_supporter_lv2_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc47 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc47.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }
        return 0;
    }

    public static void _m_46c0d744_tower_spawn_model_weakness_tower_lv3_execute(ServerCommandSource source) {
        _m_46c0d744_tower_spawn_model_weakness_tower_lv3_executeReturn(source);
    }

    public static int _m_46c0d744_tower_spawn_model_weakness_tower_lv3_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv3
        // -> tower:spawn/model/supporter/lv3
        tdpack.buckets.Bucket10._m_d95fbe34_tower_spawn_model_supporter_lv3_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc48 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc48.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }
        return 0;
    }

    public static void _m_1a3c8846_tower_spawn_model_weakness_tower_lv4_execute(ServerCommandSource source) {
        _m_1a3c8846_tower_spawn_model_weakness_tower_lv4_executeReturn(source);
    }

    public static int _m_1a3c8846_tower_spawn_model_weakness_tower_lv4_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv4
        // -> tower:spawn/model/supporter/lv4
        tdpack.buckets.Bucket10._m_b0cdf714_tower_spawn_model_supporter_lv4_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc49 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc49.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }
        return 0;
    }

    public static void _m_9eb423b1_tower_spawn_model_weakness_tower_lv5_execute(ServerCommandSource source) {
        _m_9eb423b1_tower_spawn_model_weakness_tower_lv5_executeReturn(source);
    }

    public static int _m_9eb423b1_tower_spawn_model_weakness_tower_lv5_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv5
        // -> tower:spawn/model/supporter/lv5
        tdpack.buckets.Bucket10._m_c0a85629_tower_spawn_model_supporter_lv5_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc50 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc50.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }
        return 0;
    }

    public static void _m_874ae774_tower_spawn_summon_allocate_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_874ae774_tower_spawn_summon_allocate_executeReturn(source, macroArgs);
    }

    public static int _m_874ae774_tower_spawn_summon_allocate_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // $summon marker ~ ~ ~ {Tags:[tower,tower.data,tower.animation,$(model)]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[tower,tower.data,tower.animation," + macroArgs.get("model") + "]}"); }

        // ride @n[tag=tower.data] mount @s
        { net.minecraft.entity.Entity _re = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)); net.minecraft.entity.Entity _rv = executor; if (_re != null && _rv != null) KfcGen.rideMount(_re, _rv); }

        // execute on passengers at @s[tag=tower.data] run data modify entity @s data set from storage tower temp2.components.minecraft:custom_data.Tower_Status
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc51 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.data"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc51 != null) {
                { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetStorage(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status"); if (_v != null) KfcGen.nbtSetEntity(_vE1, "data", _v); }
                }
            }
        }

        // summon marker ~ ~ ~ {Tags:[tower,tower.upgrade]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[tower,tower.upgrade]}"); }

        // ride @n[tag=tower.upgrade] mount @s
        { net.minecraft.entity.Entity _re = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.upgrade"}, new String[0], -1, -1, _ee -> (true)); net.minecraft.entity.Entity _rv = executor; if (_re != null && _rv != null) KfcGen.rideMount(_re, _rv); }

        // execute on passengers at @s[tag=tower.upgrade] run data modify entity @s data set from storage tower temp2.components.minecraft:custom_data.Tower_Upgrade
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc52 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.upgrade"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc52 != null) {
                { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetStorage(server, "tower", "temp2.components.minecraft:custom_data.Tower_Upgrade"); if (_v != null) KfcGen.nbtSetEntity(_vE1, "data", _v); }
                }
            }
        }

        // execute on passengers at @s[tag=tower.muzzle] run data modify entity @s data set from storage tower temp2.components.minecraft:custom_data.Tower_Status
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc53 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc53 != null) {
                { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetStorage(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status"); if (_v != null) KfcGen.nbtSetEntity(_vE1, "data", _v); }
                }
            }
        }

        // scoreboard players add total tower.number 1
        KfcGen.addScore(sb, "total", "tower.number", 1);

        // execute on passengers run scoreboard players operation @s tower.number = total tower.number
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (_onEnt1 != null) KfcGen.opScore(sb, _onEnt1.getNameForScoreboard(), "tower.number", "=", "total", "tower.number");
        }

        // scoreboard players operation @s tower.number = total tower.number
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "tower.number", "=", "total", "tower.number");

        // execute store result entity @s data.number int 1 run scoreboard players get total tower.number
        { net.minecraft.entity.Entity _se = executor; if (_se != null) KfcGen.entityPutNumberPath(_se, "data.number", "int", KfcGen.getScore(sb, "total", "tower.number")); }

        // execute on passengers store result entity @s data.number int 1 run scoreboard players get total tower.number
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            { net.minecraft.entity.Entity _se = _onEnt1; if (_se != null) KfcGen.entityPutNumberPath(_se, "data.number", "int", KfcGen.getScore(sb, "total", "tower.number")); }
        }

        // scoreboard players set @n[tag=tower.data] tower.level 0
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.level", 0); }

        // execute if score #temp blueprint_id matches 1 run scoreboard players set @n[tag=tower.data] tower.y -60
        if (KfcGen.scoreMatches(sb, "#temp", "blueprint_id", 1, 1)) {
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.y", -60); }
        }

        // execute if score #temp blueprint_id matches 2 run scoreboard players set @n[tag=tower.data] tower.y -52
        if (KfcGen.scoreMatches(sb, "#temp", "blueprint_id", 2, 2)) {
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.y", -52); }
        }

        // execute at @s run summon marker ~ ~ ~ {Tags:[tower,tower.upgrade_range]}
        {
            ServerCommandSource kfcSrc54 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(kfcSrc54.getPosition().x, kfcSrc54.getPosition().y, kfcSrc54.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[tower,tower.upgrade_range]}"); }
        }

        // execute on passengers at @s[tag=tower] store result score @s tower.animation run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.attack_speed 1.0
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc55 = (((_vE1 != null && _vE1.getCommandTags().contains("tower"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc55 != null) {
                int _stv = (int)(KfcGen.storageGetDouble(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status.attack_speed"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.animation", (int)(_stv));
                }
            }
        }

        // execute on passengers at @s[tag=tower.muzzle] store result score @s tower.attack run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.attack 1.0
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc56 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc56 != null) {
                int _stv = (int)(KfcGen.storageGetDouble(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status.attack"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.attack", (int)(_stv));
                }
            }
        }

        // execute on passengers at @s[tag=tower.muzzle] store result score @s tower.attack_speed run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.attack_speed 1.0
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc57 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc57 != null) {
                int _stv = (int)(KfcGen.storageGetDouble(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status.attack_speed"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.attack_speed", (int)(_stv));
                }
            }
        }

        // execute on passengers at @s[tag=tower.muzzle] store result score @s tower.target_mode run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.target_mode 1.0
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc58 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc58 != null) {
                int _stv = (int)(KfcGen.storageGetDouble(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status.target_mode"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.target_mode", (int)(_stv));
                }
            }
        }

        // execute on passengers at @s[tag=tower.muzzle,tag=farm] run scoreboard players operation @s tower.attack_countdown = @s tower.attack_speed
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc59 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle") && _vE1.getCommandTags().contains("farm"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc59 != null) {
                if (_vE1 != null && _vE1 != null) KfcGen.opScore(sb, _vE1.getNameForScoreboard(), "tower.attack_countdown", "=", _vE1.getNameForScoreboard(), "tower.attack_speed");
                }
            }
        }

        // execute on passengers at @s[tag=tower.muzzle,tag=farm] run scoreboard players remove @s tower.attack_countdown 1
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc60 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle") && _vE1.getCommandTags().contains("farm"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc60 != null) {
                if (_vE1 != null) KfcGen.addScore(sb, _vE1.getNameForScoreboard(), "tower.attack_countdown", -(1));
                }
            }
        }
        return 0;
    }

    public static void _m_6d8abc37_tower_spawn_summon_detect_limit_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_6d8abc37_tower_spawn_summon_detect_limit_executeReturn(source, macroArgs);
    }

    public static int _m_6d8abc37_tower_spawn_summon_detect_limit_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $execute store result score #count tower.limit if entity @e[tag=tower.core,tag=$(model)]
        int scnt_61 = 0;
        for (net.minecraft.entity.Entity _ce : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.core", macroArgs.get("model")}, new String[0], -1, -1)) {
            scnt_61++;
        }
        KfcGen.setScore(sb, "#count", "tower.limit", scnt_61);
        return 0;
    }

    public static void _m_545695b9_tower_spawn_summon_main_execute(ServerCommandSource source) {
        _m_545695b9_tower_spawn_summon_main_executeReturn(source);
    }

    public static int _m_545695b9_tower_spawn_summon_main_executeReturn(ServerCommandSource source) {
        
        // 부분 변환(디스패처 의존 2/9줄) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "spawn/summon/main"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // item replace entity @s weapon.mainhand from entity @s weapon.offhand
                // if (executor != null) { net.minecraft.entity.Entity _isrc = executor; if (_isrc != null) KfcGen.itemReplaceFrom(executor, "weapon.mainhand", _isrc, "weapon.offhand"); }
                // 
                // // item replace entity @s weapon.offhand with air
                // if (executor != null) KfcGen.itemReplaceWith(server, executor, "weapon.offhand", "air", 1);
                // 
                // // execute store result score #temp tower.limit run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.limit 1.0
                // KfcGen.setScore(sb, "#temp", "tower.limit", (int)(KfcGen.storageGetDouble(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status.limit")));
                // 
                // // function tower:spawn/summon/detect_limit with storage tower temp2.components.minecraft:custom_data.Tower_Status
                // // -> tower:spawn/summon/detect_limit
                // tdpack.buckets.Bucket10._m_6d8abc37_tower_spawn_summon_detect_limit_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status"));
                // 
                // // execute if score #count tower.limit >= #temp tower.limit run tellraw @s [{"text":"더 이상 소환할 수 없습니다.","color":"red"}]
                // if (KfcGen.scoreCmp(sb, "#count", "tower.limit", ">=", "#temp", "tower.limit")) {
                //     if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{\"text\":\"더 이상 소환할 수 없습니다.\",\"color\":\"red\"}]");
                // }
                // 
                // // execute if score #count tower.limit >= #temp tower.limit run return 0
                // if (KfcGen.scoreCmp(sb, "#count", "tower.limit", ">=", "#temp", "tower.limit")) {
                //     return 0;
                // }
                // 
                // // execute store result score #temp blueprint_id run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.location 1.0
                // KfcGen.setScore(sb, "#temp", "blueprint_id", (int)(KfcGen.storageGetDouble(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status.location")));
                // 
                // // execute if score #temp blueprint_id matches 1 anchored eyes facing ~ -60 ~ anchored feet positioned ^ ^ ^1 positioned ~ ~1 ~ positioned as @s[distance=..1] positioned ~ -60 ~ rotated ~ 0 positioned ^ ^ ^256 facing entity @s eyes positioned as @s positioned ^ ^1 ^ rotated as @s positioned ^ ^ ^1 positioned as @s[distance=..1.414] positioned ~ -60 ~ rotated ~ 0 positioned ^ ^ ^128 facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^64 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^32 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^16 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^8 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^4 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^2 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^1 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.5 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.25 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.125 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.0625 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.03125 ^ positioned ~ -60 ~ run function tower:spawn/summon/tower with storage tower temp2.components.minecraft:custom_data.Tower_Status
                // // ⛔ 자바 변환 불가(함수 폴백): execute 수정자 미지원: positioned as @s[distance=..1] (단일 해소 불가)
                // 
                // // execute if score #temp blueprint_id matches 2 anchored eyes facing ~ -52 ~ anchored feet positioned ^ ^ ^1 positioned ~ ~1 ~ positioned as @s[distance=..1] positioned ~ -52 ~ rotated ~ 0 positioned ^ ^ ^256 facing entity @s eyes positioned as @s positioned ^ ^1 ^ rotated as @s positioned ^ ^ ^1 positioned as @s[distance=..1.414] positioned ~ -52 ~ rotated ~ 0 positioned ^ ^ ^128 facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^64 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^32 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^16 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^8 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^4 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^2 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^1 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.5 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.25 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.125 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.0625 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.03125 ^ positioned ~ -52 ~ run function tower:spawn/summon/tower with storage tower temp2.components.minecraft:custom_data.Tower_Status
                // // ⛔ 자바 변환 불가(함수 폴백): execute 수정자 미지원: positioned as @s[distance=..1] (단일 해소 불가)
                return kfcBridgeRet;
    }

    public static void _m_f70587f3_tower_spawn_summon_tower_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_f70587f3_tower_spawn_summon_tower_executeReturn(source, macroArgs);
    }

    public static int _m_f70587f3_tower_spawn_summon_tower_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 3/14줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "spawn/summon/tower"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $execute positioned ~$(translation) ~-0.001 ~$(translation) align xz if entity @e[tag=tower.hitbox,dx=$(hitbox),dy=0,dz=$(hitbox)] run tellraw @s {text:"타워를 겹쳐 소환할 수 없습니다!",color:"red"}
                // {
                //     ServerCommandSource kfcSrc82 = null; try { kfcSrc82 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + Double.parseDouble(macroArgs.get("translation"))), (source.getPosition().y + -0.001), (source.getPosition().z + Double.parseDouble(macroArgs.get("translation"))))); } catch (NumberFormatException _nfe) {}
                //     ServerCommandSource kfcSrc83 = kfcSrc82.withPosition(new net.minecraft.util.math.Vec3d(Math.floor(kfcSrc82.getPosition().x), kfcSrc82.getPosition().y, Math.floor(kfcSrc82.getPosition().z)));
                //     if (KfcGen.anyEntityInBox(ctx, kfcSrc83.getPosition(), null, new String[]{"tower.hitbox"}, new String[0], macroArgs.get("hitbox"), 0, macroArgs.get("hitbox"))) {
                //         if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, kfcSrc83, "{text:\"타워를 겹쳐 소환할 수 없습니다!\",color:\"red\"}");
                //     }
                // }
                // 
                // // $execute positioned ~$(translation) ~-0.001 ~$(translation) align xz if entity @e[tag=tower.hitbox,dx=$(hitbox),dy=0,dz=$(hitbox)] run playsound minecraft:block.note_block.bass neutral @s
                // {
                //     ServerCommandSource kfcSrc84 = null; try { kfcSrc84 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + Double.parseDouble(macroArgs.get("translation"))), (source.getPosition().y + -0.001), (source.getPosition().z + Double.parseDouble(macroArgs.get("translation"))))); } catch (NumberFormatException _nfe) {}
                //     ServerCommandSource kfcSrc85 = kfcSrc84.withPosition(new net.minecraft.util.math.Vec3d(Math.floor(kfcSrc84.getPosition().x), kfcSrc84.getPosition().y, Math.floor(kfcSrc84.getPosition().z)));
                //     if (KfcGen.anyEntityInBox(ctx, kfcSrc85.getPosition(), null, new String[]{"tower.hitbox"}, new String[0], macroArgs.get("hitbox"), 0, macroArgs.get("hitbox"))) {
                //         if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:block.note_block.bass", "neutral", new net.minecraft.util.math.Vec3d(kfcSrc85.getPosition().x, kfcSrc85.getPosition().y, kfcSrc85.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc85.getPosition().x, kfcSrc85.getPosition().y, kfcSrc85.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc85.getPosition().x, kfcSrc85.getPosition().y, kfcSrc85.getPosition().z).z, 1.0f, 1.0f);
                //     }
                // }
                // 
                // // $execute positioned ~$(translation) ~-0.001 ~$(translation) align xz if entity @e[tag=tower.hitbox,dx=$(hitbox),dy=0,dz=$(hitbox)] run return 0
                // {
                //     ServerCommandSource kfcSrc86 = null; try { kfcSrc86 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + Double.parseDouble(macroArgs.get("translation"))), (source.getPosition().y + -0.001), (source.getPosition().z + Double.parseDouble(macroArgs.get("translation"))))); } catch (NumberFormatException _nfe) {}
                //     ServerCommandSource kfcSrc87 = kfcSrc86.withPosition(new net.minecraft.util.math.Vec3d(Math.floor(kfcSrc86.getPosition().x), kfcSrc86.getPosition().y, Math.floor(kfcSrc86.getPosition().z)));
                //     if (KfcGen.anyEntityInBox(ctx, kfcSrc87.getPosition(), null, new String[]{"tower.hitbox"}, new String[0], macroArgs.get("hitbox"), 0, macroArgs.get("hitbox"))) {
                //         return 0;
                //     }
                // }
                // 
                // // $execute positioned ~$(translation) ~-2 ~$(translation) align xz unless blocks ~ ~ ~ ~$(hitbox) ~ ~$(hitbox) 0 -62 0 all run tellraw @s {text:"타워를 설치할 수 없는 곳입니다!",color:"red"}
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // $execute positioned ~$(translation) ~-2 ~$(translation) align xz unless blocks ~ ~ ~ ~$(hitbox) ~ ~$(hitbox) 0 -62 0 all run playsound minecraft:block.note_block.bass neutral @s
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // $execute positioned ~$(translation) ~-2 ~$(translation) align xz unless blocks ~ ~ ~ ~$(hitbox) ~ ~$(hitbox) 0 -62 0 all run return 0
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // $execute unless entity @s[scores={money=$(price)..}] run tellraw @s {text:"돈이 부족합니다!",color:"red"}
                // boolean _mcond94 = false; try { _mcond94 = (!((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "money", Integer.parseInt(macroArgs.get("price")), Integer.MAX_VALUE)))); } catch (NumberFormatException _nfe) {}
                // if (_mcond94) {
                //     if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{text:\"돈이 부족합니다!\",color:\"red\"}");
                // }
                // 
                // // $execute unless entity @s[scores={money=$(price)..}] run playsound minecraft:block.note_block.bass neutral @s
                // boolean _mcond95 = false; try { _mcond95 = (!((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "money", Integer.parseInt(macroArgs.get("price")), Integer.MAX_VALUE)))); } catch (NumberFormatException _nfe) {}
                // if (_mcond95) {
                //     if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:block.note_block.bass", "neutral", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
                // }
                // 
                // // $execute unless entity @s[scores={money=$(price)..}] run return 0
                // boolean _mcond96 = false; try { _mcond96 = (!((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "money", Integer.parseInt(macroArgs.get("price")), Integer.MAX_VALUE)))); } catch (NumberFormatException _nfe) {}
                // if (_mcond96) {
                //     return 0;
                // }
                // 
                // // $scoreboard players remove @s money $(price)
                // try { if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "money", -(Integer.parseInt(macroArgs.get("price")))); } catch (NumberFormatException _nfe) {}
                // 
                // // tellraw @s {text:"타워를 성공적으로 설치하였습니다!",color:"green"}
                // if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{text:\"타워를 성공적으로 설치하였습니다!\",color:\"green\"}");
                // 
                // // playsound minecraft:entity.experience_orb.pickup neutral @s
                // if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:entity.experience_orb.pickup", "neutral", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
                // 
                // // $execute align xyz run function tower:spawn/model/$(model)/lv0
                // {
                //     ServerCommandSource kfcSrc97 = source.withPosition(new net.minecraft.util.math.Vec3d(Math.floor(source.getPosition().x), Math.floor(source.getPosition().y), Math.floor(source.getPosition().z)));
                //     // -> tower:spawn/model/macroArgs.get("model")/lv0
                //     switch (macroArgs.get("model")) {
                //         case "bleed-tower": tdpack.buckets.Bucket8._m_c7494f59_tower_spawn_model_bleed_tower_lv0_execute(kfcSrc97); break;
                //         case "coilgun": tdpack.buckets.Bucket9._m_fe1b25c9_tower_spawn_model_coilgun_lv0_execute(kfcSrc97); break;
                //         case "crossbow": tdpack.buckets.Bucket9._m_203d03e0_tower_spawn_model_crossbow_lv0_execute(kfcSrc97); break;
                //         case "farm": tdpack.buckets.Bucket9._m_fdcac774_tower_spawn_model_farm_lv0_execute(kfcSrc97); break;
                //         case "flame-tower": tdpack.buckets.Bucket9._m_c98abc38_tower_spawn_model_flame_tower_lv0_execute(kfcSrc97); break;
                //         case "freeze-tower": tdpack.buckets.Bucket9._m_e52824e7_tower_spawn_model_freeze_tower_lv0_execute(kfcSrc97); break;
                //         case "gatling": tdpack.buckets.Bucket9._m_7bd478d7_tower_spawn_model_gatling_lv0_execute(kfcSrc97); break;
                //         case "machine-gun": tdpack.buckets.Bucket9._m_427c27fe_tower_spawn_model_machine_gun_lv0_execute(kfcSrc97); break;
                //         case "mine": tdpack.buckets.Bucket9._m_a9cdb6b1_tower_spawn_model_mine_lv0_execute(kfcSrc97); break;
                //         case "poison-tower": tdpack.buckets.Bucket9._m_d3b303df_tower_spawn_model_poison_tower_lv0_execute(kfcSrc97); break;
                //         case "potato-gun": tdpack.buckets.Bucket9._m_3423487b_tower_spawn_model_potato_gun_lv0_execute(kfcSrc97); break;
                //         case "railgun": tdpack.buckets.Bucket9._m_ef5f750c_tower_spawn_model_railgun_lv0_execute(kfcSrc97); break;
                //         case "sniper": tdpack.buckets.Bucket9._m_c1826d1b_tower_spawn_model_sniper_lv0_execute(kfcSrc97); break;
                //         case "stun-tower": tdpack.buckets.Bucket10._m_f693e7be_tower_spawn_model_stun_tower_lv0_execute(kfcSrc97); break;
                //         case "supporter": tdpack.buckets.Bucket10._m_ac8c6cbc_tower_spawn_model_supporter_lv0_execute(kfcSrc97); break;
                //         case "weakness-tower": tdpack.buckets.Bucket10._m_f961b5f9_tower_spawn_model_weakness_tower_lv0_execute(kfcSrc97); break;
                //         default: break;
                //     }
                // }
                // 
                // // execute as @e[tag=tower.core] at @s unless score @s tower.number matches 0.. run function tower:spawn/summon/allocate with storage tower temp2.components.minecraft:custom_data.Tower_Status
                // for (Entity e : ctx.world.iterateEntities()) {
                //     if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                //     Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
                //     ServerCommandSource es = source.withEntity(e);
                //     ServerCommandSource kfcSrc98 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                //     if (!(!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "tower.number", 0, Integer.MAX_VALUE)))) continue;
                //     // -> tower:spawn/summon/allocate
                //     tdpack.buckets.Bucket10._m_874ae774_tower_spawn_summon_allocate_execute(kfcSrc98, KfcGen.storageMacroArgs(server, "tower", "temp2.components.minecraft:custom_data.Tower_Status"));
                // }
        return 0;
    }

    public static void _m_13b33e70_tower_state_main_execute(ServerCommandSource source) {
        _m_13b33e70_tower_state_main_executeReturn(source);
    }

    public static int _m_13b33e70_tower_state_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s tower.state.stun matches 1.. at @s run particle dust{color:[0.000,0.000,0.000],scale:1} ~ ~ ~ 0.4 0.4 0.4 0 15 force @a
        {
            ServerCommandSource kfcSrc99 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.state.stun", 1, Integer.MAX_VALUE)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc99.getPosition().x, kfcSrc99.getPosition().y, kfcSrc99.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.4, 0.4, 0.4, 0, (int)(15), 0.000f, 0.000f, 0.000f, 1f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // scoreboard players remove @s[scores={tower.state.stun=1..}] tower.state.stun 1
        if (executor != null && ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "tower.state.stun", 1, Integer.MAX_VALUE)))) KfcGen.addScore(sb, executor.getNameForScoreboard(), "tower.state.stun", -(1));
        return 0;
    }

    public static void _m_3fceebaf_tower_upgrade_cancel_execute(ServerCommandSource source) {
        _m_3fceebaf_tower_upgrade_cancel_executeReturn(source);
    }

    public static int _m_3fceebaf_tower_upgrade_cancel_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players operation #temp player.id = @s player.id
        if (executor != null) KfcGen.opScore(sb, "#temp", "player.id", "=", executor.getNameForScoreboard(), "player.id");

        // execute as @e[tag=tower.core] if score @s player.id = #temp player.id run function tower:upgrade/reset
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            // -> tower:upgrade/reset
            tdpack.buckets.Bucket10._m_66d3c596_tower_upgrade_reset_execute(es);
        }

        // tellraw @s [{text:"업그레이드가 취소되었습니다.",color:"red"}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"업그레이드가 취소되었습니다.\",color:\"red\"}]");
        return 0;
    }

    public static void _m_21b1a20e_tower_upgrade_change_mode_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_21b1a20e_tower_upgrade_change_mode_executeReturn(source, macroArgs);
    }

    public static int _m_21b1a20e_tower_upgrade_change_mode_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players operation #temp player.id = @s player.id
        if (executor != null) KfcGen.opScore(sb, "#temp", "player.id", "=", executor.getNameForScoreboard(), "player.id");

        // $execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id as @n[tag=tower.muzzle] run scoreboard players set @s tower.target_mode $(mode)
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            {
                ServerCommandSource kfcSrc2 = (_vE1 != null ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : _vS1);
            if (KfcGen.scoreCmp(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "player.id", "=", "#temp", "player.id")) {
                { net.minecraft.entity.Entity _vE2 = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc2.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true));
                  if (_vE2 != null) {
                    ServerCommandSource _vS2 = kfcSrc2.withEntity(_vE2);
                    try { if (_vE2 != null) KfcGen.setScore(sb, _vE2.getNameForScoreboard(), "tower.target_mode", Integer.parseInt(macroArgs.get("mode"))); } catch (NumberFormatException _nfe) {}
                } }
            }
            }
        }

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id as @n[tag=tower.hitbox] run scoreboard players operation @s player.last_upgrade_id = #temp player.id
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            {
                ServerCommandSource kfcSrc4 = (_vE1 != null ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : _vS1);
            if (KfcGen.scoreCmp(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "player.id", "=", "#temp", "player.id")) {
                { net.minecraft.entity.Entity _vE2 = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc4.getPosition(), new String[]{"tower.hitbox"}, new String[0], -1, -1, _ee -> (true));
                  if (_vE2 != null) {
                    ServerCommandSource _vS2 = kfcSrc4.withEntity(_vE2);
                    if (_vE2 != null) KfcGen.opScore(sb, _vE2.getNameForScoreboard(), "player.last_upgrade_id", "=", "#temp", "player.id");
                } }
            }
            }
        }

        // function tower:upgrade/re_upgrade
        // -> tower:upgrade/re_upgrade
        tdpack.buckets.Bucket10._m_bf3050ce_tower_upgrade_re_upgrade_execute(source);
        return 0;
    }

    public static void _m_ae82a7dd_tower_upgrade_confirm_execute(ServerCommandSource source) {
        _m_ae82a7dd_tower_upgrade_confirm_executeReturn(source);
    }

    public static int _m_ae82a7dd_tower_upgrade_confirm_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players operation #temp player.id = @s player.id
        if (executor != null) KfcGen.opScore(sb, "#temp", "player.id", "=", executor.getNameForScoreboard(), "player.id");

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id run function tower:upgrade/confirm_tower
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc5 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            // -> tower:upgrade/confirm_tower
            tdpack.buckets.Bucket10._m_7e816310_tower_upgrade_confirm_tower_execute(kfcSrc5);
        }
        return 0;
    }

    public static void _m_7e816310_tower_upgrade_confirm_tower_execute(ServerCommandSource source) {
        _m_7e816310_tower_upgrade_confirm_tower_executeReturn(source);
    }

    public static int _m_7e816310_tower_upgrade_confirm_tower_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score #temp money as @n[tag=tower.upgrade] run data get entity @s data.Tower_Status.price
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.upgrade"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "money", (int)(KfcGen.entityGetDouble(executor, "data.Tower_Status.price"))); }

        // execute as @a if score @s player.id = #temp player.id unless score @s money >= #temp money if function tower:upgrade/fail_for_money run return 0
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id") && !(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "money", ">=", "#temp", "money")) && (tdpack.buckets.Bucket10._m_4a5c3f9e_tower_upgrade_fail_for_money_executeReturn(es) != 0))) continue;
            return 0;
        }

        // data modify entity @n[tag=tower.data] data set from entity @n[tag=tower.upgrade] data.Tower_Status
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.upgrade"}, new String[0], -1, -1, _ee -> (true)), "data.Tower_Status"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "data", _v); }

        // data modify entity @n[tag=tower.muzzle] data set from entity @n[tag=tower.upgrade] data.Tower_Status
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.upgrade"}, new String[0], -1, -1, _ee -> (true)), "data.Tower_Status"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "data", _v); }

        // execute as @n[tag=tower.upgrade] unless data entity @s data.Tower_Upgrade run data remove entity @s data
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.upgrade"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            if ((!(KfcGen.entityHasPath(e, "data.Tower_Upgrade")))) {
                if (e != null) KfcGen.entityRemovePath(e, "data");
            }
        } }

        // execute as @n[tag=tower.upgrade] run data modify entity @s data set from entity @s data.Tower_Upgrade
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.upgrade"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(e, "data.Tower_Upgrade"); if (_v != null) KfcGen.nbtSetEntity(e, "data", _v); }
        } }

        // scoreboard players add @n[tag=tower.data] tower.level 1
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) KfcGen.addScore(sb, _t.getNameForScoreboard(), "tower.level", 1); }

        // execute store result storage tower model_update.level int 1 run scoreboard players get @n[tag=tower.data] tower.level
        KfcGen.storagePutNumber(server, "tower", "model_update.level", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "tower.level"), "int");

        // data modify storage tower model_update.model set from entity @n[tag=tower.data] data.model
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "data.model"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "model_update.model", _v); }

        // execute as @n[tag=tower.data] at @s run function tower:upgrade/update_model with storage tower model_update
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc6 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:upgrade/update_model
            tdpack.buckets.Bucket11._m_9ea74a1a_tower_upgrade_update_model_execute(kfcSrc6, KfcGen.storageMacroArgs(server, "tower", "model_update"));
        } }

        // execute as @a if score @s player.id = #temp player.id run scoreboard players operation @s money -= #temp money
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            if (e != null) KfcGen.opScore(sb, e.getNameForScoreboard(), "money", "-=", "#temp", "money");
        }

        // execute as @a if score @s player.id = #temp player.id run tellraw @s [{text:"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"},{text:"업그레이드가 완료되었습니다!",color:"green"},{text:"\n\n[다음 업그레이드]",color:gold,bold:true,click_event:{action:"run_command",command:"/function tower:upgrade/re_upgrade"}}]
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            if (e instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, es, "[{text:\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\"},{text:\"업그레이드가 완료되었습니다!\",color:\"green\"},{text:\"\\n\\n[다음 업그레이드]\",color:gold,bold:true,click_event:{action:\"run_command\",command:\"/function tower:upgrade/re_upgrade\"}}]");
        }

        // function tower:upgrade/sound/main with entity @n[tag=tower.data] data
        // -> tower:upgrade/sound/main
        tdpack.buckets.Bucket11._m_0a7ca4dd_tower_upgrade_sound_main_execute(source, KfcGen.entityMacroArgs(KfcGen.nearestEntityAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1), "data"));

        // execute on passengers at @s[tag=tower.muzzle] store result score @s tower.attack run data get entity @n[tag=tower.data] data.attack 1.0
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc7 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc7 != null) {
                int _stv = (int)(KfcGen.entityGetDouble(KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc7.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "data.attack"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.attack", (int)(_stv));
                }
            }
        }

        // execute on passengers at @s[tag=tower.muzzle] store result score @s tower.attack_speed run data get entity @n[tag=tower.data] data.attack_speed 1.0
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc8 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc8 != null) {
                int _stv = (int)(KfcGen.entityGetDouble(KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc8.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "data.attack_speed"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.attack_speed", (int)(_stv));
                }
            }
        }

        // execute on passengers at @s[tag=tower.muzzle] store result score @s tower.target_mode run data get entity @n[tag=tower.data] data.target_mode 1.0
        for (ServerCommandSource _vS1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _vE1 = _vS1.getEntity();
            {
                ServerCommandSource kfcSrc9 = (((_vE1 != null && _vE1.getCommandTags().contains("tower.muzzle"))) ? _vS1.withPosition(_vE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_vE1.getPitch(), _vE1.getYaw())) : null);
                if (kfcSrc9 != null) {
                int _stv = (int)(KfcGen.entityGetDouble(KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc9.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "data.target_mode"));
                if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.target_mode", (int)(_stv));
                }
            }
        }

        // function tower:upgrade/reset
        // -> tower:upgrade/reset
        tdpack.buckets.Bucket10._m_66d3c596_tower_upgrade_reset_execute(source);
        return 0;
    }

    public static void _m_4a5c3f9e_tower_upgrade_fail_for_money_execute(ServerCommandSource source) {
        _m_4a5c3f9e_tower_upgrade_fail_for_money_executeReturn(source);
    }

    public static int _m_4a5c3f9e_tower_upgrade_fail_for_money_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // title @s title [{text:"업그레이드 실패",color:"red"}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.titleText(_tp, source, "[{text:\"업그레이드 실패\",color:\"red\"}]", false);

        // title @s subtitle [{text:"돈이 부족합니다!",color:"gold"}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.titleText(_tp, source, "[{text:\"돈이 부족합니다!\",color:\"gold\"}]", true);

        // playsound minecraft:block.note_block.bass neutral @s
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:block.note_block.bass", "neutral", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);

        // return 1
        return 1;
    }

    public static void _m_75c541cb_tower_upgrade_interact_execute(ServerCommandSource source) {
        _m_75c541cb_tower_upgrade_interact_executeReturn(source);
    }

    public static int _m_75c541cb_tower_upgrade_interact_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute on target run scoreboard players operation #temp player.id = @s player.id
        { ServerCommandSource _on1 = KfcGen.onTarget(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (_onEnt1 != null) KfcGen.opScore(sb, "#temp", "player.id", "=", _onEnt1.getNameForScoreboard(), "player.id");
          } }

        // execute on attacker run scoreboard players operation #temp player.id = @s player.id
        { ServerCommandSource _on1 = KfcGen.onAttacker(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (_onEnt1 != null) KfcGen.opScore(sb, "#temp", "player.id", "=", _onEnt1.getNameForScoreboard(), "player.id");
          } }

        // execute on vehicle on passengers if entity @s[tag=tower.data] run data modify storage tower temp set from entity @s data
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if ((_onEnt2 != null && _onEnt2.getCommandTags().contains("tower.data"))) {
                    { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(_onEnt2, "data"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "temp", _v); }
                }
            }
          } }

        // execute on vehicle on passengers if entity @s[tag=tower.upgrade] run data modify storage tower temp2 set from entity @s data.Tower_Status
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if ((_onEnt2 != null && _onEnt2.getCommandTags().contains("tower.upgrade"))) {
                    { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(_onEnt2, "data.Tower_Status"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "temp2", _v); }
                }
            }
          } }

        // function tower:upgrade/tag
        // -> tower:upgrade/tag
        tdpack.buckets.Bucket10._m_ea300251_tower_upgrade_tag_execute(source);

        // execute on vehicle on passengers run data modify entity @s glow_color_override set value 393215
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if (_onEnt2 != null) KfcGen.entityPutSnbt(_onEnt2, "glow_color_override", "393215");
            }
          } }

        // execute on vehicle on passengers run data modify entity @s Glowing set value 1b
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if (_onEnt2 != null) KfcGen.entityPutSnbt(_onEnt2, "Glowing", "1b");
            }
          } }

        // execute on target run function tower:upgrade/ui
        { ServerCommandSource _on1 = KfcGen.onTarget(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            // -> tower:upgrade/ui
            tdpack.buckets.Bucket10._m_b92a8a44_tower_upgrade_ui_execute(_on1);
          } }

        // execute on attacker run function tower:upgrade/ui
        { ServerCommandSource _on1 = KfcGen.onAttacker(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            // -> tower:upgrade/ui
            tdpack.buckets.Bucket10._m_b92a8a44_tower_upgrade_ui_execute(_on1);
          } }

        // execute on target at @s run playsound minecraft:ui.button.click master @s
        { ServerCommandSource _on1 = KfcGen.onTarget(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            {
                ServerCommandSource kfcSrc10 = (_onEnt1 != null ? _on1.withPosition(_onEnt1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_onEnt1.getPitch(), _onEnt1.getYaw())) : _on1);
                if (_onEnt1 instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:ui.button.click", "master", new net.minecraft.util.math.Vec3d(kfcSrc10.getPosition().x, kfcSrc10.getPosition().y, kfcSrc10.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc10.getPosition().x, kfcSrc10.getPosition().y, kfcSrc10.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc10.getPosition().x, kfcSrc10.getPosition().y, kfcSrc10.getPosition().z).z, 1.0f, 1.0f);
            }
          } }

        // execute on attacker at @s run playsound minecraft:ui.button.click master @s
        { ServerCommandSource _on1 = KfcGen.onAttacker(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            {
                ServerCommandSource kfcSrc11 = (_onEnt1 != null ? _on1.withPosition(_onEnt1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_onEnt1.getPitch(), _onEnt1.getYaw())) : _on1);
                if (_onEnt1 instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:ui.button.click", "master", new net.minecraft.util.math.Vec3d(kfcSrc11.getPosition().x, kfcSrc11.getPosition().y, kfcSrc11.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc11.getPosition().x, kfcSrc11.getPosition().y, kfcSrc11.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc11.getPosition().x, kfcSrc11.getPosition().y, kfcSrc11.getPosition().z).z, 1.0f, 1.0f);
            }
          } }

        // data remove entity @s attack
        if (executor != null) KfcGen.entityRemovePath(executor, "attack");

        // data remove entity @s interaction
        if (executor != null) KfcGen.entityRemovePath(executor, "interaction");
        return 0;
    }

    public static void _m_a04def05_tower_upgrade_main_execute(ServerCommandSource source) {
        _m_a04def05_tower_upgrade_main_executeReturn(source);
    }

    public static int _m_a04def05_tower_upgrade_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute if data entity @s attack run function tower:upgrade/interact
        if (KfcGen.entityHasPath(executor, "attack")) {
            // -> tower:upgrade/interact
            tdpack.buckets.Bucket10._m_75c541cb_tower_upgrade_interact_execute(source);
        }

        // execute if data entity @s interaction run function tower:upgrade/interact
        if (KfcGen.entityHasPath(executor, "interaction")) {
            // -> tower:upgrade/interact
            tdpack.buckets.Bucket10._m_75c541cb_tower_upgrade_interact_execute(source);
        }
        return 0;
    }

    public static void _m_5170227d_tower_upgrade_re_interact_execute(ServerCommandSource source) {
        _m_5170227d_tower_upgrade_re_interact_executeReturn(source);
    }

    public static int _m_5170227d_tower_upgrade_re_interact_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute as @n[tag=tower.data] run data modify storage tower temp set from entity @s data
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(e, "data"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "temp", _v); }
        } }

        // execute as @n[tag=tower.upgrade] run data modify storage tower temp2 set from entity @s data.Tower_Status
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.upgrade"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(e, "data.Tower_Status"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "temp2", _v); }
        } }

        // function tower:upgrade/tag
        // -> tower:upgrade/tag
        tdpack.buckets.Bucket10._m_ea300251_tower_upgrade_tag_execute(source);

        // execute on vehicle on passengers run data modify entity @s glow_color_override set value 393215
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if (_onEnt2 != null) KfcGen.entityPutSnbt(_onEnt2, "glow_color_override", "393215");
            }
          } }

        // execute on vehicle on passengers run data modify entity @s Glowing set value 1b
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if (_onEnt2 != null) KfcGen.entityPutSnbt(_onEnt2, "Glowing", "1b");
            }
          } }

        // execute as @a if score @s player.id = #temp player.id run function tower:upgrade/ui
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            // -> tower:upgrade/ui
            tdpack.buckets.Bucket10._m_b92a8a44_tower_upgrade_ui_execute(es);
        }

        // execute as @a if score @s player.id = #temp player.id run playsound minecraft:ui.button.click master @s
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:ui.button.click", "master", new net.minecraft.util.math.Vec3d(es.getPosition().x, es.getPosition().y, es.getPosition().z).x, new net.minecraft.util.math.Vec3d(es.getPosition().x, es.getPosition().y, es.getPosition().z).y, new net.minecraft.util.math.Vec3d(es.getPosition().x, es.getPosition().y, es.getPosition().z).z, 1.0f, 1.0f);
        }

        // execute at @s run function tower:upgrade/summon_range with entity @s
        {
            ServerCommandSource kfcSrc12 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> tower:upgrade/summon_range
            KfcGen.instantExecuteFunction(kfcSrc12, net.minecraft.util.Identifier.of("tower", "upgrade/summon_range"), KfcGen.entityMacroArgs(executor, ""));
        }
        return 0;
    }

    public static void _m_bf3050ce_tower_upgrade_re_upgrade_execute(ServerCommandSource source) {
        _m_bf3050ce_tower_upgrade_re_upgrade_executeReturn(source);
    }

    public static int _m_bf3050ce_tower_upgrade_re_upgrade_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players operation #temp player.id = @s player.id
        if (executor != null) KfcGen.opScore(sb, "#temp", "player.id", "=", executor.getNameForScoreboard(), "player.id");

        // execute as @e[tag=tower.hitbox] at @s if score @s player.last_upgrade_id = #temp player.id run function tower:upgrade/re_interact
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.hitbox"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc13 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.last_upgrade_id", "=", "#temp", "player.id"))) continue;
            // -> tower:upgrade/re_interact
            tdpack.buckets.Bucket10._m_5170227d_tower_upgrade_re_interact_execute(kfcSrc13);
        }
        return 0;
    }

    public static void _m_66d3c596_tower_upgrade_reset_execute(ServerCommandSource source) {
        _m_66d3c596_tower_upgrade_reset_executeReturn(source);
    }

    public static int _m_66d3c596_tower_upgrade_reset_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute on passengers if data entity @s Glowing run data remove entity @s glow_color_override
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (KfcGen.entityHasPath(_onEnt1, "Glowing")) {
                if (_onEnt1 != null) KfcGen.entityRemovePath(_onEnt1, "glow_color_override");
            }
        }

        // execute on passengers if data entity @s Glowing run data remove entity @s Glowing
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (KfcGen.entityHasPath(_onEnt1, "Glowing")) {
                if (_onEnt1 != null) KfcGen.entityRemovePath(_onEnt1, "Glowing");
            }
        }

        // scoreboard players reset @s player.id
        if (executor != null) KfcGen.resetScore(sb, executor.getNameForScoreboard(), "player.id");
        return 0;
    }

    public static void _m_2c0ad10b_tower_upgrade_sell_execute(ServerCommandSource source) {
        _m_2c0ad10b_tower_upgrade_sell_executeReturn(source);
    }

    public static int _m_2c0ad10b_tower_upgrade_sell_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players operation #temp player.id = @s player.id
        if (executor != null) KfcGen.opScore(sb, "#temp", "player.id", "=", executor.getNameForScoreboard(), "player.id");

        // scoreboard players set #temp tower 0
        KfcGen.setScore(sb, "#temp", "tower", 0);

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id run function tower:upgrade/sell_tower
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc14 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            // -> tower:upgrade/sell_tower
            tdpack.buckets.Bucket10._m_fd285e14_tower_upgrade_sell_tower_execute(kfcSrc14);
        }

        // execute if score #temp tower matches 0 run return run tellraw @s {text:"판매할 타워가 없습니다.",color:"red"}
        if (KfcGen.scoreMatches(sb, "#temp", "tower", 0, 0)) {
            if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{text:\"판매할 타워가 없습니다.\",color:\"red\"}");
            return 1;
        }

        // scoreboard players operation @s money += #sell_money tower
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "money", "+=", "#sell_money", "tower");

        // tellraw @s {text:"타워를 판매했습니다.",color:"green"}
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{text:\"타워를 판매했습니다.\",color:\"green\"}");

        // tellraw @s {text:"판매 금액: ",color:"yellow",extra:[{score:{name:"#sell_money",objective:"tower"}},]}
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{text:\"판매 금액: \",color:\"yellow\",extra:[{score:{name:\"#sell_money\",objective:\"tower\"}},]}");
        return 0;
    }

    public static void _m_fd285e14_tower_upgrade_sell_tower_execute(ServerCommandSource source) {
        _m_fd285e14_tower_upgrade_sell_tower_executeReturn(source);
    }

    public static int _m_fd285e14_tower_upgrade_sell_tower_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute store result score #sell_money tower run data get entity @n[tag=tower.data] data.sell_price
        KfcGen.setScore(sb, "#sell_money", "tower", (int)(KfcGen.entityGetDouble(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "data.sell_price")));

        // kill @e[distance=..0.001,tag=tower,tag=!tower.core]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower"}, new String[]{"tower.core"}, -1, 0.001)) {
            KfcGen.killEntity(_k);
        }

        // kill @s
        if (executor != null) KfcGen.killEntity(executor);

        // scoreboard players set #temp tower 1
        KfcGen.setScore(sb, "#temp", "tower", 1);
        return 0;
    }

    public static void _m_d85b1472_tower_upgrade_show_range_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_d85b1472_tower_upgrade_show_range_executeReturn(source, macroArgs);
    }

    public static int _m_d85b1472_tower_upgrade_show_range_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // $execute positioned ^ ^ ^$(range) run particle dust{color:[1.000,0.000,0.000],scale:2} ~ -60 ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc15 = null; try { kfcSrc15 = source.withPosition(KfcGen.localOffset(source.getPosition(), source.getRotation(), 0.0, 0.0, Double.parseDouble(macroArgs.get("range")))); } catch (NumberFormatException _nfe) {}
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc15.getPosition().x, -60.0, kfcSrc15.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 1.000f, 0.000f, 0.000f, 2f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // $execute rotated ~90 ~ positioned ^ ^ ^$(range) run particle dust{color:[1.000,0.000,0.000],scale:2} ~ -60 ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc16 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 90.0f)));
            ServerCommandSource kfcSrc17 = null; try { kfcSrc17 = kfcSrc16.withPosition(KfcGen.localOffset(kfcSrc16.getPosition(), kfcSrc16.getRotation(), 0.0, 0.0, Double.parseDouble(macroArgs.get("range")))); } catch (NumberFormatException _nfe) {}
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc17.getPosition().x, -60.0, kfcSrc17.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 1.000f, 0.000f, 0.000f, 2f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // $execute rotated ~180 ~ positioned ^ ^ ^$(range) run particle dust{color:[1.000,0.000,0.000],scale:2} ~ -60 ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc18 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 180.0f)));
            ServerCommandSource kfcSrc19 = null; try { kfcSrc19 = kfcSrc18.withPosition(KfcGen.localOffset(kfcSrc18.getPosition(), kfcSrc18.getRotation(), 0.0, 0.0, Double.parseDouble(macroArgs.get("range")))); } catch (NumberFormatException _nfe) {}
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc19.getPosition().x, -60.0, kfcSrc19.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 1.000f, 0.000f, 0.000f, 2f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // $execute rotated ~270 ~ positioned ^ ^ ^$(range) run particle dust{color:[1.000,0.000,0.000],scale:2} ~ -60 ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc20 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 270.0f)));
            ServerCommandSource kfcSrc21 = null; try { kfcSrc21 = kfcSrc20.withPosition(KfcGen.localOffset(kfcSrc20.getPosition(), kfcSrc20.getRotation(), 0.0, 0.0, Double.parseDouble(macroArgs.get("range")))); } catch (NumberFormatException _nfe) {}
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc21.getPosition().x, -60.0, kfcSrc21.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 1.000f, 0.000f, 0.000f, 2f, true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute rotated ~ ~ run rotate @s ~5 0
        {
            ServerCommandSource kfcSrc22 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, source.getRotation().y));
            if (executor != null) KfcGen.rotateTo(executor, (kfcSrc22.getRotation().y + 5.0f), 0.0f);
        }
        return 0;
    }

    public static void _m_ea300251_tower_upgrade_tag_execute(ServerCommandSource source) {
        _m_ea300251_tower_upgrade_tag_executeReturn(source);
    }

    public static int _m_ea300251_tower_upgrade_tag_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute as @e[tag=tower.core] if score @s player.id = #temp player.id run function tower:upgrade/cancel
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            // -> tower:upgrade/cancel
            tdpack.buckets.Bucket10._m_3fceebaf_tower_upgrade_cancel_execute(es);
        }

        // execute as @n[tag=tower.core] run scoreboard players operation @s player.id = #temp player.id
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.opScore(sb, e.getNameForScoreboard(), "player.id", "=", "#temp", "player.id");
        } }

        // execute as @e[tag=tower.hitbox] if score @s player.last_upgrade_id = #temp player.id run scoreboard players reset @s player.last_upgrade_id
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.hitbox"))) continue;
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.last_upgrade_id", "=", "#temp", "player.id"))) continue;
            if (e != null) KfcGen.resetScore(sb, e.getNameForScoreboard(), "player.last_upgrade_id");
        }

        // scoreboard players operation @s player.last_upgrade_id = #temp player.id
        if (executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "player.last_upgrade_id", "=", "#temp", "player.id");
        return 0;
    }

    public static void _m_b92a8a44_tower_upgrade_ui_execute(ServerCommandSource source) {
        _m_b92a8a44_tower_upgrade_ui_executeReturn(source);
    }

    public static int _m_b92a8a44_tower_upgrade_ui_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score #sell_cost tower run data get storage tower temp.price
        KfcGen.setScore(sb, "#sell_cost", "tower", (int)(KfcGen.storageGetDouble(server, "tower", "temp.price")));

        // execute store result score #upgrade_cost tower run data get storage tower temp2.price
        KfcGen.setScore(sb, "#upgrade_cost", "tower", (int)(KfcGen.storageGetDouble(server, "tower", "temp2.price")));

        // execute store result score #level tower run data get storage tower temp.level
        KfcGen.setScore(sb, "#level", "tower", (int)(KfcGen.storageGetDouble(server, "tower", "temp.level")));

        // scoreboard players remove #level tower 1
        KfcGen.addScore(sb, "#level", "tower", -(1));

        // scoreboard players operation #upgrade_cost tower *= #level tower
        KfcGen.opScore(sb, "#upgrade_cost", "tower", "*=", "#level", "tower");

        // scoreboard players operation #sell_cost tower += #upgrade_cost tower
        KfcGen.opScore(sb, "#sell_cost", "tower", "+=", "#upgrade_cost", "tower");

        // scoreboard players operation #sell_cost tower /= #2 tower
        KfcGen.opScore(sb, "#sell_cost", "tower", "/=", "#2", "tower");

        // tellraw @s {text:"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"}
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{text:\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\"}");

        // execute unless data storage tower temp2 if function tower:upgrade/ui_maxed run return 0
        if (!(KfcGen.storageHasPath(source.getServer(), "tower", "temp2"))) {
            if ((tdpack.buckets.Bucket11._m_4d7a4343_tower_upgrade_ui_maxed_executeReturn(source) != 0)) {
                return 0;
            }
        }

        // tellraw @s [{nbt:temp.name,storage:tower,interpret:true},{text:" -",bold:false,color:white},{text:" Level ",bold:false,color:aqua},{nbt:temp.level,storage:tower,color:aqua,bold:false},{text:"\n",bold:false,color:aqua}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{nbt:temp.name,storage:tower,interpret:true},{text:\" -\",bold:false,color:white},{text:\" Level \",bold:false,color:aqua},{nbt:temp.level,storage:tower,color:aqua,bold:false},{text:\"\\n\",bold:false,color:aqua}]");

        // tellraw @s [{text:"[공격력] ",bold:false,color:red},{"nbt":"temp.attack","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.attack","storage":"tower",bold:false,color:green}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[공격력] \",bold:false,color:red},{\"nbt\":\"temp.attack\",\"storage\":\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{\"nbt\":\"temp2.attack\",\"storage\":\"tower\",bold:false,color:green}]");

        // tellraw @s [{text:"[공격 속도] ",bold:false,color:aqua},{"nbt":"temp.info.attack_speed","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.info.attack_speed","storage":"tower",bold:false,color:green}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[공격 속도] \",bold:false,color:aqua},{\"nbt\":\"temp.info.attack_speed\",\"storage\":\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{\"nbt\":\"temp2.info.attack_speed\",\"storage\":\"tower\",bold:false,color:green}]");

        // tellraw @s [{text:"[사거리] ",bold:false,color:blue},{"nbt":"temp.range",storage:"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.range","storage":"tower",bold:false,color:green}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[사거리] \",bold:false,color:blue},{\"nbt\":\"temp.range\",storage:\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{\"nbt\":\"temp2.range\",\"storage\":\"tower\",bold:false,color:green}]");

        // execute if data storage tower temp.Bullet.attribute run function tower:upgrade/ui_attribute
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute")) {
            // -> tower:upgrade/ui_attribute
            tdpack.buckets.Bucket10._m_e24dbe6f_tower_upgrade_ui_attribute_execute(source);
        }

        // function tower:upgrade/ui_target_mode
        // -> tower:upgrade/ui_target_mode
        tdpack.buckets.Bucket11._m_a87a1886_tower_upgrade_ui_target_mode_execute(source);

        // tellraw @s [{text:"\n업그레이드 비용: ",bold:false,color:gold},{"nbt":"temp2.price","storage":"tower",bold:false,color:yellow},{text:"\n판매 가격: ",bold:false,color:gold},{"nbt":"temp.sell_price","storage":"tower",bold:false,color:yellow},{text:"\n",bold:false,color:gold}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"\\n업그레이드 비용: \",bold:false,color:gold},{\"nbt\":\"temp2.price\",\"storage\":\"tower\",bold:false,color:yellow},{text:\"\\n판매 가격: \",bold:false,color:gold},{\"nbt\":\"temp.sell_price\",\"storage\":\"tower\",bold:false,color:yellow},{text:\"\\n\",bold:false,color:gold}]");

        // tellraw @s [{text:"[판매] \n",bold:true,color:yellow,click_event:{action:"run_command",command:"/function tower:upgrade/sell"}},{text:"[업그레이드] ",bold:true,color:green,click_event:{action:"run_command",command:"/function tower:upgrade/confirm"}},{text:"[취소] ",bold:true,color:red,click_event:{action:"run_command",command:"/function tower:upgrade/cancel"}}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[판매] \\n\",bold:true,color:yellow,click_event:{action:\"run_command\",command:\"/function tower:upgrade/sell\"}},{text:\"[업그레이드] \",bold:true,color:green,click_event:{action:\"run_command\",command:\"/function tower:upgrade/confirm\"}},{text:\"[취소] \",bold:true,color:red,click_event:{action:\"run_command\",command:\"/function tower:upgrade/cancel\"}}]");
        return 0;
    }

    public static void _m_e24dbe6f_tower_upgrade_ui_attribute_execute(ServerCommandSource source) {
        _m_e24dbe6f_tower_upgrade_ui_attribute_executeReturn(source);
    }

    public static int _m_e24dbe6f_tower_upgrade_ui_attribute_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage temp attribute-text set value [{text:"[총알 속성]",color:dark_purple}]
        KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"[총알 속성]\",color:dark_purple}]", "set");

        // execute if data storage tower temp.Bullet.attribute.freeze run data modify storage temp attribute-text append value [{text:"\n    [빙결] ",bold:false,color:aqua},{"nbt":"temp.Bullet.attribute.freeze","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.Bullet.attribute.freeze","storage":"tower",bold:false,color:green}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.freeze")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [빙결] \",bold:false,color:aqua},{\"nbt\":\"temp.Bullet.attribute.freeze\",\"storage\":\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{\"nbt\":\"temp2.Bullet.attribute.freeze\",\"storage\":\"tower\",bold:false,color:green}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.bleed run data modify storage temp attribute-text append value [{text:"\n    [출혈] ",bold:false,color:red},{"nbt":"temp.Bullet.attribute.bleed","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.Bullet.attribute.bleed","storage":"tower",bold:false,color:green}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.bleed")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [출혈] \",bold:false,color:red},{\"nbt\":\"temp.Bullet.attribute.bleed\",\"storage\":\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{\"nbt\":\"temp2.Bullet.attribute.bleed\",\"storage\":\"tower\",bold:false,color:green}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.poison run data modify storage temp attribute-text append value [{text:"\n    [중독] ",bold:false,color:green},{"nbt":"temp.Bullet.attribute.poison","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{text:"0.",bold:false,color:light_purple},{"nbt":"temp2.Bullet.attribute.poison","storage":"tower",bold:false,color:green},{text:"%",bold:false,color:green}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.poison")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [중독] \",bold:false,color:green},{\"nbt\":\"temp.Bullet.attribute.poison\",\"storage\":\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{text:\"0.\",bold:false,color:light_purple},{\"nbt\":\"temp2.Bullet.attribute.poison\",\"storage\":\"tower\",bold:false,color:green},{text:\"%\",bold:false,color:green}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.flame run data modify storage temp attribute-text append value [{text:"\n    [화염] ",bold:false,color:gold},{"nbt":"temp.Bullet.attribute.flame","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.Bullet.attribute.flame","storage":"tower",bold:false,color:green}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.flame")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [화염] \",bold:false,color:gold},{\"nbt\":\"temp.Bullet.attribute.flame\",\"storage\":\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{\"nbt\":\"temp2.Bullet.attribute.flame\",\"storage\":\"tower\",bold:false,color:green}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.stun run data modify storage temp attribute-text append value [{text:"\n    [기절] ",bold:false,color:yellow},{"nbt":"temp.Bullet.attribute.stun","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.Bullet.attribute.stun","storage":"tower",bold:false,color:green}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.stun")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [기절] \",bold:false,color:yellow},{\"nbt\":\"temp.Bullet.attribute.stun\",\"storage\":\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{\"nbt\":\"temp2.Bullet.attribute.stun\",\"storage\":\"tower\",bold:false,color:green}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.weakness run data modify storage temp attribute-text append value [{text:"\n    [취약] ",bold:false,color:dark_purple},{"nbt":"temp.Bullet.attribute.weakness","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.Bullet.attribute.weakness","storage":"tower",bold:false,color:green}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.weakness")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [취약] \",bold:false,color:dark_purple},{\"nbt\":\"temp.Bullet.attribute.weakness\",\"storage\":\"tower\",bold:false,color:yellow},{text:\" -> \",bold:false,color:white},{\"nbt\":\"temp2.Bullet.attribute.weakness\",\"storage\":\"tower\",bold:false,color:green}]", "append");
        }

        // tellraw @s {nbt:"attribute-text",storage:"temp",interpret:true}
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{nbt:\"attribute-text\",storage:\"temp\",interpret:true}");
        return 0;
    }
}
