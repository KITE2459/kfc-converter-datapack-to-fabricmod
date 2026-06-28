#라이센스 데이터 세이브 / 로드
execute store result score clear-data-from-file licensestage run data get storage license-cleardata:license-data cleared-stage 1.0
execute if entity @a[x=-124,y=4,z=233,dx=11,dy=7,dz=9] run function license:check-data

#보스바 설정
bossbar set minecraft:license players @a[scores={licensecount=1..,licensestage=1..}]

#만약 타임어택 준비중인 사람이 있다면
execute as @a at @s if score @s licensecount matches 1.. if score @s licensestage matches 1.. run function license:system/main











