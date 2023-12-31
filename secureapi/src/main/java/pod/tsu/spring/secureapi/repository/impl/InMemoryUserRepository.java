package pod.tsu.spring.secureapi.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pod.tsu.spring.secureapi.model.UserEntity;
import pod.tsu.spring.secureapi.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Logger logger = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private Set<UserEntity> users = new HashSet<>();

    public InMemoryUserRepository() {
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
