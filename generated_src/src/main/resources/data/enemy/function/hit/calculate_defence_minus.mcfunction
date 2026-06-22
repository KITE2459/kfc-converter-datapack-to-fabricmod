# 경감율 연산
scoreboard players set #100 enemy.defence 100
scoreboard players set #buffer enemy.defence 10000
scoreboard players set #buffer3 enemy.defence -1
scoreboard players operation #buffer3 enemy.defence *= @s enemy.defence
scoreboard players operation #buffer enemy.defence *= #buffer3 enemy.defence
scoreboard players set #buffer2 enemy.defence 100
scoreboard players operation #buffer2 enemy.defence += #buffer3 enemy.defence
scoreboard players operation #buffer enemy.defence /= #buffer2 enemy.defence

# 피해량 계산
scoreboard players operation #buffer enemy.defence *= #damage enemy.hp
scoreboard players set 10000 enemy.defence 10000
scoreboard players operation #buffer enemy.defence /= 10000 enemy.defence
scoreboard players operation #damage enemy.hp += #buffer enemy.defence