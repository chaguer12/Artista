package project.Artista.dto.records.user;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        String refreshToken,
        long expiresIn
) {
}
