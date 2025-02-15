package project.Artista.dto.mapper;

public interface GenericMapper<Entity, RequestDTO, ResponseDTO> {
    Entity toEntity(RequestDTO reqDTO);
    ResponseDTO toDTO(Entity entity);
}
