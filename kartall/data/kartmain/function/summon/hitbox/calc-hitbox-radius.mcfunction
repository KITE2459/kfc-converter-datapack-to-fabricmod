    #r
    scoreboard players set @s kart-hitbox-min 2147483647
    scoreboard players operation @s kart-hitbox-min < @s kart-hitbox-left
    scoreboard players operation @s kart-hitbox-min < @s kart-hitbox-right
    scoreboard players operation @s kart-hitbox-min < @s kart-hitbox-front
    scoreboard players operation @s kart-hitbox-min < @s kart-hitbox-rear

    #R
    scoreboard players set @s kart-hitbox-radius 0
    scoreboard players operation @s kart-hitbox-radius > @s kart-hitbox-left
    scoreboard players operation @s kart-hitbox-radius > @s kart-hitbox-right

    scoreboard players set #fr-max kart-hitbox-radius 0
    scoreboard players operation #fr-max kart-hitbox-radius > @s kart-hitbox-front
    #scoreboard players operation #fr-max kart-hitbox-radius > @s kart-hitbox-rear

    #R^2
    scoreboard players operation @s kart-hitbox-radius *= @s kart-hitbox-radius
    scoreboard players operation #fr-max kart-hitbox-radius *= #fr-max kart-hitbox-radius
    scoreboard players operation #fr-max kart-hitbox-radius += @s kart-hitbox-radius

    #제곱근
    scoreboard players set @s kart-hitbox-radius 5
    execute if score #fr-max kart-hitbox-radius matches 25.. run scoreboard players set @s kart-hitbox-radius 6
    execute if score #fr-max kart-hitbox-radius matches 36.. run scoreboard players set @s kart-hitbox-radius 7
    execute if score #fr-max kart-hitbox-radius matches 49.. run scoreboard players set @s kart-hitbox-radius 8
    execute if score #fr-max kart-hitbox-radius matches 64.. run scoreboard players set @s kart-hitbox-radius 9
    execute if score #fr-max kart-hitbox-radius matches 81.. run scoreboard players set @s kart-hitbox-radius 10
    execute if score #fr-max kart-hitbox-radius matches 100.. run scoreboard players set @s kart-hitbox-radius 11
    execute if score #fr-max kart-hitbox-radius matches 121.. run scoreboard players set @s kart-hitbox-radius 12
    execute if score #fr-max kart-hitbox-radius matches 144.. run scoreboard players set @s kart-hitbox-radius 13
    execute if score #fr-max kart-hitbox-radius matches 169.. run scoreboard players set @s kart-hitbox-radius 14
    execute if score #fr-max kart-hitbox-radius matches 196.. run scoreboard players set @s kart-hitbox-radius 15
    execute if score #fr-max kart-hitbox-radius matches 225.. run scoreboard players set @s kart-hitbox-radius 16
    execute if score #fr-max kart-hitbox-radius matches 256.. run scoreboard players set @s kart-hitbox-radius 17
    execute if score #fr-max kart-hitbox-radius matches 289.. run scoreboard players set @s kart-hitbox-radius 18
    execute if score #fr-max kart-hitbox-radius matches 324.. run scoreboard players set @s kart-hitbox-radius 19
    execute if score #fr-max kart-hitbox-radius matches 361.. run scoreboard players set @s kart-hitbox-radius 20
    execute if score #fr-max kart-hitbox-radius matches 400.. run scoreboard players set @s kart-hitbox-radius 21

    #45도 돌린 직선에 사영한 것 구하기
    scoreboard players operation #fr-max kart-hitbox-radius /= #kart2 kartmain

    scoreboard players set @s kart-hitbox-radius-projection 5
    execute if score #fr-max kart-hitbox-radius matches 25.. run scoreboard players set @s kart-hitbox-radius-projection 6
    execute if score #fr-max kart-hitbox-radius matches 36.. run scoreboard players set @s kart-hitbox-radius-projection 7
    execute if score #fr-max kart-hitbox-radius matches 49.. run scoreboard players set @s kart-hitbox-radius-projection 8
    execute if score #fr-max kart-hitbox-radius matches 64.. run scoreboard players set @s kart-hitbox-radius-projection 9
    execute if score #fr-max kart-hitbox-radius matches 81.. run scoreboard players set @s kart-hitbox-radius-projection 10
    execute if score #fr-max kart-hitbox-radius matches 100.. run scoreboard players set @s kart-hitbox-radius-projection 11
    execute if score #fr-max kart-hitbox-radius matches 121.. run scoreboard players set @s kart-hitbox-radius-projection 12
    execute if score #fr-max kart-hitbox-radius matches 144.. run scoreboard players set @s kart-hitbox-radius-projection 13
    execute if score #fr-max kart-hitbox-radius matches 169.. run scoreboard players set @s kart-hitbox-radius-projection 14
    execute if score #fr-max kart-hitbox-radius matches 196.. run scoreboard players set @s kart-hitbox-radius-projection 15
    execute if score #fr-max kart-hitbox-radius matches 225.. run scoreboard players set @s kart-hitbox-radius-projection 16
    execute if score #fr-max kart-hitbox-radius matches 256.. run scoreboard players set @s kart-hitbox-radius-projection 17
    execute if score #fr-max kart-hitbox-radius matches 289.. run scoreboard players set @s kart-hitbox-radius-projection 18
    execute if score #fr-max kart-hitbox-radius matches 324.. run scoreboard players set @s kart-hitbox-radius-projection 19
    execute if score #fr-max kart-hitbox-radius matches 361.. run scoreboard players set @s kart-hitbox-radius-projection 20
    execute if score #fr-max kart-hitbox-radius matches 400.. run scoreboard players set @s kart-hitbox-radius-projection 21












