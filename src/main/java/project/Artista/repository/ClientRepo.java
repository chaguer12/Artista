package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.user.Client;
import project.Artista.model.user.User;

import java.util.List;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    Client findByUserName(String username);
    Optional<Client> findByEmail(String email);
    boolean existsByEmail (String email);
    boolean existsByUserName(String username);
    boolean existsById(int id);
    Client getById(int id);
    List<Client> findAll();
}
