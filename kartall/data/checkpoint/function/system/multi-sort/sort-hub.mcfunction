#scoreboard objectives remove multi-instant-rank
#scoreboard objectives add multi-instant-rank dummy

#전체 순위체크가 10틱에 한번씩 도는데, 1등이 체크포인트를 패스했을 때 실행되는 경우 10틱 카운트를 리셋함
execute if score #from-check-pass-function check-prev matches 1 run scoreboard players set #15tick check-prev 0

scoreboard players reset player-count multi-instant-rank
scoreboard players reset ranked-count multi-instant-rank
scoreboard players reset max multi-instant-rank
scoreboard players reset @a multi-instant-rank

scoreboard players operation check-lap trackselect-lap = @s trackselect-lap
scoreboard players set early-stop check-num 0

execute store result score player-count multi-instant-rank if entity @a[tag=kart-multi-player]

function checkpoint:system/multi-sort/sort-lap

tag @a[tag=check-ranked] remove check-ranked

scoreboard players set #checkpoint-rank-decided multi-main 1