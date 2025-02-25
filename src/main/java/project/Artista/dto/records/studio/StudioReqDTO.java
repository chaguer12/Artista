package project.Artista.dto.records.studio;

import jakarta.validation.constraints.NotEmpty;

public record StudioReqDTO(
        @NotEmpty
        String name,
        @NotEmpty
        String description,
        @NotEmpty
        String city,
        @NotEmpty
        String address,
        @NotEmpty
        String phone

) {
}
