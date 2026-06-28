scoreboard players reset #from-new-collision kartcollisiontime

#모델링 떨림(쿵)
execute as @s[scores={kartdefense=..70}] on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers at @s run rotate @s ~ -7.5
execute as @e[tag=carB,scores={kartdefense=..70}] on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers at @s run rotate @s ~ -7.5

#충돌 쿨타임 설정
scoreboard players set @s kartcollisiontime 8
scoreboard players set @n[tag=carB,type=#kartmobil:kartmobil] kartcollisiontime 8

#둘의 속도를 교환(질량이 1인 완전탄성충돌)
function kartmain:collision-old/swapspeed/main

# F1 모드일 때 충돌 시 ERS 게이지 충전
execute if score @s kart-engine matches 1006 run scoreboard players add @s kart-ers-gauge 200
execute if score @s kart-ers-gauge matches 1000.. run scoreboard players set @s kart-ers-gauge 1000

#소리
playsound minecraft:entity.player.attack.crit neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.9 2
playsound minecraft:entity.zombie.attack_iron_door neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.1 2
playsound minecraft:entity.generic.big_fall neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.9 1
#playsound minecraft:entity.generic.small_fall neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.9 1
playsound minecraft:entity.zombie.attack_wooden_door neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.9 2

#매드 크래시
execute if entity @n[tag=carA,tag=mad-crash,type=#kartmobil:kartmobil] run function kartmain:collision-old/mad-crash
execute if entity @n[tag=carB,tag=mad-crash,type=#kartmobil:kartmobil] run function kartmain:collision-old/mad-crash

#크래시 게이지(속도가 40보다 낮은 사람과 높은 사람이 충돌할 시, 손해를 본 쪽에게 게이지 70% 충전)
execute if entity @s[scores={kartmove=16200..}] run scoreboard players add @n[tag=carB,scores={kartmove=..13500},type=#kartmobil:kartmobil] kartboostgauge 1400
execute if entity @n[tag=carB,scores={kartmove=16200..},type=#kartmobil:kartmobil] run scoreboard players add @s[scores={kartmove=..13500}] kartboostgauge 1400

