# 실제 밴 처리 (매크로 혹은 인자 기반)
# 인자: {idx: 0}

# 이 함수는 Chat ClickEvent로 실행되므로 @s는 플레이어임. interaction entity가 아님.
# 따라서 interaction entity를 찾아 제거하는 로직이 까다로움. tag=panel_$(idx) 사용.

# 1. 시각적 제거 (panel_$(idx) 태그를 가진 모든 엔티티 제거)
$kill @e[tag=panel_$(idx)]

# 2. 밴 카운트 증가
scoreboard players add #banned_count master 1

# 3. 데이터 업데이트 (candidates[idx] = -1)
$data modify storage master:game candidates[$(idx)] set value -1

# 4. 다음 단계 체크
execute if score #banned_count master matches 2 run function master:manager/select-final-3
