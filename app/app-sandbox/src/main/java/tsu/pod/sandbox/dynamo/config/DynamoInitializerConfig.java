package tsu.pod.sandbox.dynamo.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import tsu.pod.sandbox.dynamo.schema.DynamoSchema;
import tsu.pod.sandbox.dynamo.schema.OrdersSchema;
import tsu.pod.sandbox.dynamo.schema.UsersSchema;
import tsu.pod.sandbox.utils.FilePaths;
import tsu.pod.sandbox.utils.CsvFileUtils;

@Slf4j
@Configuration
@Profile("dynamodb")
public class DynamoInitializerConfig {

	private final DynamoDbClient dynamoDbClient;

	private final OrdersSchema ordersDbSchema;

	private final UsersSchema usersDbSchema;

	private final CsvFileUtils csvFileUtils;

	public DynamoInitializerConfig(DynamoDbClient dynamoDbClient, OrdersSchema ordersDbSchema,
			UsersSchema usersDbSchema, CsvFileUtils csvFileUtils) {
		this.dynamoDbClient = dynamoDbClient;
		this.ordersDbSchema = ordersDbSchema;
		this.usersDbSchema = usersDbSchema;
		this.csvFileUtils = csvFileUtils;
		log.info("Initialized");
	}

	@Bean
	ApplicationRunner dynamoDbInitializer(OrdersSchema ordersDbSchema, UsersSchema usersDbSchema,
			CsvFileUtils csvFileUtils) {
		return args -> {
			initialize(ordersDbSchema);
			initialize(usersDbSchema);
			loadOrders();
			loadUsers();
		};
	}

	private void initialize(DynamoSchema schema) {
		if (tableExists(schema.tableName())) {
			log.warn("Table '{}' already exists", schema.tableName());
		}
		else {
			dynamoDbClient.createTable(schema.createTableRequest());
			log.info("Table '{}' created", schema.tableName());
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

	private void loadOrders() {
		List<String[]> records = csvFileUtils.read(FilePaths.DYNAMO_ORDERS_PATH);
		records.forEach(record -> {
			Map<String, AttributeValue> item = new HashMap<>();
			item.put("PK", AttributeValue.fromS(record[0]));
			item.put("SK", AttributeValue.fromS(record[1]));
			item.put("quantity", AttributeValue.fromN(record[2]));
			dynamoDbClient.putItem(builder -> builder.tableName(ordersDbSchema.tableName()).item(item));
		});
		log.info("Table '{}' loaded", ordersDbSchema.tableName());
	}

	private void loadUsers() {
		List<String[]> records = csvFileUtils.read(FilePaths.DYNAMO_USERS_PATH);
		records.forEach(record -> {
			Map<String, AttributeValue> item = new HashMap<>();
			item.put("PK", AttributeValue.fromS(record[0]));
			item.put("SK", AttributeValue.fromS(record[1]));
			item.put("email", AttributeValue.fromS(record[2]));
			dynamoDbClient.putItem(builder -> builder.tableName(usersDbSchema.tableName()).item(item));
		});
		log.info("Table '{}' loaded", usersDbSchema.tableName());
	}

}