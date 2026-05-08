package tsu.pod.sandbox.api.dynamodb;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsu.pod.sandbox.api.dynamodb.dto.AllOrdersDto;
import tsu.pod.sandbox.api.dynamodb.dto.OrderDto;
import tsu.pod.sandbox.dynamo.persistence.repository.OrdersDbRepository;

@RestController
@Profile("dynamodb")
@RequestMapping("/api/")
public class OrdersController {

	private final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	private final OrdersDbRepository ordersDbRepository;

	public OrdersController(OrdersDbRepository ordersDbRepository) {
		this.ordersDbRepository = ordersDbRepository;
		logger.info("Initialized");
	}

	@GetMapping("orders")
	public ResponseEntity<AllOrdersDto> getAllOrders() {
		List<OrderDto> orders = ordersDbRepository.findAll().stream().map(OrderDto::from).toList();
		AllOrdersDto allOrdersDto = AllOrdersDto.builder().orders(orders).build();
		return ResponseEntity.ok(allOrdersDto);
	}

}
