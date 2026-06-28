# NemoMediaSpin created via BDEngine

execute as @e[tag=nemomediaspin_root,type=block_display] if entity @s[tag=animation_loop] at @s run function nemomediaspin:k/default/keyframe_0
execute as @e[tag=nemomediaspin_root,type=block_display] unless entity @s[tag=animation_loop] at @s run function nemomediaspin:_/stop_anim