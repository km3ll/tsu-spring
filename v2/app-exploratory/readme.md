# app-exploratory

## commands

```bash
mvn clean package
java -jar target/app-exploratory-1.0.jar

docker tag <container-id> app-exploratory:latest
docker run -d -p 8080:8080 app-exploratory:latest
```

## packages

```
├── ExploratoryApp.java
└── pod
    ├── config
    ├── controller
    ├── domain
    │   ├── model
    │   ├── repository
    │   └── service
    ├── dto
    ├── exception
    ├── infrastructure
    │   ├── cache
    │   ├── config
    │   ├── external
    │   ├── logging
    │   ├── messaging
    │   ├── persistence
    │   │   ├── dao
    │   │   ├── mapper
    │   │   └── repository
    │   ├── scheduler
    │   └── security
    ├── mapper
    └── service

```