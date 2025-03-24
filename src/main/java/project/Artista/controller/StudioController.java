package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.studio.StudioReqDTO;
import project.Artista.dto.records.studio.StudioResDTO;
import project.Artista.dto.records.studio.StudioUpdateDTO;
import project.Artista.service.StudioServiceInterface;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studio")
public class StudioController {

    private final StudioServiceInterface studioService;

    @PostMapping("/add")
    public ResponseEntity<StudioResDTO> save(@RequestBody @Valid StudioReqDTO studioDTO){
        StudioResDTO response = studioService.saveStudio(studioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping
    public ResponseEntity<List<StudioResDTO>> findAll(){
        List<StudioResDTO> response = studioService.getStudios();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudioResDTO> findById(@PathVariable int id){
        StudioResDTO response = studioService.getStudio(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<StudioResDTO> update(@PathVariable int id, @RequestBody StudioUpdateDTO studioDTO){
        StudioResDTO response = studioService.updateStudio(id, studioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudioResDTO> delete(@PathVariable int id){
        studioService.deleteStudio(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/by-provider/{providerId}")
    public ResponseEntity<List<StudioResDTO>> findByProviderId(@PathVariable int providerId){

        List<StudioResDTO> response = studioService.getStudiosByProvider(providerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
