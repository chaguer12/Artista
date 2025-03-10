package project.Artista.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.mapper.GenericMapper;
import project.Artista.model.user.Admin;

@Mapper(config = GenericMapper.class)
public interface AdminMapper  extends GenericMapper<Admin, UserReqDTO, UserResDTO>{
}
