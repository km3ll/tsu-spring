package tsu.pod.sandbox.dynamo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import tsu.pod.sandbox.dynamo.persistence.model.OrdersEntity;
import tsu.pod.sandbox.dynamo.persistence.model.UsersEntity;
import tsu.pod.sandbox.dynamo.schema.OrdersSchema;
import tsu.pod.sandbox.dynamo.schema.UsersSchema;

@Slf4j
@Configuration
@Profile("dynamodb")
public class DynamoTableConfig {

	private final DynamoDbEnhancedClient enhancedClient;

	public DynamoTableConfig(DynamoDbEnhancedClient enhancedClient) {
		this.enhancedClient = enhancedClient;
		log.info("Initialized");
	}

	@Bean
	public DynamoDbTable<OrdersEntity> ordersDynamoDbTable(OrdersSchema ordersDbSchema) {
		DynamoDbTable<OrdersEntity> table = enhancedClient.table(ordersDbSchema.tableName(),
				TableSchema.fromBean(OrdersEntity.class));
		log.info("OrdersEntity table created");
		return table;
	}

	@Bean
	public DynamoDbTable<UsersEntity> usersDynamoDbTable(UsersSchema usersSchema) {
		DynamoDbTable<UsersEntity> table = enhancedClient.table(usersSchema.tableName(),
				TableSchema.fromBean(UsersEntity.class));
		log.info("UsersEntity table created");
		return table;
	}

}
