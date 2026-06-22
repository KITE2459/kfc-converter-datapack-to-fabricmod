$execute unless data storage map $(map).infinity.round[$(index)] run return 0
$data modify storage game infinity.mob set from storage map $(map).infinity.round[$(index)]
scoreboard players operation #delta wave = game wave
$scoreboard players remove #delta wave $(unlock)
execute if score #delta wave matches ..-1 run return 0
execute store result score #mob_type wave run data get storage game infinity.mob.kind 1
execute if score #mob_type wave matches 1 run function game:infinity/process_normal
execute if score #mob_type wave matches 2 run function game:infinity/process_elite
execute if score #mob_type wave matches 3 run function game:infinity/process_boss with storage game infinity.ctx
