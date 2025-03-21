package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.service.UserServiceInterface;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final  UserServiceInterface userService;



    @PostMapping
    public ResponseEntity<UserResDTO> signUp(@Valid @RequestBody UserReqDTO userReq) {
        UserResDTO response = userService.saveUser(userReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserResDTO> updateUser(@PathVariable int id, @Valid @RequestBody UserUpdateDTO userUpdate) {
        UserResDTO response = userService.updateUser(id,userUpdate);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResDTO> getUser(@PathVariable int id) {
        UserResDTO response = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean response = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserResDTO>> getAllUsers() {
        //get users
        List<UserResDTO> response = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
