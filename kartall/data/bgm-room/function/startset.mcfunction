#초기화
data modify storage bgm-room themes set value []
data remove storage bgm-room bgms

#테마 정의
data modify storage bgm-room themes append value {key:"forest",text:{translate:"포레스트","color":"green"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdkYTRhYTQ4MGNjNmFmNWI4MDUzZTA5NjA1ZGM3MWI4NjkxMWQyNGRhOGE4YzBlNDM5MmE1NzU2NDZhYTZjMSJ9fX0="}
data modify storage bgm-room themes append value {key:"desert",text:{translate:"사막","color":"yellow"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ0Zjk5MDc3OGQ3NjdlYTkyZWJiNzc5YjBiN2M1NDJiMjEyYTE4OTIwMmExY2E4ZTk1YzY5ZmUxYjcwYjVkZCJ9fX0="}
data modify storage bgm-room themes append value {key:"village",text:{translate:"빌리지","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4MjEzODY0YzIyZDdjYTMyZWQ1MDhjMWUxMjE3ODhlNzRlYzFiY2JlN2ZjYzIxZDM3ZDgzNmMyYTUwNmM4ZCJ9fX0="}
data modify storage bgm-room themes append value {key:"ice",text:{translate:"아이스","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTgxYjRmNWYzZDk5NjcyYWIxYTM5ZjVmNDEwYmQ0ZDRmZDkyNTQ4ZTA3ZDE3YmRmMGQ0ZjFmNTdkY2ViYjVjZiJ9fX0="}
data modify storage bgm-room themes append value {key:"tomb",text:{translate:"공동묘지","color":"red"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQwNjYyOGNjZGVmMjZhN2E5OGQ4ODlkZmQyOTczMzQxYWI5ZjM1NGIwN2VjYzg3MTViMDQxZGUwNzI0OGQ0In19fQ=="}
data modify storage bgm-room themes append value {key:"mine",text:{translate:"광산","color":"light_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2YyZDM4ZWZhZTFlMGE1ODFmMjU4YWEwOWNhNjEwNzQ5ZDNlZmRkZjU4NjUzMDRmMmZhMThiN2ExYTBjM2JjZCJ9fX0="}

data modify storage bgm-room themes append value {key:"planet",text:{translate:"노르테유","color":"light_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg4YmZkNjMyYjIwOGQxNTk4N2VmNmVmOTI2OTJjMGMxYWQwZWQ4OWJkMDVjMjg0ZTViZDA4MjU3YWRhMDUzYSJ9fX0="}
data modify storage bgm-room themes append value {key:"factory",text:{translate:"팩토리","color":"yellow"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmVkZDI0ODI1YzIxNzMwM2YyNmFmNTUyNjU3MGEyMTRhZWMyOWI5Mjk3YTY4NzcxNTFiYTUwZmNlZGZmN2ZmMSJ9fX0="}
data modify storage bgm-room themes append value {key:"pirate",text:{translate:"해적","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UxNzRjNDkyZDYzYWZlNzFiNDg0NmRlYWNhZjliNTU1YzgxNjYzMzc0N2UxNDY3YjY3YWZjMDhkMmE3NmQwYyJ9fX0="}
data modify storage bgm-room themes append value {key:"story",text:{translate:"동화","color":"green"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI3OWEyNTE0ODAwZTU5MmE2YWMzYmQ1ZjUwNTM5YTk5OTRmMWI2MTI2MWQxMGUyYTRlNjA3YzI4MjhjMWMyYyJ9fX0="}

data modify storage bgm-room themes append value {key:"moonhill",text:{translate:"문힐시티","color":"dark_blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzkzNTUzNzY2YzVkNzc4OWJkZmM5ZTE5NWZiYmIxM2RjODVkNzI2ZWY3M2M5NTM1ZjRhN2IwOTBhNjExZTg4OCJ9fX0="}
data modify storage bgm-room themes append value {key:"gold",text:{translate:"황금문명","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2JkNDE4OTZkNWQ4YTI5OTRhMGZjNmU4NTFmMjA3ZDgxZGRlZmI3ZTExMDRlNDcwZTRiNmU5Mjc2NWIzOWRiZiJ9fX0="}
data modify storage bgm-room themes append value {key:"china",text:{translate:"차이나","color":"red"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5YmMwMzVjZGM4MGYxYWI1ZTExOThmMjlmM2FkM2ZkZDJiNDJkOWE2OWFlYjY0ZGU5OTA2ODE4MDBiOThkYyJ9fX0="}

data modify storage bgm-room themes append value {key:"mansion",text:{translate:"대저택","color":"red"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzExZGI1ZGZmY2VhNzQ3ZDUzODYyNTRmYzc1MTY3MWU0MTI4OGU1NmU3MTdmNTBiZmE3MDBjMzVmMjIzNTFkZCJ9fX0="}
data modify storage bgm-room themes append value {key:"wkc",text:{translate:"WKC","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2UxODlhMTQ5MTFjN2ZmMmE3NzBjZTgzMDhkNDBiNzRmYmJmMTI4ZTBhNThmYWJmOGZmYTI1NjllMTQzZTgxMyJ9fX0="}
data modify storage bgm-room themes append value {key:"beach",text:{translate:"비치","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZlOWEyMTQwZjQwZjVkN2NkMjRkZmJhZjQ1NjdmYTRhYmI3YzU1ZTJhMDYyZmJiNzUyMGE3MzczYzQxMmE3ZCJ9fX0="}
data modify storage bgm-room themes append value {key:"dino",text:{translate:"쥐라기","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJkODExZDA2NDM0ZWJmMWFlY2ExMDE5Yzc0ZDU5ZjY3YjI2YmRkOWNmOGQ3ZGE3YzQ1MzdkNGQwNDVlNTU5In19fQ=="}
data modify storage bgm-room themes append value {key:"world",text:{translate:"월드","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGVhOThlMmZiNTYzODNmMzE2Nzg2YTI0NjI2MjhmZGU4ODVhYmM3NmRjMGU1Y2JhZTI1Nzg1ZTBkMTNhZWRjZSJ9fX0="}
data modify storage bgm-room themes append value {key:"nemo",text:{translate:"네모","color":"green"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk1NGYwNTcyNWQyODZkZjBiMjQ3YzUzOGI2ODlmZTQ3OWJhNDg2NjMxZWU4NTRlYjI1MjNlOTI0OWM3Zjg2OCJ9fX0="}
data modify storage bgm-room themes append value {key:"sword",text:{translate:"도검","color":"white"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzI3MmUwMDgwZGM3Yjc0ZWQ5ZmVhZDI2MDdkYmM4Y2VlMzJiNGY0MTM4NWUxZGYzZWQ1NDg1Yzk5ODM0MDM2MyJ9fX0="}
data modify storage bgm-room themes append value {key:"god",text:{translate:"신화","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg2NmVmNzFmYjc2MWUwN2UxMDg0MTQzY2I4Njk2MDY3NGIxOGYwODgxYTExOTBjZjE1YThlMjFkOWRkOTU1NiJ9fX0="}
data modify storage bgm-room themes append value {key:"abyss",text:{translate:"어비스","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjI0OTlkYjgwY2FiNWMwMzFlYTg5YzAyNWM3M2M4ZmI5ZmI0ZmYyYjUyM2I2YWMxYzRlZDE5NGMzMGEyYTdlYiJ9fX0="}

data modify storage bgm-room themes append value {key:"korea",text:{translate:"코리아","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzc0ZDgxN2I3NGZlY2MzMTI2NTI5ZjAyZWQ4YzQxMjNmNGFlN2MxODczNzBlYjFkZWNkNzIyZGMxZDkxODVmMSJ9fX0="}
data modify storage bgm-room themes append value {key:"maple",text:{translate:"메이플","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBkYTMyMjEwNGQ1ZjJiOWI1M2ZiZTBmMTQzNmNmYTUzMTcwMmQzMjZhNzJiZGQwY2M0MTY2YzE4ZDUxNDA1YiJ9fX0="}
data modify storage bgm-room themes append value {key:"kauzee",text:{translate:"카우지","color":"light_purple"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjYxZWY2MmEzNTk5NGRlNmRiOWVmNDEwM2NlZTc3N2Y4MGM1MjQ1MTMwYWYxZmM5NTYyNGQyZmZiNmRjYzc0MSJ9fX0="}
data modify storage bgm-room themes append value {key:"minecraft",smalltext:1,text:{translate:"마인크래프트","color":"green"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQwZjIzOTFlNzFkZjU2ZDhmODgxMGU4MDM5ZmNlZDViMmUzNTM5NGY0MGRlNmZlY2Y2NDRiZGUyZmZiMDE3ZCJ9fX0="}

data modify storage bgm-room themes append value {key:"mariokart",text:{translate:"마리오카트","color":"red"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg5ZmI4NzRjZmQ1ZWRiMjY1M2U4Y2Q4MDRhYTQwM2M1MWYyZWVjYWQzMmI4OWM1YmI1OTE2OGYwYTIzNGE1MiJ9fX0=",mariokart:1}
data modify storage bgm-room themes append value {key:"sonic",text:{translate:"소닉","color":"blue"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGJkNWY5MzE4NTZjOGFkNzJiZWM0ODJiNTYwYjEzNTY3MGU4ZWIzYmJkMzM5ZGRhNTg5M2YyMTE2MGYwYjAyIn19fQ=="}
data modify storage bgm-room themes append value {key:"thirdparty",text:{translate:"서드파티","color":"gold"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVkZTcwMTMwMmE3MjNlYjAxMTJhNmY2OTE0NWNlYWVkOTdlMmMwN2I4MThiMmVjMjE0ZGQ3NTJmYWFlYzFmMCJ9fX0="}
data modify storage bgm-room themes append value {key:"etc",text:{translate:"기타","color":"yellow"},icon:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjVjNjFiOWMxYmViM2ZhY2QxOTQ0MGJlNTEzMjI5MWM0ZGQzZjEyZWFiYWNhNjRmMDFjODlkMDg2ZGE1MzI1ZSJ9fX0="}

#포레스트
data modify storage bgm-room bgms.forest append value {bgm:"forest",text:[{translate:"포레스트\n","color":"green"},{translate:"아루와 나무꾼","color":"aqua"}],composer:{translate:"By Sodyum","color":"aqua"}}
data modify storage bgm-room bgms.forest append value {bgm:"forest4",text:[{translate:"포레스트\n","color":"green"},{translate:"숲속의 친구의 친구들","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.forest append value {bgm:"forest2",text:[{translate:"포레스트\n","color":"green"},{translate:"숲 속 산책로\n(Drift)","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.forest append value {bgm:"forest3",text:[{translate:"포레스트\n","color":"green"},{translate:"곰을 만나면 죽은척","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#사막
data modify storage bgm-room bgms.desert append value {bgm:"desert",text:[{translate:"사막\n","color":"yellow"},{translate:"낙타 경주","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.desert append value {bgm:"desert2",text:[{translate:"사막\n","color":"yellow"},{translate:"춤추는 신기루","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.desert append value {bgm:"desert3",text:[{translate:"사막\n","color":"yellow"},{translate:"신기루 레이싱","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#빌리지
data modify storage bgm-room bgms.village append value {bgm:"village",text:[{translate:"빌리지\n","color":"blue"},{translate:"배찌 뒹굴","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.village append value {bgm:"village2a",text:[{translate:"빌리지\n","color":"blue"},{translate:"배찌 뒹굴뒹굴","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.village append value {bgm:"village2",text:[{translate:"빌리지\n","color":"blue"},{translate:"배찌 뒹굴뒹굴\n[α]","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.village append value {bgm:"village3",text:[{translate:"빌리지\n","color":"blue"},{translate:"붐힐 삼총사","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.village append value {bgm:"village4",text:[{translate:"빌리지\n","color":"blue"},{translate:"세컨드 드라이브","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.village append value {bgm:"village5",text:[{translate:"빌리지\n","color":"blue"},{translate:"레이스의 시작","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#아이스
data modify storage bgm-room bgms.ice append value {bgm:"ice",text:[{translate:"아이스\n","color":"blue"},{translate:"눈 내리는 마을","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.ice append value {bgm:"ice5",text:[{translate:"아이스\n","color":"blue"},{translate:"눈 치우는 마을","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.ice append value {bgm:"ice3",text:[{translate:"아이스\n","color":"blue"},{translate:"썰매 배달부","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.ice append value {bgm:"ice4",text:[{translate:"아이스\n","color":"blue"},{translate:"마음은 따뜻하게","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.ice append value {bgm:"ice2",text:[{translate:"아이스\n","color":"blue"},{translate:"목표를 향해!","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.ice append value {bgm:"bgmice6",text:[{translate:"아이스\n","color":"blue"},{translate:"눈송이의 꿈","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#공동묘지
data modify storage bgm-room bgms.tomb append value {bgm:"tomb",text:[{translate:"공동묘지\n","color":"red"},{translate:"래빗홀의 습격","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.tomb append value {bgm:"tomb3",text:[{translate:"공동묘지\n","color":"red"},{translate:"네 뒤에 유령...","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.tomb append value {bgm:"tomb2",text:[{translate:"공동묘지\n","color":"red"},{translate:"내 뒤엔 유령 댄서","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#광산
data modify storage bgm-room bgms.mine append value {bgm:"mine3",text:[{translate:"광산\n","color":"light_purple"},{translate:"다이아몬드 러쉬","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.mine append value {bgm:"mine",text:[{translate:"광산\n","color":"light_purple"},{translate:"다이아몬드 러쉬\n[α]","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.mine append value {bgm:"mine2",text:[{translate:"광산\n","color":"light_purple"},{translate:"석탄 열차","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.mine append value {bgm:"mine4",text:[{translate:"광산\n","color":"light_purple"},{translate:"석양 아래 황금의 땅","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#노르테유
data modify storage bgm-room bgms.planet append value {bgm:"planet",text:[{translate:"노르테유\n","color":"light_purple"},{translate:"타키의 무대","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.planet append value {bgm:"planet2",text:[{translate:"노르테유\n","color":"light_purple"},{translate:"행성 점프","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.planet append value {bgm:"planet3",text:[{translate:"노르테유\n","color":"light_purple"},{translate:"워프게이트","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#팩토리
data modify storage bgm-room bgms.factory append value {bgm:"factory5",text:[{translate:"팩토리\n","color":"yellow"},{translate:"관계자외 출입금지","color":"aqua"}],composer:{translate:"By Glass_Man01","color":"aqua"}}
data modify storage bgm-room bgms.factory append value {bgm:"factory",text:[{translate:"팩토리\n","color":"yellow"},{translate:"관계자외 출입금지\n(Drift)","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.factory append value {bgm:"factory2",text:[{translate:"팩토리\n","color":"yellow"},{translate:"신규 설비","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.factory append value {bgm:"factory3",text:[{translate:"팩토리\n","color":"yellow"},{translate:"즐거운 생산라인","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.factory append value {bgm:"factory4",text:[{translate:"팩토리[브로디]\n","color":"yellow"},{translate:"혁신! 브로디","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#해적
data modify storage bgm-room bgms.pirate append value {bgm:"pirate4",text:[{translate:"해적\n","color":"blue"},{translate:"약탈의 춤사위","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.pirate append value {bgm:"pirate",text:[{translate:"해적\n","color":"blue"},{translate:"약탈의 춤사위\n[α]","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.pirate append value {bgm:"pirate2",text:[{translate:"해적\n","color":"blue"},{translate:"거침없는 항해","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.pirate append value {bgm:"pirate3",text:[{translate:"해적\n","color":"blue"},{translate:"로두마니! 출항","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}

#동화
data modify storage bgm-room bgms.story append value {bgm:"pretion1",text:[{translate:"동화\n","color":"green"},{translate:"메르헨가도","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.story append value {bgm:"pretion2",text:[{translate:"동화\n","color":"green"},{translate:"티이라의 초대","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}

#문힐시티
data modify storage bgm-room bgms.moonhill append value {bgm:"moonhill",text:[{translate:"문힐시티\n","color":"dark_blue"},{translate:"악당들은 네오 조심","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.moonhill append value {bgm:"moonhill2",text:[{translate:"문힐시티\n","color":"dark_blue"},{translate:"문힐 비밥","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#황금문명
data modify storage bgm-room bgms.gold append value {bgm:"gold",text:[{translate:"황금문명\n","color":"gold"},{translate:"금빛 문이 열리면","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.gold append value {bgm:"gold2",text:[{translate:"황금문명\n","color":"gold"},{translate:"황금 어드벤처","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#차이나
data modify storage bgm-room bgms.china append value {bgm:"china",text:[{translate:"차이나\n","color":"red"},{translate:"대륙의 공휴일","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.china append value {bgm:"china2",text:[{translate:"차이나\n","color":"red"},{translate:"중국식 교차로","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.china append value {bgm:"china3",text:[{translate:"차이나\n","color":"red"},{translate:"대륙의 붉은 비트","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#대저택
data modify storage bgm-room bgms.mansion append value {bgm:"mansion3",text:[{translate:"대저택\n","color":"red"},{translate:"루이 알마의\n화려한 레이스","color":"aqua"}],composer:{translate:"By tco3307402","color":"aqua"}}
data modify storage bgm-room bgms.mansion append value {bgm:"mansion2",text:[{translate:"대저택\n","color":"red"},{translate:"대저택 댄스 배틀","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.mansion append value {bgm:"mansion",text:[{translate:"대저택\n","color":"red"},{translate:"대저택 댄스 배틀\n[α]","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#WKC
data modify storage bgm-room bgms.wkc append value {bgm:"wkc",text:[{translate:"WKC\n","color":"gold"},{translate:"오버드라이브","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.wkc append value {bgm:"wkcdrift",text:[{translate:"WKC\n","color":"gold"},{translate:"오버드라이브\n(Drift)","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.wkc append value {bgm:"wkc2",text:[{translate:"WKC\n","color":"gold"},{translate:"월드 챔프","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.wkc append value {bgm:"wkc3",text:[{translate:"WKC[포뮬러]\n","color":"gold"},{translate:"Crush Your Back","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.wkc append value {bgm:"wkc4",text:[{translate:"WKC[포뮬러]\n","color":"gold"},{translate:"Asphalt Canon","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.wkc append value {bgm:"wkc5",text:[{translate:"WKC[포뮬러]\n","color":"gold"},{translate:"Crush Driver","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.wkc append value {bgm:"wind",text:[{translate:"WKC[빌리지]\n","color":"gold"},{translate:"바람을 가르며","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#비치
data modify storage bgm-room bgms.beach append value {bgm:"beach",text:[{translate:"비치\n","color":"blue"},{translate:"알로하, 레나!","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.beach append value {bgm:"beach2",text:[{translate:"비치[월드]\n","color":"blue"},{translate:"케피의 발견","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}

#쥐라기
data modify storage bgm-room bgms.dino append value {bgm:"dino1",text:[{translate:"쥐라기\n","color":"gold"},{translate:"카르노와 레이싱 한판!","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}

#월드
data modify storage bgm-room bgms.world append value {bgm:"rio",text:[{translate:"월드\n","color":"blue"},{translate:"리오 대축제","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.world append value {bgm:"world",text:[{translate:"월드\n","color":"blue"},{translate:"새로운 세계","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.world append value {bgm:"world2",text:[{translate:"월드\n","color":"blue"},{translate:"첫번째 세계여행","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#네모
data modify storage bgm-room bgms.nemo append value {bgm:"nemo",text:[{translate:"네모\n","color":"green"},{translate:"마녀는 장난을 좋아해","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.nemo append value {bgm:"nemo2",text:[{translate:"네모\n","color":"green"},{translate:"겨울에는 캐롤","color":"aqua"}],composer:{translate:"By tco3307402","color":"aqua"}}
data modify storage bgm-room bgms.nemo append value {bgm:"nemo4",text:[{translate:"네모\n","color":"green"},{translate:"네모네모 페스티벌","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.nemo append value {bgm:"nemo3",text:[{translate:"네모\n","color":"green"},{translate:"네모네모 페스티벌\n[α]","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#도검
data modify storage bgm-room bgms.sword append value {bgm:"sword",text:[{translate:"도검\n","color":"white"},{translate:"바람보다 자유롭게","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.sword append value {bgm:"fengshen",text:[{translate:"도검[봉신전설]\n","color":"white"},{translate:"빛이 인도하는 전설","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#신화
data modify storage bgm-room bgms.god append value {bgm:"god1",text:[{translate:"신화\n","color":"gold"},{translate:"불꽃의 땅\n무스펠하임","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}


#어비스
data modify storage bgm-room bgms.abyss append value {bgm:"abyss",text:[{translate:"어비스\n","color":"blue"},{translate:"너울대는 파도처럼","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.abyss append value {bgm:"abyss2",text:[{translate:"어비스\n","color":"blue"},{translate:"꿈꾸는 소녀","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#코리아
data modify storage bgm-room bgms.korea append value {bgm:"korea",text:[{translate:"코리아\n","color":"blue"},{translate:"여우 꼬리는 아홉 개?","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.korea append value {bgm:"korea2a",text:[{translate:"코리아\n","color":"blue"},{translate:"웰컴 투 코리아","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.korea append value {bgm:"korea2",text:[{translate:"코리아\n","color":"blue"},{translate:"웰컴 투 코리아\n[α]","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.korea append value {bgm:"korea3",text:[{translate:"코리아\n","color":"blue"},{translate:"소울 드라이브","color":"aqua"}],composer:{translate:"By Dawn_Rain","color":"aqua"}}


#메이플
data modify storage bgm-room bgms.maple append value {bgm:"maplenew",text:[{translate:"메이플\n","color":"gold"},{translate:"꿈의 파편","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.maple append value {bgm:"maple",text:[{translate:"메이플\n","color":"gold"},{translate:"꿈의 파편\n[α]","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.maple append value {bgm:"maple2",text:[{translate:"메이플\n","color":"gold"},{translate:"Going on a Picnic","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#카우지
data modify storage bgm-room bgms.kauzee append value {bgm:"kauzee",text:[{translate:"카우지\n","color":"light_purple"},{translate:"낙원의 땅, 카우지","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.kauzee append value {bgm:"kauzee2",text:[{translate:"카우지\n","color":"light_purple"},{translate:"래빗홀 어셈블","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}


#마인크래프트
data modify storage bgm-room bgms.minecraft append value {bgm:"otherside",text:[{translate:"마인크래프트\n","color":"green"},{translate:"An otherside","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.minecraft append value {bgm:"dream",text:[{translate:"마인크래프트\n","color":"green"},{translate:"드래곤의 분노","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.minecraft append value {bgm:"lavachicken",text:[{translate:"마인크래프트\n","color":"green"},{translate:"Lava Chicken","color":"aqua"}],composer:{translate:"By tco3307402","color":"aqua"}}


#마리오카트
data modify storage bgm-room bgms.mariokart append value {bgm:"mkdsyoshifalls",text:[{translate:"마리오카트\n","color":"red"},{translate:"요시알 폭포","color":"aqua"}],composer:{translate:"© boomcar127","color":"aqua"},composer2:{translate:"Edit by KITE2459","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"mkdsdelfino",text:[{translate:"마리오카트\n","color":"red"},{translate:"몬테 타운","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"mkdsairshipfortress",text:[{translate:"마리오카트\n","color":"red"},{translate:"킬러 해적선","color":"aqua"}],composer:{translate:"© Luke Hackett","color":"aqua"},composer2:{translate:"Edit by KITE2459","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"mkdswaluigi",text:[{translate:"마리오카트\n","color":"red"},{translate:"와루이지 핀볼","color":"aqua"}],composer:{translate:"© Andrew Shand","color":"aqua"},composer2:{translate:"Edit by KITE2459","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"mkscsnowland",text:[{translate:"마리오카트\n","color":"red"},{translate:"스노우랜드","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"snesrainbow",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 SNES","color":"aqua"}],composer:{translate:"© JosueCr4ft","color":"aqua"},composer2:{translate:"Edit by L_Peng","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"mk8rainbow64",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 64","color":"aqua"}],composer:{translate:"© Yoshimaster","color":"aqua"},composer2:{translate:"Edit by KITE2459","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"mkgbarainbow",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 GBA","color":"aqua"}],composer:{translate:"© Bluecobra_1","color":"aqua"},composer2:{translate:"Edit by L_Peng","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"mkddrainbow",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 GC","color":"aqua"}],composer:{translate:"© khinsider","color":"aqua"},composer2:{translate:"Edit by KITE2459","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"mkdsrainbow",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 DS","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"rainbow1",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 WORLD-1","color":"aqua"}],composer:{translate:"© resmaantoniol","color":"aqua"},composer2:{translate:"Edit by LogGamja","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"rainbow2",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 WORLD-2","color":"aqua"}],composer:{translate:"© resmaantoniol","color":"aqua"},composer2:{translate:"Edit by LogGamja","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"rainbow3",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 WORLD-3","color":"aqua"}],composer:{translate:"© resmaantoniol","color":"aqua"},composer2:{translate:"Edit by LogGamja","color":"green"}}
data modify storage bgm-room bgms.mariokart append value {bgm:"rainbow4",text:[{translate:"마리오카트\n","color":"red"},{translate:"무지개 로드 WORLD-4","color":"aqua"}],composer:{translate:"© resmaantoniol","color":"aqua"},composer2:{translate:"Edit by LogGamja","color":"green"}}

#소닉
data modify storage bgm-room bgms.sonic append value {bgm:"cityescape",text:[{translate:"소닉\n","color":"blue"},{translate:"Escape From\nthe City","color":"aqua"}],composer:{translate:"By tco3307402","color":"aqua"}}
data modify storage bgm-room bgms.sonic append value {bgm:"ghz",text:[{translate:"소닉\n","color":"blue"},{translate:"Green Hill Zone","color":"aqua"}],composer:{translate:"By tco3307402","color":"aqua"}}
data modify storage bgm-room bgms.sonic append value {bgm:"greenhillcourse_sonicdrift",text:[{translate:"소닉\n","color":"blue"},{translate:"Green Hill Course","color":"aqua"}],composer:{translate:"By tco3307402","color":"aqua"}}


#서드파티
data modify storage bgm-room bgms.thirdparty append value {bgm:"run90",text:[{translate:"서드파티\n","color":"gold"},{translate:"Running in the 90\'s","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.thirdparty append value {bgm:"banjo",text:[{translate:"서드파티\n","color":"gold"},{translate:"Pause Screen","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.thirdparty append value {bgm:"sans",text:[{translate:"서드파티\n","color":"gold"},{translate:"Megalovania","color":"aqua"}],composer:{translate:"By ShinkoNetCavy","color":"aqua"}}


#기타
data modify storage bgm-room bgms.etc append value {bgm:"introbgm",text:[{translate:"오프닝\n","color":"yellow"},{translate:"K/A/R/T/R/I/D/E/R","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.etc append value {bgm:"end",text:[{translate:"엔딩\n","color":"yellow"},{translate:"낙타 경주","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.etc append value {bgm:"creditmusic",text:[{translate:"엔딩 크레딧\n","color":"yellow"},{translate:"깃발이 어딨지?","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.etc append value {bgm:"license",text:[{translate:"라이센스\n","color":"yellow"},{translate:"라이센스","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.etc append value {bgm:"kartstore",text:[{translate:"차고\n","color":"yellow"},{translate:"상점","color":"aqua"}],composer:{translate:"© 이석희1","color":"aqua"},composer2:{translate:"Edit by KITE2459","color":"green"}}
data modify storage bgm-room bgms.etc append value {bgm:"driftgarage",text:[{translate:"스페셜 차고\n","color":"yellow"},{translate:"차량 정비","color":"aqua"}],composer:{translate:"By LogGamja","color":"aqua"}}
data modify storage bgm-room bgms.etc append value {bgm:"multiplayroom",text:[{translate:"메뉴\n","color":"yellow"},{translate:"멀티플레이어","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}
data modify storage bgm-room bgms.etc append value {bgm:"singleplayroom",text:[{translate:"메뉴\n","color":"yellow"},{translate:"싱글플레이어","color":"aqua"}],composer:{translate:"By WyvernP","color":"aqua"},composer2:{translate:"Producer KITE2459","color":"green"}}



#변수 설정
scoreboard objectives add track-ui dummy

#브금루프
scoreboard objectives add bgm-section-target dummy