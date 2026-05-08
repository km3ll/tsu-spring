package tsu.pod.sandbox.dynamo.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import tsu.pod.sandbox.dynamo.persistence.model.OrdersDbEntity;

@Repository
@Profile("dynamodb")
public class OrdersDbRepository {

	private final Logger logger = LoggerFactory.getLogger(OrdersDbRepository.class);

	private DynamoDbTable<OrdersDbEntity> table;

	public OrdersDbRepository(DynamoDbTable<OrdersDbEntity> table) {
		logger.info("Initialized");
		this.table = table;
	}

	public void save(OrdersDbEntity entity) {
		table.putItem(entity);
	}

	public void saveAll(List<OrdersDbEntity> entities) {
		entities.forEach(this::save);
	}

	public Optional<OrdersDbEntity> findById(String id) {
		return Optional.ofNullable(table.getItem(OrdersDbEntity.builder().pk(id).build()));
	}

	public List<OrdersDbEntity> findAll() {
		return table.scan().items().stream().toList();
	}

}
