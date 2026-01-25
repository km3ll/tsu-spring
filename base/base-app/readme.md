# base-app

## commands

```bash
mvn clean package
mvn spring-boot:run

java -jar target/base-app-1.0.jar

docker run -d -p 8080:8080 base-app:latest
docker tag <container-id> base-app:latest
```