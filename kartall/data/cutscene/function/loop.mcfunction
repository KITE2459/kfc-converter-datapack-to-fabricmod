
#만약 컷씬 보는사람 있다면
execute as @a at @s if score @s cutscene matches 1.. unless score ending cutscene matches 1 run function cutscene:cutscene/opening

execute as @a at @s if score @s cutscene matches 1.. if score ending cutscene matches 1 run function cutscene:cutscene/ending

#엔딩크레딧
execute if score play endcredit matches 1.. run function endcredit:ani