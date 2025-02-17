package project.Artista.dto.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;

@MapperConfig(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GenericMapper<Entity, RequestDTO, ResponseDTO> {
    Entity toEntity(RequestDTO reqDTO);
    ResponseDTO toDTO(Entity entity);
}
