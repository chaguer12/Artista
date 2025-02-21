package project.Artista.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "equipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String description;
    private String image;

}
