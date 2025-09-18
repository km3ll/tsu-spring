package tsu.pod.dynamodb.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsu.pod.dynamodb.model.orders.Order;
import tsu.pod.dynamodb.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private final OrderService service;

	public OrderController(OrderService service) {
		this.service = service;
	}

	// Create or Update
	@PostMapping
	public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
		Order saved = service.saveOrder(order);
		return ResponseEntity.ok(saved);
	}

	// Read by ID
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable String id) {
		String key = "OID#" + id;
		Optional<Order> order = service.findOrder(key, key);
		return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
		String key = "OID#" + id;
		if (!service.orderExists(key, key)) {
			return ResponseEntity.notFound().build();
		}
		service.deleteOrder(key, key);
		return ResponseEntity.noContent().build();
	}

	// List All
	@GetMapping
	public ResponseEntity<Iterable<Order>> listOrders() {
		return ResponseEntity.ok(service.findAllOrders());
	}

}