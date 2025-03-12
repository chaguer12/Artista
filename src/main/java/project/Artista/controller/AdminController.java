package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.service.AdminServiceInterface;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceInterface adminService;

    @PostMapping
    public ResponseEntity<UserResDTO> signUp(@Valid @RequestBody UserReqDTO user) {
        UserResDTO response = adminService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResDTO> getUser(@PathVariable int id) {
        UserResDTO response = adminService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResDTO> updateUser(@PathVariable int id, @RequestBody UserUpdateDTO userDTO) {
        UserResDTO response = adminService.updateUser(id, userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean response = adminService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/get")
    public ResponseEntity<List<UserResDTO>> getUsers() {
        List<UserResDTO> response = adminService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
