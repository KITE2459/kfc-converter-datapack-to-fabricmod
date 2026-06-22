# 보상 지급
$scoreboard players add @a money $(money)
scoreboard players add killcount info 1

# 스컬크 좀비 회복
scoreboard players add @e[tag=catalyst-zombie, distance=..3] enemy.hp 150

# 어둠 속성 장판 생성
execute if entity @s[tag=enemy.attribute.dark] at @s run function enemy:ability/death/dark-attribute

# 사망 처리
$execute if entity @s[tag=enemy.skill-trigger.death] run function enemy:ability/death/$(id)
execute if entity @s[type=!marker] on passengers run kill @s
execute if entity @s[type=marker] on vehicle on passengers run kill @s
kill @s