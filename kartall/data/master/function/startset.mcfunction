# 마스터 시스템 초기화 (스코어보드, 보스바 등)
# 게임 로드 시 또는 필요 시 호출

# 스코어보드
scoreboard objectives add mastercount dummy
scoreboard objectives add masterstage dummy
scoreboard objectives add master-try dummy
scoreboard objectives add trackselect-map-id dummy
scoreboard objectives add master-file dummy
scoreboard objectives add masterPanelIdx dummy
scoreboard objectives add mastermap dummy
scoreboard objectives add masterrecord dummy
scoreboard objectives add masterBan dummy
scoreboard objectives add master dummy

scoreboard objectives add master-reset trigger
scoreboard players enable @a master-reset

# 보스바
bossbar add master "Master License"

# 클리어 데이터 초기화
execute unless score clear-data masterstage = clear-data masterstage run scoreboard players set clear-data masterstage 0

# 관전 UI 보스바
bossbar add master-spectator-ui [{translate:"나가기[","color":"yellow"},{"keybind":"key.sneak","color":"aqua"},{translate:"]","color":"yellow"},{translate:"  |  ","color":"yellow"},{translate:"마스터 관전 중","color":"red"}]