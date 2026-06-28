# 밴 클릭 프로세스 (매크로)
# 인자: {idx: 0}

# 클릭된 엔티티(interaction) 식별 (여기서 @s는 클릭된 interaction 엔티티여야 함)
# 만약 함수 호출부가 @s를 유지한다면 그대로 사용.
# ban.mcfunction에서는 `execute as @s`로 호출하므로 @s는 interaction이다.

# 토글 로직
# 밴 카운트 미리 계산
scoreboard players set #temp_banned_count master 0
execute as @e[type=interaction,tag=master.panel,tag=banned] run scoreboard players add #temp_banned_count master 1

# 1. 이미 BANNED 상태인 경우 (취소)
scoreboard players set #ban-temp master 0
$execute if entity @s[tag=banned] store result score #ban-temp master run function master:manager/interaction/ban-click-toggle-off {idx:$(idx)}

# 2. BANNED 상태가 아닌 경우 (추가) - 단, 현재 밴 카운트가 2 미만일 때만
$execute unless score #ban-temp master matches 1 unless entity @s[tag=banned] if score #temp_banned_count master matches ..1 run function master:manager/interaction/ban-click-toggle-on {idx:$(idx)}

# 3. 밴 카운트 다시 체크 및 확정 버튼 처리 (변경 후 상태 반영)
scoreboard players set #temp_banned_count master 0
execute as @e[type=interaction,tag=master.panel,tag=banned] run scoreboard players add #temp_banned_count master 1

# 2개면 확정 버튼 소환, 아니면 제거
execute if score #temp_banned_count master matches 2 run function master:manager/interaction/ban-confirm-summon
execute unless score #temp_banned_count master matches 2 run function master:manager/interaction/ban-confirm-kill
