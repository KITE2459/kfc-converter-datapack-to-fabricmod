function garage:custom-model-maker/reset
data modify storage kart-custom-model temp4 set from entity @n[tag=garage-custom-frame-2] Item.components.minecraft:custom_data.model

execute if data storage kart-custom-model temp4[0] run function garage:custom-model-maker/scan-and-place/place-block-loop

summon item 5265 5 4513 {PickupDelay:20,Tags:["asdfasdf0806"],Item:{id:"minecraft:paper",count:1,components:{"minecraft:custom_name":[{"text":"asdf"}]}}}

data modify entity @n[tag=asdfasdf0806] Item set from entity @n[tag=garage-custom-frame-2] Item
data remove entity @n[tag=garage-custom-frame-2] Item

tag @n[tag=asdfasdf0806] remove asdfasdf0806