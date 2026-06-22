execute store result score @s enemy.hp on vehicle run scoreboard players get @s enemy.hp
execute store result score @s enemy.defence on vehicle run scoreboard players get @s enemy.defence
execute store result score @s enemy.state.freeze on vehicle run scoreboard players get @s enemy.state.freeze
execute store result score @s enemy.state.bleed on vehicle run scoreboard players get @s enemy.state.bleed
execute store result score @s enemy.state.poison on vehicle run scoreboard players get @s enemy.state.poison
execute store result score @s enemy.state.flame on vehicle run scoreboard players get @s enemy.state.flame
execute store result score @s enemy.state.stun on vehicle run scoreboard players get @s enemy.state.stun
execute store result score @s enemy.state.weakness on vehicle run scoreboard players get @s enemy.state.weakness
execute store result score @s enemy.state.corruption on vehicle run scoreboard players get @s enemy.state.corruption
execute store result score @s enemy.attribute.healing on vehicle run scoreboard players get @s enemy.attribute.healing

# 기본 스탯 표기
execute on vehicle run data modify storage temp text set value [{text:""}]
execute on vehicle run data modify storage temp text append from entity @s data.name
data modify storage temp text append value [{text:"Hp",color:red,bold:false},{text:":",color:white,bold:false},{score:{name:"@s",objective:"enemy.hp"},color:yellow,bold:false}]
execute unless score @s enemy.defence matches 0 run data modify storage temp text append value [{text:" 🛡",color:gray},{"score":{"name":"@s","objective":"enemy.defence"},color:gray}]

# 속성 표기
execute if function enemy:animation/check-buff run data modify storage temp text append value {text:"\n",color:aqua}
execute if score @s enemy.attribute.healing matches 1.. run data modify storage temp text append value [{text:"+",color:green,bold:true},{"score":{"name":"@s","objective":"enemy.attribute.healing"},color:white}]
execute on vehicle if entity @s[tag=enemy.attribute.heavy] run data modify storage temp text append value [{text:" [육중]",color:gold}]
execute on vehicle if entity @s[tag=enemy.attribute.speed] run data modify storage temp text append value [{text:" [신속]",color:aqua}]
execute on vehicle if entity @s[tag=enemy.attribute.taunt] run data modify storage temp text append value [{text:" [도발]",color:red}]
execute on vehicle if entity @s[tag=enemy.attribute.dark] run data modify storage temp text append value [{text:" [☯어둠]",color:black}]

# 면역 표기
execute if function enemy:animation/check-immune run data modify storage temp text append value [{text:"\n",color:aqua},{text:"면역 ",color:light_purple}]
execute on vehicle if entity @s[tag=enemy.immune.freeze] run data modify storage temp text append value [{text:"❄",color:aqua}]
execute on vehicle if entity @s[tag=enemy.immune.bleed] run data modify storage temp text append value [{text:"🩸",color:red}]
execute on vehicle if entity @s[tag=enemy.immune.poison] run data modify storage temp text append value [{text:"☠",color:green}]
execute on vehicle if entity @s[tag=enemy.immune.flame] run data modify storage temp text append value [{text:"🔥",color:gold}]
execute on vehicle if entity @s[tag=enemy.immune.stun] run data modify storage temp text append value [{text:"💫",color:yellow}]
execute on vehicle if entity @s[tag=enemy.immune.weakness] run data modify storage temp text append value [{text:"☢",color:dark_green}]

# 디버프 표기
execute if function enemy:animation/check-debuff run data modify storage temp text append value {text:"\n",color:aqua}
execute if score @s enemy.state.freeze matches 1.. run data modify storage temp text append value [{text:"❄",color:aqua},{"score":{"name":"@s","objective":"enemy.state.freeze"},color:white}]
execute if score @s enemy.state.bleed matches 1.. run data modify storage temp text append value [{text:"🩸",color:red},{"score":{"name":"@s","objective":"enemy.state.bleed"},color:white}]
execute if score @s enemy.state.poison matches 1.. run function enemy:animation/poison-text
execute if score @s enemy.state.flame matches 1.. run data modify storage temp text append value [{text:"🔥",color:gold},{"score":{"name":"@s","objective":"enemy.state.flame"},color:white}]
execute if score @s enemy.state.stun matches 1.. run data modify storage temp text append value [{text:"💫",color:yellow},{"score":{"name":"@s","objective":"enemy.state.stun"},color:white}]
execute if score @s enemy.state.weakness matches 1.. run data modify storage temp text append value [{text:"☢",color:dark_green},{"score":{"name":"@s","objective":"enemy.state.weakness"},color:white}]
execute if score @s enemy.state.corruption matches 1..99 run data modify storage temp text append value [{text:"☯",color:black},{"score":{"name":"@s","objective":"enemy.state.corruption"},color:white}]

data modify entity @s text set from storage temp text