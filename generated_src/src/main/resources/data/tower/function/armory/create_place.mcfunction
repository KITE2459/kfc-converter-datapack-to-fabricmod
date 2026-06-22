kill @e[type=item_display,tag=armory-tower,distance=..0.1]

setblock ~ ~ ~ minecraft:barrel[facing=up] destroy
item replace block ~ ~ ~ container.0 from entity @s weapon.mainhand

summon item_display ~ ~3 ~ {Tags:["armory-tower"],billboard:"fixed",item_display:"ground",view_range:0.8f}
data modify entity @n[type=item_display,tag=armory-tower,distance=..0.1,sort=nearest,limit=1] item set from entity @s SelectedItem