# Check if devattack record exists for map$(iterator)-dev10-record
data remove storage master:temp current_record
$data modify storage master:temp current_record set from storage devattack:dev-attack-record map$(iterator)-dev10-record

# 레코드가 존재하면 master:config에 추가/업데이트
execute if data storage master:temp current_record.record run function master:master-data/import-dev-records-apply with storage master:temp
