# interaction entity가 클릭되었을 때 실행됨 (@s = interaction entity)
# 태그로 index 확인 (panel_0 ~ panel_6)

# [변경] 밴 확인 메시지 출력 단계로 변경
# 기존의 밴 로직을 제거하고, ban-confirm-msg 매크로 호출
# 인자: {idx: 0} 등을 전달해야 함

# idx 식별 및 매크로 호출
execute if entity @s[tag=panel_0] run function master:manager/interaction/ban-click-process {idx:0}
execute if entity @s[tag=panel_1] run function master:manager/interaction/ban-click-process {idx:1}
execute if entity @s[tag=panel_2] run function master:manager/interaction/ban-click-process {idx:2}
execute if entity @s[tag=panel_3] run function master:manager/interaction/ban-click-process {idx:3}
execute if entity @s[tag=panel_4] run function master:manager/interaction/ban-click-process {idx:4}
execute if entity @s[tag=panel_5] run function master:manager/interaction/ban-click-process {idx:5}
execute if entity @s[tag=panel_6] run function master:manager/interaction/ban-click-process {idx:6}

# 아래 기존 로직은 주석 처리 또는 제거
# ...


