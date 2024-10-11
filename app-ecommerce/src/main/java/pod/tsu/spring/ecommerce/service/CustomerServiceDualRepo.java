package pod.tsu.spring.ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pod.tsu.spring.ecommerce.model.Customer;
import pod.tsu.spring.ecommerce.repository.CustomerRepository;
import pod.tsu.spring.ecommerce.repository.CustomerRepositoryH2;

import java.util.Optional;

public class CustomerServiceDualRepo implements CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceDualRepo.class);

    private final CustomerRepository primaryRepository;
    private final CustomerRepository secondaryRepository;

    public CustomerServiceDualRepo(CustomerRepository primaryRepository, CustomerRepository secondaryRepository) {
        this.primaryRepository = primaryRepository;
        this.secondaryRepository = secondaryRepository;
        logger.info("Service instantiated");
    }

    @Override
    public Customer save(Customer customer) {
        primaryRepository.save(customer);
        secondaryRepository.save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        primaryRepository.save(customer);
        secondaryRepository.save(customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return primaryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        primaryRepository.deleteById(id);
        secondaryRepository.deleteById(id);
    }

}