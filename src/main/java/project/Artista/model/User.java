package project.Artista.model;

import jakarta.persistence.*;
import lombok.*;
import project.Artista.model.enums.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique = true)
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String profilePic;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;



}
