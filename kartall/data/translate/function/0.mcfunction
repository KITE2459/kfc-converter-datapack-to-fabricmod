# 이 함수는 pack:1에서 전달된 extra 리스트의 각 요소를 순환하며 처리하는 재귀 함수입니다.
# 처리할 리스트(pack_var_temp1)의 첫 번째 요소를 처리 완료 리스트(pack_var_temp2)에 추가합니다.
data modify storage 40planet:value data.pack_var_temp2 append from storage 40planet:value data.pack_var_temp1[0]
# 임시 저장소에 현재 처리 중인 요소를 저장합니다.
data modify storage 40planet:value data.pack_temp3 set from storage 40planet:value data.pack_var_temp1[0]
# 처리한 요소는 원래 리스트에서 제거합니다.
data remove storage 40planet:value data.pack_var_temp1[0]
# 처리 완료 리스트에 방금 추가된 요소의 "text" 필드를 "translate" 필드로 변경합니다.
data modify storage 40planet:value data.pack_var_temp2[-1].translate set from storage 40planet:value data.pack_var_temp2[-1].text
# 임시 저장소에 변경된 "text" 값을 저장합니다.
data modify storage 40planet:value data.pack_temp4 set from storage 40planet:value data.pack_var_temp2[-1].text
# 기존의 "text" 필드를 제거합니다.
data remove storage 40planet:value data.pack_var_temp2[-1].text
# 임시 저장소를 비웁니다.
data remove storage 40planet:value data.pack_temp5
# 처리할 리스트에 아직 처리할 요소가 남았는지 확인하기 위해 첫 번째 요소를 임시 저장소에 복사합니다.
data modify storage 40planet:value data.pack_temp5 set from storage 40planet:value data.pack_var_temp1[0]
# 임시 저장소의 데이터 존재 여부를 스코어보드에 저장합니다. (데이터가 있으면 1 이상)
execute store result score #data.pack_temp6 40planet_num run data get storage 40planet:value data.pack_temp5
# 스코어보드 값을 확인하여 처리할 요소가 남아있으면(1 이상) 자기 자신(pack:0)을 다시 호출합니다.
execute if score #data.pack_temp6 40planet_num matches 1.. run function pack:0
