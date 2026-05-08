package tsu.pod.sandbox.dynamo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import tsu.pod.sandbox.dynamo.persistence.model.OrdersDbEntity;
import tsu.pod.sandbox.dynamo.schema.OrdersDbSchema;

@Configuration
@Profile("dynamodb")
public class DynamoDbTableConfig {

	private final Logger logger = LoggerFactory.getLogger(DynamoDbTableConfig.class);

	private final DynamoDbEnhancedClient enhancedClient;

	public DynamoDbTableConfig(DynamoDbEnhancedClient enhancedClient) {
		this.enhancedClient = enhancedClient;
		logger.info("Initialized");
	}

	@Bean
	public DynamoDbTable<OrdersDbEntity> ordersDbTable(OrdersDbSchema ordersDbSchema) {
		DynamoDbTable<OrdersDbEntity> table = enhancedClient.table(ordersDbSchema.tableName(),
				TableSchema.fromBean(OrdersDbEntity.class));
		logger.info("OrdersDbTable created");
		return table;
	}

}
