#[b, c, d, a]를 [a, -d, c, -b]
#[b,c,d,a]⟶[a,−c,b,d]

#백업하고 자기 자신의 transform을 연산에 사용
data modify storage kartmain transformation-backup set from entity @s transformation


data modify storage kartmain left-rotation set value [0f, 0f, 0f, 0f]

data modify storage kartmain left-rotation[0] set from storage kartmain transformation-backup.left_rotation[3]
    
    # 리스트 셋팅
    data modify storage kartmain list set value [0f,0f,0f,0f, 0f,0f,0f,0f, 0f,0f,0f,0f, 0f,0f,0f,-1f]
    data modify storage kartmain list[3] set from storage kartmain transformation-backup.left_rotation[2]
    # 계산
    data modify entity @s transformation set from storage kartmain list
    data modify storage kartmain left-rotation[1] set from entity @s transformation.translation[0]

data modify storage kartmain left-rotation[2] set from storage kartmain transformation-backup.left_rotation[1]

    # 리스트 셋팅
    data modify storage kartmain list set value [0f,0f,0f,0f, 0f,0f,0f,0f, 0f,0f,0f,0f, 0f,0f,0f,-1f]
    data modify storage kartmain list[3] set from storage kartmain transformation-backup.left_rotation[0]
    # 계산
    data modify entity @s transformation set from storage kartmain list
    data modify storage kartmain left-rotation[3] set from entity @s transformation.translation[0]

#평행이동 계산
data modify storage kartmain translation set value [0f, 0f, 0f]
data modify storage kartmain translation[0] set from storage kartmain transformation-backup.translation[0]

    # 리스트 셋팅
    data modify storage kartmain list set value [0f,0f,0f,0f, 0f,0f,0f,0f, 0f,0f,0f,0f, 0f,0f,0f,-1f]
    data modify storage kartmain list[3] set from storage kartmain transformation-backup.translation[1]
    # 계산
    data modify entity @s transformation set from storage kartmain list
    data modify storage kartmain translation[1] set from entity @s transformation.translation[0]

    # 리스트 셋팅
    data modify storage kartmain list set value [0f,0f,0f,0f, 0f,0f,0f,0f, 0f,0f,0f,0f, 0f,0f,0f,-1f]
    data modify storage kartmain list[3] set from storage kartmain transformation-backup.translation[2]
    # 계산
    data modify entity @s transformation set from storage kartmain list
    data modify storage kartmain translation[2] set from entity @s transformation.translation[0]

data modify entity @s transformation set from storage kartmain transformation-backup
data modify entity @s transformation.left_rotation set from storage kartmain left-rotation
data modify entity @s transformation.translation set from storage kartmain translation