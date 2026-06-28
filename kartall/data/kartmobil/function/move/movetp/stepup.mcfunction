execute if entity @s[tag=kart-direction-fixed-by-pad] run return 1

#execute positioned as @s positioned ^ ^ ^0.75 if function kartmobil:move/movetp/stepup-condition positioned ^ ^1 ^-0.75 run function kartmobil:move/movetp/stepup-tp
#execute positioned as @s rotated ~45 ~ positioned ^ ^ ^0.75 if function kartmobil:move/movetp/stepup-condition positioned ^ ^1 ^-0.75 run function kartmobil:move/movetp/stepup-tp
#execute positioned as @s rotated ~-45 ~ positioned ^ ^ ^0.75 if function kartmobil:move/movetp/stepup-condition positioned ^ ^1 ^-0.75 run function kartmobil:move/movetp/stepup-tp

execute positioned as @s unless block ^ ^ ^0.75 #kartmobil:stones unless block ^ ^ ^0.75 #kartmobil:ignoreblock if block ^ ^1 ^0.75 #kartmobil:ignoreblock if block ^ ^2 ^0.75 #kartmobil:ignoreblock positioned ~ ~1 ~ run function kartmobil:move/movetp/stepup-tp
execute positioned as @s rotated ~45 ~ unless block ^ ^ ^0.75 #kartmobil:stones unless block ^ ^ ^0.75 #kartmobil:ignoreblock if block ^ ^1 ^0.75 #kartmobil:ignoreblock if block ^ ^2 ^0.75 #kartmobil:ignoreblock positioned ~ ~1 ~ run function kartmobil:move/movetp/stepup-tp
execute positioned as @s rotated ~-45 ~ unless block ^ ^ ^0.75 #kartmobil:stones unless block ^ ^ ^0.75 #kartmobil:ignoreblock if block ^ ^1 ^0.75 #kartmobil:ignoreblock if block ^ ^2 ^0.75 #kartmobil:ignoreblock positioned ~ ~1 ~ run function kartmobil:move/movetp/stepup-tp

#특수한 상황에서 바닥에 끼임 방지
execute positioned as @s unless function kartmobil:move/movetp/condition positioned ~ ~1 ~ if function kartmobil:move/movetp/condition run function kartmobil:move/movetp/stepup-tp