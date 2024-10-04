package pod.tsu.spring.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.stereotype.Repository;
import pod.tsu.spring.dynamodb.entity.Employee;

@Repository
public class EmployeeRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public EmployeeRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee findById(String id) {
        return dynamoDBMapper.load(Employee.class, id);
    }

    public String delete(String id) {
        var employee = dynamoDBMapper.load(Employee.class, id);
        dynamoDBMapper.delete(employee);
        return "Employee deleted using ID: " + id;
    }

    public String update(Employee employee) {
        dynamoDBMapper.save(employee,
            new DynamoDBSaveExpression()
                .withExpectedEntry("employeeId",
                    new ExpectedAttributeValue(
                        new AttributeValue().withS(employee.getEmployeeId())
                    )
                )
        );
        return "Employee updated using ID: " + employee.getEmployeeId();
    }

}
