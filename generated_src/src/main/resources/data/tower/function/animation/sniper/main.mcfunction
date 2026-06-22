$function tower:animation/sniper/lv$(level)/main
execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:entity.firework_rocket.blast record @a ~ ~ ~ 0.4 0.5
execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound minecraft:item.ominous_bottle.dispose record @a ~ ~ ~ 1 1.5