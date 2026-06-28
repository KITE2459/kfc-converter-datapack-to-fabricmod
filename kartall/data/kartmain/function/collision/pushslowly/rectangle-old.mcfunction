#playsound minecraft:entity.zombie.attack_wooden_door neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.1 2

#서로서로 밀어내기(겹침 방지) - 8회 반복
function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.1 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

#여기서부턴 조금 많이 밀기
function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.2 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.2 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.2 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.2 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

function kartmain:collision/rectangle-hitbox/main
execute if score #crashed kartcollisiontime matches 0 run return 1
execute if score #crashed kartcollisiontime matches 1 at @s facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.2 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute if score #crashed kartcollisiontime matches 1 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] at @s facing entity @e[tag=carA,limit=1,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision/pushslowly/condition positioned ^ ^ ^0.2 if block ^ ^ ^0.6 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
