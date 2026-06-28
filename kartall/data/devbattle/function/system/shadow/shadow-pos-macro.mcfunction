scoreboard players set #shadow-loaded dev-count 0
$execute if loaded $(shadowposx) $(shadowposy) $(shadowposz) run scoreboard players set #shadow-loaded dev-count 1

#$execute if score #shadow-loaded dev-count matches 1 positioned $(shadowposx) $(shadowposy) $(shadowposz) as @e[tag=shadow-models,tag=shadow-main,type=item_display,limit=1] run function kartmobil:move/movetp/tp-bug-fix/tp