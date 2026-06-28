#@s를 기준으로 돌아감

execute if score @s timecount matches 1 at @s run function timeattack:system/init

scoreboard players add @s timecount 1

#타임어택 섀도우 기록 / 재생
execute if score @s timecount matches 42 run kill @e[tag=shadow-models]
execute if score @s timecount matches 42.. if score #shadow-length trackselect-map-id matches 1.. run function timeattack:system/shadow/play
execute if score @s timecount matches 43.. run function timeattack:system/shadow/record

#카트에서 내리면 리타이어
execute if score @s timecount matches 10.. if entity @s[predicate=!kartmobil:ifride] run function timeattack:system/retire

#물이나 공허에 닿으면 r키
# execute on vehicle on vehicle at @s if block ~ ~0.25 ~ water on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey
execute on vehicle on vehicle at @s if block ~ ~-0.5 ~ structure_void on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey

#카운트다운
execute if score @s timecount matches ..100 run function timeattack:system/countdown

#체크포인트 통과
execute if score @s timecount matches 100.. run function checkpoint:system/player-main

#결승선을 지나기
execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers if entity @s[tag=check-pass-last] run function timeattack:system/passline
execute if score @s trackselect-lap >= #max-lap trackselect-lap run function timeattack:system/complete

tag @e[tag=mykart,type=#kartmobil:kartmobil] remove mykart