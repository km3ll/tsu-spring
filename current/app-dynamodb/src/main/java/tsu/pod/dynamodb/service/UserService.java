package tsu.pod.dynamodb.service;

import org.springframework.stereotype.Service;
import tsu.pod.dynamodb.model.User;
import tsu.pod.dynamodb.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public User getUser(String userId) {
        return repository.findById(userId).orElse(null);
    }
}