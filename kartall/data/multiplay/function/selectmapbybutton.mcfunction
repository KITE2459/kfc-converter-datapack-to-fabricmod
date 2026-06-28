execute if score #game multi-main matches 1.. run playsound minecraft:block.anvil.place neutral @a ~ ~ ~ 1 1
execute if score #game multi-main matches 1.. run return 1

fill -30 0 153 -4 0 153 minecraft:light_gray_concrete
fill -4 0 154 -4 0 172 minecraft:light_gray_concrete

setblock ^ ^ ^-1 minecraft:gold_block
$scoreboard players set #map multi-main $(mapnumber)

playsound minecraft:entity.experience_orb.pickup weather @a