package pod.tsu.spring.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pod.tsu.spring.models.Role;
import pod.tsu.spring.models.UserEntity;
import pod.tsu.spring.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        logger.info("Created");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

}
