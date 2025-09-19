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
			setupTable("e-store");
		};
	}

	private void setupTable(String name) {
		if (tableExists(name)) {
			deleteTable(name);
		}
		createTable(name);
	}

	private boolean tableExists(String tableName) {
		ListTablesResponse tables = dynamoDbClient.listTables();
		return tables.tableNames().contains(tableName);
	}

	private void deleteTable(String name) {
		try {
			var request = DeleteTableRequest.builder().tableName(name).build();
			var response = dynamoDbClient.deleteTable(request);
			logger.info("Deleted table: {}", response.tableDescription().tableName());
		}
		catch (ResourceNotFoundException ex) {
			logger.error("Table not found: {}", name);
		}
	}

	private void createTable(String name) {
		var request = CreateTableRequest.builder()
			.tableName(name)
			.keySchema(Arrays.asList(KeySchemaElement.builder().attributeName("PK").keyType(KeyType.HASH).build(),
					KeySchemaElement.builder().attributeName("SK").keyType(KeyType.RANGE).build()))
			.attributeDefinitions(Arrays.asList(
					AttributeDefinition.builder().attributeName("PK").attributeType(ScalarAttributeType.S).build(),
					AttributeDefinition.builder().attributeName("SK").attributeType(ScalarAttributeType.S).build()))
			.provisionedThroughput(
					ProvisionedThroughput.builder().readCapacityUnits(10L).writeCapacityUnits(10L).build())
			.build();
		dynamoDbClient.createTable(request);
		logger.info("Created table: {}", name);
	}

}
