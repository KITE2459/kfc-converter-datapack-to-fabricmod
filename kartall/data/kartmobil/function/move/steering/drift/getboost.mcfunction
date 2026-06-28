#게이지 초기화
scoreboard players set @s kartboostgauge 0

#퓨전부스터
execute if score @s kart-engine matches 9 if score @s kartboostcount >= @s kartmaxboostcount run tag @s add kart-fusion-boost

#치이익(태우면 40% 회복)
execute unless score @s kart-engine matches 7 if score @s kartboostcount >= @s kartmaxboostcount at @a[tag=kartpassenger] run playsound minecraft:block.fire.extinguish neutral @a[tag=kart-listener] ~ ~ ~ 1 2 1
execute unless score @s kart-engine matches 7 unless score @s kart-engine matches 9 if score @s kartboostcount >= @s kartmaxboostcount run scoreboard players add @s kartboostgauge 1000

#획득
execute unless score @s kartboostcount >= @s kartmaxboostcount run scoreboard players add @s kartboostcount 1
# tellraw KITE2459 [{text:"[부스터 모듈]내가 가진 부스터: "},{"score":{"name":"@s","objective":"kartboostcount"}}]
# say 2.부스터를 얻었다