package project.Artista.dto.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.dto.mapper.GenericMapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserReqDTO, UserResDTO> {
}
