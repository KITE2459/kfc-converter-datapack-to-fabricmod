# 밴 토글 ON (BANNED으로 변경)
# 인자: {idx: 0}

tag @s add banned

# 텍스트 디스플레이 변경 (panel_banned_$(idx) 태그 사용)
$data modify entity @e[type=text_display,tag=panel_banned_$(idx),limit=1] text set value {"text":"BANNED","color":"red","bold":true}

# (선택) 소리 재생
playsound minecraft:ui.button.click master @a ~ ~ ~ 1 1.2
