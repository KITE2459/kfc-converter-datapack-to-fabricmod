
#진행방향과 스티어링의 각도 차이(드리프트 각도) + 절댓값 구하기
execute if score @s kartaccel matches ..-1 run scoreboard players add #kartangle kartmain 1800
scoreboard players operation #kartangle kartmain -= #kartdirectionangle kartmain
execute if score #kartangle kartmain matches 1800.. run scoreboard players remove #kartangle kartmain 3600
execute if score #kartangle kartmain matches ..-1800 run scoreboard players add #kartangle kartmain 3600

#회전 각도 차이의 절댓값 구하기
scoreboard players operation #kartangleabs kartmain = #kartangle kartmain
execute if score #kartangleabs kartmain matches ..-1 run scoreboard players operation #kartangleabs kartmain *= #kart-1 kartmain

#헤어핀 리미트 초과 스티어량
scoreboard players operation #kartangleabs-over kartmain = #kartangleabs kartmain
scoreboard players remove #kartangleabs-over kartmain 900
execute if score #kartangleabs-over kartmain matches ..0 run scoreboard players set #kartangleabs-over kartmain 0
execute if score #kartangleabs-over kartmain matches 700.. run scoreboard players set #kartangleabs-over kartmain 700

#헤어핀 리미트
execute if entity @s[tag=!kart-hairpin-unlimit] if score #kartangle kartmain matches 900.. run scoreboard players set #kartangle kartmain 900
execute if entity @s[tag=!kart-hairpin-unlimit] if score #kartangle kartmain matches ..-900 run scoreboard players set #kartangle kartmain -900

#충돌방어력 오프셋
scoreboard players operation #kart-defense-offset kartmain = #kartangleabs kartmain
scoreboard players operation #kart-defense-offset kartmain /= #kart30 kartmain

scoreboard players set @p[tag=kartpassenger] kartdefense 8
scoreboard players operation @p[tag=kartpassenger] kartdefense -= #kart-defense-offset kartmain

execute if score @p[tag=kartpassenger] kartdefense matches 1.. run scoreboard players set @p[tag=kartpassenger] kartdefense 0