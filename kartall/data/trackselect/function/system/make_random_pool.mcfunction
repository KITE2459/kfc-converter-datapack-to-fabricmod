#테마 가져오기
scoreboard players set #temp temp 0
data modify storage temp themes set value []
function trackselect:system/get_theme_list

#랜덤 트랙 풀 초기화
scoreboard players set #temp temp 0
scoreboard players set #theme_index temp 0
data modify storage random_tracks tracks set value {easy:[],normal:[],hard:[],very-hard:[],all:[]}
function trackselect:system/set_pool