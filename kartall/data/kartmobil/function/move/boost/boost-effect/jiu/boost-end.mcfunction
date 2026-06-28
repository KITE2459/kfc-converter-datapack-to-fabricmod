tag @s[tag=a2-using-instant-boost] remove a2-using-instant-boost

#부스터 쿨타임(일부러 부스터 사이에 텀을 만들기)
scoreboard players set @p[tag=kartpassenger] kartboosttime 2

#부스터 & 듀얼 부스터 상태 초기화
execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/off-set-0

#서서히 줄어드는 불(jiu) 판정 제거
execute on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-slow-fade,type=#kartmobil:kartmodels] remove kart-boost-flame-slow-fade
execute on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-show,type=#kartmobil:kartmodels] add kart-boost-flame-hide-finish
execute on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-show,type=#kartmobil:kartmodels] remove kart-boost-flame-show


execute if entity @s[tag=shadow-main] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-slow-fade,type=#kartmobil:kartmodels] remove kart-boost-flame-slow-fade
execute if entity @s[tag=shadow-main] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-show,type=#kartmobil:kartmodels] add kart-boost-flame-hide-finish
execute if entity @s[tag=shadow-main] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-show,type=#kartmobil:kartmodels] remove kart-boost-flame-show