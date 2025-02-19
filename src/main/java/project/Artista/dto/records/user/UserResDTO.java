package project.Artista.dto.records.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import project.Artista.model.enums.Role;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResDTO(

        int id,
        String userName,
        String fullName,
        String email,
        String profilePic,
        String address,
        Role role
) {}
