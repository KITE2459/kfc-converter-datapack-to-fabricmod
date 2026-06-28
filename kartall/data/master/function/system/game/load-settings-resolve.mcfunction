# Macro: Resolve map config from master:config
# Input: {map_id: 1}

# Check if map exists in config (error handling optional but good)
# Copy map config to temp storage for application
# Clear temp storage first to avoid partial apply from previous map
data remove storage master:temp current_map
$data modify storage master:temp current_map set from storage master:config maps."$(map_id)"

# Apply settings if config exists
execute if data storage master:temp current_map run function master:system/game/load-settings-apply with storage master:temp current_map
