tag @s add bangjang-candidate-temp

execute on attacker if entity @s[tag=bangjang] as @a[tag=multi-hub-player,tag=bangjang] at @s run playsound minecraft:entity.experience_orb.pickup master @a ~ ~ ~ 2 0.5
execute on attacker if entity @s[tag=bangjang] run tellraw @a [{"selector":"@p[tag=bangjang-candidate-temp]","color":"yellow"},{translate:" 라이더가 ","color":"aqua","bold":true},{translate:"방장","color":"green","bold":true},{translate:"을 넘겨받았습니다.","color":"aqua","bold":true}]



execute on attacker if entity @s[tag=bangjang] run tag @p[tag=bangjang-candidate-temp] add bangjang
execute on attacker if entity @s[tag=bangjang] run tag @s remove bangjang

tag @s remove bangjang-candidate-temp

function bangjang:bossbar