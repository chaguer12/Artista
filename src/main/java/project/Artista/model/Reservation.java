package project.Artista.model;

import jakarta.persistence.*;
import lombok.*;
import project.Artista.model.user.Client;
import project.Artista.model.user.Provider;

@Entity
@Table(name = "reservations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
}
