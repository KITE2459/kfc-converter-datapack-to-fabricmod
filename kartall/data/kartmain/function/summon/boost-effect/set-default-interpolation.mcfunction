execute as @e[tag=kart-boost-move,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.interpolation_duration_hide run data modify entity @s data.interpolation_duration_hide set value 3

execute as @e[tag=kart-boost-move,tag=!kart-boost-move-start,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.interpolation_duration run data modify entity @s data.interpolation_duration set value 6

#이동형 변부는 slow/fast 레거시 지원
execute as @e[tag=kart-boost-move,tag=kart-boost-move-start,tag=kart-boost-move-slow,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.interpolation_duration run data modify entity @s data.interpolation_duration set value 5
execute as @e[tag=kart-boost-move,tag=kart-boost-move-start,tag=kart-boost-move-fast,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.interpolation_duration run data modify entity @s data.interpolation_duration set value 1
execute as @e[tag=kart-boost-move,tag=kart-boost-move-start,tag=!kart-boost-move-fast,tag=!kart-boost-move-slow,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.interpolation_duration run data modify entity @s data.interpolation_duration set value 3

#불꽃 기본값. SR엔진은 조금 느림
execute if score @s kart-engine matches 11 as @e[tag=kart-boost-flame,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.interpolation_duration run data modify entity @s data.interpolation_duration set value 6
execute unless score @s kart-engine matches 11 as @e[tag=kart-boost-flame,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.interpolation_duration run data modify entity @s data.interpolation_duration set value 2
execute as @e[tag=kart-boost-flame,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.interpolation_duration_hide run data modify entity @s data.interpolation_duration_hide set value 2

#start_interpolation
execute as @e[tag=kart-boost-move,distance=..0.3,type=#kartmobil:kartmodels] unless data entity @s data.start_interpolation run data modify entity @s data.start_interpolation set value 0
