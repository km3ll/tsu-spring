# learn-redis

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