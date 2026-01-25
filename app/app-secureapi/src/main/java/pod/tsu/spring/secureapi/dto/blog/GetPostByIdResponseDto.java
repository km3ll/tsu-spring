package pod.tsu.spring.secureapi.dto.blog;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetPostByIdResponseDto {

    private String timestamp;
    private PostDto post;

}
