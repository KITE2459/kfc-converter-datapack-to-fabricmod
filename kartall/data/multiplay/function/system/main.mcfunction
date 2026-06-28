#카트에서 내린 플레이어 강퇴
execute unless score #time multi-main matches 0..20 as @a[tag=kart-multi-player,predicate=!kartmobil:ifride] at @s run function multiplay:system/game/retire

    #플레이어 준비/플레이어 메인 함수
    execute unless score #map trackselect-map-id matches 1001.. as @a[tag=kart-multi-player] unless score #time multi-main matches 0..100 at @s run function multiplay:system/player
    execute if score #map trackselect-map-id matches 1001.. as @a[tag=kart-multi-player] unless score #time multi-main matches 0..100 at @s run function multiplay:system/player-updown

    #랩 처리 및 완주
    execute as @a[tag=kart-multi-player,tag=check-pass-last] on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers if entity @s[type=player] run function multiplay:system/game/passline
    execute unless score #retire-time multi-main matches 200.. unless score #map trackselect-map-id matches 1001.. as @a[tag=kart-multi-player] if score @s trackselect-lap >= #max-lap trackselect-lap on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers at @s[type=player] run function multiplay:system/game/complete-race/main
    execute unless score #retire-time multi-main matches 600.. if score #map trackselect-map-id matches 1001.. as @a[tag=kart-multi-player] if score @s trackselect-lap >= #max-lap trackselect-lap on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers at @s[type=player] run function multiplay:system/game/complete-race/main

    #초기화 함수 실행
    execute if score #time multi-main matches 1 run function multiplay:system/init

    #플레이어 시작선 배치 + 카운트다운 후 시작
    execute unless score #time multi-main matches -1 run scoreboard players add #time multi-main 1
    execute if score #time multi-main matches 0..100 run function multiplay:system/countdown

    #사람이 없으면 게임 강종
    execute unless entity @a[tag=kart-multi-player] run function multiplay:system/game/gameend

    #리타이어 카운트다운(시간을 -1로 만듬), 게임 종료
    execute unless score #map trackselect-map-id matches 1001.. if score #time multi-main matches -1 run function multiplay:system/game/retirecount
    execute if score #map trackselect-map-id matches 1001.. if score #time multi-main matches -1 run function multiplay:system/game/retirecount-updown

    #레버 락 loop on
    execute unless score #is-official-multi multi-main matches 1 run function multiplay:system/lever/lock-loop