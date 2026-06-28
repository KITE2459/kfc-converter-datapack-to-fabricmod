# Apply record to master:config
# Input: iterator (map id), current_record {record: 123, record-text: ...}

# Construct the target path (maps."ID")
# Update/Insert target_time and bossbar_name based on dev record
# target_time = current_record.record
# bossbar_name = current_record.record-text (formatted as "XX:XX.XXX 내로 완주") - but record-text is just time.
# The user's screenshot shows record-text is `{"color":"aqua", "translate":"01:09.842"}`.
# We want bossbar name to be something like "01:09.842 내로 완주" (Complete within ...).
# We can construct a translate component `{"translate":"%s 내로 완주", "with":[record-text]}` if the resource pack supports it,
# or just use the text.
# Previous code used: `{"translate":"02:00.000 내로 완주","color":"aqua"}`.
# I will assume I can wrap the record text.

# 1. target_time
$data modify storage master:config maps."$(iterator)".target_time set from storage master:temp current_record.record

# 2. bossbar_name (constructing JSON text component)
# This is tricky in macros without string concatenation.
# However, we can store the record-text as a component.
# Let's try to set a compound for bossbar_name that uses "translate" with "with".
# check if "record-text" is a clear component.
$data modify storage master:config maps."$(iterator)".bossbar_name set value {translate:"%s 내로 완주", color:"aqua", with:[{}]}
$data modify storage master:config maps."$(iterator)".bossbar_name.with[0] set from storage master:temp current_record.record-text

# 3. available_maps에 이 맵을 추가하여
# devbattle 기록이 없는 맵은 풀에 포함되지 않게 합니다.
$data modify storage master:config available_maps append value $(iterator)

# 4. complete_subtitle (optional, maybe default or derived?)# 3. complete_subtitle (optional, maybe default or derived?)
# For now, only setting what we know.
