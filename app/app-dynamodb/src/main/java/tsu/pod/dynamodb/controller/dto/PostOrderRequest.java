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
public class PostOrderRequest {

	private OrderDto order;

	@Override
	public String toString() {
		return "PostOrderRequest{" + "order=" + order + '}';
	}

}
