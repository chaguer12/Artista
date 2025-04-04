package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.Artista.exception.EntityNotFound;
import project.Artista.mapper.mappers.BlogPostMapper;
import project.Artista.dto.records.blogPost.BlogReqDTO;
import project.Artista.dto.records.blogPost.BlogResDTO;
import project.Artista.dto.records.blogPost.BlogUpdateDTO;
import project.Artista.exception.BlogPostNotFound;
import project.Artista.model.BlogPost;
import project.Artista.model.enums.PhotoType;
import project.Artista.model.user.User;
import project.Artista.repository.BlogPostRepo;
import project.Artista.repository.UserRepo;
import project.Artista.service.BlogPostServiceInterface;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogPostService implements BlogPostServiceInterface {

    private final BlogPostRepo blogPostRepo;
    private final BlogPostMapper blogPostMapper;
    private final UserRepo userRepo;
    private final CloudinaryServiceInterface cloudinaryService;
    @Override
    public BlogResDTO saveBlogPost(BlogReqDTO blogPost) throws IOException {
        User auteur = userRepo.getById(blogPost.auteur_id()).orElseThrow(() -> new EntityNotFound("entity not found using id: " + blogPost.auteur_id()));
        BlogPost post = BlogPost.builder()
                .title(blogPost.title())
                .content(blogPost.content())
                .publicationDate(Date.valueOf(LocalDate.now()))
                .auteur(auteur)
                .build();
        blogPostRepo.save(post);
        String imageUrl = cloudinaryService.uploadImage(blogPost.photo(), PhotoType.BLOG_POST,post.getId());
        post.setImage(imageUrl);
        return blogPostMapper.toDTO(post);
    }

    @Override
    public List<BlogResDTO> getAllBlogPosts() {
        List<BlogPost> posts = blogPostRepo.findAll();
        return posts.stream().map(blogPostMapper::toDTO).toList();
    }

    @Override
    public BlogResDTO getBlogPostById(int id) {
        BlogPost post = blogPostRepo.findById(id).orElseThrow(()-> new BlogPostNotFound("No blog post was found with this is: " + id));
        return blogPostMapper.toDTO(post);
    }

    @Override
    public BlogResDTO updateBlogPost(int id, BlogUpdateDTO blogPost) {
        BlogPost post = blogPostRepo.findById(id).orElseThrow(()-> new BlogPostNotFound("No blog post was found with this is: " + id));
        blogPost.title().ifPresent(post::setTitle);
        blogPost.content().ifPresent(post::setContent);
        blogPost.image().ifPresent(post::setImage);
        blogPostRepo.save(post);
        return blogPostMapper.toDTO(post);
    }

    @Override
    public void deleteBlogPost(int id) {
        blogPostRepo.deleteById(id);
    }
}
