item replace entity @n[tag=garage-special-icon-temp,type=item_display] contents from entity @n[tag=mariokart-icon,type=item_display] contents
$data modify entity @n[tag=garage-special-icon-temp,type=item_display] item.components."minecraft:profile".properties[0].value set from storage minecraft:kartmain itemtexture.$(item)

execute at @n[tag=garage-special-icon-temp,type=item_display] run rotate @n[tag=garage-special-icon-temp,type=item_display] ~180 ~