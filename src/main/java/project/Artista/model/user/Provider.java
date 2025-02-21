package project.Artista.model.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import project.Artista.model.enums.Role;

@Entity
@DiscriminatorValue("PROVIDER")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Provider extends User{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final Role role = Role.ROLE_PROVIDER;
    private boolean isValid = false;



}
