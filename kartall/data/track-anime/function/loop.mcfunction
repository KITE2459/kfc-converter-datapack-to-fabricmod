
#자동 리셋
execute unless entity @a[predicate=kartmobil:ifride] unless score iswork timermain matches 1 if entity @e[tag=spinterr0,type=item_display] run scoreboard players add #removetimer nemo-spinning-terrain 1
execute if score #removetimer nemo-spinning-terrain matches 100.. run function spinningterrain:reset

#자동 리셋
execute unless entity @a[predicate=kartmobil:ifride] unless score iswork timermain matches 1 if score #media-is-playing track-anime matches 1 if entity @e[tag=nemomediaspin_root,type=block_display] run scoreboard players add #media-remove track-anime 1

execute if score #media-remove track-anime matches 100.. run function nemotetrisanim:_/delete
execute if score #media-remove track-anime matches 100.. run function nemomediaspin:_/delete
