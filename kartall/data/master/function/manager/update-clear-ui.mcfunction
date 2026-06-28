# 매크로: 클리어 UI 업데이트
# 인자: {panel_idx: 0}

# 1. 텍스트 변경 (Clear!)
# panel_$(panel_idx) 태그를 가진 text_display 중, 기록을 표시하는 녀석
# 보통 3번째 녀석이 기록임. 하지만 태그로 구분하는 게 좋음. 기록 표시용 별도 태그가 없으므로...
# summon 할 때 y좌표로 구분됨. (2.0f:이름, 1.7f:난이도, 1.1f:기록)
# y=1.1f 위치의 엔티티를 찾아서 텍스트 변경
# 정교하게 찾기 위해 태그와 type 사용.

# 해당 패널의 모든 텍스트 디스플레이 찾기: tag=panel_$(panel_idx)
# 그 중 가장 아래에 있는 녀석(기록)을 찾으려면?
# y 좌표 정렬이 어려우므로, 그냥 전체 패널 위치로 이동 후, 상대 좌표로 특정.
# 하지만 텍스트 디스플레이는 위치가 고정되어 있음.
# 간단히: 해당 패널의 모든 텍스트를 "CLEAR!"로 바꾸거나, 기록 부분만 바꿈.
# 기록 부분만 바꾸려면 summon-panel-data-macro에서 기록 엔티티에 특정 태그(e.g., master.record)를 줬어야 함.
# 지금은 없으므로, y좌표가 가장 낮은 것을 대상으로 하거나,
# 3개의 텍스트 디스플레이 중 하나를 특정해야 함.
# 일단 panel_$(panel_idx) 태그를 가진 모든 text_display를 돌면서, 데이터 스토리지 참조를 끊고 텍스트를 직접 설정.

# panel_idx에 해당하는 위치를 찾아야 함.
# panel 0: ~ ~ ~
# panel 1: ~3 ~ ~
# panel 2: ~6 ~ ~
# init-round나 select-final-3를 어디서 실행했는지 기준 위치를 모르면 못 찾음.
# 그러나 text_display 엔티티 자체를 @e[tag=panel_$(panel_idx)]로 찾을 수 있음.

# 기록 텍스트 디스플레이 찾아서 변경
# (기록 표시용 텍스트는 맨 아래에 있음 -> y=1.1)
# execute as @e[type=text_display,tag=panel_$(panel_idx)] ...
# 3개가 잡힘. 그 중 하나만 골라야 함...
# 그냥 panel_$(panel_idx) 가진 놈들 싹 다 없애고 "CLEAR" 텍스트 하나만 띄우는 게 깔끔할 수 있음.

$kill @e[type=text_display,tag=panel_$(panel_idx)]
kill @e[type=text_display,tag=master.title]
$kill @e[type=interaction,tag=panel_$(panel_idx)]

# CLEAR 텍스트 생성 (위치는 어떻게?)
# 원래 위치를 알아야 함.
# 엔티티가 죽기 전에 위치를 저장해두거나,
# 미리 위치를 잡고 소환.

# 잠시 임시 엔티티(마커)를 소환하여 위치 잡기
# 하지만 이미 kill 해버렸음. 순서 변경.

# 수정된 순서:
# 1. interaction 위치(기준점) 찾기
# 2. 그 위치에 CLEAR 텍스트 소환
# 3. 기존 엔티티 제거

$execute as @e[type=interaction,tag=panel_$(panel_idx),limit=1] at @s run function master:manager/summon-clear-text
