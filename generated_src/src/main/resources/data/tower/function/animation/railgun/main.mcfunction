$function tower:animation/railgun/lv$(level)/main
execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound entity.firework_rocket.large_blast_far record @a ~ ~ ~ 1 0.8
execute if score @s[tag=tower.sound] tower.animation matches 1 run playsound block.beacon.deactivate record @a ~ ~ ~ 1 0.8