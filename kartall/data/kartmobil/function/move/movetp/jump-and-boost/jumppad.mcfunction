scoreboard players set @s kartmovey -3250

#속력고정
data modify storage minecraft:kartmain pad-no-text-string set value ""
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs store result score #pad-third-text-exist kartmovey run data modify storage minecraft:kartmain pad-no-text-string set from block ~ ~-1 ~ front_text.messages[2]
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs if score #pad-third-text-exist kartmovey matches 1 run data modify storage minecraft:kartmain jumppadfixspeed set from block ~ ~-1 ~ front_text.messages[2]
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs if score #pad-third-text-exist kartmovey matches 1 run function kartmobil:move/movetp/jump-and-boost/pad-kart-speed-fix-macro with storage minecraft:kartmain

#방향 변경
data modify storage minecraft:kartmain pad-no-text-string set value ""
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs store result score #pad-second-text-exist kartmovey run data modify storage minecraft:kartmain pad-no-text-string set from block ~ ~-1 ~ front_text.messages[1]
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs if score #pad-second-text-exist kartmovey matches 1 run data modify storage minecraft:kartmain boostpaddirection set from block ~ ~-1 ~ front_text.messages[1]
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs if score #pad-second-text-exist kartmovey matches 1 run function kartmobil:move/movetp/jump-and-boost/pad-direction-fix-macro with storage minecraft:kartmain

#점프패드 파워
scoreboard players operation #pad-scale kartmovey = @s kartmovey
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs run data modify storage minecraft:kartmain jumppadscale set from block ~ ~-1 ~ front_text.messages[0]
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs run function kartmobil:move/movetp/jump-and-boost/pad-power-scale-macro with storage minecraft:kartmain
scoreboard players operation @s kartmovey = #pad-scale kartmovey

#이펙트
execute at @s positioned ~ ~0.25 ~ run function kartmobil:move/movetp/tp-bug-fix/tp

execute if block ~ ~1 ~ #kartmobil:ignoreblock run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 1
execute if block ~ ~1 ~ #kartmobil:ignoreblock run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 1
execute if block ~ ~1 ~ #kartmobil:ignoreblock run playsound minecraft:entity.generic.explode neutral @a[tag=kart-listener] ~ ~ ~ 1 1.5
execute if block ~ ~1 ~ #kartmobil:ignoreblock run playsound minecraft:block.note_block.bass neutral @a[tag=kart-listener] ~ ~ ~ 1 0

execute if block ~ ~1 ~ #kartmobil:ignoreblock run particle minecraft:crit ~ ~1 ~ .25 .25 .25 1.25 50 force @a
execute if block ~ ~1 ~ #kartmobil:ignoreblock run particle minecraft:cloud ~ ~1 ~ .25 .25 .25 0.5 25 force @a

function kartmobil:rkey-ghost/show