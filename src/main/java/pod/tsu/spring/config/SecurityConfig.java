package pod.tsu.spring.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pod.tsu.spring.models.Role;
import pod.tsu.spring.models.UserEntity;
import pod.tsu.spring.repository.UserRepository;
import pod.tsu.spring.repository.impl.InMemoryUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    public SecurityConfig() {
        logger.info("Created");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/pod/**" ).permitAll()
            .antMatchers("/api/auth/**" ).permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository userRepository() {
        UserEntity admin = UserEntity.builder()
            .id(1100)
            .username("admin")
            .password("password")
            .roles(ImmutableList.of(Role.builder().name("ADMIN").build()))
            .build();
        UserEntity user = UserEntity.builder()
            .id(1101)
            .username("user")
            .password("password")
            .roles(ImmutableList.of(Role.builder().name("USER").build()))
            .build();
        return new InMemoryUserRepository(ImmutableSet.of(admin, user));
    }

}
