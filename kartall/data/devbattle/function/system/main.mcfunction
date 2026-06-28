#@s를 기준으로 돌아감

execute if score @s dev-count matches 1 at @s run function devbattle:system/init

scoreboard players add @s dev-count 1

#섀도우가 사라진 다음 틱에 리타이어 판정
execute store result score shadow-length-temp trackselect-map-id run data get storage devattack:dev-attack-shadows temp-shadow-play.pos
execute if score #shadow-length trackselect-map-id matches 1.. if score shadow-length-temp trackselect-map-id matches 1.. run tag @s[tag=kart-dev-waiting] remove kart-dev-waiting
execute if score #shadow-length trackselect-map-id matches 1.. unless score shadow-length-temp trackselect-map-id matches 1.. at @s[tag=!kart-dev-waiting] run function devbattle:system/waiting-dev

#개발자 섀도우 기록 / 재생
execute if score @s dev-count matches 42 run kill @e[tag=shadow-models]
execute if score @s dev-count matches 42.. if score shadow-length-temp trackselect-map-id matches 1.. run function devbattle:system/shadow/play
execute if score @s dev-count matches 43.. run function devbattle:system/shadow/record

#카트에서 내리면 리타이어
execute if score @s dev-count matches 20.. if entity @s[predicate=!kartmobil:ifride] run function devbattle:system/retire

#물이나 공허에 닿으면 r키
# execute on vehicle on vehicle at @s if block ~ ~0.25 ~ water on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey
execute on vehicle on vehicle at @s if block ~ ~-0.5 ~ structure_void on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey

#카운트다운
execute if score @s dev-count matches ..100 run function devbattle:system/countdown

#체크포인트 시스템
execute if score @s dev-count matches 100.. run function checkpoint:system/player-main

#결승선을 지나기
execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers if entity @s[tag=check-pass-last] run function devbattle:system/passline
execute if score @s dev-count matches 100.. if score @s trackselect-lap >= #max-lap trackselect-lap run function devbattle:system/complete

