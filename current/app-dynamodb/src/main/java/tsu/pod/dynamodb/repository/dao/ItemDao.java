package tsu.pod.dynamodb.repository.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@DynamoDBTable(tableName = "e-store")
public class ItemDao {

	@Id
	@DynamoDBIgnore
	private PrimaryKey primaryKey;

	@DynamoDBIgnore
	public String getOrderId() {
		return primaryKey.getPk().split("#")[1];
	}

	@DynamoDBIgnore
	public String getItemId() {
		return primaryKey.getSk().split("#")[1];
	}

	@DynamoDBHashKey(attributeName = "PK")
	public String getPk() {
		return primaryKey.getPk();
	}

	public void setPk(String pk) {
		if (this.primaryKey == null)
			this.primaryKey = new PrimaryKey();
		this.primaryKey.setPk(pk);
	}

	@DynamoDBRangeKey(attributeName = "SK")
	public String getSk() {
		return primaryKey.getSk();
	}

	public void setSk(String sk) {
		if (this.primaryKey == null)
			this.primaryKey = new PrimaryKey();
		this.primaryKey.setSk(sk);
	}

	@Builder.Default
	@DynamoDBAttribute(attributeName = "EntityType")
	private String entityType = "orderItem";

	@DynamoDBAttribute(attributeName = "Quantity")
	private Integer quantity;

	@DynamoDBAttribute(attributeName = "Price")
	private Double price;

	@Override
	public String toString() {
		return "ItemDao{" + "primaryKey=" + primaryKey + ", entityType='" + entityType + '\'' + ", quantity=" + quantity
				+ ", price=" + price + '}';
	}

	public static PrimaryKey buidKey(String orderId, String itemId) {
		String pk = "order#" + orderId;
		String sk = "item#" + itemId;
		return new PrimaryKey(pk, sk);
	}

	public static PrimaryKey buidKey(String orderId) {
		String pk = "order#" + orderId;
		String sk = "item#";
		return new PrimaryKey(pk, sk);
	}

}
