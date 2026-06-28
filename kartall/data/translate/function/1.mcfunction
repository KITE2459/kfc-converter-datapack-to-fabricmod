# 이 함수는 text 컴포넌트의 'extra' 필드(리스트)를 받아 번역 가능한 형식으로 변환하는 과정을 시작합니다.
# 대상 엔티티(@s)의 text.extra 필드를 임시 저장소(40planet:value)의 pack_temp1에 복사합니다.
data modify storage 40planet:value data.pack_temp1 set from entity @s text.extra
# pack_temp1의 데이터를 처리할 리스트인 pack_var_temp1로 옮깁니다.
data modify storage 40planet:value data.pack_var_temp1 set from storage 40planet:value data.pack_temp1
# 결과를 저장할 리스트(pack_temp2)를 빈 리스트로 초기화합니다.
data modify storage 40planet:value data.pack_temp2 set value []
# 초기화된 리스트를 최종 결과 리스트인 pack_var_temp2로 옮깁니다.
data modify storage 40planet:value data.pack_var_temp2 set from storage 40planet:value data.pack_temp2
# 임시 저장소를 비웁니다.
data remove storage 40planet:value data.pack_temp5
# 처리할 리스트(pack_var_temp1)의 첫 번째 요소를 임시 저장소에 복사하여 리스트가 비어있는지 확인합니다.
data modify storage 40planet:value data.pack_temp5 set from storage 40planet:value data.pack_var_temp1[0]
# 임시 저장소의 데이터 존재 여부를 스코어보드에 저장합니다. (데이터가 있으면 1 이상)
execute store result score #data.pack_temp6 40planet_num run data get storage 40planet:value data.pack_temp5
# 스코어보드 값을 확인하여 처리할 요소가 있으면(1 이상) 재귀 함수인 pack:0을 호출합니다.
execute if score #data.pack_temp6 40planet_num matches 1.. run function pack:0
# 확인용으로 사용된 스코어보드 값을 제거합니다.
data remove storage 40planet:value data.pack_temp6
# 모든 처리가 완료된 리스트(pack_var_temp2)를 원래의 임시 저장소(asdf:)의 text.extra 필드에 저장합니다.
data modify storage asdf: text.extra set from storage 40planet:value data.pack_var_temp2
