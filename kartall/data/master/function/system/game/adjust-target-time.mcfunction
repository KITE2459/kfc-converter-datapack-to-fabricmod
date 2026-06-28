# Adjust Target Time based on masterstage
# Input: #target-time master (base time)
# Logic: + offset based on masterstage

# Check masterstage and add offset (ms)
execute if score clear-data masterstage matches 11.. run scoreboard players remove #target-time master 100
execute if score clear-data masterstage matches 9 run scoreboard players add #target-time master 100
execute if score clear-data masterstage matches 8 run scoreboard players add #target-time master 300
execute if score clear-data masterstage matches 7 run scoreboard players add #target-time master 600
execute if score clear-data masterstage matches 6 run scoreboard players add #target-time master 1000
execute if score clear-data masterstage matches 5 run scoreboard players add #target-time master 1500
execute if score clear-data masterstage matches 4 run scoreboard players add #target-time master 2100
execute if score clear-data masterstage matches 3 run scoreboard players add #target-time master 2800
execute if score clear-data masterstage matches 2 run scoreboard players add #target-time master 3600
execute if score clear-data masterstage matches 1 run scoreboard players add #target-time master 4500
execute if score clear-data masterstage matches 0 run scoreboard players add #target-time master 5500

# Update display text
scoreboard players operation #input master = #target-time master
function master:util/format-time

# Store formatted text for bossbar update
# Bossbar name template: {"translate":"%s 내로 완주","color":"aqua","with":[formatted_text]}
data modify storage master:temp bossbar_name set value {translate:"%s 내로 완주",color:"aqua",with:[]}
data modify storage master:temp bossbar_name.with append from storage master:temp formatted_text

# Apply to bossbar (using macro because bossbar name accepts JSON component but needs to be set via command)
function master:system/game/update-bossbar-name with storage master:temp

# Update Scoreboard & Bossbar Max/Value
execute store result bossbar minecraft:master max run scoreboard players get #target-time master
execute store result bossbar minecraft:master value run scoreboard players get #target-time master

