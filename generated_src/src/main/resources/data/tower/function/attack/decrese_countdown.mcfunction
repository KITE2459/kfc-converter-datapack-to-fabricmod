# 공격 카운트다운 감소
scoreboard players remove @s tower.attack_countdown 1
scoreboard players operation #temp tower.attack_countdown = @s tower.attack_countdown
execute on vehicle on passengers run scoreboard players operation @s tower.animation = #temp tower.attack_countdown