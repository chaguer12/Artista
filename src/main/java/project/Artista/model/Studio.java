package project.Artista.model;

import jakarta.persistence.*;
import lombok.*;
import project.Artista.model.user.Provider;

@Entity
@Table(name = "studios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String description;
    private String city;
    private String address;
    private String phone;
    private String email;
    private int hourRate;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

}
