item replace entity @s weapon.offhand with carrot_on_a_stick 1

title @s actionbar "기능 없음"
title @s[nbt={SelectedItemSlot:0}] actionbar "체크포인트 설치하기"
title @s[nbt={SelectedItemSlot:1}] actionbar "대형 체크포인트 설치하기"
title @s[nbt={SelectedItemSlot:2}] actionbar "체크포인트 삭제하기(10m)"
title @s[nbt={SelectedItemSlot:3}] actionbar "체크포인트 번호 수정하기(10m)"
title @s[nbt={SelectedItemSlot:4}] actionbar "체크포인트 번호 증가(10m)"
title @s[nbt={SelectedItemSlot:5}] actionbar "체크포인트 번호 감소(10m)"

execute if entity @s[nbt={SelectedItemSlot:0}] run particle dust{color:[0, 1, 0],scale:1} ~ ~0.25 ~ .2 .2 .2 1 1
execute if entity @s[nbt={SelectedItemSlot:0},scores={carrot=1..}] run function checkpoint:tool/summon

execute if entity @s[nbt={SelectedItemSlot:1}] run particle dust{color:[0, 1, 0],scale:1} ~ ~0.25 ~ .2 .2 .2 1 1
execute if entity @s[nbt={SelectedItemSlot:1},scores={carrot=1..}] run function checkpoint:tool/summon-large

execute if entity @s[nbt={SelectedItemSlot:2}] at @n[tag=check-point,distance=..10,type=marker] run particle dust{color:[1, 0, 0],scale:1} ~ ~0.25 ~ .2 .2 .2 1 1
execute if entity @s[nbt={SelectedItemSlot:2},scores={carrot=1..}] run function checkpoint:tool/remove
execute if entity @s[nbt={SelectedItemSlot:3},scores={carrot=1..}] run function checkpoint:tool/setnum

execute if entity @s[nbt={SelectedItemSlot:4}] at @n[tag=check-point,distance=..10,type=marker] run particle dust{color:[1, 0, 0],scale:1} ~ ~0.25 ~ .2 .2 .2 1 1
execute if entity @s[nbt={SelectedItemSlot:4},scores={carrot=1..}] run scoreboard players add @n[tag=check-point,distance=..10,type=marker] check-num 1

execute if entity @s[nbt={SelectedItemSlot:5}] at @n[tag=check-point,distance=..10,type=marker] run particle dust{color:[1, 0, 0],scale:1} ~ ~0.25 ~ .2 .2 .2 1 1
execute if entity @s[nbt={SelectedItemSlot:5},scores={carrot=1..}] run scoreboard players remove @n[tag=check-point,distance=..10,type=marker] check-num 1

execute if score #20tick check-prev matches 1 at @s as @e[tag=check-point,distance=..200,type=marker] at @s run function checkpoint:tool/shownum

scoreboard players reset @s carrot