package pod.tsu.spring.dynamodb.persistence.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pod.tsu.spring.dynamodb.persistence.AttributeName;
import pod.tsu.spring.dynamodb.persistence.entity.Employee;

import java.util.Optional;

@Repository
public class EmployeeRepository {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeRepository.class);

    private final DynamoDBMapper dynamoDBMapper;

    public EmployeeRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        LOG.info("Repository created");
    }

    public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee update(Employee employee) {
        var saveExpression = new DynamoDBSaveExpression()
            .withExpectedEntry(AttributeName.EMPLOYEE_ID,
                new ExpectedAttributeValue(new AttributeValue().withS(employee.getEmployeeId()))
            );
        dynamoDBMapper.save(employee, saveExpression);
        return employee;
    }

    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(dynamoDBMapper.load(Employee.class, id));
    }

    public void delete(String id) {
        Optional.ofNullable(dynamoDBMapper.load(Employee.class, id))
            .ifPresent(dynamoDBMapper::delete);
    }

}