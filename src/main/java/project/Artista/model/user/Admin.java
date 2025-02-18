package project.Artista.model.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.Artista.model.enums.Role;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
@Getter
@Setter
public class Admin extends User{
    private final Role role = Role.ROLE_ADMIN;

    @Builder
    public Admin(String userName, String password, String fullName, String email, String profilePic, String address){
        super(0, userName, password, fullName, email, profilePic, address);
    }
}
