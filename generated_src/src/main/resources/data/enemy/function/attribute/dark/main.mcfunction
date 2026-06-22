execute if entity @e[type=marker,tag=enemy.dark-field,tag=!enemy.dark-field.large,distance=..1.5] run tag @s add enemy.dark-field.inside
execute if entity @e[type=marker,tag=enemy.dark-field,tag=enemy.dark-field.large,distance=..5] run tag @s add enemy.dark-field.inside
execute unless entity @e[type=marker,tag=enemy.dark-field,tag=!enemy.dark-field.large,distance=..3] unless entity @e[type=marker,tag=enemy.dark-field,tag=enemy.dark-field.large,distance=..5] run tag @s remove enemy.dark-field.inside

execute if entity @s[tag=enemy.attribute.dark,tag=enemy.dark-field.inside] run tag @s add enemy.dark-field.immune
execute unless entity @s[tag=enemy.attribute.dark,tag=enemy.dark-field.inside] run tag @s remove enemy.dark-field.immune

execute if entity @s[tag=!enemy.attribute.dark,tag=enemy.dark-field.inside] run scoreboard players add @s enemy.state.corruption 1
execute if score @s enemy.state.corruption matches 100.. run scoreboard players set @s enemy.state.corruption 100
execute if entity @s[tag=!enemy.attribute.dark,scores={enemy.state.corruption=100..}] run tag @s add enemy.attribute.dark.new

execute if entity @s[tag=enemy.attribute.dark.new] run tag @s add enemy.attribute.dark
execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.freeze 0
execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.stun 0
execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.poison 0
execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.flame 0
execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.bleed 0
execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.weakness 0
execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.stun-immune 0
execute if entity @s[tag=enemy.attribute.dark.new] run scoreboard players set @s enemy.state.corruption 0
execute if entity @s[tag=enemy.attribute.dark.new] run tag @s remove enemy.attribute.dark.new
