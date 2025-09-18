package tsu.pod.dynamodb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tsu.pod.dynamodb.model.OrderItem;
import tsu.pod.dynamodb.service.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

	private final OrderItemService service;

	public OrderItemController(OrderItemService service) {
		this.service = service;
	}

	// Create or Update
	@PostMapping
	public ResponseEntity<OrderItem> saveOrderItems(@RequestBody OrderItem orderItem) {
		OrderItem saved = service.save(orderItem);
		return ResponseEntity.ok(saved);
	}

	// Read by ID
	@GetMapping
	public ResponseEntity<OrderItem> getOrder(
		@RequestParam(value = "orderId") String orderId,
		@RequestParam(value = "itemId") String itemId
	) {
		return service.find(orderId, itemId)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	// List All
	@GetMapping("/all")
	public ResponseEntity<Iterable<OrderItem>> listAll() {
		return ResponseEntity.ok(service.findAll());
	}

}