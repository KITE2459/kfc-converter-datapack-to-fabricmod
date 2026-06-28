execute unless entity @a[scores={mastercount=1..}] run tellraw @a[distance=..50] [{translate:"아무도 ","color":"red"},{translate:"마스터","color":"red"},{translate:"를 플레이하지 않습니다.","color":"red"}]
execute unless entity @a[scores={mastercount=1..}] run return 1

tag @s add master-spectator
scoreboard players reset @s multi-shift
gamemode spectator @s

scoreboard players set @s multi-spectate-time 0

function bgm-room:manage-bgm/stopbgm
function bgm-room:manage-bgm/playbgm with entity @n[tag=gamemain-mapdata-marker] data.track

function master:system/spectator/spectate-target
execute if entity @p[scores={mastercount=1..},nbt={active_effects:[{id:"minecraft:night_vision"}]}] run effect give @s minecraft:night_vision infinite 1 true
