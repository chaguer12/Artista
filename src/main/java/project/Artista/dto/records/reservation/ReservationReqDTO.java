package project.Artista.dto.records.reservation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationReqDTO(
        long clientID,
        long providerID,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime
        ) {
}
