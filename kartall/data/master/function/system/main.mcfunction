# init (첫 틱 실행)
execute if score @s mastercount matches 1 run function master:system/init

scoreboard players add @s mastercount 1

# 20틱(1초) 이상 지났고 카트에서 내리면 리타이어
execute if score @s mastercount matches 20.. if entity @s[predicate=!kartmobil:ifride] run function master:system/retire

# 물이나 공허(structure_void)에 닿으면 R키(복귀)
execute on vehicle on vehicle at @s if block ~ ~-0.5 ~ structure_void on passengers as @s[tag=kartsaddle] on passengers run function gamemain:rkey

# 카운트다운 (100틱 = 5초)
execute if score @s mastercount matches ..100 run function master:system/countdown
# (마스터 전용 카운트다운 시스템 사용)

# 스테이지별 미션 틱 실행 (mastermap에 따라 설정된 게임 로직 실행)
function master:system/game/mission
