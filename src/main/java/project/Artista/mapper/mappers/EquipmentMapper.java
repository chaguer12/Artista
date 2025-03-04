package project.Artista.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.mapper.GenericMapper;
import project.Artista.dto.records.equipment.EquipmentReqDTO;
import project.Artista.dto.records.equipment.EquipmentResDTO;
import project.Artista.model.Equipment;

@Mapper(config = GenericMapper.class)
public interface EquipmentMapper extends GenericMapper<Equipment, EquipmentReqDTO, EquipmentResDTO> {
}
