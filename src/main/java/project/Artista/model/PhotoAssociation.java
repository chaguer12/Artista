package project.Artista.model;

import jakarta.persistence.*;
import lombok.*;
import project.Artista.model.enums.PhotoType;

@Entity
@Table(name = "photo_associations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @Enumerated(EnumType.STRING)
    private PhotoType type;

    private int entityId;



}
