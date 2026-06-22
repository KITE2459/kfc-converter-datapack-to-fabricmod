$data modify storage game setup set from storage map $(map).infinity.setup
$data modify storage game setup.map set value "$(map)"
function game:game/init
scoreboard players set game game.difficulty 5
scoreboard players set game gameState 1
