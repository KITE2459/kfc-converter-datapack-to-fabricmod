function kartmobil:control/boost-gauge/main

#페달조작
execute if entity @s[tag=kart-w-pressed] run function kartmobil:control/accel
execute if entity @p[tag=kartpassenger,predicate=kartmobil:s] run function kartmobil:control/brake

#전진 키 이벤트
tag @s remove kart-w-press
tag @s remove kart-w-release
execute if entity @s[tag=kart-w-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:w] run tag @s add kart-w-release
execute if entity @s[tag=kart-w-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:w] run tag @s remove kart-w-pressed
execute if entity @s[tag=!kart-w-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:w] run tag @s add kart-w-press
execute if entity @s[tag=!kart-w-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:w] run tag @s add kart-w-pressed

#디버그용 전진 이벤트
execute if entity @s[tag=!kart-w-pressed] if entity @p[tag=kartpassenger,tag=auto-forward] run tag @s add kart-w-press
execute if entity @s[tag=!kart-w-pressed] if entity @p[tag=kartpassenger,tag=auto-forward] run tag @s add kart-w-pressed



#부스터 키 이벤트
tag @s remove kart-boost-press
execute if entity @s[tag=kart-boost-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:a,predicate=!kartmobil:d] run tag @s remove kart-boost-pressed
execute if entity @s[tag=!kart-boost-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run tag @s add kart-boost-press
execute if entity @s[tag=!kart-boost-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run tag @s add kart-boost-press
execute if entity @s[tag=!kart-boost-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run tag @s add kart-boost-pressed
execute if entity @s[tag=!kart-boost-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run tag @s add kart-boost-pressed

#드리프트 키 이벤트
tag @s remove kart-space-press
tag @s remove kart-space-release
execute if entity @s[tag=kart-space-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:space] run tag @s add kart-space-release
execute if entity @s[tag=kart-space-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:space] run tag @s remove kart-space-pressed
execute if entity @s[tag=!kart-space-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:space] run tag @s add kart-space-press
execute if entity @s[tag=!kart-space-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:space] run tag @s add kart-space-pressed