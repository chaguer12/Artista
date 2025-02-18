package project.Artista.model.user;

import jakarta.persistence.*;
import lombok.*;
import project.Artista.model.enums.Role;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public abstract class User {
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
    private String profilePic;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;



}
