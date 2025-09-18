package tsu.pod.dynamodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import tsu.pod.dynamodb.repository.OrderRepository;
import tsu.pod.dynamodb.repository.dao.KeyDao;
import tsu.pod.dynamodb.repository.dao.OrderDao;

@Service
public class OrderService {

	private final OrderRepository repository;

	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public OrderDao saveOrder(OrderDao order) {
		return repository.save(order);
	}

	public Optional<OrderDao> findOrder(String pk, String sk) {
		return repository.findById(new KeyDao(pk, sk));
	}

	public boolean orderExists(String pk, String sk) {
		return repository.existsById(new KeyDao(pk, sk));
	}

	public void deleteOrder(String pk, String sk) {
		repository.deleteById(new KeyDao(pk, sk));
	}

	public Iterable<OrderDao> findAllOrders() {
		return repository.findAll();
	}

	public List<OrderDao> findByPk(String pk) {
		return repository.findByPk(pk);
	}

}