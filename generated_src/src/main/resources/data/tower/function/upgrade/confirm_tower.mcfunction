# 업그레이드 돈 확인
execute store result score #temp money as @n[tag=tower.upgrade] run data get entity @s data.Tower_Status.price
execute as @a if score @s player.id = #temp player.id unless score @s money >= #temp money if function tower:upgrade/fail_for_money run return 0

# 업그레이드 실행
data modify entity @n[tag=tower.data] data set from entity @n[tag=tower.upgrade] data.Tower_Status
data modify entity @n[tag=tower.muzzle] data set from entity @n[tag=tower.upgrade] data.Tower_Status
execute as @n[tag=tower.upgrade] unless data entity @s data.Tower_Upgrade run data remove entity @s data
execute as @n[tag=tower.upgrade] run data modify entity @s data set from entity @s data.Tower_Upgrade
scoreboard players add @n[tag=tower.data] tower.level 1

# 모델 업데이트
execute store result storage tower model_update.level int 1 run scoreboard players get @n[tag=tower.data] tower.level
data modify storage tower model_update.model set from entity @n[tag=tower.data] data.model
execute as @n[tag=tower.data] at @s run function tower:upgrade/update_model with storage tower model_update

# 돈 차감 및 완료 메시지
execute as @a if score @s player.id = #temp player.id run scoreboard players operation @s money -= #temp money
execute as @a if score @s player.id = #temp player.id run tellraw @s [{text:"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"},{text:"업그레이드가 완료되었습니다!",color:"green"},{text:"\n\n[다음 업그레이드]",color:gold,bold:true,click_event:{action:"run_command",command:"/function tower:upgrade/re_upgrade"}}]
function tower:upgrade/sound/main with entity @n[tag=tower.data] data

# 타워 속성 스코어보드에 값 저장
execute on passengers at @s[tag=tower.muzzle] store result score @s tower.attack run data get entity @n[tag=tower.data] data.attack 1.0
execute on passengers at @s[tag=tower.muzzle] store result score @s tower.attack_speed run data get entity @n[tag=tower.data] data.attack_speed 1.0
execute on passengers at @s[tag=tower.muzzle] store result score @s tower.target_mode run data get entity @n[tag=tower.data] data.target_mode 1.0
function tower:upgrade/reset