#카트 자동 회수
execute if function kartmain:break-kart/break run return 0

#태그 주기
execute on passengers if entity @s[tag=kartsaddle,predicate=kartmobil:ifpassenger,type=#kartmobil:kartsaddle] on passengers run tag @s[predicate=kartmobil:ifride,type=player] add kartpassenger

#이전 틱에 계산한 바닥판정 복사
scoreboard players operation #floor-distance floor-distance = @s floor-distance

#소리 듣기
function kartmobil:add-kart-listener

#데이터캐리어를 플레이어 시선 전달자로 사용
execute rotated as @p[tag=kartpassenger] on passengers run rotate @s[tag=kartdatacarrier,type=item_display] ~ 0

#플레이어 숨기기
execute if entity @s[tag=karthideplayer] run effect give @p[tag=kartpassenger] invisibility 1 1 true

    #UI - 수치 1000은 1m/s임
    scoreboard players operation #kartspeed kartmove = @s kartmove
    scoreboard players operation #kartspeed kartmove /= #kart278 kartmain

    #조작
    function kartmobil:control/main

#라이트 깜빡깜빡
execute if entity @s[tag=kart-w-press] on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kart-light] run function kartmobil:bright-blocks/light-on
execute if entity @s[tag=kart-w-release] on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kart-light] run function kartmobil:bright-blocks/light-off

#PRO엔진 / 커스텀 모델링 후처리
execute if entity @s[tag=pro-transform-need] run function kartmobil:pro-always-transform
execute if entity @s[tag=kart-has-custom-model] at @s run function kartmobil:custom-model-async-summon/main

#디버그(히트박스크기)
execute if entity @p[tag=kartpassenger,tag=kart-debug] positioned as @s on passengers if entity @s[tag=kartmodelsaddle] rotated as @s on vehicle run function kartmain:collision/rectangle-hitbox/debug/show-size

#사운드 및 이동 / 모드에 정지 데이터 전달
function kartmobil:sound-and-fov/sound
function kartmobil:s2c-value/s2c-stop-data
execute unless score @s kart-engine matches 1004 if score @s kartmove matches 1.. run function kartmobil:move/move
execute unless score @s kart-engine matches 1004 if score @s kartmove matches ..-1 run scoreboard players set @s kartmove 0
execute unless score @s kart-engine matches 1004 if score @s kartmove matches 0 on passengers if entity @s[tag=kart-old-velocity] run scoreboard players set @s kartmove 0
execute unless score @s kart-engine matches 1004 if score @s kartmove matches 0 on passengers if entity @s[tag=kart-old-velocity] run scoreboard players set @s kartmove-remain 0
execute if score @s kart-engine matches 1004 run function kartmobil:boat-engine/main

    #중력
    execute if block ~ ~-0.1 ~ #kartmobil:ignoreblock run scoreboard players add @s kartmovey 250

    execute if block ~ ~-0.1 ~ #kartmobil:ignoreblock if entity @s[tag=kart-low-gravity] if score @s kartmovey matches 900.. run tag @s add kart-low-gravity-activated
    execute if block ~ ~-0.1 ~ #kartmobil:ignoreblock if entity @s[tag=kart-low-gravity-activated] run scoreboard players operation @s kartmovey = @s kart-low-gravity
    execute if block ~ ~-0.1 ~ #kartmobil:ignoreblock if entity @s[tag=kart-low-gravity-activated] run particle minecraft:trial_spawner_detection_ominous ~ ~ ~ .5 .5 .5 0 1

    #부력
    execute if entity @s[tag=allow-buoyancy] if function kartmobil:is-water run function kartmobil:buoyancy

    #낙하와 착지, 점프
    scoreboard players operation #movetp-y kartmain = @s kartmovey
    execute if score @s kartmovey matches 1.. unless block ~ ~-0.1 ~ #kartmobil:ignoreblock run function kartmobil:move/movetp/landing

    execute unless score @s kartmovey matches -249..249 on passengers if entity @s[tag=kartmodelsaddle,type=item_display] at @s run function kartmobil:model-pitch-movey/main
    execute if score #movetp-y kartmain matches 200.. at @s run function kartmobil:move/movetp/moveupdown/movedown
    execute if score #movetp-y kartmain matches ..-200 at @s run function kartmobil:move/movetp/moveupdown/moveup

#r키 고스팅
execute if score @s kartcollisiontime matches 10.. run function kartmobil:rkey-ghost/main

#충돌 쿨타임 및 뭉개기 카운트
scoreboard players remove @s[scores={kartcollisiontime=1..}] kartcollisiontime 1
scoreboard players remove @s[scores={kartcollisiontime=..0,kartweirdcollisioncount=1..}] kartweirdcollisioncount 1

#모델 회전 보조
function kartmobil:modeldir-support/modeldir-support-vanilla
execute rotated as @p[tag=kartpassenger] on passengers run rotate @s[tag=kartdatacarrier,type=item_display] ~ 0

#크리티컬 섹션: 틱 내에서의 명령어 실행 시점이 아주 중요함. 마음대로 순서를 바꾸면 안 됨 
    #타코미터 표시
    function kartmobil:tachometer/main

    #바닥과의 거리 감지
    scoreboard players set @s floor-distance 0
    execute if block ~ ~-0.1 ~ #kartmobil:ignoreblock if entity @s[tag=!allow-buoyancy] align xyz positioned ~0.5 ~ ~0.5 run function kartmobil:detect-floor-distance
    execute if block ~ ~-0.1 ~ #kartmobil:ignoreblock if entity @s[tag=allow-buoyancy] align xyz positioned ~0.5 ~ ~0.5 run function kartmobil:detect-floor-distance-water
    scoreboard players operation #floor-distance floor-distance = @s floor-distance
    
    #공중에 떴을 때 드리프트 불 없애기
    execute if entity @s[tag=kart-drifting,tag=!kart-direction-fixed-by-pad] unless score #floor-distance floor-distance matches ..1 if entity @s[tag=show-drift-effect] run function kartmobil:move/steering/drift/control-drift-effect/hide
    execute if entity @s[tag=kart-drifting,tag=!kart-direction-fixed-by-pad] if score #floor-distance floor-distance matches ..1 if entity @s[tag=!show-drift-effect] run function kartmobil:move/steering/drift/control-drift-effect/show

#태그 제거
tag @a[tag=kartpassenger] remove kartpassenger
tag @a[tag=kart-listener] remove kart-listener

