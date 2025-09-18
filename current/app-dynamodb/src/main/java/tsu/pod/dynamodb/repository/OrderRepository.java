package tsu.pod.dynamodb.repository;

import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import tsu.pod.dynamodb.repository.dao.KeyDao;
import tsu.pod.dynamodb.repository.dao.OrderDao;

@EnableScan
public interface OrderRepository extends CrudRepository<OrderDao, KeyDao> {

    // You can define custom query methods, for example:
    List<OrderDao> findByPk(String pk);

}
