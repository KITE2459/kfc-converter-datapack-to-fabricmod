execute positioned as @s if block ~ ~1.5 ~ #kartmobil:ignoreblock if score #movetp-y kartmain matches ..-400 positioned ~ ~0.2 ~ run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #movetp-y kartmain matches ..-400 run scoreboard players add #movetp-y kartmain 400

execute positioned as @s if block ~ ~1.5 ~ #kartmobil:ignoreblock if score #movetp-y kartmain matches ..-200 positioned ~ ~0.1 ~ run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #movetp-y kartmain matches ..-200 run scoreboard players add #movetp-y kartmain 200

execute positioned as @s if block ~ ~1.5 ~ #kartmobil:ignoreblock if score #movetp-y kartmain matches ..-100 positioned ~ ~0.05 ~ run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #movetp-y kartmain matches ..-100 run scoreboard players add #movetp-y kartmain 100

execute unless score #movetp-y kartmain matches -100..100 run function kartmobil:move/movetp/moveupdown/moveup
