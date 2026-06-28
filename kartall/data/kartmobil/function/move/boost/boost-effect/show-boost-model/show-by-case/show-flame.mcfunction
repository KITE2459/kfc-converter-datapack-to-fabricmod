#부스터 불꽃의 트랜스폼 설정
execute if entity @s[tag=kart-boost-flame,tag=!kart-boost-flame-show] run data modify entity @s transformation set from entity @s data.boost-transform-end
execute if entity @s[tag=kart-boost-flame,tag=!kart-boost-flame-show] run data modify entity @s interpolation_duration set from entity @s data.interpolation_duration
execute if entity @s[tag=kart-boost-flame,tag=!kart-boost-flame-show] run data modify entity @s start_interpolation set value 0

tag @s[tag=kart-boost-flame] add kart-boost-flame-show
data modify entity @s view_range set value 1f