#플레이어의 스코어는 스페이스를 누르고 있는 시간
scoreboard players add @p[tag=kartpassenger,predicate=kartmobil:space] kart-wasd-rotation-speed 1
scoreboard players set @p[tag=kartpassenger,predicate=!kartmobil:space] kart-wasd-rotation-speed 0

#좌우회전
execute if entity @s[tag=!kart-mario-a] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run scoreboard players add @s kart-wasd-rotation-speed 22
execute if entity @s[tag=!kart-mario-d] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run scoreboard players remove @s kart-wasd-rotation-speed 22

execute if entity @s[tag=!kart-mario-a,scores={kartmove=..2500}] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run scoreboard players add @s kart-wasd-rotation-speed 15
execute if entity @s[tag=!kart-mario-d,scores={kartmove=..2500}] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run scoreboard players remove @s kart-wasd-rotation-speed 15

execute if entity @s[tag=kart-mario-a] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run scoreboard players add @s kart-wasd-rotation-speed 5
execute if entity @s[tag=kart-mario-d] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run scoreboard players remove @s kart-wasd-rotation-speed 5

#미니 점프로 드리프트 시작
execute if entity @s[tag=!kart-drifting,tag=!kart-jumped,tag=kart-space-press] run function kartmobil:move/steering/wasd-control/ds-start-drift
execute if entity @s[tag=!kart-drifting,tag=kart-jumped,tag=kart-space-pressed] run function kartmobil:move/steering/wasd-control/ds-start-drift

#미니 터보 충전
execute if entity @p[tag=kartpassenger,predicate=!kartmobil:a,predicate=!kartmobil:d] run scoreboard players set @s ds-steering-type 0
execute if score @s ds-steering-type matches 1 if entity @p[tag=kartpassenger,predicate=kartmobil:d] run scoreboard players set @s ds-steering-type 3
execute if score @s ds-steering-type matches 2 if entity @p[tag=kartpassenger,predicate=kartmobil:a] run scoreboard players set @s ds-steering-type 4

execute if entity @p[tag=kartpassenger,predicate=kartmobil:a,predicate=!kartmobil:d] run scoreboard players set @s ds-steering-type 1
execute if entity @p[tag=kartpassenger,predicate=kartmobil:d,predicate=!kartmobil:a] run scoreboard players set @s ds-steering-type 2

execute if score #floor-distance floor-distance matches ..2 if entity @s[tag=kart-drifting] unless score @s ds-steering-prev-type matches 0 unless score @s ds-steering-prev-type = @s ds-steering-type if score @s ds-steering-time matches 3.. run scoreboard players add @s kartboostgauge 501

execute unless score @s ds-steering-prev-type = @s ds-steering-type run scoreboard players set @s ds-steering-time 0
scoreboard players operation @s ds-steering-prev-type = @s ds-steering-type
scoreboard players add @s ds-steering-time 1

#드리프트 중
execute if entity @s[tag=kart-mario-a] run scoreboard players remove @s kart-wasd-rotation-speed 15
execute if entity @s[tag=kart-mario-d] run scoreboard players add @s kart-wasd-rotation-speed 15

    #드리프트 초반엔 더 잘 꺾임
    execute if entity @s[tag=kart-mario-a] if score @s kartdrifttime matches ..3 run scoreboard players remove @s kart-wasd-rotation-speed 25
    execute if entity @s[tag=kart-mario-d] if score @s kartdrifttime matches ..3 run scoreboard players add @s kart-wasd-rotation-speed 25

#execute if entity @s[tag=kart-mario-a] if entity @p[tag=kartpassenger,predicate=kartmobil:a,predicate=kartmobil:space] run scoreboard players remove @s kart-wasd-rotation-speed 0
#execute if entity @s[tag=kart-mario-d] if entity @p[tag=kartpassenger,predicate=kartmobil:d,predicate=kartmobil:space] run scoreboard players add @s kart-wasd-rotation-speed 0

#드리프트 끝
execute if entity @s[tag=kart-space-release] if entity @s[tag=kart-drifting] run function kartmobil:move/steering/wasd-control/ds-end-drift

#지수감쇄
execute store result storage minecraft:kartmain rotationSpeed int 0.65 run scoreboard players get @s kart-wasd-rotation-speed
execute store result score @s kart-wasd-rotation-speed run data get storage minecraft:kartmain rotationSpeed

#회전
execute store result storage minecraft:kartmain rotationSpeed float 0.1 run scoreboard players get @s kart-wasd-rotation-speed
function kartmobil:move/steering/wasd-control/rotate-player with storage minecraft:kartmain
