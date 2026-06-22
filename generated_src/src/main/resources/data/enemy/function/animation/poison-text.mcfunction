# 독 사전 처리
scoreboard players set #10 enemy.state.poison 10
scoreboard players set #100 enemy.state.poison 100
scoreboard players set #1000 enemy.state.poison 1000
scoreboard players operation #temp-degit-1 enemy.state.poison = @s enemy.state.poison
scoreboard players operation #temp-degit-1 enemy.state.poison %= #10 enemy.state.poison
scoreboard players operation #temp-degit-10 enemy.state.poison = @s enemy.state.poison
scoreboard players operation #temp-degit-10 enemy.state.poison %= #100 enemy.state.poison
scoreboard players operation #temp-degit-10 enemy.state.poison /= #10 enemy.state.poison
scoreboard players operation #temp-degit-100 enemy.state.poison = @s enemy.state.poison
scoreboard players operation #temp-degit-100 enemy.state.poison /= #100 enemy.state.poison

execute if score @s enemy.state.poison matches 100.. run data modify storage temp text append value [{text:"☠",color:green},{"score":{"name":"#temp-degit-100","objective":"enemy.state.poison"},color:white},{"score":{"name":"#temp-degit-10","objective":"enemy.state.poison"},color:white},{text:".",color:white},{"score":{"name":"#temp-degit-1","objective":"enemy.state.poison"},color:white},{text:"%",color:white}]
execute if score @s enemy.state.poison matches 10..99 run data modify storage temp text append value [{text:"☠",color:green},{"score":{"name":"#temp-degit-10","objective":"enemy.state.poison"},color:white},{text:".",color:white},{"score":{"name":"#temp-degit-1","objective":"enemy.state.poison"},color:white},{text:"%",color:white}]
execute if score @s enemy.state.poison matches 1..9 run data modify storage temp text append value [{text:"☠",color:green},{text:"0.",color:white},{"score":{"name":"#temp-degit-1","objective":"enemy.state.poison"},color:white},{text:"%",color:white}]