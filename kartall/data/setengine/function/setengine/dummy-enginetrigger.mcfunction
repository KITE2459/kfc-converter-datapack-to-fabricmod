
tellraw @s [{translate:"더미 엔진(밸런스 안 맞음) 메뉴\n","color":"aqua","bold":true}]
tellraw @s [{translate:"클릭하여 엔진을 변경\n","color":"green"}]

tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1000"},"color":"#FF57db",translate:"[N1]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1002"},"color":"aqua",translate:"[KEY]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1001"},"color":"#bd6e00",translate:"[RX]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1004"},"color":"yellow",translate:"[BOAT]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1003"},"color":"#ff0000",translate:"[MK]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1005"},"color":"gray",translate:"[GEAR]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1008"},italic:true,"color":"#ff0000",translate:"[DS]"} , {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1007"},"color":"#856750",translate:"[RALLY]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1006"},italic:true,"color":"#8f0000",translate:"[F1]"}]




#N1 KEY
#RX BOAT
#MK GEAR
#F1 RALLY

#tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1007"},"color":"#00ff00",translate:"RUSH+"},{"click_event":{"action":"run_command","command":"/trigger setdummyengine set 1007"},"color":"green",translate:" 엔진 설정"}]