# 타워 중복 방지
$execute positioned ~$(translation) ~-0.001 ~$(translation) align xz if entity @e[tag=tower.hitbox,dx=$(hitbox),dy=0,dz=$(hitbox)] run tellraw @s {text:"타워를 겹쳐 소환할 수 없습니다!",color:"red"}
$execute positioned ~$(translation) ~-0.001 ~$(translation) align xz if entity @e[tag=tower.hitbox,dx=$(hitbox),dy=0,dz=$(hitbox)] run playsound minecraft:block.note_block.bass neutral @s
$execute positioned ~$(translation) ~-0.001 ~$(translation) align xz if entity @e[tag=tower.hitbox,dx=$(hitbox),dy=0,dz=$(hitbox)] run return 0

# 타워 설치 가능 지역 확인
$execute positioned ~$(translation) ~-2 ~$(translation) align xz unless blocks ~ ~ ~ ~$(hitbox) ~ ~$(hitbox) 0 -62 0 all run tellraw @s {text:"타워를 설치할 수 없는 곳입니다!",color:"red"}
$execute positioned ~$(translation) ~-2 ~$(translation) align xz unless blocks ~ ~ ~ ~$(hitbox) ~ ~$(hitbox) 0 -62 0 all run playsound minecraft:block.note_block.bass neutral @s
$execute positioned ~$(translation) ~-2 ~$(translation) align xz unless blocks ~ ~ ~ ~$(hitbox) ~ ~$(hitbox) 0 -62 0 all run return 0

# 소지금 확인
$execute unless entity @s[scores={money=$(price)..}] run tellraw @s {text:"돈이 부족합니다!",color:"red"}
$execute unless entity @s[scores={money=$(price)..}] run playsound minecraft:block.note_block.bass neutral @s
$execute unless entity @s[scores={money=$(price)..}] run return 0
$scoreboard players remove @s money $(price)

# 타워 소환
tellraw @s {text:"타워를 성공적으로 설치하였습니다!",color:"green"}
playsound minecraft:entity.experience_orb.pickup neutral @s
$execute align xyz run function tower:spawn/model/$(model)/lv0

# 타워 속성 할당
execute as @e[tag=tower.core] at @s unless score @s tower.number matches 0.. run function tower:spawn/summon/allocate with storage tower temp2.components.minecraft:custom_data.Tower_Status