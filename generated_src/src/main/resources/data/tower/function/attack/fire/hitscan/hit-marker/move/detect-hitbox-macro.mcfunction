execute unless score @s hitscan-marker.area matches 1.. if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-0 run return 1
execute if score @s hitscan-marker.area matches 1 if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-1 run return 1
execute if score @s hitscan-marker.area matches 2 if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-2 run return 1
execute if score @s hitscan-marker.area matches 3 if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-2 run return 1
execute if score @s hitscan-marker.area matches 4 if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-2 run return 1
return 0