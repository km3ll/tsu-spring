package tsu.pod.sandbox.dynamo.schema;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.GlobalSecondaryIndex;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.Projection;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

@Builder
@Component
@Profile("dynamodb")
public class UsersSchema extends DynamoSchema {

	private static final String TABLE_NAME = "sandbox-users";

	private static final String INDEX_NAME_BY_EMAIL = "by-email";

	@Override
	public List<AttributeDefinition> attributeDefinitions() {
		List<AttributeDefinition> attributes = new ArrayList<>();
		attributes.add(AttributeDefinition.builder().attributeName(PK).attributeType(ScalarAttributeType.S).build());
		attributes.add(AttributeDefinition.builder().attributeName(SK).attributeType(ScalarAttributeType.S).build());
		attributes
			.add(AttributeDefinition.builder().attributeName("email").attributeType(ScalarAttributeType.S).build());
		return attributes;
	}

	@Override
	public List<GlobalSecondaryIndex> globalSecondaryIndexes() {
		return List.of(GlobalSecondaryIndex.builder()
			.indexName(INDEX_NAME_BY_EMAIL)
			.keySchema(List.of(KeySchemaElement.builder().attributeName("email").keyType(KeyType.HASH).build()))
			.projection(Projection.builder().projectionType(ProjectionType.ALL).build())
			.provisionedThroughput(provisionedThroughput())
			.build());
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	public CreateTableRequest createTableRequest() {
		return CreateTableRequest.builder()
			.tableName(TABLE_NAME)
			.keySchema(keySchemaElements())
			.attributeDefinitions(attributeDefinitions())
			.globalSecondaryIndexes(globalSecondaryIndexes())
			.provisionedThroughput(provisionedThroughput())
			.build();
	}

}
