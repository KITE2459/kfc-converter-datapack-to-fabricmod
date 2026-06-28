execute if entity @a[tag=multi-hub-ready,tag=multi-hub-player] as @a[tag=!multi-hub-ready,tag=multi-hub-player] run tp -32.5 3.00 163.5
execute if entity @a[tag=multi-hub-ready,tag=multi-hub-player] run tellraw @a[distance=..50] [{translate:"준비하지 않은 ","color":"yellow"},{translate:"라이더가 ","color":"red"},{translate:"관전","color":"aqua"},{translate:"으로 전환되었습니다.","color":"red"}]
execute unless entity @a[tag=multi-hub-ready,tag=multi-hub-player] run tellraw @a[distance=..50] [{translate:"[준비 완료]","color":"yellow"},{translate:"한 라이더가 없어 경기를 시작할 수 없습니다.","color":"red"}]
