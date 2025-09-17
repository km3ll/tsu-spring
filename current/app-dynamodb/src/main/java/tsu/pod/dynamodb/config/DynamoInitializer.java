package tsu.pod.dynamodb.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

@Configuration
public class DynamoInitializer {

    private final Logger logger = LoggerFactory.getLogger(DynamoInitializer.class);

    @Bean
    ApplicationRunner initDynamoDb(DynamoDbClient dynamoDbClient) {
        return args -> {

            String tableName = "users";
            ListTablesResponse tables = dynamoDbClient.listTables();
            if (tables.tableNames().contains(tableName)) {
                logger.info("Table already exists: {}", tableName);
                return;
            }

            // Create table
            CreateTableRequest request = CreateTableRequest.builder()
                .tableName(tableName)
                .keySchema(KeySchemaElement.builder()
                    .attributeName("userId")
                    .keyType(KeyType.HASH)
                    .build())
                .attributeDefinitions(AttributeDefinition.builder()
                    .attributeName("userId")
                    .attributeType(ScalarAttributeType.S)
                    .build())
                .provisionedThroughput(ProvisionedThroughput.builder()
                    .readCapacityUnits(5L)
                    .writeCapacityUnits(5L)
                    .build())
                .build();

            logger.info("Creating table: {}", tableName);
            dynamoDbClient.createTable(request);
            logger.info("Created table: {}", tableName);

        };
    }

}
