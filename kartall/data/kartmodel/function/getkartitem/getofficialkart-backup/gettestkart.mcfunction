# 기준 카트 연산법
# 기준 카트 RARE는, 기준 카트 LEGEND의 성능에서 guage, boosttime, maxboostcount, defense를 제외한 모든 성능 수치가 0.3% 낮습니다.
# 기준 카트 RARE는 추가로 guage가 4, boosttime가 -2, defence가 7 더 높습니다 (LEGEND 대비). 글자색은 dark_purple입니다.
# 기준 카트 COMMON은, 기준 카트 RARE의 성능에서 speed, accel, boost, corner, drift를 제외한 모든 성능 수치가 0.3% 낮습니다.
# 기준 카트 COMMON은 추가로 guage가 5, boosttime가 2, defence가 9 더 높습니다 (RARE 대비). 글자색은 gray입니다.
# 기준 카트 COMMON[95%] 는, 기준 카트 COMMON의 성능에서 speed, accel, boost, corner, drift를 제외한 모든 성능 수치가 5% 낮습니다.
# 기준 카트 COMMON[95%] 는 추가로 gauge가 6, boosttime가 3, defence가 11 더 높습니다 (COMMON 대비).
# 기준 카트는 A, B, C 등등이 있습니다.
function kartmain:makekart {"name":{"color":"yellow","italic":false,"translate":"기준 카트 A - LEGEND"},"speed":512,"accel":501,"boost":289,"corner":210,"drift":480,"gauge":14,"boosttime":59,"maxboostcount":2,"defense":33,"draft":0,"item":"practice-cyber","model":"kite-practice-cyber"}
function kartmain:makekart {"name":{"color":"dark_purple","italic":false,"translate":"기준 카트 A - RARE"},"speed":510,"accel":499,"boost":288,"corner":209,"drift":478,"gauge":18,"boosttime":61,"maxboostcount":2,"defense":40,"draft":0,"item":"practice-cyber","model":"kite-practice-cyber"}
function kartmain:makekart {"name":{"color":"gray","italic":false,"translate":"기준 카트 A - COMMON"},"speed":508,"accel":497,"boost":287,"corner":208,"drift":477,"gauge":23,"boosttime":63,"maxboostcount":2,"defense":49,"draft":0,"item":"practice-cyber","model":"kite-practice-cyber"}
function kartmain:makekart {"name":{"color":"gray","italic":false,"translate":"기준 카트 A - COMMON [95%]"},"speed":486,"accel":467,"boost":277,"corner":198,"drift":453,"gauge":29,"boosttime":66,"maxboostcount":2,"defense":60,"draft":0,"item":"practice-cyber","model":"kite-practice-cyber"}

function kartmain:makekart {"name":{"color":"yellow","italic":false,"translate":"기준 카트 B - LEGEND"},"speed":495,"accel":569,"boost":320,"corner":176,"drift":420,"gauge":11,"boosttime":58,"maxboostcount":2,"defense":35,"draft":0,"item":"burstxun","model":"kite-darkburst"}
function kartmain:makekart {"name":{"color":"dark_purple","italic":false,"translate":"기준 카트 B - RARE"},"speed":493,"accel":567,"boost":319,"corner":175,"drift":418,"gauge":15,"boosttime":60,"maxboostcount":2,"defense":42,"draft":0,"item":"burstxun","model":"kite-darkburst"}
function kartmain:makekart {"name":{"color":"gray","italic":false,"translate":"기준 카트 B - COMMON"},"speed":491,"accel":565,"boost":318,"corner":174,"drift":417,"gauge":20,"boosttime":62,"maxboostcount":2,"defense":51,"draft":0,"item":"burstxun","model":"kite-darkburst"}
function kartmain:makekart {"name":{"color":"gray","italic":false,"translate":"기준 카트 B - COMMON [95%]"},"speed":470,"accel":532,"boost":307,"corner":165,"drift":396,"gauge":26,"boosttime":65,"maxboostcount":2,"defense":62,"draft":0,"item":"burstxun","model":"kite-darkburst"}
