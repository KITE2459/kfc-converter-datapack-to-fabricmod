execute on passengers as @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kartmodel,tag=!kart-boost-flame,tag=!drift-effect,tag=rkey-hidden] run function kartmobil:rkey-ghost/show-as-model

execute unless entity @s[tag=karthideplayer] run effect clear @p[tag=kartpassenger] minecraft:invisibility