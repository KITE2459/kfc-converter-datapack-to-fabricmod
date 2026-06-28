
#만약 개발자대결 준비중인 사람이 있다면
execute as @a at @s if score @s dev-count matches 1.. if score @s trackselect-map-id matches 1.. run function devbattle:system/main

#관전자
bossbar set devattack-spectator-ui players @a[tag=dev-attack-spectator]
execute if entity @a[tag=dev-attack-spectator] run function devbattle:system/spectator/spectatormain
