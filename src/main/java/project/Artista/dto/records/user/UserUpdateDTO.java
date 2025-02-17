package project.Artista.dto.records.user;

import java.util.Optional;

public record UserUpdateDTO(
        Optional<String> userName,
        Optional<String> fullName,
        Optional<String> email,
        Optional<String> profilePic,
        Optional<String> address,
        Optional<String> password
) {
}
