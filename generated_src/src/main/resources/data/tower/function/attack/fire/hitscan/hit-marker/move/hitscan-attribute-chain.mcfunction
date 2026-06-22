execute store result score #damage enemy.hp run scoreboard players get @s bullet.attack
execute store result score #temp enemy.state.stun run scoreboard players get @s bullet.attribute.stun
execute store result score #temp enemy.state.freeze run scoreboard players get @s bullet.attribute.freeze
execute store result score #temp enemy.state.poison run scoreboard players get @s bullet.attribute.poison
execute store result score #temp enemy.state.flame run scoreboard players get @s bullet.attribute.flame
execute store result score #temp enemy.state.bleed run scoreboard players get @s bullet.attribute.bleed
execute store result score #temp enemy.state.weakness run scoreboard players get @s bullet.attribute.weakness

$tag @n[tag=target_$(number)] add hitscan-area-effect
$tag @n[tag=target_$(number)] remove target_$(number)
$tag @n[tag=enemy.target,tag=!hitscan-area-effect] add target_$(number)
$rotate @s facing entity @n[tag=target_$(number)] eyes
scoreboard players add @s bullet.life 3
scoreboard players remove @s bullet.ability.chain 1
scoreboard players set #temp game.return 0
execute if score @s bullet.ability.chain matches 0 as @e[tag=hitscan-area-effect] run function enemy:hit/main
execute if score @s bullet.ability.chain matches 0 as @e[tag=hitscan-area-effect] run tag @s remove hitscan-area-effect
execute if score @s bullet.ability.chain matches 0 run kill @e[tag=hitscan-marker]