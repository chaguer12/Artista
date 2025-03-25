package project.Artista.service;

import org.springframework.web.multipart.MultipartFile;
import project.Artista.dto.records.equipment.EquipmentReqDTO;
import project.Artista.dto.records.equipment.EquipmentResDTO;
import project.Artista.dto.records.equipment.EquipmentUpdateDTO;
import project.Artista.model.enums.PhotoType;

import java.io.IOException;
import java.util.List;

public interface EquipmentServiceInterface {
    EquipmentResDTO saveEquipment (EquipmentReqDTO equipmentReqDTO);
    EquipmentResDTO updateEquipment (int id, EquipmentUpdateDTO equipmentReqDTO);
    void deleteEquipment (int id);
    List<EquipmentResDTO> getAllEquipment();
    void associateImage(int photoId, int equipmentId, PhotoType type);
    String uploadPhoto(MultipartFile filen, int equipmentId) throws IOException;


}
