FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tecobrary-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev", "/tecobrary-0.0.1-SNAPSHOT.jar"]