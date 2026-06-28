scoreboard players set loop-num check-next 0
scoreboard players set loop-num check-prev 0

#sort-num에서, 해당 랩을 도는 사람 중 숫자가 가장 큰 사람의 next체크포인트 숫자부터 1까지 재귀 돌면서 검사할 거임
#현재 재귀에서 돌고 있는 랩을 도는 플레이어들에게 태그 - 나 포함 

execute as @a[tag=kart-multi-player] if score @s trackselect-lap = check-lap trackselect-lap run tag @s add check-same-lap
execute as @a[tag=check-same-lap] run scoreboard players operation loop-num check-next > @s check-next
execute as @a[tag=check-same-lap] run scoreboard players operation loop-num check-prev > @s check-prev

function checkpoint:system/multi-sort/sort-num

tag @a[tag=check-same-lap] remove check-same-lap

#다음 랩으로 넘어가기 전의 처리
scoreboard players remove check-lap trackselect-lap 1

#0랩까지 있음
execute if score early-stop check-num matches 0 if score check-lap trackselect-lap matches 0.. run function checkpoint:system/multi-sort/sort-lap