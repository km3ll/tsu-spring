# tsu-spring

- [commands](#commands)
- [features](#features)
  - [secureapi](#secureapi)
- [tutorials](#tutorials)
- [references](#references)

## commands

```bash
mvn clean compile
mvn test -Dgroups=UnitTest
mvn test -Dgroups=IntegrationTest
mvn -pl <module> command
```

## features

Spring Boot Web, Lombok, Junit5, AssertJ, Mockito, Instancio.

### bookstore

Spring Boot Security, Spring Data JDBC, H2 Database, DDL Scripts (schema.sql), DML Scripts (data.sql), ConfigurableAppContext, CrudRepository, CommandLineRunner.

### pokemon

Spring Data JPA, H2 Database, DDL Scripts, DML Scripts, TestKit, Generators, FileUtils, TestTags, Arrange-Act-Assert, Given-When-Then

### secureapi

Spring Boot Security, JSON Web Token, AuthEntryPoint, AuthenticationFilter, UserDetails, SecurityFilterChain, AuthenticationManager, PasswordEncoder.

## tutorials

- H2 Database Spring Boot by Dan Vega: [youtube](https://www.youtube.com/watch?v=PSrHcCwvfVQ), [github](https://github.com/danvega/h2-demo)
- Instancio Unit Test Data: [guide](https://www.instancio.org/user-guide/)
- Load Initial Data with Spring Boot by Baeldung: [blog](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql)
- Mockito Verify Cookbook by Baeldung: [blog](https://www.baeldung.com/mockito-verify)
- Spring Call REST API by Dan Vega: [youtube](https://www.youtube.com/watch?v=XEtPVm_SL2Q)
- Spring Data @JPA Query by Baeldung: [blog](https://www.baeldung.com/spring-data-jpa-query)
- Spring Security by Teddy Smith: [youtube](https://www.youtube.com/watch?v=GjN5IauaflY&list=PL82C6-O4XrHe3sDCodw31GjXbwRdCyyuY&index=1), [github](https://github.com/teddysmithdev/pokemon-review-springboot/tree/master)
- Spring Testing by Teddy Smith: [youtube](https://www.youtube.com/watch?v=jqwZthuBmZY&list=PL82C6-O4XrHcg8sNwpoDDhcxUCbFy855E), [github](https://github.com/teddysmithdev/pokemon-review-springboot/tree/master)
- Testing Spring Boot Applications by Philip Riecks: [youtube](https://www.youtube.com/watch?v=hR0bbk2tsF0) 

## references