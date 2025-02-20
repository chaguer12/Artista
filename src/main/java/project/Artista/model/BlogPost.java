package project.Artista.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.PersistenceCreator;
import project.Artista.model.user.User;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE , onConstructor_ = @PersistenceCreator)
@NoArgsConstructor
@Builder
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String title;
    private String content;
    @Temporal(TemporalType.DATE)
    private Date publicationDate;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auteur_id",nullable = false)
    private User auteur;
}
