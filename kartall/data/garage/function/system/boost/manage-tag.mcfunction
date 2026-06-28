
#드리프트 불꽃 커스텀용
tag @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models,tag=drift-effect] add kart-bypass-tag

#태그 삭제 무시
tag @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models,tag=kart-boost-block] add kart-bypass-tag
tag @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models,tag=kart-boost-flame] add kart-bypass-tag

#태그 삭제 처리
execute as @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models] run data merge entity @s {teleport_duration:1}
execute as @e[distance=..0.01,type=#kartmobil:kartmodels,tag=!kartmobil,tag=!shadow-models] run data merge entity @s[tag=!kart-bypass-tag] {Tags:[""]}
