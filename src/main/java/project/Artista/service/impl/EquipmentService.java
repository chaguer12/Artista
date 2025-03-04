package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.Artista.mapper.mappers.EquipmentMapper;
import project.Artista.dto.records.equipment.EquipmentReqDTO;
import project.Artista.dto.records.equipment.EquipmentResDTO;
import project.Artista.dto.records.equipment.EquipmentUpdateDTO;
import project.Artista.exception.EntityNotFound;
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
    public EquipmentResDTO updateEquipment(int id, EquipmentUpdateDTO equipmentUpdateDTO) {
        Equipment equipment = equipmentRepo.findById(id).orElseThrow(()-> new EntityNotFound("No equipment was found with id :" + id));
        equipmentUpdateDTO.name().ifPresent(equipment::setName);
        equipmentUpdateDTO.description().ifPresent(equipment::setDescription);
        equipmentUpdateDTO.image().ifPresent(equipment::setImage);
        equipmentRepo.save(equipment);
        return equipmentMapper.toDTO(equipment);
    }

    @Override
    public void deleteEquipment(int id) {
        Equipment equipment = equipmentRepo.findById(id).orElseThrow(()-> new EntityNotFound("No equipment was found with id :" + id));
        equipmentRepo.deleteById(id);
    }

    @Override
    public List<EquipmentResDTO> getAllEquipment() {
        List<Equipment> equipmentList = equipmentRepo.findAll();
        return equipmentList.stream().map(equipmentMapper::toDTO).toList();
    }
}
