$data modify storage master-record:current record set from storage master-record:data map$(map_id)

# 기록이 없으면 초기화 (0 또는 최대값?) - 여기서는 0으로 가정하거나 처리를 안함
# 기록이 있으면 스코어보드에 로드
execute store result score global masterrecord run data get storage master-record:current record.record
data modify storage master-record:display text set from storage master-record:current record.record-text

# 텍스트 디스플레이나 기타 표시 로직이 필요하다면 여기에 추가
# 예: title @a actionbar {"nbt":"text","storage":"master-record:display"}
