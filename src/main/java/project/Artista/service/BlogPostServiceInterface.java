package project.Artista.service;

import project.Artista.dto.records.blogPost.BlogReqDTO;
import project.Artista.dto.records.blogPost.BlogResDTO;

import java.util.List;

public interface BlogPostServiceInterface {
    BlogResDTO saveBlogPost(BlogReqDTO blogPost);
    List<BlogResDTO> getAllBlogPosts();
    BlogResDTO getBlogPostById(int id);
    BlogResDTO updateBlogPost(BlogReqDTO blogPost);
    void deleteBlogPost(int id);

}
