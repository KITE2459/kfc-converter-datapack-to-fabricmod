tag @s add check-temp-2

#직선 표시 조건 - 갈림길 맵에서의 원활한 지원
#특정 체크포인트를 기준으로, 숫자가 같은 체크포인트가 2개 이상 존재하지 않는 경우, 모든 다음 번호의 체크포인트를 향해 직선 표시
#숫자가 같은 체크포인트가 2개 이상 존재하는 경우, 가장 가까운 다음 번호의 체크포인트로 직선 표시
execute as @e[distance=..200,tag=check-point,tag=!check-temp-2,type=marker] if score @s check-num = @e[tag=check-temp-2,limit=1,type=marker] check-num run tag @e[tag=check-temp-2,limit=1,type=marker] add check-same-exist

scoreboard players add @s check-num 1
execute if entity @s[tag=!check-same-exist] as @e[distance=..200,tag=check-point,tag=!check-temp-2,type=marker] if score @s check-num = @e[tag=check-temp-2,limit=1,type=marker] check-num facing entity @s feet run function checkpoint:tool/raycast/ray
scoreboard players remove @s check-num 1

scoreboard players add @s check-num 1
execute if entity @s[tag=check-same-exist] as @e[distance=..200,tag=check-point,tag=!check-temp-2,type=marker] if score @s check-num = @e[tag=check-temp-2,limit=1,type=marker] check-num run tag @s add check-next-temp
execute if entity @s[tag=check-same-exist] as @n[tag=check-next-temp,type=marker] facing entity @s feet run function checkpoint:tool/raycast/ray
tag @e[tag=check-next-temp,type=marker] remove check-next-temp
scoreboard players remove @s check-num 1

tag @s remove check-same-exist
tag @s remove check-temp-2