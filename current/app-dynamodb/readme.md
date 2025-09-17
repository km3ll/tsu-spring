# app-dynamodb

## commands

```bash
mvn clean package
java -jar target/app-dynamodb-1.0.jar

docker run -d -p 8080:8080 app-dynamodb:latest
docker tag <container-id> app-dynamodb:latest
```