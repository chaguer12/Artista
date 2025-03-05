package project.Artista.dto.records.user;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        long expiresIn
) {
}
