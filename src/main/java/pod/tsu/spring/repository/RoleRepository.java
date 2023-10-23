package pod.tsu.spring.repository;

import java.util.Optional;
import pod.tsu.spring.models.Role;

public interface RoleRepository {

    Optional<Role> findByName(String name);

}
