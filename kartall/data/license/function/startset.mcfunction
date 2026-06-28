scoreboard objectives add trackselect-map-id dummy

scoreboard objectives add licensecount dummy
scoreboard objectives add licensestage dummy
execute unless score clear-data licensestage = clear-data licensestage run scoreboard players set clear-data licensestage 0

#라이센스 

bossbar add license "00:00:00초 내로 완주"