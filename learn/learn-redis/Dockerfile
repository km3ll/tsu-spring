FROM eclipse-temurin:17-jre

RUN apt-get update && \
    apt-get install -y vim && \
    rm -rf /var/lib/apt/lists/*

COPY files/credentials.json /app/credentials.json

COPY target/learn-redis-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
