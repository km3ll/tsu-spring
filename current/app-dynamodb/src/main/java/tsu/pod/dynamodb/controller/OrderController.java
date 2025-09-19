package tsu.pod.dynamodb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsu.pod.dynamodb.controller.dto.SaveOrderRequest;
import tsu.pod.dynamodb.controller.dto.SaveOrderResponse;
import tsu.pod.dynamodb.repository.OrderRepository;
import tsu.pod.dynamodb.repository.dao.OrderDao;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private final OrderRepository repository;

	public OrderController(OrderRepository repository) {
		this.repository = repository;
	}

	// Create or Update
	@PostMapping
	public ResponseEntity<SaveOrderResponse> saveOrder(@RequestBody SaveOrderRequest request) {

		logger.info("Saving Order: {}", request.order());
		OrderDao orderDao = OrderDao.builder()
			.primaryKey(OrderDao.buidKey(request.order().id(), request.order().customerId()))
			.date(request.order().date())
			.build();
		OrderDao saved = repository.save(orderDao);
		logger.info("Saved Order: {}", saved);

		SaveOrderResponse response = new SaveOrderResponse(request.order());
		return ResponseEntity.ok(response);
	}


}