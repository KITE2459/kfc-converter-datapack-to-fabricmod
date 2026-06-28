# 마스터 시스템 설정 초기화 (스코어보드, 보스바 등)
function master:startset

# 다른 게임 모드가 실행 중이면 시작 불가
execute if function gamemain:cannot-start-condition run return 1

# 마스터 맵 설정 (mastermap 스코어 이용)
# scoreboard objectives add mastermap dummy -> startset으로 이동됨
# scoreboard objectives add masterrecord dummy -> startset으로 이동됨
# mastermap이 설정되지 않았으면 기본값 1 (혹은 로직에 따라 처리)
execute unless score @p mastermap matches 1.. run scoreboard players set @p mastermap 1
# 선택된 맵을 timeattack 맵 변수에 저장
scoreboard players operation #map trackselect-map-id = @p mastermap
# 맵 기록 로드
function master:master-data/load-record
# 맵 목표 시간 및 보스바 설정
function master:system/game/load-settings

# 카트 탑승 확인 (손에 카트 아이템이 있는지)
execute unless items entity @p weapon *[minecraft:custom_data~{kartmobil:1}] run tellraw @a[distance=..50] [{translate:"손에 ","color":"red"},{translate:"탑승할 카트","color":"aqua"},{translate:"를 들어주세요.","color":"red"}]
execute unless items entity @p weapon *[minecraft:custom_data~{kartmobil:1}] run return 1

# 포테이토
execute if score lowdetail kartmain matches 1 run tellraw @a[distance=..50] [{translate:"이 모드에서는 ","color":"red"},{translate:"포테이토 모드","color":"aqua"},{translate:"를 사용할 수 없습니다.\n차고에서 적용을 해제해주세요.","color":"red"}]
execute if score lowdetail kartmain matches 1 run return 1

effect give @p minecraft:unluck 1 1 true

# 게임 아이디 변경 (기존 시스템 포멧 따름)
scoreboard players add #max-id game-id 1
scoreboard players operation @p game-id = #max-id game-id

# 게임 시작 설정
# $scoreboard players set @p masterstage $(masterstage) 
# 위 부분은 매크로로 호출될 것을 가정함. 직접 호출시에는 호출 전에 tag나 score를 세팅해야 함.
# 여기서는 호출자가 매크로등으로 masterstage를 세팅해주지 않는다면 기본 1로 가정하거나, 
# 외부에서 execute store result score @s masterstage run ... 로 세팅하고 들어온다고 가정.
# 하지만 readytoplay는 보통 유저가 버튼을 눌러서 실행하므로, 버튼에 매크로가 달려있을 것임.
# 아래 라인은 readytoplay를 매크로로 실행할 때 $(masterstage)를 받아서 처리하는 방식임.
# 만약 매크로가 아니라면, 호출 전에 플레이어에게 masterstage 점수가 있어야 함.
execute unless score clear-data masterstage matches 0.. run scoreboard players set clear-data masterstage 0

scoreboard players set @p mastercount 1

# 맵 설정 (Type B Only)
# 스테이지별 맵 ID 설정 (임의의 맵 ID 사용)
# execute if score @p masterstage matches 1 run scoreboard players set #map trackselect-map-id 101
# execute if score @p masterstage matches 2 run scoreboard players set #map trackselect-map-id 102
# execute if score @p masterstage matches 3 run scoreboard players set #map trackselect-map-id 103

# 맵 데이터가 -1이면 오류 처리
execute if score #map trackselect-map-id matches -1 run tellraw @p {text:"Map ID not set for this stage","color":"red"}
execute if score #map trackselect-map-id matches -1 run scoreboard players set @p mastercount 0
execute if score #map trackselect-map-id matches -1 run return 1

# 맵 데이터 마커에 BGM 설정 (마스터 전용 BGM이 있다면 변경)
data modify entity @n[tag=gamemain-mapdata-marker] data.track.bgm set value "master_license"

title @a title {text:"로딩중...", "color":"green"}