package tdpack.buckets;

import tdpack.generated.KfcGen;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/** Auto-bucketed: 6 datapack functions. */
public final class Bucket11 {
    private Bucket11() { throw new UnsupportedOperationException(); }

    public static void _m_ed706f0c_tower_upgrade_ui_attribute_maxed_execute(ServerCommandSource source) {
        _m_ed706f0c_tower_upgrade_ui_attribute_maxed_executeReturn(source);
    }

    public static int _m_ed706f0c_tower_upgrade_ui_attribute_maxed_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage temp attribute-text set value [{text:"[총알 속성]",color:dark_purple}]
        KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"[총알 속성]\",color:dark_purple}]", "set");

        // execute if data storage tower temp.Bullet.attribute.freeze run data modify storage temp attribute-text append value [{text:"\n    [빙결] ",bold:false,color:aqua},{"nbt":"temp.Bullet.attribute.freeze","storage":"tower",bold:false,color:light_purple}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.freeze")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [빙결] \",bold:false,color:aqua},{\"nbt\":\"temp.Bullet.attribute.freeze\",\"storage\":\"tower\",bold:false,color:light_purple}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.bleed run data modify storage temp attribute-text append value [{text:"\n    [출혈] ",bold:false,color:red},{"nbt":"temp.Bullet.attribute.bleed","storage":"tower",bold:false,color:light_purple}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.bleed")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [출혈] \",bold:false,color:red},{\"nbt\":\"temp.Bullet.attribute.bleed\",\"storage\":\"tower\",bold:false,color:light_purple}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.poison run data modify storage temp attribute-text append value [{text:"\n    [중독] ",bold:false,color:green},{text:"0.",bold:false,color:light_purple},{"nbt":"temp.Bullet.attribute.poison","storage":"tower",bold:false,color:light_purple},{text:"%",bold:false,color:light_purple}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.poison")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [중독] \",bold:false,color:green},{text:\"0.\",bold:false,color:light_purple},{\"nbt\":\"temp.Bullet.attribute.poison\",\"storage\":\"tower\",bold:false,color:light_purple},{text:\"%\",bold:false,color:light_purple}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.flame run data modify storage temp attribute-text append value [{text:"\n    [화염] ",bold:false,color:gold},{"nbt":"temp.Bullet.attribute.flame","storage":"tower",bold:false,color:light_purple}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.flame")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [화염] \",bold:false,color:gold},{\"nbt\":\"temp.Bullet.attribute.flame\",\"storage\":\"tower\",bold:false,color:light_purple}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.stun run data modify storage temp attribute-text append value [{text:"\n    [기절] ",bold:false,color:yellow},{"nbt":"temp.Bullet.attribute.stun","storage":"tower",bold:false,color:light_purple}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.stun")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [기절] \",bold:false,color:yellow},{\"nbt\":\"temp.Bullet.attribute.stun\",\"storage\":\"tower\",bold:false,color:light_purple}]", "append");
        }

        // execute if data storage tower temp.Bullet.attribute.weakness run data modify storage temp attribute-text append value [{text:"\n    [취약] ",bold:false,color:dark_purple},{"nbt":"temp.Bullet.attribute.weakness","storage":"tower",bold:false,color:light_purple}]
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.weakness")) {
            KfcGen.storagePutSnbt(server, "temp", "attribute-text", "[{text:\"\\n    [취약] \",bold:false,color:dark_purple},{\"nbt\":\"temp.Bullet.attribute.weakness\",\"storage\":\"tower\",bold:false,color:light_purple}]", "append");
        }

        // tellraw @s {nbt:"attribute-text",storage:"temp",interpret:true}
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "{nbt:\"attribute-text\",storage:\"temp\",interpret:true}");
        return 0;
    }

    public static void _m_4d7a4343_tower_upgrade_ui_maxed_execute(ServerCommandSource source) {
        _m_4d7a4343_tower_upgrade_ui_maxed_executeReturn(source);
    }

    public static int _m_4d7a4343_tower_upgrade_ui_maxed_executeReturn(ServerCommandSource source) {
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

        // tellraw @s [{nbt:temp.name,storage:tower,interpret:true},{text:" -",bold:false,color:white},{text:" Level ",bold:false,color:aqua},{nbt:temp.level,storage:tower,color:aqua,bold:false},{text:"\n",bold:false,color:aqua}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{nbt:temp.name,storage:tower,interpret:true},{text:\" -\",bold:false,color:white},{text:\" Level \",bold:false,color:aqua},{nbt:temp.level,storage:tower,color:aqua,bold:false},{text:\"\\n\",bold:false,color:aqua}]");

        // tellraw @s [{text:"[공격력] ",bold:false,color:red},{"nbt":"temp.attack","storage":"tower",bold:false,color:light_purple}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[공격력] \",bold:false,color:red},{\"nbt\":\"temp.attack\",\"storage\":\"tower\",bold:false,color:light_purple}]");

        // tellraw @s [{text:"[공격 속도] ",bold:false,color:aqua},{"nbt":"temp.info.attack_speed","storage":"tower",bold:false,color:light_purple}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[공격 속도] \",bold:false,color:aqua},{\"nbt\":\"temp.info.attack_speed\",\"storage\":\"tower\",bold:false,color:light_purple}]");

        // tellraw @s [{text:"[사거리] ",bold:false,color:blue},{"nbt":"temp.range","storage":"tower",bold:false,color:light_purple}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[사거리] \",bold:false,color:blue},{\"nbt\":\"temp.range\",\"storage\":\"tower\",bold:false,color:light_purple}]");

        // execute if data storage tower temp.Bullet.attribute run function tower:upgrade/ui_attribute_maxed
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute")) {
            // -> tower:upgrade/ui_attribute_maxed
            tdpack.buckets.Bucket11._m_ed706f0c_tower_upgrade_ui_attribute_maxed_execute(source);
        }

        // function tower:upgrade/ui_target_mode
        // -> tower:upgrade/ui_target_mode
        tdpack.buckets.Bucket11._m_a87a1886_tower_upgrade_ui_target_mode_execute(source);

        // tellraw @s [{text:"\n판매 가격: ",bold:false,color:gold},{"nbt":"temp.sell_price","storage":"tower",bold:false,color:yellow},{text:"\n",bold:false,color:gold}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"\\n판매 가격: \",bold:false,color:gold},{\"nbt\":\"temp.sell_price\",\"storage\":\"tower\",bold:false,color:yellow},{text:\"\\n\",bold:false,color:gold}]");

        // tellraw @s [{text:"[판매] \n",bold:true,color:yellow,click_event:{action:"run_command",command:"/function tower:upgrade/sell"}},{text:"[취소] ",bold:true,color:red,click_event:{action:"run_command",command:"/function tower:upgrade/cancel"}}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[판매] \\n\",bold:true,color:yellow,click_event:{action:\"run_command\",command:\"/function tower:upgrade/sell\"}},{text:\"[취소] \",bold:true,color:red,click_event:{action:\"run_command\",command:\"/function tower:upgrade/cancel\"}}]");

        // return 1
        return 1;
    }

    public static void _m_a87a1886_tower_upgrade_ui_target_mode_execute(ServerCommandSource source) {
        _m_a87a1886_tower_upgrade_ui_target_mode_executeReturn(source);
    }

    public static int _m_a87a1886_tower_upgrade_ui_target_mode_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players operation #temp player.id = @s player.id
        if (executor != null) KfcGen.opScore(sb, "#temp", "player.id", "=", executor.getNameForScoreboard(), "player.id");

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id unless score @n[tag=tower.muzzle] tower.target_mode matches 2..6 run data modify storage tower temp.target_mode set value [{text:"[전방] ", color:green,bold:true,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:1}"}},{text:"[후방] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:2}"}},{text:"[가장 가까운] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:3}"}},{text:"[가장 먼] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:4}"}},{text:"[체력 최저] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:5}"}},{text:"[체력 최고] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:6}"}}]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc23 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id") && KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, es.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "tower.target_mode", 2, 6, true))) continue;
            KfcGen.storagePutSnbt(server, "tower", "temp.target_mode", "[{text:\"[전방] \", color:green,bold:true,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:1}\"}},{text:\"[후방] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:2}\"}},{text:\"[가장 가까운] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:3}\"}},{text:\"[가장 먼] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:4}\"}},{text:\"[체력 최저] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:5}\"}},{text:\"[체력 최고] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:6}\"}}]", "set");
        }

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id if score @n[tag=tower.muzzle] tower.target_mode matches 2 run data modify storage tower temp.target_mode set value [{text:"[전방] ", color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:1}"}},{text:"[후방] ",color:green,bold:true,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:2}"}},{text:"[가장 가까운] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:3}"}},{text:"[가장 먼] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:4}"}},{text:"[체력 최저] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:5}"}},{text:"[체력 최고] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:6}"}}]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc24 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id") && KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, es.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "tower.target_mode", 2, 2, false))) continue;
            KfcGen.storagePutSnbt(server, "tower", "temp.target_mode", "[{text:\"[전방] \", color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:1}\"}},{text:\"[후방] \",color:green,bold:true,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:2}\"}},{text:\"[가장 가까운] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:3}\"}},{text:\"[가장 먼] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:4}\"}},{text:\"[체력 최저] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:5}\"}},{text:\"[체력 최고] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:6}\"}}]", "set");
        }

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id if score @n[tag=tower.muzzle] tower.target_mode matches 3 run data modify storage tower temp.target_mode set value [{text:"[전방] ", color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:1}"}},{text:"[후방] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:2}"}},{text:"[가장 가까운] ",color:green,bold:true,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:3}"}},{text:"[가장 먼] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:4}"}},{text:"[체력 최저] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:5}"}},{text:"[체력 최고] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:6}"}}]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc25 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id") && KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, es.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "tower.target_mode", 3, 3, false))) continue;
            KfcGen.storagePutSnbt(server, "tower", "temp.target_mode", "[{text:\"[전방] \", color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:1}\"}},{text:\"[후방] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:2}\"}},{text:\"[가장 가까운] \",color:green,bold:true,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:3}\"}},{text:\"[가장 먼] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:4}\"}},{text:\"[체력 최저] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:5}\"}},{text:\"[체력 최고] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:6}\"}}]", "set");
        }

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id if score @n[tag=tower.muzzle] tower.target_mode matches 4 run data modify storage tower temp.target_mode set value [{text:"[전방] ", color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:1}"}},{text:"[후방] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:2}"}},{text:"[가장 가까운] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:3}"}},{text:"[가장 먼] ",color:green,bold:true,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:4}"}},{text:"[체력 최저] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:5}"}},{text:"[체력 최고] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:6}"}}]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc26 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id") && KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, es.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "tower.target_mode", 4, 4, false))) continue;
            KfcGen.storagePutSnbt(server, "tower", "temp.target_mode", "[{text:\"[전방] \", color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:1}\"}},{text:\"[후방] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:2}\"}},{text:\"[가장 가까운] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:3}\"}},{text:\"[가장 먼] \",color:green,bold:true,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:4}\"}},{text:\"[체력 최저] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:5}\"}},{text:\"[체력 최고] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:6}\"}}]", "set");
        }

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id if score @n[tag=tower.muzzle] tower.target_mode matches 5 run data modify storage tower temp.target_mode set value [{text:"[전방] ", color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:1}"}},{text:"[후방] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:2}"}},{text:"[가장 가까운] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:3}"}},{text:"[가장 먼] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:4}"}},{text:"[체력 최저] ",color:green,bold:true,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:5}"}},{text:"[체력 최고] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:6}"}}]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc27 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id") && KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, es.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "tower.target_mode", 5, 5, false))) continue;
            KfcGen.storagePutSnbt(server, "tower", "temp.target_mode", "[{text:\"[전방] \", color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:1}\"}},{text:\"[후방] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:2}\"}},{text:\"[가장 가까운] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:3}\"}},{text:\"[가장 먼] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:4}\"}},{text:\"[체력 최저] \",color:green,bold:true,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:5}\"}},{text:\"[체력 최고] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:6}\"}}]", "set");
        }

        // execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id if score @n[tag=tower.muzzle] tower.target_mode matches 6 run data modify storage tower temp.target_mode set value [{text:"[전방] ", color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:1}"}},{text:"[후방] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:2}"}},{text:"[가장 가까운] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:3}"}},{text:"[가장 먼] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:4}"}},{text:"[체력 최저] ",color:gray,bold:false,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:5}"}},{text:"[체력 최고] ",color:green,bold:true,click_event:{action:"run_command",command:"/function tower:upgrade/change_mode {mode:6}"}}]
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("tower.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc28 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id") && KfcGen.entityScoreMatches(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, es.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "tower.target_mode", 6, 6, false))) continue;
            KfcGen.storagePutSnbt(server, "tower", "temp.target_mode", "[{text:\"[전방] \", color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:1}\"}},{text:\"[후방] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:2}\"}},{text:\"[가장 가까운] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:3}\"}},{text:\"[가장 먼] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:4}\"}},{text:\"[체력 최저] \",color:gray,bold:false,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:5}\"}},{text:\"[체력 최고] \",color:green,bold:true,click_event:{action:\"run_command\",command:\"/function tower:upgrade/change_mode {mode:6}\"}}]", "set");
        }

        // tellraw @s [{text:"[타겟 모드] ",bold:false,color:aqua},{nbt:"temp.target_mode",storage:"tower",interpret:true}]
        if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp) KfcGen.tellraw(_tp, source, "[{text:\"[타겟 모드] \",bold:false,color:aqua},{nbt:\"temp.target_mode\",storage:\"tower\",interpret:true}]");
        return 0;
    }

    public static void _m_9ea74a1a_tower_upgrade_update_model_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_9ea74a1a_tower_upgrade_update_model_executeReturn(source, macroArgs);
    }

    public static int _m_9ea74a1a_tower_upgrade_update_model_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/1줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "upgrade/update_model"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function tower:spawn/model/$(model)/lv$(level)
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_0a7ca4dd_tower_upgrade_sound_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_0a7ca4dd_tower_upgrade_sound_main_executeReturn(source, macroArgs);
    }

    public static int _m_0a7ca4dd_tower_upgrade_sound_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $execute as @a if score @s player.id = #temp player.id run function tower:upgrade/sound/$(model)/main
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            // -> tower:upgrade/sound/macroArgs.get("model")/main
            switch (macroArgs.get("model")) {
                case "crossbow": tdpack.buckets.Bucket11._m_f1481c7a_tower_upgrade_sound_crossbow_main_execute(es); break;
                default: break;
            }
        }
        return 0;
    }

    public static void _m_f1481c7a_tower_upgrade_sound_crossbow_main_execute(ServerCommandSource source) {
        _m_f1481c7a_tower_upgrade_sound_crossbow_main_executeReturn(source);
    }

    public static int _m_f1481c7a_tower_upgrade_sound_crossbow_main_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute as @a if score @s player.id = #temp player.id run playsound minecraft:entity.villager.work_fletcher weather @s ~ ~ ~ 1 1 1
        for (ServerPlayerEntity e : ctx.allPlayers) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "player.id", "=", "#temp", "player.id"))) continue;
            if (e instanceof net.minecraft.server.network.ServerPlayerEntity _ps) KfcGen.playSound(_ps, "minecraft:entity.villager.work_fletcher", "weather", new net.minecraft.util.math.Vec3d(es.getPosition().x, es.getPosition().y, es.getPosition().z).x, new net.minecraft.util.math.Vec3d(es.getPosition().x, es.getPosition().y, es.getPosition().z).y, new net.minecraft.util.math.Vec3d(es.getPosition().x, es.getPosition().y, es.getPosition().z).z, 1.0f, 1.0f);
        }
        return 0;
    }
}
