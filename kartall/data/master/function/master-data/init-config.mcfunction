# 마스터 시스템 설정 데이터 초기화
# usage: function master:master-data/init-config

data remove storage master:config available_maps

# available_maps가 없으면 빈 목록으로 초기화하여
# import-dev-records에서 devbattle 기록이 있는 맵만 채웁니다.
execute unless data storage master:config available_maps run data modify storage master:config available_maps set value []

# 수동 설정 (1~3)

# 자동 가져오기 (devattack 데이터 기반)
# 기존 수동 설정을 덮어쓸 수도 있고, 없는 맵을 추가할 수도 있음.
# 여기서는 "우선적으로 적용"하라고 했으므로, import를 나중에 실행하여 덮어쓰거나 병합하도록 함.
function master:master-data/import-dev-records



