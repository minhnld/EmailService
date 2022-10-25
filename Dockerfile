# syntax=docker/dockerfile:experimental
FROM openjdk:11 AS build

ARG AWS_DEFAULT_REGION
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY
ARG AWS_SESSION_TOKEN

WORKDIR /app

# Gradle installation
COPY gradlew ./gradlew
COPY gradle ./gradle
RUN --mount=type=cache,id=gradle,target=/root/.gradle ./gradlew --no-daemon

# Gradle settings
COPY settings.gradle.kts build.gradle.kts gradle.properties install-git-hooks.gradle.kts ./

# Sources
COPY applications  ./applications
COPY domain-models  ./domain-models
COPY infrastructures  ./infrastructures
COPY features  ./features
COPY api  ./api

# Build boot jar
RUN --mount=type=cache,id=gradle,target=/root/.gradle ./gradlew \
    --console=plain \
    --no-daemon \
    -PbuildEnv=ci \
    --init-script ./gradle/gradle-init.d/init.gradle.kts \
    -i :applications:bootJar

FROM openjdk:11-jre-slim

ADD https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.13.0/elastic-apm-agent-1.13.0.jar /app/elastic-apm-agent.jar

RUN apt-get update

COPY docker/run.sh /app/run.sh

ENTRYPOINT [ "/app/run.sh" ]

EXPOSE 8080

COPY --from=build /app/applications/grpc-service/build/libs/grpc-service.jar /app/
COPY --from=build /app/applications/graphql-api-service/build/libs/graphql-api-service.jar /app/



