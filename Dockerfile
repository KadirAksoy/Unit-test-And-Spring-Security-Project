FROM openjdk:17
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Unit-test-And-Spring-Security-Project-0.0.1-SNAPSHOT.jar
ENTRYPOINT["java","-jar","Unit-test-And-Spring-Security-Project-0.0.1-SNAPSHOT.jar"]