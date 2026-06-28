execute facing entity @s feet store result score #kart-pushing-wall kartcollisiontime run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/wall-condition
execute if score #kart-pushing-wall kartcollisiontime matches 1 positioned as @s positioned ^ ^ ^-0.15 unless function kartmobil:move/movetp/wallcollision/hitbox/condition run function kartmobil:move/movetp/tp-bug-fix/tp

#건조된낙엽분말
#function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/binary-tp/start-tree