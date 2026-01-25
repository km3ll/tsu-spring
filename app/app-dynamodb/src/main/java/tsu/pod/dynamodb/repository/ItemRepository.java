package tsu.pod.dynamodb.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import tsu.pod.dynamodb.repository.dao.ItemDao;
import tsu.pod.dynamodb.repository.dao.PrimaryKey;

import java.util.List;

@EnableScan
public interface ItemRepository extends CrudRepository<ItemDao, PrimaryKey> {

	List<ItemDao> findByPk(String pk);

}
