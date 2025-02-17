package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.Artista.model.User;


public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUserName(String username);
    User findByEmail(String email);
    boolean existsByEmail (String email);
    boolean existsByUserName(String username);
    boolean existsById(int id);
    User getById(int id);
}
