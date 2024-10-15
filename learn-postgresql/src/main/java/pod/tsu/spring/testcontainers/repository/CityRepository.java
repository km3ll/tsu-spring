package pod.tsu.spring.testcontainers.repository;

import org.springframework.data.repository.CrudRepository;
import pod.tsu.spring.testcontainers.model.City;

public interface CityRepository extends CrudRepository<City, Long> {
}