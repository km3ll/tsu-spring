package pod.tsu.spring.secureapi.dto.blog;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostDto {

    private int id;
    private String title;
    private String body;

}
