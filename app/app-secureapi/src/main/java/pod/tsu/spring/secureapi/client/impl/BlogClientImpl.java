package pod.tsu.spring.secureapi.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pod.tsu.spring.secureapi.client.BlogClient;
import pod.tsu.spring.secureapi.model.blog.BlogPost;

import java.net.URI;
import java.util.List;

@Component
public class BlogClientImpl implements BlogClient {

    private final Logger logger = LoggerFactory.getLogger(BlogClientImpl.class);

    private RestTemplate restTemplate;
    private String baseUrl;

    @Autowired
    public BlogClientImpl(RestTemplateBuilder builder, @Value("${external.blog.url}") String baseUrl) {
        this.restTemplate = builder.build();
        this.baseUrl = baseUrl;
        logger.info("Created");
    }

    @Override
    public List<BlogPost> getAllPosts() {

        URI uri = URI.create(String.format("%s/posts", baseUrl));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ParameterizedTypeReference<List<BlogPost>> responseType = new ParameterizedTypeReference<List<BlogPost>>(){};

        logger.info("Getting all posts from {}", uri);
        return restTemplate.exchange(requestEntity, responseType).getBody();

    }

    @Override
    public BlogPost getPostById(int id) {

        URI uri = URI.create(String.format("%s/posts/%s", baseUrl, id));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ParameterizedTypeReference<BlogPost> responseType = new ParameterizedTypeReference<BlogPost>(){};

        logger.info("Getting post by id from {}", uri);
        return restTemplate.exchange(requestEntity, responseType).getBody();

    }
}
