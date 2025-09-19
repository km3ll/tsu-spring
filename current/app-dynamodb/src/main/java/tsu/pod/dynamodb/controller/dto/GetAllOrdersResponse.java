package tsu.pod.dynamodb.controller.dto;

import java.util.List;

public record GetAllOrdersResponse(List<OrderDto> orders) {
}
