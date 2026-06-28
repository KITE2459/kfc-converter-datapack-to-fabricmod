# temp_pool에서 하나 뽑아서 current_targets에 추가
execute store result score #pool_size master run data get storage master:game temp_pool
execute store result score #rnd master run random value 0..2147483646
scoreboard players operation #rnd master %= #pool_size master

# 매크로 호출
data modify storage master:game macro_args.idx set value 0
execute store result storage master:game macro_args.idx int 1 run scoreboard players get #rnd master
function master:manager/pick-target-macro with storage master:game macro_args
