package pod.tsu.spring.ecommerce.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pod.tsu.spring.ecommerce.app.dto.ResponseDto;
import pod.tsu.spring.ecommerce.model.Customer;
import pod.tsu.spring.ecommerce.service.CustomerService;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("customer")
    public ResponseEntity<ResponseDto> save(@RequestBody Customer customer) {
        customerService.save(customer);
        return ResponseEntity.ok(new ResponseDto("Customer created"));
    }

    @PutMapping("customer")
    public ResponseEntity<ResponseDto> update(@RequestBody Customer customer) {
        customerService.update(customer);
        return ResponseEntity.ok(new ResponseDto("Customer updated"));
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        return customerService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("customer/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        return ResponseEntity.ok(new ResponseDto("Customer deleted"));
    }

}