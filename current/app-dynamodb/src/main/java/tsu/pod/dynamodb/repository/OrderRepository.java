package tsu.pod.dynamodb.repository;

import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import tsu.pod.dynamodb.model.orders.Order;
import tsu.pod.dynamodb.model.orders.OrderKey;

@EnableScan
public interface OrderRepository extends CrudRepository<Order, OrderKey> {

	// You can define custom query methods, for example:
	List<Order> findByPk(String pk);

}
