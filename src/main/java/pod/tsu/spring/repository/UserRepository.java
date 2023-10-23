package pod.tsu.spring.repository;

import pod.tsu.spring.models.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

}
