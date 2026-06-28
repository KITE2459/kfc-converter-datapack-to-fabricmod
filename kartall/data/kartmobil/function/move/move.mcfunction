tag @s add kartself
execute on passengers run tag @s[tag=kartdirection,type=item_display] add kartdirectiontemp

#공기저항 초기화
scoreboard players set #kartairresistconst kartmove 0

#각도 차이용 정보 저장(플레이어 nbt접근 우회), 10을 곱함
execute store result score #kartangle kartmain on passengers if entity @s[tag=kartdatacarrier,type=item_display] run data get entity @s Rotation[0] 10
execute store result score #kartdirectionangle kartmain on passengers if entity @s[tag=kartdirectiontemp,type=item_display] run data get entity @s Rotation[0] 10

#조향각 및 조향각의 절댓값 구하기
function kartmobil:move/steering/getsteerangle

#부스터
function kartmobil:move/boost/main

#스티어링 및 드리프트
function kartmobil:move/steering/main

#고정점프대 고스트
scoreboard players set @s[tag=kart-direction-fixed-by-pad] kartcollisiontime 15

#뛰라이더
execute unless score @s kart-engine matches 7 if entity @s[tag=kart-use-run-anime] run function kartmobil:move/run-anime/main

    #A2/PRO/force-instant-boost - 순부
    execute if score @s kart-engine matches 6 run function kartmobil:move/instant-boost/instant-boost
    execute if score @s kart-engine matches 8 run function kartmobil:move/instant-boost/instant-boost

    execute if entity @s[tag=force-instant-boost] run function kartmobil:move/instant-boost/force-instant-boost
    execute if entity @s[tag=!a2-using-instant-boost] on passengers if score @s[tag=kartdirection] kartmove matches 1.. run scoreboard players remove @s kartmove 1
    execute if entity @s[tag=!a2-using-instant-boost] on passengers if score @s[tag=kartdirection] kartmove matches ..-1 run scoreboard players add @s kartmove 1

#익시드
execute if score @s[tag=!can-jump] kart-engine matches 5 run function kartmobil:move/v1-exceed/main
execute if score @s[tag=!can-jump] kart-engine matches 9 run function kartmobil:move/krp-exceed/main

#부능효과
execute if score @s[tag=!can-jump] kart-engine matches 10 run function kartmobil:move/xun-charger/main

#업다운 - 점프
execute if entity @p[tag=kartpassenger,predicate=kartmobil:space] if entity @s[tag=can-jump,tag=!kart-jumped] unless score @s kartmovey matches 1000.. run function kartmobil:move/jump

#전진 - 저항과 드리프트 물리 연산 전에 미리 해야 함
function kartmobil:move/movetp/main

#마찰저항 / 공기저항
function kartmobil:move/resistance/main

#드리프트 탈출력 + 드리프트 물리
execute if entity @s[tag=ignore-slip] if score @p[tag=kartpassenger] kartdrift matches ..-1 run scoreboard players set @p[tag=kartpassenger] kartdrift 0
execute if entity @s[tag=!kart-direction-fixed-by-pad] run function kartmobil:move/steering/drift-physics/main

execute on passengers run tag @s[tag=kartdirectiontemp] remove kartdirectiontemp
tag @s remove kartself