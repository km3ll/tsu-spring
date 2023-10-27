package pod.tsu.spring.secureapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PodResponseDto {

    private String message;
    private String timestamp;

}