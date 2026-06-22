# 판매 가격 계산 (최대 레벨이므로 temp2.price를 사용)
execute store result score #sell_cost tower run data get storage tower temp.price
execute store result score #upgrade_cost tower run data get storage tower temp2.price
execute store result score #level tower run data get storage tower temp.level
scoreboard players remove #level tower 1
scoreboard players operation #upgrade_cost tower *= #level tower
scoreboard players operation #sell_cost tower += #upgrade_cost tower
scoreboard players operation #sell_cost tower /= #2 tower

tellraw @s [{nbt:temp.name,storage:tower,interpret:true},{text:" -",bold:false,color:white},{text:" Level ",bold:false,color:aqua},{nbt:temp.level,storage:tower,color:aqua,bold:false},{text:"\n",bold:false,color:aqua}]
tellraw @s [{text:"[공격력] ",bold:false,color:red},{"nbt":"temp.attack","storage":"tower",bold:false,color:light_purple}]
tellraw @s [{text:"[공격 속도] ",bold:false,color:aqua},{"nbt":"temp.info.attack_speed","storage":"tower",bold:false,color:light_purple}]
tellraw @s [{text:"[사거리] ",bold:false,color:blue},{"nbt":"temp.range","storage":"tower",bold:false,color:light_purple}]
execute if data storage tower temp.Bullet.attribute run function tower:upgrade/ui_attribute_maxed
function tower:upgrade/ui_target_mode
tellraw @s [{text:"\n판매 가격: ",bold:false,color:gold},{"nbt":"temp.sell_price","storage":"tower",bold:false,color:yellow},{text:"\n",bold:false,color:gold}]
tellraw @s [{text:"[판매] \n",bold:true,color:yellow,click_event:{action:"run_command",command:"/function tower:upgrade/sell"}},{text:"[취소] ",bold:true,color:red,click_event:{action:"run_command",command:"/function tower:upgrade/cancel"}}]
return 1