execute if score copyright-content trackselect-map-id matches 0 unless entity @a[tag=random-aaaaaa] run tellraw @a[distance=..50] [{translate:"해당 트랙은 ","color":"red"},{translate:"저작권 주의 컨텐츠","color":"aqua"},{translate:"입니다.","color":"red"}]
execute if score copyright-content trackselect-map-id matches 0 unless entity @a[tag=random-aaaaaa] run tellraw @a[distance=..50] [{translate:"저작권 주의 컨텐츠 레버를 당긴 뒤 선택할 수 있습니다.","color":"red"}]

return 1