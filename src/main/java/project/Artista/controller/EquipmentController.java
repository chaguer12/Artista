package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.equipment.EquipmentReqDTO;
import project.Artista.dto.records.equipment.EquipmentResDTO;
import project.Artista.dto.records.equipment.EquipmentUpdateDTO;
import project.Artista.service.EquipmentServiceInterface;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentServiceInterface equipmentService;

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
    public ResponseEntity<EquipmentResDTO> deleteEquipment(@PathVariable int id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
