# Build Stage

FROM maven:3.9.8 AS build

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

################################################################################
# Exec Stage

FROM eclipse-temurin:21-jre-jammy AS exec

WORKDIR /opt/app

COPY --from=build /build/target/eureka-server*.jar ./eureka-server.jar

ARG UID=10001

RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

RUN mkdir -p /opt/app/logs && chown appuser:appuser /opt/app/logs

USER appuser

EXPOSE 8761

ENTRYPOINT ["java","-jar","./eureka-server.jar"]
