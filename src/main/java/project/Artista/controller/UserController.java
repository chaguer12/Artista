package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.service.UserServiceInterface;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final  UserServiceInterface userService;



    @PostMapping("/sign-up")
    public ResponseEntity<UserResDTO> signUp(@Valid @RequestBody UserReqDTO userReq) {
        UserResDTO response = userService.saveUser(userReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
