execute if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailheight:1}] run particle minecraft:block_marker{block_state:barrier} ~ ~ ~ 0 0 0 1 1 normal @s
execute if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailheight:2}] run particle minecraft:block_marker{block_state:barrier} ~ ~1 ~ 0 0 0 1 1 normal @s
execute if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailheight:3}] run particle minecraft:block_marker{block_state:barrier} ~ ~1 ~ 0 0 0 1 1 normal @s
execute if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailheight:3}] run particle minecraft:block_marker{block_state:barrier} ~ ~2 ~ 0 0 0 1 1 normal @s



execute if score @s carrot matches 1.. if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailheight:1}] if block ~ ~ ~ #guardrailtool:ignoreblock run setblock ~ ~ ~ minecraft:target
execute if score @s carrot matches 1.. if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailheight:2}] if block ~ ~1 ~ #guardrailtool:ignoreblock run setblock ~ ~1 ~ minecraft:target
execute if score @s carrot matches 1.. if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailheight:3}] if block ~ ~1 ~ #guardrailtool:ignoreblock run setblock ~ ~1 ~ minecraft:target
execute if score @s carrot matches 1.. if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailheight:3}] if block ~ ~2 ~ #guardrailtool:ignoreblock run setblock ~ ~2 ~ minecraft:target