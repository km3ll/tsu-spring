package pod.tsu.spring.testcontainers.repository;

import org.springframework.data.repository.CrudRepository;
import pod.tsu.spring.testcontainers.model.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
}