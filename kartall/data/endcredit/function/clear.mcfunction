
title @a title [{"bold":false,"color":"yellow","italic":false,translate:"Kart"},{"bold":false,"color":"green","italic":false,translate:"R"},{"bold":false,"color":"yellow","italic":false,translate:"ider"},{"bold":false,"color":"yellow","italic":false,translate:" Forever!"}]
title @a subtitle {translate:"플레이해주셔서 감사합니다","color":"aqua"}
tellraw @s [{translate:"차고의 ","color":"green"},{translate:"[카트바디 성능 커스텀]","color":"aqua"},{translate:"이 해금되었습니다!","color":"green"}]
tellraw @s [{translate:"[스페셜]","color":"aqua"},{translate:"등급 카트가 해금되었습니다!","color":"green"}]
execute positioned -118.5 4.00 215.0 run playsound minecraft:ui.toast.challenge_complete weather @a ~ ~ ~ 1 1 1

setblock -102 5 216 minecraft:oak_button[face=wall,facing=east,powered=false]
setblock -102 5 212 minecraft:oak_button[face=wall,facing=east,powered=false]