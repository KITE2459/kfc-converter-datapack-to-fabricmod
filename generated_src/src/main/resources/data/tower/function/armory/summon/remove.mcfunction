scoreboard players reset @s amory-time

# 크기 키우기
data merge entity @s {start_interpolation:0,interpolation_duration:5,transformation:{scale:[1.25f,1.25f,1.25f],translation:[0f,0.25f,0f]}}

# 타워 죽이기
execute positioned ~ ~-0.5 ~ run kill @e[distance=..0.0001,tag=!armory-show,tag=!armory-text,type=!player]
tag @s remove armory-show
tag @s remove armory-show-unsecurity
tag @s remove show-transform