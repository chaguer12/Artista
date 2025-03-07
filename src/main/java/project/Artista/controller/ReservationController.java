package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.reservation.ReservationReqDTO;
import project.Artista.dto.records.reservation.ReservationResDTO;
import project.Artista.service.ReservationServiceInterface;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationServiceInterface reservationService;

    @PostMapping
    public ResponseEntity<ReservationResDTO> saveReservation(@RequestBody @Valid ReservationReqDTO reservationDTO) {
        ReservationResDTO response = reservationService.saveReservation(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ReservationResDTO>> getAllReservations() {
        List<ReservationResDTO> response = reservationService.getAllReservations();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ReservationResDTO>> getReservationById(@PathVariable String id) {
        List<ReservationResDTO> response = reservationService.getReservationsByUserId(Integer.parseInt(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
