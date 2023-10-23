package pod.tsu.spring.config;

import com.google.common.collect.ImmutableSet;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pod.tsu.spring.models.Role;
import pod.tsu.spring.models.UserEntity;
import pod.tsu.spring.repository.RoleRepository;
import pod.tsu.spring.repository.UserRepository;
import pod.tsu.spring.repository.impl.InMemoryRoleRepository;
import pod.tsu.spring.repository.impl.InMemoryUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public SecurityConfig() {
        logger.info("Created");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
            .username("admin")
            .password("password")
            .roles("ADMIN")
            .build();
        UserDetails user = User.builder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public RoleRepository roleRepository() {
        Set<Role> roles = ImmutableSet.of(
             Role.builder().id(1000).name("ADMIN").build(),
             Role.builder().id(1001).name("USER").build()
        );
        return new InMemoryRoleRepository(roles);
    }

    @Bean
    public UserRepository userRepository() {
        Set<UserEntity> users = new HashSet<>();
        return new InMemoryUserRepository(users);
    }

}
