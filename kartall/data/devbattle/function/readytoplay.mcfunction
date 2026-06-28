execute as @p if entity @s[scores={devrecorder=1}] unless score #dev dev-count matches 10 run tellraw @s "'다오'의 기록을 제외한 대결은 할 수 없습니다."
execute as @p if entity @s[scores={devrecorder=1}] unless score #dev dev-count matches 10 run return 1


#만약 멀티 플레이, 제작자 대결, 타임어택, 라이센스 중 하나라도 플레이중이라면 return
execute if function gamemain:cannot-start-condition run return 1

execute unless items entity @p weapon *[minecraft:custom_data~{kartmobil:1}] run tellraw @a[distance=..50] [{translate:"손에 ","color":"red"},{translate:"탑승할 카트","color":"aqua"},{translate:"를 들어주세요.","color":"red"}]
execute if score @n[tag=trackselect-devbattle-record] timermain matches 2147483647 unless score #dev-record-mode dev-count matches 1 run tellraw @a[distance=..50] [{translate:"제작자가 ","color":"red"},{translate:"주행 데이터","color":"aqua"},{translate:"를 기록하지 않은 트랙입니다.","color":"red"}]

execute unless items entity @p weapon *[minecraft:custom_data~{kartmobil:1}] run return 1
execute if score @n[tag=trackselect-devbattle-record] timermain matches 2147483647 unless score #dev-record-mode dev-count matches 1 run return 1

execute if score lowdetail kartmain matches 1 run tellraw @a[distance=..50] [{translate:"이 모드에서는 ","color":"red"},{translate:"포테이토 모드","color":"aqua"},{translate:"를 사용할 수 없습니다.\n차고에서 적용을 해제해주세요.","color":"red"}]
execute if score lowdetail kartmain matches 1 run return 1

execute as @n[tag=trackselect-devbattle-marker,type=marker] at @s run function timerpack:show-track-on-sidebar/main
data modify entity @e[tag=dev-state-text,limit=1] text set value [{translate:"대결 진행 중\n","color":"green"},{translate:"관전하기","color":"aqua"}]

effect give @p minecraft:unluck 1 1 true


#게임 아이디 변경
scoreboard players add #max-id game-id 1
scoreboard players operation @p game-id = #max-id game-id

#게임 시작
execute store result score @p trackselect-map-id run data get entity @n[tag=trackselect-devbattle-marker] data.track.number
scoreboard players operation #map trackselect-map-id = @p trackselect-map-id
scoreboard players set @p dev-count 1

#안전빵으로 초기화 한번
function checkpoint:system/init