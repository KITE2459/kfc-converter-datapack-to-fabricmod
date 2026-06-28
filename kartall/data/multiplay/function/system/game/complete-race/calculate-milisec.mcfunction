#결승선과의 거리 측정
scoreboard players reset @s kart-milisec-calc
execute on vehicle on vehicle at @s on passengers rotated as @s[tag=kart-old-velocity] rotated ~ 0 on vehicle on passengers if entity @s[tag=kartsaddle] on passengers if entity @s[type=player] run function multiplay:system/game/complete-race/raycast

#서브 틱(100분의 1틱 측정)
scoreboard players set #subtick kartmain 100
scoreboard players operation #finish-line-distance kart-milisec-calc = @s kart-milisec-calc
execute on vehicle on vehicle on passengers if entity @s[tag=kart-old-velocity] run scoreboard players operation #can-go-distance kartmain = @s kartmove
scoreboard players reset @s kart-milisec-calc

#can-go-distance의 1000의자리 날리기
scoreboard players operation #can-go-distance kartmain /= #kart1000 kartmain
scoreboard players operation #can-go-distance kartmain *= #kart1000 kartmain

scoreboard players operation #finish-line-distance kart-milisec-calc *= #kart100 kartmain
scoreboard players operation #finish-line-distance kart-milisec-calc /= #can-go-distance kartmain
scoreboard players operation #subtick kartmain -= #finish-line-distance kart-milisec-calc

execute if score #subtick kartmain matches ..0 run scoreboard players set #subtick kartmain 0

scoreboard players operation #subtick kartmain /= #kart2 kartmain

scoreboard players operation #time timermain = time timermain
scoreboard players operation #time timermain += #subtick kartmain

function timerpack:convert-milisec