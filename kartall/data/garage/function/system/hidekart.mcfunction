scoreboard players reset @s garage-time

#크기 키우기
data merge entity @s {start_interpolation:0,interpolation_duration:5,transformation:{scale:[1.25f,1.25f,1.25f],translation:[0f,0.25f,0f]}}

#상자로 바꾸기
execute if entity @s[tag=garage-show-unsecurity] positioned ~ ~-3 ~ if block ~ ~ ~ barrel run function garage:system/security-chest/change-to-chest

#카트 죽이기
execute positioned ~ ~-0.5 ~ run kill @e[distance=..0.0001,tag=!garage-show,tag=!garage-text,type=!player]
tag @s remove garage-show
tag @s remove garage-show-unsecurity
tag @s remove show-transform