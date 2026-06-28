#3틱 뒤에 해당 대상을 관전
execute if score @s multi-spectate-time matches 1.. run function multiplay:system/spectator/wait-spectate
execute if score @s multi-spectate-time matches 1 run function multiplay:system/spectator/spectate-target-delayed

#키를 누르면 증가하는 스코어
execute if entity @s[predicate=kartmobil:s] run scoreboard players add @s multi-spectate-change 1
execute if entity @s[predicate=kartmobil:w] run scoreboard players add @s multi-spectate-change 1
execute if entity @s[predicate=!kartmobil:w,predicate=!kartmobil:s] run scoreboard players reset @s multi-spectate-change

execute if score @s multi-spectate-change matches 1 run function multiplay:system/spectator/set-target

#웅크리기 하면 나가기
execute at @s run function multiplay:system/spectator/ui
execute if entity @s[scores={multi-shift=1..}] at @s run function multiplay:system/spectator/exit

effect give @s minecraft:invisibility 1 1 true

#게임이 진행중이 아니면 나가기
execute unless score #game multi-main matches 1.. at @s run function multiplay:system/spectator/exit

#브금 섹션
scoreboard players operation @s bgm-section-target = @p[tag=kart-multi-player,scores={bgm-section-target=1..},limit=1] bgm-section-target