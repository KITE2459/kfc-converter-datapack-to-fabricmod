
scoreboard players add @s kart-run-anime 1


execute if score #kartspeed kartmove matches 5.. run scoreboard players set #term kart-run-anime 6
execute if score #kartspeed kartmove matches 40.. run scoreboard players set #term kart-run-anime 5
execute if score #kartspeed kartmove matches 80.. run scoreboard players set #term kart-run-anime 4
execute if score #kartspeed kartmove matches 120.. run scoreboard players set #term kart-run-anime 3
execute if score #kartspeed kartmove matches 150.. run scoreboard players set #term kart-run-anime 2

#인원수가 많으면
execute if score #late-sync-player-count kartmain matches 6.. run scoreboard players set #term kart-run-anime 2
execute if score #late-sync-player-count kartmain matches 6.. run scoreboard players operation #term kart-run-anime += #late-sync-player-count kartmain

#애니메이션 상태 변경
execute on passengers if entity @s[tag=kartmodelsaddle,type=item_display] store result entity @s data.interpolation_duration int 1 run scoreboard players get #term kart-run-anime

execute if score #kartspeed kartmove matches 5.. if score @s kart-run-anime >= #term kart-run-anime run function kartmobil:move/run-anime/shift-anime-state
execute if score #kartspeed kartmove matches 5.. if score @s kart-run-anime >= #term kart-run-anime run scoreboard players set @s kart-run-anime 0
