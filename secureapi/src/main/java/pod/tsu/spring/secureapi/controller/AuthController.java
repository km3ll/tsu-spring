package pod.tsu.spring.secureapi.controller;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.secureapi.dto.AuthResponseDto;
import pod.tsu.spring.secureapi.dto.LoginRequestDto;
import pod.tsu.spring.secureapi.dto.LoginResponseDto;
import pod.tsu.spring.secureapi.dto.RegisterRequestDto;
import pod.tsu.spring.secureapi.model.Role;
import pod.tsu.spring.secureapi.model.UserEntity;
import pod.tsu.spring.secureapi.repository.UserRepository;
import pod.tsu.spring.secureapi.security.JwtGenerator;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        UserRepository userRepository,
        JwtGenerator jwtGenerator
    ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
        logger.info("Created");
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            AuthResponseDto responseDto = AuthResponseDto.builder()
                .message(String.format("Username '%s' already exists", request.getUsername()))
                .build();
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = UserEntity.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(ImmutableList.of(Role.builder().name("USER").build()))
            .build();
        userRepository.save(userEntity);
        AuthResponseDto responseDto = AuthResponseDto.builder()
            .message(String.format("Username '%s' registered successfully", request.getUsername())).build();
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {

        Authentication userToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(userToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        LoginResponseDto responseDto = new LoginResponseDto(token);
        return ResponseEntity.ok(responseDto);

    }

}