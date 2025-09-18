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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@DynamoDBTable(tableName = "orders")
public class OrderItemDao {

	@Id
	private KeyDao keyDao;

	@DynamoDBIgnore
	public KeyDao getKeyDao() {
		return keyDao;
	}

	@DynamoDBHashKey(attributeName = "PK")
	public String getPk() {
		return keyDao.getPk();
	}

	public void setPk(String pk) {
		if (this.keyDao == null)
			this.keyDao = new KeyDao();
		this.keyDao.setPk(pk);
	}

	@DynamoDBRangeKey(attributeName = "SK")
	public String getSk() {
		return keyDao.getSk();
	}

	public void setSk(String sk) {
		if (this.keyDao == null)
			this.keyDao = new KeyDao();
		this.keyDao.setSk(sk);
	}

	// Attributes

	@DynamoDBAttribute(attributeName = "EntityType")
	@Builder.Default
	private String recordType = "orderItem";

	@DynamoDBAttribute(attributeName = "Quantity")
	private Integer quantity;

	@DynamoDBAttribute(attributeName = "Price")
	private Double price;

	@Override
	public String toString() {
		return "OrderItemDao{" +
			"keyDao=" + keyDao +
			", recordType='" + recordType + '\'' +
			", quantity=" + quantity +
			", price=" + price +
			'}';
	}

	public static KeyDao buildKey(String orderId, String itemId) {
		String pk = "order#" + orderId;
		String sk = "item#" + itemId;
		return new KeyDao(pk, sk);
	}

}