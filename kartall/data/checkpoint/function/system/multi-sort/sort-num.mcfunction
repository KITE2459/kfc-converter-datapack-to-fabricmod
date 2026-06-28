#주체는 tag=check-same-lap

scoreboard players set #is-last-check check-next 0

#현재 순회중인 번호와 같은 번호를 next로 둔 라이더에게 태그 - 나도 포함
execute as @a[tag=check-same-lap] if score @s check-next = loop-num check-next run tag @s add check-same-num

#체크포인트로부터 정렬해서 순위매기기 - 분기 순위에 대한 버그 있을 수 있음 / 알빠노 ㅋㅋㅋㅋㅋ
execute store result storage checkpoint:checkpoint checknexttemp int 1 run scoreboard players get loop-num check-next
function checkpoint:system/multi-sort/sort-num-find-check with storage checkpoint:checkpoint

    #마지막 체크포인트인 경우, check-next-temp태그는 사실 이전 체크포인트가 가져감. 대신 nearest가 아닌 furthest기준으로 매김.
    execute unless entity @e[tag=check-next-temp,type=marker] run scoreboard players set #is-last-check check-next 1
    execute if score #is-last-check check-next matches 1 store result storage checkpoint:checkpoint checknexttemp int 1 run scoreboard players get loop-num check-prev
    execute if score #is-last-check check-next matches 1 run function checkpoint:system/multi-sort/sort-num-find-check with storage checkpoint:checkpoint

    #execute unless entity @e[tag=check-next-temp,type=marker] at @p[tag=check-same-num,tag=check-pass-last] run tag @n[tag=check-point,type=marker] add check-next-temp - old code

tag @a[tag=check-same-num] add check-ranked
execute if score #is-last-check check-next matches 0 at @n[tag=check-next-temp,type=marker] as @a[tag=check-same-num,sort=nearest] store result score @s multi-instant-rank run scoreboard players add max multi-instant-rank 1
execute if score #is-last-check check-next matches 1 at @n[tag=check-next-temp,type=marker] as @a[tag=check-same-num,sort=furthest] store result score @s multi-instant-rank run scoreboard players add max multi-instant-rank 1

#알고리즘 조기종료
execute store result score ranked-count multi-instant-rank if entity @a[tag=check-ranked]
execute if score player-count multi-instant-rank <= ranked-count multi-instant-rank run scoreboard players set early-stop check-num 1

tag @e[tag=check-next-temp,limit=10,type=marker] remove check-next-temp
tag @a[tag=check-same-num] remove check-same-num

scoreboard players remove loop-num check-next 1
scoreboard players remove loop-num check-prev 1

execute if score early-stop check-num matches 0 if score loop-num check-next matches 1.. run function checkpoint:system/multi-sort/sort-num