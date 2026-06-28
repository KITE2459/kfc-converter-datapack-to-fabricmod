execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-exceed/while-set-1

playsound minecraft:item.elytra.flying neutral @a[tag=kart-listener] ~ ~ ~ 1 1
playsound minecraft:item.elytra.flying neutral @a[tag=kart-listener] ~ ~ ~ 1 1.25
playsound minecraft:item.elytra.flying neutral @a[tag=kart-listener] ~ ~ ~ 1 1.5

#드래프트 발동중 너프
execute unless score @s kartmain matches 60..100 run scoreboard players add @s kartmove 1150

tag @s add kart-exceed-active

