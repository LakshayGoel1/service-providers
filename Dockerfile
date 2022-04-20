FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/service-providers-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]