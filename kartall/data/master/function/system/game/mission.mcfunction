# 5틱: 맵으로 이동 (한 번만 실행)
execute if score @s mastercount matches 5 run function master:movetomap

# 진행 시간 보스바 표시 (남은 시간 / 전체 시간)
scoreboard players operation #temp master = #target-time master
scoreboard players operation #temp master -= time timermain
execute store result bossbar minecraft:master value run scoreboard players get #temp master

# 시간 초과 체크 (target-time 보다 크거나 같으면 실패)
execute if score time timermain >= #target-time master run function master:system/retirefail

# 체크포인트 통과 (시작 후 일정 시간 이후부터 체크 - 3초(60틱) 정도?)
execute if score @s mastercount matches 61.. run function checkpoint:system/player-main

# 결승선 통과 (Map Type Mission)
# magma_block이 결승선 트리거
# check-pass-last 태그가 있어야 함 (모든 체크포인트 통과시 획득) -> 23년 datapack 수정사항: check-pass-last는 check-pass로 통합됨
# 하지만 여기서는 기존 구현을 참고함.
# 참고로 kartsaddle은 말이고, player가 타고 있다.
# execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers if entity @s[tag=check-pass-last] run function master:system/passline
# 위에서 봤던 기존 stage1/mission 코드를 그대로 가져온다.
execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers if entity @s[tag=check-pass-last] run function master:system/passline

# 모든 랩 완주 시 성공
execute if score @s trackselect-lap >= #max-lap trackselect-lap run function multiplay:system/game/complete-race/calculate-milisec
execute if score @s trackselect-lap >= #max-lap trackselect-lap if score #time timermain > #target-time master run function master:system/retirefail
execute if score @s trackselect-lap >= #max-lap trackselect-lap unless score #time timermain > #target-time master run function master:system/game/complete


# 카트 태그 제거 (필요한 경우)
tag @e[tag=mykart,type=#kartmobil:kartmobil] remove mykart
