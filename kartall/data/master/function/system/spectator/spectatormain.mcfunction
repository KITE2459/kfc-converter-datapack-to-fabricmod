scoreboard players add @s multi-spectate-time 1

#3틱 뒤에 해당 대상을 관전
execute if score @s multi-spectate-time matches 1.. run function master:system/spectator/wait-spectate
execute if score @s multi-spectate-time matches 1 run function master:system/spectator/spectate-target-delayed

#웅크리기 하면 나가기
execute as @a[tag=master-spectator,scores={multi-shift=1..}] at @s run function master:system/spectator/exit

effect give @a[tag=master-spectator] minecraft:invisibility 1 1 true

#게임이 진행중이 아니면 나가기
execute unless entity @a[scores={mastercount=1..}] as @a[tag=master-spectator] at @s run function master:system/spectator/exit

#브금 섹션
scoreboard players operation @s bgm-section-target = @a[scores={mastercount=1..},limit=1] bgm-section-target