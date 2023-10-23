package pod.tsu.spring.models;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserEntity {

    private int id;
    private String username;
    private String password;
    private List<Role> roles;

}


