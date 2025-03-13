package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.PhotoAssociation;
import project.Artista.model.enums.PhotoType;

import java.util.List;

public interface PhotoAssociationRepo extends JpaRepository<PhotoAssociation, Integer> {
    List<PhotoAssociation> findByTypeAndEntityId(PhotoType type, int entityId);
}
