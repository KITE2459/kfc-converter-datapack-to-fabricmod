#두 차의 Y축 속력을 그대로 스왑
scoreboard players operation #kartmovey-a kartmovey = @e[tag=carA,limit=1,type=#kartmobil:kartmobil] kartmovey
scoreboard players operation @e[tag=carA,limit=1,type=#kartmobil:kartmobil] kartmovey = @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartmovey
scoreboard players operation @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartmovey = #kartmovey-a kartmovey