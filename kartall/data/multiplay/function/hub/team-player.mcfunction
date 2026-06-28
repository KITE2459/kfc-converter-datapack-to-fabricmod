execute if items entity @s weapon.offhand minecraft:jigsaw run function multiplay:hub/change-team
execute unless items entity @s container.* minecraft:jigsaw run loot give @s loot {type:"minecraft:block",pools:[{rolls:1,bonus_rolls:0,entries:[{type:"minecraft:item",name:"jigsaw",functions:[{function:"minecraft:set_components",components:{"minecraft:custom_name":[{italic:false,translate:"팀 바꾸기["},{keybind:"key.swapOffhand"},{translate:"]"}]},conditions:[]}]}]}],functions:[]}
#{"type":"minecraft:block","pools":[{"bonus_rolls":0,"entries":[{"type":"minecraft:item","name":"minecraft:jigsaw","functions":[{"function":"minecraft:set_components","components":{"minecraft:custom_name":"[{\"italic\":false,\"text\":\"팀 바꾸기[\"},{\"keybind\":\"key.swapOffhand\"},\"]\"]"}}]}],"rolls":1}]}

execute unless score @s kart-team matches 0..1 run scoreboard players set @s kart-team 0

#모자 색깔
execute if items entity @s[scores={kart-team=0}] armor.head minecraft:leather_helmet[minecraft:dyed_color=255,enchantments={"minecraft:binding_curse":1}] run item replace entity @s armor.head with air
execute if items entity @s[scores={kart-team=1}] armor.head minecraft:leather_helmet[minecraft:dyed_color=16711680,enchantments={"minecraft:binding_curse":1}] run item replace entity @s armor.head with air

execute unless items entity @s[scores={kart-team=0}] armor.head minecraft:leather_helmet rotated ~ 0 run item replace entity @s armor.head with leather_helmet[dyed_color=16711680,enchantments={"minecraft:binding_curse":1}] 1
execute unless items entity @s[scores={kart-team=1}] armor.head minecraft:leather_helmet rotated ~ 0 run item replace entity @s armor.head with leather_helmet[dyed_color=255,enchantments={"minecraft:binding_curse":1}] 1


