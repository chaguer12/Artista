package project.Artista.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.dto.records.reservation.ReservationReqDTO;
import project.Artista.dto.records.reservation.ReservationResDTO;
import project.Artista.mapper.GenericMapper;
import project.Artista.model.Reservation;
@Mapper(config = GenericMapper.class)
public interface ReservationMapper extends GenericMapper <Reservation, ReservationReqDTO, ReservationResDTO>{

}
