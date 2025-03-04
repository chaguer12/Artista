package project.Artista.service;

import project.Artista.dto.records.reservation.ReservationReqDTO;
import project.Artista.dto.records.reservation.ReservationResDTO;
import project.Artista.dto.records.reservation.ReservationUpdateDTO;

import java.util.List;

public interface ReservationServiceInterface {
    ReservationResDTO saveReservation(ReservationReqDTO reservationDTo);
    ReservationResDTO updateReservation(ReservationUpdateDTO reservationDTO);
    void deleteReservation(long id);
    ReservationResDTO getReservationById(long id);
    List<ReservationResDTO> getAllReservations();

}
