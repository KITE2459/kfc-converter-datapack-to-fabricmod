execute if score @s kart-engine matches 0 run function kartmain:summon/kart-spec-consider-engine/x
execute if score @s kart-engine matches 1 run function kartmain:summon/kart-spec-consider-engine/ex
execute if score @s kart-engine matches 2 run function kartmain:summon/kart-spec-consider-engine/jiu
execute if score @s kart-engine matches 3 run function kartmain:summon/kart-spec-consider-engine/new
execute if score @s kart-engine matches 4 run function kartmain:summon/kart-spec-consider-engine/z7
execute if score @s kart-engine matches 5 run function kartmain:summon/kart-spec-consider-engine/v1
execute if score @s kart-engine matches 6 run function kartmain:summon/kart-spec-consider-engine/a2
execute if score @s kart-engine matches 7 run function kartmain:summon/kart-spec-consider-engine/1.0
execute if score @s kart-engine matches 8 run function kartmain:summon/kart-spec-consider-engine/pro
execute if score @s kart-engine matches 9 run function kartmain:summon/kart-spec-consider-engine/krp
execute if score @s kart-engine matches 10 run function kartmain:summon/kart-spec-consider-engine/xun
execute if score @s kart-engine matches 11 run function kartmain:summon/kart-spec-consider-engine/sr

execute if score @s kart-engine matches 1000 run function kartmain:summon/kart-spec-consider-engine/dummy/n1
execute if score @s kart-engine matches 1001 run function kartmain:summon/kart-spec-consider-engine/dummy/rx
execute if score @s kart-engine matches 1002 run function kartmain:summon/kart-spec-consider-engine/dummy/a2-d-keyboard
execute if score @s kart-engine matches 1003 run function kartmain:summon/kart-spec-consider-engine/dummy/mario
#execute if score @s kart-engine matches 1004 run data modify entity @n[tag=kartsaddle,type=#kartmobil:kartsaddle] CustomName set value "mcrider-saddle-boat"
execute if score @s kart-engine matches 1005 run function kartmain:summon/kart-spec-consider-engine/dummy/gear
execute if score @s kart-engine matches 1006 run function kartmain:summon/kart-spec-consider-engine/dummy/f1
execute if score @s kart-engine matches 1007 run function kartmain:summon/kart-spec-consider-engine/dummy/rally
execute if score @s kart-engine matches 1008 run function kartmain:summon/kart-spec-consider-engine/dummy/ds

#듀부 없는 엔진 부스터 지속시간 보정 | 부스터가 60보다 길면 가속 너프, 짧으면 버프
scoreboard players set #accel-offset kartboostduration 60
scoreboard players operation #accel-offset kartboostduration -= @s kartboostduration

execute if score @s kartboostduration matches 61.. run scoreboard players operation #accel-offset kartboostduration /= #kart3 kartmain
execute if score @s kartboostduration matches ..59 run scoreboard players operation #accel-offset kartboostduration /= #kart3 kartmain

execute if score @s[tag=!kart-has-dualboost] kartboostduration matches 20.. run scoreboard players operation @s kartaccel += #accel-offset kartboostduration