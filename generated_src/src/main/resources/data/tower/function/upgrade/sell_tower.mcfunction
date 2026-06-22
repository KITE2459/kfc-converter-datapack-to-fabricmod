# 타워 판매
# 돈 추가
execute store result score #sell_money tower run data get entity @n[tag=tower.data] data.sell_price

# 타워 제거
kill @e[distance=..0.001,tag=tower,tag=!tower.core]
kill @s

scoreboard players set #temp tower 1