package tsu.pod.dynamodb.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import tsu.pod.dynamodb.model.User;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
}
