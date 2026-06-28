#두 차의 속력을 그대로 스왑
scoreboard players operation #kartAvector kartmovey = @n[tag=carA,type=#kartmobil:kartmobil] kartmovey
scoreboard players operation @n[tag=carA,type=#kartmobil:kartmobil] kartmovey = @n[tag=carB,type=#kartmobil:kartmobil] kartmovey
scoreboard players operation @n[tag=carB,type=#kartmobil:kartmobil] kartmovey = #kartAvector kartmovey