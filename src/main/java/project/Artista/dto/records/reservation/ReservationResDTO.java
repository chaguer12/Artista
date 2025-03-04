package project.Artista.dto.records.reservation;

import java.util.Date;

public record ReservationResDTO(
        long id,
        long clientId,
        long ProviderId,
        Date dateTime
) {
}
