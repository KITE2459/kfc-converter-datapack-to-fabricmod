execute unless score game wave matches 0 run title @s actionbar [{text:"소지금: ", bold:true, color:green},{score:{name:"@s", objective:money},bold:false, color:yellow},{text:" | ", bold:false, color:white},\
{text:"체력: ", bold:true, color:red},{score:{name:"game", objective:game.base_health},bold:false, color:yellow},{text:" | ", bold:false, color:white},\
{text:"웨이브: ", bold:true, color:aqua},{score:{name:"game", objective:wave},bold:false, color:yellow},{text:" | ", bold:false, color:white}]
execute if score game wave matches 0 run title @s actionbar [{text:"소지금: ", bold:true, color:green},{score:{name:"@s", objective:money},bold:false, color:yellow},{text:" | ", bold:false, color:white},\
{text:"체력: ", bold:true, color:red},{score:{name:"game", objective:game.base_health},bold:false, color:yellow},{text:" | ", bold:false, color:white},\
{text:"웨이브: ", bold:true, color:aqua},{text:"준비",bold:true, color:green},{text:" | ", bold:false, color:white}]