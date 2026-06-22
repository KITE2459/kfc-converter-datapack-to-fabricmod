# stage gameState 값
# 0: 게임 준비
# 1: 라운드 준비
# 2: 라운드 진행
# 3: 다음 라운드 대기
# 게임 준비
execute unless score stage gameState matches 1.. run function game:game/ready with storage game setup

# 라운드 준비
execute if score stage gameState matches 1 run function game:game/round_ready

# 라운드 진행
execute store result storage game setup.time int 1.0 run scoreboard players get timer gameState
execute store result score #temp wave run scoreboard players get game wave
scoreboard players remove #temp wave 1
execute store result storage game setup.round int 1.0 run scoreboard players get #temp wave
execute if score stage gameState matches 2 run function game:game/round_progress with storage game setup

# 패배 검사
execute if score game game.base_health matches ..0 run function game:game/defeat

# 다음 라운드 대기
execute if score stage gameState matches 3 run function game:game/round_wait with storage game setup

# 사이드바 통계 갱신
function game:game/show_stats with storage game setup

# 카운트 증가
scoreboard players add timer gameState 1