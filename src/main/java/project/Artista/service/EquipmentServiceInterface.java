package project.Artista.service;

import project.Artista.dto.records.equipment.EquipmentReqDTO;
import project.Artista.dto.records.equipment.EquipmentResDTO;
import project.Artista.dto.records.equipment.EquipmentUpdateDTO;
import project.Artista.model.enums.PhotoType;

import java.util.List;

public interface EquipmentServiceInterface {
    EquipmentResDTO saveEquipment (EquipmentReqDTO equipmentReqDTO);
    EquipmentResDTO updateEquipment (int id, EquipmentUpdateDTO equipmentReqDTO);
    void deleteEquipment (int id);
    List<EquipmentResDTO> getAllEquipment();
    void associateImage(int photoId, int equipmentId, PhotoType type);


}
