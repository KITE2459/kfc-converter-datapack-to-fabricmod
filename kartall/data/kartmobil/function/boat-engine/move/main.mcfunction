
#미끄럼 수치 설정
data modify entity @s data.slip set value 0.98

#푸른얼음
data modify entity @s data.blueslip set value 0.989

function kartmobil:boat-engine/move/angle-resist with entity @s data
function kartmobil:boat-engine/move/move-resist with entity @s data

#회전
execute store result storage boatpack anglespeedreal float 0.00001 run scoreboard players get @s boat-angle-speed
function kartmobil:boat-engine/move/rotate with storage boatpack

#이동
function kartmobil:boat-engine/move/move-axis/main

function kartmobil:boat-engine/move/model-dir