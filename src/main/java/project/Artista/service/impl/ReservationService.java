package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.Artista.dto.records.reservation.ReservationReqDTO;
import project.Artista.dto.records.reservation.ReservationResDTO;
import project.Artista.dto.records.reservation.ReservationUpdateDTO;
import project.Artista.mapper.mappers.ReservationMapper;
import project.Artista.repository.ReservationRepo;
import project.Artista.service.ReservationServiceInterface;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepo reservationRepo;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationResDTO saveReservation(ReservationReqDTO reservationDTo) {
        return null;
    }

    @Override
    public ReservationResDTO updateReservation(ReservationUpdateDTO reservationDTO) {
        return null;
    }

    @Override
    public void deleteReservation(long id) {

    }

    @Override
    public ReservationResDTO getReservationById(long id) {
        return null;
    }

    @Override
    public List<ReservationResDTO> getAllReservations() {
        return List.of();
    }
}
