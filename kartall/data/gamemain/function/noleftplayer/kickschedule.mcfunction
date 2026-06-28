#1틱뒤 예약 태그 주기
tag @s add kart-left-player

#플레이 중이라는 데이터 제거
tag @s remove kart-multi-player
scoreboard players reset @s dev-count
scoreboard players reset @s timecount
scoreboard players reset @s licensecount
scoreboard players reset @s updown-count

tag @s remove checkpointpass
