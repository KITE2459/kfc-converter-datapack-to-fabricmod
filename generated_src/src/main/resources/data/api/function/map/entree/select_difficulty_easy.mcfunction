$data modify storage game setup set from storage map $(map).easy.setup
$data modify storage game setup.map set value "$(map)"
function game:game/init
scoreboard players set game game.difficulty 1
scoreboard players set game gameState 1