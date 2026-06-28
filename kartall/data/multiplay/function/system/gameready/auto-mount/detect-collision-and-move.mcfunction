tag @s add temp-asdf-this

tag @e[tag=kart-multi-collision] remove kart-multi-collision
execute at @s if entity @e[tag=auto-mount-marker,type=marker,tag=!temp-asdf-this,distance=..1.5] run tag @s add kart-multi-collision

#이동
execute at @s[tag=kart-multi-collision] if score auto-mount-left multi-main matches 0 unless function multiplay:system/gameready/auto-mount/if-face-wall run tp @s ^1.75 ^ ^
execute at @s[tag=kart-multi-collision] if score auto-mount-left multi-main matches 1 unless function multiplay:system/gameready/auto-mount/if-face-wall run tp @s ^-1.75 ^ ^

#이동 후 다시 감지
tag @s remove kart-multi-collision
execute at @s if entity @e[tag=auto-mount-marker,type=marker,tag=!temp-asdf-this,distance=..1.5] run tag @s add kart-multi-collision

#겹친 엔티티는 뒤로 이동
execute at @s[tag=kart-multi-collision] if score auto-mount-left multi-main matches 0 positioned ^-1.85 ^ ^ if entity @e[tag=auto-mount-marker,type=marker,tag=!temp-asdf-this,distance=..1.5] positioned ^1.85 ^ ^ if function multiplay:system/gameready/auto-mount/if-face-wall run function multiplay:system/gameready/auto-mount/face-wall
execute at @s[tag=kart-multi-collision] if score auto-mount-left multi-main matches 1 positioned ^1.85 ^ ^ if entity @e[tag=auto-mount-marker,type=marker,tag=!temp-asdf-this,distance=..1.5] positioned ^-1.85 ^ ^ if function multiplay:system/gameready/auto-mount/if-face-wall run function multiplay:system/gameready/auto-mount/face-wall

#한 플레이어를 배치 완료할때마다, 변수가 1과 0의 상태를 왔다갔다한다.
execute at @s[tag=!kart-multi-collision] store result score auto-mount-left multi-main if score auto-mount-left multi-main matches 0
execute at @s[tag=kart-multi-collision] run function multiplay:system/gameready/auto-mount/detect-collision-and-move

#선에서 벗어나면 배치 안 하기
execute at @s unless block ~ ~-1.75 ~ magma_block run kill @s

tag @s remove temp-asdf-this
tag @s remove face-wall