# 경고 메시지
scoreboard players enable @s master-reset
tellraw @s [{translate:"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n지금까지의 모든 마스터 플레이 데이터가 ",color:white},{translate:"초기화",color:red,bold:true},{translate:"됩니다.",color:white,bold:false}]
tellraw @s [{translate:"[확인]",color:green,bold:true,click_event:{action:"run_command",command:"trigger master-reset set 1"}}]
scoreboard players set @s master-reset 0