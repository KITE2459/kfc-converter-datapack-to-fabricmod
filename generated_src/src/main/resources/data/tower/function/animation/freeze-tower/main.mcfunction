$function tower:animation/freeze-tower/lv$(level)/main
# execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.trial_spawner.break record @a ~ ~ ~ 1 1
execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.trial_spawner.spawn_item record @a ~ ~ ~ 1 1