package pod.tsu.spring.ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pod.tsu.spring.ecommerce.model.Customer;
import pod.tsu.spring.ecommerce.repository.CustomerRepository;

import java.util.Optional;

public class CustomerServiceSingleRepo implements CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceSingleRepo.class);

    private final CustomerRepository repository;

    public CustomerServiceSingleRepo(CustomerRepository repository) {
        this.repository = repository;
        logger.info("Service instantiated");
    }

    @Override
    public Customer save(Customer customer) {
        repository.save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        repository.save(customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}