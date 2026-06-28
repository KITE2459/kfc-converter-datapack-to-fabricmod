# 오프핸드 카트 아이템 생성에 필요한 값 저장
execute store result storage customspec:ui speed int 1 run scoreboard players get @s customspec-speed
execute store result storage customspec:ui accel int 1 run scoreboard players get @s customspec-accel
execute store result storage customspec:ui boost int 1 run scoreboard players get @s customspec-boost
execute store result storage customspec:ui corner int 1 run scoreboard players get @s customspec-corner
execute store result storage customspec:ui drift int 1 run scoreboard players get @s customspec-drift
execute store result storage customspec:ui gauge int 1 run scoreboard players get @s customspec-gauge
execute store result storage customspec:ui boosttime int 1 run scoreboard players get @s customspec-boosttime
execute store result storage customspec:ui defense int 1 run scoreboard players get @s customspec-defense
execute store result storage customspec:ui draft int 1 run scoreboard players get @s customspec-draft

# 커스텀 카트 지급(오프핸드) + 즉시 소환
function customspec:interaction/give-offhand with storage customspec:ui
function kartmain:summon/summonkart

# UI 정리
tag @s remove customspec-open
kill @e[tag=customspec-ui,distance=..10]
