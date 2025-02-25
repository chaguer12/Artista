package project.Artista.dto.records.equipment;

import jakarta.validation.constraints.NotEmpty;

import java.util.Optional;

public record EquipmentReqDTO(
        @NotEmpty
        String name,
        @NotEmpty
        String description,
        @NotEmpty
        String image
) {
}
