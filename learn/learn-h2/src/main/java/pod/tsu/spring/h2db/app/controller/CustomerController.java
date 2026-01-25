package pod.tsu.spring.h2db.app.controller;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pod.tsu.spring.h2db.app.dto.ResponseDto;
import pod.tsu.spring.h2db.model.Customer;
import pod.tsu.spring.h2db.repository.CustomerRepository;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("customer")
    public ResponseEntity<ResponseDto> save(@RequestBody Customer customer) {
        repository.save(customer);
        return ResponseEntity.ok(new ResponseDto("Customer created"));
    }

    @PutMapping("customer")
    public ResponseEntity<ResponseDto> update(@RequestBody Customer customer) {
        repository.save(customer);
        return ResponseEntity.ok(new ResponseDto("Customer updated"));
    }

    @GetMapping("customer")
    public ResponseEntity<List<Customer>> findByAll() {
        var customers = IterableUtils.toList(repository.findAll());
        return ResponseEntity.ok(customers);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("customer/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok(new ResponseDto("Customer deleted"));
    }

}