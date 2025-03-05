package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.Artista.dto.records.reservation.ReservationReqDTO;
import project.Artista.dto.records.reservation.ReservationResDTO;
import project.Artista.dto.records.reservation.ReservationUpdateDTO;
import project.Artista.exception.EntityNotFound;
import project.Artista.mapper.mappers.ReservationMapper;
import project.Artista.model.Reservation;
import project.Artista.repository.ReservationRepo;
import project.Artista.service.ReservationServiceInterface;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepo reservationRepo;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationResDTO saveReservation(ReservationReqDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
                .date(reservationDTO.date())
                .startTime(reservationDTO.startTime())
                .endTime(reservationDTO.endTime())
                .build();
        reservationRepo.save(reservation);
        return reservationMapper.toDTO(reservation);
    }

    @Override
    public ReservationResDTO updateReservation(long reservationId,ReservationUpdateDTO reservationDTO) {
        Reservation reservation = reservationRepo.findById((int) reservationId).orElseThrow(() -> new EntityNotFound("No reservation found using id: " + reservationId));
        reservationDTO.date().ifPresent(reservation::setDate);
        reservationDTO.startTime().ifPresent(reservation::setStartTime);
        reservationDTO.endTIme().ifPresent(reservation::setEndTime);
        reservationRepo.save(reservation);
        return reservationMapper.toDTO(reservation);
    }

    @Override
    public void deleteReservation(long id) {
        reservationRepo.deleteById((int) id);
    }

    @Override
    public ReservationResDTO getReservationById(long id) {
        Reservation reservation = reservationRepo.findById((int) id).orElseThrow(() -> new EntityNotFound("No reservation found using id: " + id));
        return reservationMapper.toDTO(reservation);
    }

    @Override
    public List<ReservationResDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepo.findAll();
        return reservations.stream().map(reservationMapper::toDTO).toList();
    }
}
