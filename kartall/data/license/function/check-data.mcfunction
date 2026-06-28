execute unless score clear-data licensestage matches 0 if score clear-data-from-file licensestage matches 0 run function license:license-data/load-progress
execute if score clear-data-from-file licensestage < clear-data licensestage run function license:license-data/save-progress
execute if score clear-data-from-file licensestage > clear-data licensestage run function license:license-data/load-progress
