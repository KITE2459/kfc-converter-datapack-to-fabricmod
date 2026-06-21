#!/bin/bash

COMMIT_MSG="$(date '+%Y-%m-%d_%H-%M-%S')"

git rm -r --cached .
git add .
git commit -m "$COMMIT_MSG"
git push