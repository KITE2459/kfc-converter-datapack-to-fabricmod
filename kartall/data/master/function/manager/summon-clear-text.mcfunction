# CLEAR 텍스트 소환 및 기존 제거
# 실행 위치: interaction 엔티티 위치

# 기존 제거
kill @e[type=text_display,distance=..0.5,tag=master.panel]
kill @s

# CLEAR 텍스트 소환
summon text_display ~ ~1.5 ~ {Tags:["master.text","master.panel","master.clear"],billboard:"center",text:'{text:"CLEAR!","color":"green","bold":true,"underlined":true}',background:16711680,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2f,2f,2f]}}
