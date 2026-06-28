#execute at @s로 시작하는 모델 지원을 위함

summon marker ~ ~-0.5 ~ {Tags:["garage-0.5"]}
$execute as @n[tag=garage-0.5] at @s run function kartmodel:$(model)
kill @e[tag=garage-0.5]
kill @e[tag=drift-effect,distance=..0.7]
