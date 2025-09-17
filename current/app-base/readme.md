# app-base

## commands

```bash
mvn clean package
java -jar target/app-base-1.0.jar

docker run -d -p 8080:8080 app-base:latest
docker tag <container-id> app-base:latest
```