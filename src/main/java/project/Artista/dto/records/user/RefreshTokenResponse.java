package project.Artista.dto.records.user;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        String accessToken,
        String refreshToken,
        long expiresIn
) {
}
