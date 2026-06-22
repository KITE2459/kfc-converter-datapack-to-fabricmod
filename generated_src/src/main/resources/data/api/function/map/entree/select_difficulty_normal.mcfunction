$data modify storage game setup set from storage map $(map).normal.setup
$data modify storage game setup.map set value "$(map)"
function game:game/init
scoreboard players set game game.difficulty 2
scoreboard players set game gameState 1