#trackselect-game-id 4일때 업다운, 크로스오버 둘만 표시
#trackselect-game-id 3일때 모두 표시
#3..4가 아니면 업다운, 크로스오버 숨기기

execute as @p[scores={trackselect-game-id=1..}] if score @s trackselect-game-id matches 4 unless data storage track-list temp[0].updown run return 0
execute as @p[scores={trackselect-game-id=1..}] unless score @s trackselect-game-id matches 3..4 if data storage track-list temp[0].updown run return 0

#랜덤 테마는 멀티플레이에서만 표시
execute as @p[scores={trackselect-game-id=1..}] unless score @s trackselect-game-id matches 3 if data storage track-list temp[0].random run return 0

return 1