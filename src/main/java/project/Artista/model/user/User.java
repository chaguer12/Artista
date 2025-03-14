package project.Artista.model.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.PersistenceCreator;
import project.Artista.model.BlogPost;
import project.Artista.model.Photo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE , onConstructor_ = @PersistenceCreator)
@NoArgsConstructor
@SuperBuilder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique = true,nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(columnDefinition = "TEXT")
    private String profilePic;
    private String address;
    private String city;
    @OneToMany(
            mappedBy = "auteur",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<BlogPost> blogSpots = new ArrayList<>();



}
