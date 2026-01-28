package tsu.pod.dynamodb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsu.pod.dynamodb.domain.CustomerItem;
import tsu.pod.dynamodb.repository.CustomerItemRepository;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerItemRepository customerItemRepository;

	public CustomerController(CustomerItemRepository customerItemRepository) {
        this.customerItemRepository = customerItemRepository;
	}

    @PostMapping
    public ResponseEntity<CustomerItem> createCustomer(CustomerItem customer) {
        customerItemRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerItem>> getAllCustomers() {
        return ResponseEntity.ok(customerItemRepository.findAllByPkPrefix());
    }

	@GetMapping("/{id}")
	public ResponseEntity<CustomerItem> getCustomer(@PathVariable("id") String id) {
		return ResponseEntity.ok(customerItemRepository.findById(id).orElse(null));
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id) {
        customerItemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}