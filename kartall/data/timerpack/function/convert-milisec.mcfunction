scoreboard players operation min timermain = #time timermain
scoreboard players operation sec timermain = #time timermain
scoreboard players operation milisec timermain = #time timermain

scoreboard players operation min timermain /= 60000 timermain

scoreboard players operation sec timermain %= 60000 timermain
scoreboard players operation sec timermain /= 1000 timermain

scoreboard players operation milisec timermain %= 60000 timermain
scoreboard players operation milisec timermain %= 1000 timermain

execute store result storage timermain min int 1 run scoreboard players get min timermain
execute store result storage timermain sec int 1 run scoreboard players get sec timermain
execute store result storage timermain milisec int 1 run scoreboard players get milisec timermain

function timerpack:convert-to-string-macro with storage timermain