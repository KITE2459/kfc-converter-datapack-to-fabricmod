data modify storage minecraft:kartmain commandpad set from block ~ ~ ~ Command
execute at @s run function kartmobil:move/movetp/jump-and-boost/command-pad-scale-macro with storage minecraft:kartmain

tag @s add kart-command-pad-executed