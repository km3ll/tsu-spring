package tsu.pod.dynamodb.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import tsu.pod.dynamodb.repository.dao.OrderDao;
import tsu.pod.dynamodb.repository.dao.PrimaryKey;

import java.util.List;

@EnableScan
public interface OrderRepository extends CrudRepository<OrderDao, PrimaryKey> {

	List<OrderDao> findByPk(String pk);

}
