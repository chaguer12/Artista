package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.PhotoAssociation;

public interface PhotoAssociationRepo extends JpaRepository<PhotoAssociation,Integer> {
}
