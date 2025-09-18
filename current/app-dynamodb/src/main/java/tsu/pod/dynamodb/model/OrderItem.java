package tsu.pod.dynamodb.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class OrderItem {

    private String orderId;
    private String itemId;
    private int quantity;
    private double price;

}
