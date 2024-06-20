package pod.tsu.spring.secureapi.service;

import pod.tsu.spring.secureapi.model.blog.BlogPost;

import java.util.List;

public interface BlogService {

    List<BlogPost> listAllPosts();

    BlogPost findPostById(int id);

}
