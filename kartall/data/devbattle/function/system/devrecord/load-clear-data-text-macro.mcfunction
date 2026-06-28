data modify storage devattack-cleardata:dev-attack-cleardata temp-cleardata-for-text set value 0
$data modify storage devattack-cleardata:dev-attack-cleardata temp-cleardata-for-text set from storage devattack-cleardata:dev-attack-cleardata map$(tempmap)-dev$(tempdev)-cleardata
execute store result score cleardata timermain run data get storage devattack-cleardata:dev-attack-cleardata temp-cleardata-for-text

execute if score cleardata timermain matches 1 run data modify entity @s text set value {translate:"클리어!","color":"yellow"}
execute if score cleardata timermain matches 0 run data modify entity @s text set value {translate:"클리어하지 않음","color":"gray"}