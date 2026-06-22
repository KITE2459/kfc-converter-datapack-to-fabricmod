execute if score @s enemy.attribute.healing matches 1.. run function enemy:attribute/buff/healing
execute if score @s enemy.attribute.dealing matches 1.. run function enemy:attribute/buff/dealing
function enemy:attribute/dark/main

execute if score @s enemy.state.bleed matches 1.. run function enemy:attribute/debuff/bleed
execute if score @s enemy.state.flame matches 1.. run function enemy:attribute/debuff/flame
execute if score @s enemy.state.poison matches 1.. run function enemy:attribute/debuff/poison
execute if score @s enemy.state.freeze matches 1.. run function enemy:attribute/debuff/freeze
execute if score @s enemy.state.stun matches 1.. run function enemy:attribute/debuff/stun
execute if score @s enemy.state.weakness matches 1.. run function enemy:attribute/debuff/weakness
execute if score @s enemy.state.stun-immune matches 1.. run function enemy:attribute/debuff/stun-immune

execute unless score @s enemy.hp matches 1.. run function enemy:hit/death with entity @s data
function enemy:ability/main
scoreboard players add @s enemy.attribute.timer 1