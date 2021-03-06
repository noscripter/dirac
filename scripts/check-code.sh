#!/usr/bin/env bash

# check our devtools code for google closure annotations and Chromium coding conventions

set -e

pushd `dirname "${BASH_SOURCE[0]}"` > /dev/null
source "./config.sh"

pushd "$DEVTOOLS_ROOT"

./scripts/compile_frontend.py

popd

popd
