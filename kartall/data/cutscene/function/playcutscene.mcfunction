#이미 컷씬이 재생중이라면 돌려보내기
execute if entity @a[scores={cutscene=1..}] run tellraw @a[distance=..10] [{translate:"누군가가 이미 엔딩이나 오프닝을 시청 중입니다.","color":"red"}]
execute if entity @a[scores={cutscene=1..}] run return 1

#게임 시작
$scoreboard players set ending cutscene $(isending)
scoreboard players set @p cutscene 1


