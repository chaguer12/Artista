package project.Artista.dto.records.user;

import project.Artista.model.enums.Role;

public record UserResDTO(

        int id,
        String userName,
        String fullName,
        String email,
        String profilePic,
        String address,
        Role role
) {}
