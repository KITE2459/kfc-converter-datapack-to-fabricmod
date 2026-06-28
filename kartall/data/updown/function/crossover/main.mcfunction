#@s를 기준으로 돌아감

execute if score @s updown-count matches 1 at @s run function updown:system/init

scoreboard players add @s updown-count 1

#카트에서 내리면 리타이어
execute if score @s updown-count matches 10.. if entity @s[predicate=!kartmobil:ifride] run function updown:system/retire

#물이나 공허에 닿으면 r키
# execute on vehicle on vehicle at @s if block ~ ~0.25 ~ water on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey
execute on vehicle on vehicle at @s if block ~ ~-0.5 ~ structure_void on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey

#카운트다운
execute if score @s updown-count matches ..100 run function updown:system/countdown

#체크포인트 통과
execute if score @s updown-count matches 100.. run function checkpoint:system/player-main

#결승선을 지나기
execute if block ~ ~-1.6 ~ magma_block if entity @s[tag=check-pass-last] run function updown:crossover/passline
execute if score @s trackselect-lap >= #max-lap trackselect-lap run function updown:system/complete

tag @e[tag=mykart,type=#kartmobil:kartmobil] remove mykart