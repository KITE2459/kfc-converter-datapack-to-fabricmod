scoreboard players set #update-exist kartmain 0

tp @a -139 4 198 0 0
gamemode adventure @a
clear @a

function license:check-data
tellraw @a "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
stopsound @a

#텍스트 출력
tellraw @a {"text":"[카트라이더: 마인크래프트]","color":"gold","bold":true}
tellraw @a {"text":"데이터 파일을 옮기셨군요!","color":"aqua"}
tellraw @a {"interpret":true,"nbt":"kart-update-check","storage":"kartmain"}
tellraw @a {"text":"인벤토리는 초기화되었으니 차고로 이동해 카트를 얻어주세요!","color":"aqua"}


function updatedetector:on-user-dismount-kart
