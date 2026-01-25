package com.example.demo;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.model.Feature;
import com.example.model.FeatureRepository;
import com.example.model.User;
import com.example.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.List;


@SpringBootApplication
@EnableCassandraRepositories(basePackages="com.example.model")
public class DemoApplication implements CommandLineRunner {

    @Autowired(required = true)
    private UserRepository sampleUserRepository;

    @Autowired(required = true)
    private FeatureRepository featureRepository;

    @Autowired
    private CassandraOperations cassandraTemplate;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        CqlTemplate cqlTemplate = (CqlTemplate) cassandraTemplate.getCqlOperations();
        CqlSession session = cqlTemplate.getSession();

        int count = session.execute ("SELECT * FROM aws.app_features").all().size();
        System.out.println("Number of features: "+  count);

        insertFeature();
        findAllFeatures().forEach(System.out::println);

        deleteFeature();
        findAllFeatures().forEach(System.out::println);

    }

    private void runCount(CqlSession session) {
        int count = session.execute ("SELECT * FROM system.peers").all().size();
        System.out.println("Number of hosts: "+  count);
    }

    private void testDriveUser() {
        String userName = "aws-user";
        User userIn = new User();
        userIn.setUsername(userName);
        userIn.setFname("emma");
        userIn.setLname("brie");
        sampleUserRepository.insert(userIn);
        User userOut = sampleUserRepository.findByUsername(userName);
        System.out.println("Primary Key: " + userOut.getUsername());
    }

    private void insertFeature() {
        Feature.Key key = Feature.Key.builder()
            .companyId("company_456")
            .countryCode("US")
            .featureId("event_logs")
            .build();
        Feature feature = Feature.builder()
            .key(key)
            .build();
        featureRepository.insert(feature);
        System.out.println(" > Inserted feature: " + feature);
    }

    private void deleteFeature() {
        Feature.Key key = Feature.Key.builder()
            .companyId("company_456")
            .countryCode("US")
            .featureId("event_logs")
            .build();
        featureRepository.deleteById(key);
        System.out.println(" > Deleted feature: " + key);
    }

    private List<Feature> findAllFeatures() {
        return featureRepository.findAll();
    }

}