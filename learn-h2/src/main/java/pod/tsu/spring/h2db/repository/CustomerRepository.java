package pod.tsu.spring.h2db.repository;

import org.springframework.data.repository.CrudRepository;
import pod.tsu.spring.h2db.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}