# 재귀적으로 7번 실행하여 랜덤 맵 선택
# pool에서 하나 뽑아서 candidates에 추가하고 pool에서 제거

# pool 크기 확인
execute store result score #pool_size master run data get storage master:game pool

# pool이 비어있으면 중단 (예외처리)
execute if score #pool_size master matches 0 run return 0

# 랜덤 인덱스 생성 (0 ~ size-1)
execute store result score #rnd master run random value 0..2147483646
scoreboard players operation #rnd master %= #pool_size master

# 해당 인덱스의 값을 candidates에 추가
data modify storage master:game temp_val set from storage master:game pool[]
# (매크로를 사용하여 인덱스 접근 필요 - 혹은 리스트 맨 앞/뒤로 이동시켜서 처리)
# 단순화를 위해 매크로 사용

# 매크로용 데이터 설정
data modify storage master:game macro_args.idx set value 0
execute store result storage master:game macro_args.idx int 1 run scoreboard players get #rnd master
function master:manager/pick-single-macro with storage master:game macro_args

# 반복 카운트
scoreboard players add #i master 1
execute if score #i master matches ..6 run function master:manager/pick-7-random
