package project.Artista.dto.records.studio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StudioReqDTO(
        @NotNull
        String name,
        @NotEmpty
        String description,
        @NotEmpty
        String city,
        @NotEmpty
        String address,
        @NotEmpty
        String phone,
        @NotEmpty
        @Email
        String email,
        @NotNull
        @Positive
        int hourRate,
        @NotNull
        int provider_id

) {
}
