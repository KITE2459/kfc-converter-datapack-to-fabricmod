$data modify storage game setup set from storage map $(map).hard.setup
$data modify storage game setup.map set value "$(map)"
function game:game/init
scoreboard players set game game.difficulty 3
scoreboard players set game gameState 1