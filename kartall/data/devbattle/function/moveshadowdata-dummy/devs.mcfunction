#만약 멀티 플레이, 제작자 대결, 타임어택 중 하나라도 플레이중이라면 return
execute if entity @a[tag=kart-multi-player] run tellraw @a[distance=..50] [{translate:"멀티 플레이 ","color":"aqua"},{translate:"경기가 진행 중입니다.","color":"red"}]
execute if entity @a[scores={dev-count=1..}] run tellraw @a[distance=..50] [{translate:"누군가가 ","color":"red"},{translate:"제작자와의 대결","color":"aqua"},{translate:"을 플레이 중입니다.","color":"red"}]
execute if entity @a[scores={timecount=1..}] run tellraw @a[distance=..50] [{translate:"누군가가 ","color":"red"},{translate:"타임어택","color":"aqua"},{translate:"을 플레이 중입니다.","color":"red"}]
#execute if entity @a[scores={licensecount=1..}] run tellraw @a[distance=..50] [{translate:"누군가가 ","color":"red"},{translate:"라이센스","color":"aqua"},{translate:"를 플레이 중입니다.","color":"red"}]

execute if entity @a[tag=kart-multi-player] run return 1
execute if entity @a[scores={dev-count=1..}] run return 1
execute if entity @a[scores={timecount=1..}] run return 1
#execute if entity @a[scores={licensecount=1..}] run return 1

#게임 시작
$scoreboard players set @p licensestage $(licensestage)
scoreboard players set @p licensecount 1