# 모델 소환
$function enemy:spawn/model/$(model)

# 데이터 할당
execute as @e[tag=enemy.data] unless entity @s[scores={enemy.number=0..}] run function enemy:spawn/summon/allocate