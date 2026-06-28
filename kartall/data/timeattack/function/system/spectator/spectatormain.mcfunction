#3틱 뒤에 해당 대상을 관전
execute as @a[tag=timeattack-spectator] if score @s multi-spectate-time matches 1.. run function timeattack:system/spectator/wait-spectate
execute as @a[tag=timeattack-spectator] if score @s multi-spectate-time matches 1 run function timeattack:system/spectator/spectate-target-delayed

#웅크리기 하면 나가기
execute as @a[tag=timeattack-spectator,scores={multi-shift=1..}] at @s run function timeattack:system/spectator/exit

effect give @a[tag=timeattack-spectator] minecraft:invisibility 1 1 true

#게임이 진행중이 아니면 나가기
execute unless entity @a[scores={timecount=1..}] as @a[tag=timeattack-spectator] at @s run function timeattack:system/spectator/exit

#브금 섹션
scoreboard players operation @s bgm-section-target = @a[scores={timecount=1..},limit=1] bgm-section-target