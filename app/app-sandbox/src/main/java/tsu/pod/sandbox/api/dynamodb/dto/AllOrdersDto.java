package tsu.pod.sandbox.api.dynamodb.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllOrdersDto {

	private List<OrderDto> orders;

}
