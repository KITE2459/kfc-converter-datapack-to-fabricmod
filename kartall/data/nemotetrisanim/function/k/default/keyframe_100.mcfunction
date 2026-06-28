# Nemotetrisanim created via BDEngine
data merge entity @e[type=item_display,tag=nemotetrisanim_8,distance=..1,limit=1,sort=nearest] {transformation:[32f,0f,0f,-7.9375f,0f,16f,0f,26f,0f,0f,16f,-4f,0f,0f,0f,1f],interpolation_duration:2,start_interpolation:0}
data merge entity @e[type=item_display,tag=nemotetrisanim_9,distance=..1,limit=1,sort=nearest] {transformation:[16f,0f,0f,-3.9375f,0f,32f,0f,18f,0f,0f,16f,-4f,0f,0f,0f,1f],interpolation_duration:2,start_interpolation:0}

#terrain
clone ~-1 ~-12 ~-1 ~-72 ~-14 ~-8 ~-72 ~1 ~-8 replace

schedule function nemotetrisanim:k/default/check_pause_100 0.1s
