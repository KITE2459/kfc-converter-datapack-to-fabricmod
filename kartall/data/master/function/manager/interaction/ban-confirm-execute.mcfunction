# 확정 버튼 클릭 시 실행
# BANNED 태그가 붙은 패널들 밴 처리
execute as @e[type=interaction,tag=banned] run function master:manager/interaction/ban-process-apply-by-tag

# 확정 버튼 제거
function master:manager/interaction/ban-confirm-kill
