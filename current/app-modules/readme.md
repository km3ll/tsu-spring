# app-modules

## commands

```bash
mvn clean install
mvn -pl main-app spring-boot:run

java -jar target/app-modules-1.0.jar

docker run -d -p 8080:8080 app-modules:latest
docker tag <container-id> app-modules:latest
```

## structure

```
app-modules/
│── pom.xml
├── main-app/     <-- Main Spring Boot app
│   └── pom.xml
└── utils/        <-- Spring Boot starter module
    └── pom.xml
```