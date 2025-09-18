package tsu.pod.dynamodb.repository.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@DynamoDBTable(tableName = "orders")
public class OrderDao {

	@Id
	private KeyDao orderKey;

	private String entityType;

	private String userId;

	private String date;

	@DynamoDBIgnore
	public KeyDao getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(KeyDao orderKey) {
		this.orderKey = orderKey;
	}

	@DynamoDBHashKey(attributeName = "PK")
	public String getPk() {
		return orderKey != null ? orderKey.getPk() : null;
	}

	public void setPk(String pk) {
		if (this.orderKey == null)
			this.orderKey = new KeyDao();
		this.orderKey.setPk(pk);
	}

	@DynamoDBRangeKey(attributeName = "SK")
	public String getSk() {
		return orderKey != null ? orderKey.getSk() : null;
	}

	public void setSk(String sk) {
		if (this.orderKey == null)
			this.orderKey = new KeyDao();
		this.orderKey.setSk(sk);
	}

	// Attributes
	@DynamoDBAttribute(attributeName = "EntityType")
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@DynamoDBAttribute(attributeName = "UserId")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@DynamoDBAttribute(attributeName = "Date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}