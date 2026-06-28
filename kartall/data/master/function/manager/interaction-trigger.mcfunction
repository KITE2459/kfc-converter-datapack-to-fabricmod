# 상호작용 트리거 함수
# interaction 데이터를 처리하고 초기화합니다.

# 클릭 로직 실행
function master:manager/interaction/click

# 상호작용 데이터 초기화 (다시 클릭 가능하도록)
# ban 등으로 entity가 죽었으면 이 명령은 무시됨
data remove entity @s interaction
data remove entity @s attack
