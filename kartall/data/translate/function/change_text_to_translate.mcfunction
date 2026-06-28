execute if data entity @s text.text run data modify storage translate:buffer texts append from entity @s text
# 대상 엔티티(@s)의 text 컴포넌트를 임시 저장소(asdf:)에 복사합니다.
data modify storage asdf: text set from entity @s text
# 만약 text 컴포넌트에 'extra' 필드가 존재하면, pack:1 함수를 호출하여 하위 컴포넌트들을 처리합니다.
execute if data entity @s text.extra run function pack:1
# text 필드의 값을 translate 필드로 옮깁니다. (e.g., {"text":"key"} -> {"translate":"key"})
data modify storage asdf: text.translate set from storage asdf: text.text
# 기존의 text 필드를 제거합니다.
data remove storage asdf: text.text
# 변환된 text 컴포넌트를 다시 대상 엔티티(@s)에 적용합니다.
data modify entity @s text set from storage asdf: text