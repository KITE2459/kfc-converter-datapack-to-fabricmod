execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run particle minecraft:explosion ~ ~ ~ 1.5 0.2 1.5 0 20 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.zombie_villager.converted record @a ~ ~ ~ 1.0 0.5

execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:heavy-dark}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run function enemy:spawn/summon/main {model:dark-mist}

execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run tag @s remove enemy.skill-loop.1
execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run tag @s add enemy.skill-loop.2
execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
