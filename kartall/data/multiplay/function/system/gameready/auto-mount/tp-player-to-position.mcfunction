execute as @a[tag=kart-multi-player] at @s run function multiplay:system/gameready/auto-mount/tp-player-to-marker
kill @e[tag=auto-mount-marker,type=marker]
execute as @a[tag=kart-multi-player] run function kartmain:summon/swaphand

#업앤다운 엔진고정
execute if score #map trackselect-map-id matches 1001.. at @a[tag=kart-multi-player] run summon minecraft:item_display ~ ~ ~ {Tags:["updown-engine-fix","kart-bypass-tag"],data:{engine-fix:2}}