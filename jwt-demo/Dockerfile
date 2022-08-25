FROM openjdk:11-slim as build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} jwt-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/jwt-demo-0.0.1-SNAPSHOT.jar"]