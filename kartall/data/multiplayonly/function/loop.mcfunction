
#멀티플레이시 방장 감지


# #not(not a and not b)
# scoreboard players set #kicknonbangjang multi-main 0
# execute unless score #time multi-main matches 61.. unless score #game multi-main matches 0.. run scoreboard players set #kicknonbangjang multi-main 1
# execute if entity @a[x=-31,y=-1,z=154,dx=28, dy=6, dz=9,tag=!bangjang] if score #kicknonbangjang multi-main matches 1 as @a[x=-31,y=-1,z=154,dx=28, dy=6, dz=9,tag=!bangjang] run function multiplayonly:kicknonbangjang

#싱글플레이 막기
execute if entity @a[x=-124,y=4,z=211,dx=11, dy=5, dz=9] as @a[x=-124,y=4,z=211,dx=11, dy=5, dz=9] run function multiplayonly:kicksingleplayer

#컷씬 막기
execute if entity @a[x=199,y=4,z=-415,dx=-2,dy=3,dz=13] as @a[x=199,y=4,z=-415,dx=-2,dy=3,dz=13] run function multiplayonly:kickcutscene