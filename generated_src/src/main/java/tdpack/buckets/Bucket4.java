package tdpack.buckets;

import tdpack.generated.KfcGen;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/** Auto-bucketed: 83 datapack functions. */
public final class Bucket4 {
    private Bucket4() { throw new UnsupportedOperationException(); }

    public static void _m_3c5fc517_api_tower_get_bleed_tower_execute(ServerCommandSource source) {
        _m_3c5fc517_api_tower_get_bleed_tower_executeReturn(source);
    }

    public static int _m_3c5fc517_api_tower_get_bleed_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 전서래 검 ]",bold:false,color:green},minecraft:item_model=iron_sword,minecraft:lore=[{text:"700G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 전서래 검 ]",bold:false,color:green},number:1,level:0,model:bleed-tower,location:1,attack:7,attack_speed:40,info:{attack_speed:"0.5/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:4,Bullet:{type:sword,model:arrow,speed:2,life:3,attribute:{bleed:3}},price:700,sell_price:350,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:"[ 전서래 검 ]",bold:false,color:green},number:1,level:1,model:bleed-tower,location:1,attack:10,attack_speed:30,info:{attack_speed:"0.66/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:4.3,Bullet:{type:sword,model:arrow,speed:1.75,life:4,attribute:{bleed:6}},price:300,sell_price:500,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 전서래 검 ]",bold:false,color:green},number:1,level:2,model:bleed-tower,location:1,attack:15,attack_speed:20,info:{attack_speed:"1.0/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 40 block/s"}]},range:5,Bullet:{type:sword,model:arrow,speed:2,life:4,attribute:{bleed:10}},price:1000,sell_price:1000,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 전서래 검 ]",bold:false,color:green},number:1,level:3,model:bleed-tower,location:1,attack:25,attack_speed:15,info:{attack_speed:"1.33/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 60 block/s"}]},range:4.6,Bullet:{type:sword,model:arrow,speed:3,life:3,attribute:{bleed:15}},price:2000,sell_price:2000,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 전서래 검 ]",bold:false,color:green},number:1,level:4,model:bleed-tower,location:1,attack:35,attack_speed:10,info:{attack_speed:"2/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:4.9,Bullet:{type:sword,model:arrow,speed:2.5,life:4,attribute:{bleed:20}},price:4250,sell_price:4125,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 전서래 검 ]",bold:false,color:green},number:1,level:5,model:bleed-tower,location:1,attack:60,attack_speed:10,info:{attack_speed:"2/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:5.2,Bullet:{type:sword,model:arrow,speed:2.5,life:4,attribute:{bleed:30}},price:8500,sell_price:8375,size:2f,translation:-1f,hitbox:1f}}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 전서래 검 ]\",bold:false,color:green},minecraft:item_model=iron_sword,minecraft:lore=[{text:\"700G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 전서래 검 ]\",bold:false,color:green},number:1,level:0,model:bleed-tower,location:1,attack:7,attack_speed:40,info:{attack_speed:\"0.5/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:4,Bullet:{type:sword,model:arrow,speed:2,life:3,attribute:{bleed:3}},price:700,sell_price:350,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 전서래 검 ]\",bold:false,color:green},number:1,level:1,model:bleed-tower,location:1,attack:10,attack_speed:30,info:{attack_speed:\"0.66/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:4.3,Bullet:{type:sword,model:arrow,speed:1.75,life:4,attribute:{bleed:6}},price:300,sell_price:500,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 전서래 검 ]\",bold:false,color:green},number:1,level:2,model:bleed-tower,location:1,attack:15,attack_speed:20,info:{attack_speed:\"1.0/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 40 block/s\"}]},range:5,Bullet:{type:sword,model:arrow,speed:2,life:4,attribute:{bleed:10}},price:1000,sell_price:1000,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 전서래 검 ]\",bold:false,color:green},number:1,level:3,model:bleed-tower,location:1,attack:25,attack_speed:15,info:{attack_speed:\"1.33/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 60 block/s\"}]},range:4.6,Bullet:{type:sword,model:arrow,speed:3,life:3,attribute:{bleed:15}},price:2000,sell_price:2000,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 전서래 검 ]\",bold:false,color:green},number:1,level:4,model:bleed-tower,location:1,attack:35,attack_speed:10,info:{attack_speed:\"2/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:4.9,Bullet:{type:sword,model:arrow,speed:2.5,life:4,attribute:{bleed:20}},price:4250,sell_price:4125,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 전서래 검 ]\",bold:false,color:green},number:1,level:5,model:bleed-tower,location:1,attack:60,attack_speed:10,info:{attack_speed:\"2/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:5.2,Bullet:{type:sword,model:arrow,speed:2.5,life:4,attribute:{bleed:30}},price:8500,sell_price:8375,size:2f,translation:-1f,hitbox:1f}}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_4565e83c_api_tower_get_coilgun_execute(ServerCommandSource source) {
        _m_4565e83c_api_tower_get_coilgun_executeReturn(source);
    }

    public static int _m_4565e83c_api_tower_get_coilgun_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 코일건 ]",bold:false,color:green},minecraft:item_model=golden_horse_armor,minecraft:lore=[{text:"1650G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 코일건 ]",bold:false,color:green},number:2,level:0,model:coilgun,location:2,attack:45,attack_speed:100,info:{attack_speed:"5s"},range:15,Bullet:{type:hitscan,model:arrow,life:30},price:1650,sell_price:825,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:"[ 코일건 ]",bold:false,color:green},number:2,level:1,model:coilgun,location:2,attack:80,attack_speed:90,info:{attack_speed:"4.5s"},range:17,Bullet:{type:hitscan,model:arrow,life:34},price:1100,sell_price:1375,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 코일건 ]",bold:false,color:green},number:2,level:2,model:coilgun,location:2,attack:145,attack_speed:80,info:{attack_speed:"4s"},range:19,Bullet:{type:hitscan,model:arrow,life:38},price:3300,sell_price:3025,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 코일건 ]",bold:false,color:green},number:2,level:3,model:coilgun,location:2,attack:250,attack_speed:70,info:{attack_speed:"3.5s"},range:21,Bullet:{type:hitscan,model:arrow,life:42},price:8800,sell_price:7425,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 코일건 ]",bold:false,color:green},number:2,level:4,model:coilgun,location:2,attack:430,attack_speed:60,info:{attack_speed:"3s"},range:25,Bullet:{type:hitscan,model:arrow,life:50},price:13200,sell_price:14025,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 코일건 ]",bold:false,color:green},number:2,level:5,model:coilgun,location:2,attack:450,attack_speed:40,info:{attack_speed:"2s"},range:25,Bullet:{type:hitscan,model:arrow,life:50},price:14000,sell_price:21025,size:2f,translation:-1f,hitbox:1f}}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 코일건 ]\",bold:false,color:green},minecraft:item_model=golden_horse_armor,minecraft:lore=[{text:\"1650G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 코일건 ]\",bold:false,color:green},number:2,level:0,model:coilgun,location:2,attack:45,attack_speed:100,info:{attack_speed:\"5s\"},range:15,Bullet:{type:hitscan,model:arrow,life:30},price:1650,sell_price:825,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 코일건 ]\",bold:false,color:green},number:2,level:1,model:coilgun,location:2,attack:80,attack_speed:90,info:{attack_speed:\"4.5s\"},range:17,Bullet:{type:hitscan,model:arrow,life:34},price:1100,sell_price:1375,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 코일건 ]\",bold:false,color:green},number:2,level:2,model:coilgun,location:2,attack:145,attack_speed:80,info:{attack_speed:\"4s\"},range:19,Bullet:{type:hitscan,model:arrow,life:38},price:3300,sell_price:3025,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 코일건 ]\",bold:false,color:green},number:2,level:3,model:coilgun,location:2,attack:250,attack_speed:70,info:{attack_speed:\"3.5s\"},range:21,Bullet:{type:hitscan,model:arrow,life:42},price:8800,sell_price:7425,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 코일건 ]\",bold:false,color:green},number:2,level:4,model:coilgun,location:2,attack:430,attack_speed:60,info:{attack_speed:\"3s\"},range:25,Bullet:{type:hitscan,model:arrow,life:50},price:13200,sell_price:14025,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 코일건 ]\",bold:false,color:green},number:2,level:5,model:coilgun,location:2,attack:450,attack_speed:40,info:{attack_speed:\"2s\"},range:25,Bullet:{type:hitscan,model:arrow,life:50},price:14000,sell_price:21025,size:2f,translation:-1f,hitbox:1f}}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_693f89a8_api_tower_get_crossbow_execute(ServerCommandSource source) {
        _m_693f89a8_api_tower_get_crossbow_executeReturn(source);
    }

    public static int _m_693f89a8_api_tower_get_crossbow_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 석궁 ]",bold:false,color:green},minecraft:item_model=crossbow,minecraft:lore=[{text:"250G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 석궁 ]",bold:false,color:green},number:3,level:0,model:crossbow,location:1,attack:3,attack_speed:40,info:{attack_speed:"0.5/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:6,Bullet:{type:projectile,model:arrow,speed:2,life:3},price:250,sell_price:125,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:12},Tower_Upgrade:{Tower_Status:{name:{text:"[ 석궁 ]",bold:false,color:green},number:3,level:1,model:crossbow,location:1,attack:3,attack_speed:30,info:{attack_speed:"0.66/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:7,Bullet:{type:projectile,model:arrow,speed:1.75,life:4},price:50,sell_price:150,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 석궁 ]",bold:false,color:green},number:3,level:2,model:crossbow,location:1,attack:4,attack_speed:20,info:{attack_speed:"1.0/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 40 block/s"}]},range:8,Bullet:{type:projectile,model:arrow,speed:2,life:4},price:300,sell_price:300,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 석궁 ]",bold:false,color:green},number:3,level:3,model:crossbow,location:1,attack:7,attack_speed:15,info:{attack_speed:"1.33/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 60 block/s"}]},range:9,Bullet:{type:projectile,model:arrow,speed:3,life:3},price:1000,sell_price:800,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 석궁 ]",bold:false,color:green},number:3,level:4,model:crossbow,location:1,attack:15,attack_speed:10,info:{attack_speed:"2/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:10,Bullet:{type:projectile,model:arrow,speed:2.5,life:4},price:3000,sell_price:2300,size:2f,translation:-1f,hitbox:1f}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 석궁 ]\",bold:false,color:green},minecraft:item_model=crossbow,minecraft:lore=[{text:\"250G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 석궁 ]\",bold:false,color:green},number:3,level:0,model:crossbow,location:1,attack:3,attack_speed:40,info:{attack_speed:\"0.5/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:6,Bullet:{type:projectile,model:arrow,speed:2,life:3},price:250,sell_price:125,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:12},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 석궁 ]\",bold:false,color:green},number:3,level:1,model:crossbow,location:1,attack:3,attack_speed:30,info:{attack_speed:\"0.66/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:7,Bullet:{type:projectile,model:arrow,speed:1.75,life:4},price:50,sell_price:150,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 석궁 ]\",bold:false,color:green},number:3,level:2,model:crossbow,location:1,attack:4,attack_speed:20,info:{attack_speed:\"1.0/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 40 block/s\"}]},range:8,Bullet:{type:projectile,model:arrow,speed:2,life:4},price:300,sell_price:300,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 석궁 ]\",bold:false,color:green},number:3,level:3,model:crossbow,location:1,attack:7,attack_speed:15,info:{attack_speed:\"1.33/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 60 block/s\"}]},range:9,Bullet:{type:projectile,model:arrow,speed:3,life:3},price:1000,sell_price:800,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 석궁 ]\",bold:false,color:green},number:3,level:4,model:crossbow,location:1,attack:15,attack_speed:10,info:{attack_speed:\"2/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:10,Bullet:{type:projectile,model:arrow,speed:2.5,life:4},price:3000,sell_price:2300,size:2f,translation:-1f,hitbox:1f}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_34122582_api_tower_get_farm_execute(ServerCommandSource source) {
        _m_34122582_api_tower_get_farm_executeReturn(source);
    }

    public static int _m_34122582_api_tower_get_farm_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p stone_hoe[minecraft:item_name={text:"[ 농장 ]",bold:false,color:green},minecraft:lore=[{text:"450G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 농장 ]",bold:false,color:green},number:4,range:0,level:0,model:farm,location:1,attack:15,attack_speed:100,info:{attack_speed:"25/5s"},Bullet:{type:farm},price:450,sell_price:225,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:8},Tower_Upgrade:{Tower_Status:{name:{text:"[ 농장 ]",bold:false,color:green},number:4,range:0,level:1,model:farm,location:1,attack:30,attack_speed:100,info:{attack_speed:"50/5s"},Bullet:{type:farm},price:350,sell_price:400,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 농장 ]",bold:false,color:green},number:4,range:0,level:2,model:farm,location:1,attack:45,attack_speed:100,info:{attack_speed:"75/5s"},Bullet:{type:farm},price:750,sell_price:775,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 농장 ]",bold:false,color:green},number:4,range:0,level:3,model:farm,location:1,attack:90,attack_speed:100,info:{attack_speed:"150/5s"},Bullet:{type:farm},price:2500,sell_price:2025,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 농장 ]",bold:false,color:green},number:4,range:0,level:4,model:farm,location:1,attack:180,attack_speed:100,info:{attack_speed:"300/5s"},Bullet:{type:farm},price:5000,sell_price:4525,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 농장 ]",bold:false,color:green},number:4,range:0,level:5,model:farm,location:1,attack:250,attack_speed:100,info:{attack_speed:"500/5s"},Bullet:{type:farm},price:15000,size:2f,translation:-1f,hitbox:1f}}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "stone_hoe[minecraft:item_name={text:\"[ 농장 ]\",bold:false,color:green},minecraft:lore=[{text:\"450G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 농장 ]\",bold:false,color:green},number:4,range:0,level:0,model:farm,location:1,attack:15,attack_speed:100,info:{attack_speed:\"25/5s\"},Bullet:{type:farm},price:450,sell_price:225,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:8},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 농장 ]\",bold:false,color:green},number:4,range:0,level:1,model:farm,location:1,attack:30,attack_speed:100,info:{attack_speed:\"50/5s\"},Bullet:{type:farm},price:350,sell_price:400,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 농장 ]\",bold:false,color:green},number:4,range:0,level:2,model:farm,location:1,attack:45,attack_speed:100,info:{attack_speed:\"75/5s\"},Bullet:{type:farm},price:750,sell_price:775,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 농장 ]\",bold:false,color:green},number:4,range:0,level:3,model:farm,location:1,attack:90,attack_speed:100,info:{attack_speed:\"150/5s\"},Bullet:{type:farm},price:2500,sell_price:2025,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 농장 ]\",bold:false,color:green},number:4,range:0,level:4,model:farm,location:1,attack:180,attack_speed:100,info:{attack_speed:\"300/5s\"},Bullet:{type:farm},price:5000,sell_price:4525,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 농장 ]\",bold:false,color:green},number:4,range:0,level:5,model:farm,location:1,attack:250,attack_speed:100,info:{attack_speed:\"500/5s\"},Bullet:{type:farm},price:15000,size:2f,translation:-1f,hitbox:1f}}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_2405b16c_api_tower_get_flame_tower_execute(ServerCommandSource source) {
        _m_2405b16c_api_tower_get_flame_tower_executeReturn(source);
    }

    public static int _m_2405b16c_api_tower_get_flame_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 화염방사기 ]",bold:false,color:green},minecraft:item_model=fire_charge,minecraft:lore=[{text:"1200G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 화염방사기 ]",bold:false,color:green},number:5,level:0,model:"flame-tower",location:1,attack:1,attack_speed:30,info:{attack_speed:"0.66/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:6,Bullet:{type:hitscan,model:arrow,life:5,attribute:{flame:3},hitscan:{area:4}},price:1200,sell_price:600,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:"[ 화염방사기 ]",bold:false,color:green},number:5,level:1,model:"flame-tower",location:1,attack:3,attack_speed:20,info:{attack_speed:"1/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:7,Bullet:{type:hitscan,model:arrow,life:5,attribute:{flame:6},hitscan:{area:4}},price:600,sell_price:900,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 화염방사기 ]",bold:false,color:green},number:5,level:2,model:"flame-tower",location:1,attack:5,attack_speed:10,info:{attack_speed:"2/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 40 block/s"}]},range:8,Bullet:{type:hitscan,model:arrow,life:6,attribute:{flame:10},hitscan:{area:4}},price:3000,sell_price:2400,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 화염방사기 ]",bold:false,color:green},number:5,level:3,model:"flame-tower",location:1,attack:7,attack_speed:5,info:{attack_speed:"4/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 60 block/s"}]},range:10,Bullet:{type:hitscan,model:arrow,life:7,attribute:{flame:15},hitscan:{area:4}},price:7500,sell_price:6150,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 화염방사기 ]",bold:false,color:green},number:5,level:4,model:"flame-tower",location:1,attack:10,attack_speed:4,info:{attack_speed:"5/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:12,Bullet:{type:hitscan,model:arrow,life:8,attribute:{flame:20},hitscan:{area:4}},price:12500,sell_price:12400,size:2f,translation:-1f,hitbox:1f}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 화염방사기 ]\",bold:false,color:green},minecraft:item_model=fire_charge,minecraft:lore=[{text:\"1200G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 화염방사기 ]\",bold:false,color:green},number:5,level:0,model:\"flame-tower\",location:1,attack:1,attack_speed:30,info:{attack_speed:\"0.66/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:6,Bullet:{type:hitscan,model:arrow,life:5,attribute:{flame:3},hitscan:{area:4}},price:1200,sell_price:600,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 화염방사기 ]\",bold:false,color:green},number:5,level:1,model:\"flame-tower\",location:1,attack:3,attack_speed:20,info:{attack_speed:\"1/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:7,Bullet:{type:hitscan,model:arrow,life:5,attribute:{flame:6},hitscan:{area:4}},price:600,sell_price:900,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 화염방사기 ]\",bold:false,color:green},number:5,level:2,model:\"flame-tower\",location:1,attack:5,attack_speed:10,info:{attack_speed:\"2/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 40 block/s\"}]},range:8,Bullet:{type:hitscan,model:arrow,life:6,attribute:{flame:10},hitscan:{area:4}},price:3000,sell_price:2400,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 화염방사기 ]\",bold:false,color:green},number:5,level:3,model:\"flame-tower\",location:1,attack:7,attack_speed:5,info:{attack_speed:\"4/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 60 block/s\"}]},range:10,Bullet:{type:hitscan,model:arrow,life:7,attribute:{flame:15},hitscan:{area:4}},price:7500,sell_price:6150,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 화염방사기 ]\",bold:false,color:green},number:5,level:4,model:\"flame-tower\",location:1,attack:10,attack_speed:4,info:{attack_speed:\"5/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:12,Bullet:{type:hitscan,model:arrow,life:8,attribute:{flame:20},hitscan:{area:4}},price:12500,sell_price:12400,size:2f,translation:-1f,hitbox:1f}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_2b896225_api_tower_get_freeze_tower_execute(ServerCommandSource source) {
        _m_2b896225_api_tower_get_freeze_tower_executeReturn(source);
    }

    public static int _m_2b896225_api_tower_get_freeze_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 빙결 포탑 ]",bold:false,color:green},minecraft:item_model=ice,minecraft:lore=[{text:"450G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 빙결 포탑 ]",bold:false,color:green},number:6,level:0,model:"freeze-tower",location:1,attack:2,attack_speed:40,info:{attack_speed:"0.5/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:7,Bullet:{type:hitscan,model:arrow,life:6,attribute:{freeze:15},hitscan:{area:4}},price:450,sell_price:187,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:"[ 빙결 포탑 ]",bold:false,color:green},number:6,level:1,model:"freeze-tower",location:1,attack:2,attack_speed:30,info:{attack_speed:"0.66/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:8,Bullet:{type:hitscan,model:arrow,life:7,attribute:{freeze:30},hitscan:{area:4}},price:1200,sell_price:510,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 빙결 포탑 ]",bold:false,color:green},number:6,level:2,model:"freeze-tower",location:1,attack:3,attack_speed:20,info:{attack_speed:"1.0/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 40 block/s"}]},range:10,Bullet:{type:hitscan,model:arrow,life:8,attribute:{freeze:45},hitscan:{area:4}},price:4500,sell_price:1435,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 빙결 포탑 ]",bold:false,color:green},number:6,level:3,model:"freeze-tower",location:1,attack:5,attack_speed:10,info:{attack_speed:"2/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 60 block/s"}]},range:12,Bullet:{type:hitscan,model:arrow,life:9,attribute:{freeze:60},hitscan:{area:4}},price:15000,sell_price:3585,size:2f,translation:-1f,hitbox:1f}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 빙결 포탑 ]\",bold:false,color:green},minecraft:item_model=ice,minecraft:lore=[{text:\"450G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 빙결 포탑 ]\",bold:false,color:green},number:6,level:0,model:\"freeze-tower\",location:1,attack:2,attack_speed:40,info:{attack_speed:\"0.5/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:7,Bullet:{type:hitscan,model:arrow,life:6,attribute:{freeze:15},hitscan:{area:4}},price:450,sell_price:187,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 빙결 포탑 ]\",bold:false,color:green},number:6,level:1,model:\"freeze-tower\",location:1,attack:2,attack_speed:30,info:{attack_speed:\"0.66/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:8,Bullet:{type:hitscan,model:arrow,life:7,attribute:{freeze:30},hitscan:{area:4}},price:1200,sell_price:510,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 빙결 포탑 ]\",bold:false,color:green},number:6,level:2,model:\"freeze-tower\",location:1,attack:3,attack_speed:20,info:{attack_speed:\"1.0/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 40 block/s\"}]},range:10,Bullet:{type:hitscan,model:arrow,life:8,attribute:{freeze:45},hitscan:{area:4}},price:4500,sell_price:1435,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 빙결 포탑 ]\",bold:false,color:green},number:6,level:3,model:\"freeze-tower\",location:1,attack:5,attack_speed:10,info:{attack_speed:\"2/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 60 block/s\"}]},range:12,Bullet:{type:hitscan,model:arrow,life:9,attribute:{freeze:60},hitscan:{area:4}},price:15000,sell_price:3585,size:2f,translation:-1f,hitbox:1f}}}}}]", 1); } }
        return 0;
    }

    public static void _m_5c64890e_api_tower_get_gatling_execute(ServerCommandSource source) {
        _m_5c64890e_api_tower_get_gatling_executeReturn(source);
    }

    public static int _m_5c64890e_api_tower_get_gatling_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p diamond_hoe[minecraft:item_name={text:"[ 기관총 ]",bold:false,color:green},minecraft:lore=[{text:"2000G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 기관총 ]",bold:false,color:green},number:7,level:0,model:gatling,location:1,attack:6,attack_speed:10,info:{attack_speed:"2/s"},range:7,Bullet:{type:hitscan,model:arrow,life:9},price:2000,sell_price:1000,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:"[ 기관총 ]",bold:false,color:green},number:7,level:1,model:gatling,location:1,attack:10,attack_speed:8,info:{attack_speed:"2.5/s"},range:8,Bullet:{type:hitscan,model:arrow,life:10},price:1660,sell_price:1330,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 기관총 ]",bold:false,color:green},number:7,level:2,model:gatling,location:1,attack:15,attack_speed:5,info:{attack_speed:"4/s"},range:9,Bullet:{type:hitscan,model:arrow,life:11},price:4200,sell_price:2330,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 기관총 ]",bold:false,color:green},number:7,level:3,model:gatling,location:1,attack:25,attack_speed:4,info:{attack_speed:"5/s"},range:11,Bullet:{type:hitscan,model:arrow,life:13},price:18000,sell_price:4830,size:2f,translation:-1f,hitbox:1f}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "diamond_hoe[minecraft:item_name={text:\"[ 기관총 ]\",bold:false,color:green},minecraft:lore=[{text:\"2000G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 기관총 ]\",bold:false,color:green},number:7,level:0,model:gatling,location:1,attack:6,attack_speed:10,info:{attack_speed:\"2/s\"},range:7,Bullet:{type:hitscan,model:arrow,life:9},price:2000,sell_price:1000,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 기관총 ]\",bold:false,color:green},number:7,level:1,model:gatling,location:1,attack:10,attack_speed:8,info:{attack_speed:\"2.5/s\"},range:8,Bullet:{type:hitscan,model:arrow,life:10},price:1660,sell_price:1330,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 기관총 ]\",bold:false,color:green},number:7,level:2,model:gatling,location:1,attack:15,attack_speed:5,info:{attack_speed:\"4/s\"},range:9,Bullet:{type:hitscan,model:arrow,life:11},price:4200,sell_price:2330,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 기관총 ]\",bold:false,color:green},number:7,level:3,model:gatling,location:1,attack:25,attack_speed:4,info:{attack_speed:\"5/s\"},range:11,Bullet:{type:hitscan,model:arrow,life:13},price:18000,sell_price:4830,size:2f,translation:-1f,hitbox:1f}}}}}]", 1); } }
        return 0;
    }

    public static void _m_100beaa1_api_tower_get_machine_gun_execute(ServerCommandSource source) {
        _m_100beaa1_api_tower_get_machine_gun_executeReturn(source);
    }

    public static int _m_100beaa1_api_tower_get_machine_gun_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p netherite_hoe[minecraft:item_name={text:"[ 머신건 ]",bold:false,color:green},minecraft:lore=[{text:"10,000G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 머신건 ]",bold:false,color:green},number:3,level:0,model:machine-gun,location:1,attack:20,attack_speed:5,info:{attack_speed:"4/s"},range:20,Bullet:{type:hitscan,model:arrow,life:20},price:10000,sell_price:5000,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:2},Tower_Upgrade:{Tower_Status:{name:{text:"[ 머신건 ]",bold:false,color:green},number:3,level:1,model:machine-gun,location:1,attack:30,attack_speed:4,info:{attack_speed:"5/s"},range:25,Bullet:{type:hitscan,model:arrow,life:25},price:7500,sell_price:8750,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 머신건 ]",bold:false,color:green},number:3,level:2,model:machine-gun,location:1,attack:50,attack_speed:3,info:{attack_speed:"6.66/s"},range:30,Bullet:{type:hitscan,model:arrow,life:30},price:25000,sell_price:21250,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 머신건 ]",bold:false,color:green},number:3,level:3,model:machine-gun,location:1,attack:75,attack_speed:2,info:{attack_speed:"10/s"},range:35,Bullet:{type:hitscan,model:arrow,life:35},price:75000,sell_price:46200,size:2f,translation:-1f,hitbox:1f}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "netherite_hoe[minecraft:item_name={text:\"[ 머신건 ]\",bold:false,color:green},minecraft:lore=[{text:\"10,000G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 머신건 ]\",bold:false,color:green},number:3,level:0,model:machine-gun,location:1,attack:20,attack_speed:5,info:{attack_speed:\"4/s\"},range:20,Bullet:{type:hitscan,model:arrow,life:20},price:10000,sell_price:5000,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:2},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 머신건 ]\",bold:false,color:green},number:3,level:1,model:machine-gun,location:1,attack:30,attack_speed:4,info:{attack_speed:\"5/s\"},range:25,Bullet:{type:hitscan,model:arrow,life:25},price:7500,sell_price:8750,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 머신건 ]\",bold:false,color:green},number:3,level:2,model:machine-gun,location:1,attack:50,attack_speed:3,info:{attack_speed:\"6.66/s\"},range:30,Bullet:{type:hitscan,model:arrow,life:30},price:25000,sell_price:21250,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 머신건 ]\",bold:false,color:green},number:3,level:3,model:machine-gun,location:1,attack:75,attack_speed:2,info:{attack_speed:\"10/s\"},range:35,Bullet:{type:hitscan,model:arrow,life:35},price:75000,sell_price:46200,size:2f,translation:-1f,hitbox:1f}}}}}]", 1); } }
        return 0;
    }

    public static void _m_b86b05fd_api_tower_get_main_execute(ServerCommandSource source) {
        _m_b86b05fd_api_tower_get_main_executeReturn(source);
    }

    public static int _m_b86b05fd_api_tower_get_main_executeReturn(ServerCommandSource source) {
        
        // function api:tower/get/stun-tower
        // -> api:tower/get/stun-tower
        tdpack.buckets.Bucket4._m_d45869dd_api_tower_get_stun_tower_execute(source);

        // function api:tower/get/bleed-tower
        // -> api:tower/get/bleed-tower
        tdpack.buckets.Bucket4._m_3c5fc517_api_tower_get_bleed_tower_execute(source);

        // function api:tower/get/flame-tower
        // -> api:tower/get/flame-tower
        tdpack.buckets.Bucket4._m_2405b16c_api_tower_get_flame_tower_execute(source);

        // function api:tower/get/freeze-tower
        // -> api:tower/get/freeze-tower
        tdpack.buckets.Bucket4._m_2b896225_api_tower_get_freeze_tower_execute(source);

        // function api:tower/get/poison-tower
        // -> api:tower/get/poison-tower
        tdpack.buckets.Bucket4._m_e457a51e_api_tower_get_poison_tower_execute(source);

        // function api:tower/get/weakness-tower
        // -> api:tower/get/weakness-tower
        tdpack.buckets.Bucket4._m_12f801ac_api_tower_get_weakness_tower_execute(source);

        // function api:tower/get/crossbow
        // -> api:tower/get/crossbow
        tdpack.buckets.Bucket4._m_693f89a8_api_tower_get_crossbow_execute(source);

        // function api:tower/get/gatling
        // -> api:tower/get/gatling
        tdpack.buckets.Bucket4._m_5c64890e_api_tower_get_gatling_execute(source);

        // function api:tower/get/machine-gun
        // -> api:tower/get/machine-gun
        tdpack.buckets.Bucket4._m_100beaa1_api_tower_get_machine_gun_execute(source);

        // function api:tower/get/sniper
        // -> api:tower/get/sniper
        tdpack.buckets.Bucket4._m_8e728219_api_tower_get_sniper_execute(source);

        // function api:tower/get/coilgun
        // -> api:tower/get/coilgun
        tdpack.buckets.Bucket4._m_4565e83c_api_tower_get_coilgun_execute(source);

        // function api:tower/get/railgun
        // -> api:tower/get/railgun
        tdpack.buckets.Bucket4._m_a41d1a78_api_tower_get_railgun_execute(source);

        // function api:tower/get/mine
        // -> api:tower/get/mine
        tdpack.buckets.Bucket4._m_966fbe06_api_tower_get_mine_execute(source);

        // function api:tower/get/farm
        // -> api:tower/get/farm
        tdpack.buckets.Bucket4._m_34122582_api_tower_get_farm_execute(source);
        return 0;
    }

    public static void _m_966fbe06_api_tower_get_mine_execute(ServerCommandSource source) {
        _m_966fbe06_api_tower_get_mine_executeReturn(source);
    }

    public static int _m_966fbe06_api_tower_get_mine_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p stone_pickaxe[minecraft:item_name={text:"[ 광산 ]",bold:false,color:green},minecraft:lore=[{text:"450G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 광산 ]",bold:false,color:green},number:1,level:0,model:mine,location:1,attack:50,info:{attack_speed:"50/round"},Bullet:{type:mine},price:450,sell_price:225,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:8},Tower_Upgrade:{Tower_Status:{name:{text:"[ 광산 ]",bold:false,color:green},number:1,level:1,model:mine,location:1,attack:100,info:{attack_speed:"100/round"},Bullet:{type:mine},price:350,sell_price:400,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 광산 ]",bold:false,color:green},number:1,level:2,model:mine,location:1,attack:200,info:{attack_speed:"200/round"},Bullet:{type:mine},price:750,sell_price:775,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 광산 ]",bold:false,color:green},number:1,level:3,model:mine,location:1,attack:400,info:{attack_speed:"400/round"},Bullet:{type:mine},price:1500,sell_price:1525,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 광산 ]",bold:false,color:green},number:1,level:4,model:mine,location:1,attack:750,info:{attack_speed:"750/round"},Bullet:{type:mine},price:3750,sell_price:3400,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 광산 ]",bold:false,color:green},number:1,level:5,model:mine,location:1,attack:1500,info:{attack_speed:"1500/round"},Bullet:{type:mine},price:7500,size:2f,translation:-1f,hitbox:1f}}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "stone_pickaxe[minecraft:item_name={text:\"[ 광산 ]\",bold:false,color:green},minecraft:lore=[{text:\"450G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 광산 ]\",bold:false,color:green},number:1,level:0,model:mine,location:1,attack:50,info:{attack_speed:\"50/round\"},Bullet:{type:mine},price:450,sell_price:225,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:8},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 광산 ]\",bold:false,color:green},number:1,level:1,model:mine,location:1,attack:100,info:{attack_speed:\"100/round\"},Bullet:{type:mine},price:350,sell_price:400,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 광산 ]\",bold:false,color:green},number:1,level:2,model:mine,location:1,attack:200,info:{attack_speed:\"200/round\"},Bullet:{type:mine},price:750,sell_price:775,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 광산 ]\",bold:false,color:green},number:1,level:3,model:mine,location:1,attack:400,info:{attack_speed:\"400/round\"},Bullet:{type:mine},price:1500,sell_price:1525,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 광산 ]\",bold:false,color:green},number:1,level:4,model:mine,location:1,attack:750,info:{attack_speed:\"750/round\"},Bullet:{type:mine},price:3750,sell_price:3400,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 광산 ]\",bold:false,color:green},number:1,level:5,model:mine,location:1,attack:1500,info:{attack_speed:\"1500/round\"},Bullet:{type:mine},price:7500,size:2f,translation:-1f,hitbox:1f}}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_e457a51e_api_tower_get_poison_tower_execute(ServerCommandSource source) {
        _m_e457a51e_api_tower_get_poison_tower_executeReturn(source);
    }

    public static int _m_e457a51e_api_tower_get_poison_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 중독 포탑 ]",bold:false,color:green},minecraft:item_model=potion,minecraft:lore=[{text:"275G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 중독 포탑 ]",bold:false,color:green},number:1,level:0,model:"poison-tower",location:1,attack:3,attack_speed:200,info:{attack_speed:"0.1/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:6,Bullet:{type:projectile,model:arrow,speed:2,life:3,attribute:{poison:30,floor:{range:3,time:60,attack:1,attribute:{poison:1}}}},price:275,sell_price:137,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:"[ 중독 포탑 ]",bold:false,color:green},number:1,level:1,model:"poison-tower",location:1,attack:3,attack_speed:180,info:{attack_speed:"0.11/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:7,Bullet:{type:projectile,model:arrow,speed:1.75,life:4,attribute:{poison:60,floor:{range:3.5,time:70,attack:1,attribute:{poison:1}}}},price:125,sell_price:200,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 중독 포탑 ]",bold:false,color:green},number:1,level:2,model:"poison-tower",location:1,attack:4,attack_speed:160,info:{attack_speed:"0.12/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 40 block/s"}]},range:8,Bullet:{type:projectile,model:arrow,speed:2,life:4,attribute:{poison:90,floor:{range:4,time:80,attack:2,attribute:{poison:2}}}},price:950,sell_price:675,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 중독 포탑 ]",bold:false,color:green},number:1,level:3,model:"poison-tower",location:1,attack:7,attack_speed:140,info:{attack_speed:"0.14/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 60 block/s"}]},range:9,Bullet:{type:projectile,model:arrow,speed:3,life:3,attribute:{poison:150,floor:{range:4.5,time:90,attack:2,attribute:{poison:2,freeze:20}}}},price:3600,sell_price:2475,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 중독 포탑 ]",bold:false,color:green},number:1,level:4,model:"poison-tower",location:1,attack:15,attack_speed:100,info:{attack_speed:"0.2/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:10,Bullet:{type:projectile,model:arrow,speed:2.5,life:4,attribute:{poison:210,floor:{range:5,time:100,attack:3,attribute:{poison:3,freeze:30}}}},price:9500,sell_price:7225,size:2f,translation:-1f,hitbox:1f}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 중독 포탑 ]\",bold:false,color:green},minecraft:item_model=potion,minecraft:lore=[{text:\"275G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 중독 포탑 ]\",bold:false,color:green},number:1,level:0,model:\"poison-tower\",location:1,attack:3,attack_speed:200,info:{attack_speed:\"0.1/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:6,Bullet:{type:projectile,model:arrow,speed:2,life:3,attribute:{poison:30,floor:{range:3,time:60,attack:1,attribute:{poison:1}}}},price:275,sell_price:137,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 중독 포탑 ]\",bold:false,color:green},number:1,level:1,model:\"poison-tower\",location:1,attack:3,attack_speed:180,info:{attack_speed:\"0.11/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:7,Bullet:{type:projectile,model:arrow,speed:1.75,life:4,attribute:{poison:60,floor:{range:3.5,time:70,attack:1,attribute:{poison:1}}}},price:125,sell_price:200,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 중독 포탑 ]\",bold:false,color:green},number:1,level:2,model:\"poison-tower\",location:1,attack:4,attack_speed:160,info:{attack_speed:\"0.12/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 40 block/s\"}]},range:8,Bullet:{type:projectile,model:arrow,speed:2,life:4,attribute:{poison:90,floor:{range:4,time:80,attack:2,attribute:{poison:2}}}},price:950,sell_price:675,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 중독 포탑 ]\",bold:false,color:green},number:1,level:3,model:\"poison-tower\",location:1,attack:7,attack_speed:140,info:{attack_speed:\"0.14/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 60 block/s\"}]},range:9,Bullet:{type:projectile,model:arrow,speed:3,life:3,attribute:{poison:150,floor:{range:4.5,time:90,attack:2,attribute:{poison:2,freeze:20}}}},price:3600,sell_price:2475,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 중독 포탑 ]\",bold:false,color:green},number:1,level:4,model:\"poison-tower\",location:1,attack:15,attack_speed:100,info:{attack_speed:\"0.2/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:10,Bullet:{type:projectile,model:arrow,speed:2.5,life:4,attribute:{poison:210,floor:{range:5,time:100,attack:3,attribute:{poison:3,freeze:30}}}},price:9500,sell_price:7225,size:2f,translation:-1f,hitbox:1f}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_77dedf35_api_tower_get_potato_gun_execute(ServerCommandSource source) {
        _m_77dedf35_api_tower_get_potato_gun_executeReturn(source);
    }

    public static int _m_77dedf35_api_tower_get_potato_gun_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 포테이토 건 ]",bold:false,color:green},minecraft:item_model=potato,minecraft:lore=[{text:"250G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 포테이토 건 ]",bold:false,color:green},number:1,level:0,model:potato-gun,location:1,attack:3,attack_speed:40,info:{attack_speed:"0.5/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:6,Bullet:{type:projectile,model:potato,speed:2,life:3},price:250,sell_price:125,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:12},Tower_Upgrade:{Tower_Status:{name:{text:"[ 포테이토 건 ]",bold:false,color:green},number:1,level:1,model:potato-gun,location:1,attack:3,attack_speed:30,info:{attack_speed:"0.66/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:7,Bullet:{type:projectile,model:potato,speed:1.75,life:4},price:50,sell_price:150,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 포테이토 건 ]",bold:false,color:green},number:1,level:2,model:potato-gun,location:1,attack:4,attack_speed:20,info:{attack_speed:"1.0/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 40 block/s"}]},range:8,Bullet:{type:projectile,model:potato,speed:2,life:4},price:300,sell_price:300,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 포테이토 건 ]",bold:false,color:green},number:1,level:3,model:potato-gun,location:1,attack:7,attack_speed:15,info:{attack_speed:"1.33/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 60 block/s"}]},range:9,Bullet:{type:projectile,model:potato,speed:3,life:3},price:1000,sell_price:800,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 포테이토 건 ]",bold:false,color:green},number:1,level:4,model:potato-gun,location:1,attack:15,attack_speed:10,info:{attack_speed:"2/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:10,Bullet:{type:projectile,model:potato,speed:2.5,life:4},price:3000,sell_price:2300,size:2f,translation:-1f,hitbox:1f}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 포테이토 건 ]\",bold:false,color:green},minecraft:item_model=potato,minecraft:lore=[{text:\"250G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 포테이토 건 ]\",bold:false,color:green},number:1,level:0,model:potato-gun,location:1,attack:3,attack_speed:40,info:{attack_speed:\"0.5/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:6,Bullet:{type:projectile,model:potato,speed:2,life:3},price:250,sell_price:125,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:12},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 포테이토 건 ]\",bold:false,color:green},number:1,level:1,model:potato-gun,location:1,attack:3,attack_speed:30,info:{attack_speed:\"0.66/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:7,Bullet:{type:projectile,model:potato,speed:1.75,life:4},price:50,sell_price:150,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 포테이토 건 ]\",bold:false,color:green},number:1,level:2,model:potato-gun,location:1,attack:4,attack_speed:20,info:{attack_speed:\"1.0/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 40 block/s\"}]},range:8,Bullet:{type:projectile,model:potato,speed:2,life:4},price:300,sell_price:300,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 포테이토 건 ]\",bold:false,color:green},number:1,level:3,model:potato-gun,location:1,attack:7,attack_speed:15,info:{attack_speed:\"1.33/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 60 block/s\"}]},range:9,Bullet:{type:projectile,model:potato,speed:3,life:3},price:1000,sell_price:800,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 포테이토 건 ]\",bold:false,color:green},number:1,level:4,model:potato-gun,location:1,attack:15,attack_speed:10,info:{attack_speed:\"2/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:10,Bullet:{type:projectile,model:potato,speed:2.5,life:4},price:3000,sell_price:2300,size:2f,translation:-1f,hitbox:1f}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_a41d1a78_api_tower_get_railgun_execute(ServerCommandSource source) {
        _m_a41d1a78_api_tower_get_railgun_executeReturn(source);
    }

    public static int _m_a41d1a78_api_tower_get_railgun_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 레일건 ]",bold:false,color:green},minecraft:item_model=diamond_horse_armor,minecraft:lore=[{text:"3600G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 레일건 ]",bold:false,color:green},number:2,level:0,model:railgun,location:2,attack:80,attack_speed:80,info:{attack_speed:"4s"},range:25,Bullet:{type:hitscan,model:arrow,life:60},price:3600,sell_price:1800,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:2},Tower_Upgrade:{Tower_Status:{name:{text:"[ 레일건 ]",bold:false,color:green},number:2,level:1,model:railgun,location:2,attack:1500,attack_speed:110,info:{attack_speed:"5.5s"},range:30,Bullet:{type:hitscan,model:arrow,life:60},price:25000,sell_price:14300,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 레일건 ]",bold:false,color:green},number:2,level:2,model:railgun,location:2,attack:5000,attack_speed:140,info:{attack_speed:"7s"},range:40,Bullet:{type:hitscan,model:arrow,life:75},price:75000,sell_price:51800,size:2f,translation:-1f,hitbox:1f}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 레일건 ]\",bold:false,color:green},minecraft:item_model=diamond_horse_armor,minecraft:lore=[{text:\"3600G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 레일건 ]\",bold:false,color:green},number:2,level:0,model:railgun,location:2,attack:80,attack_speed:80,info:{attack_speed:\"4s\"},range:25,Bullet:{type:hitscan,model:arrow,life:60},price:3600,sell_price:1800,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:2},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 레일건 ]\",bold:false,color:green},number:2,level:1,model:railgun,location:2,attack:1500,attack_speed:110,info:{attack_speed:\"5.5s\"},range:30,Bullet:{type:hitscan,model:arrow,life:60},price:25000,sell_price:14300,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 레일건 ]\",bold:false,color:green},number:2,level:2,model:railgun,location:2,attack:5000,attack_speed:140,info:{attack_speed:\"7s\"},range:40,Bullet:{type:hitscan,model:arrow,life:75},price:75000,sell_price:51800,size:2f,translation:-1f,hitbox:1f}}}}]", 1); } }
        return 0;
    }

    public static void _m_8e728219_api_tower_get_sniper_execute(ServerCommandSource source) {
        _m_8e728219_api_tower_get_sniper_executeReturn(source);
    }

    public static int _m_8e728219_api_tower_get_sniper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 저격총 ]",bold:false,color:green},minecraft:item_model=spyglass,minecraft:lore=[{text:"400G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 저격총 ]",bold:false,color:green},number:2,level:0,model:sniper,location:2,attack:8,attack_speed:100,info:{attack_speed:"5s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:12,Bullet:{type:hitscan,model:arrow,life:36},price:400,sell_price:200,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:12},Tower_Upgrade:{Tower_Status:{name:{text:"[ 저격총 ]",bold:false,color:green},number:2,level:1,model:sniper,location:2,attack:10,attack_speed:90,info:{attack_speed:"4.5s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:14,Bullet:{type:hitscan,model:arrow,life:42},price:250,sell_price:325,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 저격총 ]",bold:false,color:green},number:2,level:2,model:sniper,location:2,attack:18,attack_speed:80,info:{attack_speed:"4s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 40 block/s"}]},range:15,Bullet:{type:hitscan,model:arrow,life:45},price:450,sell_price:550,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 저격총 ]",bold:false,color:green},number:2,level:3,model:sniper,location:2,attack:40,attack_speed:70,info:{attack_speed:"3.5s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 60 block/s"}]},range:15,Bullet:{type:hitscan,model:arrow,life:45},price:1500,sell_price:1300,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 저격총 ]",bold:false,color:green},number:2,level:4,model:sniper,location:2,attack:100,attack_speed:60,info:{attack_speed:"3s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:18,Bullet:{type:hitscan,model:arrow,life:54},price:4500,sell_price:3550,size:2f,translation:-1f,hitbox:1f}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 저격총 ]\",bold:false,color:green},minecraft:item_model=spyglass,minecraft:lore=[{text:\"400G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 저격총 ]\",bold:false,color:green},number:2,level:0,model:sniper,location:2,attack:8,attack_speed:100,info:{attack_speed:\"5s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:12,Bullet:{type:hitscan,model:arrow,life:36},price:400,sell_price:200,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:12},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 저격총 ]\",bold:false,color:green},number:2,level:1,model:sniper,location:2,attack:10,attack_speed:90,info:{attack_speed:\"4.5s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:14,Bullet:{type:hitscan,model:arrow,life:42},price:250,sell_price:325,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 저격총 ]\",bold:false,color:green},number:2,level:2,model:sniper,location:2,attack:18,attack_speed:80,info:{attack_speed:\"4s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 40 block/s\"}]},range:15,Bullet:{type:hitscan,model:arrow,life:45},price:450,sell_price:550,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 저격총 ]\",bold:false,color:green},number:2,level:3,model:sniper,location:2,attack:40,attack_speed:70,info:{attack_speed:\"3.5s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 60 block/s\"}]},range:15,Bullet:{type:hitscan,model:arrow,life:45},price:1500,sell_price:1300,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 저격총 ]\",bold:false,color:green},number:2,level:4,model:sniper,location:2,attack:100,attack_speed:60,info:{attack_speed:\"3s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:18,Bullet:{type:hitscan,model:arrow,life:54},price:4500,sell_price:3550,size:2f,translation:-1f,hitbox:1f}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_d45869dd_api_tower_get_stun_tower_execute(ServerCommandSource source) {
        _m_d45869dd_api_tower_get_stun_tower_executeReturn(source);
    }

    public static int _m_d45869dd_api_tower_get_stun_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 뇌전탑 ]",bold:false,color:green},minecraft:item_model=mace,minecraft:lore=[{text:"900G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 뇌전탑 ]",bold:false,color:green},number:1,level:0,model:stun-tower,location:1,attack:3,attack_speed:40,info:{attack_speed:"0.5/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:6,Bullet:{type:hitscan,model:arrow,speed:2,life:6,attribute:{chain:1,stun:10}},price:900,sell_price:450,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:"[ 뇌전탑 ]",bold:false,color:green},number:1,level:1,model:stun-tower,location:1,attack:3,attack_speed:30,info:{attack_speed:"0.66/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 20 block/s"}]},range:7,Bullet:{type:hitscan,model:arrow,speed:1.75,life:7,attribute:{chain:2,stun:13}},price:450,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 뇌전탑 ]",bold:false,color:green},number:1,level:2,model:stun-tower,location:1,attack:4,attack_speed:20,info:{attack_speed:"1.0/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 40 block/s"}]},range:8,Bullet:{type:hitscan,model:arrow,speed:2,life:8,attribute:{chain:3,stun:16}},price:1200,sell_price:1275,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 뇌전탑 ]",bold:false,color:green},number:1,level:3,model:stun-tower,location:1,attack:7,attack_speed:15,info:{attack_speed:"1.33/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 60 block/s"}]},range:9,Bullet:{type:hitscan,model:arrow,speed:3,life:9,attribute:{chain:4,stun:19}},price:3600,sell_price:3075,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 뇌전탑 ]",bold:false,color:green},number:1,level:4,model:stun-tower,location:1,attack:15,attack_speed:10,info:{attack_speed:"2/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:10,Bullet:{type:hitscan,model:arrow,speed:2.5,life:10,attribute:{chain:5,stun:22}},price:8900,sell_price:7525,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 뇌전탑 ]",bold:false,color:green},number:1,level:5,model:stun-tower,location:1,attack:35,attack_speed:5,info:{attack_speed:"4/s",weapon:[{text:"무장 정보:",color:white,bold:true},{text:"\n\s\s\s\s유형: 투사체"},{text:"\n\s\s\s\s투사체 속도: 100 block/s"}]},range:12,Bullet:{type:hitscan,model:arrow,speed:2.5,life:12,attribute:{chain:7,stun:25}},price:15000,sell_price:15025,size:2f,translation:-1f,hitbox:1f}}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 뇌전탑 ]\",bold:false,color:green},minecraft:item_model=mace,minecraft:lore=[{text:\"900G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 뇌전탑 ]\",bold:false,color:green},number:1,level:0,model:stun-tower,location:1,attack:3,attack_speed:40,info:{attack_speed:\"0.5/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:6,Bullet:{type:hitscan,model:arrow,speed:2,life:6,attribute:{chain:1,stun:10}},price:900,sell_price:450,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 뇌전탑 ]\",bold:false,color:green},number:1,level:1,model:stun-tower,location:1,attack:3,attack_speed:30,info:{attack_speed:\"0.66/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 20 block/s\"}]},range:7,Bullet:{type:hitscan,model:arrow,speed:1.75,life:7,attribute:{chain:2,stun:13}},price:450,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 뇌전탑 ]\",bold:false,color:green},number:1,level:2,model:stun-tower,location:1,attack:4,attack_speed:20,info:{attack_speed:\"1.0/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 40 block/s\"}]},range:8,Bullet:{type:hitscan,model:arrow,speed:2,life:8,attribute:{chain:3,stun:16}},price:1200,sell_price:1275,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 뇌전탑 ]\",bold:false,color:green},number:1,level:3,model:stun-tower,location:1,attack:7,attack_speed:15,info:{attack_speed:\"1.33/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 60 block/s\"}]},range:9,Bullet:{type:hitscan,model:arrow,speed:3,life:9,attribute:{chain:4,stun:19}},price:3600,sell_price:3075,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 뇌전탑 ]\",bold:false,color:green},number:1,level:4,model:stun-tower,location:1,attack:15,attack_speed:10,info:{attack_speed:\"2/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:10,Bullet:{type:hitscan,model:arrow,speed:2.5,life:10,attribute:{chain:5,stun:22}},price:8900,sell_price:7525,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 뇌전탑 ]\",bold:false,color:green},number:1,level:5,model:stun-tower,location:1,attack:35,attack_speed:5,info:{attack_speed:\"4/s\",weapon:[{text:\"무장 정보:\",color:white,bold:true},{text:\"\\n\\s\\s\\s\\s유형: 투사체\"},{text:\"\\n\\s\\s\\s\\s투사체 속도: 100 block/s\"}]},range:12,Bullet:{type:hitscan,model:arrow,speed:2.5,life:12,attribute:{chain:7,stun:25}},price:15000,sell_price:15025,size:2f,translation:-1f,hitbox:1f}}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_12f801ac_api_tower_get_weakness_tower_execute(ServerCommandSource source) {
        _m_12f801ac_api_tower_get_weakness_tower_executeReturn(source);
    }

    public static int _m_12f801ac_api_tower_get_weakness_tower_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // give @p copper_ingot[minecraft:item_name={text:"[ 취약 포탑 ]",bold:false,color:green},minecraft:item_model=suspicious_stew,minecraft:lore=[{text:"1800G",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:"[ 취약 포탑 ]",bold:false,color:green},number:1,level:0,model:weakness-tower,location:1,attack:0,attack_speed:10,info:{attack_speed:"5/s"},range:6,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:1,floor:{range:5,time:10,attack:0,attribute:{weakness:1}}}},price:1800,sell_price:187,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:"[ 취약 포탑 ]",bold:false,color:green},number:1,level:1,model:weakness-tower,location:1,attack:0,attack_speed:10,info:{attack_speed:"5/s"},range:7,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:2,floor:{range:6,time:10,attack:0,attribute:{weakness:2}}}},price:1200,sell_price:510,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 취약 포탑 ]",bold:false,color:green},number:1,level:2,model:weakness-tower,location:1,attack:1,attack_speed:10,info:{attack_speed:"5/s"},range:8,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:3,floor:{range:7,time:10,attack:1,attribute:{weakness:3}}}},price:6500,sell_price:1435,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 취약 포탑 ]",bold:false,color:green},number:1,level:3,model:weakness-tower,location:1,attack:1,attack_speed:10,info:{attack_speed:"5/s"},range:10,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:4,floor:{range:9,time:10,attack:1,attribute:{weakness:4}}}},price:12500,sell_price:3585,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:"[ 취약 포탑 ]",bold:false,color:green},number:1,level:4,model:weakness-tower,location:1,attack:2,attack_speed:10,info:{attack_speed:"5/s"},range:13,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:5,floor:{range:12,time:10,attack:2,attribute:{weakness:5}}}},price:30000,sell_price:8585,size:2f,translation:-1f,hitbox:1f}}}}}}]
        { net.minecraft.entity.Entity _gE = KfcGen.nearestPlayer(ctx, source.getPosition(), new String[0], new String[0], -1, -1); if (_gE != null) { if (_gE instanceof net.minecraft.server.network.ServerPlayerEntity _gp) KfcGen.giveItemString(source, _gp, "copper_ingot[minecraft:item_name={text:\"[ 취약 포탑 ]\",bold:false,color:green},minecraft:item_model=suspicious_stew,minecraft:lore=[{text:\"1800G\",color:yellow}],minecraft:custom_data={is_tower:1b,Tower_Status:{name:{text:\"[ 취약 포탑 ]\",bold:false,color:green},number:1,level:0,model:weakness-tower,location:1,attack:0,attack_speed:10,info:{attack_speed:\"5/s\"},range:6,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:1,floor:{range:5,time:10,attack:0,attribute:{weakness:1}}}},price:1800,sell_price:187,size:2f,translation:-1f,hitbox:1f,target_mode:1,limit:4},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 취약 포탑 ]\",bold:false,color:green},number:1,level:1,model:weakness-tower,location:1,attack:0,attack_speed:10,info:{attack_speed:\"5/s\"},range:7,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:2,floor:{range:6,time:10,attack:0,attribute:{weakness:2}}}},price:1200,sell_price:510,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 취약 포탑 ]\",bold:false,color:green},number:1,level:2,model:weakness-tower,location:1,attack:1,attack_speed:10,info:{attack_speed:\"5/s\"},range:8,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:3,floor:{range:7,time:10,attack:1,attribute:{weakness:3}}}},price:6500,sell_price:1435,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 취약 포탑 ]\",bold:false,color:green},number:1,level:3,model:weakness-tower,location:1,attack:1,attack_speed:10,info:{attack_speed:\"5/s\"},range:10,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:4,floor:{range:9,time:10,attack:1,attribute:{weakness:4}}}},price:12500,sell_price:3585,size:2f,translation:-1f,hitbox:1f},Tower_Upgrade:{Tower_Status:{name:{text:\"[ 취약 포탑 ]\",bold:false,color:green},number:1,level:4,model:weakness-tower,location:1,attack:2,attack_speed:10,info:{attack_speed:\"5/s\"},range:13,Bullet:{type:projectile,model:arrow,speed:0,life:0,attribute:{weakness:5,floor:{range:12,time:10,attack:2,attribute:{weakness:5}}}},price:30000,sell_price:8585,size:2f,translation:-1f,hitbox:1f}}}}}}]", 1); } }
        return 0;
    }

    public static void _m_54968aca_enemy_load_execute(ServerCommandSource source) {
        _m_54968aca_enemy_load_executeReturn(source);
    }

    public static int _m_54968aca_enemy_load_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard objectives add enemy.number dummy
        KfcGen.ensureObjective(sb, "enemy.number", "dummy");

        // scoreboard objectives add enemy.progress dummy
        KfcGen.ensureObjective(sb, "enemy.progress", "dummy");

        // scoreboard objectives add enemy.rotation dummy
        KfcGen.ensureObjective(sb, "enemy.rotation", "dummy");

        // scoreboard objectives add enemy.attribute.timer dummy
        KfcGen.ensureObjective(sb, "enemy.attribute.timer", "dummy");

        // scoreboard objectives add enemy.onGround dummy
        KfcGen.ensureObjective(sb, "enemy.onGround", "dummy");

        // scoreboard objectives add enemy.hp dummy
        KfcGen.ensureObjective(sb, "enemy.hp", "dummy");

        // scoreboard objectives add enemy.max_hp dummy
        KfcGen.ensureObjective(sb, "enemy.max_hp", "dummy");

        // scoreboard objectives add enemy.defence dummy
        KfcGen.ensureObjective(sb, "enemy.defence", "dummy");

        // scoreboard objectives add enemy.speed dummy
        KfcGen.ensureObjective(sb, "enemy.speed", "dummy");

        // scoreboard objectives add enemy.state.freeze dummy
        KfcGen.ensureObjective(sb, "enemy.state.freeze", "dummy");

        // scoreboard objectives add enemy.state.poison dummy
        KfcGen.ensureObjective(sb, "enemy.state.poison", "dummy");

        // scoreboard objectives add enemy.state.flame dummy
        KfcGen.ensureObjective(sb, "enemy.state.flame", "dummy");

        // scoreboard objectives add enemy.state.bleed dummy
        KfcGen.ensureObjective(sb, "enemy.state.bleed", "dummy");

        // scoreboard objectives add enemy.state.stun dummy
        KfcGen.ensureObjective(sb, "enemy.state.stun", "dummy");

        // scoreboard objectives add enemy.state.weakness dummy
        KfcGen.ensureObjective(sb, "enemy.state.weakness", "dummy");

        // scoreboard objectives add enemy.state.stun-immune dummy
        KfcGen.ensureObjective(sb, "enemy.state.stun-immune", "dummy");

        // scoreboard objectives add enemy.state.corruption dummy
        KfcGen.ensureObjective(sb, "enemy.state.corruption", "dummy");

        // scoreboard objectives add enemy.attribute.healing dummy
        KfcGen.ensureObjective(sb, "enemy.attribute.healing", "dummy");

        // scoreboard objectives add enemy.attribute.dealing dummy
        KfcGen.ensureObjective(sb, "enemy.attribute.dealing", "dummy");

        // scoreboard objectives add enemy.skill-trigger.hp dummy
        KfcGen.ensureObjective(sb, "enemy.skill-trigger.hp", "dummy");

        // scoreboard objectives add enemy.skill-trigger.timer dummy
        KfcGen.ensureObjective(sb, "enemy.skill-trigger.timer", "dummy");

        // scoreboard objectives add enemy.skill-trigger.timer-cooldown dummy
        KfcGen.ensureObjective(sb, "enemy.skill-trigger.timer-cooldown", "dummy");

        // scoreboard objectives add enemy.skill-trigger.mode dummy
        KfcGen.ensureObjective(sb, "enemy.skill-trigger.mode", "dummy");

        // scoreboard objectives add enemy.dark-field.timer dummy
        KfcGen.ensureObjective(sb, "enemy.dark-field.timer", "dummy");

        // scoreboard objectives add mob_gen.number dummy
        KfcGen.ensureObjective(sb, "mob_gen.number", "dummy");

        // scoreboard objectives add mob_gen.tick dummy
        KfcGen.ensureObjective(sb, "mob_gen.tick", "dummy");

        // scoreboard objectives add mob_gen.count dummy
        KfcGen.ensureObjective(sb, "mob_gen.count", "dummy");

        // team add noColison
        KfcGen.teamAdd(ctx, "noColison", null);
        return 0;
    }

    public static void _m_28da3f46_enemy_tick_execute(ServerCommandSource source) {
        _m_28da3f46_enemy_tick_executeReturn(source);
    }

    public static int _m_28da3f46_enemy_tick_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute as @e[tag=enemy.data] at @s run function enemy:attribute/main
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("enemy.data"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc2 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:attribute/main
            tdpack.buckets.Bucket5._m_59122ae4_enemy_attribute_main_execute(kfcSrc2);
        }

        // execute as @e[tag=enemy.core] at @s run function enemy:move/main
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("enemy.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc3 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:move/main
            tdpack.buckets.Bucket5._m_6c3b8400_enemy_move_main_execute(kfcSrc3);
        }

        // execute as @e[tag=enemy.text] run function enemy:animation/reload-text
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("enemy.text"))) continue;
            ServerCommandSource es = source.withEntity(e);
            // -> enemy:animation/reload-text
            tdpack.buckets.Bucket5._m_99732e02_enemy_animation_reload_text_execute(es);
        }

        // execute as @e[tag=mob-gen] at @s run function enemy:ability/mob_generator/main with entity @s data
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("mob-gen"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc4 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:ability/mob_generator/main
            tdpack.buckets.Bucket4._m_803ce71b_enemy_ability_mob_generator_main_execute(kfcSrc4, KfcGen.entityMacroArgs(e, "data"));
        }

        // execute as @e[type=marker,tag=enemy.dark-field] at @s run function enemy:ability/dark-field/tick
        for (Entity e : ctx.world.getEntitiesByType(EntityType.MARKER,
                en -> en.getCommandTags().contains("enemy.dark-field"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc5 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:ability/dark-field/tick
            tdpack.buckets.Bucket4._m_230ad324_enemy_ability_dark_field_tick_execute(kfcSrc5);
        }

        // function enemy:ability/tick
        // -> enemy:ability/tick
        tdpack.buckets.Bucket4._m_e55c9932_enemy_ability_tick_execute(source);
        return 0;
    }

    public static void _m_cb0aea95_enemy_ability_main_execute(ServerCommandSource source) {
        _m_cb0aea95_enemy_ability_main_executeReturn(source);
    }

    public static int _m_cb0aea95_enemy_ability_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.state.stun matches 1.. run return 0
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.state.stun", 1, Integer.MAX_VALUE)) {
            return 0;
        }

        // execute if entity @s[tag=enemy.skill-trigger.low-hp] if score @s enemy.hp <= @s enemy.skill-trigger.hp run function enemy:ability/low-hp/main with entity @s data
        if ((executor != null && executor.getCommandTags().contains("enemy.skill-trigger.low-hp"))) {
            if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", "<=", (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.hp")) {
                // -> enemy:ability/low-hp/main
                tdpack.buckets.Bucket4._m_37f6a253_enemy_ability_low_hp_main_execute(source, KfcGen.entityMacroArgs(executor, "data"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches ..100000 run scoreboard players remove @s enemy.skill-trigger.timer-cooldown 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 100000)) {
            if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", -(1));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches ..0 run function enemy:ability/timer/main with entity @s data
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0)) {
            // -> enemy:ability/timer/main
            tdpack.buckets.Bucket4._m_d0c46e8d_enemy_ability_timer_main_execute(source, KfcGen.entityMacroArgs(executor, "data"));
        }
        return 0;
    }

    public static void _m_e55c9932_enemy_ability_tick_execute(ServerCommandSource source) {
        _m_e55c9932_enemy_ability_tick_executeReturn(source);
    }

    public static int _m_e55c9932_enemy_ability_tick_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute as @e[tag=sculk-sonicwave.blast] at @s run function enemy:ability/timer/sculk-titan/sculk-sonicwave-blast
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("sculk-sonicwave.blast"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc2 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:ability/timer/sculk-titan/sculk-sonicwave-blast
            tdpack.buckets.Bucket5._m_b412dbb8_enemy_ability_timer_sculk_titan_sculk_sonicwave_blast_execute(kfcSrc2);
        }

        // execute as @e[tag=evoker-fangs] at @s run function enemy:ability/timer/evoker/evoker-fangs
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("evoker-fangs"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc3 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:ability/timer/evoker/evoker-fangs
            tdpack.buckets.Bucket4._m_c39eab8d_enemy_ability_timer_evoker_evoker_fangs_execute(kfcSrc3);
        }
        return 0;
    }

    public static void _m_230ad324_enemy_ability_dark_field_tick_execute(ServerCommandSource source) {
        _m_230ad324_enemy_ability_dark_field_tick_executeReturn(source);
    }

    public static int _m_230ad324_enemy_ability_dark_field_tick_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // particle minecraft:dust{color:[0f,0f,0f],scale:2f} ~ ~0.05 ~ 1 0 1 0 2 normal @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 0.05), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 1, 0, 1, 0, (int)(2), 0f, 0f, 0f, 2f, false, KfcGen.filterPlayers(ctx, _pv -> (true))); }

        // execute if entity @s[tag=enemy.dark-field.large] run particle minecraft:dust{color:[0f,0f,0f],scale:2f} ~ ~0.05 ~ 4.5 0 4.5 0 30 normal @a
        if ((executor != null && executor.getCommandTags().contains("enemy.dark-field.large"))) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 0.05), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 4.5, 0, 4.5, 0, (int)(30), 0f, 0f, 0f, 2f, false, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // scoreboard players remove @s enemy.dark-field.timer 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.dark-field.timer", -(1));

        // execute if score @s enemy.dark-field.timer matches ..0 run kill @s
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.dark-field.timer", Integer.MIN_VALUE, 0)) {
            if (executor != null) KfcGen.killEntity(executor);
        }
        return 0;
    }

    public static void _m_add5a37e_enemy_ability_death_charged_creeper_execute(ServerCommandSource source) {
        _m_add5a37e_enemy_ability_death_charged_creeper_executeReturn(source);
    }

    public static int _m_add5a37e_enemy_ability_death_charged_creeper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set @e[tag=tower.data,distance=..10] tower.state.stun 90
        for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, 10)) {
            KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 90);
        }

        // particle minecraft:explosion_emitter ~ ~ ~ 0 0 0 0 1 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion_emitter", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }

        // playsound entity.generic.explode record @a ~ ~ ~ 1.0 1.0
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "entity.generic.explode", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
        }
        return 0;
    }

    public static void _m_8f6e0b88_enemy_ability_death_creeper_execute(ServerCommandSource source) {
        _m_8f6e0b88_enemy_ability_death_creeper_executeReturn(source);
    }

    public static int _m_8f6e0b88_enemy_ability_death_creeper_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set @e[tag=tower.data,distance=..5] tower.state.stun 60
        for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, 5)) {
            KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 60);
        }

        // particle minecraft:explosion ~ ~ ~ 1 1 1 0 10 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 1, 1, 1, 0, (int)(10), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }

        // playsound entity.generic.explode record @a ~ ~ ~ 1.0 1.0
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "entity.generic.explode", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
        }
        return 0;
    }

    public static void _m_b68ac934_enemy_ability_death_dark_attribute_execute(ServerCommandSource source) {
        _m_b68ac934_enemy_ability_death_dark_attribute_executeReturn(source);
    }

    public static int _m_b68ac934_enemy_ability_death_dark_attribute_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~ ~ {Tags:[enemy.dark-field,enemy.dark-field.new]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[enemy.dark-field,enemy.dark-field.new]}"); }

        // scoreboard players set @n[type=marker,tag=enemy.dark-field.new,distance=..1,sort=nearest,limit=1] enemy.dark-field.timer 100
        net.minecraft.entity.Entity _sel0 = KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"enemy.dark-field.new"}, new String[0], -1, 1);
        { net.minecraft.entity.Entity _t = _sel0; if (_t != null) KfcGen.setScore(sb, _t.getNameForScoreboard(), "enemy.dark-field.timer", 100); }

        // tag @n[type=marker,tag=enemy.dark-field.new,distance=..1,sort=nearest,limit=1] remove enemy.dark-field.new
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"enemy.dark-field.new"}, new String[0], -1, 1); if (_t != null) _t.removeCommandTag("enemy.dark-field.new"); }
        return 0;
    }

    public static void _m_8f0342be_enemy_ability_death_dark_headbomb_execute(ServerCommandSource source) {
        _m_8f0342be_enemy_ability_death_dark_headbomb_executeReturn(source);
    }

    public static int _m_8f0342be_enemy_ability_death_dark_headbomb_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players set @e[tag=tower.data,distance=..5] tower.state.stun 60
        for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, 5)) {
            KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 60);
        }

        // summon marker ~ ~ ~ {Tags:[enemy.dark-field,enemy.dark-field.large,enemy.dark-field.new]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[enemy.dark-field,enemy.dark-field.large,enemy.dark-field.new]}"); }

        // scoreboard players set @n[type=marker,tag=enemy.dark-field.new,distance=..1,sort=nearest,limit=1] enemy.dark-field.timer 100
        net.minecraft.entity.Entity _sel0 = KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"enemy.dark-field.new"}, new String[0], -1, 1);
        { net.minecraft.entity.Entity _t = _sel0; if (_t != null) KfcGen.setScore(sb, _t.getNameForScoreboard(), "enemy.dark-field.timer", 100); }

        // tag @n[type=marker,tag=enemy.dark-field.new,distance=..1,sort=nearest,limit=1] remove enemy.dark-field.new
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"enemy.dark-field.new"}, new String[0], -1, 1); if (_t != null) _t.removeCommandTag("enemy.dark-field.new"); }

        // particle minecraft:explosion ~ ~ ~ 1 1 1 0 10 force @a
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 1, 1, 1, 0, (int)(10), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }

        // playsound entity.generic.explode record @a ~ ~ ~ 1.0 1.0
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "entity.generic.explode", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
        }
        return 0;
    }

    public static void _m_8ebf2e22_enemy_ability_death_endermite_execute(ServerCommandSource source) {
        _m_8ebf2e22_enemy_ability_death_endermite_executeReturn(source);
    }

    public static int _m_8ebf2e22_enemy_ability_death_endermite_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // tag @s add map.spawn_point
        if (executor != null) executor.addCommandTag("map.spawn_point");

        // function enemy:spawn/summon/main {model:endermite-egg}
        // -> enemy:spawn/summon/main
        tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "endermite-egg"));

        // tag @s remove map.spawn_point
        if (executor != null) executor.removeCommandTag("map.spawn_point");
        return 0;
    }

    public static void _m_b79fe92e_enemy_ability_death_endermite_egg_execute(ServerCommandSource source) {
        _m_b79fe92e_enemy_ability_death_endermite_egg_executeReturn(source);
    }

    public static int _m_b79fe92e_enemy_ability_death_endermite_egg_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~ ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:5,model:endermite-baby}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:5,model:endermite-baby}}"); }

        // data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)), "Rotation", _v); }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.tick", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.count", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_count"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.number", (int)(_stv));
        } }

        // execute store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "enemy.progress", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress")); }

        // tag @n[tag=mob-gen,tag=not-allocated] remove not-allocated
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("not-allocated"); }
        return 0;
    }

    public static void _m_d63ad9bb_enemy_ability_death_indura_zombie_execute(ServerCommandSource source) {
        _m_d63ad9bb_enemy_ability_death_indura_zombie_executeReturn(source);
    }

    public static int _m_d63ad9bb_enemy_ability_death_indura_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // tag @s add map.spawn_point
        if (executor != null) executor.addCommandTag("map.spawn_point");

        // function enemy:spawn/summon/main {model:indura-stone}
        // -> enemy:spawn/summon/main
        tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "indura-stone"));

        // tag @s remove map.spawn_point
        if (executor != null) executor.removeCommandTag("map.spawn_point");
        return 0;
    }

    public static void _m_f4312e18_enemy_ability_death_revive_zombie_execute(ServerCommandSource source) {
        _m_f4312e18_enemy_ability_death_revive_zombie_executeReturn(source);
    }

    public static int _m_f4312e18_enemy_ability_death_revive_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // tag @s add map.spawn_point
        if (executor != null) executor.addCommandTag("map.spawn_point");

        // function enemy:spawn/summon/main {model:revived-zombie}
        // -> enemy:spawn/summon/main
        tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "revived-zombie"));

        // tag @s remove map.spawn_point
        if (executor != null) executor.removeCommandTag("map.spawn_point");
        return 0;
    }

    public static void _m_07241b4c_enemy_ability_death_silverfish_execute(ServerCommandSource source) {
        _m_07241b4c_enemy_ability_death_silverfish_executeReturn(source);
    }

    public static int _m_07241b4c_enemy_ability_death_silverfish_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // tag @s add map.spawn_point
        if (executor != null) executor.addCommandTag("map.spawn_point");

        // function enemy:spawn/summon/main {model:silverfish-egg}
        // -> enemy:spawn/summon/main
        tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "silverfish-egg"));

        // tag @s remove map.spawn_point
        if (executor != null) executor.removeCommandTag("map.spawn_point");
        return 0;
    }

    public static void _m_1ce5b19e_enemy_ability_death_silverfish_egg_execute(ServerCommandSource source) {
        _m_1ce5b19e_enemy_ability_death_silverfish_egg_executeReturn(source);
    }

    public static int _m_1ce5b19e_enemy_ability_death_silverfish_egg_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~ ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:5,spawn_count:5,model:silverfish-baby}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[mob-gen,not-allocated],data:{spawn_cooldown:5,spawn_count:5,model:silverfish-baby}}"); }

        // data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)), "Rotation", _v); }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.tick", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.count", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_count"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.number", (int)(_stv));
        } }

        // execute store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "enemy.progress", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress")); }

        // tag @n[tag=mob-gen,tag=not-allocated] remove not-allocated
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("not-allocated"); }
        return 0;
    }

    public static void _m_a89b1407_enemy_ability_death_split_zombie_execute(ServerCommandSource source) {
        _m_a89b1407_enemy_ability_death_split_zombie_executeReturn(source);
    }

    public static int _m_a89b1407_enemy_ability_death_split_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~ ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:5,spawn_count:2,model:split-zombie-splits}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[mob-gen,not-allocated],data:{spawn_cooldown:5,spawn_count:2,model:split-zombie-splits}}"); }

        // data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)), "Rotation", _v); }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.tick", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.count", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_count"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.number", (int)(_stv));
        } }

        // execute store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "enemy.progress", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress")); }

        // tag @n[tag=mob-gen,tag=not-allocated] remove not-allocated
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("not-allocated"); }
        return 0;
    }

    public static void _m_dcf8d850_enemy_ability_low_hp_darkness_soron_execute(ServerCommandSource source) {
        _m_dcf8d850_enemy_ability_low_hp_darkness_soron_executeReturn(source);
    }

    public static int _m_dcf8d850_enemy_ability_low_hp_darkness_soron_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // tag @s remove enemy.skill-trigger.low-hp
        if (executor != null) executor.removeCommandTag("enemy.skill-trigger.low-hp");

        // tag @s remove enemy.skill-loop.1
        if (executor != null) executor.removeCommandTag("enemy.skill-loop.1");

        // tag @s remove enemy.skill-loop.2
        if (executor != null) executor.removeCommandTag("enemy.skill-loop.2");

        // tag @s remove enemy.skill-loop.3
        if (executor != null) executor.removeCommandTag("enemy.skill-loop.3");

        // tag @s remove enemy.skill-loop.5
        if (executor != null) executor.removeCommandTag("enemy.skill-loop.5");

        // tag @s add enemy.skill-loop.4
        if (executor != null) executor.addCommandTag("enemy.skill-loop.4");

        // scoreboard players set @s enemy.skill-trigger.timer-cooldown 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0);
        return 0;
    }

    public static void _m_defc045f_enemy_ability_low_hp_illusioner_execute(ServerCommandSource source) {
        _m_defc045f_enemy_ability_low_hp_illusioner_executeReturn(source);
    }

    public static int _m_defc045f_enemy_ability_low_hp_illusioner_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~ ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:3,model:illusioner-illusion}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:3,model:illusioner-illusion}}"); }

        // data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)), "Rotation", _v); }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.tick", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.count", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_count"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.number", (int)(_stv));
        } }

        // execute store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "enemy.progress", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress")); }

        // execute as @n[tag=mob-gen,tag=not-allocated] run tag @s remove not-allocated
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        } }

        // scoreboard players add @s enemy.hp 500
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.hp", 500);

        // data modify entity @s data.speed set value 0.2
        if (executor != null) KfcGen.entityPutSnbt(executor, "data.speed", "0.2");

        // tp @n[tag=map.spawn_point]
        {
            net.minecraft.entity.Entity _tpDest = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.spawn_point"}, new String[0], -1, -1, _ee -> (true));
        if (executor != null) { if (_tpDest != null) KfcGen.teleportToEntity(executor, _tpDest); }
        }

        // scoreboard players set @s enemy.progress 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.progress", 0);

        // scoreboard players set @s enemy.skill-trigger.hp -1
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.hp", -1);
        return 0;
    }

    public static void _m_37f6a253_enemy_ability_low_hp_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_37f6a253_enemy_ability_low_hp_main_executeReturn(source, macroArgs);
    }

    public static int _m_37f6a253_enemy_ability_low_hp_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // $function enemy:ability/low-hp/$(id)
        // -> enemy:ability/low-hp/macroArgs.get("id")
        switch (macroArgs.get("id")) {
            case "darkness-soron": tdpack.buckets.Bucket4._m_dcf8d850_enemy_ability_low_hp_darkness_soron_execute(source); break;
            case "illusioner": tdpack.buckets.Bucket4._m_defc045f_enemy_ability_low_hp_illusioner_execute(source); break;
            case "raid-guardian": tdpack.buckets.Bucket4._m_52c32b74_enemy_ability_low_hp_raid_guardian_execute(source); break;
            case "raid-lord": tdpack.buckets.Bucket4._m_46a76157_enemy_ability_low_hp_raid_lord_execute(source); break;
            case "shiled-zombie": tdpack.buckets.Bucket4._m_7051b4c5_enemy_ability_low_hp_shiled_zombie_execute(source); break;
            case "witch": tdpack.buckets.Bucket4._m_fb49c2ce_enemy_ability_low_hp_witch_execute(source); break;
            default: break;
        }
        return 0;
    }

    public static void _m_52c32b74_enemy_ability_low_hp_raid_guardian_execute(ServerCommandSource source) {
        _m_52c32b74_enemy_ability_low_hp_raid_guardian_executeReturn(source);
    }

    public static int _m_52c32b74_enemy_ability_low_hp_raid_guardian_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // data modify entity @s data.speed set value 0
        if (executor != null) KfcGen.entityPutSnbt(executor, "data.speed", "0");

        // data modify entity @s data.defence set value 75
        if (executor != null) KfcGen.entityPutSnbt(executor, "data.defence", "75");

        // scoreboard players set @s enemy.defence 75
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.defence", 75);

        // scoreboard players set @s enemy.skill-trigger.hp -1
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.hp", -1);

        // scoreboard players set @s enemy.skill-trigger.timer-cooldown 40
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 40);
        return 0;
    }

    public static void _m_46a76157_enemy_ability_low_hp_raid_lord_execute(ServerCommandSource source) {
        _m_46a76157_enemy_ability_low_hp_raid_lord_executeReturn(source);
    }

    public static int _m_46a76157_enemy_ability_low_hp_raid_lord_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // data modify entity @s data.speed set value 12
        if (executor != null) KfcGen.entityPutSnbt(executor, "data.speed", "12");
        return 0;
    }

    public static void _m_7051b4c5_enemy_ability_low_hp_shiled_zombie_execute(ServerCommandSource source) {
        _m_7051b4c5_enemy_ability_low_hp_shiled_zombie_executeReturn(source);
    }

    public static int _m_7051b4c5_enemy_ability_low_hp_shiled_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data remove entity @s equipment.mainhand
        if (executor != null) KfcGen.entityRemovePath(executor, "equipment.mainhand");

        // data modify entity @s data.speed set value 0.25
        if (executor != null) KfcGen.entityPutSnbt(executor, "data.speed", "0.25");

        // execute at @s run playsound item.shield.break record @a ~ ~ ~ 2.0 1.0
        {
            ServerCommandSource kfcSrc1 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "item.shield.break", "record", new net.minecraft.util.math.Vec3d(kfcSrc1.getPosition().x, kfcSrc1.getPosition().y, kfcSrc1.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc1.getPosition().x, kfcSrc1.getPosition().y, kfcSrc1.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc1.getPosition().x, kfcSrc1.getPosition().y, kfcSrc1.getPosition().z).z, 2.0f, 1.0f);
            }
        }

        // data modify entity @s data.defence set value 0
        if (executor != null) KfcGen.entityPutSnbt(executor, "data.defence", "0");

        // scoreboard players set @s enemy.defence 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.defence", 0);

        // tag @s remove enemy.immune.stun
        if (executor != null) executor.removeCommandTag("enemy.immune.stun");

        // scoreboard players set @s enemy.skill-trigger.hp -1
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.hp", -1);
        return 0;
    }

    public static void _m_fb49c2ce_enemy_ability_low_hp_witch_execute(ServerCommandSource source) {
        _m_fb49c2ce_enemy_ability_low_hp_witch_executeReturn(source);
    }

    public static int _m_fb49c2ce_enemy_ability_low_hp_witch_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score #temp enemy.number run random value 1..3
        KfcGen.setScore(sb, "#temp", "enemy.number", ctx.world.getRandom().nextBetween(1, 3));

        // playsound entity.witch.drink record @a ~ ~ ~ 2.0 1.0
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "entity.witch.drink", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 2.0f, 1.0f);
        }

        // execute if score #temp enemy.number matches 1 run scoreboard players set @s enemy.attribute.healing 5
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.number", 1, 1)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.attribute.healing", 5);
        }

        // execute if score #temp enemy.number matches 2 unless entity @s[tag=enemy.attribute.heavy] run scoreboard players operation @s enemy.hp += @s enemy.hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.number", 2, 2)) {
            if (!((executor != null && executor.getCommandTags().contains("enemy.attribute.heavy")))) {
                if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.hp", "+=", executor.getNameForScoreboard(), "enemy.hp");
            }
        }

        // execute if score #temp enemy.number matches 2 unless entity @s[tag=enemy.attribute.heavy] run scoreboard players operation @s enemy.max_hp += @s enemy.max_hp
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.number", 2, 2)) {
            if (!((executor != null && executor.getCommandTags().contains("enemy.attribute.heavy")))) {
                if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.max_hp", "+=", executor.getNameForScoreboard(), "enemy.max_hp");
            }
        }

        // execute if score #temp enemy.number matches 2 unless entity @s[tag=enemy.attribute.heavy] run tag @s add enemy.attribute.heavy
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.number", 2, 2)) {
            if (!((executor != null && executor.getCommandTags().contains("enemy.attribute.heavy")))) {
                if (executor != null) executor.addCommandTag("enemy.attribute.heavy");
            }
        }

        // execute if score #temp enemy.number matches 3 unless entity @s[tag=enemy.attribute.speed] run data modify entity @s data.speed set value 0.18
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.number", 3, 3)) {
            if (!((executor != null && executor.getCommandTags().contains("enemy.attribute.speed")))) {
                if (executor != null) KfcGen.entityPutSnbt(executor, "data.speed", "0.18");
            }
        }

        // execute if score #temp enemy.number matches 3 unless entity @s[tag=enemy.attribute.speed] run tag @s add enemy.attribute.speed
        if (KfcGen.scoreMatches(sb, "#temp", "enemy.number", 3, 3)) {
            if (!((executor != null && executor.getCommandTags().contains("enemy.attribute.speed")))) {
                if (executor != null) executor.addCommandTag("enemy.attribute.speed");
            }
        }

        // scoreboard players set @s enemy.skill-trigger.hp -1
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.hp", -1);

        // scoreboard players set @s enemy.skill-trigger.timer-cooldown 80
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 80);
        return 0;
    }

    public static void _m_803ce71b_enemy_ability_mob_generator_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_803ce71b_enemy_ability_mob_generator_main_executeReturn(source, macroArgs);
    }

    public static int _m_803ce71b_enemy_ability_mob_generator_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players remove @s mob_gen.count 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "mob_gen.count", -(1));

        // $function enemy:ability/mob_generator/particles/$(model)
        // -> enemy:ability/mob_generator/particles/macroArgs.get("model")
        switch (macroArgs.get("model")) {
            case "endermite-baby": tdpack.buckets.Bucket4._m_95d6273c_enemy_ability_mob_generator_particles_endermite_baby_execute(source); break;
            case "illusioner-illusion": tdpack.buckets.Bucket4._m_8bdf25f4_enemy_ability_mob_generator_particles_illusioner_illusion_execute(source); break;
            case "silverfish-baby": tdpack.buckets.Bucket4._m_e81dde1e_enemy_ability_mob_generator_particles_silverfish_baby_execute(source); break;
            case "skulk-zombie": tdpack.buckets.Bucket4._m_1d121753_enemy_ability_mob_generator_particles_skulk_zombie_execute(source); break;
            case "split-zombie-splits": tdpack.buckets.Bucket4._m_65b6d901_enemy_ability_mob_generator_particles_split_zombie_splits_execute(source); break;
            case "vex": tdpack.buckets.Bucket4._m_eac290e0_enemy_ability_mob_generator_particles_vex_execute(source); break;
            default: break;
        }

        // execute if score @s mob_gen.count matches 0 run tag @s add map.spawn_point
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "mob_gen.count", 0, 0)) {
            if (executor != null) executor.addCommandTag("map.spawn_point");
        }

        // $execute if score @s mob_gen.count matches 0 run function enemy:spawn/summon/main {model:$(model)}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "mob_gen.count", 0, 0)) {
            // -> enemy:spawn/summon/main
            if (!KfcGen.macroEmpty(macroArgs.get("model"))) tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", macroArgs.get("model")));
        }

        // execute if score @s mob_gen.count matches 0 run tag @s remove map.spawn_point
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "mob_gen.count", 0, 0)) {
            if (executor != null) executor.removeCommandTag("map.spawn_point");
        }

        // execute if score @s mob_gen.count matches 0 run scoreboard players remove @s mob_gen.number 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "mob_gen.count", 0, 0)) {
            if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "mob_gen.number", -(1));
        }

        // execute if score @s mob_gen.count matches 0 run scoreboard players operation @s mob_gen.count = @s mob_gen.tick
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "mob_gen.count", 0, 0)) {
            if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "mob_gen.count", "=", executor.getNameForScoreboard(), "mob_gen.tick");
        }

        // execute if score @s mob_gen.number matches ..0 run kill @s
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "mob_gen.number", Integer.MIN_VALUE, 0)) {
            if (executor != null) KfcGen.killEntity(executor);
        }
        return 0;
    }

    public static void _m_95d6273c_enemy_ability_mob_generator_particles_endermite_baby_execute(ServerCommandSource source) {
        _m_95d6273c_enemy_ability_mob_generator_particles_endermite_baby_executeReturn(source);
    }

    public static int _m_95d6273c_enemy_ability_mob_generator_particles_endermite_baby_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // particle dust{color:[0.400,0.100,0.400],scale:1} ~ ~0.5 ~ 0.2 0.2 0.2 0 15 force
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 0.5), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.2, 0.2, 0.2, 0, (int)(15), 0.400f, 0.100f, 0.400f, 1f, true, null); }

        // playsound minecraft:block.fire.extinguish record @a ~ ~ ~ 0.2 1
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "minecraft:block.fire.extinguish", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.0f);
        }
        return 0;
    }

    public static void _m_8bdf25f4_enemy_ability_mob_generator_particles_illusioner_illusion_execute(ServerCommandSource source) {
        _m_8bdf25f4_enemy_ability_mob_generator_particles_illusioner_illusion_executeReturn(source);
    }

    public static int _m_8bdf25f4_enemy_ability_mob_generator_particles_illusioner_illusion_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // particle dust{color:[0.000,0.000,0.400],scale:1} ~ ~1 ~ 0.3 0.6 0.3 0 15 force
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.3, 0.6, 0.3, 0, (int)(15), 0.000f, 0.000f, 0.400f, 1f, true, null); }

        // playsound minecraft:block.fire.extinguish record @a ~ ~ ~ 0.4 0.5
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "minecraft:block.fire.extinguish", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.5f);
        }
        return 0;
    }

    public static void _m_e81dde1e_enemy_ability_mob_generator_particles_silverfish_baby_execute(ServerCommandSource source) {
        _m_e81dde1e_enemy_ability_mob_generator_particles_silverfish_baby_executeReturn(source);
    }

    public static int _m_e81dde1e_enemy_ability_mob_generator_particles_silverfish_baby_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // particle dust{color:[0.100,0.100,0.100],scale:1} ~ ~0.5 ~ 0.2 0.2 0.2 0 15 force
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 0.5), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.2, 0.2, 0.2, 0, (int)(15), 0.100f, 0.100f, 0.100f, 1f, true, null); }

        // playsound minecraft:block.fire.extinguish record @a ~ ~ ~ 0.2 1
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "minecraft:block.fire.extinguish", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.0f);
        }
        return 0;
    }

    public static void _m_1d121753_enemy_ability_mob_generator_particles_skulk_zombie_execute(ServerCommandSource source) {
        _m_1d121753_enemy_ability_mob_generator_particles_skulk_zombie_executeReturn(source);
    }

    public static int _m_1d121753_enemy_ability_mob_generator_particles_skulk_zombie_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // particle dust{color:[0.000,0.400,0.000],scale:1} ~ ~1 ~ 0.3 0.6 0.3 0 15 force
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.3, 0.6, 0.3, 0, (int)(15), 0.000f, 0.400f, 0.000f, 1f, true, null); }

        // playsound minecraft:block.fire.extinguish record @a ~ ~ ~ 0.4 0.5
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "minecraft:block.fire.extinguish", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.5f);
        }
        return 0;
    }

    public static void _m_65b6d901_enemy_ability_mob_generator_particles_split_zombie_splits_execute(ServerCommandSource source) {
        _m_65b6d901_enemy_ability_mob_generator_particles_split_zombie_splits_executeReturn(source);
    }

    public static int _m_65b6d901_enemy_ability_mob_generator_particles_split_zombie_splits_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // particle dust{color:[0.000,0.431,0.145],scale:1} ~ ~0.5 ~ 0.2 0.2 0.2 0 15 force
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 0.5), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.2, 0.2, 0.2, 0, (int)(15), 0.000f, 0.431f, 0.145f, 1f, true, null); }

        // playsound minecraft:block.fire.extinguish record @a ~ ~ ~ 0.2 1
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "minecraft:block.fire.extinguish", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.2f, 1.0f);
        }
        return 0;
    }

    public static void _m_eac290e0_enemy_ability_mob_generator_particles_vex_execute(ServerCommandSource source) {
        _m_eac290e0_enemy_ability_mob_generator_particles_vex_executeReturn(source);
    }

    public static int _m_eac290e0_enemy_ability_mob_generator_particles_vex_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // particle dust{color:[0.200,0.200,0.300],scale:1} ~ ~1 ~ 0.1 0.2 0.1 0 15 force
        { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0.1, 0.2, 0.1, 0, (int)(15), 0.200f, 0.200f, 0.300f, 1f, true, null); }

        // playsound minecraft:block.fire.extinguish record @a ~ ~ ~ 0.4 0.5
        for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
            KfcGen.playSound(_ps, "minecraft:block.fire.extinguish", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.4f, 0.5f);
        }
        return 0;
    }

    public static void _m_3dbdff8b_enemy_ability_timer_breeze_execute(ServerCommandSource source) {
        _m_3dbdff8b_enemy_ability_timer_breeze_executeReturn(source);
    }

    public static int _m_3dbdff8b_enemy_ability_timer_breeze_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute at @s run function enemy:ability/timer/breeze/main
        {
            ServerCommandSource kfcSrc4 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/breeze/main
            tdpack.buckets.Bucket4._m_6aa95233_enemy_ability_timer_breeze_main_execute(kfcSrc4);
        }
        return 0;
    }

    public static void _m_e68efbcb_enemy_ability_timer_dark_dash_zombie_execute(ServerCommandSource source) {
        _m_e68efbcb_enemy_ability_timer_dark_dash_zombie_executeReturn(source);
    }

    public static int _m_e68efbcb_enemy_ability_timer_dark_dash_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute at @s run function enemy:ability/timer/dark-dash-zombie/main
        {
            ServerCommandSource kfcSrc20 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/dark-dash-zombie/main
            tdpack.buckets.Bucket4._m_decb2b69_enemy_ability_timer_dark_dash_zombie_main_execute(kfcSrc20);
        }
        return 0;
    }

    public static void _m_e5b45c75_enemy_ability_timer_dark_guardian_execute(ServerCommandSource source) {
        _m_e5b45c75_enemy_ability_timer_dark_guardian_executeReturn(source);
    }

    public static int _m_e5b45c75_enemy_ability_timer_dark_guardian_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 unless entity @n[tag=tower.data,scores={tower.y=-60}] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            if (!(KfcGen.anyEntityWhere(ctx, _se -> (_se.getCommandTags().contains("tower.data") && KfcGen.scoreMatches(sb, _se.getNameForScoreboard(), "tower.y", -60, -60))))) {
                if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 10);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run data modify entity @s data.last_rotation set from entity @s Rotation
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "data.last_rotation", _v); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run rotate @s facing entity @n[tag=tower.data,scores={tower.y=-60},sort=nearest,limit=1] eyes
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.entity.Entity _rt = executor, _fe = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (KfcGen.scoreMatches(sb, _ee.getNameForScoreboard(), "tower.y", -60, -60))); if (_rt != null && _fe != null) KfcGen.rotateToFaceEntity(_rt, _fe, true); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.wither.shoot record @a ~ ~ ~ 1.0 0.8
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.wither.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.8f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^1 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc25 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc26 = kfcSrc25.withPosition(KfcGen.localOffset(kfcSrc25.getPosition(), kfcSrc25.getRotation(), 0.0, 0.0, 1.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc26.getPosition().x, kfcSrc26.getPosition().y, kfcSrc26.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^2 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc27 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc28 = kfcSrc27.withPosition(KfcGen.localOffset(kfcSrc27.getPosition(), kfcSrc27.getRotation(), 0.0, 0.0, 2.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc28.getPosition().x, kfcSrc28.getPosition().y, kfcSrc28.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^3 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc29 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc30 = kfcSrc29.withPosition(KfcGen.localOffset(kfcSrc29.getPosition(), kfcSrc29.getRotation(), 0.0, 0.0, 3.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc30.getPosition().x, kfcSrc30.getPosition().y, kfcSrc30.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^4 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc31 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc32 = kfcSrc31.withPosition(KfcGen.localOffset(kfcSrc31.getPosition(), kfcSrc31.getRotation(), 0.0, 0.0, 4.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc32.getPosition().x, kfcSrc32.getPosition().y, kfcSrc32.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^5 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc33 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc34 = kfcSrc33.withPosition(KfcGen.localOffset(kfcSrc33.getPosition(), kfcSrc33.getRotation(), 0.0, 0.0, 5.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc34.getPosition().x, kfcSrc34.getPosition().y, kfcSrc34.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^6 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc35 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc36 = kfcSrc35.withPosition(KfcGen.localOffset(kfcSrc35.getPosition(), kfcSrc35.getRotation(), 0.0, 0.0, 6.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc36.getPosition().x, kfcSrc36.getPosition().y, kfcSrc36.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^7 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc37 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc38 = kfcSrc37.withPosition(KfcGen.localOffset(kfcSrc37.getPosition(), kfcSrc37.getRotation(), 0.0, 0.0, 7.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc38.getPosition().x, kfcSrc38.getPosition().y, kfcSrc38.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^8 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc39 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc40 = kfcSrc39.withPosition(KfcGen.localOffset(kfcSrc39.getPosition(), kfcSrc39.getRotation(), 0.0, 0.0, 8.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc40.getPosition().x, kfcSrc40.getPosition().y, kfcSrc40.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^9 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc41 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc42 = kfcSrc41.withPosition(KfcGen.localOffset(kfcSrc41.getPosition(), kfcSrc41.getRotation(), 0.0, 0.0, 9.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc42.getPosition().x, kfcSrc42.getPosition().y, kfcSrc42.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^10 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
        {
            ServerCommandSource kfcSrc43 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc44 = kfcSrc43.withPosition(KfcGen.localOffset(kfcSrc43.getPosition(), kfcSrc43.getRotation(), 0.0, 0.0, 10.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc44.getPosition().x, kfcSrc44.getPosition().y, kfcSrc44.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.6, 0.2, 0.6, 0, (int)(8), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^1 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc45 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc46 = kfcSrc45.withPosition(KfcGen.localOffset(kfcSrc45.getPosition(), kfcSrc45.getRotation(), 0.0, 0.0, 1.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc46.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^2 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc47 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc48 = kfcSrc47.withPosition(KfcGen.localOffset(kfcSrc47.getPosition(), kfcSrc47.getRotation(), 0.0, 0.0, 2.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc48.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^3 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc49 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc50 = kfcSrc49.withPosition(KfcGen.localOffset(kfcSrc49.getPosition(), kfcSrc49.getRotation(), 0.0, 0.0, 3.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc50.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^4 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc51 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc52 = kfcSrc51.withPosition(KfcGen.localOffset(kfcSrc51.getPosition(), kfcSrc51.getRotation(), 0.0, 0.0, 4.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc52.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^5 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc53 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc54 = kfcSrc53.withPosition(KfcGen.localOffset(kfcSrc53.getPosition(), kfcSrc53.getRotation(), 0.0, 0.0, 5.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc54.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^6 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc55 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc56 = kfcSrc55.withPosition(KfcGen.localOffset(kfcSrc55.getPosition(), kfcSrc55.getRotation(), 0.0, 0.0, 6.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc56.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^7 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc57 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc58 = kfcSrc57.withPosition(KfcGen.localOffset(kfcSrc57.getPosition(), kfcSrc57.getRotation(), 0.0, 0.0, 7.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc58.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^8 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc59 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc60 = kfcSrc59.withPosition(KfcGen.localOffset(kfcSrc59.getPosition(), kfcSrc59.getRotation(), 0.0, 0.0, 8.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc60.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^9 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc61 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc62 = kfcSrc61.withPosition(KfcGen.localOffset(kfcSrc61.getPosition(), kfcSrc61.getRotation(), 0.0, 0.0, 9.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc62.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^10 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
        {
            ServerCommandSource kfcSrc63 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc64 = kfcSrc63.withPosition(KfcGen.localOffset(kfcSrc63.getPosition(), kfcSrc63.getRotation(), 0.0, 0.0, 10.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc64.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run data modify entity @s Rotation set from entity @s data.last_rotation
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data.last_rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "Rotation", _v); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 300
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 300);
        }
        return 0;
    }

    public static void _m_e212f8e2_enemy_ability_timer_dark_knight_execute(ServerCommandSource source) {
        _m_e212f8e2_enemy_ability_timer_dark_knight_executeReturn(source);
    }

    public static int _m_e212f8e2_enemy_ability_timer_dark_knight_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 unless entity @n[tag=tower.data,scores={tower.y=-60}] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            if (!(KfcGen.anyEntityWhere(ctx, _se -> (_se.getCommandTags().contains("tower.data") && KfcGen.scoreMatches(sb, _se.getNameForScoreboard(), "tower.y", -60, -60))))) {
                if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 10);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run data modify entity @s data.last_rotation set from entity @s Rotation
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "data.last_rotation", _v); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run rotate @s facing entity @n[tag=tower.data,scores={tower.y=-60},sort=nearest,limit=1] eyes
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.entity.Entity _rt = executor, _fe = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (KfcGen.scoreMatches(sb, _ee.getNameForScoreboard(), "tower.y", -60, -60))); if (_rt != null && _fe != null) KfcGen.rotateToFaceEntity(_rt, _fe, true); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.zombie.ambient record @a ~ ~ ~ 1.0 0.6
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.zombie.ambient", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.6f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 as @n[tag=tower.data,scores={tower.y=-60},sort=nearest,limit=1] at @s run particle minecraft:explosion ~ ~ ~ 2.5 0.2 2.5 0 30 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (KfcGen.scoreMatches(sb, _ee.getNameForScoreboard(), "tower.y", -60, -60)));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc65 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc65.getPosition().x, kfcSrc65.getPosition().y, kfcSrc65.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 2.5, 0.2, 2.5, 0, (int)(30), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            } }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 as @n[tag=tower.data,scores={tower.y=-60},sort=nearest,limit=1] at @s run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..5] tower.state.stun 100
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (KfcGen.scoreMatches(sb, _ee.getNameForScoreboard(), "tower.y", -60, -60)));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc66 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc66.getPosition(), new String[]{"tower.data"}, new String[0], -1, 5)) {
                    if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
                }
            } }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run data modify entity @s Rotation set from entity @s data.last_rotation
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data.last_rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "Rotation", _v); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 300
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 300);
        }
        return 0;
    }

    public static void _m_2d0972c9_enemy_ability_timer_darkness_soron_execute(ServerCommandSource source) {
        _m_2d0972c9_enemy_ability_timer_darkness_soron_executeReturn(source);
    }

    public static int _m_2d0972c9_enemy_ability_timer_darkness_soron_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @s[tag=enemy.skill-loop.1,scores={enemy.skill-trigger.timer-cooldown=..0}] run function enemy:ability/timer/darkness-soron/dark-emission
        if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.1") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0))) {
            // -> enemy:ability/timer/darkness-soron/dark-emission
            tdpack.buckets.Bucket4._m_66f9b789_enemy_ability_timer_darkness_soron_dark_emission_execute(source);
        }

        // execute if entity @s[tag=enemy.skill-loop.2,scores={enemy.skill-trigger.timer-cooldown=..0}] run function enemy:ability/timer/darkness-soron/dark-touch
        if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.2") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0))) {
            // -> enemy:ability/timer/darkness-soron/dark-touch
            tdpack.buckets.Bucket4._m_9f9def9d_enemy_ability_timer_darkness_soron_dark_touch_execute(source);
        }

        // execute if entity @s[tag=enemy.skill-loop.3,scores={enemy.skill-trigger.timer-cooldown=..0}] run function enemy:ability/timer/darkness-soron/erosion
        if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.3") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0))) {
            // -> enemy:ability/timer/darkness-soron/erosion
            tdpack.buckets.Bucket4._m_2df1a527_enemy_ability_timer_darkness_soron_erosion_execute(source);
        }

        // execute if entity @s[tag=enemy.skill-loop.4,scores={enemy.skill-trigger.timer-cooldown=..0}] run function enemy:ability/timer/darkness-soron/reverse-side
        if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.4") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0))) {
            // -> enemy:ability/timer/darkness-soron/reverse-side
            tdpack.buckets.Bucket4._m_ba94dbe6_enemy_ability_timer_darkness_soron_reverse_side_execute(source);
        }

        // execute if entity @s[tag=enemy.skill-loop.5,scores={enemy.skill-trigger.timer-cooldown=..0}] run function enemy:ability/timer/darkness-soron/asphyxia
        if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.5") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0))) {
            // -> enemy:ability/timer/darkness-soron/asphyxia
            tdpack.buckets.Bucket4._m_954a8195_enemy_ability_timer_darkness_soron_asphyxia_execute(source);
        }
        return 0;
    }

    public static void _m_de623575_enemy_ability_timer_dash_zombie_execute(ServerCommandSource source) {
        _m_de623575_enemy_ability_timer_dash_zombie_executeReturn(source);
    }

    public static int _m_de623575_enemy_ability_timer_dash_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute at @s run function enemy:ability/timer/dash-zombie/main
        {
            ServerCommandSource kfcSrc69 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/dash-zombie/main
            tdpack.buckets.Bucket4._m_765d548c_enemy_ability_timer_dash_zombie_main_execute(kfcSrc69);
        }
        return 0;
    }

    public static void _m_47f90b91_enemy_ability_timer_evoker_execute(ServerCommandSource source) {
        _m_47f90b91_enemy_ability_timer_evoker_executeReturn(source);
    }

    public static int _m_47f90b91_enemy_ability_timer_evoker_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @s[tag=enemy.skill-loop.1] at @s run function enemy:ability/timer/evoker/summon-vex
        {
            ServerCommandSource kfcSrc74 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.1"))) {
                // -> enemy:ability/timer/evoker/summon-vex
                tdpack.buckets.Bucket4._m_300b291a_enemy_ability_timer_evoker_summon_vex_execute(kfcSrc74);
            }
        }

        // execute if entity @s[tag=enemy.skill-loop.2,scores={enemy.skill-trigger.timer-cooldown=0}] at @s run function enemy:ability/timer/evoker/stun-tower
        {
            ServerCommandSource kfcSrc75 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.2") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 0, 0))) {
                // -> enemy:ability/timer/evoker/stun-tower
                tdpack.buckets.Bucket4._m_5be61d0b_enemy_ability_timer_evoker_stun_tower_execute(kfcSrc75);
            }
        }
        return 0;
    }

    public static void _m_69e41671_enemy_ability_timer_horse_skeleton_execute(ServerCommandSource source) {
        _m_69e41671_enemy_ability_timer_horse_skeleton_executeReturn(source);
    }

    public static int _m_69e41671_enemy_ability_timer_horse_skeleton_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute at @s run function enemy:ability/timer/skeleton/main
        {
            ServerCommandSource kfcSrc84 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/skeleton/main
            tdpack.buckets.Bucket5._m_d51aab57_enemy_ability_timer_skeleton_main_execute(kfcSrc84);
        }
        return 0;
    }

    public static void _m_bdccf008_enemy_ability_timer_indura_stone_execute(ServerCommandSource source) {
        _m_bdccf008_enemy_ability_timer_indura_stone_executeReturn(source);
    }

    public static int _m_bdccf008_enemy_ability_timer_indura_stone_executeReturn(ServerCommandSource source) {
        
        // function enemy:hit/death {money:0,id:indura-stone}
        // -> enemy:hit/death
        tdpack.buckets.Bucket5._m_2db09a02_enemy_hit_death_execute(source, KfcGen.macroArgs("money", "0", "id", "indura-stone"));
        return 0;
    }

    public static void _m_d0c46e8d_enemy_ability_timer_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_d0c46e8d_enemy_ability_timer_main_executeReturn(source, macroArgs);
    }

    public static int _m_d0c46e8d_enemy_ability_timer_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // $function enemy:ability/timer/$(id)
        // -> enemy:ability/timer/macroArgs.get("id")
        switch (macroArgs.get("id")) {
            case "breeze": tdpack.buckets.Bucket4._m_3dbdff8b_enemy_ability_timer_breeze_execute(source); break;
            case "dark-dash-zombie": tdpack.buckets.Bucket4._m_e68efbcb_enemy_ability_timer_dark_dash_zombie_execute(source); break;
            case "dark-guardian": tdpack.buckets.Bucket4._m_e5b45c75_enemy_ability_timer_dark_guardian_execute(source); break;
            case "dark-knight": tdpack.buckets.Bucket4._m_e212f8e2_enemy_ability_timer_dark_knight_execute(source); break;
            case "darkness-soron": tdpack.buckets.Bucket4._m_2d0972c9_enemy_ability_timer_darkness_soron_execute(source); break;
            case "dash-zombie": tdpack.buckets.Bucket4._m_de623575_enemy_ability_timer_dash_zombie_execute(source); break;
            case "evoker": tdpack.buckets.Bucket4._m_47f90b91_enemy_ability_timer_evoker_execute(source); break;
            case "horse-skeleton": tdpack.buckets.Bucket4._m_69e41671_enemy_ability_timer_horse_skeleton_execute(source); break;
            case "indura-stone": tdpack.buckets.Bucket4._m_bdccf008_enemy_ability_timer_indura_stone_execute(source); break;
            case "pillager": tdpack.buckets.Bucket4._m_8b0f2be8_enemy_ability_timer_pillager_execute(source); break;
            case "rabbit": tdpack.buckets.Bucket4._m_788173a9_enemy_ability_timer_rabbit_execute(source); break;
            case "raid-guardian": tdpack.buckets.Bucket4._m_96588e5b_enemy_ability_timer_raid_guardian_execute(source); break;
            case "raid-lord": tdpack.buckets.Bucket4._m_80c82158_enemy_ability_timer_raid_lord_execute(source); break;
            case "revived-zombie": tdpack.buckets.Bucket4._m_b5201516_enemy_ability_timer_revived_zombie_execute(source); break;
            case "scream-zombie": tdpack.buckets.Bucket4._m_54954a99_enemy_ability_timer_scream_zombie_execute(source); break;
            case "sculk-titan": tdpack.buckets.Bucket4._m_91f68ed2_enemy_ability_timer_sculk_titan_execute(source); break;
            case "skeleton": tdpack.buckets.Bucket4._m_b4bc28d6_enemy_ability_timer_skeleton_execute(source); break;
            case "warden": tdpack.buckets.Bucket4._m_dd2f3fa8_enemy_ability_timer_warden_execute(source); break;
            case "witch": tdpack.buckets.Bucket4._m_2b841d1f_enemy_ability_timer_witch_execute(source); break;
            default: break;
        }
        return 0;
    }

    public static void _m_8b0f2be8_enemy_ability_timer_pillager_execute(ServerCommandSource source) {
        _m_8b0f2be8_enemy_ability_timer_pillager_executeReturn(source);
    }

    public static int _m_8b0f2be8_enemy_ability_timer_pillager_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute at @s run function enemy:ability/timer/skeleton/main
        {
            ServerCommandSource kfcSrc85 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/skeleton/main
            tdpack.buckets.Bucket5._m_d51aab57_enemy_ability_timer_skeleton_main_execute(kfcSrc85);
        }
        return 0;
    }

    public static void _m_788173a9_enemy_ability_timer_rabbit_execute(ServerCommandSource source) {
        _m_788173a9_enemy_ability_timer_rabbit_executeReturn(source);
    }

    public static int _m_788173a9_enemy_ability_timer_rabbit_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute at @s run function enemy:ability/timer/rabbit/main
        {
            ServerCommandSource kfcSrc86 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/rabbit/main
            tdpack.buckets.Bucket5._m_d74fd68f_enemy_ability_timer_rabbit_main_execute(kfcSrc86);
        }
        return 0;
    }

    public static void _m_96588e5b_enemy_ability_timer_raid_guardian_execute(ServerCommandSource source) {
        _m_96588e5b_enemy_ability_timer_raid_guardian_executeReturn(source);
    }

    public static int _m_96588e5b_enemy_ability_timer_raid_guardian_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // data modify entity @s data.speed set value 0.2
        if (executor != null) KfcGen.entityPutSnbt(executor, "data.speed", "0.2");

        // scoreboard players reset @s enemy.skill-trigger.timer-cooldown
        if (executor != null) KfcGen.resetScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown");
        return 0;
    }

    public static void _m_80c82158_enemy_ability_timer_raid_lord_execute(ServerCommandSource source) {
        _m_80c82158_enemy_ability_timer_raid_lord_executeReturn(source);
    }

    public static int _m_80c82158_enemy_ability_timer_raid_lord_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if entity @s[tag=enemy.skill-loop.1] at @s run function enemy:ability/timer/raid-lord/stun-tower
        {
            ServerCommandSource kfcSrc97 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.1"))) {
                // -> enemy:ability/timer/raid-lord/stun-tower
                tdpack.buckets.Bucket5._m_910b2235_enemy_ability_timer_raid_lord_stun_tower_execute(kfcSrc97);
            }
        }

        // execute if entity @s[tag=enemy.skill-loop.2,scores={enemy.skill-trigger.timer-cooldown=..0}] at @s run function enemy:ability/timer/raid-lord/call-horn
        {
            ServerCommandSource kfcSrc98 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.2") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0))) {
                // -> enemy:ability/timer/raid-lord/call-horn
                tdpack.buckets.Bucket5._m_f8f76f99_enemy_ability_timer_raid_lord_call_horn_execute(kfcSrc98);
            }
        }

        // execute if entity @s[tag=enemy.skill-loop.3,scores={enemy.skill-trigger.timer-cooldown=..0}] at @s run function enemy:ability/timer/raid-lord/healing-crystal
        {
            ServerCommandSource kfcSrc99 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.3") && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", Integer.MIN_VALUE, 0))) {
                // -> enemy:ability/timer/raid-lord/healing-crystal
                tdpack.buckets.Bucket5._m_f6cf5363_enemy_ability_timer_raid_lord_healing_crystal_execute(kfcSrc99);
            }
        }
        return 0;
    }

    public static void _m_b5201516_enemy_ability_timer_revived_zombie_execute(ServerCommandSource source) {
        _m_b5201516_enemy_ability_timer_revived_zombie_executeReturn(source);
    }

    public static int _m_b5201516_enemy_ability_timer_revived_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set @s enemy.hp 75
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.hp", 75);

        // scoreboard players reset @s enemy.skill-trigger.timer-cooldown
        if (executor != null) KfcGen.resetScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown");
        return 0;
    }

    public static void _m_54954a99_enemy_ability_timer_scream_zombie_execute(ServerCommandSource source) {
        _m_54954a99_enemy_ability_timer_scream_zombie_executeReturn(source);
    }

    public static int _m_54954a99_enemy_ability_timer_scream_zombie_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run tag @s add enemy.attribute.taunt
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            if (executor != null) executor.addCommandTag("enemy.attribute.taunt");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run tag @s remove enemy.attribute.taunt
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -100, -100)) {
            if (executor != null) executor.removeCommandTag("enemy.attribute.taunt");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 320
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -100, -100)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 320);
        }
        return 0;
    }

    public static void _m_91f68ed2_enemy_ability_timer_sculk_titan_execute(ServerCommandSource source) {
        _m_91f68ed2_enemy_ability_timer_sculk_titan_executeReturn(source);
    }

    public static int _m_91f68ed2_enemy_ability_timer_sculk_titan_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute if entity @s[tag=enemy.skill-loop.1] at @s run function enemy:ability/timer/sculk-titan/summon-zombie
        {
            ServerCommandSource kfcSrc109 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.1"))) {
                // -> enemy:ability/timer/sculk-titan/summon-zombie
                tdpack.buckets.Bucket5._m_73c6526e_enemy_ability_timer_sculk_titan_summon_zombie_execute(kfcSrc109);
            }
        }

        // execute if entity @s[tag=enemy.skill-loop.2] at @s run function enemy:ability/timer/sculk-titan/stun-tower
        {
            ServerCommandSource kfcSrc110 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if ((executor != null && executor.getCommandTags().contains("enemy.skill-loop.2"))) {
                // -> enemy:ability/timer/sculk-titan/stun-tower
                tdpack.buckets.Bucket5._m_a1b182e5_enemy_ability_timer_sculk_titan_stun_tower_execute(kfcSrc110);
            }
        }
        return 0;
    }

    public static void _m_b4bc28d6_enemy_ability_timer_skeleton_execute(ServerCommandSource source) {
        _m_b4bc28d6_enemy_ability_timer_skeleton_executeReturn(source);
    }

    public static int _m_b4bc28d6_enemy_ability_timer_skeleton_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // execute at @s run function enemy:ability/timer/skeleton/main
        {
            ServerCommandSource kfcSrc114 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/skeleton/main
            tdpack.buckets.Bucket5._m_d51aab57_enemy_ability_timer_skeleton_main_execute(kfcSrc114);
        }
        return 0;
    }

    public static void _m_dd2f3fa8_enemy_ability_timer_warden_execute(ServerCommandSource source) {
        _m_dd2f3fa8_enemy_ability_timer_warden_executeReturn(source);
    }

    public static int _m_dd2f3fa8_enemy_ability_timer_warden_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches 0 unless entity @n[distance=..15,scores={tower.y=-60},tag=tower.data] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10
        {
            ServerCommandSource kfcSrc115 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc116 = kfcSrc115.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc115.getPosition().x, (kfcSrc115.getPosition().y + 2.0), kfcSrc115.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                if (!(KfcGen.anyEntityWhere(ctx, _se -> (_se.getCommandTags().contains("tower.data") && KfcGen.posInRange(kfcSrc116.getPosition(), _se.getPos(), -1, 15) && KfcGen.scoreMatches(sb, _se.getNameForScoreboard(), "tower.y", -60, -60))))) {
                    if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 10);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches 0 run data modify entity @s data.last_rotation set from entity @s Rotation
        {
            ServerCommandSource kfcSrc117 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc118 = kfcSrc117.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc117.getPosition().x, (kfcSrc117.getPosition().y + 2.0), kfcSrc117.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "data.last_rotation", _v); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches 0 run rotate @s facing entity @e[scores={tower.y=-60},distance=..15,tag=tower.data,sort=furthest,limit=1] eyes
        {
            ServerCommandSource kfcSrc119 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc120 = kfcSrc119.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc119.getPosition().x, (kfcSrc119.getPosition().y + 2.0), kfcSrc119.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                { net.minecraft.entity.Entity _rt = executor, _fe = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc120.getPosition(), new String[]{"tower.data"}, new String[0], -1, 15, _ee -> (KfcGen.scoreMatches(sb, _ee.getNameForScoreboard(), "tower.y", -60, -60))); if (_rt != null && _fe != null) KfcGen.rotateToFaceEntity(_rt, _fe, true); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound entity.warden.sonic_charge record @a ~ ~ ~ 1.0 1.0 0.5
        {
            ServerCommandSource kfcSrc121 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc122 = kfcSrc121.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc121.getPosition().x, (kfcSrc121.getPosition().y + 2.0), kfcSrc121.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
                for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                    KfcGen.playSound(_ps, "entity.warden.sonic_charge", "record", new net.minecraft.util.math.Vec3d(kfcSrc122.getPosition().x, kfcSrc122.getPosition().y, kfcSrc122.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc122.getPosition().x, kfcSrc122.getPosition().y, kfcSrc122.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc122.getPosition().x, kfcSrc122.getPosition().y, kfcSrc122.getPosition().z).z, 1.0f, 1.0f);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 run playsound entity.warden.sonic_boom record @a ~ ~ ~ 1.0 1.0 0.5
        {
            ServerCommandSource kfcSrc123 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc124 = kfcSrc123.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc123.getPosition().x, (kfcSrc123.getPosition().y + 2.0), kfcSrc123.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                    KfcGen.playSound(_ps, "entity.warden.sonic_boom", "record", new net.minecraft.util.math.Vec3d(kfcSrc124.getPosition().x, kfcSrc124.getPosition().y, kfcSrc124.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc124.getPosition().x, kfcSrc124.getPosition().y, kfcSrc124.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc124.getPosition().x, kfcSrc124.getPosition().y, kfcSrc124.getPosition().z).z, 1.0f, 1.0f);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^1 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc125 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc126 = kfcSrc125.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc125.getPosition().x, (kfcSrc125.getPosition().y + 2.0), kfcSrc125.getPosition().z));
            ServerCommandSource kfcSrc127 = (executor != null ? kfcSrc126.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc126);
            ServerCommandSource kfcSrc128 = kfcSrc127.withPosition(KfcGen.localOffset(kfcSrc127.getPosition(), kfcSrc127.getRotation(), 0.0, 0.0, 1.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc128.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^2 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc129 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc130 = kfcSrc129.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc129.getPosition().x, (kfcSrc129.getPosition().y + 2.0), kfcSrc129.getPosition().z));
            ServerCommandSource kfcSrc131 = (executor != null ? kfcSrc130.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc130);
            ServerCommandSource kfcSrc132 = kfcSrc131.withPosition(KfcGen.localOffset(kfcSrc131.getPosition(), kfcSrc131.getRotation(), 0.0, 0.0, 2.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc132.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^3 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc133 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc134 = kfcSrc133.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc133.getPosition().x, (kfcSrc133.getPosition().y + 2.0), kfcSrc133.getPosition().z));
            ServerCommandSource kfcSrc135 = (executor != null ? kfcSrc134.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc134);
            ServerCommandSource kfcSrc136 = kfcSrc135.withPosition(KfcGen.localOffset(kfcSrc135.getPosition(), kfcSrc135.getRotation(), 0.0, 0.0, 3.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc136.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^4 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc137 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc138 = kfcSrc137.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc137.getPosition().x, (kfcSrc137.getPosition().y + 2.0), kfcSrc137.getPosition().z));
            ServerCommandSource kfcSrc139 = (executor != null ? kfcSrc138.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc138);
            ServerCommandSource kfcSrc140 = kfcSrc139.withPosition(KfcGen.localOffset(kfcSrc139.getPosition(), kfcSrc139.getRotation(), 0.0, 0.0, 4.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc140.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^5 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc141 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc142 = kfcSrc141.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc141.getPosition().x, (kfcSrc141.getPosition().y + 2.0), kfcSrc141.getPosition().z));
            ServerCommandSource kfcSrc143 = (executor != null ? kfcSrc142.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc142);
            ServerCommandSource kfcSrc144 = kfcSrc143.withPosition(KfcGen.localOffset(kfcSrc143.getPosition(), kfcSrc143.getRotation(), 0.0, 0.0, 5.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc144.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^6 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc145 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc146 = kfcSrc145.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc145.getPosition().x, (kfcSrc145.getPosition().y + 2.0), kfcSrc145.getPosition().z));
            ServerCommandSource kfcSrc147 = (executor != null ? kfcSrc146.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc146);
            ServerCommandSource kfcSrc148 = kfcSrc147.withPosition(KfcGen.localOffset(kfcSrc147.getPosition(), kfcSrc147.getRotation(), 0.0, 0.0, 6.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc148.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^7 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc149 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc150 = kfcSrc149.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc149.getPosition().x, (kfcSrc149.getPosition().y + 2.0), kfcSrc149.getPosition().z));
            ServerCommandSource kfcSrc151 = (executor != null ? kfcSrc150.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc150);
            ServerCommandSource kfcSrc152 = kfcSrc151.withPosition(KfcGen.localOffset(kfcSrc151.getPosition(), kfcSrc151.getRotation(), 0.0, 0.0, 7.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc152.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^8 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc153 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc154 = kfcSrc153.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc153.getPosition().x, (kfcSrc153.getPosition().y + 2.0), kfcSrc153.getPosition().z));
            ServerCommandSource kfcSrc155 = (executor != null ? kfcSrc154.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc154);
            ServerCommandSource kfcSrc156 = kfcSrc155.withPosition(KfcGen.localOffset(kfcSrc155.getPosition(), kfcSrc155.getRotation(), 0.0, 0.0, 8.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc156.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^9 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc157 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc158 = kfcSrc157.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc157.getPosition().x, (kfcSrc157.getPosition().y + 2.0), kfcSrc157.getPosition().z));
            ServerCommandSource kfcSrc159 = (executor != null ? kfcSrc158.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc158);
            ServerCommandSource kfcSrc160 = kfcSrc159.withPosition(KfcGen.localOffset(kfcSrc159.getPosition(), kfcSrc159.getRotation(), 0.0, 0.0, 9.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc160.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^10 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc161 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc162 = kfcSrc161.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc161.getPosition().x, (kfcSrc161.getPosition().y + 2.0), kfcSrc161.getPosition().z));
            ServerCommandSource kfcSrc163 = (executor != null ? kfcSrc162.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc162);
            ServerCommandSource kfcSrc164 = kfcSrc163.withPosition(KfcGen.localOffset(kfcSrc163.getPosition(), kfcSrc163.getRotation(), 0.0, 0.0, 10.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc164.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^11 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc165 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc166 = kfcSrc165.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc165.getPosition().x, (kfcSrc165.getPosition().y + 2.0), kfcSrc165.getPosition().z));
            ServerCommandSource kfcSrc167 = (executor != null ? kfcSrc166.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc166);
            ServerCommandSource kfcSrc168 = kfcSrc167.withPosition(KfcGen.localOffset(kfcSrc167.getPosition(), kfcSrc167.getRotation(), 0.0, 0.0, 11.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc168.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^12 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc169 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc170 = kfcSrc169.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc169.getPosition().x, (kfcSrc169.getPosition().y + 2.0), kfcSrc169.getPosition().z));
            ServerCommandSource kfcSrc171 = (executor != null ? kfcSrc170.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc170);
            ServerCommandSource kfcSrc172 = kfcSrc171.withPosition(KfcGen.localOffset(kfcSrc171.getPosition(), kfcSrc171.getRotation(), 0.0, 0.0, 12.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc172.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^13 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc173 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc174 = kfcSrc173.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc173.getPosition().x, (kfcSrc173.getPosition().y + 2.0), kfcSrc173.getPosition().z));
            ServerCommandSource kfcSrc175 = (executor != null ? kfcSrc174.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc174);
            ServerCommandSource kfcSrc176 = kfcSrc175.withPosition(KfcGen.localOffset(kfcSrc175.getPosition(), kfcSrc175.getRotation(), 0.0, 0.0, 13.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc176.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^14 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc177 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc178 = kfcSrc177.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc177.getPosition().x, (kfcSrc177.getPosition().y + 2.0), kfcSrc177.getPosition().z));
            ServerCommandSource kfcSrc179 = (executor != null ? kfcSrc178.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc178);
            ServerCommandSource kfcSrc180 = kfcSrc179.withPosition(KfcGen.localOffset(kfcSrc179.getPosition(), kfcSrc179.getRotation(), 0.0, 0.0, 14.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc180.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^15 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
        {
            ServerCommandSource kfcSrc181 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc182 = kfcSrc181.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc181.getPosition().x, (kfcSrc181.getPosition().y + 2.0), kfcSrc181.getPosition().z));
            ServerCommandSource kfcSrc183 = (executor != null ? kfcSrc182.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc182);
            ServerCommandSource kfcSrc184 = kfcSrc183.withPosition(KfcGen.localOffset(kfcSrc183.getPosition(), kfcSrc183.getRotation(), 0.0, 0.0, 15.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
                for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, kfcSrc184.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
                    KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 120);
                }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^1 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc185 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc186 = kfcSrc185.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc185.getPosition().x, (kfcSrc185.getPosition().y + 2.0), kfcSrc185.getPosition().z));
            ServerCommandSource kfcSrc187 = (executor != null ? kfcSrc186.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc186);
            ServerCommandSource kfcSrc188 = kfcSrc187.withPosition(KfcGen.localOffset(kfcSrc187.getPosition(), kfcSrc187.getRotation(), 0.0, 0.0, 1.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc188.getPosition().x, kfcSrc188.getPosition().y, kfcSrc188.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^2 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc189 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc190 = kfcSrc189.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc189.getPosition().x, (kfcSrc189.getPosition().y + 2.0), kfcSrc189.getPosition().z));
            ServerCommandSource kfcSrc191 = (executor != null ? kfcSrc190.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc190);
            ServerCommandSource kfcSrc192 = kfcSrc191.withPosition(KfcGen.localOffset(kfcSrc191.getPosition(), kfcSrc191.getRotation(), 0.0, 0.0, 2.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc192.getPosition().x, kfcSrc192.getPosition().y, kfcSrc192.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^3 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc193 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc194 = kfcSrc193.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc193.getPosition().x, (kfcSrc193.getPosition().y + 2.0), kfcSrc193.getPosition().z));
            ServerCommandSource kfcSrc195 = (executor != null ? kfcSrc194.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc194);
            ServerCommandSource kfcSrc196 = kfcSrc195.withPosition(KfcGen.localOffset(kfcSrc195.getPosition(), kfcSrc195.getRotation(), 0.0, 0.0, 3.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc196.getPosition().x, kfcSrc196.getPosition().y, kfcSrc196.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^4 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc197 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc198 = kfcSrc197.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc197.getPosition().x, (kfcSrc197.getPosition().y + 2.0), kfcSrc197.getPosition().z));
            ServerCommandSource kfcSrc199 = (executor != null ? kfcSrc198.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc198);
            ServerCommandSource kfcSrc200 = kfcSrc199.withPosition(KfcGen.localOffset(kfcSrc199.getPosition(), kfcSrc199.getRotation(), 0.0, 0.0, 4.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc200.getPosition().x, kfcSrc200.getPosition().y, kfcSrc200.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^5 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc201 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc202 = kfcSrc201.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc201.getPosition().x, (kfcSrc201.getPosition().y + 2.0), kfcSrc201.getPosition().z));
            ServerCommandSource kfcSrc203 = (executor != null ? kfcSrc202.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc202);
            ServerCommandSource kfcSrc204 = kfcSrc203.withPosition(KfcGen.localOffset(kfcSrc203.getPosition(), kfcSrc203.getRotation(), 0.0, 0.0, 5.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc204.getPosition().x, kfcSrc204.getPosition().y, kfcSrc204.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^6 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc205 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc206 = kfcSrc205.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc205.getPosition().x, (kfcSrc205.getPosition().y + 2.0), kfcSrc205.getPosition().z));
            ServerCommandSource kfcSrc207 = (executor != null ? kfcSrc206.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc206);
            ServerCommandSource kfcSrc208 = kfcSrc207.withPosition(KfcGen.localOffset(kfcSrc207.getPosition(), kfcSrc207.getRotation(), 0.0, 0.0, 6.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc208.getPosition().x, kfcSrc208.getPosition().y, kfcSrc208.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^7 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc209 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc210 = kfcSrc209.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc209.getPosition().x, (kfcSrc209.getPosition().y + 2.0), kfcSrc209.getPosition().z));
            ServerCommandSource kfcSrc211 = (executor != null ? kfcSrc210.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc210);
            ServerCommandSource kfcSrc212 = kfcSrc211.withPosition(KfcGen.localOffset(kfcSrc211.getPosition(), kfcSrc211.getRotation(), 0.0, 0.0, 7.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc212.getPosition().x, kfcSrc212.getPosition().y, kfcSrc212.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^8 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc213 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc214 = kfcSrc213.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc213.getPosition().x, (kfcSrc213.getPosition().y + 2.0), kfcSrc213.getPosition().z));
            ServerCommandSource kfcSrc215 = (executor != null ? kfcSrc214.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc214);
            ServerCommandSource kfcSrc216 = kfcSrc215.withPosition(KfcGen.localOffset(kfcSrc215.getPosition(), kfcSrc215.getRotation(), 0.0, 0.0, 8.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc216.getPosition().x, kfcSrc216.getPosition().y, kfcSrc216.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^9 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc217 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc218 = kfcSrc217.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc217.getPosition().x, (kfcSrc217.getPosition().y + 2.0), kfcSrc217.getPosition().z));
            ServerCommandSource kfcSrc219 = (executor != null ? kfcSrc218.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc218);
            ServerCommandSource kfcSrc220 = kfcSrc219.withPosition(KfcGen.localOffset(kfcSrc219.getPosition(), kfcSrc219.getRotation(), 0.0, 0.0, 9.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc220.getPosition().x, kfcSrc220.getPosition().y, kfcSrc220.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^10 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc221 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc222 = kfcSrc221.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc221.getPosition().x, (kfcSrc221.getPosition().y + 2.0), kfcSrc221.getPosition().z));
            ServerCommandSource kfcSrc223 = (executor != null ? kfcSrc222.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc222);
            ServerCommandSource kfcSrc224 = kfcSrc223.withPosition(KfcGen.localOffset(kfcSrc223.getPosition(), kfcSrc223.getRotation(), 0.0, 0.0, 10.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc224.getPosition().x, kfcSrc224.getPosition().y, kfcSrc224.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^11 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc225 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc226 = kfcSrc225.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc225.getPosition().x, (kfcSrc225.getPosition().y + 2.0), kfcSrc225.getPosition().z));
            ServerCommandSource kfcSrc227 = (executor != null ? kfcSrc226.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc226);
            ServerCommandSource kfcSrc228 = kfcSrc227.withPosition(KfcGen.localOffset(kfcSrc227.getPosition(), kfcSrc227.getRotation(), 0.0, 0.0, 11.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc228.getPosition().x, kfcSrc228.getPosition().y, kfcSrc228.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^12 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc229 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc230 = kfcSrc229.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc229.getPosition().x, (kfcSrc229.getPosition().y + 2.0), kfcSrc229.getPosition().z));
            ServerCommandSource kfcSrc231 = (executor != null ? kfcSrc230.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc230);
            ServerCommandSource kfcSrc232 = kfcSrc231.withPosition(KfcGen.localOffset(kfcSrc231.getPosition(), kfcSrc231.getRotation(), 0.0, 0.0, 12.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc232.getPosition().x, kfcSrc232.getPosition().y, kfcSrc232.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^13 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc233 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc234 = kfcSrc233.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc233.getPosition().x, (kfcSrc233.getPosition().y + 2.0), kfcSrc233.getPosition().z));
            ServerCommandSource kfcSrc235 = (executor != null ? kfcSrc234.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc234);
            ServerCommandSource kfcSrc236 = kfcSrc235.withPosition(KfcGen.localOffset(kfcSrc235.getPosition(), kfcSrc235.getRotation(), 0.0, 0.0, 13.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc236.getPosition().x, kfcSrc236.getPosition().y, kfcSrc236.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^14 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc237 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc238 = kfcSrc237.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc237.getPosition().x, (kfcSrc237.getPosition().y + 2.0), kfcSrc237.getPosition().z));
            ServerCommandSource kfcSrc239 = (executor != null ? kfcSrc238.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc238);
            ServerCommandSource kfcSrc240 = kfcSrc239.withPosition(KfcGen.localOffset(kfcSrc239.getPosition(), kfcSrc239.getRotation(), 0.0, 0.0, 14.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc240.getPosition().x, kfcSrc240.getPosition().y, kfcSrc240.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^15 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
        {
            ServerCommandSource kfcSrc241 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc242 = kfcSrc241.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc241.getPosition().x, (kfcSrc241.getPosition().y + 2.0), kfcSrc241.getPosition().z));
            ServerCommandSource kfcSrc243 = (executor != null ? kfcSrc242.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrc242);
            ServerCommandSource kfcSrc244 = kfcSrc243.withPosition(KfcGen.localOffset(kfcSrc243.getPosition(), kfcSrc243.getRotation(), 0.0, 0.0, 15.0));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -45, -40)) {
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc244.getPosition().x, kfcSrc244.getPosition().y, kfcSrc244.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:sonic_boom", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -50 run data modify entity @s Rotation set from entity @s data.last_rotation
        {
            ServerCommandSource kfcSrc245 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc246 = kfcSrc245.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc245.getPosition().x, (kfcSrc245.getPosition().y + 2.0), kfcSrc245.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -50, -50)) {
                { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data.last_rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "Rotation", _v); }
            }
        }

        // execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -50 run scoreboard players operation @s enemy.skill-trigger.timer-cooldown = @s enemy.skill-trigger.timer
        {
            ServerCommandSource kfcSrc247 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            ServerCommandSource kfcSrc248 = kfcSrc247.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc247.getPosition().x, (kfcSrc247.getPosition().y + 2.0), kfcSrc247.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -50, -50)) {
                if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", "=", executor.getNameForScoreboard(), "enemy.skill-trigger.timer");
            }
        }
        return 0;
    }

    public static void _m_2b841d1f_enemy_ability_timer_witch_execute(ServerCommandSource source) {
        _m_2b841d1f_enemy_ability_timer_witch_executeReturn(source);
    }

    public static int _m_2b841d1f_enemy_ability_timer_witch_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players operation @s enemy.skill-trigger.hp = @s enemy.hp
        if (executor != null && executor != null) KfcGen.opScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.hp", "=", executor.getNameForScoreboard(), "enemy.hp");

        // scoreboard players remove @s enemy.skill-trigger.hp 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.hp", -(1));

        // scoreboard players reset @s enemy.skill-trigger.timer-cooldown
        if (executor != null) KfcGen.resetScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown");
        return 0;
    }

    public static void _m_6aa95233_enemy_ability_timer_breeze_main_execute(ServerCommandSource source) {
        _m_6aa95233_enemy_ability_timer_breeze_main_executeReturn(source);
    }

    public static int _m_6aa95233_enemy_ability_timer_breeze_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches -4 run data modify entity @s data.speed set value 1.0
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -4, -4)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "data.speed", "1.0");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -4 run playsound minecraft:entity.breeze.charge record @a ~ ~ ~ 1.0 1.0
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -4, -4)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.breeze.charge", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -8..-4 run particle explosion ~ ~ ~ 0 0 0 0 1 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -8, -4)) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "explosion", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -15 run playsound minecraft:entity.breeze.jump record @a ~ ~ ~ 1.0 1.0
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -15, -15)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.breeze.jump", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20..-15 run particle explosion ~ ~ ~ 0 0 0 0 1 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -15)) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "explosion", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // data modify storage enemy temp set from entity @s data
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data"); if (_v != null) KfcGen.nbtSetStorage(server, "enemy", "temp", _v); }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -15 positioned ~ -59.3 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc5 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -59.3, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -15, -15)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc5, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -16 positioned ~ -58.7 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc6 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.7, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -16, -16)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc6, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -17 positioned ~ -58.2 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc7 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.2, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -17, -17)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc7, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -18 positioned ~ -57.8 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc8 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -57.8, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -18, -18)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc8, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -19 positioned ~ -57.5 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc9 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -57.5, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -19, -19)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc9, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 positioned ~ -57.3 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc10 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -57.3, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc10, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -21 positioned ~ -57.2 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc11 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -57.2, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -21, -21)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc11, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -22 positioned ~ -57.2 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc12 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -57.2, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -22, -22)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc12, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -23 positioned ~ -57.3 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc13 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -57.3, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -23, -23)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc13, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -24 positioned ~ -57.5 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc14 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -57.5, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -24, -24)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc14, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -25 positioned ~ -57.8 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc15 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -57.8, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -25, -25)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc15, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -26 positioned ~ -58.2 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc16 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.2, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -26, -26)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc16, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -27 positioned ~ -58.7 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc17 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -58.7, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -27, -27)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc17, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -28 positioned ~ -59.3 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc18 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -59.3, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -28, -28)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc18, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -29 positioned ~ -60 ~ run function enemy:move/teleport with storage enemy temp
        {
            ServerCommandSource kfcSrc19 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -29, -29)) {
                // -> enemy:move/teleport
                tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(kfcSrc19, KfcGen.storageMacroArgs(server, "enemy", "temp"));
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -30 run playsound minecraft:entity.breeze.land record @a ~ ~ ~ 1.0 1.0
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -30, -30)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.breeze.land", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 1.0f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -30 run particle minecraft:explosion ~ ~ ~ 0 0 0 0 1 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -30, -30)) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -30 run data modify entity @s data.speed set value 0.22
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -30, -30)) {
            if (executor != null) KfcGen.entityPutSnbt(executor, "data.speed", "0.22");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -30 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 100
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -30, -30)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 100);
        }
        return 0;
    }

    public static void _m_26c841e4_enemy_ability_timer_dark_dash_zombie_checkpoint_execute(ServerCommandSource source) {
        _m_26c841e4_enemy_ability_timer_dark_dash_zombie_checkpoint_executeReturn(source);
    }

    public static int _m_26c841e4_enemy_ability_timer_dark_dash_zombie_checkpoint_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
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

        // execute at @n[tag=map.check_point] run tp @s ~ -60 ~ ~ ~
        {
            net.minecraft.entity.Entity _atE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.check_point"}, new String[0], -1, -1, _ee -> (true));
            ServerCommandSource kfcSrc21 = (_atE1 != null ? source.withPosition(_atE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_atE1.getPitch(), _atE1.getYaw())) : null);
            if (kfcSrc21 != null) {
                {
                    net.minecraft.util.math.Vec3d _tpPos = new net.minecraft.util.math.Vec3d(kfcSrc21.getPosition().x, -60.0, kfcSrc21.getPosition().z);
                if (executor != null) KfcGen.teleportToWithRot(executor, _tpPos.x, _tpPos.y, _tpPos.z, kfcSrc21.getRotation().y, kfcSrc21.getRotation().x);
                }
            }
        }

        // execute on passengers at @s run rotate @s ~ ~
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            {
                ServerCommandSource kfcSrc22 = (_onEnt1 != null ? _on1.withPosition(_onEnt1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_onEnt1.getPitch(), _onEnt1.getYaw())) : _on1);
                if (_onEnt1 != null) KfcGen.rotateTo(_onEnt1, kfcSrc22.getRotation().y, kfcSrc22.getRotation().x);
            }
        }

        // scoreboard players set @s enemy.onGround 50
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.onGround", 50);

        // return 1
        return 1;
    }

    public static void _m_55abb916_enemy_ability_timer_dark_dash_zombie_dest_point_execute(ServerCommandSource source) {
        _m_55abb916_enemy_ability_timer_dark_dash_zombie_dest_point_executeReturn(source);
    }

    public static int _m_55abb916_enemy_ability_timer_dark_dash_zombie_dest_point_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players operation game game.base_health -= @s enemy.hp
        if (executor != null) KfcGen.opScore(sb, "game", "game.base_health", "-=", executor.getNameForScoreboard(), "enemy.hp");

        // execute on passengers run kill @s
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (_onEnt1 != null) KfcGen.killEntity(_onEnt1);
        }

        // kill @s
        if (executor != null) KfcGen.killEntity(executor);
        return 0;
    }

    public static void _m_decb2b69_enemy_ability_timer_dark_dash_zombie_main_execute(ServerCommandSource source) {
        _m_decb2b69_enemy_ability_timer_dark_dash_zombie_main_executeReturn(source);
    }

    public static int _m_decb2b69_enemy_ability_timer_dark_dash_zombie_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy temp set from entity @s data
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data"); if (_v != null) KfcGen.nbtSetStorage(server, "enemy", "temp", _v); }

        // data modify storage enemy temp.speed set value 1.0
        KfcGen.storagePutNumber(server, "enemy", "temp.speed", 1.0, "double");

        // function enemy:move/teleport with storage enemy temp
        // -> enemy:move/teleport
        tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(source, KfcGen.storageMacroArgs(server, "enemy", "temp"));

        // execute at @s run function enemy:ability/timer/dark-dash-zombie/teleport
        {
            ServerCommandSource kfcSrc23 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/dark-dash-zombie/teleport
            tdpack.buckets.Bucket4._m_71598c6f_enemy_ability_timer_dark_dash_zombie_teleport_execute(kfcSrc23);
        }

        // scoreboard players set @s enemy.skill-trigger.timer-cooldown 20
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 20);
        return 0;
    }

    public static void _m_71598c6f_enemy_ability_timer_dark_dash_zombie_teleport_execute(ServerCommandSource source) {
        _m_71598c6f_enemy_ability_timer_dark_dash_zombie_teleport_executeReturn(source);
    }

    public static int _m_71598c6f_enemy_ability_timer_dark_dash_zombie_teleport_executeReturn(ServerCommandSource source) {
        
        // 자기재귀 → 브릿지(스택오버플로 방지) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("enemy", "ability/timer/dark-dash-zombie/teleport"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // execute store result score #temp game.return run function enemy:move/teleport with storage enemy temp
                // KfcGen.setScore(sb, "#temp", "game.return", tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_executeReturn(source, KfcGen.storageMacroArgs(server, "enemy", "temp")));
                // 
                // // execute if score #temp game.return matches 0 run return 0
                // if (KfcGen.scoreMatches(sb, "#temp", "game.return", 0, 0)) {
                //     return 0;
                // }
                // 
                // // execute at @s run function enemy:ability/timer/dark-dash-zombie/teleport
                // {
                //     ServerCommandSource kfcSrc24 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
                //     // -> enemy:ability/timer/dark-dash-zombie/teleport
                //     tdpack.buckets.Bucket4._m_71598c6f_enemy_ability_timer_dark_dash_zombie_teleport_execute(kfcSrc24);
                // }
                return kfcBridgeRet;
    }

    public static void _m_954a8195_enemy_ability_timer_darkness_soron_asphyxia_execute(ServerCommandSource source) {
        _m_954a8195_enemy_ability_timer_darkness_soron_asphyxia_executeReturn(source);
    }

    public static int _m_954a8195_enemy_ability_timer_darkness_soron_asphyxia_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run tag @e[tag=tower.target.darkness-soron] remove tower.target.darkness-soron
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAny(ctx, source.getPosition(), new String[]{"tower.target.darkness-soron"}, new String[0], -1, -1)) {
                _t.removeCommandTag("tower.target.darkness-soron");
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 as @n[tag=tower.core,sort=nearest,limit=1] run tag @s add tower.target.darkness-soron
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                if (e != null) e.addCommandTag("tower.target.darkness-soron");
            } }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 as @e[tag=tower.target.darkness-soron,limit=1] at @s run particle minecraft:explosion ~ ~ ~ 0.8 0.2 0.8 0 20 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.target.darkness-soron"}, new String[0], -1, -1, _ee -> (true));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc67 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc67.getPosition().x, kfcSrc67.getPosition().y, kfcSrc67.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 0.8, 0.2, 0.8, 0, (int)(20), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
            } }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.wither.shoot record @a ~ ~ ~ 0.8 0.4
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.wither.shoot", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.8f, 0.4f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -100 as @e[tag=tower.target.darkness-soron,tag=tower.core,limit=1] at @s run kill @e[distance=..0.001,tag=tower,tag=!tower.core]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -100, -100)) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.target.darkness-soron", "tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc68 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, kfcSrc68.getPosition(), new String[]{"tower"}, new String[]{"tower.core"}, -1, 0.001)) {
                    KfcGen.killEntity(_k);
                }
            } }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run kill @e[tag=tower.target.darkness-soron,tag=tower.core,limit=1]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -100, -100)) {
            for (net.minecraft.entity.Entity _k : KfcGen.nearestNAnyType(ctx, source.getPosition(), new String[]{"tower.target.darkness-soron", "tower.core"}, new String[0], -1, -1, 1)) {
                KfcGen.killEntity(_k);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run tag @e[tag=tower.target.darkness-soron] remove tower.target.darkness-soron
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -100, -100)) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAny(ctx, source.getPosition(), new String[]{"tower.target.darkness-soron"}, new String[0], -1, -1)) {
                _t.removeCommandTag("tower.target.darkness-soron");
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -160 run tag @s remove enemy.skill-loop.5
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -160, -160)) {
            if (executor != null) executor.removeCommandTag("enemy.skill-loop.5");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -160 run tag @s add enemy.skill-loop.1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -160, -160)) {
            if (executor != null) executor.addCommandTag("enemy.skill-loop.1");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -160 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -160, -160)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        }
        return 0;
    }

    public static void _m_66f9b789_enemy_ability_timer_darkness_soron_dark_emission_execute(ServerCommandSource source) {
        _m_66f9b789_enemy_ability_timer_darkness_soron_dark_emission_executeReturn(source);
    }

    public static int _m_66f9b789_enemy_ability_timer_darkness_soron_dark_emission_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run particle minecraft:explosion ~ ~ ~ 1.5 0.2 1.5 0 20 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 1.5, 0.2, 1.5, 0, (int)(20), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.zombie_villager.converted record @a ~ ~ ~ 1.0 0.5
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.zombie_villager.converted", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.5f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "heavy-dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "heavy-dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "heavy-dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "heavy-dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "heavy-dark"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark-mist"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark-mist"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark-mist"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark-mist"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            // -> enemy:spawn/summon/main
            tdpack.buckets.Bucket6._m_5a993352_enemy_spawn_summon_main_execute(source, KfcGen.macroArgs("model", "dark-mist"));
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run tag @s remove enemy.skill-loop.1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) executor.removeCommandTag("enemy.skill-loop.1");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run tag @s add enemy.skill-loop.2
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) executor.addCommandTag("enemy.skill-loop.2");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        }
        return 0;
    }

    public static void _m_9f9def9d_enemy_ability_timer_darkness_soron_dark_touch_execute(ServerCommandSource source) {
        _m_9f9def9d_enemy_ability_timer_darkness_soron_dark_touch_executeReturn(source);
    }

    public static int _m_9f9def9d_enemy_ability_timer_darkness_soron_dark_touch_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run particle minecraft:explosion ~ ~ ~ 3.5 0.2 3.5 0 35 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 3.5, 0.2, 3.5, 0, (int)(35), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.wither.ambient record @a ~ ~ ~ 1.0 0.7
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.wither.ambient", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 1.0f, 0.7f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -60 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..7] tower.state.stun 150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -60, -60)) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, 7)) {
                if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 150);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run tag @s remove enemy.skill-loop.2
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -80, -80)) {
            if (executor != null) executor.removeCommandTag("enemy.skill-loop.2");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run tag @s add enemy.skill-loop.3
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -80, -80)) {
            if (executor != null) executor.addCommandTag("enemy.skill-loop.3");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -80, -80)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        }
        return 0;
    }

    public static void _m_2df1a527_enemy_ability_timer_darkness_soron_erosion_execute(ServerCommandSource source) {
        _m_2df1a527_enemy_ability_timer_darkness_soron_erosion_executeReturn(source);
    }

    public static int _m_2df1a527_enemy_ability_timer_darkness_soron_erosion_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run particle minecraft:explosion ~ ~ ~ 10 0.2 10 0 120 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 10, 0.2, 10, 0, (int)(120), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.ender_dragon.growl record @a ~ ~ ~ 0.7 0.8
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.ender_dragon.growl", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.7f, 0.8f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60}] tower.state.stun 60
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -20, -20)) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1)) {
                if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 60);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run tag @s remove enemy.skill-loop.3
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) executor.removeCommandTag("enemy.skill-loop.3");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run tag @s add enemy.skill-loop.5
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) executor.addCommandTag("enemy.skill-loop.5");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        }
        return 0;
    }

    public static void _m_ba94dbe6_enemy_ability_timer_darkness_soron_reverse_side_execute(ServerCommandSource source) {
        _m_ba94dbe6_enemy_ability_timer_darkness_soron_reverse_side_executeReturn(source);
    }

    public static int _m_ba94dbe6_enemy_ability_timer_darkness_soron_reverse_side_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run particle minecraft:explosion ~ ~ ~ 10 0.2 10 0 120 force @a
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.spawnParticle(ctx.world, "minecraft:explosion", _pp.x, _pp.y, _pp.z, 10, 0.2, 10, 0, (int)(120), true, KfcGen.filterPlayers(ctx, _pv -> (true))); }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.wither.spawn record @a ~ ~ ~ 0.8 0.5
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", 0, 0)) {
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.wither.spawn", "record", new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).x, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).y, new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z).z, 0.8f, 0.5f);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60}] tower.state.stun 100
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -40, -40)) {
            for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1)) {
                if (!(KfcGen.scoreMatches(sb, _t.getNameForScoreboard(), "tower.y", -60, -60))) continue;
                KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 100);
            }
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run tag @s remove enemy.skill-loop.4
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -80, -80)) {
            if (executor != null) executor.removeCommandTag("enemy.skill-loop.4");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run tag @s add enemy.skill-loop.1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -80, -80)) {
            if (executor != null) executor.addCommandTag("enemy.skill-loop.1");
        }

        // execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.skill-trigger.timer-cooldown", -80, -80)) {
            if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 150);
        }
        return 0;
    }

    public static void _m_a72c9568_enemy_ability_timer_dash_zombie_checkpoint_execute(ServerCommandSource source) {
        _m_a72c9568_enemy_ability_timer_dash_zombie_checkpoint_executeReturn(source);
    }

    public static int _m_a72c9568_enemy_ability_timer_dash_zombie_checkpoint_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
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

        // execute at @n[tag=map.check_point] run tp @s ~ -60 ~ ~ ~
        {
            net.minecraft.entity.Entity _atE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"map.check_point"}, new String[0], -1, -1, _ee -> (true));
            ServerCommandSource kfcSrc70 = (_atE1 != null ? source.withPosition(_atE1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_atE1.getPitch(), _atE1.getYaw())) : null);
            if (kfcSrc70 != null) {
                {
                    net.minecraft.util.math.Vec3d _tpPos = new net.minecraft.util.math.Vec3d(kfcSrc70.getPosition().x, -60.0, kfcSrc70.getPosition().z);
                if (executor != null) KfcGen.teleportToWithRot(executor, _tpPos.x, _tpPos.y, _tpPos.z, kfcSrc70.getRotation().y, kfcSrc70.getRotation().x);
                }
            }
        }

        // execute on passengers at @s run rotate @s ~ ~
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            {
                ServerCommandSource kfcSrc71 = (_onEnt1 != null ? _on1.withPosition(_onEnt1.getPos()).withRotation(new net.minecraft.util.math.Vec2f(_onEnt1.getPitch(), _onEnt1.getYaw())) : _on1);
                if (_onEnt1 != null) KfcGen.rotateTo(_onEnt1, kfcSrc71.getRotation().y, kfcSrc71.getRotation().x);
            }
        }

        // scoreboard players set @s enemy.onGround 50
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.onGround", 50);

        // return 1
        return 1;
    }

    public static void _m_15f7aadc_enemy_ability_timer_dash_zombie_dest_point_execute(ServerCommandSource source) {
        _m_15f7aadc_enemy_ability_timer_dash_zombie_dest_point_executeReturn(source);
    }

    public static int _m_15f7aadc_enemy_ability_timer_dash_zombie_dest_point_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players operation game game.base_health -= @s enemy.hp
        if (executor != null) KfcGen.opScore(sb, "game", "game.base_health", "-=", executor.getNameForScoreboard(), "enemy.hp");

        // execute on passengers run kill @s
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (_onEnt1 != null) KfcGen.killEntity(_onEnt1);
        }

        // kill @s
        if (executor != null) KfcGen.killEntity(executor);
        return 0;
    }

    public static void _m_765d548c_enemy_ability_timer_dash_zombie_main_execute(ServerCommandSource source) {
        _m_765d548c_enemy_ability_timer_dash_zombie_main_executeReturn(source);
    }

    public static int _m_765d548c_enemy_ability_timer_dash_zombie_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data modify storage enemy temp set from entity @s data
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "data"); if (_v != null) KfcGen.nbtSetStorage(server, "enemy", "temp", _v); }

        // data modify storage enemy temp.speed set value 1.0
        KfcGen.storagePutNumber(server, "enemy", "temp.speed", 1.0, "double");

        // function enemy:move/teleport with storage enemy temp
        // -> enemy:move/teleport
        tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_execute(source, KfcGen.storageMacroArgs(server, "enemy", "temp"));

        // execute at @s run function enemy:ability/timer/dash-zombie/teleport
        {
            ServerCommandSource kfcSrc72 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> enemy:ability/timer/dash-zombie/teleport
            tdpack.buckets.Bucket4._m_36b2dba1_enemy_ability_timer_dash_zombie_teleport_execute(kfcSrc72);
        }

        // scoreboard players set @s enemy.skill-trigger.timer-cooldown 20
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 20);
        return 0;
    }

    public static void _m_36b2dba1_enemy_ability_timer_dash_zombie_teleport_execute(ServerCommandSource source) {
        _m_36b2dba1_enemy_ability_timer_dash_zombie_teleport_executeReturn(source);
    }

    public static int _m_36b2dba1_enemy_ability_timer_dash_zombie_teleport_executeReturn(ServerCommandSource source) {
        
        // 자기재귀 → 브릿지(스택오버플로 방지) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("enemy", "ability/timer/dash-zombie/teleport"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // execute store result score #temp game.return run function enemy:move/teleport with storage enemy temp
                // KfcGen.setScore(sb, "#temp", "game.return", tdpack.buckets.Bucket5._m_9284ec00_enemy_move_teleport_executeReturn(source, KfcGen.storageMacroArgs(server, "enemy", "temp")));
                // 
                // // execute if score #temp game.return matches 0 run return 0
                // if (KfcGen.scoreMatches(sb, "#temp", "game.return", 0, 0)) {
                //     return 0;
                // }
                // 
                // // execute at @s run function enemy:ability/timer/dash-zombie/teleport
                // {
                //     ServerCommandSource kfcSrc73 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
                //     // -> enemy:ability/timer/dash-zombie/teleport
                //     tdpack.buckets.Bucket4._m_36b2dba1_enemy_ability_timer_dash_zombie_teleport_execute(kfcSrc73);
                // }
                return kfcBridgeRet;
    }

    public static void _m_c39eab8d_enemy_ability_timer_evoker_evoker_fangs_execute(ServerCommandSource source) {
        _m_c39eab8d_enemy_ability_timer_evoker_evoker_fangs_executeReturn(source);
    }

    public static int _m_c39eab8d_enemy_ability_timer_evoker_evoker_fangs_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 85
        for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, 2)) {
            KfcGen.setScore(sb, _t.getNameForScoreboard(), "tower.state.stun", 85);
        }

        // summon evoker_fangs ~ ~-1 ~
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + -1.0), source.getPosition().z); KfcGen.summon(ctx.world, "evoker_fangs", _sp.x, _sp.y, _sp.z, null); }

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

    public static void _m_5be61d0b_enemy_ability_timer_evoker_stun_tower_execute(ServerCommandSource source) {
        _m_5be61d0b_enemy_ability_timer_evoker_stun_tower_executeReturn(source);
    }

    public static int _m_5be61d0b_enemy_ability_timer_evoker_stun_tower_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[evoker-fangs,not-allocated]}"); }

        // execute as @e[tag=evoker-fangs,tag=not-allocated,distance=..2] at @s run rotate @s ~72 ~
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("evoker-fangs") && en.getCommandTags().contains("not-allocated") && KfcGen.inRange(source.getPosition(), en, -1, 2))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc76 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (e != null) KfcGen.rotateTo(e, (kfcSrc76.getRotation().y + 72.0f), kfcSrc76.getRotation().x);
        }

        // execute rotated ~72 ~ run summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
        {
            ServerCommandSource kfcSrc77 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 72.0f)));
            { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(kfcSrc77.getPosition().x, (kfcSrc77.getPosition().y + 1.0), kfcSrc77.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[evoker-fangs,not-allocated]}"); }
        }

        // execute as @e[tag=evoker-fangs,tag=not-allocated,distance=..2] at @s run rotate @s ~72 ~
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("evoker-fangs") && en.getCommandTags().contains("not-allocated") && KfcGen.inRange(source.getPosition(), en, -1, 2))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc78 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (e != null) KfcGen.rotateTo(e, (kfcSrc78.getRotation().y + 72.0f), kfcSrc78.getRotation().x);
        }

        // execute rotated ~144 ~ run summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
        {
            ServerCommandSource kfcSrc79 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 144.0f)));
            { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(kfcSrc79.getPosition().x, (kfcSrc79.getPosition().y + 1.0), kfcSrc79.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[evoker-fangs,not-allocated]}"); }
        }

        // execute as @e[tag=evoker-fangs,tag=not-allocated,distance=..2] at @s run rotate @s ~72 ~
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("evoker-fangs") && en.getCommandTags().contains("not-allocated") && KfcGen.inRange(source.getPosition(), en, -1, 2))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc80 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (e != null) KfcGen.rotateTo(e, (kfcSrc80.getRotation().y + 72.0f), kfcSrc80.getRotation().x);
        }

        // execute rotated ~216 ~ run summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
        {
            ServerCommandSource kfcSrc81 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 216.0f)));
            { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(kfcSrc81.getPosition().x, (kfcSrc81.getPosition().y + 1.0), kfcSrc81.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[evoker-fangs,not-allocated]}"); }
        }

        // execute as @e[tag=evoker-fangs,tag=not-allocated,distance=..2] at @s run rotate @s ~72 ~
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("evoker-fangs") && en.getCommandTags().contains("not-allocated") && KfcGen.inRange(source.getPosition(), en, -1, 2))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc82 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (e != null) KfcGen.rotateTo(e, (kfcSrc82.getRotation().y + 72.0f), kfcSrc82.getRotation().x);
        }

        // execute rotated ~288 ~ run summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
        {
            ServerCommandSource kfcSrc83 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 288.0f)));
            { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(kfcSrc83.getPosition().x, (kfcSrc83.getPosition().y + 1.0), kfcSrc83.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[evoker-fangs,not-allocated]}"); }
        }

        // scoreboard players set @e[tag=evoker-fangs,distance=..2,tag=not-allocated] enemy.hp 50
        for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"evoker-fangs", "not-allocated"}, new String[0], -1, 2)) {
            KfcGen.setScore(sb, _t.getNameForScoreboard(), "enemy.hp", 50);
        }

        // tag @e[tag=evoker-fangs,tag=not-allocated,distance=..2] remove not-allocated
        for (net.minecraft.entity.Entity _t : KfcGen.allEntitiesAny(ctx, source.getPosition(), new String[]{"evoker-fangs", "not-allocated"}, new String[0], -1, 2)) {
            _t.removeCommandTag("not-allocated");
        }

        // tag @s remove enemy.skill-loop.2
        if (executor != null) executor.removeCommandTag("enemy.skill-loop.2");

        // tag @s add enemy.skill-loop.1
        if (executor != null) executor.addCommandTag("enemy.skill-loop.1");

        // scoreboard players set @s enemy.skill-trigger.timer-cooldown 300
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 300);
        return 0;
    }

    public static void _m_300b291a_enemy_ability_timer_evoker_summon_vex_execute(ServerCommandSource source) {
        _m_300b291a_enemy_ability_timer_evoker_summon_vex_executeReturn(source);
    }

    public static int _m_300b291a_enemy_ability_timer_evoker_summon_vex_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~1 ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:3,model:vex}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.0), source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:3,model:vex}}"); }

        // data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "Rotation"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)), "Rotation", _v); }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.tick", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_cooldown"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.count", (int)(_stv));
        } }

        // execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
        { net.minecraft.entity.Entity _vE1 = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (_vE1 != null) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)(KfcGen.entityGetDouble(_vE1, "data.spawn_count"));
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "mob_gen.number", (int)(_stv));
        } }

        // execute store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "enemy.progress", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress")); }

        // execute as @n[tag=mob-gen,tag=not-allocated] run tag @s remove not-allocated
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"mob-gen", "not-allocated"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        } }

        // tag @s remove enemy.skill-loop.1
        if (executor != null) executor.removeCommandTag("enemy.skill-loop.1");

        // tag @s add enemy.skill-loop.2
        if (executor != null) executor.addCommandTag("enemy.skill-loop.2");

        // scoreboard players set @s enemy.skill-trigger.timer-cooldown 300
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "enemy.skill-trigger.timer-cooldown", 300);
        return 0;
    }
}
