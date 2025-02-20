package project.Artista.dto.records.blogPost;

import java.util.Optional;

public record BlogUpdateDTO(
        Optional<String> title,
        Optional<String> content,
        Optional<String> image

) {
}
