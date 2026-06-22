# 틱 주기 설정
scoreboard players set #7 enemy.attribute.timer 2
scoreboard players operation #temp enemy.attribute.timer = @s enemy.attribute.timer
scoreboard players operation #temp enemy.attribute.timer %= #7 enemy.attribute.timer

# 독 스택 설정
execute if score @s enemy.hp matches 1000000.. run scoreboard players set #1000 enemy.state.poison 25000
execute if score @s enemy.hp matches 750000..999999 run scoreboard players set #1000 enemy.state.poison 21125
execute if score @s enemy.hp matches 562500..749999 run scoreboard players set #1000 enemy.state.poison 17850
execute if score @s enemy.hp matches 421875..562499 run scoreboard players set #1000 enemy.state.poison 15083
execute if score @s enemy.hp matches 316406..421874 run scoreboard players set #1000 enemy.state.poison 12745
execute if score @s enemy.hp matches 237304..316405 run scoreboard players set #1000 enemy.state.poison 10769
execute if score @s enemy.hp matches 177978..237303 run scoreboard players set #1000 enemy.state.poison 9099
execute if score @s enemy.hp matches 133483..177977 run scoreboard players set #1000 enemy.state.poison 7688
execute if score @s enemy.hp matches 100112..133482 run scoreboard players set #1000 enemy.state.poison 6496
execute if score @s enemy.hp matches 75084..100111 run scoreboard players set #1000 enemy.state.poison 5489
execute if score @s enemy.hp matches 56313..75083 run scoreboard players set #1000 enemy.state.poison 4638
execute if score @s enemy.hp matches 42234..56312 run scoreboard players set #1000 enemy.state.poison 3919
execute if score @s enemy.hp matches 31675..42233 run scoreboard players set #1000 enemy.state.poison 3311
execute if score @s enemy.hp matches 23756..31674 run scoreboard players set #1000 enemy.state.poison 2797
execute if score @s enemy.hp matches 17817..23755 run scoreboard players set #1000 enemy.state.poison 2363
execute if score @s enemy.hp matches 13362..17816 run scoreboard players set #1000 enemy.state.poison 1996
execute if score @s enemy.hp matches 10021..13361 run scoreboard players set #1000 enemy.state.poison 1686
execute if score @s enemy.hp matches 7515..10020 run scoreboard players set #1000 enemy.state.poison 1424
execute if score @s enemy.hp matches 5636..7514 run scoreboard players set #1000 enemy.state.poison 1203
execute if score @s enemy.hp matches 4227..5635 run scoreboard players set #1000 enemy.state.poison 1016
execute if score @s enemy.hp matches 3170..4226 run scoreboard players set #1000 enemy.state.poison 858
execute if score @s enemy.hp matches 2377..3169 run scoreboard players set #1000 enemy.state.poison 725
execute if score @s enemy.hp matches 1782..2376 run scoreboard players set #1000 enemy.state.poison 612
execute if score @s enemy.hp matches 1336..1781 run scoreboard players set #1000 enemy.state.poison 517
execute if score @s enemy.hp matches 1002..1335 run scoreboard players set #1000 enemy.state.poison 436
execute if score @s enemy.hp matches 751..1001 run scoreboard players set #1000 enemy.state.poison 368
execute if score @s enemy.hp matches 563..750 run scoreboard players set #1000 enemy.state.poison 310
execute if score @s enemy.hp matches 422..562 run scoreboard players set #1000 enemy.state.poison 261
execute if score @s enemy.hp matches 316..421 run scoreboard players set #1000 enemy.state.poison 220
execute if score @s enemy.hp matches 237..315 run scoreboard players set #1000 enemy.state.poison 185
execute if score @s enemy.hp matches 177..236 run scoreboard players set #1000 enemy.state.poison 156
execute if score @s enemy.hp matches 132..176 run scoreboard players set #1000 enemy.state.poison 131
execute if score @s enemy.hp matches 99..131 run scoreboard players set #1000 enemy.state.poison 110
execute if score @s enemy.hp matches 74..98 run scoreboard players set #1000 enemy.state.poison 92
execute if score @s enemy.hp matches 55..73 run scoreboard players set #1000 enemy.state.poison 77
execute if score @s enemy.hp matches 41..54 run scoreboard players set #1000 enemy.state.poison 65
execute if score @s enemy.hp matches 30..40 run scoreboard players set #1000 enemy.state.poison 54
execute if score @s enemy.hp matches 22..29 run scoreboard players set #1000 enemy.state.poison 45
execute if score @s enemy.hp matches 16..21 run scoreboard players set #1000 enemy.state.poison 38
execute if score @s enemy.hp matches 12..15 run scoreboard players set #1000 enemy.state.poison 32
execute if score @s enemy.hp matches 9..11 run scoreboard players set #1000 enemy.state.poison 27
execute if score @s enemy.hp matches 6..8 run scoreboard players set #1000 enemy.state.poison 22
execute if score @s enemy.hp matches 4..5 run scoreboard players set #1000 enemy.state.poison 18
execute if score @s enemy.hp matches 3..3 run scoreboard players set #1000 enemy.state.poison 15
execute if score @s enemy.hp matches 2..2 run scoreboard players set #1000 enemy.state.poison 12
execute if score @s enemy.hp matches 1..1 run scoreboard players set #1000 enemy.state.poison 10
execute if score @s enemy.hp matches 0..0 run scoreboard players set #1000 enemy.state.poison 8
execute store result storage temp temp int 10 run scoreboard players get #1000 enemy.state.poison
execute store result score #1000 enemy.state.poison run data get storage temp temp

# 처형
scoreboard players set #1000 enemy.hp 1000
scoreboard players operation #temp enemy.hp = @s enemy.max_hp
scoreboard players operation #temp_r enemy.hp = @s enemy.max_hp
scoreboard players operation #temp enemy.hp /= #1000 enemy.hp
scoreboard players operation #temp enemy.hp *= @s enemy.state.poison
scoreboard players operation #temp_r enemy.hp %= #1000 enemy.hp
scoreboard players operation #temp_r enemy.hp *= @s enemy.state.poison
scoreboard players operation #temp_r enemy.hp /= #1000 enemy.hp
scoreboard players operation #temp enemy.hp += #temp_r enemy.hp
execute if score @s enemy.hp <= #temp enemy.hp run particle dust{color:[0.0f,0.5f,0.0f],scale:2.0f} ~ ~1 ~ 0.3 0.6 0.3 0 20 force @a
execute if score @s enemy.hp <= #temp enemy.hp run particle dust{color:[0.5f,0.5f,0.5f],scale:2.0f} ~ ~1 ~ 0.3 0.6 0.3 0 20 force @a
execute if score @s enemy.hp <= #temp enemy.hp run particle dust{color:[0.1f,0.1f,0.1f],scale:2.0f} ~ ~1 ~ 0.3 0.6 0.3 0 20 force @a
execute if score @s enemy.hp <= #temp enemy.hp run function enemy:hit/death with entity @s data
execute if score @s enemy.hp <= #temp enemy.hp run scoreboard players operation poison statistics += #temp enemy.hp

# 독 피해
execute if score #temp enemy.attribute.timer matches 0 store result score #temp enemy.hp run scoreboard players get @s enemy.hp
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp enemy.hp /= #1000 enemy.state.poison
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation @s enemy.hp -= #temp enemy.hp

# 스택 감소
execute if score #temp enemy.attribute.timer matches 0 store result storage temp poison int 0.97 run scoreboard players get @s enemy.state.poison
execute if score #temp enemy.attribute.timer matches 0 store result score @s enemy.state.poison run data get storage temp poison

# 데이터 보기용 
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation poison statistics += #temp enemy.hp