
execute on vehicle on vehicle run scoreboard players add @s kartmove 10
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=20..] on vehicle on vehicle run scoreboard players add @s kartmove 5
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=25..] on vehicle on vehicle run scoreboard players add @s kartmove 5

execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=30..] on vehicle on vehicle run scoreboard players add @s kartmove 10
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=40..] on vehicle on vehicle run scoreboard players add @s kartmove 10
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=50..] on vehicle on vehicle run scoreboard players add @s kartmove 15
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=60..] on vehicle on vehicle run scoreboard players add @s kartmove 15
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=70..] on vehicle on vehicle run scoreboard players add @s kartmove 20
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=80..] on vehicle on vehicle run scoreboard players add @s kartmove 20
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=90..] on vehicle on vehicle run scoreboard players add @s kartmove 25
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=100..] on vehicle on vehicle run scoreboard players add @s kartmove 25

execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=20..] run scoreboard players add @s kartdrift 2
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=40..] run scoreboard players add @s kartdrift 8
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=60..] run scoreboard players add @s kartdrift 8
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=80..] run scoreboard players add @s kartdrift 8
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=100..] run scoreboard players add @s kartdrift 8

#견인 증가
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=25..] on vehicle on vehicle run scoreboard players add @s[tag=multi-increase-pull-accel] kartmove 10
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=35..] on vehicle on vehicle run scoreboard players add @s[tag=multi-increase-pull-accel] kartmove 10
execute if entity @a[tag=kart-multi-player,scores={multi-instant-rank=1},distance=45..] on vehicle on vehicle run scoreboard players add @s[tag=multi-increase-pull-accel] kartmove 10