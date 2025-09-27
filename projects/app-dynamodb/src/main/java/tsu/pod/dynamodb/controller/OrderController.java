package tsu.pod.dynamodb.controller;

import com.google.common.collect.Lists;
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
import tsu.pod.dynamodb.repository.ItemRepository;
import tsu.pod.dynamodb.repository.OrderRepository;
import tsu.pod.dynamodb.repository.dao.ItemDao;
import tsu.pod.dynamodb.repository.dao.OrderDao;
import tsu.pod.dynamodb.repository.dao.PrimaryKey;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private final OrderRepository orderRepository;

	private final ItemRepository itemRepository;

	public OrderController(OrderRepository repository, ItemRepository itemRepository) {
		this.orderRepository = repository;
		this.itemRepository = itemRepository;
	}

	// Create or Update
	@PostMapping
	public ResponseEntity<PostOrderResponse> postOrder(@RequestBody PostOrderRequest request) {

		logger.info("Request: {}", request);
		OrderDao orderDao = OrderMapper.toOrderDao(request.getOrder());
		OrderDao savedOrderDao = orderRepository.save(orderDao);

		List<ItemDao> itemDaos = OrderMapper.toListOfItemDaos(request.getOrder());
		List<ItemDao> savedItemDaos = Lists.newArrayList(itemRepository.saveAll(itemDaos));

		OrderDto orderDto = OrderMapper.toOrderDto(savedOrderDao, savedItemDaos);
		PostOrderResponse response = PostOrderResponse.builder().order(orderDto).build();

		logger.info("Response: {}", response);
		return ResponseEntity.ok(response);

	}

	// Read by Order ID
	@GetMapping("/{id}")
	public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("id") String orderId) {

		PrimaryKey orderKey = OrderDao.buidKey(orderId);
		Optional<OrderDao> orderDao = orderRepository.findByPk(orderKey.getPk()).stream().findFirst();
		List<ItemDao> itemDaos = findOrderItems(orderId);

		Optional<GetOrderResponse> response = orderDao
			.map(dao -> GetOrderResponse.builder().order(OrderMapper.toOrderDto(dao, itemDaos)).build());
		return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	// List All
	@GetMapping
	public ResponseEntity<GetAllOrdersResponse> getAllOrders() {
		List<OrderDto> orders = Lists.newArrayList(orderRepository.findAll())
			.stream()
			.filter(orderDao -> orderDao.getEntityType().equals("order"))
			.map(orderDao -> {
				List<ItemDao> itemDaos = findOrderItems(orderDao.getOrderId());
				return OrderMapper.toOrderDto(orderDao, itemDaos);
			})
			.toList();
		GetAllOrdersResponse response = GetAllOrdersResponse.builder().orders(orders).build();
		return ResponseEntity.ok(response);
	}

	private List<ItemDao> findOrderItems(String orderId) {
		PrimaryKey itemKey = ItemDao.buidKey(orderId);
		return Lists.newArrayList(itemRepository.findByPk(itemKey.getPk()))
			.stream()
			.filter(itemDao -> itemDao.getEntityType().equals("orderItem"))
			.toList();
	}

}