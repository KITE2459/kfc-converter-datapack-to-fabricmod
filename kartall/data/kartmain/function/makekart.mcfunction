#엔티티인 경우
$execute if entity @s[type=!player] run data modify entity @s data.kart-item-data append value {name:$(name), speed:$(speed), accel:$(accel), boost:$(boost), corner:$(corner), drift:$(drift), gauge:$(gauge), boosttime:$(boosttime), maxboostcount:$(maxboostcount), defense:$(defense), item:$(item), draft:$(draft), model:"$(model)"}
execute if entity @s[type=!player] run return 0

#플레이어인 경우
scoreboard players set #item-exist kartmain 0
$function kartmain:player-head-item/detect-exist-item {item:$(item)}

$execute if score #item-exist kartmain matches 1 run give @p[gamemode=!spectator] iron_nugget[custom_name=$(name),custom_data={kartmobil:1, speed:$(speed), accel:$(accel), boost:$(boost), corner:$(corner), drift:$(drift), gauge:$(gauge), boosttime:$(boosttime), maxboostcount:$(maxboostcount), defense:$(defense), draft:$(draft), model:"$(model)"}, \
lore=[[{"color":"aqua","italic":false,translate:"스피드: "},{"color":"#d6d6d6","italic":true,translate:"$(speed)"}],[{"color":"aqua","italic":false,translate:"가속력: "},{"color":"#d6d6d6","italic":true,translate:"기본 $(accel) | 부스터 $(boost)"}],[{"color":"aqua","italic":false,translate:"코너링: "},{"color":"#d6d6d6","italic":true,translate:"기본 $(corner) | 탈출력 $(drift)"}],[{"color":"aqua","italic":false,translate:"부스터: "},{"color":"#d6d6d6","italic":true,translate:"충전 $(gauge) | 지속 $(boosttime) | 슬롯 $(maxboostcount)"}],[{"color":"aqua","italic":false,translate:"충돌: "},{"color":"#d6d6d6","italic":true,translate:"방어력 $(defense)"}],[{"color":"gray",translate:"$(model)"}]],minecraft:item_model="$(item)",minecraft:max_stack_size=1,minecraft:consumable={"animation":"block","consume_seconds":1000000.0,"has_consume_particles":false}] 1

#커스텀 헤드 모델 찾기
data remove storage minecraft:kartmain itemtexture.result
$execute if score #item-exist kartmain matches 0 if data storage minecraft:kartmain itemtexture.$(item) run data modify storage minecraft:kartmain itemtexture.result.texture set from storage minecraft:kartmain itemtexture.$(item)
$data merge storage minecraft:kartmain {itemtexture:{result:{name:$(name), speed:$(speed), accel:$(accel), boost:$(boost), corner:$(corner), drift:$(drift), gauge:$(gauge), boosttime:$(boosttime), maxboostcount:$(maxboostcount), defense:$(defense), item:$(item), draft:$(draft), model:"$(model)"}}}

    #커스텀 헤드 아이템 지급
    execute if score #item-exist kartmain matches 0 run function kartmain:player-head-item/makekart with storage minecraft:kartmain itemtexture.result