execute if score @s kartmaxboostcount matches 3 run function kartmain:summon/kart-spec-consider-engine/dummy/f1-bike
execute if score @s kartmaxboostcount matches 3 run return 0

execute store result storage temp temp int 1.8 run scoreboard players get @s kartdrift
execute store result score @s kartdrift run data get storage temp temp
scoreboard players operation @s f1-kartdrift-origin = @s kartdrift

execute store result storage temp temp int 1 run scoreboard players get @s kartcorner
execute store result score @s kartcorner run data get storage temp temp

execute store result storage temp temp int 2 run scoreboard players get @s kartdefense
execute store result score @s kartdefense run data get storage temp temp

execute store result storage temp temp int 0.5 run scoreboard players get @s kart-draft
execute store result score @s kart-draft run data get storage temp temp

#원래 밸런스 복구
scoreboard players remove @s kartspeed 84
scoreboard players remove @s kartboost 100
scoreboard players add @s kartaccel 100

execute store result storage temp temp int 1.06 run scoreboard players get @s kartspeed
execute store result score @s kartspeed run data get storage temp temp

execute store result storage temp temp int 0.49 run scoreboard players get @s kartaccel
execute store result score @s kartaccel run data get storage temp temp

execute store result storage temp temp int 0.5 run scoreboard players get @s kartboostgaugecharge
execute store result score @s kartboostgaugecharge run data get storage temp temp

# ERS 충전량 상향
scoreboard players add @s kartboostgaugecharge 2

execute store result storage temp temp int -0.09375 run scoreboard players get @s kartboostduration
execute store result score @s kartboostduration run data get storage temp temp
scoreboard players add @s kartboostduration 8

#시작 시 부스터 한 개 주기
scoreboard players set @s kartboostcount 1

tag @s remove force-instant-boost

scoreboard players set @s kart-ers-gauge 1000