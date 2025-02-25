package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.user.Admin;


import java.util.List;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Admin findByUserName(String username);
    Admin findByEmail(String email);
    boolean existsByEmail (String email);
    boolean existsByUserName(String username);
    boolean existsById(int id);
    Admin getById(int id);
    List<Admin> findAll();

}
