package pod.tsu.spring.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .mvcMatchers("/pod/status").permitAll()
            .anyRequest().authenticated()
            .and()
            .headers(headers -> headers.frameOptions().sameOrigin())
            .httpBasic(Customizer.withDefaults())
        ;

        return http.build();
    }

}
