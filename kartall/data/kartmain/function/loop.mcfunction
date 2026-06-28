#카트 물리엔진 테스트
execute as @a[tag=kart-physics-test] at @s if predicate kartmobil:ifride run rotate @s ~0 35

#pos, sidite-curse, kfc-opti 모드 존재 여부 감지
scoreboard players add #addon-mod-detect-timer kartmain 1
execute if score #addon-mod-detect-timer kartmain matches 200.. run function kartmain:addon-mod-detect/detect

    #이펙트 및 fov 초기화
    execute as @a[tag=kart-fov-changed-player] run function kartmain:reset-fov

    #부스터 불꽃 숨기기
    execute as @e[tag=kart-boost-flame-hide-finish,tag=kart-boost-flame-hide-finish-2,type=#kartmobil:kartmodels] run function kartmain:hide-flame-finish
    tag @e[tag=kart-boost-flame-hide-finish,type=#kartmobil:kartmodels] add kart-boost-flame-hide-finish-2

    #충돌 판정
    execute if score #physics-mode kartcollisiontime matches 0 as @e[tag=kartmobil,tag=!no-collision,type=#kartmobil:kartmobil] at @s if entity @n[tag=kartmobil,tag=!no-collision,distance=0.01..1.6,type=#kartmobil:kartmobil] run function kartmain:collision-old/onkartcollision
    execute if score #physics-mode kartcollisiontime matches 1 unless score #kart-hitbox-mode kartcollisiontime matches 1 as @e[tag=kartmobil,tag=!no-collision,type=#kartmobil:kartmobil] at @s if entity @n[tag=kartmobil,tag=!no-collision,distance=0.01..1.6,type=#kartmobil:kartmobil] run function kartmain:collision/onkartcollision
    execute if score #physics-mode kartcollisiontime matches 1 if score #kart-hitbox-mode kartcollisiontime matches 1 as @e[tag=kartmobil,tag=!no-collision,type=#kartmobil:kartmobil] at @s if entity @n[tag=kartmobil,tag=!no-collision,distance=0.01..4,type=#kartmobil:kartmobil] run function kartmain:collision/onkartcollision
    tag @e[tag=kart-collision-executed,type=#kartmobil:kartmobil] remove kart-collision-executed
    
    #갓겜
    execute as @e[tag=kartmobil,tag=mad-crash-spin,tag=!no-collision,type=#kartmobil:kartmobil] at @s run function kartmain:mad-crash-spin

    #카트 메인 함수
    tag @a[tag=kartdeceleration,predicate=kartmobil:ifride] remove kartdeceleration
    execute as @e[tag=kartmobil,type=#kartmobil:kartmobil] at @s run function kartmobil:main
    bossbar set minecraft:deceleration players @a[tag=kartdeceleration,predicate=kartmobil:ifride]

    #드래프트
    execute as @e[tag=kartmobil,tag=!no-draft,type=#kartmobil:kartmobil] at @s run function kartmain:draft/main

    #차량 소환
    execute as @a if items entity @s weapon.offhand iron_nugget[minecraft:custom_data~{kartmobil:1}] at @s run function kartmain:summon/summonkart

#rotafix
#execute as @e[tag=kartdirection,type=item_display] store success entity @s OnGround byte 1 store success score @s rotafix unless score @s rotafix matches 1