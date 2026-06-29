SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DATAPACKS_DIR="$SCRIPT_DIR/datapacks-kart"

rm -rf "$DATAPACKS_DIR"
cp -r "$SCRIPT_DIR"/datapacks "$DATAPACKS_DIR"

rm -rf "$DATAPACKS_DIR"/.git
rm -rf "$DATAPACKS_DIR"/ani-*
rm -rf "$DATAPACKS_DIR"/achivement*
rm -rf "$DATAPACKS_DIR"/cutscene*
rm -rf "$DATAPACKS_DIR"/ending*
rm -rf "$DATAPACKS_DIR"/entity*
rm -rf "$DATAPACKS_DIR"/stevechago*
rm -rf "$DATAPACKS_DIR"/sys-voidviewer*
rm -rf "$DATAPACKS_DIR"/sys-guardrailtool*
rm -rf "$DATAPACKS_DIR"/boatpack*
rm -rf "$DATAPACKS_DIR"/entitycopy*
rm -rf "$DATAPACKS_DIR"/bangjangsuper*
rm -rf "$DATAPACKS_DIR"/aaadevrecorder
# rm -rf "$DATAPACKS_DIR"/playerstats-basic
rm -rf "$DATAPACKS_DIR"/update*

rm -rf "$DATAPACKS_DIR"/gamesystem/devbattle*
rm -rf "$DATAPACKS_DIR"/gamesystem/league*
rm -rf "$DATAPACKS_DIR"/gamesystem/license*
rm -rf "$DATAPACKS_DIR"/gamesystem/master*
rm -rf "$DATAPACKS_DIR"/gamesystem/timeattack*


rm -rf "$DATAPACKS_DIR"/.git
rm -rf "$DATAPACKS_DIR"/aaadevrecorder*
rm -rf "$DATAPACKS_DIR"/bgm-old
rm -rf "$DATAPACKS_DIR"/tools
rm -rf "$DATAPACKS_DIR"/auto_push.sh
rm -rf "$DATAPACKS_DIR"/*.py
rm -rf "$DATAPACKS_DIR"/*.json
rm -rf "$DATAPACKS_DIR"/*.txt
rm -rf "$DATAPACKS_DIR"/*.md