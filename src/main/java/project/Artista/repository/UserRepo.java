package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.user.User;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUserName(String username);
    User findByEmail(String email);
    boolean existsByEmail (String email);
    boolean existsByUserName(String username);
    boolean existsById(int id);
    User getById(int id);
    List<User> findAll();
}
