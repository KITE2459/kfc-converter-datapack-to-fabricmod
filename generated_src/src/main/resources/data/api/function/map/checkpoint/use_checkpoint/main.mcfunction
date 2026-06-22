execute store result score @s player.rotation run data get entity @s Rotation[0] 10.0
$execute if score @s player.rotation matches -450..449 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[0f,0f]}
$execute if score @s player.rotation matches 450..1349 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[90f,0f]}
$execute if score @s player.rotation matches 1350..1800 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[180f,0f]}
$execute if score @s player.rotation matches -1800..-1351 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[180f,0f]}
$execute if score @s player.rotation matches -1350..-451 run summon marker ~ ~ ~ {Tags:[map.point,map.$(type)_point,map.$(map)],Rotation:[270f,0f]}
$function api:map/checkpoint/use_checkpoint/$(type)_point with storage player temp.components.minecraft:custom_data
item replace entity @s weapon.mainhand from entity @s weapon.offhand
item replace entity @s weapon.offhand with air