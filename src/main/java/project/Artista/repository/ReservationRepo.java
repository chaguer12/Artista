package project.Artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Artista.model.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

}
