# 업그레이드 UI 출력
# 판매 가격 계산
execute store result score #sell_cost tower run data get storage tower temp.price
execute store result score #upgrade_cost tower run data get storage tower temp2.price
execute store result score #level tower run data get storage tower temp.level
scoreboard players remove #level tower 1
scoreboard players operation #upgrade_cost tower *= #level tower
scoreboard players operation #sell_cost tower += #upgrade_cost tower
scoreboard players operation #sell_cost tower /= #2 tower

tellraw @s {text:"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"}
execute unless data storage tower temp2 if function tower:upgrade/ui_maxed run return 0
tellraw @s [{nbt:temp.name,storage:tower,interpret:true},{text:" -",bold:false,color:white},{text:" Level ",bold:false,color:aqua},{nbt:temp.level,storage:tower,color:aqua,bold:false},{text:"\n",bold:false,color:aqua}]
tellraw @s [{text:"[공격력] ",bold:false,color:red},{"nbt":"temp.attack","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.attack","storage":"tower",bold:false,color:green}]
tellraw @s [{text:"[공격 속도] ",bold:false,color:aqua},{"nbt":"temp.info.attack_speed","storage":"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.info.attack_speed","storage":"tower",bold:false,color:green}]
tellraw @s [{text:"[사거리] ",bold:false,color:blue},{"nbt":"temp.range",storage:"tower",bold:false,color:yellow},{text:" -> ",bold:false,color:white},{"nbt":"temp2.range","storage":"tower",bold:false,color:green}]
execute if data storage tower temp.Bullet.attribute run function tower:upgrade/ui_attribute
function tower:upgrade/ui_target_mode
tellraw @s [{text:"\n업그레이드 비용: ",bold:false,color:gold},{"nbt":"temp2.price","storage":"tower",bold:false,color:yellow},{text:"\n판매 가격: ",bold:false,color:gold},{"nbt":"temp.sell_price","storage":"tower",bold:false,color:yellow},{text:"\n",bold:false,color:gold}]
# tellraw @s [{nbt:"temp.info.weapon",storage:"tower",interpret:true}]
tellraw @s [{text:"[판매] \n",bold:true,color:yellow,click_event:{action:"run_command",command:"/function tower:upgrade/sell"}},{text:"[업그레이드] ",bold:true,color:green,click_event:{action:"run_command",command:"/function tower:upgrade/confirm"}},{text:"[취소] ",bold:true,color:red,click_event:{action:"run_command",command:"/function tower:upgrade/cancel"}}]