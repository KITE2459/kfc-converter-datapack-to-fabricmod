tag @s add multi-spectator
scoreboard players reset @s multi-shift
gamemode spectator @s

function bgm-room:manage-bgm/stopbgm
function bgm-room:manage-bgm/playbgm with entity @n[tag=trackselect-multi-marker] data.track

scoreboard players reset @s multi-spectator-id
scoreboard players operation @s multi-spectator-id = @r[tag=kart-multi-player] multi-spectator-id

scoreboard players set @s multi-spectate-time 0
scoreboard players reset @s multi-spectate-change

function multiplay:system/spectator/spectate-target
execute if entity @p[tag=kart-multi-player,nbt={active_effects:[{id:"minecraft:night_vision"}]}] run effect give @s minecraft:night_vision infinite 1 true
