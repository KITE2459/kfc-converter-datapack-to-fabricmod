#부스터 쿨타임(일부러 부스터 사이에 텀을 만들기)
scoreboard players set @p[tag=kartpassenger] kartboosttime 2

#서서히 줄어드는 불(jiu) 판정 제거
execute on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-slow-fade,type=#kartmobil:kartmodels] remove kart-boost-flame-slow-fade
execute on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-show,type=#kartmobil:kartmodels] add kart-boost-flame-hide-finish
execute on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-show,type=#kartmobil:kartmodels] remove kart-boost-flame-show


execute if entity @s[tag=shadow-main] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-slow-fade,type=#kartmobil:kartmodels] remove kart-boost-flame-slow-fade
execute if entity @s[tag=shadow-main] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-show,type=#kartmobil:kartmodels] add kart-boost-flame-hide-finish
execute if entity @s[tag=shadow-main] on passengers run tag @s[tag=kart-boost-flame,tag=kart-boost-flame-show,type=#kartmobil:kartmodels] remove kart-boost-flame-show