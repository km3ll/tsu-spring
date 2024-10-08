package pod.tsu.spring.dynamodb.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.dynamodb.entity.Employee;
import pod.tsu.spring.dynamodb.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/employee")
    public Employee save(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable("id") String id) {
        return employeeRepository.findById(id);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteById(@PathVariable("id") String id) {
        return employeeRepository.delete(id);
    }

    @PutMapping("/employee/{id}")
    public String update(@PathVariable("id") String id, @RequestBody Employee employee) {
        return employeeRepository.update(employee);
    }

}
