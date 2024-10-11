package pod.tsu.spring.ecommerce.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pod.tsu.spring.ecommerce.model.Customer;
import pod.tsu.spring.ecommerce.repository.template.h2.CustomerH2DAO;
import pod.tsu.spring.ecommerce.repository.template.h2.CustomerH2Template;

import java.util.Optional;

@Repository
public class CustomerRepositoryH2 implements CustomerRepository {

    private final Logger logger = LoggerFactory.getLogger(CustomerRepositoryH2.class);

    private final CustomerH2Template h2Template;

    public CustomerRepositoryH2(CustomerH2Template h2Template) {
        this.h2Template = h2Template;
        logger.info("Repository instantiated");
    }

    @Override
    public Customer save(Customer customer) {
        logger.info("Saving customer");
        h2Template.save(mapToDAO(customer));
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        logger.info("Updating customer");
        h2Template.save(mapToDAO(customer));
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        logger.info("Finding customer");
        return h2Template.findById(id).map(this::mapFromDAO);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting customer");
        h2Template.deleteById(id);
    }

    private CustomerH2DAO mapToDAO(Customer customer) {
        return new CustomerH2DAO(customer.getId(), customer.getName());
    }

    private Customer mapFromDAO(CustomerH2DAO dao) {
        return new Customer(dao.getId(), dao.getName());
    }

}