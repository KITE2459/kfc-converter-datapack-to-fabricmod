# Macro: Resolve completion settings
# Input: {map_id: ...}

# Try to find map config
$data modify storage master:temp current_map set from storage master:config maps."$(map_id)"

# Check if we have a subtitle in the resolved config
execute if data storage master:temp current_map.complete_subtitle run function master:system/game/complete-show with storage master:temp current_map

# If no config or no subtitle, show default
execute unless data storage master:temp current_map.complete_subtitle run title @s subtitle {translate:"Map Cleared!","color":"yellow"}
