# exploratory

## commands

**Maven**

```bash
mvn spring-javaformat:apply
mvn clean package
```

**Java**

```bash
java -jar target/exploratory-1.0.jar
```

**Docker**
```bash
docker tag <container-id> exploratory:latest
docker run -d -p 8080:8080 exploratory:latest
```