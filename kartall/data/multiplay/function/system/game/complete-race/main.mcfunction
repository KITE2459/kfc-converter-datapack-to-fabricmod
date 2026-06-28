execute unless entity @s[tag=kart-multi-player] run return 1

tag @s add kart-multi-complete

#동타 완주자 체크
execute as @a[tag=kart-multi-player,tag=!kart-multi-complete] if score @s trackselect-lap >= #max-lap trackselect-lap at @s on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers run tag @s[type=player] add kart-multi-same-time

#동타 완주자가 없으면 그냥 완주하고 끝냄
execute unless entity @a[tag=kart-multi-same-time] run return run function multiplay:system/game/complete-race/complete
tag @s add kart-multi-same-time

#거리 판정용으로 쓸 초기화
scoreboard players set @a[tag=kart-multi-same-time] kart-milisec-calc 0

#결승선과의 거리 측정
execute as @a[tag=kart-multi-same-time] at @s run function multiplay:system/game/complete-race/save-milisec-to-kartdrift

#동타 난 플레이어들을 완주 처리
function multiplay:system/game/complete-race/complete-same-time-player

tag @s remove kart-multi-complete
tag @a[tag=kart-multi-same-time] remove kart-multi-same-time