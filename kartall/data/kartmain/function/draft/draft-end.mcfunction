scoreboard players reset @s[tag=kart-draft-charging] kartmain
tag @s remove kart-draft-charging

    #태그 주기
    # tag predicate=kartmobil:ifride] add kartpassenger
    execute on passengers if entity @s[tag=kartsaddle,predicate=kartmobil:ifpassenger,type=#kartmobil:kartsaddle] on passengers run tag @s[predicate=kartmobil:ifride,type=player] add kartpassenger

    #소리 듣기
    function kartmobil:add-kart-listener

effect clear @a[tag=kart-listener] minecraft:wind_charged
execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-draft/off-set-0

tag @a[tag=kartpassenger] remove kartpassenger
tag @a[tag=kart-listener] remove kart-listener