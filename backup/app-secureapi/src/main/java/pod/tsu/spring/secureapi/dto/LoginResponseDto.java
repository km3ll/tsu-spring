package pod.tsu.spring.secureapi.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String accessToken;
    private String tokenType = "Bearer ";

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

}
