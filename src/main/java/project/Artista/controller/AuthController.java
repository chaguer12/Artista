package project.Artista.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {



    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserReqDTO userDTO){
        UserResDTO response;
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/log-in")
    public ResponseEntity<?> logIn(@RequestBody UserReqDTO userDTO){
        UserResDTO response;
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response)
    }
}
