# Nemotetrisanim created via BDEngine
data merge entity @e[type=item_display,tag=nemotetrisanim_2,distance=..1,limit=1,sort=nearest] {transformation:[32f,0f,0f,-31.9375f,0f,16f,0f,10f,0f,0f,16f,-4f,0f,0f,0f,1f],interpolation_duration:2,start_interpolation:0}

#terrain
clone ~-1 ~-27 ~-1 ~-72 ~-29 ~-8 ~-72 ~1 ~-8 replace

schedule function nemotetrisanim:k/default/check_pause_200 0.1s
