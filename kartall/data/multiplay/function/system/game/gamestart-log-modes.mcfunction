# 게임 시작 로그 출력 - 활성화된 모드 목록
# 모드 목록을 배열로 구성하여 출력

# 모드 카운터 초기화
scoreboard players set #mode-count multi-main 0

# 각 모드 체크 및 배열에 추가
data modify storage adai_gamestart modes set value []

execute if score inf-boost multi-main matches 1 run data modify storage adai_gamestart modes append value "무한 부스터 모드"
execute if score inf-boost multi-main matches 1 run scoreboard players add #mode-count multi-main 1

execute if score no-collision multi-main matches 1 run data modify storage adai_gamestart modes append value "고스트 모드"
execute if score no-collision multi-main matches 1 run scoreboard players add #mode-count multi-main 1

execute if score no-draft multi-main matches 1 run data modify storage adai_gamestart modes append value "드래프트 끄기"
execute if score no-draft multi-main matches 1 run scoreboard players add #mode-count multi-main 1

execute if score no-pull-accel multi-main matches 1 run data modify storage adai_gamestart modes append value "견인 가속 끄기"
execute if score no-pull-accel multi-main matches 1 run scoreboard players add #mode-count multi-main 1

execute if score drag-accel multi-main matches 1 run data modify storage adai_gamestart modes append value "톡톡이 모드"
execute if score drag-accel multi-main matches 1 run scoreboard players add #mode-count multi-main 1

execute if score mad-crash multi-main matches 1 run data modify storage adai_gamestart modes append value "갓겜 모드"
execute if score mad-crash multi-main matches 1 run scoreboard players add #mode-count multi-main 1

execute if score wall-crash multi-main matches 1 run data modify storage adai_gamestart modes append value "벽 충돌 페널티"
execute if score wall-crash multi-main matches 1 run scoreboard players add #mode-count multi-main 1

execute if score team-battle multi-main matches 1 run data modify storage adai_gamestart modes append value "팀전"
execute if score team-battle multi-main matches 1 run scoreboard players add #mode-count multi-main 1

# 모드가 없으면 "모드 없음" 출력, 있으면 모드 목록 출력
execute if score #mode-count multi-main matches 0 run logtellraw targets @a[tag=logtellraw] [{translate:"모드: 모드 없음","color":"yellow"}]
execute if score #mode-count multi-main matches 1.. run function multiplay:system/game/gamestart-log-modes-list
