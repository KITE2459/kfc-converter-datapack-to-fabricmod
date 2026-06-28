$execute on vehicle on passengers if entity @s[tag=check-rank-text,type=text_display] run data modify entity @s text set value "$(rank)등"
$scoreboard players display numberformat @s timerdisplay fixed "$(rank)등"

#공식 멀티
$execute if score mad-crash multi-main matches 0 if entity @s[tag=kite-played,tag=!bump-allow] run scoreboard players display numberformat @s timerdisplay fixed [{"font":"include/default",text:"⛨",color:yellow},{"font":"default",text:" $(rank)등",color:white}]
$execute if score mad-crash multi-main matches 0 if entity @s[tag=kite-played,tag=bump-allow] run scoreboard players display numberformat @s timerdisplay fixed [{"font":"include/default",text:"🗡",color:red},{"font":"default",text:" $(rank)등",color:white}]

$execute if score mad-crash multi-main matches 1 if entity @s[tag=kite-played] run scoreboard players display numberformat @s timerdisplay fixed [{"font":"include/default",text:"⚠",color:red},{"font":"default",text:" $(rank)등",color:white}]