package project.Artista.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Artista.dto.records.blogPost.BlogReqDTO;
import project.Artista.dto.records.blogPost.BlogResDTO;
import project.Artista.service.BlogPostServiceInterface;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogPostController {

    private final BlogPostServiceInterface blogPostService;

    @PostMapping("/add")
    public ResponseEntity<BlogResDTO> save(BlogReqDTO blogDTO){
        BlogResDTO response = blogPostService.saveBlogPost(blogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<BlogResDTO>> getAll(){
        List<BlogResDTO> response = blogPostService.getAllBlogPosts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
