scoreboard objectives add multi-main dummy
scoreboard objectives add multi-rank dummy
scoreboard objectives add multi-returntohub trigger
scoreboard objectives add multi-record-mod dummy {translate:"기록 모드",bold:true,color:green}
scoreboard objectives add temp dummy

scoreboard objectives add multi-instant-rank dummy
scoreboard objectives modify multi-instant-rank displayname "등"
#관전
scoreboard objectives add multi-shift minecraft.custom:minecraft.sneak_time
scoreboard objectives add multi-spectator-id dummy
scoreboard objectives add multi-spectate-time dummy
scoreboard objectives add multi-spectate-change dummy
#멀티플레이 관전기능
scoreboard objectives add multi-spectate-quit dummy

#랩타임 계산용
scoreboard objectives add multi-last-pass-time dummy
scoreboard objectives add multi-best-time dummy

scoreboard objectives add kart-milisec-calc dummy

#xla
team add multi-hidenick "닉네임 숨기기"
team modify multi-hidenick seeFriendlyInvisibles false
team modify multi-hidenick nametagVisibility never
team modify multi-hidenick collisionRule never
team modify multi-hidenick friendlyFire false

bossbar remove multi-spectator-ui
bossbar add multi-spectator-ui [{translate:"나가기[","color":"yellow"},{"keybind":"key.sneak","color":"aqua"},{translate:"]","color":"yellow"},{translate:"  |  ","color":"yellow"},{translate:"관전 대상 변경[","color":"yellow"},{"keybind":"key.forward","color":"aqua"},{translate:" / ","color":"aqua"},{"keybind":"key.back","color":"aqua"},{translate:"]","color":"yellow"}]

team add redteam "레드팀"
team modify redteam color red
team modify redteam seeFriendlyInvisibles false
team modify redteam nametagVisibility never
team modify redteam friendlyFire false
team modify redteam collisionRule never

team add blueteam "블루팀"
team modify blueteam color blue
team modify blueteam seeFriendlyInvisibles false
team modify blueteam nametagVisibility never
team modify blueteam friendlyFire false
team modify blueteam collisionRule never