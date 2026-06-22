scoreboard players remove @s mob_gen.count 1
$function enemy:ability/mob_generator/particles/$(model)
execute if score @s mob_gen.count matches 0 run tag @s add map.spawn_point
$execute if score @s mob_gen.count matches 0 run function enemy:spawn/summon/main {model:$(model)}
execute if score @s mob_gen.count matches 0 run tag @s remove map.spawn_point
execute if score @s mob_gen.count matches 0 run scoreboard players remove @s mob_gen.number 1
execute if score @s mob_gen.count matches 0 run scoreboard players operation @s mob_gen.count = @s mob_gen.tick
execute if score @s mob_gen.number matches ..0 run kill @s