package pod.tsu.spring.secureapi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class UserEntity {

    private String username;
    private String password;
    private List<Role> roles;

}