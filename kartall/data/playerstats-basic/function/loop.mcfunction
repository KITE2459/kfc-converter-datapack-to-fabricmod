#잠재력 고정 - 카트팩 자체에 처음 카트를 타는 사람의 기본 잠재력 설정 구문 추가
execute if score #kfc-gamesystem kartmain matches 1 run return 1
#이펙트 셋업
function playerstats-basic:effect

#스폰포인트
spawnpoint @a 206 4 -372 180
setworldspawn 206 4 -373 180
execute positioned 206 -63 -372 as @a[distance=..5] run tp @s ~ 4 ~ 180 0

#아이템 사라지게 하기
execute as @e[type=item,tag=!itemdelete] at @s run function playerstats-basic:itemdelete
