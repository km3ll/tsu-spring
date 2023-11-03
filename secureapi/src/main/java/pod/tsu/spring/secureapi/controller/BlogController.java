package pod.tsu.spring.secureapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.secureapi.dto.blog.GetPostByIdResponseDto;
import pod.tsu.spring.secureapi.dto.blog.GetPostsResponseDto;
import pod.tsu.spring.secureapi.dto.blog.PostDto;
import pod.tsu.spring.secureapi.model.blog.BlogPost;
import pod.tsu.spring.secureapi.service.BlogService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private BlogService blogService;

    private PostDto mapToDto(BlogPost blogPost) {
        return PostDto.builder()
            .id(blogPost.getId())
            .title(blogPost.getTitle())
            .body(blogPost.getBody())
            .build();
    }

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
        logger.info("Created");
    }

    @GetMapping("/posts")
    public ResponseEntity<GetPostsResponseDto> getPosts() {

        List<PostDto> posts = blogService.listAllPosts().stream()
            .map(this::mapToDto)
            .toList();

        GetPostsResponseDto body = GetPostsResponseDto.builder()
            .timestamp(dateTimeFormatter.format(LocalDateTime.now()))
            .posts(posts)
            .build();

        return ResponseEntity.ok(body);

    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<GetPostByIdResponseDto> getPostById(@PathVariable int id) {

        PostDto post = mapToDto(blogService.findPostById(id));

        GetPostByIdResponseDto body = GetPostByIdResponseDto.builder()
            .timestamp(dateTimeFormatter.format(LocalDateTime.now()))
            .post(post)
            .build();

        return ResponseEntity.ok(body);

    }

}