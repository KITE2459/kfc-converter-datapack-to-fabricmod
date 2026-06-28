tellraw @s [{translate:"지금은 조작이 불가합니다",color:red}]
execute as @s at @s run playsound minecraft:block.note_block.didgeridoo master @s ~ ~ ~ 2 2
execute as @s at @s run playsound minecraft:block.note_block.didgeridoo master @s ~ ~ ~ 2 2

execute as @e[tag=kite-lock,type=minecraft:interaction] run data remove entity @s attack
execute as @e[tag=kite-lock,type=minecraft:interaction] run data remove entity @s interaction