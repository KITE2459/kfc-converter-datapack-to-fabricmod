scoreboard players add @s kartdrifttime 1

#속도계 패치 전의 게충 시뮬레이션
scoreboard players operation #kartboostgauge kartmove = @s kartmove
scoreboard players operation #kartboostgauge kartmove /= #kart360 kartmain

    #키엔진 동적게충 / 동적게충
    execute if score @s kart-engine matches 1002 if score #floor-distance floor-distance matches ..1 if score #kartspeed kartmove matches 25.. unless score #kartangle kartmain matches -250..250 run scoreboard players operation @s kartboostgauge += #kartboostgauge kartmove
    execute unless score @s kart-engine matches 1002..1003 unless score @s kart-engine matches 1008 if score #floor-distance floor-distance matches ..1 if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartboostgauge += #kartboostgauge kartmove
    
    #1.0엔진 - 공중 게충 부활
    execute if score @s kart-engine matches 7 unless score #floor-distance floor-distance matches ..1 if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartboostgauge += #kartboostgauge kartmove
    execute if score @s kart-engine matches 7 unless score #floor-distance floor-distance matches ..1 if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartboostgauge += @s kartboostgaugecharge

    #기본 게충 / 부스터 패드
    execute unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1008 if score #floor-distance floor-distance matches ..1 if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartboostgauge += @s kartboostgaugecharge
    execute unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1008 if score #floor-distance floor-distance matches ..1 if score @s kartboostpadtime matches 1.. run scoreboard players add @s kartboostgauge 25

#감속[0.833배 하기 위해 일단 더하고 1/6을 뻄] / 1.0, F1 엔진은 1배 그대로
function kartmobil:move/steering/drift/drift-deceleration

#톡톡이
function kartmobil:move/steering/drift/drag-accel/main

#저속 무감속
execute if score #kartspeed kartmove matches ..-25 run scoreboard players operation @s kartmove += #kartangleabs kartmain

#이펙트
execute if entity @s[tag=kart-hairpin-unlimit] unless score @s kart-engine matches 1006 on passengers at @s[tag=kartdatacarrier,type=item_display] on vehicle run function kartmobil:move/steering/drift/skidparticle/main


execute unless score @s kart-engine matches 1006 on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 on vehicle run function kartmobil:move/steering/drift/skidparticle/main

execute if score @s kart-engine matches 1006 on passengers at @s[tag=kartdatacarrier,type=item_display] on vehicle run function kartmobil:move/steering/drift/skidparticle/f1-drifteffect