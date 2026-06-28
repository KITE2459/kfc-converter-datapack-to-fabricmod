
gamemode adventure @s[tag=!op]

#물이나 공허에 닿으면 r키
# execute on vehicle on vehicle at @s if block ~ ~0.25 ~ water on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey
execute on vehicle on vehicle at @s if block ~ ~-0.25 ~ minecraft:pointed_dripstone on passengers as @s[tag=kartsaddle] on passengers if entity @s[type=player] run function gamemain:rkey
execute on vehicle on vehicle at @s if block ~ ~-0.5 ~ structure_void on passengers as @s[tag=kartsaddle] on passengers if entity @s[type=player] run function gamemain:rkey

#수동R키
function gamemain:rkey-detect/main

#업 다운 모드
execute on vehicle on vehicle run scoreboard players set @s kartboostgauge 0
execute on vehicle on vehicle run scoreboard players set @s kartboostcount 0

#체크포인트 시스템
function checkpoint:system/player-main-updown
