package tsu.pod.dynamodb.config;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableResponse;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

@Configuration
public class DynamoInitializer {

	private final Logger logger = LoggerFactory.getLogger(DynamoInitializer.class);

	private final DynamoDbClient dynamoDbClient;

	public DynamoInitializer(DynamoDbClient dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
	}

	@Bean
	ApplicationRunner initDynamoDb() {
		return args -> {
			createUsersTable();
			createOrdersTable();
		};
	}

	private void createUsersTable() {
		String tableName = "users";
		if (tableExists(tableName)) {
			logger.info("Table already exists: {}", tableName);
			return;
		}
		CreateTableRequest request = CreateTableRequest.builder()
			.tableName(tableName)
			.keySchema(KeySchemaElement.builder().attributeName("userId").keyType(KeyType.HASH).build())
			.attributeDefinitions(
					AttributeDefinition.builder().attributeName("userId").attributeType(ScalarAttributeType.S).build())
			.provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(5L).writeCapacityUnits(5L).build())
			.build();
		logger.info("Creating table: {}", tableName);
		dynamoDbClient.createTable(request);
		logger.info("Created table: {}", tableName);
	}

	private void createOrdersTable() {
		String tableName = "orders";
		if (tableExists(tableName)) {
			logger.info("Table already exists: {}", tableName);
			return;
		}

		CreateTableRequest request = CreateTableRequest.builder()
			.tableName(tableName)
			.keySchema(Arrays.asList(KeySchemaElement.builder().attributeName("PK").keyType(KeyType.HASH).build(),
					KeySchemaElement.builder().attributeName("SK").keyType(KeyType.RANGE).build()))
			.attributeDefinitions(Arrays.asList(
					AttributeDefinition.builder().attributeName("PK").attributeType(ScalarAttributeType.S).build(),
					AttributeDefinition.builder().attributeName("SK").attributeType(ScalarAttributeType.S).build()))
			.provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(5L).writeCapacityUnits(5L).build())
			.build();

		logger.info("Creating table: {}", tableName);
		dynamoDbClient.createTable(request);
		logger.info("Created table: {}", tableName);
	}

	private boolean tableExists(String tableName) {
		ListTablesResponse tables = dynamoDbClient.listTables();
		return tables.tableNames().contains(tableName);
	}

	private void deleteTable(String tableName) {
		try {
			logger.info("Deleting table: {}", tableName);
			DeleteTableRequest request = DeleteTableRequest.builder().tableName(tableName).build();
			DeleteTableResponse response = dynamoDbClient.deleteTable(request);
			logger.info("Table deleted: {}", response.tableDescription().tableName());
		}
		catch (ResourceNotFoundException ex) {
			logger.error("Table not found: {}", tableName);
		}
	}

}
