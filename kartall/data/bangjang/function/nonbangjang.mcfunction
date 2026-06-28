title @a actionbar [{translate:"방장이 존재하지 않습니다, 방장을 얻으려면 중앙으로 이동해주세요",color:red,bold:true}]
bossbar set bangjang name [{color:green,translate:"방장 ",bold:true},{color:yellow,translate:"없음"}]

scoreboard players set #alert multi-main 1
tag @r[tag=multi-hub-player,limit=1] add bangjang
