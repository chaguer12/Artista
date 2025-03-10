package project.Artista.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.mapper.GenericMapper;
import project.Artista.model.user.Provider;
@Mapper(config = GenericMapper.class)
public interface ProviderMapper extends GenericMapper<Provider, UserReqDTO, UserResDTO> {
}
