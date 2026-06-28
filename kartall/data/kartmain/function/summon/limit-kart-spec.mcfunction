$execute store result storage temp temp int 0.$(limit) run scoreboard players get @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartspeed
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartspeed run data get storage temp temp
$execute store result storage temp temp int 0.$(limit) run scoreboard players get @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartaccel
execute store result score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kartaccel run data get storage temp temp
