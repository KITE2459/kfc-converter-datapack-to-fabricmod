#playsound minecraft:entity.zombie.attack_wooden_door neutral @a[tag=kart-listener-collision] ~ ~ ~ 0.2 2

#내 차가 벽충돌을 당했으면
execute at @n[tag=carA,type=#kartmobil:kartmobil] facing entity @n[tag=carB,type=#kartmobil:kartmobil] feet rotated ~180 0 align xyz positioned ~0.5 ~ ~0.5 unless block ^ ^ ^1 #kartmobil:ignoreblock run scoreboard players add @n[tag=carB,type=#kartmobil:kartmobil] kartweirdcollisioncount 1
execute at @n[tag=carB,type=#kartmobil:kartmobil] facing entity @n[tag=carA,type=#kartmobil:kartmobil] feet rotated ~180 0 align xyz positioned ~0.5 ~ ~0.5 unless block ^ ^ ^1 #kartmobil:ignoreblock run scoreboard players add @n[tag=carA,type=#kartmobil:kartmobil] kartweirdcollisioncount 1

#서로서로 밀어내기(겹침 방지)
execute as @s at @s if entity @e[tag=carB,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carB,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute as @e[tag=carB,type=#kartmobil:kartmobil] at @s if entity @e[tag=carA,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carA,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

execute as @s at @s if entity @e[tag=carB,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carB,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute as @e[tag=carB,type=#kartmobil:kartmobil] at @s if entity @e[tag=carA,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carA,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

execute as @s at @s if entity @e[tag=carB,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carB,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute as @e[tag=carB,type=#kartmobil:kartmobil] at @s if entity @e[tag=carA,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carA] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

execute as @s at @s if entity @e[tag=carB,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carB,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute as @e[tag=carB,type=#kartmobil:kartmobil] at @s if entity @e[tag=carA,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carA,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp

execute as @s at @s if entity @e[tag=carB,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carB,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
execute as @e[tag=carB,type=#kartmobil:kartmobil] at @s if entity @e[tag=carA,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carA,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.2 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp


#충분히 뗴어놓고 나서도 더 밀어내기
#execute as @s at @s unless entity @e[tag=carB,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carB,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.12 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
#execute as @e[tag=carB,type=#kartmobil:kartmobil] at @s unless entity @e[tag=carA,type=#kartmobil:kartmobil,distance=..1.5] facing entity @e[tag=carA,type=#kartmobil:kartmobil] feet rotated ~180 0 if function kartmain:collision-old/pushslowly-condition positioned ^ ^ ^0.12 if block ^ ^ ^0.4 #kartmobil:ignoreblock run function kartmobil:move/movetp/tp-bug-fix/tp
