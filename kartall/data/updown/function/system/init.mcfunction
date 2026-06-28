title @s times 0 20 20
#랩 설정

scoreboard players set @s trackselect-lap 0

execute store result score #max-lap trackselect-lap run function trackselect:read-track-data/getmaxlap with entity @n[tag=trackselect-updown-marker] data.track
function trackselect:read-track-data/movetomap with entity @n[tag=trackselect-updown-marker] data.track
execute as @n[tag=trackselect-updown-marker] run function trackselect:read-track-data/execute-etc-command

function checkpoint:system/init

