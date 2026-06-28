execute unless score clear-data masterstage matches 0 if score master-file masterstage matches 0 run function master:master-data/load-progress
execute if score master-file masterstage < clear-data masterstage run function master:master-data/save-progress
execute if score master-file masterstage > clear-data masterstage run function master:master-data/load-progress
