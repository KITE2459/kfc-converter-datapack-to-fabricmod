execute as @a run attribute @s minecraft:block_interaction_range modifier remove build
execute as @a[tag=garage-player] run attribute @s minecraft:block_interaction_range modifier add build 100 add_value

execute as @a[tag=garage-player] if items entity @s weapon #garage:breakable run item replace entity @s weapon.offhand with minecraft:carrot_on_a_stick
execute as @a[tag=garage-player] unless items entity @s weapon #garage:breakable if items entity @s weapon.offhand carrot_on_a_stick run item replace entity @s weapon.offhand with air
execute as @a[tag=garage-player] if items entity @s container.* minecraft:carrot_on_a_stick run clear @s minecraft:carrot_on_a_stick

execute as @a[tag=garage-player] at @s if score @s carrot matches 1.. if items entity @s weapon #garage:breakable anchored eyes positioned ^ ^ ^ run function garage:custom-model-maker/building-system/raycast

scoreboard players reset @a[tag=garage-player,tag=!check-editor] carrot