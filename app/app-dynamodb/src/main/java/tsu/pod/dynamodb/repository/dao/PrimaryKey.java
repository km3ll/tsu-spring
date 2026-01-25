package tsu.pod.dynamodb.repository.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryKey {

	@DynamoDBHashKey(attributeName = "PK")
	private String pk;

	@DynamoDBRangeKey(attributeName = "SK")
	private String sk;

	@Override
	public String toString() {
		return "TableKey{" + "pk='" + pk + '\'' + ", sk='" + sk + '\'' + '}';
	}

}
