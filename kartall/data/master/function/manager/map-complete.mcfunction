# 플레이어가 특정 맵을 클리어했을 때 호출
# mastermap 스코어가 현재 클리어한 맵 ID
# current_targets 리스트에서 해당 ID 제거

# 매크로를 사용하여 제거 (리스트 순회하며 값 비교 제거는 복잡하므로)
# 매크로가 아닌 재귀나 반복 필요. 하지만 여기선 3개뿐이므로 하드코딩 체크 가능.
# 혹은 current_targets에서 값 제거를 시도.

# 값이 존재하는지 확인하고 제거하는 매크로
# execute store result storage master:game completed_map_id int 1 run scoreboard players get @p mastermap
# function master:manager/remove-completed-map-macro with storage master:game

# Update based on masterPanelIdx
execute if score #game masterPanelIdx matches 0 run data modify storage master:game current_targets[0] set value -1
execute if score #game masterPanelIdx matches 0 run data modify entity @e[tag=panel_banned_0,limit=1] text set value {text:"CLEAR!","color":"green","bold":true}
execute if score #game masterPanelIdx matches 0 run kill @e[tag=panel_interaction_0]

execute if score #game masterPanelIdx matches 1 run data modify storage master:game current_targets[1] set value -1
execute if score #game masterPanelIdx matches 1 run data modify entity @e[tag=panel_banned_1,limit=1] text set value {text:"CLEAR!","color":"green","bold":true}
execute if score #game masterPanelIdx matches 1 run kill @e[tag=panel_interaction_1]

execute if score #game masterPanelIdx matches 2 run data modify storage master:game current_targets[2] set value -1
execute if score #game masterPanelIdx matches 2 run data modify entity @e[tag=panel_banned_2,limit=1] text set value {text:"CLEAR!","color":"green","bold":true}
execute if score #game masterPanelIdx matches 2 run kill @e[tag=panel_interaction_2]

# 패널 업데이트 (클리어 표시)
# 이미 클리어 된 맵은 리스트에서 빠지므로, 남은 맵만 다시 그리거나
# 혹은 별도의 finished_maps 리스트 관리하여 UI 표시.
# 여기서는 간단하게 "남은 맵"만 다시 그리는 방식 (select-final-3 처럼)
# 또는 클리어 된 맵 패널을 찾아서 제거/변경.

# 모든 맵 클리어 체크 (current_targets 요소가 모두 -1인지 확인)
# data get으로 확인하거나, temp storage로 확인

# -1이 아닌 요소가 하나라도 있는지 확인
execute unless data storage master:game current_targets[0] run function master:manager/round-complete
# 리스트 요소가 제거되지 않고 -1로 바뀌었으므로, 모든 요소가 -1인지 확인해야 함.
# 간단한 방법: -1이 아닌 요소를 찾음.
scoreboard players set #not_cleared master 0

execute store result score #temp master run data get storage master:game current_targets[0]
execute unless score #temp master matches -1 run scoreboard players set #not_cleared master 1

execute store result score #temp master run data get storage master:game current_targets[1]
execute unless score #temp master matches -1 run scoreboard players set #not_cleared master 1

execute store result score #temp master run data get storage master:game current_targets[2]
execute unless score #temp master matches -1 run scoreboard players set #not_cleared master 1

execute if score #not_cleared master matches 0 run function master:manager/round-complete

# 아직 남았으면 UI 갱신 로직 (선택적)
# ...
