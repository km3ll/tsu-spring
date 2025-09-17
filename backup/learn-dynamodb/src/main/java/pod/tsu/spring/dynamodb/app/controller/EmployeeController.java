package pod.tsu.spring.dynamodb.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.dynamodb.app.dto.ResponseDto;
import pod.tsu.spring.dynamodb.persistence.entity.Employee;
import pod.tsu.spring.dynamodb.persistence.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/")
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("employee")
    public ResponseEntity<ResponseDto> save(@RequestBody Employee employee) {
        LOG.info("Saving employee : {}", employee);
        employeeRepository.save(employee);
        return ResponseEntity.ok(new ResponseDto("Employee created with id: " + employee.getEmployeeId()));
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") String id, @RequestBody Employee employee) {
        LOG.info("Updating employee : {}", employee);
        if (!id.equals(employee.getEmployeeId())) {
            var response = new ResponseDto("Employee id mismatch");
            return ResponseEntity.badRequest().body(response);
        }
        employeeRepository.update(employee);
        return ResponseEntity.ok(new ResponseDto("Employee updated"));
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") String id) {
        LOG.info("Finding employee with ID: {}", id);
        return employeeRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") String id) {
        LOG.info("Deleting employee with ID: {}", id);
        employeeRepository.delete(id);
        return ResponseEntity.ok(new ResponseDto("Employee deleted"));
    }

}