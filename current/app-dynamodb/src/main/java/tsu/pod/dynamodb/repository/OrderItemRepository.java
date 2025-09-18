package tsu.pod.dynamodb.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import tsu.pod.dynamodb.repository.dao.KeyDao;
import tsu.pod.dynamodb.repository.dao.OrderItemDao;

import java.util.List;

@EnableScan
public interface OrderItemRepository extends CrudRepository<OrderItemDao, KeyDao> {

	List<OrderItemDao> findByPk(String pk);

}
