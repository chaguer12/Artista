package project.Artista.model.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.Artista.model.enums.Role;

@Entity
@DiscriminatorValue("PROVIDER")
@Getter
@Setter
@NoArgsConstructor
public class Provider extends User{
    private final Role role = Role.ROLE_PROVIDER;

    @Builder
    public Provider(String userName, String password, String fullName, String email, String profilePic, String address){
        super(0, userName, password, fullName, email, profilePic, address);
    }

}
