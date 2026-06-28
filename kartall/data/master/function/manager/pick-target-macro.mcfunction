# idx 위치의 값을 current_targets로 이동
$data modify storage master:game current_targets append from storage master:game temp_pool[$(idx)]
$data remove storage master:game temp_pool[$(idx)]
