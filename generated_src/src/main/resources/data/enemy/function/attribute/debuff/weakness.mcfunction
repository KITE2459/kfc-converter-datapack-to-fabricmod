# 취약 적용
execute if score @s enemy.state.weakness matches 25.. if score @s enemy.defence matches 1.. run execute store result storage temp weakness.defence int 0.075 run scoreboard players get @s enemy.defence
execute if score @s enemy.state.weakness matches 25.. if score @s enemy.defence matches 1.. run execute store result score weakness-apply enemy.defence run data get storage temp weakness.defence
execute if score @s enemy.state.weakness matches 25.. if score @s enemy.defence matches ..0 run scoreboard players set weakness-apply enemy.defence 1
execute if score @s enemy.state.weakness matches 25.. unless score weakness-apply enemy.defence matches 1.. run scoreboard players set weakness-apply enemy.defence 1
execute if score @s enemy.state.weakness matches 25.. run scoreboard players operation @s enemy.defence -= weakness-apply enemy.defence

# 데이터 보기용 
execute if score @s enemy.state.weakness matches 25.. run scoreboard players operation weakness statistics += weakness-apply enemy.defence
execute if score @s enemy.state.weakness matches 25.. run scoreboard players remove @s enemy.state.weakness 25