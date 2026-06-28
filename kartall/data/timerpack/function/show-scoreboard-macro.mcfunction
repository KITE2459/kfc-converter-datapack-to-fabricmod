scoreboard players set timertext timerdisplay 2147483647
scoreboard players set timertext2 timerdisplay 2147483646

scoreboard objectives modify timerdisplay displayname ""
$data modify storage timermain time set value {translate:"$(min):$(sec).$(milisec)","color":"aqua"}
$scoreboard players display name timertext timerdisplay {translate:"   $(min):$(sec).$(milisec)   ","color":"aqua"}
scoreboard players display name timertext2 timerdisplay ""

scoreboard players display numberformat timertext timerdisplay blank
scoreboard players display numberformat timertext2 timerdisplay blank