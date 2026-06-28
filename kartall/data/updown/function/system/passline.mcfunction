scoreboard players add @s trackselect-lap 2

title @s title ""
execute if score @s trackselect-lap = #max-lap trackselect-lap run function lapsound:play
execute if score @s trackselect-lap = #max-lap trackselect-lap run title @s subtitle [{translate:"마지막 바퀴","color":"yellow"}]
execute unless score @s trackselect-lap >= #max-lap trackselect-lap run function lapsound1:play
execute unless score @s trackselect-lap >= #max-lap trackselect-lap run title @s subtitle [{"score":{"name":"@s","objective":"trackselect-lap","color":"yellow"},"color":"yellow"},{translate:"바퀴째","color":"yellow"}]

scoreboard players remove @s trackselect-lap 1



tag @s remove checkpointpass

function checkpoint:system/init