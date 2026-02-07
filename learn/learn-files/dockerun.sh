#!/usr/bin/env bash
mvn spring-javaformat:apply
mvn clean package
docker build -t learn-files .
docker run --rm -it --name learn-files-app -p 8080:8080 learn-files:latest