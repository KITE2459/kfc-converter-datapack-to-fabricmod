# $id, $idx 변수 사용 가능

# 1. 트랙 프롬포트 데이터 불러오기
$function trackselect:read-track-data/get-track-by-number/main {number:$(id)}

# 2. 기록 데이터 준비
# (1) 기본값으로 초기화
$data modify storage master-record:display_list map$(idx).text set value {text:"--:--.---","color":"gray"}

# (2) 기록이 있으면 덮어쓰기
# readytoplay -> load-settings -> master:config maps."$(id)".bossbar_name
# bossbar_name은 {"translate":"%s 내로 완주", "with":[record-text]} 형태임.
# 따라서 bossbar_name.with[0] 에 기록 텍스트가 있음.
# 키는 map$(id) 가 아니라 "$(id)" 형태임.

# $execute if data storage master:config maps."$(id)".bossbar_name.with[0] run data modify storage master-record:display_list map$(idx).text set from storage master:config maps."$(id)".bossbar_name.with[0]

# [변경] masterstage에 따른 보정 적용
# target_time을 가져와서 adjust-target-time 실행 후 결과 사용
$execute store result score #target-time master run data get storage master:config maps."$(id)".target_time
$execute if data storage master:config maps."$(id)".target_time run function master:system/game/adjust-target-time
$execute if data storage master:config maps."$(id)".target_time run data modify storage master-record:display_list map$(idx).text set from storage master:temp formatted_text
$data modify storage master-record:display_list map$(idx).text.color set value red
$data modify storage master-record:display_list map$(idx).text.italic set value true

# 3. 엔티티 소환
# record-text는 master-record:display_list의 map$(idx).text를 참조하도록 설정

$summon text_display ~.001 ~ ~ {Tags:["master.text","master.panel","panel_$(idx)"],Rotation:[-90F,0F],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,2f,0f],scale:[1.3f,1.3f,1.3f]},text:{interpret:true,"storage":"track-list","nbt":"number-search-result.text"},background:16711680}
$summon text_display ~.001 ~ ~ {Tags:["master.text","master.panel","panel_$(idx)"],Rotation:[-90F,0F],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.7f,0f],scale:[1f,1f,1f]},text:{interpret:true,"storage":"track-list","nbt":"number-search-result.difficulty"},background:16711680}
$summon text_display ~.001 ~ ~ {Tags:["master.text","master.panel","panel_$(idx)","panel_record_$(idx)"],Rotation:[-90F,0F],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.1f,0f],scale:[2f,2f,2f]},text:{interpret:true,"storage":"master-record:display_list","nbt":"map$(idx).text"},background:16711680}
$summon text_display ~.001 ~ ~ {Tags:["master.text","master.panel","panel_$(idx)","panel_banned_$(idx)"],Rotation:[-90F,0F],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0.6f,0f],scale:[2f,2f,2f]},background:16711680}
$summon interaction ~-1.499 ~ ~ {width:3f,height:3f,Tags:["master.interaction","panel_$(idx)","panel_interaction_$(idx)","master.panel"]}
