# mastermap 스코어에 따라 맵 기록을 로드합니다.
# 사용되는 스토리지: master-record:data
# 구조: {map1:{record:12345, record-text:"00:12.345"}, map2:...}

# 맵 ID 확인 map<ID> 형태의 키를 찾기 위해 매크로 사용
data modify storage master-record:temp map_key set value "map"
execute store result storage master-record:temp map_id int 1 run scoreboard players get @s mastermap

# 매크로 실행 보조 함수 호출
function master:master-data/load-record-macro with storage master-record:temp
