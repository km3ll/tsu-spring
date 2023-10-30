package pod.tsu.spring.secureapi.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pod.tsu.spring.secureapi.client.BlogClient;
import pod.tsu.spring.secureapi.model.blog.BlogPost;

import java.util.List;

@Component
public class BlogClientImpl implements BlogClient {

    private final Logger logger = LoggerFactory.getLogger(BlogClientImpl.class);
    private final String baseUrl = "https://jsonplaceholder.typicode.com";

    private RestTemplate restTemplate;

    @Autowired
    public BlogClientImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        logger.info("Created");
    }

    @Override
    public List<BlogPost> getAllPosts() {

        String url = baseUrl + "/posts";
        RequestEntity<Void> requestBody = null;
        ParameterizedTypeReference<List<BlogPost>> responseType = new ParameterizedTypeReference<List<BlogPost>>(){};

        return restTemplate
            .exchange(url, HttpMethod.GET, requestBody, responseType)
            .getBody();

    }

}
