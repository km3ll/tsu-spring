package pod.tsu.spring.ecommerce.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pod.tsu.spring.ecommerce.model.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepositoryHashMap implements CustomerRepository {

    private final Logger logger = LoggerFactory.getLogger(CustomerRepositoryHashMap.class);
    private final Map<Long, Customer> customers = new HashMap<>();

    public CustomerRepositoryHashMap() {
        logger.info("Repository instantiated");
    }

    @Override
    public Customer save(Customer customer) {
        logger.info("Saving customer");
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        logger.info("Updating customer");
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        logger.info("Finding customer");
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting customer");
        customers.remove(id);
    }

}