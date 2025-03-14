package project.Artista.model;

import jakarta.persistence.*;
import lombok.*;
import project.Artista.model.enums.PhotoType;

@Entity
@Table(name = "photos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(columnDefinition = "TEXT")
    private String url;
    private String description;
    @Enumerated(EnumType.STRING)
    private PhotoType type;


}
