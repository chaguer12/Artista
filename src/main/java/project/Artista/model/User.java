package project.Artista.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;
    protected String username;
    protected String password;
    protected String fullName;
    protected String email;
    protected String profilePic;
    protected String address;

    public User(){}
    public User(String username, String password, String fullName, String email, String profilePic, String address) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.profilePic = profilePic;
        this.address = address;
    }
}
