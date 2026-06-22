# 적 데이터를 storage에 저장
execute if entity @s[tag=enemy.data] run data modify storage enemy temp set from entity @s data
execute unless entity @s[tag=enemy.data] on passengers if entity @s[tag=enemy.data] run data modify storage enemy temp set from entity @s data

# freeze 상태 계산
execute if entity @s[tag=enemy.data] if score @s enemy.state.freeze matches 1.. run function enemy:move/calculate_freeze
execute unless entity @s[tag=enemy.data] on passengers if entity @s[tag=enemy.data] if score @s enemy.state.freeze matches 1.. run function enemy:move/calculate_freeze

# stun 상태 계산
execute if entity @s[tag=enemy.data] if score @s enemy.state.stun matches 1.. run data modify storage enemy temp.speed set value 0
execute unless entity @s[tag=enemy.data] on passengers if entity @s[tag=enemy.data] if score @s enemy.state.stun matches 1.. run data modify storage enemy temp.speed set value 0

# 스킬 사용중 이동 불가
execute if entity @s[tag=enemy.data] if score @s enemy.skill-trigger.timer-cooldown matches ..0 run data modify storage enemy temp.speed set value 0
execute unless entity @s[tag=enemy.data] on passengers if entity @s[tag=enemy.data] if score @s enemy.skill-trigger.timer-cooldown matches ..0 run data modify storage enemy temp.speed set value 0

# 적 이동
function enemy:move/teleport with storage enemy temp