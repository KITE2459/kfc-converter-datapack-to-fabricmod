scoreboard players reset * timerdisplay

    data modify storage minecraft:timermain track set from entity @s data.track.text

    data modify storage minecraft:timermain track-text-result set value []
    data modify storage minecraft:timermain track-text-result-temp set value []

    function timerpack:show-track-on-sidebar/split-by-linefeed

#data modify storage minecraft:timermain track-text-result[0] prepend value "   "

scoreboard players set timertext timerdisplay 2147483647
scoreboard players set timertext2 timerdisplay 2147483646
scoreboard players set timertext3 timerdisplay 2147483645
scoreboard players set timertext4 timerdisplay 2147483644

scoreboard objectives modify timerdisplay displayname "트랙"
scoreboard players display name timertext timerdisplay ["",{"nbt":"track-text-result[0]","storage":"minecraft:timermain",interpret:true}]
scoreboard players display name timertext2 timerdisplay ["",{"nbt":"track-text-result[1]","storage":"minecraft:timermain",interpret:true}]
scoreboard players display name timertext3 timerdisplay ["",{"nbt":"data.track.difficulty","entity":"@s",interpret:true}]

    data merge storage replace {target:"\n",with:", ",from:""}
    data modify storage replace from set from entity @s data.track.creator.translate
    function timerpack:show-track-on-sidebar/strip-middle-linefeed/replace_ready
    
    data modify storage replace temp set from entity @s data.track.creator
    data modify storage replace temp.translate set from storage replace final_result

scoreboard players display name timertext4 timerdisplay ["",{"nbt":"temp","storage":"replace",interpret:true}]

scoreboard players display numberformat timertext timerdisplay blank
scoreboard players display numberformat timertext2 timerdisplay blank
scoreboard players display numberformat timertext3 timerdisplay blank
scoreboard players display numberformat timertext4 timerdisplay blank

scoreboard objectives setdisplay sidebar timerdisplay