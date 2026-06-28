# 매크로: 지정된 인덱스의 맵 ID로 게임 시작
# 인자 필요없음, 스코어보드 기반

# 1. ID 가져오기
data modify storage master:game start_args.idx set value 0
execute store result storage master:game start_args.idx int 1 run scoreboard players get #clicked_idx master
function master:manager/interaction/get-id-macro with storage master:game start_args

# 2. 게임 시작 (get-id-macro가 호출함)
