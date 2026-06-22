# 방어력에 따른 데미지 계산
execute if score @s enemy.defence matches 1.. run function enemy:hit/calculate_defence_plus
execute if score @s enemy.defence matches ..-1 run function enemy:hit/calculate_defence_minus