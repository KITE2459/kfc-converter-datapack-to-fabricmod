# 밴 확인 메시지 최종 출력
# 인자: {idx:0, id:10}

# track-list 데이터 가져오기 (이미 로드되어 있다고 가정하거나, 새로 로드)
$function trackselect:read-track-data/get-track-by-number/main {number:$(id)}

# 메시지 출력
# 클릭 이벤트: /function master:manager/interaction/ban-process {idx:$(idx)}
# JSON 텍스트 구성

$tellraw @a [ \
    {text:"선택된 맵: ","color":"white"}, \
    {"nbt":"number-search-result.text","storage":"track-list","color":"yellow","bold":true,interpret:true}, \
    {text:"\n\n","color":"white"}, \
    {text:"[ 밴 확정 ]","color":"red","bold":true,"click_event":{"action":"run_command","command":"/function master:manager/interaction/ban-process {idx:$(idx)}"},"hover_event":{"action":"show_text","value":"Click to permanently BAN this map from selection"}}]
