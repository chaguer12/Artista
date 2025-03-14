package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.user.Admin;


import java.util.List;
import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Admin findByUserName(String username);
    Optional<Admin> findByEmail(String email);
    boolean existsByEmail (String email);
    boolean existsByUserName(String username);
    boolean existsById(int id);
    Admin getById(int id);
    List<Admin> findAll();

}
