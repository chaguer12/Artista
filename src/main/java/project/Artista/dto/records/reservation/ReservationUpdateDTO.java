package project.Artista.dto.records.reservation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public record ReservationUpdateDTO(
        Optional<LocalDate> date,
        Optional<LocalTime> startTime,
        Optional<LocalTime> endTIme
) {

}
