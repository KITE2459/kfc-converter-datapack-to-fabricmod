scoreboard players set #kartdrift kartmain 230400

#tellraw @p [{text:"[DEBUG] kartdrift: ",color:"gold",extra:[{score:{name:"@s",objective:"kartdrift"}}]}]

#레이싱 타이어 - 유턴 90도 고정하고 드탈 보정
scoreboard players operation #kartdrift-player kartmain = @s kartdrift
execute if entity @s[tag=!kart-hairpin-unlimit] unless score #kartangle kartmain matches -899..899 run function kartmobil:move/steering/drift-physics/hairpin-limit-kartdrift
scoreboard players operation #kartdrift-player kartmain += @p[tag=kartpassenger] kartdrift

    #드탈력 저하 조건 | 드리프트 중이면서 전진 미입력
    execute if score #floor-distance floor-distance matches ..1 if score @s[tag=!kart-w-pressed,tag=kart-drifting] kartmove matches 13900.. run scoreboard players operation #kartdrift-player kartmain /= #kart8 kartmain
    execute if score #floor-distance floor-distance matches ..1 if score @s[tag=!kart-w-pressed,tag=kart-drifting] kartmove matches 6950..13899 run scoreboard players operation #kartdrift-player kartmain /= #kart4 kartmain

execute if score #kartdrift-player kartmain matches ..0 run scoreboard players set #kartdrift-player kartmain 1

execute if score @s kart-engine matches 1006 run function kartmobil:move/steering/drift-physics/f1-modify

#tellraw N_Devil [{text:"[DEBUG] kartdrift: ",color:"gold",extra:[{score:{name:"#kartdrift-player",objective:"kartmain"}}]}]

scoreboard players operation #kartdrift kartmain /= #kartdrift-player kartmain

#1.0엔진 - 낮은 피치각 물리
execute if score @s kartmove matches 2780.. if score @s kart-engine matches 7 if score @s kartaccel matches 0.. on passengers as @s[tag=kartdirectiontemp,type=item_display] at @s anchored eyes rotated as @p[tag=kartpassenger] rotated ~ 23.5 positioned ^ ^ ^5 rotated as @s run function kartmobil:move/steering/drift-physics/drift-tree/main

#다른 엔진
execute if entity @s[tag=kart-hairpin-unlimit] if score @s kartmove matches 2780.. unless score #kartangle kartmain matches -1..1 run function kartmobil:move/steering/drift-physics/drift-converge
execute if entity @s[tag=!kart-hairpin-unlimit] run function kartmobil:move/steering/drift-physics/hairpin-limit-converge

execute if score @s kartmove matches 2780.. if score #kartangle kartmain matches -1..1 on passengers as @s[tag=kartdirectiontemp,type=item_display] run function kartmobil:move/steering/drift-physics/driftmacro-lowspeed
execute if score @s kartmove matches ..2779 on passengers as @s[tag=kartdirectiontemp,type=item_display] run function kartmobil:move/steering/drift-physics/driftmacro-lowspeed

#동적인 드탈 보정값 초기화
scoreboard players set @p[tag=kartpassenger] kartdrift 0
