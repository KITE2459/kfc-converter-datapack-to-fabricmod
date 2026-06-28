
execute if entity @e[distance=..0.5,tag=kart-special-ability] run 
execute unless entity @e[distance=..0.5,tag=kart-special-ability] run 
summon minecraft:item_display ~ ~ ~ {Tags:["updown-engine-fix","kart-bypass-tag"],data:{engine-fix:2}}