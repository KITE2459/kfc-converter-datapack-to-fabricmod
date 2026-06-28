
#충돌 쿨타임 설정
execute store result score @s kartcollisiontime run random value 6..8
execute store result score @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartcollisiontime run random value 6..8

#충돌 연산 메인
function kartmain:collision/swapspeed/main

#F1 모드일 때 충돌 시 ERS 게이지 충전
execute if score @s kart-engine matches 1006 run scoreboard players add @s kart-ers-gauge 200
execute if score @s kart-ers-gauge matches 1000.. run scoreboard players set @s kart-ers-gauge 1000

#모델링 떨림(쿵)
#execute if score #magnitude-relative kartcollisiontime matches 15000.. on passengers if entity @s[tag=kartmodelsaddle] at @s run rotate @s ~ -5
#execute if score #magnitude-relative kartcollisiontime matches 15000.. as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] on passengers if entity @s[tag=kartmodelsaddle] at @s run rotate @s ~ -5

#소리
execute if score #magnitude-relative kartcollisiontime matches 15000.. run playsound minecraft:entity.player.attack.crit neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.9 2
execute if score #magnitude-relative kartcollisiontime matches 15000.. run playsound minecraft:entity.zombie.attack_iron_door neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.1 2
#execute if score #magnitude-relative kartcollisiontime matches 15000.. run playsound minecraft:entity.generic.big_fall neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.9 1
execute if score #magnitude-relative kartcollisiontime matches 15000.. run playsound minecraft:entity.zombie.attack_wooden_door neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.9 2

execute if score #magnitude-relative kartcollisiontime matches 10000..14999 run playsound minecraft:entity.player.attack.crit neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.7 2
execute if score #magnitude-relative kartcollisiontime matches 10000..14999 run playsound minecraft:entity.zombie.attack_wooden_door neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.7 2

execute if score #magnitude-relative kartcollisiontime matches 2500..9999 run playsound minecraft:entity.player.attack.crit neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.5 2
execute if score #magnitude-relative kartcollisiontime matches 2500..9999 run playsound minecraft:entity.zombie.attack_wooden_door neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.5 2

#갓겜모드
execute if entity @e[tag=carA,tag=mad-crash,limit=1,type=#kartmobil:kartmobil] run function kartmain:collision/mad-crash
execute if entity @e[tag=carB,tag=mad-crash,limit=1,type=#kartmobil:kartmobil] run function kartmain:collision/mad-crash
