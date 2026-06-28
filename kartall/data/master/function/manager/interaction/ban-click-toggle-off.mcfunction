# 밴 토글 OFF (기록 복구)
# 인자: {idx: 0}

tag @s remove banned

# 텍스트 디스플레이 변경 (panel_banned_$(idx) 태그 사용) - 텍스트 제거
$data modify entity @e[type=text_display,tag=panel_banned_$(idx),limit=1] text set value {text:" "}

# (선택) 소리 재생
playsound minecraft:ui.button.click master @a ~ ~ ~ 1 0.8

return 1