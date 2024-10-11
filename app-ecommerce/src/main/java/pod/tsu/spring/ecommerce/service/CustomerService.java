package pod.tsu.spring.ecommerce.service;

import pod.tsu.spring.ecommerce.model.Customer;

import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    Customer update(Customer customer);

    Optional<Customer> findById(Long id);

    void deleteById(Long id);

}