execute unless entity @a[scores={dev-count=1..}] run tellraw @a[distance=..50] [{translate:"아무도 ","color":"red"},{translate:"제작자와의 대결","color":"aqua"},{translate:"을 플레이하지 않습니다.","color":"red"}]
execute unless entity @a[scores={dev-count=1..}] run return 1

tag @s add dev-attack-spectator
scoreboard players reset @s multi-shift
gamemode spectator @s

scoreboard players set @s multi-spectate-time 0

function bgm-room:manage-bgm/stopbgm
function bgm-room:manage-bgm/playbgm with entity @n[tag=trackselect-devbattle-marker] data.track

function devbattle:system/spectator/spectate-target
execute if entity @p[scores={dev-count=1..},nbt={active_effects:[{id:"minecraft:night_vision"}]}] run effect give @s minecraft:night_vision infinite 1 true
