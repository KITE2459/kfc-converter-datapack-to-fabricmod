summon armor_stand 0 0 0 {Tags:["kartswaphand"]}

item replace entity @n[tag=kartswaphand] weapon from entity @s weapon.offhand
item replace entity @s weapon.offhand from entity @s weapon
item replace entity @s weapon from entity @n[tag=kartswaphand] weapon

kill @e[tag=kartswaphand,type=armor_stand]