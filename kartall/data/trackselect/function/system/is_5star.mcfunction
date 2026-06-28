data modify storage temp difficulty_text set value "★★★★★"
execute store result score #return temp run data modify storage temp difficulty_text set from storage temp difficulty_text_origin
execute if score #return temp matches 0 run return 1