package pod.tsu.spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TsuResponseDto {

    private String datetime;
    private String message;

}
