#카트 중심엔티티에 태그 달기
tag @s add kart-to-die

#아이템 데이터 캐리어에 태그 달기
execute as @e[tag=kart-to-die,type=#kartmobil:kartmobil] on passengers if entity @s[tag=kartdatacarrier,type=item_display] run tag @s add kartdatacarriertemp
execute as @a[tag=kartbreakplayer] run function kartmain:break-kart/breakandgiveitem with entity @n[tag=kartdatacarriertemp,type=item_display] data.itemdata

#플레이어가 바닥에 파묻히는 버그 수정용 tp
execute at @n[tag=kartsaddle,type=#kartmobil:kartsaddle] run tp @a[tag=kartbreakplayer,distance=..1] ~ ~ ~

#위에 탑승한 모델 다 죽이기
execute as @e[tag=kart-to-die,type=#kartmobil:kartmobil] on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers run kill @s[type=!player]
execute as @e[tag=kart-to-die,type=#kartmobil:kartmobil] on passengers if entity @s[tag=kartsaddle,type=#kartmobil:kartsaddle] on passengers run kill @s[type=!player]
execute as @e[tag=kart-to-die,type=#kartmobil:kartmobil] on passengers run kill @s[type=!player]

#R키용 아머 삭제
clear @a[tag=kartbreakplayer] minecraft:armor_stand[custom_data={rkey:1}]
clear @a[tag=kartbreakplayer] *[minecraft:custom_data~{xun:1}]

tag @a[tag=kartbreakplayer] remove kartbreakplayer
kill @e[tag=kart-to-die,type=#kartmobil:kartmobil]

#세이브 데이터 관리 팩 의존성
function updatedetector:on-user-dismount-kart

return 1