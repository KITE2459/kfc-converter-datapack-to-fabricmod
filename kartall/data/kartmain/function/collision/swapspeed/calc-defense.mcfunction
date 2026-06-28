#defense
scoreboard players operation #defense-a kartcollisiontime = @e[tag=carA,limit=1,type=#kartmobil:kartmobil] kartdefense
scoreboard players operation #defense-b kartcollisiontime = @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartdefense

scoreboard players operation #magnitude-relative-a kartcollisiontime = #kart-defense-mul kartcollisiontime
scoreboard players operation #magnitude-relative-b kartcollisiontime = #kart-defense-mul kartcollisiontime

scoreboard players operation #magnitude-relative-a kartcollisiontime -= #defense-a kartcollisiontime
scoreboard players operation #magnitude-relative-b kartcollisiontime -= #defense-b kartcollisiontime

scoreboard players operation #magnitude-relative-a kartcollisiontime *= #magnitude-relative kartcollisiontime
scoreboard players operation #magnitude-relative-b kartcollisiontime *= #magnitude-relative kartcollisiontime

scoreboard players operation #magnitude-relative-a kartcollisiontime /= #kart-defense-mul kartcollisiontime
scoreboard players operation #magnitude-relative-b kartcollisiontime /= #kart-defense-mul kartcollisiontime

#m1 + m2
#scoreboard players set #mass-a kartcollisiontime 1000
#scoreboard players set #mass-b kartcollisiontime 1000

#scoreboard players operation #mass-a kartcollisiontime += @e[tag=carA,limit=1,type=#kartmobil:kartmobil] kart-weight
#scoreboard players operation #mass-b kartcollisiontime += @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kart-weight

#scoreboard players operation #ma+mb kartcollisiontime = #mass-a kartcollisiontime
#scoreboard players operation #ma+mb kartcollisiontime += #mass-b kartcollisiontime