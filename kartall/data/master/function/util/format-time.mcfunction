# Format Time: Converts #input master (ms) to formatted text "MM:SS.mmm"
# Output: storage master:temp formatted_text

# 1. Calculate MM, SS, mmm
scoreboard players operation #minutes master = #input master
scoreboard players operation #minutes master /= #c60000 master

scoreboard players operation #seconds master = #input master
scoreboard players operation #seconds master %= #c60000 master
scoreboard players operation #seconds_rem master = #seconds master
scoreboard players operation #seconds master /= #c1000 master

scoreboard players operation #millis master = #seconds_rem master
scoreboard players operation #millis master %= #c1000 master

# 2. Construct Text Component using Macro for Zero Padding? No, macro needs string args.
# Better approach: Use storage to hold components and checks for padding logic.
# Pad Minutes (if needed, but usually MM: is fine as 0-59. Actually if >9 ok. If <10 add 0? Time usually 02:00. So yes)

data modify storage master:temp time_components set value {}

# Minutes (e.g. "02")
execute if score #minutes master matches 0..9 run data modify storage master:temp time_components.minutes set value {text:"0"}
execute if score #minutes master matches 0..9 run data modify storage master:temp time_components.minutes.extra set value [{score:{name:"#minutes",objective:"master"}}]
execute if score #minutes master matches 10.. run data modify storage master:temp time_components.minutes set value {score:{name:"#minutes",objective:"master"}}

# Seconds (e.g. "05")
execute if score #seconds master matches 0..9 run data modify storage master:temp time_components.seconds set value {text:"0"}
execute if score #seconds master matches 0..9 run data modify storage master:temp time_components.seconds.extra set value [{score:{name:"#seconds",objective:"master"}}]
execute if score #seconds master matches 10.. run data modify storage master:temp time_components.seconds set value {score:{name:"#seconds",objective:"master"}}


# Millis (e.g. "007")
# If millis < 10: "00" + val
# If millis < 100: "0" + val
# Else val

# 먼저 100 이상인 경우 기본값 설정
data modify storage master:temp time_components.millis set value {score:{name:"#millis",objective:"master"}}

# 0~9 (e.g. 7 -> 007)
execute if score #millis master matches 0..9 run data modify storage master:temp time_components.millis set value {text:"00"}
execute if score #millis master matches 0..9 run data modify storage master:temp time_components.millis.extra set value [{score:{name:"#millis",objective:"master"}}]

# 10~99 (e.g. 52 -> 052)
execute if score #millis master matches 10..99 run data modify storage master:temp time_components.millis set value {text:"0"}
execute if score #millis master matches 10..99 run data modify storage master:temp time_components.millis.extra set value [{score:{name:"#millis",objective:"master"}}]

# 100 이상은 기본값 그대로 유지


# Combine
# formatted_text = {text:"", "extra":[minutes, {text:":"}, seconds, {text:"."}, millis]}
data modify storage master:temp formatted_text set value {text:"", extra:[]}
data modify storage master:temp formatted_text.extra append from storage master:temp time_components.minutes
data modify storage master:temp formatted_text.extra append value {text:":"}
data modify storage master:temp formatted_text.extra append from storage master:temp time_components.seconds
data modify storage master:temp formatted_text.extra append value {text:"."}
data modify storage master:temp formatted_text.extra append from storage master:temp time_components.millis

