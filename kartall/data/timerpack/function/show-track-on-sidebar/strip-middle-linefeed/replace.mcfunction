$data modify storage replace temp set string storage replace from $(index) $(indexp1)
$execute if data storage replace {temp:"$(target)"} run data modify storage replace result.s$(index) set from storage replace with
$execute unless data storage replace {temp:"$(target)"} run data modify storage replace result.s$(index) set from storage replace temp

scoreboard players add index var 1
scoreboard players add indexp1 var 1
execute if score indexp1 var > last_index var run return run function timerpack:show-track-on-sidebar/strip-middle-linefeed/replace_result with storage replace result

execute store result storage replace index int 1 run scoreboard players get index var
execute store result storage replace indexp1 int 1 run scoreboard players get indexp1 var
function timerpack:show-track-on-sidebar/strip-middle-linefeed/replace with storage replace
