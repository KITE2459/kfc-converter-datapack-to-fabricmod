tellraw @s {text:"난이도 선택\n", color:gold, bold:true}
tellraw @s {text:"[쉬움]", color:green, bold:true,click_event:{action:run_command,command:"function api:map/entree/select_difficulty_easy with storage game setup"}}
tellraw @s [{text:"  초기 골드: ", color:yellow},{text:"600", color:white, bold:true}]
tellraw @s [{text:"  웨이브 수: ", color:red},{text:"25", color:white, bold:true}]
tellraw @s {text:"[보통]", color:yellow, bold:true,click_event:{action:run_command,command:"function api:map/entree/select_difficulty_normal with storage game setup"}}
tellraw @s [{text:"  초기 골드: ", color:yellow},{text:"700", color:white, bold:true}]
tellraw @s [{text:"  웨이브 수: ", color:red},{text:"30", color:white, bold:true}]
tellraw @s {text:"[어려움]", color:red, bold:true,click_event:{action:run_command,command:"function api:map/entree/select_difficulty_hard with storage game setup"}}
tellraw @s [{text:"  초기 골드: ", color:yellow},{text:"800", color:white, bold:true}]
tellraw @s [{text:"  웨이브 수: ", color:red},{text:"35", color:white, bold:true}]
tellraw @s {text:"다크", color:dark_purple, bold:true,click_event:{action:run_command,command:"function api:map/entree/select_difficulty_dark with storage game setup"}}
tellraw @s [{text:"  초기 골드: ", color:yellow},{text:"1,000", color:white, bold:true}]
tellraw @s [{text:"  웨이브 수: ", color:red},{text:"40", color:white, bold:true}]
tellraw @s {text:"[무한 모드]", color:aqua, bold:true,click_event:{action:run_command,command:"function api:map/entree/select_difficulty_infinity with storage game setup"}}
tellraw @s [{text:"  초기 골드: ", color:yellow},{text:"850", color:white, bold:true}]
tellraw @s [{text:"  웨이브 수: ", color:red},{text:"무한", color:white, bold:true}]