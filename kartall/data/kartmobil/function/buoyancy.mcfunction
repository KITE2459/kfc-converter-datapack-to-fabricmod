function kartmobil:move/movetp/landing-tag-remove

#부력 물리
#물에 있을 때 상승력 추가
scoreboard players remove @s kartmovey 250
execute positioned ~ ~0.5 ~ if function kartmobil:is-water run scoreboard players remove @s kartmovey 125

#점성 저항
execute positioned ~ ~1 ~ unless function kartmobil:is-water if score @s kartmovey matches ..-300 store result storage kartmain temp float 0.9 run scoreboard players get @s kartmovey
execute positioned ~ ~1 ~ unless function kartmobil:is-water if score @s kartmovey matches ..-500 store result storage kartmain temp float 0.9 run data get storage kartmain temp

execute positioned ~ ~1 ~ unless function kartmobil:is-water if score @s kartmovey matches ..-300 store result score @s kartmovey run data get storage kartmain temp

#선형 저항
execute if score @s kartmovey matches 900.. store result storage kartmain temp float 0.9 run scoreboard players get @s kartmovey
execute if score @s kartmovey matches 1100.. store result storage kartmain temp float 0.9 run data get storage kartmain temp
execute if score @s kartmovey matches 1300.. store result storage kartmain temp float 0.9 run data get storage kartmain temp
execute if score @s kartmovey matches 1500.. store result storage kartmain temp float 0.9 run data get storage kartmain temp

execute if score @s kartmovey matches 900.. store result score @s kartmovey run data get storage kartmain temp

execute if score @s kartmovey matches 0..249 run scoreboard players add @s kartmovey 10

#tellraw @p {text:"kartmovey: ",color:"gold",extra:[{score:{name:"@s",objective:kartmovey}}]}