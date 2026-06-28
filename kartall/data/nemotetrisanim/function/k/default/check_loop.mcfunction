# Nemotetrisanim created via BDEngine

execute as @e[tag=nemotetrisanim_root,type=block_display] if entity @s[tag=animation_loop] at @s run function nemotetrisanim:k/default/keyframe_0
execute as @e[tag=nemotetrisanim_root,type=block_display] unless entity @s[tag=animation_loop] at @s run function nemotetrisanim:_/stop_anim