package project.Artista.dto.records.reservation;

import java.sql.Date;

public record ReservationReqDTO(
        long clientID,
        long providerID,
        Date dateTime
) {
}
