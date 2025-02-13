package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.User;


public interface UserRepo extends JpaRepository<User, Integer> {
}
