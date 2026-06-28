execute if score @s setdummyengine matches 1 run function setengine:setengine/dummy-enginetrigger

execute if score @s setdummyengine matches 1000 run function setengine:setengine/dummy/engine_n1
execute if score @s setdummyengine matches 1001 run function setengine:setengine/dummy/engine_rx
execute if score @s setdummyengine matches 1002 run function setengine:setengine/dummy/engine_key
execute if score @s setdummyengine matches 1003 run function setengine:setengine/dummy/engine_mario
execute if score @s setdummyengine matches 1004 run function setengine:setengine/dummy/engine_boat
execute if score @s setdummyengine matches 1005 run function setengine:setengine/dummy/engine_gear
execute if score @s setdummyengine matches 1006 run function setengine:setengine/dummy/engine_f1
#execute if score @s setdummyengine matches 1007 run function setengine:setengine/dummy/engine_krp
execute if score @s setdummyengine matches 1007 run function setengine:setengine/dummy/engine_rally
execute if score @s setdummyengine matches 1008 run function setengine:setengine/dummy/engine_ds


function kartmobil:s2c-value/for-official-multiplay/push-into-value

scoreboard players reset @s setdummyengine


