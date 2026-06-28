
execute if block ~ ~ ~ #garage:can-place-on positioned ^ ^ ^-0.01 if block ~ ~ ~ #kartmobil:ignoreblock run function garage:custom-model-maker/building-system/place with entity @s SelectedItem
execute if block ~ ~ ~ #garage:can-place-on run return 1
execute positioned ^ ^ ^0.01 if entity @s[distance=..20] run function garage:custom-model-maker/building-system/raycast