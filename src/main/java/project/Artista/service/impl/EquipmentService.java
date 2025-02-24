package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.Artista.dto.mapper.mappers.EquipmentMapper;
import project.Artista.dto.records.equipment.EquipmentReqDTO;
import project.Artista.dto.records.equipment.EquipmentResDTO;
import project.Artista.model.Equipment;
import project.Artista.repository.EquipmentRepo;
import project.Artista.service.EquipmentServiceInterface;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EquipmentService implements EquipmentServiceInterface {
    private final EquipmentRepo equipmentRepo;
    private final EquipmentMapper equipmentMapper;

    @Override
    public EquipmentResDTO saveEquipment(EquipmentReqDTO equipmentReqDTO) {
        Equipment equipment = Equipment.builder()
                .name(equipmentReqDTO.name())
                .description(equipmentReqDTO.description())
                .image(equipmentReqDTO.image())
                .build();
        equipmentRepo.save(equipment);
        return equipmentMapper.toDTO(equipment);

    }

    @Override
    public EquipmentResDTO updateEquipment(int id, EquipmentReqDTO equipmentReqDTO) {
        return null;
    }

    @Override
    public void deleteEquipment(int id) {

    }

    @Override
    public List<EquipmentResDTO> getAllEquipment() {
        return List.of();
    }
}
