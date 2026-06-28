data modify storage minecraft:timermain tracktemp2 set value "\n"

#끝 한 글자만 떼기
execute store result score #has-not-line-feed timermain run data modify storage minecraft:timermain tracktemp2 set string storage minecraft:timermain track[0].translate -1

#추가하기
data modify storage minecraft:timermain track-text-result-temp append from storage minecraft:timermain track[0]

#라인 피드를 발견했다면 다음으로 넘기기
execute if score #has-not-line-feed timermain matches 0 run data modify storage minecraft:timermain track-text-result-temp[-1].translate set string storage minecraft:timermain track-text-result-temp[-1].translate 0 -1
execute if score #has-not-line-feed timermain matches 0 run data modify storage minecraft:timermain track-text-result-temp append value " "
execute if score #has-not-line-feed timermain matches 0 run data modify storage minecraft:timermain track-text-result append from storage minecraft:timermain track-text-result-temp
execute if score #has-not-line-feed timermain matches 0 run data modify storage minecraft:timermain track-text-result-temp set value []

data remove storage minecraft:timermain track[0]

#마무리
execute unless data storage minecraft:timermain track[0] run data modify storage minecraft:timermain track-text-result append from storage minecraft:timermain track-text-result-temp
execute if data storage minecraft:timermain track[0] run function timerpack:show-track-on-sidebar/split-by-linefeed
