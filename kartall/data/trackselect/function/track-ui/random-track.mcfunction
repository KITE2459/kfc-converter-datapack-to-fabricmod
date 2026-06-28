execute unless entity @a[scores={trackselect-game-id=1..}] run say 랜덤 트랙 함수를 호출하려면 적어도 한 명의 플레이어가 트랙 선택 중이여야 합니다.

#일단 테마 UI 띄우기
execute as @a[scores={trackselect-game-id=1..}] as @e[tag=track-main] at @s run function trackselect:track-ui/show-theme-ui

#랜덤 테마 고르기
execute as @a[scores={trackselect-game-id=1..}] at @e[tag=track-theme-interaction,type=interaction,limit=1,sort=random] run function trackselect:track-ui/show-track-ui

#랜덤 트랙 고르기
execute as @a[scores={trackselect-game-id=1..}] at @e[tag=track-track-interaction,type=interaction,limit=1,sort=random] run function trackselect:track-ui/select-track/main
