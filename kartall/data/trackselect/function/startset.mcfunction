scoreboard objectives add trackselect-map-id dummy
scoreboard objectives add trackselect-game-id dummy
scoreboard objectives add trackselect-lap dummy

bossbar add trackselect {translate:"1분 안에 트랙을 선택하세요","color":"yellow"}
bossbar set trackselect max 1200
bossbar set trackselect color yellow


#초기화
data modify storage track-list themes set value []
data remove storage track-list tracks

#테마 정의
data modify storage track-list themes append value {key:"forest",text:{translate:"포레스트","color":"green"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdkYTRhYTQ4MGNjNmFmNWI4MDUzZTA5NjA1ZGM3MWI4NjkxMWQyNGRhOGE4YzBlNDM5MmE1NzU2NDZhYTZjMSJ9fX0="}
data modify storage track-list themes append value {key:"desert",text:{translate:"사막","color":"yellow"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ0Zjk5MDc3OGQ3NjdlYTkyZWJiNzc5YjBiN2M1NDJiMjEyYTE4OTIwMmExY2E4ZTk1YzY5ZmUxYjcwYjVkZCJ9fX0="}
data modify storage track-list themes append value {key:"village",text:{translate:"빌리지","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4MjEzODY0YzIyZDdjYTMyZWQ1MDhjMWUxMjE3ODhlNzRlYzFiY2JlN2ZjYzIxZDM3ZDgzNmMyYTUwNmM4ZCJ9fX0="}
data modify storage track-list themes append value {key:"ice",text:{translate:"아이스","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTgxYjRmNWYzZDk5NjcyYWIxYTM5ZjVmNDEwYmQ0ZDRmZDkyNTQ4ZTA3ZDE3YmRmMGQ0ZjFmNTdkY2ViYjVjZiJ9fX0="}
data modify storage track-list themes append value {key:"tomb",text:{translate:"공동묘지","color":"red"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQwNjYyOGNjZGVmMjZhN2E5OGQ4ODlkZmQyOTczMzQxYWI5ZjM1NGIwN2VjYzg3MTViMDQxZGUwNzI0OGQ0In19fQ=="}
data modify storage track-list themes append value {key:"mine",text:{translate:"광산","color":"light_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2YyZDM4ZWZhZTFlMGE1ODFmMjU4YWEwOWNhNjEwNzQ5ZDNlZmRkZjU4NjUzMDRmMmZhMThiN2ExYTBjM2JjZCJ9fX0="}

data modify storage track-list themes append value {key:"planet",text:{translate:"노르테유","color":"light_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg4YmZkNjMyYjIwOGQxNTk4N2VmNmVmOTI2OTJjMGMxYWQwZWQ4OWJkMDVjMjg0ZTViZDA4MjU3YWRhMDUzYSJ9fX0="}
data modify storage track-list themes append value {key:"factory",text:{translate:"팩토리","color":"yellow"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmVkZDI0ODI1YzIxNzMwM2YyNmFmNTUyNjU3MGEyMTRhZWMyOWI5Mjk3YTY4NzcxNTFiYTUwZmNlZGZmN2ZmMSJ9fX0="}
data modify storage track-list themes append value {key:"pirate",text:{translate:"해적","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UxNzRjNDkyZDYzYWZlNzFiNDg0NmRlYWNhZjliNTU1YzgxNjYzMzc0N2UxNDY3YjY3YWZjMDhkMmE3NmQwYyJ9fX0="}
data modify storage track-list themes append value {key:"story",text:{translate:"동화","color":"green"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI3OWEyNTE0ODAwZTU5MmE2YWMzYmQ1ZjUwNTM5YTk5OTRmMWI2MTI2MWQxMGUyYTRlNjA3YzI4MjhjMWMyYyJ9fX0="}
data modify storage track-list themes append value {key:"moonhill",text:{translate:"문힐시티","color":"dark_blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzkzNTUzNzY2YzVkNzc4OWJkZmM5ZTE5NWZiYmIxM2RjODVkNzI2ZWY3M2M5NTM1ZjRhN2IwOTBhNjExZTg4OCJ9fX0="}
data modify storage track-list themes append value {key:"gold",text:{translate:"황금문명","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2JkNDE4OTZkNWQ4YTI5OTRhMGZjNmU4NTFmMjA3ZDgxZGRlZmI3ZTExMDRlNDcwZTRiNmU5Mjc2NWIzOWRiZiJ9fX0="}

data modify storage track-list themes append value {key:"china",text:{translate:"차이나","color":"red"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5YmMwMzVjZGM4MGYxYWI1ZTExOThmMjlmM2FkM2ZkZDJiNDJkOWE2OWFlYjY0ZGU5OTA2ODE4MDBiOThkYyJ9fX0="}
data modify storage track-list themes append value {key:"mansion",text:{translate:"대저택","color":"red"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzExZGI1ZGZmY2VhNzQ3ZDUzODYyNTRmYzc1MTY3MWU0MTI4OGU1NmU3MTdmNTBiZmE3MDBjMzVmMjIzNTFkZCJ9fX0="}
data modify storage track-list themes append value {key:"wkc",text:{translate:"WKC","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2UxODlhMTQ5MTFjN2ZmMmE3NzBjZTgzMDhkNDBiNzRmYmJmMTI4ZTBhNThmYWJmOGZmYTI1NjllMTQzZTgxMyJ9fX0="}
data modify storage track-list themes append value {key:"beach",text:{translate:"비치","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZlOWEyMTQwZjQwZjVkN2NkMjRkZmJhZjQ1NjdmYTRhYmI3YzU1ZTJhMDYyZmJiNzUyMGE3MzczYzQxMmE3ZCJ9fX0="}
data modify storage track-list themes append value {key:"dino",text:{translate:"쥐라기","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJkODExZDA2NDM0ZWJmMWFlY2ExMDE5Yzc0ZDU5ZjY3YjI2YmRkOWNmOGQ3ZGE3YzQ1MzdkNGQwNDVlNTU5In19fQ=="}
data modify storage track-list themes append value {key:"world",text:{translate:"월드","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGVhOThlMmZiNTYzODNmMzE2Nzg2YTI0NjI2MjhmZGU4ODVhYmM3NmRjMGU1Y2JhZTI1Nzg1ZTBkMTNhZWRjZSJ9fX0="}
data modify storage track-list themes append value {key:"nemo",text:{translate:"네모","color":"green"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk1NGYwNTcyNWQyODZkZjBiMjQ3YzUzOGI2ODlmZTQ3OWJhNDg2NjMxZWU4NTRlYjI1MjNlOTI0OWM3Zjg2OCJ9fX0="}

data modify storage track-list themes append value {key:"sword",text:{translate:"도검","color":"white"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzI3MmUwMDgwZGM3Yjc0ZWQ5ZmVhZDI2MDdkYmM4Y2VlMzJiNGY0MTM4NWUxZGYzZWQ1NDg1Yzk5ODM0MDM2MyJ9fX0="}
data modify storage track-list themes append value {key:"god",text:{translate:"신화","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg2NmVmNzFmYjc2MWUwN2UxMDg0MTQzY2I4Njk2MDY3NGIxOGYwODgxYTExOTBjZjE1YThlMjFkOWRkOTU1NiJ9fX0="}
data modify storage track-list themes append value {key:"abyss",text:{translate:"어비스","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjI0OTlkYjgwY2FiNWMwMzFlYTg5YzAyNWM3M2M4ZmI5ZmI0ZmYyYjUyM2I2YWMxYzRlZDE5NGMzMGEyYTdlYiJ9fX0="}
data modify storage track-list themes append value {key:"korea",text:{translate:"코리아","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzc0ZDgxN2I3NGZlY2MzMTI2NTI5ZjAyZWQ4YzQxMjNmNGFlN2MxODczNzBlYjFkZWNkNzIyZGMxZDkxODVmMSJ9fX0="}
data modify storage track-list themes append value {key:"maple",text:{translate:"메이플","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBkYTMyMjEwNGQ1ZjJiOWI1M2ZiZTBmMTQzNmNmYTUzMTcwMmQzMjZhNzJiZGQwY2M0MTY2YzE4ZDUxNDA1YiJ9fX0="}
data modify storage track-list themes append value {key:"kauzee",text:{translate:"카우지","color":"light_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjYxZWY2MmEzNTk5NGRlNmRiOWVmNDEwM2NlZTc3N2Y4MGM1MjQ1MTMwYWYxZmM5NTYyNGQyZmZiNmRjYzc0MSJ9fX0="}
data modify storage track-list themes append value {key:"minecraft",smalltext:1,text:{translate:"마인크래프트","color":"green"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQwZjIzOTFlNzFkZjU2ZDhmODgxMGU4MDM5ZmNlZDViMmUzNTM5NGY0MGRlNmZlY2Y2NDRiZGUyZmZiMDE3ZCJ9fX0="}

#data modify storage track-list themes append value {key:"alpha",text:{translate:"알파","color":"gold"},icon:"minecraft:test_block"}
data modify storage track-list themes append value {key:"mariokart",text:{translate:"마리오카트","color":"red"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg5ZmI4NzRjZmQ1ZWRiMjY1M2U4Y2Q4MDRhYTQwM2M1MWYyZWVjYWQzMmI4OWM1YmI1OTE2OGYwYTIzNGE1MiJ9fX0="}
data modify storage track-list themes append value {key:"sonic",text:{translate:"소닉","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGJkNWY5MzE4NTZjOGFkNzJiZWM0ODJiNTYwYjEzNTY3MGU4ZWIzYmJkMzM5ZGRhNTg5M2YyMTE2MGYwYjAyIn19fQ=="}
data modify storage track-list themes append value {key:"thirdparty",text:{translate:"서드파티","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVkZTcwMTMwMmE3MjNlYjAxMTJhNmY2OTE0NWNlYWVkOTdlMmMwN2I4MThiMmVjMjE0ZGQ3NTJmYWFlYzFmMCJ9fX0="}

data modify storage track-list themes append value {key:"updown",updown:1,smalltext:1,text:{translate:"UP & DOWN","color":"light_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2YxMTRiZWM2YTZmZGRlYTM3ZDJiODc2N2FjZTI2YjBkMTgyODMwNzVmZmQ4NWQ1Njg3YzU1YTdiNGYxZThjIn19fQ=="}
data modify storage track-list themes append value {key:"updowncrossover",updown:1,smalltext:1,carriagereturn:1,text:{translate:"UP & DOWN\n크로스오버","color":"dark_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWQxMjU3OTE1MDI5YTY1OTVkZjI4OTg2MThjMTg0ZWI0Zjc1NzFiYjUwYjRiYjNjYzY3NGQ2N2M3ZDVmZmE0YSJ9fX0="}

data modify storage track-list themes append value {key:"random",random:1,text:{translate:"랜덤","color":"light_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE5ZDI4YTg2MzJmYTRkODdjYTE5OWJiYzJlODhjZjM2OGRlZGQ1NTc0NzAxN2FlMzQ4NDM1NjlmN2E2MzRjNSJ9fX0="}

##최신 트랙: 148 네모 메디아
#UP & DOWN 최신 트랙: 1008 윷놀이
#UP & DOWN 크로스오버 최신 트랙: -1008 어숨바

#일반 모드: 1번 이상 1000번 이하
#UP & DOWN: 1001번 이상
#UP & DOWN 크로스오버: -1001번 이하

# 난이도순 | (원작, 원작 리버스, 오리지널, 오리지널 리버스) 순서로 나열
# 알파 트랙은 새 트랙의 다음에 배치


#forceload 꼭 넣어라ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ
#트랙 번호-load 파일 만들고 범위 잘 지정해서 forceload 하기

#포레스트
data modify storage track-list tracks.forest append value {bgm:"forest",text:[{translate:"포레스트\n","color":"green"},{translate:"기암괴석","color":"aqua"}],number:17,lap:3,pos:"-893 19 271 0 0",creator:{translate:"By Kation","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.forest append value {bgm:"forest",text:[{translate:"포레스트\n","color":"green"},{translate:"통나무","color":"aqua"}],number:3,lap:3,pos:"-689 46 876 0 0",creator:{translate:"By GhangDhang","color":"gold"},difficulty:{translate:"★☆☆☆☆","color":"yellow"}}
data modify storage track-list tracks.forest append value {bgm:"forest2",text:[{translate:"포레스트\n","color":"green"},{translate:"아슬아슬 점프","color":"aqua"}],number:53,lap:3,pos:"875 4 -1368 180 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.forest append value {bgm:"forest3",text:[{translate:"포레스트\n","color":"green"},{translate:"지그재그","color":"aqua"}],number:33,lap:2,pos:"-424 78 -1550 -90 0",creator:{translate:"By bobjihoo","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.forest append value {bgm:"forest4",text:[{translate:"포레스트\n","color":"green"},{translate:"아찔한 다운힐","color":"aqua"}],number:110,lap:1,pos:"10000 153 -4999 180 0",creator:{translate:"By HITE_yt","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.forest append value {bgm:"forest4",text:[{translate:"포레스트\n","color":"green"},{translate:"오싹한 공중다리","color":"aqua"}],number:133,lap:2,pos:"6000 98 -10000 180 0",creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.forest append value {bgm:"forest3",text:[{translate:"포레스트\n","color":"green"},{translate:"통곡의 절벽","color":"aqua"}],number:34,lap:2,pos:"8999 49 9000 -90 0",creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}

#사막
data modify storage track-list tracks.desert append value {bgm:"desert3",text:[{translate:"사막\n","color":"yellow"},{translate:"피라미드 탐험","color":"aqua"}],number:47,lap:3,pos:"1998 5 0 0 0",creator:{translate:"By Physical","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.desert append value {bgm:"desert",text:[{translate:"사막\n","color":"yellow"},{translate:"놀라운 공룡 유적지","color":"aqua"}],number:54,lap:2,pos:"1981 4 3019 90 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.desert append value {bgm:"desert2",text:[{translate:"사막\n","color":"yellow"},{translate:"빙글빙글 공사장","color":"aqua"}],number:4,lap:2,pos:"50.0 4 1093 180 0",creator:{translate:"By GhangDhang","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.desert append value {bgm:"desert2",text:[{translate:"[R] 사막\n","color":"yellow","italic":true},{translate:"빙글빙글 공사장","color":"aqua","italic":true}],number:89,lap:2,pos:"5095 4 -49 0 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#빌리지 #61부터 시작
data modify storage track-list tracks.village append value {bgm:"village",text:[{translate:"빌리지\n","color":"blue"},{translate:"운하","color":"aqua"}],number:90,lap:3,pos:"4000 62 -8000 -90 0",creator:{translate:"By HITE_yt","color":"gold"},difficulty:{translate:"★☆☆☆☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village5",text:[{translate:"빌리지\n","color":"blue"},{translate:"시계탑","color":"aqua"}],number:85,lap:3,pos:"4999 36 -8000 90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village5",text:[{translate:"빌리지\n","color":"blue"},{translate:"돌아올 수 없는 지름길","color":"aqua"}],number:125,lap:3,pos:"9999 99 -6001 180 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village3",text:[{translate:"빌리지\n","color":"blue"},{translate:"손가락","color":"aqua"}],number:2,lap:3,pos:"291 4 274 90 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village",text:[{translate:"빌리지\n","color":"blue"},{translate:"손가락 2","color":"aqua"}],number:48,lap:2,pos:"4040 4 1941 -90 0",creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village2",text:[{translate:"빌리지\n","color":"blue"},{translate:"남산","color":"aqua"}],number:26,lap:2,pos:"6510 5 4126 -90 0",creator:{translate:"By Dominogames0229","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}

data modify storage track-list tracks.village append value {bgm:"village2a",text:[{translate:"빌리지\n","color":"blue"},{translate:"고가의 질주","color":"aqua"}],number:112,lap:2,pos:"4001 92 10000 -90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village2",text:[{translate:"[","color":"blue"},{translate:"α","color":"gold"},{translate:"] 빌리지\n","color":"blue"},{translate:"고가의 질주","color":"aqua"}],number:1,lap:2,pos:"-221 4 80 0 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village2a",text:[{translate:"빌리지\n","color":"blue"},{translate:"운명의 다리","color":"aqua"}],number:64,lap:2,pos:"1010.0 29 -8949 0 0",creator:{translate:"By KITE2459","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village3",text:[{translate:"빌리지\n","color":"blue"},{translate:"붐힐터널","color":"aqua"}],number:61,lap:1,pos:"-1000 31 1000 0 0",creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}} 
data modify storage track-list tracks.village append value {bgm:"village4",text:[{translate:"빌리지\n","color":"blue"},{translate:"붐힐 드라이브","color":"aqua","bold":true}],number:96,lap:1,pos:"10013 62 -2183 90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"license",text:[{translate:"빌리지\n","color":"blue"},{translate:"뱅크 트랙","color":"aqua"}],number:101,lap:3,pos:"7999 71 -8000 90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★☆☆☆☆","color":"yellow"}}

data modify storage track-list tracks.village append value {bgm:"village5",text:[{translate:"[R] 빌리지\n","color":"blue","italic":true},{translate:"시계탑","color":"aqua","italic":true}],number:115,lap:3,pos:"8000 36 -9000 -90 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village2",text:[{translate:"[R] 빌리지\n","color":"blue","italic":true },{translate:"고가의 질주","color":"aqua","italic":true}],number:113,lap:2,pos:"6000 92 -9000 90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.village append value {bgm:"village3",text:[{translate:"[R] 빌리지\n","color":"blue","italic":true },{translate:"붐힐 터널","color":"aqua","italic":true}],number:126,lap:1,pos:"-6000 99 -10001 180 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
#data modify storage track-list tracks.village append value {bgm:"license",text:[{translate:"빌리지\n","color":"blue"},{translate:"테스트 트랙","color":"aqua"}],number:999,lap:1,pos:"419 4 -118 -90 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★★★★★★★★★★★★★★★","color":"yellow"},etc:["function etc:lol"]}


#아이스
data modify storage track-list tracks.ice append value {bgm:"ice",text:[{translate:"아이스\n","color":"blue"},{translate:"신나는 하프파이프","color":"aqua"}],number:10,lap:3,pos:"9082 61 3865 180 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.ice append value {bgm:"ice2",text:[{translate:"아이스\n","color":"blue"},{translate:"360 타워","color":"aqua"}],number:117,lap:3,pos:"2958 129 -10039 90 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.ice append value {bgm:"ice2",text:[{translate:"아이스\n","color":"blue"},{translate:"익스트림 경기장","color":"aqua"}],number:86,lap:2,pos:"-3910 55 960 180 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.ice append value {bgm:"ice5",text:[{translate:"아이스\n","color":"blue"},{translate:"갈라진 빙산","color":"aqua"}],number:68,lap:3,pos:"-4838 10 -5733 180 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.ice append value {bgm:"ice4",text:[{translate:"아이스\n","color":"blue"},{translate:"부서진 빙산","color":"aqua"}],number:59,lap:2,pos:"1000 19 -8001 0 0",creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.ice append value {bgm:"ice3",text:[{translate:"아이스\n","color":"blue"},{translate:"설산 다운힐","color":"aqua"}],number:73,lap:2,pos:"5000 218 8000 90 0",creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}

data modify storage track-list tracks.ice append value {bgm:"ice2",text:[{translate:"아이스\n","color":"blue"},{translate:"버려진 연습장","color":"aqua"}],number:38,lap:5,pos:"1108 4 -1105 -90 0",creator:{translate:"By anotherone_yt","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.ice append value {bgm:"bgmice6",text:[{translate:"아이스\n","color":"blue"},{translate:"아이스리온 랜드","color":"aqua","bold":true}],number:102,lap:1,pos:"-7933 10 8256 180 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}


#공동묘지
data modify storage track-list tracks.tomb append value {bgm:"tomb",text:[{translate:"공동묘지\n","color":"red"},{translate:"공포의 외길","color":"aqua"}],number:42,lap:3,pos:"-1948 71 1951 180 0",etc:["time set 18000"],creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.tomb append value {bgm:"tomb2",text:[{translate:"공동묘지\n","color":"red"},{translate:"마왕의 초대","color":"aqua"}],number:44,lap:2,pos:"-2973 69 -1935 90 0",etc:["time set 13500"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.tomb append value {bgm:"tomb3",text:[{translate:"공동묘지\n","color":"red"},{translate:"어둠의 박쥐성","color":"aqua"}],number:120,lap:3,pos:"5184 97 10252 180 0",etc:["time set 18000"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.tomb append value {bgm:"tomb2",text:[{translate:"[R] 공동묘지\n","color":"red","italic":true},{translate:"마왕의 초대","color":"aqua","italic":true}],number:119,lap:2,pos:"-2001 73 9998 -90 0",etc:["time set 13500"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.tomb append value {bgm:"tomb3",text:[{translate:"공동묘지\n","color":"red"},{translate:"망자의 절벽","color":"aqua"}],number:121,lap:1,pos:"-3001 100 -10004 90 0",etc:["time set 18000"],creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}


#광산
data modify storage track-list tracks.mine append value {bgm:"mine2",text:[{translate:"광산\n","color":"light_purple"},{translate:"아슬아슬 궤도전차","color":"aqua"}],number:29,lap:3,pos:"4906 32 5787 -90 0",creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.mine append value {bgm:"mine3",text:[{translate:"광산\n","color":"light_purple"},{translate:"꼬불꼬불 다운힐","color":"aqua"}],number:5,lap:1,pos:"-31 106 -1377 -90 0",creator:{translate:"By GhangDhang","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.mine append value {bgm:"mine3",text:[{translate:"광산\n","color":"light_purple"},{translate:"위험한 제련소","color":"aqua"}],number:65,lap:2,pos:"5000 138 2999 0 0",creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.mine append value {bgm:"mine4",text:[{translate:"광산\n","color":"light_purple"},{translate:"광물 컨베이어 벨트","color":"aqua"}],number:136,lap:2,pos:"1 91 10004 0 0",etc:["effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.mine append value {bgm:"mine2",text:[{translate:"[R] 광산\n","color":"light_purple","italic":true},{translate:"광물 컨베이어 벨트","color":"aqua","italic":true}],number:138,lap:2,pos:"1 91 -4003 180 0",etc:["effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}


#노르테유
data modify storage track-list tracks.planet append value {bgm:"planet2",text:[{translate:"노르테유\n","color":"light_purple"},{translate:"허공의 갈림길","color":"aqua"}],number:25,lap:3,pos:"1430 138 228 -90 0",creator:{translate:"By fourgod","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.planet append value {bgm:"planet2",text:[{translate:"노르테유\n","color":"light_purple"},{translate:"붕붕 점프","color":"aqua"}],number:27,lap:3,pos:"753 159 -413 -90 0",etc:["time set 18000"],creator:{translate:"By Kation","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.planet append value {bgm:"planet3",text:[{translate:"노르테유\n","color":"light_purple"},{translate:"전투비행장","color":"aqua"}],number:67,lap:2,pos:"1999 134 -5999.0 -90 0",etc:["time set 18000"],creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.planet append value {bgm:"planet",text:[{translate:"노르테유\n","color":"light_purple"},{translate:"익스프레스","color":"aqua"}],number:99,lap:1,pos:"4998 66 -9000 90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.planet append value {bgm:"planet",text:[{translate:"[","color":"light_purple"},{translate:"α","color":"gold"},{translate:"] 노르테유\n","color":"light_purple"},{translate:"익스프레스","color":"aqua"}],number:9,lap:1,pos:"1418 155 17 -90 0",creator:{translate:"By GhangDhang","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.planet append value {bgm:"planet3",text:[{translate:"[R] 노르테유\n","color":"light_purple","italic":true},{translate:"익스프레스","color":"aqua","italic":true}],number:106,lap:1,pos:"3001 20 9999 -150 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}


#팩토리
data modify storage track-list tracks.factory append value {bgm:"factory3",text:[{translate:"팩토리\n","color":"yellow"},{translate:"부비트랩 공장 탐험","color":"aqua"}],number:70,lap:3,pos:"-1898 32 597 90 0",etc:["time set day"],creator:{translate:"By Koral","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.factory append value {bgm:"factory5",text:[{translate:"팩토리\n","color":"yellow"},{translate:"두개의 공장","color":"aqua"}],number:63,lap:2,pos:"4001 33 -4000 0 0",etc:["time set 12500"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.factory append value {bgm:"factory2",text:[{translate:"팩토리\n","color":"yellow"},{translate:"미완성 5구역","color":"aqua"}],number:23,lap:1,pos:"2974 30 1995 180 0",etc:["time set 12500"],creator:{translate:"By Kation","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.factory append value {bgm:"factory4",text:[{translate:"팩토리\n","color":"yellow"},{translate:"브로디 비밀의 연구소","color":"aqua"}],number:72,lap:2,pos:"3000 125 1000 180 0",etc:["time set noon"],creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.factory append value {bgm:"factory",text:[{translate:"팩토리\n","color":"yellow"},{translate:"모험의 시작","color":"aqua"}],number:122,lap:3,pos:"5001 82 -7000 90 0",etc:["effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.factory append value {bgm:"factory5",text:[{translate:"[R] 팩토리\n","color":"yellow","italic":true},{translate:"미완성 5구역","color":"aqua","italic":true}],number:137,lap:1,pos:"0 32 7001 0 0",etc:["time set 12500"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.factory append value {bgm:"factory",text:[{translate:"[R] 팩토리\n","color":"yellow","italic":true},{translate:"모험의 시작","color":"aqua","italic":true}],number:141,lap:3,pos:"10007 82 -1000 -90 0",etc:["effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}


#동화
data modify storage track-list tracks.story append value {bgm:"pretion1",text:[{translate:"동화\n","color":"green"},{translate:"잠자는 숲속의 거인","color":"aqua"}],number:128,lap:2,pos:"-2000 145 -10000 180 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.story append value {bgm:"pretion2",text:[{translate:"동화\n","color":"green"},{translate:"이상한 나라의 문","color":"aqua"}],number:129,lap:2,pos:"8999 125 -10000 90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#해적
data modify storage track-list tracks.pirate append value {bgm:"pirate4",text:[{translate:"해적\n","color":"blue"},{translate:"로비 절벽의 전투","color":"aqua"}],number:16,lap:2,pos:"118 14 2123 180 0",creator:{translate:"By Kation","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.pirate append value {bgm:"pirate2",text:[{translate:"해적\n","color":"blue"},{translate:"숨겨진 보물","color":"aqua"}],number:46,lap:2,pos:"2999 4 -1002 -90 0",creator:{translate:"By SEOLEETAE","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.pirate append value {bgm:"pirate3",text:[{translate:"해적\n","color":"blue"},{translate:"가파른 감시탑","color":"aqua"}],number:83,lap:2,pos:"0 19 -7001 0 0",creator:{translate:"By EYRT","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.pirate append value {bgm:"pirate",text:[{translate:"[R] 해적\n","color":"blue","italic":true},{translate:"로비 절벽의 전투","color":"aqua","italic":true}],number:98,lap:2,pos:"-9000 14 -3999 0 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#문힐시티
data modify storage track-list tracks.moonhill append value {bgm:"moonhill",text:[{translate:"문힐시티\n","color":"dark_blue"},{translate:"숨겨진 지하터널","color":"aqua"}],number:74,lap:2,pos:"4000 100 -6997 0 0",etc:["time set 13500"],creator:{translate:"By EYRT77","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.moonhill append value {bgm:"moonhill2",text:[{translate:"문힐시티\n","color":"dark_blue"},{translate:"폭우속의 질주","color":"aqua"}],number:105,lap:2,pos:"1001 99 10000 90 0",etc:["time set midnight","weather rain"],creator:{translate:"By EYRT77","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.moonhill append value {bgm:"moonhill",text:[{translate:"[R] 문힐시티\n","color":"dark_blue","italic":true},{translate:"폭우속의 질주","color":"aqua","italic":true}],number:107,lap:2,pos:"-4997 99 -10000 -90 0",etc:["time set midnight","weather rain"],creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.moonhill append value {bgm:"moonhill2",text:[{translate:"[R] 문힐시티\n","color":"dark_blue","italic":true},{translate:"숨겨진 지하터널","color":"aqua","italic":true}],number:140,lap:2,pos:"10880 100 -7741 180 0",etc:["time set 13500"],creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#황금문명
data modify storage track-list tracks.gold append value {bgm:"gold",text:[{translate:"황금문명\n","color":"gold"},{translate:"오르에트 황금 좌표","color":"aqua"}],number:69,lap:2,pos:"-3853 31 -3621 90 0",etc:["effect give @a minecraft:night_vision infinite 1 true","time set 12800"],creator:{translate:"By N_Devil","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.gold append value {bgm:"gold2",text:[{translate:"황금문명\n","color":"gold"},{translate:"비밀장치의 위협","color":"aqua"}],number:127,lap:2,pos:"5000 275 -10000 180 0",etc:["time set 12800"],creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#차이나
data modify storage track-list tracks.china append value {bgm:"china",text:[{translate:"차이나\n","color":"red"},{translate:"상해 동방명주","color":"aqua"}],number:14,lap:3,pos:"1000 51 -7000 0 0",creator:{translate:"By trl3","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.china append value {bgm:"china2",text:[{translate:"차이나\n","color":"red"},{translate:"동방명주의 밤","color":"aqua"}],number:60,lap:3,pos:"4000 75 3000 10 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"},etc:["time set midnight"]}
data modify storage track-list tracks.china append value {bgm:"china3",text:[{translate:"차이나\n","color":"red"},{translate:"서안 병마용","color":"aqua"}],number:6,lap:2,pos:"861 61 -4971 0 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.china append value {bgm:"china",text:[{translate:"[","color":"red"},{translate:"α","color":"gold"},{translate:"] 차이나\n","color":"red"},{translate:"서안 병마용","color":"aqua"}],number:93,lap:2,pos:"5001 84 1000 -90 0",creator:{translate:"By GhangDhang","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.china append value {bgm:"china2",text:[{translate:"차이나\n","color":"red"},{translate:"무당산 다운힐","color":"aqua"}],number:45,lap:1,pos:"-2440 295 1654 0 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.china append value {bgm:"china2",text:[{translate:"차이나\n","color":"red"},{translate:"골목길 대질주","color":"aqua"}],number:88,lap:2,pos:"-5000 34 1998 180 0",creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.china append value {bgm:"china3",text:[{translate:"[R] 차이나\n","color":"red","italic":true},{translate:"골목길 대질주","color":"aqua","italic":true}],number:123,lap:2,pos:"-10000 86 3000 0 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#대저택
data modify storage track-list tracks.mansion append value {bgm:"mansion",text:[{translate:"대저택\n","color":"red"},{translate:"루이의 서재","color":"aqua"}],number:84,lap:3,pos:"-2 19 2986 90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★☆☆☆☆","color":"yellow"}}
data modify storage track-list tracks.mansion append value {bgm:"mansion",text:[{translate:"대저택\n","color":"red"},{translate:"루이의 숨겨진 비밀 방","color":"aqua"}],number:77,lap:3,pos:"5196 5 -3842 90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.mansion append value {bgm:"mansion3",text:[{translate:"대저택\n","color":"red"},{translate:"럭셔리 거대욕조","color":"aqua"}],number:81,lap:3,pos:"9011 30 -29 -90 0",creator:{translate:"By Glass_Man01","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.mansion append value {bgm:"mansion3",text:[{translate:"대저택\n","color":"red"},{translate:"루이의 장난감 천국","color":"aqua"}],number:55,lap:3,pos:"-5011 4 -5000 90 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.mansion append value {bgm:"mansion2",text:[{translate:"대저택\n","color":"red"},{translate:"은밀한 지하실","color":"aqua"}],number:8,lap:2,pos:"-850.1 62 -233 0 0",creator:{translate:"By GhangDhang","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#WKC
data modify storage track-list tracks.wkc append value {bgm:"wkc",text:[{translate:"WKC\n","color":"gold"},{translate:"코리아 서킷","color":"aqua"}],number:76,lap:2,pos:"3504 4 -7651 -90 0",etc:["weather rain"],creator:{translate:"By tco3307402","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkc2",text:[{translate:"WKC\n","color":"gold"},{translate:"투어링 랠리","color":"aqua"}],number:21,lap:1,pos:"4006 69 3999 180 0",etc:["time set 12500"],creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkc2",text:[{translate:"WKC\n","color":"gold"},{translate:"상해 서킷","color":"aqua"}],number:28,lap:2,pos:"7997 5 6000 180 0",etc:["weather rain"],creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkcdrift",text:[{translate:"WKC\n","color":"gold"},{translate:"강남 서킷","color":"aqua"}],number:32,lap:1,pos:"-985 5 -670 0 0",etc:["time set 12500"],creator:{translate:"By bobjihoo","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkcdrift",text:[{translate:"WKC\n","color":"gold"},{translate:"웜업 스피드웨이","color":"aqua"}],number:116,lap:9,pos:"1048 4 3503 0 0",etc:[],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}

data modify storage track-list tracks.wkc append value {bgm:"wkc3",text:[{translate:"WKC\n","color":"gold"},{translate:"인제 스피디움","color":"aqua"}],number:62,lap:3,pos:"3834 8 -1179 0 0",creator:{translate:"By banjil2009","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkc4",text:[{translate:"WKC\n","color":"gold"},{translate:"서울 시가지 서킷","color":"aqua"}],number:49,lap:2,pos:"985 18 -3001 0 0",creator:{translate:"By whitecow","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkc5",text:[{translate:"WKC\n","color":"gold"},{translate:"바쿠 시티 서킷","color":"aqua"}],number:108,lap:2,pos:"987 4 8141 180 0",creator:{translate:"By bobjihoo","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkc",text:[{translate:"WKC\n","color":"gold"},{translate:"F1 서킷","color":"aqua"}],number:11,lap:2,pos:"-470 4 -533 90 0",creator:{translate:"By SEOLEETAE","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkc4",text:[{translate:"WKC\n","color":"gold"},{translate:"루사일 서킷","color":"aqua"}],number:139,lap:2,pos:"7001 5 -49 90 0",etc:["time set night","effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By whitecow","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}

data modify storage track-list tracks.wkc append value {bgm:"wind",text:[{translate:"WKC\n","color":"gold"},{translate:"익스트림 레이스","color":"aqua"}],number:12,lap:1,pos:"599 4 806 180 0",creator:{translate:"By Kation","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {bgm:"wkc",text:[{translate:"WKC\n","color":"gold"},{translate:"트럼펫","color":"aqua"}],number:13,lap:3,pos:"563 4 600 90 0",creator:{translate:"By NewConnor","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.wkc append value {is-copyright:true,bgm:"wkcdrift",text:[{translate:"WKC\n","color":"gold"},{translate:"미완성 서킷","color":"aqua"}],number:35,lap:3,pos:"9024.5 24 8171 0 0",creator:{translate:"© RedgraveMC\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}


#비치
data modify storage track-list tracks.beach append value {bgm:"beach",text:[{translate:"비치\n","color":"blue"},{translate:"신나는 여름휴가","color":"aqua"}],number:20,lap:3,pos:"2067 5 2322 90 0",creator:{translate:"By Onliy","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.beach append value {bgm:"beach2",text:[{translate:"비치\n","color":"blue"},{translate:"해변 드라이브","color":"aqua"}],number:22,lap:2,pos:"-2362 19 -1779 0 0",creator:{translate:"By fourgod","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.beach append value {bgm:"beach",text:[{translate:"트랙 9","color":"aqua"}],number:36,lap:1,pos:"9070 85 7575 0 0",creator:{translate:"By fourgod","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}

#쥐라기
data modify storage track-list tracks.dino append value {bgm:"dino1",text:[{translate:"쥐라기\n","color":"gold"},{translate:"공룡 결투장","color":"aqua"}],number:134,lap:2,pos:"9687 111 -10151 180 0",etc:["time set 12600"],creator:{translate:"By ongler_","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}

#월드
data modify storage track-list tracks.world append value {bgm:"rio",text:[{translate:"월드\n","color":"blue"},{translate:"리오 다운힐","color":"aqua"}],number:7,lap:1,pos:"-834 129 1583 -90 0",creator:{translate:"By GhangDhang","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.world append value {bgm:"world",text:[{translate:"월드\n","color":"blue"},{translate:"두바이 다운타운","color":"aqua"}],number:52,lap:2,pos:"4024 5 5990 0 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.world append value {bgm:"world2",text:[{translate:"월드\n","color":"blue"},{translate:"파리 에펠탑 다이브","color":"aqua"}],number:75,lap:2,pos:"5050 -41 -1854 0 0",etc:["time set 12600"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#네모
#data modify storage track-list tracks.nemo append value {bgm:"nemo4",text:[{translate:"네모\n","color":"green"},{translate:"메디아 은신처","color":"aqua"}],number:148,lap:3,pos:"12473 -31 -5772 180 0",etc:["execute positioned 12322 -34.51 -5807 run function nemomediaspin:_/create","function nemomediaspin:a/default/play_anim_loop","execute positioned 12202 -32.5 -5699 run function nemotetrisanim:_/create","function nemotetrisanim:a/default/play_anim_loop"],creator:{translate:"By tco3307402","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}

data modify storage track-list tracks.nemo append value {bgm:"nemo",text:[{translate:"네모\n","color":"green"},{translate:"산타의 비밀공간","color":"aqua"}],number:15,lap:2,pos:"1169.0 163.0 -865.5 -90 0",creator:{translate:"By KITE2459","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.nemo append value {bgm:"nemo3",text:[{translate:"[","color":"green"},{translate:"α","color":"gold"},{translate:"] 네모\n","color":"green"},{translate:"산타의 비밀공간","color":"aqua"}],number:94,lap:3,pos:"5001 140 2000 -90 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.nemo append value {bgm:"nemo2",text:[{translate:"네모\n","color":"green"},{translate:"장난감 선물공장","color":"aqua"}],number:50,lap:1,pos:"-2001 308 -1000 0 0",creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.nemo append value {bgm:"nemo4",text:[{translate:"네모\n","color":"green"},{translate:"강철바위 용광로","color":"aqua"}],number:57,lap:2,pos:"2810 224 -4293 -90 0",etc:["function spinningterrain:summonspinob","function spinningterrain:startrota"],creator:{translate:"By tco3307402","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#도검
data modify storage track-list tracks.sword append value {bgm:"sword",text:[{translate:"도검\n","color":"white"},{translate:"구름의 협곡","color":"aqua"}],number:56,lap:2,pos:"5796 237 -5781 -90 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.sword append value {bgm:"fengshen",text:[{translate:"도검\n","color":"white"},{translate:"봉신전설 천외선경","color":"aqua"}],number:92,lap:2,pos:"3978 150 -9023 -90 0",etc:["effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}

#신화
data modify storage track-list tracks.god append value {bgm:"god1",text:[{translate:"신화\n","color":"gold"},{translate:"신들의 세계","color":"aqua"}],number:132,lap:1,pos:"3039 101 -6956 90 0",etc:[""],creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.god append value {bgm:"god1",text:[{translate:"신화\n","color":"gold"},{translate:"오딘의 궁전","color":"aqua"}],number:143,lap:2,pos:"9142 98 6392 90 0",etc:[""],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}

#어비스
data modify storage track-list tracks.abyss append value {bgm:"abyss",text:[{translate:"어비스\n","color":"blue"},{translate:"스카이라인","color":"aqua"}],number:24,lap:2,pos:"1999 161 3994 90 0",etc:["time set 18000"],creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.abyss append value {bgm:"abyss2",text:[{translate:"어비스\n","color":"blue"},{translate:"숨겨진 바닷길","color":"aqua"}],number:71,lap:2,pos:"-966 149 -1578 -117 0",etc:["time set 18000","effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.abyss append value {bgm:"abyss",text:[{translate:"[R] 어비스\n","color":"blue","italic":true},{translate:"숨겨진 바닷길","color":"aqua","italic":true}],number:144,lap:2,pos:"11996 149 -4996 63 0",etc:["time set 18000","effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#코리아
data modify storage track-list tracks.korea append value {bgm:"korea",text:[{translate:"코리아\n","color":"blue"},{translate:"제주 해오름 다운힐","color":"aqua"}],number:40,lap:1,pos:"2429 226 3393 180 0",creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.korea append value {bgm:"korea2a",text:[{translate:"코리아\n","color":"blue"},{translate:"부산의 밤","color":"aqua"}],number:58,lap:2,pos:"999 87 5000 -90 0",etc:["time set 18000"],creator:{translate:"By ECYCEeeee","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.korea append value {bgm:"korea",text:[{translate:"[R] 코리아\n","color":"blue","italic":true},{translate:"제주 해오름 다운힐","color":"aqua","italic":true}],number:111,lap:1,pos:"539 31 8781 180 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}

data modify storage track-list tracks.korea append value {bgm:"korea3",text:[{translate:"코리아\n","color":"blue"},{translate:"홍대 새벽녘 질주","color":"aqua"}],number:97,lap:2,pos:"3123 4 8223 90 0",etc:["time set 23100"],creator:{translate:"By withlight_","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.korea append value {bgm:"korea",text:[{translate:"코리아\n","color":"blue"},{translate:"울릉도 고갯길","color":"aqua"}],number:87,lap:1,pos:"2884 39 -8677 135 0",creator:{translate:"By withlight_","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}


#메이플
data modify storage track-list tracks.maple append value {bgm:"maple2",text:[{translate:"메이플\n","color":"gold"},{translate:"헤네시스 공원","color":"aqua"}],number:66,lap:2,pos:"7174 76 7417 90 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.maple append value {bgm:"maplenew",text:[{translate:"메이플\n","color":"gold"},{translate:"레헬른 악몽의 시계탑","color":"aqua"}],number:18,lap:2,pos:"8200 6 7975 -90 0",etc:["time set 13500"],creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.maple append value {bgm:"maple",text:[{translate:"[α] 메이플\n","color":"gold"},{translate:"레헬른 악몽의 시계탑","color":"aqua"}],number:95,lap:3,pos:"2000 4 2000 0 0",etc:["time set 13500"],creator:{translate:"By _Nekter_","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.maple append value {bgm:"maple2",text:[{translate:"메이플\n","color":"gold"},{translate:"리스항구 언덕길","color":"aqua"}],number:135,lap:2,pos:"-8000 74 -10001 90 0",creator:{translate:"By ongler_","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}


#카우지
data modify storage track-list tracks.kauzee append value {bgm:"kauzee",text:[{translate:"카우지\n","color":"light_purple"},{translate:"디스트릭트 13","color":"aqua"}],number:51,lap:3,pos:"1009 118 3000 90 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.kauzee append value {bgm:"kauzee2",text:[{translate:"카우지\n","color":"light_purple"},{translate:"네온 스트리트","color":"aqua"}],number:109,lap:2,pos:"1999 150 -9001 180 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.kauzee append value {bgm:"kauzee2",text:[{translate:"[R] 카우지\n","color":"light_purple","italic":true},{translate:"네온 스트리트","color":"aqua","italic":true}],number:131,lap:2,pos:"2000 150 10002 0 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}


#마인크래프트
data modify storage track-list tracks.minecraft append value {bgm:"otherside",text:[{translate:"마인크래프트\n","color":"green"},{translate:"모험의 시간","color":"aqua"}],number:19,lap:2,pos:"1080 4 -1756 90 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.minecraft append value {is-copyright:true,bgm:"wkc",text:[{translate:"마인크래프트\n","color":"green"},{translate:"잊혀진 정글","color":"aqua"}],number:31,lap:1,pos:"6427 11 6373 -90 0",etc:["time set 13000"],creator:{translate:"© Bluleader64","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.minecraft append value {bgm:"dream",text:[{translate:"마인크래프트\n","color":"green"},{translate:"엔드 시티 탐험","color":"aqua"}],number:30,lap:2,pos:"-3008 72 3990 180 0",etc:["time set 18000"],creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.minecraft append value {is-copyright:true,bgm:"mine",text:[{translate:"마인크래프트\n","color":"green"},{translate:"다이아몬드다!","color":"aqua"}],number:37,lap:1,pos:"3756 138 757 0 0",creator:{translate:"© ghostly","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.minecraft append value {bgm:"village4",text:[{translate:"마인크래프트\n","color":"green"},{translate:"1년의 시간","color":"aqua"}],number:100,lap:1,pos:"-9022 4 -10001 90 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"},etc:["time set midnight"]}


#마리오카트
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mkdsyoshifalls",text:[{translate:"마리오카트\n","color":"red"},{translate:"요시알 폭포","color":"aqua"}],number:103,lap:5,pos:"11000.00 17.00 10000.0 -90 0",creator:{translate:"© Springstof\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mkdsdelfino",text:[{translate:"마리오카트\n","color":"red"},{translate:"몬테 타운","color":"aqua"}],number:80,lap:3,pos:"9000 72 -4000 90 0",creator:{translate:"© Beany\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mkdsairshipfortress",text:[{translate:"마리오카트\n","color":"red"},{translate:"킬러 해적선","color":"aqua"}],number:104,lap:3,pos:"12000 82 10000 -90 0",creator:{translate:"© Rocket Raccoon\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mkdswaluigi",text:[{translate:"마리오카트\n","color":"red"},{translate:"와루이지 핀볼","color":"aqua"}],number:79,lap:3,pos:"3999 66 8000 90 0",etc:["time set 18000"],creator:{translate:"© TheRealBlank512\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mkscsnowland",text:[{translate:"마리오카트\n","color":"red"},{translate:"스노우랜드","color":"aqua"}],number:91,lap:5,pos:"2999 4 6999 180 0",creator:{translate:"By resi-le","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}

data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"snesrainbow",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 SNES","color":"aqua"}],number:114,lap:3,pos:"7015 200 -9895 180 0",etc:["time set 18000"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mk8rainbow64",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 64","color":"aqua"}],number:82,lap:2,pos:"-7000 150 -1 180 0",etc:["time set 18000"],creator:{translate:"© TheRealBlank512\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mkgbarainbow",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 GBA","color":"aqua"}],number:124,lap:3,pos:"9998 200 3000 90 0",creator:{translate:"By resi-le","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"},etc:["time set midnight","effect give @a minecraft:night_vision infinite 1 true"]}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mkddrainbow",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 GC","color":"aqua"}],number:78,lap:2,pos:"6000 212 -999 0 0",etc:["time set 18000"],creator:{translate:"© TheRealBlank512\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"mkdsrainbow",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 DS","color":"aqua"}],number:118,lap:2,pos:"-11001 200 -11002 135 0",etc:["time set 18000"],creator:{translate:"© TheRealBlank512\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.mariokart append value {is-copyright:true,bgm:"rainbow1",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 WORLD","color":"aqua"}],number:142,lap:1,pos:"-14437 -3 -7831 180 0",etc:["time set midnight"],creator:{translate:"© Outlaw Runner, Iggy Ze Koopa\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}

#소닉
data modify storage track-list tracks.sonic append value {is-copyright:true,bgm:"cityescape",text:[{translate:"소닉\n","color":"blue"},{translate:"시티 이스케이프","color":"aqua"}],number:39,lap:1,pos:"1138 299 872 -90 0",creator:{translate:"By Asdf08","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.sonic append value {is-copyright:true,bgm:"ghz",text:[{translate:"소닉\n","color":"blue"},{translate:"그린 힐","color":"aqua"}],number:130,lap:2,pos:"11131 90 -9120 180 0",creator:{translate:"By tco3307402","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.sonic append value {is-copyright:true,bgm:"greenhillcourse_sonicdrift",text:[{translate:"소닉\n","color":"blue"},{translate:"그린 힐 드리프트 I","color":"aqua"}],number:145,lap:3,pos:"1187 9 -14694 180 0",creator:{translate:"By tco3307402","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.sonic append value {is-copyright:true,bgm:"greenhillcourse_sonicdrift",text:[{translate:"소닉\n","color":"blue"},{translate:"그린 힐 드리프트 II","color":"aqua"}],number:146,lap:3,pos:"12203 9 -11695 180 0",creator:{translate:"By tco3307402","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.sonic append value {is-copyright:true,bgm:"greenhillcourse_sonicdrift",text:[{translate:"소닉\n","color":"blue"},{translate:"그린 힐 드리프트 III","color":"aqua"}],number:147,lap:3,pos:"12243 15 12325 180 0",creator:{translate:"By tco3307402","color":"gold"},difficulty:{translate:"★☆☆☆☆","color":"yellow"}}

#서드파티
data modify storage track-list tracks.thirdparty append value {is-copyright:true,bgm:"ice2",text:[{translate:"서드파티\n","color":"gold"},{translate:"황금의 순간","color":"aqua"}],number:41,lap:2,pos:"9378 66 10314 -90 0",etc:["time set 13500"],creator:{translate:"© LASTFOR\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.thirdparty append value {is-copyright:true,bgm:"run90",text:[{translate:"서드파티\n","color":"gold"},{translate:"마키나 다운힐","color":"aqua"}],number:43,lap:1,pos:"1573 222 -1394 0 0",etc:["time set 18000"],creator:{translate:"© Justinb535\nAll rights reserved.","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}

#업앤다운
data modify storage track-list tracks.updown append value {bgm:"pirate2",text:[{translate:"[↑↓] 해적\n","color":"blue"},{translate:"위험한 오르막","color":"aqua"}],number:1001,lap:1,pos:"-2975 19 -2998 90 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.updown append value {bgm:"desert3",text:[{translate:"[↑↓] 사막\n","color":"yellow"},{translate:"스핑크스 다이브","color":"aqua"}],number:1002,lap:1,pos:"-3363 137 -2764 90 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.updown append value {bgm:"forest2",text:[{translate:"[↑↓] 포레스트\n","color":"green"},{translate:"인내의 통나무","color":"aqua"}],number:1003,lap:1,pos:"-2023 13 -3014 -90 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.updown append value {bgm:"ice2",text:[{translate:"[↑↓] 아이스\n","color":"blue"},{translate:"익스트림 터치다운","color":"aqua"}],number:1004,lap:1,pos:"4994 299 -4999 -90 0",creator:{translate:"By BKGPolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.updown append value {bgm:"tomb",text:[{translate:"[↑↓] 공동묘지\n","color":"red"},{translate:"영혼의 하늘길","color":"aqua"}],number:1005,lap:1,pos:"-4999 30 4993 180 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"},etc:["time set midnight"]}
data modify storage track-list tracks.updown append value {bgm:"maple2",text:[{translate:"[↑↓] 메이플\n","color":"gold"},{translate:"인내의 숲","color":"aqua"}],number:1006,lap:1,pos:"3000 100 5019 180 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.updown append value {bgm:"lavachicken",text:[{translate:"[↑↓] 마인크래프트\n","color":"green"},{translate:"미네랄 파쿠르","color":"aqua"}],number:1007,lap:1,pos:"977 6 7022 180 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.updown append value {bgm:"korea2",text:[{translate:"[↑↓] 코리아\n","color":"blue"},{translate:"하늘 위 윷놀이","color":"aqua"}],number:1008,lap:2,pos:"7991 100 -10001 -90 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}


#업앤다운 크로스오버
data modify storage track-list tracks.updowncrossover append value {bgm:"village2",text:[{translate:"[↑↓x] 빌리지\n","color":"blue"},{translate:"운명의 다리","color":"aqua"}],number:-1003,lap:2,pos:"1563 12 -9623 0 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.updowncrossover append value {bgm:"desert2",text:[{translate:"[↑↓x] 사막\n","color":"yellow"},{translate:"빙글빙글 공사장","color":"aqua"}],number:-1001,lap:2,pos:"8999 4 -9008 180 0",creator:{translate:"By LogGamja","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.updowncrossover append value {bgm:"pirate3",text:[{translate:"[↑↓x] 해적\n","color":"blue"},{translate:"가파른 감시탑","color":"aqua"}],number:-1002,lap:2,pos:"3000 18 6001 0 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.updowncrossover append value {bgm:"korea",text:[{translate:"[↑↓x] [R] 코리아\n","color":"blue","italic":true},{translate:"제주 해오름 다운힐","color":"aqua","italic":true}],number:-1004,lap:1,pos:"7000 31 10000 180 0",creator:{translate:"By BKGpolar","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.updowncrossover append value {bgm:"mansion3",text:[{translate:"[↑↓x] ","color":"red"},{translate:"대저택\n","color":"red"},{translate:"루이의 장난감 천국","color":"aqua"}],number:-1005,lap:3,pos:"4142 4 -5163 90 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.updowncrossover append value {bgm:"tomb",text:[{translate:"[↑↓x] ","color":"gold"},{translate:"WKC\n","color":"gold"},{translate:"잊혀진 도로","color":"aqua"}],number:-1006,lap:1,pos:"2041 29 -1918 180 0",creator:{translate:"By comet_09","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.updowncrossover append value {bgm:"pirate4",text:[{translate:"[↑↓x] ","color":"blue"},{translate:"해적\n","color":"blue"},{translate:"로비 절벽의 전투","color":"aqua"}],number:-1007,lap:2,pos:"0 14 -10000 180 0",creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.updowncrossover append value {bgm:"abyss2",text:[{translate:"[↑↓x] ","color":"blue"},{translate:"어비스\n","color":"blue"},{translate:"숨겨진 바닷길","color":"aqua"}],number:-1008,lap:2,pos:"-10001 149 -1 -117 0",etc:["time set 18000","effect give @a minecraft:night_vision infinite 1 true"],creator:{translate:"By L_Peng","color":"gold"},difficulty:{translate:"★★★★★","color":"yellow"}}


#랜덤
data modify storage track-list tracks.random append value {random:1,text:[{translate:"EASY\n","color":"green"},{translate:"RANDOM","color":"light_purple"}],number:10000,creator:[{translate:"★☆☆☆☆","color":"yellow"},{translate:"-",color:white},{translate:"★★☆☆☆","color":"yellow"}],difficulty:{translate:"★★☆☆☆","color":"yellow"}}
data modify storage track-list tracks.random append value {random:2,text:[{translate:"NORMAL\n","color":"yellow"},{translate:"RANDOM","color":"light_purple"}],number:10001,creator:[{translate:"★★☆☆☆","color":"yellow"},{translate:"-",color:white},{translate:"★★★☆☆","color":"yellow"}],difficulty:{translate:"★★★☆☆","color":"yellow"}}
data modify storage track-list tracks.random append value {random:3,text:[{translate:"HARD\n","color":"red"},{translate:"RANDOM","color":"light_purple"}],number:10002,creator:[{translate:"★★★☆☆","color":"yellow"},{translate:"-",color:white},{translate:"★★★★☆","color":"yellow"}],difficulty:{translate:"★★★★☆","color":"yellow"}}
data modify storage track-list tracks.random append value {random:4,text:[{translate:"VERY HARD\n","color":"dark_red"},{translate:"RANDOM","color":"light_purple"}],number:10003,creator:[{translate:"★★★★☆","color":"yellow"},{translate:"-",color:white},{translate:"★★★★★","color":"yellow"}],difficulty:{translate:"★★★★★","color":"yellow"}}
data modify storage track-list tracks.random append value {random:5,text:[{translate:"ALL\n","color":"blue"},{translate:"RANDOM","color":"light_purple"}],number:10004,creator:[{translate:"★☆☆☆☆","color":"yellow"},{translate:"-",color:white},{translate:"★★★★★","color":"yellow"}],difficulty:{translate:"☆☆☆☆☆","color":"yellow"}}

function trackselect:system/make_random_pool

#변수 설정
scoreboard objectives add track-ui dummy


#테스트용
#data modify storage track-list tracks.minecraft append value {bgm:"mine",text:[{translate:"마인크래프트\n","color":"green"},{translate:"니얼굴이다!","color":"aqua"}],number:2000,lap:1,pos:"419 4 -127 -90 0",creator:{translate:"© ghostly","color":"gold"},etc:["setblock 201 73 -57 minecraft:redstone_block"]}