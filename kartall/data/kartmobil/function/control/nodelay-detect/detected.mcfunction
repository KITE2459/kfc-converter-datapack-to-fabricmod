title @p[tag=kartpassenger] title {translate:"부스터 연타 감지",color:"red"}
title @p[tag=kartpassenger] subtitle {translate:"부스터 키는 꾹 눌러도 발동됩니다!",color:"aqua"}
playsound minecraft:block.anvil.land master @p[tag=kartpassenger] ~ ~ ~ 1 0
tellraw @p[tag=kartpassenger] [{"click_event":{"action":"run_command","command":"/trigger getkart set -1699"},"color":"yellow",translate:"[부스터 키 연타 알림]을 끄려면 이 메시지를 클릭하세요."}]
scoreboard players reset @s kart-nodelay-detect
