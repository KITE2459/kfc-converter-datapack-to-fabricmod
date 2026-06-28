# master 게임을 제어하는 메인 함수
# 게임 상태에 따라 다른 로직을 실행하거나 초기화합니다.

# 게임 상태 확인 (0: 초기화/대기, 1: 맵 밴 단계, 2: 맵 선택 완료/플레이 대기, 3: 플레이 중)
execute if score #game_state master matches 0 positioned -149.999 4 287.5 run function master:manager/init-round

# 상호작용 감지 및 처리
# interaction 태그를 가진 엔티티 중 상호작용이 감지된 경우 실행 (우클릭)
execute as @e[type=interaction,tag=master.interaction,nbt={interaction:{}}] at @s run function master:manager/interaction-trigger

# 공격 감지 및 처리 (좌클릭 - 필요 시 밴 기능 등으로 연결)
execute as @e[type=interaction,tag=master.interaction,nbt={attack:{}}] at @s run function master:manager/interaction-trigger