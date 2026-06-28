#플레이어는 현재 지나간 체크포인트 A와 그 다음 번호의 체크포인트 B, 즉 A와 B라는 이름의 두 변수를 가짐.
#만약 내가 통과(체크포인트 주변에 접근)한 체크포인트가 B의 번호와 같다면, A와 B의 숫자를 각각 1씩 증가시키는 체크포인트 통과함수를 1회 실행함.
#만약 B를 증가시킬 때, B의 번호에 해당하는 체크포인트가 존재하지 않는다면, 해당 플레이어는 마지막 체크포인트 통과로 간주하고, 결승선을 통과할 때 랩 카운트가 올라가거나 1랩 맵의 경우 완주하며, A는 0, B는 1로 초기화됨.

execute at @n[tag=kartmobil,type=#kartmobil:kartmobil] if block ~ ~-0.5 ~ #kartmobil:ignoreblock run return 1

tag @s add check-player-temp

#역주행
#execute positioned ~-15 -64 ~-15 as @e[dx=30,dy=384,dz=30,tag=check-point,tag=!check-large,type=marker] if score @s check-num = @a[tag=check-player-temp,limit=1] check-prev as @a[tag=check-player-temp,limit=1] at @s run function checkpoint:system/check-pass-backward
#execute positioned ~-30 -64 ~-30 as @e[dx=60,dy=384,dz=60,tag=check-point,tag=check-large,type=marker] if score @s check-num = @a[tag=check-player-temp,limit=1] check-prev as @a[tag=check-player-temp,limit=1] at @s run function checkpoint:system/check-pass-backward

#만약 내가 통과(체크포인트 주변에 접근)한 체크포인트가 B의 번호와 같다면, A와 B의 숫자를 각각 1씩 증가시키는 체크포인트 통과함수를 1회 실행함.
execute positioned ~-15 ~-0.5 ~-15 as @e[dx=30,dy=-1,dz=30,tag=check-point,tag=!check-large,type=marker] run function checkpoint:system/as-checkpoint-entity-updown
execute positioned ~-30 ~-0.5 ~-30 as @e[dx=60,dy=-1,dz=60,tag=check-point,tag=check-large,type=marker] run function checkpoint:system/as-checkpoint-entity-updown

tag @s remove check-player-temp