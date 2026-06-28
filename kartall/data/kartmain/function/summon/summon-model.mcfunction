    # 커스텀 모델링 감지
    data modify storage minecraft:kart-custom-model iscustommodel set value "custom"
    $execute store success score #not-custom-model kartmain run data modify storage minecraft:kart-custom-model iscustommodel set value "$(model)"

#포테이토 모드
execute if score lowdetail kartmain matches 1.. run function kartmodel:potato-mode
$execute if score lowdetail kartmain matches 1.. run data modify entity @e[distance=..0.01,tag=!kartmobil,tag=modeltext1,limit=1,type=#kartmobil:kartmodels] text set value "$(model)"
execute if score lowdetail kartmain matches 1.. run data modify entity @e[distance=..0.01,tag=!kartmobil,tag=modeltext2,limit=1,type=#kartmobil:kartmodels] text set from entity @s equipment.offhand.components.minecraft:custom_name

$execute unless score lowdetail kartmain matches 1.. if score #not-custom-model kartmain matches 1 run function kartmodel:$(model)
    # 커스텀 모델링 소환
    execute unless score lowdetail kartmain matches 1.. if score #not-custom-model kartmain matches 0 run data modify entity @e[tag=kartmobil,tag=karttemp,limit=1,type=#kartmobil:kartmobil] data.kart-custom-model.temp3 set from entity @s EnderItems[0].components.minecraft:custom_data.model
    execute unless score lowdetail kartmain matches 1.. if score #not-custom-model kartmain matches 0 as @e[tag=kartmobil,tag=karttemp,limit=1,type=#kartmobil:kartmobil] if data entity @s data.kart-custom-model run tag @s add kart-has-custom-model