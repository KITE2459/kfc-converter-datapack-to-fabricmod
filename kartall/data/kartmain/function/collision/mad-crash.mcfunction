#충돌 후 라이더의 시점도 회전
execute as @e[tag=carA,limit=1,tag=mad-crash,type=#kartmobil:kartmobil] at @s rotated as @n[tag=kartdirection] on passengers if entity @s[tag=kartsaddle] on passengers run rotate @s[type=player] ~ ~
execute as @e[tag=carB,limit=1,tag=mad-crash,type=#kartmobil:kartmobil] at @s rotated as @n[tag=kartdirection] on passengers if entity @s[tag=kartsaddle] on passengers run rotate @s[type=player] ~ ~

#빙글빙글 도는 갓겜(5%)
execute if predicate kartmobil:0.1random if predicate kartmobil:0.5random run tag @e[tag=car-collision,limit=1,sort=random] add mad-crash-spin
execute if predicate kartmobil:0.5random run tag @e[tag=car-collision,tag=mad-crash-spin,limit=1,sort=random] add mad-crash-spin-reverse

#20% - 10% - 2% - 0.6%
execute if predicate kartmobil:0.2random run scoreboard players remove @e[tag=car-collision,limit=1,sort=random] kartmovey 750
execute if predicate kartmobil:0.1random run scoreboard players remove @e[tag=car-collision,limit=1,sort=random] kartmovey 1500

execute if predicate kartmobil:0.1random if predicate kartmobil:0.2random run scoreboard players remove @e[tag=car-collision,limit=1,sort=random] kartmovey 3000
execute if predicate kartmobil:0.1random if predicate kartmobil:0.1random if predicate kartmobil:0.6random run scoreboard players remove @e[tag=car-collision,limit=1,sort=random] kartmovey 4000