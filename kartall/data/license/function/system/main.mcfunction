

#init
execute if score @s licensecount matches 1 run function license:system/init

scoreboard players add @s licensecount 1

#내리면 리타이어
execute if score @s licensecount matches 20.. if entity @s[predicate=!kartmobil:ifride] run function license:system/retire

#물이나 공허에 닿으면 r키
# execute on vehicle on vehicle at @s if block ~ ~0.25 ~ water on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey
execute on vehicle on vehicle at @s if block ~ ~-0.5 ~ structure_void on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey

#카운트다운
execute if score @s licensecount matches ..100 run function license:system/countdown

#각 스테이지
execute if score @s licensestage matches 1 run function license:system/rookie/mission1
execute if score @s licensestage matches 2 run function license:system/rookie/mission2
execute if score @s licensestage matches 3 run function license:system/rookie/mission3

execute if score @s licensestage matches 4 run function license:system/l3/mission1
execute if score @s licensestage matches 5 run function license:system/l3/mission2
execute if score @s licensestage matches 6 run function license:system/l3/mission3

execute if score @s licensestage matches 7 run function license:system/l2/mission1
execute if score @s licensestage matches 8 run function license:system/l2/mission2
execute if score @s licensestage matches 9 run function license:system/l2/mission3

execute if score @s licensestage matches 10 run function license:system/l1/mission1
execute if score @s licensestage matches 11 run function license:system/l1/mission2
execute if score @s licensestage matches 12 run function license:system/l1/mission3

execute if score @s licensestage matches 13 run function license:system/pro/mission1
execute if score @s licensestage matches 14 run function license:system/pro/mission2
execute if score @s licensestage matches 15 run function license:system/pro/mission3
execute if score @s licensestage matches 16 run function license:system/pro/mission4
execute if score @s licensestage matches 17 run function license:system/pro/mission5