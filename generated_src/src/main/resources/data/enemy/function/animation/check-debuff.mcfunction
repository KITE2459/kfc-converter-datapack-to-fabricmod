execute if score @s enemy.state.freeze matches 1.. run return 1
execute if score @s enemy.state.poison matches 1.. run return 1
execute if score @s enemy.state.flame matches 1.. run return 1
execute if score @s enemy.state.bleed matches 1.. run return 1
execute if score @s enemy.state.stun matches 1.. run return 1
execute if score @s enemy.state.weakness matches 1.. run return 1
execute if score @s enemy.state.corruption matches 1..99 run return 1
return 0