execute store result score #player-count multi-spectate-time if entity @a[tag=kart-multi-player]

tag @s add spectator-self
execute as @a[tag=kart-multi-player] if score @s multi-spectator-id = @n[tag=spectator-self] multi-spectator-id run tag @s add spectator-target-temp-now
tag @s remove spectator-self

#내가 관전하던 사람의 순위보다 1 높은/낮은 사람의 아이디를 찾기
scoreboard players operation multi-rank-temp multi-spectator-id = @p[tag=spectator-target-temp-now] multi-instant-rank


execute if entity @s[predicate=kartmobil:s] run scoreboard players add multi-rank-temp multi-spectator-id 1
execute if entity @s[predicate=kartmobil:w] run scoreboard players remove multi-rank-temp multi-spectator-id 1

#순환
execute if entity @s[predicate=kartmobil:s] if score multi-rank-temp multi-spectator-id > #player-count multi-spectate-time run scoreboard players set multi-rank-temp multi-spectator-id 1
execute if entity @s[predicate=kartmobil:w] if score multi-rank-temp multi-spectator-id matches ..0 run scoreboard players operation multi-rank-temp multi-spectator-id = #player-count multi-spectate-time

execute as @a[tag=kart-multi-player] if score @s multi-instant-rank = multi-rank-temp multi-spectator-id run tag @s add spectator-target-temp-next



scoreboard players operation @s multi-spectator-id = @p[tag=spectator-target-temp-next] multi-spectator-id

tag @a[tag=spectator-target-temp-now] remove spectator-target-temp-now
tag @a[tag=spectator-target-temp-next] remove spectator-target-temp-next


function multiplay:system/spectator/spectate-target
