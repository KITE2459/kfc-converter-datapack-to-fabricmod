#$data modify storage timermain time set value {"color":"aqua",translate:"$(min) : $(sec) : $(milisec)"}

#$execute if score min timermain matches 0..9 run data modify storage timermain min set value [{translate:"0"},{translate:"$(min)"}]
#$execute if score sec timermain matches 0..9 run data modify storage timermain sec set value [{translate:"0"},{translate:"$(sec)"}]
#$execute if score milisec timermain matches 0..9 run data modify storage timermain milisec set value [{translate:"0"},{translate:"$(milisec)"}]


$execute unless score min timermain matches 0..9 run data modify storage timermain min set value '$(min)'
$execute unless score milisec timermain matches 0..9 run data modify storage timermain milisec set value '$(milisec)'

$execute if score min timermain matches 0..9 run data modify storage timermain min set value '0$(min)'
$execute if score sec timermain matches 0..9 run data modify storage timermain sec set value '0$(sec)'
$execute if score milisec timermain matches 0..9 run data modify storage timermain milisec set value '00$(milisec)'
$execute if score milisec timermain matches 10..99 run data modify storage timermain milisec set value '0$(milisec)'

function timerpack:convert-to-string-macro2 with storage timermain