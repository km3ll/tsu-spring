package tsu.pod.dynamodb.controller.dto;

public record OrderDto(
    String id,
    String customerId,
    String date
) {
}
