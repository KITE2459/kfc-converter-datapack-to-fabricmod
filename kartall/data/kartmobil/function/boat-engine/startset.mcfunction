

scoreboard objectives add boat-main dummy
scoreboard objectives add boat-angle-speed dummy

scoreboard objectives add boat-move-x dummy
scoreboard objectives add boat-move-y dummy
scoreboard objectives add boat-move-z dummy

#원점을 사용하기 위한 강제로드
execute positioned 0 0 0 run forceload add ~ ~
execute positioned 0 0 -1 run forceload add ~ ~
execute positioned -1 0 0 run forceload add ~ ~
execute positioned -1 0 -1 run forceload add ~ ~