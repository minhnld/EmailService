#!/usr/bin/env bash

set -eux

if [ "$#" -ne 2 ]; then
    echo "./run.sh MAIN_CLASS APPLICATION_NAME."
    exit 0
fi

MAIN_CLASS="$1"
shift

JAR_PATH="/app/$1.jar"
shift

if [[ -z "${EXTRA_SPRING_PROFILES:-}" ]]
then
ACTIVE_PROFILES="${APP_ENV}"
else
ACTIVE_PROFILES="${APP_ENV},${EXTRA_SPRING_PROFILES}"
fi

java \
  -Dspring.profiles.active="${ACTIVE_PROFILES}" \
  -Dsentry.environment="${APP_ENV}" \
  -classpath ${JAR_PATH} \
  -Dloader.main="$MAIN_CLASS" \
  -javaagent:/app/elastic-apm-agent.jar \
  org.springframework.boot.loader.PropertiesLauncher "$@"
