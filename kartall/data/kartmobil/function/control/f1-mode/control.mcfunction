function kartmobil:control/boost-gauge/f1-main

#전진 키 이벤트(불빛 깜빡임을 위해)
tag @s remove kart-w-press
tag @s remove kart-w-release
execute if entity @s[tag=kart-w-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:w] run tag @s add kart-w-release
execute if entity @s[tag=kart-w-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:w] run tag @s remove kart-w-pressed
execute if entity @s[tag=!kart-w-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:w] run tag @s add kart-w-press
execute if entity @s[tag=!kart-w-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:w] run tag @s add kart-w-pressed

#부스터 키 이벤트
tag @s remove kart-gear-press
execute if entity @s[tag=kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:a,predicate=!kartmobil:d] run tag @s remove kart-gear-pressed
execute if entity @s[tag=!kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run tag @s add kart-gear-press
execute if entity @s[tag=!kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run tag @s add kart-gear-press
execute if entity @s[tag=!kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run tag @s add kart-gear-pressed
execute if entity @s[tag=!kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run tag @s add kart-gear-pressed

#스페이스 키(그립주행 게이지 모드 변경) 이벤트
tag @s remove kart-space-press
tag @s remove kart-space-release

execute if entity @s[tag=kart-space-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:space] run tag @s add kart-space-release
execute if entity @s[tag=kart-space-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:space] run tag @s remove kart-space-pressed
execute if entity @s[tag=!kart-space-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:space] run tag @s add kart-space-press
execute if entity @s[tag=!kart-space-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:space] run tag @s add kart-space-pressed

#페달조작
execute if entity @p[tag=kartpassenger,predicate=kartmobil:w] run function kartmobil:control/f1-mode/accel
execute if entity @p[tag=kartpassenger,predicate=kartmobil:s] run function kartmobil:control/brake

# ers 발동 및 충전
scoreboard players set @s kart-ers 0
execute if entity @p[tag=kartpassenger,predicate=kartmobil:space,predicate=!kartmobil:s] if score @s kart-ers-gauge matches 10.. store result score @s kart-ers run function kartmobil:control/f1-mode/ers

# 감속 기반 ERS 충전
# 공식: 충전량(게이지) = 감속(km/h) * 현재속도(km/h) / 15
# 예시: 150km/h에서 1 감속 -> 10(1%), 300km/h에서 1 감속 -> 20(2%)
scoreboard players operation #ers-decel kart-ers-gauge = @s kart-ers-prev-speed
scoreboard players operation #ers-decel kart-ers-gauge -= #kartspeed kartmove
execute if score #ers-decel kart-ers-gauge matches 1.. run function kartmobil:control/f1-mode/ers-charge-by-decel
execute if score @s kart-ers-gauge matches 1000.. run scoreboard players set @s kart-ers-gauge 1000
scoreboard players operation @s kart-ers-prev-speed = #kartspeed kartmove

#기어 단수별 드탈값 (고정 탈출력 모드에서는 비활성화)

#속도별 선형화
#scoreboard players set #temp959 kartdrift 959
#scoreboard players operation #temp-drift-reduce kartdrift = #kartspeed kartmove
#scoreboard players operation #temp-drift-reduce kartdrift *= #temp959 kartdrift
#scoreboard players operation #temp-drift-reduce kartdrift /= #kart100 kartmain
#scoreboard players add #temp-drift-reduce kartdrift 193
#scoreboard players operation @p[tag=kartpassenger] kartdrift -= #temp-drift-reduce kartdrift

# 디버그 플레이어
execute if entity @p[tag=f1-test] run function kartmobil:control/f1-mode/accel
tellraw @p[tag=f1-test] {text:"kartmove: ",color:"gold",extra:[{score:{name:"@s",objective:"kartmove"}},{text:" "},{text:"kartboostcount: ",color:"gold"},{score:{name:"@s",objective:"kartboostcount"}}]}

#기어조작
execute unless score @s[tag=kart-gear-press] kartboostcount matches 8.. if entity @p[tag=kartpassenger,predicate=kartmobil:d] run function kartmobil:control/f1-mode/gear-up
execute unless score @s[tag=kart-gear-press] kartboostcount matches ..1 if entity @p[tag=kartpassenger,predicate=kartmobil:a] run function kartmobil:control/f1-mode/gear-down

execute if entity @s[tag=kart-gear-press] at @s as @e[limit=2] run playsound minecraft:block.nether_bricks.break weather @a[tag=kart-listener] ~ ~ ~ 1.5 0