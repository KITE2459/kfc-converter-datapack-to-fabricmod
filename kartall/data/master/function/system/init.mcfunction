title @s times 0 20 20
scoreboard players reset time timermain

scoreboard players set @s trackselect-lap 0

# 상수 설정
function master:util/constants

# 체크포인트 시스템 초기화
function checkpoint:system/init