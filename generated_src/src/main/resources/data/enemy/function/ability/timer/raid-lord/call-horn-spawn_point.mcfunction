scoreboard players set #5 enemy.skill-trigger.timer-cooldown 5
scoreboard players operation #temp2 enemy.skill-trigger.timer-cooldown = #temp enemy.skill-trigger.timer-cooldown
scoreboard players operation #temp2 enemy.skill-trigger.timer-cooldown %= #5 enemy.skill-trigger.timer-cooldown

execute if score #temp enemy.skill-trigger.timer-cooldown matches -45..-20 if score #temp2 enemy.skill-trigger.timer-cooldown matches 0 run function api:enemy/spawn {model:vindicator,difficulty:hard,map:forest_temple}
execute if score #temp enemy.skill-trigger.timer-cooldown matches -80..-61 if score #temp2 enemy.skill-trigger.timer-cooldown matches 0 run function api:enemy/spawn {model:pillager,difficulty:hard,map:forest_temple}
execute if score #temp enemy.skill-trigger.timer-cooldown matches -40 run function api:enemy/spawn {model:illusioner,difficulty:hard,map:forest_temple}
execute if score #temp enemy.skill-trigger.timer-cooldown matches -50 run function api:enemy/spawn {model:ravager,difficulty:hard,map:forest_temple}
execute if score #temp enemy.skill-trigger.timer-cooldown matches -60 run function api:enemy/spawn {model:evoker,difficulty:hard,map:forest_temple}