execute if entity @s[scores={tower.state.stun=1..}] run return 0
execute store result storage tower temp.level int 1 run scoreboard players get @s tower.level
function tower:animation/main-sub
execute on vehicle on passengers if entity @s[tag=tower.animation] run function tower:animation/main-sub