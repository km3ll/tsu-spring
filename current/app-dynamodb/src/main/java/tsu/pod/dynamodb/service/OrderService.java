package tsu.pod.dynamodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import tsu.pod.dynamodb.model.orders.Order;
import tsu.pod.dynamodb.model.orders.OrderKey;
import tsu.pod.dynamodb.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository repository;

	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public Order saveOrder(Order order) {
		return repository.save(order);
	}

	public Optional<Order> findOrder(String pk, String sk) {
		return repository.findById(new OrderKey(pk, sk));
	}

	public boolean orderExists(String pk, String sk) {
		return repository.existsById(new OrderKey(pk, sk));
	}

	public void deleteOrder(String pk, String sk) {
		repository.deleteById(new OrderKey(pk, sk));
	}

	public Iterable<Order> findAllOrders() {
		return repository.findAll();
	}

	public List<Order> findByPk(String pk) {
		return repository.findByPk(pk);
	}

}