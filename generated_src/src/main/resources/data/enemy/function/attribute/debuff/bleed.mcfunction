# 타이머 확인
scoreboard players set #20 enemy.attribute.timer 20
scoreboard players operation #temp enemy.attribute.timer = @s enemy.attribute.timer
scoreboard players operation #temp enemy.attribute.timer %= #20 enemy.attribute.timer

# 출혈 효과 스택 설정
execute if score @s enemy.hp matches 1000000.. run scoreboard players set #1000 enemy.state.bleed 200000
execute if score @s enemy.hp matches 750000..999999 run scoreboard players set #1000 enemy.state.bleed 172000
execute if score @s enemy.hp matches 562500..749999 run scoreboard players set #1000 enemy.state.bleed 147920
execute if score @s enemy.hp matches 421875..562499 run scoreboard players set #1000 enemy.state.bleed 127211
execute if score @s enemy.hp matches 316406..421874 run scoreboard players set #1000 enemy.state.bleed 109401
execute if score @s enemy.hp matches 237304..316405 run scoreboard players set #1000 enemy.state.bleed 94084
execute if score @s enemy.hp matches 177978..237303 run scoreboard players set #1000 enemy.state.bleed 80912
execute if score @s enemy.hp matches 133483..177977 run scoreboard players set #1000 enemy.state.bleed 69584
execute if score @s enemy.hp matches 100112..133482 run scoreboard players set #1000 enemy.state.bleed 59842
execute if score @s enemy.hp matches 75084..100111 run scoreboard players set #1000 enemy.state.bleed 51464
execute if score @s enemy.hp matches 56313..75083 run scoreboard players set #1000 enemy.state.bleed 44259
execute if score @s enemy.hp matches 42234..56312 run scoreboard players set #1000 enemy.state.bleed 38062
execute if score @s enemy.hp matches 31675..42233 run scoreboard players set #1000 enemy.state.bleed 32733
execute if score @s enemy.hp matches 23756..31674 run scoreboard players set #1000 enemy.state.bleed 28150
execute if score @s enemy.hp matches 17817..23755 run scoreboard players set #1000 enemy.state.bleed 24209
execute if score @s enemy.hp matches 13362..17816 run scoreboard players set #1000 enemy.state.bleed 20819
execute if score @s enemy.hp matches 10021..13361 run scoreboard players set #1000 enemy.state.bleed 17904
execute if score @s enemy.hp matches 7515..10020 run scoreboard players set #1000 enemy.state.bleed 15397
execute if score @s enemy.hp matches 5636..7514 run scoreboard players set #1000 enemy.state.bleed 13241
execute if score @s enemy.hp matches 4227..5635 run scoreboard players set #1000 enemy.state.bleed 11387
execute if score @s enemy.hp matches 3170..4226 run scoreboard players set #1000 enemy.state.bleed 9792
execute if score @s enemy.hp matches 2377..3169 run scoreboard players set #1000 enemy.state.bleed 8421
execute if score @s enemy.hp matches 1782..2376 run scoreboard players set #1000 enemy.state.bleed 7242
execute if score @s enemy.hp matches 1336..1781 run scoreboard players set #1000 enemy.state.bleed 6228
execute if score @s enemy.hp matches 1002..1335 run scoreboard players set #1000 enemy.state.bleed 5356
execute if score @s enemy.hp matches 751..1001 run scoreboard players set #1000 enemy.state.bleed 4606
execute if score @s enemy.hp matches 563..750 run scoreboard players set #1000 enemy.state.bleed 3961
execute if score @s enemy.hp matches 422..562 run scoreboard players set #1000 enemy.state.bleed 3406
execute if score @s enemy.hp matches 316..421 run scoreboard players set #1000 enemy.state.bleed 2929
execute if score @s enemy.hp matches 237..315 run scoreboard players set #1000 enemy.state.bleed 2518
execute if score @s enemy.hp matches 177..236 run scoreboard players set #1000 enemy.state.bleed 2165
execute if score @s enemy.hp matches 132..176 run scoreboard players set #1000 enemy.state.bleed 1861
execute if score @s enemy.hp matches 99..131 run scoreboard players set #1000 enemy.state.bleed 1600
execute if score @s enemy.hp matches 74..98 run scoreboard players set #1000 enemy.state.bleed 1376
execute if score @s enemy.hp matches 55..73 run scoreboard players set #1000 enemy.state.bleed 1183
execute if score @s enemy.hp matches 41..54 run scoreboard players set #1000 enemy.state.bleed 1017
execute if score @s enemy.hp matches 30..40 run scoreboard players set #1000 enemy.state.bleed 874
execute if score @s enemy.hp matches 22..29 run scoreboard players set #1000 enemy.state.bleed 751
execute if score @s enemy.hp matches 16..21 run scoreboard players set #1000 enemy.state.bleed 645
execute if score @s enemy.hp matches 12..15 run scoreboard players set #1000 enemy.state.bleed 554
execute if score @s enemy.hp matches 9..11 run scoreboard players set #1000 enemy.state.bleed 476
execute if score @s enemy.hp matches 6..8 run scoreboard players set #1000 enemy.state.bleed 409
execute if score @s enemy.hp matches 4..5 run scoreboard players set #1000 enemy.state.bleed 351
execute if score @s enemy.hp matches 3..3 run scoreboard players set #1000 enemy.state.bleed 301
execute if score @s enemy.hp matches 2..2 run scoreboard players set #1000 enemy.state.bleed 258
execute if score @s enemy.hp matches 1..1 run scoreboard players set #1000 enemy.state.bleed 221
execute if score @s enemy.hp matches 0..0 run scoreboard players set #1000 enemy.state.bleed 190

execute if score #temp enemy.attribute.timer matches 0 store result score #temp enemy.hp run scoreboard players get @s enemy.hp
execute if score #temp enemy.attribute.timer matches 0 store result score #temp enemy.state.bleed run scoreboard players get @s enemy.state.bleed

# (hp / 1000) * bleed
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_q enemy.hp = #temp enemy.hp
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_q enemy.hp /= #1000 enemy.state.bleed
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_q enemy.hp *= #temp enemy.state.bleed

# (hp % 1000) * bleed / 1000
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_r enemy.hp = #temp enemy.hp
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_r enemy.hp %= #1000 enemy.state.bleed
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_r enemy.hp *= #temp enemy.state.bleed
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_r enemy.hp /= #1000 enemy.state.bleed

# result sum
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation #temp_q enemy.hp += #temp_r enemy.hp
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation @s enemy.hp -= #temp_q enemy.hp

# 스택 감소
execute if score #temp enemy.attribute.timer matches 0 store result storage temp bleed int 0.75 run scoreboard players get @s enemy.state.bleed
execute if score #temp enemy.attribute.timer matches 0 store result score @s enemy.state.bleed run data get storage temp bleed

# 데이터 보기용 
execute if score #temp enemy.attribute.timer matches 0 run scoreboard players operation bleed statistics += #temp_q enemy.hp