#아이템을 기반으로 카트의 커스텀 성능 설정
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartspeed run data get entity @s equipment.offhand.components.minecraft:custom_data.speed
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartaccel run data get entity @s equipment.offhand.components.minecraft:custom_data.accel
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartboost run data get entity @s equipment.offhand.components.minecraft:custom_data.boost

#실제 내부적으로 적용되는 코너링 = 300 - 수치, 높을수록 안 좋음
scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartcorner 300
execute store result score #kartcornertemp kartcorner run data get entity @s equipment.offhand.components.minecraft:custom_data.corner
scoreboard players operation @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartcorner -= #kartcornertemp kartcorner

execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartdrift run data get entity @s equipment.offhand.components.minecraft:custom_data.drift
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartboostgaugecharge run data get entity @s equipment.offhand.components.minecraft:custom_data.gauge
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartboostduration run data get entity @s equipment.offhand.components.minecraft:custom_data.boosttime
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartdefense run data get entity @s equipment.offhand.components.minecraft:custom_data.defense
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-draft run data get entity @s equipment.offhand.components.minecraft:custom_data.draft

scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartmaxboostcount 2
execute if data entity @s equipment.offhand.components.minecraft:custom_data.maxboostcount store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartmaxboostcount run data get entity @s equipment.offhand.components.minecraft:custom_data.maxboostcount

#출력 반영
execute store result storage temp kart-limit.limit int 1.0 run scoreboard players get @s kart-performance-limit-level
execute if score @s kart-performance-limit-level matches 1 run function kartmain:summon/limit-kart-spec {limit:985}
execute if score @s kart-performance-limit-level matches 2 run function kartmain:summon/limit-kart-spec {limit:970}
execute if score @s kart-performance-limit-level matches 3 run function kartmain:summon/limit-kart-spec {limit:955}
execute if score @s kart-performance-limit-level matches 4 run function kartmain:summon/limit-kart-spec {limit:940}
execute if score @s kart-performance-limit-level matches 5 run function kartmain:summon/limit-kart-spec {limit:925}
execute if score @s kart-performance-limit-level matches 6 run function kartmain:summon/limit-kart-spec {limit:910}
execute if score @s kart-performance-limit-level matches 7 run function kartmain:summon/limit-kart-spec {limit:895}
execute if score @s kart-performance-limit-level matches 8 run function kartmain:summon/limit-kart-spec {limit:880}
execute if score @s kart-performance-limit-level matches 9 run function kartmain:summon/limit-kart-spec {limit:865}
execute if score @s kart-performance-limit-level matches 10 run function kartmain:summon/limit-kart-spec {limit:850}

#성능 조절
scoreboard players add @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartboostgaugecharge 2
    #벽충돌 크기 조절
    execute store result score #size kartdefense run data get entity @s equipment.offhand.components.minecraft:custom_data.size
    execute if score #size kartdefense matches 0 run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add kart-small-size
    execute if score #size kartdefense matches 1 run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add kart-mid-size
                                              #2는 기본 크기
    execute if score #size kartdefense matches 3 run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add kart-large-size

    #엔진
    scoreboard players set @e[tag=kartmobil,tag=karttemp,type=#kartmobil:kartmobil] kart-engine 0
    execute unless score @s kart-engine = @s kart-engine run scoreboard players set @s kart-engine 0
    scoreboard players operation @e[tag=kartmobil,tag=karttemp,type=#kartmobil:kartmobil] kart-engine = @s kart-engine

    #타이어
    execute if score @s kart-tire matches 1 run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add kart-hairpin-unlimit

    #크기
    scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-hitbox-front 10
    scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-hitbox-rear 7
    scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-hitbox-left 7
    scoreboard players set @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-hitbox-right 7

    #특수능력 / kart-special-ability
    tag @s add kart-summon-player
    attribute @n[tag=kartsaddle,tag=karttemp] minecraft:scale base set 1
    execute as @n[distance=..0.01,tag=kart-special-ability,type=#kartmobil:kartmodels] at @s run function kartmain:summon/apply-special-ability
    tag @s remove kart-summon-player

        #순부차 순부 사용 가능 여부(A2, PRO는 엔진별 스펙에서 활성화)
        execute as @e[tag=kartmobil,tag=force-instant-boost,distance=..0.1,limit=1,type=#kartmobil:kartmobil] as @n[tag=kartsaddle] run function kartmobil:s2c-value/skill-instant-boost/true-1

    #히트박스 r1 R1 게산
    execute as @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] run function kartmain:summon/hitbox/calc-hitbox-radius

    #특수능력 - 업앤다운 엔진고정
    execute as @n[tag=updown-engine-fix,distance=..0.01,type=item_display] store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine run data get entity @s data.engine-fix

#데이터 캐리어에 아이템 정보 등록
data modify entity @n[tag=kartdatacarrier,tag=karttemp,type=item_display] item set from entity @s equipment.offhand
data modify entity @n[tag=kartdatacarrier,tag=karttemp,type=item_display] data.itemdata set from entity @s equipment.offhand
item replace entity @s weapon.offhand with air

#엔진에 따른 버프
execute as @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] run function kartmain:summon/kart-spec-consider-engine/main

#드래프트 지속시간 계산
scoreboard players operation @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-draft-time = @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-draft
scoreboard players operation @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-draft-time /= #kart2 kartmain
scoreboard players add @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-draft-time 100