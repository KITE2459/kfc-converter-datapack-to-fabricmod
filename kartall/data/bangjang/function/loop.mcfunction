execute unless score #game multi-main matches 1 run function bangjang:manage
execute if score #game multi-main matches 1 run bossbar remove minecraft:bangjang

#방장팩 꺼짐감지
scoreboard players set #bangjangpack kartmain 1
execute if block -20 4 151 air run setblock -20 4 151 minecraft:redstone_block