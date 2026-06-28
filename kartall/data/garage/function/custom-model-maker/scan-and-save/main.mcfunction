execute positioned 5248 5 4500 run kill @e[type=minecraft:item_display,distance=..0.01]

execute unless data entity @n[tag=garage-custom-frame] Item run tellraw @a[x=5245,y=4,z=4487,dx=27,dy=27,dz=27,gamemode=adventure] [{"translate":"원하는 모델링 이름을 적어 아이템 액자에 넣어 주세요.","color":"red"}]
execute unless data entity @n[tag=garage-custom-frame] Item run return 1


    function garage:custom-model-maker/scan-and-save/count-block/main
    scoreboard players set #block-count-max garage-time 350

    scoreboard players operation #block-count-max garage-time -= #block-count garage-time
    scoreboard players operation #block-count-max garage-time *= #kart-1 kartmain
    execute if score #block-count-max garage-time matches 1.. run tellraw @a[x=5245,y=4,z=4487,dx=27,dy=27,dz=27,gamemode=adventure] [{"translate":"블록 개수 제한 350개를 초과했습니다. ","color":"red"},{"score":{"name":"#block-count-max","objective":"garage-time"}},{"translate":"개의 블록을 줄여 주세요.","color":"red"}]
    execute if score #block-count-max garage-time matches 1.. run return 1


data modify storage kart-custom-model temp2 set value []
execute positioned 5251 4 4493 run function garage:custom-model-maker/scan-and-save/y-axis-scan

tag @a[x=5245,y=4,z=4487,dx=27,dy=27,dz=27,gamemode=adventure] add garage-temp-asdfasdf

execute positioned 5250 4 4513 if data entity @n[tag=garage-custom-frame] Item run setblock 5244 4 4516 oak_sign{front_text:{messages:[[{"selector":"@p[tag=garage-temp-asdfasdf]"},{"translate":"의 "},{"nbt":"Item.components.\"minecraft:writable_book_content\".pages[0].raw","entity":"@n[tag=garage-custom-frame]"}],"","",""]}} replace
execute positioned 5250 4 4513 unless data entity @n[tag=garage-custom-frame] Item run setblock 5244 4 4516 oak_sign{front_text:{messages:[[{"selector":"@p[tag=garage-temp-asdfasdf]"},{"translate":"의 이름 없는 모델링"}],"","",""]}} replace

execute positioned 5250 4 4513 at @p[tag=garage-temp-asdfasdf] run summon item 5250 5 4513 {PickupDelay:20,Tags:["asdfasdf0808"],Item:{id:"minecraft:paper",count:1,components:{"minecraft:custom_name":[{"text":"asdf"}]}}}

data modify entity @n[tag=asdfasdf0808] Item.components."minecraft:custom_name" set from block 5244 4 4516 front_text.messages[0]
data modify entity @n[tag=asdfasdf0808] Item.components.minecraft:custom_data.model set from storage kart-custom-model temp2

data remove entity @n[tag=garage-custom-frame] Item

tag @n[tag=asdfasdf0808] remove asdfasdf0808
tag @a[tag=garage-temp-asdfasdf] remove garage-temp-asdfasdf




