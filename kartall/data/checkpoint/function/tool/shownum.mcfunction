execute store result storage checkpoint:checkpoint checknum int 1 run scoreboard players get @s check-num
function checkpoint:tool/shownum-macro with storage checkpoint:checkpoint

particle minecraft:block_marker{block_state:"iron_block"} ~ ~ ~ 0 0 0 1 1 force @a
function checkpoint:tool/showline