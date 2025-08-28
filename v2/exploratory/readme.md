# exploratory

## commands

```bash
mvn clean package
java -jar target/exploratory-1.0.jar

docker tag <container-id> exploratory:latest
docker run -d -p 8080:8080 exploratory:latest
```