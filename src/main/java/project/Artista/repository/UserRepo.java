package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail (String email);
    boolean existsByUserName(String username);
    boolean existsById(int id);
    Optional<User> getById(int id);
    List<User> findAll();
}
