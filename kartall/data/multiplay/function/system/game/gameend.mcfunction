execute as @a[tag=kart-multi-player] at @s run function multiplay:system/returntohub

#순위표출력
execute if entity @a[scores={multi-rank=-2147483648..}] run tellraw @a [{translate:"--------------------","color":"yellow"}]

scoreboard players set #redteam-score multi-rank 0
scoreboard players set #blueteam-score multi-rank 0
scoreboard players reset #rank-print-temp multi-rank
execute if entity @a[scores={multi-rank=1..}] run function multiplay:system/game/printrank

#무승부면 1등팀이 승리
execute if score team-battle multi-main matches 1 if score #redteam-score multi-rank = #blueteam-score multi-rank if score @n[scores={multi-rank=1}] kart-team matches 0 run scoreboard players add #redteam-score multi-rank 1
execute if score team-battle multi-main matches 1 if score #redteam-score multi-rank = #blueteam-score multi-rank if score @n[scores={multi-rank=1}] kart-team matches 1 run scoreboard players add #blueteam-score multi-rank 1

execute if score team-battle multi-main matches 1 if score #redteam-score multi-rank > #blueteam-score multi-rank run tellraw @a [{translate:"\n레드팀 승리!","color":"aqua"}]
execute if score team-battle multi-main matches 1 if score #redteam-score multi-rank < #blueteam-score multi-rank run tellraw @a [{translate:"\n블루팀 승리!","color":"aqua"}]


#리타이어 순위표 출력
execute as @a[scores={multi-rank=-1}] unless score @s game-id = #max-id game-id run scoreboard players reset @s multi-rank

execute if entity @a[scores={multi-rank=-1}] if entity @a[scores={multi-rank=1..}] run tellraw @a ""
execute if entity @a[scores={multi-rank=-1}] run tellraw @a [{translate:"완주 실패:","color":"red"}]
execute if entity @a[scores={multi-rank=-1}] run tellraw @a [{"selector":"@a[scores={multi-rank=-1}]","color":"light_purple"}]
execute if entity @a[scores={multi-rank=-2147483648..}] run tellraw @a [{translate:"--------------------","color":"yellow"}]

#순위 정보 리셋 전에 방장 지급
tag @a remove bangjang
tag @a[scores={multi-rank=1},limit=1] add bangjang
tag @a[scores={multi-rank=1},limit=1] add multi-hub-player
scoreboard players set #alert multi-main 1

scoreboard players reset @a multi-rank

scoreboard players reset #retire-time multi-main
scoreboard players reset #game multi-main
scoreboard players reset #time multi-main
# scoreboard players reset * multi-spectate-quit
scoreboard objectives remove multi-spectate-quit
scoreboard objectives add multi-spectate-quit dummy
#게임 끝
data modify entity @e[tag=multi-state-text,limit=1] text set value [{translate:"경기 시작","color":"yellow"}]

#마무리
time set day
weather clear
execute as @a[x=-31,y=-1,z=154,dx=28, dy=6, dz=19,tag=!bgm-delay] run function multiplayroom:play
function timerpack:stop

execute as @a[tag=multi-hub-player] run team leave @s

function multiplay:system/lever/lock-off

# 공식 멀티전용 기능
execute if score #is-official-multi multi-main matches 1 if score #forceloadextend kartmain matches 1 run schedule function trackselect:forceload/all-unload 2t replace
# function trackselect:forceload/player-generate-chunks-true