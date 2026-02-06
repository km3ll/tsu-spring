#!/usr/bin/env bash
mvn spring-javaformat:apply
mvn clean package
docker build -t learn-redis .
docker run --rm -it --name learn-redis-app --network redis-net -p 8080:8080 learn-redis:latest