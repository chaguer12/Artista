package project.Artista.service;

import project.Artista.dto.records.equipment.EquipmentReqDTO;
import project.Artista.dto.records.equipment.EquipmentResDTO;

import java.util.List;

public interface EquipmentServiceInterface {
    EquipmentResDTO saveEquipment (EquipmentReqDTO equipmentReqDTO);
    EquipmentResDTO updateEquipment (int id,EquipmentReqDTO equipmentReqDTO);
    void deleteEquipment (int id);
    List<EquipmentResDTO> getAllEquipment();


}
