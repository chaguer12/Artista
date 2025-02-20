package project.Artista.dto.records.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LogInDTO(
        @Email(message = "Email is not valid")
        @NotEmpty(message = "Email cannot be empty")
        String email,
        @NotNull
        @Size(min = 8)
        String password
) {
}
