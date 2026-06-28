#3틱 뒤에 해당 대상을 관전
execute as @a[tag=dev-attack-spectator] if score @s multi-spectate-time matches 1.. run function devbattle:system/spectator/wait-spectate
execute as @a[tag=dev-attack-spectator] if score @s multi-spectate-time matches 1 run function devbattle:system/spectator/spectate-target-delayed

#웅크리기 하면 나가기
execute as @a[tag=dev-attack-spectator,scores={multi-shift=1..}] at @s run function devbattle:system/spectator/exit

effect give @a[tag=dev-attack-spectator] minecraft:invisibility 1 1 true

#게임이 진행중이 아니면 나가기
execute unless entity @a[scores={dev-count=1..}] as @a[tag=dev-attack-spectator] at @s run function devbattle:system/spectator/exit

#브금 섹션
scoreboard players operation @s bgm-section-target = @a[scores={dev-count=1..},limit=1] bgm-section-target