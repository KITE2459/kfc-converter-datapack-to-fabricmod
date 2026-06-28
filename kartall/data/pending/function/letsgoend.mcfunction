execute if score #pendcredit pendcredit matches 1.. positioned -113.50 4.00 218.50 run tellraw @a[distance=..10] [{translate:"누군가가 이미 엔딩이나 오프닝을 시청 중입니다.","color":"red"}]
execute unless score #pendcredit pendcredit matches 1.. run scoreboard players set #pendcredit pendcredit 1
