package tsu.pod.dynamodb.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import tsu.pod.dynamodb.domain.CustomerItem;
import tsu.pod.dynamodb.domain.ItemPrefix;
import tsu.pod.dynamodb.domain.ItemType;

@Repository
public class CustomerItemRepository {

    private final DynamoDbTable<CustomerItem> customerTable;

    public CustomerItemRepository(DynamoDbTable<CustomerItem> customerTable) {
        this.customerTable = customerTable;
    }

    public void save(CustomerItem customer) {
        customerTable.putItem(customer);
    }

    public Optional<CustomerItem> findById(String customerId) {
        return Optional.ofNullable(
            customerTable.getItem(
                Key.builder()
                    .partitionValue(ItemPrefix.CID.getPrefix() + customerId)
                    .sortValue(ItemPrefix.CID.getPrefix() + customerId)
                    .build()
            )
        );
    }

    public void deleteById(String customerId) {
        customerTable.deleteItem(
            Key.builder()
                .partitionValue(ItemPrefix.CID.getPrefix() + customerId)
                .sortValue(ItemPrefix.CID.getPrefix() + customerId)
                .build()
        );
    }

    public List<CustomerItem> findAllByPkPrefix() {
        List<CustomerItem> customers = new ArrayList<>();
        QueryConditional condition =
            QueryConditional.sortBeginsWith(
                Key.builder().partitionValue(ItemPrefix.CID.getPrefix()).build()
            );
        customerTable.query(condition)
            .items()
            .forEach(customers::add);
        return customers;
    }

}
