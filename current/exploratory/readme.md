# exploratory

Table of contents

- [commands](#commands)
- [concepts](#concepts)
- [features](#features)
  - [swagger](#swagger)

## concepts

- Spring profiles
- Springdoc OpenAPI

## commands

Maven

```bash
mvn clean package
mvn spring-javaformat:apply

mvn spring-boot:run 
mvn spring-boot:run -Dspring-boot.run.profiles=base
```

Java

```bash
java -jar target/exploratory-1.0.jar
```

Docker

```bash
docker tag <container-id> exploratory:latest
docker run -d -p 8080:8080 exploratory:latest
```

## features

### swagger

- http://localhost:8080/swagger-ui/index.html