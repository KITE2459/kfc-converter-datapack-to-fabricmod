tag @s remove kart-loop-flipped-late2
tag @s remove kart-loop-flipped-late

function kartmobil:rkey-ghost/show
execute on passengers if entity @s[tag=kartmodelsaddle] on passengers run data modify entity @s teleport_duration set value 1