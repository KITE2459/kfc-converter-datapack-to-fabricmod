# devattack 데이터를 기반으로 master:config를 초기화/업데이트하는 함수
# usage: function master:master-data/import-dev-records

# 임시 스토리지 초기화
data modify storage master:temp maps set value {}

# 루프 시작 (1부터 999까지)
scoreboard players set #map_iterator master 1
function master:master-data/import-dev-records-loop
