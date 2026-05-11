package tsu.pod.sandbox.dynamo.schema;

import lombok.Builder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;

@Builder
@Component
@Profile("dynamodb")
public class OrdersSchema extends DynamoSchema {

	public static final String TABLE_NAME = "sandbox-orders";

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
