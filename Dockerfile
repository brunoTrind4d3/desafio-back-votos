ARG BUILD_IMAGE=maven:3.5-jdk-11
ARG RUNTIME_IMAGE=openjdk:11-jdk-slim

FROM ${BUILD_IMAGE} as dependencies
WORKDIR /opt/app
COPY pom.xml .

RUN mvn -B -e -C dependency:go-offline

FROM dependencies as build
WORKDIR /opt/app

COPY --from=dependencies /root/.m2 /root/.m2
COPY --from=dependencies /opt/app /opt/app

RUN mvn -B -e clean package

FROM ${RUNTIME_IMAGE}

WORKDIR /opt/app
COPY --from=build /opt/app/ .
EXPOSE 8080

CMD ["java", "-jar", "/opt/app/target/voting-sytem-0.0.1-SNAPSHOT.jar"]