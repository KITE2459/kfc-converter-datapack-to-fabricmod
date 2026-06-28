execute as @p run function bgm-room:manage-bgm/stopbgm
execute as @p run function multiplayroom:play
execute unless entity @p[x=-4,y=-2,z=154,dx=-54,dy=13,dz=22] unless score #game multi-main matches 1 run function modscript:startset

tp @p -18 -1 165 180 0
