package tsu.pod.sandbox.config.dynamodb.schema;

import lombok.Builder;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;

@Builder
public class OrdersDbSchema extends DynamoDbSchema {

  private static final String TABLE_NAME = "sandbox-orders";

  @Override
  public String tableName() {
    return TABLE_NAME;
  }

  @Override
  public CreateTableRequest createTableRequest() {
    return CreateTableRequest.builder()
        .tableName(TABLE_NAME)
        .keySchema(keySchemaElements())
        .attributeDefinitions(attributeDefinitions())
        .provisionedThroughput(provisionedThroughput())
        .build();
  }
}
