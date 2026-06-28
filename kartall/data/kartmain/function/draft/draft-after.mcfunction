#파티클
execute if score @s kartmain matches ..65 unless score @s kart-engine matches 1006 on passengers rotated as @s[tag=kartdirection,type=item_display] on vehicle rotated ~ 0 run particle minecraft:gust ~ ~1 ~ .75 .75 .75 10 1 normal @a[distance=..15]

#드래프트 발동 이후 스피드 증가 / JIU는 더 증가
execute if score @s kartmain <= @s kart-draft-time on passengers if entity @s[tag=kartsaddle] on passengers if entity @s[type=player] run scoreboard players add @s kartspeed 20
execute if score @s kart-engine matches 2 if score @s kartmain <= @s kart-draft-time on passengers if entity @s[tag=kartsaddle] on passengers if entity @s[type=player] run scoreboard players add @s kartspeed 10

#드래프트 끝
execute if score @s kartmain > @s kart-draft-time run function kartmain:draft/draft-end

    #드래프트 수치에 따라 짧은 가속
    execute if score @s kartmain matches ..80 run scoreboard players operation @s kartmove += @s kart-draft

    #드'리'프트 중 드'래'프트 가속 강화
    execute if score @s[tag=kart-drifting] kartmain matches ..100 run scoreboard players operation @s kartmove += @s kart-draft

#V1 익시드 / A2 순부 사용 중 너프
execute if score @s[tag=kart-exceed-active] kartmain matches 60.. if score @s kartmain <= @s kart-draft-time on passengers if entity @s[tag=kartsaddle] on passengers if entity @s[type=player] run scoreboard players remove @s kartspeed 18
execute if score @s[tag=a2-using-instant-boost] kartmain matches 60.. if score @s kartmain <= @s kart-draft-time on passengers if entity @s[tag=kartsaddle] on passengers if entity @s[type=player] run scoreboard players remove @s kartspeed 12

#가속 파티클
#execute if score @s kartmain matches 60..100 unless score @s kart-engine matches 1006 run playsound minecraft:entity.wind_charge.wind_burst neutral @a[tag=kart-listener] ~ ~ ~ 0.5 1
#execute if score @s kartmain matches 60..100 unless score @s kart-engine matches 1006 run particle dust{color:[1, 1, 1],scale:1} ~ ~1 ~ 0.5 0.5 0.5 1 0 normal @a[distance=..15]