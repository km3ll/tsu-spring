package tsu.pod.dynamodb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ItemDto {

	private String id;

	private Integer quantity;

	private Double price;

	@Override
	public String toString() {
		return "ItemDto{" + "id='" + id + '\'' + ", quantity=" + quantity + ", price=" + price + '}';
	}

}
