#바로 보이는 부스터
execute if entity @s[tag=kart-boost-instant,tag=!kart-boost-blocks-show] run data modify entity @s view_range set value 1f

#사라지는 파츠 없애기
execute if entity @s[tag=kart-boost-fade,tag=!kart-boost-blocks-show] run data modify entity @s view_range set value 0f

#이동 부스터
execute if entity @s[tag=kart-boost-move,tag=!kart-boost-blocks-show] run data modify entity @s transformation set from entity @s data.boost-transform-end
execute if entity @s[tag=kart-boost-move,tag=!kart-boost-blocks-show] run data modify entity @s interpolation_duration set from entity @s data.interpolation_duration
execute if entity @s[tag=kart-boost-move,tag=!kart-boost-blocks-show] run data modify entity @s start_interpolation set from entity @s data.start_interpolation

tag @s[tag=kart-boost-blocks] add kart-boost-blocks-show