tellraw @s [{translate:"공식 카트 메뉴\n","color":"aqua","bold":true}]
tellraw @s [{translate:"클릭하여 카트를 획득\n","color":"green"}]


tellraw @s [{text:"* ","color":"gray"},{"click_event":{"action":"run_command","command":"/trigger getkart set 11"},"color":"gray",translate:"[일반-1]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 12"},"color":"gray",translate:"[일반-2]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 13"},"color":"gray",translate:"[일반-3]"}]

tellraw @s [{text:"* ","color":"light_purple"},{"click_event":{"action":"run_command","command":"/trigger getkart set 21"},"color":"light_purple",translate:"[레어-1]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 22"},"color":"light_purple",translate:"[레어-2]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 23"},"color":"light_purple",translate:"[레어-3]"}]

tellraw @s [{text:"* ","color":"yellow"},{"click_event":{"action":"run_command","command":"/trigger getkart set 31"},"color":"yellow",translate:"[레전드-1]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 32"},"color":"yellow",translate:"[레전드-2]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 33"},"color":"yellow",translate:"[레전드-3]"}]

tellraw @s [{text:"* ","color":"dark_red"},{"click_event":{"action":"run_command","command":"/trigger getkart set 41"},"color":"dark_red",translate:"[유니크]"}]

tellraw @s [{text:"* ","color":"aqua"},{"click_event":{"action":"run_command","command":"/trigger getkart set 51"},"color":"aqua",translate:"[스페셜-1]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 52"},"color":"aqua",translate:"[스페셜-2]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 53"},"color":"aqua",translate:"[스페셜-3]"}]

tellraw @s [{text:"* ","color":"dark_gray"},{"click_event":{"action":"run_command","command":"/trigger getkart set 61"},"color":"dark_gray",translate:"[클래식-1]"},{text:"  "},{"click_event":{"action":"run_command","command":"/trigger getkart set 62"},"color":"dark_gray",translate:"[클래식-2]"}]
