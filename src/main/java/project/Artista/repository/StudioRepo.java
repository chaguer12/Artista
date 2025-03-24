package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.Studio;

import java.util.List;

public interface StudioRepo extends JpaRepository<Studio, Integer> {
    List<Studio> getStudioByProviderId(int providerId);
}
