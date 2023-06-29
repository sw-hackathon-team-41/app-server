FROM openjdk:17-alpine

ARG JAR_FILE_PATH=./build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar

EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]