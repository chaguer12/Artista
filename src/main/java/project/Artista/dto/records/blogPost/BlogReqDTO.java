package project.Artista.dto.records.blogPost;

import jakarta.validation.constraints.NotEmpty;
import project.Artista.model.user.User;

public record BlogReqDTO(
        @NotEmpty(message = "title must not be empty")
        String title,
        @NotEmpty(message = "content must not be empty")
        String content,
        @NotEmpty(message = "image must not be empty")
        String image,
        int auteur_id

) {
}
