package project.Artista.dto.records.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserReqDTO(
    @NotNull
    String fullName,
    @NotNull
    @Size(min = 6 ,max = 40)
    String userName,
    @NotNull
    @Size(min = 8)
    String password,
    @NotNull
    @Size(min = 8)
    String confirmPassword,
    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email cannot be empty")
    String email

) {
}
