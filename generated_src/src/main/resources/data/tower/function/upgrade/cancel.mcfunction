scoreboard players operation #temp player.id = @s player.id
execute as @e[tag=tower.core] if score @s player.id = #temp player.id run function tower:upgrade/reset
tellraw @s [{text:"업그레이드가 취소되었습니다.",color:"red"}]