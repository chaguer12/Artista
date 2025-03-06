package project.Artista.model.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import project.Artista.model.enums.Role;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
@SuperBuilder
@Getter
@Setter
public class Admin extends User{



}
