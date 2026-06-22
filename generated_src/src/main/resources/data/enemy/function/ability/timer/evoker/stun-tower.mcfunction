# 소닉 발사
summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
execute as @e[tag=evoker-fangs,tag=not-allocated,distance=..2] at @s run rotate @s ~72 ~
execute rotated ~72 ~ run summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
execute as @e[tag=evoker-fangs,tag=not-allocated,distance=..2] at @s run rotate @s ~72 ~
execute rotated ~144 ~ run summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
execute as @e[tag=evoker-fangs,tag=not-allocated,distance=..2] at @s run rotate @s ~72 ~
execute rotated ~216 ~ run summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
execute as @e[tag=evoker-fangs,tag=not-allocated,distance=..2] at @s run rotate @s ~72 ~
execute rotated ~288 ~ run summon marker ~ ~1 ~ {Tags:[evoker-fangs,not-allocated]}
scoreboard players set @e[tag=evoker-fangs,distance=..2,tag=not-allocated] enemy.hp 50
tag @e[tag=evoker-fangs,tag=not-allocated,distance=..2] remove not-allocated
tag @s remove enemy.skill-loop.2
tag @s add enemy.skill-loop.1
scoreboard players set @s enemy.skill-trigger.timer-cooldown 300
