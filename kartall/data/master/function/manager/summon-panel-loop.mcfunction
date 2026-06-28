# 7개의 패널 소환 루프

# 현재 인덱스(#panel_idx)의 맵 ID 가져오기 위해 매크로 준비
data modify storage master:game macro_args set value {}
execute store result storage master:game macro_args.idx int 1 run scoreboard players get #panel_idx master

# 매크로 실행: 인덱스로 ID를 가져옴
function master:manager/summon-panel-macro with storage master:game macro_args

# 다음 위치로 이동 (x + 3) 및 반복
scoreboard players add #panel_idx master 1
execute positioned ~ ~ ~3 if score #panel_idx master matches ..6 run function master:manager/summon-panel-loop
