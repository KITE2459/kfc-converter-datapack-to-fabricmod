
#만약 타임어택 준비중인 사람이 있다면
execute as @a at @s if score @s updown-count matches 1.. if score #map trackselect-map-id matches 1001.. run function updown:system/main
execute as @a at @s if score @s updown-count matches 1.. if score #map trackselect-map-id matches ..-1001 run function updown:crossover/main
#관전자
bossbar set updown-spectator-ui players @a[tag=updown-spectator]
execute if entity @a[tag=updown-spectator] run function updown:system/spectator/spectatormain