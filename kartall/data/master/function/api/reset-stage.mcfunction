function master:master-data/init-config

# 마스터 난이도 초기화
$scoreboard players set clear-data masterstage $(stage)
$scoreboard players set master-file masterstage $(stage)
$data modify storage master-cleardata:master-data cleared-stage set value $(stage)

# 게임 상태 초기화
scoreboard players set #game_state master 0

scoreboard players set @s master-reset 0