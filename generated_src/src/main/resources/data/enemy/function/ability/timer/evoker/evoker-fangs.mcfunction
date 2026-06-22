scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 85
summon evoker_fangs ~ ~-1 ~
tp @s ^ ^ ^1
scoreboard players remove @s enemy.hp 1
execute if score @s enemy.hp matches ..0 run kill @s