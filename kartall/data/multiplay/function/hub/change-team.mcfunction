execute store result score @s kart-team if score @s kart-team matches 0
clear @s jigsaw
loot give @s loot {type:"minecraft:block",pools:[{rolls:1,bonus_rolls:0,entries:[{type:"minecraft:item",name:"jigsaw",functions:[{function:"minecraft:set_components",components:{"minecraft:custom_name":[{italic:false,translate:"팀 바꾸기["},{keybind:"key.swapOffhand"},{translate:"]"}]},conditions:[]}]}]}],functions:[]}
#{"type":"minecraft:block","pools":[{"bonus_rolls":0,"entries":[{"type":"minecraft:item","name":"minecraft:jigsaw","functions":[{"function":"minecraft:set_components","components":{"minecraft:custom_name":"[{\"italic\":false,\"text\":\"팀 바꾸기[\"},{\"keybind\":\"key.swapOffhand\"},\"]\"]"}}]}],"rolls":1}]}

tellraw @s[scores={kart-team=0}] [{translate:"팀을 ","color":"aqua"},{translate:"레드팀","color":"red"},{translate:"으로 변경했습니다.","color":"aqua"}]
tellraw @s[scores={kart-team=1}] [{translate:"팀을 ","color":"aqua"},{translate:"블루팀","color":"blue"},{translate:"으로 변경했습니다.","color":"aqua"}]

playsound minecraft:entity.experience_orb.pickup neutral @s