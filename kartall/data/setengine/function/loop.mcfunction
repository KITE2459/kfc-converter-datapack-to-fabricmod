#카트 얻기
scoreboard players enable @a setengine
execute as @a[scores={setengine=1..}] run function setengine:setengine/enginehub

scoreboard players enable @a setdummyengine
execute as @a[scores={setdummyengine=1..}] run function setengine:setengine/dummy-enginehub

scoreboard players enable @a clear
execute as @a[scores={clear=1..}] run function setengine:setengine/clear

scoreboard players enable @a settire
execute as @a[scores={settire=1..}] run function setengine:settire/tirehub