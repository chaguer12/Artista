package project.Artista.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.mapper.GenericMapper;
import project.Artista.dto.records.studio.StudioReqDTO;
import project.Artista.dto.records.studio.StudioResDTO;
import project.Artista.model.Studio;
@Mapper(config = GenericMapper.class)
public interface StudioMapper extends GenericMapper<Studio, StudioReqDTO, StudioResDTO> {
}
