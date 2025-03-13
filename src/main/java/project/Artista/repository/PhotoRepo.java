package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.Photo;

public interface PhotoRepo extends JpaRepository<Photo, Integer> {

}
