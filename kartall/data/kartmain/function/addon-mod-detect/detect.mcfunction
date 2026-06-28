# scoreboard players set #pos-mod kartmain 0
# scoreboard players set #sidite-cursed-mod kartmain 0
# scoreboard players set #kfc-kartriderpack kartmain 0
# scoreboard players set #kfc-gamesystem kartmain 0
scoreboard players set #kfc-converted kartmain 0


scoreboard players set #addon-mod-detect-timer kartmain 0

#모드 감지
# summon marker 0.0 0.0 0.0 {Tags:["pos-mod-detect"]}

function kartmain:addon-mod-detect/if-kfc-converted
# function kartmain:addon-mod-detect/if-kfc-kartriderpack
# function kartmain:addon-mod-detect/if-kfc-gamesystem
# function kartmain:addon-mod-detect/if-sidite-cursed-mod
# execute as @e[tag=pos-mod-detect,type=marker] at @s run function kartmain:addon-mod-detect/if-pos-mod

# kill @e[tag=pos-mod-detect]

#뛰라이더 모션 느리게 만드는 용도의 인원수 감지

