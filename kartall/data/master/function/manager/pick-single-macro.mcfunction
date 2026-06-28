# 매크로: 지정된 인덱스의 값을 candidates로 이동
$data modify storage master:game candidates append from storage master:game pool[$(idx)]
$data remove storage master:game pool[$(idx)]
