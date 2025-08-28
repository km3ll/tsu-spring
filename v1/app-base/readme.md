# app-base

## commands

```bash
mvn clean package
java -jar target/app-base-1.0.jar

docker tag 234faf2930ce app-base:latest
docker run -d -p 8080:8080 app-base:latest
```