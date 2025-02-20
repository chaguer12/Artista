package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.BlogPost;

public interface BlogPostRepo extends JpaRepository<BlogPost,Integer> {

}
