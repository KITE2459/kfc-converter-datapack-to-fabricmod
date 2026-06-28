#git add .
#git commit -m "Auto-Push"
#git push origin beta
#위: 기존 코드


TITLE=${1:-"Auto-Push"}
DESC=${2:-"Auto-Description"}

git add .
git commit -m "$TITLE" -m "$DESC"
git push origin beta
