# 밴 확인 메시지 출력 매크로
# 인자: {idx: 0}

# 1. 맵 이름 가져오기
# candidates[idx] -> ID
$data modify storage master:game ban_msg_args.id set from storage master:game candidates[$(idx)]
$data modify storage master:game ban_msg_args.idx set value $(idx)

# 2. 이름 조회 후 메시지 출력 매크로 재호출
# (ID를 알았으니 track-list에서 이름을 가져올 수 있음)
function master:manager/interaction/ban-confirm-msg-resolve with storage master:game ban_msg_args
