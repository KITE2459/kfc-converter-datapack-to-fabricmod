execute store result score clear-data licensestage run data get storage license-cleardata:license-data cleared-stage

#clear-data-from-file licensestage 기반

#엔딩, 차고 초기화
fill -102 4 208 -102 5 208 minecraft:chiseled_tuff_bricks
fill -102 5 212 -92 5 219 air replace #minecraft:buttons
setblock -94 5 219 minecraft:oak_button[face=wall,facing=north,powered=false]
setblock -114 5 217 air
setblock -114 5 213 air

#엔진룸 초기화
fill -91 7 215 -91 4 213 minecraft:iron_bars

#버튼 초기화
fill -115 5 242 -123 5 242 minecraft:chiseled_tuff_bricks
fill -115 5 241 -123 5 241 air
setblock -115 5 241 minecraft:oak_button[face=wall,facing=north,powered=false]


#버튼 초기화 2
clone -142 5 275 -136 5 274 -142 5 262
clone -142 5 275 -136 5 274 -122 5 262
clone -142 5 275 -136 5 274 -102 5 262
clone -142 5 275 -136 5 274 -82 5 262
fill -55 5 263 -63 5 263 minecraft:chiseled_tuff_bricks
fill -55 5 262 -63 5 262 minecraft:air
setblock -55 5 262 minecraft:oak_button[face=wall,facing=north,powered=false]

#더미룸
forceload add 4969 5129
setblock 4965 5 5130 air

#마스터 초기화
clone -125 17 235 -125 21 239 -125 4 235

#각 라이센스 클리어
execute if score clear-data-from-file licensestage matches 1.. run function license:system/rookie/mission1complete
execute if score clear-data-from-file licensestage matches 2.. run function license:system/rookie/mission2complete
execute if score clear-data-from-file licensestage matches 3.. run function license:system/rookie/mission3complete

execute if score clear-data-from-file licensestage matches 4.. run function license:system/l3/mission1complete
execute if score clear-data-from-file licensestage matches 5.. run function license:system/l3/mission2complete
execute if score clear-data-from-file licensestage matches 6.. run function license:system/l3/mission3complete

execute if score clear-data-from-file licensestage matches 7.. run function license:system/l2/mission1complete
execute if score clear-data-from-file licensestage matches 8.. run function license:system/l2/mission2complete
execute if score clear-data-from-file licensestage matches 9.. run function license:system/l2/mission3complete

execute if score clear-data-from-file licensestage matches 10.. run function license:system/l1/mission1complete
execute if score clear-data-from-file licensestage matches 11.. run function license:system/l1/mission2complete
execute if score clear-data-from-file licensestage matches 12.. run function license:system/l1/mission3complete

execute if score clear-data-from-file licensestage matches 13.. run function license:system/pro/mission1complete
execute if score clear-data-from-file licensestage matches 14.. run function license:system/pro/mission2complete
execute if score clear-data-from-file licensestage matches 15.. run function license:system/pro/mission3complete
execute if score clear-data-from-file licensestage matches 16.. run function license:system/pro/mission4complete
execute if score clear-data-from-file licensestage matches 17.. run function license:system/pro/mission5complete