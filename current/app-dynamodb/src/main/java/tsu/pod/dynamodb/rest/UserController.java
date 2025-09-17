package tsu.pod.dynamodb.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsu.pod.dynamodb.model.User;
import tsu.pod.dynamodb.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Create or Update
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User saved = repository.save(user);
        return ResponseEntity.ok(saved);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        Optional<User> user = repository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update (same as save)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        user.setUserId(id);
        User updated = repository.save(user);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // List All
    @GetMapping
    public ResponseEntity<Iterable<User>> listUsers() {
        return ResponseEntity.ok(repository.findAll());
    }
}