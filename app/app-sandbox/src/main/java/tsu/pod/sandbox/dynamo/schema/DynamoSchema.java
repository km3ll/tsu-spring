package tsu.pod.sandbox.dynamo.schema;

import java.util.ArrayList;
import java.util.List;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.GlobalSecondaryIndex;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

public abstract class DynamoSchema {

	protected static final String PK = "PK";

	protected static final String SK = "SK";

	protected static final Long DEFAULT_UNITS = 50L;

	public List<KeySchemaElement> keySchemaElements() {
		List<KeySchemaElement> keySchema = new ArrayList<>();
		keySchema.add(KeySchemaElement.builder().attributeName(PK).keyType(KeyType.HASH).build());
		keySchema.add(KeySchemaElement.builder().attributeName(SK).keyType(KeyType.RANGE).build());
		return keySchema;
	}

	public List<AttributeDefinition> attributeDefinitions() {
		List<AttributeDefinition> attributes = new ArrayList<>();
		attributes.add(AttributeDefinition.builder().attributeName(PK).attributeType(ScalarAttributeType.S).build());
		attributes.add(AttributeDefinition.builder().attributeName(SK).attributeType(ScalarAttributeType.S).build());
		return attributes;
	}

	public List<GlobalSecondaryIndex> globalSecondaryIndexes() {
		return new ArrayList<>();
	}

	public ProvisionedThroughput provisionedThroughput() {
		return ProvisionedThroughput.builder()
			.readCapacityUnits(DEFAULT_UNITS)
			.writeCapacityUnits(DEFAULT_UNITS)
			.build();
	}

	public abstract String tableName();

	public abstract CreateTableRequest createTableRequest();

}
