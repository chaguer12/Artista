package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.Equipment;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {
    Equipment findByName(String name);

}
