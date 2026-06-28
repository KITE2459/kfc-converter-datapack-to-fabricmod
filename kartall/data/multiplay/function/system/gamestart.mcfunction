scoreboard players reset #max-rank multi-rank

function timerpack:start
data modify storage minecraft:timermain multi-record set value []
data modify storage minecraft:timermain multi-record-subtick set value []

# 게임 시작 로그 출력
function multiplay:system/game/gamestart-log-main

#모드
execute if score inf-boost multi-main matches 1 run scoreboard players set @e[tag=kartmobil,type=#kartmobil:kartmobil] kartboostgaugecharge 9999
execute if score no-collision multi-main matches 1 run tag @e[tag=kartmobil,type=#kartmobil:kartmobil] add no-collision
execute if score no-draft multi-main matches 1 run tag @e[tag=kartmobil,type=#kartmobil:kartmobil] add no-draft
execute if score drag-accel multi-main matches 1 run tag @e[tag=kartmobil,type=#kartmobil:kartmobil] add drag-accel
execute if score mad-crash multi-main matches 1 run tag @e[tag=kartmobil,type=#kartmobil:kartmobil] add mad-crash
execute if score wall-crash multi-main matches 1 run tag @e[tag=kartmobil,type=#kartmobil:kartmobil] add wall-crash

#출부
execute as @a[tag=kart-multi-player] on vehicle on vehicle run function kartmobil:move/boost/startboost/use

#플레이어에게 아이디 부여하기 - 관전을 위해
scoreboard players reset max-id multi-spectator-id

execute as @a[tag=kart-multi-player] store result score @s multi-spectator-id run scoreboard players add max-id multi-spectator-id 1
execute as @a[tag=kart-multi-player] store result score @s multi-instant-rank run scoreboard players add max-id multi-spectator-id 1
execute store result score #player-count multi-spectate-time if entity @a[tag=kart-multi-player]

#모드
team leave @a[tag=kart-multi-player]

#팀전
execute if score team-battle multi-main matches 1 as @a[tag=kart-multi-player] on vehicle on vehicle run tag @s add kart-teamboost
execute if score team-battle multi-main matches 1 as @a[tag=kart-multi-player,scores={kart-team=0}] on vehicle on vehicle run scoreboard players set @s kart-team 0
execute if score team-battle multi-main matches 1 as @a[tag=kart-multi-player,scores={kart-team=1}] on vehicle on vehicle run scoreboard players set @s kart-team 1

execute if score team-battle multi-main matches 1 as @a[tag=kart-multi-player,scores={kart-team=0}] run team join redteam @s
execute if score team-battle multi-main matches 1 as @a[tag=kart-multi-player,scores={kart-team=1}] run team join blueteam @s

execute if score team-battle multi-main matches 1 run scoreboard players reset #redteam-gauge kart-teamboost-gauge
execute if score team-battle multi-main matches 1 run scoreboard players reset #blueteam-gauge kart-teamboost-gauge
execute if score team-battle multi-main matches 1 run clear @a[tag=kart-multi-player] minecraft:soul_campfire

#순위 및 닉네임 텍스트
    execute as @a[tag=kart-multi-player] at @s run function multiplay:system/gamestart-headup-text

    #R키감지용 아머스탠드
    #execute as @a[tag=kart-multi-player] at @s run summon armor_stand ~ ~ ~ {Tags:["rkey-armor"],Invisible:1b}
    #execute as @a[tag=kart-multi-player] at @s run summon armor_stand ~ ~ ~ {Pose:{Head:[180f,0f,0f]},Invisible:1b,Tags:["rkey-armor"],attributes:[{id:"minecraft:scale",base:0.53}]}
    #execute as @a[tag=kart-multi-player] at @s as @e[tag=rkey-armor,sort=nearest,limit=1,distance=..0.00001] run ride @s mount @n[tag=kartsaddle]
    #tellraw @a[tag=kart-multi-player,gamemode=creative] "크리에이티브 모드에서는 재배치(R키)를 사용할 수 없습니다."
    #execute at @a[tag=kart-multi-player,gamemode=creative] run kill @n[tag=rkey-armor,distance=..0.3]

execute if score team-battle multi-main matches 0 as @a[tag=kart-multi-player] run team join multi-hidenick @s 
clear @a minecraft:jigsaw