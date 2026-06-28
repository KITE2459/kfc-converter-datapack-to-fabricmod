# 우클릭일 때는 attacker 기준 처리
execute if data entity @s interaction if entity @s[tag=customspec-select-speed] on attacker run scoreboard players set @s customspec-active 1
execute if data entity @s interaction if entity @s[tag=customspec-select-accel] on attacker run scoreboard players set @s customspec-active 2
execute if data entity @s interaction if entity @s[tag=customspec-select-boost] on attacker run scoreboard players set @s customspec-active 3
execute if data entity @s interaction if entity @s[tag=customspec-select-corner] on attacker run scoreboard players set @s customspec-active 4
execute if data entity @s interaction if entity @s[tag=customspec-select-drift] on attacker run scoreboard players set @s customspec-active 5
execute if data entity @s interaction if entity @s[tag=customspec-select-gauge] on attacker run scoreboard players set @s customspec-active 6
execute if data entity @s interaction if entity @s[tag=customspec-select-boosttime] on attacker run scoreboard players set @s customspec-active 7
execute if data entity @s interaction if entity @s[tag=customspec-select-defense] on attacker run scoreboard players set @s customspec-active 8
execute if data entity @s interaction if entity @s[tag=customspec-select-draft] on attacker run scoreboard players set @s customspec-active 9

execute if data entity @s interaction if entity @s[tag=customspec-add-1] on attacker run function customspec:apply/plus1
execute if data entity @s interaction if entity @s[tag=customspec-add-10] on attacker run function customspec:apply/plus10
execute if data entity @s interaction if entity @s[tag=customspec-add-100] on attacker run function customspec:apply/plus100
execute if data entity @s interaction if entity @s[tag=customspec-sub-1] on attacker run function customspec:apply/minus1
execute if data entity @s interaction if entity @s[tag=customspec-sub-10] on attacker run function customspec:apply/minus10
execute if data entity @s interaction if entity @s[tag=customspec-sub-100] on attacker run function customspec:apply/minus100

execute if data entity @s interaction if entity @s[tag=customspec-confirm] on attacker run function customspec:confirm
execute if data entity @s interaction if entity @s[tag=customspec-close] on attacker run function customspec:close

# 좌클릭일 때는 target 기준 처리
execute if data entity @s attack if entity @s[tag=customspec-select-speed] on target run scoreboard players set @s customspec-active 1
execute if data entity @s attack if entity @s[tag=customspec-select-accel] on target run scoreboard players set @s customspec-active 2
execute if data entity @s attack if entity @s[tag=customspec-select-boost] on target run scoreboard players set @s customspec-active 3
execute if data entity @s attack if entity @s[tag=customspec-select-corner] on target run scoreboard players set @s customspec-active 4
execute if data entity @s attack if entity @s[tag=customspec-select-drift] on target run scoreboard players set @s customspec-active 5
execute if data entity @s attack if entity @s[tag=customspec-select-gauge] on target run scoreboard players set @s customspec-active 6
execute if data entity @s attack if entity @s[tag=customspec-select-boosttime] on target run scoreboard players set @s customspec-active 7
execute if data entity @s attack if entity @s[tag=customspec-select-defense] on target run scoreboard players set @s customspec-active 8
execute if data entity @s attack if entity @s[tag=customspec-select-draft] on target run scoreboard players set @s customspec-active 9

execute if data entity @s attack if entity @s[tag=customspec-add-1] on target run function customspec:apply/plus1
execute if data entity @s attack if entity @s[tag=customspec-add-10] on target run function customspec:apply/plus10
execute if data entity @s attack if entity @s[tag=customspec-add-100] on target run function customspec:apply/plus100
execute if data entity @s attack if entity @s[tag=customspec-sub-1] on target run function customspec:apply/minus1
execute if data entity @s attack if entity @s[tag=customspec-sub-10] on target run function customspec:apply/minus10
execute if data entity @s attack if entity @s[tag=customspec-sub-100] on target run function customspec:apply/minus100

execute if data entity @s attack if entity @s[tag=customspec-confirm] on target run function customspec:confirm
execute if data entity @s attack if entity @s[tag=customspec-close] on target run function customspec:close

# 중복 트리거 방지
data remove entity @s interaction
data remove entity @s attack
