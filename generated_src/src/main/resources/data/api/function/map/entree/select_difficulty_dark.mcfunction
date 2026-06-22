$data modify storage game setup set from storage map $(map).dark.setup
$data modify storage game setup.map set value "$(map)"
function game:game/init
scoreboard players set game game.difficulty 4
scoreboard players set game gameState 1