package pod.tsu.spring.ecommerce.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pod.tsu.spring.ecommerce.repository.CustomerRepositoryH2;
import pod.tsu.spring.ecommerce.repository.CustomerRepositoryHashMap;
import pod.tsu.spring.ecommerce.service.CustomerService;
import pod.tsu.spring.ecommerce.service.CustomerServiceDualRepo;
import pod.tsu.spring.ecommerce.service.CustomerServiceSingleRepo;

@Configuration
public class RepoConfig {

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceDualRepo.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CustomerRepositoryH2 customerRepositoryH2;

    @Autowired
    private CustomerRepositoryHashMap customerRepositoryHashMap;

    @Bean
    public CustomerService customerService() {
        var profiles = environment.getActiveProfiles();
        if (profiles.length == 1) {
            return startServiceInSingleMode(profiles[0]);
        } else {
            return startServiceInDualMode(profiles[0]);
        }
    }

    private CustomerService startServiceInSingleMode(String profile) {
        logger.info("Single mode enabled with repository {}", profile);
        if (profile.equalsIgnoreCase("db-h2")) {
            return new CustomerServiceSingleRepo(customerRepositoryH2);
        } else {
            return new CustomerServiceSingleRepo(customerRepositoryHashMap);
        }
    }

    private CustomerService startServiceInDualMode(String profile) {
        logger.info("Dual mode enabled with primary repository {}", profile);
        if (profile.equalsIgnoreCase("db-h2")) {
            return new CustomerServiceDualRepo(customerRepositoryH2, customerRepositoryHashMap);
        } else {
            return new CustomerServiceDualRepo(customerRepositoryHashMap, customerRepositoryH2);
        }
    }

}