execute if score @s enemy.attribute.healing matches 1.. run return 1
execute on vehicle if entity @s[tag=enemy.attribute.heavy] run return 1
execute on vehicle if entity @s[tag=enemy.attribute.speed] run return 1
execute on vehicle if entity @s[tag=enemy.attribute.taunt] run return 1
execute on vehicle if entity @s[tag=enemy.attribute.dark] run return 1
return 0