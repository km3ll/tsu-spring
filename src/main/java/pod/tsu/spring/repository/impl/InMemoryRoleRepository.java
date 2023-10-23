package pod.tsu.spring.repository.impl;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pod.tsu.spring.models.Role;
import pod.tsu.spring.repository.RoleRepository;

public class InMemoryRoleRepository implements RoleRepository {

    private final Logger logger = LoggerFactory.getLogger(InMemoryRoleRepository.class);
    private final Set<Role> roles;

    public InMemoryRoleRepository(Set<Role> roles) {
        this.roles = roles;
        logger.info("Created");
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roles.stream()
            .filter(role -> role.getName().equals(name))
            .findFirst();
    }

}
