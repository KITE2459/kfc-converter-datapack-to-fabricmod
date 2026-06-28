#effect give @a[tag=kart-listener] minecraft:dolphins_grace 3 169 true
execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/on-set-1

#자동변신
execute if score #kartspeed kartmove matches 125.. if entity @e[distance=..0.3,tag=kart-boost-blocks,tag=!kart-boost-blocks-show,type=#kartmobil:kartmodels] as @e[limit=8] run playsound minecraft:item.armor.equip_netherite neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
##sidite 분기
execute if score #kartspeed kartmove matches 125.. run function kartmobil:move/boost/boost-effect/show-boost-model/show-block

execute if score #kartspeed kartmove matches ..124 if entity @e[distance=..0.3,tag=kart-boost-blocks,tag=kart-boost-blocks-show,type=#kartmobil:kartmodels] as @e[limit=8] run playsound minecraft:item.armor.equip_diamond neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75

##sidite 분기
execute if score #kartspeed kartmove matches ..124 run function kartmobil:move/boost/boost-effect/show-boost-model/hide-block

#부스터 쓰면 변신
#execute if score @s kartboosttime matches 1.. if entity @e[distance=..0.3,tag=kart-boost-blocks,tag=!kart-boost-blocks-show,type=#kartmobil:kartmodels] run playsound minecraft:item.armor.equip_netherite neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
##sidite 분기
#execute if score @s kartboosttime matches 1.. run function kartmobil:move/boost/boost-effect/show-boost-model/show-block

#자동부스터연출
execute if score #kartspeed kartmove matches 140.. if entity @e[distance=..0.3,tag=kart-boost-flame,tag=!kart-boost-flame-show,type=#kartmobil:kartmodels] run function kartmobil:move/boost/boost-effect/jiu/boost-sound
##sidite 분기
execute if score #kartspeed kartmove matches 140.. run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

##sidite 분기
execute if score #kartspeed kartmove matches ..139 run function kartmobil:move/boost/boost-effect/show-boost-model/hide-flame
