bossbar set minecraft:master players

scoreboard players reset @s mastercount

# BGM 끄기
function bgm-room:manage-bgm/stopbgm
time set day
weather clear

# 로비로 텔레포트
tp @s -138.5 4 296.5 90 0
tag @s remove checkpointpass
tag @s remove check-pass-last

# 타이머 스탑
function timerpack:stop

tag @s remove master_racing

# 타이머 스탑
function timerpack:stop

# 관전 종료 처리
function master:manager/interaction/spectate-exit
# 관전 패널 초기화 예약
schedule function master:manager/interaction/reset-spectate-panel 1s replace

function checkpoint:system/init
