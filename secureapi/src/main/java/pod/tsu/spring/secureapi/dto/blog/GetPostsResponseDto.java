package pod.tsu.spring.secureapi.dto.blog;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetPostsResponseDto {

    private String timestamp;
    private List<PostDto> posts;

}
