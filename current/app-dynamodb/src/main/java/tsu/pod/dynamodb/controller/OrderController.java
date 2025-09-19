package tsu.pod.dynamodb.controller;

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
import tsu.pod.dynamodb.controller.dto.GetAllOrdersResponse;
import tsu.pod.dynamodb.controller.dto.GetOrderResponse;
import tsu.pod.dynamodb.controller.dto.OrderDto;
import tsu.pod.dynamodb.controller.dto.PostOrderRequest;
import tsu.pod.dynamodb.controller.dto.PostOrderResponse;
import tsu.pod.dynamodb.repository.OrderRepository;
import tsu.pod.dynamodb.repository.dao.OrderDao;
import tsu.pod.dynamodb.repository.dao.PrimaryKey;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private final OrderRepository repository;

	public OrderController(OrderRepository repository) {
		this.repository = repository;
	}

	// Create or Update
	@PostMapping
	public ResponseEntity<PostOrderResponse> postOrder(@RequestBody PostOrderRequest request) {
		OrderDao orderDao = OrderMapper.toOrderDao(request.order());
		OrderDao saved = repository.save(orderDao);
		PostOrderResponse response = new PostOrderResponse(OrderMapper.toOrderDto(saved));
		return ResponseEntity.ok(response);
	}

	// Read by Order ID
	@GetMapping("/{id}")
	public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("id") String id) {
		PrimaryKey key = OrderDao.buidKey(id);
		Optional<GetOrderResponse> response = repository.findByPk(key.getPk())
			.stream()
			.findFirst()
			.map(dao -> new GetOrderResponse(OrderMapper.toOrderDto(dao)));
		return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") String id) {
		PrimaryKey key = OrderDao.buidKey(id);
		repository.deleteAll(repository.findByPk(key.getPk()));
		return ResponseEntity.noContent().build();
	}

	// List All
	@GetMapping
	public ResponseEntity<GetAllOrdersResponse> getAllOrders() {
		List<OrderDto> orders = StreamSupport.stream(repository.findAll().spliterator(), false)
			.map(OrderMapper::toOrderDto)
			.toList();
		return ResponseEntity.ok(new GetAllOrdersResponse(orders));
	}

}