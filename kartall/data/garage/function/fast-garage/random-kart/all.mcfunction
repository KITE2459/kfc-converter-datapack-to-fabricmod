function garage:fast-garage/common/show-ui
function garage:fast-garage/rare/show-ui
function garage:fast-garage/legend/show-ui
function garage:fast-garage/unique/show-ui
function garage:fast-garage/special/show-ui
function garage:fast-garage/classic/show-ui

execute at @e[tag=fast-garage-interaction,limit=1,sort=random] run function garage:fast-garage/getkart

function garage:fast-garage/remove-all-ui
