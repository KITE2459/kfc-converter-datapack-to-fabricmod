function garage:system/give-macro with entity @n[tag=garage-kart,type=item_display] item

particle minecraft:trial_spawner_detection ~ ~ ~ .5 .5 .5 0 25
execute at @s run particle minecraft:trial_spawner_detection ~ ~ ~ .5 .5 .5 0 25

playsound minecraft:block.trial_spawner.about_to_spawn_item weather @a ~ ~ ~ 1 2