execute if entity @s[tag=nbs_multiplayr] unless entity @s[tag=nbs_retiresoun] run function multiplayroom:stop
execute if entity @s[tag=!nbs_singleplay] unless entity @s[tag=nbs_retiresoun] run function singleplayroom:play
