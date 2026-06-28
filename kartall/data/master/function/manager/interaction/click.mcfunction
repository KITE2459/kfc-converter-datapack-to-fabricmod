# 상호작용 엔티티가 클릭되었을 때 실행되는 함수

# 1. 밴 단계일 경우 (#game_state = 1)
# 확정 버튼 클릭 처리
execute if score #game_state master matches 1 if entity @s[tag=ban_confirm_button] run function master:manager/interaction/ban-confirm-execute
# 일반 패널 클릭 처리
execute if score #game_state master matches 1 unless entity @s[tag=ban_confirm_button] run function master:manager/interaction/ban

# 2. 플레이 단계일 경우 (#game_state = 3) -> 맵 시작
# 클릭된 패널의 인덱스 확인 (panel_0, panel_1, panel_2 중 하나일 것임)
execute if score #game_state master matches 3 run function master:manager/interaction/start-selected-map
