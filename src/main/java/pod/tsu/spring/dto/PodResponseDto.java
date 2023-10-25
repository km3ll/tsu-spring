package pod.tsu.spring.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PodResponseDto {

    private String datetime;
    private String message;

}