package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.user.Provider;
import project.Artista.model.user.User;

import java.util.List;
import java.util.Optional;

public interface ProviderRepo extends JpaRepository<Provider, Integer> {
    Provider findByUserName(String username);
    Optional<Provider> findByEmail(String email);
    boolean existsByEmail (String email);
    boolean existsByUserName(String username);
    boolean existsById(int id);
    Provider getById(int id);
    List<Provider> findAll();
}
