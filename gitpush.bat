@echo off

for /f %%i in ('powershell -command "Get-Date -Format yyyy-MM-dd_HH-mm-ss"') do set COMMIT_MSG=%%i

git add .
git commit -m "%COMMIT_MSG%"
git push