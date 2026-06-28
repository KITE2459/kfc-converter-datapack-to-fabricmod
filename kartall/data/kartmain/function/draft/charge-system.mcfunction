tag @s add kartself

execute on passengers run tag @s[tag=kartdirection,type=item_display] add kartdirectiontemp

#태그 주기
# tag predicate=kartmobil:ifride] add kartpassenger
execute on passengers if entity @s[tag=kartsaddle,predicate=kartmobil:ifpassenger,type=#kartmobil:kartsaddle] on passengers run tag @s[predicate=kartmobil:ifride,type=player] add kartpassenger

#소리 듣기
function kartmobil:add-kart-listener

#드래프트 충전
execute unless score @s kartmain matches 60.. if score @s kartmove matches ..13899 run function kartmain:draft/draft-end
execute unless score @s kartmain matches 60.. if score @s kartmove matches 13900.. unless score @s kart-engine matches 1006 on passengers rotated as @s[tag=kartdirection,type=item_display] on vehicle if function kartmain:draft/draft-condition at @s run function kartmain:draft/draft-charge
execute unless score @s[tag=kart-draft-charging] kartmain matches 60.. if score @s kartmove matches 13900.. unless score @s kart-engine matches 1006 on passengers rotated as @s[tag=kartdirection,type=item_display] on vehicle unless function kartmain:draft/draft-condition run function kartmain:draft/draft-end

#F1
execute if score @s kart-engine matches 1006 if score @s kartmove matches 13900.. on passengers rotated as @s[tag=kartdirection,type=item_display] on vehicle if function kartmain:draft/draft-condition at @s run function kartmain:draft/draft-charge
execute if score @s kart-engine matches 1006 if score @s kartmove matches 13900.. on passengers rotated as @s[tag=kartdirection,type=item_display] on vehicle unless function kartmain:draft/draft-condition run function kartmain:draft/draft-end

#태그 제거
execute on passengers run tag @s[tag=kartdirectiontemp] remove kartdirectiontemp
tag @a[tag=kartpassenger] remove kartpassenger
tag @a[tag=kart-listener] remove kart-listener
tag @e[tag=kart-draft-target,distance=..30,type=#kartmobil:kartmobil] remove kart-draft-target

tag @s remove kartself
