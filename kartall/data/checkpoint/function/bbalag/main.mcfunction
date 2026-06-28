execute store result storage checkpoint:checkpoint checknexttemp int 1 run scoreboard players get @s check-num
function checkpoint:system/multi-sort/sort-num-find-check with storage checkpoint:checkpoint

execute store result storage checkpoint:checkpoint checknexttemp int 1 run scoreboard players get @s check-next
function checkpoint:system/multi-sort/sort-num-find-check with storage checkpoint:checkpoint

#execute store result storage checkpoint:checkpoint checknexttemp int 1 run scoreboard players get @s check-prev
#function checkpoint:system/multi-sort/sort-num-find-check with storage checkpoint:checkpoint

execute unless entity @e[tag=check-next-temp,distance=..125,limit=1,type=marker] run playsound minecraft:block.note_block.pling hostile @s ~ ~ ~ 1 1.0595
execute unless entity @e[tag=check-next-temp,distance=..125,limit=1,type=marker] run title @s title {text:"🚫",color:red}

tag @e[tag=check-next-temp,type=marker] remove check-next-temp


    