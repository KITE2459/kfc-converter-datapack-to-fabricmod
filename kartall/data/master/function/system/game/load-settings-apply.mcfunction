# Macro: Apply settings
# Input: {target_time: 120000, bossbar_name: '...', ...}

$scoreboard players set #target-time master $(target_time)

# 스테이지에 따른 타겟 시간 보정
# (중요) 플레이어가 load-settings-apply를 실행해야 @p masterstage 접근 가능
execute if entity @p run function master:system/game/adjust-target-time

# 보스바 설정 (adjust-target-time에서 bossbar name도 업데이트함)
# 보스바 Max 설정 (adjust-target-time 후에 해야 보정된 시간이 반영됨)
execute store result bossbar minecraft:master max run scoreboard players get #target-time master
# 보스바 Value 설정 (꽉 채우기)
execute store result bossbar minecraft:master value run scoreboard players get #target-time master



