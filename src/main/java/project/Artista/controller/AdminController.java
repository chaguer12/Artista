package project.Artista.controller;

import com.cloudinary.Cloudinary;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.model.enums.PhotoType;
import project.Artista.service.AdminServiceInterface;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceInterface adminService;
    private final CloudinaryServiceInterface cloudinaryService;
    private final Cloudinary cloudinary;

    @PostMapping
    public ResponseEntity<UserResDTO> signUp(@Valid @RequestBody UserReqDTO user) throws IOException {
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
    @PatchMapping("/admin-upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("email") String email,@RequestParam("file") MultipartFile file) throws IOException {
        String url = adminService.uploadProfilePic(email,file);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}
