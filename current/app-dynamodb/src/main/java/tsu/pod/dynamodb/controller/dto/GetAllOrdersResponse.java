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
public class GetAllOrdersResponse {

	private List<OrderDto> orders;

	@Override
	public String toString() {
		return "GetAllOrdersResponse{" + "orders=" + orders + '}';
	}

}
