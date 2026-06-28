scoreboard objectives add fast-garage-x dummy
scoreboard objectives add fast-garage-y dummy
scoreboard objectives add fast-garage-z dummy

data remove entity @s data.kart-item-data

execute if entity @s[tag=garage-common-main] run function kartmodel:getkartitem/getofficialkart/getcommonkart
execute if entity @s[tag=garage-common-main] run function kartmodel:getkartitem/getofficialkart/getcommonkart-2
execute if entity @s[tag=garage-common-main] run function kartmodel:getkartitem/getofficialkart/getcommonkart-3

execute if entity @s[tag=garage-rare-main] run function kartmodel:getkartitem/getofficialkart/getrarekart
execute if entity @s[tag=garage-rare-main] run function kartmodel:getkartitem/getofficialkart/getrarekart-2
execute if entity @s[tag=garage-rare-main] run function kartmodel:getkartitem/getofficialkart/getrarekart-3

execute if entity @s[tag=garage-legend-main] run function kartmodel:getkartitem/getofficialkart/getlegendkart
execute if entity @s[tag=garage-legend-main] run function kartmodel:getkartitem/getofficialkart/getlegendkart-2
execute if entity @s[tag=garage-legend-main] run function kartmodel:getkartitem/getofficialkart/getlegendkart-3

execute if entity @s[tag=garage-unique-main] run function kartmodel:getkartitem/getofficialkart/getuniquekart
execute if entity @s[tag=garage-unique-main] run function kartmodel:getkartitem/getofficialkart/getuniquekart-2
execute if entity @s[tag=garage-unique-main] run function kartmodel:getkartitem/getofficialkart/getuniquekart-3

execute if entity @s[tag=garage-special-main] run function kartmodel:getkartitem/getofficialkart/getspecialkart
execute if entity @s[tag=garage-special-main] run function kartmodel:getkartitem/getofficialkart/getspecialkart-2
execute if entity @s[tag=garage-special-main] run function kartmodel:getkartitem/getofficialkart/getspecialkart-3

execute if entity @s[tag=garage-classic-main] run function kartmodel:getkartitem/getofficialkart/getclassickart
execute if entity @s[tag=garage-classic-main] run function kartmodel:getkartitem/getofficialkart/getclassickart-2
execute if entity @s[tag=garage-classic-main] run function kartmodel:getkartitem/getofficialkart/getclassickart-3