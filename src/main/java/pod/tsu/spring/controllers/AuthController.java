package pod.tsu.spring.controllers;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.dto.AuthResponseDto;
import pod.tsu.spring.dto.RegisterRequestDto;
import pod.tsu.spring.models.Role;
import pod.tsu.spring.models.UserEntity;
import pod.tsu.spring.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public AuthController(
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        logger.info("Created");
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            AuthResponseDto errorDto = AuthResponseDto.builder()
                .message(String.format("Username '%s' already exists", request.getUsername()))
                .build();
            return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = UserEntity.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(ImmutableList.of(Role.builder().name("USER").build()))
            .build();
        userRepository.save(userEntity);
        AuthResponseDto successDto = AuthResponseDto.builder()
            .message(String.format("Username '%s' registered successfully", request.getUsername()))
            .build();
        return ResponseEntity.ok(successDto);
    }

}