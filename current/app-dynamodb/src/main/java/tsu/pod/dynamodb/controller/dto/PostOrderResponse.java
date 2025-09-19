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
public class PostOrderResponse {

	private OrderDto order;

	@Override
	public String toString() {
		return "PostOrderResponse{" + "order=" + order + '}';
	}

}
