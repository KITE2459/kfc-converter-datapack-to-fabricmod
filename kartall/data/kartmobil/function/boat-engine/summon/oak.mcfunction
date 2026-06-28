
summon item_display ~ ~ ~ {Tags:["boat-model-saddle","boat-temp"]}
summon item_display ~ ~ ~ {teleport_duration:1,Tags:["boat-main","boat-temp"]}
summon interaction ~ ~ ~ {Tags:["boat-interaction","boat-temp"]}

summon cod ~ ~ ~ {NoAI:1b,Silent:1b,Invulnerable:1b,NoGravity:1b,Tags:["boat-saddle","boat-temp"],DeathLootTable:"minecraft:empty"}

function boatpack:summon/model/oak

tag @e[distance=..0.01,tag=!boat-main,tag=!boat-model-saddle,type=#boatpack:models] add boat-model
execute as @e[distance=..0.01,tag=boat-model,type=#boatpack:models] run data merge entity @s {teleport_duration:1}
execute as @e[distance=..0.01,tag=boat-model,type=#boatpack:models] run ride @s dismount

effect give @e[tag=boat-saddle] invisibility infinite 1 true

#미끄럼 수치 설정
data modify entity @n[tag=boat-main] data.slip set value 0.98

#푸른얼음
data modify entity @n[tag=boat-main] data.blueslip set value 0.989

#탑승
execute as @e[tag=boat-model] run ride @s mount @n[distance=..0.01,tag=boat-model-saddle,tag=boat-temp,type=item_display]

execute as @e[tag=boat-saddle] run ride @s mount @n[distance=..0.01,tag=boat-main,tag=boat-temp,type=#kartmobil:kartmobil]
execute as @e[tag=boat-model-saddle] run ride @s mount @n[distance=..0.01,tag=boat-main,tag=boat-temp,type=#kartmobil:kartmobil]
execute as @e[tag=boat-interaction] run ride @s mount @n[distance=..0.01,tag=boat-main,tag=boat-temp,type=#kartmobil:kartmobil]

tag @e[tag=boat-temp] remove boat-temp