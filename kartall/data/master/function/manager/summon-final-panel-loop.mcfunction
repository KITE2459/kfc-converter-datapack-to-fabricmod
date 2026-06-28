# 최종 3개 패널 소환 루프

# 현재 인덱스(#panel_idx)의 맵 ID 가져오기
data modify storage master:game macro_args.idx set value 0
execute store result storage master:game macro_args.idx int 1 run scoreboard players get #panel_idx master

# 매크로 실행
function master:manager/summon-final-panel-macro with storage master:game macro_args

# 다음 위치로 이동 (x + 3) 및 반복
scoreboard players add #panel_idx master 1
execute positioned ~ ~ ~3 if score #panel_idx master matches ..2 run function master:manager/summon-final-panel-loop
