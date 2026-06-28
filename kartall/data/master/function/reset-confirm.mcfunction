# 경고 메시지
scoreboard players enable @s master-reset
tellraw @s [{translate:"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n되돌릴 수 없습니다. ",color:red,bold:true},{translate:"정말 초기화하시겠습니까?",color:white,bold:false}]
tellraw @s [{translate:"[확인]",color:green,bold:true,click_event:{action:"run_command",command:"trigger master-reset set 2"}}]
scoreboard players set @s master-reset 0