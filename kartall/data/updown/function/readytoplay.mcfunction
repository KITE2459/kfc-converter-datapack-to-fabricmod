#만약 멀티 플레이, 제작자 대결, 타임어택, 라이센스 중 하나라도 플레이중이라면 return
execute if function gamemain:cannot-start-condition run return 1

execute unless items entity @p weapon *[minecraft:custom_data~{kartmobil:1}] run tellraw @a[distance=..50] [{translate:"손에 ","color":"red"},{translate:"탑승할 카트","color":"aqua"},{translate:"를 들어주세요.","color":"red"}]
execute unless items entity @p weapon *[minecraft:custom_data~{kartmobil:1}] run return 1

execute as @n[tag=trackselect-updown-marker,type=marker] at @s run function timerpack:show-track-on-sidebar/main
#data modify entity @e[tag=timeattack-state-text,limit=1] text set value [{translate:"타임어택 진행 중\n","color":"green"},{translate:"관전하기","color":"aqua"}]

effect give @p minecraft:unluck 1 1 true

#게임 아이디 변경
scoreboard players add #max-id game-id 1
scoreboard players operation @p game-id = #max-id game-id

#게임 시작
execute store result score #map trackselect-map-id run data get entity @n[tag=trackselect-updown-marker] data.track.number
scoreboard players set @p updown-count 1

#안전빵으로 초기화 한번
function checkpoint:system/init