execute unless entity @n[tag=kartmodelsaddle,distance=..0.3] run return 1

summon item_display ~ ~ ~ {Rotation:[0, 90],Tags:["custom-model-garage-temp-2"],teleport_duration:1}

data modify entity @e[tag=custom-model-garage-temp-2,type=item_display,limit=1] transformation.scale set value [0.2f, 0.2f, 0.2f]

data modify entity @e[tag=custom-model-garage-temp-2,type=item_display,limit=1] transformation.translation set from entity @s data.kart-custom-model.temp3[0].translation
data modify entity @e[tag=custom-model-garage-temp-2,type=item_display,limit=1] item.id set from entity @s data.kart-custom-model.temp3[0].id

execute on passengers if entity @s[tag=kartmodelsaddle] run ride @e[tag=custom-model-garage-temp-2,type=item_display,limit=1] mount @s
tag @e[tag=custom-model-garage-temp-2,type=item_display,limit=1] remove custom-model-garage-temp-2

data remove entity @s data.kart-custom-model.temp3[0]
execute unless data entity @s data.kart-custom-model.temp3[0] run tag @s remove kart-has-custom-model
execute unless data entity @s data.kart-custom-model.temp3[0] run data remove entity @s data.kart-custom-model

