# learn-files

## features

- Atomic Reference
- Commons Collections
- Commons Pool2
- Configuration Properties
- Executable
- Executor Service
- File Monitor Credentials Provider
- Instancio
- Lombok
- Post-Construct
- Pre-Destroy
- Spring Boot Data Redis
- Spring Boot Test
- Spring Boot Web
- Spring Java Format
- Watch Service

## commands

#### bash

```bash
mvn spring-javaformat:apply
mvn clean test
```

#### docker

```bash
mvn clean package; 
docker build -t learn-files .

docker run --rm -it --name learn-files-app \
-p 8080:8080 learn-files:latest

./docker-run.sh
```