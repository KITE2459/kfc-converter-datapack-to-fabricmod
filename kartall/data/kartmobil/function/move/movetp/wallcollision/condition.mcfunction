execute unless block ~ ~ ~ #kartmobil:ignoreblock run return 0
execute unless block ~ ~1.5 ~ #kartmobil:ignoreblock run return 0

#특대형
execute if entity @s[tag=kart-large-size] unless block ^ ^1.5 ^1.125 #kartmobil:ignoreblock run return 1
execute if entity @s[tag=kart-large-size] if block ^ ^ ^1.125 #kartmobil:stones run return 1

#대형
execute if entity @s[tag=!kart-small-size,tag=!kart-mid-size,tag=!kart-large-size] unless block ^ ^ ^0.8 #kartmobil:ignoreblock unless block ^ ^2.5 ^0.8 #kartmobil:ignoreblock run return 1
execute if entity @s[tag=!kart-small-size,tag=!kart-mid-size,tag=!kart-large-size] unless block ^ ^1.5 ^1 #kartmobil:ignoreblock run return 1
execute if entity @s[tag=!kart-small-size,tag=!kart-mid-size,tag=!kart-large-size] if block ^ ^ ^1 #kartmobil:stones run return 1

#중형
execute if entity @s[tag=kart-mid-size] unless block ^ ^ ^0.8 #kartmobil:ignoreblock unless block ^ ^2.5 ^0.8 #kartmobil:ignoreblock run return 1
execute if entity @s[tag=kart-mid-size] unless block ^ ^1.5 ^0.8 #kartmobil:ignoreblock run return 1
execute if entity @s[tag=kart-mid-size] if block ^ ^ ^0.8 #kartmobil:stones run return 1

#소형
execute if entity @s[tag=kart-small-size] unless block ^ ^ ^0.8 #kartmobil:ignoreblock unless block ^ ^2.5 ^0.8 #kartmobil:ignoreblock run return 1
execute if entity @s[tag=kart-small-size] unless block ^ ^1.5 ^0.6 #kartmobil:ignoreblock run return 1
execute if entity @s[tag=kart-small-size] if block ^ ^ ^0.6 #kartmobil:stones run return 1

return 0

#1.2
#1.6
#2
#2.25