function devbattle:system/dev-model/remove-select-room

execute positioned -31 5 244 run function devbattle:system/dev-model/lpeng
execute positioned -27 5 244 run function devbattle:system/dev-model/sidite
execute positioned -23 5 244 run function devbattle:system/dev-model/pangch
execute positioned -19 5 244 run function devbattle:system/dev-model/kite
execute positioned -15 5 244 run function devbattle:system/dev-model/loggamja
execute positioned -11 5 244 run function devbattle:system/dev-model/another


execute positioned -10 5 253 run function devbattle:system/dev-model/ghangdhang
execute positioned -10 5 256 run function devbattle:system/dev-model/nekter
execute positioned -10 5 259 run function devbattle:system/dev-model/towercrain

execute positioned -10 5 253 as @e[distance=..0.1,type=!player] at @s run tp @s ~ ~ ~ ~90 ~
execute positioned -10 5 256 as @e[distance=..0.1,type=!player] at @s run tp @s ~ ~ ~ ~90 ~
execute positioned -10 5 259 as @e[distance=..0.1,type=!player] at @s run tp @s ~ ~ ~ ~90 ~