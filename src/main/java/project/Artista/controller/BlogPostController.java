package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.blogPost.BlogReqDTO;
import project.Artista.dto.records.blogPost.BlogResDTO;
import project.Artista.dto.records.blogPost.BlogUpdateDTO;
import project.Artista.service.BlogPostServiceInterface;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogPostController {

    private final BlogPostServiceInterface blogPostService;

    @PostMapping("/add")
    public ResponseEntity<BlogResDTO> save(@RequestBody @Valid BlogReqDTO blogDTO) throws IOException {
        BlogResDTO response = blogPostService.saveBlogPost(blogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<BlogResDTO>> getAll(){
        List<BlogResDTO> response = blogPostService.getAllBlogPosts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<BlogResDTO> getBlogPost(@PathVariable int id){
        BlogResDTO response = blogPostService.getBlogPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BlogResDTO> update(@PathVariable int id, @Valid @RequestBody BlogUpdateDTO blogDTO){
        BlogResDTO response = blogPostService.updateBlogPost(id,blogDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        blogPostService.deleteBlogPost(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }
}
