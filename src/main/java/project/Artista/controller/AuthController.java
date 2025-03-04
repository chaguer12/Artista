package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Artista.dto.records.user.LogInDTO;
import project.Artista.dto.records.user.SignUpDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.service.AuthServiceInterface;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceInterface authService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDTO userDTO){
        UserResDTO  response = authService.signUp(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/log-in")
    public ResponseEntity<?> logIn(@RequestBody @Valid LogInDTO userDTO){
        UserResDTO response = authService.logIn(userDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
