package tsu.pod.sandbox.config.dynamodb;

import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import tsu.pod.sandbox.config.dynamodb.schema.DynamoDbSchema;
import tsu.pod.sandbox.config.dynamodb.schema.OrdersDbSchema;
import tsu.pod.sandbox.config.dynamodb.schema.UsersDbSchema;

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
  ApplicationRunner dynamoInitializer() {
    return args -> {
      Stream.of(OrdersDbSchema.builder().build(), UsersDbSchema.builder().build())
          .forEach(this::initialize);
    };
  }

  private void initialize(DynamoDbSchema schema) {
    if (tableExists(schema.tableName())) {
      logger.warn("Table '{}' already exists", schema.tableName());
    } else {
      dynamoDbClient.createTable(schema.createTableRequest());
      logger.info("Table '{}' created", schema.tableName());
    }
  }

  private boolean tableExists(String tableName) {
    try {
      dynamoDbClient.describeTable(builder -> builder.tableName(tableName).build());
      return true;
    } catch (ResourceNotFoundException e) {
      return false;
    }
  }
}
