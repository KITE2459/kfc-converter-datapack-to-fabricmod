data modify storage license-cleardata:license-data cleared-stage set value 0
execute store result score clear-data-from-file licensestage run data get storage license-cleardata:license-data cleared-stage 1.0

function license:check-data
#tellraw @a "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
stopsound @a

tellraw @a {translate:"라이센스가 초기화되었습니다.","color":"aqua"}