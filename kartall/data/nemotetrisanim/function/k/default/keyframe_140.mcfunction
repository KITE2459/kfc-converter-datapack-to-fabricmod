# Nemotetrisanim created via BDEngine
data merge entity @e[type=item_display,tag=nemotetrisanim_10,distance=..1,limit=1,sort=nearest] {transformation:[32f,0f,0f,-23.9375f,0f,16f,0f,10f,0f,0f,16f,-4f,0f,0f,0f,1f],interpolation_duration:2,start_interpolation:0}
data merge entity @e[type=item_display,tag=nemotetrisanim_11,distance=..1,limit=1,sort=nearest] {transformation:[0f,-16f,0f,-23.9375f,32f,0f,0f,10f,0f,0f,16f,-4f,0f,0f,0f,1f],interpolation_duration:2,start_interpolation:0}

#terrain
clone ~-1 ~-18 ~-1 ~-72 ~-20 ~-8 ~-72 ~1 ~-8 replace

schedule function nemotetrisanim:k/default/check_pause_140 0.1s
