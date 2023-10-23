package pod.tsu.spring.repository.impl;

import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pod.tsu.spring.models.UserEntity;
import pod.tsu.spring.repository.UserRepository;

public class InMemoryUserRepository implements UserRepository {

    private final Logger logger = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Set<UserEntity> users;

    public InMemoryUserRepository(Set<UserEntity> users) {
        this.users = users;
        logger.info("Created");
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return users.stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return users.stream()
            .anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public Boolean save(UserEntity userEntity) {
        return users.add(userEntity);
    }
}
