$function tower:animation/coilgun/lv$(level)/main
execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.trial_spawner.ominous_activate record @a ~ ~ ~ 0.5 1.0
execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:item.ominous_bottle.dispose record @a ~ ~ ~ 1 1.5
execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:block.beacon.activate record @a ~ ~ ~ 1 1.5