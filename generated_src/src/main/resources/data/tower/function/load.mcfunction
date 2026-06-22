# 스코어보드
scoreboard objectives add blueprint_id dummy
scoreboard objectives add blueprint_seq dummy

# 타워의 식별 번호
scoreboard objectives add tower.id dummy

scoreboard objectives add tower.animation dummy
scoreboard objectives add tower.number dummy

scoreboard objectives add tower.attack_countdown dummy
scoreboard objectives add tower.target_mode dummy
scoreboard objectives add tower.attack dummy
scoreboard objectives add tower.attack_speed dummy

scoreboard objectives add tower.level dummy
scoreboard objectives add tower.y dummy

scoreboard objectives add tower.limit dummy

scoreboard objectives add tower.model.y_sync dummy

# 타워 속성 
scoreboard objectives add tower.attribute.freeze dummy
scoreboard objectives add tower.attribute.freeze_duration dummy
scoreboard objectives add tower.attribute.poison dummy
scoreboard objectives add tower.attribute.flame dummy
scoreboard objectives add tower.attribute.bleed dummy
scoreboard objectives add tower.attribute.stun dummy

# 타워 상태
scoreboard objectives add tower.state.stun dummy

# 상수
scoreboard objectives add tower dummy
scoreboard players set #2 tower 2

# 스토리지

# 다른 load
function tower:bullet/load
function tower:armory/load