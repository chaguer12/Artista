package project.Artista.model.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import project.Artista.model.Reservation;
import project.Artista.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Client extends User {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  Role role = Role.ROLE_CLIENT;
    @OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();


}
