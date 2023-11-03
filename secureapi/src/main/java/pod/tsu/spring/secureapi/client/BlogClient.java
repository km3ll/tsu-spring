package pod.tsu.spring.secureapi.client;

import pod.tsu.spring.secureapi.model.blog.BlogPost;

import java.util.List;

public interface BlogClient {

    List<BlogPost> getAllPosts();

    BlogPost getPostById(int id);

}
