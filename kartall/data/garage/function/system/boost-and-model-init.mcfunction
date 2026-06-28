
#부스터 초기화
summon item_display ~ ~-0.5 ~ {Tags:["kartdatacarrier"]}
execute positioned ~ ~-0.5 ~ run function kartmain:summon/manage-tag
execute positioned ~ ~-0.5 ~ run function kartmain:summon/boost-effect/boost-effect

#모델 초기화
execute positioned ~ ~-0.5 ~ run tag @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models] remove karttemp
execute positioned ~ ~-0.5 ~ run tag @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models] remove kartmodel
execute positioned ~ ~-0.5 ~ run tag @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models] remove kartmodeltemp
execute positioned ~ ~-0.5 ~ run tag @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models] remove kartentity


execute positioned ~ ~-0.5 ~ if entity @e[distance=..0.01,type=#kartmobil:kartmodels,tag=kart-run-anime] run tag @s add kart-use-run-anime