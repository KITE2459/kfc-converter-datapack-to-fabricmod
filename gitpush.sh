#!/bin/bash

COMMIT_MSG="$(date '+%Y-%m-%d_%H-%M-%S')"

git add .
git commit -m "$COMMIT_MSG"
git push