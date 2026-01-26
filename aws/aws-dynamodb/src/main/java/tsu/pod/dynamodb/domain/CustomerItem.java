package tsu.pod.dynamodb.domain;

import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@NoArgsConstructor
public class CustomerItem {

    private String pk;
    private String sk;
    private String entityType;

    /* ========= Partition Key ========= */

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    /* ========= Sort Key ========= */

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    /* ========= Regular attribute ========= */

    @DynamoDbAttribute("entityType")
    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    /* ========= Convenience factory ========= */

    public static CustomerItem of(String customerId) {
        CustomerItem item = new CustomerItem();
        item.setPk(ItemPrefix.CID.getPrefix() + customerId);
        item.setSk(ItemPrefix.CID.getPrefix() + customerId);
        item.setEntityType(ItemType.CUSTOMER.getValue());
        return item;
    }

}
