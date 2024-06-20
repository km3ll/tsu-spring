package pod.tsu.spring.secureapi.repository;

import pod.tsu.spring.secureapi.model.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean save(UserEntity userEntity);

}