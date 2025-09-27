FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/app-modules*.jar
COPY ${JAR_FILE} /tmp/app.jar
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]