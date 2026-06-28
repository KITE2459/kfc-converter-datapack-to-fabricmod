scoreboard players set #is-game-play game-id 1

#게임이 진행 중인 상황에서, 나갔다 들어온 플레이어 킥하기
execute unless score @s game-id = #max-id game-id at @s run function gamemain:noleftplayer/kickschedule

#만약 타이머가 꺼졌는데 플레이어가 있다면 킥하기
execute if entity @s[scores={dev-count=101..}] unless score iswork timermain matches 1 at @s run function gamemain:noleftplayer/kickschedule
execute if entity @s[scores={timecount=101..}] unless score iswork timermain matches 1 at @s run function gamemain:noleftplayer/kickschedule
execute if entity @s[scores={licensecount=101..}] unless score iswork timermain matches 1 at @s run function gamemain:noleftplayer/kickschedule
execute if entity @s[scores={updown-count=101..}] unless score iswork timermain matches 1 at @s run function gamemain:noleftplayer/kickschedule
execute if entity @s[scores={mastercount=101..}] unless score iswork timermain matches 1 at @s run function gamemain:noleftplayer/kickschedule
