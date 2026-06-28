# 플레이어별 고유 ID 발급
execute as @a[scores={customspec-id=0}] run scoreboard players add #customspec-next customspec-id 1
execute as @a[scores={customspec-id=0}] run scoreboard players operation @s customspec-id = #customspec-next customspec-id

# UI 열림 상태 플레이어 렌더
execute as @a[tag=customspec-open] at @s run function customspec:render

# 인터랙션 처리 (우클릭)
execute as @e[type=interaction,tag=customspec-interaction,nbt={interaction:{}}] at @s run function customspec:interaction

# 인터랙션 처리 (좌클릭)
execute as @e[type=interaction,tag=customspec-interaction,nbt={attack:{}}] at @s run function customspec:interaction
