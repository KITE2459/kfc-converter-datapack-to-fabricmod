

#위치
execute on vehicle on vehicle run data modify storage timeattack:timeattack-shadows temp-shadow.pos append from entity @s Pos
#회전각도
execute as @n[tag=kartmodel,type=#kartmobil:modeldir] run data modify storage timeattack:timeattack-shadows temp-shadow.rotation append from entity @s Rotation
#부스터 타이밍
execute on vehicle on vehicle store result storage timeattack:timeattack-shadows temp-booster int 1 run scoreboard players get @s kartboosttime
data modify storage timeattack:timeattack-shadows temp-shadow.booster append from storage timeattack:timeattack-shadows temp-booster
#현재속도(속도계 / 2)
execute on vehicle on vehicle store result storage timeattack:timeattack-shadows temp-kartspeed int 0.0035971223021583 run scoreboard players get @s kartmove
data modify storage timeattack:timeattack-shadows temp-shadow.kartspeed append from storage timeattack:timeattack-shadows temp-kartspeed