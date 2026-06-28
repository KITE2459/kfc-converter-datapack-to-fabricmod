# 활성 상태 텍스트 반영
execute if score @s customspec-active matches 1 run data modify entity @e[type=text_display,tag=customspec-state-speed,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 1 run data modify entity @e[type=text_display,tag=customspec-state-speed,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

execute if score @s customspec-active matches 2 run data modify entity @e[type=text_display,tag=customspec-state-accel,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 2 run data modify entity @e[type=text_display,tag=customspec-state-accel,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

execute if score @s customspec-active matches 3 run data modify entity @e[type=text_display,tag=customspec-state-boost,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 3 run data modify entity @e[type=text_display,tag=customspec-state-boost,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

execute if score @s customspec-active matches 4 run data modify entity @e[type=text_display,tag=customspec-state-corner,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 4 run data modify entity @e[type=text_display,tag=customspec-state-corner,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

execute if score @s customspec-active matches 5 run data modify entity @e[type=text_display,tag=customspec-state-drift,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 5 run data modify entity @e[type=text_display,tag=customspec-state-drift,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

execute if score @s customspec-active matches 6 run data modify entity @e[type=text_display,tag=customspec-state-gauge,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 6 run data modify entity @e[type=text_display,tag=customspec-state-gauge,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

execute if score @s customspec-active matches 7 run data modify entity @e[type=text_display,tag=customspec-state-boosttime,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 7 run data modify entity @e[type=text_display,tag=customspec-state-boosttime,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

execute if score @s customspec-active matches 8 run data modify entity @e[type=text_display,tag=customspec-state-defense,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 8 run data modify entity @e[type=text_display,tag=customspec-state-defense,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

execute if score @s customspec-active matches 9 run data modify entity @e[type=text_display,tag=customspec-state-draft,distance=..10,limit=1,sort=nearest] text set value {text:"활성화",color:"aqua",bold:true,italic:false}
execute unless score @s customspec-active matches 9 run data modify entity @e[type=text_display,tag=customspec-state-draft,distance=..10,limit=1,sort=nearest] text set value {text:"비활성화",color:"gray",bold:false,italic:false}

# 수치 텍스트 반영 (매 틱)
execute store result storage customspec:ui speed int 1 run scoreboard players get @s customspec-speed
execute store result storage customspec:ui accel int 1 run scoreboard players get @s customspec-accel
execute store result storage customspec:ui boost int 1 run scoreboard players get @s customspec-boost
execute store result storage customspec:ui corner int 1 run scoreboard players get @s customspec-corner
execute store result storage customspec:ui drift int 1 run scoreboard players get @s customspec-drift
execute store result storage customspec:ui gauge int 1 run scoreboard players get @s customspec-gauge
execute store result storage customspec:ui boosttime int 1 run scoreboard players get @s customspec-boosttime
execute store result storage customspec:ui defense int 1 run scoreboard players get @s customspec-defense
execute store result storage customspec:ui draft int 1 run scoreboard players get @s customspec-draft

function customspec:render/value/speed with storage customspec:ui
function customspec:render/value/accel with storage customspec:ui
function customspec:render/value/boost with storage customspec:ui
function customspec:render/value/corner with storage customspec:ui
function customspec:render/value/drift with storage customspec:ui
function customspec:render/value/gauge with storage customspec:ui
function customspec:render/value/boosttime with storage customspec:ui
function customspec:render/value/defense with storage customspec:ui
function customspec:render/value/draft with storage customspec:ui
