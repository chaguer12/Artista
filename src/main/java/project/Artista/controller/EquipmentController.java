    package project.Artista.controller;

    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import project.Artista.dto.records.equipment.EquipmentReqDTO;
    import project.Artista.dto.records.equipment.EquipmentResDTO;
    import project.Artista.dto.records.equipment.EquipmentUpdateDTO;
    import project.Artista.model.enums.PhotoType;
    import project.Artista.service.EquipmentServiceInterface;
    import project.Artista.service.impl.CloudinaryService;

    import java.io.IOException;
    import java.util.List;


    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/equipment")
    public class EquipmentController {
        private final EquipmentServiceInterface equipmentService;
        private final CloudinaryService cloudinaryService;


        @PostMapping("/add")
        public ResponseEntity<EquipmentResDTO> saveEquipment(@RequestBody @Valid EquipmentReqDTO equipment) {

            EquipmentResDTO resp = equipmentService.saveEquipment(equipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        }

        @GetMapping
        public ResponseEntity<List<EquipmentResDTO>> getAllEquipment() {
            List<EquipmentResDTO> resp = equipmentService.getAllEquipment();
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        @PatchMapping("/update/{id}")
        public ResponseEntity<EquipmentResDTO> updateEquipment(@PathVariable int id, @RequestBody @Valid EquipmentUpdateDTO equipment) {
            EquipmentResDTO resp = equipmentService.updateEquipment(id, equipment);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteEquipment(@PathVariable int id) {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.ok("deleted successfully !");
        }
        @PostMapping("/associate-image")
        public ResponseEntity<String> associateImage(
                @RequestParam int photoId,
                @RequestParam int equipmentId,
                @RequestParam PhotoType type) {

            equipmentService.associateImage(photoId, equipmentId, type);
            return ResponseEntity.ok("Photo associated successfully with Equipment.");
        }
        @PatchMapping("/equipment-upload")
        public ResponseEntity<String> uploadEquipmentPhoto(@RequestParam("file") MultipartFile file,@RequestParam int equipmentId) throws IOException {
            String urlResponse = equipmentService.uploadPhoto(file,equipmentId);
            return ResponseEntity.status(HttpStatus.OK).body(urlResponse);

        }
    }
