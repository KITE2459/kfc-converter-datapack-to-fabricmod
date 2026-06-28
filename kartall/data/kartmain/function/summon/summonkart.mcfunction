execute unless loaded ~ ~ ~ run return 1

#카트 소환 못 함
execute if entity @e[distance=..1.5,tag=garage-kart,type=item_display] run return run function kartmain:summon/cant-summon-kart
execute if entity @e[distance=..1.5,tag=kartmobil,type=#kartmobil:kartmobil] run return run function kartmain:summon/cant-summon-kart
execute if entity @s[nbt={active_effects:[{id:"minecraft:unluck"}]}] run return run function kartmain:summon/cant-summon-kart
execute if data entity @s RootVehicle run return run function kartmain:summon/cant-summon-kart

#카트 엔티티들 소환
summon item_display ~ ~ ~ {Tags:["kartmodelsaddle","karttemp","kartentity"],CustomName:"mcrider-modelsaddle"}
summon item_display ~ ~ ~ {Tags:["kartdirection","kartentity","karttemp"],CustomName:"mcrider-direction"}
summon text_display ~ ~ ~ {Tags:["kartmobil","karttemp","kartentity"],teleport_duration:2,CustomName:"mcrider-kartmain"}

summon item_display ~ ~ ~ {Tags:["kartdatacarrier","kartentity","karttemp"],CustomName:"mcrider-datacarrier",view_range:0f,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[0f,0f,0f]}}
summon marker ~ ~ ~ {Tags:["kart-old-velocity","kartentity","karttemp"]}

summon cod ~ ~ ~ {Tags:["kartentity","kartsaddle","karttemp"],CustomName:"mcrider-saddle-common",NoAI:1b,Silent:1b,Invulnerable:1b,NoGravity:1b,DeathLootTable:"minecraft:empty"}

scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartboostgauge 1
scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartboostcount 0
scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartboosttime 0
scoreboard players set @s kartboostgauge 0
scoreboard players set @s kartboosttime 0
xp set @s 0 points

#드랲 초기 쿨타임
scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartmain 120

function kartmain:summon/summon-model with entity @s equipment.offhand.components.minecraft:custom_data
execute as @e[distance=..0.01,tag=!kart-boost-flame,tag=!drift-effect,type=#kartmobil:kartmodels] if data entity @s {brightness:{sky:15,block:15}} run tag @s add kart-light
execute as @e[distance=..0.01,tag=kart-light,type=#kartmobil:kartmodels] run function kartmobil:bright-blocks/light-off
function kartmain:summon/set-kart-spec
function kartmain:summon/manage-tag

function kartmain:summon/drift-effect
function kartmain:summon/boost-effect/boost-effect
execute as @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models] run ride @s dismount

function kartmain:summon/tp-and-ride

#attribute 정보 반영 및 리셋
execute on vehicle if entity @s[tag=kartsaddle] run function kartmain:summon/init-s2c-value

#견인에 의한 드탈 보정 변수
scoreboard players set @s kartdrift 0

effect give @e[tag=kartsaddle,type=#kartmobil:kartsaddle] resistance infinite 10 true
effect give @e[tag=kartsaddle,type=#kartmobil:kartsaddle] invisibility infinite 1 true

tag @e[tag=kartmodeltemp] remove kartmodeltemp
tag @e[tag=karttemp] remove karttemp






