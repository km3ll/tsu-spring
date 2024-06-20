package pod.tsu.spring.secureapi.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String username;
    private String password;

}
