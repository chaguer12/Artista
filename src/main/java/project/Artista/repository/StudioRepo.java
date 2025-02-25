package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.Studio;

public interface StudioRepo extends JpaRepository<Studio, Integer> {
}
