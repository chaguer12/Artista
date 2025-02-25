package project.Artista.dto.records.studio;

import jakarta.validation.constraints.NotEmpty;

import java.util.Optional;

public record StudioUpdateDTO(

        Optional<String> name,

        Optional<String> description,

        Optional<String> city,

        Optional<String> address,

        Optional<String> phone
) {
}
