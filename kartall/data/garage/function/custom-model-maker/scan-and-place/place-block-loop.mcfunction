data get storage kart-custom-model temp4[0].translation[0]

data modify storage minecraft:kart-custom-model temp5.pos0 set from storage kart-custom-model temp4[0].translation[0] 
data modify storage minecraft:kart-custom-model temp5.pos1 set from storage kart-custom-model temp4[0].translation[1] 
data modify storage minecraft:kart-custom-model temp5.pos2 set from storage kart-custom-model temp4[0].translation[2] 
data modify storage minecraft:kart-custom-model temp5.id set from storage kart-custom-model temp4[0].id

function garage:custom-model-maker/scan-and-place/place-block with storage kart-custom-model temp5

data remove storage kart-custom-model temp4[0]

execute if data storage kart-custom-model temp4[0] run function garage:custom-model-maker/scan-and-place/place-block-loop