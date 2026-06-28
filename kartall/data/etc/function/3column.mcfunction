execute if score @s kartmove matches ..42500 run return 1

execute on passengers if entity @s[tag=kartdirection] run rotate @s 160 ~

particle item{item:"sandstone"} ~ ~1 ~ 0 0 0 0.25 50 force @p[tag=kart-listener]
playsound minecraft:entity.zombie.attack_wooden_door neutral @p[tag=kart-listener]
playsound minecraft:block.slime_block.step neutral @p[tag=kart-listener]
playsound minecraft:block.slime_block.step neutral @p[tag=kart-listener]

scoreboard players set @s kartmove 40000