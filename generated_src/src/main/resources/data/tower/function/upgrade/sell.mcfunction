scoreboard players operation #temp player.id = @s player.id
scoreboard players set #temp tower 0
execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id run function tower:upgrade/sell_tower
execute if score #temp tower matches 0 run return run tellraw @s {text:"판매할 타워가 없습니다.",color:"red"}
scoreboard players operation @s money += #sell_money tower
tellraw @s {text:"타워를 판매했습니다.",color:"green"}
tellraw @s {text:"판매 금액: ",color:"yellow",extra:[{score:{name:"#sell_money",objective:"tower"}},]}