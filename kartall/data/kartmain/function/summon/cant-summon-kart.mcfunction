summon armor_stand 0 0 0 {Tags:["kartswaphand"]}

item replace entity @n[tag=kartswaphand] weapon from entity @s weapon.offhand
item replace entity @s weapon.offhand from entity @s weapon
item replace entity @s weapon from entity @n[tag=kartswaphand] weapon

kill @e[tag=kartswaphand]

playsound minecraft:block.anvil.place neutral @s ~ ~ ~ 1 1
title @s title ""
title @s subtitle [{bold:true,text:"여기서는 카트 설치가 불가능합니다",color:red}]
title @s times 0 20 20

return 1
