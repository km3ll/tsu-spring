package tsu.pod.sandbox.api.dynamodb.dto;

import lombok.Builder;
import lombok.Data;
import tsu.pod.sandbox.dynamo.persistence.model.OrdersDbEntity;

@Data
@Builder
public class OrderDto {

	private String orderId;

	private String countryCode;

	private Integer quantity;

	public static OrderDto from(OrdersDbEntity entity) {

		String[] pairs = entity.getPk().split("#");
		return OrderDto.builder()
			.orderId(pairs[0].split(":")[1])
			.countryCode(pairs[1].split(":")[1])
			.quantity(entity.getQuantity())
			.build();
	}

}
