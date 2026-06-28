
    #execute positioned as @s if block ~ ~-0.5 ~ #kartmobil:jumpboostpad run function kartmobil:move/movetp/jump-and-boost/pad-detect
    #execute positioned as @s if block ~ ~-1.5 ~ #kartmobil:jumpboostpad run function kartmobil:move/movetp/jump-and-boost/pad-detect

    #내려가기 / 올라가기 + 대각선 벽통과 막기
    execute positioned as @s align xyz positioned ~0.5 ~ ~0.5 rotated ~45 0 positioned ^ ^ ^0.6 if function kartmobil:move/movetp/wallcollision/hitbox/condition positioned ^ ^ ^-0.6 rotated ~-90 0 positioned ^ ^ ^0.6 if function kartmobil:move/movetp/wallcollision/hitbox/condition run return run function kartmobil:move/movetp/wallcollision/diagonal-collision-stop
    execute if block ~ -64 ~ honey_block run function kartmobil:move/movetp/stepdown
    function kartmobil:move/movetp/stepup

#이진tp + 점프/부스터패드
execute positioned as @s if block ~ ~-0.5 ~ #kartmobil:jumpboostpad run function kartmobil:move/movetp/jump-and-boost/pad-detect
execute positioned as @s if block ~ ~-1.5 ~ #kartmobil:jumpboostpad run function kartmobil:move/movetp/jump-and-boost/pad-detect

execute if score @s kartmove-remain matches 8000.. positioned as @s positioned ^ ^ ^0.4 if function kartmobil:move/movetp/condition run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score @s kartmove-remain matches 8000.. run scoreboard players remove @s kartmove-remain 8000

execute positioned as @s if block ~ ~-0.5 ~ #kartmobil:jumpboostpad run function kartmobil:move/movetp/jump-and-boost/pad-detect
execute positioned as @s if block ~ ~-1.5 ~ #kartmobil:jumpboostpad run function kartmobil:move/movetp/jump-and-boost/pad-detect

#0.2 0.1 0.05 tp
execute positioned as @s run function kartmobil:move/movetp/tp-tree/start-tree

    #내려가기 / 올라가기 + 대각선 벽통과 막기
    execute positioned as @s align xyz positioned ~0.5 ~ ~0.5 rotated ~45 0 positioned ^ ^ ^0.6 if function kartmobil:move/movetp/wallcollision/hitbox/condition positioned ^ ^ ^-0.6 rotated ~-90 0 positioned ^ ^ ^0.6 if function kartmobil:move/movetp/wallcollision/hitbox/condition run return run function kartmobil:move/movetp/wallcollision/diagonal-collision-stop
    execute if block ~ -64 ~ honey_block run function kartmobil:move/movetp/stepdown
    function kartmobil:move/movetp/stepup

#벽피하기
execute if score #wall-hitbox-mode kartcollisiontime matches 0 positioned as @s run function kartmobil:move/movetp/wallcollision/real-main
execute if score #wall-hitbox-mode kartcollisiontime matches 1.. positioned as @s run function kartmobil:move/movetp/wallcollision/hitbox/main

#재귀
execute if score @s kartmove-remain matches 1000.. positioned as @s run function kartmobil:move/movetp/moveforward