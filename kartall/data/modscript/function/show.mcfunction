execute if block -13 0 153 minecraft:redstone_lamp[lit=false] run scoreboard players display name modscript1 kart-mod [{translate:"팀전: "},{"bold":true,translate:"❌","color":"dark_red"}]
execute if block -13 0 153 minecraft:redstone_lamp[lit=true] run scoreboard players display name modscript1 kart-mod [{translate:"팀전: "},{"bold":true,translate:"✔","color":"green"}]

execute if block -11 0 153 minecraft:redstone_lamp[lit=false] run scoreboard players display name modscript2 kart-mod [{translate:"고스트 모드: "},{"bold":true,translate:"❌","color":"dark_red"}]
execute if block -11 0 153 minecraft:redstone_lamp[lit=true] run scoreboard players display name modscript2 kart-mod [{translate:"고스트 모드: "},{"bold":true,translate:"✔","color":"green"}]

execute if block -9 0 153 minecraft:redstone_lamp[lit=false] run scoreboard players display name modscript3 kart-mod [{translate:"무한 부스터: "},{"bold":true,translate:"❌","color":"dark_red"}]
execute if block -9 0 153 minecraft:redstone_lamp[lit=true] run scoreboard players display name modscript3 kart-mod [{translate:"무한 부스터: "},{"bold":true,translate:"✔","color":"green"}]

execute if block -7 0 153 minecraft:redstone_lamp[lit=false] run scoreboard players display name modscript4 kart-mod [{translate:"드래프트 끄기: "},{"bold":true,translate:"❌","color":"dark_red"}]
execute if block -7 0 153 minecraft:redstone_lamp[lit=true] run scoreboard players display name modscript4 kart-mod [{translate:"드래프트 끄기: "},{"bold":true,translate:"✔","color":"green"}]

execute if block -7 2 153 minecraft:redstone_lamp[lit=false] run scoreboard players display name modscript9 kart-mod [{translate:"견인 가속 끄기: "},{"bold":true,translate:"❌","color":"dark_red"}]
execute if block -7 2 153 minecraft:redstone_lamp[lit=true] run scoreboard players display name modscript9 kart-mod [{translate:"견인 가속 끄기: "},{"bold":true,translate:"✔","color":"green"}]

execute if block -5 0 153 minecraft:redstone_lamp[lit=false] run scoreboard players display name modscript5 kart-mod [{translate:"톡톡이 모드: "},{"bold":true,translate:"❌","color":"dark_red"}]
execute if block -5 0 153 minecraft:redstone_lamp[lit=true] run scoreboard players display name modscript5 kart-mod [{translate:"톡톡이 모드: "},{"bold":true,translate:"✔","color":"green"}]

execute if block -3 0 155 minecraft:redstone_lamp[lit=false] run scoreboard players display name modscript6 kart-mod [{translate:"갓겜 모드: "},{"bold":true,translate:"❌","color":"dark_red"}]
execute if block -3 0 155 minecraft:redstone_lamp[lit=true] run scoreboard players display name modscript6 kart-mod [{translate:"갓겜 모드: "},{"bold":true,translate:"✔","color":"green"}]

execute if block -3 0 157 minecraft:redstone_lamp[lit=false] run scoreboard players display name modscript7 kart-mod [{translate:"벽 충돌 페널티: "},{"bold":true,translate:"❌","color":"dark_red"}]
execute if block -3 0 157 minecraft:redstone_lamp[lit=true] run scoreboard players display name modscript7 kart-mod [{translate:"벽 충돌 페널티: "},{"bold":true,translate:"✔","color":"green"}]
