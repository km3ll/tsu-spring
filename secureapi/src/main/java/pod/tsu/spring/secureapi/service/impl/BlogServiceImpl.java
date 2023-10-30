package pod.tsu.spring.secureapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pod.tsu.spring.secureapi.client.BlogClient;
import pod.tsu.spring.secureapi.model.blog.BlogPost;
import pod.tsu.spring.secureapi.service.BlogService;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    private BlogClient blogClient;

    @Autowired
    public BlogServiceImpl(BlogClient blogClient) {
        this.blogClient = blogClient;
        logger.info("Created");
    }

    @Override
    public List<BlogPost> listAllPosts() {
        return blogClient.getAllPosts();
    }

}