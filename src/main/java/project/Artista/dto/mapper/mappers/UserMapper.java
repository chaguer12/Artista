package project.Artista.dto.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.dto.mapper.GenericMapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.model.user.User;

@Mapper(config = GenericMapper.class)
public interface UserMapper extends GenericMapper<User, UserReqDTO, UserResDTO> {
}
