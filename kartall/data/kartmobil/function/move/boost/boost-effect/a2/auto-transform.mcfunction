#자동변신
execute if score #kartspeed kartmove matches 50.. if entity @e[distance=..0.3,tag=kart-boost-blocks,tag=!kart-boost-blocks-show,type=#kartmobil:kartmodels] as @e[limit=8] run playsound minecraft:item.armor.equip_netherite neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
##sidite 분기
execute if score #kartspeed kartmove matches 50.. run function kartmobil:move/boost/boost-effect/show-boost-model/show-block

execute if score #kartspeed kartmove matches ..49 unless score @s kartboosttime matches 1.. if entity @e[distance=..0.3,tag=kart-boost-blocks,tag=kart-boost-blocks-show,type=#kartmobil:kartmodels] as @e[limit=8] run playsound minecraft:item.armor.equip_diamond neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75

##sidite 분기
execute if score #kartspeed kartmove matches ..49 unless score @s kartboosttime matches 1.. run function kartmobil:move/boost/boost-effect/show-boost-model/hide-block

#부스터 쓰면 변신
execute if score @s kartboosttime matches 1.. if entity @e[distance=..0.3,tag=kart-boost-blocks,tag=!kart-boost-blocks-show,type=#kartmobil:kartmodels] as @e[limit=8] run playsound minecraft:item.armor.equip_netherite neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
##sidite 분기
execute if score @s kartboosttime matches 1.. run function kartmobil:move/boost/boost-effect/show-boost-model/show-block