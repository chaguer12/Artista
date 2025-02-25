package project.Artista.dto.records.equipment;

import java.util.Optional;

public record EquipmentUpdateDTO(
        Optional<String> name,
        Optional<String> description,
        Optional<String> image
) {
}
