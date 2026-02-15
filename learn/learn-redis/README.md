# learn-redis

## features

- Atomic Reference
- Commons Collections
- Config Properties
- Credentials File
- Disposable Bean
- Docker Redis
- Executor Service
- File Monitor Credentials Provider
- Get Mapping
- Instancio
- Lettuce Client Options
- Lettuce Connection Factory
- Lettuce Pooling Client Config
- Lettuce Topology Refresh Options
- Lombok
- Post Mapping
- Redis Cache Manager
- Request Mapping
- Rest Controller
- Spring Boot Data Redis
- Spring Boot Maven Plugin
- Spring Boot Test
- Spring Boot Web
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
docker build -t learn-redis .

docker network create redis-net

docker exec -it docker-redis-node-1-1 redis-cli \
-a your_strong_password \
--cluster create \
redis-node-1:6379 \
redis-node-2:6379 \
redis-node-3:6379 \
--cluster-replicas 0

docker run --rm -it --name learn-redis-app \
--network redis-net -p 8080:8080 learn-redis:latest
```