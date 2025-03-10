package project.Artista.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.mapper.GenericMapper;
import project.Artista.model.user.Client;

@Mapper(config = GenericMapper.class)
public interface ClientMapper extends GenericMapper<Client, UserReqDTO, UserResDTO>{
}
