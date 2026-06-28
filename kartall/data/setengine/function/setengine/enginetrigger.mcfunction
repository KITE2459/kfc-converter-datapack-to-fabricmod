tellraw @s [{translate:"공식 엔진 메뉴\n","color":"aqua","bold":true}]
tellraw @s [{translate:"클릭하여 엔진을 변경\n","color":"green"}]

tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 10"},"color":"#0095b3",translate:"[X]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 21"},"color":"#ff00ff",translate:"[SR]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 11"},"color":"#8e2121",translate:"[EX]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 12"},"color":"#55FFFF",translate:"[JIU]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 15"},"color":"#ffffff",translate:"[V1]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 13"},"color":"#495b24",translate:"[NEW]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 17"},"color":"#aaaaaa",translate:"[1.0]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 18"},"color":"#0000ff",translate:"[PRO]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 19"},"color":"#00ff00",translate:"[RUSH+]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 16"},"color":"#ffff00",translate:"[A2]"}]
tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 20"},"color":"#7300ff",translate:"[CHARGE]"}, {text:"  "}, {"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 14"},"color":"#e06101",translate:"[Z7]"}]


# 0 x #0095b3
# 1 ex #8e2121
# 2 jiu #55FFFF
# 3 new #495b24
# 4 z7 #e06101
# 5 v1 #ffffff
# 6 a2 #ffff00
# 7 1.0 #aaaaaa
# 8 pro #0000ff
# 9 RUSH+ #00ff00, 파티클: #7CFF7C
# 10 CHARGE #7300ff
# 11 SR #ff00ff

#X SR
#EX JIU
#V1 NEW
#CHARGE Z7
#1.0 PRO
#RUSH+ A2




# --9 rally #856750--
#tellraw @s [{"bold":true,"click_event":{"action":"run_command","command":"/trigger setengine set 19"},"color":"#856750",translate:"RALLY"},{"click_event":{"action":"run_command","command":"/trigger setengine set 19"},"color":"green",translate:" 엔진 설정"}]