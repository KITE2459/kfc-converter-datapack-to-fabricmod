# 개요
- 당신은 해당 타워디펜스 데이터팩의 일부 기능을 개발하는 데이터팩 개발자입니다.
- 당신이 개발해야 하는 기능은 UI에 해당하며, siderbar에 정보가 올바르게 표기되도록 개발해야 합니다.

# 주요 기능
- 당신은 사이드바에 여러 정보를 표기해야 합니다.
- 정보 샘플은 다음과 같습니다
[제목]:   소론
[1번 줄]: 맵 정보
[2번 줄]: 저지선 (맵 이름)
[3번 줄]: 쉬움 (맵 난이도)
[4번 줄]: 공백
[5번 줄]: 라운드 정보
[6번 줄]: 라운드 1/10 (현재 라운드/총 라운드)
[7번 줄]: 남은 몹 수: 20 (남은 몹 수)
[8번 줄]: 00:30 (남은 시간)

# 개발 지침
- 사이드바에 정보를 표기하기 위해서는 Minecraft의 scoreboard 기능을 활용해야 합니다
- 스코어보드의 기본 세팅은 다음과 같이 초기화됩니다:
scoreboard objectives remove statistics
scoreboard objectives add statistics dummy
scoreboard objectives setdisplay sidebar statistics
scoreboard objectives modify statistics numberformat blank
scoreboard objectives modify statistics displayname {text:"저지선",bold:true,color:green}
- 제목은 scoreboard objectives modify statistics displayname {text:"[제목]",bold:true,color:green} 형식으로 설정할 수 있습니다.
- 각 줄의 정보는 해당하는 스코어보드의 numberformat을 blank로 설정한 후, scoreboard players display를 통해 텍스트를 변경하여 표기해야 합니다.
- 맵 이름은 맵 데이터를 불러올 때 스토리지의 map_name 값을 활용하여 표기할 수 있습니다.
- 맵 난이도는 맵 데이터를 불러올 때 스토리지의 difficulty 값을 활용하여 표기할 수 있습니다. 무한 모드의 경우에는 "무한"으로 표기해야 합니다.
- 라운드 정보는 현재 라운드와 총 라운드를 스토리지의 round 배열의 길이를 활용하여 표기할 수 있습니다. 무한 모드의 경우에는 총 라운드를 표기하지 않습니다.
- 남은 몹 수는 존재하는 enemy.core 엔티티의 수를 세어서 표기해야 합니다.
- 남은 시간은 좀 특수하게 측정해야 하는데, 몹이 전부 소환된 이후 30초 보너스 시간이 주어지므로 그것까지 고려해서 남은 시간을 계산해야 합니다.