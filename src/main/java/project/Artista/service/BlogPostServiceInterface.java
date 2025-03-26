package project.Artista.service;

import project.Artista.dto.records.blogPost.BlogReqDTO;
import project.Artista.dto.records.blogPost.BlogResDTO;
import project.Artista.dto.records.blogPost.BlogUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface BlogPostServiceInterface {
    BlogResDTO saveBlogPost(BlogReqDTO blogPost) throws IOException;
    List<BlogResDTO> getAllBlogPosts();
    BlogResDTO getBlogPostById(int id);
    BlogResDTO updateBlogPost(int id, BlogUpdateDTO blogPost);
    void deleteBlogPost(int id);

}
