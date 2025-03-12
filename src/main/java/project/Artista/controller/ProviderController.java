package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.service.ProviderServiceInterface;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderServiceInterface providerService;

    @PostMapping
    public ResponseEntity<UserResDTO> signUp(@Valid @RequestBody UserReqDTO userDTO) {
        UserResDTO response  = providerService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResDTO> getUserById(@PathVariable int id) {
        UserResDTO response = providerService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserResDTO> updateUser(@PathVariable int id, @Valid @RequestBody UserUpdateDTO userDTO) {
        UserResDTO response = providerService.updateUser(id,userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean response = providerService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserResDTO>> getAllUsers() {
        List<UserResDTO> response = providerService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
