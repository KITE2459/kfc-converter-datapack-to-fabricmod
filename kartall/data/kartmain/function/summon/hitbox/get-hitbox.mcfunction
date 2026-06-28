
    #scoreboard players remove @s kart-hitbox-front 1
    #scoreboard players remove @s kart-hitbox-rear 1
    #scoreboard players remove @s kart-hitbox-left 1
    #scoreboard players remove @s kart-hitbox-right 1

    #최댓값 구하기
    scoreboard players set #max-length kart-hitbox-left 0
    scoreboard players operation #max-length kart-hitbox-left > @s kart-hitbox-left
    scoreboard players operation #max-length kart-hitbox-left > @s kart-hitbox-right
    scoreboard players operation #max-length kart-hitbox-left > @s kart-hitbox-front
    scoreboard players operation #max-length kart-hitbox-left > @s kart-hitbox-rear
    
    execute if score #max-length kart-hitbox-left matches 11..13 run tag @s add kart-hitbox-normal
    execute if score #max-length kart-hitbox-left matches 9..10 run tag @s add kart-hitbox-mid
    execute if score #max-length kart-hitbox-left matches ..8 run tag @s add kart-hitbox-small

    #하트박스 최대
    scoreboard players set #lr-max kart-hitbox-radius 0
    scoreboard players operation #lr-max kart-hitbox-radius > @s kart-hitbox-left
    scoreboard players operation #lr-max kart-hitbox-radius > @s kart-hitbox-right

    scoreboard players set #fr-max kart-hitbox-radius 0
    scoreboard players operation #fr-max kart-hitbox-radius > @s kart-hitbox-front
    scoreboard players operation #fr-max kart-hitbox-radius > @s kart-hitbox-rear

    #양옆이 9보다 넓으면 출부가 끝나기 전까지 2배로 줄여버림
    execute if score #lr-max kart-hitbox-radius matches 9.. run tag @s add kart-wide-hitbox

        #질량
        #scoreboard players operation @s kart-weight = @s kart-hitbox-left
        #scoreboard players operation @s kart-weight += @s kart-hitbox-right

        #scoreboard players operation #temp kart-weight = @s kart-hitbox-front
        #scoreboard players operation #temp kart-weight += @s kart-hitbox-rear

        #scoreboard players operation @s kart-weight *= #temp kart-weight