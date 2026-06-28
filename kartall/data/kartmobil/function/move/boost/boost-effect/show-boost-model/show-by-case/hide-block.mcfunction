#사라지는 파츠 보이기
execute if entity @s[tag=kart-boost-fade] run data modify entity @s view_range set value 1f

#바로 보이는 부스터 숨기기
execute if entity @s[tag=kart-boost-instant] run data modify entity @s view_range set value 0f

#이동 부스터
execute if entity @s[tag=kart-boost-move] run data modify entity @s transformation set from entity @s data.boost-transform-start
execute if entity @s[tag=kart-boost-move] run data modify entity @s interpolation_duration set from entity @s data.interpolation_duration_hide
execute if entity @s[tag=kart-boost-move] run data modify entity @s start_interpolation set value 2

tag @s remove kart-boost-blocks-show