#모든 플레이어 중 가장 큰 A값을 가진 플레이어가 체크포인트 통과할 때, 순위 감지함수를 실행함.
#순위 감지 함수:
#가장 많은 랩을 돌았으며, A값이 가장 큰 플레이어 및 그와 동일한 값을 가진 플레이어를, B번 체크포인트로부터 가까운 순서대로 정렬(sort=nearest)해 순위를 매김.
#그 다음, 그들을 제외하고 가장 A값이 큰 플레이어...로 동일한 연산을 반복함.
#모든 라이더의 순위를 매겼다면 알고리즘을 조기 종료함.

execute if score #checkpoint-rank-decided multi-main matches 1 run return 0

tag @s add check-multi-temp

tag @s add check-1st-lap-player
tag @s add check-1st-num-player

#내가 가장 많은 랩을 돌았으며, "그 랩 중에" A값이 가장 큰 플레이어인지 감지 = 1등체크
execute as @a[tag=kart-multi-player,tag=!check-multi-temp] run function checkpoint:system/multi-sort/main-check

execute as @a[tag=check-same-lap-player,tag=!check-multi-temp] if score @s check-num > @a[tag=check-multi-temp,limit=1] check-num run tag @a[tag=check-multi-temp,limit=1] remove check-1st-num-player
tag @a[tag=check-same-lap-player] remove check-same-lap-player

#순위 감지 함수 실행
execute if entity @s[tag=check-1st-lap-player,tag=check-1st-num-player] run function checkpoint:system/multi-sort/sort-hub

tag @s remove check-1st-lap-player
tag @s remove check-1st-num-player

tag @s remove check-multi-temp

#텍스트
execute as @a[tag=kart-multi-player] at @s run function checkpoint:system/set-rank-text