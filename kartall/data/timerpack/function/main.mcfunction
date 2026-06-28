scoreboard players add time timermain 50

#분초틱 계산(무작위 한자리수 밀리초를 빼기)
scoreboard players operation #time timermain = time timermain
execute store result score random-fake-milisec timermain run random value 0..49
execute if score #time timermain matches 50.. run scoreboard players operation #time timermain -= random-fake-milisec timermain

function timerpack:convert-milisec

#표시
function timerpack:show-scoreboard-macro with storage timermain