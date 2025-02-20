package project.Artista.dto.records.blogPost;

import project.Artista.dto.records.user.UserResDTO;

import java.sql.Date;

public record BlogResDTO(
        int id,
        String title,
        String content,
        Date publicationDate,
        String image,
        UserResDTO auteur

) {
}
