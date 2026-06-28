#입력된 데이터가 정수인지 확인
$scoreboard players set #kart-boost-move-temp kartmain $(boosttag)

#정수라면 태그가 같은 end의 데이터를 저장
$data modify entity @s data.boost-transform-end set from entity @n[tag=kart-boost-move-end,tag=$(boosttag)] transformation