# tsu-spring

- [modules](#modules)
- [commands](#commands)
- [features](#features)
- [tutorials](#tutorials)

## modules

- [bookstore](bookstore)
- [pokemon](pokemon)
- [secureapi](secureapi)

## commands

```bash
mvn clean compile
mvn -pl <module> command

mvn -Dtests=AllTests test
mvn -Dtests=Unit test
mvn -Dtests=Integration test
mvn -Dtests=Parallel test
mvn -Dtests=Performance test

protoc -I=bookstore/src/main/resources/protobuf --java_out=bookstore/src/main/java author.proto
```

## features

- AssertJ
- GitHub Flows
- Instancio
- JUnit5
- JParams
- Lombok
- Mockito
- Spring Boot Web

## tutorials

- Configuration Properties in Spring by Baeldung: [blog](https://www.baeldung.com/configuration-properties-in-spring-boot)
- H2 Database Spring Boot by Dan Vega: [youtube](https://www.youtube.com/watch?v=PSrHcCwvfVQ), [github](https://github.com/danvega/h2-demo)
- Instancio Unit Test Data: [guide](https://www.instancio.org/user-guide/)
- Introduction to Protocol Buffers by Baeldung: [blog](https://www.baeldung.com/google-protocol-buffer)
- JUnit5 Parameterized Tests: [guide](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests)
- Load Initial Data with Spring Boot by Baeldung: [blog](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql)
- Log Incoming Requests by Baeldung: [youtube](https://www.baeldung.com/spring-http-logging)
- Mockito Verify Cookbook by Baeldung: [blog](https://www.baeldung.com/mockito-verify)
- Parallel Test Execution by Baeldung: [blog](https://www.baeldung.com/junit-5-parallel-tests)
- Running Logic on Startup by Baeldung: [blog](https://www.baeldung.com/running-setup-logic-on-startup-in-spring)
- Scheduled annotation in Spring by Baeldung: [blog](https://www.baeldung.com/spring-scheduled-tasks)
- Spring Actuators [blog](https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/production-ready-endpoints.html)
- Spring Access Log Filter: [youtube](https://www.youtube.com/watch?v=E060vI9JWaM)
- Spring Call REST API by Dan Vega: [youtube](https://www.youtube.com/watch?v=XEtPVm_SL2Q)
- Spring Data @JPA Query by Baeldung: [blog](https://www.baeldung.com/spring-data-jpa-query)
- Spring Security by Teddy Smith: [youtube](https://www.youtube.com/watch?v=GjN5IauaflY&list=PL82C6-O4XrHe3sDCodw31GjXbwRdCyyuY&index=1), [github](https://github.com/teddysmithdev/pokemon-review-springboot/tree/master)
- Spring Unit Testing by Teddy Smith: [youtube](https://www.youtube.com/watch?v=jqwZthuBmZY&list=PL82C6-O4XrHcg8sNwpoDDhcxUCbFy855E), [github](https://github.com/teddysmithdev/pokemon-review-springboot/tree/master)
- Testcontainers From Zero to Hero by @MarcoCodes: [youtube](https://www.youtube.com/watch?v=v3eQCIWLYOw)
- Testing Spring Boot Applications by Philip Riecks: [youtube](https://www.youtube.com/watch?v=hR0bbk2tsF0)