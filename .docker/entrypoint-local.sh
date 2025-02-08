#!/usr/bin/env bash
# set -x
set -eou pipefail
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:8000 -jar ./ryoko-api.jar