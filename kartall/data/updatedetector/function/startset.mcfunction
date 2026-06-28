#파싱
execute store result score #check-update-timeattack kartmain run data get storage timeattack:timeattack-record check-update
execute store result score #check-update-license kartmain run data get storage license-cleardata:license-data check-update
execute store result score #check-update-devattack kartmain run data get storage devattack-cleardata:dev-attack-cleardata check-update
execute store result score #check-update-master kartmain run data get storage master-cleardata:master-data check-update

#비교
data modify storage minecraft:kartmain kart-update-check set value []

execute unless score #check-update-timeattack kartmain = #check-update kartmain run data modify storage minecraft:kartmain kart-update-check append value {"text":"타임어택 ","color":"yellow"}
execute unless score #check-update-license kartmain = #check-update kartmain run data modify storage minecraft:kartmain kart-update-check append value {"text":"라이센스 ","color":"yellow"} 
execute unless score #check-update-devattack kartmain = #check-update kartmain run data modify storage minecraft:kartmain kart-update-check append value {"text":"제작자와의 대결 ","color":"yellow"}
execute unless score #check-update-master kartmain = #check-update kartmain run data modify storage minecraft:kartmain kart-update-check append value {"text":"마스터 ","color":"yellow"}

data modify storage minecraft:kartmain kart-update-check append value {"text":"세이브 데이터를 적용하는 데 성공했습니다.","color":"aqua"}

scoreboard players set #update-exist kartmain 0
execute if data storage minecraft:kartmain kart-update-check[1] run scoreboard players set #update-exist kartmain 1

