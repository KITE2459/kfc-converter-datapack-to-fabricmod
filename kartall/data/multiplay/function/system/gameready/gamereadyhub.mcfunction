#게임중이라면 관전버튼으로 기능하기
execute if score #game multi-main matches 1.. run function multiplay:system/gameready/startspectate

#만약 멀티 플레이, 제작자 대결, 타임어택, 라이센스 중 하나라도 플레이중이라면 return
execute if function gamemain:cannot-start-condition run return 1

execute if entity @a[scores={trackselect-game-id=3}] run tellraw @a[distance=..50] [{translate:"누군가가 멀티 플레이 ","color":"red"},{translate:"트랙 선택 ","color":"aqua"},{translate:"중입니다.","color":"red"}]
execute if entity @a[scores={trackselect-game-id=3}] run return 1

#손이 비면 기본 카트
execute unless score #game multi-main matches 1.. as @a[tag=multi-hub-player] unless predicate kartmobil:ifride unless items entity @s weapon *[minecraft:custom_data~{kartmobil:1}] run item replace entity @s weapon from block -18 -1 146 container.0

#게임 준비중이 아니라면 게임 준비
tag @a[tag=multi-hub-ready,tag=multi-hub-player] remove multi-spectate-zone

execute unless score #game multi-main matches 1.. run function multiplay:system/gameready/gamestart