package project.Artista.dto.records.user;

public record AuthResponse(
        String token,
        long expiresIn
) {
}
