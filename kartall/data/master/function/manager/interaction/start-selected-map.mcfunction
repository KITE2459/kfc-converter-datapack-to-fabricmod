# 선택된 맵 시작 로직
# 클릭된 패널의 인덱스를 식별하고, current_targets에서 해당 인덱스의 맵 ID를 가져와 시작

# 인덱스 식별 (임시 스코어 #clicked_idx)
scoreboard players set #clicked_idx master -1

# start-selected-map은 현재 select-final-3 이후 단계이므로
# panel_0, panel_1, panel_2 태그만 존재함.
execute if entity @s[tag=panel_0] run scoreboard players set #clicked_idx master 0
execute if entity @s[tag=panel_1] run scoreboard players set #clicked_idx master 1
execute if entity @s[tag=panel_2] run scoreboard players set #clicked_idx master 2

# 유효한 인덱스인지 확인하고 시작
scoreboard objectives add masterPanelIdx dummy
execute if score #clicked_idx master matches 0..2 run scoreboard players operation #game masterPanelIdx = #clicked_idx master
execute if score #clicked_idx master matches 0..2 run function master:manager/interaction/start-map-macro
