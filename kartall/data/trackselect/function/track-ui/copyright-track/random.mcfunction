function trackselect:trackselect/selectbutton-random-macro with storage minecraft:kartmain

#저작권트랙이 아닌 트랙이 나올때까지 리롤
tag @r add random-aaaaaa
execute store result score is-copyright-content trackselect-map-id run function trackselect:trackselect/copyright-track/is-copyright-track
tag @a remove random-aaaaaa
execute if score is-copyright-content trackselect-map-id matches 1 run function trackselect:trackselect/copyright-track/random

