#누가 이미 타고있는 카트라면 못 부수게 하기
execute on passengers if entity @s[tag=kartsaddle,type=#kartmobil:kartsaddle] if entity @s[predicate=kartmobil:ifpassenger] run return 0

#아이디가 일치하는 플레이어에게 태그 주기
execute as @a if score @s kartid = @n[tag=kartmobil,type=#kartmobil:kartmobil] kartid run tag @s add kartbreakplayer
execute unless entity @a[tag=kartbreakplayer] run return run function kartmain:break-kart/kill-kart

stopsound @a[tag=kart-listener] neutral minecraft:item.elytra.flying
stopsound @a[tag=kartbreakplayer] neutral minecraft:item.elytra.flying

effect clear @a[tag=kartbreakplayer] minecraft:wind_charged
xp set @a[tag=kartbreakplayer] 0 points

#이펙트
particle minecraft:crit ~ ~0.5 ~ 0 0 0 0.75 50
particle minecraft:explosion ~ ~0.5 ~ 0 0 0 0 0

playsound minecraft:entity.zombie.attack_wooden_door neutral @a[tag=kartbreakplayer] ~ ~ ~ 0.5 0
playsound minecraft:entity.zombie.break_wooden_door neutral @a[tag=kartbreakplayer] ~ ~ ~ 0.25 1
playsound minecraft:entity.generic.explode neutral @a[tag=kartbreakplayer] ~ ~ ~ 0.5 0.75

function kartmain:break-kart/kill-kart