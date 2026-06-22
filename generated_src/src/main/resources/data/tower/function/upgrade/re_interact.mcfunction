# 업그레이드 데이터 로드
execute as @n[tag=tower.data] run data modify storage tower temp set from entity @s data
execute as @n[tag=tower.upgrade] run data modify storage tower temp2 set from entity @s data.Tower_Status

# 태그 초기화
function tower:upgrade/tag

# 발광 효과
execute on vehicle on passengers run data modify entity @s glow_color_override set value 393215
execute on vehicle on passengers run data modify entity @s Glowing set value 1b

# 업그레이드 UI 호출
execute as @a if score @s player.id = #temp player.id run function tower:upgrade/ui

# 클릭 소리 재생
execute as @a if score @s player.id = #temp player.id run playsound minecraft:ui.button.click master @s

# 타워 사거리 표시
execute at @s run function tower:upgrade/summon_range with entity @s