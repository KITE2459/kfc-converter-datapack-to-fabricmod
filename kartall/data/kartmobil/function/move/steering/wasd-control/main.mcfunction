#플레이어의 스코어는 스페이스를 누르고 있는 시간
scoreboard players add @p[tag=kartpassenger,predicate=kartmobil:space] kart-wasd-rotation-speed 1
scoreboard players set @p[tag=kartpassenger,predicate=!kartmobil:space] kart-wasd-rotation-speed 0

#드리프트 시작
execute if entity @s[tag=kart-space-press,tag=!kart-drifting] run function kartmobil:move/steering/drift/drift-start

#좌우회전
execute if entity @p[tag=kartpassenger,predicate=kartmobil:d] run scoreboard players add @s kart-wasd-rotation-speed 8
execute if entity @p[tag=kartpassenger,predicate=kartmobil:a] run scoreboard players remove @s kart-wasd-rotation-speed 8

#드리프트 중 좌우회전
execute if entity @s[tag=kart-drifting] if entity @p[tag=kartpassenger,predicate=kartmobil:d,predicate=!kartmobil:space] run scoreboard players add @s kart-wasd-rotation-speed 10
execute if entity @s[tag=kart-drifting] if entity @p[tag=kartpassenger,predicate=kartmobil:a,predicate=!kartmobil:space] run scoreboard players remove @s kart-wasd-rotation-speed 10

#드리프트 키 홀드
execute if entity @p[tag=kartpassenger,predicate=kartmobil:d,predicate=kartmobil:space] run scoreboard players add @s kart-wasd-rotation-speed 32
execute if entity @p[tag=kartpassenger,predicate=kartmobil:a,predicate=kartmobil:space] run scoreboard players remove @s kart-wasd-rotation-speed 32

scoreboard players add @p[tag=kartpassenger,predicate=kartmobil:space,scores={kart-wasd-rotation-speed=1..3}] kartdrift 32
scoreboard players add @p[tag=kartpassenger,predicate=kartmobil:space,scores={kart-wasd-rotation-speed=4..5}] kartdrift 22
scoreboard players add @p[tag=kartpassenger,predicate=kartmobil:space,scores={kart-wasd-rotation-speed=6..6}] kartdrift 12

execute if entity @p[tag=kartpassenger,predicate=kartmobil:d,predicate=kartmobil:space,scores={kart-wasd-rotation-speed=5..}] run scoreboard players add @s kart-wasd-rotation-speed 3
execute if entity @p[tag=kartpassenger,predicate=kartmobil:a,predicate=kartmobil:space,scores={kart-wasd-rotation-speed=5..}] run scoreboard players remove @s kart-wasd-rotation-speed 3

execute if entity @p[tag=kartpassenger,predicate=kartmobil:d,predicate=kartmobil:space,scores={kart-wasd-rotation-speed=10..}] run scoreboard players add @s kart-wasd-rotation-speed 3
execute if entity @p[tag=kartpassenger,predicate=kartmobil:a,predicate=kartmobil:space,scores={kart-wasd-rotation-speed=10..}] run scoreboard players remove @s kart-wasd-rotation-speed 3

#지수감쇄
execute store result storage minecraft:kartmain rotationSpeed int 0.775 run scoreboard players get @s kart-wasd-rotation-speed
execute store result score @s kart-wasd-rotation-speed run data get storage minecraft:kartmain rotationSpeed

#회전
execute store result storage minecraft:kartmain rotationSpeed float 0.1 run scoreboard players get @s kart-wasd-rotation-speed
function kartmobil:move/steering/wasd-control/rotate-player with storage minecraft:kartmain

