package tsu.pod.sandbox.dynamo.persistence.repository;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import tsu.pod.sandbox.dynamo.persistence.model.OrdersEntity;

@Slf4j
@Repository
@Profile("dynamodb")
public class OrdersDbRepository {

	private DynamoDbTable<OrdersEntity> table;

	public OrdersDbRepository(DynamoDbTable<OrdersEntity> table) {
		log.info("Initialized");
		this.table = table;
	}

	public void save(OrdersEntity entity) {
		table.putItem(entity);
	}

	public void saveAll(List<OrdersEntity> entities) {
		entities.forEach(this::save);
	}

	public Optional<OrdersEntity> findById(String id) {
		return Optional.ofNullable(table.getItem(OrdersEntity.builder().pk(id).build()));
	}

	public List<OrdersEntity> findAll() {
		return table.scan().items().stream().toList();
	}

}
