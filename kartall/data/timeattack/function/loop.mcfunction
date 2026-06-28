
#만약 타임어택 준비중인 사람이 있다면
execute as @a at @s if score @s timecount matches 1.. if score @s trackselect-map-id matches 1.. run function timeattack:system/main

#관전자
bossbar set timeattack-spectator-ui players @a[tag=timeattack-spectator]
execute if entity @a[tag=timeattack-spectator] run function timeattack:system/spectator/spectatormain