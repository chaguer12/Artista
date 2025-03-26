package project.Artista.dto.records.blogPost;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.model.user.User;

public record BlogReqDTO(
        @NotEmpty(message = "Title must not be empty")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
        String title,

        @NotEmpty(message = "Content must not be empty")
        @Size(min = 10, max = 5000, message = "Content must be between 10 and 5000 characters")
        String content,

        @NotNull(message = "Author ID must not be null")
        @Positive(message = "Author ID must be positive")
        int auteur_id,

        @NotNull(message = "Photo must not be null")
        @Size(max = 5242880, message = "Photo size must not exceed 5MB")
        MultipartFile photo

) {
}
