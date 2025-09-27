package tsu.pod.dynamodb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderDto {

	private String id;

	private String customerId;

	private String date;

	private List<ItemDto> items;

	@Override
	public String toString() {
		return "OrderDto{" + "id='" + id + '\'' + ", customerId='" + customerId + '\'' + ", date='" + date + '\''
				+ ", items=" + items + '}';
	}

}
