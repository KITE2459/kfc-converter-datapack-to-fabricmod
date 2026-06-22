# gamestate 처리, 0: 평시, 1: 게임 중
execute if score game gameState matches 1 run function game:ui/main
execute if score game gameState matches 1 run function game:game/main

execute as @a unless score @s player.id matches 0.. run function game:player/init