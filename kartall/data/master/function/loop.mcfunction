# 마스터 데이터 세이브 / 로드
execute store result score master-file masterstage run data get storage master-cleardata:master-data cleared-stage 1.0
execute if entity @a run function master:check-data

# 보스바 설정 (마스터 전용)
bossbar set minecraft:master players @a[scores={mastercount=1..}]

# 마스터 모드 플레이 중인 사람 틱 실행
execute as @a at @s if score @s mastercount matches 1.. run function master:system/main

# 마스터 리셋 감지
execute as @a[scores={master-reset=1}] run function master:reset-confirm
execute as @a[scores={master-reset=2}] run function master:api/reset-stage {stage:0}

function master:manager/tick

# 관전
bossbar set master-spectator-ui players @a[tag=master-spectator]
execute if entity @a[tag=master-spectator] run function master:system/spectator/spectatormain