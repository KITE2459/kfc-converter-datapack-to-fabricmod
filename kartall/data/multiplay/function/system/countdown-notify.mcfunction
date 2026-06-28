#tellraw @a[tag=kite-played] [{translate:"⛨",color:yellow},{translate:" 유저는 최소한의 몸싸움을 원합니다",color:aqua}]
#tellraw @a[tag=kite-played] [{translate:"🗡",color:red},{translate:" 유저는 적극적인 몸싸움을 원합니다",color:aqua}]
#tellraw @a[tag=kite-played] [{translate:"표식 변경은 /trigger bump-allow 입력",color:aqua}]

#tellraw @a {"entity":"@n[tag=trackselect-multi-marker,type=marker]","nbt":"data.track.text","interpret":true}
#tellraw @a {"entity":"@n[tag=trackselect-multi-marker,type=marker]","nbt":"data.track.difficulty","interpret":true}
#tellraw @a {"entity":"@n[tag=trackselect-multi-marker,type=marker]","nbt":"data.track.creator","interpret":true}

execute as @n[tag=trackselect-multi-marker,type=marker] at @s run function timerpack:show-track-on-sidebar/main

tellraw @a {translate:"----적용된 모드----","color":"yellow"}
execute unless score team-battle multi-main matches 1 unless score no-collision multi-main matches 1 unless score inf-boost multi-main matches 1 unless score no-draft multi-main matches 1 unless score no-pull-accel multi-main matches 1 unless score drag-accel multi-main matches 1 unless score mad-crash multi-main matches 1 unless score wall-crash multi-main matches 1 run tellraw @a {translate:"없음","color":"aqua"}
execute if score team-battle multi-main matches 1 run tellraw @a {translate:"팀전","color":"blue"}
execute if score no-collision multi-main matches 1 run tellraw @a {translate:"고스트 모드","color":"aqua"}
execute if score inf-boost multi-main matches 1 run tellraw @a {translate:"무한 부스터 모드","color":"aqua"}
execute if score no-draft multi-main matches 1 run tellraw @a {translate:"드래프트 끄기","color":"aqua"}
execute if score no-pull-accel multi-main matches 1 run tellraw @a {translate:"견인 가속 끄기","color":"aqua"}
execute if score drag-accel multi-main matches 1 run tellraw @a {translate:"톡톡이 모드","color":"aqua"}
execute if score mad-crash multi-main matches 1 run tellraw @a {translate:"갓겜 모드","color":"aqua"}
execute if score wall-crash multi-main matches 1 run tellraw @a {translate:"벽 충돌 페널티","color":"aqua"}