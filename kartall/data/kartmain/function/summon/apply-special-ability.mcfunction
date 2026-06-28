#@s = kart-special-ability

#엔진 고정
execute if data entity @s data.engine-fix run tellraw @p[tag=kart-summon-player] {translate:"[🔒] 특정 엔진으로 고정된 카트입니다.","color":"red"}
execute if data entity @s data.engine-fix run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add kart-engine-fixed

execute if data entity @s data.engine-fix store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine run data get entity @s data.engine-fix

#순부
execute if data entity @s data.instant-boost run tellraw @p[tag=kart-summon-player] [{translate:"이 카트는 ","color":"light_purple"},{translate:"순간 부스터","color":"gold"},{translate:"를 사용할 수 있습니다.","color":"light_purple"}]
execute if data entity @s data.instant-boost run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add force-instant-boost

#버스트 순부
execute if data entity @s data.burst-instant-boost if score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine matches 6 run tellraw @p[tag=kart-summon-player] [{translate:"이 카트의 ","color":"light_purple"},{translate:"강화 순간 부스터","color":"gold"},{translate:"가 더욱 빨라졌습니다.","color":"light_purple"}]
execute if data entity @s data.burst-instant-boost run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add burst-instant-boost

#미끄럼 방지
execute if data entity @s data.ignore-slip run tellraw @p[tag=kart-summon-player] [{translate:"이 카트는 ","color":"light_purple"},{translate:"미끄러운 구간","color":"gold"},{translate:"의 영향을 받지 않습니다.","color":"light_purple"}]
execute if data entity @s data.ignore-slip run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add ignore-slip

#바이크 여부
execute if data entity @s data.is-bike run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add kartbike

#플레이어 숨기기 여부
execute if data entity @s data.hide-player if items entity @p[tag=kart-summon-player] armor.head leather_helmet run item replace entity @p[tag=kart-summon-player] armor.head with air
execute if data entity @s data.hide-player run tag @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] add karthideplayer

#히트박스
#execute if data entity @s data.hitbox run data modify entity @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] data.hitbox set from entity @s data.hitbox
execute if data entity @s data.hitbox store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-hitbox-left run data get entity @s data.hitbox.left
execute if data entity @s data.hitbox store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-hitbox-right run data get entity @s data.hitbox.right
execute if data entity @s data.hitbox store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-hitbox-front run data get entity @s data.hitbox.front
execute if data entity @s data.hitbox store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-hitbox-rear run data get entity @s data.hitbox.rear
    execute as @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] run function kartmain:summon/hitbox/get-hitbox


#bgm-fix: "sans" - 브금 함수에서 읽기

#안장높이조절
execute store result score #saddle-height kartdefense run data get entity @s data.saddle-height
execute store result entity @n[tag=kartsaddle,tag=karttemp] attributes[{id:"minecraft:scale"}].base float 0.0116665 run scoreboard players add #saddle-height kartdefense 86