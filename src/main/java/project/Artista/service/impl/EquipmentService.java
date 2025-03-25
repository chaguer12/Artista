package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.mapper.mappers.EquipmentMapper;
import project.Artista.dto.records.equipment.EquipmentReqDTO;
import project.Artista.dto.records.equipment.EquipmentResDTO;
import project.Artista.dto.records.equipment.EquipmentUpdateDTO;
import project.Artista.exception.EntityNotFound;
import project.Artista.model.Equipment;
import project.Artista.model.Photo;
import project.Artista.model.PhotoAssociation;
import project.Artista.model.Studio;
import project.Artista.model.enums.PhotoType;
import project.Artista.repository.EquipmentRepo;
import project.Artista.repository.PhotoAssociationRepo;
import project.Artista.repository.PhotoRepo;
import project.Artista.repository.StudioRepo;
import project.Artista.service.EquipmentServiceInterface;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EquipmentService implements EquipmentServiceInterface {
    private final EquipmentRepo equipmentRepo;
    private final EquipmentMapper equipmentMapper;
    private final PhotoAssociationRepo photoAssociationRepo;
    private final PhotoRepo photoRepo;
    private final StudioRepo studioRepo;
    private final CloudinaryService cloudinaryService;

    @Override
    public EquipmentResDTO saveEquipment(EquipmentReqDTO equipmentReqDTO) {
        Studio studio = studioRepo.findById(equipmentReqDTO.studioId()).orElseThrow(() -> new EntityNotFound("Studio not found using id: " + equipmentReqDTO.studioId()));
        Equipment equipment = Equipment.builder()
                .name(equipmentReqDTO.name())
                .description(equipmentReqDTO.description())
                .image(equipmentReqDTO.image())
                .studio(studio)
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
        equipmentRepo.deleteById(equipment.getId());
    }

    @Override
    public List<EquipmentResDTO> getAllEquipment() {
        List<Equipment> equipmentList = equipmentRepo.findAll();
        return equipmentList.stream().map(equipmentMapper::toDTO).toList();
    }

    @Override
    public String uploadPhoto(MultipartFile file,int equipmentId) throws IOException {
        Equipment equipment = equipmentRepo.findById(equipmentId).orElseThrow(()-> new EntityNotFound("No equipment was found with id :" + equipmentId));
        String photo = cloudinaryService.uploadImage(file,PhotoType.EQUIPMENT,equipment.getId());
        equipment.setImage(photo);
        equipmentRepo.save(equipment);
        return photo;
    }

    @Override
    public void associateImage(int photoId, int equipmentId, PhotoType type) {
        Equipment equipment = equipmentRepo.findById(equipmentId)
                .orElseThrow(() -> new EntityNotFound("Equipment not found"));

        Photo photo = photoRepo.findById(photoId)
                .orElseThrow(() -> new EntityNotFound("Photo not found"));

        PhotoAssociation association = PhotoAssociation.builder()
                .photo(photo)
                .type(type)
                .entityId(equipment.getId())
                .build();

        photoAssociationRepo.save(association);
    }
}
