execute store result score #hub-player-count-half multi-main if entity @a[tag=multi-hub-player]

scoreboard players operation #hub-player-count-half multi-main /= #kart2 kartmain

scoreboard players set @a kart-team -1
function multiplay:hub/team-assign/random-team-loop
execute as @a[tag=multi-hub-player,scores={kart-team=-1}] run scoreboard players set @s kart-team 1

tellraw @a[tag=multi-hub-player,scores={kart-team=0}] [{translate:"레드팀","color":"red"},{translate:"이 되었습니다.","color":"aqua"}]
tellraw @a[tag=multi-hub-player,scores={kart-team=1}] [{translate:"블루팀","color":"blue"},{translate:"이 되었습니다.","color":"aqua"}]
