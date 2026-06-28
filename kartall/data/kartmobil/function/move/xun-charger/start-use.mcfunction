scoreboard players set @p[tag=kartpassenger] kartboostgauge 6000

execute rotated as @p[tag=kartpassenger] rotated ~ 0 run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ^ ^ ^8 2 2
execute rotated as @p[tag=kartpassenger] rotated ~ 0 run playsound minecraft:block.trial_spawner.spawn_item neutral @a[tag=kart-listener] ^ ^ ^8 2 1
execute rotated as @p[tag=kartpassenger] rotated ~ 0 run playsound minecraft:block.trial_spawner.ominous_activate neutral @a[tag=kart-listener] ^ ^ ^8 2 2
execute rotated as @p[tag=kartpassenger] rotated ~ 0 run playsound minecraft:block.trial_spawner.spawn_item neutral @a[tag=kart-listener] ^ ^ ^8 2 1

execute if entity @s[tag=!karthideplayer] as @p[tag=kartpassenger,gamemode=!survival] unless items entity @s armor.chest * run item replace entity @s armor.chest with minecraft:diamond_chestplate[custom_data={xun:1},enchantments={"protection":1}]
execute if entity @s[tag=!karthideplayer] as @p[tag=kartpassenger,gamemode=!survival] unless items entity @s armor.feet * run item replace entity @s armor.feet with minecraft:diamond_boots[custom_data={xun:1},enchantments={"protection":1}]
execute if entity @s[tag=!karthideplayer] as @p[tag=kartpassenger,gamemode=!survival] unless items entity @s armor.head * run item replace entity @s armor.head with minecraft:diamond_helmet[custom_data={xun:1},enchantments={"protection":1}]
execute if entity @s[tag=!karthideplayer] as @p[tag=kartpassenger,gamemode=!survival] unless items entity @s armor.legs * run item replace entity @s armor.legs with minecraft:diamond_leggings[custom_data={xun:1},enchantments={"protection":1}]

execute on passengers if entity @s[tag=kartmodelsaddle] on passengers if entity @s[tag=xun-charger-shield] run data merge entity @s {transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1f,0f],scale:[2f,2f,2f]}}

scoreboard players add @s kartdefense 20
scoreboard players add @s kartboostcount 1
scoreboard players add @s kartmaxboostcount 1
scoreboard players add @s kart-teamboost-gauge 2000

tag @s add kart-charger-active

