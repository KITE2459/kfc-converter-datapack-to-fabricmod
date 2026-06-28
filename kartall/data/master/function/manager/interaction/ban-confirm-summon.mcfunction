# 확정 버튼 소환
# 이미 존재하는지 확인
execute unless entity @e[type=interaction,tag=ban_confirm_button] run summon interaction -146.5 5 296.5 {Tags:["ban_confirm_button","master.interaction"],width:1.5f,height:1.5f,Rotation:[-90F,0F]}
execute unless entity @e[type=text_display,tag=ban_confirm_text] run summon text_display -146.5 5.5 296.5 {Tags:["ban_confirm_text"],text:{"text":"[ 확정 ]","color":"green","bold":true},Rotation:[-90F,0F],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2f,2f,2f]},billboard:"center"}
