package tsu.pod.sandbox.dynamo.config;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import tsu.pod.sandbox.dynamo.schema.DynamoDbSchema;
import tsu.pod.sandbox.dynamo.schema.OrdersDbSchema;
import tsu.pod.sandbox.dynamo.schema.UsersDbSchema;

@Configuration
@Profile("dynamodb")
public class DynamoDbInitializerConfig {

	private final Logger logger = LoggerFactory.getLogger(DynamoDbInitializerConfig.class);

	private final DynamoDbClient dynamoDbClient;

	public DynamoDbInitializerConfig(DynamoDbClient dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
		logger.info("Initialized");
	}

	@Bean
	ApplicationRunner dynamoDbInitializer(OrdersDbSchema ordersDbSchema, UsersDbSchema usersDbSchema) {
		return args -> {
			initialize(ordersDbSchema);
			initialize(usersDbSchema);
			loadOrders(ordersDbSchema);
		};
	}

	private void initialize(DynamoDbSchema schema) {
		if (tableExists(schema.tableName())) {
			logger.warn("Table '{}' already exists", schema.tableName());
		}
		else {
			dynamoDbClient.createTable(schema.createTableRequest());
			logger.info("Table '{}' created", schema.tableName());
		}
	}

	private boolean tableExists(String tableName) {
		try {
			dynamoDbClient.describeTable(builder -> builder.tableName(tableName).build());
			return true;
		}
		catch (ResourceNotFoundException e) {
			return false;
		}
	}

	private void loadOrders(OrdersDbSchema schema) {
		Map<String, AttributeValue> item1 = Map.of("PK", AttributeValue.fromS("ORDER:80001#COUNTRY:USA"), "SK",
				AttributeValue.fromS("PRODUCT:501"), "quantity", AttributeValue.fromN("3"));
		Map<String, AttributeValue> item2 = Map.of("PK", AttributeValue.fromS("ORDER:80002#COUNTRY:CAN"), "SK",
				AttributeValue.fromS("PRODUCT:630"), "quantity", AttributeValue.fromN("5"));
		dynamoDbClient.putItem(builder -> builder.tableName(schema.tableName()).item(item1));
		dynamoDbClient.putItem(builder -> builder.tableName(schema.tableName()).item(item2));
		logger.info("Table '{}' loaded", schema.tableName());
	}

}