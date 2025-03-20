package project.Artista.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import project.Artista.dto.records.user.*;
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
    public ResponseEntity<AuthResponse> logIn(@RequestBody @Valid LogInDTO userDTO){
        UserDetails userDetails = authService.logIn(userDTO);
        AuthResponse authResponse = authService.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody String refreshToken){
        String newToken = authService.refreshToken(refreshToken);
        RefreshTokenResponse response = RefreshTokenResponse.builder().
                accessToken(newToken).refreshToken(refreshToken).expiresIn(86400).build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/log-out")
    public ResponseEntity<String> logout(){
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResDTO> getUserProfile(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserResDTO response = authService.getUserProfileByUserName(username);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
