#[법선(내가 상대방 차를 바라보는 방향)과 카트 각각의 방향의 차이] 의 차이를 구하기

summon marker ~ ~ ~ {Tags:["kart-normal-line"]}

#a
execute positioned as @n[tag=carA,type=#kartmobil:kartmobil] facing entity @n[tag=carB,type=#kartmobil:kartmobil] feet run rotate @n[tag=kart-normal-line,type=marker] ~ 0
execute store result score #delta-normal-a kartcollisiontime run data get entity @n[tag=kart-normal-line,type=marker] Rotation[0]
    #get delta
    scoreboard players operation #delta-normal-a kartcollisiontime -= #direction-a kartcollisiontime
    execute if score #delta-normal-a kartcollisiontime matches 180.. run scoreboard players remove #delta-normal-a kartcollisiontime 360
    execute if score #delta-normal-a kartcollisiontime matches ..-180 run scoreboard players add #delta-normal-a kartcollisiontime 360
    execute if score #delta-normal-a kartcollisiontime matches ..-1 run scoreboard players operation #delta-normal-a kartcollisiontime *= #kart-1 kartmain

#b
execute positioned as @n[tag=carB,type=#kartmobil:kartmobil] facing entity @n[tag=carA,type=#kartmobil:kartmobil] feet run rotate @n[tag=kart-normal-line,type=marker] ~ 0
execute store result score #delta-normal-b kartcollisiontime run data get entity @n[tag=kart-normal-line,type=marker] Rotation[0]
    #get delta
    scoreboard players operation #delta-normal-b kartcollisiontime -= #direction-b kartcollisiontime
    execute if score #delta-normal-b kartcollisiontime matches 180.. run scoreboard players remove #delta-normal-b kartcollisiontime 360
    execute if score #delta-normal-b kartcollisiontime matches ..-180 run scoreboard players add #delta-normal-b kartcollisiontime 360
    execute if score #delta-normal-b kartcollisiontime matches ..-1 run scoreboard players operation #delta-normal-b kartcollisiontime *= #kart-1 kartmain

#get delta of delta
scoreboard players operation #delta-normal-a kartcollisiontime -= #delta-normal-b kartcollisiontime
execute if score #delta-normal-a kartcollisiontime matches 180.. run scoreboard players remove #delta-normal-a kartcollisiontime 360
execute if score #delta-normal-a kartcollisiontime matches ..-180 run scoreboard players add #delta-normal-a kartcollisiontime 360
execute if score #delta-normal-a kartcollisiontime matches ..-1 run scoreboard players operation #delta-normal-a kartcollisiontime *= #kart-1 kartmain

#[0 ~ 180] / 12 = [0 ~ 15]
scoreboard players set #100e-offset kartcollisiontime 180
scoreboard players operation #100e-offset kartcollisiontime -= #delta-normal-a kartcollisiontime
scoreboard players operation #100e-offset kartcollisiontime /= #kart12 kartmain

tellraw @a[tag=kart-debug] {"score":{"name":"#100e-offset","objective":"kartcollisiontime"}}

kill @e[tag=kart-normal-line,type=marker]