# 라운드 종료 처리
# masterstage 증가
# scoreboard players add @a masterstage 1

# 완료된 맵들을 영구 제외 리스트에 추가 (master:config excluded_maps) -> 2편에서 구현 예정

# 다음 라운드 준비 (게임 상태 초기화 - 대기 상태로 설정)
scoreboard players set #game_state master 4

# 잠시 후 init-round 호출 (페이드 아웃 효과 등 있으면 여기서)
# 클리어 메시지나 연출
title @a title [{text:"Master ","color":"gold","bold":true},{"color":"yellow","score":{"name":"clear-data","objective":"masterstage"}}]
title @a subtitle [{text:"Clear!","color":"green"}]

data modify entity @n[tag=master.title,dx=17,dy=4,dz=24,x=-153,y=4,z=283,limit=1,type=text_display] text[0] set value {text:"Master ",bold:true,color:red}

execute positioned -146 5 287 run summon firework_rocket ~ ~ ~ {LifeTime:12,FireworksItem:{id:firework_rocket,count:1b,components:{"minecraft:fireworks":{explosions:[{shape:"small_ball",has_twinkle:true,has_trail:true,colors:[16711680]}]}}}}
execute positioned -146 5 290 run summon firework_rocket ~ ~ ~ {LifeTime:12,FireworksItem:{id:firework_rocket,count:1b,components:{"minecraft:fireworks":{explosions:[{shape:"small_ball",has_twinkle:true,has_trail:true,colors:[16744448]}]}}}}
execute positioned -146 5 293 run summon firework_rocket ~ ~ ~ {LifeTime:12,FireworksItem:{id:firework_rocket,count:1b,components:{"minecraft:fireworks":{explosions:[{shape:"small_ball",has_twinkle:true,has_trail:true,colors:[16776960]}]}}}}
execute positioned -146 5 296 run summon firework_rocket ~ ~ ~ {LifeTime:12,FireworksItem:{id:firework_rocket,count:1b,components:{"minecraft:fireworks":{explosions:[{shape:"small_ball",has_twinkle:true,has_trail:true,colors:[32768]}]}}}}
execute positioned -146 5 299 run summon firework_rocket ~ ~ ~ {LifeTime:12,FireworksItem:{id:firework_rocket,count:1b,components:{"minecraft:fireworks":{explosions:[{shape:"small_ball",has_twinkle:true,has_trail:true,colors:[255]}]}}}}
execute positioned -146 5 302 run summon firework_rocket ~ ~ ~ {LifeTime:12,FireworksItem:{id:firework_rocket,count:1b,components:{"minecraft:fireworks":{explosions:[{shape:"small_ball",has_twinkle:true,has_trail:true,colors:[128]}]}}}}
execute positioned -146 5 305 run summon firework_rocket ~ ~ ~ {LifeTime:12,FireworksItem:{id:firework_rocket,count:1b,components:{"minecraft:fireworks":{explosions:[{shape:"small_ball",has_twinkle:true,has_trail:true,colors:[8388736]}]}}}}

# schedule function master:manager/init-round 3s replace
scoreboard players add clear-data masterstage 1
scoreboard players operation master-file masterstage = clear-data masterstage
execute store result storage master-cleardata:master-data cleared-stage int 1.0 run scoreboard players get clear-data masterstage
schedule function master:manager/round-state-reset 3s
